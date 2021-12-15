package de.diedavids.jmix.wizard.impl;


import io.jmix.core.Messages;
import io.jmix.ui.UiComponents;
import io.jmix.ui.action.Action;
import io.jmix.ui.action.BaseAction;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.ButtonsPanel;
import io.jmix.ui.component.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class WizardButtonsPanel {

    private final UiComponents uiComponents;
    private final Messages messages;

    private final Map<WizardButtonType, BaseAction> actions = new HashMap<>();

    public WizardButtonsPanel(
        UiComponents uiComponents,
        Messages messages
    ) {
        this.uiComponents = uiComponents;
        this.messages = messages;
    }

    public void refresh() {

        actions
            .forEach((key, value) ->
                value.refreshState()
            );
    }

    public enum WizardButtonType {
        CANCEL("cancel"),
        PREVIOUS("prev"),
        NEXT("next"),
        FINISH("finish");

        private final String id;

        WizardButtonType(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }

    public static WizardButtonDescriptor buttonDescriptor(
        WizardButtonType type,
        Consumer<Action.ActionPerformedEvent> clickHandler,
        BaseAction.EnabledRule enabledRule
    ) {
        return new WizardButtonDescriptor(
            type,
            clickHandler,
            enabledRule
        );
    }

    public static class WizardButtonDescriptor {
        final WizardButtonType type;
        final Consumer<Action.ActionPerformedEvent> clickHandler;
        final BaseAction.EnabledRule enabledRule;

        public WizardButtonDescriptor(
            WizardButtonType type,
            Consumer<Action.ActionPerformedEvent> clickHandler,
            BaseAction.EnabledRule enabledRule
        ) {
            this.type = type;
            this.clickHandler = clickHandler;
            this.enabledRule = enabledRule;
        }
    }

    class WizardButtonDescriptors {
        final List<WizardButtonDescriptor> buttonDescriptors;

        WizardButtonDescriptors(
            List<WizardButtonDescriptor> buttonDescriptors
        ) {
            this.buttonDescriptors = buttonDescriptors;
        }

        WizardButtonDescriptor byType(WizardButtonType type) {
            return buttonDescriptors.stream()
                .filter(wizardButtonDescriptor -> wizardButtonDescriptor.type.equals(type))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Wizard Button Type not found"));
        }
    }

    public ButtonsPanel createWizardButtonPanel(
        List<WizardButtonDescriptor> buttonDescriptors
    ) {

        final WizardButtonDescriptors descriptors = new WizardButtonDescriptors(
            buttonDescriptors
        );

        ButtonsPanel wizardButtonsPanel = uiComponents.create(ButtonsPanel.class);
        wizardButtonsPanel.setAlignment(Component.Alignment.TOP_RIGHT);

        wizardButtonsPanel.add(createCancelBtn(descriptors.byType(WizardButtonType.CANCEL)));
        wizardButtonsPanel.add(createBtn(descriptors.byType(WizardButtonType.PREVIOUS)));
        wizardButtonsPanel.add(createBtn(descriptors.byType(WizardButtonType.NEXT)));
        wizardButtonsPanel.add(createBtn(descriptors.byType(WizardButtonType.FINISH)));

        return wizardButtonsPanel;
    }

    private Button createCancelBtn(
        WizardButtonDescriptor descriptor
    ) {
        Button btn = createBtn(descriptor);
        btn.setTabIndex(-1);
        return btn;
    }

    private Button createBtn(
        WizardButtonDescriptor descriptor
    ) {
        String id = descriptor.type.getId();
        Button btn = uiComponents.create(Button.class);
        btn.setId(id);
        final String message = messages.getMessage("de.diedavids.jmix.wizard", id + "BtnCaption");
        final String icon = messages.getMessage("de.diedavids.jmix.wizard", id + "BtnIcon");
        btn.setCaption(message);
        btn.setIcon(icon);

        BaseAction action = wizardAction(btn, descriptor);
        btn.setAction(action);
        actions.put(descriptor.type, action);

        return btn;
    }


    private BaseAction wizardAction(
        Button button,
        WizardButtonDescriptor handler
    ) {
        final BaseAction action = new BaseAction(button.getId())
            .withHandler(handler.clickHandler);
        action.addEnabledRule(handler.enabledRule);
        return action;
    }

}