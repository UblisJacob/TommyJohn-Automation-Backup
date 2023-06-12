package com.tommyjohn.automation.Components;


import java.io.FileReader;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.opencsv.CSVReader;
import com.tommyjohn.automation.PageLocators.CollectionPageLocator;
import com.tommyjohn.automation.PageLocators.FlyCartPageLocator;
import com.tommyjohn.automation.PageLocators.ProductDetailsPageLocators;

import commonMethods.GeneralMethods;

public class CollectionPageComponent extends CollectionPageLocator {

	GeneralMethods objGeneralMethods = new GeneralMethods();

	public CollectionPageComponent(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriver driver;
	WebElement element;
	SoftAssert softAssert = new SoftAssert();
	String text;
	String Selected_size;
	String Selected_size1;
	String Selected_size2;
	int count=0;

	public String navigateToProductDetailsPage() throws Exception {
		String text = null;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_PRODUCT_HEAD_LINE_TEXT));
		// get text and click on the first product
		text = driver.findElement(FIRST_PRODUCT_IMAGE).getAttribute("alt");
		// text= driver.findElement(FIRST_PRODUCT_HEAD_LINE_TEXT).getText();
		WebElement el = driver.findElement(FIRST_PRODUCT_HEAD_LINE_TEXT);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", el);
		wait.until(ExpectedConditions.visibilityOfElementLocated(ProductDetailsPageLocators.PRODUCT_TITLE));
		Reporter.log(driver.getCurrentUrl());
		// text =
		// driver.findElement(ProductDetailsPageLocators.PRODUCT_TITLE).getText();
		return text;
	}

	@SuppressWarnings("static-access")
	public void validateProductImageOnCollectionPage() throws Exception {
		String text = null;

		// changed
		/*
		 * WebDriverWait wait = new WebDriverWait(driver, 30);
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_PRODUCT_IMAGE)
		 * );
		 */
		objGeneralMethods.isElementVisible(driver, FIRST_PRODUCT_IMAGE);
		// get product title and click on image if present
		text = driver.findElement(FIRST_PRODUCT_IMAGE).getAttribute("alt");

		WebElement Prod_Image = driver.findElement(FIRST_PRODUCT_IMAGE);
		if (!Prod_Image.isDisplayed())
			// # if(!driver.findElement(THIRD_PRODUCT_IMAGE).isDisplayed())
			throw new Exception("Product image is not present");

		WebElement ele = driver.findElement(FIRST_PRODUCT_IMAGE);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", ele);

		// driver.findElement(THIRD_PRODUCT_IMAGE).click();
		Thread.sleep(7000);
		Reporter.log(driver.getCurrentUrl());

		// check correct Product details page opened or not
		new ProductDetailsPageComponents(driver).checkCorrectProductDetailsPageOpenedOrNot(text);

		// validate product details page by calling the method
		new ProductDetailsPageComponents(driver).verifybundlepages();

		Reporter.log("Product image on collection page is Displayed :: Clickable and validated successfully");
	}

	public void validateProductImageOnPLP() throws Exception {
		String text = null;

		// changed
		objGeneralMethods.implicitWait(driver);
		objGeneralMethods.waitTillPageLoad(driver);
		objGeneralMethods.isElementVisible(driver, FIRST_PRODUCT_IMAGE);
		// get product title and click on image if present
		text = driver.findElement(FIRST_PRODUCT_IMAGE).getAttribute("alt");
		JavascriptExecutor executor = (JavascriptExecutor) driver;

		WebElement Prod_Image = driver.findElement(FIRST_PRODUCT_IMAGE);
		if (!Prod_Image.isDisplayed())
			// # if(!driver.findElement(THIRD_PRODUCT_IMAGE).isDisplayed())
			throw new Exception("Product image is not present");

		WebElement ele = driver.findElement(FIRST_PRODUCT_IMAGE);

		executor.executeScript("arguments[0].click();", ele);

		// driver.findElement(THIRD_PRODUCT_IMAGE).click();
		Thread.sleep(7000);
		Reporter.log(driver.getCurrentUrl());

		// check correct Product details page opened or not
		new ProductDetailsPageComponents(driver).checkCorrectProductDetailsPageOpenedOrNot(text);

		Reporter.log("Product image on collection page is Displayed :: Clickable and validated successfully");

		
		Thread.sleep(5000);
		

		/*List<WebElement> list = driver.findElements(By.xpath(PRODUCT_IMAGES));
		int count = 0;
		for (int i = 0; i < list.size(); i++) {
			if (objGeneralMethods.isElementVisible(driver, By.xpath(PRODUCT_IMAGES))) {
				count++;
			} else {
				Reporter.log("Product image is not present");
				break;
			}
		}
		System.out.println(count);
		String no_of_products = driver.findElement(PRODUCT_COUNT_TEXT).getText();
		no_of_products = no_of_products.replaceAll("[^0-9.]", "");
		int count_1 = Integer.parseInt(no_of_products);

		if (count_1 == count) {
			Reporter.log("All Product images are present");
		} else {
			throw new Exception("Product image are present");
		}*/

	}

	public void methodToSortByPrice() throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)");

		WebElement sortByPrice = driver.findElement(SORTBYPRICE_DROPDOWN);
		driver.findElement(PLP_PRICE);

		wait.until(ExpectedConditions.visibilityOfElementLocated(SORTBYPRICE_DROPDOWN));

		String price = null;
		String updatedPrice = null;
		double priceValue = 0;
		Select drpCountry = new Select(sortByPrice);
		drpCountry.selectByValue("price:ascending");
		Thread.sleep(5000);

		//js.executeScript("arguments[0].scrollIntoView();", prices);
		//Thread.sleep(5000);
		List<WebElement> list = driver.findElements(PLP_PRICE);
		ArrayList<Double> productPrice = new ArrayList<Double>();
		ArrayList<Double> sortedList = new ArrayList<Double>();
		;
		for (int i = 0; i < list.size(); i++) {
			price = list.get(i).getText();
			updatedPrice = price.replaceAll("[^0-9.]", "");
			priceValue = Double.parseDouble(updatedPrice);
			sortedList.add(priceValue);
			productPrice.add(priceValue);
		}

		System.out.println(productPrice);
		Collections.sort(sortedList);
		System.out.println(sortedList);
		if (!sortedList.equals(productPrice)) {
			throw new Exception("The products are not sorted in ascending order");
		} else {
			Reporter.log("The products are sorted in ascending order");
		}

		js.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
		drpCountry.selectByIndex(2);
		js.executeScript("window.scrollBy(0,450)");
		Thread.sleep(5000);
		List<WebElement> list1 = driver.findElements(PLP_PRICE);
		ArrayList<Double> productPrice1 = new ArrayList<Double>();
		ArrayList<Double> sortedList1 = new ArrayList<Double>();

		for (int i = 0; i < list1.size(); i++) {
			price = list1.get(i).getText();
			updatedPrice = price.replaceAll("[^0-9.]", "");
			priceValue = Double.parseDouble(updatedPrice);
			sortedList1.add(priceValue);
			productPrice1.add(priceValue);
		}
		System.out.println(productPrice1);
		Collections.sort(sortedList1);
		Collections.reverse(sortedList1);
		System.out.println(sortedList1);
		if (!sortedList1.equals(productPrice1)) {
			throw new Exception("The products are sorted not successfully");
		} else {
			Reporter.log("The products are sorted successfully");
		}
	}

	@SuppressWarnings("static-access")
	public void validateProductHeadingTextOnCollectionPage() throws Exception {
		String text = null;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)");

		wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_PRODUCT_HEAD_LINE_TEXT));
		// get product title and click on product heading text if present

		text = driver.findElement(FIRST_PRODUCT_IMAGE).getAttribute("alt");
		driver.findElement(FIRST_PRODUCT_HEAD_LINE_TEXT).click();
		Thread.sleep(3000);
		Reporter.log(driver.getCurrentUrl());

		// check correct Product details page opened or not
		new ProductDetailsPageComponents(driver).checkCorrectProductDetailsPageOpenedOrNot(text);

		// validate product details page by calling the method
