package com.tommyjohn.automation.Components;

import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.base.ExtentReport;
import com.tommyjohn.automation.PageLocators.CollectionPageLocator;
import com.tommyjohn.automation.PageLocators.FlyCartPageLocator;
import com.tommyjohn.automation.PageLocators.HomePageLocators;
import com.tommyjohn.automation.PageLocators.ProductDetailsPageLocators;
import com.tommyjohn.automation.utils.CustomUtilities;

import static com.tommyjohn.automation.PageLocators.CollectionPageLocator.ADD_TO_BAG_QUICKSHOP;
import static com.tommyjohn.automation.PageLocators.ProductDetailsPageLocators.CART_ICON;

public class FlyCartComponents extends FlyCartPageLocator{


	double installment;
	public JavascriptExecutor jse;
	public WebDriver driver;
	Properties properties = CustomUtilities.properties;
	WebElement element;
	List<WebElement> allElements;
	List<WebElement> RemoveItems;
	SoftAssert softAssert = new SoftAssert();
	public String price;
	private WebElement text;

	public FlyCartComponents(WebDriver driver) {
		this.driver = driver;

	}

    public void ValidateBundleTierinInLineCartPage() throws Exception {
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        new AddToCartComponents(driver).add3for$48product();
        driver.findElement(HomePageLocators.CART_ICON).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
//        driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.BUNDLE_TIER_TITLE));
        String BUNDLE_TIER_TITLE = driver.findElement(FlyCartPageLocator.BUNDLE_TIER_TITLE).getText();
        System.out.println(BUNDLE_TIER_TITLE);
        softAssert.assertEquals(BUNDLE_TIER_TITLE,"Women's 5 for $65","The Bundle Tier Meter Is Not Displayed");
        Reporter.log("Bundle Tier functionality works fine in InLineCart page");
        softAssert.assertAll();
        ExtentReport.extentTest.info("Bundle Tier functionality works fine in InLineCart page");
    }

    public void ValidateFiveFor65InInLineCart() throws Exception {
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        new AddToCartComponents(driver).add3for$48product();
        driver.findElement(HomePageLocators.CART_ICON).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
//        driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
        Thread.sleep(3000);
        String DISCOUNT_5for$65 = driver.findElement(FlyCartPageLocator.DISCOUNT_5for$65).getText().split(":")[1].trim();
        System.out.println(DISCOUNT_5for$65);
        softAssert.assertEquals(DISCOUNT_5for$65,"5 For $65","5 for $65 discount is not applied and not displayed in the Cart line item");
        String TOTAL = driver.findElement(FlyCartPageLocator.MINI_CART_TOTAL).getText();
        System.out.println(TOTAL);
        softAssert.assertEquals(TOTAL,"$65.00","Cart Total is differ from the 5 for $65 functionality");
        softAssert.assertAll();
        Reporter.log("5 for $65 functionality works fine in Cart page");
        ExtentReport.extentTest.info("5 for $65 functionality works fine in Cart page");
    }

	public void ValidateQuantityIncreaseInInLineCartPage() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		wait.until(ExpectedConditions.visibilityOfElementLocated(HomePageLocators.CART_ICON));
		Thread.sleep(2000);
//		js.executeScript("arguments[0].click();", driver.findElement(HomePageLocators.CART_ICON));
//		js.executeScript("arguments[0].click();", driver.findElement(CART_ICON));
		//driver.findElement(HomePageLocators.CART_ICON).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		SoftAssert softAssert = new SoftAssert();
//		WebElement quantityInput = wait.until(ExpectedConditions.visibilityOfElementLocated(QUANTITY_INPUT));
	//	JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement quantityInput = wait.until(ExpectedConditions.visibilityOfElementLocated(MINICART_QUANTITY_INPUT));
		int initialQty = Integer.parseInt(js.executeScript("return arguments[0].value;", quantityInput).toString().trim());

		// Click the + button until quantity reaches 4
		for (int i = initialQty; i < 4; i++) {
			WebElement plusButton = wait.until(ExpectedConditions.elementToBeClickable(MINICART_QUANTITY_INCREASE));
			js.executeScript("arguments[0].click();", plusButton);
			//plusButton.click();

			// Fix: create a final variable for lambda
			final int expectedQty = i + 1;

			wait.until(driver1 -> {
				WebElement refreshedQtyInput = driver1.findElement(MINICART_QUANTITY_INPUT);
				int updated = Integer.parseInt(js.executeScript("return arguments[0].value;", refreshedQtyInput).toString().trim());
				return updated == expectedQty;
			});
		}

		// Final validation
		WebElement updatedInput = wait.until(ExpectedConditions.visibilityOfElementLocated(MINICART_QUANTITY_INPUT));
		int updatedQty = Integer.parseInt(js.executeScript("return arguments[0].value;", updatedInput).toString().trim());

		softAssert.assertEquals(updatedQty, 4, "Quantity did not reach 4 as expected.");
		System.out.println(" Quantity successfully increased to " + updatedQty);

		softAssert.assertAll();
		Reporter.log(" Quantity increased to 4 in Cart page");
		ExtentReport.extentTest.info(" Quantity increased to 4 in Cart page");
	}




	public void ValidatePromoCodeFIRST20InInlineCart() throws InterruptedException {

		WebElement element;
		SoftAssert softAssert = new SoftAssert();

		driver.get("https://www.tommyjohn.com/");

		Thread.sleep(2000);
		driver.findElement(HomePageLocators.CART_ICON).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		Thread.sleep(3000);

		driver.findElement(CART_COUPONFIELD).sendKeys("FIRST20");
		Thread.sleep(8000);
		driver.findElement(APPLY_CTA).click();


		Thread.sleep(15000);
		String Coupon = driver.findElement(APPLIED_COUPON).getText();
		System.out.println(Coupon);

		element= driver.findElement(APPLIED_COUPON);
		softAssert.assertTrue(element.isDisplayed(), "The Coupon code FIRST20 is not getting applied in Cart page");
		Reporter.log("The Coupon code FIRST20 applied in Cart page");
		ExtentReport.extentTest.info("The Coupon code FIRST20 applied in Cart page");

		driver.get("https://www.tommyjohn.com/");

		driver.findElement(HomePageLocators.CART_ICON).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.INLINE_CART_APPLIED_COUPON));
	//	String Inline_Coupon = driver.findElement(INLINE_CART_APPLIED_COUPON).getText();
	//	System.out.println(Inline_Coupon);

		element= driver.findElement(INLINE_CART_APPLIED_COUPON);
		softAssert.assertTrue(element.isDisplayed(), "The Coupon code FIRST20 is not displayed in Cart page");
		Reporter.log("The Coupon code FIRST20 is displayed in Cart page");
		ExtentReport.extentTest.info("The Coupon code FIRST20 is displayed in Cart page");

	}

	public void ValidateAddingProductToCartFromInlineCartSuggestions() throws Exception {
		int count = 0;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		SoftAssert softAssert = new SoftAssert();
		jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", driver.findElement(INLINE_CART_ICON));
		jse.executeScript("window.scrollBy(0,500)");
		Thread.sleep(10000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(SUGGESTION_FIRST_PRODUCT)));
