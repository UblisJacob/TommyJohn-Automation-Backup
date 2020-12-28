package TestScenario;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.tommyjohn.automation.Pages.QuizPage;
import com.tommyjohn.automation.utils.CustomUtilities;

public class ValidateQuizFlow 
{
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
	public void ValidateMensQuizFlow() throws Exception
	{
		new QuizPage(driver).VerifyMensQuizFlow();
	}

	@Test
	public void ValidateMensRetakeQuizFlow() throws Exception
	{
		new QuizPage(driver).VerifyMensRetakeQuizFlow();
	}
	
	@Test
	public void ValidateMensResultPage() throws Exception
	{
		new QuizPage(driver).VerifyMensResultPage();
	}

	@Test
	public void ValidateWomensQuizFlow() throws Exception
	{
		new QuizPage(driver).VerifyWomensQuizFlow();
	}

	@Test
	public void ValidateWomensRetakeQuizFlow() throws Exception
	{
		new QuizPage(driver).VerifyWomensRetakeQuizFlow();
	}
	
	@Test
	public void ValidateWomensResultPage() throws Exception
	{
		new QuizPage(driver).VerifyWomensResultPage();
	}

	@AfterMethod(alwaysRun = true)
	void tearDown() {

		driver.quit();

	}
}
