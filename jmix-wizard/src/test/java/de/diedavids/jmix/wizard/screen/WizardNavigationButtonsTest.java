package de.diedavids.jmix.wizard.screen;

import de.diedavids.jmix.wizard.Wizard;
import de.diedavids.jmix.wizard.WizardConfiguration;
import de.diedavids.jmix.wizard.WizardTestConfiguration;
import de.diedavids.jmix.wizard.screen.sample.simple.SimpleWizard;
import io.jmix.core.CoreConfiguration;
import io.jmix.data.DataConfiguration;
import io.jmix.eclipselink.EclipselinkConfiguration;
import io.jmix.ui.Screens;
import io.jmix.ui.UiConfiguration;
import io.jmix.ui.component.Button;
import io.jmix.ui.testassist.junit.UiTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;

import java.util.EventObject;

import static de.diedavids.jmix.wizard.Wizard.*;
import static org.assertj.core.api.Assertions.assertThat;

@UiTest(
        // authenticatedUser = "admin", --> throws 'User admin not found'
        mainScreenId = "TestWizardMainScreen",
        screenBasePackages = "de.diedavids.jmix.wizard.screen.sample"
)
@ContextConfiguration(classes = {
        CoreConfiguration.class,
        DataConfiguration.class,
        EclipselinkConfiguration.class,
        UiConfiguration.class,

        WizardConfiguration.class,
        WizardTestConfiguration.class
})
class WizardNavigationButtonsTest {

    private Wizard wizard;

    @BeforeEach
    void openSimpleWizard(Screens screens) {

        SimpleWizard simpleWizardScreen = screens.create(SimpleWizard.class);

        simpleWizardScreen.show();

        wizard = simpleWizardScreen.getWizard();
    }

    @Test
    void when_screenIsLoaded_then_wizardButtonPanelsAreAvailable() {

        // when:
        assertThat(wizard)
                .isNotNull();

        // then:
        assertThat(button(wizard, "next").isEnabled())
                .isTrue();
        assertThat(button(wizard, "prev").isEnabled())
                .isFalse();
        assertThat(button(wizard, "finish").isEnabled())
                .isFalse();
    }


    @Test
    void when_nextStepIsPerformed_then_wizardButtonStateChangesAccordingly() {

        // when:
        wizard.nextTab();

        // then:
        assertThat(button(wizard, "next").isEnabled())
                .isFalse();
        assertThat(button(wizard, "prev").isEnabled())
                .isTrue();
        assertThat(button(wizard, "finish").isEnabled())
                .isTrue();
    }


    @Test
    void when_nextStepIsPerformed_then_secondStepIsActive() {

        // when:
        wizard.nextTab();

        // then:
        assertThat(wizard.getSelectedTab())
                .isEqualTo(wizard.getTab("step2Tab"));

        assertThat(wizard.getTab("step1Tab").isEnabled())
                .isFalse();
    }

    @Test
    void when_previousStepIsPerformed_then_wizardButtonStateChangesAccordingly() {

        // given:
        wizard.nextTab();

        // when:
        wizard.previousTab();

        // then:
        assertThat(button(wizard, "next").isEnabled())
                .isTrue();
        assertThat(button(wizard, "prev").isEnabled())
                .isFalse();
        assertThat(button(wizard, "finish").isEnabled())
                .isFalse();
    }

    @Test
    void when_previousStepIsPerformed_then_firstStepIsActive() {

        // given:
        wizard.nextTab();

        // when:
        wizard.previousTab();

        // then:
        assertThat(wizard.getSelectedTab())
                .isEqualTo(wizard.getTab("step1Tab"));

        assertThat(wizard.getTab("step2Tab").isEnabled())
                .isFalse();
    }

    private Button button(Wizard wizard, String buttonId) {
        return (Button) wizard.getComponent(buttonId);
    }
}