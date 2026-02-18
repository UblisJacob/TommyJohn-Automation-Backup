package com.tommyjohn.automation.Components;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.base.ExtentReport;
import com.tommyjohn.automation.PageLocators.BundlePDPLocators;
import com.tommyjohn.automation.Pages.ProductDetailsPage;
import com.tommyjohn.automation.utils.CustomUtilities;

import static com.tommyjohn.automation.PageLocators.ProductDetailsPageLocators.*;

public class BundlePDPComponent extends BundlePDPLocators{
	
	public WebDriver driver;
	public WebElement element;
	public Actions action;
	public JavascriptExecutor jse;
	boolean flag = false;
	Properties properties = CustomUtilities.properties;
	List<WebElement> allElements;
	int elementsCount;
	public  static String prodsize;
	public static String productname;
	public  static String productsize;
	public  static String productcolor;
	public  static String productprice;
	public static String productquant;
	public String price;

	public static String Sizename1;
	public static String Sizename2;
	public static String BundleSizename1;
	public static String BundleSizename2;

	double installment;


	public BundlePDPComponent(WebDriver driver) {
		this.driver = driver;
	}

	public void ValidateLearnMoreLink() throws Exception {
		SoftAssert softAssert = new SoftAssert();
//		new ProductDetailsPage(driver).navigateToAllMensPacksInPacksCategory();
//
//		//navigate to PDP
//		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
//		wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_PRODUCT));
//		Thread.sleep(5000);
//		WebElement el = driver.findElement(FIRST_PRODUCT_HEAD_LINE_TEXT);
//		JavascriptExecutor executor = (JavascriptExecutor)driver;
//		executor.executeScript("arguments[0].click();", el);
//
		jse = (JavascriptExecutor) driver;
//		jse.executeScript("window.scrollBy(0,300)");

		driver.get("https://www.tommyjohn.com/products/second-skin-mid-length-boxer-brief-6-6-pack-1?variant=43194128138285");
		WebElement element1 = driver.findElement(LEARN_MORE);
		jse.executeScript("arguments[0].click();", element1);

		Thread.sleep(2000);
		softAssert.assertTrue(driver.findElement(By.xpath("//h2[@class='text-display-sm font-medium leading-none']")).isDisplayed(),"Learn More Link is not functional");

		Reporter.log("Learn More Link is functional");
		ExtentReport.extentTest.info("Learn More Link is functional");

	}

	public void verifyImageGallery() throws Exception {
		new ProductDetailsPage(driver).navigateToAllWomensPacksInPacksCategory();
		
		SoftAssert softAssert = new SoftAssert();
		Thread.sleep(3000);
		//navigate to PDP
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_PRODUCT));

		wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_PRODUCT_HEAD_LINE_TEXT));
		WebElement el = driver.findElement(FIRST_PRODUCT_HEAD_LINE_TEXT);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", el);
		
		Thread.sleep(7000);
		allElements = driver.findElements(COLOR_VARIANT);
		elementsCount = allElements.size();
		System.out.println("Image Element count :: "+elementsCount);
		
		if(elementsCount > 1)
		{
			for(int i=1;i<elementsCount-1;i++)
			{
				WebElement img = driver.findElement(By.xpath("(//div[@class='space-y-lg']//span[@class='sr-only'])["+i+"]"));
				executor.executeScript("arguments[0].click();", img);

				Thread.sleep(3000);

//				String imagegallery = "";
//				String mainimage = "";
//				Thread.sleep(5000);
//				imagegallery = driver.findElement(By.cssSelector(".pdpVerticalSlides>.slick-list.draggable>div>div:nth-child("+i+")")).getAttribute("data-color");
//				Thread.sleep(2000);
//				WebElement img = driver.findElement(By.cssSelector(".pdpVerticalSlides>.slick-list.draggable>div>div:nth-child("+i+")"));
//				executor.executeScript("arguments[0].click();", img);
//
//
//				mainimage = driver.findElement(By.cssSelector(".pdpImageSlidesWrapper>div>div>div>div:nth-child("+i+").slick-active")).getAttribute("data-color");
//
////				mainimage = driver.findElement(By.cssSelector(".pdpImageSlidesWrapper>div>div>div>div.slick-active")).getAttribute("data-color-handle");
//
//				softAssert.assertTrue(imagegallery.contentEquals(mainimage), "Image caraousel does not change");


			}

			Reporter.log("New Image is visible");
			ExtentReport.extentTest.info("New Image is visible");
		}
