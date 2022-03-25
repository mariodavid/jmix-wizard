package de.diedavids.jmix.wizard;

import io.jmix.core.annotation.JmixModule;
import io.jmix.core.security.CoreSecurityConfiguration;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import javax.sql.DataSource;

@SpringBootConfiguration
@EnableAutoConfiguration
@JmixModule(id = "de.diedavids.jmix.wizard.test", dependsOn = WizardConfiguration.class)
public class WizardTestConfiguration {

    @Bean
    @Primary
    DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setType(EmbeddedDatabaseType.HSQL)
                .build();
    }

    @EnableWebSecurity
    protected class CoreSecurity extends CoreSecurityConfiguration {

    }
}
