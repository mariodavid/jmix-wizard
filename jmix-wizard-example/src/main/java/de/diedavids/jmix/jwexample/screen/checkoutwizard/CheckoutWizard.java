package de.diedavids.jmix.jwexample.screen.checkoutwizard;

import de.diedavids.jmix.jwexample.entity.Address;
import de.diedavids.jmix.jwexample.entity.Customer;
import de.diedavids.jmix.jwexample.entity.Order;
import de.diedavids.jmix.jwexample.entity.OrderLine;
import de.diedavids.jmix.wizard.Wizard;
import io.jmix.core.TimeSource;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.CheckBox;
import io.jmix.ui.component.ComponentContainer;
import io.jmix.ui.component.Form;
import io.jmix.ui.component.GroupBoxLayout;
import io.jmix.ui.component.HasValue;
import io.jmix.ui.component.ValidationErrors;
import io.jmix.ui.model.CollectionPropertyContainer;
import io.jmix.ui.model.DataContext;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.MessageBundle;
import io.jmix.ui.screen.Screen;
import io.jmix.ui.screen.ScreenValidation;
import io.jmix.ui.screen.StandardOutcome;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static de.diedavids.jmix.wizard.Wizard.*;
import static io.jmix.ui.Notifications.NotificationType.*;

@UiController("CheckoutWizard")
@UiDescriptor("checkout-wizard.xml")
public class CheckoutWizard extends Screen {


    @Autowired
    protected Notifications notifications;
    @Autowired
    protected MessageBundle messageBundle;
    @Autowired
    protected InstanceContainer<Address> invoiceAddressDc;
    @Autowired
    protected InstanceContainer<Customer> customerDc;
    @Autowired
    protected InstanceContainer<Order> orderDc;
    @Autowired
    protected DataContext dataContext;
    @Autowired
    protected InstanceContainer<Address> deliveryAddressDc;
    @Autowired
    protected TimeSource timeSource;
    @Autowired
    protected ScreenValidation screenValidation;
    @Autowired
    protected CollectionPropertyContainer<OrderLine> orderLinesDc;
    @Autowired
    protected Wizard wizard;
    @Autowired
    protected Form customerForm;
    @Autowired
    protected Form communicationForm;
    @Autowired
    protected Form deliveryAddressForm;
    @Autowired
    protected Form invoiceAddressForm;
    @Autowired
    protected CheckBox sameAsDeliveryAddress;
    @Autowired
    protected GroupBoxLayout invoiceAddressGroupBox;

    private CheckoutDataContainerCoordinator dcCoordinator;

    @Subscribe
    protected void onAfterShow(AfterShowEvent event) {

        dcCoordinator = new CheckoutDataContainerCoordinator(
                invoiceAddressDc,
                customerDc,
                orderDc,
                deliveryAddressDc, orderLinesDc, dataContext,
                timeSource
        );

        sameAsDeliveryAddress.setValue(true);

    }


    @Subscribe("wizard")
    protected void onCancelWizardClick(WizardCancelClickEvent event) {
        close(StandardOutcome.DISCARD);
    }

    @Subscribe("wizard")
    protected void onTabPreChange(WizardTabPreChangeEvent event) {
        if (nextTab(event, "step3", "step4")) {
            if (noOrderLinesPresent()) {
                event.preventTabChange();
                warn(messageBundle.formatMessage("validationAtLeastOneOrderLine"));
            }
        } else if (nextTab(event, "step1", "step2")) {
            final ValidationErrors validationErrors = validateComponents(
                    customerForm, communicationForm
            );

            if (!validationErrors.isEmpty()) {
                event.preventTabChange();
                screenValidation.showValidationErrors(this, validationErrors);
            }
        } else if (nextTab(event, "step2", "step3")) {
            final ValidationErrors validationErrors = validateComponents(
                    deliveryAddressForm, invoiceAddressForm
            );

            if (!validationErrors.isEmpty()) {
                event.preventTabChange();
                screenValidation.showValidationErrors(this, validationErrors);
            }
        }

    }

    @Subscribe("sameAsDeliveryAddress")
    protected void onSameAsDeliveryAddressValueChange(HasValue.ValueChangeEvent<Boolean> event) {
        invoiceAddressForm.setVisible(!event.getValue());
    }



    private ValidationErrors validateComponents(ComponentContainer... containers) {

        final List<ComponentContainer> componentContainers = new ArrayList<>(Arrays.asList(containers));

        if (CollectionUtils.isEmpty(componentContainers)) {
            return ValidationErrors.none();
        }

        final ComponentContainer firstContainer = componentContainers.get(0);
        final ValidationErrors validationErrors = screenValidation
                .validateUiComponents(firstContainer);

        if (componentContainers.size() > 1) {
            componentContainers.remove(0);

            componentContainers.stream()
                    .map(
                            componentContainer -> screenValidation.validateUiComponents(componentContainer))
                    .forEach(validationErrors::addAll);
        }

        return validationErrors;
    }

    private boolean tabChange(WizardTabPreChangeEvent event, String from, String to, Direction direction) {
        return event.getOldTab().equals(wizard.getTab(from)) &&
                event.getNewTab().equals(wizard.getTab(to)) &&
                direction.equals(event.getDirection());
    }
    private boolean nextTab(WizardTabPreChangeEvent event, String from, String to) {
        return tabChange(event, from, to, Direction.NEXT);
    }

    private boolean previousTab(WizardTabPreChangeEvent event, String from, String to) {
        return tabChange(event, from, to, Direction.NEXT);
    }


    private boolean noOrderLinesPresent() {
        return orderLinesDc.getItems().size() == 0;
    }

    private void warn(String message) {
        notifications.create(WARNING)
                .withCaption(message)
                .show();
    }

    @Subscribe("wizard")
    protected void onFinishWizardClick(WizardFinishClickEvent event) {
        dcCoordinator.saveCheckout();

        close(StandardOutcome.COMMIT);
        notifications.create(TRAY)
                .withCaption(messageBundle.getMessage("orderPlaced"))
                .show();
    }

}