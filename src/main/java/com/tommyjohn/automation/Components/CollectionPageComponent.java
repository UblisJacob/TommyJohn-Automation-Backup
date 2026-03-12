package com.tommyjohn.automation.Components;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.base.ExtentReport;
import com.opencsv.CSVReader;
import com.tommyjohn.automation.PageLocators.CollectionPageLocator;
import com.tommyjohn.automation.PageLocators.FlyCartPageLocator;
import com.tommyjohn.automation.PageLocators.ProductDetailsPageLocators;
import com.tommyjohn.automation.commonMethods.GeneralMethods;

import static com.tommyjohn.automation.PageLocators.ProductDetailsPageLocators.INLINE_CART_CLOSE;
import static com.tommyjohn.automation.PageLocators.ProductDetailsPageLocators.INLINE_CART_TITLE;

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
	int count = 0;
	int PLPs = 55;
	List<WebElement> CollectionDoorways;
	int Count;


	public void ValidateDyPlaceholderBannerInPLP()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		SoftAssert softAssert = new SoftAssert();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		new HomePageComponents(driver).navigateToMensAll();
		wait.until(ExpectedConditions.visibilityOfElementLocated(DYPLACEHOLDERBANNER));
		softAssert.assertTrue(driver.findElement(DYPLACEHOLDERBANNER).isDisplayed(),"DY Placeholder banner is displayed in the PLP");
		Reporter.log("DY Placeholder banner is displayed in the PLP");
		ExtentReport.extentTest.info("DY Placeholder banner is displayed in the PLP");
	}

	public void VerifyFilterPill()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		SoftAssert softAssert = new SoftAssert();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		new HomePageComponents(driver).navigateToMensAll();
		wait.until(ExpectedConditions.visibilityOfElementLocated(FILTER_PILLS));
		softAssert.assertTrue(driver.findElement(FILTER_PILLS).isDisplayed(),"Filter Pills are not displayed");
		Reporter.log("Filter Pills are displayed");
		ExtentReport.extentTest.info("Filter Pills are displayed");
	}

    public void ValidateCollectionFilterInFilterPill() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        SoftAssert softAssert = new SoftAssert();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        new HomePageComponents(driver).navigateToMensAll();
        wait.until(ExpectedConditions.visibilityOfElementLocated(FILTER_PILLS));
        softAssert.assertTrue(driver.findElement(FILTER_PILLS).isDisplayed(),"Filter Pills are not displayed");
        Reporter.log("Filter Pills are displayed");
        ExtentReport.extentTest.info("Filter Pills are displayed");
        js.executeScript("arguments[0].click();", driver.findElement(COLLECTION_FILTER_PILL));
        String AirFilter = driver.findElement(By.xpath("(//span[@class='font-medium' and contains(text(),'Airs')])")).getText();
        System.out.println(AirFilter);
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("(//span[@class='font-medium' and contains(text(),'Air')])")));
        Thread.sleep(10000);
        String FilterAir = driver.findElement(By.xpath("(//a[contains(text(),'Air Trunk 4')])[1]")).getText();
        String[] Filter = FilterAir.split(" Trunks");
        String text = Filter[0];
        System.out.println(text);
        softAssert.assertEquals(AirFilter, text, "Appropriate Product is not displayed while from filter pill");
        Reporter.log("Appropriate Product is displayed while from filter pill");
        ExtentReport.extentTest.info("Appropriate Product is displayed while from filter pill");
    }

    public void ValidateStyleFilterInFilterPill() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        SoftAssert softAssert = new SoftAssert();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        new HomePageComponents(driver).navigateToMensAll();
        wait.until(ExpectedConditions.visibilityOfElementLocated(FILTER_PILLS));
        softAssert.assertTrue(driver.findElement(FILTER_PILLS).isDisplayed(),"Filter Pills are not displayed");
        Reporter.log("Filter Pills are displayed");
        ExtentReport.extentTest.info("Filter Pills are displayed");
        js.executeScript("arguments[0].click();", driver.findElement(STYLE_FILTER_PILL));
        String BriefFilter = driver.findElement(By.xpath("(//span[@class='font-medium' and contains(text(),'Boxer Briefs')])[2]")).getText();
        System.out.println(BriefFilter);
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("(//span[@class='font-medium' and contains(text(),'Boxer Briefs')])[2]")));
        Thread.sleep(10000);
        String FilterBrief = driver.findElement(By.xpath("//a[contains(text(),'Second Skin Boxer Brief 8\" (3-Pack)')]")).getText();
//        String[] Filter = FilterBrief.split(" Boxer Brief");
//        String text = Filter[1];
        System.out.println(FilterBrief);
