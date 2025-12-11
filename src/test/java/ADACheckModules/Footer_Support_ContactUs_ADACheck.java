package ADACheckModules;

import com.tommyjohn.automation.ADACheck.ADACheckMethods;
import com.tommyjohn.automation.utils.CustomUtilities;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Footer_Support_ContactUs_ADACheck {

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
    public void ADACheck_Footer_Support_ContactUs_Page() throws Exception {

        driver.get("https://www.tommyjohn.com/pages/help-desk?hcUrl=%2Fen-US");
        ADACheckMethods.ADACheck(driver, "Footer_Support_ContactUs_ADACheck");
    }

    @AfterMethod(alwaysRun = true)
    void teardown() {

        driver.close();
        driver.quit();
    }


}
