package ADACheckModules;

import com.tommyjohn.automation.ADACheck.ADACheckMethods;
import com.tommyjohn.automation.utils.CustomUtilities;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Womens_NewArrivals_ADACheck {

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
    public void ADACheck_Womens_NewArrivals_All_Page() throws Exception {

        driver.get("https://www.tommyjohn.com/collections/new-arrivals-womens?sort-by=relevance&sort-order=descending");
        ADACheckMethods.ADACheck(driver, "Womens_NewArrivals_Page_ADACheck");
    }

    @AfterMethod(alwaysRun = true)
    void teardown() {

        driver.close();
        driver.quit();
    }
}
