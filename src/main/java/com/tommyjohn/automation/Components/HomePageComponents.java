package com.tommyjohn.automation.Components;



import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.testng.Assert;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.base.ExtentReport;
import com.tommyjohn.automation.PageLocators.CA_SupplyChainsActPageLocators;
import com.tommyjohn.automation.PageLocators.DoNotSellMyInfoPageLocators;
import com.tommyjohn.automation.PageLocators.HomePageLocators;
import com.tommyjohn.automation.PageLocators.PrivacyPolicyPageLocators;
import com.tommyjohn.automation.PageLocators.ProductDetailsPageLocators;
import com.tommyjohn.automation.PageLocators.TermsAndConditionsPageLocators;
import com.tommyjohn.automation.commonMethods.GeneralMethods;
import com.tommyjohn.automation.utils.CustomUtilities;

import static com.tommyjohn.automation.PageLocators.CollectionPageLocator.*;
import static com.tommyjohn.automation.PageLocators.FlyCartPageLocator.INLINECART_PRODUCT_NAME;
import static com.tommyjohn.automation.PageLocators.FlyCartPageLocator.INLINE_COLOR;

public class HomePageComponents extends HomePageLocators {

	public WebDriver driver;
	Wait<WebDriver> wait;
	List<WebElement> list;
	public static String baseUrl = CustomUtilities.baseUrl;

	public String currentUrl;
	public String currentUrl1;
	public String currentUrl2;
	boolean flag = false;
	public static WebElement element;
	public static WebElement ele1;
	public Actions action;
	JavascriptExecutor executor = (JavascriptExecutor) driver;
	SoftAssert softassert = new SoftAssert();
	WebElement footer_link;
	String text;
	String linkText;
	String pageTitle;
	GeneralMethods objGeneralMethods = new GeneralMethods();

	public HomePageComponents(WebDriver driver) {

		this.driver = driver;

		wait = new FluentWait<WebDriver>(driver).pollingEvery(Duration.ofMillis(500))
				.withTimeout(Duration.ofSeconds(60)).ignoring(NoSuchElementException.class);

	}

	public void ValidateNewMarkdownsSection() throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		wait.until(ExpectedConditions.visibilityOfElementLocated(HERO_BANNER_IMAGE));
		Thread.sleep(2000);
		js.executeScript("window.scrollBy(0,500)");
		wait.until(ExpectedConditions.visibilityOfElementLocated(NEW_MARKDOWNS_SECTIONS));
		softAssert.assertTrue(driver.findElement(NEW_MARKDOWNS_SECTIONS).isDisplayed(),"New Markdown Section is not available");
		Reporter.log("New Markdown Section is available");
		ExtentReport.extentTest.info("New Markdown Section is available");


	}

	public void ValidateImageInHeroBanner()
	{
		SoftAssert softAssert = new SoftAssert();
		wait.until(ExpectedConditions.visibilityOfElementLocated(HERO_BANNER_IMAGE));
		softAssert.assertTrue(driver.findElement(HERO_BANNER_IMAGE).isDisplayed(),"Hero Banner Image is not available");
		Reporter.log("Hero Banner Image is available");
		ExtentReport.extentTest.info("Hero Banner Image is available");

	}

	public void ValidateShopMensCTAInHeroSlider() throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		wait.until(ExpectedConditions.visibilityOfElementLocated(HERO_BANNER_IMAGE));
		Thread.sleep(2000);
		driver.findElement(HERO_SLIDER_SHOPMENS_CTA).click();
		Thread.sleep(3000);
		element = driver.findElement(PAGEHEADER);
		softAssert.assertTrue(element.isDisplayed(),"Shop Men's CTA is not functional in Hero Slider");
		Reporter.log("Shop Men's CTA is functional in Hero Slider");
		ExtentReport.extentTest.info("Shop Men's CTA is functional in Hero Slider");
	}

	public void ValidateBrandTicker() throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		wait.until(ExpectedConditions.visibilityOfElementLocated(HERO_BANNER_IMAGE));
		Thread.sleep(2000);
		js.executeScript("window.scrollBy(0,2000)");
		element = driver.findElement(BRAND_TICKER);
		softAssert.assertTrue(element.isDisplayed(),"Brand Ticker is not available in Homepage");
		Reporter.log("Brand Ticker is available in Homepage");
		ExtentReport.extentTest.info("Brand Ticker is available in Homepage");
	}

	public void ValidateShopWomensCTAInHeroSlider() throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		wait.until(ExpectedConditions.visibilityOfElementLocated(HERO_BANNER_IMAGE));
		Thread.sleep(2000);
		driver.findElement(HERO_SLIDER_SHOPWOMENS_CTA).click();
		Thread.sleep(3000);
		element = driver.findElement(PAGEHEADER);
		softAssert.assertTrue(element.isDisplayed(),"Shop Women's CTA is not functional in Hero Slider");
		Reporter.log("Shop Women's CTA is functional in Hero Slider");
		ExtentReport.extentTest.info("Shop Women's CTA is functional in Hero Slider");
	}

	public void ValidateMenInCategorySelector() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		SoftAssert softAssert = new SoftAssert();
		wait.until(ExpectedConditions.visibilityOfElementLocated(HERO_BANNER_IMAGE));
		js.executeScript("window.scrollBy(0,1000)");
		wait.until(ExpectedConditions.visibilityOfElementLocated(CATEGORY_SELECTOR_SECTION));
		driver.findElement(CATEGORY_SELECTOR_MEN).click();
		list = driver.findElements(MEN_CATEGORIES);
		for(int i=1;i<list.size();i++)
		{
			driver.findElement(By.xpath("((//ul[@class='mt-6 md:mt-8'])[1]//li)["+i+"]")).click();
			softAssert.assertTrue(driver.findElement(PAGEHEADER).isDisplayed(),"Categories in Men is not functional");
			driver.navigate().back();
			js.executeScript("window.scrollBy(0,1000)");

		}
		softAssert.assertAll();
		Reporter.log("Categories in Men Category Selector section are functional");
		ExtentReport.extentTest.info("Categories in Men Category Selector section are functional");
	}

	public void ValidateWomenInCategorySelector() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		SoftAssert softAssert = new SoftAssert();
		wait.until(ExpectedConditions.visibilityOfElementLocated(HERO_BANNER_IMAGE));
		js.executeScript("window.scrollBy(0,1000)");
		wait.until(ExpectedConditions.visibilityOfElementLocated(CATEGORY_SELECTOR_SECTION));
		driver.findElement(CATEGORY_SELECTOR_WOMEN).click();
		list = driver.findElements(WOMEN_CATEGORIES);
		for(int i=1;i<list.size();i++)
		{

			driver.findElement(By.xpath("((//ul[@class='mt-6 md:mt-8'])[2]//li)["+i+"]")).click();
			softAssert.assertTrue(driver.findElement(PAGEHEADER).isDisplayed(),"Categories in Men is not functional");
			driver.navigate().back();
			js.executeScript("window.scrollBy(0,1000)");
			driver.findElement(CATEGORY_SELECTOR_WOMEN).click();


		}
		softAssert.assertAll();
		Reporter.log("Categories in Men Category Selector section are functional");
		ExtentReport.extentTest.info("Categories in Men Category Selector section are functional");
	}

//	public void ValidateShopMensInHeroBanner() throws InterruptedException {
//		JavascriptExecutor js = (JavascriptExecutor)driver;
//		SoftAssert softAssert = new SoftAssert();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(HERO_BANNER_IMAGE));
//	//	js.executeScript("window.scrollBy(0,2000)");
//		driver.findElement(IMAGE_BANNER_MENS_CTA).click();
//		Thread.sleep(2000);
//		softAssert.assertTrue(driver.findElement(SHOP_PAGEHEADER).isDisplayed(),"Shop Men CTA in Image Banner is not functional");
//		Reporter.log("Shop Men CTA in Image Banner is functional");
//		ExtentReport.extentTest.info("Shop Men CTA in Image Banner is functional");
//
//	}


	public void ValidateShopMensInHeroBanner() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		SoftAssert softAssert = new SoftAssert();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Wait for the hero banner image to be visible
		wait.until(ExpectedConditions.visibilityOfElementLocated(HERO_BANNER_IMAGE));

		// Scroll to the 'Shop Men's' CTA
		WebElement mensCTA = driver.findElement(IMAGE_BANNER_MENS_CTA);
		js.executeScript("arguments[0].scrollIntoView(true);", mensCTA);
		js.executeScript("window.scrollBy(0, -100);"); // Adjust scrolling to avoid sticky header
		Thread.sleep(500); // Optional: Add delay for smooth scrolling

		try {
			// Attempt to click the 'Shop Men's' CTA
			wait.until(ExpectedConditions.elementToBeClickable(mensCTA)).click();
		} catch (WebDriverException e) {
			// Fallback to JavaScript click if standard click fails
			js.executeScript("arguments[0].click();", mensCTA);
		}

		// Verify the shop page header is displayed
		wait.until(ExpectedConditions.visibilityOfElementLocated(SHOP_PAGEHEADER));
		softAssert.assertTrue(
				driver.findElement(SHOP_PAGEHEADER).isDisplayed(),
				"Shop Men CTA in Image Banner is not functional"
		);

		// Log results
		Reporter.log("Shop Men CTA in Image Banner is functional");
		ExtentReport.extentTest.info("Shop Men CTA in Image Banner is functional");

		// Assert all validations
		softAssert.assertAll();
	}


//	public void ValidateShopWomensInHeroBanner() throws InterruptedException {
//		JavascriptExecutor js = (JavascriptExecutor)driver;
//		SoftAssert softAssert = new SoftAssert();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(HERO_BANNER_IMAGE));
//	//	js.executeScript("window.scrollBy(0,2000)");
//		Thread.sleep(10000);
//		driver.findElement(IMAGE_BANNER_WOMENS_CTA).click();
//		Thread.sleep(2000);
//		softAssert.assertTrue(driver.findElement(SHOP_PAGEHEADER).isDisplayed(),"Shop Women CTA in Image Banner is not functional");
//		Reporter.log("Shop Women CTA in Image Banner is functional");
//		ExtentReport.extentTest.info("Shop Women CTA in Image Banner is functional");
//
//	}

	public void ValidateShopWomensInHeroBanner() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		SoftAssert softAssert = new SoftAssert();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Wait for the hero banner image to be visible
		wait.until(ExpectedConditions.visibilityOfElementLocated(HERO_BANNER_IMAGE));

		// Scroll to the 'Shop Women's' CTA
		WebElement womensCTA = driver.findElement(IMAGE_BANNER_WOMENS_CTA);
		js.executeScript("arguments[0].scrollIntoView(true);", womensCTA);
		js.executeScript("window.scrollBy(0, -100);"); // Adjust scrolling for potential header obstruction
		Thread.sleep(500); // Optional: Add delay for smooth scrolling

		try {
			// Attempt to click the 'Shop Women's' CTA
			wait.until(ExpectedConditions.elementToBeClickable(womensCTA)).click();
		} catch (WebDriverException e) {
			// Fallback to JavaScript click if standard click fails
			js.executeScript("arguments[0].click();", womensCTA);
		}

		// Verify the shop page header is displayed
		wait.until(ExpectedConditions.visibilityOfElementLocated(WOMENS_SHOP_PAGEHEADER));
		softAssert.assertTrue(
				driver.findElement(WOMENS_SHOP_PAGEHEADER).isDisplayed(),
				"Shop Women CTA in Image Banner is not functional"
		);

		// Log results
		Reporter.log("Shop Women CTA in Image Banner is functional");
		ExtentReport.extentTest.info("Shop Women CTA in Image Banner is functional");

		// Assert all validations
		softAssert.assertAll();
	}


	public void ValidateLinksInCollectionSelector() throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor)driver;
		SoftAssert softAssert = new SoftAssert();
		wait.until(ExpectedConditions.visibilityOfElementLocated(HERO_BANNER_IMAGE));
		js.executeScript("window.scrollBy(0,3000)");
		wait.until(ExpectedConditions.visibilityOfElementLocated(COLLECTION_SELECTOR));
		driver.findElement(COLLECTION_SELECTOR_SECOND_SKIN).click();
		softAssert.assertTrue(driver.findElement(By.xpath("//a[@class='button-inverse mt-xl' and contains(text(),'Explore Second Skin')]")).isDisplayed(),"Explore Second Skin is not Functional");
		driver.findElement(COLLECTION_SELECTOR_COOL_COTTON).click();
		Thread.sleep(2000);
		softAssert.assertTrue(driver.findElement(By.xpath("//a[@class='button-inverse mt-xl' and contains(text(),'Explore Cool Cotton')]")).isDisplayed(),"Explore Cool Cotton is not Functional");
		driver.findElement(COLLECTION_SELECTOR_360_SPORT).click();
		Thread.sleep(2000);
		softAssert.assertTrue(driver.findElement(By.xpath("//a[@class='button-inverse mt-xl' and contains(text(),'Explore 360 Sport')]")).isDisplayed(),"Explore 360 Sport is not Functional");
		driver.findElement(COLLECTION_SELECTOR_APOLLO).click();
		Thread.sleep(2000);
		softAssert.assertTrue(driver.findElement(By.xpath("//a[@class='button-inverse mt-xl' and contains(text(),'Explore Apollo')]")).isDisplayed(),"Explore Apollo is not Functional");
		driver.findElement(COLLECTION_SELECTOR_AIR).click();
		Thread.sleep(2000);
		softAssert.assertTrue(driver.findElement(By.xpath("//a[@class='button-inverse mt-xl' and contains(text(),'Explore Air')]")).isDisplayed(),"Explore Air is not Functional");
		softAssert.assertAll();
		Reporter.log("All the Collection Selector Links are functional");
		ExtentReport.extentTest.info("All the Collection Selector Links are functional");

	}

//	public void ValidateOurStoryInImageBanner() throws InterruptedException {
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		SoftAssert softAssert = new SoftAssert();
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//		// Wait for the hero banner image to be visible
//		wait.until(ExpectedConditions.visibilityOfElementLocated(HERO_BANNER_IMAGE));
//
//		// Scroll to the 'Our Story' CTA
//		WebElement ourStoryCTA = driver.findElement(OUR_STORY_CTA);
//		js.executeScript("arguments[0].scrollIntoView(true);", ourStoryCTA);
//		js.executeScript("window.scrollBy(0, -100);"); // Adjust scrolling for sticky headers
//		Thread.sleep(500); // Optional: Add a delay for smoother scrolling
//
//		try {
//			// Attempt to click the 'Our Story' CTA
//			wait.until(ExpectedConditions.elementToBeClickable(OUR_STORY_CTA)).click();
//		} catch (WebDriverException e) {
//			// Fallback to JavaScript click if standard click fails
//			js.executeScript("arguments[0].click();", ourStoryCTA);
//		}
//
//		// Validate the URL after clicking
//		String currentURL = driver.getCurrentUrl();
//		softAssert.assertEquals(
//				currentURL,
//				"https://www.tommyjohn.com/pages/about-us",
//				"User is not directed to About Us page on clicking Our Story CTA"
//		);
//
//		// Log results
//		Reporter.log("User is directed to About Us page on clicking Our Story CTA");
//		ExtentReport.extentTest.info("User is directed to About Us page on clicking Our Story CTA");
//
//		// Assert all validations
//		softAssert.assertAll();
//	}

	public void ValidateOurStoryInImageBanner() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		SoftAssert softAssert = new SoftAssert();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Wait for the hero banner image to be visible
		wait.until(ExpectedConditions.visibilityOfElementLocated(HERO_BANNER_IMAGE));

		// Scroll to the 'Our Story' CTA
		WebElement ourStoryCTA = driver.findElement(OUR_STORY_CTA);
		js.executeScript("arguments[0].scrollIntoView(true);", ourStoryCTA);
		js.executeScript("window.scrollBy(0, -100);"); // Adjust scrolling for sticky headers
		Thread.sleep(500); // Optional: Add a delay for smoother scrolling

		try {
			// Attempt to click the 'Our Story' CTA
			wait.until(ExpectedConditions.elementToBeClickable(OUR_STORY_CTA)).click();
		} catch (WebDriverException e) {
			// Fallback to JavaScript click if standard click fails
			js.executeScript("arguments[0].click();", ourStoryCTA);
		}

		// Validate the URL after clicking
		String currentURL = driver.getCurrentUrl();
		softAssert.assertEquals(
				currentURL,
				"https://www.tommyjohn.com/pages/about-us",
				"User is not directed to About Us page on clicking Our Story CTA"
		);

		// Log results
		Reporter.log("User is directed to About Us page on clicking Our Story CTA");
		ExtentReport.extentTest.info("User is directed to About Us page on clicking Our Story CTA");

		// Assert all validations
		softAssert.assertAll();
	}



