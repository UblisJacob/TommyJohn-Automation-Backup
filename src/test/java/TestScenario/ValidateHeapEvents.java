package TestScenario;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.tommyjohn.automation.Components.AddToCartComponents;
import com.tommyjohn.automation.Components.CheckoutPageComponents;
import com.tommyjohn.automation.Components.CollectionPageComponent;
import com.tommyjohn.automation.Components.HomePageComponents;
import com.tommyjohn.automation.Components.ShoppingCartComponents;
import com.tommyjohn.automation.PageLocators.CheckoutPageLocators;
import com.tommyjohn.automation.PageLocators.FlyCartPageLocator;
import com.tommyjohn.automation.PageLocators.HomePageLocators;
import com.tommyjohn.automation.utils.ChromeExtensionUtility;
import com.tommyjohn.automation.utils.CustomUtilities;

import bsh.Console;

public class ValidateHeapEvents {

	WebDriver driver;

	@Test
	public void TC_ValidateHomePageEvents() throws Exception
	{
		List<String> expectedevents = new ArrayList<String>();
		driver = ChromeExtensionUtility.launchchromewithextension();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("javascript:window.scrollBy(0,300)");
		Thread.sleep(3000);
		WebElement homepage_cta = driver.findElement(By.cssSelector("div.overlay-contents > a:nth-child(3)"));
		if(homepage_cta.isDisplayed())
			//homepage_cta.click();
			js.executeScript("arguments[0].click();", homepage_cta);
		else
			throw new Exception("There are no cta to click on homepage");
		String logEntries = driver.manage().logs().getAvailableLogTypes().toString();
		System.out.println("Homepage logentries: "+logEntries);

		LogEntries logclient = driver.manage().logs().get(LogType.BROWSER);
		//	List<String> log = new ArrayList<>();
		for (LogEntry entry : logclient) {

			if(entry.getMessage().contains("chrome-extension"))
			{
				if(entry.getMessage().contains("\"name:\" \"homepage_view\""))
				{
					System.out.println(entry.getMessage());
					Reporter.log("homepage_view event fired on homepage.");
					//count++;
					if(!expectedevents.contains("homepage_view"))
						expectedevents.add("homepage_view");

				}
				if(entry.getMessage().contains("\"name:\" \"homepage_click_content\""))
				{
					System.out.println(entry.getMessage());
					Reporter.log("homepage_click_content event fired on homepage.");
					//count++;
					if(!expectedevents.contains("homepage_click_content"))
						expectedevents.add("homepage_click_content");
				}				
			}
		}

		if(expectedevents.size()!=2)
		{
			Reporter.log("Few events not fired on homepage.");
			throw new Exception("Few events not fired on homepage.");
		}
		//driver.quit();
	}


