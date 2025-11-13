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

//	@Test
	public void ValidatePromoCodeNotAppliedForFinalSaleProducts() throws Exception
	{
		new AddToCartComponents(driver).addFinalSaleProducts();
		new CheckoutPage(driver).ValidatePromoCodeNotAppliedForFinalSaleProducts();
	}

	@Test
	public void VerifyGCFraudDisclosureInCheckoutPage() throws Exception
	{
		new AddToCartComponents(driver).addGCToCart();
		new CheckoutPage(driver).VerifyGCFraudDisclosureInCheckoutPage();
	}

	@Test
	public void VerifySplitBundleProduct() throws Exception
	{
		new AddToCartComponents(driver).addmultipackbundleproduct();
		new CheckoutPage(driver).VerifSplitBundleProduct();
	}

//	@Test
	public void VerifyGCFraudDisclosureInShippingPage() throws Exception
	{
		new AddToCartComponents(driver).addGCToCart();
		new AddToCartComponents(driver).addsingleproductToCart();
		new CheckoutPage(driver).VerifyGCFraudDisclosureInShippingPage();
	}

//	@Test
	public void VerifyGCFraudDisclosureInPaymentPage() throws Exception
	{
		new AddToCartComponents(driver).addGCToCart();
		new AddToCartComponents(driver).addsingleproductToCart();
		new CheckoutPage(driver).VerifyGCFraudDisclosureInPaymentPage();
	}


	@Test
	public void ValidatePromoCodeErrorScenario() throws Exception
	{
		new AddToCartComponents(driver).addsingleproductToCartforFirst20Coupon();
		new CheckoutPage(driver).ValidatePromoCodeErrorScenarioInCheckoutPage();
	}

	@Test
	public void ValidateNonFreeShipping() throws Exception
	{
		new CheckoutPage(driver).validateNonFreeShipping();
	}

	@Test
	public void validateFreeShipping() throws Exception
	{
		new CheckoutPage(driver).validateFreeShipping();
	}

//	@Test
	public void ValidatePromoCodeErrorScenarioInShippingPage() throws Exception
	{
		new AddToCartComponents(driver).addsingleproductToCartforFirst20Coupon();
		new CheckoutPage(driver).ValidatePromoCodeErrorScenarioInShippingPage();
	}

//	@Test
		public void ValidatePromoCodeErrorScenarioInPaymentPage() throws Exception
	{
		new AddToCartComponents(driver).addsingleproductToCartforFirst20Coupon();
		new CheckoutPage(driver).ValidatePromoCodeErrorScenarioInPaymentPage();
	}

	@Test
	public void VerifyCartIconInCheckoutPage() throws Exception
	{
		new AddToCartComponents(driver).addsingleproductToCartforFirst20Coupon();
		new CheckoutPage(driver).VerifyCartIconInCheckoutPage();
	}

	@Test
	public void ValidateCartIconInCheckoutPage() throws Exception
	{
		new AddToCartComponents(driver).addsingleproductToCartforFirst20Coupon();
		new CheckoutPage(driver).ValidateCartIconInCheckoutPage();
	}

	@Test
	public void ValidateOrderProtectionInCheckoutPage() throws Exception
	{
		new AddToCartComponents(driver).addsingleproductToCartforFirst20Coupon();
		new CheckoutPage(driver).ValidateOrderProtectionInCheckoutPage();
	}


	@Test
	public void ValidatePromoCodeFIRST20() throws Exception
	{
		new AddToCartComponents(driver).addsingleproductToCartforFirst20Coupon();
		new CheckoutPage(driver).ValidatePromoCodeFIRST20InCheckoutPage();
	}

//	@Test
		public void ValidatePromoCodeFIRST20InShippingPage() throws Exception
	{
		new AddToCartComponents(driver).addsingleproductToCartforFirst20Coupon();
		new CheckoutPage(driver).ValidatePromoCodeFIRST20InShippingPage();
	}

//	@Test
		public void ValidatePromoCodeFIRST20InPaymentPage() throws Exception
	{
		new AddToCartComponents(driver).addsingleproductToCartforFirst20Coupon();
		new CheckoutPage(driver).ValidatePromoCodeFIRST20InPaymentPage();
	}

	@Test
	public void ValidatefiveforsixtyfivefunctionalityinCheckoutPage() throws Exception
	{
		new AddToCartComponents(driver).add3for$48product();
		new CheckoutPage(driver).Validate5for$65functionalityinCheckoutPage();
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
	public void ValidateContactSectionOfCheckout() throws Exception
	{
		new CheckoutPage(driver).ValidateContactSectionOfCheckout();
	}

	@Test
	public void ValidateFieldsInContactSectionOfCheckout() throws Exception
	{
		new CheckoutPage(driver).ValidateFieldsinCheckout();
	}

	@Test
	public void ValidateShippingSectionOfCheckout() throws Exception
	{
		new CheckoutPage(driver).ValidateshippingSection();
	}
	
	@Test
	public void ValidatePaymentSectionOfCheckout() throws Exception
	{
		new CheckoutPage(driver).Validatepaymentsection();
	}

	@Test
	public void ValidateFieldsInPaymentSectionOfCheckout() throws Exception
	{
		new CheckoutPage(driver).ValidateFieldsinIPaymentSection();
	}

	@Test
	public void Validatetextmewithnewslettersandoffers() throws Exception
	{
		new CheckoutPage(driver).Validatetextmewithnewslettersandoffers();
	}

	@Test
	public void ValidateSaveMyInformationSection() throws Exception
	{
		new CheckoutPage(driver).ValidateSaveMyInformationInPaymentSection();
	}

	@Test
	public void ValidateBillingAddressField() throws Exception
	{
		new CheckoutPage(driver).ValidateBillingAddressFieldInPaymentSection();
	}


	@Test
	public void ValidateRequiredBillingAddressFields() throws Exception
	{
		new CheckoutPage(driver).ValidateRequiredBillingAddressFieldsInPaymentSection();
	}

	@AfterMethod(alwaysRun = true)
	void tearDown() {

		this.driver.close();
//		this.driver.quit();

	}

}