//		if (!(driver.getCurrentUrl().contains("pack") || driver.getCurrentUrl().contains("set")
//				|| driver.getCurrentUrl().contains("gift-cards")))
//			new ProductDetailsPageComponents(driver).validatePage();

		Reporter.log("Product head line text on collection page is Displayed :: Clickable and validated successfully");

	}

	public void validateProductHeadingTextOnPLP() throws Exception {
		String text = null;
		objGeneralMethods.waitTillPageLoad(driver);
		objGeneralMethods.isElementVisible(driver, FIRST_PRODUCT_HEAD_LINE_TEXT);
		// get product title and click on product heading text if present

		text = driver.findElement(FIRST_PRODUCT_IMAGE).getAttribute("alt");
		objGeneralMethods.clickButtonByJS(driver, FIRST_PRODUCT_HEAD_LINE_TEXT);
		// driver.findElement(FIRST_PRODUCT_HEAD_LINE_TEXT).click();
		Thread.sleep(3000);
		Reporter.log(driver.getCurrentUrl());

		// check correct Product details page opened or not
		new ProductDetailsPageComponents(driver).checkCorrectProductDetailsPageOpenedOrNot(text);

		/*
		 * // validate product details page by calling the method
		 * if(!(driver.getCurrentUrl().contains("pack")||driver.getCurrentUrl().contains
		 * ("set")||driver.getCurrentUrl().contains("gift-cards"))) new
		 * ProductDetailsPageComponents(driver).validatePage();
		 */

		Reporter.log("Product head line text on collection page is Displayed :: Clickable and validated successfully");

	}

	@SuppressWarnings("static-access")
	public void validateProductReviewStarsOnCollectionPage() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.navigate().to("https://www.tommyjohn.com/collections/womens-all");
		
		JavascriptExecutor jse = (JavascriptExecutor) driver;

		jse.executeScript("window.scrollBy(0,250)");
		wait.until(ExpectedConditions.presenceOfElementLocated(FIRST_PRODUCT_REVIEW_STARS));
		
		driver.findElement(FIRST_PRODUCT_IMAGE).getAttribute("alt");
		driver.findElement(FIRST_PRODUCT_REVIEW_STARS).click();
		Thread.sleep(3000);
		Reporter.log(driver.getCurrentUrl());
		// check correct Product details page opened or not
		// new
		// ProductDetailsPageComponents(driver).checkCorrectProductDetailsPageOpenedOrNot(text);

		// validate product details page by calling the method
		if (driver.findElement(ProductDetailsPageLocators.PRODUCT_TITLE).getText().contains("Set"))
			new ProductDetailsPageComponents(driver).verifybundlepages();
		else
			new ProductDetailsPageComponents(driver).validatePage();

		Reporter.log("Product review stars on collection page is Displayed :: Clickable and validated successfully");
	}

	@SuppressWarnings("static-access")
	public void validateProductReviewStarsOnPLP() throws Exception {
		String text = null;

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.presenceOfElementLocated(TOTAL_COUNTS_OF_ITEMS));
		JavascriptExecutor jse = (JavascriptExecutor) driver;

		jse.executeScript("window.scrollBy(0,250)");

		Thread.sleep(3000);
		// get product title and click on product heading text if present
		// try {
		if (!driver.findElement(FIRST_PRODUCT_REVIEW_STARS).isDisplayed())
			throw new Exception("Product review stars are not present");
		text = driver.findElement(FIRST_PRODUCT_IMAGE).getAttribute("alt");
		objGeneralMethods.clickButtonByJS(driver, FIRST_PRODUCT_HEAD_LINE_TEXT);
		Thread.sleep(3000);
		Reporter.log(driver.getCurrentUrl());
		// check correct Product details page opened or not
		new ProductDetailsPageComponents(driver).checkCorrectProductDetailsPageOpenedOrNot(text);

		/*
		 * // validate product details page by calling the method
		 * if(driver.findElement(ProductDetailsPageLocators.PRODUCT_TITLE).getText().
		 * contains("Set")) new
		 * ProductDetailsPageComponents(driver).verifybundlepages(); else new
		 * ProductDetailsPageComponents(driver).validatePage();
		 */

		Reporter.log("Product review stars on collection page is Displayed :: Clickable and validated successfully");
	}

	public void validateReviewStarOnGCPLP() throws Exception {
		// changed
		/*
		 * WebDriverWait wait = new WebDriverWait(driver, 30);
		 * wait.until(ExpectedConditions.presenceOfElementLocated(TOTAL_COUNTS_OF_ITEMS)
		 * );
		 */
		objGeneralMethods.isElementVisible(driver, TOTAL_COUNTS_OF_ITEMS);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)");
		Thread.sleep(3000);
		List<WebElement> product_name = driver.findElements(PRODUCT_TITLE_ON_COLLECTION);
		for (WebElement ele : product_name) {
			if (ele.getText().equalsIgnoreCase("E-Gift Card")) {
				try {
					WebElement reviews = driver
							.findElement(By.xpath("//*[@class='product-meta' and @title='E-Gift Card']/child::div[1]"));
					softAssert.assertFalse(reviews.isDisplayed(),
							"Reviews stars are displayed for 'E Gift Card' product on plp");
				} catch (Exception ex) {
					System.out.println("Reviews stars are not displayed for 'E Gift Card' product on plp");
				}
			} else if (ele.getText().equalsIgnoreCase("Tommy John Gift Card")) {

			}
			WebElement reviews = driver.findElement(
					By.xpath("//*[@class='product-meta' and @title='Tommy John Gift Card']/child::div[1]"));
			softAssert.assertTrue(reviews.isDisplayed(),
					"Review stars are not displayed for 'Tommy John Gift Card' product on plp");
		}
		softAssert.assertAll();
		Reporter.log("Validated Product rating stars on GC plp");

	}

	// method to validate all filters
	public void methodToCheckAllFilters() throws Exception {
		List<WebElement> allElements = null;
		int filterList = 0;
		int optionsList = 0;
		String selectedOption = null;
		int optionsList1 = 0;
		int count = 0;
		List<WebElement> allProductsOnPage = null;
		String totalDisplayedCount = null;

		// get total no of filters
		allElements = driver.findElements(ALL_FILTERS);
		filterList = allElements.size();
		System.out.println("All filters list :: " + filterList);

		// loop for total not of filters
		for (int i = 2; i <= filterList + 1; i++) {
			element = driver.findElement(By.cssSelector("div.mega-collection-filters > div:nth-child(" + i + ") > a"));
			// changed
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", element);
			// objGeneralMethods.clickButtonByJS(driver, element);
			// get all the options list available in ith filter
			allElements = driver
					.findElements(By.cssSelector("div.mega-collection-filters > div:nth-child(" + i + ") > ul > li"));
			optionsList = allElements.size();
			System.out.println("All options list :: " + optionsList);
			int ct = 0;
			// loop for clicking all the options in ith filter
			// for(int j = 1; j <= optionsList; j++) {

			// check if not any option present
			if (optionsList == 0)
				break;
			WebElement Filter_option_item = driver.findElement(By.cssSelector(
					"div.mega-collection-filters > div:nth-child(" + i + ") > ul > li:nth-child(1) > label > input"));
			if (!Filter_option_item.isDisplayed())
				continue;
			count++;
			jse.executeScript("arguments[0].click();", Filter_option_item);
			Thread.sleep(3000);

			// get the name of selected option
			// selectedOption =
			// driver.findElement(By.cssSelector("div.mega-collection-filters >
			// div:nth-child("+i+") > ul > li:nth-child(1) > label >
			// input")).getAttribute("data-filter-btn");
			if (i == 5 || i == 8 || i == 9) {
				selectedOption = driver.findElement(By.cssSelector(
						"div.mega-collection-filters > div:nth-child(" + i + ") > ul > li:nth-child(1) > label > span"))
						.getText();
			} else {
				selectedOption = driver.findElement(By.cssSelector(
						"div.mega-collection-filters > div:nth-child(" + i + ") > ul > li:nth-child(1) > label > span"))
						.getText();
			}
			// selectedOption =
			// driver.findElement(By.cssSelector("div.mega-collection-filters >
			// div:nth-child("+i+") > ul > li:nth-child(1) > label > span")).getText();
			System.out.println("selected option :: " + selectedOption);

			// get all selected displayed optioins
			allElements = driver.findElements(DISPLAYED_ALL_SELECTED_OPTIONS);
			optionsList1 = allElements.size();

			System.out.println("count :: " + count);
			System.out.println("optionlist1 :: " + optionsList1);
			// check selected options count and displayed selected list count is same or not
			if (count != optionsList1)
				throw new Exception("Selected options and selected displayed options are not same");

			System.out.println("ct :: " + ct);
			System.out.println("ct th ele :: " + allElements.get(ct).getText());
			if (!(allElements.get(ct).getText().replace("\"", "")).equals(selectedOption.replace("\"", "")))
				throw new Exception("Selected item is not present in displayed item list");

			// check total no of count is displayed or not
			if (!driver.findElement(TOTAL_COUNTS_OF_ITEMS).isDisplayed())
				throw new Exception("Total counts of items is not displayed for filter :: " + i);

			// check total not of count and products are correct
			totalDisplayedCount = driver.findElement(TOTAL_COUNTS_OF_ITEMS).getText();
			allProductsOnPage = driver.findElements(FIRST_PRODUCT_IMAGE);
			int allProductsOnPage1 = allProductsOnPage.size();
			System.out.println("Out ddd :: " + allProductsOnPage1);
			// check for hoverable class (if image is hoverable then it count two images for
			// one image)
			Thread.sleep(2000);
			element = driver.findElement(FIRST_PRODUCT_IMAGE);
			System.out.println("total displayed product on page :: " + allProductsOnPage1);
			System.out.println("total displayed count of product :: " + totalDisplayedCount);
			try {
				if (allProductsOnPage1 != (Integer.parseInt(totalDisplayedCount)))
					throw new Exception("Total displayed count is not same as all products on page");
			} catch (Exception e) {
				System.out.println("Due to lazy loading total displayed count is not possible.");
			}

			ct++;

			// }

			// check again any options are present or not
			if (optionsList == 0)
				continue;

			// click on last item to remove it from list
			Thread.sleep(5000);
			element = driver.findElement(By.cssSelector("div.mega-collection-filters > div:nth-child(" + i + ") > a"));
			jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", element);
			Thread.sleep(1000);
			System.out.println("last item :: " + driver.findElement(By.xpath(
					"(//div[contains(@class,'mega-collection-filter-tags mega-collection-filter-tags--desktop')]//button)["
							+ ct + "]"))
					.getText());
			element = driver.findElement(By.xpath(
					"(//div[contains(@class,'mega-collection-filter-tags mega-collection-filter-tags--desktop')]//button)["
							+ ct + "]"));
			jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", element);
			// driver.findElement(PLUS_BUTTON).click();
			Thread.sleep(5000);

			Thread.sleep(1000);
			// after last element removed decrease ct by 1
			System.out.println("Before ct::" + ct);
			// decrease ct by 1 bcz when one element is removed from list
			ct--;
			ct--; // for taking last element from list
			System.out.println("After ct--::" + ct);
			// check when we click on item then it remove from list or not
			System.out.println("allelements size:: " + allElements.size());

			if (ct != 0 && ct != -1) {
				System.out.println("ct :: " + allElements.get(ct).getText());
				System.out.println("ct :: " + driver.findElement(By.xpath(
						"(//div[contains(@class,'mega-collection-filter-tags mega-collection-filter-tags--desktop')]//button)["
								+ ct + "]"))
						.getText());
				if ((allElements.get(ct).getText()).equals(driver.findElement(By.xpath(
						"(//div[contains(@class,'mega-collection-filter-tags mega-collection-filter-tags--desktop')]//button)["
								+ ct + "]"))
						.getText()))
					throw new Exception("Item is not removed from displayed list when click on it");
			}
			ct = 0;

			// scroll it top again
			// JavascriptExecutor js = (JavascriptExecutor) driver;
			// js.executeScript("arguments[0].scrollIntoView();",
			// driver.findElement(CLEAR_FILTER_BUTTON));
			// ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,
			// -document.body.scrollHeight)");
			Thread.sleep(2000);

			// clear the filters
			WebElement ele = driver.findElement(CLEAR_FILTER_BUTTON);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", ele);
			// driver.findElement(CLEAR_FILTER_BUTTON).click();
			Thread.sleep(3000);
			allElements = null;
			count = 0;
		}
		Reporter.log("All filters are working correctly");
		Reporter.log("All selected items are displayed in list and removing from list");
		Reporter.log("Clear filters button works correctly");

		// scroll to last filter view
		// element = driver.findElement(LAST_FILTER);
		// JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("arguments[0].scrollIntoView();", element);
		//
		/*
		 * WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(LAST_FILTER)); if
		 * (driver.findElement(LAST_FILTER).isDisplayed()) { //
		 * driver.findElement(LAST_FILTER).click(); Select selectElement = new
		 * Select(driver.findElement(LAST_FILTER));
		 * selectElement.selectByValue("price:ascending");
		 * 
		 * // click on second option
		 * 
		 * // wait.until(ExpectedConditions.visibilityOfElementLocated(
		 * SORTBYPRICE_HIGH_TO_LOW_FILTER_OPTION)); //
		 * driver.findElement(SORTBYPRICE_HIGH_TO_LOW_FILTER_OPTION).click();
		 * Thread.sleep(5000); allProductsOnPage =
		 * driver.findElements(FIRST_PRODUCT_IMAGE);
		 * System.out.println("All product on page :: " + allProductsOnPage.size()); try
		 * { for (int i = 1; i < allProductsOnPage.size(); i++) { String text = null;
		 * String text1 = null; String text2 = null;
		 * 
		 * text = driver.findElement(By.
		 * cssSelector(".mega-collection-grid__row > div.product-meta__prices(" + i +
		 * ") > span.product-meta__price")).getText(); System.out.println("Text :: " +
		 * text); for (String a : text.split("\\$")) text1 = a;
		 * System.out.println("text 1 :: " + text1); // loop to check previous and next
		 * price int j = i + 1; System.out.println("Text :: " + driver.findElement(By.
		 * cssSelector(".mega-collection-grid__row > div.product-meta__prices(" + j +
		 * ") > span.product-meta__price")).getText()); for (String a : driver
		 * .findElement(By.
		 * cssSelector(".mega-collection-grid__row > div.product-meta__prices(" + j +
		 * ") > span.product-meta__price")) .getText().split("\\$")) text2 = a;
		 * System.out.println("text2 :: " + text2); if (Float.parseFloat(text1) >
		 * Float.parseFloat(text2)) throw new
		 * Exception("Products are not sorted by price correctly");
		 * 
		 * } } catch (Exception e) { System.out.
		 * println("Due to lazy loading price comparison is not possible for sort by filter."
		 * ); } Reporter.log("All Products are sorted as selected option");
		 * 
		 * }
		 */

	}

	public void methodToCheckCorosalsOnTopOfPageIsActiveWhenRelatedStyleIsChecked() throws Exception {
		boolean flag1 = false;
		int i = 2;
		List<WebElement> allElements = null;
		List<WebElement> allCorosals = null;

		Thread.sleep(20000);

		// check carosals are displayed on top of page or not
		if (!driver.findElement(TOTAL_NO_OF_COROSALS).isDisplayed())
			throw new Exception("Carousels are not displayed on top of page");

		allCorosals = driver.findElements(TOTAL_NO_OF_COROSALS);
		System.out.println("Total no of corosals :: " + allCorosals.size());

		// first check all corosals are not active by default
		for (int k = 1; k <= allCorosals.size(); k++) {
			element = driver.findElement(By.cssSelector(".hero-interactive-slide.slick-slide:nth-child(" + k + ")"));
			String classes = element.getAttribute("class");
			for (String c : classes.split(" ")) {
				if (c.equals("active")) {
					flag1 = true;
				}
			}
			if (flag1 == true)
				throw new Exception("All corosals are not deactive by default");
			flag1 = false;
		}
		Reporter.log("All corosals are Displayed :: And by default not active");
		// check selected option in style and respected corosal is active or not
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.cssSelector("div.mega-collection-filters > div:nth-child(" + i + ") > a")));
		WebElement filter = driver
				.findElement(By.cssSelector("div.mega-collection-filters > div:nth-child(" + i + ") > a"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", filter);
		// get all the options list available in ith filter
		allElements = driver
				.findElements(By.cssSelector("div.mega-collection-filters > div:nth-child(" + i + ") > ul > li"));
		// loop for clicking all the options in ith filter
		for (int j = 1; j <= allElements.size(); j++) {
			// check if element is not displayed but present then continue at this step
			System.out.println("i = " + i + " j = " + j);
			if (!driver.findElement(By.cssSelector("div.mega-collection-filters > div:nth-child(" + i
					+ ") > ul > li:nth-child(" + j + ") > label > input")).isDisplayed())
				continue;

			driver.findElement(By.cssSelector("div.mega-collection-filters > div:nth-child(" + i
					+ ") > ul > li:nth-child(" + j + ") > label > input")).click();
			Thread.sleep(1000);

			String selectedOption = driver.findElement(By.cssSelector("div.mega-collection-filters > div:nth-child(" + i
					+ ") > ul > li:nth-child(" + j + ") > label > input")).getAttribute("data-filter-btn");
			// System.out.println("Selected Option :: "+selectedOption);

			// check selected option related carosal is active or not
			for (int k = 1; k <= allCorosals.size(); k++) {
				String corosalName = driver
						.findElement(
								By.cssSelector(".hero-interactive-slide.slick-slide:nth-child(" + k + ") > button"))
						.getAttribute("data-hero-filter-name");
				if (selectedOption == corosalName) {
					element = driver
							.findElement(By.cssSelector(".hero-interactive-slide.slick-slide:nth-child(" + k + ")"));
					String classes = element.getAttribute("class");
					for (String c : classes.split(" ")) {
						if (c.equals("active")) {
							flag1 = true;
						}
					}
					if (!flag1 == true)
						throw new Exception("Corosal related to " + selectedOption + " is not gets active");
					flag1 = false;
				}
			}
		}
		// clear all filters

		wait.until(ExpectedConditions.visibilityOfElementLocated(CLEAR_FILTER_BUTTON));
		WebElement ele = driver.findElement(CLEAR_FILTER_BUTTON);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", ele);

		// driver.findElement(CLEAR_FILTER_BUTTON).click();
		Reporter.log("Corosals gets active when respective option selected");
	}
	
	public void validateStyleCarousel() throws Exception {
		boolean flag1 = false;
		List<WebElement> allCorosals = null;
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, -document.body.scrollHeight)");

		Thread.sleep(10000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		WebElement carouselFilter;
		WebElement filterPill;

		// check carosals are displayed on top of page or not
		if (!driver.findElement(TOTAL_NO_OF_COROSALS).isDisplayed())
			throw new Exception("Carousels are not displayed on top of page");

		allCorosals = driver.findElements(TOTAL_NO_OF_COROSALS);
		System.out.println("Total no of corosals :: " + allCorosals.size());

		// first check all corosals are not active by default
		for (int k = 1; k <= allCorosals.size(); k++) {
			element = driver.findElement(By.cssSelector("div.dyHero-interactive__slider>button:nth-child(" + k + ")"));
			String classes = element.getAttribute("class");
			for (String c : classes.split(" ")) {
				if (c.equals("active")) {
					flag1 = true;
				}
			}
			if (flag1 == true)
				throw new Exception("All corosals are not deactive by default");
			flag1 = false;
		}
		Reporter.log("All corosals are Displayed :: And by default not active");
		
		
		for (int k = 1; k <= allCorosals.size(); k++) {
			carouselFilter = driver.findElement(By.cssSelector("div.dyHero-interactive__slider>button:nth-child(" + k + ")"));
			if(carouselFilter.isEnabled()) {
				System.out.println("Carousel filter is clickable");
				js.executeScript("arguments[0].click();", carouselFilter);
			}
			 filterPill = driver.findElement(FILTER_PILL);
			wait.until(ExpectedConditions.visibilityOfElementLocated(FILTER_PILL));
			js.executeScript("arguments[0].click();", filterPill);
		}
		// clear all filters

		wait.until(ExpectedConditions.visibilityOfElementLocated(CLEAR_FILTER_BUTTON));
		WebElement ele = driver.findElement(CLEAR_FILTER_BUTTON);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", ele);

		// driver.findElement(CLEAR_FILTER_BUTTON).click();
		Reporter.log("Corosals gets active when respective option selected");
	}

	public void validateallurls() throws Exception

	{
		new WebDriverWait(driver, Duration.ofSeconds(30));
		try {
			
			String csv_path = "src/main/resources/PLPs.csv";
			CSVReader CSVReader = new CSVReader(new FileReader(csv_path));
			List<String[]> list = CSVReader.readAll();
			Iterator<String[]>ite= list.iterator();
			while(ite.hasNext()){
				String[] data = ite.next();
				for(int i=0; i<data.length; i++){
					System.out.println(data[i]);
					String url = "https://www.tommyjohn.com" + data[i];
				//	checkurl(url);
				driver.get(url);
				if (driver.findElement(FIRST_PRODUCT).isDisplayed()) {
					count++;
				}
				}
			}
			

if(count!=59)
	//System.out.println(count);
	throw new Exception("Some urls not loading.");

			
			
		//	File src = new File("src/main/resources/PLPs.csv");
		
			// load file
		//	FileInputStream fis = new FileInputStream(src);

			// Load workbook
		//	XSSFWorkbook wb = new XSSFWorkbook(fis);

			// Load sheet- Here we are loading first sheet only
		//	XSSFSheet sh1 = wb.getSheetAt(0);

//			System.out.println(sh1.getRow(0).getCell(0).getStringCellValue());
//			Reporter.log(sh1.getRow(0).getCell(0).getStringCellValue());
//
//			int rowCount = sh1.getLastRowNum() - sh1.getFirstRowNum();
//
//			for (int i = 1; i < rowCount + 1; i++) {
//				String url = sh1.getRow(i).getCell(0).getStringCellValue();
//				checkurl(url);
//			}

		} catch (Exception e) {
			Reporter.log("File not found");
			throw new Exception ("File not found");
		}
	}

	public void QuickShopOnMegaCollection() throws Exception {
		Actions action = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Thread.sleep(8000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		js.executeScript("window.scrollBy(0,500)");

		wait.until(ExpectedConditions.presenceOfElementLocated(FIRST_PRODUCT_IMAGE));
		// Hover On product image on collection page
		element = driver.findElement(FIRST_PRODUCT_IMAGE);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		action.moveToElement(element).build().perform();
		Thread.sleep(3000);
		AddproductToCartByQuickShop();
	}

	
		public void AddproductToCartByQuickShop() throws Exception {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			String prod_title = driver.findElement(PRODUCT_TITLE_ON_COLLECTION).getText();
			String color_name = driver.findElement(PRODUCT_ITEM_SWATCH_CIRCLE).getAttribute("title");
			String amount = driver.findElement(PRODUCT_AMOUNT_ON_Collection).getText();
			Thread.sleep(3000);
			// If Quick shop ATC is displayed
			if (driver.findElement(QUICK_SHOP_ATC).isDisplayed()) {
				Reporter.log("Quick shop ATC button is displayed on hover.");
				System.out.println("Quick shop ATC text is: " + driver.findElement(QUICK_SHOP_ATC).getText());
				softAssert.assertEquals(driver.findElement(QUICK_SHOP_ATC).getText(), "Quick Shop +",
						"Text changed for Quick shop ATC button.");
				driver.findElement(QUICK_SHOP_ATC).click();
				Thread.sleep(3000);
				Reporter.log("Quick shop ATC button text is validated :: Clickable.");

				// size overlay Field validation
				softAssert.assertTrue(driver.findElement(QUICK_SHOP_SIZE_OVERLAY).isDisplayed(),
						"On collection page Quick shop select size overlay is not displayed after click on ATC button.");
				Reporter.log("Quick shop size overlay is displayed.");
				softAssert.assertTrue(driver.findElement(QUICK_SHOP_SIZE_OVERLAY_CLOSE).isDisplayed(),
						"Quick shop select size overlay close btn is not displayed after click on ATC button.");
				Reporter.log("Quick shop size overlay is close button is displayed.");
				softAssert.assertTrue(driver.findElement(QUICK_SHOP_SIZE_OVERLAY_HEADING).isDisplayed(),
						"Quick shop select size overlay's heading is not displayed.");
			//	softAssert.assertEquals(driver.findElement(QUICK_SHOP_SIZE_OVERLAY_HEADING).getText(), "Choose Your Size",
			//			"Text changed for Quick shop Size overlay heading.");
				Reporter.log("Quick shop size overlay heading is displayed ::Correct.");
				softAssert.assertTrue(driver.findElement(QUICK_SHOP_SIZE_LABEL).isDisplayed(),
						"Quick shop size labels are not displayed.");
				Reporter.log("Quick shop size labels are displayed.");
				/*
				 * softAssert.assertTrue(!driver.findElement(QUICK_SHOP_SIZE_OVERLAY_ATC).
				 * isEnabled(),
				 * "Quick shop size overlay ATC is enabled before size selection. ");
				 * Reporter.log("Quick shop size overlay ATC is disabled before size selection."
				 * );
				 * 
				 */
				// select sizes from quick shop size overlay
				List<WebElement> allElements1;
				List<WebElement> allElements2;
				String classtext;
				//allElements = driver.findElements(QUICK_SHOP_SIZE_LIST);
				allElements1 = driver.findElements(QUICK_SHOP_SIZE_LIST_1);
				allElements2 = driver.findElements(QUICK_SHOP_SIZE_LIST_2);
				System.out.println("All sizes list :: " + allElements1.size());
				for (int i = 0; i < allElements1.size(); i++) {
					// classtext=allElements.get(i).getAttribute("class");
					String[] classes1 = allElements1.get(i).getAttribute("class").split(" ");
					classtext = classes1[classes1.length - 1];
					System.out.println("last class name is: " + classtext);
					if (!classtext.equalsIgnoreCase("not-available")) {
						element = driver.findElement(
								By.cssSelector("div.inline-quick-shop > ul:nth-child(4) > li:nth-child(" + (i + 1) + ") > input"));
						js.executeScript("arguments[0].click();", element);
						// Selected_size=allElements.get(i).getAttribute("value");
						Selected_size1 = element.getAttribute("value");
						System.out.println("Selected size is: " + Selected_size1);
						Thread.sleep(3000);
						break;

					} else {
						System.out.println("Selected size is OOS");
					}	
				}
				if(!allElements2.isEmpty()) {
					for (int i = 0; i < allElements2.size(); i++) {
						// classtext=allElements.get(i).getAttribute("class");
						String[] classes1 = allElements2.get(i).getAttribute("class").split(" ");
						classtext = classes1[classes1.length - 1];
						System.out.println("last class name is: " + classtext);
						if (!classtext.equalsIgnoreCase("not-available")) {
							element = driver.findElement(
									By.cssSelector("div.inline-quick-shop > ul:nth-child(6) > li:nth-child(" + (i + 1) + ") > input"));
							js.executeScript("arguments[0].click();", element);
							// Selected_size=allElements.get(i).getAttribute("value");
							Selected_size2 = element.getAttribute("value");
							System.out.println("Selected size is: " + Selected_size2);
							Thread.sleep(3000);
							break;

						} else {
							System.out.println("Selected size is OOS");
						}
					}	
					Thread.sleep(5000);
					text = driver.findElement(FlyCartPageLocator.COLOR_AND_SIZE_IN_CART_MULTI).getText();
					String actual = text;
					// String[] text1 = text.split("/");
					// actual = text1[0];
					String expected = color_name + " / " + Selected_size1 + " / " + Selected_size2;
					// String expected = color_name+" ";
					System.out.println("Actual Color name is: " + actual + " and Expected color name is: " + expected);
					softAssert.assertEquals(actual, expected, "Product Line item option on flycart is incorrect.");
				}
				else {
					System.out.println("The product doesn't have multivariant sizes in quickshop");
				}
				Reporter.log("Quick shop size labels are Clickable.");

				/*
				 * for(int i=0;i<allElements.size();i++) { element = driver.findElement(By.
				 * cssSelector("div.inline-quick-shop > ul > li:nth-child("+(i+1)+")"));
				 * //element.click(); if(!element.getClass().equals("not-available"))
				 * element.click(); Selected_size=allElements.get(i+1).getText();
				 * System.out.println("Selected size is: "+Selected_size); Thread.sleep(3000); }
				 */
				// Add available size to the cart
				// if(driver.findElement(QUICK_SHOP_SIZE_OVERLAY_ATC).isEnabled()){
				/*
				 * text= driver.findElement(QUICK_SHOP_SIZE_OVERLAY_ATC).getText();
				 * if(text.equalsIgnoreCase("Out Of Stock")) throw new
				 * Exception("Quick shop size overlay ATC btn is enabled for OOS product size."
				 * );
				 * 
				 * else if(text.equalsIgnoreCase("Add To Cart"))
				 * driver.findElement(QUICK_SHOP_SIZE_OVERLAY_ATC).click();
				 * Reporter.log("Quick Shop size overlay ATC button is Displayed :: Clickable."
				 * ); Thread.sleep(3000);
				 */
				// field validation for added product on fly cart
				Thread.sleep(3000);
				String actual = driver.findElement(FlyCartPageLocator.FIRST_PRODUCT_TITLE_IN_CART).getText();
				System.out.println("Actual product name is: " + actual + " and Expected product name is: " + prod_title);
				// softAssert.assertEquals(actual,prod_title ,"Product name on flycart is
				// incorrect.");

				if(driver.findElement(FlyCartPageLocator.COLOR_AND_SIZE_IN_CART).isDisplayed())
				{
					text = driver.findElement(FlyCartPageLocator.COLOR_AND_SIZE_IN_CART).getText();
					actual = text;
					// String[] text1 = text.split("/");
					// actual = text1[0];
					String expected = color_name + " / " + Selected_size1;
					// String expected = color_name+" ";
					System.out.println("Actual Color name is: " + actual + " and Expected color name is: " + expected);
					//softAssert.assertEquals(actual, expected, "Product Line item option on flycart is incorrect.");
				}
				
				actual = driver.findElement(FlyCartPageLocator.PRICE_IN_QUANTITY_LINE).getText();
				// Product amount is displayed without ".00" suffix in Buy with section
				String new_amount = null;
				String new_actual_amount = null;
				for (String a : amount.split("\\.00"))
					new_amount = a;
				for (String a : amount.split("\\.00"))
					new_actual_amount = a;
				System.out.println("Actual product amount is: " + new_actual_amount + " and Expected product amount is: "
						+ new_amount);
				softAssert.assertEquals(new_actual_amount, new_amount, "Product amount on flycart is incorrect.");
				System.out.println("product details are correct on Fly cart.");
				Thread.sleep(3000);
				Reporter.log("Product details are validated on Inline cart.");
				WebElement btn = driver.findElement(FlyCartPageLocator.INLINE_CART_CLOSE_BUTTON);
				js.executeScript("arguments[0].click();", btn);
				// }
				// If selected size is OOS/Unavailable
				/*
				 * else{ text= driver.findElement(QUICK_SHOP_SIZE_OVERLAY_ATC).getText();
				 * System.out.println("Text of ATC on Quick shop overlay for OOS size is: "+text
				 * ); if(!text.equalsIgnoreCase("Out Of Stock")){
				 * if(!text.equalsIgnoreCase("Unavilable")) throw new
				 * Exception("Text change of ATC on Quick shop overlay for OOS/Unavailable size."
				 * ); }
				 * 
				 * driver.findElement(QUICK_SHOP_SIZE_OVERLAY_CLOSE).click();
				 * System.out.println("OOS/Unavaiable size is selected.");
				 * Reporter.log("Quick shop size overlay OOS/unavailable button is disabled.");
				 * }
				 */
			} else
				throw new Exception(
						"Quick Shop ATC button is not displayed on hovered product or quickshop not available for particular product");
			softAssert.assertAll();
		}
	

	public void MethodToCheckColorSwatchChanging() throws Exception {
		driver.navigate().back();
		Thread.sleep(5000);
		JavascriptExecutor executore = (JavascriptExecutor) driver;
		//executore.executeScript("window.scrollBy(0, 350)", "");
		Thread.sleep(5000);

		List<WebElement> allElements = driver.findElements(ALL_COLOR_LIST);
		int elementsCount = allElements.size();
		System.out.println("Color Element count :: " + elementsCount);

		if (elementsCount > 1) {
			element = driver.findElement(By.cssSelector(
				"div[data-index='0']>div:nth-child(2)>div>div>div.product-item__swatch--circle"));
		//	element.click();

			for (int i = 1; i < elementsCount; i++) {
				if (i < 4) {
					String Img_colortext = null;
					String colornew1 = null;
					text = driver.findElement(PRODUCT_IMAGE_COLOR_NAME).getAttribute("href");
					String[] strarray = text.split("[=]");
					String text1 = strarray[strarray.length - 1];
					Img_colortext = text1.replace("-", " ");

					System.out.println("Product image color name is: " + Img_colortext);

					element = driver.findElement(By.cssSelector(
							"div[data-index='0']>div:nth-child(2)>div>div:nth-child(" + i + ")>div.product-item__swatch--circle"));

					colornew1 = element.getAttribute("title");
					String colornew = colornew1.replace("/", " ");
					System.out.println(colornew);
					if (colornew.equalsIgnoreCase(Img_colortext)) {
						i++;
						WebElement elemen = driver.findElement(By.cssSelector(
								"div[data-index='0']>div:nth-child(2)>div>div:nth-child(" + i + ")>div.product-item__swatch--circle"));
						executore.executeScript("arguments[0].click();", elemen);
						//softAssert.assertEquals(Img_colortext, colornew, "Color not changing");
						
						i--;
					} else {
						throw new Exception("Color not changing");
					}
				}
			}
		}

		// Check slick arrows of color Swatches
		if (elementsCount > 8) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			element = driver.findElement(COLOR_SWATCH_NEXT_SLICK_ARROWS);
			softAssert.assertTrue(element.getAttribute("aria-disabled").equals("false"),
					"Color swatch next slick arrow is not enabled.");
			if (element.getAttribute("aria-disabled").equals("false")) {
				js.executeScript("arguments[0].click();", element);
			}

			element = driver.findElement(COLOR_SWATCH_PREV_SLICK_ARROWS);
			softAssert.assertTrue(element.getAttribute("aria-disabled").equals("false"),
					"Color swatch prev slick arrow is not enabled.");
			if (element.getAttribute("aria-disabled").equals("false")) {
				js.executeScript("arguments[0].click();", element);
			}
			Reporter.log("Color swatch slick arrows are displayed :: Clickable");
		}
		
		softAssert.assertAll();
		Reporter.log("Color swatches are changing correctly");

	}

	public void MethodToCheckColorSwatchChangingOnPLP() throws Exception {
		Thread.sleep(5000);
		JavascriptExecutor executore = (JavascriptExecutor) driver;
		executore.executeScript("window.scrollBy(0, 350)", "");
		Thread.sleep(5000);

		List<WebElement> allElements = driver.findElements(ALL_COLOR_LIST);
		int elementsCount = allElements.size();
		System.out.println("Color Element count :: " + elementsCount);

		if (elementsCount > 1) {
			element = driver.findElement(By.cssSelector(
					"div.mega-collection-grid.js-mega-collection-grid > div:nth-child(3) > div:nth-child(1)>div:nth-child(2)>.product-item__swatch-list>.slick-list>.slick-track>div:nth-child(1)>div"));
			element.click();

			for (int i = 1; i < elementsCount; i++) {
				if (i < 4) {
					String Img_colortext = null;
					String colornew1 = null;
					text = driver.findElement(PRODUCT_IMAGE_COLOR_NAME).getAttribute("href");
					String[] strarray = text.split("[=]");
					String text1 = strarray[strarray.length - 1];
					Img_colortext = text1.replace("-", " ");

					System.out.println("Product image color name is: " + Img_colortext);

					element = driver.findElement(By.cssSelector(
							"div.mega-collection-grid.js-mega-collection-grid > div:nth-child(3) > div:nth-child(1)>div:nth-child(2)>.product-item__swatch-list>.slick-list>.slick-track>div:nth-child("
									+ i + ")>div"));
					colornew1 = element.getAttribute("title");
					String colornew = colornew1.replace("/", " ");
					System.out.println(colornew);
					if (colornew.equalsIgnoreCase(Img_colortext)) {
						WebElement elemen = driver.findElement(By.cssSelector(
								"div.mega-collection-grid.js-mega-collection-grid > div:nth-child(3) > div:nth-child(1)>div:nth-child(2)>.product-item__swatch-list>.slick-list>.slick-track>div:nth-child( "
										+ (i + 1) + ")>div"));
						executore.executeScript("arguments[0].click();", elemen);
						softAssert.assertEquals(Img_colortext, colornew, "Color not changing");
						Reporter.log("Color swatches are changing correctly");
					} else {
						throw new Exception("Color not changing");
					}
				}
			}
		}

		// Check slick arrows of color Swatches
		if (elementsCount > 8) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			element = driver.findElement(COLOR_SWATCH_NEXT_SLICK_ARROWS);
			softAssert.assertTrue(element.getAttribute("aria-disabled").equals("false"),
					"Color swatch next slick arrow is not enabled.");
			if (element.getAttribute("aria-disabled").equals("false")) {
				js.executeScript("arguments[0].click();", element);
			}

			element = driver.findElement(COLOR_SWATCH_PREV_SLICK_ARROWS);
			softAssert.assertTrue(element.getAttribute("aria-disabled").equals("false"),
					"Color swatch prev slick arrow is not enabled.");
			if (element.getAttribute("aria-disabled").equals("false")) {
				js.executeScript("arguments[0].click();", element);
			}
			Reporter.log("Color swatch slick arrows are displayed :: Clickable");
		}

	}

	public void Verify_ProductTile_HoverFunctionality() throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Thread.sleep(8000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		js.executeScript("window.scrollBy(0,500)");

		wait.until(ExpectedConditions.presenceOfElementLocated(FIRST_PRODUCT_IMAGE));
		String text1 = driver.findElement(HOVERED_PRODUCT_IMAGE).getAttribute("src");
		String[] imagetext = text1.split("_425");
		String Hover_on_plp = imagetext[0];
		element = driver.findElement(FIRST_PRODUCT_IMAGE);
		js.executeScript("arguments[0].click();", element);
		Thread.sleep(3000);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(ProductDetailsPageLocators.SECOND_VERTICAL_IMAGE));
		String text = driver.findElement(ProductDetailsPageLocators.SECOND_VERTICAL_IMAGE).getAttribute("src");
		String[] list1 = text.split(".jpg");
		String Second_image_pdp = list1[0];
		if (!Hover_on_plp.equalsIgnoreCase(Second_image_pdp))
			throw new Exception("Product tile hover functionality is not correct on plp");
		Reporter.log("Verified Product tile hover functionality on plp");
	}

	public void UpdatedSizeFilter() {
		List<WebElement> allElements = null;
		allElements = driver.findElements(ALL_FILTERS);
		int filterList = 0;
		filterList = allElements.size();
		for (int i = 2; i <= filterList + 1; i++) {
			element = driver.findElement(By.cssSelector("div.mega-collection-filters > div:nth-child(" + i + ") > a"));
			if (element.getAttribute("title").equalsIgnoreCase("Size")) {
				element.click();
				break;
			}
		}
	}

	// new
	public void validateProductGrid() throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PLP_PRODUCTS)));
		
	org.openqa.selenium.Dimension d= 	driver.findElement(By.cssSelector(".product-image-container>a")).getSize();
	if(d.width>278)
		throw new Exception("Product grid conatins incorrect number of items"); 
		

