package de.diedavids.jmix.wizard.screen.test_support;

import de.diedavids.jmix.wizard.Wizard;
import io.jmix.ui.component.TabSheet;
import io.jmix.ui.screen.Screen;
import io.jmix.ui.screen.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.EventObject;
import java.util.HashMap;

public abstract class AbstractWizardScreen extends Screen {

    @Autowired
    protected Wizard wizard;

    public Wizard getWizard() {
        return wizard;
    }

    protected HashMap<Class, ? super EventObject> events = new HashMap<>();

    @Subscribe("wizard")
    protected void onTabSheetSelectedTabChange(TabSheet.SelectedTabChangeEvent event) {
        events.put(TabSheet.SelectedTabChangeEvent.class, event);
    }

    @Subscribe("wizard")
    protected void onCancelWizardClick(Wizard.WizardCancelClickEvent event) {
        events.put(Wizard.WizardCancelClickEvent.class, event);
    }

    @Subscribe("wizard")
    protected void onWizardStepPreChangeEvent(Wizard.WizardTabPreChangeEvent event) {
        events.put(Wizard.WizardTabPreChangeEvent.class, event);
    }

    @Subscribe("wizard")
    protected void onWizardStepChangeEvent(Wizard.WizardTabChangeEvent event) {
        events.put(Wizard.WizardTabChangeEvent.class, event);
    }

    @Subscribe("wizard")
    protected void onFinishWizardClick(Wizard.WizardFinishClickEvent event) {
        events.put(Wizard.WizardFinishClickEvent.class, event);
    }

    public <T extends EventObject> T receivedEvent(Class<T> clazz) {
        return (T) events.get(clazz);
    }

}