package TestModules;

import com.tommyjohn.automation.Pages.CollectionPage;
import com.tommyjohn.automation.Pages.GTMValidations;
import com.tommyjohn.automation.utils.CustomUtilities;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ValidateGTM {

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
            public void ValidateGTMForFredHopper() throws Exception {

        new GTMValidations(driver).validateGTMEventsForFredHopper();

    }

    @Test
    public void ValidateSplunk() throws Exception {

        new GTMValidations(driver).validateSplunk();

    }

    @Test
    public void Validatepowerfront() throws Exception {

        new GTMValidations(driver).validatePowerfront();

    }

    @Test
    public void ValidateRudderstack() throws Exception {

        new GTMValidations(driver).validateRudderstack();

    }

    @Test
    public void ValidateKlaviyo() throws Exception {

        new GTMValidations(driver).validateKlaviyo();

    }

    @Test
    public void ValidateContentSquare() throws Exception {

        new GTMValidations(driver).validateContentSquare();

    }



    @AfterMethod(alwaysRun = true)
    void teardown() {

        driver.close();
//		driver.quit();
    }
}