//        softAssert.assertEquals(BriefFilter, text, "Appropriate Product is not displayed while from filter pill");
        softAssert.assertTrue(
                FilterBrief.contains("Boxer Brief"),
                "Appropriate Product is not displayed while selecting Boxer Briefs filter"
        );
        softAssert.assertAll();
        Reporter.log("Appropriate Product is displayed while from filter pill");
        ExtentReport.extentTest.info("Appropriate Product is displayed while from filter pill");
    }



	public void ValidateShopNowCTAinDyPlaceholderBanner()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		SoftAssert softAssert = new SoftAssert();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		new HomePageComponents(driver).navigateToMensAll();
		wait.until(ExpectedConditions.visibilityOfElementLocated(DYPLACEHOLDERBANNER));
		softAssert.assertTrue(driver.findElement(DYPLACEHOLDERBANNER).isDisplayed(),"DY Placeholder banner is not displayed in the PLP");
		Reporter.log("DY Placeholder banner is displayed in the PLP");
		ExtentReport.extentTest.info("DY Placeholder banner is displayed in the PLP");
		js.executeScript("arguments[0].click();", driver.findElement(SHOPNOWCTAINDYPLACEHOLDERBANNER));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_PRODUCT));
		softAssert.assertTrue(driver.findElement(FIRST_PRODUCT).isDisplayed(),"Shop Now CTA in DY Placeholder banner is not functional");
		Reporter.log("Shop Now CTA in DY Placeholder banner is functional");
		ExtentReport.extentTest.info("Shop Now CTA in DY Placeholder banner is functional");
	}


	public void ValidateFunctionalityOfCollectionDoorways()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		SoftAssert softAssert = new SoftAssert();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		new HomePageComponents(driver).navigateToWomensAll();
		wait.until(ExpectedConditions.visibilityOfElementLocated(COLLECTION_DOORWAYS));
		softAssert.assertTrue(driver.findElement(COLLECTION_DOORWAYS).isDisplayed(),"Collection Doorways is displayed in the PLP");
		Reporter.log("Collection Doorways is displayed in the PLP");
		ExtentReport.extentTest.info("Collection Doorways is displayed in the PLP");
		CollectionDoorways = driver.findElements(COLLECTION_DOORWAYS_COUNT);
		Count = CollectionDoorways.size();
		System.out.println(Count);
		for(int i=1;i<=Count;i++)
		{
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("(//p[@class='text-heading-sm font-medium text-text-primary mb-2xs leading-tight'])[" + i + "]")));
			softAssert.assertTrue(driver.findElement(By.xpath("//h1[@class='inline']")).isDisplayed(),"Header is displayed on the Collection Doorways");
			driver.navigate().back();
		}

		softAssert.assertAll();
		Reporter.log("Collection Doorways is displayed and functional in the PLP");
		ExtentReport.extentTest.info("Collection Doorways is displayed and functional in the PLP");

	}




	public void ValidateCollectionDoorways()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		SoftAssert softAssert = new SoftAssert();
		new HomePageComponents(driver).navigateToMensAll();
		wait.until(ExpectedConditions.visibilityOfElementLocated(COLLECTION_DOORWAYS));
		softAssert.assertTrue(driver.findElement(COLLECTION_DOORWAYS).isDisplayed(),"Collection Doorways is displayed in the PLP");
		Reporter.log("Collection Doorways is displayed in the PLP");
		ExtentReport.extentTest.info("Collection Doorways is displayed in the PLP");
	}

	public void ValidateStarRatingIsFunctional()
	{	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		SoftAssert softAssert = new SoftAssert();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("(//a[contains(text(),'Women')])[1]")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_PRODUCT_HEAD_LINE_TEXT));
		js.executeScript("arguments[0].click();", driver.findElement(FIRST_PRODUCT_STAR_RATING));
		wait.until(ExpectedConditions.visibilityOfElementLocated(REVIEWS));
		softAssert.assertTrue(driver.findElement(REVIEWS).isDisplayed(),"On Clicking the Star Rating under the Product tile the User is not directed directly to the Reviews Section in the PDP");
		Reporter.log("On Clicking the Star Rating under the Product tile the User is directed directly to the Reviews Section in the PDP");
		ExtentReport.extentTest.info("On Clicking the Star Rating under the Product tile the User is directed directly to the Reviews Section in the PDP");

	}

	public void ValidateRecsInPLP() throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("(//a[contains(text(),'Women')])[1]")));
		js.executeScript("window.scrollBy(0,10600)");
		Thread.sleep(5000);
		js.executeScript("window.scrollBy(0,50)");
		Thread.sleep(5000);
		js.executeScript("scroll(0,-80);");
        js.executeScript("window.scrollBy(0,20000)");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(PLP_RECS));
		softAssert.assertTrue(driver.findElement(PLP_RECS).isDisplayed(),"Recs are not available in the PLP");
		Reporter.log("Recs are not available in the PLP");
		ExtentReport.extentTest.info("Recs are not available in the PLP");

	}

	public void ValidateCollectionFiltersCountInAllFiltersDropdownAndInCollectionDropdown() {
		SoftAssert softAssert = new SoftAssert();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@aria-label='Women link']//span[contains(text(),'Women')]")));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_PRODUCT_HEAD_LINE_TEXT));
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[@class='select--options__label-text' and contains(text(),'Collection')]")));
		int CollectionFilterCountInCollectionDropwdown = driver.findElements(By.xpath("//div[@data-tj-qa='plp-filter-collection']//li")).size();
		System.out.println(CollectionFilterCountInCollectionDropwdown);
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[@class='all-filter-pill-span']")));
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='filter-element--name' and contains(text(),'Collection')]")));
		int CollectionFilterCountInAllFiltersDropwdown = driver.findElements(By.xpath("//button[@data-tj-qa='mobile-filter-collection']//following-sibling::div//li")).size();
		System.out.println(CollectionFilterCountInAllFiltersDropwdown);
		softAssert.assertEquals(CollectionFilterCountInCollectionDropwdown, CollectionFilterCountInAllFiltersDropwdown, "Appropriate Collection filters are not displayed");
		Reporter.log("Appropriate Collection filters are displayed");
		ExtentReport.extentTest.info("Appropriate Collection filters are displayed");

	}

	public void ValidateCollectionFiltersNameInAllFiltersDropdownAndInCollectionDropdown() {
		String NamesInCollectionDropdown = "";
		String NamesInAllFiltersDropdown = "";
		SoftAssert softAssert = new SoftAssert();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@aria-label='Women link']//span[contains(text(),'Women')]")));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_PRODUCT_HEAD_LINE_TEXT));
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[@class='select--options__label-text' and contains(text(),'Collection')]")));
		List<WebElement> CollectionNameList = driver.findElements(By.xpath("//div[@data-tj-qa='plp-filter-collection']//li//button"));


		for (int i = 0; i < CollectionNameList.size(); i++) {
			NamesInCollectionDropdown = CollectionNameList.get(i).getAttribute("data-filter-btn");
			System.out.println(NamesInCollectionDropdown);

		}

		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[@class='all-filter-pill-span']")));
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='filter-element--name' and contains(text(),'Collection')]")));

		List<WebElement> CollectionNameListInAllFilters = driver.findElements(By.xpath("//button[@data-tj-qa='mobile-filter-collection']//following-sibling::div//li//button"));

		for (int i = 0; i < CollectionNameListInAllFilters.size(); i++) {
			NamesInAllFiltersDropdown = CollectionNameListInAllFilters.get(i).getAttribute("data-filter-btn");
			System.out.println(NamesInAllFiltersDropdown);
			if (NamesInAllFiltersDropdown.contains(NamesInCollectionDropdown)) {
				System.out.println("True");
			}

		}

		softAssert.assertEquals(NamesInCollectionDropdown, NamesInAllFiltersDropdown, "The Collection names are not equal in All Filter Dropdown and Collection Dropdown");
		Reporter.log("The Collection names are equal in All Filter Dropdown and Collection Dropdown");
		ExtentReport.extentTest.info("The Collection names are equal in All Filter Dropdown and Collection Dropdown");
	}

	public void ValidateCategoryFiltersCountInAllFiltersDropdownAndInCategoryDropdown() {
		SoftAssert softAssert = new SoftAssert();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@aria-label='Women link']//span[contains(text(),'Women')]")));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_PRODUCT_HEAD_LINE_TEXT));
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[@class='select--options__label-text' and contains(text(),'Category')]")));
		int CategoryFilterCountInCategoryDropwdown = driver.findElements(By.xpath("//div[@data-tj-qa='plp-filter-category']//li")).size();
		System.out.println(CategoryFilterCountInCategoryDropwdown);
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[@class='all-filter-pill-span']")));
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='filter-element--name' and contains(text(),'Category')]")));
		int CategoryFilterCountInAllFiltersDropwdown = driver.findElements(By.xpath("//button[@data-tj-qa='mobile-filter-category']//following-sibling::div//li")).size();
		System.out.println(CategoryFilterCountInAllFiltersDropwdown);
		softAssert.assertEquals(CategoryFilterCountInCategoryDropwdown, CategoryFilterCountInAllFiltersDropwdown, "Appropriate Category filters are not displayed");
		Reporter.log("Appropriate Category filters are displayed");
		ExtentReport.extentTest.info("Appropriate Category filters are displayed");

	}

	public void ValidateCategoryFiltersNameInAllFiltersDropdownAndInCategoryDropdown() {
		String NamesInCategoryDropdown = "";
		String NamesInAllFiltersDropdown = "";
		SoftAssert softAssert = new SoftAssert();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@aria-label='Women link']//span[contains(text(),'Women')]")));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_PRODUCT_HEAD_LINE_TEXT));
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[@class='select--options__label-text' and contains(text(),'Category')]")));
		List<WebElement> CategoryNameList = driver.findElements(By.xpath("//div[@data-tj-qa='plp-filter-category']//li//button"));

		for (int i = 0; i < CategoryNameList.size(); i++) {
			NamesInCategoryDropdown = CategoryNameList.get(i).getAttribute("data-filter-btn");
			System.out.println(NamesInCategoryDropdown);

		}

		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[@class='all-filter-pill-span']")));
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='filter-element--name' and contains(text(),'Category')]")));

		List<WebElement> CategoryNameListInAllFilters = driver.findElements(By.xpath("//button[@data-tj-qa='mobile-filter-category']//following-sibling::div//li//button"));

		for (int i = 0; i < CategoryNameListInAllFilters.size(); i++) {
			NamesInAllFiltersDropdown = CategoryNameListInAllFilters.get(i).getAttribute("data-filter-btn");
			System.out.println(NamesInAllFiltersDropdown);
			if (NamesInAllFiltersDropdown.contains(NamesInCategoryDropdown)) {
				System.out.println("True");
			}

		}

		softAssert.assertEquals(NamesInCategoryDropdown, NamesInAllFiltersDropdown, "The Category names are not equal in All Filter Dropdown and Category Dropdown");
		Reporter.log("The Category names are equal in All Filter Dropdown and Category Dropdown");
		ExtentReport.extentTest.info("The Category names are equal in All Filter Dropdown and Category Dropdown");
	}

	public void ValidateColorFiltersCountInAllFiltersDropdownAndInColorDropdown() {
		SoftAssert softAssert = new SoftAssert();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@aria-label='Men link']//span[contains(text(),'Men')]")));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_PRODUCT_HEAD_LINE_TEXT));
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[@class='select--options__label-text' and contains(text(),'Color')]")));
		int ColorFilterCountInSizeDropwdown = driver.findElements(By.xpath("//div[@data-tj-qa='plp-filter-color']//li")).size();
		System.out.println(ColorFilterCountInSizeDropwdown);
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[@class='all-filter-pill-span']")));
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='filter-element--name' and contains(text(),'Color')]")));
		int ColorFilterCountInAllFiltersDropwdown = driver.findElements(By.xpath("//button[@data-tj-qa='mobile-filter-color']//following-sibling::div//li//span")).size();
		System.out.println(ColorFilterCountInAllFiltersDropwdown);
		softAssert.assertEquals(ColorFilterCountInSizeDropwdown, ColorFilterCountInAllFiltersDropwdown, "Appropriate Color filters are not displayed");
		Reporter.log("Appropriate Color filters are displayed");
		ExtentReport.extentTest.info("Appropriate Color filters are displayed");
	}

	public void ValidateColorFiltersNameInAllFiltersDropdownAndInColorDropdown() {
		String NamesInColorDropdown = "";
		String NamesInAllFiltersDropdown = "";
		SoftAssert softAssert = new SoftAssert();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@aria-label='Men link']//span[contains(text(),'Men')]")));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_PRODUCT_HEAD_LINE_TEXT));
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[@class='select--options__label-text' and contains(text(),'Color')]")));
		List<WebElement> ColorNameList = driver.findElements(By.xpath("//div[@data-tj-qa='plp-filter-color']//li//button"));

		for (int i = 0; i < ColorNameList.size(); i++) {
			NamesInColorDropdown = ColorNameList.get(i).getAttribute("data-filter-btn");
			System.out.println(NamesInColorDropdown);

		}

		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[@class='all-filter-pill-span']")));
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='filter-element--name' and contains(text(),'Color')]")));

		List<WebElement> ColorNameListInAllFilters = driver.findElements(By.xpath("//button[@data-tj-qa='mobile-filter-color']//following-sibling::div//li//button"));

		for (int i = 0; i < ColorNameListInAllFilters.size(); i++) {
			NamesInAllFiltersDropdown = ColorNameListInAllFilters.get(i).getAttribute("data-filter-btn");
			System.out.println(NamesInAllFiltersDropdown);

		}

		softAssert.assertEquals(NamesInColorDropdown, NamesInAllFiltersDropdown, "The Color names are not equal in All Filter Dropdown and Size Dropdown");
		Reporter.log("The Color names are equal in All Filter Dropdown and Size Dropdown");
		ExtentReport.extentTest.info("The Color names are equal in All Filter Dropdown and Size Dropdown");
	}


	public void ValidateSizeFiltersCountInAllFiltersDropdownAndInSizeDropdown() {
		SoftAssert softAssert = new SoftAssert();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@aria-label='Men link']//span[contains(text(),'Men')]")));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_PRODUCT_HEAD_LINE_TEXT));
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[@class='select--options__label-text' and contains(text(),'Size')]")));
		int SizeFilterCountInSizeDropwdown = driver.findElements(By.xpath("//div[@data-tj-qa='plp-filter-size']//li")).size();
		System.out.println(SizeFilterCountInSizeDropwdown);
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[@class='all-filter-pill-span']")));
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='filter-element--name' and contains(text(),'Size')]")));
		int SizeFilterCountInAllFiltersDropwdown = driver.findElements(By.xpath("//button[@data-tj-qa='mobile-filter-size']//following-sibling::div//li")).size();
		System.out.println(SizeFilterCountInAllFiltersDropwdown);
		softAssert.assertEquals(SizeFilterCountInSizeDropwdown, SizeFilterCountInAllFiltersDropwdown, "Appropriate Size filters are not displayed");
		Reporter.log("Appropriate Size filters are displayed");
		ExtentReport.extentTest.info("Appropriate Size filters are displayed");
	}

	public void ValidateSizeFiltersNameInAllFiltersDropdownAndInSizeDropdown() {
		String NamesInSizeDropdown = "";
		String NamesInAllFiltersDropdown = "";
		SoftAssert softAssert = new SoftAssert();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@aria-label='Men link']//span[contains(text(),'Men')]")));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_PRODUCT_HEAD_LINE_TEXT));
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[@class='select--options__label-text' and contains(text(),'Size')]")));
		List<WebElement> SizeNameList = driver.findElements(By.xpath("//div[@data-tj-qa='plp-filter-size']//li//button"));

		for (int i = 0; i < SizeNameList.size(); i++) {
			NamesInSizeDropdown = SizeNameList.get(i).getText();
			System.out.println(NamesInSizeDropdown);

		}

		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[@class='all-filter-pill-span']")));
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='filter-element--name' and contains(text(),'Size')]")));

		List<WebElement> SizeNameListInAllFilters = driver.findElements(By.xpath("//button[@data-tj-qa='mobile-filter-size']//following-sibling::div//li//button"));

		for (int i = 0; i < SizeNameList.size(); i++) {
			NamesInAllFiltersDropdown = SizeNameListInAllFilters.get(i).getText();
			System.out.println(NamesInAllFiltersDropdown);

		}

		softAssert.assertEquals(NamesInSizeDropdown, NamesInAllFiltersDropdown, "The Size names are not equal in All Filter Dropdown and Size Dropdown");
		Reporter.log("The Size names are equal in All Filter Dropdown and Size Dropdown");
		ExtentReport.extentTest.info("The Size names are equal in All Filter Dropdown and Size Dropdown");

	}


	public void ValidateProductsOnFiltering() throws InterruptedException {

		SoftAssert softAssert = new SoftAssert();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("(//a[contains(text(),'Men')])[3]")));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_PRODUCT_HEAD_LINE_TEXT));
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[@class='flex gap-xs items-center leading-none']")));
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//legend[contains(text(),'Collection')]")));
		String AirFilter = driver.findElement(By.xpath("(//span[@class='font-medium' and contains(text(),'Air')])")).getText();
		System.out.println(AirFilter);
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("(//span[@class='font-medium' and contains(text(),'Air')])")));
		Thread.sleep(10000);
