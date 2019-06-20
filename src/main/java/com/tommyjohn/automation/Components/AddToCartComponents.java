package com.tommyjohn.automation.Components;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

import com.tommyjohn.automation.PageLocators.AddToCartLocators;
import com.tommyjohn.automation.utils.CustomUtilities;

public class AddToCartComponents extends AddToCartLocators{

	public AddToCartComponents(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriver driver;
	public WebElement element;
	public Actions action;
	public JavascriptExecutor jse;
	Properties properties = CustomUtilities.properties;
	List<WebElement> allElements;
	int elementsCount;


	public String addToCart() throws Exception {
		String text = null;

		// navigate to any product collection page
		new HomePageComponents(driver).navigateToAllPantiesInWomenCategory();

		// call method to navigate product details page
		text = new CollectionPageComponent(driver).navigateToProductDetailsPage();

		// call method to check correct PDP opened or not
		new ProductDetailsPageComponents(driver).checkCorrectProductDetailsPageOpenedOrNot(text);


		// select size and add product to cart
		selectSize();
		 atc();
		return text;
	}

	public void selectSize() throws Exception {
		String allClasses = null;
		boolean flag = false;
		String text1 = null;

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
				Thread.sleep(2000);
				// if flag is not true means size is available
				WebElement ele = driver.findElement(By.cssSelector(".product-option__variants.product-option__variants-size > ul > li:nth-child("+i+") > label"));
				jse = (JavascriptExecutor)driver;
				jse.executeScript("arguments[0].click();", ele);
				System.out.println(i+"th size is selected");
				// check the button txt and click
				element = driver.findElement(ADD_TO_CART_BUTTON);
				text1 = element.getText();
				System.out.println("Button text when Available size selected :: "+text1);
				if(!(text1.equalsIgnoreCase("ADD TO CART"))) {
					throw new Exception("Text change for 'ADD TO CART' ");
				}			
				Thread.sleep(2000);
				break;
			}
		}
	}

	public void atc() throws Exception
	{
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

	}
}
