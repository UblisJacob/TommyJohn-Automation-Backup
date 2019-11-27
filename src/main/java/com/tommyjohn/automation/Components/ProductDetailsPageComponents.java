package com.tommyjohn.automation.Components;

import static org.junit.Assume.assumeNoException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.tommyjohn.automation.PageLocators.FlyCartPageLocator;
import com.tommyjohn.automation.PageLocators.ProductDetailsPageLocators;
import com.tommyjohn.automation.utils.CustomUtilities;

public class ProductDetailsPageComponents extends ProductDetailsPageLocators {
	public WebDriver driver;
	public WebElement element;
	public Actions action;
	public JavascriptExecutor jse;
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
	double installment;

	public ProductDetailsPageComponents(WebDriver driver) {
		this.driver = driver;
	}

	public void validateProductDetailsPage() throws Exception {
		String text = null;

		// call method to navigate to collection page
		new HomePageComponents(driver).navigateToAllClothingInWomensCategory();

		// call method to nevigate product details page
		text = new CollectionPageComponent(driver).navigateToProductDetailsPage();

		// call method to check correct PDP opend or not
		checkCorrectProductDetailsPageOpenedOrNot(text);

		// call method for PDP validation
		validatePage();

	}

	public void checkCorrectProductDetailsPageOpenedOrNot(String text1) throws Exception {
		String text2 = null;
		Thread.sleep(5000);
		if(!driver.findElement(PRODUCT_TITLE).isDisplayed())
			throw new Exception("Product title is not displayed on product details page");
		text2 = driver.findElement(PRODUCT_TITLE).getText();

		// check correct PDP opened
		if(!text1.equals(text2))
			throw new Exception("Wrog product details page opened");
		Reporter.log("Product details page is Correct :: Product title is Displayed");

	}


	public  ProductDetailsPageComponents getproductdetails() throws Exception
	{
		ProductDetailsPageComponents pdp = new ProductDetailsPageComponents(driver);
		new HomePageComponents(driver).navigateToAllPantiesInWomenCategory();
		new CollectionPageComponent(driver).navigateToProductDetailsPage();
		new AddToCartComponents(driver).selectSize();
		pdp.productname = driver.findElement(PRODUCT_TITLE).getText();
		pdp.productprice = driver.findElement(PRODUCT_PRICE).getText();
		List<WebElement> productcolors = driver.findElements(COLOR_TEXT);
		for( WebElement color : productcolors )
		{
			if(!color.getText().isEmpty()) {
				pdp.productcolor = color.getText();
				break;
			}
		}

		pdp.productsize = driver.findElement(SIZE_TEXT).getText();
		pdp.productquant =  driver.findElement(QUANTITY).getAttribute("data-add-qty");
		pdp.prodsize = driver.findElement(SIZE_TEXT).getAttribute("textContent");
		String[] arrOfStr = pdp.prodsize.split(","); 
		pdp.prodsize = arrOfStr[0];

		new AddToCartComponents(driver).selectSize();
		return pdp;

	}