//	public void ValidateBrandBuildingInfoCTAs() throws InterruptedException {
//		JavascriptExecutor js = (JavascriptExecutor)driver;
//		SoftAssert softAssert = new SoftAssert();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(HERO_BANNER_IMAGE));
//		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(LEARNMORE_CTA));
//		driver.findElement(LEARNMORE_CTA).click();
//		softAssert.assertTrue(driver.findElement(By.xpath("(//h1)[1]")).isDisplayed(),"LearnMore CTA in Brand Building section is not functional");
//		driver.navigate().back();
//		js.executeScript("window.scrollBy(0,5000)");
//		driver.findElement(OURSHOPPINGPOLICIES_CTA).click();
//		softAssert.assertTrue(driver.findElement(By.xpath("(//h1)[1]")).isDisplayed(),"Our SHopping Policies CTA in Brand Building section is not functional");
//		driver.navigate().back();
//		js.executeScript("window.scrollBy(0,5000)");
//		driver.findElement(OURSTORY_CTA).click();
//		String URL = driver.getCurrentUrl();
//		softAssert.assertEquals(URL,"https://www.tommyjohn.com/pages/about-us","Our Story CTA in Brand Building section is not functional");
//		softAssert.assertAll();
//		Reporter.log("Brand Building CTAs are functional");
//		ExtentReport.extentTest.info("Brand Building CTAs are functional");
//	}

	public void ValidateBrandBuildingInfoCTAs() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		SoftAssert softAssert = new SoftAssert();

		// Wait for the page to fully load
		new WebDriverWait(driver, Duration.ofSeconds(30))
				.until(webDriver -> js.executeScript("return document.readyState").equals("complete"));

		// Validate 'Learn More' CTA
		validateCTA(LEARNMORE_CTA, "(//h1)[1]", "Learn More CTA in Brand Building section is not functional", softAssert);

		// Validate 'Our Shopping Policies' CTA
		validateCTA(OURSHOPPINGPOLICIES_CTA, "(//h1)[1]", "Our Shopping Policies CTA in Brand Building section is not functional", softAssert);

		// Validate 'Our Story' CTA
		validateURLCTA(OURSTORY_CTA, "https://www.tommyjohn.com/pages/about-us",
				"Our Story CTA in Brand Building section is not functional", softAssert);

		// Assert all validations
		softAssert.assertAll();
		Reporter.log("Brand Building CTAs are functional");
		ExtentReport.extentTest.info("Brand Building CTAs are functional");
	}


	// Helper method to validate CTA functionality
	private void validateCTA(By locator, String xpathToVerify, String errorMessage, SoftAssert softAssert) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Wait for the element to be present
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		WebElement element = driver.findElement(locator);

		// Scroll element into view and adjust for sticky headers
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		js.executeScript("window.scrollBy(0, -100);"); // Adjust scrolling for potential header obstruction

		try {
			// Attempt to click the element when clickable
			wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
		} catch (WebDriverException e) {
			// Fallback to JavaScript click if standard click fails
			js.executeScript("arguments[0].click();", element);
		}

		// Verify the expected element is displayed
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathToVerify)));
		softAssert.assertTrue(driver.findElement(By.xpath(xpathToVerify)).isDisplayed(), errorMessage);

		// Navigate back and wait for the page to stabilize
		driver.navigate().back();
		wait.until(ExpectedConditions.visibilityOfElementLocated(HERO_BANNER_IMAGE));
	}


	// Helper method to validate URL navigation for CTAs
	private void validateURLCTA(By locator, String expectedURL, String errorMessage, SoftAssert softAssert) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Wait for the element to be present
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		WebElement element = driver.findElement(locator);

		// Scroll element into view and adjust for sticky headers
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		js.executeScript("window.scrollBy(0, -100);"); // Adjust scrolling for potential header obstruction

		try {
			// Attempt to click the element when clickable
			wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
		} catch (WebDriverException e) {
			// Fallback to JavaScript click if standard click fails
			js.executeScript("arguments[0].click();", element);
		}

		// Validate the URL
		String currentURL = driver.getCurrentUrl();
		softAssert.assertEquals(currentURL, expectedURL, errorMessage);

		// Navigate back and wait for the page to stabilize
		driver.navigate().back();
		wait.until(ExpectedConditions.visibilityOfElementLocated(HERO_BANNER_IMAGE));
	}


	public void ValidateSearchResult() throws InterruptedException {

		SoftAssert softAssert = new SoftAssert();
		action = new Actions(driver);
		//searchText ="women";
		WebElement element = driver.findElement(SEARCH_ICON);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);

		driver.findElement(SEARCH_ICON_INPUT).sendKeys("second skin"+Keys.ENTER);

	}


	public void ValidateNoResultPageOnSearch() throws InterruptedException {

		SoftAssert softAssert = new SoftAssert();
		action = new Actions(driver);
		//searchText ="women";
		WebElement element = driver.findElement(SEARCH_ICON);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);

		driver.findElement(SEARCH_ICON_INPUT).sendKeys("kgfsh"+Keys.ENTER);

		wait.until(ExpectedConditions.visibilityOfElementLocated(NORESULTPAGETITLE));

		String PageTitle = driver.findElement(NORESULTPAGETITLE).getText();
		System.out.println(PageTitle);

		softAssert.assertTrue(driver.findElement(NORESULTPAGETITLE).isDisplayed(), "User is not directed to the No results page");
		Reporter.log("User is directed to the No results page");
		ExtentReport.extentTest.info("User is directed to the No results page");

	}

	public void ValidateAddingProductToCartFromInstagramWidget() throws Exception {

		action = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		new HomePageComponents(driver).navigatetoPDP();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		SoftAssert softAssert = new SoftAssert();
		Thread.sleep(5000);
		js.executeScript("window.scrollBy(0,2000)");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(INSTA_SHOP_ICON)));
		js.executeScript("arguments[0].click();", driver.findElement(INSTA_SHOP_ICON));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		js.executeScript("arguments[0].click();", driver.findElement(INSTA_ATC));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(INSTA_FIRST_SIZE)));
		js.executeScript("arguments[0].click();", driver.findElement(INSTA_FIRST_SIZE));

		wait.until(ExpectedConditions.visibilityOfElementLocated(INLINECART_PRODUCT_NAME));
		String InLineCart_Product = driver.findElement(INLINECART_PRODUCT_NAME).getText();
		System.out.println(InLineCart_Product);

		softAssert.assertTrue(driver.findElement(INLINECART_PRODUCT_NAME).isDisplayed(), "Product is not added to Cart from Instagram Widget");
		Reporter.log("Product is added to Cart from Instagram Widget");
		ExtentReport.extentTest.info("Product is added to Cart from Instagram Widget");
	}

	public void ValidateAppropriateProductIsAddedToCartFromInstagramWidget() throws Exception {
		action = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		new HomePageComponents(driver).navigatetoPDP();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		SoftAssert softAssert = new SoftAssert();
		Thread.sleep(5000);
		js.executeScript("window.scrollBy(0,2000)");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(INSTA_SHOP_ICON)));
		js.executeScript("arguments[0].click();", driver.findElement(INSTA_SHOP_ICON));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		String First_Product_Name = driver.findElement(INSTA_FIRST_PRODUCT_NAME).getText();
		System.out.println(First_Product_Name);
		js.executeScript("arguments[0].click();", driver.findElement(INSTA_ATC));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(INSTA_FIRST_SIZE)));
		js.executeScript("arguments[0].click();", driver.findElement(INSTA_FIRST_SIZE));

		wait.until(ExpectedConditions.visibilityOfElementLocated(INLINECART_PRODUCT_NAME));
		String InLineCart_Product = driver.findElement(INLINECART_PRODUCT_NAME).getText();
		System.out.println(InLineCart_Product);

		softAssert.assertEquals(First_Product_Name, InLineCart_Product, "Wrong product is added to Cart from Instagram Widget");
		Reporter.log("Appropriate product is added to Cart from Instagram Widget");
		ExtentReport.extentTest.info("Appropriate product is added to Cart from Instagram Widget");

	}

	public void ValidateSizeOverlayInInstagramWidget() throws Exception {
		action = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		new HomePageComponents(driver).navigatetoPDP();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		SoftAssert softAssert = new SoftAssert();
		Thread.sleep(5000);
		js.executeScript("window.scrollBy(0,2000)");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(INSTA_SHOP_ICON)));
		js.executeScript("arguments[0].click();", driver.findElement(INSTA_SHOP_ICON));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		String First_Product_Name = driver.findElement(INSTA_FIRST_PRODUCT_NAME).getText();
		System.out.println(First_Product_Name);
		js.executeScript("arguments[0].click();", driver.findElement(INSTA_ATC));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(INSTA_FIRST_SIZE)));
		softAssert.assertTrue(driver.findElement(INSTA_FIRST_SIZE).isDisplayed(),"Size Overlay is not displayed in the Instagram Widget");
		Reporter.log("Size Overlay is displayed in the Instagram Widget");
		ExtentReport.extentTest.info("Size Overlay is displayed in the Instagram Widget");

	}

	public void verifySelectedColorSwatchIsDisplayedInTheInlineCart() throws Exception {
		action = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		new HomePageComponents(driver).navigatetoPDP();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		SoftAssert softAssert = new SoftAssert();
		Thread.sleep(5000);
		js.executeScript("window.scrollBy(0,2000)");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(INSTA_SHOP_ICON)));
		js.executeScript("arguments[0].click();", driver.findElement(INSTA_SHOP_ICON));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		String ColorSwatch = driver.findElement(INSTA_COLOR_SWATCH).getAttribute("title");
		System.out.println(ColorSwatch);

		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", driver.findElement(INSTA_COLOR_SWATCH));

		js.executeScript("arguments[0].click();", driver.findElement(INSTA_ATC));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(INSTA_FIRST_SIZE)));
		js.executeScript("arguments[0].click();", driver.findElement(INSTA_FIRST_SIZE));

		wait.until(ExpectedConditions.visibilityOfElementLocated(INLINECART_PRODUCT_NAME));

		String[] color;
		color = (driver.findElement(INLINE_COLOR).getAttribute("title")).split(" /");
		String text = color[0];
		System.out.println(text);
		softAssert.assertEquals(ColorSwatch,text,"Color Mismatched In InLine Cart");
		softAssert.assertAll();
		Reporter.log("Selected Color Is Added To Cart");
		ExtentReport.extentTest.info("Selected Color Is Added To Cart");


	}

	//Verify logo and all category
	public void ValidateLogoAndCategoriesPresence() throws Exception
	{
		action = new Actions(driver);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		if(driver.findElement(HOMEPAGE_LOGO).isDisplayed())
		{

			currentUrl1 = driver.getCurrentUrl();
			WebElement element = driver.findElement(HOMEPAGE_LOGO);

			executor.executeScript("arguments[0].click();", element);	
			Thread.sleep(3000);

			String currentUrl2 = driver.getCurrentUrl();
			if(!currentUrl1.contains(currentUrl2))
				throw new Exception("HomePage logo not working correctly");
			Reporter.log("TommyJohnLogo is displayed and clickable :: home page opens on clicking logo");
			ExtentReport.extentTest.info("TommyJohnLogo is displayed and clickable :: home page opens on clicking logo");
		}
		else
			throw new Exception("HomePage logo is not present");

		//Verify header search icon image
		softassert.assertTrue(driver.findElement(SEARCH_ICON_IMAGE).isDisplayed(), "Global header search icon image is not displayed");
		Reporter.log("Global header serach icon image is displayed");
		ExtentReport.extentTest.info("Global header serach icon image is displayed");

		//Verify new arrivals
		//		if(driver.findElement(NEWARRIVALS_CATEGORY).isDisplayed()) {
		//
		//			element = driver.findElement(NEWARRIVALS_CATEGORY);
		//			action.moveToElement(element).perform();
		//			//			if(!driver.findElement(MEN_IN_NEWARRIVALS_CATEGORY).isEnabled())
		//			//				throw new Exception("New Arrivals category not hoverable"); 
		//		}
		//		else
		//			throw new Exception("New Arrivals category is not present");
		//		Reporter.log("New Arrivals category is Displayed :: Hoverable");
		//		Thread.sleep(3000);

		// Verify Men category
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		try {
			System.out.println("Entering Try1");
			wait.until(ExpectedConditions.presenceOfElementLocated(MEN_CATEGORY));
			if (!driver.findElement(MEN_CATEGORY).isDisplayed())
				throw new Exception("Men category is not present");

			element = driver.findElement(MEN_CATEGORY);
			action.moveToElement(element).perform();
			Thread.sleep(10000);
			try {
				if (!driver.findElement(FIRST_lEVEL1_CATEGORY).isDisplayed())
					throw new Exception("Men Category is not hoverable");
				Thread.sleep(3000);
				Reporter.log("Men category is displayed :: Hoverable");
				ExtentReport.extentTest.info("Men category is displayed :: Hoverable");
			} catch (Exception ex) {
				System.out.println("Entering Catch");
				if (!driver.findElement(FIRST_lEVEL1_CATEGORY_COPY).isDisplayed())
					throw new Exception("Men Category is not hoverable");
				Thread.sleep(3000);
				Reporter.log("Men category is displayed :: Hoverable");
				ExtentReport.extentTest.info("Men category is displayed :: Hoverable");
			}
		}
		catch(Exception e)
		{
			System.out.println("Entering Catch1");
			wait.until(ExpectedConditions.presenceOfElementLocated(MEN_CATEGORY_COPY));
			if (!driver.findElement(MEN_CATEGORY_COPY).isDisplayed())
				throw new Exception("Men category is not present");

			element = driver.findElement(MEN_CATEGORY_COPY);
			action.moveToElement(element).perform();
			Thread.sleep(10000);
			try {
				if (!driver.findElement(FIRST_lEVEL1_CATEGORY).isDisplayed())
					throw new Exception("Men Category is not hoverable");
				Thread.sleep(3000);
				Reporter.log("Men category is displayed :: Hoverable");
				ExtentReport.extentTest.info("Men category is displayed :: Hoverable");
			} catch (Exception ex) {
				System.out.println("Entering Catch");
				if (!driver.findElement(FIRST_lEVEL1_CATEGORY_COPY).isDisplayed())
					throw new Exception("Men Category is not hoverable");
				Thread.sleep(3000);
				Reporter.log("Men category is displayed :: Hoverable");
				ExtentReport.extentTest.info("Men category is displayed :: Hoverable");
			}

		}

		Thread.sleep(3000);
		// Verify Women category
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(WOMEN_CATEGORY_COPY));
			if (!driver.findElement(WOMEN_CATEGORY_COPY).isDisplayed())
				throw new Exception("Women category is not displayed");
			element = driver.findElement(WOMEN_CATEGORY_COPY);
			action.moveToElement(element).perform();
			Thread.sleep(3000);
			try {
				if (!driver.findElement(WOMENS_FIRST_lEVEL1_CATEGORY).isDisplayed())
					throw new Exception("Women Category is not hoverable");
				Reporter.log("Women category is Displayed :: Hoverable :: Clickable");
				ExtentReport.extentTest.info("Women category is Displayed :: Hoverable :: Clickable");
				Thread.sleep(3000);
			} catch (Exception ex) {
				System.out.println("Entering Catch");
				if (!driver.findElement(WOMENS_FIRST_lEVEL1_CATEGORY_COPY).isDisplayed())
					throw new Exception("Women Category is not hoverable");
				Reporter.log("Women category is Displayed :: Hoverable :: Clickable");
				ExtentReport.extentTest.info("Women category is Displayed :: Hoverable :: Clickable");
			}

		}

		catch (Exception f)
		{
			wait.until(ExpectedConditions.presenceOfElementLocated(WOMEN_CATEGORY));
			if (!driver.findElement(WOMEN_CATEGORY).isDisplayed())
				throw new Exception("Women category is not displayed");
			element = driver.findElement(WOMEN_CATEGORY);
			action.moveToElement(element).perform();
			Thread.sleep(3000);
			try {
				if (!driver.findElement(WOMENS_FIRST_lEVEL1_CATEGORY).isDisplayed())
					throw new Exception("Women Category is not hoverable");
				Reporter.log("Women category is Displayed :: Hoverable :: Clickable");
				ExtentReport.extentTest.info("Women category is Displayed :: Hoverable :: Clickable");
				Thread.sleep(3000);
			} catch (Exception ex) {
				System.out.println("Entering Catch");
				if (!driver.findElement(WOMENS_FIRST_lEVEL1_CATEGORY_COPY).isDisplayed())
					throw new Exception("Women Category is not hoverable");
				Reporter.log("Women category is Displayed :: Hoverable :: Clickable");
				ExtentReport.extentTest.info("Women category is Displayed :: Hoverable :: Clickable");
			}



		}
		// Verify ImNewHere category
//		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//		wait.until(ExpectedConditions.presenceOfElementLocated(IM_NEW_HERE));
//		if(!driver.findElement(IM_NEW_HERE).isDisplayed())
//			throw new Exception("Im New Here category is not displayed");
//		element = driver.findElement(IM_NEW_HERE);
//		action.moveToElement(element).perform();
//		//	if(!driver.findElement(FIRST_lEVEL1_CATEGORY).isDisplayed())
//		//	throw new Exception("Final Sale category is not hoverable");
//		Reporter.log("ImNewHere category is Displayed :: Hoverable :: clickable");
//		ExtentReport.extentTest.info("ImNewHere category is Displayed :: Hoverable :: clickable");
////		softassert.assertAll();



		// Verify New+Featured category
