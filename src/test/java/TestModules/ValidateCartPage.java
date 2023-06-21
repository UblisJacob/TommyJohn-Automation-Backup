package TestModules;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.tommyjohn.automation.Pages.AccountLoginPage;
import com.tommyjohn.automation.Pages.CollectionPage;
import com.tommyjohn.automation.Pages.HomePage;
import com.tommyjohn.automation.Pages.NavigationFlowPage;
import com.tommyjohn.automation.Pages.ShoppingCartPage;
import com.tommyjohn.automation.utils.CustomUtilities;

import commonMethods.GeneralMethods;

public class ValidateCartPage {

	WebDriver driver;
	GeneralMethods objGeneralMethods = new GeneralMethods();
	String searchText="";

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
	public void TC_Search_InteractWithEmptyCart() throws Exception {	
		new ShoppingCartPage(driver).ValidateEmptyCart();	
	} 
	
	/*@Test
	public void TC_Search_InteractWithGiftWrap() throws Exception {
		new CollectionPage(driver).validateQuickshopFunctionality();
		new ShoppingCartPage(driver).ValidateViewCartLink();
		new ShoppingCartPage(driver).AddGiftWrap();
	}*/
	
	/*@Test
	public void TC_Search_ApplyingDiscountCode() throws Exception {
		new CollectionPage(driver).validateQuickshopFunctionality();
		new ShoppingCartPage(driver).Promo_Code_OncartPage();
		
	}*/
	
	/*@Test
	public void TC_Search_InteractWithActiveCart() throws Exception {
		searchText = objGeneralMethods.readPropertiesFile("searchSleepwearSets");
		new HomePage(driver).validateSearchFunctionEnterKeyword(searchText);
		new CollectionPage(driver).validateQuickshopFunctionalityOnSearchPLP();
		new CollectionPage(driver).validateQuickshopFunctionality();
		new ShoppingCartPage(driver).ValidateViewCartLink();
		new ShoppingCartPage(driver).tieredMeterFunctionality();
		new ShoppingCartPage(driver).AddRemoveProductsFromCart();
		
	}*/
	
	/*@Test
	public void TC_Search_ViewOOSItem() throws Exception {
		searchText = objGeneralMethods.readPropertiesFile("searchBundle");
		new HomePage(driver).validateSearchFunctionEnterKeyword(searchText);
		new CollectionPage(driver).validateQuickshopFunctionalityOnSearchPLP();
		new ShoppingCartPage(driver).ValidateViewCartLink();
		new ShoppingCartPage(driver).OOSValidation();
		
	}*/
	
	@Test
	public void TC_Search_ViewPromotions() throws Exception {
		String accountEmail = objGeneralMethods.readPropertiesFile("loginemail");
		String password = objGeneralMethods.readPropertiesFile("password");
		new AccountLoginPage(driver).loginAccount(accountEmail, password);
		searchText = objGeneralMethods.readPropertiesFile("searchSleepwearSets");
		new HomePage(driver).validateSearchFunctionEnterKeyword(searchText);
		new CollectionPage(driver).validateQuickshopFunctionalityOnSearchPLP();
		new ShoppingCartPage(driver).ValidateViewCartLink();
		new ShoppingCartPage(driver).validatePromotion();
		
	}
	
	
	
	

	@AfterMethod(alwaysRun = true)
	void teardown() {

		// driver.close();
		driver.quit();
	}
	
}