	public void validatePage() throws Exception {
		String text1 = null;
		String allClasses = null;
		boolean flag = false;
		String colornew = null;
		String colortext = null;
		String quantity;

		SoftAssert softAssert =  new SoftAssert();
		System.out.println(driver.getCurrentUrl());
		driver.navigate().refresh();
		// check product price is displayed or not
		if(!driver.findElement(PRODUCT_PRICE).isEnabled())
			throw new Exception("Product price is not displayed");

		// check for whats my size
		if(!driver.findElement(SIZEGUIDE).isEnabled())
			throw new Exception("Size Guide button is not present");
		WebElement ele = driver.findElement(SIZEGUIDE);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", ele);
		//	driver.findElement(SIZEGUIDE).click();
		Thread.sleep(3000);

		if (!driver.findElement(By.cssSelector(".size-chart-content")).isDisplayed())
			//		allClasses = driver.findElement(SIZE_GUIDE_BUTTON).getAttribute("class");
			//		for (String c : allClasses.split(" ")) {
			//			if (c.equals("active")) {
			//				flag = true;
			//			}
			//		}
			//		if(!flag)
			throw new Exception("Sizes chart overlay is not displayed after Size guide button was clicked");
		Reporter.log("Size guide is Displayed :: Clickable");

		driver.findElement(By.cssSelector(".close_chart_modal")).click();

		// scroll it top again

		Thread.sleep(3000);

		// Check review stars are clickable and visible or not

		//	throw new Exception("Star rating not present");

		//		element = driver.findElement(RATING_STARS);
		//		// get all classes from specified path
		//		allClasses = element.getAttribute("class");
		//		// check stars are clickable or not by using star-clickable class name
		//		for (String c : allClasses.split(" ")) {
		//			if (c.equals("star-clickable")) {

		//			}
		//		}
		if(driver.findElement(RATING_STARS).isDisplayed()) {

			//			Thread.sleep(3000);
			//			jse = (JavascriptExecutor)driver;
			//			jse.executeScript("window.scrollBy(0,-250)", "");
			//			Thread.sleep(4000);
			//			//	driver.findElement(By.cssSelector(".yotpo-stars")).click();
			//			//driver.findElement(RATING_STARS).click();
			driver.navigate().refresh();
			WebElement elem = driver.findElement(RATING_STARS);
			JavascriptExecutor executore = (JavascriptExecutor)driver;
			executore.executeScript("arguments[0].click();", elem);
			// wait for scroll it down
			Thread.sleep(6000);
			Reporter.log("Review Stars Are Displayed :: Clickable :: Clicked");
			// check for Rating and Reviews are present or not
			System.out.println("rating: " + driver.findElement(RATING_NUMBER).getText());
			if(!driver.findElement(RATING_NUMBER).getText().equals("(0)"))
			{

				if(driver.findElement(RATING_AND_REVIEWS_BOX).isDisplayed()) {
					if(!driver.findElement(FIRST_BOX_IN_RATING_AND_REVIEWS).isDisplayed())
						throw new Exception("In first box Review Stars not present in Rating and Reviews section");
					if(!driver.findElement(SECOND_BOX_IN_RATING_AND_REVIEWS).isDisplayed())
						throw new Exception("In second box rating list not present in Rating and Reviews section");
					if(!driver.findElement(THIRD_BOX_IN_RATING_AND_REVIEWS).isDisplayed())
						throw new Exception("In third box fit status not present in Rating and Reviews section");
				}

				else 

					throw new Exception("Rating box is not present");

			}

			else 
			{
				if(!driver.findElement(NO_REVIEW_WRITE_REVIEW).isEnabled())

					throw new Exception("'WRITE A REVIEW' button is not displayed");
				driver.findElement(NO_REVIEW_WRITE_REVIEW).click();
				Reporter.log("'WRITE A REVIEW' button is Displayed :: Clickable");

			}
			Reporter.log("Rating and Reviews Displayed");
		}
		else
			throw new Exception("Rating Stars are not clickable");


		// check for write a review button
		//		if(!driver.findElement(WRITE_REVIEW_BUTTON).isEnabled())
		//			throw new Exception("Write Review Button is not displayed");
		//		// check Write Review portion should not be visible before click button to Write Review
		//		element = driver.findElement(WRITE_REVIEW_HEADING);
		//		allClasses = element.getAttribute("class");
		//		for (String c : allClasses.split(" ")) {
		//			if (c.equals("visible")) {
		//				throw new Exception("Write Review portion should not be visible before Write Review button is clicked");
		//			}
		//		}
		//		WebDriverWait wait = new WebDriverWait(driver, 30);
		//		wait.until(ExpectedConditions.visibilityOfElementLocated(WRITE_REVIEW_BUTTON));	
		//		//driver.findElement(WRITE_REVIEW_BUTTON).click();
		//		WebElement elem = driver.findElement(WRITE_REVIEW_BUTTON);
		//		JavascriptExecutor executore = (JavascriptExecutor)driver;
		//		executore.executeScript("arguments[0].click();", elem);
		//		// check Write Review portion should be visible by clicking Write A Review button
		//		element = driver.findElement(WRITE_REVIEW_HEADING);
		//		allClasses = element.getAttribute("class");
		//		for (String c : allClasses.split(" ")) {
		//			if (c.equals("visible")) {
		//				flag = true;
		//			}
		//		}
		//		if(!flag)
		//			throw new Exception("Write Review portion is not visible after click Write Review button");
		//		Reporter.log("Write A Review button Displayed :: Clickable :: Write Review portion is Displayed after click button :: Text Correct");

		// scroll it top again
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -document.body.scrollHeight)");
		Thread.sleep(3000);

