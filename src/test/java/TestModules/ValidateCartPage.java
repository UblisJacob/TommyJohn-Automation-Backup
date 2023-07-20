package TestModules;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.base.ExtentReport;
import com.tommyjohn.automation.Components.AddToCartComponents;
import com.tommyjohn.automation.Components.ProductDetailsPageComponents;
import com.tommyjohn.automation.Pages.AccountLoginPage;
import com.tommyjohn.automation.Pages.CollectionPage;
import com.tommyjohn.automation.Pages.HomePage;
import com.tommyjohn.automation.Pages.ShoppingCartPage;
import com.tommyjohn.automation.commonMethods.GeneralMethods;
import com.tommyjohn.automation.utils.CustomUtilities;

public class ValidateCartPage extends ExtentReport{

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
	public void TC_Cart_InteractWithEmptyCart() throws Exception {	
		new ShoppingCartPage(driver).ValidateEmptyCart();	
	} 

	@Test
	public void TC_Cart_InteractWithGiftWrap() throws Exception {
		new AddToCartComponents(driver).addToCart();
		new ShoppingCartPage(driver).ValidateViewCartLink();
		new ShoppingCartPage(driver).AddGiftWrap();
	}

	@Test
	public void TC_Cart_ApplyingDiscountCode() throws Exception {
		new AddToCartComponents(driver).addToCart();
		new ShoppingCartPage(driver).Promo_Code_OncartPage();

	}

	@Test
	public void TC_Cart_InteractWithActiveCart() throws Exception {

		new AddToCartComponents(driver).addToCart();
		new ShoppingCartPage(driver).ValidateViewCartLink();
		new ShoppingCartPage(driver).tieredMeterpresence();
		new ShoppingCartPage(driver).AddRemoveProductsFromCart();

	}

	@Test
	public void TC_Cart_ViewOOSItem() throws Exception {
		new ProductDetailsPageComponents(driver).verifyBundlePDPs();
		new ShoppingCartPage(driver).ValidateViewCartLink();
		new ShoppingCartPage(driver).OOSValidation();

	}

	@Test
	public void TC_Cart_ViewPromotions() throws Exception {
		String accountEmail = objGeneralMethods.readPropertiesFile("loginemail");
		String password = objGeneralMethods.readPropertiesFile("password");
		new AccountLoginPage(driver).loginAccount(accountEmail, password);
		searchText = objGeneralMethods.readPropertiesFile("searchSleepwearSets");
		new HomePage(driver).validateSearchFunctionEnterKeyword(searchText);
		new CollectionPage(driver).validateQuickshopFunctionalityOnSearchPLP();
		new ShoppingCartPage(driver).ValidateViewCartLink();
		new ShoppingCartPage(driver).validatePromotion();

	}

	@Test
	public void TC_Cart_InteractWithPayments() throws Exception {

		new AddToCartComponents(driver).addToCart();
		new ShoppingCartPage(driver).ValidateViewCartLink();
		new ShoppingCartPage(driver).validateCartPaymentMethods();

	}

	@Test
	public void TC_Cart_InteractWithTieredMeter() throws Exception {

		new AddToCartComponents(driver).addsingleproductToCart();
		new ShoppingCartPage(driver).ValidateViewCartLink();
		new ShoppingCartPage(driver).tieredMeterFunctionality();


	}




	@AfterMethod(alwaysRun = true)
	void teardown() {

		// driver.close();
		driver.quit();
	}

}