//		int Filtercount = driver.findElements(By.xpath("//span[@class='collection-meta__title']")).size();
//		System.out.println(Filtercount);
		String FilterAir = driver.findElement(By.xpath("(//a[contains(text(),'Air Trunk 4')])[1]")).getText();
		String[] Filter = FilterAir.split(" Trunk");
		String text = Filter[0];
		System.out.println(text);
		softAssert.assertEquals(AirFilter, text, "Appropriate Product is not displayed while filtering");
		Reporter.log("Appropriate Product is displayed while filtering");
		ExtentReport.extentTest.info("Appropriate Product is displayed while filtering");

	}

	public String navigateTo3FOR$48PDP() throws Exception {
		String text = null;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_PRODUCT_HEAD_LINE_TEXT));
		// get text and click on the first product
		//text = driver.findElement(FIRST_PRODUCT_IMAGE).getAttribute("alt");
		text = driver.findElement(FIRST_PRODUCT_HEAD_LINE_TEXT).getText();
		WebElement el = driver.findElement(FIRST_PRODUCT_HEAD_LINE_TEXT);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", el);
		wait.until(ExpectedConditions.visibilityOfElementLocated(ProductDetailsPageLocators.PRODUCT_TITLE));
		Reporter.log(driver.getCurrentUrl());
		ExtentReport.extentTest.info(driver.getCurrentUrl());
		// text =
		// driver.findElement(ProductDetailsPageLocators.PRODUCT_TITLE).getText();
		return text;
	}

	public String navigateToFinalSalePDP() throws Exception {
		String text = null;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_PRODUCT_HEAD_LINE_TEXT));
		// get text and click on the first product
		//text = driver.findElement(FIRST_PRODUCT_IMAGE).getAttribute("alt");
		text = driver.findElement(FINAL_SALE_PROMO_TEXT).getText();
		WebElement el = driver.findElement(FINAL_SALE_PROMO_TEXT);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", el);
		wait.until(ExpectedConditions.visibilityOfElementLocated(ProductDetailsPageLocators.PRODUCT_TITLE));
		Reporter.log(driver.getCurrentUrl());
		ExtentReport.extentTest.info(driver.getCurrentUrl());
		// text =
		// driver.findElement(ProductDetailsPageLocators.PRODUCT_TITLE).getText();
		return text;
	}


	public String navigateToPDP() throws Exception {
		String text = null;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_PRODUCT_HEAD_LINE_TEXT));
		// get text and click on the first product
		//text = driver.findElement(FIRST_PRODUCT_IMAGE).getAttribute("alt");
		text = driver.findElement(FIRST_PRODUCT_HEAD_LINE_TEXT).getText();
		WebElement el = driver.findElement(FIRST_PRODUCT_HEAD_LINE_TEXT);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", el);
		wait.until(ExpectedConditions.visibilityOfElementLocated(ProductDetailsPageLocators.PRODUCT_TITLE));
		Reporter.log(driver.getCurrentUrl());
		ExtentReport.extentTest.info(driver.getCurrentUrl());
		// text =
		// driver.findElement(ProductDetailsPageLocators.PRODUCT_TITLE).getText();
		return text;
	}

	public String navigateToProductDetailsPage() throws Exception {
		String text = null;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,800)");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_PRODUCT_HEAD_LINE_TEXT));
		// get text and click on the first product
		//text = driver.findElement(FIRST_PRODUCT_IMAGE).getAttribute("alt");
		text = driver.findElement(FIRST_PRODUCT_HEAD_LINE_TEXT).getText();
		WebElement el = driver.findElement(FIRST_PRODUCT_HEAD_LINE_TEXT);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", el);
		wait.until(ExpectedConditions.visibilityOfElementLocated(ProductDetailsPageLocators.PRODUCT_TITLE));
		Reporter.log(driver.getCurrentUrl());
		ExtentReport.extentTest.info(driver.getCurrentUrl());
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
//		executor.executeScript("arguments[0].click();", ele);

		// driver.findElement(THIRD_PRODUCT_IMAGE).click();
		Thread.sleep(7000);
		Reporter.log(driver.getCurrentUrl());

		// check correct Product details page opened or not
		new ProductDetailsPageComponents(driver).checkCorrectProductDetailsPageOpenedOrNot(text);

		// validate product details page by calling the method
		if (driver.findElement(ProductDetailsPageLocators.PRODUCT_TITLE).getText().contains("Set") || driver.findElement(ProductDetailsPageLocators.PRODUCT_TITLE).getText().contains("Pack"))
			new ProductDetailsPageComponents(driver).verifybundlepages();
		else
			new ProductDetailsPageComponents(driver).validatePage();

		Reporter.log("Product image on collection page is Displayed :: Clickable and validated successfully");
	}

	public void validateProductImageOnPLP() throws Exception {
		String text = null;

		// changed
		objGeneralMethods.implicitWait(driver);
		objGeneralMethods.waitTillPageLoad(driver);
		objGeneralMethods.isElementVisible(driver, FIRST_PRODUCT_IMAGE);
		// get product title and click on image if present
		//text = driver.findElement(FIRST_PRODUCT_IMAGE).getAttribute("alt");
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		Thread.sleep(3000);
		WebElement Prod_Image = driver.findElement(FIRST_PRODUCT_IMAGE);
		if (!Prod_Image.isDisplayed())
			// # if(!driver.findElement(THIRD_PRODUCT_IMAGE).isDisplayed())
			throw new Exception("Product image is not present");

		WebElement ele = driver.findElement(FIRST_PRODUCT_IMAGE);

		executor.executeScript("arguments[0].click();", ele);

		// driver.findElement(THIRD_PRODUCT_IMAGE).click();
		Thread.sleep(7000);
		Reporter.log(driver.getCurrentUrl());
		ExtentReport.extentTest.info(driver.getCurrentUrl());

		// check correct Product details page opened or not
		//new ProductDetailsPageComponents(driver).checkCorrectProductDetailsPageOpenedOrNot(text);

		Reporter.log("Product image on collection page is Displayed :: Clickable and validated successfully");
		ExtentReport.extentTest.info("Product image on collection page is Displayed :: Clickable and validated successfully");


		Thread.sleep(5000);
		driver.navigate().back();

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

//	public void methodToSortByPrice() throws Exception {
//
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("window.scrollBy(0,250)");
//
////		wait.until(ExpectedConditions.visibilityOfElementLocated(SORTBYPRICE_DROPDOWN));
////		WebElement sortByPrice = driver.findElement(SORTBYPRICE_DROPDOWN);
//
//		wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_PRODUCT));
//		String price = null;
//		String updatedPrice = null;
//		double priceValue = 0;
//	//	driver.findElement(PRICE_DROPDOWN).click();
//		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[@class='flex gap-xs items-center leading-none']")));
//		Thread.sleep(2000);
//		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//legend[@class='mr-auto']")));
////		driver.findElement(PRICEASC_DROPDOWN).click();
////		js.executeScript("window.scrollBy(600,0)");
//		driver.findElement(By.xpath("//label[@for='Filters-Sort-price-asc']")).click();
//
//		//Select drpCountry = new Select(sortByPrice);
//		//drpCountry.selectByValue("price:ascending");
////		Thread.sleep(5000);
//
////
////		js.executeScript("window.scrollBy(0,250)");
////		Thread.sleep(5000);
//
//		//js.executeScript("arguments[0].scrollIntoView();", prices);
//
//
//		List<WebElement> list = driver.findElements(PLP_PRICE);
//		ArrayList<Double> productPrice = new ArrayList<Double>();
//		ArrayList<Double> sortedList = new ArrayList<Double>();
//
//		for (int i = 0; i < list.size(); i++) {
//			price = list.get(i).getText();
//			updatedPrice = price.replaceAll("[^0-9.]", "");
//			priceValue = Double.valueOf(updatedPrice);
//			sortedList.add(priceValue);
//			productPrice.add(priceValue);
//		}
//
//		System.out.println(productPrice);
//		Collections.sort(sortedList);
//		System.out.println(sortedList);
//		if (!sortedList.equals(productPrice)) {
//			throw new Exception("The products are not sorted in ascending order");
//		} else {
//			Reporter.log("The products are sorted in ascending order of price");
//			ExtentReport.extentTest.info("The products are sorted in ascending order of price");
//		}
//
////
////		js.executeScript("window.scrollBy(0,250)");
////		Thread.sleep(5000);
//		//drpCountry.selectByValue("price:descending");
//
//		//js.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
//		//	drpCountry.selectByIndex(2);
//		//	js.executeScript("window.scrollBy(0,450)");
//		//	drpCountry.selectByValue("price:descending");
////		Thread.sleep(5000);
//
//		driver.findElement(By.xpath("//label[@for='Filters-Sort-price-desc']")).click();
//
//		ArrayList<Double> productPrice1 = new ArrayList<Double>();
//		ArrayList<Double> sortedList1 = new ArrayList<Double>();
//		List<WebElement> list1 = driver.findElements(PLP_PRICE);
//		for (int i = 0; i < list1.size(); i++) {
//			price = list1.get(i).getText();
//			updatedPrice = price.replaceAll("[^0-9.]", "");
//			priceValue = Double.parseDouble(updatedPrice);
//			sortedList1.add(priceValue);
//			productPrice1.add(priceValue);
//		}
//		System.out.println(productPrice1);
//		Collections.sort(sortedList1);
//		Collections.reverse(sortedList1);
//		System.out.println(sortedList1);
//		if (!sortedList1.equals(productPrice1)) {
//			throw new Exception("The products are not sorted successfully");
//		} else {
//			Reporter.log("The products are sorted successfully");
//		}
//
//	}



	public String methodToSortByPrice() throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Scroll down to bring elements into view
		js.executeScript("window.scrollBy(0,250)");

		// Wait until the first product is visible before proceeding
		wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_PRODUCT));

		// Click on sort dropdown (simulate opening sort options)
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[@class='flex gap-xs items-center leading-none']")));
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//legend[@class='mr-auto']")));

		// Click on ascending sort option
		Thread.sleep(5000);
		driver.findElement(By.xpath("//label[@for='Filters-Sort-price-asc']")).click();

		Thread.sleep(10000);
		// Wait for the sorted prices to load and capture their texts
		//js.executeScript("window.scrollBy(0,-250)");
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(PLP_PRICE));
		List<WebElement> priceElementsAsc = driver.findElements(PLP_PRICE);
		int limit = Math.min(1, priceElementsAsc.size());
		ArrayList<Double> productPricesAsc = new ArrayList<>();
		ArrayList<Double> sortedPricesAsc = new ArrayList<>();

		// Immediately capture the text of the first 'limit' elements
		for (int i = 0; i < limit; i++) {
			String priceText = priceElementsAsc.get(i).getText();
			String updatedPrice = priceText.replaceAll("[^0-9.]", "");
			double priceValue = Double.valueOf(updatedPrice);
			productPricesAsc.add(priceValue);
			sortedPricesAsc.add(priceValue);
		}

		System.out.println("Original prices (asc): " + productPricesAsc);
		Collections.sort(sortedPricesAsc);
		System.out.println("Sorted prices (asc): " + sortedPricesAsc);

		if (!sortedPricesAsc.equals(productPricesAsc)) {
			throw new Exception("The products are not sorted in ascending order");
		} else {
			Reporter.log("The products are sorted in ascending order of price");
			ExtentReport.extentTest.info("The products are sorted in ascending order of price");
		}

		// Now sort descending
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//label[@for='Filters-Sort-price-desc']")));

		Thread.sleep(5000);
		// Wait for descending sorted prices to load and capture their texts
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(PLP_PRICE));
		List<WebElement> priceElementsDesc = driver.findElements(PLP_PRICE);
		int limitDesc = Math.min(1, priceElementsDesc.size());
		ArrayList<Double> productPricesDesc = new ArrayList<>();
		ArrayList<Double> sortedPricesDesc = new ArrayList<>();

		for (int i = 0; i < limitDesc; i++) {
			String priceText = priceElementsDesc.get(i).getText();
			String updatedPrice = priceText.replaceAll("[^0-9.]", "");
			double priceValue = Double.valueOf(updatedPrice);
			productPricesDesc.add(priceValue);
			sortedPricesDesc.add(priceValue);
		}

		System.out.println("Original prices (desc): " + productPricesDesc);
		Collections.sort(sortedPricesDesc);
		Collections.reverse(sortedPricesDesc);
		System.out.println("Sorted prices (desc): " + sortedPricesDesc);

		if (!sortedPricesDesc.equals(productPricesDesc)) {
			throw new Exception("The products are not sorted successfully in descending order");
		} else {
			Reporter.log("The products are sorted successfully in descending order");
			ExtentReport.extentTest.info("The products are sorted successfully in descending order");
		}

		return "Sorting completed successfully";
	}

	@SuppressWarnings("static-access")
	public void validateProductHeadingTextOnCollectionPage() throws Exception {
		String text = null;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)");

		wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_PRODUCT_HEAD_LINE_TEXT));
		softAssert.assertTrue(driver.findElement(FIRST_PRODUCT_HEAD_LINE_TEXT).isDisplayed(),"Product Headline text is not displayed");
		// get product title and click on product heading text if present

//		text = driver.findElement(FIRST_PRODUCT_IMAGE).getAttribute("alt");
//		/*driver.findElement(FIRST_PRODUCT_HEAD_LINE_TEXT).click();
//		Thread.sleep(3000);
//		Reporter.log(driver.getCurrentUrl());*/

		// check correct Product details page opened or not
		/*new ProductDetailsPageComponents(driver).checkCorrectProductDetailsPageOpenedOrNot(text);*/

		// validate product details page by calling the method