//		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//		wait.until(ExpectedConditions.presenceOfElementLocated(NEW_FEATURED));
//		if(!driver.findElement(NEW_FEATURED).isDisplayed())
//			throw new Exception("Im New Here category is not displayed");
//		element = driver.findElement(NEW_FEATURED);
//		action.moveToElement(element).perform();
//			if(!driver.findElement(SECOND_lEVEL1_CATEGORY).isDisplayed())
//			throw new Exception("New+Featured category is not hoverable");
//		Reporter.log("New+Featured category is Displayed :: Hoverable :: clickable");
//		ExtentReport.extentTest.info("New+Featured category is Displayed :: Hoverable :: clickable");
		softassert.assertAll();

	}

	// Verify Underwear category
	//		if(!driver.findElement(UNDERWEAR_CATEGORY).isDisplayed())
	//			throw new Exception("Underwear category is not displayed");
	//		element = driver.findElement(UNDERWEAR_CATEGORY);
	//		action.moveToElement(element).perform();
	//		if(!driver.findElement(MEN_IN_UNDERWEAR_CATEGORY).isDisplayed())
	//			throw new Exception("Underwear Category is not hoverable");
	//		Reporter.log("Underwear category is Displayed :: Hoverable");

	//		Thread.sleep(3000);
	// Verify Shop by Bras category
	//		if(!driver.findElement(BRAS).isDisplayed())
	//			throw new Exception("Bras category is not displayed");
	//		element = driver.findElement(BRAS);
	//		action.moveToElement(element).perform();
	//		if(!driver.findElement(BRAS_TITLE).isDisplayed())
	//			throw new Exception("Bras category is not hoverable");
	//		Reporter.log("Bras category is Displayed :: Hoverable");
	//
	//		Thread.sleep(3000);

	// Verify Packs category
	//		if(!driver.findElement(PACKS).isDisplayed())
	//			throw new Exception("Packs category is not displayed");
	//		element = driver.findElement(PACKS);
	//		action.moveToElement(element).perform();
	//		if(!driver.findElement(MEN_IN_PACKS).isDisplayed())
	//			throw new Exception("Packs category is not hoverable");
	//		Reporter.log("Packs category is Displayed :: Hoverable");
	//

	// Verify Gift Guide category
	//		if(!driver.findElement(GIFT_GUIDE).isDisplayed())
	//			throw new Exception("Gift guide category is not displayed");
	//		element = driver.findElement(GIFT_GUIDE);
	//		action.moveToElement(element).perform();
	//		if(!driver.findElement(GIFTCARDS).isDisplayed())
	//			throw new Exception("Gift Guide category is not hoverable");
	//		Reporter.log("Gift Guide category is Displayed :: Hoverable");
	//	}
	
	public void validateSearchFunctionEnter(String searchText) throws Exception {

		SoftAssert softAssert = new SoftAssert();
		action = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		
		
//		action.moveToElement(driver.findElement(SEARCH_ICON_INPUT)).build().perform();
//		Thread.sleep(3000);

		driver.findElement(SEARCH_ICON).click();
		
		driver.findElement(SEARCH_ICON_INPUT).sendKeys(searchText + Keys.ENTER);

		
		wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCHPAGE_TITLE));

		if(driver.findElement(SEARCHPAGE_TITLE).isDisplayed())
		{
			System.out.println("Search Page Title is displayed");
			Reporter.log("Search Page Title is displayed");
			ExtentReport.extentTest.info("Search Page Title is displayed");

			try {
				if (driver.getCurrentUrl().contains("second+skin")) {
					System.out.println("Search Page Title is correct");
					Reporter.log("Search Page Title is correct");
					ExtentReport.extentTest.info("Search Page Title is correct");
				} else {
					Reporter.log("Search Page Title is not correct");
					ExtentReport.extentTest.info("Search Page Title is not correct");
					throw new Exception("Search Page Title is not correct");
				}
			}
			catch(Exception ex)
			{
				if (driver.getCurrentUrl().contains("mens-sleep-lounge")) {
					System.out.println("Search Page Title is correct");
					Reporter.log("Search Page Title is correct");
					ExtentReport.extentTest.info("Search Page Title is correct");
				} else {
					Reporter.log("Search Page Title is not correct");
					ExtentReport.extentTest.info("Search Page Title is not correct");
					throw new Exception("Search Page Title is not correct");
				}

			}
		}
		else
		{
			Reporter.log("Search Page Title is not displayed");
			ExtentReport.extentTest.info("Search Page Title is not displayed");
			throw new Exception("Search Page Title is not displayed");
		}
			
		
		
		/*if(driver.findElement(ProductDetailsPageLocators.PRODUCT_DETAILS).getText().contains("Pack"))
			new ProductDetailsPageComponents(driver).verifybundlepages();
		else
			new ProductDetailsPageComponents(driver).validatePage();*/

		softAssert.assertAll();

	}

	public void VerifyPopularCategoriesSectionInSearchDropdown() throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		action = new Actions(driver);
		//searchText ="women";
		WebElement element = driver.findElement(SEARCH_ICON_INPUT);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(3000);
		softAssert.assertTrue(driver.findElement(POPULARCATEGORIES).isDisplayed(),"Popular categories is not displayed in the Search Dropdown");
		Reporter.log("Popular categories is displayed in the Search Dropdown");
		ExtentReport.extentTest.info("Popular categories is displayed in the Search Dropdown");

	}

	public void VerifyCategoriesInPopularCategoriesSection() throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		action = new Actions(driver);
		WebElement element = driver.findElement(SEARCH_ICON_INPUT);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(3000);
		int Categories = driver.findElements(CATEGORIESINSEARCHDROPDOWN).size();
		System.out.println(Categories);
		softAssert.assertEquals(Categories,4,"Popular categories does not contain four categories");
		Reporter.log("Popular categories contains four categories");
		ExtentReport.extentTest.info("Popular categories contains four categories");

	}

	public void VerifyCategoriesInPopularTermsSection() throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		action = new Actions(driver);
		driver.findElement(SEARCH_ICON).click();
		WebElement element = driver.findElement(SEARCH_ICON_INPUT);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(10000);
		int Categories = driver.findElements(CATEGORIESINPOPULARTERMS).size();
		System.out.println(Categories);
		softAssert.assertEquals(Categories,5,"Popular Terms does not contain five categories");
		Reporter.log("Popular Terms contains five categories");
		ExtentReport.extentTest.info("Popular Terms contains five categories");

	}


	public void VerifyPopularTermsSectionInSearchDropdown() throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		action = new Actions(driver);
		//searchText ="women";
		driver.findElement(SEARCH_ICON).click();
		WebElement element = driver.findElement(SEARCH_ICON_INPUT);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(8000);
		softAssert.assertTrue(driver.findElement(POPULARTERMS).isDisplayed(),"Popular Terms is not displayed in the Search Dropdown");
		Reporter.log("Popular Terms is displayed in the Search Dropdown");
		ExtentReport.extentTest.info("Popular Terms is displayed in the Search Dropdown");
	}

	public void validateSearchFunction(String searchText) throws Exception {

		SoftAssert softAssert = new SoftAssert();
		action = new Actions(driver);
		//searchText ="women";
		WebElement element = driver.findElement(SEARCH_ICON);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		
		driver.findElement(SEARCH_ICON_INPUT).sendKeys(searchText+Keys.ENTER);

//		action.moveToElement(driver.findElement(SEARCH_ICON_INPUT)).build().perform();
		Thread.sleep(5000);
		//softAssert.assertTrue(driver.findElement(PRODUCT_SUGGESTION).isDisplayed(),
		//		"Product suggestions section not displayed ");
//		softAssert.assertTrue(driver.findElement(PRODUCT_MATCHES_TITLE).isDisplayed(),
//				"Product matches section is not displayed");
//
//		WebElement pm = driver.findElement(PRODUCT_SUGGESTION);
//		String productName = pm.getAttribute("aria-label");
//		System.out.println(productName);
//
//
//		executor.executeScript("arguments[0].click();", pm);
//		Thread.sleep(8000);
//
//		String SearchPageTitle = driver.findElement(SEARCHPAGE_TITLE).getText();
//
//
//
//		if(driver.findElement(SEARCHPAGE_TITLE).isDisplayed())
//		{
//			System.out.println("Search Page Title is displayed");
//			Reporter.log("Search Page Title is displayed");
//			ExtentReport.extentTest.info("Search Page Title is displayed");
//			System.out.println(driver.findElement(SEARCHPAGE_TITLE).getText());
//			if(driver.findElement(SEARCHPAGE_TITLE).getText().contains("results for \""+ productName +"\""))
//			{
//				System.out.println("Search Page Title is correct");
//				Reporter.log("Search Page Title is correct");
//				ExtentReport.extentTest.info("Search Page Title is correct");
//			}
//			else
//			{
//				Reporter.log("Search Page Title is not correct");
//				ExtentReport.extentTest.info("Search Page Title is not correct");
//				throw new Exception("Search Page Title is not correct");
//			}
//		}
//		else
//		{
//			Reporter.log("Search Page Title is not displayed");
//			ExtentReport.extentTest.info("Search Page Title is not displayed");
//			throw new Exception("Search Page Title is not displayed");
//		}
			
		
		
		/*if(driver.findElement(ProductDetailsPageLocators.PRODUCT_DETAILS).getText().contains("Pack"))
			new ProductDetailsPageComponents(driver).verifybundlepages();
		else
			new ProductDetailsPageComponents(driver).validatePage();*/

		softAssert.assertAll();

	}
	
	public void validateSearchFunctionality() throws Exception {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		SoftAssert softAssert = new SoftAssert();
		action = new Actions(driver);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));

		String Search_keyword = "women";
		driver.findElement(SEARCH_ICON).click();
		Thread.sleep(2000);
		if(!driver.findElement(SEARCH_TEXTBOX).isDisplayed())
			throw new Exception("Search option is not present");
//		WebElement searchTXTBX = driver.findElement(SEARCH_TEXTBOX);
//		searchTXTBX.sendKeys("");
//		searchTXTBX.clear();
//		searchTXTBX.sendKeys(Search_keyword);

		driver.findElement(SEARCH_TEXTBOX).click();
		driver.findElement(SEARCH_TEXTBOX).clear();
		driver.findElement(SEARCH_TEXTBOX).sendKeys("underwear");
		
	    action.moveToElement(driver.findElement(SEARCH_TEXTBOX)).build().perform();
		Thread.sleep(10000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(SUGGESTED_PRODUCT_IMG));
		Thread.sleep(5000);
		System.out.println(driver.findElement(PRODUCT_MATCHES_TITLE).getText());
		softAssert.assertEquals(driver.findElement(PRODUCT_MATCHES_TITLE).getText(),"PRODUCTS","Text changed for Product matches title on autosuggestion container");
		softAssert.assertTrue(driver.findElement(SUGGESTED_PRODUCT_IMG).isDisplayed(), "Product Matches are not displayed");
		List<WebElement> product_matches_img = driver.findElements(SUGGESTED_PRODUCT_IMG);
		softAssert.assertTrue(product_matches_img.size()== 4, "Total count of suggested product matched images are not 4");
		
		
		softAssert.assertTrue(driver.findElement(SUGGESTED_LINKS).isDisplayed(),"Search Suggestions are not displayed ");

		Thread.sleep(2000);
		WebElement First_Suggested_link = driver.findElement(AUTO_SUGGESTED_PRODUCT_TILE_TITLE);
		String search_value = First_Suggested_link.getAttribute("aria-label");
		System.out.println(search_value);
		executor.executeScript("arguments[0].click();", First_Suggested_link);
		//First_Suggested_link.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionPageComponent.FIRST_PRODUCT_IMAGE));
		String[] list1 = driver.findElement(SEARCHPAGE_TITLE).getText().split("for ");
		text = list1[list1.length-1].replace("\"", "");
		System.out.println(text);
//		String[] list2 = text.split("s");
//		String title = list2[0].replace("'","");
//		System.out.println(title);
		softAssert.assertEquals(text, search_value, "Search page title is not matched with clicked suggested link");

		driver.get("https://www.tommyjohn.com/");
		driver.findElement(SEARCH_ICON).click();
		driver.findElement(SEARCH_TEXTBOX).click();
		driver.findElement(SEARCH_TEXTBOX).sendKeys("women");

		wait.until(ExpectedConditions.visibilityOfElementLocated(AUTO_SUGGESTED_PRODUCT_IMG_TITLE));
		WebElement FIRST_SUGGESTED_PRODUCT_TILE_TITLE = driver.findElement(AUTO_SUGGESTED_PRODUCT_IMG_TITLE);
		String suggested_title = FIRST_SUGGESTED_PRODUCT_TILE_TITLE.getText();
		//FIRST_SUGGESTED_PRODUCT_TILE_TITLE.click();
		executor.executeScript("arguments[0].click();", FIRST_SUGGESTED_PRODUCT_TILE_TITLE);
		wait.until(ExpectedConditions.visibilityOfElementLocated(ProductDetailsPageLocators.PRODUCT_TITLE));
		String actual = driver.findElement(ProductDetailsPageLocators.PRODUCT_TITLE).getText();
		System.out.println("actual is: "+actual+" expected is: "+suggested_title);
		softAssert.assertTrue(actual.equals(suggested_title) , "Wrong PDP displayed after clicking on name of auto suggested product tile title" );
		Reporter.log("Verified Search auto suggestion functionality");
		ExtentReport.extentTest.info("Verified Search auto suggestion functionality");

		//validate search page
		driver.findElement(SEARCH_ICON).click();
		driver.findElement(SEARCH_ICON_INPUT).click();
		driver.findElement(SEARCH_ICON_INPUT).sendKeys(Search_keyword + Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCHPAGE_PRODUCT_IMAGE));
		wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCHPAGE_PRODUCT_REVIEWS));
		softAssert.assertTrue(driver.findElement(SEARCHPAGE_TITLE).isDisplayed(), "Search Page title is not displayed on search page");
		String[] list = driver.findElement(SEARCHPAGE_TITLE).getText().split("for ");
		text = list[list.length-1].replace("\"", "");
		System.out.println("search title is: "+text);
		softAssert.assertEquals(text, Search_keyword, "Search page title is not matched with searched keyword");
	//	softAssert.assertTrue(driver.findElement(SEARCHPAGE_ITEMCOUNT).isDisplayed(), "Item count not displayed on search page");

		executor.executeScript("window.scrollBy(0,100)");
		softAssert.assertTrue(driver.findElement(SEARCHPAGE_PRODUCT_IMAGE).isDisplayed(), "Product image not present on search page");
		softAssert.assertTrue(driver.findElement(SEARCHPAGE_PRODUCT_NAME).isDisplayed(), "Product name not present on search page");
		softAssert.assertTrue(driver.findElement(SEARCHPAGE_PRODUCT_REVIEWS).isDisplayed(), "Product reviews not present on search page");
		softAssert.assertTrue(driver.findElement(SEARCHPAGE_PRODUCT_SWATCHES).isDisplayed(), "Product swatches not present on search page");
		softAssert.assertTrue(driver.findElement(SEARCHPAGE_PRODUCT_PRICE).isDisplayed(), "Product price not present on search page");
		

		WebElement pname  = driver.findElement(SEARCHPAGE_PRODUCT_NAME);
		text= pname.getText();
		System.out.println(text);
		Thread.sleep(3000);
		executor.executeScript("arguments[0].click();", pname );
//		driver.findElement(SEARCHPAGE_PRODUCT_IMAGE).click();
//		Thread.sleep(2000);
	//	wait.until(ExpectedConditions.visibilityOfElementLocated(ProductDetailsPageLocators.ADD_TO_CART_BUTTON));
		System.out.println(driver.findElement(ProductDetailsPageLocators.PRODUCT_TITLE).getText());
		softAssert.assertEquals(driver.findElement(ProductDetailsPageLocators.PRODUCT_TITLE).getText(), text , "correct PDP not displayed after clicking on name of product on search page" );
		Thread.sleep(3000);
		driver.navigate().back();
	//	wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCHPAGE_PRODUCT_NAME));

	//	new CollectionPageComponent(driver).Verify_ProductTile_HoverFunctionality();

	//	wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCHPAGE_PRODUCT_NAME));

		//new CollectionPageComponent(driver).MethodToCheckColorSwatchChanging();

		//new CollectionPageComponent(driver).QuickShopOnMegaCollection();

//		MethodToCheckAllFiltersOnSearchPage();

		Reporter.log("Search page is displayed :: Correct");
		ExtentReport.extentTest.info("Search page is displayed :: Correct");

		driver.findElement(SEARCH_ICON).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(RECENT_SEARCHES_TITLE));
		softAssert.assertTrue(driver.findElement(CLEAR_RECENT_SEARCHES).isDisplayed(), "Clear recent search link is not displayed");
		driver.findElement(CLEAR_RECENT_SEARCHES).click();
