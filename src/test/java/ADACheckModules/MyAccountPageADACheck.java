package ADACheckModules;

import com.tommyjohn.automation.ADACheck.ADACheckMethods;
import com.tommyjohn.automation.PageLocators.AccountLoginLocators;
import com.tommyjohn.automation.utils.CustomUtilities;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class MyAccountPageADACheck {

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
    public void ADACheck_MyAccountpage() throws Exception {
        driver.get("https://www.tommyjohn.com/account/login");
        String email =  CustomUtilities.properties.getProperty("loginemail");
        String password =  CustomUtilities.properties.getProperty("password");
        driver.findElement(AccountLoginLocators.CUSTOMER_EMAIL_INPUT).sendKeys(email);
        driver.findElement(AccountLoginLocators.CUSTOMER_PASSWORD_INPUTBOX).sendKeys(password);
        driver.findElement(AccountLoginLocators.LOGIN_BUTTON).click();
        ADACheckMethods.ADACheck(driver, "MyAccountPageADACheck");
    }

    @AfterMethod(alwaysRun = true)
    void teardown() {

        // driver.close();
        driver.quit();
    }


}
