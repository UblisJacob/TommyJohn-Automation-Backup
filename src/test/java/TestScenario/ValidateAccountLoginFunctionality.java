package TestScenario;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.tommyjohn.automation.Pages.AccountLoginPage;
import com.tommyjohn.automation.utils.CustomUtilities;

public class ValidateAccountLoginFunctionality {
	public WebDriver driver;
	String accountCreatedEmail;
//	String accountCreatedEmail = "testaccount1558509824283@gmail.com";

//	@BeforeTest(alwaysRun = true)
//	void setup() {
//		try {
//			CustomUtilities.launchtj();
//
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//	}

	@Test
	public void TC_FieldValidationsOnLoginPage() throws Exception
	{
		new AccountLoginPage().verifyAllFieldsOnLoginPage();
	}

	@Test
	public void TC_FieldValidationsOnCreateAccountPage() throws Exception
	{
		new AccountLoginPage().verifyfieldsandtextsoncreateaccountpage();
	}
	
	@Test
	public void TC_FieldValidationsOnRecoverPasswordPage() throws Exception
	{
		new AccountLoginPage().verifyFieldsAndTextsOnRecoverPasswordPage();
	}


	@Test(dependsOnMethods = { "TC_VerifyCreateAccount" })
//	@Test(priority = 3)
	public void TC_VerifyAccountLogin() throws Exception
	{
		new AccountLoginPage().loginCustomerAccount(accountCreatedEmail);
	}

	@Test
	public void TC_VerifyCreateAccount() throws Exception
	{
		accountCreatedEmail = new AccountLoginPage().createCustomerAccount();
	}

	@Test(dependsOnMethods = { "TC_VerifyCreateAccount" })
//	@Test(priority = 2)
	public void TC_VerifyRecoverPassword() throws Exception
	{
		new AccountLoginPage().recoverPasswordFunctionality(accountCreatedEmail);
	}
		
	@Test
	public void TC_VerifyErrorScenariosForLogin() throws Exception
	{
		new AccountLoginPage().verifyErrorsOnLoginPage();
	}
	@Test
	public void TC_VerifyErrorScenariosForCreateAccount() throws Exception
	{
		new AccountLoginPage().verifyerrorsoncreateaccountpage();	
	}
	@Test
	public void TC_VerifyErrorScenariosForRecoverPassword() throws Exception
	{
		new AccountLoginPage().verifyErrorsOnRecoverPasswordPage();
	}

//	@AfterSuite(alwaysRun = true)
//	void tearDown() {
//		try {
//			CustomUtilities.teardown();
//
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//	}


}
