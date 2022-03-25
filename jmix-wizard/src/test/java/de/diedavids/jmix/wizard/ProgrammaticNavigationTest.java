package de.diedavids.jmix.wizard;

import de.diedavids.jmix.wizard.screen.test_support.FourStepsWizard;
import io.jmix.ui.Screens;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.EventObject;

import static de.diedavids.jmix.wizard.Wizard.*;
import static org.assertj.core.api.Assertions.assertThat;

class ProgrammaticNavigationTest extends WizardUiTest {

    private Wizard wizard;
    private FourStepsWizard screen;

    @BeforeEach
    void openScreen(Screens screens) {

        screen = screens.create(FourStepsWizard.class);

        screen.show();

        wizard = screen.getWizard();
    }

    @Test
    void given_step1IsActive_when_nextTabIsPerformed_then_step2IsActive() {


        // given:
        ensureTabIsActive("step1Tab");

        // when:
        wizard.nextTab();

        // then:
        ensureTabIsActive("step2Tab");
    }

    @Test
    void given_step1IsActive_when_tab3IsOpened_then_step3IsActive() {

        // given:
        ensureTabIsActive("step1Tab");

        // when:
        wizard.setSelectedTab("step3Tab");

        // then:
        ensureTabIsActive("step3Tab");

        // and:
        assertThat(event(WizardTabChangeEvent.class).getDirection())
                .isEqualTo(Direction.NEXT);
    }

    @Test
    void given_tabChangeIsPrevented_when_tab3IsOpened_then_tabChangeHasNotBeenExecuted() {

        // given:
        ensureTabIsActive("step1Tab");

        // and:
        wizard.addWizardTabPreChangeListener(event -> event.preventTabChange());

        // when:
        wizard.setSelectedTab("step3Tab");

        // then:
        ensureTabIsActive("step1Tab");
    }


    @Test
    void given_tab3IsActive_when_tab1IsOpened_then_tab1IsActive() {

        // given:
        wizard.setSelectedTab("step3Tab");

        // when:
        wizard.setSelectedTab("step1Tab");

        // then:
        ensureTabIsActive("step1Tab");

        // and:
        assertThat(event(WizardTabChangeEvent.class).getDirection())
                .isEqualTo(Direction.PREVIOUS);
    }

    private void ensureTabIsActive(String tabName) {
        assertThat(wizard.getSelectedTab())
                .isEqualTo(wizard.getTab(tabName));
    }

    private <T extends EventObject> T event(Class<T> clazz) {
        return screen.receivedEvent(clazz);
    }
}