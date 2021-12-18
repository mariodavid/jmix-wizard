package de.diedavids.jmix.wizard;

import de.diedavids.jmix.wizard.screen.test_support.SimpleWizard;
import io.jmix.ui.Screens;
import io.jmix.ui.component.ComponentContainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RenderingTest extends WizardUiTest {

    private SimpleWizard screen;
    private Wizard wizard;

    @BeforeEach
    void openScreen(Screens screens) {

        screen = screens.create(SimpleWizard.class);

        screen.show();

        wizard = screen.getWizard();
    }

    @Test
    void when_screenIsLoaded_then_wizardComponentIsAvailable() {

        // given:
        assertThat(wizard)
                .isNotNull();

        // when:
        final ComponentContainer step1Tab = (ComponentContainer) wizard.getTabComponent("step1Tab");

        // then:
        assertThat(step1Tab.getComponent("checkBtn"))
                .isNotNull();
    }


    @Test
    void when_screenIsLoaded_then_firstTabIsActive() {

        // expect:
        assertThat(wizard.getSelectedTab())
                .isEqualTo(wizard.getTab("step1Tab"));
    }

    @Test
    void when_screenIsLoaded_then_onlyTheFirstTabIsEnabled() {

        // expect:
        assertThat(wizard.getTab("step1Tab").isEnabled())
                .isTrue();

        // and:
        assertThat(wizard.getTab("step2Tab").isEnabled())
                .isFalse();
    }

}