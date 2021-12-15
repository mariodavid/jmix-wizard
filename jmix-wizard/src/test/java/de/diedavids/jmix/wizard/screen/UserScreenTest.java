package de.diedavids.jmix.wizard.screen;

import de.diedavids.jmix.wizard.WizardConfiguration;
import de.diedavids.jmix.wizard.WizardTestConfiguration;
import de.diedavids.jmix.wizard.screen.sample.simple.SimpleWizard;
import io.jmix.ui.testassist.junit.extension.UiTest;
import io.jmix.ui.testassist.ui.AppUiManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.Collections;

@UiTest
@ContextConfiguration(classes = {WizardConfiguration.class, WizardTestConfiguration.class})
class UserScreenTest {

    @Autowired
    protected AppUiManager appUiManager;

    @BeforeEach
    public void beforeEach() {
        appUiManager.exportScreensPackages(Collections.singletonList("de.diedavids.jmix.wizard.screen.sample"));

    }

    @Test
    void openSimpleWizard() {
        SimpleWizard simpleWizard = appUiManager.getScreens().create(SimpleWizard.class);
        simpleWizard.show();
    }
}