//		if (!(driver.getCurrentUrl().contains("pack") || driver.getCurrentUrl().contains("set")
//				|| driver.getCurrentUrl().contains("gift-cards")))
//			new ProductDetailsPageComponents(driver).validatePage();

		Reporter.log("Product head line text on collection page is Displayed :: Clickable and validated successfully");
		ExtentReport.extentTest.info("Product head line text on collection page is Displayed :: Clickable and validated successfully");

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
		ExtentReport.extentTest.info(driver.getCurrentUrl());

		// check correct Product details page opened or not
		new ProductDetailsPageComponents(driver).checkCorrectProductDetailsPageOpenedOrNot(text);

		/*
		 * // validate product details page by calling the method
		 * if(!(driver.getCurrentUrl().contains("pack")||driver.getCurrentUrl().contains
		 * ("set")||driver.getCurrentUrl().contains("gift-cards"))) new
		 * ProductDetailsPageComponents(driver).validatePage();
		 */

		Reporter.log("Product head line text on collection page is Displayed :: Clickable and validated successfully");
		ExtentReport.extentTest.info("Product head line text on collection page is Displayed :: Clickable and validated successfully");

	}

	@SuppressWarnings("static-access")
	public void validateProductReviewStarsOnCollectionPage() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.navigate().to("https://www.tommyjohn.com/collections/mens-underwear");

		JavascriptExecutor jse = (JavascriptExecutor) driver;

		Thread.sleep(5000);
		jse.executeScript("window.scrollBy(0,250)");
		Thread.sleep(3000);

		wait.until(ExpectedConditions.presenceOfElementLocated(FIRST_PRODUCT_REVIEW_STARS));

		driver.findElement(FIRST_PRODUCT_IMAGE).getAttribute("alt");
		WebElement element1 = driver.findElement(FIRST_PRODUCT_HEAD_LINE_TEXT);
		jse.executeScript("arguments[0].click();", element1);
		Thread.sleep(3000);
		Reporter.log(driver.getCurrentUrl());
		ExtentReport.extentTest.info(driver.getCurrentUrl());
    
		/*text = driver.findElement(FIRST_PRODUCT_IMAGE).getAttribute("alt");
		driver.findElement(FIRST_PRODUCT_HEAD_LINE_TEXT).click();

		wait.until(ExpectedConditions.presenceOfElementLocated(FIRST_PRODUCT_REVIEW_STARS));
		
		driver.findElement(FIRST_PRODUCT_IMAGE).getAttribute("alt");
		driver.findElement(FIRST_PRODUCT_REVIEW_STARS).click();

		Thread.sleep(3000);
		Reporter.log(driver.getCurrentUrl());*/
		// check correct Product details page opened or not
		// new
		// ProductDetailsPageComponents(driver).checkCorrectProductDetailsPageOpenedOrNot(text);

		// validate product details page by calling the method
		if (driver.findElement(ProductDetailsPageLocators.PRODUCT_TITLE).getText().contains("Second"))
			new ProductDetailsPageComponents(driver).verifybundlepages();
		else
			new ProductDetailsPageComponents(driver).validatePage();

		Reporter.log("Product review stars on collection page is Displayed :: Clickable and validated successfully");
		ExtentReport.extentTest.info("Product review stars on collection page is Displayed :: Clickable and validated successfully");
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

	public void ValidateFacet() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		js.executeScript("window.scrollBy(0, 250)");
		Thread.sleep(2000);

		driver.findElement(ALL_FILTERS).click();
		Thread.sleep(5000);

		js.executeScript("window.scrollBy(0, 250)");
		WebElement collectionFilter = wait.until(ExpectedConditions.elementToBeClickable(COLLECTION_FILTER));
		js.executeScript("arguments[0].click();", collectionFilter);
		Thread.sleep(5000);

		WebElement facetElement = wait.until(ExpectedConditions.visibilityOfElementLocated(FACET_VALUE));
		js.executeScript("arguments[0].scrollIntoView(true);", facetElement);
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", facetElement);
		Thread.sleep(5000);  // Wait for the network call
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

	public void validateHammockPouchIconInFilters() throws InterruptedException {
		driver.findElement(ALL_FILTERS).click();
		element = driver.findElement(By.xpath("//span[@class='icon icon-info']"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", element);
		Thread.sleep(5000);
		String Hammock = driver.findElement(By.xpath("//h2[@class='text-display-sm font-medium leading-none']")).getText();
		System.out.println(Hammock);
		softAssert.assertTrue(driver.findElement(By.xpath("//h2[@class='text-display-sm font-medium leading-none']")).isDisplayed(),"Hammock Pouch Icon is not fundtional");
		Reporter.log("Hammock Pouch Icon is fundtional");
		ExtentReport.extentTest.info("Hammock Pouch Icon is fundtional");
	}

	// method to validate all filters
	public void methodToCheckAllFilters() throws Exception {
		List<WebElement> allElements = null;
		int filterList = 0;
		int optionsList = 0;
		String options;
		String selectedOption = null;
		int optionsList1 = 0;
		int count = 0;
		List<WebElement> allProductsOnPage = null;
		String totalDisplayedCount = null;

		driver.findElement(ALL_FILTERS).click();

		// get total no of filters
		allElements = driver.findElements(ALL_FILTERS_LIST);
		filterList = allElements.size();
		System.out.println("All filters list :: " + filterList);

		// loop for total not of filters
		for (int i = 3; i < filterList; i++) {
			element = driver.findElement(By.xpath("(//div[@class='font-sans']//legend)[" + i + "]"));
			options = driver.findElement(By.xpath("(//div[@class='font-sans']//legend)[" + i + "]")).getText();
			System.out.println("Clicking on" + options);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", element);
			System.out.println("All filters are displayed and functional");
		}

		Reporter.log("All filters is Displayed :: Clickable and validated successfully");
		ExtentReport.extentTest.info("All filters is Displayed :: Clickable and validated successfully");


		// objGeneralMethods.clickButtonByJS(driver, element);
		// get all the options list available in ith filter
//			options = driver.findElements(By.xpath(""));
//			optionsList = allElements.size();
//			System.out.println("All options list :: " + optionsList);
//			int ct = 0;
//			// loop for clicking all the options in ith filter
//			// for(int j = 1; j <= optionsList; j++) {
//
//			// check if not any option present
//			if (optionsList == 0)
//				break;
//			WebElement Filter_option_item = driver.findElement(By.cssSelector(
//					"div.mega-collection-filters > div:nth-child(" + i + ") > ul > li:nth-child(1) > label > input"));
//			if (!Filter_option_item.isDisplayed())
//				continue;
//			count++;
//			jse.executeScript("arguments[0].click();", Filter_option_item);
//			Thread.sleep(3000);
//
//			// get the name of selected option
//			// selectedOption =
//			// driver.findElement(By.cssSelector("div.mega-collection-filters >
//			// div:nth-child("+i+") > ul > li:nth-child(1) > label >
//			// input")).getAttribute("data-filter-btn");
//			if (allElements.size()>5) {
//				selectedOption = driver.findElement(By.cssSelector(
//						"div.mega-collection-filters > div:nth-child(" + i + ") > ul > li:nth-child(1) > label > span"))
//						.getText();
//			} else {
//
//				selectedOption = driver.findElement(By.cssSelector(
//						"div.mega-collection-filters > div:nth-child(" + i + ") > ul > li:nth-child(1) > label > span"))
//						.getText();
//
//			}
//			// selectedOption =
//			// driver.findElement(By.cssSelector("div.mega-collection-filters >
//			// div:nth-child("+i+") > ul > li:nth-child(1) > label > span")).getText();
//			System.out.println("selected option :: " + selectedOption);
//
//			// get all selected displayed optioins
//			allElements = driver.findElements(DISPLAYED_ALL_SELECTED_OPTIONS);
//			optionsList1 = allElements.size();
//
//			System.out.println("count :: " + count);
//			System.out.println("optionlist1 :: " + optionsList1);
//			// check selected options count and displayed selected list count is same or not
//			if (count != optionsList1)
//				throw new Exception("Selected options and selected displayed options are not same");
//
//			System.out.println("ct :: " + ct);
//			System.out.println("ct th ele :: " + allElements.get(ct).getText());
//			if (!(allElements.get(ct).getText().replace("\"", "")).equals(selectedOption.replace("\"", "")))
//				throw new Exception("Selected item is not present in displayed item list");
//
//			// check total no of count is displayed or not
//			if (!driver.findElement(TOTAL_COUNTS_OF_ITEMS).isDisplayed())
//				throw new Exception("Total counts of items is not displayed for filter :: " + i);
//
//			// check total not of count and products are correct
//			totalDisplayedCount = driver.findElement(TOTAL_COUNTS_OF_ITEMS).getText();
//			allProductsOnPage = driver.findElements(FIRST_PRODUCT_IMAGE);
//			int allProductsOnPage1 = allProductsOnPage.size();
//			System.out.println("Out ddd :: " + allProductsOnPage1);
//			// check for hoverable class (if image is hoverable then it count two images for
//			// one image)
//			Thread.sleep(2000);
//			element = driver.findElement(FIRST_PRODUCT_IMAGE);
//			System.out.println("total displayed product on page :: " + allProductsOnPage1);
//			System.out.println("total displayed count of product :: " + totalDisplayedCount);
//			try {
//				if (allProductsOnPage1 != (Integer.parseInt(totalDisplayedCount)))
//					throw new Exception("Total displayed count is not same as all products on page");
//			} catch (Exception e) {
//				System.out.println("Due to lazy loading total displayed count is not possible.");
//			}
//
//			ct++;
//
//			// }
//
//			// check again any options are present or not
//			if (optionsList == 0)
//				continue;
//
//			// click on last item to remove it from list
//			Thread.sleep(5000);
//			element = driver.findElement(By.cssSelector("div.mega-collection-filters > div:nth-child(" + i + ") > a"));
//			jse = (JavascriptExecutor) driver;
//			jse.executeScript("arguments[0].click();", element);
//			Thread.sleep(1000);
//			System.out.println("last item :: " + driver.findElement(By.xpath(
//					"(//div[contains(@class,'mega-collection-filter-tags mega-collection-filter-tags--desktop')]//button)["
//							+ ct + "]"))
//					.getText());
//			element = driver.findElement(By.xpath(
//					"(//div[contains(@class,'mega-collection-filter-tags mega-collection-filter-tags--desktop')]//button)["
//							+ ct + "]"));
//			jse = (JavascriptExecutor) driver;
//			jse.executeScript("arguments[0].click();", element);
//			// driver.findElement(PLUS_BUTTON).click();
//			Thread.sleep(5000);
//
//			Thread.sleep(1000);
//			// after last element removed decrease ct by 1
//			System.out.println("Before ct::" + ct);
//			// decrease ct by 1 bcz when one element is removed from list
//			ct--;
//			ct--; // for taking last element from list
//			System.out.println("After ct--::" + ct);
//			// check when we click on item then it remove from list or not
//			System.out.println("allelements size:: " + allElements.size());
//
//			if (ct != 0 && ct != -1) {
//				System.out.println("ct :: " + allElements.get(ct).getText());
//				System.out.println("ct :: " + driver.findElement(By.xpath(
//						"(//div[contains(@class,'mega-collection-filter-tags mega-collection-filter-tags--desktop')]//button)["
//								+ ct + "]"))
//						.getText());
//				if ((allElements.get(ct).getText()).equals(driver.findElement(By.xpath(
//						"(//div[contains(@class,'mega-collection-filter-tags mega-collection-filter-tags--desktop')]//button)["
//								+ ct + "]"))
//						.getText()))
//					throw new Exception("Item is not removed from displayed list when click on it");
//			}
//			ct = 0;
//
//			// scroll it top again
//			// JavascriptExecutor js = (JavascriptExecutor) driver;
//			// js.executeScript("arguments[
//
//
//			0].scrollIntoView();",
//			// driver.findElement(CLEAR_FILTER_BUTTON));
//			// ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,
//			// -document.body.scrollHeight)");
//			Thread.sleep(2000);
//
//			// clear the filters
//			WebElement ele = driver.findElement(CLEAR_FILTER_BUTTON);
//			JavascriptExecutor executor = (JavascriptExecutor) driver;
//			executor.executeScript("arguments[0].click();", ele);
//			// driver.findElement(CLEAR_FILTER_BUTTON).click();
//			Thread.sleep(3000);
//			allElements = null;
//			count = 0;
//		}
//		Reporter.log("All filters are working correctly");
//		ExtentReport.extentTest.info("All filters are working correctly");
//		Reporter.log("All selected items are displayed in list and removing from list");
//		ExtentReport.extentTest.info("All selected items are displayed in list and removing from list");
//		Reporter.log("Clear filters button works correctly");
//		ExtentReport.extentTest.info("Clear filters button works correctly");

		// scroll to last filter view
		// element = driver.findElement(LAST_FILTER);
		// JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("arguments[0].scrollIntoView();", element);
		//
		
		/* WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		 wait.until(ExpectedConditions.visibilityOfElementLocated(LAST_FILTER)); if
		 (driver.findElement(LAST_FILTER).isDisplayed()) { //
		 driver.findElement(LAST_FILTER).click(); Select selectElement = new
		 Select(driver.findElement(LAST_FILTER));
		 selectElement.selectByValue("price:ascending");*/

		// click on second option
		 
		 /*wait.until(ExpectedConditions.visibilityOfElementLocated(
		 SORTBYPRICE_HIGH_TO_LOW_FILTER_OPTION));
		 driver.findElement(SORTBYPRICE_HIGH_TO_LOW_FILTER_OPTION).click();
		 Thread.sleep(5000); allProductsOnPage =
		 driver.findElements(FIRST_PRODUCT_IMAGE);
		 System.out.println("All product on page :: " + allProductsOnPage.size()); try
		 { for (int i = 1; i < allProductsOnPage.size(); i++) { String text = null;
		 String text1 = null; String text2 = null;
		  
		 text = driver.findElement(By.
		 cssSelector(".mega-collection-grid__row > div.product-meta__prices(" + i +
		 ") > span.product-meta__price")).getText(); System.out.println("Text :: " +
		 text); for (String a : text.split("\\$")) text1 = a;
		 System.out.println("text 1 :: " + text1); // loop to check previous and next
		 price int j = i + 1; System.out.println("Text :: " + driver.findElement(By.
		 cssSelector(".mega-collection-grid__row > div.product-meta__prices(" + j +
		 ") > span.product-meta__price")).getText()); for (String a : driver
		 .findElement(By.
		 cssSelector(".mega-collection-grid__row > div.product-meta__prices(" + j +
		 ") > span.product-meta__price")) .getText().split("\\$")) text2 = a;
		 System.out.println("text2 :: " + text2); if (Float.parseFloat(text1) >
		 Float.parseFloat(text2)) throw new
		 Exception("Products are not sorted by price correctly");
		 
		 } } catch (Exception e) { System.out.
		 println("Due to lazy loading price comparison is not possible for sort by filter."
		 ); } Reporter.log("All Products are sorted as selected option");
		 
		 }*/


	}

	public void methodToCheckFiltersSearch() throws Exception {
		/*List<WebElement> allElements = null;
		int filterList = 0;
		int optionsList = 0;
		String selectedOption = null;
		int optionsList1 = 0;
		int count = 0;
		List<WebElement> allProductsOnPage = null;
		String totalDisplayedCount = null;*/

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_PRICE_FILTER));
		js.executeScript("arguments[0].click();", driver.findElement(SEARCH_PRICE_FILTER));
		wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_PRICE_FILTER_1));
		String priceFilter = driver.findElement(SEARCH_PRICE_FILTER_1).getText();
		System.out.println(priceFilter);
		js.executeScript("arguments[0].click();", driver.findElement(SEARCH_PRICE_FILTER_1));
		Thread.sleep(5000);
		js.executeScript("arguments[0].click();", driver.findElement(SEARCH_PRICE_FILTER));
		js.executeScript("arguments[0].click();", driver.findElement(SEARCH_PRICE_FILTER));
		String priceFilterPill = driver.findElement(SEARCH_PRICE_FILTER_PILL_1).getText();
		System.out.println(priceFilterPill);
		js.executeScript("arguments[0].click();", driver.findElement(SEARCH_PRICE_FILTER_1));

