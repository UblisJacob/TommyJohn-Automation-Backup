package ADACheckModules;

import com.tommyjohn.automation.ADACheck.ADACheckMethods;
import com.tommyjohn.automation.utils.CustomUtilities;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Womens_BraQuiz_ADACheck {

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
    public void ADACheck_Womens_BraQuiz_Page() throws Exception {

        driver.get("https://www.tommyjohn.com/pages/quiz?quizID=BR-10005-QZ");
        ADACheckMethods.ADACheck(driver, "Womens_BraQuiz_Page_ADACheck");
    }

    @AfterMethod(alwaysRun = true)
    void teardown() {

        // driver.close();
        driver.quit();
    }


}