	@Test
	public void TC_ValidateCollectionPageEvents() throws Exception
	{
		//int count=0;
		List<String> expectedevents = new ArrayList<String>();
		driver = ChromeExtensionUtility.launchchromewithextension();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement element;
		Actions action = new Actions(driver);


		JavascriptExecutor js = (JavascriptExecutor) driver;
		String script = "console.clear();";
		js.executeScript(script);

		new HomePageComponents(driver).navigateToDressesInwomensCategory();

		Thread.sleep(10000);
		driver.findElement(By.cssSelector("div.mega-collection-filters > div:nth-child(2) > a")).click();
		driver.findElement(By.cssSelector("div.mega-collection-filters > div:nth-child(2) > ul > li:nth-child(1) > label > input")).click();
		driver.findElement(By.cssSelector("div.mega-collection-filters > div:nth-child(2) > a")).click();

		Thread.sleep(3000);
		Select selectElement = new Select(driver.findElement(CollectionPageComponent.LAST_FILTER));
		selectElement.selectByValue("Price:ascending");

		Thread.sleep(5000);

		js.executeScript("window.scrollBy(0,250)", "");
		//Hover On product image on collection page

		wait.until(ExpectedConditions.presenceOfElementLocated(CollectionPageComponent.FIRST_PRODUCT_IMAGE));

		element = driver.findElement(CollectionPageComponent.FIRST_PRODUCT_IMAGE);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(3000);
		action.moveToElement(element).build().perform();
		Thread.sleep(2000);
		driver.findElement(CollectionPageComponent.QUICK_SHOP_ATC).click();
		Thread.sleep(3000);

		element = driver.findElement(CollectionPageComponent.QUICK_SHOP_ATC);
		List<WebElement> allElements;
		String classtext;
		allElements = driver.findElements(CollectionPageComponent.QUICK_SHOP_SIZE_LIST);
		System.out.println("All sizes list :: "+ allElements.size());
		for(int i=0;i<allElements.size();i++) 
		{
			classtext=allElements.get(i).getAttribute("class");
			if (!classtext.contains("not-available")) 
			{
				element= driver.findElement(By.cssSelector("div.inline-quick-shop > ul > li:nth-child("+(i+1)+") > label"));
				js.executeScript("arguments[0].click();", element);
				String Selected_size = allElements.get(i).getAttribute("value");
				System.out.println("Selected size is: "+Selected_size);
				Thread.sleep(3000);	
				break;

			}	
			else
			{
				System.out.println("Selected size is OOS");
			}
		}



		Thread.sleep(10000);
		String logEntries = driver.manage().logs().getAvailableLogTypes().toString();
		System.out.println(logEntries);


		LogEntries logclient = driver.manage().logs().get(LogType.BROWSER);
		for (LogEntry entry : logclient) {
			{

				if(entry.getMessage().contains("chrome-extension"))
				{
					if(entry.getMessage().contains("\"name:\" \"view_plp\""))
					{
						System.out.println(entry.getMessage());
						Reporter.log("view_plp event fired on collection page.");
						//	count++;
						if(!expectedevents.contains("view_plp"))
							expectedevents.add("view_plp");


					}
					if(entry.getMessage().contains("\"name:\" \"test_view_plp\""))
					{
						System.out.println(entry.getMessage());
						Reporter.log("test_view_plp event fired on collection page.");
						//count++;
						if(!expectedevents.contains("test_view_plp"))
							expectedevents.add("test_view_plp");


					}

					if(entry.getMessage().contains("\"name:\" \"apply_sort\""))
					{
						System.out.println(entry.getMessage());
						Reporter.log("apply_sort event fired on collection page.");
						//count++;
						if(!expectedevents.contains("apply_sort"))
							expectedevents.add("apply_sort");


					}

					if(entry.getMessage().contains("\"name:\" \"apply_filter\""))
					{
						System.out.println(entry.getMessage());
						Reporter.log("apply_filter event fired on collection page.");
						//	count++;
						if(!expectedevents.contains("apply_filter"))
							expectedevents.add("apply_filter");


					}

					if(entry.getMessage().contains("\"name:\" \"add_to_cart_all\""))
					{
						System.out.println(entry.getMessage());
						Reporter.log("addtocart_all event fired on collection page.");
						//count++;
						if(!expectedevents.contains("add_to_cart_all"))
							expectedevents.add("add_to_cart_all");

					}
				}
			}
		}

		if(expectedevents.size()!=5)
		{
			Reporter.log("Few events not fired on PLP.");
			throw new Exception("Few events not fired on PLP.");
		}
		//driver.quit();
	}

