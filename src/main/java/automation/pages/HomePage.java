package automation.pages;

import automation.drivers.DriverSingleton;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import automation.utils.Constants;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;

    public HomePage(){
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#menu-item-2330 > a")
    private WebElement signInButton;

    @FindBy(css = "#menu-item-1310 > a")
    private WebElement shopButton;

    @FindBy(css = "#menu-item-2333 > a")
    private WebElement username;

    public void clickSignIn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(signInButton));
        signInButton.click();
    }

    public void clickShopButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(shopButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", shopButton);
    }

    public String getUsername() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.TIMEOUT));
        wait.until(ExpectedConditions.visibilityOf(username));
        return username.getText();
    }

}