package de.diedavids.jmix.wizard;

import de.diedavids.jmix.wizard.screen.test_support.LazyWizard;
import io.jmix.ui.Screens;
import io.jmix.ui.component.ComponentContainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LazyRenderingTest extends WizardUiTest {

    private Wizard wizard;

    @BeforeEach
    void openScreen(Screens screens) {

        LazyWizard screen = screens.create(LazyWizard.class);

        screen.show();

        wizard = screen.getWizard();
    }

    @Test
    void when_screenIsLoaded_then_theFirstLazyTabComponentsAreAvailable() {

        // given:
        assertThat(wizard)
                .isNotNull();

        // and:
        wizard.nextTab();

        // when:
        final ComponentContainer step2Tab = (ComponentContainer) wizard.getTabComponent("step2Tab");

        // then:
        assertThat(step2Tab.getComponent("check2Btn"))
                .isNotNull();
    }
}