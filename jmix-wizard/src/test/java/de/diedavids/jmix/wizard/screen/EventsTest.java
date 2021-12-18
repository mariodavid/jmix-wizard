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
class EventsTest {

    private SimpleWizard screen;
    private Wizard wizard;

    @BeforeEach
    void openSimpleWizard(Screens screens) {

        screen = screens.create(SimpleWizard.class);

        screen.show();

        wizard = screen.getWizard();
    }

    @Test
    void wizardIsPresent() {
        // then
        assertThat(wizard)
                .isNotNull();
    }


    @Test
    void when_cancelIsPerformed_then_cancelEventHasBeenReceived() {

        // given:
        final Button cancelBtn = wizardBtn("cancel");

        // when:
        cancelBtn.click();

        // then:
        assertThat(event(WizardCancelClickEvent.class))
                .isNotNull();
    }

    @Test
    void when_nextStepIsPerformed_then_stepChangedEventsAreReceived() {

        // given:
        final Button nextBtn = wizardBtn("next");

        // when:
        nextBtn.click();

        // then:

        assertThat(event(WizardTabPreChangeEvent.class))
                .isNotNull();

        assertThat(event(WizardTabChangeEvent.class))
                .isNotNull();
    }


    @Test
    void when_nextStepIsPerformed_then_eventsContainCorrectDirection() {

        // given:
        final Button nextBtn = wizardBtn("next");

        // when:
        nextBtn.click();

        // then:
        assertThat(event(WizardTabPreChangeEvent.class).getDirection())
                .isEqualTo(Direction.NEXT);

        assertThat(event(WizardTabChangeEvent.class).getDirection())
                .isEqualTo(Direction.NEXT);
    }


    @Test
    void when_prevStepIsPerformed_then_eventsContainCorrectDirection() {

        // given:
        wizardBtn("next").click();

        // when:
        wizardBtn("prev").click();

        // then:
        assertThat(event(WizardTabPreChangeEvent.class).getDirection())
                .isEqualTo(Direction.PREVIOUS);

        assertThat(event(WizardTabChangeEvent.class).getDirection())
                .isEqualTo(Direction.PREVIOUS);
    }


    @Test
    void when_nextStepIsPerformed_then_stepChangedEventHasBeenReceived() {

        // given:
        final Button nextBtn = wizardBtn("next");

        // when:
        nextBtn.click();

        // then:
        assertThat(event(WizardTabChangeEvent.class))
                .isNotNull();
    }

    @Test
    void when_nextStepIsPermitted_then_stepChangedEventHasNotBeenReceived() {

        // given:
        final Button nextBtn = wizardBtn("next");

        wizard.addWizardTabPreChangeListener(event -> event.preventTabChange());

        // when:
        nextBtn.click();

        // then:
        assertThat(event(WizardTabChangeEvent.class))
                .isNull();
    }

    @Test
    void when_finishIsPerformed_then_wizardFinishEventHasBeenReceived() {

        // given:
        wizard.nextTab();

        // and:
        final Button finishBtn = wizardBtn("finish");

        // when:
        finishBtn.click();

        // then:
        assertThat(event(WizardFinishClickEvent.class))
                .isNotNull();
    }

    private <T extends EventObject> T event(Class<T> clazz) {
        return screen.receivedEvent(clazz);
    }

    private Button wizardBtn(String id) {
        return wizard.getComponentNN(id).unwrap(Button.class);
    }
}