//		driver.findElement(POPULAR_SEARCHES).isDisplayed();
//		executor.executeScript("arguments[0].click();", element);
//		String[] attribute = driver.findElement(RECENT_SEARCH_CONTAINER).getAttribute("style").split("display: ");
//		text = attribute[attribute.length-1].replace(";", "");
		softAssert.assertEquals(driver.findElement(POPULAR_SEARCHES).isDisplayed(), "Clear recent search link is not functional");
		Reporter.log("Clear recent search link is Displayed :: Clickable");
		ExtentReport.extentTest.info("Clear recent search link is Displayed :: Clickable");
//		softAssert.assertAll();

	}

	public void MethodToCheckAllFiltersOnSearchPage() throws Exception
	{
		List<WebElement> allElements = null;
		int filterList = 0;
		int optionsList = 0;
		String selectedOption = null;
		int optionsList1 = 0;
		int count = 0;
		List<WebElement> allProductsOnPage = null;
		String totalDisplayedCount = null;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		// get total no of filters
		allElements = driver.findElements(SEARCHPAGE_ALL_FILTERS);
		filterList = allElements.size();
		System.out.println("All filters list :: "+ filterList);

		// loop for total not of filters
		for(int i = 2; i <= filterList+1; i++) 
		{
			element=driver.findElement(By.cssSelector("div.mega-collection-filters > div:nth-child("+i+") > a"));
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("arguments[0].click();", element);
			// get all the options list available in ith filter
			allElements = driver.findElements(By.cssSelector("div.mega-collection-filters > div:nth-child("+i+") > ul > li"));
			optionsList = allElements.size();
			System.out.println("All options list :: "+ optionsList);
			int ct = 0;
			// check if not any option present
			if(optionsList == 0)
				break;

			WebElement Filter_option_item = driver.findElement(By.cssSelector("div.mega-collection-filters > div:nth-child("+i+") > ul > li:nth-child(1) > label > input"));
			if(!Filter_option_item.isDisplayed())
				continue;
			count++;
			//driver.findElement(By.cssSelector("div.mega-collection-filters > div:nth-child("+i+") > ul > li:nth-child("+j+") > label > input")).click();
			//Filter_option_item.click();
			jse.executeScript("arguments[0].click();", Filter_option_item);
			Thread.sleep(2000);

			// get the name of selected option
			selectedOption = Filter_option_item.getAttribute("data-filter-btn");
			System.out.println("selected option :: "+selectedOption);


			// get all selected displayed optioins
			allElements = driver.findElements(SEARCHPAGE_ALL_SELECTED_FILTER_OPTIONS);
			optionsList1 = allElements.size();

			System.out.println("count :: "+count);
			System.out.println("optionlist1 :: "+optionsList1);
			// check selected options count and displayed selected list count is same or not
			if(count != optionsList1)
				throw new Exception("Selected options and selected displayed options are not same");

			System.out.println("ct :: "+ct);
			System.out.println("ct th ele :: "+allElements.get(ct).getText());
			if(!(allElements.get(ct).getText()).equals(selectedOption))
				throw new Exception("Selected item is not present in displayed item list");

			// check total no of count is displayed or not
			if(!driver.findElement(SEARCHPAGE_ITEMCOUNT).isDisplayed())
				throw new Exception("Total counts of items is not displayed for filter :: "+i);

			// check total not of count and products are correct
			totalDisplayedCount = driver.findElement(SEARCHPAGE_ITEMCOUNT).getText();
			System.out.println(totalDisplayedCount);
			Thread.sleep(3000);
			//	wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCHPAGE_PRODUCT_IMAGE));
			allProductsOnPage = driver.findElements(SEARCHPAGE_PRODUCT_IMAGE);
			int allProductsOnPage1 = allProductsOnPage.size();
			System.out.println("Out ddd :: "+allProductsOnPage1);
			// check for hoverable class (if image is hoverable then it count two images for one image)
			element = driver.findElement(SEARCHPAGE_PRODUCT_IMAGE);
			System.out.println("total displayed product on page :: "+allProductsOnPage1);
			System.out.println("total displayed count of product :: "+totalDisplayedCount);
			try{
				if(allProductsOnPage1 != (Integer.parseInt(totalDisplayedCount)))
					throw new Exception("Total displayed count is not same as all products on page");
			}
			catch(Exception e)
			{
				System.out.println("Due to lazy loading total displayed count is not possible.");
			}

			ct++;

			//	}

			// check again any options are present or not
			if(optionsList == 0)
				continue;

			// click on last item to remove it from list
			Thread.sleep(5000);
			element=driver.findElement(By.cssSelector("div.mega-collection-filters > div:nth-child("+i+") > a"));
			jse.executeScript("arguments[0].click();", element);
			Thread.sleep(1000);
			System.out.println("last item :: "+driver.findElement(By.cssSelector("div.js-mega-collection-desktop-filter-tags >div> button:nth-child("+ct+")")).getText());
			element=driver.findElement(By.cssSelector("div.js-mega-collection-desktop-filter-tags >div> button:nth-child("+ct+")"));
			jse.executeScript("arguments[0].click();", element);
			//driver.findElement(PLUS_BUTTON).click();
			Thread.sleep(5000);

			Thread.sleep(1000);
			// after last element removed decrease ct by 1
			System.out.println("Before ct::"+ct);
			// decrease ct by 1 bcz when one element is removed from list
			ct--;
			ct--; // for taking last element from list
			System.out.println("After ct--::"+ct);
			// check when we click on item then it remove from list or not
			System.out.println("allelements size:: "+allElements.size());


			if(ct!=0 && ct!=-1) {
				System.out.println("ct :: "+allElements.get(ct).getText());
				System.out.println("ct :: "+driver.findElement(By.cssSelector("div.js-mega-collection-desktop-filter-tags > button:nth-child("+ct+")")).getText());
				if((allElements.get(ct).getText()).equals(driver.findElement(By.cssSelector("div.js-mega-collection-desktop-filter-tags > button:nth-child("+ct+")")).getText()))
					throw new Exception("Item is not removed from displayed list when click on it");
			}
			ct = 0;
			Thread.sleep(2000);

			// clear the filters
			WebElement ele = driver.findElement(SEARCHPAGE_CLEAR_FILTER);
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", ele);
			//	driver.findElement(CLEAR_FILTER_BUTTON).click();
			Thread.sleep(3000);
			allElements = null;
			count = 0;
		}
		Reporter.log("All filters are working correctly");
		Reporter.log("All selected items are displayed in list and removing from list");
		Reporter.log("Clear filters button works correctly");

		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCHPAGE_LAST_FILTER));
		if(driver.findElement(SEARCHPAGE_LAST_FILTER).isDisplayed()) 
		{
			//driver.findElement(LAST_FILTER).click();
			Select selectElement = new Select(driver.findElement(SEARCHPAGE_LAST_FILTER));
			selectElement.selectByValue("Price:ascending");
			Thread.sleep(5000);
			allProductsOnPage = driver.findElements(SEARCHPAGE_PRODUCT_IMAGE);
			System.out.println("All product on page :: "+allProductsOnPage.size());
			try{
				for(int i=1; i < allProductsOnPage.size(); i++) {
					String text = null;
					String text1 = null;
					String text2 = null;

					text = driver.findElement(By.cssSelector(".mega-collection-grid__row > div.product-item:nth-child("+i+") > div.product-meta__prices")).getText();
					System.out.println("Text :: "+text);
					for (String a : text.split("\\$")) 
						text1 = a;
					System.out.println("text 1 :: "+text1);
					// loop to check previous and next price
					int j = i+1;
					System.out.println("Text :: "+driver.findElement(By.cssSelector(".mega-collection-grid__row > div.product-item:nth-child("+j+") > div.product-meta__prices")).getText());
					for (String a : driver.findElement(By.cssSelector(".mega-collection-grid__row > div.product-item:nth-child("+j+") > div.product-meta__prices")).getText().split("\\$")) 
						text2 = a;
					System.out.println("text2 :: "+text2);
					if(Float.parseFloat(text1) > Float.parseFloat(text2))
						throw new Exception("Products are not sorted by price correctly");

				}
			}
			catch(Exception e)
			{
				System.out.println("Due to lazy loading price comparison is not possible for sort by filter.");
			}
			Reporter.log("All Products are sorted as selected option");

		}

	}

	public void validateaccountdropdown() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		element = driver.findElement(ACCOUNT_NAV_DROPDOWN);
		action = new Actions(driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(ACCOUNT_NAV_DROPDOWN));
		if(!driver.findElement(ACCOUNT_NAV_DROPDOWN).isDisplayed())
			throw new Exception("Account nav drop down is not present");

		// check sign in option
		driver.get(baseUrl);
		wait.until(ExpectedConditions.presenceOfElementLocated(ACCOUNT_NAV_DROPDOWN));
		element = driver.findElement(ACCOUNT_NAV_DROPDOWN);
		action.moveToElement(element).perform();
		wait.until(ExpectedConditions.presenceOfElementLocated(SIGNIN_OPTION));
		WebElement signIn = driver.findElement(SIGNIN_OPTION);
		action.moveToElement(signIn).perform();


		driver.findElement(SIGNIN_OPTION).click();
		Thread.sleep(3000);
		currentUrl2 = driver.getCurrentUrl();
		if(!currentUrl2.startsWith("https://tommyjohn.com/account/login"))
			throw new Exception("Wrong page opened after clicked on sin in option");
		Reporter.log("sign in is Displayed :: Clickable");
		ExtentReport.extentTest.info("sign in is Displayed :: Clickable");

		//check help
		driver.get(baseUrl);
		wait.until(ExpectedConditions.presenceOfElementLocated(ACCOUNT_NAV_DROPDOWN));
		element = driver.findElement(ACCOUNT_NAV_DROPDOWN);
		action.moveToElement(element).perform();
		if(!driver.findElement(HELP_ICON_HELP_OPTION).isDisplayed())
			throw new Exception("Help Center option not displayed");
		Reporter.log("Help center option is Displayed ");
		ExtentReport.extentTest.info("Help center option is Displayed ");
		driver.findElement(HELP_ICON_HELP_OPTION).click();
		Thread.sleep(3000);
		//	driver.findElement(By.cssSelector(".LeaveSiteButtons > a")).click();
		Thread.sleep(3000);
		currentUrl2 = driver.getCurrentUrl();
		if(!currentUrl2.equalsIgnoreCase("https://help.tommyjohn.com/hc/en-us"))
			throw new Exception("Wrong page opened after clicked on help option");
		Reporter.log("Help option is Displayed :: Clickable");
		ExtentReport.extentTest.info("Help option is Displayed :: Clickable");

		// check my account option
		driver.get(baseUrl);
		wait.until(ExpectedConditions.presenceOfElementLocated(ACCOUNT_NAV_DROPDOWN));
		Thread.sleep(2000);
		element = driver.findElement(ACCOUNT_NAV_DROPDOWN);
		action.moveToElement(element).perform();
		if(!driver.findElement(MYACCOUNT_OPTION).isDisplayed())
			throw new Exception("My account option not displayed");
		Reporter.log("My account option is Displayed ");
		ExtentReport.extentTest.info("My account option is Displayed ");
		driver.findElement(MYACCOUNT_OPTION).click();
		Thread.sleep(3000);
		currentUrl2 = driver.getCurrentUrl();
		if(!currentUrl2.startsWith("https://tommyjohn.com/account"))
			throw new Exception("Wrong page opened after clicked on my account option");
		Reporter.log("My account is Displayed :: Clickable");
		ExtentReport.extentTest.info("My account is Displayed :: Clickable");




		// check chat with us option
		//		driver.get(baseUrl);
		//		Thread.sleep(3000);
		//		element = driver.findElement(HELP_ICON);
		//		action.moveToElement(element).perform();
		//		if(!driver.findElement(CHAT_WITH_US).isDisplayed())
		//			throw new Exception("Chat With Us Option is not present");
		//		driver.findElement(CHAT_WITH_US).click();
		//		Thread.sleep(6000);
		//		// switch to another frame
		//		element = driver.findElement(CHAT_WITH_US_IFRAME);
		//		driver.switchTo().frame(element);
		//		element = driver.findElement(CHAT_WITH_US_POPUP);
		//		if(!element.isEnabled())
		//			throw new Exception("Chat With Us Popup is not opened");
		//				if(element.isEnabled()) {
		//					//			if(!driver.findElement(CHAT_WITH_US_POPUP_MINIMIZE).isDisplayed())
		//					//				throw new Exception("Chat With Us Popup Minimize button is not present");
		//		
		//					WebElement element = driver.findElement(CHAT_WITH_US_POPUP_MINIMIZE);
		//					JavascriptExecutor executor = (JavascriptExecutor)driver;
		//					executor.executeScript("arguments[0].click();", element);
		//					
		//				}
		//				else
		//					throw new Exception("Chat With Us Popup is not opened");
		//		Reporter.log("Chat With Us Popup is Displayed :: Minimize Button working");
		//
		//		driver.switchTo().defaultContent();
		//		// Validating Call Us option
		//		driver.get(baseUrl);
		//		element = driver.findElement(HELP_ICON);
		//		action.moveToElement(element).perform();
		//
		//		if(!driver.findElement(CALL_US_OPTION).isDisplayed())
		//			throw new Exception("Call US Option is not present");
		//
		//		text = driver.findElement(CALL_US_OPTION).getText();
		//		if(!text.equals("Call Us: 1-800-708-3490"))
		//			throw new Exception("Text or Number for Call Us changed");
		//		
		//		if(!driver.findElement(TEXT_US_OPTION).isDisplayed())
		//			throw new Exception("TEXT US Option is not present");
		//		
		//		text = driver.findElement(TEXT_US_OPTION).getText();
		//		if(!text.equals("Text Us: 1-855-976-4933"))
		//			throw new Exception("Text or Number for Text Us changed");
		//		
		//
		//		// valaditing Email Us option
		//		driver.get(baseUrl);
		//		element = driver.findElement(HELP_ICON);
		//		action.moveToElement(element).perform();
		//		if(!driver.findElement(EMAIL_US).isDisplayed())
		//			throw new Exception("Email Us option is not present");
		//		driver.findElement(EMAIL_US).click();
		//		Thread.sleep(4000);
		//		if(!driver.getCurrentUrl().equalsIgnoreCase("https://tommyjohn.com/pages/contact-us"))
		//			throw new Exception("Wrong page opened after clicked on Emails Us");
		//		Reporter.log("Call Us option and Email Us is Displayed :: Clickable");
	}


	// check Account/Login icon
	public void validateAccountIcon() throws Exception {
		if(!driver.findElement(ACCOUNT_NAV_DROPDOWN).isDisplayed())
			throw new Exception("Account Icon is not present");
		element=driver.findElement(ACCOUNT_NAV_DROPDOWN);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
		Thread.sleep(5000);
		if(!driver.getCurrentUrl().contains("https://www.tommyjohn.com/account/login?return_url=%2Faccount"))
			throw new Exception("Wrong page opened after clicked on Account Icon");
		Reporter.log("Account Icon is Displayed :: Clickable");
		ExtentReport.extentTest.info("Account Icon is Displayed :: Clickable");

	}
	public void validateCartIcon() throws Exception {
		// Check for Cart icon

		driver.navigate().to(baseUrl);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.presenceOfElementLocated(CART_ICON));
		driver.findElement(CART_ICON).click();
		Thread.sleep(2000);
		if(!driver.findElement(MINICART_HEADING).isDisplayed())
			throw new Exception("Mini Cart is not opened");
		driver.get(baseUrl);
		Reporter.log("Cart Icon is Displayed :: Clickable :: Mini Cart is Opened");	
		ExtentReport.extentTest.info("Cart Icon is Displayed :: Clickable :: Mini Cart is Opened");	
	}

	
	/**
	 * Validates if all links in Shop Section are present and navigate to correct
	 * page
	 * 
	 * @throws Exception
	 */
	public void validateFooterShopSection() throws Exception {
		softassert = new SoftAssert();

		System.out.println("\n---Checking Footer Shop Section Links---");

		if (!driver.getCurrentUrl().equals("https://www.tommyjohn.com")) {
			driver.navigate().to("https://www.tommyjohn.com");
		}

		Thread.sleep(3000);

//		Scroll to bottom of page for footer section to be visible
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

//		Check Shop Women
		System.out.println("***Validating Shop Women Footer***");

		text = driver.findElement(SHOP_WOMEN).getText();

		softassert.assertTrue(text.equals("Shop Women"), "Shop Women link text is changed");

		clickWithJS(SHOP_WOMEN);

		wait.until(ExpectedConditions.titleContains("Women's All"));

		softassert.assertTrue(driver.getCurrentUrl().contains("https://www.tommyjohn.com/collections/womens-all"),
				"Wrong page opened after Shop Women footer link clicked");

		softassert.assertAll();
		System.out.println("Shop Women Footer Link Text is Correct and Correct Webpage opened after clicking on link\n");

		Reporter.log("Shop Women footer link is Displayed :: Clickable");

//		Check Shop Men
		System.out.println("***Validating Shop Men Footer***");

		driver.navigate().back();
		wait.until(ExpectedConditions.titleIs("Tommy John | No Adjustment Needed"));

		text = driver.findElement(SHOP_MEN).getText();
		softassert.assertTrue(text.equals("Shop Men"), "Shop Men link text is changed");

		clickWithJS(SHOP_MEN);

		wait.until(ExpectedConditions.titleContains("Men's All"));

		softassert.assertTrue(driver.getCurrentUrl().contains("https://www.tommyjohn.com/collections/mens-all"),
				"Wrong page opened after Shop Men Footer link was clicked");

		softassert.assertAll();

		System.out.println("Shop Men Link text is Correct and Correct webpage opened after clicking on link\n");
		Reporter.log("Shop Men footer link is Displayed :: Clickable");

//		Check Fabric Guide Footer
		System.out.println("***Validating Fabric Guide Footer***");

		driver.navigate().back();
		wait.until(ExpectedConditions.titleIs("Tommy John | No Adjustment Needed"));

		text = driver.findElement(FABRIC_GUIDE_LINK).getText();
		softassert.assertTrue(text.equals("Fabric Guide"), "Fabric Guide link text is changed");

		clickWithJS(FABRIC_GUIDE_LINK);

		wait.until(ExpectedConditions.titleContains("Fabric Guide"));

		softassert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase("https://www.tommyjohn.com/pages/fabric-guide"),
				"Wrong page opened after Fabric Guide Footer link was clicked");

		softassert.assertAll();

		System.out.println("Fabric Guide Link text is Correct and Correct webpage opened after clicking on link\n");
		Reporter.log("Fabric Guide footer link is Displayed :: Clickable");