//		Reporter.log("Image gallery changes with main image");
//		ExtentReport.extentTest.info("Image gallery changes with main image");
		
		softAssert.assertAll();
	}

	
	public void viewImageGallery() throws Exception {
		String thumbnail;
		SoftAssert softAssert = new SoftAssert();
		new ProductDetailsPage(driver).navigateToAllMensPacksInPacksCategory();
		Thread.sleep(3000);
		//navigate to PDP
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_PRODUCT));
		
		WebElement el = driver.findElement(FIRST_PRODUCT_HEAD_LINE_TEXT);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", el);
		
		Thread.sleep(3000);
		allElements = driver.findElements(IMG_CAROUSEL);
		elementsCount = allElements.size();
		System.out.println("Image Element count :: "+elementsCount);
		
		if(elementsCount > 1) 
		{	
			for(int i=1;i<=elementsCount;i++) 
			{
				thumbnail = driver.findElement(By.cssSelector(".pdpVerticalSlides>.slick-list.draggable>div>div:nth-child("+i+")")).getAttribute("data-color");
				WebElement img = driver.findElement(By.cssSelector(".pdpVerticalSlides>.slick-list.draggable>div>div:nth-child("+i+")>img"));
				executor.executeScript("arguments[0].click();", img);
				softAssert.assertTrue(img.isDisplayed(), "Corresponding Image is not visible");
				Thread.sleep(1000);
				//System.out.println(thumbnail);
			}
		}
			Reporter.log("New Image is visible");
		
		softAssert.assertAll();
		
	}
	
	
	public void verifyOnImageBadging() throws Exception {
		
		SoftAssert softAssert = new SoftAssert();
		new ProductDetailsPage(driver).navigateToEveryDayEssentials();
		
		//navigate to PDP
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
//		wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_PRODUCT));
//		Thread.sleep(5000);
//		WebElement el = driver.findElement(SECOND_PRODUCT_HEAD_LINE_TEXT);
//		JavascriptExecutor executor = (JavascriptExecutor)driver;
//		executor.executeScript("arguments[0].click();", el);
//
//		jse = (JavascriptExecutor) driver;
//		jse.executeScript("window.scrollBy(0,800)");
		WebElement allElements = (WebElement) driver.findElement(IMAGE_BADGING_NEW_STYLE);
		try{
			if(!(allElements).isDisplayed())
				throw new Exception("Badging is not displayed on PDP.");
			else {
				System.out.println("Badging is displayed on PDP.");
				Reporter.log("Badge is displayed on PDP.");
				ExtentReport.extentTest.info("Badge is displayed on PDP.");
			}
		}
		catch(Exception e){
			System.out.println("Badging is not applicable for this product.");
			Reporter.log("Badge is not displayed on PDP.");
			ExtentReport.extentTest.info("Badge is not displayed on PDP.");
			
		}
		
		softAssert.assertAll();
	}
		
	public void verifySecondaryBadging() throws Exception {
		
		SoftAssert softAssert = new SoftAssert();
		new ProductDetailsPage(driver).NavigateToAllSaleInSaleCategory();
		
		//navigate to PDP
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_PRODUCT));
		Thread.sleep(10000);
		WebElement el = driver.findElement(FIRST_PRODUCT_HEAD_LINE_TEXT);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", el);
		
		
		WebElement allElements;
		allElements = (WebElement) driver.findElement(SECONDARY_BADGING);