		// Check we can select color or not
		JavascriptExecutor executore = (JavascriptExecutor)driver;
		allElements = driver.findElements(ALL_COLOR_LIST);
		elementsCount = allElements.size();
		System.out.println("Color Element count :: "+elementsCount);


		if(elementsCount > 1)
		{


			for(int i=1;i<elementsCount;i++)
			{
				if(i<4)
				{
					colortext = driver.findElement(COLOR_TEXT).getText();
					if(driver.getCurrentUrl().contains("product"))
						colornew = driver.findElement(By.cssSelector(".product-option__color-swatches-wrapper > ul  > li:nth-child("+ i +") > input")).getAttribute("data-color-variant");
					else
						colornew = driver.findElement(By.cssSelector(".product-option__color-swatches-wrapper > ul > li:nth-child("+ i +") > input")).getAttribute("data-option-name");
					System.out.println(colornew);
					if(!colornew.contentEquals(colortext))	
					{
						WebElement elemen = driver.findElement(By.cssSelector(".product-option__color-swatches-wrapper > ul  > li:nth-child(" + i + ") > label"));
						//elem.click();
						executore.executeScript("arguments[0].click();", elemen);
						softAssert.assertEquals(driver.findElement(COLOR_TEXT).getText() , colornew , "Color not changing");
					}
				}
			}
			//	elementsCount++;


		}


		//		// change second color if present
		//		if(elementsCount > 1) {
		//			colorBeforeChange = driver.findElement(COLOR_TEXT).getText();
		//			
		//			
		//			driver.findElement(SECOND_COLOR).click();
		//			Thread.sleep(2000);
		//			colorAfterChange = driver.findElement(COLOR_TEXT).getText();
		//			if(colorBeforeChange.equals(colorAfterChange))
		//				throw new Exception("Second color not changing");
		//		}
		//		// change third color if present
		//		if(elementsCount > 2) {
		//			colorBeforeChange = driver.findElement(COLOR_TEXT).getText();
		//			driver.findElement(THIRD_COLOR).click();
		//			Thread.sleep(2000);
		//			colorAfterChange = driver.findElement(COLOR_TEXT).getText();
		//			if(colorBeforeChange.equals(colorAfterChange))
		//				throw new Exception("Third color not changing");
		//		}
		Reporter.log("Colors are present and changing correct");

		//driver.findElement(SIZE_DROPDOWN).click();
		// Select first size between available sizes
		allElements = driver.findElements(ALL_SIZE_LIST);
		elementsCount = allElements.size();
		System.out.println("All sizes list :: "+ elementsCount);