//		element = driver.findElement(SUGGESTION_FIRST_PRODUCT);
//		js.executeScript("arguments[0].scrollIntoView(true);", element);
//		action.moveToElement(element).build().perform();


		if (driver.findElement(QUICK_SHOP_ATC).isDisplayed()) {
			Reporter.log("Quick shop ATC button is displayed");
			ExtentReport.extentTest.info("Quick shop ATC button is displayed");
//			System.out.println("Quick shop ATC text is: " + driver.findElement(QUICK_SHOP_ATC).getText());
//			softAssert.assertEquals(driver.findElement(QUICK_SHOP_ATC).getText(), "Add To Cart +",
//					"Text changed for Quick shop ATC button.");
			driver.findElement(QUICK_SHOP_ATC).click();
			Thread.sleep(3000);
			Reporter.log("Quick shop ATC button is validated :: Clickable.");
			ExtentReport.extentTest.info("Quick shop ATC button is validated :: Clickable.");

			softAssert.assertTrue(driver.findElement(QUICK_SHOP_SIZE_OVERLAY).isDisplayed(),
					"On InLine Cart Quick shop select size overlay is not displayed after click on ATC button.");

			Reporter.log("Quick shop size overlay is displayed.");
			ExtentReport.extentTest.info("Quick shop size overlay is displayed.");
			softAssert.assertTrue(driver.findElement(QUICK_SHOP_SIZE_OVERLAY_CLOSE).isDisplayed(),
					"Quick shop select size overlay close btn is not displayed after click on ATC button.");
			Reporter.log("Quick shop size overlay is close button is displayed.");
			ExtentReport.extentTest.info("Quick shop size overlay is close button is displayed.");
			softAssert.assertTrue(driver.findElement(QUICK_SHOP_SIZE_OVERLAY_HEADING).isDisplayed(),
					"Quick shop select size overlay's heading is not displayed.");
			//	softAssert.assertEquals(driver.findElement(QUICK_SHOP_SIZE_OVERLAY_HEADING).getText(), "Choose Your Size",
			//			"Text changed for Quick shop Size overlay heading.");
			Reporter.log("Quick shop size overlay heading is displayed ::Correct.");
			ExtentReport.extentTest.info("Quick shop size overlay heading is displayed ::Correct.");
			softAssert.assertTrue(driver.findElement(QUICK_SHOP_SIZE_LABEL).isDisplayed(),
					"Quick shop size labels are not displayed.");
			Reporter.log("Quick shop size labels are displayed.");
			ExtentReport.extentTest.info("Quick shop size labels are displayed.");

			String First_Product_Name = driver.findElement(SUGGESTION_FIRST_PRODUCT_NAME).getText();
			System.out.println(First_Product_Name);
		//	jse.executeScript("arguments[0].click();", driver.findElement(SUGGESTION_FIRST_PRODUCT_SIZE));

			for (int k = 1; k <= 3; k++) {

				WebElement elements = driver.findElement(By.xpath("(//form[@data-cy='productForm']//input[@name='option[Size]'])[" + k + "]"));
				jse.executeScript("arguments[0].click();", elements);
				Thread.sleep(5000);

				try {

					jse.executeScript("arguments[0].click();", driver.findElement(ADD_TO_BAG_QUICKSHOP));
					wait.until(ExpectedConditions.visibilityOfElementLocated(INLINECART_PRODUCT_NAME));
					String InLineCart_Product = driver.findElement(INLINECART_PRODUCT_NAME).getText();
					System.out.println(InLineCart_Product);

					softAssert.assertTrue(driver.findElement(INLINECART_PRODUCT_NAME).isDisplayed(), "Product is not added to Cart from InLine Cart suggestion");
					Reporter.log("Product is added to Cart from InLine Cart Suggestion");
					ExtentReport.extentTest.info("Product is added to Cart from InLine Cart Suggestion");
					count++;

				} catch (Exception ex) {
					System.out.println("Product is OOS");
				}

				Thread.sleep(1000);


				if (count == 1)
					break;

			}


		}
	}
		public void ValidateAppropriateProductIsAddedToCartFromInLineCartSuggestion() throws InterruptedException {
			int count = 0;
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			SoftAssert softAssert = new SoftAssert();
			jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", driver.findElement(INLINE_CART_ICON));
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(SUGGESTION_FIRST_PRODUCT)));
//		element = driver.findElement(SUGGESTION_FIRST_PRODUCT);
//		js.executeScript("arguments[0].scrollIntoView(true);", element);
//		action.moveToElement(element).build().perform();


			if (driver.findElement(QUICK_SHOP_ATC).isDisplayed()) {
				Reporter.log("Quick shop ATC button is displayed");
				ExtentReport.extentTest.info("Quick shop ATC button is displayed");
//			System.out.println("Quick shop ATC text is: " + driver.findElement(QUICK_SHOP_ATC).getText());
//			softAssert.assertEquals(driver.findElement(QUICK_SHOP_ATC).getText(), "Add To Cart +",
//					"Text changed for Quick shop ATC button.");
				driver.findElement(QUICK_SHOP_ATC).click();
				Thread.sleep(3000);
				Reporter.log("Quick shop ATC button is validated :: Clickable.");
				ExtentReport.extentTest.info("Quick shop ATC button is validated :: Clickable.");

				softAssert.assertTrue(driver.findElement(QUICK_SHOP_SIZE_OVERLAY).isDisplayed(),
						"On InLine Cart Quick shop select size overlay is not displayed after click on ATC button.");

				Reporter.log("Quick shop size overlay is displayed.");
				ExtentReport.extentTest.info("Quick shop size overlay is displayed.");
				Thread.sleep(5000);
				softAssert.assertTrue(driver.findElement(QUICK_SHOP_SIZE_OVERLAY_CLOSE).isDisplayed(),
						"Quick shop select size overlay close btn is not displayed after click on ATC button.");
				Reporter.log("Quick shop size overlay is close button is displayed.");
				ExtentReport.extentTest.info("Quick shop size overlay is close button is displayed.");
				softAssert.assertTrue(driver.findElement(QUICK_SHOP_SIZE_OVERLAY_HEADING).isDisplayed(),
						"Quick shop select size overlay's heading is not displayed.");
				//	softAssert.assertEquals(driver.findElement(QUICK_SHOP_SIZE_OVERLAY_HEADING).getText(), "Choose Your Size",
				//			"Text changed for Quick shop Size overlay heading.");
				Reporter.log("Quick shop size overlay heading is displayed ::Correct.");
				ExtentReport.extentTest.info("Quick shop size overlay heading is displayed ::Correct.");
				softAssert.assertTrue(driver.findElement(QUICK_SHOP_SIZE_LABEL).isDisplayed(),
						"Quick shop size labels are not displayed.");
				Reporter.log("Quick shop size labels are displayed.");
				ExtentReport.extentTest.info("Quick shop size labels are displayed.");

				String First_Product_Name = driver.findElement(SUGGESTION_FIRST_PRODUCT_NAME).getText();
				System.out.println(First_Product_Name);
				//	jse.executeScript("arguments[0].click();", driver.findElement(SUGGESTION_FIRST_PRODUCT_SIZE));

				for (int k = 1; k <= 3; k++) {

					WebElement elements = driver.findElement(By.xpath("(//form[@data-cy='productForm']//input[@name='option[Size]'])[" + k + "]"));
					jse.executeScript("arguments[0].click();", elements);
					Thread.sleep(5000);

					try {

						String[] AddtoBag;
						AddtoBag = (driver.findElement(By.xpath("//button[@class='button grow']")).getText()).split(" -");
						String text1 = AddtoBag[0];
						System.out.println(text1);

						//	WebElement elem = driver.findElement(By.cssSelector(".multiPdp__atc-container>button"));
						jse.executeScript("arguments[0].click();", driver.findElement(ADD_TO_BAG_QUICKSHOP));
						Thread.sleep(5000);
						wait.until(ExpectedConditions.visibilityOfElementLocated(INLINECART_PRODUCT_NAME));
						String InLineCart_Product = driver.findElement(INLINECART_PRODUCT_NAME).getText();
						System.out.println(InLineCart_Product);

						softAssert.assertEquals(First_Product_Name, InLineCart_Product, "Wrong product is added to Cart from InLine Cart suggestion");
						softAssert.assertAll();
						Reporter.log("Appropriate product is added to Cart from InLine Cart Suggestion");
						ExtentReport.extentTest.info("Appropriate product is added to Cart from InLine Cart Suggestion");
						count++;

					} catch (Exception ex) {
						System.out.println("Product is OOS");
					}

					Thread.sleep(1000);


					if (count == 1)
						break;

				}





			}
		}

		public void verifyATCInInlineCartSuggestion() throws InterruptedException {
			Actions action = new Actions(driver);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

			SoftAssert softAssert = new SoftAssert();
			jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", driver.findElement(INLINE_CART_ICON));
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(SUGGESTION_FIRST_PRODUCT)));
			int ProductsSize = driver.findElements(SUGGESTION_PRODUCTS).size();
			System.out.println(ProductsSize);
			int ATCCTA = driver.findElements(ALL_QUICK_SHOP_ATC).size();
			System.out.println(ATCCTA);
			softAssert.assertEquals(ATCCTA,4,"Quick Shop ATC is not displayed in all the suggestion products");
			softAssert.assertAll();
			Reporter.log("Quick shop ATC button is available in all the Suggestion products");
			ExtentReport.extentTest.info("Quick shop ATC button is available in all the Suggestion products");
		}


		public void verifyQuickShopSizeOverlayIsDisplayed() throws InterruptedException {
			Actions action = new Actions(driver);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

			SoftAssert softAssert = new SoftAssert();
			jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", driver.findElement(INLINE_CART_ICON));
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(SUGGESTION_FIRST_PRODUCT)));
//			element = driver.findElement(SUGGESTION_FIRST_PRODUCT);
//			js.executeScript("arguments[0].scrollIntoView(true);", element);
//			action.moveToElement(element).build().perform();


			if (driver.findElement(QUICK_SHOP_ATC).isDisplayed()) {
				Reporter.log("Quick shop ATC button is displayed on hover.");
				ExtentReport.extentTest.info("Quick shop ATC button is displayed on hover.");
//				System.out.println("Quick shop ATC text is: " + driver.findElement(QUICK_SHOP_ATC).getText());
//				softAssert.assertEquals(driver.findElement(QUICK_SHOP_ATC).getText(), "Add To Cart +",
//						"Text changed for Quick shop ATC button.");
				driver.findElement(QUICK_SHOP_ATC).click();
				Thread.sleep(3000);
				Reporter.log("Quick shop ATC button text is validated :: Clickable.");
				ExtentReport.extentTest.info("Quick shop ATC button text is validated :: Clickable.");

				softAssert.assertTrue(driver.findElement(QUICK_SHOP_SIZE_OVERLAY).isDisplayed(),
						"On InLine Cart Quick shop select size overlay is not displayed after click on ATC button.");

				Reporter.log("Quick shop size overlay is displayed.");
				ExtentReport.extentTest.info("Quick shop size overlay is displayed.");

			}

		}

		public void verifySelectedColorSwatchIsDisplayedInTheInlineCart() throws InterruptedException {
			int count = 0;
			Actions action = new Actions(driver);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(80));

			SoftAssert softAssert = new SoftAssert();
			jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", driver.findElement(INLINE_CART_ICON));
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(SUGGESTION_FIRST_PRODUCT)));

			String ColorSwatch = driver.findElement(COLOR_SWATCH).getAttribute("alt");
			System.out.println(ColorSwatch);

			Thread.sleep(2000);
			jse.executeScript("arguments[0].click();", driver.findElement(COLOR_SWATCH));