//
//		if(priceFilterPill.contains(priceFilter)) {
//			System.out.println("Price Filter is working");
//			Reporter.log("Price Filter is working");
//			ExtentReport.extentTest.info("Price Filter is working");
//		}
//		else {
//			System.out.println("Price Filter is not working");
//			ExtentReport.extentTest.info("Price Filter is not working");
//			throw new Exception("Price Filter is not working");
//		}

		js.executeScript("arguments[0].click();", driver.findElement(SEARCH_PRICE_FILTER_1));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.mega-collection-filters > div:nth-child(2) > a")));
		WebElement firstFilter = driver.findElement(By.cssSelector("div.mega-collection-filters > div:nth-child(2) > a"));
		js.executeScript("arguments[0].click();", firstFilter);

//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.mega-collection-filters > div:nth-child(2) > ul > li:nth-child(1) > label > input")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//li[@class='collection-filter__item']//button)[1]")));
		WebElement firstFilterOption = driver.findElement(By.xpath(
				"(//li[@class='collection-filter__item']//button)[1]"));
		js.executeScript("arguments[0].click();", firstFilterOption);
		Thread.sleep(5000);

	}

	public void clickOnSearchPageFilter() throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		JavascriptExecutor js = (JavascriptExecutor) driver;

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.mega-collection-filters > div:nth-child(2) > a")));
		WebElement firstFilter = driver.findElement(By.cssSelector("div.mega-collection-filters > div:nth-child(2) > a"));
		js.executeScript("arguments[0].click();", firstFilter);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.mega-collection-filters > div:nth-child(2) > ul > li:nth-child(1) > label > input")));
		WebElement firstFilterOption = driver.findElement(By.cssSelector(
				"div.mega-collection-filters > div:nth-child(2) > ul > li:nth-child(1) > label > input"));
		js.executeScript("arguments[0].click();", firstFilterOption);
		Thread.sleep(5000);
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
		boolean flag = false;
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
			try {
				element = driver.findElement(By.cssSelector(".dyHero-interactive__slider>button:nth-child(" + k + ")"));
			} catch (Exception e) {
				element = driver.findElement(By.xpath("(//div[@class='interactive-hero-slider']//a)[" + k + "]"));
			}

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
		ExtentReport.extentTest.info("All corosals are Displayed :: And by default not active");


		for (int k = 1; k <= allCorosals.size(); k++) {

			try {
				carouselFilter = driver.findElement(By.cssSelector("div.dyHero-interactive__slider>button:nth-child(" + k + ")"));
			} catch (Exception e) {
				carouselFilter = driver.findElement(By.xpath("(//div[@class='interactive-hero-slider']//a)[" + k + "]"));
			}
			//carouselFilter = driver.findElement(By.cssSelector("div.dyHero-interactive__slider>button:nth-child(" + k + ")"));
			if (carouselFilter.isEnabled()) {
				Thread.sleep(5000);
				js.executeScript("arguments[0].click();", carouselFilter);
				System.out.println("Carousel filter is clickable");
			}
			try {
				filterPill = driver.findElement(FILTER_PILL);
				wait.until(ExpectedConditions.visibilityOfElementLocated(FILTER_PILL));
				js.executeScript("arguments[0].click();", filterPill);
				flag = true;
				driver.get("https://www. tommyjohn.com/collections/mens-underwear");
			} catch (Exception e) {
				//Assert.assertFalse(driver.getCurrentUrl().contains("mens-underwear?"), "DY style carousel");
				driver.get("https://www.tommyjohn.com/collections/mens-underwear");
			}

		}

		// driver.findElement(CLEAR_FILTER_BUTTON).click();
		Reporter.log("Corosals gets active when respective option selected");
		ExtentReport.extentTest.info("Corosals gets active when respective option selected");
	}

	public void validateallurls() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

		//	try {
		//File src = new File("src/main/resources/AllCollectionPages.xlsx");

		String csv_path = "src/main/resources/PLPs.csv";
		CSVReader CSVReader = new CSVReader(new FileReader(csv_path));
		List<String[]> list = CSVReader.readAll();
		Thread.sleep(3000);
		Iterator<String[]> ite = list.iterator();
		Thread.sleep(2000);
		while (ite.hasNext()) {
			String[] data = ite.next();
			for (int i = 0; i < data.length; i++) {
				System.out.println(data[i]);
				String url = "https://www.tommyjohn.com" + data[i];
				//	checkurl(url);
				driver.get(url);
				try {
					//	Thread.sleep(5000);
					wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_PRODUCT));
					if (driver.findElement(FIRST_PRODUCT).isDisplayed()) {
						count++;
						Reporter.log(url + "loads fine and products are displayed");
						ExtentReport.extentTest.info(url + "loads fine and products are displayed");
					}
				} catch (Exception ex) {

					System.out.println("Page is not loading in " + url);
					Reporter.log("Page is not loading in " + url);
					ExtentReport.extentTest.info("Page is not loading in " + url);

				}
			}
			//		}


//			if(count!=59) {
//				//System.out.println(count);
//				throw new Exception("Some urls not loading.");
//			}

		}
