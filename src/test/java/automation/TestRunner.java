package automation;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber-reports/report.html"},
        features = "src/test/resources/features",
        glue = "automation.glue"
)

public class TestRunner {
}