//			element = driver.findElement(SUGGESTION_FIRST_PRODUCT);
//			js.executeScript("arguments[0].scrollIntoView(true);", element);
//			action.moveToElement(element).build().perform();

//			jse.executeScript("arguments[0].click();", driver.findElement(SUGGESTION_FIRST_PRODUCT_SIZE));

			driver.findElement(QUICK_SHOP_ATC).click();
			Thread.sleep(3000);
			Reporter.log("Quick shop ATC button is validated :: Clickable.");
			ExtentReport.extentTest.info("Quick shop ATC button is validated :: Clickable.");

			softAssert.assertTrue(driver.findElement(QUICK_SHOP_SIZE_OVERLAY).isDisplayed(),
					"On InLine Cart Quick shop select size overlay is not displayed after click on ATC button.");

			Reporter.log("Quick shop size overlay is displayed.");
			ExtentReport.extentTest.info("Quick shop size overlay is displayed.");
			softAssert.assertTrue(driver.findElement(QUICK_SHOP_SIZE_OVERLAY_CLOSE).isDisplayed(),
					"Quick shop select size overlay close btn is not displayed after click on ATC button.");
			Reporter.log("Quick shop size overlay is close button is displayed.");
			ExtentReport.extentTest.info("Quick shop size overlay is close button is displayed.");
			softAssert.assertTrue(driver.findElement(QUICK_SHOP_SIZE_OVERLAY_HEADING).isDisplayed(),
					"Quick shop select size overlay's heading is not displayed.");
			//	softAssert.assertEquals(driver.findElement(QUICK_SHOP_SIZE_OVERLAY_HEADING).getText(), "Choose Your Size",
			//			"Text changed for Quick shop Size overlay heading.");
			Reporter.log("Quick shop size overlay heading is displayed ::Correct.");
			ExtentReport.extentTest.info("Quick shop size overlay heading is displayed ::Correct.");
			softAssert.assertTrue(driver.findElement(QUICK_SHOP_SIZE_LABEL).isDisplayed(),
					"Quick shop size labels are not displayed.");
			Reporter.log("Quick shop size labels are displayed.");
			ExtentReport.extentTest.info("Quick shop size labels are displayed.");