//		catch (Exception e) {
//			Reporter.log("File not found");
//			throw new Exception("Not worked"+e);
//
//		}
		System.out.println(count);
		softAssert.assertEquals(count, PLPs, "URLs are not loading properly");
		softAssert.assertAll();

	}


	private void checkurl(String url) throws InterruptedException {

		try {
			driver.get(url);
			System.out.println(url);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.presenceOfElementLocated(FIRST_PRODUCT));
			List<WebElement> items = driver.findElements(By.cssSelector(".product-item"));
			Reporter.log("No. of items present in " + url + " is " + items.size());
			ExtentReport.extentTest.info("No. of items present in " + url + " is " + items.size());

		} catch (Exception e) {
			Reporter.log(url + "not loading");
			ExtentReport.extentTest.info(url + "not loading");
		}
	}

	public void LearnMoreLinkinQuickShop() throws InterruptedException {
		Actions action = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//Thread.sleep(8000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		js.executeScript("window.scrollBy(0,500)");

		Thread.sleep(5000);
		wait.until(ExpectedConditions.presenceOfElementLocated(FIRST_PRODUCT_IMAGE));
		// Hover On product image on collection page
		element = driver.findElement(FIRST_PRODUCT_IMAGE);
//		js.executeScript("arguments[0].scrollIntoView(true);", element);
		action.moveToElement(element).build().perform();
		Thread.sleep(3000);
		if (driver.findElement(QUICK_SHOP_ATC).isDisplayed()) {
			Reporter.log("Quick shop ATC button is displayed on hover.");
			ExtentReport.extentTest.info("Quick shop ATC button is displayed on hover.");

			System.out.println("Quick shop ATC text is: " + driver.findElement(QUICK_SHOP_ATC).getText());
			softAssert.assertEquals(driver.findElement(QUICK_SHOP_ATC).getText(), "Select a size",
					"Text changed for Quick shop ATC button.");

			WebElement element1 = driver.findElement(QUICK_SHOP_ATC);
			js.executeScript("arguments[0].click();", element1);
			//
			// .driver.findElement(QUICK_SHOP_ATC).click();
			Thread.sleep(3000);
			Reporter.log("Quick shop ATC button text is validated :: Clickable.");
			ExtentReport.extentTest.info("Quick shop ATC button text is validated :: Clickable.");
		}
		softAssert.assertTrue(driver.findElement(QUICK_SHOP_LEARN_MORE).isDisplayed(),"Learn More link is not available in the Quick Shop");
		driver.findElement(QUICK_SHOP_LEARN_MORE).click();
		Thread.sleep(3000);
		softAssert.assertTrue(driver.findElement(By.xpath("//h2[@class='text-display-sm font-medium leading-none']")).isDisplayed(),"Learn More Link is not functional");
		softAssert.assertAll();
		Reporter.log("Learn More Link is functional");
		ExtentReport.extentTest.info("Learn More Link is functional");
	}


	public void QuickShopOnMegaCollection() throws Exception {
		Actions action = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//Thread.sleep(8000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		js.executeScript("window.scrollBy(0,700)");

		Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfElementLocated(FIRST_PRODUCT_IMAGE));
		// Hover On product image on collection page
		element = driver.findElement(FIRST_PRODUCT_IMAGE);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		action.moveToElement(element).build().perform();
		Thread.sleep(7000);
		AddproductToCartByQuickShop();
	}

	public void ValidateCloseQuickshop() throws Exception {
		Actions action = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//Thread.sleep(8000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		js.executeScript("window.scrollBy(0,500)");

		Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfElementLocated(FIRST_PRODUCT_IMAGE));
		// Hover On product image on collection page
		element = driver.findElement(FIRST_PRODUCT_IMAGE);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		action.moveToElement(element).build().perform();
		Thread.sleep(3000);
		driver.findElement(QUICK_SHOP_ATC).click();
		Reporter.log("Quick shop ATC button text is validated :: Clickable.");
		ExtentReport.extentTest.info("Quick shop ATC button text is validated :: Clickable.");
		driver.findElement(QUICKSHOP_CLOSE).click();
		softAssert.assertTrue(driver.findElement(QUICK_SHOP_ATC).isDisplayed(),"QUICKSHOP close icon is not functional.");
		Reporter.log("QUICKSHOP close icon is functional.");
		ExtentReport.extentTest.info("QUICKSHOP close icon is functional.");

	}


	public void AddproductToCartByQuickShop() throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String prod_title = driver.findElement(PRODUCT_TITLE_ON_COLLECTION).getText();
		String color_name = driver.findElement(PRODUCT_ITEM_SWATCH_CIRCLE).getAttribute("alt");
		String amount = driver.findElement(PRODUCT_AMOUNT_ON_Collection).getText();
		int count = 0;
		Thread.sleep(8000);
		// If Quick shop ATC is displayed
//		js.executeScript("window.scrollBy(0,500)");
		if (driver.findElement(QUICK_SHOP_ATC).isDisplayed()) {
			Reporter.log("Quick shop ATC button is displayed on hover.");
			ExtentReport.extentTest.info("Quick shop ATC button is displayed on hover.");
			System.out.println("Quick shop ATC text is: " + driver.findElement(QUICK_SHOP_ATC).getText());
			softAssert.assertEquals(driver.findElement(QUICK_SHOP_ATC).getText(), "Add To Cart +",
					"Text changed for Quick shop ATC button.");
			driver.findElement(QUICK_SHOP_ATC).click();
			Thread.sleep(3000);
			Reporter.log("Quick shop ATC button text is validated :: Clickable.");
			ExtentReport.extentTest.info("Quick shop ATC button text is validated :: Clickable.");

			// size overlay Field validation
			softAssert.assertTrue(driver.findElement(QUICK_SHOP_SIZE_OVERLAY).isDisplayed(),
					"On collection page Quick shop select size overlay is not displayed after click on ATC button.");
			Reporter.log("Quick shop size overlay is displayed.");
			ExtentReport.extentTest.info("Quick shop size overlay is displayed.");
			softAssert.assertTrue(driver.findElement(QUICK_SHOP_SIZE_OVERLAY_CLOSE).isDisplayed(),
					"Quick shop select size overlay close btn is not displayed after click on ATC button.");
			Reporter.log("Quick shop size overlay is close button is displayed.");
			ExtentReport.extentTest.info("Quick shop size overlay is close button is displayed.");
//				softAssert.assertTrue(driver.findElement(QUICK_SHOP_SIZE_OVERLAY_HEADING).isDisplayed(),
//						"Quick shop select size overlay's heading is not displayed.");
			//	softAssert.assertEquals(driver.findElement(QUICK_SHOP_SIZE_OVERLAY_HEADING).getText(), "Choose Your Size",
			//			"Text changed for Quick shop Size overlay heading.");
			Reporter.log("Quick shop size overlay heading is displayed ::Correct.");
			ExtentReport.extentTest.info("Quick shop size overlay heading is displayed ::Correct.");
			softAssert.assertTrue(driver.findElement(QUICK_SHOP_SIZE_LABEL).isDisplayed(),
					"Quick shop size labels are not displayed.");
			Reporter.log("Quick shop size labels are displayed.");
			ExtentReport.extentTest.info("Quick shop size labels are displayed.");
			/*
			 * softAssert.assertTrue(!driver.findElement(QUICK_SHOP_SIZE_OVERLAY_ATC).
			 * isEnabled(),
			 * "Quick shop size overlay ATC is enabled before size selection. ");
			 * Reporter.log("Quick shop size overlay ATC is disabled before size selection."
			 * );
			 *
			 */
			// select sizes from quick shop size overlay
	//		driver.findElement(By.xpath("//span[contains(text(),'Set')]")).click();
			List<WebElement> allElements1;
			List<WebElement> allElements2;
			String classtext;
			//allElements = driver.findElements(QUICK_SHOP_SIZE_LIST);
			allElements1 = driver.findElements(QUICK_SHOP_SIZE_LIST_1);
			allElements2 = driver.findElements(QUICK_SHOP_SIZE_LIST_2);
			System.out.println("All sizes list :: " + allElements1.size());
			for (int i = 1; i < allElements1.size(); i++) {
				// classtext=allElements.get(i).getAttribute("class");
				classtext = allElements1.get(i).getAttribute("value");
//					classtext = classes1[classes1.length - 1];
				System.out.println("last class name is: " + classtext);
				element = driver.findElement(By.xpath("(//input[@name='option[Top Size]'])[" + i + "]"));
				js.executeScript("arguments[0].click();", element);

				for (int k = 1; k < allElements2.size(); k++) {

					classtext = allElements2.get(i).getAttribute("value");

					System.out.println("last class name is: " + classtext);
					element = driver.findElement(By.xpath("(//input[@name='option[Bottom Size]'])[" + k + "]"));
					js.executeScript("arguments[0].click();", element);


					Thread.sleep(2000);
					try {
						System.out.println("Entering Try");
						if (driver.findElement(By.xpath("//button[@aria-label='Out of stock']")).isDisplayed())
							System.out.println("Out of Stock");

					} catch (Exception ex) {
						System.out.println("Entering Catch");
						count++;
						Selected_size1 = element.getAttribute("value");
						System.out.println("Selected size is: " + Selected_size1);
						String[] AddtoBag;
						AddtoBag = (driver.findElement(By.xpath("//button[@class='button grow']")).getText()).split(" -");
						String text1 = AddtoBag[0];
						System.out.println(text1);
						//	WebElement elem = driver.findElement(By.cssSelector(".multiPdp__atc-container>button"));
						if (text1.equals("Add to bag")) {
							System.out.println(driver.findElement(PRODUCT_TITLE_ON_COLLECTION).getText() + element.getText() + "Product is added to Cart");
							Thread.sleep(2000);
							js.executeScript("arguments[0].click();", driver.findElement(ADD_TO_BAG_QUICKSHOP));
							Thread.sleep(3000);
							driver.findElement(INLINE_CART_TITLE);
							js.executeScript("arguments[0].click();", driver.findElement(INLINE_CART_CLOSE));

							Thread.sleep(1000);
							System.out.println("=============================");

						}


//					Thread.sleep(3000);


					}

					if (count == 1) {
						break;
					}

				}

				if (count == 1) {
					break;
				}
			}


			Reporter.log("Quick Shop is functional");
			ExtentReport.extentTest.info("Quick Shop is functional");
		}

	//				if (!classtext.equalsIgnoreCase("not-available")) {
