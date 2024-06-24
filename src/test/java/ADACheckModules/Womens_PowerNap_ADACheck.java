package ADACheckModules;

import com.tommyjohn.automation.ADACheck.ADACheckMethods;
import com.tommyjohn.automation.utils.CustomUtilities;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Womens_PowerNap_ADACheck {

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
    public void ADACheck_Womens_PowerNap_Page() throws Exception {

        driver.get("https://www.tommyjohn.com/collections/womens-all?sort-by=relevance&sort-order=descending&filter%5Bfacet_collection%5D=powernap");
        ADACheckMethods.ADACheck(driver, "Womens_PowerNap_ADACheck");
    }

    @AfterMethod(alwaysRun = true)
    void teardown() {

        driver.close();
        driver.quit();
    }
}
