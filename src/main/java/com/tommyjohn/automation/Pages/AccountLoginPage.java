package com.tommyjohn.automation.Pages;

import org.openqa.selenium.WebDriver;

import com.tommyjohn.automation.Components.AccountLoginComponents;

public class AccountLoginPage extends AccountLoginComponents {

	public AccountLoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// TODO Auto-generated constructor stub
	}

	public String createCustomerAccount(String email, String password) throws Exception {
		return createAccount(email, password);
	}

	public void verifyfieldsandtextsoncreateaccountpage() throws Exception {
		verifyFieldsOnCreateAccount();
	}

	public void verifyerrorsoncreateaccountpage() throws Exception {
		verifyErrosForCreateAccount();
	}

	public void verifyFieldsAndTextsOnRecoverPasswordPage() throws Exception {
		verifyFieldsOnRecoverPasswordPage();
	}

	public void recoverPasswordFunctionality() throws Exception {
		recoverPassword();
	}

	public void verifyErrorsOnRecoverPasswordPage() throws Exception {
		verifyAllErrorsOnRecoverPasswordPage();
	}

	public void verifyAllFieldsAndTextsOnLoginPage() throws Exception {
		verifyAllFieldsOnLoginPage();
	}

	public void loginCustomerAccount(String email, String password) throws Exception {
		verifyAccountLoginFuctionality(email, password);
	}
	
	public void loginAccount(String email, String password) throws Exception {
		verifyAccountLogin(email, password);
	}

	public void verifyErrorsOnLoginPage() throws Exception {
		verifyErrorMessagesOnLoginPage();
	}

	public void verifyResetPasswordFunctionality(String email) throws Exception {
		resetPasswordFunctionality(email);
	}

	/**
	 * Helps create a new password when provided the reset password link received on
	 * user email
	 * 
	 * @param resetLink
	 * @param newPassword
	 * @param Email       To Re-Login With
	 * @throws Exception
	 */
	public void verifyCreateNewPasswordFunctionality(String resetLink, String email, String newPassword)
			throws Exception {
		createNewPasswordFunctionality(resetLink, newPassword);

//		To Verify if password is successfully changed, 
//		Signing out and Signing back in with the changed password
		verifyLoginFunctionality(email, newPassword);
	}

}