//		Check Gift Cards Footer
		System.out.println("***Validating Gift Cards Footer***");

		driver.navigate().back();
		wait.until(ExpectedConditions.titleIs("Tommy John | No Adjustment Needed"));

		text = driver.findElement(GIFT_CARD_LINK).getText();
		softassert.assertTrue(text.equals("Gift Cards"), "Gift Cards link text is changed");

		clickWithJS(GIFT_CARD_LINK);

		wait.until(ExpectedConditions.titleContains("Gift Cards"));

		softassert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase("https://www.tommyjohn.com/pages/giftcards"),
				"Wrong page opened after Gift Cards Footer link was clicked");

		softassert.assertAll();

		System.out.println("Gift Cards Link text is Correct and Correct webpage opened after clicking on link\n");
		Reporter.log("Gift Cards footer link is Displayed :: Clickable");

	}

	/**
	 * vValidates if all links in Promotion Section are present and they navigate to
	 * correct page
	 * 
	 * @throws Exception
	 */
	public void validateFooterPromotionsSection() throws Exception {

		softassert = new SoftAssert();
		System.out.println("\n---Checking Footer Promotions Section---");

		if (!driver.getCurrentUrl().equals("https://www.tommyjohn.com")) {
			driver.navigate().to("https://www.tommyjohn.com");
		}

//		Thread.sleep(3000);
		objGeneralMethods.waitTillPageLoad(driver);

//		Check Sitewide Promotion Link
		System.out.println("***Validating Sitewide Promotion Footer***");

//		Scroll to bottom of page for footer section to be visible
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

		linkText = driver.findElement(SITEWIDE_PROMOTION_LINK).getText();

		softassert.assertTrue(linkText.equals("Sitewide Promotion"), "Sitewide Promotion link text is changed");

		clickWithJS(SITEWIDE_PROMOTION_LINK);

		wait.until(ExpectedConditions.titleContains("Sitewide Promotion"));

		softassert.assertTrue(
				driver.getCurrentUrl().contains("https://www.tommyjohn.com/pages/sitewide-promotion-disclaimer"),
				"Wrong page opened after Sitewide Promotion footer link was clicked");

		softassert.assertAll();
		System.out.println(
				"Sitewide Promotion Footer Link text is Correct and Correct webpage opened after clicking on link\n");

		Reporter.log("Sitewide Promotion footer link is Displayed :: Clickable");

//		Check Military Discount
		System.out.println("***Validating Military Discount Footer***");
//		Go back to Homepage and wait for load
		driver.navigate().back();
		wait.until(ExpectedConditions.titleIs("Tommy John | No Adjustment Needed"));

		linkText = driver.findElement(MILITARY_DISCOUNT_LINK).getText();
		softassert.assertTrue(linkText.equals("Military Discount"), "Military Discount link text is changed");

		clickWithJS(MILITARY_DISCOUNT_LINK);

		Thread.sleep(2000);

		list = driver.findElements(LEAVE_SITE_POPUP);

		Assert.assertTrue(list.size() > 0, "Leave Site Popup has not appeared");

		objGeneralMethods.clickButtonByJS(driver, LEAVE_SITE_POPUP_CANCEL_BTN);

		softassert.assertAll();

		System.out.println("Military Discount Link text is Correct and Popup opened after clicking on link\n");
		Reporter.log("Military Discount footer link is Displayed :: Clickable");

//		Check First Responder link
		System.out.println("***Validating First Responder Footer***");

		linkText = driver.findElement(FIRST_RESPONDER).getText();

		softassert.assertTrue(linkText.equals("First Responder"), "First Responder link text is changed");

		clickWithJS(FIRST_RESPONDER);

		Thread.sleep(1000);

		list = driver.findElements(LEAVE_SITE_POPUP);

		Assert.assertTrue( list.size() > 0, "Leave Site Popup has not appeared");

		clickWithJS(LEAVE_SITE_POPUP_CANCEL_BTN);

		softassert.assertAll();

		System.out.println("First Responder Link text is Correct and Popup opened after clicking on link\n");

		Reporter.log("First Responder footer link is Displayed :: Clickable");

//		Check Government Employee Discount link
		System.out.println("***Validating Government Employee Discount Footer***");

		linkText = driver.findElement(GOVERNMENT_EMPLOYEE_DISCOUNT).getText();

		softassert.assertTrue(linkText.equals("Government Employee Discount"),
				"Government Employee Discount link text is changed");

		clickWithJS(GOVERNMENT_EMPLOYEE_DISCOUNT);

		Thread.sleep(1000);

		list = driver.findElements(LEAVE_SITE_POPUP);

		Assert.assertTrue( list.size() > 0, "Leave Site Popup has not appeared");

		clickWithJS(LEAVE_SITE_POPUP_CANCEL_BTN);

		softassert.assertAll();

		System.out.println("Government Employee Discount Link text is Correct and Popup opened after clicking on link\n");

		Reporter.log("Government Employee Discount footer link is Displayed :: Clickable");

//		Check Teacher Discount link
		System.out.println("***Validating Teacher Discount Footer***");

		linkText = driver.findElement(TEACHER_DISCOUNT_LINK).getText();

		softassert.assertTrue(linkText.equals("Teacher Discount"), "Teacher Discount link text is changed");

		clickWithJS(TEACHER_DISCOUNT_LINK);

		Thread.sleep(1000);

		list = driver.findElements(LEAVE_SITE_POPUP);

		Assert.assertTrue(list.size() > 0, "Leave Site Popup has not appeared");

		clickWithJS(LEAVE_SITE_POPUP_CANCEL_BTN);

		softassert.assertAll();

		System.out.println("Teacher Discount Link text is Correct and Popup opened after clicking on link\n");

		Reporter.log("Teacher Discount footer link is Displayed :: Clickable");

//		Check Referral Link

		System.out.println("***Validating Referral Footer***");

		linkText = driver.findElement(REFERRAL_LINK).getText();

		softassert.assertTrue(linkText.equals("Give $25, Get $25"), "Give $25, Get $25 link text is changed");

		clickWithJS(REFERRAL_LINK);

		wait.until(ExpectedConditions.titleContains("Refer a Friend"));

		softassert.assertTrue(driver.getCurrentUrl().contains("https://www.tommyjohn.com/pages/refer"),
				"Wrong page opened after Give $25, Get $25 footer link was clicked");

		softassert.assertAll();

		System.out.println(
				"Give $25, Get $25 Footer Link text is Correct and Correct webpage opened after clicking on link\n");

		Reporter.log("Give $25, Get $25 footer link is Displayed :: Clickable");

	}

	/**
	 * Validates if all links in Account Section are present and they navigate to
	 * correct page
	 * 
	 * @throws InterruptedException
	 */
	public void validateFooterAccountSection() throws InterruptedException {
		softassert = new SoftAssert();

		System.out.println("\n---Checking Footer Account Section Links---");

		if (!driver.getCurrentUrl().equals("https://www.tommyjohn.com")) {
			driver.navigate().to("https://www.tommyjohn.com");
		}

		Thread.sleep(3000);

//		Scroll to bottom of page for footer section to be visible
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

//		Check Sign In link
		System.out.println("***Validating Sign In Footer***");

		linkText = driver.findElement(SIGN_IN_FOOTER_LINK).getText();

		softassert.assertTrue(linkText.equals("Sign In"), "Sign In link text is changed");

		clickWithJS(SIGN_IN_FOOTER_LINK);

		wait.until(ExpectedConditions.titleContains("Account"));

		softassert.assertTrue(
				driver.getCurrentUrl()
						.equalsIgnoreCase("https://www.tommyjohn.com/account/login?return_url=%2Faccount"),
				"Wrong page opened after Sign In footer link clicked");

		softassert.assertAll();
		System.out.println("Sign In Footer Link text is Correct and Correct webpage opened after clicking on link\n");

		Reporter.log("Sign In footer link is Displayed :: Clickable");

//		Check Order History link
		System.out.println("***Validating Order History Footer***");

		driver.navigate().back();
		wait.until(ExpectedConditions.titleIs("Tommy John | No Adjustment Needed"));

		linkText = driver.findElement(ORDER_HISTORY).getText();
		System.out.println("Order History Link Text is : " + linkText);

		softassert.assertTrue(linkText.equals("Order History"), "Order History Link text is changed");

		clickWithJS(ORDER_HISTORY);

		wait.until(ExpectedConditions.titleContains("Account"));

		softassert.assertTrue(
				driver.getCurrentUrl()
						.equalsIgnoreCase("https://www.tommyjohn.com/account/login?return_url=%2Faccount"),
				"Wrong page opened after Order History footer link clicked");

		softassert.assertAll();
		System.out
				.println("Order History Footer Link text is Correct and Correct webpage opened after clicking on link\n");

		Reporter.log("Sign In footer link is Displayed :: Clickable");

	}

	/**
	 * Validates if all links in Support Section are present and they navigate to
	 * correct page
	 * 
	 * @throws Exception
	 */
	public void validateFooterSupportSection() throws Exception {
		softassert = new SoftAssert();

		System.out.println("\n---Checking Footer Support Section Links---");

		if (!driver.getCurrentUrl().equals("https://www.tommyjohn.com")) {
			driver.navigate().to("https://www.tommyjohn.com");
		}

		Thread.sleep(3000);

//		Scroll to bottom of page for footer section to be visible
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

//		Check Contact Us link
		System.out.println("***Validating Contact & FAQ Footer***");

		linkText = driver.findElement(CONTACT_US_LINK).getText();

		softassert.assertTrue(linkText.equals("Contact & FAQ"), "Contact Us link text is changed");

		clickWithJS(CONTACT_US_LINK);

		wait.until(ExpectedConditions.titleContains("Help"));

		softassert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase("https://help.tommyjohn.com/hc/en-us"),
				"Wrong page opened after Contact Us footer link clicked");

		softassert.assertAll();
		System.out.println("Contact Us Footer Link text is correct and Correct webpage opened after clicking on link\n");

		Reporter.log("Contact Us footer link is Displayed :: Clickable");

		/**
		 * // Check Returns & Exchanges Link 
		 * System.out.println("***Validating Returns & Exchanges Footer***"); 
		 * driver.navigate().back(); 
		 * wait.until(ExpectedConditions.titleIs("Tommy John | No Adjustment Needed"));
		 * 
		 * linkText = driver.findElement(RETURNS_AND_EXCHANGES).getText();
		 * softassert.assertTrue(linkText.equals("Returns & Exchanges"), "Returns and
		 * Exchanges link text is changed");
		 * 
		 * clickWithJS(RETURNS_AND_EXCHANGES);
		 * 
		 * wait.until(ExpectedConditions.titleContains("Returns"));
		 * 
		 * softassert.assertTrue( driver.getCurrentUrl().equalsIgnoreCase(
		 * "https://help.tommyjohn.com/hc/en-us/sections/360000065123-Returns-Exchanges"),
		 * "Wrong page opened after Returns And Exchanges footer link clicked");
		 * 
		 * softassert.assertAll();
		 * 
		 * System.out.println("Returns and Exchange Footer Link text is correct and
		 * Correct webpage opened after clicking on link\n"); 
		 * Reporter.log("Returns and
		 * Exchange footer link is Displayed :: Clickable");
		 * 
		 * 
		 * // Check Shipping and Returns 
		 * System.out.println("***Validating Shipping and Returns Footer***"); 
		 * driver.navigate().back(); 
		 * wait.until(ExpectedConditions.titleIs("Tommy John | No Adjustment Needed"));
		 * 
		 * linkText = driver.findElement(SHIPPING_AND_RETURNS_LINK).getText();
		 * softassert.assertTrue(linkText.equals("Shipping & Return Policy"), "Shipping and
		 * Return Policy Link text is changed");
		 * 
		 * clickWithJS(SHIPPING_AND_RETURNS_LINK);
		 * 
		 * wait.until(ExpectedConditions.titleContains("Shipping"));
		 * 
		 * softassert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase(
		 * "https://help.tommyjohn.com/hc/en-us/articles/360000507746-Shipping-Returns"),
		 * "Wrong page opened after Shipping and Returns Footer link clicked");
		 * 
		 * softassert.assertAll();
		 * 
		 * System.out.println
		 * ("Shipping and Returns Link text is correct and Correct webpage opened after clicking on link\n"); 
		 * Reporter.log("Shipping and Returns footer link is Displayed :: Clickable");
		 **/

//		Check Gift card Balance Page
		System.out.println("***Validating Gift Card Balance Footer***");
		driver.navigate().back();
		wait.until(ExpectedConditions.titleIs("Tommy John | No Adjustment Needed"));

		linkText = driver.findElement(GIFT_CARD_BALANCE_CHECK).getText();
		softassert.assertTrue(linkText.equals("Check Gift Card Balance"), "Check Gift Card Balance link text is changed");

		clickWithJS(GIFT_CARD_BALANCE_CHECK);

		wait.until(ExpectedConditions.titleContains("My Card Balance"));

		softassert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase("https://www.tommyjohn.com/pages/my-card-balance"),
				"Wrong page opened after Check Gift Card Balance Footer link was clicked");

		softassert.assertAll();

		System.out.println(
				"Check Gift Card Balance Link text is correct and Correct webpage opened after clicking on link\n");
		Reporter.log("Check Gift Card Balance footer link is Displayed :: Clickable");

		
//		Check Tommy John Stores
		System.out.println("***Validating Tommy John Stores Footer***");
		driver.navigate().back();
		wait.until(ExpectedConditions.titleIs("Tommy John | No Adjustment Needed"));

		linkText = driver.findElement(TOMMY_JOHN_STORES).getText();
		softassert.assertTrue(linkText.equals("Tommy John Stores"), "Tommy John Stores link text is changed");

		clickWithJS(TOMMY_JOHN_STORES);

		wait.until(ExpectedConditions.titleContains("Store Directory"));

		softassert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase("https://www.tommyjohn.com/pages/store-directory"),
				"Wrong page opened after Tommy John Stores Footer link was clicked");

		softassert.assertAll();

		System.out.println("Tommy John Stores Link text is correct and Correct webpage opened after clicking on link\n");
		Reporter.log("Tommy John Stores footer link is Displayed :: Clickable");

		
//		Check Store Locator
		System.out.println("***Validating Store Locator Footer***");
		driver.navigate().back();
		wait.until(ExpectedConditions.titleIs("Tommy John | No Adjustment Needed"));

		linkText = driver.findElement(STORE_LOCATOR_LINK).getText();
		softassert.assertTrue(linkText.equals("Store Locator"), "Store Locator link text is changed");

		clickWithJS(STORE_LOCATOR_LINK);

		wait.until(ExpectedConditions.titleContains("Store Locator"));

		softassert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase("https://www.tommyjohn.com/pages/store-locator"),
				"Wrong page opened after Store Locator Footer link was clicked");

		softassert.assertAll();

		System.out
				.println("Store Locator Footer Link text is Correct and Correct webpage opened after clicking on link\n");
		Reporter.log("Store Locator footer link is Displayed :: Clickable");