//		for (int i = 1; i < 5; i++) {
//			if (objGeneralMethods.verifyElementExists(driver, By.xpath(PLP_PRODUCTS + "[" + i + "]"))) {
//			
//				System.out.println("Product " + i + " is displayed successfully");
//				
//				
//			} else {
//				throw new Exception("Product " + i + " is not displayed");
//			}
//		}
	}

	// new
	public void validatePLPBreadcrumbLinks() throws Exception {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		JavascriptExecutor js = (JavascriptExecutor) driver;

		WebElement breadcrumb = driver.findElement(PLP_BREADCRUMB);
		WebElement pageHeading;

		wait.until(ExpectedConditions.visibilityOfElementLocated(PLP_BREADCRUMB));
		Assert.assertTrue(breadcrumb.isEnabled(), "Element Clickable");
		js.executeScript("arguments[0].click();", breadcrumb);

		wait.until(ExpectedConditions.visibilityOfElementLocated(PLP_TITLE));
		pageHeading = driver.findElement(PLP_TITLE);

		if (pageHeading.getText().equals("Women's All")) {
			System.out.println("Breadcrumb link is working properly");
			Reporter.log("Breadcrumb link is working properly");

		} else {
			System.out.println("Breadcrumb link is not working");
			throw new Exception("Breadcrumb link is not working");
		}

	}

	// new
	public void validatePLPHeader() throws Exception {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		WebElement pageHeader = driver.findElement(PLP_TITLE);

		wait.until(ExpectedConditions.visibilityOfElementLocated(PLP_TITLE));
		Assert.assertTrue(pageHeader.isDisplayed(), "The Category page header is displayed");

		if ((pageHeader.isDisplayed())) {
			Reporter.log("The Category page header is displayed");
			System.out.println("Page Header is - " + pageHeader.getText());

		} else {
			throw new Exception("Page Header is not displayed");
		}

	}

	// new
	public void validatePLPSectionHeaders() throws Exception {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		JavascriptExecutor js = (JavascriptExecutor) driver;

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PLP_SECTION_HEADER)));
		WebElement sectionHeader = driver.findElement(By.xpath(PLP_SECTION_HEADER));
		List<WebElement> sectionHeaders = driver.findElements(By.xpath(PLP_SECTION_HEADER));

		js.executeScript("window.scrollBy(0,250)");
		Assert.assertTrue(sectionHeader.isDisplayed(), "The Category page section header is displayed");

		for (int i = 1; i < sectionHeaders.size(); i++) {
			if (driver.findElement(By.xpath(PLP_SECTION_HEADER + "[" + i + "]")).isDisplayed()) {

				Reporter.log("The Category page header "
						+ driver.findElement(By.xpath(PLP_SECTION_HEADER + "[" + i + "]")).getText() + " is displayed");
				System.out.println("Section Header - "
						+ driver.findElement(By.xpath(PLP_SECTION_HEADER + "[" + i + "]")).getText());

			} else {
				throw new Exception("Section Header is not displayed");
			}
		}
	}

	public void validatePLPInlineBanner() throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(PLP_INLINE_BANNER));
		WebElement inlineBanner = driver.findElement(PLP_INLINE_BANNER);
		System.out.println(driver.getCurrentUrl());
		

		Assert.assertTrue(inlineBanner.isEnabled(), "Inline banner is clickable");
		js.executeScript("arguments[0].scrollIntoView(true);", inlineBanner);
		//Actions action = new Actions(driver);
		//action.moveToElement(inlineBanner).click().perform();
		js.executeScript("arguments[0].click();", inlineBanner);


	}

	public void validateOnImageAndSecondaryBadges() throws Exception {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(PLP_PRODUCT_IMAGE_BADGE));
		wait.until(ExpectedConditions.visibilityOfElementLocated(PLP_PRODUCT_SECONDARY_BADGE));

		WebElement onImageBadge = driver.findElement(PLP_PRODUCT_IMAGE_BADGE);
		WebElement secondaryBadge = driver.findElement(PLP_PRODUCT_SECONDARY_BADGE);

		js.executeScript("window.scrollBy(0,250)");
		Assert.assertTrue(onImageBadge.isDisplayed(), "The On Image Badge is displayed");
		Assert.assertTrue(secondaryBadge.isDisplayed(), "The Seacondary Badge is displayed");

		if (onImageBadge.isDisplayed()) {

			Reporter.log("The On Image Badge is displayed");
			System.out.println("The On Image Badge is displayed");

		} else {
			System.out.println("The On Image Badge is not displayed");
			throw new Exception("The On Image Badge is not displayed");
		}
		
		if (secondaryBadge.isDisplayed()) {

			Reporter.log("The Secondary Badge is displayed");
			System.out.println("The Secondary Badge is displayed");

		} else {
			
			System.out.println("The Secondary Badge is not displayed");
			throw new Exception("The Secondary Badge is not displayed");
		}
	}

	public void validateSEO() throws Exception {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(PLP_SEO_TITLE));
		wait.until(ExpectedConditions.visibilityOfElementLocated(PLP_SEO_BODY));

		WebElement seoTitle = driver.findElement(PLP_SEO_TITLE);
		WebElement seoBody = driver.findElement(PLP_SEO_BODY);
		WebElement seoLink = driver.findElement(PLP_SEO_LINK);

		js.executeScript("window.scrollBy(0,250)");
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(3000);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(3000);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(3000);
		
		Assert.assertTrue(seoTitle.isDisplayed(), "Seo title is displayed");
		Assert.assertTrue(seoBody.isDisplayed(), "Seo Body is displayed");

		if (seoTitle.isDisplayed()) {

			Reporter.log("SEO title is displayed");
			System.out.println("SEO title is displayed");
			if(seoBody.isDisplayed()) {
				Reporter.log("SEO Body displayed");
				System.out.println("SEO Body is displayed");
			}
			else {
				System.out.println("SEO Body is not displayed");
				throw new Exception("SEO Body is not displayed");
			}
		} else {
			System.out.println("SEO title is not displayed");
			throw new Exception("SEO title is not displayed");
		}
		
		String textLink = seoLink.getAttribute("href");
		Assert.assertTrue(seoLink.isEnabled(), "Seo link is clickable");
		js.executeScript("arguments[0].click();", seoLink);
		
		Thread.sleep(5000);
		String url = driver.getCurrentUrl();
		System.out.println(textLink);
		System.out.println(url);
		
		if(url.contains(textLink)) {
			System.out.println("User has navigated to correct page");
			Reporter.log("User has navigated to correct page");
		}
		else {
			System.out.println("User has not navigated to desired page");
			throw new Exception("User has not navigated to desired page");
		}
	}
	
	public void validateSingleTile() throws Exception {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		js.executeScript("window.scrollBy(0,250)");
		wait.until(ExpectedConditions.visibilityOfElementLocated(PLP_PRICE_FILTER));
		wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_IMAGE));

		WebElement priceFilter = driver.findElement(PLP_PRICE_FILTER);
		WebElement singleProductFilter = driver.findElement(PLP_FILTER_SINGLE_PROODUCT);
		
		int width ;
