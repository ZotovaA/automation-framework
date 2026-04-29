package automation.glue;

import automation.config.AutomationFrameworkConfiguration;
import automation.drivers.DriverSingleton;
import automation.pages.*;
import automation.utils.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import automation.utils.Log;

import static org.junit.jupiter.api.Assertions.assertEquals;

@CucumberContextConfiguration
@ContextConfiguration(classes = AutomationFrameworkConfiguration.class)
public class StepDefinition {
    private WebDriver driver;
    private HomePage homePage;
    private SignInPage signInPage;
    private CheckoutPage checkoutPage;
    private ShopPage shopPage;
    private CartPage cartPage;
    ExtentTest test;
    static ExtentSparkReporter spark = new ExtentSparkReporter("report/TestReport.html");
    static ExtentReports report = new ExtentReports();
    static { report.attachReporter(spark); }
    private static Logger log;

    @Autowired
    ConfigurationProperties configurationProperties;

    @Before
    public void initializeObjects(){
        DriverSingleton.getInstance(configurationProperties.getBrowser());
        homePage = new HomePage();
        signInPage = new SignInPage();
        checkoutPage = new CheckoutPage();
        shopPage = new ShopPage();
        cartPage = new CartPage();
        TestCases[] tests = TestCases.values();
        if(Utils.testCount >= tests.length) {
            Utils.testCount = 0;
        }
        test = report.createTest(tests[Utils.testCount].getTestName());
        log = Log.getLogData(Log.class.getName());
        Log.startTest(tests[Utils.testCount].getTestName());
        Utils.testCount++;
    }

    @After
    public void closeObjects() {
        DriverSingleton.closeObjectInstance();
        report.flush();
    }

    @Given("I go to the Website")
    public void i_go_to_the_website() {
        driver = DriverSingleton.getDriver();
        driver.get(Constants.URL);
        Log.info("INFO: Navigating to " + Constants.URL);
        test.log(Status.PASS, "Navigating to " + Constants.URL);
    }

    @When("I click on Sign In button")
    public void i_click_on_sign_in_button() {
        homePage.clickSignIn();
        Log.info("INFO: Open Sign In Window");
        test.log(Status.PASS, "Sign In button has been clicked.");
    }

    @And("I specify my credentials and click Login")
    public void i_specify_my_credentials_and_click_login() {
        signInPage.logIn(configurationProperties.getEmail(), configurationProperties.getPassword());
        Log.info("INFO: Enter email " + configurationProperties.getEmail() + " and password " + configurationProperties.getPassword());
        test.log(Status.PASS, "Log In has been clicked.");
    }

    @When("I add one element to the cart")
    public void i_add_one_element_to_the_cart() {
        homePage.clickShopButton();
        Log.info("INFO: Open Shop Page");
        shopPage.addElementToCart();
        Log.info("INFO: Add element to cart");
        test.log(Status.PASS, "One element was added to the cart.");
    }

    @And("I proceed to checkout")
    public void i_proceed_to_checkout() throws InterruptedException {
        Log.warn("WARN: Have to wait after adding item");
        Thread.sleep(3000);
        shopPage.proceedToCheckout();
        Log.info("INFO: Open Cart Page");
        cartPage.proceedToCheckout();
        Log.info("INFO: Go to checkout");
        test.log(Status.PASS, "We proceed to checkout.");
    }

    @And("I confirm address, shipping, payment and final order")
    public void i_confirm_address_shipping_payment_and_final_order() throws InterruptedException {
        checkoutPage.providePersonalDetails();
        Log.info("INFO: Providing personal details");
        Log.warn("WARN: Have to wait after providing details");
        Thread.sleep(3000);
        checkoutPage.placeOrder();
        test.log(Status.PASS, "We confirm the final order.");
    }

    @Then("I can log into the website")
    public void i_can_log_into_the_website() {
        if(configurationProperties.getUsername().equals(homePage.getUsername())) {
            test.log(Status.PASS, "The authentication is successful.");
            Log.endTest("'Log into website'");
        } else {
            Log.error("ERROR: No log in");
            test.log(Status.FAIL, "The authentication is not successful.");
        }

        assertEquals(configurationProperties.getUsername(), homePage.getUsername());
    }

    @Then("The element are bought")
    public void the_element_are_bought() throws InterruptedException {
        Thread.sleep(3000);
        if(Constants.BOUGHT_ORDER_STATUS.equals(checkoutPage.getOrderStatus())) {
            test.log(Status.PASS, "One item is bought.");
            Log.endTest("'Item was bought'");
        }
        else {
            Log.error("ERROR: Item wasn't bought");
            test.log(Status.FAIL, "One item was not bought.");
        }

        assertEquals(Constants.BOUGHT_ORDER_STATUS, checkoutPage.getOrderStatus());
    }
}