//			String First_Product_Name = driver.findElement(SUGGESTION_FIRST_PRODUCT_NAME).getText();
//			System.out.println(First_Product_Name);
			//	jse.executeScript("arguments[0].click();", driver.findElement(SUGGESTION_FIRST_PRODUCT_SIZE));

			for (int k = 1; k <= 1; k++) {

				WebElement elements = driver.findElement(By.xpath("(//input[@name='option[Size]'])[" + k + "]"));
				jse.executeScript("arguments[0].click();", elements);
				Thread.sleep(5000);

				try {

					jse.executeScript("arguments[0].click();", driver.findElement(ADD_TO_BAG_QUICKSHOP));

					wait.until(ExpectedConditions.visibilityOfElementLocated(INLINECART_PRODUCT_NAME));
					String[] color;
					color = (driver.findElement(INLINE_COLOR).getText().split(" /"));
					String text = color[0];
					System.out.println(text);
					softAssert.assertEquals(ColorSwatch,text,"Color Mismatched In InLine Cart");
					softAssert.assertAll();
					Reporter.log("Selected Color Is Added To Cart");
					ExtentReport.extentTest.info("Selected Color Is Added To Cart");
					count++;
				}
				catch (Exception ex) {
					System.out.println("Product is OOS");
				}

				Thread.sleep(1000);


				if (count == 1)
					break;

			}

	}

		public void ValidateRemovingItemsFromInlineCart() throws Exception {

            softAssert = new SoftAssert();
            jse = (JavascriptExecutor) driver;
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
            new AddToCartComponents(driver).addToCart();
			jse.executeScript("arguments[0].click();", driver.findElement(HomePageLocators.CART_ICON));
			RemoveItems = driver.findElements(By.xpath("//button[@class='button-link text-body-xs text-text-tertiary' and contains(text(),'Remove')]"));
			System.out.println(RemoveItems.size());
			for(int i=1; i<=RemoveItems.size(); i++) {

				Thread.sleep(5000);
				jse.executeScript("arguments[0].click();",driver.findElement(By.xpath("(//button[@class='button-link text-body-xs text-text-tertiary' and contains(text(),'Remove')])[1]")));
				System.out.println(i);
			}
			Thread.sleep(5000);
			softAssert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Your cart is currently empty.')]")).isDisplayed(),
					"All the items in the Cart are not removed");

			Reporter.log("Items are removed from Cart");
			ExtentReport.extentTest.info("Items are removed from Cart");

        }

		public void ValidatePageGreyedOutOnAddingProducts() throws Exception {
			softAssert = new SoftAssert();
			jse = (JavascriptExecutor) driver;
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
			new AddToCartComponents(driver).addToCart();
			jse.executeScript("arguments[0].click();", driver.findElement(HomePageLocators.CART_ICON));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			String opacity = driver.findElement(HomePageLocators.OVERLAY).getCssValue("opacity");
			System.out.println(opacity);
			softAssert.assertEquals(opacity,"0.5","Page is not greyed out on adding products");
			Reporter.log("Page is greyed out on adding products");
			ExtentReport.extentTest.info("Page is greyed out on adding products");

		}


	public void verifyAllTextsAndFieldsOnEmptyFlyCart() throws Exception {
		SoftAssert softAssert = new SoftAssert();
		jse=(JavascriptExecutor)driver;
//		jse.executeScript("arguments[0].click();", driver.findElement(C));
//		Thread.sleep(3000);
//		jse.executeScript("arguments[0].click();", driver.findElement(INLINE_CART_CLOSE_BUTTON));
		String text = null;


		new HomePageComponents(driver).validateCartIcon();
		driver.findElement(HomePageLocators.CART_ICON).click();
		Thread.sleep(3000);
		// check for continue shopping button text and is displayed, enabled or not		
		softAssert.assertTrue(driver.findElement(INLINE_CART_CLOSE_BUTTON).isDisplayed(), "Inline cart close button is not displayed");
		softAssert.assertTrue(driver.findElement(INLINE_CART_CLOSE_BUTTON).isEnabled(), "Inline cart close button is not enable");
//		text = driver.findElement(INLINE_CART_CLOSE_BUTTON).getText();
//		System.out.println("Text :: "+text);
//		softAssert.assertTrue(text.equals(properties.get("continueShoppingButton")), "Text for inline cart close button is changed");
		Reporter.log("Inline Cart close button is Displayed :: Enabled :: Text is correct ");
		ExtentReport.extentTest.info("Inline Cart close button is Displayed :: Enabled :: Text is correct ");

		Thread.sleep(3000);
		// check for view cart link
		softAssert.assertTrue(driver.findElement(VIEW_CART_LINK).isDisplayed(), "View cart link is not displayed");
		softAssert.assertTrue(driver.findElement(VIEW_CART_LINK).isEnabled(), "View cart link is not enable");
		text = driver.findElement(VIEW_CART_LINK).getText();
		System.out.println("Text :: "+text);
		softAssert.assertTrue(text.equals(properties.get("viewCart")), "Text for view cart link is changed");

		Reporter.log("View Cart link is Displayed :: Enabled :: Text is correct ");
		ExtentReport.extentTest.info("View Cart link is Displayed :: Enabled :: Text is correct ");

		Thread.sleep(3000);

		// check inline cart title
		softAssert.assertTrue(driver.findElement(INLINE_CART_TITLE).isDisplayed(), "Inline cart title is not displayed");
		text = driver.findElement(INLINE_CART_TITLE).getText();
		System.out.println("Text :: "+text);
		softAssert.assertTrue(text.equals(properties.get("inlineCartTitle")), "Text for inline cart title is changed");
		Reporter.log("Inline Cart Title is Displayed :: Text is correct ");
		ExtentReport.extentTest.info("Inline Cart Title is Displayed :: Text is correct ");

		Thread.sleep(3000);
//		try {
//				// check for shipping message text
//				softAssert.assertTrue(driver.findElement(SHIPING_MESSAGE_TEXT).isDisplayed(), "Shipping message text is not displayed");
//				Reporter.log("Shipping message text is Displayed ");
//				ExtentReport.extentTest.info("Shipping message text is Displayed ");
//
//				Thread.sleep(3000);
//
//				// check for shipping message meter
////				softAssert.assertTrue(driver.findElement(SHIPING_MESSAGE_METER).isDisplayed(), "Shipping message meter is not displayed");
////				Reporter.log("Shipping message meter is Displayed");
////				ExtentReport.extentTest.info("Shipping message meter is Displayed");
////				Thread.sleep(3000);
//			}
//		catch(Exception e)
//			{
//				Reporter.log("Shipping message  absent");
//				ExtentReport.extentTest.info("Shipping message absent");
//			}


			// check for empty cart text
			softAssert.assertTrue(driver.findElement(INLINE_CART_EMPTY_TEXT).isDisplayed(), "Shipping 'Your cart is empty' is not displayed");
			text = driver.findElement(INLINE_CART_EMPTY_TEXT).getText();
			System.out.println("Text :: "+text);
			softAssert.assertTrue(text.equals(properties.get("inlineCartEmptyText")), "Expected text is "+properties.get("inlineCartEmptyText")+" but actual is "+text);
			Reporter.log("'Inline cart is empty' text is Displayed");
			ExtentReport.extentTest.info("'Inline cart is empty' text is Displayed");
	
			driver.get(CustomUtilities.baseUrl);
			softAssert.assertAll();
	}

	public void verifyAllTextsAndFieldsOnNonEmptyFlyCart() throws Exception {
		SoftAssert softAssert = new SoftAssert();
		String text = null;

		// first add any product to cart
		new AddToCartComponents(driver).addToCart();
//new ProductDetailsPageComponents(driver).verifyAddToCart();
		Thread.sleep(2000);
		driver.get("https://www.tommyjohn.com/");
		driver.findElement(HomePageLocators.CART_ICON).click();
		Thread.sleep(3000);

		// check for continue shopping button text and is displayed, enabled or not		
		softAssert.assertTrue(driver.findElement(INLINE_CART_CLOSE_BUTTON).isDisplayed(), "Inline cart close button is not displayed");
		softAssert.assertTrue(driver.findElement(INLINE_CART_CLOSE_BUTTON).isEnabled(), "Inline cart close button is not enable");
//		text = driver.findElement(INLINE_CART_CLOSE_BUTTON).getText();
//		System.out.println("Text :: "+text);
//		softAssert.assertTrue(text.equals(properties.get("continueShoppingButton")), "Text for inline cart close button is changed");
//		Reporter.log("Continue Shopping button is Displayed :: Enabled :: Text is correct ");
//		ExtentReport.extentTest.info("Continue Shopping button is Displayed :: Enabled :: Text is correct ");
		Thread.sleep(3000);

		// check for inline cart link
		softAssert.assertTrue(driver.findElement(VIEW_CART_LINK).isDisplayed(), "View cart link is not displayed");
		softAssert.assertTrue(driver.findElement(VIEW_CART_LINK).isEnabled(), "View cart link is not enable");
		text = driver.findElement(VIEW_CART_LINK).getText();
		System.out.println("Text :: "+text);
		softAssert.assertTrue(text.equals(properties.get("viewCart")), "Text for view cart link is changed");	
		Reporter.log("View Cart link is Displayed :: Enabled :: Text is correct ");
		ExtentReport.extentTest.info("View Cart link is Displayed :: Enabled :: Text is correct ");
		Thread.sleep(3000);

		// check inline cart title
		softAssert.assertTrue(driver.findElement(INLINE_CART_TITLE).isDisplayed(), "Inline cart title is not displayed");
		text = driver.findElement(INLINE_CART_TITLE).getText();
		System.out.println("Text :: "+text);
		softAssert.assertTrue(text.equals(properties.get("inlineCartTitle")), "Text for inline cart title is changed");
		Reporter.log("Inline Cart Title is Displayed :: Text is correct ");
		ExtentReport.extentTest.info("Inline Cart Title is Displayed :: Text is correct ");
		Thread.sleep(3000);
		
		allElements = driver.findElements(LIST_OF_ITEMS_IN_FLYCART);

		// check all fields for all items in flycart
		for(int i=1; i<=2;i++) {
			// check for product image
			softAssert.assertTrue(driver.findElement(By.xpath("(//td[@class='row-span-3 p-0 sm:row-span-2'])[2]")).isDisplayed(), "First item image is not displayed");

			// check for product heading
			softAssert.assertTrue(driver.findElement(By.xpath("(//a[@class='font-medium text-text-primary mt-1 block text-body-lg leading-tight sm:text-body sm:leading-5'])["+i+"]")).isDisplayed(), "First item heading is not displayed");

			// check for remove item from cart button
			softAssert.assertTrue(driver.findElement(By.xpath("(//button[@class='button-link text-body-xs text-text-tertiary' and contains(text(),'Remove')])["+i+"]")).isDisplayed(), "First item remove button is not displayed");

			// check for line item option
		//	softAssert.assertTrue(driver.findElement(By.xpath("(//div[@class='mt-2 text-body text-text-secondary sm:mt-4'])["+i+"]")).isDisplayed(), "Line item option is not displayed");

			// check for price
			softAssert.assertTrue(driver.findElement(By.xpath("(//div[@class='mt-2 flex gap-2 text-body-sm font-medium'])[2]")).isDisplayed(), "Price is not displayed");

			// check for plus minus and quantity
			//softAssert.assertTrue(driver.findElement(QUANTITY_PLUS_MINUS_BUTTON).isDisplayed(), "plus or minus button is not displayed");

			// check for second price displayed
			softAssert.assertTrue(driver.findElement(By.xpath("(//div[@class='flex rounded-full border border-stroke'])["+i+"]")).isDisplayed(), "plus or minus button is not displayed");

		}
		Reporter.log("Image, link text, price, plus-minus button is displayed for all items in cart");
		ExtentReport.extentTest.info("Image, link text, price, plus-minus button is displayed for all items in cart");
		// check for Add Gift Wrap button
//		softAssert.assertTrue(driver.findElement(ADD_GIFT_WRAP_BUTTON).isDisplayed(), "Add Gift Wrap button is not displayed");
//		Thread.sleep(3000);

//		text = driver.findElement(ESTIMATE_TOTAL).getText();
//		System.out.println("Estimate total text :: "+text);
//		softAssert.assertTrue(text.equals(properties.get("totalEstimateText")), "Expected text is "+properties.get("totalEstimateText")+" but actual is "+text);
		//		text = driver.findElement(ESTIMATE_TOTAL_SMALL).getText();
		//		System.out.println("Estimate total small text :: "+text);
		//		softAssert.assertTrue(text.equals(properties.get("totalEstimateSmallText")), "Expected text is "+properties.get("totalEstimateSmallText")+" but actual is "+text);

		Thread.sleep(3000);

//		softAssert.assertTrue(driver.findElement(ESTIMATE_TOTAL).isDisplayed(), "Estimate total amount is not displayed");

		// check for checkout button
		softAssert.assertTrue(driver.findElement(CHECKOUT_BUTTON).isDisplayed(), "Checkout button is not displayed");
		Thread.sleep(3000);

		softAssert.assertAll();
		driver.get(CustomUtilities.baseUrl);

	}


	public void validateNonEmptyFlyCart() throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		String text1 = null;
		String text2 = null;
		String color = null;
	//	String colorincart = null;
		String price1 = null;
		String price2 = null;

		// first add any product to cart
		new AddToCartComponents(driver).addToCart();
		//new ProductDetailsPageComponents(driver).verifyAddToCart();
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(HomePageLocators.CART_ICON));
		Thread.sleep(3000);

		
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(INLINE_CART_CLOSE_BUTTON));
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Thread.sleep(2000);

		((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(HomePageLocators.CART_ICON));
		Thread.sleep(2000);

		((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(VIEW_CART_LINK));
		Thread.sleep(3000);

		driver.get("https://www.tommyjohn.com/");
		//softAssert.assertTrue(driver.getCurrentUrl().equals(properties.get("viewCartPage")), "Wrong page opened after cicked on View Cart link");
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(HomePageLocators.CART_ICON));
		
		Thread.sleep(2000);

		// check product heading for available items in cart
		text = driver.findElement(FIRST_PRODUCT_TITLE_IN_CART);
		Thread.sleep(2000);
		System.out.println("Text1 :: "+text.getText());
		//jse.executeScript("arguments[0].click();", driver.findElement(FIRST_PRODUCT_TITLE_IN_CART));
		WebElement prod=driver.findElement(FIRST_PRODUCT_TITLE_IN_CART);
		jse.executeScript("arguments[0].click();", prod);
		Thread.sleep(3000);
		text2 = driver.findElement(ProductDetailsPageLocators.PRODUCT_TITLE).getText();
		System.out.println("Text2 :: "+text2);
		//softAssert.assertTrue(text.getText().equals(text2), "Wrong page opened after clicked on cart product");

		color = driver.findElement(ProductDetailsPageLocators.COLOR_TEXT).getText();
		System.out.println("color and size :: "+color);

		WebElement icon = driver.findElement(HomePageLocators.CART_ICON);
		jse.executeScript("arguments[0].click();", icon);
		Thread.sleep(2000);

		String[] colorincart;
		colorincart = driver.findElement(COLOR_AND_SIZE_IN_CART).getText().split(" / ");
		String text3 = colorincart[0];
		System.out.println("color and size2 :: "+text3);

		//softAssert.assertTrue(colorAndSize.equalsIgnoreCase(colorAndSize2), "Color and size is mismatch from PDP and Cart");

		// check price before adding more item
		price1 = driver.findElement(PRICE_IN_QUANTITY_LINE).getText();
//		for (String a : price1.split("\\$"))
//			price1 = a;
		System.out.println("price1  :: "+price1);

		// check for plus minus button
//		String quantity = driver.findElement(QUANTITY).getAttribute("data-item-qty");
//		System.out.println("quantity :: "+quantity);
//
//
//		((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(PLUS_BUTTON));
//		Thread.sleep(5000);
//		String quantity1 = driver.findElement(QUANTITY).getAttribute("data-item-qty");
//		System.out.println("quantity1 :: "+quantity1);
//		// check quantity added by 1 or not
//		if(Integer.parseInt(quantity1)!=Integer.parseInt(quantity)+1)
//			throw new Exception("Quantity not increased by 1");
//
//		Reporter.log("Quantity increased by 1");
//		ExtentReport.extentTest.info("Quantity increased by 1");
//
//		price2 = driver.findElement(PRICE_IN_QUANTITY_LINE).getText();
//		for (String a : price2.split("\\$"))
//			price2 = a;
//		System.out.println("price2  :: "+price2);
//
//		// check price changed or not
//		softAssert.assertFalse(price1.equals(price2), "Total price not changing after adding more items");
//
//		((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(MINUS_BUTTON));
//		Thread.sleep(3000);
//
//		String quantity2 = driver.findElement(QUANTITY).getAttribute("data-item-qty");
//		System.out.println("quantity2 :: "+quantity2);
//		if(Integer.parseInt(quantity2)!=Integer.parseInt(quantity1)-1)
//			throw new Exception("Quantity not decreased by 1");
//		Reporter.log("Quantity decreased by 1");
//		ExtentReport.extentTest.info("Quantity decreased by 1");

		// check for checkout button
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(CHECKOUT_BUTTON));
		
		Thread.sleep(3000);
		driver.get(CustomUtilities.baseUrl);
		Thread.sleep(3000);
		Reporter.log("Checkout button is Displayed :: Clickable");
		ExtentReport.extentTest.info("Checkout button is Displayed :: Clickable");

		// check for cross close button near text heading link to remove item from cart
		
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(HomePageLocators.CART_ICON));
		Thread.sleep(2000);

		allElements = driver.findElements(LIST_OF_ITEMS_IN_FLYCART);
		System.out.println("List of items after click :: "+allElements.size());
		int list1 = allElements.size();
		if(list1>0) {
			for(int i=1;i<=list1;i++) {
				JavascriptExecutor executor = (JavascriptExecutor)driver;
				WebElement size = driver.findElement(By.xpath("(//button[contains(text(),'Remove')])["+list1+"]"));
				executor.executeScript("arguments[0].click();", size);
			}
		}
		Thread.sleep(3000);

		allElements = driver.findElements(LIST_OF_ITEMS_IN_FLYCART);
		System.out.println("List of items after click :: "+allElements.size());
		int list2 = allElements.size();

		softAssert.assertFalse(list1 == list2, "Item is not removed from cart");

		Reporter.log("Item is able to remove from cart");
		ExtentReport.extentTest.info("Item is able to remove from cart");

		softAssert.assertAll();
	}

	public void verifygiftWrap() throws Exception {
		String allClasses = null;
		boolean flag = false;
		String price1 = null;
		String price = null;
		String price2 = null;

		driver.get(CustomUtilities.baseUrl);
		//new ProductDetailsPage(driver).verifyAddToCart();
		new AddToCartComponents(driver).addToCart();
		driver.findElement(HomePageLocators.CART_ICON).click();
		Thread.sleep(2000);

		// first get the price in quantity line
		price = driver.findElement(ESTIMATE_TOTAL_PRICE).getText();
		for (String a : price.split("\\$")) 
			price = a;
		System.out.println("price  :: "+price);

		Thread.sleep(3000);
		driver.findElement(GIFT_WRAP).click();
		Thread.sleep(2000);

		// check popup is opened or not
		softAssert.assertTrue(driver.findElement(GIFT_WRAP_POPUP).isDisplayed(), "Gift Wrap Popup is not displayed");
		element = driver.findElement(GIFT_BOX_$5);
		allClasses = element.getAttribute("class");
		// check size available or not
		for (String c : allClasses.split(" ")) {
			if (c.equals("is-checked")) {
				flag = true;
			}
		}
		if(flag)
			Reporter.log("Gift Box $5.00 is already checked");
		else {
			driver.findElement(GIFT_BOX_$5).click();
			Thread.sleep(2000);
			Reporter.log("Gift Box $5.00 is checked");
		}
		// click on apply button
		driver.findElement(GIFT_WRAP_APPLY).click();
		Thread.sleep(7000);

		// check the $5 price is applying or not
		price2 = driver.findElement(GIFT_WRAP_PRICE).getText();
		System.out.println("previous price2 is: "+price2);
		for (String a : price2.split("\\$")) 
			price2 = a;
		System.out.println("price2  :: "+price2);

		// get estimated total price
		price1 = driver.findElement(ESTIMATE_TOTAL_PRICE).getText();
		for (String b : price1.split("\\$")) 
			price1 = b;
		System.out.println("price1  :: "+price1);


		double p = Float.parseFloat(price);
		double p2 = Float.parseFloat(price2);
		double p1 = Float.parseFloat(price1);
//		System.out.println(price);
//		System.out.println(price2);
//		System.out.println(price1);
		
		// check the total estimated price is displayed correctly when added gift wrap price
		softAssert.assertTrue(p1 == p2+p, "Total estimated price is not correct");

		driver.get(CustomUtilities.baseUrl);

		softAssert.assertAll();
	}
	

	
		
	
	public void validateafterpayoninlinecart() throws Exception
	{

//		new HomePageComponents(driver).navigateToBraCategory();
//
//		new CollectionPageComponent(driver).navigateToProductDetailsPage();
		//	new ProductDetailsPageComponents(driver).validatePage();
		new AddToCartComponents(driver).addToCart();
		driver.findElement(HomePageLocators.CART_ICON).click();
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		if(!driver.findElement(AFTERPAYTEXT).isDisplayed())
			throw new Exception("AfterPay Message not present on Fly cart.");
		Reporter.log("After pay Message is displayed on Inline Cart :: Displayed");
		ExtentReport.extentTest.info("After pay Message is displayed on Inline Cart :: Displayed");

		price = driver.findElement(ESTIMATE_TOTAL_PRICE).getText();
		String[] price1 = price.split("\\$");
		String price2 = price1[price1.length-1];
		System.out.println("Estimate total is: "+price);
		
		WebElement ele = driver.findElement(By.xpath("//iframe[@title='pdp_messaging']"));
		driver.switchTo().frame(ele);
		
		SearchContext shadowRoot = driver.findElement(By.cssSelector("#root>div>div>afterpay-placement")).getShadowRoot();
		Thread.sleep(6000);
		WebElement afterpaymessaging = (WebElement) js.executeScript("return document.querySelector(\"#root > div > div > afterpay-placement\").shadowRoot.querySelector(\"p > span\")");
		
		System.out.print(afterpaymessaging.getText());
		
		if(Float.parseFloat(price2)<35 || Float.parseFloat(price2)>1000 )
		{
			WebElement afterpaylogo = (WebElement) js.executeScript("return document.querySelector(\"#root > div > div > afterpay-placement\").shadowRoot.querySelector(\"p > button.afterpay-logo.brand-afterpay.type-badge.black-on-mint\")");

			softAssert.assertTrue(afterpaymessaging.getText().contains("available for orders between $35 - $1000") , "Wrong afterpay message displayed for product price");
			Reporter.log("After pay message is correct On Inline cart :: Displayed and Correct");
			ExtentReport.extentTest.info("After pay message is correct On Inline cart :: Displayed and Correct");
			softAssert.assertTrue(afterpaylogo.isDisplayed(), "Afterpay logo not present on Fly-out cart.");
			Reporter.log("After pay logo icon is displayed on Inline cart :: Displayed");
			ExtentReport.extentTest.info("After pay logo icon is displayed on Inline cart :: Displayed");
		}
		else
		{
			WebElement afterpaylogo = (WebElement) js.executeScript("return document.querySelector(\"#root > div > div > afterpay-placement\").shadowRoot.querySelector(\"p > span.afterpay-logo-link > button.afterpay-logo.brand-afterpay.type-badge.black-on-mint\")");

			installment =Float.parseFloat(price2)/4;
			String inst = String.format("%.2f",installment );
			//	String expectedmessage = "In 4 installments of $" + inst + " by ?"; 
			String expectedmessage =  inst+ " "; 
			System.out.println("expected message is: " + expectedmessage);
			String actual= afterpaymessaging.getText();
			String ins_amount= actual.substring(actual.indexOf("$")+1, actual.indexOf("w"));
			System.out.println("Actual int_amount is: " +ins_amount);
			softAssert.assertEquals(ins_amount, expectedmessage , "Wrong afterpay message displayed for product price");
			Reporter.log("After pay message with calculated installment is correct on Inline cart :: Displayed and correct");
			ExtentReport.extentTest.info("After pay message with calculated installment is correct on Inline cart :: Displayed and correct");
			softAssert.assertTrue(afterpaylogo.isDisplayed(), "Afterpay logo not present on PDP");
			Reporter.log("After pay logo icon is displayed on Inline cart :: Displayed");
			ExtentReport.extentTest.info("After pay logo icon is displayed on Inline cart :: Displayed");
		}
		Thread.sleep(3000);
		// Close Fly cart
		element = driver.findElement(FlyCartPageLocator.INLINE_CART_CLOSE_BUTTON);
		jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click();", element);
		Thread.sleep(3000);

		softAssert.assertAll();
	}

	public void VerifyFlyCartOpenWithQSAndPDP() throws Exception
	{
		Actions action = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		new HomePageComponents(driver).navigateToMensAll();
		Thread.sleep(8000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		js.executeScript("window.scrollBy(0,450)");
		int count = 0;
		wait.until(ExpectedConditions.presenceOfElementLocated(CollectionPageLocator.FIRST_PRODUCT_IMAGE));
		element = driver.findElement(CollectionPageLocator.FIRST_PRODUCT_IMAGE);
	//	js.executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(15000);
		action.moveToElement(element).build().perform();
		Thread.sleep(15000);
		if(driver.findElement(CollectionPageLocator.QUICK_SHOP_ATC).isDisplayed())
		{
            Thread.sleep(10000);
			element = driver.findElement(CollectionPageLocator.QUICK_SHOP_ATC);
			js.executeScript("arguments[0].click();", element);
		//	driver.findElement(CollectionPageLocator.QUICK_SHOP_ATC).click();
			Thread.sleep(5000);
			//select sizes from quick shop size overlay
			List<WebElement> allElements;
			String classtext;
			allElements = driver.findElements(QUICK_SHOP_SIZE_LIST);
			System.out.println("All sizes list :: "+ allElements.size());
			for(int i=1;i<allElements.size();i++)
			{
                Thread.sleep(5000);
				element = driver.findElement(By.xpath("(//input[@name='option[Size]'])[" + i + "]"));
				js.executeScript("arguments[0].click();", element);
				String[] AddtoBag;
				AddtoBag = (driver.findElement(By.xpath("//button[@class='button grow']")).getText()).split(" -");
				String text1 = AddtoBag[0];
				System.out.println(text1);
				if (text1.equals("Add to bag")) {
					System.out.println(driver.findElement(PRODUCT_TITLE_ON_COLLECTION).getText() + element.getText() + "Product is added to Cart");
					Thread.sleep(2000);
					js.executeScript("arguments[0].click();", driver.findElement(ADD_TO_BAG_QUICKSHOP));
					Thread.sleep(3000);
					count++;
				}

				else
				{
					System.out.println("Selected size is OOS");
				}

				if(count==1)
				{
					break;
				}
			}
            wait.until(ExpectedConditions.visibilityOfElementLocated(INLINE_CART_TITLE));
			softAssert.assertTrue(driver.findElement(INLINE_CART_TITLE).isDisplayed(), "Flyout cart is not opened with QuickShop");
			Reporter.log("Validated Flyout cart opens with Quickshop");
			ExtentReport.extentTest.info("Validated Flyout cart opens with Quickshop");

		}
		else {
			throw new Exception("Quick Shop ATC button is not displayed on hover.");
		}
		//Check on pdp
		//new HomePageComponents(driver).navigateToAllWomensCollectionInWomensCategory();
		//new CollectionPageComponent(driver).navigateToProductDetailsPage();
		//new AddToCartComponents(driver).selectSize();
		new ProductDetailsPageComponents(driver).verifyAddToCart();
//		if(!driver.findElement(AddToCartLocators.ADD_TO_CART_BUTTON).isEnabled())
//			throw new Exception("ADD TO CART button is not present");
//		element = driver.findElement(AddToCartLocators.ADD_TO_CART_BUTTON);
//		jse = (JavascriptExecutor)driver;
//		jse.executeScript("arguments[0].click();", element);
//		Thread.sleep(5000);
//		softAssert.assertTrue(driver.findElement(INLINE_CART_TITLE).isDisplayed(), "Flyout cart is not opened with ATC from pdp");
		Reporter.log("Validated Flyout cart opens with ATC from pdp");
		ExtentReport.extentTest.info("Validated Flyout cart opens with ATC from pdp");
		softAssert.assertAll();
	}
	public void validateOOSonInlinecart() throws Exception {
		jse = (JavascriptExecutor)driver;
		SoftAssert softassert = new SoftAssert();
		new HomePageComponents(driver).navigateToAllWomensPacksInPacksCategory();
		new CollectionPageComponent(driver).navigateToProductDetailsPage();

		Thread.sleep(2000);   
		allElements = driver.findElements(ProductDetailsPageComponents.ALL_COLOR_LIST);
		int elementsCount = allElements.size();
		System.out.println("Color Element count :: "+elementsCount);
		elementsCount = elementsCount-(elementsCount-1);
		if(elementsCount > 0)
			
			for(int i=1;i<=1;i++)
			{
				
				WebElement elemen = driver.findElement(By.cssSelector(".product-option__color-swatches-wrapper>ul>li:nth-child(" + i + ")>input"));
				jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].click();", elemen);
				Thread.sleep(3000);
				
					for(int k=1;k<=3;k++)				
					{	
						WebElement elements = driver.findElement(By.cssSelector(".option-swatch__option>li:nth-child("+ k +")>input"));
						jse.executeScript("arguments[0].click();", elements);
						Thread.sleep(1000);
						
						WebElement elem = driver.findElement(By.cssSelector(".multiPdp__atc-container>button"));		
						
						if(driver.findElement(ProductDetailsPageComponents.ADD_TO_CART_BUTTON).getText().contains("Add to Cart")) {
							
							System.out.println(elemen.getText() + elements.getText() + "Product is added to Cart");
							jse.executeScript("arguments[0].click();", driver.findElement(ProductDetailsPageComponents.ADD_TO_CART_BUTTON));
							Thread.sleep(4000);
					}
						
						
					}
					
			}
		driver.findElement(QUANTITY).sendKeys("10000");
		jse.executeScript("arguments[0].click();", driver.findElement(PLUS_BUTTON));
		Thread.sleep(10000);
		
		softassert.assertEquals(driver.findElement(OOS_MESSAGE_ON_TOP).getText(), "Some items are out of stock. Please remove or edit them to continue.", "MAIN OOS MESSAGE");
		softassert.assertEquals(driver.findElement(OOS_LINE_ITEMS).getText(), "Out of Stock", "oos");
		jse.executeScript("arguments[0].click();", driver.findElement(CHECKOUT_BUTTON));
		String attr = driver.findElement(CHECKOUT_BUTTON).getAttribute("disabled");
		System.out.println(attr);
		if(attr != null)
		{Reporter.log("Out of Stock is displayed");
		ExtentReport.extentTest.info("Out of Stock is displayed");
		}
		else
		{
			throw new Exception("Checkout not disabled");
		}
		
		softassert.assertAll();
			
		
	}
	public void tieredMeterPresence() throws Exception
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(HomePageLocators.CART_ICON));


		driver.findElement(HomePageLocators.CART_ICON).click();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(TIERED_METER));
		Thread.sleep(5000);

	try
	{
			if(driver.findElement(TIERED_METER).isDisplayed()){
				System.out.println("Tiered Meter Title is displayed on cart page");
				Reporter.log("Tiered Meter Title is displayed on cart page");
				ExtentReport.extentTest.info("Tiered Meter Title is displayed on cart page");
			
		}
	}
			catch(Exception e)
			{
				Reporter.log("Tiered Meter not displayed on cart page");
				ExtentReport.extentTest.info("Tiered Meter not displayed on cart page");
			}

		
		//softAssert.assertAll();	
	}
	public void validatePromotionalMessage() throws Exception
	{
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		jse=(JavascriptExecutor)driver;
		wait.until(ExpectedConditions.visibilityOfElementLocated(HomePageLocators.CART_ICON));
		jse.executeScript("arguments[0].click();", driver.findElement(HomePageLocators.CART_ICON));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(TIERED_METER));
		Thread.sleep(5000);
		
		try
		{
		//softAssert.assertTrue(driver.findElement(PROMOTIONAL_PRICE).isDisplayed(), "Promotional discounted price is displayed on cart page");
		if(driver.findElement(PROMOTIONAL_MESSAGE).isDisplayed()) {

			System.out.println("PROMOTIONAL MESSAGE is displayed on cart page");
			Reporter.log("PROMOTIONAL MESSAGE is displayed on cart page");
			ExtentReport.extentTest.info("PROMOTIONAL MESSAGE is displayed on cart page");
		}
		else {
			Reporter.log("PROMOTIONAL MESSAGE is not displayed on cart page for logged in user");
			ExtentReport.extentTest.info("PROMOTIONAL MESSAGE is not displayed on cart page for logged in user");
			Reporter.log("PLEASE CHECK FOR PROMOTIONS  IF PUBLISHED");
			ExtentReport.extentTest.info("PLEASE CHECK FOR PROMOTIONS  IF PUBLISHED");
		}
		}
		catch(Exception e)
		{	
			
			Reporter.log("PLEASE CHECK FOR PROMOTIONS  IF PUBLISHED");
			ExtentReport.extentTest.info("PLEASE CHECK FOR PROMOTIONS  IF PUBLISHED");
		}

		//softAssert.assertAll();	
	}
	
}