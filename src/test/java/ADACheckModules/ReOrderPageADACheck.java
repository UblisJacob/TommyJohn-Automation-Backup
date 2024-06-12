package ADACheckModules;

import com.base.ExtentReport;
import com.tommyjohn.automation.ADACheck.ADACheckMethods;
import com.tommyjohn.automation.PageLocators.AccountLoginLocators;
import com.tommyjohn.automation.PageLocators.AccountPageLocators;
import com.tommyjohn.automation.PageLocators.HomePageLocators;
import com.tommyjohn.automation.PageLocators.ReOrderPageLocators;
import com.tommyjohn.automation.utils.CustomUtilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.NoSuchElementException;

import static com.tommyjohn.automation.PageLocators.HomePageLocators.ACCOUNT_NAV_DROPDOWN;
import static com.tommyjohn.automation.PageLocators.ReOrderPageLocators.HEADER_REORDER_LINK;
import static com.tommyjohn.automation.PageLocators.ReOrderPageLocators.REORDER_TEXT;

public class ReOrderPageADACheck extends ReOrderPageLocators {

    WebElement element;

    WebDriver driver;


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
    public void ADACheck_ReOrderpage() throws Exception {

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
        Actions action = new Actions(driver);

        driver.get("https://www.tommyjohn.com/account/login");
        String email =  CustomUtilities.properties.getProperty("loginemail");
        String password =  CustomUtilities.properties.getProperty("password");
        driver.findElement(AccountLoginLocators.CUSTOMER_EMAIL_INPUT).sendKeys(email);
        driver.findElement(AccountLoginLocators.CUSTOMER_PASSWORD_INPUTBOX).sendKeys(password);
        driver.findElement(AccountLoginLocators.LOGIN_BUTTON).click();
        Thread.sleep(3000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(ACCOUNT_NAV_DROPDOWN));
 //       wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(AccountPageLocators.ACCOUNT_LOGGED_IN_ICON, 0));

        element = driver.findElement(ACCOUNT_NAV_DROPDOWN);
        action.moveToElement(element).perform();

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(HEADER_REORDER_LINK));
            element = driver.findElement(HEADER_REORDER_LINK);
            action.click(element).perform();
        } catch (Exception e) {
            System.out.println("Reorder option not displayed");
            Assert.fail("Reorder option not displayed");
            ExtentReport.extentTest.info("Reorder option not displayed");
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(REORDER_TEXT));
        ADACheckMethods.ADACheck(driver, "ReOrderPageADACheck");
    }

    @AfterMethod(alwaysRun = true)
    void teardown() {

        // driver.close();
        driver.quit();
    }


}