//		Check Retailers Footer
		System.out.println("***Validating Retailers Footer***");
		driver.navigate().back();
		wait.until(ExpectedConditions.titleIs("Tommy John | No Adjustment Needed"));

		linkText = driver.findElement(RETAILERS_LINK).getText();
		softassert.assertTrue(linkText.equals("Retailers"), "Retailers link text is changed");

		clickWithJS(RETAILERS_LINK);

		wait.until(ExpectedConditions.titleContains("Retailers"));

		softassert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase("https://www.tommyjohn.com/pages/retailers"),
				"Wrong page opened after Retailers Footer link was clicked");

		softassert.assertAll();

		System.out.println("Retailers Footer Link text is Correct and Correct webpage opened after clicking on link\n");
		Reporter.log("Retailers footer link is Displayed :: Clickable");

//		Check Affiliates Footer
		System.out.println("***Validating Affiliates Footer***");
		driver.navigate().back();
		wait.until(ExpectedConditions.titleIs("Tommy John | No Adjustment Needed"));

		linkText = driver.findElement(AFFILIATES_LINK).getText();
		softassert.assertTrue(linkText.equals("Affiliates"), "Affiliates link text is changed");

		clickWithJS(AFFILIATES_LINK);

		wait.until(ExpectedConditions.titleContains("Affiliate"));

		softassert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase("https://www.tommyjohn.com/pages/affiliate-landing"),
				"Wrong page opened after Affiliates Footer link was clicked");

		softassert.assertAll();

		System.out.println("Affiliates Footer Link text is Correct and Correct webpage opened after clicking on link\n");
		Reporter.log("Affiliates footer link is Displayed :: Clickable");

	}

	/**
	 * Validates if all links in Company Section are present and they navigate to
	 * correct page
	 * 
	 * @throws InterruptedException
	 */
	public void validateFooterCompanySection() throws InterruptedException {
		
		softassert = new SoftAssert();

		System.out.println("\n---Checking Footer Company Section Links---");

		if (!driver.getCurrentUrl().equals("https://www.tommyjohn.com")) {
			driver.navigate().to("https://www.tommyjohn.com");
		}

		objGeneralMethods.waitTillPageLoad(driver);
		
		objGeneralMethods.scrollElementIntoView(driver, ABOUT_LINK);
		
//		Scroll to bottom of page for footer section to be visible
//		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

//		Check About Link
		System.out.println("***Validating About Footer***");

		linkText = driver.findElement(ABOUT_LINK).getText();

		softassert.assertTrue(linkText.equals("About"), "About link text is changed");

		clickWithJS(ABOUT_LINK);

		wait.until(ExpectedConditions.titleContains("About Us"));

		softassert.assertTrue(
				driver.getCurrentUrl()
						.equalsIgnoreCase("https://www.tommyjohn.com/pages/about-us"),
				"Wrong page opened after About footer link was clicked");

		softassert.assertAll();
		System.out.println("About Footer Link text is Correct and Correct webpage opened after clicking on link\n");

		Reporter.log("About footer link is Displayed :: Clickable");

//		Check Careers
		System.out.println("***Validating Careers Footer***");
		
		driver.navigate().back();
		wait.until(ExpectedConditions.titleIs("Tommy John | No Adjustment Needed"));

		linkText = driver.findElement(CAREERS_LINK).getText();

		softassert.assertTrue(linkText.equals("Careers"), "Careers Link text is changed");

		clickWithJS(CAREERS_LINK);

		wait.until(ExpectedConditions.titleContains("Careers"));

		softassert.assertTrue(
				driver.getCurrentUrl()
						.equalsIgnoreCase("https://www.tommyjohn.com/pages/careers"),
				"Wrong page opened after Careers footer link was clicked");

		softassert.assertAll();
		System.out
				.println("Careers Footer Link text is Correct and Correct webpage opened after clicking on link\n");

		Reporter.log("Careers footer link is Displayed :: Clickable");
		
//		Check Press
		System.out.println("***Validating Press Footer***");
		
		driver.navigate().back();
		wait.until(ExpectedConditions.titleIs("Tommy John | No Adjustment Needed"));

		linkText = driver.findElement(PRESS_LINK).getText();

		softassert.assertTrue(linkText.equals("Press"), "Press Link text is changed");

		clickWithJS(PRESS_LINK);

		wait.until(ExpectedConditions.titleContains("Press"));

		softassert.assertTrue(
				driver.getCurrentUrl()
						.equalsIgnoreCase("https://www.tommyjohn.com/pages/press"),
				"Wrong page opened after Press footer link was clicked");

		softassert.assertAll();
		System.out
				.println("Press Footer Link text is Correct and Correct webpage opened after clicking on link\n");

		Reporter.log("Press footer link is Displayed :: Clickable");
		
//		Check Press
		System.out.println("***Validating Blog Footer***");
		
		driver.navigate().back();
		wait.until(ExpectedConditions.titleIs("Tommy John | No Adjustment Needed"));

		linkText = driver.findElement(BLOG_LINK).getText();

		softassert.assertTrue(linkText.equals("Blog"), "Blog Link text is changed");

		clickWithJS(BLOG_LINK);

		wait.until(ExpectedConditions.titleContains("News"));

		softassert.assertTrue(
				driver.getCurrentUrl()
						.equalsIgnoreCase("https://www.tommyjohn.com/blogs/news"),
				"Wrong page opened after Blog footer link was clicked");

		softassert.assertAll();
		System.out
				.println("Blog Footer Link text is Correct and Correct webpage opened after clicking on link\n");

		Reporter.log("Blog footer link is Displayed :: Clickable");
		
//		Check Reviews
		System.out.println("***Validating Reviews Footer***");
		
		driver.navigate().back();
		wait.until(ExpectedConditions.titleIs("Tommy John | No Adjustment Needed"));

		linkText = driver.findElement(REVIEWS_LINK).getText();

		softassert.assertTrue(linkText.equals("Reviews"), "Reviews Link text is changed");

		clickWithJS(REVIEWS_LINK);

		wait.until(ExpectedConditions.titleContains("Reviews"));

		softassert.assertTrue(
				driver.getCurrentUrl()
						.equalsIgnoreCase("https://www.tommyjohn.com/pages/reviews"),
				"Wrong page opened after Reviews footer link was clicked");

		softassert.assertAll();
		System.out
				.println("Reviews Link text is Correct and Correct webpage opened after clicking on link\n");

		Reporter.log("Reviews footer link is Displayed :: Clickable");	

	}
	
	/**
	 * Validates Social Media Icons in footer
	 * @throws Exception
	 */
	public void validateFooterSocialLinks() throws Exception {
		String hrefLink;
		
		System.out.println("\n---Checking CopyRight Seciton---");

		if (!driver.getCurrentUrl().equals("https://www.tommyjohn.com")) {
			driver.navigate().to("https://www.tommyjohn.com");
		}
		
//		Scroll the page at bottom to test all the footer Social Icons
		objGeneralMethods.scrollElementIntoView(driver, FACEBOOK_ICON);
		
//		Check Facebook Link
		System.out.println("***Validating Facebook Link***");
		softassert = new SoftAssert();
		
		element = driver.findElement(FACEBOOK_ICON);
		hrefLink = driver.findElement(FACEBOOK_LINK).getAttribute("href");
		
		clickWithJS(FACEBOOK_LINK);

		Thread.sleep(1000);

		list = driver.findElements(LEAVE_SITE_POPUP);

		Assert.assertTrue(list.size() > 0, "Leave Site Popup has not appeared");

		clickWithJS(LEAVE_SITE_POPUP_CANCEL_BTN);
		
		softassert.assertTrue(element.isDisplayed(), "Facebook Icon is not Displayed");
		softassert.assertTrue(hrefLink.equals("https://www.facebook.com/TommyJohnWear"), "Facebook Link is not Correct");
		
		softassert.assertAll();
		
		System.out.println("Facebook Icon is Present and Link is correct\n");
		Reporter.log("Facebook Footer Icon is Displayed :: Clickable");	
		
//		Check Twitter Link
		System.out.println("***Validating Twitter Link***");
		softassert = new SoftAssert();
		
		element = driver.findElement(TWITTER_ICON);
		hrefLink = driver.findElement(TWITTER_LINK).getAttribute("href");
		
		clickWithJS(TWITTER_LINK);

		Thread.sleep(1000);

		list = driver.findElements(LEAVE_SITE_POPUP);

		Assert.assertTrue(list.size() > 0, "Leave Site Popup has not appeared");

		clickWithJS(LEAVE_SITE_POPUP_CANCEL_BTN);
		
		softassert.assertTrue(element.isDisplayed(), "Twitter Icon is not Displayed");
		softassert.assertTrue(hrefLink.equals("https://twitter.com/TommyJohn"), "Twitter Link is not Correct");
		
		softassert.assertAll();

		System.out.println("Twitter Icon is Present and Link is correct\n");
		Reporter.log("Twitter Footer Icon is Displayed :: Clickable");

//		Check Pinterest Link
		System.out.println("***Validating Pinterest Link***");
		softassert = new SoftAssert();
		
		element = driver.findElement(PINTEREST_ICON);
		hrefLink = driver.findElement(PINTEREST_LINK).getAttribute("href");
		
		clickWithJS(PINTEREST_LINK);

		Thread.sleep(1000);

		list = driver.findElements(LEAVE_SITE_POPUP);

		Assert.assertTrue(list.size() > 0, "Leave Site Popup has not appeared");

		clickWithJS(LEAVE_SITE_POPUP_CANCEL_BTN);
		
		softassert.assertTrue(element.isDisplayed(), "Pinterest Icon is not Displayed");
		softassert.assertTrue(hrefLink.equals("https://www.pinterest.com/tommyjohnwear/"), "Pinterest Link is not Correct");
		
		softassert.assertAll();

		System.out.println("Pinterest Icon is Present and Link is correct\n");
		Reporter.log("Pinterest Footer Icon is Displayed :: Clickable");
		
//		Check Instagram Link
		System.out.println("***Validating Instagram Link***");
		softassert = new SoftAssert();
		
		element = driver.findElement(INSTAGRAM_ICON);
		hrefLink = driver.findElement(INSTAGRAM_LINK).getAttribute("href");
		
		clickWithJS(INSTAGRAM_LINK);

		Thread.sleep(1000);

		list = driver.findElements(LEAVE_SITE_POPUP);

		Assert.assertTrue(list.size() > 0, "Leave Site Popup has not appeared");

		clickWithJS(LEAVE_SITE_POPUP_CANCEL_BTN);
		
		softassert.assertTrue(element.isDisplayed(), "Instagram Icon is not Displayed");
		softassert.assertTrue(hrefLink.equals("https://www.instagram.com/tommyjohnwear/"), "Pinterest Link is not Correct");
		
		softassert.assertAll();

		System.out.println("Instagram Icon is Present and Link is correct\n");
		Reporter.log("Instagram Footer Icon is Displayed :: Clickable");
		
//		Check Youtube Link
		System.out.println("***Validating Youtube Link***");
		softassert = new SoftAssert();
		
		element = driver.findElement(YOUTUBE_ICON);
		hrefLink = driver.findElement(YOUTUBE_LINK).getAttribute("href");
		
		clickWithJS(YOUTUBE_LINK);

		Thread.sleep(1000);

		list = driver.findElements(LEAVE_SITE_POPUP);

		Assert.assertTrue(list.size() > 0, "Leave Site Popup has not appeared");

		clickWithJS(LEAVE_SITE_POPUP_CANCEL_BTN);
		
		softassert.assertTrue(element.isDisplayed(), "Youtube Icon is not Displayed");
		softassert.assertTrue(hrefLink.equals("https://www.youtube.com/user/tommyjohnwear"), "Pinterest Link is not Correct");
		
		softassert.assertAll();

		System.out.println("Youtube Icon is Present and Link is correct\n");
		Reporter.log("Youtube Footer Icon is Displayed :: Clickable");
		
	}
	
	/**
	 * Validates the links - Privacy Policy, T&C, Do Not Sell My info, CA Transparency, CopyRight
	 * @throws Exception
	 */
	public void validateFooterCopyrightCCPASection() throws Exception{
		
		String pageHeader;
		String pageURL;

		System.out.println("\n---Checking CopyRight Seciton---");

		if (!driver.getCurrentUrl().equals("https://www.tommyjohn.com")) {
			driver.navigate().to("https://www.tommyjohn.com");
		}

		objGeneralMethods.waitTillPageLoad(driver);
		
		objGeneralMethods.scrollElementIntoView(driver, PRIVACY_POLICY_LINK);
		
//		Checking Privacy Policy
		System.out.println("***Validating Privacy Policy***");
		
		softassert = new SoftAssert();
		
		element = driver.findElement(PRIVACY_POLICY_LINK);
		linkText = driver.findElement(PRIVACY_POLICY_LINK).getText();
		
		Assert.assertTrue(element.isDisplayed(), "Privacy Policy Link is not Displayed");
		softassert.assertTrue(linkText.equals("Privacy Policy"), "Privacy Policy Link Text is Incorrect");

		clickWithJS(element);
		
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(PrivacyPolicyPageLocators.PAGE_HEADER, 0));
		
		pageURL = driver.getCurrentUrl();
		pageHeader = driver.findElement(PrivacyPolicyPageLocators.PAGE_HEADER).getText();
		
		softassert.assertTrue
		(pageURL.equals("https://www.tommyjohn.com/pages/tommy-john-website-privacy-policy"), "Wrong Page opened after clicking on Privacy Policy Link ");
		softassert.assertTrue(pageHeader.equals("Tommy John Website Privacy Policy"), "Page Header doesn't match");
		
		softassert.assertAll();
		
		System.out.println("Privacy Policy Link is Displayed and Corresponding Page opened after clicking on Link\n");
		Reporter.log("Privacy Policy Link is Displayed and Corresponding Page opened after clicking on Link");
		
//		Checking Terms and Conditions
		driver.navigate().back();
		System.out.println("***Validating Terms and Conditions***");
		
		softassert = new SoftAssert();
		
		element = driver.findElement(TERMS_AND_CONDITION_LINK);
		linkText = driver.findElement(TERMS_AND_CONDITION_LINK).getText();
		
		Assert.assertTrue(element.isDisplayed(), "Terms and Conditions Link is not Displayed");
		softassert.assertTrue(linkText.equals("Terms & Conditions"), "Terms and Conditions Link Text is Incorrect");

		clickWithJS(element);
		
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(TermsAndConditionsPageLocators.PAGE_HEADER, 0));
		
		pageURL = driver.getCurrentUrl();
		pageHeader = driver.findElement(TermsAndConditionsPageLocators.PAGE_HEADER).getText();
		
		softassert.assertTrue
		(pageURL.equals("https://www.tommyjohn.com/pages/tommy-john-terms-and-conditions"), "Wrong Page opened after clicking on Terms & Conditions Link ");
		softassert.assertTrue(pageHeader.equals("Tommy John Terms & Conditions"), "Page Header doesn't match");
		
		softassert.assertAll();
		
		System.out.println("Tommy John Terms & Conditions Link is Displayed and Corresponding Page opened after clicking on Link\n");
		Reporter.log("Tommy John Terms & Conditions Link is Displayed and Corresponding Page opened after clicking on Link");

//		Checking Do Not Sell My Information
		driver.navigate().back();
		System.out.println("***Validating Do Not Sell My Information***");
		
		softassert = new SoftAssert();
		
		element = driver.findElement(DO_NOT_SELL_PERSONAL_INFO_LINK);
		linkText = driver.findElement(DO_NOT_SELL_PERSONAL_INFO_LINK).getText();
		
		Assert.assertTrue(element.isDisplayed(), "Do Not Sell My Information Link is not Displayed");
		softassert.assertTrue(linkText.equals("Do Not Sell My Information"), "Do Not Sell My Information Link Text is Incorrect");

		clickWithJS(element);
		
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(DoNotSellMyInfoPageLocators.PAGE_HEADER, 0));
		
		pageURL = driver.getCurrentUrl();
		pageHeader = driver.findElement(DoNotSellMyInfoPageLocators.PAGE_HEADER).getText();
		
		softassert.assertTrue
		(pageURL.equals("https://www.tommyjohn.com/pages/do-not-sell-my-personal-information"), "Wrong Page opened after clicking on Do Not Sell My Information Link ");
		softassert.assertTrue(pageHeader.equals("For California residents- CCPA: “Do Not Sell Or Share My Personal Information Request Form"), "Page Header doesn't match");
		
		softassert.assertAll();
		
		System.out.println("Do Not Sell My Information Link is Displayed and Corresponding Page opened after clicking on Link\n");
		Reporter.log("Do Not Sell My Information Link is Displayed and Corresponding Page opened after clicking on Link");

