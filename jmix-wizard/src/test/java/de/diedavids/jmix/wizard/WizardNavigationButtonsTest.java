package de.diedavids.jmix.wizard;

import de.diedavids.jmix.wizard.screen.test_support.SimpleWizard;
import io.jmix.ui.Screens;
import io.jmix.ui.component.Button;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WizardNavigationButtonsTest extends WizardUiTest {

    private Wizard wizard;

    @BeforeEach
    void openScreen(Screens screens) {

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