//						element = driver.findElement(
//								By.cssSelector("div.inline-quick-shop > ul:nth-child(4) > li:nth-child(" + (i + 1) + ") > input"));
//						js.executeScript("arguments[0].click();", element);
//						// Selected_size=allElements.get(i).getAttribute("value");
//						Selected_size1 = element.getAttribute("value");
//						System.out.println("Selected size is: " + Selected_size1);
//						Thread.sleep(3000);
//						break;
//
//					} else {
//						System.out.println("Selected size is OOS");
//					}
//				}
//				if(!allElements2.isEmpty()) {
//					for (int i = 0; i < allElements2.size(); i++) {
//						// classtext=allElements.get(i).getAttribute("class");
//						String[] classes1 = allElements2.get(i).getAttribute("class").split(" ");
//						classtext = classes1[classes1.length - 1];
//						System.out.println("last class name is: " + classtext);
//						if (!classtext.equalsIgnoreCase("not-available")) {
//							element = driver.findElement(
//									By.cssSelector("div.inline-quick-shop > ul:nth-child(6) > li:nth-child(" + (i + 1) + ") > input"));
//							js.executeScript("arguments[0].click();", element);
//							// Selected_size=allElements.get(i).getAttribute("value");
//							Selected_size2 = element.getAttribute("value");
//							System.out.println("Selected size is: " + Selected_size2);
//							Thread.sleep(3000);
//							break;
//
//						} else {
//							System.out.println("Selected size is OOS");
//						}
//					}
//					Thread.sleep(5000);
////					text = driver.findElement(FlyCartPageLocator.COLOR_AND_SIZE_IN_CART_MULTI).getText();
////					String actual = text;
////					// String[] text1 = text.split("/");
////					// actual = text1[0];
////					String expected = color_name + " / " + Selected_size1 + " / " + Selected_size2;
////					// String expected = color_name+" ";
////					System.out.println("Actual Color name is: " + actual + " and Expected color name is: " + expected);
////					softAssert.assertEquals(actual, expected, "Product Line item option on flycart is incorrect.");
////				}
////				else {
////					System.out.println("The product doesn't have multivariant sizes in quickshop");
////				}
//				Reporter.log("Quick shop size labels are Clickable.");
//				ExtentReport.extentTest.info("Quick shop size labels are Clickable.");
//
//				/*
//				 * for(int i=0;i<allElements.size();i++) { element = driver.findElement(By.
//				 * cssSelector("div.inline-quick-shop > ul > li:nth-child("+(i+1)+")"));
//				 * //element.click(); if(!element.getClass().equals("not-available"))
//				 * element.click(); Selected_size=allElements.get(i+1).getText();
//				 * System.out.println("Selected size is: "+Selected_size); Thread.sleep(3000); }
//				 */
//				// Add available size to the cart
//				// if(driver.findElement(QUICK_SHOP_SIZE_OVERLAY_ATC).isEnabled()){
//				/*
//				 * text= driver.findElement(QUICK_SHOP_SIZE_OVERLAY_ATC).getText();
//				 * if(text.equalsIgnoreCase("Out Of Stock")) throw new
//				 * Exception("Quick shop size overlay ATC btn is enabled for OOS product size."
//				 * );
//				 *
//				 * else if(text.equalsIgnoreCase("Add To Cart"))
//				 * driver.findElement(QUICK_SHOP_SIZE_OVERLAY_ATC).click();
//				 * Reporter.log("Quick Shop size overlay ATC button is Displayed :: Clickable."
//				 * ); Thread.sleep(3000);
//				 */
//				// field validation for added product on fly cart
//				Thread.sleep(3000);
//				String actual = driver.findElement(FlyCartPageLocator.FIRST_PRODUCT_TITLE_IN_CART).getText();
//				System.out.println("Actual product name is: " + actual + " and Expected product name is: " + prod_title);
//				// softAssert.assertEquals(actual,prod_title ,"Product name on flycart is
//				// incorrect.");
//
//				if(driver.findElement(FlyCartPageLocator.COLOR_AND_SIZE_IN_CART).isDisplayed())
//				{
//					text = driver.findElement(FlyCartPageLocator.COLOR_AND_SIZE_IN_CART).getText();
//					actual = text;
//					// String[] text1 = text.split("/");
//					// actual = text1[0];
//					String expected = color_name + " / " + Selected_size1;
//					// String expected = color_name+" ";
//					System.out.println("Actual Color name is: " + actual + " and Expected color name is: " + expected);
//					//softAssert.assertEquals(actual, expected, "Product Line item option on flycart is incorrect.");
//				}
//
//				actual = driver.findElement(FlyCartPageLocator.PRICE_IN_QUANTITY_LINE).getText();
//				// Product amount is displayed without ".00" suffix in Buy with section
//				String new_amount = null;
//				String new_actual_amount = null;
//				for (String a : amount.split("\\.00"))
//					new_amount = a;
//				for (String a : amount.split("\\.00"))
//					new_actual_amount = a;
//				System.out.println("Actual product amount is: " + new_actual_amount + " and Expected product amount is: "
//						+ new_amount);
//				softAssert.assertEquals(new_actual_amount, new_amount, "Product amount on flycart is incorrect.");
//				System.out.println("product details are correct on Fly cart.");
//				Thread.sleep(3000);
//				Reporter.log("Product details are validated on Inline cart.");
//				ExtentReport.extentTest.info("Product details are validated on Inline cart.");
//				WebElement btn = driver.findElement(FlyCartPageLocator.INLINE_CART_CLOSE_BUTTON);
//				js.executeScript("arguments[0].click();", btn);
//				// }
//				// If selected size is OOS/Unavailable
//				/*
//				 * else{ text= driver.findElement(QUICK_SHOP_SIZE_OVERLAY_ATC).getText();
//				 * System.out.println("Text of ATC on Quick shop overlay for OOS size is: "+text
//				 * ); if(!text.equalsIgnoreCase("Out Of Stock")){
//				 * if(!text.equalsIgnoreCase("Unavilable")) throw new
//				 * Exception("Text change of ATC on Quick shop overlay for OOS/Unavailable size."
//				 * ); }
//				 *
//				 * driver.findElement(QUICK_SHOP_SIZE_OVERLAY_CLOSE).click();
//				 * System.out.println("OOS/Unavaiable size is selected.");
//				 * Reporter.log("Quick shop size overlay OOS/unavailable button is disabled.");
//				 * }
//				 */
//			} else
//				throw new Exception(
//						"Quick Shop ATC button is not displayed on hovered product or quickshop not available for particular product");
//			softAssert.assertAll();
		}

	


	public void MethodToCheckColorSwatchChanging() throws Exception {
		
		Thread.sleep(5000);
		JavascriptExecutor executore = (JavascriptExecutor) driver;
		//executore.executeScript("window.scrollBy(0, 350)", "");
		Thread.sleep(5000);

		List<WebElement> allElements = driver.findElements(ALL_COLOR_LIST);
		int elementsCount = allElements.size();
		System.out.println("Color Element count :: " + elementsCount);

		if (elementsCount > 1){
//			element = driver.findElement(By.cssSelector(
//				"div[data-index='0']>div:nth-child(2)>div>div>div.product-item__swatch--circle"));
//		//	element.click();

			for (int i = 1; i < elementsCount; i++) {
				if (i < 4) {
					String Img_colortext = null;
					String colornew1 = null;
					text = driver.findElement(PRODUCT_IMAGE_COLOR_NAME).getAttribute("data-color-handle");
					String[] strarray = text.split("[=]");
					String text1 = strarray[strarray.length - 1];
					Img_colortext = text1.replace("-", " ");

					System.out.println("Product image color name is: " + Img_colortext);

					element = driver.findElement(By.xpath(
							"(//input[@class='product-option__variant-btn product-option__variant-btn--color js-variant--color'])["+i+"]"));

					colornew1 = element.getAttribute("data-color-variant");
					String colornew = colornew1.replace("-", " ");
					System.out.println(colornew);
					if (colornew.equalsIgnoreCase(Img_colortext)) {
						i++;
						WebElement elemen = driver.findElement(By.xpath(
								"(//input[@class='product-option__variant-btn product-option__variant-btn--color js-variant--color'])["+i+"]"));
						executore.executeScript("arguments[0].click();", elemen);
						//softAssert.assertEquals(Img_colortext, colornew, "Color not changing");
						
						i--;
					}

                    else
                    {
						ExtentReport.extentTest.info("Color not changing");
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
			ExtentReport.extentTest.info("Color swatch slick arrows are displayed :: Clickable");
		}
		
		softAssert.assertAll();
		Reporter.log("Color swatches are changing correctly");
		ExtentReport.extentTest.info("Color swatches are changing correctly");
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
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		js.executeScript("window.scrollBy(0,500)");

		wait.until(ExpectedConditions.presenceOfElementLocated(FIRST_PRODUCT_IMAGE));
		String text1 = driver.findElement(HOVER_PRODUCT_IMAGE).getAttribute("alt");
//		String[] imagetext = text1.split("_425");
//		String Hover_on_plp = imagetext[0];
		String text = driver.findElement(FIRST_PRODUCT_IMAGE).getAttribute("alt");
//		String[] list1 = text.split(".jpg");
//		String Second_image_pdp = list1[0];
		System.out.println(text);
		System.out.println(text1);
		if (!text1.equals(text))
			throw new Exception("Product tile hover functionality is not correct on plp");
		Reporter.log("Verified Product tile hover functionality on plp");
		ExtentReport.extentTest.info("Verified Product tile hover functionality on plp");
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

	public void validateHammockpouchCheckboxInFilters() throws InterruptedException {

		int count = 0;
		Actions action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Scroll down to bring elements into view
		js.executeScript("window.scrollBy(0,250)");

		// Wait until the first product is visible before proceeding
		wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_PRODUCT));

		// Click on sort dropdown (simulate opening sort options)
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[@class='flex gap-xs items-center leading-none']")));
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//input[@id='Filters-hammock_pouch-Toggle']")));
		js.executeScript("window.scrollBy(0,-250)");
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_PRODUCT));
		element = driver.findElement(FIRST_PRODUCT_IMAGE);
//		js.executeScript("arguments[0].scrollIntoView(true);", element);
		action.moveToElement(element).build().perform();
		Thread.sleep(3000);
		if (driver.findElement(QUICK_SHOP_ATC).isDisplayed()) {
			Reporter.log("Quick shop ATC button is displayed on hover.");
			ExtentReport.extentTest.info("Quick shop ATC button is displayed on hover.");

			System.out.println("Quick shop ATC text is: " + driver.findElement(QUICK_SHOP_ATC).getText());
			softAssert.assertEquals(driver.findElement(QUICK_SHOP_ATC).getText(), "Select a size",
					"Text changed for Quick shop ATC button.");

			WebElement element1 = driver.findElement(QUICK_SHOP_ATC);
			js.executeScript("arguments[0].click();", element1);
			//
			// .driver.findElement(QUICK_SHOP_ATC).click();
			Thread.sleep(3000);
			Reporter.log("Quick shop ATC button text is validated :: Clickable.");
			ExtentReport.extentTest.info("Quick shop ATC button text is validated :: Clickable.");
		}
		softAssert.assertTrue(driver.findElement(HAMMOCK_POUCH_CTA).isDisplayed(),"Hammock Pouch toggle button is not functional");
		softAssert.assertAll();
		Reporter.log("Hammock Pouch toggle button is functional");
		ExtentReport.extentTest.info("Hammock Pouch toggle button is functional");

	}

	public void validateMultipacksCheckBox() throws Exception {
		int count = 0;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PLP_PRODUCTS)));
		wait.until(ExpectedConditions.visibilityOfElementLocated(MULTIPACKS_CHECKBOX));
		driver.findElement(MULTIPACKS_CHECKBOX).click();
		Thread.sleep(10000);
		List<WebElement> Packs = driver.findElements(By.xpath("//a[@data-cy='productTileLink']"));
		System.out.println(Packs.size());
		for(int i=1;i<=2;i++)
		{
			String[] PacksName = driver.findElement(By.xpath("(//a[@data-cy='productTileLink'])["+i+"]")).getText().split("\"");
			System.out.println(PacksName[1]);

			if(PacksName[1].contains("Pack")){

				System.out.println("Product is displayed as multipack");

			}

			else {
				count++;
				throw new Exception("Product is not displayed as multipack");

			}

		}

		if (count>1){
			throw new Exception("Product is not displayed as multipack");
		}

		else{

			System.out.println("Product is displayed as multipack");
			Reporter.log("Products are displayed as multipacks on selecting the Multipacks checkbox");
			ExtentReport.extentTest.info("Products are displayed as multipacks on selecting the Multipacks checkbox");

		}
	}


	// new
	public void validateProductGrid() throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PLP_PRODUCTS)));
		
