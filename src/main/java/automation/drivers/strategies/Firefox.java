package automation.drivers.strategies;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;;

public class Firefox implements DriverStrategy {
    public WebDriver setStrategy() {
        return new FirefoxDriver();
    }
}
