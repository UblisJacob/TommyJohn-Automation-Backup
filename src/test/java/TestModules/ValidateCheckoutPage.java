package TestModules;

import com.tommyjohn.automation.Components.AddToCartComponents;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.base.ExtentReport;
import com.tommyjohn.automation.Pages.CheckoutPage;
import com.tommyjohn.automation.utils.CustomUtilities;

public class ValidateCheckoutPage extends ExtentReport {
	
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
	
	//@Test
//	public void ValidateExpressCheckoutoptionsAreFunctional() throws Exception
	//{
		//new CheckoutPage(driver).ValidateExpressCheckoutOptions();
//	}

	@Test
	public void ValidatePromoCodeErrorScenarioInInformationPage() throws Exception
	{
		new AddToCartComponents(driver).addsingleproductToCartforFirst20Coupon();
		new CheckoutPage(driver).ValidatePromoCodeErrorScenarioInInformationPage();
	}

	@Test
	public void validateFreeShipping() throws Exception
	{
		new CheckoutPage(driver).validateFreeShipping();
	}

	@Test
	public void ValidatePromoCodeErrorScenarioInShippingPage() throws Exception
	{
		new AddToCartComponents(driver).addsingleproductToCartforFirst20Coupon();
		new CheckoutPage(driver).ValidatePromoCodeErrorScenarioInShippingPage();
	}

	@Test
		public void ValidatePromoCodeErrorScenarioInPaymentPage() throws Exception
	{
		new AddToCartComponents(driver).addsingleproductToCartforFirst20Coupon();
		new CheckoutPage(driver).ValidatePromoCodeErrorScenarioInPaymentPage();
	}

	@Test
	public void ValidatePromoCodeFIRST20InInformationPage() throws Exception
	{
		new AddToCartComponents(driver).addsingleproductToCartforFirst20Coupon();
		new CheckoutPage(driver).ValidatePromoCodeFIRST20InInformationPage();
	}

	@Test
		public void ValidatePromoCodeFIRST20InShippingPage() throws Exception
	{
		new AddToCartComponents(driver).addsingleproductToCartforFirst20Coupon();
		new CheckoutPage(driver).ValidatePromoCodeFIRST20InShippingPage();
	}

	@Test
		public void ValidatePromoCodeFIRST20InPaymentPage() throws Exception
	{
		new AddToCartComponents(driver).addsingleproductToCartforFirst20Coupon();
		new CheckoutPage(driver).ValidatePromoCodeFIRST20InPaymentPage();
	}

//	@Test
	public void ValidatethreeforfourtyeightfunctionalityinInformationPage() throws Exception
	{
		new AddToCartComponents(driver).add3for$48product();
		new CheckoutPage(driver).Validate3for$48functionalityinInformationPage();
	}

//	@Test
	public void ValidatethreeforfourtyeightfunctionalityinShippingPage() throws Exception
	{
		new AddToCartComponents(driver).add3for$48product();
		new CheckoutPage(driver).Validate3for$48functionalityinShippingPage();
	}

//	@Test
	public void ValidatethreeforfourtyeightfunctionalityinPaymentPage() throws Exception
	{
		new AddToCartComponents(driver).add3for$48product();
		new CheckoutPage(driver).Validate3for$48functionalityinPaymentPage();
	}

	@Test
	public void ValidateGuestCheckoutFlow() throws Exception
	{
		new CheckoutPage(driver).Validateguestcheckout();
	}
	
	@Test
	public void ValidateRegisteredUserCheckoutFlow() throws Exception
	{
		new CheckoutPage(driver).Validateregisterusercheckout();
	}
	
	@Test
	public void ValidateInformationPageOfCheckout() throws Exception
	{
		new CheckoutPage(driver).Validateinformationpage();
	}

	@Test
	public void ValidateFieldsInInformationPageOfCheckout() throws Exception
	{
		new CheckoutPage(driver).ValidateFieldsinInformationPage();
	}


	
	@Test
	public void ValidateShippingPageOfCheckout() throws Exception
	{
		new CheckoutPage(driver).Validateshippingpage();
	}
	
	@Test
	public void ValidatePaymentPageOfCheckout() throws Exception
	{
		new CheckoutPage(driver).Validatepaymentpage();
	}

	@Test
	public void ValidateFieldsInPaymentPageOfCheckout() throws Exception
	{
		new CheckoutPage(driver).ValidateFieldsinIPaymentPage();
	}

	@Test
	public void Validatetextmewithnewslettersandoffers() throws Exception
	{
		new CheckoutPage(driver).Validatetextmewithnewslettersandoffers();
	}

	@Test
	public void ValidateSaveMyInformationInPaymentPage() throws Exception
	{
		new CheckoutPage(driver).ValidateSaveMyInformationInPaymentPage();
	}

	@Test
	public void ValidateBillingAddressFieldInPaymentPage() throws Exception
	{
		new CheckoutPage(driver).ValidateBillingAddressFieldInPaymentPage();
	}


	@Test
	public void ValidateRequiredBillingAddressFieldsInPaymentPage() throws Exception
	{
		new CheckoutPage(driver).ValidateRequiredBillingAddressFieldsInPaymentPage();
	}

	@AfterMethod(alwaysRun = true)
	void tearDown() {

		this.driver.close();
//		this.driver.quit();

	}

}