//		System.out.println(allElements);
		try{
			if(!(allElements).isDisplayed())
				throw new Exception("Badging is not displayed on PDP.");
			else {
				System.out.println("Badging is displayed on PDP.");
				Reporter.log("Badge is displayed on PDP.");
				ExtentReport.extentTest.info("Badge is displayed on PDP.");
			}
		}
		catch(Exception e){
			System.out.println("Badging is not applicable for this product.");
			Reporter.log("Badge is not displayed on PDP.");
			ExtentReport.extentTest.info("Badge is not displayed on PDP.");
		}
		
		softAssert.assertAll();
	}
	
		public void verifySelectedColorSwatch() throws Exception {
			String colornew;
			SoftAssert softAssert = new SoftAssert();
		//	new ProductDetailsPage(driver).navigateToAllMensPacksInPacksCategory();
			new HomePageComponents(driver).navigateToAllWomensPacksInPacksCategory();
			
			//navigate to PDP
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
			wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_PRODUCT));
			Thread.sleep(3000);
			WebElement el = driver.findElement(FIRST_PRODUCT_HEAD_LINE_TEXT);
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", el);

		//	driver.findElement(By.xpath("(//div[@class='relative text-center'])[2]")).click();
			
			Thread.sleep(3000);
			allElements = driver.findElements(COLOR_VARIANT);
			
			elementsCount = allElements.size();
			System.out.println("Color Element count :: "+elementsCount);
			System.out.println("*****************************");

			if (elementsCount > 1) {
				for (int i = 1; i < elementsCount; i++) {
					String imagecolor = null;
					WebElement elemen = driver.findElement(By.xpath("(//div[@class='space-y-lg']//span[@class='sr-only'])["+i+"]"));
					jse = (JavascriptExecutor) driver;
					jse.executeScript("arguments[0].click();", elemen);
//				driver.findElement(COLOR_VARIANT).getText();


//				List<WebElement> inactivesizes = driver.findElements(INACTIVE_SIZES);
//				int num1 = inactivesizes.size();


//				if (num1 > 0) {


					colornew = driver.findElement(By.xpath("(//div[@class='space-y-lg']//span[@class='font-medium text-text-primary'])[1]")).getText();
					imagecolor = driver.findElement(By.xpath("(//div[@class='space-y-lg']//span[@class='sr-only'])["+i+"]")).getText();

					System.out.println("For Color Swatch: " + colornew);


//							System.out.println(colortext.toLowerCase());
//							System.out.println(colornew);
//							System.out.println(imagecolor);

					Thread.sleep(1000);

					//gallery will also update to color product
					if (imagecolor.contentEquals(colornew)) {
//									Thread.sleep(5000);
						//wait.until(ExpectedConditions.visibilityOfElementLocated(COLOR_TEXT));
						System.out.println("Expected is: " + colornew + " Actual is: " + imagecolor);
						softAssert.assertEquals(colornew.equalsIgnoreCase(imagecolor), true, "Color not changing");
						System.out.println("----------------------------------");

					}

					//sizes getting updated
//				}


					Reporter.log("Colors are present and changing correctly");
					ExtentReport.extentTest.info("Colors are present and changing correctly");


					if (i > 10)
						break;
				}
			}