		for(int i=1;i<=elementsCount;i++) {
			flag = false;
			//driver.findElement(SIZE_DROPDOWN).click();
			executore.executeScript("arguments[0].click();", driver.findElement(SIZE_DROPDOWN));
			element = driver.findElement(By.cssSelector(".select--options__list > li:nth-child("+i+") > div"));

			allClasses = element.getAttribute("class");
			// check size available or not
			for (String c : allClasses.split(" ")) {
				if (c.equals("unavailable")) {
					if(!driver.findElement(PRODUCT_TITLE).getText().contains("Pack"))
					{
						Thread.sleep(3000);
						// click on the unavailable item and check the button text
						//driver.findElement(By.cssSelector(".select--options__list > li:nth-child("+i+")")).click();
						jse = (JavascriptExecutor)driver;
						jse.executeScript("arguments[0].click();", element);
						//element.click();
						Thread.sleep(4000);

						driver.navigate().refresh();
						//						driver.findElement(By.cssSelector(".swym-tab-modal-close")).click();
						//						}		
						//						catch (Exception e)
						//						{
						//							Reporter.log("Join the waitlist not functional at the moment");
						//							
						//						}

						System.out.println("Unavailable size clicked");
						element = driver.findElement(JOIN_THE_WAITLIST);
						text1 = element.getText();
						System.out.println("Text of button when Unavailable size selected :: "+text1);
						if(!(text1.equalsIgnoreCase("JOIN THE WAITLIST")))
							throw new Exception("Text change for 'JOIN THE WAITLIST' ");

						flag = true;
					}
					else 
					{
						driver.findElement(By.cssSelector(".select--options__list> li:nth-child("+i+")")).click();
						System.out.println("Unavailable size clicked");
						//	driver.findElement(ADD_TO_CART_BUTTON).click();
						System.out.println(driver.findElement(ADD_TO_CART_BUTTON).getText());
						if (!driver.findElement(ADD_TO_CART_BUTTON).getText().contentEquals("Out of Stock"))
						{
							element = driver.findElement(JOIN_THE_WAITLIST);
							text1 = element.getText();
							System.out.println("Text of button when Unavailable size selected :: "+text1);
							if(!(text1.equalsIgnoreCase("JOIN THE WAITLIST")))
								throw new Exception("Text change for 'JOIN THE WAITLIST' for Packs");
							text1 = element.getText();
						}
						flag = true;
					}
					//break;
				}
				else 
				{
					jse = (JavascriptExecutor)driver;
					jse.executeScript("arguments[0].click();" , element);
					//	element.click();
				}
				//				else if (driver.findElement(ADD_TO_CART_BUTTON).getText().contentEquals("Out of Stock"))
				//				{
				//					flag=true;
				//					break;
				//				}
			}
			if(i==elementsCount) {
				Reporter.log("No any size available");
				break;
			}			
			if(flag == true)
				continue;
			else {
				// if flag is not true means size is available
				//	driver.findElement(By.cssSelector(".select--options__list > li:nth-child("+i+")")).click();
				// check the button txt and click
				element = driver.findElement(ADD_TO_CART_BUTTON);
				text1 = element.getText();
				System.out.println("Button text when Available size selected :: "+text1);
				if(!text1.equalsIgnoreCase("OUT OF STOCK"))
				{
					if(!(text1.equalsIgnoreCase("ADD TO CART"))) {
						throw new Exception("Text change for 'ADD TO CART' ");
					}
				}
				// check if we are able to add and minus quantity
				quantity = driver.findElement(QUANTITY).getAttribute("data-add-qty");
				Thread.sleep(3000);
				element = driver.findElement(PLUS_BUTTON);
				jse = (JavascriptExecutor)driver;
				jse.executeScript("arguments[0].click();", element);
				//	driver.findElement(PLUS_BUTTON).click();
				Thread.sleep(3000);
				String quantity1 = driver.findElement(QUANTITY).getAttribute("data-add-qty");
				// check quantity added by 1 or not 
				if(Integer.parseInt(quantity1)!=Integer.parseInt(quantity)+1) 
					//if(!quantity1.equals(quantity + 1) )
					//if(quantity1)
					throw new Exception("Quantity not increased by 1");

				Reporter.log("Quantity increased by 1");
				element = driver.findElement(MINUS_BUTTON);
				jse = (JavascriptExecutor)driver;
				jse.executeScript("arguments[0].click();", element);
				//driver.findElement(MINUS_BUTTON).click();

				// some times .click() not clicking the element then that time use JavascriptExecutor
				//		element = driver.findElement(By.id("minusBtn"));
				//		jse = (JavascriptExecutor)driver;
				//		jse.executeScript("arguments[0].click();", element);

				String quantity2 = driver.findElement(QUANTITY).getAttribute("data-add-qty");
				if(Integer.parseInt(quantity2)!=Integer.parseInt(quantity1)-1)
					throw new Exception("Quantity not decreased by 1");
				Reporter.log("Quantity decreased by 1");

				// check 'Best PairGuarantee' and 'Shopping And Returns' are clickable or not
				flag = false;


				if (driver.findElement(PRODUCT_DETAILS).isDisplayed())
				{
					element = driver.findElement(PRODUCT_DETAILS);
					jse = (JavascriptExecutor)driver;
					jse.executeScript("arguments[0].click();", element);
				}
				else throw new Exception("Product details accordion not present or not clikable ");
				try
				{
					if (driver.findElement(PAIR_GUARANTEE).isDisplayed())
					{
						element = driver.findElement(PAIR_GUARANTEE);
						jse = (JavascriptExecutor)driver;
						jse.executeScript("arguments[0].click();", element);
					}
					else throw new Exception("PAIR_GUARANTEE accordion not present or not clikable");
				}
				catch(Exception e )
				{
					Reporter.log("Pair guarantee link is not present for this particular product");
				}
				if (driver.findElement(SHIPING_AND_RETURNS).isDisplayed()) {
					element = driver.findElement(SHIPING_AND_RETURNS);
					jse = (JavascriptExecutor)driver;
					jse.executeScript("arguments[0].click();", element);
					//driver.findElement(SHIPING_AND_RETURNS).click();
				}		
				else throw new Exception("SHIPING_AND_RETURNS accordion not present or not clikable");	

				//Buywithcode

				//driver.findElement())



				if (!driver.findElement(PRODUCT_TITLE).getText().contains("Pack"))
				{
					// click on Add To Cart Button
					if(!driver.findElement(ADD_TO_CART_BUTTON).getText().equalsIgnoreCase("Out Of Stock"))
					{
						if(!driver.findElement(ADD_TO_CART_BUTTON).isEnabled())
							throw new Exception("ADD TO CART button is not present");
						element = driver.findElement(ADD_TO_CART_BUTTON);
						jse = (JavascriptExecutor)driver;
						jse.executeScript("arguments[0].click();", element);

						Thread.sleep(5000);
						driver.get(CustomUtilities.baseUrl);
						//	driver.findElement(ADD_TO_CART_BUTTON).click();
						Thread.sleep(3000);
						Reporter.log("ADD TO CART button is Displayed :: Clickable");
					}
				}
				else
				{
					if(!driver.findElement(ADD_TO_CART_BUTTON).isEnabled())
						throw new Exception("ADD TO CART button is not present");
					element = driver.findElement(ADD_TO_CART_BUTTON);
					jse = (JavascriptExecutor)driver;
					jse.executeScript("arguments[0].click();" , element);

					Reporter.log("ADD TO CART button is Displayed :: Clickable");


				}
				break;
			}
		}
	}

	public void validateafterpayonpdp() throws Exception {
		String text = null;
		SoftAssert softAssert = new SoftAssert();

		// call method to navigate to collection page
		new HomePageComponents(driver).navigateToBraCategory();

		// call method to navigate product details page
		text = new CollectionPageComponent(driver).navigateToProductDetailsPage();

		// call method to check correct PDP opend or not
		checkCorrectProductDetailsPageOpenedOrNot(text);

		//Check afterpay message display
		if(!driver.findElement(AFTERPAY_PDP).isDisplayed())
			throw new Exception("AfterPay Message not present on PDP");
		Reporter.log("After pay Message on PDP is displayed :: Displayed");

		//Check message with calculated installment and icon display
		price= driver.findElement(PRODUCT_PRICE).getText();
		String[] price1 = price.split("\\$");
		String price2 = price1[price1.length-1];
		if(Float.parseFloat(price2)<35 || Float.parseFloat(price2)>1000 )
		{
			softAssert.assertEquals(driver.findElement(AFTERPAY_PDP).getText(), "available for orders between $35 - $1000" , "Wrong afterpay message displayed for product price");
			Reporter.log("After pay message is correct On PDP :: Displayed and Correct message is displayed");
			softAssert.assertTrue(driver.findElement(AFTERPAY_PDP_BEFOREMESSAGE_LOGO).isDisplayed(), "Afterpay logo not present on PDP");
			Reporter.log("After pay logo icon is displayed on PDP :: Displayed");
		}

		else
		{

			installment =Float.parseFloat(price2)/4;
			String inst = String.format("%.2f",installment );
			String expectedmessage = "In 4 interest-free installments of $" + inst + " by"; 
			System.out.println("expected message is: " + expectedmessage);
			System.out.println("Actual message is: " +driver.findElement(AFTERPAY_PDP).getText());
			softAssert.assertEquals(driver.findElement(AFTERPAY_PDP).getText(), expectedmessage , "Wrong afterpay message displayed for product price");
			Reporter.log("After pay message with calculated installment is correct :: Displayed and correct");
			softAssert.assertTrue(driver.findElement(AFTERPAY_PDP_AFTERMESSAGE_LOGO).isDisplayed(), "Afterpay logo not present on PDP");
			Reporter.log("After pay logo icon is displayed on PDP :: Displayed");

		}


		softAssert.assertAll();
	}


	public void verifyReviewsDisplay() throws Exception
	{
		String text = null;
		SoftAssert softAssert = new SoftAssert();
		// call method to navigate to collection page
		new HomePageComponents(driver).navigateToAllPantiesInWomenCategory();
		// call method to navigate product details page
		text = new CollectionPageComponent(driver).navigateToProductDetailsPage();
		// call method to check correct PDP opend or not
		checkCorrectProductDetailsPageOpenedOrNot(text);

		if(driver.findElement(RATING_STARS).isDisplayed()) {

			WebElement elem = driver.findElement(RATING_STARS);
			JavascriptExecutor executore = (JavascriptExecutor)driver;
			executore.executeScript("arguments[0].click();", elem);
			// wait for scroll it down
			Thread.sleep(6000);
			Reporter.log("Review Stars Are Displayed :: Clickable :: Clicked");
			// check for Rating and Reviews are present or not
			softAssert.assertEquals(driver.findElement(REVIEWS_TITLE).getText(), "Rating & Reviews", "Text change for Ratings and Reviews title");
			softAssert.assertTrue(driver.findElement(WRITE_REVIEW_BUTTON).isDisplayed(), "Write a review button is not displayed");


			System.out.println("rating: " + driver.findElement(RATING_NUMBER).getText());
			if(!driver.findElement(RATING_NUMBER).getText().equals("(0)"))
			{

				if(driver.findElement(RATING_AND_REVIEWS_BOX).isDisplayed()) {
					
					softAssert.assertTrue(driver.findElement(FIRST_BOX_IN_RATING_AND_REVIEWS).isDisplayed() , " First box is not present in Rating and Reviews section" );
					softAssert.assertTrue(driver.findElement(STARS_IN_FIRST_BOX).isDisplayed(), "In first box Review Stars not present");
					softAssert.assertTrue(driver.findElement(AVG_RATING_IN_FIRST_BOX).isDisplayed(), "In first box Average Rating is not present");
					softAssert.assertTrue(driver.findElement(TOTAL_REVIEWS_IN_FIRST_BOX).isDisplayed(), "In first box Total Reviews are not present");

					softAssert.assertTrue(driver.findElement(SECOND_BOX_IN_RATING_AND_REVIEWS).isDisplayed() , "Second Box is not present in Rating and Reviews section");
					softAssert.assertTrue(driver.findElement(RATING_LABEL_IN_SECOND_BOX).isDisplayed(), "In second box Rating Label is not present");				
					softAssert.assertTrue(driver.findElement(RATING_METER_IN_SECOND_BOX).isDisplayed(), "In second box Rating Meter is not present");
					softAssert.assertTrue(driver.findElement(RATING_COUNT_IN_SECOND_BOX).isDisplayed(), "In second box Rating Count is not present");

					softAssert.assertTrue(driver.findElement(THIRD_BOX_IN_RATING_AND_REVIEWS).isDisplayed(), "Third Box is not present in Rating and Reviews section");
					softAssert.assertTrue(driver.findElement(FIT_GRAPH_IN_THIRD_BOX).isDisplayed(), "In third box fit graph not present");
					softAssert.assertTrue(driver.findElement(FIT_STATUS_IN_THIRD_BOX).isDisplayed(), "In third box fit status not present");

					Reporter.log("Verified Reviews and Rating box section, All elements are present including fit status, fit graph, Rating label, rating count, Rating meter, Avg rating, total reviews etc");
				}

				else 

					throw new Exception("Rating and Reviews box is not present");

				softAssert.assertEquals(driver.findElement(REVIEWER_NAME).getText(), "abc", "Reviewer name is not displayed");
				softAssert.assertTrue(driver.findElement(REVIEWER_TYPE).isDisplayed(), "Reviewer type is not displayed");
				softAssert.assertTrue(driver.findElement(USER_REVIEW_FIT).isDisplayed(), "User review for fit is not displayed");
				softAssert.assertTrue(driver.findElement(USER_REVIEW_DATE).isDisplayed(), "User review date is not displayed");
				softAssert.assertTrue(driver.findElement(USER_REVIEW_LOCATION).isDisplayed(), "User review location is not displayed");
				softAssert.assertTrue(driver.findElement(USER_REVIEW_STARS).isDisplayed(), "User review stars is not displayed");
				softAssert.assertTrue(driver.findElement(USER_REVIEW_TITLE).isDisplayed(), "User review title is not displayed");
				softAssert.assertTrue(driver.findElement(USER_REVIEW_MAIN_CONTENT).isDisplayed(), "User review main content is not displayed");
				softAssert.assertTrue(driver.findElement(USER_REVIEW_WAS_THIS_HELPFUL_TEXT).isDisplayed(), "Was this helpful text is not displayed");
				softAssert.assertTrue(driver.findElement(USER_REVIEW_THUMBS_UP).isDisplayed(), "Thumbs Up icon is not displayed");
				softAssert.assertTrue(driver.findElement(USER_REVIEW_THUMBS_DOWN).isDisplayed(), "Thumbs down icon is not displayed");


			}

			else 
			{
				softAssert.assertTrue(driver.findElement(NO_REVIEWS_TEXT).isDisplayed(), "No reviews text is not displayed");
				softAssert.assertTrue(driver.findElement(NO_REVIEWS_SUBTEXT).isDisplayed(), "No reviews subtext is not displayed");
				softAssert.assertTrue(driver.findElement(NO_REVIEW_WRITE_REVIEW).isDisplayed(), "No reviews container - write a review is not displayed");

			}

			softAssert.assertAll();
			Reporter.log("Rating and Reviews Displayed");
		}
		else
			throw new Exception("Rating Stars are not clickable");
	}

	public void verifyWriteReview() throws Exception
	{
		String text = null;
		SoftAssert softAssert = new SoftAssert();
		// call method to navigate to collection page
		new HomePageComponents(driver).navigateToAllUnderwearInMenCategory();
		// call method to navigate product details page
		text = new CollectionPageComponent(driver).navigateToProductDetailsPage();
		// call method to check correct PDP opend or not
		checkCorrectProductDetailsPageOpenedOrNot(text);

		WebElement elem = driver.findElement(RATING_STARS);
		JavascriptExecutor executore = (JavascriptExecutor)driver;
		executore.executeScript("arguments[0].click();", elem);
		// wait for scroll it down
		Thread.sleep(6000);

		driver.findElement(WRITE_REVIEW_BUTTON).click();

		softAssert.assertEquals(driver.findElement(WRITE_REVIEW_TITLE).getText(), "Write a Review", "Laguage mismatch for Write a Review title");
		softAssert.assertEquals(driver.findElement(NAME).getText(), "* Name", "Laguage mismatch for Name ");
		driver.findElement(NAME_TEXTBOX).sendKeys("Vijeta");
		softAssert.assertEquals(driver.findElement(EMAIL).getText(), "* Email", "Laguage mismatch for Email ");
		driver.findElement(EMAIL_TEXTBOX).sendKeys("vijeta@tommyjohnwear.com");
		softAssert.assertEquals(driver.findElement(LOCATION).getText(), "Location (Optional)", "Laguage mismatch for Location ");
		driver.findElement(LOCATION_TEXTBOX).sendKeys("India");
		softAssert.assertEquals(driver.findElement(FIT).getText(), "Fit", "Laguage mismatch for Fit	 ");
		driver.findElement(FIT_SELECTION).click();
		softAssert.assertEquals(driver.findElement(OVERALLRATING).getText(), "* Overall Rating", "Laguage mismatch for Overall Rating	 ");
		driver.findElement(STAR_SELECTION).click();
		softAssert.assertEquals(driver.findElement(HEADLINE).getText(), "* Add a Headline", "Laguage mismatch for Headline ");
		driver.findElement(HEADLINE_TEXTBOX).sendKeys("Nice Product");
		softAssert.assertEquals(driver.findElement(REVIEW).getText(), "* Review", "Laguage mismatch for Review	 ");
		driver.findElement(REVIEW_TEXTBOX).sendKeys("Must Buy");

		softAssert.assertTrue(driver.findElement(SUBMIT_BTN).isEnabled(), "Submit button not enabled");


		softAssert.assertAll();

	}

	public void verifyWriteReviewErrorMsgs() throws Exception
	{
		String text = null;
		SoftAssert softAssert = new SoftAssert();
		// call method to navigate to collection page
		new HomePageComponents(driver).navigateToAllUnderwearInMenCategory();
		// call method to navigate product details page
		text = new CollectionPageComponent(driver).navigateToProductDetailsPage();
		// call method to check correct PDP opend or not
		checkCorrectProductDetailsPageOpenedOrNot(text);

		WebElement elem = driver.findElement(RATING_STARS);
		JavascriptExecutor executore = (JavascriptExecutor)driver;
		executore.executeScript("arguments[0].click();", elem);
		// wait for scroll it down
		Thread.sleep(6000);

		driver.findElement(WRITE_REVIEW_BUTTON).click();
		Thread.sleep(4000);
		driver.findElement(SUBMIT_BTN).click();
		softAssert.assertEquals(driver.findElement(NAME_ERROR).getText(), "Pleases enter a name for this review.", "Laguage mismatch for error message for Name");
		softAssert.assertEquals(driver.findElement(EMAIL_ERROR).getText(), "Please enter a valid email for this review.", "Laguage mismatch for error message for Email");
		softAssert.assertEquals(driver.findElement(HEADLINE_ERROR).getText(), "Please enter a headline for this review.", "Laguage mismatch for error message for Headline");
		softAssert.assertEquals(driver.findElement(REVIEW_ERROR).getText(), "Please enter some content for this review.", "Laguage mismatch for error message for Review");

		softAssert.assertAll();

	}



}

