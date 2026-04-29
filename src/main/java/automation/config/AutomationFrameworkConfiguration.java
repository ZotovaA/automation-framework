package automation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("automation")
@PropertySource("classpath:framework.properties")
public class AutomationFrameworkConfiguration {
    public AutomationFrameworkConfiguration(){}
}
