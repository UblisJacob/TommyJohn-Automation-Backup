package TestModules;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.tommyjohn.automation.Pages.AccountLoginPage;
import com.tommyjohn.automation.utils.CustomUtilities;

public class ValidateCreateAccount_Login {
	 WebDriver driver;
	String accountCreatedEmail;
	String newPassword = "NewPassword";
	String initialEmail = "thebayharborbutcher14+";
	String password = "Aretove@123";
//	String accountCreatedEmail = "testaccount1558509824283@gmail.com";
	int count = 20;
	int finalCount;
		
		
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
	public void TC_FieldValidationsOnLoginPage() throws Exception
	{
		new AccountLoginPage(driver).verifyAllFieldsOnLoginPage();
	}

	@Test
	public void TC_FieldValidationsOnCreateAccountPage() throws Exception
	{
		new AccountLoginPage(driver).verifyfieldsandtextsoncreateaccountpage();
	}
	
	@Test
	public void TC_FieldValidationsOnRecoverPasswordPage() throws Exception
	{
		new AccountLoginPage(driver).verifyFieldsAndTextsOnRecoverPasswordPage();
	}


	@Test(dependsOnMethods = { "TC_VerifyCreateAccount" })
//	@Test(priority = 3)
	public void TC_VerifyAccountLogin() throws Exception
	{
		new AccountLoginPage(driver).loginCustomerAccount(accountCreatedEmail, password);
	}

	@Test
	public void TC_VerifyCreateAccount() throws Exception
	{
		accountCreatedEmail = new AccountLoginPage(driver).createCustomerAccount(initialEmail,password);
	}

	@Test(dependsOnMethods = { "TC_VerifyCreateAccount" })
//	@Test(priority = 2)
	public void TC_VerifyRecoverPassword() throws Exception
	{
		new AccountLoginPage(driver).recoverPasswordFunctionality(accountCreatedEmail);
	}
		
	@Test
	public void TC_VerifyErrorScenariosForLogin() throws Exception
	{
		new AccountLoginPage(driver).verifyErrorsOnLoginPage();
	}

	@Test
	public void TC_VerifyErrorScenariosForCreateAccount() throws Exception
	{
		new AccountLoginPage(driver).verifyerrorsoncreateaccountpage();	
	}
	
	@Test
	public void TC_VerifyErrorScenariosForRecoverPassword() throws Exception
	{
		new AccountLoginPage(driver).verifyErrorsOnRecoverPasswordPage();
	}
	
	/**
	 * First Part of Reset Password for Shopify Users TC
	 * @throws Exception
	 */
//	@Test
	public void TC_VerifyCreateNewAccountAndResetPasswordFunctionality() throws Exception{
		
		accountCreatedEmail =  new AccountLoginPage(driver).createCustomerAccount(initialEmail, password);
		new AccountLoginPage(driver).verifyResetPasswordFunctionality(accountCreatedEmail);
		
	}
	
	/**
	 * Second Part of Reset Password for Shopify Users TC,
	 * need to get Manually do the following things
	 * <li> Get reset link from email
	 * <li> After new password is set, for validation code will try to login with email and new password
	 * <li> Hence need to provide email of account for which New password has been set and New password
	 */
	
//	@Test
	public void TC_VerifyCreateNewPasswordFunctionality() throws Exception{
		
		new AccountLoginPage(driver).
		verifyCreateNewPasswordFunctionality("https://www.tommyjohn.com/account/reset/6260479426605/d03e305be881f8fdaa602cd7d5e21ef5-1686314262","thebayharborbutcher14+1686314227286@gmail.com", newPassword);
	}

	@AfterMethod(alwaysRun = true)
	void teardown () {
		
		//driver.close();
		driver.quit();
	}

}