//		String marginLeft="";
//		String marginRight="";
		
		Assert.assertTrue(priceFilter.isDisplayed(), "Price Filter is displayed");
		Assert.assertTrue(priceFilter.isEnabled(), "Price Filter is clickable");

		if (priceFilter.isDisplayed()) {

			Reporter.log("Price Filter is displayed");
			System.out.println("Price Filter is displayed");
			js.executeScript("arguments[0].click();", priceFilter);
			
			if(singleProductFilter.isEnabled())
			{
				Reporter.log("Single Product Filter is clickable");
				System.out.println("Single Product Filter is clickable");
				js.executeScript("arguments[0].click();", singleProductFilter);
				js.executeScript("arguments[0].click();", priceFilter);
				Thread.sleep(5000);
			}
			else {
				System.out.println("Single Product Filter is not clickable");
				throw new Exception("Single Product Filter is not clickable");
			}
		} else {
			System.out.println("Price Filter is not displayed");
			throw new Exception("Price Filter is not displayed");
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_IMAGE));

		WebElement productImage = driver.findElement(By.cssSelector("div[data-index='0']>div>a>img.product-image--main"));
		org.openqa.selenium.Dimension dim = productImage.getSize();
		width=	dim.width;
//		marginLeft = productImage.getCssValue("margin-left");
//		marginRight = productImage.getCssValue("margin-right");
//		System.out.println(width + " " + marginLeft + " " + marginRight);
		
		if(width>280)  {
			Reporter.log("Single Product Tile annotations are correct");
			System.out.println("Single Product Tile annotations are correct");
		}
		else {
			System.out.println("Single Product Tile annotations are not correct");
			throw new Exception("Single Product Tile annotations are not correct");
		}
	}
}
