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

public class ShopPage {
    private WebDriver driver;

    public ShopPage() {
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "main > ul > li:nth-child(15) > a.button")
    private WebElement addToCartButton;

    @FindBy(css = "body > nav > div.wb4wp-wrapper > div.wb4wp-right > div > a > span")
    private WebElement numberOfProducts;

    @FindBy(css = "body > nav > div.wb4wp-wrapper > div.wb4wp-right > div > a")
    private WebElement cartButton;

    @FindBy(css = "#main > nav > ul > li:nth-child(2) > a")
    private WebElement secondPage;

    public void addElementToCart(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", secondPage);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartButton);
    }

    public void proceedToCheckout(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cartButton);
    }

    public String getNumberOfProducts(){
        return numberOfProducts.getText();
    }
}
