package com.tommyjohn.automation.Components;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

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

	public ProductDetailsPageComponents(WebDriver driver) {
		this.driver = driver;
	}
	
	public void validateProductDetailsPage() throws Exception {
		String text = null;
		
		// call method to navigate to collection page
		new HomePageComponents(driver).navigateToAllUnderwearInMenCategory();
		
		// call method to nevigate product details page
		text = new CollectionPageComponent(driver).navigateToProductDetailsPage();
		
		// call method to check correct PDP opend or not
		checkCorrectProductDetailsPageOpenedOrNot(text);
		
		// call method for PDP validation
		validatePage();

	}
	
	public void checkCorrectProductDetailsPageOpenedOrNot(String text1) throws Exception {
		String text2 = null;
		
		if(!driver.findElement(PRODUCT_TITLE).isEnabled())
			throw new Exception("Product title is not displayed on product details page");
		text2 = driver.findElement(PRODUCT_TITLE).getText();
		
		// check correct PDP opened
		if(!text1.equals(text2))
			throw new Exception("Wrog product details page opened");
		Reporter.log("Product details page is Correct :: Product title is Displayed");

	}
	


	public void validatePage() throws Exception {
		String text1 = null;
		String allClasses = null;
		boolean flag = false;
		String colorAfterChange = null;
		String colorBeforeChange = null;
		String quantity = null;
		

		// check product price is displayed or not
		if(!driver.findElement(PRODUCT_PRICE).isEnabled())
			throw new Exception("Product price is not displayed");
		
		// check for whats my size
		if(!driver.findElement(WHATS_MY_SIZE).isEnabled())
			throw new Exception("Whats my size button is not present");
		WebElement ele = driver.findElement(WHATS_MY_SIZE);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", ele);
	//	driver.findElement(WHATS_MY_SIZE).click();
		Thread.sleep(3000);
		allClasses = driver.findElement(SIZE_GUIDE_BUTTON).getAttribute("class");
		for (String c : allClasses.split(" ")) {
			if (c.equals("active")) {
				flag = true;
			}
		}
		if(!flag)
			throw new Exception("Sizes table is not displayed after Whats my size button clicked");
		Reporter.log("Whats my size is Displayed :: Clickable");
		
		// scroll it top again
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -document.body.scrollHeight)");
		Thread.sleep(3000);

		// Check review stars are clickable and visible or not
		if(!driver.findElement(RATING_STARS).isEnabled())
			throw new Exception("Rating Stars are not displayed on product details page");
		element = driver.findElement(RATING_STARS);
		// get all classes from specified path
		allClasses = element.getAttribute("class");
		// check stars are clickable or not by using star-clickable class name
		for (String c : allClasses.split(" ")) {
			if (c.equals("star-clickable")) {
				flag = true;
			}
		}
		if(flag == true) {
			Thread.sleep(3000);
			jse = (JavascriptExecutor)driver;
			jse.executeScript("window.scrollBy(0,-250)", "");
			Thread.sleep(4000);
		//	driver.findElement(By.cssSelector(".yotpo-stars")).click();
			//driver.findElement(RATING_STARS).click();
			WebElement elem = driver.findElement(RATING_STARS);
			JavascriptExecutor executore = (JavascriptExecutor)driver;
			executore.executeScript("arguments[0].click();", elem);
			// wait for scroll it down
			Thread.sleep(3000);
			Reporter.log("Review Stars Are Displayed :: Clickable :: Clicked");
			// check for Rating and Reviews are present or not
			System.out.println("rating: " + driver.findElement(RATING_NUMBER).getText());
			if(!driver.findElement(RATING_NUMBER).getText().equals("(0)"))
			{
			if(driver.findElement(RATING_AND_REVIEWS_BOX).isDisplayed()) {
				if(!driver.findElement(FIRST_BOX_IN_RATING_AND_REVIEWS).isDisplayed())
					throw new Exception("In first box Review Stars not present in Rating and Reviews section");
			}
			else {
				text1 = driver.findElement(STAR_RATINGS).getText();
				System.out.println("Ratings :: "+text1);
				
				if(text1.equals(properties.getProperty("reviewsRating"))) {
					if(!driver.findElement(BE_THE_FIRST_TO_WRITE_REVIEW).isEnabled())
						throw new Exception("'BE THE FIRST TO WRITE A REVIEW' button is not displayed");
					driver.findElement(BE_THE_FIRST_TO_WRITE_REVIEW).click();
					Reporter.log("'BE THE FIRST TO WRITE A REVIEW' button is Displayed :: Clickable");
				}
			}
			Reporter.log("Rating and Reviews Displayed");
			}
		}else
			throw new Exception("Rating Stars are not clickable");
		
		
		// check for write a review button
		if(!driver.findElement(WRITE_REVIEW_BUTTON).isEnabled())
			throw new Exception("Write Review Button is not displayed");
		// check Write Review portion should not be visible before click button to Write Review
		element = driver.findElement(WRITE_REVIEW_HEADING);
		allClasses = element.getAttribute("class");
		for (String c : allClasses.split(" ")) {
			if (c.equals("visible")) {
				throw new Exception("Write Review portion should not be visible before Write Review button is clicked");
			}
		}
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(WRITE_REVIEW_BUTTON));	
		driver.findElement(WRITE_REVIEW_BUTTON).click();
		// check Write Review portion should be visible by clicking Write A Review button
		element = driver.findElement(WRITE_REVIEW_HEADING);
		allClasses = element.getAttribute("class");
		for (String c : allClasses.split(" ")) {
			if (c.equals("visible")) {
				flag = true;
			}
		}
		if(!flag)
			throw new Exception("Write Review portion is not visible after click Write Review button");
		Reporter.log("Write A Review button Displayed :: Clickable :: Write Review portion is Displayed after click button :: Text Correct");
		
		// scroll it top again
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -document.body.scrollHeight)");
		Thread.sleep(3000);
		
		// Check we can select color or not
		allElements = driver.findElements(ALL_COLOR_LIST);
        elementsCount = allElements.size();
        System.out.println("Color Element count :: "+elementsCount);

        // change second color if present
        if(elementsCount > 1) {
        	colorBeforeChange = driver.findElement(COLOR_TEXT).getText();
        	driver.findElement(SECOND_COLOR).click();
        	Thread.sleep(2000);
        	colorAfterChange = driver.findElement(COLOR_TEXT).getText();
        	if(colorBeforeChange.equals(colorAfterChange))
        		throw new Exception("Second color not changing");
        }
        // change third color if present
        if(elementsCount > 2) {
        	colorBeforeChange = driver.findElement(COLOR_TEXT).getText();
        	driver.findElement(THIRD_COLOR).click();
        	Thread.sleep(2000);
        	colorAfterChange = driver.findElement(COLOR_TEXT).getText();
        	if(colorBeforeChange.equals(colorAfterChange))
        		throw new Exception("Third color not changing");
        }
        Reporter.log("Colors are present and changing correct");
        
        
		// Select first size between available sizes
		allElements = driver.findElements(ALL_SIZE_LIST);
        elementsCount = allElements.size();
        System.out.println("All sizes list :: "+ elementsCount);
        
        for(int i=1;i<=elementsCount;i++) {
        	flag = false;
        	element = driver.findElement(By.cssSelector(".product-option__variants.product-option__variants-size > ul > li:nth-child("+i+") > input"));
			allClasses = element.getAttribute("class");
			// check size available or not
			for (String c : allClasses.split(" ")) {
				if (c.equals("unavailable")) {
					flag = true;
					// click on the unavailable item and check the button text
					driver.findElement(By.cssSelector(".product-option__variants.product-option__variants-size > ul > li:nth-child("+i+")")).click();
					System.out.println("Unavailable size clicked");
					element = driver.findElement(EMAIL_WHEN_IN_STOCK_BUTTON);
					text1 = element.getText();
					System.out.println("Text of button when Unavailable size selected :: "+text1);
					if(!(text1.equalsIgnoreCase("EMAIL WHEN IN STOCK")))
						throw new Exception("Text change for 'EMAIL WHEN IN STOCK' ");
					break;
				}
			}
			if(i==elementsCount) {
				Reporter.log("No any size available");
				break;
			}			
			if(flag == true)
				continue;
			else {
				// if flag is not true meanse size is available
				driver.findElement(By.cssSelector(".product-option__variants.product-option__variants-size > ul > li:nth-child("+i+")")).click();
				// check the button txt and click
				element = driver.findElement(ADD_TO_CART_BUTTON);
				text1 = element.getText();
				System.out.println("Button text when Available size selected :: "+text1);
				if(!(text1.equalsIgnoreCase("ADD TO CART"))) {
					throw new Exception("Text change for 'ADD TO CART' ");
				}
				// check if we are able to add and minus quantity
				quantity = driver.findElement(QUANTITY).getAttribute("data-add-qty");
				driver.findElement(PLUS_BUTTON).click();
				Thread.sleep(3000);
				String quantity1 = driver.findElement(UPDATED_QUANTITY).getAttribute("data-add-qty");
				// check quantity added by 1 or not 
				if(Integer.parseInt(quantity1)!=Integer.parseInt(quantity)+1) 
					throw new Exception("Quantity not increased by 1");

				Reporter.log("Quantity increased by 1");

				driver.findElement(MINUS_BUTTON).click();
				
				// some times .click() not clicking the element then that time use JavascriptExecutor
		//		element = driver.findElement(By.id("minusBtn"));
		//		jse = (JavascriptExecutor)driver;
		//		jse.executeScript("arguments[0].click();", element);
				
				String quantity2 = driver.findElement(UPDATED_QUANTITY).getAttribute("data-add-qty");
				if(Integer.parseInt(quantity2)!=Integer.parseInt(quantity1)-1)
					throw new Exception("Quantity not decreased by 1");
				Reporter.log("Quantity decreased by 1");
				
				// check 'Best PairGuarantee' and 'Shopping And Returns' are clickable or not
				flag = false;

				if(!driver.findElement(PAIR_GUARANTEE_LINK).isEnabled())
					throw new Exception("Pair gaurantee link is Displayed :: Clickable");
				if(!driver.findElement(SHIPING_AND_RETURNS_LINK).isEnabled())
					throw new Exception("Shiping and returns link is Displayed :: Clickable");

				
				// check for Details and Side Guid button
				flag = false;
				if(!driver.findElement(PRODUCT_DETAILS_BUTTON).isEnabled())
					throw new Exception("Details button is not Displayed");
				driver.findElement(PRODUCT_DETAILS_BUTTON).click();
				allClasses = driver.findElement(PRODUCT_DETAILS_BUTTON).getAttribute("class");
				for (String c : allClasses.split(" ")) {
					if (c.equals("active")) {
						flag = true;
					}
				}
				if(!flag)
					throw new Exception("Details button is not clickable");
				
				if(!driver.findElement(SIZE_GUIDE_BUTTON).isEnabled())
					throw new Exception("Size Guide button is not Displayed");
				driver.findElement(SIZE_GUIDE_BUTTON).click();
				allClasses = driver.findElement(SIZE_GUIDE_BUTTON).getAttribute("class");
				for (String c : allClasses.split(" ")) {
					if (c.equals("active")) {
						flag = true;
					}
				}
				if(!flag)
					throw new Exception("Size Guide button is not Clickable");
				Reporter.log("Details button and Size Guide button is Displayed :: Clickable");
				
				element = driver.findElement(RATING_STARS);
				jse.executeScript("arguments[0].scrollIntoView(true);", element);
				Thread.sleep(3000); 

				
				// click on Add To Cart Button
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
				break;
			}
        }
	}
}