	@Test
	public void TC_ValidatePDPEvents() throws Exception
	{
		//int count=0;
		List<String> expectedevents = new ArrayList<String>();
		driver = ChromeExtensionUtility.launchchromewithextension();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		String script = "console.clear();";
		js.executeScript(script);

		new HomePageComponents(driver).navigateToBoxerBriefInMenCategory();
		Thread.sleep(3000);
		new CollectionPageComponent(driver).navigateToProductDetailsPage();
		Thread.sleep(5000);
		js.executeScript(script);
		new AddToCartComponents(driver).selectSize();
		new AddToCartComponents(driver).atc();


		Thread.sleep(10000);
		LogEntries logclient = driver.manage().logs().get(LogType.BROWSER);
		for (LogEntry entry : logclient) {
			{

				if(entry.getMessage().contains("chrome-extension"))
				{
					if(entry.getMessage().contains("\"name:\" \"add_to_cart_all\""))
					{
						System.out.println(entry.getMessage());
						Reporter.log("add_to_cart_all event fired on pdp.");
						//count++;
						if(!expectedevents.contains("add_to_cart_all"))
							expectedevents.add("add_to_cart_all");


					}

					if(entry.getMessage().contains("\"name:\" \"PDP_AddtoCart\""))
					{
						System.out.println(entry.getMessage());
						Reporter.log("PDP_AddtoCart event fired on pdp.");
						//count++;
						if(!expectedevents.contains("PDP_AddtoCart"))
							expectedevents.add("PDP_AddtoCart");

					}
					if(entry.getMessage().contains("\"name:\" \"PDP_ViewPDP\""))
					{
						System.out.println(entry.getMessage());
						Reporter.log("PDP_ViewPDP event fired on pdp.");
						//count++;
						if(!expectedevents.contains("PDP_ViewPDP"))
							expectedevents.add("PDP_ViewPDP");


					}
					if(entry.getMessage().contains("\"name:\" \"test_view_pdp\""))
					{
						System.out.println(entry.getMessage());
						Reporter.log("test_view_pdp event fired on pdp.");
						//count++;
						if(!expectedevents.contains("test_view_pdp"))
							expectedevents.add("test_view_pdp");


					}

				}
			}
		}

		if(expectedevents.size()!=4)
		{
			Reporter.log("Few events not fired on PDP.");
			throw new Exception("Few events not fired on PDP.");
		}
		//driver.quit();

	}	

