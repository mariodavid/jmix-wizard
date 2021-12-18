package de.diedavids.jmix.wizard.screen;

import com.vaadin.ui.Button;
import de.diedavids.jmix.wizard.Wizard;
import de.diedavids.jmix.wizard.WizardConfiguration;
import de.diedavids.jmix.wizard.WizardTestConfiguration;
import de.diedavids.jmix.wizard.screen.sample.simple.SimpleWizard;
import io.jmix.core.CoreConfiguration;
import io.jmix.data.DataConfiguration;
import io.jmix.eclipselink.EclipselinkConfiguration;
import io.jmix.ui.Screens;
import io.jmix.ui.UiConfiguration;
import io.jmix.ui.component.ComponentContainer;
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
class RenderingTest {

    private SimpleWizard screen;
    private Wizard wizard;

    @BeforeEach
    void openSimpleWizard(Screens screens) {

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