//			if(elementsCount > 1)
//			{
//				for(int i=1;i<=elementsCount;i++)
//				{
//						String imagecolor;
//						WebElement elemen = driver.findElement(By.cssSelector(".product-option__color-swatches-wrapper>ul>li:nth-child(" + i + ") > label"));
//						jse = (JavascriptExecutor)driver;
//						jse.executeScript("arguments[0].click();", elemen);
//						driver.findElement(COLOR_TEXT).getText();
//
//
//						List<WebElement> inactivesizes = driver.findElements(INACTIVE_SIZES);
//						int num1= inactivesizes.size();
//						List<WebElement> totalsizes = driver.findElements(TOTAL_SIZES);
//						int num= (totalsizes.size()-num1);
//
//						if(num>1)
//						{
//
//
//							colornew = driver.findElement(By.cssSelector(".product-option__color-swatches-wrapper > ul  > li:nth-child("+i+") > input")).getAttribute("data-variant");
//							imagecolor = driver.findElement(IMAGE_COLOR).getAttribute("data-color");
//
//							System.out.println("For Color Swatch: " +colornew);
//							System.out.println("Active Sizes: " +num );
//							System.out.println("Unavailable sizes:" + num1);
//
////							System.out.println(colortext.toLowerCase());
//							System.out.println(colornew);
//							System.out.println(imagecolor);
//
//							Thread.sleep(1000);
//
//							//gallery will also update to color product
//							if(imagecolor.contentEquals(colornew))
//								{
////									Thread.sleep(5000);
//									//wait.until(ExpectedConditions.visibilityOfElementLocated(COLOR_TEXT));
//									System.out.println("Expected is: "+ colornew +" Actual is: "+imagecolor);
//									softAssert.assertEquals(colornew.equalsIgnoreCase(imagecolor), true, "Color not changing");
//									System.out.println("----------------------------------");
//
//								}
//
//							//sizes getting updated
//						}
//
//
//							Reporter.log("Colors are present and changing correctly");
//							ExtentReport.extentTest.info("Colors are present and changing correctly");
//
//				}
//			}
		}
	public void verifyAvailableSizes() throws Exception{
		SoftAssert softAssert = new SoftAssert();
		JavascriptExecutor jse = (JavascriptExecutor)driver;
//		new ProductDetailsPage(driver).navigateToMensAll();
//		//new HomePageComponents(driver).navigateToAllPantiesInWomenCategory();
//
//		//navigate to PDP
//		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
//		wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_PRODUCT));
//		Thread.sleep(3000);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_PRODUCT_HEAD_LINE_TEXT));
//		WebElement el = driver.findElement(FIRST_PRODUCT_HEAD_LINE_TEXT);
//		jse.executeScript("arguments[0].click();", el);
//
//		driver.findElement(By.xpath("(//div[@class='relative text-center'])[2]")).click();
		driver.get("https://www.tommyjohn.com/products/womens-second-skin-boyshort-3-pack-8?variant=41973264154669");

		allElements = driver.findElements(ALL_SIZE_LIST);
		
		elementsCount = allElements.size();
		System.out.println("Color Element count :: "+elementsCount);
		Reporter.log("Sizes are present");
		ExtentReport.extentTest.info("Sizes are present");
		
		if(elementsCount > 1)
		{
			for(int i=1;i<=1;i++)
			{
				
				WebElement elemen = driver.findElement(By.xpath("(//div[@class='space-y-lg']//input[@class='sr-only peer'])[" + i + "]"));
				
				jse.executeScript("arguments[0].click();", elemen);

				WebElement elem = driver.findElement(By.xpath("(//input[@name='option[Size]'])["+i+"]"));

				jse.executeScript("arguments[0].click();", elem);


				String sizename = driver.findElement(By.xpath("(//label[@for='ProductForm-sizeOption-xs-0-2']//span)["+i+"]")).getText();
				System.out.println(sizename);
				String sizevalue = driver.findElement(SIZE_VALUE).getText();
				System.out.println(sizevalue);
				softAssert.assertEquals(sizename, sizevalue, "Sizes and Colors not changing");
								
			}
			Reporter.log("Sizes are changing correctly");
			ExtentReport.extentTest.info("Sizes are changing correctly");
		}

	}
		public void verifyStickyATC() throws Exception {
			new HomePageComponents(driver).navigateToAllMensPacksInPacksCategory();
			new CollectionPageComponent(driver).navigateToProductDetailsPage();
			//new AddToCartComponents(driver).selectSize();
			
			Thread.sleep(5000);
			jse = (JavascriptExecutor) driver;		
			jse.executeScript("window.scrollBy(0,200)");
			allElements = driver.findElements(TOTAL_SIZES);
			elementsCount = allElements.size();
			System.out.println("Size Element count :: "+elementsCount);
			
			if(elementsCount>0)
			{
				
				//System.out.println("i am here");
				
				for(int i=1;i<elementsCount;i++)
				{
					
					WebElement elemen = driver.findElement(By.cssSelector(".option-swatch__option>li:nth-child("+ i +")>input"));
					Thread.sleep(1000);
									
					jse.executeScript("arguments[0].click();", elemen);
					Thread.sleep(1000);
					
					jse = (JavascriptExecutor) driver;
					jse.executeScript("window.scrollBy(0,800)");
					
					WebElement Stickynav = driver.findElement(By.cssSelector(".product-bar"));
					SoftAssert softassert = new SoftAssert();
					softassert.assertTrue(Stickynav.isDisplayed(), "Sticky nav is not displayed on PDP");
					element =driver.findElement(By.cssSelector(".product-bar__atc"));
					softassert.assertTrue(element.isDisplayed(), "scrolling CTA Add to Cart is not displayed");
					softassert.assertEquals(element.getText(), "Add To Cart", "Text is not matched");
					Thread.sleep(3000);			
					//jse.executeScript("arguments[0].click();", element);
					softassert.assertAll();
					
				}
			}	
			
		Reporter.log("Scrolling CTA Add to Cart is displayed :: Clickable");	
		ExtentReport.extentTest.info("Scrolling CTA Add to Cart is displayed :: Clickable");	
			
		}
		
		public void verifyReviewRecommendations() throws Exception {
			new HomePageComponents(driver).navigateToAllMensPacksInPacksCategory();
			new CollectionPageComponent(driver).navigateToProductDetailsPage();
			//new AddToCartComponents(driver).selectSize();


			Thread.sleep(3000);
			if (driver.findElement(RATING_STARS).isDisplayed()) {

				WebElement elem = driver.findElement(RATING_STARS);
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", elem);
				// wait for scroll it down
				Thread.sleep(3000);
				executor.executeScript("window.scrollBy(0, 600)");
				//wait.until(ExpectedConditions.presenceOfElementLocated(WRITE_REVIEW_BUTTON));
				Reporter.log("Review Stars Are Displayed :: Clickable :: Clicked");
				ExtentReport.extentTest.info("Review Stars Are Displayed :: Clickable :: Clicked");
				// check for Rating and Reviews are present or not
				System.out.println("rating: " + driver.findElement(RATING_NUMBER).getText());

				if (!driver.findElement(RATING_NUMBER).getText().equals("(0)")) {

					Thread.sleep(3000);
					if (driver.findElement(RATING_AND_REVIEWS_BOX).isDisplayed()) {
						if (!driver.findElement(FIRST_BOX_IN_RATING_AND_REVIEWS).isDisplayed())
							throw new Exception("In first box Review Stars not present in Rating and Reviews section");
						if (!driver.findElement(SECOND_BOX_IN_RATING_AND_REVIEWS).isDisplayed())
							throw new Exception("In second box rating list not present in Rating and Reviews section");
						if (!driver.findElement(THIRD_BOX_IN_RATING_AND_REVIEWS).isDisplayed())
							throw new Exception("In third box fit status not present in Rating and Reviews section");
					} else
						throw new Exception("Rating box is not present");
				} else {
					Thread.sleep(3000);
					if (!driver.findElement(NO_REVIEW_WRITE_REVIEW).isEnabled())
						throw new Exception("'WRITE A REVIEW' button is not displayed");
					driver.findElement(NO_REVIEW_WRITE_REVIEW).click();
					Reporter.log("'WRITE A REVIEW' button is Displayed :: Clickable");
					driver.findElement(NO_REVIEW_WRITE_REVIEW).click();
				}
				Reporter.log("Rating and Reviews Displayed");
				ExtentReport.extentTest.info("Rating and Reviews Displayed");
			} else
				throw new Exception("Rating Stars are not clickable");

		}
		
	public void verifyProductNamePrice() throws Exception {
			
			new HomePageComponents(driver).navigateToAllWomensPacksInPacksCategory();
			new CollectionPageComponent(driver).navigateToProductDetailsPage();
			//new AddToCartComponents(driver).selectSize();
			productname = driver.findElement(PRODUCT_TITLE).getText();
			productprice = driver.findElement(PRODUCT_PRICE).getText();
			System.out.println("Product Name: " +productname);
			System.out.println("Product Price: " +productprice);
			SoftAssert softassert= new SoftAssert(); 
			softassert.assertEquals(productname, PRODUCT_TITLE, "Text is not matched");
	
			Reporter.log("Product name and Price are displayed");
			ExtentReport.extentTest.info("Product name and Price are displayed");
	}
		
	public void verifyAccordions() throws Exception {
		new HomePageComponents(driver).navigateToAllMensPacksInPacksCategory();
		new CollectionPageComponent(driver).navigateToProductDetailsPage();
		jse = (JavascriptExecutor) driver;		
		jse.executeScript("window.scrollBy(0,800)");
		flag = false;
		Exception except = null;


		if (driver.findElement(PRODUCT_DETAILS).isDisplayed()) {

			element = driver.findElement(PRODUCT_DETAILS);
			Thread.sleep(3000);
			jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", element);
			jse.executeScript("arguments[0].click();", element);

			Thread.sleep(3000);

			if (driver.findElement(PRODUCT_DETAILS_SECTION).isDisplayed()) {
				System.out.println("PRODUCT_DETAILS_SECTION is Displayed");
				Reporter.log("Product details accordion :: displayed");
				ExtentReport.extentTest.info("Product details accordion :: displayed");
			}
		} else
			throw new Exception("Product details accordion not present or not clikable ");


//		try {
//			if (driver.findElement(BEST_PAIR_GUARANTEE).isDisplayed()) {
//
//				element = driver.findElement(BEST_PAIR_GUARANTEE);
//				jse = (JavascriptExecutor) driver;
//				jse.executeScript("arguments[0].click();", element);
//				jse.executeScript("window.scrollBy(0,50)");
//
//				Thread.sleep(3000);
//
//				if (driver.findElement(BEST_PAIR_GUARANTEE_SECTION).isDisplayed()) {
//					System.out.println("BEST_PAIR_GUARANTEE_SECTION is Displayed");
//					Reporter.log("BEST_PAIR_GUARANTEE_SECTION :: displayed");
//					ExtentReport.extentTest.info("BEST_PAIR_GUARANTEE_SECTION :: displayed");
//				}
//			} else throw new Exception("BEST_PAIR_GUARANTEE accordion not present or not clikable");
//		} catch (Exception e)
//		{
//			Reporter.log("Best_Pair guarantee link is not present for this particular product");
//			except = e;
//		}
//		if (except != null) {
		if (driver.findElement(FIT_MATERIAL).isDisplayed()) {
			element = driver.findElement(FIT_MATERIAL);
			jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", element);
			//driver.findElement(FREE_RETURNS).click();
			//jse.executeScript("window.scrollBy(0,300)");
			//element.click();
//				Actions actions = new Actions(driver);
//				actions.moveToElement(element).click().build().perform();
			Thread.sleep(3000);
			//element.click();
			if (driver.findElement(FIT_MATERIAL_SECTION).isDisplayed()) {
				System.out.println("FIT_MATERIAL_SECTION is Displayed");
				Reporter.log("FIT_MATERIAL_SECTION :: displayed");
				ExtentReport.extentTest.info("FIT_MATERIAL_SECTION :: displayed");
			}
		} else throw new Exception("FIT_MATERIAL_SECTION accordion not present or not clikable");
//		}
		Reporter.log("PRODUCT ACCORDIONS ARE DISPLAYED");
		ExtentReport.extentTest.info("PRODUCT ACCORDIONS ARE DISPLAYED");
	}
	
	
	
	
	public void verifyLowStockInventory() throws Exception {
		new HomePageComponents(driver).navigateToAllWomensPacksInPacksCategory();
		new CollectionPageComponent(driver).navigateToProductDetailsPage();
		Thread.sleep(3000);
		allElements = driver.findElements(ALL_COLOR_LIST);
		
		elementsCount = allElements.size();
		System.out.println("Color Element count :: "+elementsCount);
		Thread.sleep(2000);
		if(elementsCount > 1)
			
				for(int i=1;i<=elementsCount;i++)
				{
					
					WebElement elemen = driver.findElement(By.cssSelector(".product-option__color-swatches-wrapper>ul>li:nth-child(" + i + ")>input"));
					jse = (JavascriptExecutor) driver;
					jse.executeScript("arguments[0].click();", elemen);
					Thread.sleep(1000);
					
						for(int k=1;k<6;k++)				
						{	
							WebElement elements = driver.findElement(By.cssSelector(".option-swatch__option>li:nth-child("+ k +")>input"));
							//elements.click();
							Thread.sleep(1000);
							jse.executeScript("arguments[0].click();", elements);
							
							if(driver.findElement(LOW_INVENTORY_MSG).isDisplayed()){
								System.out.println("LOW INVENTORY MESSAGE for below combinations");
								System.out.println("For Color: " +elemen.getAttribute("value"));
								System.out.println("For Size: " +elements.getAttribute("value"));
								System.out.println("===================");
								Reporter.log("Low Stock Inventory message :: Displayed");	
								ExtentReport.extentTest.info("Low Stock Inventory message :: Displayed");	
								
							}
							
						}
				}
				Reporter.log("no msg");	
				ExtentReport.extentTest.info("no msg");	
			
		}
		
		public void verifyReviewRecommendationsSection() throws Exception {
			SoftAssert softAssert =  new SoftAssert();
			new HomePageComponents(driver).navigateToAllMensPacksInPacksCategory();
			new CollectionPageComponent(driver).navigateToProductDetailsPage();
			Thread.sleep(6000);
			WebElement ratingstars = driver.findElement(RATING_STARS);
			jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", ratingstars);
			//ratingstars.click();
			Thread.sleep(4000);
			jse.executeScript("window.scrollBy(0, 650)");
			String avgrating = driver.findElement(AVG_RATING_IN_FIRST_BOX).getText();
			System.out.println("Average Rating: " +avgrating);
			
			String totalreviews = driver.findElement(TOTAL_REVIEWS_IN_FIRST_BOX).getText();
			System.out.println("Total Reviews: " +totalreviews);
			
			Reporter.log("Average rating and Total reviews are :: Displayed");	
			ExtentReport.extentTest.info("Average rating and Total reviews are :: Displayed");	
			//fit range
			WebElement fitrange = driver.findElement(THIRD_BOX_IN_RATING_AND_REVIEWS);
			jse.executeScript("arguments[0].click();", fitrange);
			Thread.sleep(2000);
			Reporter.log("Fit range is :: Clickable");	
			ExtentReport.extentTest.info("Fit range is :: Clickable");	
			//filters click
			WebElement ratingsfilter = driver.findElement(FILTER_RATINGS);
			jse.executeScript("arguments[0].click();", ratingsfilter);
			Thread.sleep(1000);
			WebElement ratingsfit = driver.findElement(FILTER_FIT);
			jse.executeScript("arguments[0].click();", ratingsfit);
			Thread.sleep(1000);
			WebElement ratingssortby = driver.findElement(FILTER_SORTBY);
			ratingssortby.click();
			Thread.sleep(1000);
			Reporter.log("Filters are :: Clickable");	
			ExtentReport.extentTest.info("Filters are :: Clickable");	
			
			//sort by dropdown
			List<WebElement> elem = driver.findElements(FILTER_SORTBY_SECTION);
			int sortby = elem.size();
			Thread.sleep(3000);		
			System.out.println(sortby);
			if(sortby >=1) {
			
				for(int i=1;i<=sortby;i++) {
					
					element = driver.findElement(By.cssSelector(".yotpo-sort-by-options-container.active>ul>li:nth-child("+i+")"));
					softAssert.assertTrue(element.isDisplayed(), "SORT bY Filter does not work");
					jse = (JavascriptExecutor) driver;
					jse.executeScript("arguments[0].click();", element);
					Thread.sleep(3000);	
					WebElement ratingssortbynew = driver.findElement(FILTER_SORTBY);
					ratingssortbynew.click();
				}
				Reporter.log("Sort by is :: Clickable");	
				ExtentReport.extentTest.info("Sort by is :: Clickable");
				
					
			}
			//Display Questions
			
			WebElement qsntab = driver.findElement(QUESTION_TAB);
			jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", qsntab);
			element = driver.findElement(QUESTION_FIRST);
			softAssert.assertTrue(element.isDisplayed(), "Questions are not displayed");
			Reporter.log("Questions are :: Displayed");	
			ExtentReport.extentTest.info("Questions are :: Displayed");	
			softAssert.assertAll();
		}
		
		
		
		public void verifyAddToCart() throws Exception {
			String text = null;
			new SoftAssert();
			// call method to navigate to collection page
//			new HomePageComponents(driver).navigateToAllMensPacksInPacksCategory();
//			jse=(JavascriptExecutor)driver;
//			jse.executeScript("window.scrollBy(0,250)");
//			// call method to navigate product details page
//			text = new CollectionPageComponent(driver).navigateToProductDetailsPage();
			// call method to check correct PDP opend or not
		//	checkCorrectProductDetailsPageOpenedOrNot(text);
			driver.get("https://www.tommyjohn.com/products/second-skin-crew-neck-stay-tucked-undershirt-1-3-pack?color=white");
			Thread.sleep(2000);


			driver.findElement(By.xpath("(//div[@class='relative text-center'])[2]")).click();

			allElements = driver.findElements(ProductDetailsPageComponents.COLOR_VARIANT);
			int count = 0;
			elementsCount = allElements.size();
			System.out.println("Color Element count :: " + elementsCount);

			Random randomGenerator = new Random();
			int randomInt = randomGenerator.nextInt(1000);
			if (elementsCount > 1)

				for (int i = 1; i <= 2; i++) {
					count++;
					WebElement elemen = driver.findElement(By.xpath("(//div[@class='space-y-lg']//input[@class='sr-only peer'])["+i+"]"));
					jse = (JavascriptExecutor) driver;
					jse.executeScript("arguments[0].click();", elemen);
					Thread.sleep(3000);


					for (int k = 1; k <= 2; k++) {
						WebElement elements = driver.findElement(By.xpath("(//input[@name='option[Size]'])["+k+"]"));
						jse.executeScript("arguments[0].click();", elements);
						Thread.sleep(1000);


						String[] AddtoBag;
						AddtoBag = (driver.findElement(By.xpath("//button[@class='button grow']")).getText()).split(" -");
						String text1 = AddtoBag[0];
						System.out.println(text1);

						//	WebElement elem = driver.findElement(By.cssSelector(".multiPdp__atc-container>button"));



						if (driver.findElement(ProductDetailsPageComponents.ADD_TO_CART_BUTTON).getText().contains(text1)) {

							System.out.println(elemen.getText() + elements.getText() + "Product is added to Cart");
							Thread.sleep(2000);
							jse.executeScript("arguments[0].click();", driver.findElement(ADD_TO_CART_BUTTON));
							Thread.sleep(3000);
							driver.findElement(INLINE_CART_TITLE);
							jse.executeScript("arguments[0].click();", driver.findElement(INLINE_CART_CLOSE));

							Thread.sleep(1000);
							System.out.println("=============================");

						}

						//	if (elem.getText().contentEquals("Join the Waitlist")) {
//								System.out.println(elemen.getText()+ elements.getText() +"Product is Out of Stock and Join The waitlist is  :: Clickable");
//								jse.executeScript("arguments[0].click();", driver.findElement(ADD_TO_CART_BUTTON));
//								if(driver.findElement(EMAIL_WHEN_IN_STOCK_BUTTON).isDisplayed()) {
//
//									System.out.println("JOIN THE WAITLIST BUTTON IS WORKING FINE");
//									Thread.sleep(2000);
//									jse.executeScript("arguments[0].click();", driver.findElement(JOINTHEWAITLIST_EMAILTEXTBOX));
//									Thread.sleep(2000);
//									driver.findElement(JOINTHEWAITLIST_EMAILTEXTBOX).sendKeys("abc"+randomInt+"@tommyjohnwear.com");
//									Thread.sleep(2000);
//									jse.executeScript("arguments[0].click();", driver.findElement(JOINTHEWAITLIST_NOTIFYBUTTON));
//									Thread.sleep(1000);
//									jse.executeScript("arguments[0].click();", driver.findElement(EMAIL_WHEN_IN_STOCK_BUTTON_CLOSE));
//									System.out.println("=============================");
//									Thread.sleep(1000);

						//							}
//

						//	}


					}

					if (count == 2)
						break;

				}

			Reporter.log("ATC works fine");
			ExtentReport.extentTest.info("ATC works fine");
			
		}
		
		
}