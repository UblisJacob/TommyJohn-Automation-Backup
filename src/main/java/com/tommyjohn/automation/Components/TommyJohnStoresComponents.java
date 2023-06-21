package com.tommyjohn.automation.Components;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.tommyjohn.automation.PageLocators.TommyJohnStoresPageLocators;
import com.tommyjohn.automation.utils.CustomUtilities;

import commonMethods.GeneralMethods;

public class TommyJohnStoresComponents extends TommyJohnStoresPageLocators {

	public WebDriver driver;
	Wait<WebDriver> wait;
	public static String baseUrl = CustomUtilities.baseUrl;
	public String currentUrl;
	public static WebElement element;
	JavascriptExecutor executor = (JavascriptExecutor) driver;
	SoftAssert softassert = new SoftAssert();
	GeneralMethods genMethods = new GeneralMethods();

	public TommyJohnStoresComponents(WebDriver driver) {

		this.driver = driver;

		wait = new FluentWait<WebDriver>(driver).pollingEvery(Duration.ofMillis(500))
				.withTimeout(Duration.ofSeconds(30)).ignoring(NoSuchElementException.class);

	}

	/**
	 * 
	 * 1. The page should list out each Tommy John store location with address,
	 * phone number & hours. 2. "Get Directions" should open Google maps with the
	 * location search.
	 * 
	 * @throws InterruptedException
	 */

	public void validateTJStoresPageComponentsAndInteractions() throws InterruptedException {

//		Navigate to Store Directory Page
		new HomePageComponents(driver).navigateToStoreDirectoryPage();

		wait.until(ExpectedConditions.visibilityOfElementLocated(STORE_PAGE_HEADER));

		for (int i = 1; i <= 6; i++) {

			String storeName = genMethods.autoIndex(driver, STORE_NAME, i).getText();

//			validate store address is present
			element = genMethods.autoIndex(driver, STORE_ADDRESS, i);
//			element = driver.findElement(By.cssSelector(".stores-section__container>div:nth-child("+i+")>div>div:nth-child(3)>p"));
			String storeAddress = element.getText();
			System.out.println(storeName + "Store Address is: " + storeAddress);
			Reporter.log(storeName + "Store Address is: " + storeAddress);
			softassert.assertTrue(element.isDisplayed(), "Store address is not present for store ");

//			validate store phone number is present
			if (!storeName.contains("Opening Soon")) {
				element = genMethods.autoIndex(driver, STORE_PHONE, i);
				String phoneNum = element.getText();
//				element = driver.findElement(By.cssSelector(".stores-section__container>div:nth-child("+i+")>div>div:nth-child(4)>p"));
				System.out.println("Store Phone Number is: " + phoneNum);
				Reporter.log("Store Phone Number is: " + phoneNum);
				softassert.assertTrue(element.isDisplayed(),
						"Store phone number is not present for store " + storeName);
			}

//			validate store hours are present
			if (!storeName.contains("Opening Soon")) {
				element = genMethods.autoIndex(driver, STORE_HOURS, i);
//				element = driver.findElement(By.cssSelector(".stores-section__container>div:nth-child("+i+")>div>div:nth-child(4)>p:nth-child(2)"));
				String storeHours = element.getText();
				System.out.println("Store Hours are: " + storeHours);
				Reporter.log("Store Hours are: " + storeHours);
				softassert.assertTrue(element.isDisplayed(), "Store hours are not present for store " + storeName);
			}

//			"Get Directions" should open Google maps with the location search.
			if (!storeName.contains("Opening Soon")) {
				element = genMethods.autoIndex(driver, STORE_GET_DIRECTIONS, i);
//				element = driver.findElement(By.cssSelector(".stores-section__container>div:nth-child("+i+")>div>div+a"));
				softassert.assertTrue(element.isDisplayed(),
						"Get Directions button is not present for store " + storeName);

				if (element.isDisplayed()) {
					element.click();
					try {
						wait.until(ExpectedConditions.titleContains("Google Maps"));
						currentUrl = driver.getCurrentUrl();
						softassert.assertTrue(currentUrl.contains("https://www.google.com/maps"),
								"Google maps page did not open after clicking on Get Directions link of Store: "
										+ storeName);

//						Validation of auto population on google maps destination field
						String destinationField = driver.findElement(DESTINATION_FIELD).getAttribute("aria-label").replace(",", "").trim();
						String[] arr = storeAddress.split("\n");

						String addressFirstLine = arr[0];
						String lastLine = arr[arr.length - 1];
						String pincode = lastLine.substring(lastLine.length() - 5, lastLine.length());

						softassert.assertTrue(destinationField.contains(addressFirstLine),
								"Store address did not match the text in the auto populated destination field of Google Maps");
						softassert.assertTrue(destinationField.contains(pincode),
								"Store Pincode did not match the text in the auto populated destination field of Google Maps");
						
						System.out.println("After Clicking on Get Directions link, Google Maps destination field contained correct store address");
						Reporter.log("After Clicking on Get Directions link, Google Maps destination field contained correct store address");
						
						driver.navigate().back();
						
					} catch (TimeoutException e) {
						softassert.fail("Google Maps did not load or user was redirected to indirect page");
					}

				}

			}

		}
		
		softassert.assertAll();
		System.out.println("All Stores address, phone, open hours and Get Directions functionality validated successfully");
		Reporter.log("All Stores address, phone, open hours and Get Directions functionality validated successfully");

	}

}
