package automation.pages;

import automation.drivers.DriverSingleton;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import automation.utils.Utils;

public class SignInPage {
    private WebDriver driver;

    public SignInPage(){
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "body > div.xoo-el-container.xoo-el-style-popup.xoo-el-popup-active > div.xoo-el-modal > div > div > div.xoo-el-srcont > div > div > div.xoo-el-section.xoo-el-active > div > form > div.xoo-el-fields-cont > div.xoo-aff-group.xoo-aff-cont-text.one.xoo-aff-cont-required.xoo-el-username_cont > div > input")
    private WebElement signInEmail;

    @FindBy(css = "body > div.xoo-el-container.xoo-el-style-popup.xoo-el-popup-active > div.xoo-el-modal > div > div > div.xoo-el-srcont > div > div > div.xoo-el-section.xoo-el-active > div > form > div.xoo-el-fields-cont > div.xoo-aff-group.xoo-aff-cont-password.one.xoo-aff-cont-required.xoo-el-password_cont > div > input")
    private WebElement signInPassword;

    @FindBy(css = "body > div.xoo-el-container.xoo-el-style-popup.xoo-el-popup-active > div.xoo-el-modal > div > div > div.xoo-el-srcont > div > div > div.xoo-el-section.xoo-el-active > div > form > button")
    private WebElement signInButton;

    public void logIn(String email, String password) {
        signInEmail.sendKeys(email);
        signInPassword.sendKeys(Utils.decode64(password));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", signInButton);
    }
}