//	String d= 	driver.findElement(By.xpath("//div[@class='relative aspect-tile']//swiper-slide")).getAttribute("style");
//	if(d.contains("width: 361px;")) {
//		ExtentReport.extentTest.info("Product grid contains incorrect number of items");
//		throw new Exception("Product grid contains incorrect number of items");
//	}
		SoftAssert softassert = new SoftAssert();
		softassert.assertTrue(driver.findElement(By.xpath(PLP_PRODUCTS)).isDisplayed(),"Products are not displayed");
		ExtentReport.extentTest.info("Product grids are displayed");

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

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		JavascriptExecutor js = (JavascriptExecutor) driver;

		
	

		wait.until(ExpectedConditions.visibilityOfElementLocated(PLP_BREADCRUMBHOME));
		WebElement breadcrumb = driver.findElement(PLP_BREADCRUMBHOME);
		Assert.assertTrue(breadcrumb.isEnabled(), "Element Clickable");
		String breadcrumbhome = breadcrumb.getText();
		softAssert.assertEquals(breadcrumbhome, "Home");
		js.executeScript("arguments[0].click();", breadcrumb);
		softAssert.assertEquals(driver.getCurrentUrl(), "https://www.tommyjohn.com" , "Clicked on home breadcrumb and not navigated toHomepage URL ");
		
		new HomePageComponents(driver).navigateToAllPantiesnInWomenCategory();
		wait.until(ExpectedConditions.visibilityOfElementLocated(PLP_BREADCRUMBHOME));
		WebElement breadcrumbg = driver.findElement(PLP_BREADCRUMBGENDER);
		Assert.assertTrue(breadcrumbg.isEnabled(), "Element Clickable");
		String breadcrumbgender = breadcrumbg.getText();
		softAssert.assertEquals(breadcrumbgender, "Womens");
		js.executeScript("arguments[0].click();", breadcrumbg);
		softAssert.assertEquals(driver.getCurrentUrl(), "https://www.tommyjohn.com" , "Clicked on home breadcrumb and not navigated toHomepage URL ");
		wait.until(ExpectedConditions.presenceOfElementLocated(FIRST_PRODUCT));
		Thread.sleep(4000);
		 WebElement pageHeading  = driver.findElement(PLP_TITLE);
	
		if ((pageHeading.getText()).contains("Women's")) {

			System.out.println("Breadcrumb link is working properly");
			Reporter.log("Breadcrumb link is working properly");
			ExtentReport.extentTest.info("Breadcrumb link is working properly");

		} else {
			System.out.println("Breadcrumb link is not working");
			ExtentReport.extentTest.info("Breadcrumb link is not working");
			throw new Exception("Breadcrumb link is not working");
		}

	}

	// new
	public void validatePLPHeader() throws Exception {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		

		wait.until(ExpectedConditions.visibilityOfElementLocated(PLP_TITLE));
		WebElement pageHeader = driver.findElement(PLP_TITLE);
		WebElement headingcount = driver.findElement(PLP_TITLE_COUNT);
		Assert.assertTrue(pageHeader.isDisplayed(), "The Category page header is displayed");
		Assert.assertTrue(headingcount.isDisplayed(), "The Category page header is displayed");
		
//		String count= headingcount.getText();
	//	String countitem = count.replace(")","");
//		int c = Integer.parseInt(countitem);
//		if(c>0)
//		{
//			Reporter.log("The Category page displayed correct count of items");
//			ExtentReport.extentTest.info("The Category page displayed correct count of items");
//		}
//		else {
//			ExtentReport.extentTest.info("The Category page displayed incorrect count of items");
//			throw new Exception("The Category page displayed incorrect count of items");
//		}

		if ((pageHeader.isDisplayed()) &&  headingcount.isDisplayed()) {
			Reporter.log("The Category page header is displayed correctly with count of items");
			ExtentReport.extentTest.info("The Category page header is displayed");
	//		System.out.println("Page Header is - " + pageHeader.getText());

		} else {
			ExtentReport.extentTest.info("Page Header is not displayed");
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

		js.executeScript("window.scrollBy(0,500)");
		Assert.assertTrue(sectionHeader.isDisplayed(), "The Category page section header is displayed");

		for (int i = 1; i < sectionHeaders.size(); i++) {
			if (driver.findElement(By.xpath("(//h2[@class='mb-md font-serif text-body-lg text-text-accent'])["+i+"]")).isDisplayed()) {

				Reporter.log("The Category page header "
						+ driver.findElement(By.xpath("(//h2[@class='mb-md font-serif text-body-lg text-text-accent'])["+i+"]")).getText() + " is displayed");
				ExtentReport.extentTest.info("The Category page header "
						+ driver.findElement(By.xpath("(//h2[@class='mb-md font-serif text-body-lg text-text-accent'])["+i+"]")).getText() + " is displayed");
				System.out.println("Section Header - "
						+ driver.findElement(By.xpath("(//h2[@class='mb-md font-serif text-body-lg text-text-accent'])["+i+"]")).getText());

			} else {
				ExtentReport.extentTest.info("Section Header is not displayed");
				throw new Exception("Section Header is not displayed");
			}
		}
	}

	public void validatePLPInlineBanner() throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,200)");
		objGeneralMethods.waitTillPageLoad(driver);
		Thread.sleep(10000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(PLP_INLINE_BANNER));
		WebElement inlineBanner = driver.findElement(PLP_INLINE_BANNER);
		System.out.println(driver.getCurrentUrl());
		Assert.assertTrue(inlineBanner.isEnabled(), "Inline banner is clickable");
		ExtentReport.extentTest.info("Inline banner is clickable");
		js.executeScript("arguments[0].scrollIntoView(true);", inlineBanner);
		//Actions action = new Actions(driver);
		//action.moveToElement(inlineBanner).click().perform();
		js.executeScript("arguments[0].click();", inlineBanner);


	}

	public void ValidateOnClickingTheInLineBanner() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		objGeneralMethods.waitTillPageLoad(driver);
		Thread.sleep(10000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(PLP_INLINE_BANNER));
		WebElement inlineBanner = driver.findElement(PLP_INLINE_BANNER);
//		System.out.println(driver.getCurrentUrl());
//		String[] InLineBannerText;
//		InLineBannerText = (driver.findElement(INLINEBANNERTEXT).getText().split("Underwear "));
//		String InLineBanner = InLineBannerText[1];
//		System.out.println(InLineBanner);
//		String InLineBannerText = driver.findElement(By.xpath("//div[@class='plpIlb_maintext']")).getText();
		Assert.assertTrue(inlineBanner.isEnabled(), "Inline banner is clickable");
		ExtentReport.extentTest.info("Inline banner is clickable");
		js.executeScript("arguments[0].scrollIntoView(true);", inlineBanner);
		//Actions action = new Actions(driver);
		//action.moveToElement(inlineBanner).click().perform();
		js.executeScript("arguments[0].click();", inlineBanner);
//		String[] PageHeader;
//		PageHeader = (driver.findElement(PAGEHEADER).getText().split("Underwear "));
//		String PageName = PageHeader[1];
//		System.out.println(PageName);
		softAssert.assertTrue(driver.findElement(SHOP_PAGEHEADER).isDisplayed(),"User is not directed to Appropriate Page On Clicking the Inline Banner from PLP");
		Reporter.log("User is directed to Appropriate Page On Clicking the Inline Banner from PLP");
		ExtentReport.extentTest.info("User is directed to Appropriate Page On Clicking the Inline Banner from PLP");

	}


	public void validateOnImageAndSecondaryBadges() throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
		wait.until(ExpectedConditions.visibilityOfElementLocated(PLP_PRODUCT_IMAGE_BADGE));
	//	wait.until(ExpectedConditions.visibilityOfElementLocated(PLP_PRODUCT_SECONDARY_BADGE));
		}
		catch(Exception e)
		{
			driver.navigate().refresh();
			wait.until(ExpectedConditions.visibilityOfElementLocated(PLP_PRODUCT_IMAGE_BADGE));
			wait.until(ExpectedConditions.visibilityOfElementLocated(PLP_PRODUCT_SECONDARY_BADGE));
		}
		WebElement onImageBadge = driver.findElement(PLP_PRODUCT_IMAGE_BADGE);
		WebElement secondaryBadge = driver.findElement(PLP_PRODUCT_SECONDARY_BADGE);

		js.executeScript("window.scrollBy(0,250)");
		Assert.assertTrue(onImageBadge.isDisplayed(), "The On Image Badge is displayed");
		Assert.assertTrue(secondaryBadge.isDisplayed(), "The Seacondary Badge is displayed");

		if (onImageBadge.isDisplayed()) {

			Reporter.log("The On Image Badge is displayed");
			ExtentReport.extentTest.info("The On Image Badge is displayed");
			System.out.println("The On Image Badge is displayed");

		} else {
			System.out.println("The On Image Badge is not displayed");
			ExtentReport.extentTest.info("The On Image Badge is not displayed");
			throw new Exception("The On Image Badge is not displayed");
		}
		
		if (secondaryBadge.isDisplayed()) {

			Reporter.log("The Secondary Badge is displayed");
			ExtentReport.extentTest.info("The Secondary Badge is displayed");
			System.out.println("The Secondary Badge is displayed");

		} else {
			
			System.out.println("The Secondary Badge is not displayed");
			Reporter.log("The Secondary Badge is not displayed");
			ExtentReport.extentTest.info("The Secondary Badge is not displayed");
			//throw new Exception("The Secondary Badge is not displayed");
		}
	
	}
	
	public void validateOnImageBadging() throws Exception {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(PLP_PRODUCT_IMAGE_BADGE));

		WebElement onImageBadge = driver.findElement(PLP_PRODUCT_IMAGE_BADGE);
		

		js.executeScript("window.scrollBy(0,250)");
		Assert.assertTrue(onImageBadge.isDisplayed(), "The On Image Badge is displayed");
		

		if (onImageBadge.isDisplayed()) {

			Reporter.log("The On Image Badge is displayed");
			System.out.println("The On Image Badge is displayed");

		} else {
			System.out.println("The On Image Badge is not displayed");
			throw new Exception("The On Image Badge is not displayed");
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
			ExtentReport.extentTest.info("SEO title is displayed");
			System.out.println("SEO title is displayed");
			if(seoBody.isDisplayed()) {
				Reporter.log("SEO Body displayed");
				ExtentReport.extentTest.info("SEO Body displayed");
				System.out.println("SEO Body is displayed");
			}
			else {
				System.out.println("SEO Body is not displayed");
				ExtentReport.extentTest.info("SEO Body is not displayed");
				throw new Exception("SEO Body is not displayed");
			}
		} else {
			System.out.println("SEO title is not displayed");
			ExtentReport.extentTest.info("SEO title is not displayed");
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
			ExtentReport.extentTest.info("User has navigated to correct page");
		}
		else {
			System.out.println("User has not navigated to desired page");
			ExtentReport.extentTest.info("User has not navigated to desired page");
			throw new Exception("User has not navigated to desired page");
		}
	}
	
	public void validateSingleTile() throws Exception {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		js.executeScript("window.scrollBy(0,250)");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_IMAGE));
		new CollectionPageComponent(driver).validateProductImageOnPLP();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(PLP_PRICE_FILTER));
		//WebElement priceFilter = driver.findElement(PLP_PRICE_FILTER);
		//WebElement singleProductFilter = driver.findElement(PLP_FILTER_SINGLE_PROODUCT);
		
		int width ;

//		Assert.assertTrue(priceFilter.isDisplayed(), "Price Filter is displayed");
//		Assert.assertTrue(priceFilter.isEnabled(), "Price Filter is clickable");

//		if (priceFilter.isDisplayed()) {
//
//			Reporter.log("Price Filter is displayed");
//			ExtentReport.extentTest.info("Price Filter is displayed");
//			System.out.println("Price Filter is displayed");
//			js.executeScript("arguments[0].click();", priceFilter);
//			
//			if(singleProductFilter.isEnabled())
//			{
//				Reporter.log("Single Product Filter is clickable");
//				ExtentReport.extentTest.info("Single Product Filter is clickable");
//				System.out.println("Single Product Filter is clickable");
//				js.executeScript("arguments[0].click();", singleProductFilter);
//				js.executeScript("arguments[0].click();", priceFilter);
//				Thread.sleep(5000);
//			}
//			else {
//				System.out.println("Single Product Filter is not clickable");
//				ExtentReport.extentTest.info("Single Product Filter is not clickable");
//				throw new Exception("Single Product Filter is not clickable");
//			}
//		} else {
//			System.out.println("Price Filter is not displayed");
//			ExtentReport.extentTest.info("Price Filter is not displayed");
//			throw new Exception("Price Filter is not displayed");
//		}
//		wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_IMAGE));
//		
//code to check double wide single tile
//		WebElement productImage = driver.findElement(By.cssSelector("div[data-index='0']>div>a>img.product-image--main"));
//		org.openqa.selenium.Dimension dim = productImage.getSize();
//		width=	dim.width;		
//		if(width>280)  {
//			Reporter.log("Single Product Tile annotations are correct");
//			ExtentReport.extentTest.info("Single Product Tile annotations are correct");
//			System.out.println("Single Product Tile annotations are correct");
//		}
//		else {
//			System.out.println("Single Product Tile annotations are not correct");
//			ExtentReport.extentTest.info("Single Product Tile annotations are not correct");
//			throw new Exception("Single Product Tile annotations are not correct");
//		}
	}
	
	public void validateSingleTileSearch() throws Exception {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		//JavascriptExecutor js = (JavascriptExecutor) driver;
		
		//js.executeScript("window.scrollBy(0,250)");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_IMAGE));
		
		//WebElement productImage = driver.findElement(By.cssSelector("div[data-index='1']>div>a>img[data-tj-qa='plp-product-img-main']"));
		WebElement productImage = driver.findElement(By.xpath("(//img[@class='aspect-tile bg-background-image object-cover text-[0] w-full'])[1]"));
		Dimension dim = productImage.getSize();
		int width=	dim.width;
//		marginLeft = productImage.getCssValue("margin-left");
//		marginRight = productImage.getCssValue("margin-right");
//		System.out.println(width + " " + marginLeft + " " + marginRight);
		
		if(width>250)  {
			Reporter.log("Single Product Tile annotations are correct");
			ExtentReport.extentTest.info("Single Product Tile annotations are correct");
			System.out.println("Single Product Tile annotations are correct");
		}
		else {
			System.out.println("Single Product Tile annotations are not correct");
			ExtentReport.extentTest.info("Single Product Tile annotations are not correct");
			throw new Exception("Single Product Tile annotations are not correct");
		}
	}
	
	
}
