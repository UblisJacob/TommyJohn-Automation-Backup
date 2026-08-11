package TestModules;

import com.tommyjohn.automation.Pages.GTMValidations;
import com.tommyjohn.automation.utils.CustomUtilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.URL;

public class ValidateGTM {

    WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser", "seleniumGridURL"})
    public void setup(@Optional("chrome") String browser,
                      @Optional("") String seleniumGridURL) {
        try {
            if (seleniumGridURL != null && !seleniumGridURL.isEmpty()) {
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setBrowserName(browser);

                driver = new RemoteWebDriver(new URL(seleniumGridURL), caps);
                driver.get("https://www.tommyjohn.com");
                driver.manage().window().maximize();
            } else {
                driver = new CustomUtilities().launchtj(browser);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Test
    public void ValidateGoogleTagAssistantEvents() throws Exception {

        new GTMValidations(driver).validateGTMEvents();

    }

    @Test
    public void ValidateElevarFacebookInitiateCheckout() throws Exception {

        new GTMValidations(driver).validateElevarFacebookInitiateCheckout();

    }



    @Test
    public void ValidateGTMForFredHopper() throws Exception {
        new GTMValidations(driver).validateGTMEventsForFredHopper();
    }

  //  @Test
    public void Validatepowerfront() throws Exception {
        new GTMValidations(driver).validatePowerfront();
    }

 //   @Test
    public void ValidatePixelForDirectMailRetargeting() throws Exception {
        new GTMValidations(driver).ValidatePixelForDirectMailRetargeting();
    }

    @Test
    public void ValidateKlaviyo() throws Exception {
        new GTMValidations(driver).validateKlaviyo();
    }

    @Test
    public void ValidateContentSquare() throws Exception {
        new GTMValidations(driver).validateContentSquare();
    }

    @Test
    public void ValidateClaritas() throws Exception {
        new GTMValidations(driver).validateClaritas();
    }

    @Test
    public void ValidateBing() throws Exception {
        new GTMValidations(driver).validateBing();
    }

    @AfterMethod(alwaysRun = true)
    public void teardown() {
        if (driver != null) {
            driver.quit();  // safer than close()
        }
    }
}