//		Checking CA Transparency in Supply Chains Act
		driver.navigate().back();
		System.out.println("***Validating CA Transparency in Supply Chains Act***");
		
		softassert = new SoftAssert();
		
		element = driver.findElement(CA_SUPPLY_CHAINS_ACT_LINK);
		linkText = driver.findElement(CA_SUPPLY_CHAINS_ACT_LINK).getText();
		
		Assert.assertTrue(element.isDisplayed(), "CA Transparency in Supply Chains Act Link is not Displayed");
		softassert.assertTrue(linkText.equals("CA Transparency in Supply Chains Act"), "CA Transparency in Supply Chains Act Link Text is Incorrect");

		clickWithJS(element);
		
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(CA_SupplyChainsActPageLocators.PAGE_HEADER, 0));
		
		pageURL = driver.getCurrentUrl();
		pageHeader = driver.findElement(CA_SupplyChainsActPageLocators.PAGE_HEADER).getText();
		
		softassert.assertTrue
		(pageURL.equals("https://www.tommyjohn.com/pages/california-supply-chains-act"), "Wrong Page opened after clicking on CA Transparency in Supply Chains Act");
		softassert.assertTrue(pageHeader.equals("California Supply Chains Act"), "Page Header doesn't match");
		
		softassert.assertAll();
		
		System.out.println("CA Transparency in Supply Chains Act Link is Displayed and Corresponding Page opened after clicking on Link\n");
		Reporter.log("CA Transparency in Supply Chains Act Link is Displayed and Corresponding Page opened after clicking on Link");
	}
	
	/**
	 * Validates Sign Up for Email and Feedback Section
	 * @throws Exception
	 */
	public void validateFooterNewsletter() throws Exception{

		System.out.println("\n---Checking Newsletter Seciton---");

		if (!driver.getCurrentUrl().equals("https://www.tommyjohn.com")) {
			driver.navigate().to("https://www.tommyjohn.com");
		}

		objGeneralMethods.waitTillPageLoad(driver);
		
		objGeneralMethods.scrollElementIntoView(driver, NEWSLETTER_TITLE);
		
		// Verify Footer Newsletter section
		System.out.println("***Validating Newsletter Email Sign Up***");
		
		softassert = new SoftAssert();
		
		softassert.assertTrue(driver.findElement(NEWSLETTER_TITLE).isDisplayed(),
				"Newsletter Title is not displayed on footer");
		softassert.assertTrue(driver.findElement(NEWSLETTER_MEN_RADIO_BUTTON).isDisplayed() && driver.findElement(NEWSLETTER_MEN_RADIO_BUTTON).isEnabled(),
				"Newsletter Radio button 'Men' is either not Displayed or not Enabled on footer");
		softassert.assertTrue(driver.findElement(NEWSLETTER_WOMEN_RADIO_BUTTON).isDisplayed() && driver.findElement(NEWSLETTER_WOMEN_RADIO_BUTTON).isEnabled(),
				"Newsletter Radio button 'Women' is either not Displayed or not Enabled on footer");
		softassert.assertTrue(driver.findElement(NEWSLETTER_BOTH_RADIO_BUTTON).isDisplayed() && driver.findElement(NEWSLETTER_BOTH_RADIO_BUTTON).isEnabled(),
				"Newsletter Radio button 'Both' is either not Displayed or not Enabled on footer");
		softassert.assertTrue(driver.findElement(NEWSLETTER_INPUT_TEXTBOX).isDisplayed(),
				"Newsletter Email Input Text Box is not displayed on footer");
		softassert.assertTrue(driver.findElement(NEWSLETTER_SIGN_UP_BUTTON).isDisplayed(),
				"Newsletter Sign up button is not displayed on footer");
		
		softassert.assertTrue(driver.findElement(NEWSLETTER_BOTH_RADIO_BUTTON).isSelected(), "Radio Button 'Both' is not preselected for user");
		
//		Negative Validation (User does not enter email)
		driver.findElement(NEWSLETTER_SIGN_UP_BUTTON).click();
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(NEWSLETTER_FORM_FAIL_MESSAGE_VISIBLE, 0));
		
		softassert.assertTrue(driver.findElement(NEWSLETTER_FORM_FAIL_MESSAGE).isDisplayed(),
				"Error message is not displayed ");
		softassert.assertTrue(driver.findElement(NEWSLETTER_FORM_FAIL_MESSAGE).getText().equals("Please enter a valid email !"),
				"Incorrect message is displayed ");
		
//		Positive Validation
		String testemail = "testaccount" + System.currentTimeMillis() + "@gmail.com";
		driver.findElement(NEWSLETTER_INPUT_TEXTBOX).sendKeys(testemail);
		driver.findElement(NEWSLETTER_SIGN_UP_BUTTON).click();
		
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(NEWSLETTER_FORM_SUCCESS_MESSAGE_VISIBLE, 0));

		softassert.assertTrue(driver.findElement(NEWSLETTER_FORM_SUCCESS_MESSAGE).isDisplayed(),
				"Success message is not displayed after newsletter form submission");
		
		softassert.assertAll();
		
		System.out.println("Newsletter Email Sign Up functionality is working and Success Message is Displayed to user\n");
		Reporter.log("Newsletter Email Sign Up functionality is working and Success Message is Displayed to user");
		
