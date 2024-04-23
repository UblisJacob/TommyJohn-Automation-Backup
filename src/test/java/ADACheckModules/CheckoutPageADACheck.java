package ADACheckModules;

import com.base.ExtentReport;
import com.tommyjohn.automation.ADACheck.ADACheckMethods;
import com.tommyjohn.automation.Components.*;
import com.tommyjohn.automation.PageLocators.CheckoutPageLocators;
import com.tommyjohn.automation.PageLocators.HomePageLocators;
import com.tommyjohn.automation.utils.CustomUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class CheckoutPageADACheck {

    WebDriver driver;
    Wait<WebDriver> wait;
    public JavascriptExecutor jse;

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setup(String browser) {
        try {
            driver = new CustomUtilities().launchtj(browser);

        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Test
    public void ADACheck_CheckoutPage() throws Exception {
        jse = (JavascriptExecutor)driver;
        //Navigating to Information Page
        new AddToCartComponents(driver).addToCart();
        driver.findElement(HomePageLocators.CART_ICON).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        jse.executeScript("arguments[0].click();", driver.findElement(FlyCartComponents.CHECKOUT_BUTTON));
        Reporter.log("Information page displayed");
        ExtentReport.extentTest.info("Information page displayed");
        ADACheckMethods.ADACheck(driver, "InformationPageADACheck");

        //Navigating to Shipping page
        wait.until(ExpectedConditions.visibilityOfElementLocated(CheckoutPageLocators.CHECKOUT_EMAIL));
        driver.findElement(CheckoutPageLocators.CHECKOUT_EMAIL).sendKeys(CustomUtilities.properties.getProperty("checkoutemail"));
        driver.findElement(CheckoutPageLocators.FIRSTNAME_TEXTBOX).clear();
        driver.findElement(CheckoutPageLocators.FIRSTNAME_TEXTBOX).sendKeys("Test");
        driver.findElement(CheckoutPageLocators.LASTNAME_TEXTBOX).clear();
        driver.findElement(CheckoutPageLocators.LASTNAME_TEXTBOX).sendKeys("User");
        driver.findElement(CheckoutPageLocators.ADDRESS1_TEXTBOX).clear();
        driver.findElement(CheckoutPageLocators.ADDRESS1_TEXTBOX).sendKeys("100 Broadway Test");
        Thread.sleep(2000);
        driver.findElement(CheckoutPageLocators.ADDRESS2_TEXTBOX).clear();
        driver.findElement(CheckoutPageLocators.ADDRESS2_TEXTBOX).sendKeys("8th floor");
        driver.findElement(CheckoutPageLocators.CITY_TEXTBOX).clear();
        driver.findElement(CheckoutPageLocators.CITY_TEXTBOX).sendKeys("New York");
        WebElement countrydp = driver.findElement(CheckoutPageLocators.COUNTRY_DROPDOWN);
        Select country = new Select(countrydp);
        country.selectByValue("US");

        WebElement statedp = driver.findElement(CheckoutPageLocators.STATE_DROPDOWN);
        Select state = new Select(statedp);
        state.selectByValue("NY");

        driver.findElement(CheckoutPageLocators.PINCODE_TEXTBOX).clear();
        driver.findElement(CheckoutPageLocators.PINCODE_TEXTBOX).sendKeys("10005");
        Thread.sleep(2000);
        driver.findElement(CheckoutPageLocators.PHONE_TEXTBOX).clear();
        driver.findElement(CheckoutPageLocators.PHONE_TEXTBOX).sendKeys("8889996667");
        Reporter.log("Shipping Address Details entered");
        ExtentReport.extentTest.info("Shipping Address Details entered");
        WebElement Continue_shipping = driver.findElement(CheckoutPageLocators.CONTINUE_TO_SHIPPING_BUTTON);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", Continue_shipping);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@type='radio'])[2]")));
  //      driver.findElement(By.xpath("(//input[@type='radio'])[2]")).click();
        Reporter.log("Shipping page is displayed");
        ExtentReport.extentTest.info("Shipping page is displayed");
        ADACheckMethods.ADACheck(driver, "ShippingPageADACheck");

        //Navigating to Payment Page
        WebElement element = driver.findElement(CheckoutPageLocators.CONTINUE_TO_PAYMENT_BUTTON);
        jse.executeScript("arguments[0].click();", element);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='checkbox']")));
  //      driver.findElement(By.xpath("//input[@type='checkbox']")).click();
        Reporter.log("Payment page is displayed");
        ExtentReport.extentTest.info("Payment page is displayed");
        ADACheckMethods.ADACheck(driver, "PaymentPageADACheck");
        ADACheckMethods.softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    void teardown() {

        // driver.close();
        driver.quit();
    }



}
