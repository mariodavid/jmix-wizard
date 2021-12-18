package de.diedavids.jmix.wizard.screen.sample.lazy;


import de.diedavids.jmix.wizard.Wizard;
import io.jmix.ui.screen.Screen;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("ddcw_LazyWizard")
@UiDescriptor("lazy-wizard.xml")
public class LazyWizard extends Screen {


    @Autowired
    Wizard wizard;

    public Wizard getWizard() {
        return wizard;
    }

}