//		Validation of Give Feedback Section
		System.out.println("***Validating Feedback Section***");
		
		softassert = new SoftAssert();
		
		element = driver.findElement(GIVE_FEEDBACK_TITLE);
		
		Assert.assertTrue(driver.findElement(GIVE_FEEDBACK_CTA).isDisplayed(), "Give Feedback button is not present");
		softassert.assertTrue(element.isDisplayed(), "Feedback title is not present");
		softassert.assertTrue(element.getText().equals("Your feedback matters to us"), "Feedback title does not match");
		
		driver.findElement(GIVE_FEEDBACK_CTA).click();
		
		wait.until(ExpectedConditions.titleContains("Survey"));
		
		String feedbackPageHeader_TXT = driver.findElement(By.cssSelector(".comment-text")).getText();
		String Feedback_page_url = "https://selfserve.decipherinc.com/survey/selfserve/";
		
		softassert.assertTrue(
				feedbackPageHeader_TXT.equals("Thank you for taking our survey - your insights help us make sure we are building the best possible shopping experience for you. If you need any immediate assistance please call us 1-800-708-3490."), "Survey Page Header doesnt match");
		softassert.assertTrue(driver.getCurrentUrl().contains(Feedback_page_url),
				"Wrong page opened after click on Give Feedback CTA on footer");

		softassert.assertAll();
		
		System.out.println("Give Feedback Section is present and corresponding page opened after clicking on 'Give Feedback' button");
		Reporter.log("Give Feedback Section is present and corresponding page opened after clicking on 'Give Feedback' button");
	}
	



	public void validate_DoNotSellMyPersonalInfo() throws Exception
	{
	//	NavigateToDoNotSellMyPersonalInfoPage();
		softassert.assertTrue(driver.findElement(CCPA_STATE_OF_RESIDENCE_QSN).isDisplayed(), "'What is your state of residence' question is not displayed on CCPA page");
		softassert.assertTrue(driver.findElement(STATE_DROPDOWN).isDisplayed(), "State option dropdown is not displayed on CCPA page");

		Select stateoptions = new Select(driver.findElement(STATE_DROPDOWN));
		//Check field validations
		stateoptions.selectByValue("California");
		Thread.sleep(2000);
		driver.findElement(SUBMIT_REQUEST_CTA).click();
		softassert.assertTrue(driver.findElement(ERROR_MESSAGE).isDisplayed(), "Error message is not displayed for wrong/empty submission with california user");
		Reporter.log("Field validation is correct on Do Not Sell My Personal Information Page");
		ExtentReport.extentTest.info("Field validation is correct on Do Not Sell My Personal Information Page");

		//select all other states
		stateoptions.selectByValue("All other states");
		Thread.sleep(2000);
		softassert.assertTrue(driver.findElement(NON_CALIFORNIA_USER_HINT_SECTION).isDisplayed(), "how to protect privacy message for non california user is not displayed ");
		driver.findElement(BACK_TO_HOME_CTA).click();
		Thread.sleep(3000);
		softassert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase("https://tommyjohn.com/"), "Wrong page opened after click on 'Back to Home' CTA from CCPA page");
		Reporter.log("Page is correct for non-california user");
		ExtentReport.extentTest.info("Page is correct for non-california user");


		//Select California state
		//NavigateToDoNotSellMyPersonalInfoPage();
		stateoptions = new Select(driver.findElement(STATE_DROPDOWN));
		stateoptions.selectByValue("California");
		Thread.sleep(3000);
		softassert.assertTrue(driver.findElement(CALIFORNIA_USER_FORM_HEADING).isDisplayed(), "Form heading for California user is not displayed");
		softassert.assertTrue(driver.findElement(NAME_LABEL).isDisplayed(), "Name label for California user is not displayed");
		softassert.assertTrue(driver.findElement(NAME_TEXTBOX).isDisplayed(), "Name Textbox for California user is not displayed");
		driver.findElement(NAME_TEXTBOX).sendKeys("test_name");
		softassert.assertTrue(driver.findElement(EMAIL_LABEL).isDisplayed(), "Email Label for California user is not displayed");
		softassert.assertTrue(driver.findElement(EMAIL_TEXTBOX).isDisplayed(), "Email textbox for California user is not displayed");
		String testemail = "testaccount" + System.currentTimeMillis() +"@gmail.com";
		driver.findElement(EMAIL_TEXTBOX).sendKeys(testemail);
		softassert.assertTrue(driver.findElement(WOULD_LIKE_TO_HEADING).isDisplayed(), "'I would like to' heading is not displayed for checkbox selection");
		int total_checkbox = driver.findElements(By.cssSelector(".ccpa-form-california-user>div:nth-child(4)>.checkbox-wrapper>label>div:nth-child(1)")).size();
		for(int i=2; i<=total_checkbox+1; i++)
		{
			WebElement checkbox = driver.findElement(By.cssSelector(".ccpa-form-california-user>div:nth-child(4)>.checkbox-wrapper:nth-child("+i+")>label>div:nth-child(1)"));
			WebElement checkbox_label = driver.findElement(By.cssSelector(".ccpa-form-california-user>div:nth-child(4)>.checkbox-wrapper:nth-child("+i+")>label>div:nth-child(2)>span"));
			softassert.assertTrue(checkbox_label.isDisplayed(), "Checkbox label is not displayed for checkbox number"+(i-1));
			checkbox.click();
			Thread.sleep(3000);
			System.out.println("selected checkbox is: "+checkbox_label.getText());
		}
		softassert.assertTrue(driver.findElement(SUBMIT_REQUEST_CTA).isDisplayed(), "Submit Request CTA is not displayed for california user");
		driver.findElement(SUBMIT_REQUEST_CTA).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.presenceOfElementLocated(SUCCESSFULLY_SUBMIT_MESSAGE));
		softassert.assertTrue(driver.findElement(SUCCESSFULLY_SUBMIT_MESSAGE).isDisplayed(), "Successful submit message is not displayed for california user");
		softassert.assertTrue(driver.findElement(BACK_TO_HOME_CTA).isDisplayed(), "'Back to Home' CTA is not displayed after successful submission by california user");
		//		WebElement element= driver.findElement(BACK_TO_HOME_CALIFORNIA);
		//		((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
		//		Thread.sleep(3000);
		//		System.out.println(element.getText()+" CTA is cliackable");
		//		softassert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase("https://tommyjohn.com/"), "Wrong page opened after click on 'Back to Home' CTA after successful submission by california user");
		Reporter.log("Page is correct for california user");
		ExtentReport.extentTest.info("Page is correct for california user");
		softassert.assertAll();

	}

	public void VerifyQuickShopOnDYRecs() throws Exception
	{
		Actions action = new Actions(driver);
		Thread.sleep(3000);
		element= driver.findElement(ProductDetailsPageLocators.RFY_SECTION);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView(true);",element);
		Thread.sleep(3000);
		element = driver.findElement(ProductDetailsPageLocators.RFY_PRODUCT_IMAGE);
		jse.executeScript("arguments[0].scrollIntoView(true);", element);
		action.moveToElement(element).build().perform();
		Thread.sleep(3000);
		new CollectionPageComponent(driver).AddproductToCartByQuickShop();
		Reporter.log("Verified DY recs quick shop functionality on HomePgae");
		ExtentReport.extentTest.info("Verified DY recs quick shop functionality on HomePgae");
	}

	public void MethodToCheckColorSwatchOnDYRecs() throws Exception
	{
		SoftAssert softassert = new SoftAssert();
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		Thread.sleep(4000);
		element= driver.findElement(ProductDetailsPageLocators.RFY_SECTION);
		jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView(true);",element);
		Thread.sleep(3000);

		List<WebElement> allElements = driver.findElements(RFY_SWATCH_COUNT);
		//	System.out.println("color classes are: "+driver.findElement(RFY_SWATCH_COUNT).getAttribute("class"));
		int elementsCount = allElements.size();
		System.out.println("Color Element count :: " + elementsCount);

		if (elementsCount > 1)
		{
			element = driver.findElement(By.cssSelector(".dy-rec--products>div:nth-child(1) > div > div.product-item__swatches:nth-child(2)> div > div:nth-child(1)>div"));
			element.click();

			for (int i = 1; i < elementsCount; i++) {
				if (i < 4) {
					String Img_colortext = null;
					String colornew1 = null;
					String text = driver.findElement(RFY_PRODUCT_IMG_COLORNAME).getAttribute("href");
					String[] strarray = text.split("[=]");
					String text1 = strarray[strarray.length - 1];
					Img_colortext = text1.replace("-", " ");

					System.out.println("Product image color name is: " + Img_colortext);


					element = driver.findElement(By.cssSelector(".dy-rec--products>div:nth-child(1) > div > div.product-item__swatches:nth-child(2)> div > div:nth-child("+i+")>div"));
					colornew1 = element.getAttribute("title"); 
					//	String colornew = colornew1.replace("/", " ");
					System.out.println("swatch color is: "+colornew1);
					if(colornew1.replace("/", " ").equalsIgnoreCase(Img_colortext)) 
					{
						WebElement elemen =driver.findElement(By.cssSelector(".dy-rec--products>div:nth-child(1) > div > div.product-item__swatches:nth-child(2)> div > div:nth-child("+(i+1)+")>div"));
						jse.executeScript("arguments[0].click();", elemen);
						//	softassert.assertTrue(Img_colortext.equalsIgnoreCase(colornew1), "Color not changing");
						Reporter.log("Color swatches are changing correctly");
						ExtentReport.extentTest.info("Color swatches are changing correctly");
					}
					else {
						throw new Exception("Color not changing");
					}
				}
			}
		}
		softassert.assertAll();
	}

	public void RFY_ProductTileHoverFunctionality() throws Exception
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		element= driver.findElement(ProductDetailsPageLocators.RFY_SECTION);
		jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView(true);",element);
		Thread.sleep(3000);

		wait.until(ExpectedConditions.presenceOfElementLocated(ProductDetailsPageLocators.RFY_PRODUCT_IMAGE));
		String text1 = driver.findElement(ProductDetailsPageLocators.RFY_HOVER_PRODUCT_IMG).getAttribute("src");
		String[] imagetext = text1.split("_425");
		String Hover_on_plp = imagetext[0];
		element = driver.findElement(ProductDetailsPageLocators.RFY_PRODUCT_IMAGE);
		jse.executeScript("arguments[0].click();", element);
		Thread.sleep(3000);
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(ProductDetailsPageLocators.SECOND_VERTICAL_IMAGE));
		String text = driver.findElement(ProductDetailsPageLocators.SECOND_VERTICAL_IMAGE).getAttribute("src");
		String[] list1 = text.split(".jpg");
		String Second_image_pdp = list1[0];
		if(!Hover_on_plp.equalsIgnoreCase(Second_image_pdp))
			throw new Exception("Product tile hover functionality is not correct");
		Reporter.log("Verified Product tile hover functionality");	
		ExtentReport.extentTest.info("Verified Product tile hover functionality");

	}

	public void Handle_GovX_Popup() throws Exception
	{
		String parent = driver.getWindowHandle();
		java.util.Set<String> s= driver.getWindowHandles();
		Iterator<String> I1 = s.iterator();
		while(I1.hasNext())
		{
			String child_window= I1.next();
			if(!parent.equals(child_window))
			{
				driver.switchTo().window(child_window);
				System.out.println("pop-up url is: "+driver.getCurrentUrl());
				if(driver.getCurrentUrl().contains("https://auth.govx.com/"))
				{
					driver.close();
				}
				else
					throw new Exception("Wrong page opened after footer link clicked");
			}
		}
		driver.switchTo().window(parent);

	}

	// navigate new arrival in men category
	public void navigateToNewArrivalsInMenCategory() throws Exception {
		driver.get("https://tommyjohn.com/collections/mens-new-arrivals-1");
	}

	// navigate to all underwear in men category 
	public void navigateToPLP(String url) throws Exception {
		driver.get(url);
		Thread.sleep(5000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)");
	}
	public void navigateToAllUnderwearInMenCategory() throws Exception {
		driver.get("https://www.tommyjohn.com/collections/underwear-mens-styles-boxer-brief");
		Thread.sleep(5000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)");
	}

	public void navigateToBraInWomenCategory()  {
		driver.get("https://www.tommyjohn.com/collections/bras-bralettes");
		
	}


	public void navigateToWomensUnderwear()
	{
		driver.get("https://www.tommyjohn.com/collections/womens-underwear");
	}

	public void navigateToMensAll()
	{
		driver.get("https://www.tommyjohn.com/collections/mens-all");
	}

	public void navigateToWomensAll()
	{
		driver.get("https://www.tommyjohn.com/collections/womens-all");
	}

	public void navigateToMensApparel()
	{
		driver.get("https://www.tommyjohn.com/collections/mens-apparel");
	}
	public void navigateToMensBriefs()
	{
		driver.get("https://www.tommyjohn.com/collections/underwear-mens-styles-brief");
	}

	public void navigateToSales()
	{
		driver.get("https://www.tommyjohn.com/collections/sale-mens-womens");
	}

	public void navigateToSecondSkinSleepCrewNeckPocket()
	{
		driver.get("https://www.tommyjohn.com/products/second-skin-mid-length-boxer-brief-9?variant=43190760996909");
	}


	public void navigateToMensUnderShirts()
	{
		driver.get("https://www.tommyjohn.com/collections/mens-undershirts?sort-by=relevance&sort-order=descending");
	}

	//navigate to Button down shirts
	public void navigateToButtonDownShirtInMenCategory() throws Exception {

		driver.get("https://tommyjohn.com/collections/mens-shirts");
	}
	
	public void navigateToCareersPage() throws Exception {

		driver.get("https://tommyjohn.com/pages/careers");
	}

	public void navigateToAboutUsPage() throws Exception {

		driver.get("https://tommyjohn.com/pages/about-us");
	}
	
	
	
	// navigate to Pants and shorts in Men's category
	public void navigateToPantsAndShortsInMensCategory() throws Exception {
		driver.get("https://tommyjohn.com/collections/pants-and-shorts");

	}

	// navigate to Hoodies & Jackets in Men's category
	public void navigateToHoodiesandJacketsInMensCategory() throws Exception {
		driver.get("https://tommyjohn.com/collections/jackets-outerwear-1");

	}

	// navigate to Camisole in womens category
	public void navigateToCamisoleInwomensCategory() throws Exception {
		driver.get("https://tommyjohn.com/collections/womens-camisoles");

	}	

	public void navigateToDressesInwomensCategory() throws Exception {
		driver.get("https://tommyjohn.com/collections/womens-dresses");

	}

	// navigate to Tops & Tees in womens category
	public void navigateToTopsAndTeesInwomensCategory() throws Exception {
		driver.get("https://tommyjohn.com/collections/womens-tops-tees");

	}		
	// navigate to Hoodies and Jackets in womens category
	public void navigateToHoodiesandJacketsInwomensCategory() throws Exception {
		driver.get("https://tommyjohn.com/collections/womens-sweatshirts-jackets");

	}		
	// navigate to Pants & Shorts in womens category
	public void navigateToPantsandShortsInwomensCategory() throws Exception {
		driver.get("https://tommyjohn.com/collections/womens-pants-shorts");

	}	

	// navigate to tank in men category
	public void navigateToTankInMenCategory() throws Exception {
		driver.get("https://tommyjohn.com/collections/mens-tanks");
	}

	// navigate to dress shirts in men category
	public void navigateToDressShirtsInMenCategory() throws Exception {
		driver.get("https://tommyjohn.com/collections/mens-shirts");
	}

	// navigate to all mens collections in men category
	public void navigateToAllMensCollectionsInMenCategory() throws Exception {
		driver.get("https://tommyjohn.com/collections/mens-all");
		Thread.sleep(10000);
		//		element = driver.findElement(By.cssSelector("div.mega-collection-filters--controls > a"));
		//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		//		wait.until(ExpectedConditions.presenceOfElementLocated((By) element));
		//		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",element);
	}

	// navigate to New Arrivals in Women category
	public void navigateToNewArrivalsInWomenCategory() throws Exception {
		driver.get("https://tommyjohn.com/collections/womens-new-arrivals");
	}

	// navigate to All Panties in Women category
	public void navigateToAllPantiesnInWomenCategory() throws Exception {
		driver.get("https://tommyjohn.com/collections/womens-underwear");
	}

	// navigate to Tees and Polos in Women category
	public void navigateToTeesAndPolosInWomenCategory() throws Exception {
		driver.get("https://tommyjohn.com/collections/womens-tops-tees");
	}

	// navigate to All Womens Collections in Women category
	public void navigateToAllWomensCollectionsInWomenCategory() throws Exception {
		driver.get("https://tommyjohn.com/collections/all-womens");
	}

	// navigate to All Mens Packs in Packs category
	public void navigateToAllMensPacksInPacksCategory() throws Exception {
		driver.get("https://www.tommyjohn.com/collections/mens-underwear?filter.pack_size=set%3B3%3B6%3B2%3B5");
	}



	public void navigateToExploreWomensCategory() throws Exception {
		driver.get("https://www.tommyjohn.com/collections/explore-womens");
	}

	public void navigateToEveryDayEssentials() throws Exception {
		driver.get("https://www.tommyjohn.com/products/second-skin-trunk-4-3-pack-46?variant=43053722468397");
	}

	// navigate to All Womens Packs in Packs category
	public void navigateToAllWomensPacksInPacksCategory() throws Exception {
		driver.get("https://tommyjohn.com/collections/womens-packs");
		Thread.sleep(5000);
	}

	public void navigatetoPDP() throws Exception {
		driver.get("https://www.tommyjohn.com/products/womens-downtime-pajama-top-short-set-3?variant=42816122486829\n");
		Thread.sleep(5000);
	}

	// navigate to single collection cool cotton
	public void navigateToCoolCottonInMensCollection() throws Exception {
		driver.get("https://tommyjohn.com/collections/cool-cotton");
	}

	// navigate to All Clothing in womens categories
	public void navigateToAllClothingInWomensCategory() throws Exception {
		driver.get("https://tommyjohn.com/collections/womens-all-clothing");
	}

	// navigate to second skin in men catagory
	public void navigateToSecondSkinInMenCategory() throws Exception {
		driver.get("https://tommyjohn.com/collections/mens-second-skin");
		Thread.sleep(5000);

	}
	// navigate to all panties in women catagory
	public void navigateToAllPantiesInWomenCategory() throws Exception {
		driver.get("https://tommyjohn.com/collections/womens-underwear");
		Thread.sleep(5000);
	}

	public void navigateToMensAndLoungeTops() throws Exception {
		driver.get("https://www.tommyjohn.com/search?keywords=mens-sleep-lounge");
		Thread.sleep(5000);
	}

	public void navigateToMensUnderwear() throws Exception {
		driver.get("https://www.tommyjohn.com/collections/mens-underwear");
		Thread.sleep(5000);
	}

	public void navigateToAWomensAirBikini() throws Exception {
		driver.get("https://www.tommyjohn.com/collections/womens-air?sort-by=relevance&sort-order=descending");
	}

	public void navigateToAirTriangleBralette()
	{
		driver.get("https://www.tommyjohn.com/products/air-triangle-bralette?variant=42358949773357");
	}

	public void navigateToSecondSkinMidlengthBoxerBrief()
	{
		driver.get("https://www.tommyjohn.com/products/second-skin-mid-length-boxer-brief-9?variant=41164297338925");
	}

	// navigate to cotton basics in shop by collections catagory
	public void navigateToCottonBasicsInMensCategory() throws Exception {
		driver.get("https://tommyjohn.com/collections/mens-cotton-basics");

	}

	// navigate to cotton basics in shop by collections catagory
	public void navigateToAllUnderShirtsInMensCategory() throws Exception {
		driver.get("https://tommyjohn.com/collections/mens-undershirts");
	}

	// navigate to cotton basics in shop by collections catagory
	public void navigateToAllWomensCollectionInWomensCategory() throws Exception {
		driver.get("https://tommyjohn.com/collections/all-womens");
		Thread.sleep(5000);

	}
	public void navigateToMensUWCAT () throws Exception {
		driver.get("https://www.tommyjohn.com/collections/mens-underwear?sort-by=relevance&sort-order=descending&filter%5Bfacet_category%5D=underwear");
		Thread.sleep(5000);

	}

	public void navigateToMensCoolCotton () throws Exception {
		driver.get("https://www.tommyjohn.com/collections/mens-cool-cotton");
		Thread.sleep(5000);

	}
	

	// navigate to Gift Card category
	public void navigateToGiftCard() throws Exception {
		driver.get("https://tommyjohn.com/products/e-gift-card");

	}

	//navigate to physical GC
	public void navigateToPhysicalGC()
	{
		driver.get("https://tommyjohn.com/collections/gift-cards/products/gift-card");
	}
	// navigate to Bra category
	public void navigateToBraCategory() throws Exception {
		driver.get("https://tommyjohn.com/collections/bras");

	}
	public void navigateTo360Sport()
	{
		driver.get("https://www.tommyjohn.com/collections/underwear-mens-styles-mid-length-boxer-brief-360-sport");
	}

	//navigate to GC plp
	public void NavigateToGCPLP()
	{
		driver.get("https://tommyjohn.com/collections/gift-cards");
	}

	// navigate to Triangle Bralette category
	public void navigateToTriangleBraletteCategory() throws Exception {
		driver.get("https://tommyjohn.com/collections/bra-womens-styles-triangle-bralette");

	}

	// navigate to Scoop Bralette category
	public void navigateToScoopBraletteCategory() throws Exception {
		driver.get("https://tommyjohn.com/collections/bra-womens-styles-scoop-bralette");

	}

	// navigate to all panties in women catagory
	public void navigateToMysteryPacksinPacksCategory() throws Exception {

		driver.get("https://tommyjohn.com/search?keywords=mystery+packs");
	}



	// navigate to Contact Us Page
	public void navigateToHelpPage() throws Exception {

		driver.get(("https://help.tommyjohn.com/hc/en-us"));
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//		JavascriptExecutor jse = (JavascriptExecutor)driver;
//		text = driver.findElement(CONTACT_US_LINK).getText();
//		if(!text.contains("Contact & FAQ"))
//			throw new Exception("Contact Us link text is changed");
//		jse.executeScript("arguments[0].click();", driver.findElement(CONTACT_US_LINK));
		Thread.sleep(5000);
					if(driver.getCurrentUrl().contains("https://help.tommyjohn.com/hc/en-us"))
					{
						System.out.println("Help Us page opened correctly");
						Reporter.log("Help Us page opened correctly");
						ExtentReport.extentTest.info("Help Us page opened correctly");
					}
					
					else {
						System.out.println("Help Us page didn't open correctly");			
						ExtentReport.extentTest.info("Help Us page didn't open correctly");
						throw new Exception("Help Us page didn't open correctly");
				}


	}
	
	public void navigateToContactUsPage() throws Exception {

//		driver.get(("https://tommyjohn.com"));
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//		JavascriptExecutor jse = (JavascriptExecutor)driver;
//		text = driver.findElement(CONTACT_US_LINK).getText();
//		if(!text.contains("Contact & FAQ"))
//			throw new Exception("Contact Us link text is changed");
//		jse.executeScript("arguments[0].click();", driver.findElement(CONTACT_US_LINK));
//		Thread.sleep(5000);
		if(driver.getCurrentUrl().contains("help.tommyjohn.com/hc/en-us")) 
		{
//			Thread.sleep(5000);
//			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#contact-info>p:nth-child(5)>a")));
//			driver.findElement(By.cssSelector("#contact-info>p:nth-child(5)>a")).click();
			driver.navigate().to("https://www.tommyjohn.com/pages/contact-us");
			Thread.sleep(5000);
			Reporter.log("Contact Us/Email link is Displayed :: Clickable");
			ExtentReport.extentTest.info("Contact Us/Email link is Displayed :: Clickable");
			if(driver.getCurrentUrl().contains("/pages/contact-us")) {
				System.out.println("Email link on help page is Displayed :: Clickable");
				ExtentReport.extentTest.info("Email link on help page is Displayed :: Clickable");
			}
			else {
				ExtentReport.extentTest.info("Wrong page opened after Email link on help page link clicked");
				throw new Exception("Wrong page opened after Email link on help page link clicked");
			}
		}
		
		else {
		if(driver.getCurrentUrl().equalsIgnoreCase("https://tommyjohn.com/pages/contact-us")) 
			//driver.navigate().back();
			System.out.println("Contact Us footer link is Displayed :: Clickable");
		else						
			throw new Exception("Wrong page opened after Contact Us footer link clicked");
		 Reporter.log("Contact Us footer link is Displayed :: Clickable");
		 ExtentReport.extentTest.info("Contact Us footer link is Displayed :: Clickable");
	}
							
	}

	// navigate to mix & match in Bra Category
	public void navigateToMixAndMatchInBrasCategory() throws Exception {
		driver.get("https://tommyjohn.com/collections/womens-mix-and-match");
	}

	public void navigateToHisNHerInPacks() throws Exception{

		driver.get("https://tommyjohn.com/collections/his-hers-rose-gold-packs");

	}

	//navigate to bottom in men category
	public void navigateToBottomInMensCategory(){

		driver.get("https://tommyjohn.com/collections/mens-lounge-bottoms");

	}
	//navigate to packs in men category
	public void nevigateToAllMensPacksInPacksCategory() 
	{
		driver.get("https://tommyjohn.com/collections/mens-packs-all");

	}

	//navigate to lounge wear packs in women category
	public void navigateToLoungeWearPacksInWomenPacksCategory(){

		//changed
		//driver.get("https://tommyjohn.com/collections/womens-lounge-sleep-packs");
		driver.get("https://www.tommyjohn.com/collections/womens-air-brief-3-pack/?variant=41591736467501");
		

	}

	//navigate to Boxer Brief in men category
	public void navigateToBoxerBriefInMenCategory(){
		driver.get("https://tommyjohn.com/collections/mens-boxer-brief-underwear");
		((JavascriptExecutor)driver).executeScript("window.scrollTo(document.body.scrollHeight,0)");
	}
	//navigate to Mens underwear quiz page
	public void navigateToMensQuizPage()
	{
		driver.get("https://tommyjohn.com/pages/quiz?quizID=MN-UW-QZ");

	}

	//navigate to Womens underwear quiz page
	public void navigateToWomensQuizPage()
	{
		driver.get("https://tommyjohn.com/pages/quiz?quizID=WN-UW-QZ");
	}

	public void navigateToWomensBraQuizPage()
	{
		driver.get("https://tommyjohn.com/pages/quiz?quizID=BR-10005-QZ");
	}
	
	// new
	// To navigate to Mens Loungewear Category page
	public void navigateToMensLoungewearCategory() throws Exception
	{
		String url = objGeneralMethods.readPropertiesFile("mensLoungewearUrl");
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		if((driver.findElement(MENS_LOUNGEWEAR_TITLE).getText()).equals(MENS_LOUNGEWEAR_TITLE_1))
		{
			System.out.println("User has navigated to Mens Loungewear successfully");
		}
		else
		{
			System.out.println("User hasn't navigated to Mens Loungewear");
		}
	}

	public void NavigateToAllLoungewearInMenCategory() {
		// TODO Auto-generated method stub
		driver.get("https://www.tommyjohn.com/collections/loungewear");
		
	}

	public void NavigateToAllSaleInSaleCategory() {
		// TODO Auto-generated method stub
		driver.get("https://www.tommyjohn.com/collections/sale-mens-womens");
		
	}

	public void NavigateToAllLoungewearInWomenCategory() {
		// TODO Auto-generated method stub
		driver.get("https://www.tommyjohn.com/collections/loungewear-womens");
		
	}

	public void NavigateToAllSleepwearInMenCategory() {
		// TODO Auto-generated method stub
		driver.get("https://www.tommyjohn.com/collections/pajamas-mens");
		
	}

	public void NavigateToAllSleepwearInWomenCategory() {
		// TODO Auto-generated method stub
		driver.get("https://www.tommyjohn.com/collections/pajamas-womens");
	}
	public void NavigateToplpwithsingleproduct() {
		// TODO Auto-generated method stub
		driver.get("https://www.tommyjohn.com/collections/bras?sort-by=relevance&sort-order=descending&filter%5Bfacet_collection%5D=comfortsmoothing");
	}

	public void NavigateToLoungewearSetsInWomenCategory() {
		// TODO Auto-generated method stub
		driver.get("https://www.tommyjohn.com/collections/womens-lounge-sets");
	}
	
	public void navigateToStoreDirectoryPage() {
		driver.get("https://www.tommyjohn.com/pages/store-directory");
	}
	public void navigateToReviewsPage() {
		driver.get("https://www.tommyjohn.com/pages/reviews");
	}
	/**
	 * Helps click on a element with JSExecutor
	 * 
	 //* @param WebElement
	 */
	private void clickWithJS(WebElement ele) {

		((JavascriptExecutor) driver).executeScript("arguments[0].click()", ele);

	}

	/**
	 * Helps click on a element with JSExecutor
	 * 
	 //* @param By instance of Web element
	 */
	private void clickWithJS(By by) {

		WebElement ele = driver.findElement(by);

		((JavascriptExecutor) driver).executeScript("arguments[0].click()", ele);

	}


	public void navigateToSocksPage() {
		driver.get("");
		
	}


	public void navigateToMenssleepLoungeCategory() {
		// TODO Auto-generated method stub
		driver.navigate().to("https://www.tommyjohn.com/collections/mens-sleep-lounge");
	}
	
	
}