	@Test
	public void TC_ValidateCartEvents() throws Exception
	{
		List<String> expectedevents = new ArrayList<String>();
		driver = ChromeExtensionUtility.launchchromewithextension();
        WebDriverWait wait = new WebDriverWait(driver, 30);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String script = "console.clear();";
		js.executeScript(script);

		new HomePageComponents(driver).navigateToBoxerBriefInMenCategory();
		Thread.sleep(3000);
		new CollectionPageComponent(driver).navigateToProductDetailsPage();
		Thread.sleep(5000);
		new AddToCartComponents(driver).selectSize();
		new AddToCartComponents(driver).atc();
		driver.navigate().refresh();
		Thread.sleep(3000);
        driver.findElement(HomePageLocators.CART_ICON).click();
        js.executeScript(script);
        wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.QUANTITY_PLUS_MINUS_BUTTON));
        driver.findElement(FlyCartPageLocator.PLUS_BUTTON).click();
		Thread.sleep(10000);
		LogEntries logclient = driver.manage().logs().get(LogType.BROWSER);
		for (LogEntry entry : logclient) {
			{

				if(entry.getMessage().contains("chrome-extension"))
				{
					if(entry.getMessage().contains("\"name:\" \"cart_quantity_change\""))
					{
						System.out.println(entry.getMessage());
						Reporter.log("cart_quantity_change event fired on cart.");
						//count++;
						if(!expectedevents.contains("cart_quantity_change"))
							expectedevents.add("cart_quantity_change");
					}			
				}
			}
		}

		if(expectedevents.size()!=1)
		{
			Reporter.log("Few events not fired on cart.");
			throw new Exception("Few events not fired on cart.");
		}

	}

	@Test
	public void TC_ValidateSearchEvents() throws Exception
	{
		//int count=0;
		List<String> expectedevents = new ArrayList<String>();
		driver = ChromeExtensionUtility.launchchromewithextension();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		String script = "console.clear();";
		js.executeScript(script);

		WebElement element = driver.findElement(HomePageLocators.SEARCH_ICON);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);

		driver.findElement(HomePageLocators.SEARCH_ICON).sendKeys("tees" + Keys.ENTER);

		Thread.sleep(10000);
		LogEntries logclient = driver.manage().logs().get(LogType.BROWSER);
		for (LogEntry entry : logclient) {
			{

				if(entry.getMessage().contains("chrome-extension"))
				{
					if(entry.getMessage().contains("\"name:\" \"Search Submit\""))
					{
						System.out.println(entry.getMessage());
						Reporter.log("'Search Submit' event fired on search page.");
						//count++;
						if(!expectedevents.contains("Search Submit"))
							expectedevents.add("Search Submit");
					}
				}
			}
		}

		if(expectedevents.size()!=1)
		{
			Reporter.log("Few events not fired on search page.");
			throw new Exception("Few events not fired on search page.");
		}
		//driver.quit();

	}	

	@Test
	public void TC_ValidateCheckoutEventsOnInformationPage() throws Exception
	{
		//int count=0;
		List<String> expectedevents = new ArrayList<String>();
		SoftAssert softAssert = new SoftAssert();
		driver = ChromeExtensionUtility.launchchromewithextension();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		String script = "console.clear();";
		js.executeScript(script);

		new AddToCartComponents(driver).addToCart();
		driver.findElement(HomePageLocators.CART_ICON).click();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(ShoppingCartComponents.CART_TITLE));

		driver.findElement(ShoppingCartComponents.CHECKOUT_BUTTON).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(CheckoutPageLocators.AMZ_PAY));
		WebElement element = driver.findElement(CheckoutPageLocators.AMZ_PAY);
		js.executeScript("arguments[0].click();", element);
		Thread.sleep(10000);
		//		driver.navigate().back();
		//
		//		Thread.sleep(10000);
		LogEntries logclient = driver.manage().logs().get(LogType.BROWSER);
		for (LogEntry entry : logclient) {
			{

				if(entry.getMessage().contains("chrome-extension"))
				{
					if(entry.getMessage().contains("\"name:\" \"checkout_click_express_checkout_method\""))
					{
						System.out.println(entry.getMessage());
						Reporter.log("checkout_click_express_checkout_method event fired on information page.");
						//count++;
						if(!expectedevents.contains("checkout_click_express_checkout_method"))
							expectedevents.add("checkout_click_express_checkout_method");

					}
					if(entry.getMessage().contains("\"name:\" \"view_checkout_step\""))
					{
						System.out.println(entry.getMessage());
						Reporter.log("view_checkout_step event fired on information page.");
						//count++;
						if(!expectedevents.contains("view_checkout_step"))
							expectedevents.add("view_checkout_step");

					}
					if(entry.getMessage().contains("\"name:\" \"click_to_visit_checkout\""))
					{
						System.out.println(entry.getMessage());
						Reporter.log("click_to_visit_checkout event fired on information page.");
						//count++;
						if(!expectedevents.contains("click_to_visit_checkout"))
							expectedevents.add("click_to_visit_checkout");

					}
				}
			}
		}

		if(expectedevents.size()!=3)
		{
			Reporter.log("Few events not fired on information page.");
			throw new Exception("Few events not fired on information page.");
		}
		//driver.quit();

	}	

	@Test
	public void TC_ValidateCheckoutEventsOnShippingPage() throws Exception
	{
		//int count=0;
		List<String> expectedevents = new ArrayList<String>();
		SoftAssert softAssert = new SoftAssert();
		driver = ChromeExtensionUtility.launchchromewithextension();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		String script = "console.clear();";
		js.executeScript(script);

		new AddToCartComponents(driver).addToCart();
		driver.findElement(HomePageLocators.CART_ICON).click();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(ShoppingCartComponents.CART_TITLE));

		driver.findElement(ShoppingCartComponents.CHECKOUT_BUTTON).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(CheckoutPageLocators.CHECKOUT_EMAIL));
		driver.findElement(CheckoutPageLocators.CHECKOUT_EMAIL).sendKeys(ChromeExtensionUtility.properties.getProperty("checkoutemail"));

		softAssert.assertEquals(driver.findElement(CheckoutPageLocators.SHIPPINGADD_TEXT).getText(), "Shipping address");
		new CheckoutPageComponents(driver).entershippingdetails();
		js.executeScript(script);
		driver.findElement(CheckoutPageLocators.CONTINUE_TO_SHIPPING_BUTTON).click();

		Thread.sleep(10000);
		LogEntries logclient = driver.manage().logs().get(LogType.BROWSER);
		for (LogEntry entry : logclient) {
			{

				if(entry.getMessage().contains("chrome-extension"))
				{

					if(entry.getMessage().contains("\"name:\" \"view_checkout_step\""))
					{
						System.out.println(entry.getMessage());
						Reporter.log("view_checkout_step event fired on shipping page.");
						//count++;
						if(!expectedevents.contains("view_checkout_step"))
							expectedevents.add("view_checkout_step");

					}
					if(entry.getMessage().contains("\"name:\" \"click_to_visit_checkout\""))
					{
						System.out.println(entry.getMessage());
						Reporter.log("click_to_visit_checkout event fired on shipping page.");
						//count++;
						if(!expectedevents.contains("click_to_visit_checkout"))
							expectedevents.add("click_to_visit_checkout");

					}
				}
			}
		}

		if(expectedevents.size()!=2)
		{
			Reporter.log("Few events not fired on shipping page.");
			throw new Exception("Few events not fired on shipping page.");
		}
		//driver.quit();

	}	

	@Test
	public void TC_ValidateCheckoutEventsOnPaymentPage() throws Exception
	{
		//int count=0;
		List<String> expectedevents = new ArrayList<String>();
		SoftAssert softAssert = new SoftAssert();
		driver = ChromeExtensionUtility.launchchromewithextension();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		String script = "console.clear();";
		js.executeScript(script);

		new AddToCartComponents(driver).addToCart();
		driver.findElement(HomePageLocators.CART_ICON).click();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(ShoppingCartComponents.CART_TITLE));

		driver.findElement(ShoppingCartComponents.CHECKOUT_BUTTON).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(CheckoutPageLocators.CHECKOUT_EMAIL));
		driver.findElement(CheckoutPageLocators.CHECKOUT_EMAIL).sendKeys(ChromeExtensionUtility.properties.getProperty("checkoutemail"));

		softAssert.assertEquals(driver.findElement(CheckoutPageLocators.SHIPPINGADD_TEXT).getText(), "Shipping address");
		new CheckoutPageComponents(driver).entershippingdetails();
		js.executeScript(script);
		driver.findElement(CheckoutPageLocators.CONTINUE_TO_SHIPPING_BUTTON).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(CheckoutPageLocators.SHIPPING_RADIO_BUTTON));
		js.executeScript("window.scrollBy(0,100)");
		js.executeScript(script);
		Thread.sleep(3000);
		WebElement element = driver.findElement(CheckoutPageLocators.CONTINUE_TO_PAYMENT_BUTTON);
		js.executeScript("arguments[0].click();", element);
		Thread.sleep(5000);
		WebElement coupon_input= driver.findElement(By.cssSelector("div.order-summary__section--discount>form:nth-child(3)>div>div>div>div>input"));
		coupon_input.sendKeys("FIRST15");
		driver.findElement(CheckoutPageLocators.APPLY_DISCOUNT_BUTTON).click();

		Thread.sleep(10000);
		LogEntries logclient = driver.manage().logs().get(LogType.BROWSER);
		for (LogEntry entry : logclient) {
			{

				if(entry.getMessage().contains("chrome-extension"))
				{

					if(entry.getMessage().contains("\"name:\" \"view_checkout_step\""))
					{
						System.out.println(entry.getMessage());
						Reporter.log("view_checkout_step event fired on payment page.");
						//count++;
						if(!expectedevents.contains("view_checkout_step"))
							expectedevents.add("view_checkout_step");

					}
					if(entry.getMessage().contains("\"name:\" \"click_to_visit_checkout\""))
					{
						System.out.println(entry.getMessage());
						Reporter.log("click_to_visit_checkout event fired on payment page.");
						//count++;
						if(!expectedevents.contains("click_to_visit_checkout"))
							expectedevents.add("click_to_visit_checkout");

					}
					if(entry.getMessage().contains("\"name:\" \"apply_coupon\""))
					{
						System.out.println(entry.getMessage());
						Reporter.log("apply_coupon event fired on payment page.");
						//count++;
						if(!expectedevents.contains("apply_coupon"))
							expectedevents.add("apply_coupon");

					}
				}
			}
		}
		if(expectedevents.size()!=3)
		{
			Reporter.log("Few events not fired on payment page.");
			throw new Exception("Few events not fired on payment page.");
		}
		//driver.quit();

	}	

	@AfterMethod(alwaysRun = true)
	void close()
	{
		driver.close();
		driver.quit();
	}
}


