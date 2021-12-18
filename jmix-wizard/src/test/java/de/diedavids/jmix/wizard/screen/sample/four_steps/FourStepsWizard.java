package de.diedavids.jmix.wizard.screen.sample.four_steps;


import de.diedavids.jmix.wizard.Wizard;
import io.jmix.ui.screen.Screen;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.EventObject;
import java.util.HashMap;

import static de.diedavids.jmix.wizard.Wizard.*;


@UiController("ddcw_FourStepsWizard")
@UiDescriptor("four-steps-wizard.xml")
public class FourStepsWizard extends Screen {

    @Autowired
    Wizard wizard;

    public Wizard getWizard() {
        return wizard;
    }


    private HashMap<Class, ? super EventObject> events = new HashMap<>();

    @Subscribe("wizard")
    protected void onTabSheetSelectedTabChange(SelectedTabChangeEvent event) {
        events.put(SelectedTabChangeEvent.class, event);
    }

    @Subscribe("wizard")
    protected void onCancelWizardClick(WizardCancelClickEvent event) {
        events.put(WizardCancelClickEvent.class, event);
    }

    @Subscribe("wizard")
    protected void onWizardStepPreChangeEvent(WizardTabPreChangeEvent event) {
        events.put(WizardTabPreChangeEvent.class, event);
    }

    @Subscribe("wizard")
    protected void onWizardStepChangeEvent(WizardTabChangeEvent event) {
        events.put(WizardTabChangeEvent.class, event);
    }

    @Subscribe("wizard")
    protected void onFinishWizardClick(WizardFinishClickEvent event) {
        events.put(WizardFinishClickEvent.class, event);
    }

    public <T extends EventObject> T receivedEvent(Class<T> clazz) {
        return (T) events.get(clazz);
    }


}