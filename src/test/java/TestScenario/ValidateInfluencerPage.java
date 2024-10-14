package TestScenario;

import com.tommyjohn.automation.Components.InfluenecerPageComponents;
import com.tommyjohn.automation.Pages.HomePage;
import com.tommyjohn.automation.utils.CustomUtilities;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ValidateInfluencerPage {

    public WebDriver driver;

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
    public void VerifyFavoritesRecs() throws Exception {
        new InfluenecerPageComponents(driver).VerifyFavoritesRecs();
    }

    @AfterMethod(alwaysRun = true)
    void teardown () {

		driver.close();
        driver.quit();
    }


}
