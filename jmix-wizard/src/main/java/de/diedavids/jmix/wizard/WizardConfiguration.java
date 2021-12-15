package de.diedavids.jmix.wizard;

import de.diedavids.jmix.wizard.impl.WizardImpl;
import de.diedavids.jmix.wizard.loader.WizardLoader;
import io.jmix.core.annotation.JmixModule;
import io.jmix.core.impl.scanning.AnnotationScanMetadataReaderFactory;
import io.jmix.eclipselink.EclipselinkConfiguration;
import io.jmix.ui.UiConfiguration;
import io.jmix.ui.sys.UiControllersConfiguration;
import io.jmix.ui.sys.registration.ComponentRegistration;
import io.jmix.ui.sys.registration.ComponentRegistrationBuilder;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Collections;

@Configuration
@ComponentScan
@ConfigurationPropertiesScan
@JmixModule(dependsOn = {EclipselinkConfiguration.class, UiConfiguration.class})
@PropertySource(name = "de.diedavids.jmix.wizard", value = "classpath:/de/diedavids/jmix/wizard/module.properties")
public class WizardConfiguration {

    @Bean("wizard_WizardUiControllers")
    public UiControllersConfiguration screens(ApplicationContext applicationContext,
                                              AnnotationScanMetadataReaderFactory metadataReaderFactory) {
        UiControllersConfiguration uiControllers
                = new UiControllersConfiguration(applicationContext, metadataReaderFactory);
        uiControllers.setBasePackages(Collections.singletonList("de.diedavids.jmix.wizard"));
        return uiControllers;
    }

    @Bean
    public ComponentRegistration wizardComponent() {
        return ComponentRegistrationBuilder.create(Wizard.NAME)
                .withComponentClass(WizardImpl.class)
                .withComponentLoaderClass(WizardLoader.class)
                .build();
    }

}
