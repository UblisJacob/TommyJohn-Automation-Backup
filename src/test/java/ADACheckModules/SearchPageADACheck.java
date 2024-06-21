package ADACheckModules;

import com.base.ExtentReport;
import com.tommyjohn.automation.ADACheck.ADACheckMethods;
import com.tommyjohn.automation.PageLocators.AccountLoginLocators;
import com.tommyjohn.automation.commonMethods.GeneralMethods;
import com.tommyjohn.automation.utils.CustomUtilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

import static com.tommyjohn.automation.PageLocators.HomePageLocators.*;

public class SearchPageADACheck {


    WebDriver driver;
    GeneralMethods objGeneralMethods = new GeneralMethods();
    String searchText="";



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
    public void ADACheck_Searchpage() throws Exception {
        Actions action = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
        driver.findElement(SEARCH_TEXTBOX).click();
        driver.findElement(SEARCH_TEXTBOX).clear();
        driver.findElement(SEARCH_ICON_INPUT).sendKeys("Second Skin" + Keys.ENTER);
        Thread.sleep(5000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCHPAGE_PRODUCT_IMAGE));
        ADACheckMethods.ADACheck(driver, "SearchPageADACheck");
    }

    @AfterMethod(alwaysRun = true)
    void teardown() {

        driver.close();
        driver.quit();
    }



}
