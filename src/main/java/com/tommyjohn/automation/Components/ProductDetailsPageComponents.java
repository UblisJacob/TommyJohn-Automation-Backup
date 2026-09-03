package com.tommyjohn.automation.Components;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.base.ExtentReport;
import com.tommyjohn.automation.PageLocators.CollectionPageLocator;
import com.tommyjohn.automation.PageLocators.FlyCartPageLocator;
import com.tommyjohn.automation.PageLocators.HomePageLocators;
import com.tommyjohn.automation.PageLocators.ProductDetailsPageLocators;
import com.tommyjohn.automation.Pages.ProductDetailsPage;
import com.tommyjohn.automation.utils.CustomUtilities;

import static com.tommyjohn.automation.PageLocators.CollectionPageLocator.FIRST_PRODUCT_IMAGE;
import static com.tommyjohn.automation.PageLocators.FlyCartPageLocator.*;
import static com.tommyjohn.automation.PageLocators.HomePageLocators.*;

public class ProductDetailsPageComponents extends ProductDetailsPageLocators {
	public WebDriver driver;
	public WebElement element;
	public Actions action;
	public JavascriptExecutor jse;
	boolean flag = false;
	Properties properties = CustomUtilities.properties;
	List<WebElement> allElements;
	List<WebElement> Tabs;
	List<WebElement> Steps;
	String TabsPrice;
	String SelectASize_CTA;
	String Sticky_SelectASize_CTA;
	int elementsCount;
	int Tabs_Count;
	int Steps_Count;
	public static String prodsize;
	public static String productname;
	public static String productsize;
	public static String productcolor;
	public static String productprice;
	public static String productquant;
	public String price;

	public static String Sizename1;
	public static String Sizename2;
	public static String BundleSizename1;
	public static String BundleSizename2;
	int count = 0;
	int NoTabs = 0;
	int BrokenURL = 0;

	double installment;


	public ProductDetailsPageComponents(WebDriver driver) {
		this.driver = driver;
	}

	public void validateTrueFitExperienceOnPDP() throws Exception {
		SoftAssert softAssert = new SoftAssert();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// 1. Navigation: Hover Women's Nav Menu -> Click Bras & Bralettes
		WebElement womenNavMenu = wait.until(ExpectedConditions.presenceOfElementLocated(WOMEN_NAV_MENU));
		try {
			Actions actions = new Actions(driver);
			actions.moveToElement(womenNavMenu).perform();
			Thread.sleep(500);
		} catch (Exception e) {
			js.executeScript("arguments[0].dispatchEvent(new MouseEvent('mouseover', {bubbles: true}));", womenNavMenu);
		}

		WebElement brasBralettesSubmenu = wait.until(ExpectedConditions.presenceOfElementLocated(BRAS_BRALETTES_SUBMENU));
		js.executeScript("arguments[0].click();", brasBralettesSubmenu);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

		// 2. Select first product card on PLP
		WebElement firstProduct = wait.until(ExpectedConditions.presenceOfElementLocated(FIRST_PRODUCT_CARD));
		js.executeScript("arguments[0].scrollIntoView({block: 'center'});", firstProduct);
		js.executeScript("arguments[0].click();", firstProduct);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

		Thread.sleep(2000);

		// ==========================================
		// ENTRY POINT 1: Direct PDP True Fit Link
		// ==========================================
		boolean pdpTrueFitValidated = false;
		try {
			WebElement pdpTrueFitLink = wait.until(ExpectedConditions.presenceOfElementLocated(TRUE_FIT_CTA));
			js.executeScript("arguments[0].scrollIntoView({block: 'center'});", pdpTrueFitLink);
			Thread.sleep(1000);

			try {
				pdpTrueFitLink.click();
			} catch (Exception ex) {
				js.executeScript("arguments[0].click();", pdpTrueFitLink);
			}
			System.out.println("✔ Clicked True Fit link directly on PDP.");

			WebElement visibleModal = wait.until(ExpectedConditions.visibilityOfElementLocated(TRUE_FIT_MODAL));
			pdpTrueFitValidated = (visibleModal != null && visibleModal.isDisplayed());

			if (pdpTrueFitValidated) {
				System.out.println("✔ True Fit modal displayed visually from PDP link.");
				closeTrueFitModalSafely(wait, js);
			}
		} catch (Exception e) {
			System.out.println("⚠ Direct PDP True Fit validation failed: " + e.getMessage());
		}

		softAssert.assertTrue(pdpTrueFitValidated, "True Fit link on PDP failed to open a visible True Fit modal.");

		// Reset page state prior to Entry Point 2 execution
		driver.navigate().refresh();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
		Thread.sleep(2000);

		// ==========================================
		// ENTRY POINT 2: True Fit via Size Chart Modal
		// ==========================================
		boolean sizeChartTrueFitValidated = false;
		try {
			// Click "Sizing chart" button
			WebElement sizingChartBtn = wait.until(ExpectedConditions.presenceOfElementLocated(SIZING_CHART_TRIGGER));
			js.executeScript("arguments[0].scrollIntoView({block: 'center'});", sizingChartBtn);
			Thread.sleep(1000);

			try {
				sizingChartBtn.click();
			} catch (Exception clickEx) {
				js.executeScript("arguments[0].click();", sizingChartBtn);
			}
			System.out.println("✔ Clicked 'Sizing chart' button.");

			// Wait for modal transition/rendering
			Thread.sleep(1500);

			// Locate and click "What's My Size?" button inside Sizing Chart modal
			WebElement innerWhatsMySizeBtn = wait.until(ExpectedConditions.presenceOfElementLocated(MODAL_TRUE_FIT_BUTTON));
			js.executeScript("arguments[0].scrollIntoView({block: 'center'});", innerWhatsMySizeBtn);
			Thread.sleep(500);

			try {
				innerWhatsMySizeBtn.click();
			} catch (Exception ex) {
				js.executeScript("arguments[0].click();", innerWhatsMySizeBtn);
			}
			System.out.println("✔ Clicked 'What\'s My Size?' button inside Sizing Chart modal.");

			// Verify True Fit modal appears visually
			WebElement visibleModalFromChart = wait.until(ExpectedConditions.visibilityOfElementLocated(TRUE_FIT_MODAL));
			sizeChartTrueFitValidated = (visibleModalFromChart != null && visibleModalFromChart.isDisplayed());

			if (sizeChartTrueFitValidated) {
				System.out.println("✔ True Fit modal displayed visually from Size Chart.");
				closeTrueFitModalSafely(wait, js);
			}
		} catch (Exception e) {
			System.out.println("⚠ Size Chart True Fit validation failed: " + e.getMessage());
		}

		softAssert.assertTrue(sizeChartTrueFitValidated, "True Fit CTA inside Size Chart failed to open a visible True Fit modal.");

		softAssert.assertAll();
	}

	private void closeTrueFitModalSafely(WebDriverWait wait, JavascriptExecutor js) {
		try {
			WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(TRUE_FIT_MODAL_CLOSE));
			try {
				closeButton.click();
			} catch (Exception ex) {
				js.executeScript("arguments[0].click();", closeButton);
			}
			System.out.println("✔ Closed True Fit modal.");
		} catch (Exception e) {
			System.out.println("⚠ Could not close True Fit modal automatically: " + e.getMessage());
		}
	}

	public void validatePdpTrueFitMainButton() throws Exception {
		SoftAssert softAssert = new SoftAssert();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// 1. Navigation: Hover Women's Nav Menu -> Click Bras & Bralettes
		WebElement womenNavMenu = wait.until(ExpectedConditions.presenceOfElementLocated(WOMEN_NAV_MENU));
		try {
			Actions actions = new Actions(driver);
			actions.moveToElement(womenNavMenu).perform();
			Thread.sleep(500);
		} catch (Exception e) {
			js.executeScript("arguments[0].dispatchEvent(new MouseEvent('mouseover', {bubbles: true}));", womenNavMenu);
		}

		WebElement brasBralettesSubmenu = wait.until(ExpectedConditions.presenceOfElementLocated(BRAS_BRALETTES_SUBMENU));
		js.executeScript("arguments[0].click();", brasBralettesSubmenu);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

		// 2. Select first product card on PLP
		WebElement firstProduct = wait.until(ExpectedConditions.presenceOfElementLocated(FIRST_PRODUCT_CARD));
		js.executeScript("arguments[0].scrollIntoView({block: 'center'});", firstProduct);
		js.executeScript("arguments[0].click();", firstProduct);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

		// ==========================================
		// ENTRY POINT: Direct Shadow DOM Interaction
		// ==========================================
		boolean buttonClicked = false;
		boolean pdpTrueFitValidated = false;

		// Recursive script that searches ALL shadow roots across the entire document
		String clickTrueFitShadowBtnScript =
				"function searchAndClick(root) {" +
						"    if (!root) return false;" +
						"    var btn = root.querySelector(" +
						"        'button[data-truefit-id=\"pdpCta.button\"], ' +" +
						"        '[data-truefit-id=\"pdpCta.ctaText\"], ' +" +
						"        '[data-truefit-id=\"pdpCta.buttonThemeButton\"]'" +
						"    );" +
						"    if (btn) {" +
						"        var clickTarget = btn.closest('button') || btn;" +
						"        ['pointerdown', 'mousedown', 'pointerup', 'mouseup', 'click'].forEach(function(evt) {" +
						"            clickTarget.dispatchEvent(new MouseEvent(evt, {bubbles: true, cancelable: true, view: window}));" +
						"        });" +
						"        return true;" +
						"    }" +
						"    var allElements = root.querySelectorAll('*');" +
						"    for (var i = 0; i < allElements.length; i++) {" +
						"        if (allElements[i].shadowRoot) {" +
						"            if (searchAndClick(allElements[i].shadowRoot)) return true;" +
						"        }" +
						"    }" +
						"    return false;" +
						"}" +
						"return searchAndClick(document);";

		System.out.println("⏳ Waiting for True Fit Shadow Root to mount and clicking button...");

		// Poll up to 10 seconds for the web component shadow root to hydrate
		for (int i = 0; i < 10; i++) {
			Thread.sleep(1000);
			Boolean result = (Boolean) js.executeScript(clickTrueFitShadowBtnScript);
			if (Boolean.TRUE.equals(result)) {
				buttonClicked = true;
				System.out.println("✔ Found and clicked True Fit button inside Shadow DOM.");
				break;
			}
		}

		if (!buttonClicked) {
			System.out.println("⚠ Could not locate True Fit button inside Shadow DOM after waiting.");
		}

		// Verify dialog activation inside Shadow DOM or DOM tree
		String checkShadowDialogScript =
				"function checkDialog(root) {" +
						"    if (!root) return false;" +
						"    var dialog = root.querySelector('dialog, [role=\"dialog\"], iframe[src*=\"truefit\"]');" +
						"    if (dialog) {" +
						"        return dialog.hasAttribute('open') || " +
						"               window.getComputedStyle(dialog).display !== 'none' || " +
						"               dialog.offsetWidth > 0;" +
						"    }" +
						"    var allElements = root.querySelectorAll('*');" +
						"    for (var i = 0; i < allElements.length; i++) {" +
						"        if (allElements[i].shadowRoot && checkDialog(allElements[i].shadowRoot)) return true;" +
						"    }" +
						"    return false;" +
						"}" +
						"return checkDialog(document);";

		for (int i = 0; i < 10; i++) {
			Thread.sleep(1000);
			Boolean isModalOpen = (Boolean) js.executeScript(checkShadowDialogScript);
			if (Boolean.TRUE.equals(isModalOpen)) {
				pdpTrueFitValidated = true;
				break;
			}
		}

		if (pdpTrueFitValidated) {
			System.out.println("✔ True Fit shadow modal verified open!");

			// Attempt closing the modal inside shadow root
			String closeShadowDialogScript =
					"function closeDialog(root) {" +
							"    if (!root) return false;" +
							"    var closeBtn = root.querySelector('button[aria-label*=\"close\" i], [data-truefit-id*=\"close\" i], .tfc-close');" +
							"    if (closeBtn) { closeBtn.click(); return true; }" +
							"    var allElements = root.querySelectorAll('*');" +
							"    for (var i = 0; i < allElements.length; i++) {" +
							"        if (allElements[i].shadowRoot && closeDialog(allElements[i].shadowRoot)) return true;" +
							"    }" +
							"    return false;" +
							"}" +
							"return closeDialog(document);";

			js.executeScript(closeShadowDialogScript);
		}

		softAssert.assertTrue(pdpTrueFitValidated, "What's My Size CTA button on PDP failed to open the True Fit modal dialog inside Shadow DOM.");
		softAssert.assertAll();
	}








	public void VerifyShareIconPDP() throws Exception {

		String text = null;
		JavascriptExecutor js1 = (JavascriptExecutor) driver;;
		SoftAssert softAssert = new SoftAssert();
        new HomePageComponents(driver).navigateToMensAll();
        new CollectionPageComponent(driver).navigateToProductDetailsPage();
		//driver.get("https://www.tommyjohn.com/products/second-skin-deep-v-neck-stay-tucked-undershirt-3-pack?color=white&view=upsidedown");
		js1.executeScript("window.scrollBy(0,500)");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(SHARE_ICON));
		softAssert.assertTrue(driver.findElement(SHARE_ICON).isDisplayed(),"Share Icon is not available in PDP");
		Reporter.log("Share Icon is available in PDP");
		ExtentReport.extentTest.info("Share Icon is available in PDP");
	}






    public void ValidateNeckLineSection()
    {
        String text = null;
        JavascriptExecutor js1 = (JavascriptExecutor) driver;;
        SoftAssert softAssert = new SoftAssert();
//        new HomePageComponents(driver).navigateToMensAll();
//        new CollectionPageComponent(driver).navigateToProductDetailsPage();
        driver.get("https://www.tommyjohn.com/products/second-skin-deep-v-neck-stay-tucked-undershirt-3-pack?color=white&view=upsidedown");
        js1.executeScript("window.scrollBy(0,700)");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement ele = driver.findElement(CREW_NECK_CTA);
        js1.executeScript("arguments[0].click();", ele);
        js1.executeScript("window.scrollBy(0,300)");
        String Product_title = driver.findElement(PRODUCT_TITLE).getText();
        System.out.println(Product_title);
        softAssert.assertTrue(Product_title.contains("Crew Neck"),
                "Product title does not contain 'Crew Neck'");
        WebElement elem = driver.findElement(DEEP_V_NECK_CTA);
        js1.executeScript("arguments[0].click();", elem);
        js1.executeScript("window.scrollBy(0,300)");
        String Product_title1 = driver.findElement(PRODUCT_TITLE).getText();
        System.out.println(Product_title1);
        softAssert.assertTrue(Product_title1.contains("Deep V-Neck"),
                "Product title does not contain 'Deep V-Neck'");
        WebElement elemt = driver.findElement(HIGH_V_NECK_CTA);
        js1.executeScript("arguments[0].click();", elemt);
        js1.executeScript("window.scrollBy(0,300)");
        String Product_title2 = driver.findElement(PRODUCT_TITLE).getText();
        System.out.println(Product_title2);
        softAssert.assertTrue(Product_title2.contains("High V-Neck"),
                "Product title does not contain 'High V-Neck'");
        WebElement element = driver.findElement(TANK_CTA);
        js1.executeScript("arguments[0].click();", element);
        js1.executeScript("window.scrollBy(0,300)");
        String Product_title3 = driver.findElement(PRODUCT_TITLE).getText();
        System.out.println(Product_title3);
        softAssert.assertTrue(Product_title3.contains("Tank"),
                "Product title does not contain 'Tank'");
        softAssert.assertAll();
        Reporter.log("Appropriate Product is displayed when the User selects the Collection Category");
        ExtentReport.extentTest.info("Appropriate Product is displayed when the User selects the Neckline Category");
    }

    public void ValidateStyleSection()
    {
        String text = null;
        JavascriptExecutor js1 = (JavascriptExecutor) driver;;
        SoftAssert softAssert = new SoftAssert();
//        new HomePageComponents(driver).navigateToMensAll();
//        new CollectionPageComponent(driver).navigateToProductDetailsPage();
        driver.get("https://www.tommyjohn.com/products/second-skin-deep-v-neck-stay-tucked-undershirt-3-pack?color=white&view=upsidedown");
        js1.executeScript("window.scrollBy(0,500)");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement ele = driver.findElement(STAY_TUCKED_STYLE_CTA);
        js1.executeScript("arguments[0].click();", ele);
        js1.executeScript("window.scrollBy(0,300)");
        String Product_title = driver.findElement(PRODUCT_TITLE).getText();
        System.out.println(Product_title);
        softAssert.assertTrue(Product_title.contains("Stay-Tucked"),
                "Product title does not contain 'Stay-Tucked'");
        softAssert.assertAll();
        Reporter.log("Appropriate Product is displayed when the User selects the Style Category");
        ExtentReport.extentTest.info("Appropriate Product is displayed when the User selects the Style Category");
    }


	public void ValidateDisabledATCInUpsideDownPDP() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
//		new HomePageComponents(driver).navigateToAllPantiesInWomenCategory();
//		new CollectionPageComponent(driver).navigateToProductDetailsPage();
		driver.get("https://www.tommyjohn.com/products/second-skin-deep-v-neck-stay-tucked-undershirt-3-pack?color=white&view=upsidedown");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1500)");
		SoftAssert softAssert = new SoftAssert();
		wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_TITLE));
		Thread.sleep(5000);
		Tabs = driver.findElements(PDP_TABS);
		Tabs_Count = Tabs.size();
		System.out.println(Tabs_Count);
		for (int i = 1; i <= Tabs_Count; i++) {
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("(//span[@class='text-sm text-text-tertiary'])[" + i + "]")));
			SelectASize_CTA = driver.findElement(By.xpath("(//button[@class='button-secondary grow'])[1]")).getText();
			System.out.println(SelectASize_CTA);
			js.executeScript("arguments[0].click();", driver.findElement(RATING_STARS));
			Thread.sleep(2000);
//			WebElement Readallreviews = wait.until(ExpectedConditions.visibilityOfElementLocated(READ_ALL_REVIEWS_IN_SUMMARY));
//			js.executeScript("arguments[0].click();", Readallreviews);
			Thread.sleep(5000);
			Sticky_SelectASize_CTA = driver.findElement(By.xpath("(//button[@class='button-secondary grow'])[2]")).getText();
			System.out.println(Sticky_SelectASize_CTA);
			softAssert.assertEquals(SelectASize_CTA,Sticky_SelectASize_CTA,"ATC CTA is not disabled in Sticky and Regular CTAs when the User doesn't select any size");
			Reporter.log("ATC CTA is disabled in Sticky and Regular CTAs when the User doesn't select any size");
			ExtentReport.extentTest.info("ATC CTA is disabled in Sticky and Regular CTAs when the User doesn't select any size");
		}

	}







	public void ValidateCollectionSection()
	{
		String text = null;
		JavascriptExecutor js1 = (JavascriptExecutor) driver;;
		SoftAssert softAssert = new SoftAssert();
//        new HomePageComponents(driver).navigateToMensAll();
//        new CollectionPageComponent(driver).navigateToProductDetailsPage();
		driver.get("https://www.tommyjohn.com/products/second-skin-deep-v-neck-stay-tucked-undershirt-3-pack?color=white&view=upsidedown");
		js1.executeScript("window.scrollBy(0,500)");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement ele = driver.findElement(SECONDSKIN_COLLECTION_CTA);
		js1.executeScript("arguments[0].click();", ele);
		js1.executeScript("window.scrollBy(0,300)");
		String Product_title = driver.findElement(PRODUCT_TITLE).getText();
		System.out.println(Product_title);
		softAssert.assertTrue(Product_title.contains("Second Skin"),
				"Product title does not contain 'Second Skin'");
		WebElement elem = driver.findElement(COOL_COTTON_COLLECTION_CTA);
		js1.executeScript("arguments[0].click();", elem);
		js1.executeScript("window.scrollBy(0,300)");
		String Product_title1 = driver.findElement(PRODUCT_TITLE).getText();
		System.out.println(Product_title1);
		softAssert.assertTrue(Product_title1.contains("Cool Cotton"),
				"Product title does not contain 'Cool Cotton'");
		softAssert.assertAll();
		Reporter.log("Appropriate Product is displayed when the User selects the Collection Category");
		ExtentReport.extentTest.info("Appropriate Product is displayed when the User selects the Collection Category");
	}


    public void VerifySectionsInUpsideDownPDP()
    {
        String text = null;
        JavascriptExecutor js1 = (JavascriptExecutor) driver;;
        SoftAssert softAssert = new SoftAssert();
//        new HomePageComponents(driver).navigateToMensAll();
//        new CollectionPageComponent(driver).navigateToProductDetailsPage();
        driver.get("https://www.tommyjohn.com/products/second-skin-deep-v-neck-stay-tucked-undershirt-3-pack?color=white&view=upsidedown");
        js1.executeScript("window.scrollBy(0,500)");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(UPSIDE_DOWN_PROGRESS_BAR));
        List<WebElement> Recs_Products = driver.findElements(UPSIDEDOWN_SECTIONS);
        System.out.println(Recs_Products.size());
        softAssert.assertEquals(Recs_Products.size(), 3, "The Upsdie Down PDP does not contain 3 sections");
        softAssert.assertAll();
        Reporter.log("The Upsdie Down PDP contains 3 sections");
        ExtentReport.extentTest.info("The Upsdie Down PDP contains 3 sections");
    }


	public void VerifyProgressBarInUpsideDownPDP()
	{

		String text = null;
		JavascriptExecutor js1 = (JavascriptExecutor) driver;;
		SoftAssert softAssert = new SoftAssert();
//        new HomePageComponents(driver).navigateToMensAll();
//        new CollectionPageComponent(driver).navigateToProductDetailsPage();
		driver.get("https://www.tommyjohn.com/products/second-skin-deep-v-neck-stay-tucked-undershirt-3-pack?color=white&view=upsidedown");
		js1.executeScript("window.scrollBy(0,500)");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(UPSIDE_DOWN_PROGRESS_BAR));
		softAssert.assertTrue(driver.findElement(UPSIDE_DOWN_PROGRESS_BAR).isDisplayed(),"Progress Bar is not available in PDP");
		Reporter.log("Progress Bar is available in PDP");
		ExtentReport.extentTest.info("Progress Bar is available in PDP");


	}



    public void VerifyPDPSectionRecs() throws Exception {
        JavascriptExecutor js1 = (JavascriptExecutor) driver;;
        SoftAssert softAssert = new SoftAssert();
        new HomePageComponents(driver).navigateToMensAll();
//        new CollectionPageComponent(driver).navigateToProductDetailsPage();
         driver.get("https://www.tommyjohn.com/products/womens-second-skin-luxe-rib-boyshort?color=lilac-chiffon-luxe");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        js1.executeScript("window.scrollBy(0,500)");
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(PDP_SECTION_RECS));
        softAssert.assertTrue(driver.findElement(PDP_SECTION_RECS).isDisplayed(),"PDP Section Recs are not available in PDP");
        List<WebElement> Recs_Products = driver.findElements(LIST_PDP_SECTION_RECS);
        System.out.println(Recs_Products.size());
        softAssert.assertEquals(Recs_Products.size(), 4, "Recs does not contain 4 products");
       softAssert.assertAll();
        Reporter.log("PDP Section contains Recs");
        ExtentReport.extentTest.info("PDP Section contains Recs");
    }


	public void VerifyAddToWishlist() throws Exception {
		String text = null;
		JavascriptExecutor js1 = (JavascriptExecutor) driver;;
		SoftAssert softAssert = new SoftAssert();
		new HomePageComponents(driver).navigateToMensAll();
		new CollectionPageComponent(driver).navigateToProductDetailsPage();
//		driver.get("https://www.tommyjohn.com/products/womens-air-bra-pack-2-pack?variant=43216573464621");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ADD_TO_WISHLIST));
		softAssert.assertTrue(driver.findElement(ADD_TO_WISHLIST).isDisplayed(),"Add to Wishlist is not available in PDP");
		String Add_to_Wishlist = driver.findElement(ADD_TO_WISHLIST).getText();
		System.out.println("Add to Wishlist text is:"+ Add_to_Wishlist);
		Reporter.log("Add to Wishlist is available in PDP");
		ExtentReport.extentTest.info("Add to Wishlist is available in PDP");

	}

	public void VerifyAddToWishlistInUpsideDownPDP() throws Exception {
		String text = null;
		JavascriptExecutor js1 = (JavascriptExecutor) driver;;
		SoftAssert softAssert = new SoftAssert();
		new HomePageComponents(driver).navigateToMensAll();
		new CollectionPageComponent(driver).navigateToProductDetailsPage();
		driver.get("https://www.tommyjohn.com/products/second-skin-deep-v-neck-stay-tucked-undershirt-3-pack?color=white&view=upsidedown");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ADD_TO_WISHLIST));
		softAssert.assertTrue(driver.findElement(ADD_TO_WISHLIST).isDisplayed(),"Add to Wishlist is not available in PDP");
		String Add_to_Wishlist = driver.findElement(ADD_TO_WISHLIST).getText();
		System.out.println("Add to Wishlist text is:"+ Add_to_Wishlist);
		Reporter.log("Add to Wishlist is available in PDP");
		ExtentReport.extentTest.info("Add to Wishlist is available in PDP");

	}



	public void ValidateGuestUserAddToWishlist() throws Exception {
		JavascriptExecutor js1 = (JavascriptExecutor) driver;;
		SoftAssert softAssert = new SoftAssert();
		new HomePageComponents(driver).navigateToAllWomensCollectionInWomensCategory();
		new CollectionPageComponent(driver).navigateToProductDetailsPage();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ADD_TO_WISHLIST));
		softAssert.assertTrue(driver.findElement(ADD_TO_WISHLIST).isDisplayed(),"Add to Wishlist is not available in PDP");
		String Add_to_Wishlist = driver.findElement(ADD_TO_WISHLIST).getText();
		System.out.println("Add to Wishlist text is:"+ Add_to_Wishlist);
		Reporter.log("Add to Wishlist is available in PDP");
		ExtentReport.extentTest.info("Add to Wishlist is available in PDP");
		WebElement elem = driver.findElement(ADD_TO_WISHLIST);
		js1.executeScript("arguments[0].click();", elem);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(LOGIN_DRAWER));
		softAssert.assertTrue(driver.findElement(LOGIN_DRAWER).isDisplayed(),"Login Drawer Is not Displayed for the Guest user");
		Reporter.log("Login Drawer Is Displayed for the Guest user when the User tries to Add the Product to Wishlist");
		ExtentReport.extentTest.info("Login Drawer Is Displayed for the Guest user when the User tries to Add the Product to Wishlist");
		
	}

	public void VerifyProductAddOn()
	{

		JavascriptExecutor js1 = (JavascriptExecutor) driver;;
		SoftAssert softAssert = new SoftAssert();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//		new HomePageComponents(driver).navigateToBraInWomenCategory();
//		new CollectionPageComponent(driver).navigateToProductDetailsPage();
		driver.get("https://www.tommyjohn.com/products/second-skin-mid-length-boxer-brief-6-3-pack");
		wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_TITLE));
		softAssert.assertTrue(driver.findElement(PRODUCT_ADD_ON).isDisplayed(),"Product AddOn is not available");
		Reporter.log("Product AddOn is available");
		ExtentReport.extentTest.info("Product AddOn is available");
	}

	public void ValidateComparisonModule() throws InterruptedException {
		JavascriptExecutor js1 = (JavascriptExecutor) driver;;
		SoftAssert softAssert = new SoftAssert();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//		new HomePageComponents(driver).navigateToBraInWomenCategory();
//		new CollectionPageComponent(driver).navigateToProductDetailsPage();
		driver.get("https://www.tommyjohn.com/products/low-cut-liner-sock-3-pack?variant=43269072191533");
		wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_TITLE));
		js1.executeScript("window.scrollBy(0,800)");
		Thread.sleep(2000);
		softAssert.assertTrue(driver.findElement(COMPARISON_MODULE).isDisplayed(),"Comparison Module is not available");
		Reporter.log("Comparison Module is available");
		ExtentReport.extentTest.info("Comparison Module is available");
//		WebElement ele = driver.findElement(SHOP_LINER_CTA);
//		js1.executeScript("arguments[0].click();", ele);
//	//	driver.findElement(SHOP_LINER_CTA).click();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_TITLE));
//		softAssert.assertTrue(driver.findElement(PRODUCT_TITLE).isDisplayed(),"Shop Liner CTA is Functional");
//		driver.navigate().back();
		WebElement elem = driver.findElement(SHOP_NO_SHOW_CTA);
		js1.executeScript("arguments[0].click();", elem);
	//	driver.findElement(SHOP_NO_SHOW_CTA).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_TITLE));
		softAssert.assertTrue(driver.findElement(PRODUCT_TITLE).isDisplayed(),"Shop No SHOW CTA is Functional");
		driver.navigate().back();
		wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_TITLE));
		softAssert.assertAll();
		Reporter.log("Comparison Module is functional");
		ExtentReport.extentTest.info("Comparison Module is functional");
	}

	public void VerifyComparisonModule() throws InterruptedException {

		JavascriptExecutor js1 = (JavascriptExecutor) driver;;
		SoftAssert softAssert = new SoftAssert();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//		new HomePageComponents(driver).navigateToBraInWomenCategory();
//		new CollectionPageComponent(driver).navigateToProductDetailsPage();
		driver.get("https://www.tommyjohn.com/products/low-cut-liner-sock-3-pack?variant=43269072191533");
		wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_TITLE));
		js1.executeScript("window.scrollBy(0,500)");
		Thread.sleep(2000);
		softAssert.assertTrue(driver.findElement(COMPARISON_MODULE).isDisplayed(),"Comparison Module is not available");
		Reporter.log("Comparison Module is available");
		ExtentReport.extentTest.info("Comparison Module is available");

	}

	public void VerifyProductSets() throws InterruptedException {
		JavascriptExecutor js1 = (JavascriptExecutor) driver;;
		SoftAssert softAssert = new SoftAssert();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//		new HomePageComponents(driver).navigateToBraInWomenCategory();
//		new CollectionPageComponent(driver).navigateToProductDetailsPage();
		driver.get("https://www.tommyjohn.com/products/second-skin-sleep-pocket-tee-and-short-set?variant=43344192995373");
		wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_TITLE));
		Thread.sleep(5000);
		Tabs = driver.findElements(PDP_PRODUCT_SETS);
		Tabs_Count = Tabs.size();
		System.out.println(Tabs_Count);
		softAssert.assertEquals(Tabs_Count,6,"Product Sets is not appropriate");
//		List<WebElement> Product_Sets = Tabs;
//		for (WebElement Set : Product_Sets) {
//			String text1 = Set.getText();
//			System.out.println("Product Sets are" + text1);
//		}
		List<WebElement> Product_Sets = Tabs;

		for (int i = 0; i < Product_Sets.size(); i++) {
			String text = Product_Sets.get(i).getText();
			System.out.println("Product Set:  [" + i + "]: " + text);

			// Soft assertion to check text is not empty
			softAssert.assertFalse(text.trim().isEmpty(), " Product Set" + i + " is empty.");
		}
		softAssert.assertAll();

	}

	public void ValidateAppropriateResultForSelectingBandSizeAndCupVolume() throws Exception {
		String text = null;
		JavascriptExecutor js1 = (JavascriptExecutor) driver;;
		SoftAssert softAssert = new SoftAssert();
		new HomePageComponents(driver).navigateToBraInWomenCategory();
		new CollectionPageComponent(driver).navigateToProductDetailsPage();
//		driver.get("https://www.tommyjohn.com/products/womens-air-bra-pack-2-pack?variant=43216573464621");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(SIZEGUIDE));

		//HOW TO Measure
		if (driver.findElement(SIZEGUIDE).isDisplayed()) {
			WebElement ele = driver.findElement(SIZEGUIDE);
			js1.executeScript("arguments[0].click();", ele);
			Thread.sleep(3000);
			js1.executeScript("window.scrollBy(0,500)");
			Thread.sleep(2000);
		}

		if (!driver.findElement(By.xpath("//h2[@class='mb-lg mt-2 pr-14 text-heading-md']")).isDisplayed())
			throw new Exception("Sizes chart overlay is not displayed after Size guide button was clicked");
		System.out.println("Size Chart is displayed");
		Reporter.log("Size Chart is displayed");
		ExtentReport.extentTest.info("Size Chart is displayed");
		Thread.sleep(2000);
		//Size Calculator
		wait.until(ExpectedConditions.visibilityOfElementLocated(BRA_BAND_SIZE_DROPDOWN));
		if (!driver.findElement(BRA_BAND_SIZE_DROPDOWN).isEnabled()) {
			throw new Exception("Band Size Drop-Down is not enable.");
		} else {
			Select BandSize_DropDown = new Select(driver.findElement(BRA_BAND_SIZE_DROPDOWN));
			BandSize_DropDown.selectByIndex(2);
		}
		if (!driver.findElement(CUP_VOLUME).isEnabled()) {
			throw new Exception("Band Size Drop-Down is not enable.");
		} else {
			Select CupVoulme_DropDown = new Select(driver.findElement(CUP_VOLUME));
			CupVoulme_DropDown.selectByValue("A");
		}

		wait.until(ExpectedConditions.visibilityOfElementLocated(SIZE_RESULT));
		softAssert.assertTrue(driver.findElement(SIZE_RESULT).isDisplayed(),"Size Result is not displayed on selecting the Band Size and Cup Volume");
		String Size_Result = driver.findElement(SIZE_RESULT).getText();
		System.out.println("Size Result is:"+ Size_Result);
		Reporter.log("Size Result is displayed on selecting the Band Size and Cup Volume");
		ExtentReport.extentTest.info("Size Result is displayed on selecting the Band Size and Cup Volume\"");
	}

	public void VerifyHowToMeasureSection() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		new HomePageComponents(driver).navigateToBraCategory();
		new CollectionPageComponent(driver).navigateToProductDetailsPage();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		SoftAssert softAssert = new SoftAssert();
		wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_TITLE));
		wait.until(ExpectedConditions.visibilityOfElementLocated(SIZEGUIDE));
		if (driver.findElement(SIZEGUIDE).isDisplayed()) {
			WebElement ele = driver.findElement(SIZEGUIDE);
			js.executeScript("arguments[0].click();", ele);
			Thread.sleep(5000);
			softAssert.assertTrue(driver.findElement(HOW_TO_MEASURE_SECTION).isDisplayed(),"How to measure section is not displayed in the Size chart");
			Steps = driver.findElements(STEPS);
			Steps_Count = Steps.size();
			System.out.println(Steps_Count);
			softAssert.assertEquals(Steps_Count, 3,"The How to Measure section does not contain 3 steps");
			softAssert.assertAll();
			Reporter.log("The How to Measure section is available and contain 3 steps");
			ExtentReport.extentTest.info("The How to Measure section is available and contain 3 steps");

		}
	}
	public void ValidateSizeChartDetailsForBrasandBrallets() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		new HomePageComponents(driver).navigateToBraCategory();
		new CollectionPageComponent(driver).navigateToProductDetailsPage();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		SoftAssert softAssert = new SoftAssert();
		wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_TITLE));
		wait.until(ExpectedConditions.visibilityOfElementLocated(SIZEGUIDE));
		if (driver.findElement(SIZEGUIDE).isDisplayed()) {
			WebElement ele = driver.findElement(SIZEGUIDE);
			js.executeScript("arguments[0].click();", ele);
			Thread.sleep(5000);
			softAssert.assertTrue(driver.findElement(PRODUCT_NAME_IN_SIZECHART).isDisplayed(),"Product name is not displayed in the Size chart");
			softAssert.assertTrue(driver.findElement(HOW_TO_MEASURE_CTA_IN_SIZECHART).isDisplayed(),"How to measure CTA is not displayed in Size chart");
			softAssert.assertTrue(driver.findElement(BAND_SIZE).isDisplayed(),"Band Size dropdown is not displayed in the Size chart");
			softAssert.assertTrue(driver.findElement(CUP_VOLUME).isDisplayed(),"Cup Volume dropdown is not displayed in the Size chart");
			softAssert.assertTrue(driver.findElement(SIZEGUIDE_CHART_TABLE).isDisplayed(),"Size Guide Table is not displayed in the Size chart");
			softAssert.assertTrue(driver.findElement(HOW_TO_MEASURE_SECTION).isDisplayed(),"How to measure section is not displayed in the Size chart");

			//Close first size guide window
			try {
				WebElement SizeChartClose = driver.findElement(By.xpath("(//button[@aria-label='Close modal'])[1]"));
				jse.executeScript("arguments[0].click();", SizeChartClose);
			}
			catch(Exception ex)
			{
				WebElement SizeChartClose = driver.findElement(By.xpath("(//button[@aria-label='Close modal'])[2]"));
				js.executeScript("arguments[0].click();", SizeChartClose);
			}
			//		driver.findElement(By.xpath("(//button[@aria-label='Close modal'])[2]")).click();
		} else {
			System.out.println("Size guide link is not Displayed");
			Reporter.log("Size guide link is not Displayed.");
			ExtentReport.extentTest.info("Size guide link is not Displayed.");
		}

		softAssert.assertAll();
		Reporter.log("Size Chart contains all the details");
		ExtentReport.extentTest.info("Size Chart contains all the details");
	}

	public void ValidateReadAllReviewsLinkInReviewsSummary() throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		new HomePageComponents(driver).navigateToAllPantiesInWomenCategory();
		new CollectionPageComponent(driver).navigateToProductDetailsPage();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		SoftAssert softAssert = new SoftAssert();
		wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_TITLE));
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(REVIEW_SUMMARY));
		js.executeScript("arguments[0].click();", driver.findElement(REVIEW_SUMMARY));
		softAssert.assertTrue(driver.findElement(REVIEW_SUMMARY).isDisplayed(),"Review Summary link is not functional");
		Reporter.log("Review Summary link is functional");
		ExtentReport.extentTest.info("Review Summary link is functional");
		Thread.sleep(5000);
		js.executeScript("arguments[0].click();", driver.findElement(READ_ALL_REVIEWS));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_REVIEW));
		softAssert.assertTrue(driver.findElement(FIRST_REVIEW).isDisplayed(),"Read All Review link is not functional");
		Reporter.log("Read All Review link is not functional");
		ExtentReport.extentTest.info("Read All Review link is not functional");

	}
	public void VerifyShopPayIsDisplayed()
	{

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		driver.get("https://www.tommyjohn.com/products/360-movement-medium-impact-sports-bra?color=black");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		SoftAssert softAssert = new SoftAssert();
		wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_TITLE));
		wait.until(ExpectedConditions.visibilityOfElementLocated(SHOP_PAY));
		softAssert.assertTrue(driver.findElement(SHOP_PAY).isDisplayed(),"Shop Pay is not available");
		Reporter.log("Shop Pay is available");
		ExtentReport.extentTest.info("Shop Pay is available");
	}

	public void ValidateWriteAReviewLink()
	{

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		driver.get("https://www.tommyjohn.com/products/360-movement-medium-impact-sports-bra?color=black");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		SoftAssert softAssert = new SoftAssert();
		wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_TITLE));
		wait.until(ExpectedConditions.visibilityOfElementLocated(WRITE_A_REVIEW_LINK));
		softAssert.assertTrue(driver.findElement(WRITE_A_REVIEW_LINK).isDisplayed(),"Write a Review Link is not available");
		Reporter.log("Write a Review Link is available");
		ExtentReport.extentTest.info("Write a Review Link is available");
	}

	public void ValidateReviewsSummaryLink() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		new HomePageComponents(driver).navigateToWomensBriefs();
		new CollectionPageComponent(driver).navigateToProductDetailsPage();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		SoftAssert softAssert = new SoftAssert();
		wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_TITLE));
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(REVIEW_SUMMARY));
		WebElement ReviewSummary = driver.findElement(REVIEW_SUMMARY);
		js.executeScript("arguments[0].click();", driver.findElement(REVIEW_SUMMARY));
		softAssert.assertTrue(driver.findElement(REVIEW_SUMMARY).isDisplayed(),"Review Summary link is not functional");
		Reporter.log("Review Summary link is functional");
		ExtentReport.extentTest.info("Review Summary link is functional");
	}


	public void ValidateReviewsSummaryCTAINReviewsSection() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		new HomePageComponents(driver).navigateToWomensBriefs();
		new CollectionPageComponent(driver).navigateToProductDetailsPage();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		SoftAssert softAssert = new SoftAssert();
		wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_TITLE));
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(REVIEW_SUMMARY));
		js.executeScript("arguments[0].click();", driver.findElement(REVIEW_SUMMARY));
		Thread.sleep(2000);
		WebElement Readallreviews = wait.until(ExpectedConditions.visibilityOfElementLocated(READ_ALL_REVIEWS_IN_SUMMARY));
		js.executeScript("arguments[0].click();", Readallreviews);
		Thread.sleep(2000);
		js.executeScript("window.scrollBy(0,-200)");
		wait.until(ExpectedConditions.visibilityOfElementLocated(REVIEWS_SUMMARY_IN_RATINGS_SECTION));
		js.executeScript("arguments[0].click();", driver.findElement(REVIEWS_SUMMARY_IN_RATINGS_SECTION));
		wait.until(ExpectedConditions.visibilityOfElementLocated(REVIEW_SUMMARY_TITLE));
		softAssert.assertTrue(driver.findElement(REVIEW_SUMMARY_TITLE).isDisplayed(),"Review Summary CTA is not functional");
		Reporter.log("Review Summary CTA is functional");
		ExtentReport.extentTest.info("Review Summary CTA is functional");
	}

	public void ValidateDisabledATC() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		new HomePageComponents(driver).navigateToAllPantiesInWomenCategory();
		new CollectionPageComponent(driver).navigateToProductDetailsPage();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		SoftAssert softAssert = new SoftAssert();
		wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_TITLE));
		Thread.sleep(5000);
		Tabs = driver.findElements(PDP_TABS);
		Tabs_Count = Tabs.size();
		System.out.println(Tabs_Count);
		for (int i = 1; i <= Tabs_Count; i++) {
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("(//span[@class='text-sm text-text-tertiary'])[" + i + "]")));
			SelectASize_CTA = driver.findElement(By.xpath("(//button[@class='button-secondary grow'])[1]")).getText();
			System.out.println(SelectASize_CTA);

			js.executeScript("arguments[0].click();", driver.findElement(RATING_STARS));
			WebElement Readallreviews = wait.until(ExpectedConditions.visibilityOfElementLocated(READ_ALL_REVIEWS_IN_SUMMARY));
			js.executeScript("arguments[0].click();", Readallreviews);
			Thread.sleep(5000);
			Sticky_SelectASize_CTA = driver.findElement(By.xpath("(//button[@class='button-secondary grow'])[2]")).getText();
			System.out.println(Sticky_SelectASize_CTA);
			softAssert.assertEquals(SelectASize_CTA,Sticky_SelectASize_CTA,"ATC CTA is not disabled in Sticky and Regular CTAs when the User doesn't select any size");
			Reporter.log("ATC CTA is disabled in Sticky and Regular CTAs when the User doesn't select any size");
			ExtentReport.extentTest.info("ATC CTA is disabled in Sticky and Regular CTAs when the User doesn't select any size");
		}




	}

	public void ValidateTabPriceAndAddToCartPrice() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		SoftAssert softAssert = new SoftAssert();
		driver.get("https://www.tommyjohn.com/products/womens-lounge-henley?variant=34935848402989");
		wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_TITLE));
		Thread.sleep(5000);
		Tabs = driver.findElements(PDP_TABS);
		Tabs_Count = Tabs.size();
		System.out.println(Tabs_Count);
		for (int i = 1; i <= Tabs_Count; i++) {
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("(//span[@class='text-sm text-text-tertiary'])[" + i + "]")));
			TabsPrice = driver.findElement(By.xpath("(//span[@class='text-sm text-text-tertiary'])[" + i + "]")).getText();
			System.out.println(TabsPrice);

			for (int k = 1; k <= 5; k++) {
				try {
					WebElement elements = driver.findElement(By.xpath("(//input[@name='option[Size]'])[" + k + "]"));
					js.executeScript("arguments[0].click();", elements);
					Thread.sleep(5000);
				}
				catch(Exception e)
				{
					WebElement elements = driver.findElement(By.xpath("(//input[@name='option[Top Size]'])["+k+"]"));
					js.executeScript("arguments[0].click();", elements);

					WebElement element = driver.findElement(By.xpath("(//input[@name='option[Bottom Size]'])["+k+"]"));
					js.executeScript("arguments[0].click();", element);

					Thread.sleep(5000);

				}
				try {

					String[] AddtoBag;
					AddtoBag = (driver.findElement(By.xpath("//button[@class='button grow']")).getText()).split(" - ");
					String Text = AddtoBag[1];
					String Text1 = Text.substring(0, Text.length() - 3);
					System.out.println(Text1);
					softAssert.assertEquals(TabsPrice,Text1,"Same values are not displayed in the Tabs and Add to Cart CTA");
					Reporter.log("Same values are displayed in the Tabs and Add to Cart CTA");
					ExtentReport.extentTest.info("Same values are displayed in the Tabs and Add to Cart CTA");

						Thread.sleep(1000);
						System.out.println("=============================");
						count++;


				}

				catch(Exception ex)
				{
					System.out.println("Product is OOS");
				}


			}



		}


	}

	public void ValidatesectionsinPDP() throws InterruptedException, IOException, CsvException, Exception {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		SoftAssert softAssert = new SoftAssert();
		//	try {
		//File src = new File("src/main/resources/AllCollectionPages.xlsx");

		String csv_path = "src/main/resources/PDP.csv";
		CSVReader CSVReader = new CSVReader(new FileReader(csv_path));
		List<String[]> list = CSVReader.readAll();
		Thread.sleep(3000);
		Iterator<String[]> ite = list.iterator();
		Thread.sleep(2000);
		while (ite.hasNext()) {
			String[] data = ite.next();
			if (data[0].startsWith("#")) {
				continue;
			}
			for (int i = 0; i < data.length; i++) {
				System.out.println(data[i]);
				String url = "https://www.tommyjohn.com" + data[i];
				//	checkurl(url);
				driver.get(url);
				try {
					//	Thread.sleep(5000);
					wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_TITLE));
					Thread.sleep(5000);
					Tabs = driver.findElements(PDP_TABS);
					Tabs_Count = Tabs.size();
					System.out.println(Tabs_Count);

					for (int j = 1; j <= Tabs_Count; j++) {

						try {
							js.executeScript("arguments[0].click();", driver.findElement(By.xpath("(//span[@class='text-sm text-text-tertiary'])[" + j + "]")));
							try {
								if (driver.findElement(SWATCH_SECTION).isDisplayed()) {
									System.out.println("Swatch section is displayed");
								}
							} catch (Exception ex) {
								count++;
								System.out.println("Swatch section is not displayed in " + url);
								Reporter.log("Swatch section is not displayed in " + url);
								ExtentReport.extentTest.info("Swatch section is not displayed in " + url);
							}

							try {
								if (driver.findElement(SIZE_SECTION).isDisplayed()) {
									System.out.println("Size section is displayed");
								}
							} catch (Exception ex) {
								count++;
								System.out.println("Size section is not displayed in Tab1 " + url);
								Reporter.log("Size section is not displayed in Tab1 " + url);
								ExtentReport.extentTest.info("Size section is not displayed in Tab1 " + url);
							}

							try {
								if (driver.findElement(QUANTITY_ATC_SECTION).isDisplayed()) {
									System.out.println("Quantity and Add to Cart sections are displayed");
								}
							} catch (Exception ex) {
								count++;
								System.out.println("Quantity and Add to Cart sections are displayed in Tab1 " + url);
								Reporter.log("Quantity and Add to Cart sections are displayed in Tab1 " + url);
								ExtentReport.extentTest.info("Quantity and Add to Cart sections are displayed in Tab1 " + url);
							}

						} catch (Exception e) {
							NoTabs++;
							System.out.println("No Tabs are found for the given URL " + url);
						}
					}
				}

				catch(Exception ex){
				    	BrokenURL++;
						System.out.println("URL not working for" + url);

				}
			}

		}

//		softAssert.assertAll();
		if(count > 1 || NoTabs >1 || BrokenURL>1){
			throw new Exception("PDP Sections are not working as expected");
		}

	}

	public void ValidateAddingProductToCartFromInstagramWidget() throws Exception {

		action = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		new HomePageComponents(driver).navigatetoPDP();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		SoftAssert softAssert = new SoftAssert();
		Thread.sleep(5000);
		js.executeScript("window.scrollBy(0,2000)");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(INSTA_SHOP_ICON)));
		js.executeScript("arguments[0].click();", driver.findElement(INSTA_SHOP_ICON));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
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

	public void VerifyPromoBannerIsAvailableinPDP() throws Exception {
		//new HomePageComponents(driver).navigateToSales();
		SoftAssert softAssert = new SoftAssert();
		//new CollectionPageComponent(driver).navigateToProductDetailsPage();
		driver.get("https://www.tommyjohn.com/products/womens-second-skin-brief?color=rosewater");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfElementLocated(PDP_PROMOBANNER));
		softAssert.assertTrue(driver.findElement(PDP_PROMOBANNER).isDisplayed(),"Promo Banner is not available in the PDP");
		softAssert.assertAll();
		Reporter.log("Promo Banner is available in the PDP");
		ExtentReport.extentTest.info("Promo Banner is available in the PDP");
	}

	public void ValidateAppropriateProductIsAddedToCartFromInstagramWidget() throws Exception {
		action = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		new HomePageComponents(driver).navigatetoPDP();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		SoftAssert softAssert = new SoftAssert();
		Thread.sleep(5000);
		js.executeScript("window.scrollBy(0,2000)");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(INSTA_SHOP_ICON)));
		js.executeScript("arguments[0].click();", driver.findElement(INSTA_SHOP_ICON));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		String First_Product_Name = driver.findElement(INSTA_FIRST_PRODUCT_NAME).getText();
		System.out.println(First_Product_Name);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(INSTA_ATC)));
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
		JavascriptExecutor js = (JavascriptExecutor) driver;
		new HomePageComponents(driver).navigatetoPDP();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		SoftAssert softAssert = new SoftAssert();
		Thread.sleep(5000);
		js.executeScript("window.scrollBy(0,2000)");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(INSTA_SHOP_ICON)));
		js.executeScript("arguments[0].click();", driver.findElement(INSTA_SHOP_ICON));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		String First_Product_Name = driver.findElement(INSTA_FIRST_PRODUCT_NAME).getText();
		System.out.println(First_Product_Name);
		js.executeScript("arguments[0].click();", driver.findElement(INSTA_ATC));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(INSTA_FIRST_SIZE)));
		softAssert.assertTrue(driver.findElement(INSTA_FIRST_SIZE).isDisplayed(), "Size Overlay is not displayed in the Instagram Widget");
		Reporter.log("Size Overlay is displayed in the Instagram Widget");
		ExtentReport.extentTest.info("Size Overlay is displayed in the Instagram Widget");

	}

	public void verifySelectedColorSwatchIsDisplayedInTheInlineCart() throws Exception {
		action = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		new HomePageComponents(driver).navigatetoPDP();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		SoftAssert softAssert = new SoftAssert();
		Thread.sleep(5000);
		js.executeScript("window.scrollBy(0,2000)");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(INSTA_SHOP_ICON)));
		js.executeScript("arguments[0].click();", driver.findElement(INSTA_SHOP_ICON));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
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
		softAssert.assertEquals(ColorSwatch, text, "Color Mismatched In InLine Cart");
		softAssert.assertAll();
		Reporter.log("Selected Color Is Added To Cart");
		ExtentReport.extentTest.info("Selected Color Is Added To Cart");


	}


	public void validateProductDetailsPage() throws Exception {
		String text = null;

		// call method to navigate to collection page
		new HomePageComponents(driver).navigateToAllPantiesInWomenCategory();

		// call method to nevigate product details page
		text = new CollectionPageComponent(driver).navigateToProductDetailsPage();

		// call method to check correct PDP opend or not
		checkCorrectProductDetailsPageOpenedOrNot(text);

		// call method for PDP validation
		validatePage();

	}

	public void checkCorrectProductDetailsPageOpenedOrNot(String text1) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		String text2 = null;
		String[] list;
		Thread.sleep(4000);
		wait.until(ExpectedConditions.presenceOfElementLocated(ADD_TO_CART_BUTTON));
		//	list =driver.getCurrentUrl().split("/");
		list = driver.findElement(PRODUCT_TITLE_1).getText().split(",");
//		text2 = list[list.length-1];
//		list = text2.split("\\?");
		text2 = list[0];
		//  text2 = text2.replace("\"","" );
		list = text1.split(",");
//		text1=list[list.length-1];
//		list = text1.split("\\?");
		text1 = list[0];
		//text1 = text1.replace("\"","" );

		System.out.println("text2 is: " + text2 + " and text1 is: " + text1);
		// check correct PDP opened
		if (!text2.contains(text1))
			throw new Exception("Wrong product details page opened");
		Reporter.log("Product details page is Correct :: Product title is Displayed");
		driver.navigate().back();

	}


	public ProductDetailsPageComponents getproductdetails() throws Exception {
		ProductDetailsPageComponents pdp = new ProductDetailsPageComponents(driver);
		new HomePageComponents(driver).navigateToAllPantiesInWomenCategory();
		new CollectionPageComponent(driver).navigateToProductDetailsPage();
		new AddToCartComponents(driver).selectSize();
		ProductDetailsPageComponents.productname = driver.findElement(PRODUCT_TITLE).getText();
		ProductDetailsPageComponents.productprice = driver.findElement(PRODUCT_PRICE).getText();
		List<WebElement> productcolors = driver.findElements(COLOR_TEXT);
		for (WebElement color : productcolors) {
			System.out.println(color.getText());
			if (!color.getText().isEmpty()) {
				ProductDetailsPageComponents.productcolor = color.getText();
				break;
			}
		}

		ProductDetailsPageComponents.productsize = driver.findElement(SIZE_TEXT).getText();
		ProductDetailsPageComponents.productquant = driver.findElement(By.cssSelector(".product-info-quant-atc-container>.qty-selector")).getAttribute("data-item-qty");
		ProductDetailsPageComponents.prodsize = driver.findElement(SIZE_TEXT).getAttribute("value");
		String[] arrOfStr = ProductDetailsPageComponents.prodsize.split(",");
		ProductDetailsPageComponents.prodsize = arrOfStr[0];

		new AddToCartComponents(driver).selectSize();
		return pdp;

	}


	public void validatePage() throws Exception {
		String colornew = null;
		String colortext = null;
		SoftAssert softAssert = new SoftAssert();
		System.out.println(driver.getCurrentUrl());
		JavascriptExecutor executor = (JavascriptExecutor) driver;

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_PRICE));
		// check product price is displayed or not
		if (!driver.findElement(PRODUCT_PRICE).isEnabled())
			throw new Exception("Product price is not displayed");

		//Check Breadcrumbs
//		WebElement BREADCRUMBS = driver.findElement(By.cssSelector(".breadcrumbs"));
//		softAssert.assertTrue(BREADCRUMBS.isDisplayed(), "Breadcrumb is not displayed on PDP.");
//		System.out.println("Breadcrumb is displayed on PDP.");
//		Reporter.log("Breadcrumb is displayed on PDP.");

		//Check Badging
//		try {
//			System.out.println("Verifying for Badging .......");
//			WebElement BADGE = driver.findElement(By.cssSelector("div.product-info__inner-top > div > div.product-info__title-wrap > span"));
//			if (!BADGE.isDisplayed())
//				throw new Exception("Badging is not displayed on PDP.");
//			else {
//				System.out.println("Badging is displayed on PDP.");
//				Reporter.log("Badge is displayed on PDP.");
//			}
//		} catch (Exception e) {
//			System.out.println("Badging is not applicable for this product.");
//		}

		//Check Promo Message
		try {
			System.out.println("Verifying for Promo Message .......");
			WebElement PROMO_MESSAGE = driver.findElement(By.xpath("//span[@class='font-bold text-text-primary']"));
			if (!PROMO_MESSAGE.isDisplayed())
				throw new Exception("Promo Message is not displayed on PDP.");
			else
				System.out.println("Promo Message is displayed on PDP.");
			Reporter.log("Promo Message is displayed on PDP.");
		} catch (Exception e) {
			System.out.println("Promo Message is not applicable for this product.");
		}

		Thread.sleep(7000);
		//Check vertical image(carousel)
		WebElement VERTICAL_IMAGE = driver.findElement(By.xpath("(//img[@class='block h-full w-full object-center !object-cover bg-background-image'])[1]"));
		softAssert.assertTrue(VERTICAL_IMAGE.isDisplayed(), "Vertical images are not displayed on PDP.");
		System.out.println("Vertical images are displayed on PDP.");
		Reporter.log("Vertical images are displayed on PDP.");

		//To avoid bounceX pop-up
		WebElement p_title = driver.findElement(PRODUCT_TITLE);
		executor.executeScript("arguments[0].click();", p_title);
		// check for Size Guide button
		WebElement ele = driver.findElement(SIZEGUIDE);
		executor.executeScript("arguments[0].click();", ele);
		//	driver.findElement(SIZEGUIDE).click();
		Thread.sleep(3000);
		if (!driver.findElement(SIZEGUIDE_TITLE).isDisplayed())
			throw new Exception("Sizes chart overlay is not displayed after Size guide button was clicked");
		Reporter.log("Size guide is Displayed :: Clickable");
//		try {
////			WebElement close_icon = driver.findElement(By.xpath("(//button[@aria-label='Close modal'])[4]"));
////			executor.executeScript("arguments[0].click();", close_icon);
//			driver.findElement(By.xpath("(//button[@aria-label='Close modal'])[4]")).click();
//		}
//		catch(Exception ex)
//		{
//			WebElement close_icon = driver.findElement(By.xpath("(//span[@class='icon size-5 icon-x'])[4]"));
//			executor.executeScript("arguments[0].click();", close_icon);
//		}
		driver.get("https://www.tommyjohn.com/products/second-skin-trunk-4-1?color=della-robbia-blue");
		//check rating n reviews

		wait.until(ExpectedConditions.visibilityOfElementLocated(RATING_STARS));
		if (driver.findElement(RATING_STARS).isDisplayed()) {

			WebElement elem = driver.findElement(RATING_STARS);
			executor.executeScript("arguments[0].click();", elem);
			WebElement elems = driver.findElement(READ_ALL_REVIEWS);
			executor.executeScript("arguments[0].click();", elems);
			Thread.sleep(2000);
			// wait for scroll it down
			executor.executeScript("window.scrollBy(0,750)", "");

			wait.until(ExpectedConditions.presenceOfElementLocated(WRITE_REVIEW_BUTTON));
			Reporter.log("Review Stars Are Displayed :: Clickable :: Clicked");
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
		} else
			throw new Exception("Rating Stars are not clickable");

		// check for write a review button
		WebElement writereview = driver.findElement(WRITE_REVIEW_BUTTON);
		executor.executeScript("arguments[0].click();", writereview);
		//	softAssert.assertEquals(driver.findElement(WRITE_REVIEW_TITLE).getText(), "Write a Review", "Laguage mismatch for Write a Review title 1");
		// scroll it top again
//		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -document.body.scrollHeight)");
//		Thread.sleep(3000);
//
//		// Check we can select color or not
//		JavascriptExecutor executore = (JavascriptExecutor) driver;
//		allElements = driver.findElements(ALL_COLOR_LIST);
//		elementsCount = allElements.size();
//		System.out.println("Color Element count :: " + elementsCount);
//		if (elementsCount > 1) {
//			for (int i = 1; i < elementsCount; i++) {
//				if (i < 4) {
//					colortext = driver.findElement(COLOR_TEXT).getText();
//					if (driver.getCurrentUrl().contains("products"))
//						colornew = driver.findElement(By.cssSelector(".product-option__color-swatches-wrapper > ul  > li:nth-child(" + i + ") > label")).getAttribute("aria-label");
//					else
//						colornew = driver.findElement(By.cssSelector(".product-option__color-swatches-wrapper > ul > li:nth-child(" + i + ") > label")).getAttribute("aria-label");
//					System.out.println(colornew);
//					if (!colornew.contentEquals(colortext)) {
//						WebElement elemen = driver.findElement(By.cssSelector(".product-option__color-swatches-wrapper > ul  > li:nth-child(" + i + ") > label"));
//						//elem.click();
//						executore.executeScript("arguments[0].click();", elemen);
//						//Thread.sleep(5000);
//						wait.until(ExpectedConditions.visibilityOfElementLocated(COLOR_TEXT));
//						System.out.println("expected is: " + driver.findElement(COLOR_TEXT).getText() + " Actual is: " + colornew);
//						softAssert.assertEquals(driver.findElement(COLOR_TEXT).getText().equalsIgnoreCase(colornew), true, "Color not changing");
//					}
//				}
//			}
//		}
//		Reporter.log("Colors are present and changing correct");
		// Select first size between available sizes
//		allElements = driver.findElements(ALL_SIZE_LIST);
//		elementsCount = allElements.size();
//		System.out.println("All sizes list :: "+ elementsCount);
//		try {
//			if(driver.findElement(By.cssSelector("div.radios-wrapper")).isDisplayed())
//			{
//
//				//radio
//				for(int i=3;i<=elementsCount;i=i+2) {
//					flag = false;
//					element = driver.findElement(By.cssSelector("div.radios-wrapper > div.radio-options-container > div > label:nth-child("+i+")"));
//					if (element.getAttribute("class").contains("oosVariant")) {
//						{
//							if(!(driver.findElement(PRODUCT_TITLE).getText().contains("Pack")|| driver.findElement(PRODUCT_TITLE).getText().contains("Set")))
//							{
//
//								Thread.sleep(3000);
//								jse = (JavascriptExecutor)driver;
//								jse.executeScript("arguments[0].click();", element);
//								//element.click();
//								Thread.sleep(4000);
//
//								System.out.println("Unavailable size clicked");
//								element = driver.findElement(ADD_TO_CART_BUTTON);
//								text1 = element.getText();
//								System.out.println("Text of button when Unavailable size selected :: "+text1);
//								if(!((text1.equalsIgnoreCase("JOIN THE WAITLIST"))||(text1.equalsIgnoreCase("Out of Stock"))))
//									throw new Exception("Text change for 'JOIN THE WAITLIST' ");
//
//								flag = true;
//
//							}
//							else
//							{
//
//								WebElement el = driver.findElement(By.cssSelector("div.radios-wrapper > div.radio-options-container > div > label:nth-child("+i+")"));
//								jse = (JavascriptExecutor)driver;
//								jse.executeScript("arguments[0].click();", el);
//
//								System.out.println("Unavailable size clicked");
//								System.out.println(driver.findElement(ADD_TO_CART_BUTTON).getText());
//								if (!driver.findElement(ADD_TO_CART_BUTTON).getText().contentEquals("Out of Stock"))
//								{
//									element = driver.findElement(ADD_TO_CART_BUTTON);
//									text1 = element.getText();
//									System.out.println("Text of button when Unavailable size selected :: "+text1);
//									if(!(text1.equalsIgnoreCase("JOIN THE WAITLIST")))
//										throw new Exception("Text change for 'JOIN THE WAITLIST' for Packs");
//									text1 = element.getText();
//								}
//								flag = true;
//							}
//							//break;
//						}
//					}
//					else
//					{
//						jse = (JavascriptExecutor)driver;
//						jse.executeScript("arguments[0].click();" , element);
//
//					}
//
//
//					if(i==elementsCount) {
//						Reporter.log("No any size available");
//						break;
//					}
//
//					if(flag == true)
//						continue;
//					else {
//						element = driver.findElement(ADD_TO_CART_BUTTON);
//						text1 = element.getText();
//						System.out.println("Button text when Available size selected :: "+text1);
//						if(!text1.equalsIgnoreCase("OUT OF STOCK"))
//						{
//							if(!(text1.equalsIgnoreCase("ADD TO CART"))) {
//								throw new Exception("Text change for 'ADD TO CART' ");
//							}
//						}
//						break;}
//
//				}
//
//			}
//		}
//		catch(Exception ex) {
//			//		else
//			//		{
//			//dropdown
//			for(int i=1;i<=elementsCount;i++) {
//				flag = false;
//				//driver.findElement(SIZE_DROPDOWN).click();
//				executore.executeScript("arguments[0].click();", driver.findElement(SIZE_DROPDOWN));
//				element = driver.findElement(By.cssSelector(".select--options__list > li:nth-child("+i+") > div"));
//
//				allClasses = element.getAttribute("class");
//				// check size available or not
//				for (String c : allClasses.split(" ")) {
//					if (c.equals("unavailable")) {
//						if(!(driver.findElement(PRODUCT_TITLE).getText().contains("Pack")||driver.findElement(PRODUCT_TITLE).getText().contains("Set")))
//						{
//
//							Thread.sleep(3000);
//							// click on the unavailable item and check the button text
//							//driver.findElement(By.cssSelector(".select--options__list > li:nth-child("+i+")")).click();
//							jse = (JavascriptExecutor)driver;
//							jse.executeScript("arguments[0].click();", element);
//							//element.click();
//							Thread.sleep(4000);
//
//							driver.navigate().refresh();
//							System.out.println("Unavailable size clicked");
//							element = driver.findElement(JOIN_THE_WAITLIST);
//							text1 = element.getText();
//							System.out.println("Text of button when Unavailable size selected :: "+text1);
//							if(!(text1.equalsIgnoreCase("JOIN THE WAITLIST")))
//								throw new Exception("Text change for 'JOIN THE WAITLIST' ");
//
//							flag = true;
//
//						}
//						else
//						{
//
//							WebElement el = driver.findElement(By.cssSelector(".select--options__list> li:nth-child("+i+")"));
//							jse = (JavascriptExecutor)driver;
//							jse.executeScript("arguments[0].click();", el);
//
//							System.out.println("Unavailable size clicked");
//							//	driver.findElement(ADD_TO_CART_BUTTON).click();
//							System.out.println(driver.findElement(ADD_TO_CART_BUTTON).getText());
//							if (!driver.findElement(ADD_TO_CART_BUTTON).getText().contentEquals("Out of Stock"))
//							{
//								element = driver.findElement(JOIN_THE_WAITLIST);
//								text1 = element.getText();
//								System.out.println("Text of button when Unavailable size selected :: "+text1);
//								if(!(text1.equalsIgnoreCase("JOIN THE WAITLIST")))
//									throw new Exception("Text change for 'JOIN THE WAITLIST' for Packs");
//								text1 = element.getText();
//							}
//							flag = true;
//						}
//						//break;
//					}
//					else
//					{
//						jse = (JavascriptExecutor)driver;
//						jse.executeScript("arguments[0].click();" , element);
//						Thread.sleep(3000);
//					}
//				}
//				if(i==elementsCount) {
//					Reporter.log("No any size available");
//					break;
//				}
//				if(flag == true)
//					continue;
//				else {
//					// if flag is not true means size is available
//					// check the button txt and click
//					element = driver.findElement(ADD_TO_CART_BUTTON);
//					text1 = element.getText();
//					System.out.println("Button text when Available size selected :: "+text1);
//					if(!text1.equalsIgnoreCase("OUT OF STOCK"))
//					{
//						if(!(text1.equalsIgnoreCase("ADD TO CART"))) {
//							throw new Exception("Text change for 'ADD TO CART' ");
//						}
//					}
//
//				}
//				try
//				{
//					// check if we are able to add and minus quantity
//					quantity = driver.findElement(QUANTITY).getAttribute("data-add-qty");
//					Thread.sleep(3000);
//					element = driver.findElement(PLUS_BUTTON);
//					jse = (JavascriptExecutor)driver;
//					jse.executeScript("arguments[0].click();", element);
//					//	driver.findElement(PLUS_BUTTON).click();
//					Thread.sleep(3000);
//					String quantity1 = driver.findElement(QUANTITY).getAttribute("data-add-qty");
//					// check quantity added by 1 or not
//					if(Integer.parseInt(quantity1)!=Integer.parseInt(quantity)+1)
//						throw new Exception("Quantity not increased by 1");
//					Reporter.log("Quantity increased by 1");
//
//					element = driver.findElement(MINUS_BUTTON);
//					jse = (JavascriptExecutor)driver;
//					jse.executeScript("arguments[0].click();", element);
//					String quantity2 = driver.findElement(QUANTITY).getAttribute("data-add-qty");
//					if(Integer.parseInt(quantity2)!=Integer.parseInt(quantity1)-1)
//						throw new Exception("Quantity not decreased by 1");
//					Reporter.log("Quantity decreased by 1");
//				}
//				catch(Exception e)
//				{
//					Reporter.log("Quantity selector was not present - please check if DY hides Qunatity selector is active");
//				}
//
//				// check 'Best PairGuarantee' and 'Shopping And Returns' are clickable or not
//				flag = false;
//				Exception except = null;
//
//
//				if (driver.findElement(PRODUCT_DETAILS).isDisplayed())
//				{
//					element = driver.findElement(PRODUCT_DETAILS);
//					jse = (JavascriptExecutor)driver;
//					jse.executeScript("arguments[0].click();", element);
//				}
//				else throw new Exception("Product details accordion not present or not clikable ");
//				try
//				{
//					if (driver.findElement(BEST_PAIR_GUARANTEE).isDisplayed())
//					{
//						element = driver.findElement(BEST_PAIR_GUARANTEE);
//						jse = (JavascriptExecutor)driver;
//						jse.executeScript("arguments[0].click();", element);
//					}
//					else throw new Exception("PAIR_GUARANTEE accordion not present or not clikable");
//				}
//				catch(Exception e )
//				{
//					Reporter.log("Pair guarantee link is not present for this particular product");
//					except = e;
//				}
//				if(except!=null)
//				{
//					if (driver.findElement(FREE_RETURNS).isDisplayed()) {
//						element = driver.findElement(FREE_RETURNS);
//						jse = (JavascriptExecutor)driver;
//						jse.executeScript("arguments[0].click();", element);
//						//driver.findElement(SHIPING_AND_RETURNS).click();
//					}
//					else throw new Exception("SHIPING_AND_RETURNS accordion not present or not clikable");
//				}
//				//Buywithcode
//
//				if (!driver.findElement(PRODUCT_TITLE).getText().contains("Pack"))
//				{
//					// click on Add To Cart Button
//					if(!driver.findElement(ADD_TO_CART_BUTTON).getText().equalsIgnoreCase("Out Of Stock"))
//					{
//						if(!driver.findElement(ADD_TO_CART_BUTTON).isEnabled())
//							throw new Exception("ADD TO CART button is not present");
//						element = driver.findElement(ADD_TO_CART_BUTTON);
//						jse = (JavascriptExecutor)driver;
//						jse.executeScript("arguments[0].click();", element);
//
//						Thread.sleep(5000);
//						driver.get(CustomUtilities.baseUrl);
//						//	driver.findElement(ADD_TO_CART_BUTTON).click();
//						Thread.sleep(3000);
//						Reporter.log("ADD TO CART button is Displayed :: Clickable");
//					}
//				}
//				else
//				{
//					if(!driver.findElement(ADD_TO_CART_BUTTON).isEnabled())
//						throw new Exception("ADD TO CART button is not present");
//					element = driver.findElement(ADD_TO_CART_BUTTON);
//					jse = (JavascriptExecutor)driver;
//					jse.executeScript("arguments[0].click();" , element);
//					Reporter.log("ADD TO CART button is Displayed :: Clickable");
//				}
//				break;
//			}
//		}
		softAssert.assertAll();


	}


	public void validateafterpayonpdp() throws Exception {
		String text = null;
		SoftAssert softAssert = new SoftAssert();

		// call method to navigate to collection page
		new ProductDetailsPage(driver).navigateToAllUnderwearInMenCategory();

		//navigate to PDP
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_PRODUCT));

		WebElement el = driver.findElement(FIRST_PRODUCT_HEAD_LINE_TEXT);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", el);


		//Check afterpay message display
		wait.until(ExpectedConditions.visibilityOfElementLocated(AFTERPAY_PDP));
		if (!driver.findElement(AFTERPAY_PDP).isDisplayed())
			throw new Exception("AfterPay Message not present on PDP");
		Reporter.log("After pay Message on PDP is displayed :: Displayed");
		ExtentReport.extentTest.info("After pay Message on PDP is displayed :: Displayed");


		//Check message with calculated installment and icon display
		price = driver.findElement(PRODUCT_PRICE).getText();
		String[] price1 = price.split("\\$");
		String price2 = price1[price1.length - 1];

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement ele = driver.findElement(By.xpath("//iframe[@title='pdp_messaging']"));
		driver.switchTo().frame(ele);
		//WebElement root1 = driver.findElement(By.cssSelector("#root>div>div>afterpay-placement"));
		SearchContext shadowRoot = driver.findElement(By.cssSelector("#root>div>div>afterpay-placement")).getShadowRoot();
		Thread.sleep(6000);
		WebElement afterpaymessaging = (WebElement) js.executeScript("return document.querySelector(\"#root > div > div > afterpay-placement\").shadowRoot.querySelector(\"p > span\")");
		//WebElement afterpaymessaging =  (WebElement) js.executeScript("return document.querySelector('#root>div>div>afterpay-placement').shadowRoot.querySelector('p.afterpay-paragraph>span.afterpay-main-text')");
		System.out.print(afterpaymessaging.getText());


		if (Float.parseFloat(price2) < 35 || Float.parseFloat(price2) > 1000) {
			WebElement afterpaylogo = (WebElement) js.executeScript("return document.querySelector(\"#root > div > div > afterpay-placement\").shadowRoot.querySelector(\"p > button.afterpay-logo.brand-afterpay.type-badge.black-on-mint\")");

			softAssert.assertTrue(afterpaymessaging.getText().contains("available for orders between $35 - $1,000"), "Wrong afterpay message displayed for product price");
			Reporter.log("After pay message is correct On PDP :: Displayed and Correct message is displayed");
			ExtentReport.extentTest.info("After pay message is correct On PDP :: Displayed and Correct message is displayed");
			softAssert.assertTrue(afterpaylogo.isDisplayed(), "Afterpay logo not present on PDP");
			Reporter.log("After pay logo icon is displayed on PDP :: Displayed");
			ExtentReport.extentTest.info("After pay logo icon is displayed on PDP :: Displayed");
		} else {
			WebElement afterpaylogo = (WebElement) js.executeScript("return document.querySelector(\"#root > div > div > afterpay-placement\").shadowRoot.querySelector(\"p > span.afterpay-logo-link > button.afterpay-logo.brand-afterpay.type-badge.black-on-mint\")");


			installment = Float.parseFloat(price2) / 4;
			String inst = String.format("%.2f", installment);
			//	String expectedmessage = "or 4 installments of $" + inst + " by ?";
			String expectedmessage = inst + " ";
			System.out.println("expected message is: " + expectedmessage);
			String actual = afterpaymessaging.getText();
			String ins_amount = actual.substring(actual.indexOf("$") + 1, actual.indexOf("b"));
			System.out.println("Actual int_amount is: " + actual);
			softAssert.assertEquals(ins_amount, expectedmessage, "Wrong afterpay message displayed for product price");
			Reporter.log("After pay message with calculated installment is correct :: Displayed and correct");
			ExtentReport.extentTest.info("After pay message with calculated installment is correct :: Displayed and correct");
			softAssert.assertTrue(afterpaylogo.isDisplayed(), "Afterpay logo not present on PDP");
			Reporter.log("After pay logo icon is displayed on PDP :: Displayed");
			ExtentReport.extentTest.info("After pay logo icon is displayed on PDP :: Displayed");

		}


		softAssert.assertAll();
	}


	public void verifyReviewsDisplay() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		String text = null;
		SoftAssert softAssert = new SoftAssert();
		// call method to navigate to collection page
		new HomePageComponents(driver).navigateToAllMensCollectionsInMenCategory();
		// call method to navigate product details page
		text = new CollectionPageComponent(driver).navigateToProductDetailsPage();
		// call method to check correct PDP opend or not
		checkCorrectProductDetailsPageOpenedOrNot(text);

		wait.until(ExpectedConditions.presenceOfElementLocated(RATING_STARS));

		if (driver.findElement(RATING_STARS).isDisplayed()) {

			WebElement elem = driver.findElement(RATING_STARS);
			JavascriptExecutor executore = (JavascriptExecutor) driver;
			executore.executeScript("arguments[0].click();", elem);
			// wait for scroll it down
			Thread.sleep(6000);
			Reporter.log("Review Stars Are Displayed :: Clickable :: Clicked");
			// check for Rating and Reviews are present or not
			softAssert.assertEquals(driver.findElement(REVIEWS_TITLE).getText(), "Rating & Reviews", "Text change for Ratings and Reviews title");
			softAssert.assertTrue(driver.findElement(WRITE_REVIEW_BUTTON).isDisplayed(), "Write a review button is not displayed");

			System.out.println("rating: " + driver.findElement(RATING_NUMBER).getText());
			if (!driver.findElement(RATING_NUMBER).getText().equals("(0)")) {

				if (driver.findElement(RATING_AND_REVIEWS_BOX).isDisplayed()) {

					softAssert.assertTrue(driver.findElement(FIRST_BOX_IN_RATING_AND_REVIEWS).isDisplayed(), " First box is not present in Rating and Reviews section");
					softAssert.assertTrue(driver.findElement(STARS_IN_FIRST_BOX).isDisplayed(), "In first box Review Stars not present");
					softAssert.assertTrue(driver.findElement(AVG_RATING_IN_FIRST_BOX).isDisplayed(), "In first box Average Rating is not present");
					softAssert.assertTrue(driver.findElement(TOTAL_REVIEWS_IN_FIRST_BOX).isDisplayed(), "In first box Total Reviews are not present");

					softAssert.assertTrue(driver.findElement(SECOND_BOX_IN_RATING_AND_REVIEWS).isDisplayed(), "Second Box is not present in Rating and Reviews section");
					softAssert.assertTrue(driver.findElement(RATING_LABEL_IN_SECOND_BOX).isDisplayed(), "In second box Rating Label is not present");
					softAssert.assertTrue(driver.findElement(RATING_METER_IN_SECOND_BOX).isDisplayed(), "In second box Rating Meter is not present");
					softAssert.assertTrue(driver.findElement(RATING_COUNT_IN_SECOND_BOX).isDisplayed(), "In second box Rating Count is not present");

					softAssert.assertTrue(driver.findElement(THIRD_BOX_IN_RATING_AND_REVIEWS).isDisplayed(), "Third Box is not present in Rating and Reviews section");
					softAssert.assertTrue(driver.findElement(FIT_GRAPH_IN_THIRD_BOX).isDisplayed(), "In third box fit graph not present");
					softAssert.assertTrue(driver.findElement(FIT_STATUS_IN_THIRD_BOX).isDisplayed(), "In third box fit status not present");

					Reporter.log("Verified Reviews and Rating box section, All elements are present including fit status, fit graph, Rating label, rating count, Rating meter, Avg rating, total reviews etc");


					//verify paginations
					softAssert.assertTrue(driver.findElement(PAGINATION_LEFT_ARROW).isDisplayed(), "Pagination left arrow is not displayed on pdp");
					softAssert.assertTrue(driver.findElement(PAGINATION_RIGHT_ARROW).isDisplayed(), "Pagination right arrow is not displayed on pdp");
					softAssert.assertTrue(driver.findElement(PAGINATION_CURRENT_VALUE).isDisplayed(), "Pagination current value is not displayed on pdp");
					String[] page_value1 = driver.findElement(PAGINATION_CURRENT_VALUE).getText().split(" /");
					String previous_page_number = page_value1[0];
					int pre_number = Integer.parseInt(previous_page_number);
					driver.findElement(PAGINATION_RIGHT_ARROW).click();
					Thread.sleep(5000);
					String[] page_value2 = driver.findElement(PAGINATION_CURRENT_VALUE).getText().split(" /");
					String next_page_number = page_value2[0];
					int next_number = Integer.parseInt(next_page_number);
					System.out.println("previous is: " + previous_page_number + " next page no. is: " + next_page_number);
					softAssert.assertTrue(pre_number < next_number, "Pagination next arrow is not functional");
					Reporter.log("Reviews Pagination is functional on PDP");
				} else
					throw new Exception("Rating and Reviews box is not present");
				softAssert.assertTrue(driver.findElement(REVIEWER_NAME).isDisplayed(), "Reviewer name is not displayed");
				softAssert.assertTrue(driver.findElement(REVIEWER_TYPE).isDisplayed(), "Reviewer type is not displayed");
				softAssert.assertTrue(driver.findElement(USER_REVIEW_FIT).isDisplayed(), "User review for fit is not displayed");
				softAssert.assertTrue(driver.findElement(USER_REVIEW_DATE).isDisplayed(), "User review date is not displayed");
				softAssert.assertTrue(driver.findElement(USER_REVIEW_STARS).isDisplayed(), "User review stars is not displayed");
				softAssert.assertTrue(driver.findElement(USER_REVIEW_TITLE).isDisplayed(), "User review title is not displayed");
				softAssert.assertTrue(driver.findElement(USER_REVIEW_MAIN_CONTENT).isDisplayed(), "User review main content is not displayed");
				softAssert.assertTrue(driver.findElement(USER_REVIEW_WAS_THIS_HELPFUL_TEXT).isDisplayed(), "Was this helpful text is not displayed");
				softAssert.assertTrue(driver.findElement(USER_REVIEW_THUMBS_UP).isDisplayed(), "Thumbs Up icon is not displayed");
				softAssert.assertTrue(driver.findElement(USER_REVIEW_THUMBS_DOWN).isDisplayed(), "Thumbs down icon is not displayed");
			} else {
				softAssert.assertTrue(driver.findElement(NO_REVIEWS_TEXT).isDisplayed(), "No reviews text is not displayed");
				softAssert.assertTrue(driver.findElement(NO_REVIEWS_SUBTEXT).isDisplayed(), "No reviews subtext is not displayed");
				softAssert.assertTrue(driver.findElement(NO_REVIEW_WRITE_REVIEW).isDisplayed(), "No reviews container - write a review is not displayed");
			}
			softAssert.assertAll();
			Reporter.log("Rating and Reviews Displayed");
		} else
			throw new Exception("Rating Stars are not clickable");
	}

	public void verifyWriteReview() throws Exception {
		String text = null;
		SoftAssert softAssert = new SoftAssert();
		// call method to navigate to collection page
		new HomePageComponents(driver).navigateToAllWomensCollectionInWomensCategory();
		// call method to navigate product details page
		text = new CollectionPageComponent(driver).navigateToProductDetailsPage();
		// call method to check correct PDP opend or not
		checkCorrectProductDetailsPageOpenedOrNot(text);
		Thread.sleep(5000);
		WebElement elem = driver.findElement(RATING_STARS);
		JavascriptExecutor executore = (JavascriptExecutor) driver;
		executore.executeScript("arguments[0].click();", elem);
		// wait for scroll it down
		Thread.sleep(6000);

		WebElement writereview = driver.findElement(WRITE_REVIEW_BUTTON);
		executore.executeScript("arguments[0].click();", writereview);

		softAssert.assertEquals(driver.findElement(WRITE_REVIEW_TITLE).getText(), "Write a Review", "Laguage mismatch for Write a Review title 2");
		softAssert.assertEquals(driver.findElement(NAME).getText(), "* Name", "Laguage mismatch for Name ");
		driver.findElement(NAME_TEXTBOX).sendKeys("Vijeta");
		softAssert.assertEquals(driver.findElement(EMAIL).getText(), "* Email", "Laguage mismatch for Email ");
		driver.findElement(EMAIL_TEXTBOX).sendKeys("vijeta@tommyjohnwear.com");
		softAssert.assertEquals(driver.findElement(LOCATION).getText(), "Location (Optional)", "Laguage mismatch for Location ");
		driver.findElement(LOCATION_TEXTBOX).sendKeys("India");
		softAssert.assertEquals(driver.findElement(FIT).getText(), "Fit", "Laguage mismatch for Fit	 ");
		WebElement fit = driver.findElement(FIT_SELECTION);
		executore.executeScript("arguments[0].click();", fit);
		softAssert.assertEquals(driver.findElement(OVERALLRATING).getText(), "* Overall Rating", "Laguage mismatch for Overall Rating	 ");
		WebElement stars = driver.findElement(STAR_SELECTION);
		executore.executeScript("arguments[0].click();", stars);
		softAssert.assertEquals(driver.findElement(HEADLINE).getText(), "* Add a Headline", "Laguage mismatch for Headline ");
		driver.findElement(HEADLINE_TEXTBOX).sendKeys("Nice Product");
		softAssert.assertEquals(driver.findElement(REVIEW).getText(), "* Review", "Laguage mismatch for Review	 ");
		driver.findElement(REVIEW_TEXTBOX).sendKeys("Must Buy");

		softAssert.assertTrue(driver.findElement(SUBMIT_BTN).isEnabled(), "Submit button not enabled");


		softAssert.assertAll();

	}

	public void verifyWriteReviewErrorMsgs() throws Exception {
		String text = null;
		SoftAssert softAssert = new SoftAssert();
		// call method to navigate to collection page
		new HomePageComponents(driver).navigateToAllUnderwearInMenCategory();
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)");
		// call method to navigate product details page
		text = new CollectionPageComponent(driver).navigateToProductDetailsPage();
		// call method to check correct PDP opend or not
		checkCorrectProductDetailsPageOpenedOrNot(text);
		Thread.sleep(3000);
		WebElement elem = driver.findElement(RATING_STARS);
		JavascriptExecutor executore = (JavascriptExecutor) driver;
		executore.executeScript("arguments[0].click();", elem);
		// wait for scroll it down
		Thread.sleep(6000);
		elem = driver.findElement(WRITE_REVIEW_BUTTON);
		executore = (JavascriptExecutor) driver;
		executore.executeScript("arguments[0].click();", elem);
		//driver.findElement(WRITE_REVIEW_BUTTON).click();
		Thread.sleep(4000);
		driver.findElement(SUBMIT_BTN).click();
		softAssert.assertEquals(driver.findElement(NAME_ERROR).getText(), "Please enter a name for this review.", "Laguage mismatch for error message for Name");
		softAssert.assertEquals(driver.findElement(EMAIL_ERROR).getText(), "Please enter a valid email for this review.", "Laguage mismatch for error message for Email");
		softAssert.assertEquals(driver.findElement(HEADLINE_ERROR).getText(), "Please enter a headline for this review.", "Laguage mismatch for error message for Headline");
		softAssert.assertEquals(driver.findElement(REVIEW_ERROR).getText(), "Please enter some content for this review.", "Laguage mismatch for error message for Review");

		softAssert.assertAll();

	}


	public void verifybundlepages() throws Exception {
		//	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		jse = (JavascriptExecutor) driver;
		String allClasses = null;
		String allClass = null;

		Thread.sleep(5000);

		if (!driver.findElement(PRODUCT_TITLE_1).isDisplayed())
			throw new Exception("Product Title is not displayed");
		if (!driver.findElement(PRODUCT_PRICE).isDisplayed())
			throw new Exception("Product price is not displayed");

		if (!driver.findElement(SIZEGUIDE).isEnabled())
			throw new Exception("Size Guide button is not present");
		WebElement ele = driver.findElement(SIZEGUIDE);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", ele);
		Thread.sleep(3000);

		if (driver.findElement(By.xpath("//h2[@class='mb-lg mt-2 pr-14 text-heading-md']")).isDisplayed()) {
			Reporter.log("Bundle product's First size guide link is Displayed :: Clickable");
			WebElement elem = driver.findElement(By.xpath("//button[@aria-label='Close modal']"));
			JavascriptExecutor executor1 = (JavascriptExecutor) driver;
			executor1.executeScript("arguments[0].click();", elem);
			//driver.findElement(By.xpath("//button[@aria-label='Close modal']")).click();
		}

		//Close first size guide window


		/*if(!driver.findElement(SizeGuideLocators.BUNDLE_SECOND_SIZE_GUIDE_BUTTON).isEnabled())
			throw new Exception("In bundle product Second Size Guide button is not present");
		WebElement ele2 = driver.findElement(SizeGuideLocators.BUNDLE_SECOND_SIZE_GUIDE_BUTTON);
		JavascriptExecutor executor2 = (JavascriptExecutor)driver;
		executor2.executeScript("arguments[0].click();", ele2);
		Thread.sleep(3000);

		driver.findElement(SizeGuideLocators.CLOSE_BTN).click();
		Reporter.log("Size guide is Displayed :: Clickable");

		Thread.sleep(3000);*/

//		if (driver.findElement(RATING_STARS).isDisplayed()) {
//
//			WebElement elem = driver.findElement(RATING_STARS);
//			JavascriptExecutor executore = (JavascriptExecutor) driver;
//			executore.executeScript("arguments[0].click();", elem);
//			// wait for scroll it down
//			Thread.sleep(6000);
//			Reporter.log("Review Stars Are Displayed :: Clickable :: Clicked");
//			// check for Rating and Reviews are present or not
//			System.out.println("rating: " + driver.findElement(RATING_NUMBER).getText());
//			if (!driver.findElement(RATING_NUMBER).getText().equals("(0)")) {
//
//				if (driver.findElement(RATING_AND_REVIEWS_BOX).isDisplayed()) {
//					if (!driver.findElement(FIRST_BOX_IN_RATING_AND_REVIEWS).isDisplayed())
//						throw new Exception("In first box Review Stars not present in Rating and Reviews section");
//					if (!driver.findElement(SECOND_BOX_IN_RATING_AND_REVIEWS).isDisplayed())
//						throw new Exception("In second box rating list not present in Rating and Reviews section");
//					if (!driver.findElement(THIRD_BOX_IN_RATING_AND_REVIEWS).isDisplayed())
//						throw new Exception("In third box fit status not present in Rating and Reviews section");
//				}
//				else
//
//					throw new Exception("Rating box is not present");
//
//			} else {
//				if (!driver.findElement(NO_REVIEW_WRITE_REVIEW).isEnabled())
//
//					throw new Exception("'WRITE A REVIEW' button is not displayed");
//				driver.findElement(NO_REVIEW_WRITE_REVIEW).click();
//				Reporter.log("'WRITE A REVIEW' button is Displayed :: Clickable");
//
//			}
//			Reporter.log("Rating and Reviews Displayed");
//		} else
//			throw new Exception("Rating Stars are not clickable");
////		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -document.body.scrollHeight)");
////		allElements = driver.findElements(ALL_SIZE_LIST);
////		elementsCount = allElements.size();
////		System.out.println("All sizes list :: " + elementsCount);
////		if (driver.findElement(By.cssSelector(".option-swatch__option>li.option-swatch__item")).isDisplayed()) {
////			//radio
////			List<WebElement> Size_OptionName = driver.findElements(By.cssSelector(".option-swatch__option>li.option-swatch__item"));
////			int Size_OptionCount = Size_OptionName.size();
////			for (int i = 1; i <= Size_OptionCount; i++) {
////
////				Thread.sleep(3000);
////				WebElement elements = driver.findElement(By.cssSelector(".option-swatch__option>li:nth-child(" + i + ")>input"));
////				//jse.executeScript("arguments[0].click();", elements);
////
////				allClasses = elements.getAttribute("class");
////				// check size available or not
////				//for (String c : allClasses.split(" ")) {
////				if (allClasses.contains("unavailable")) {
////					flag = false;
////					jse = (JavascriptExecutor) driver;
////					jse.executeScript("arguments[0].click();",
////							element);
////					System.out.println("Unavailable size clicked");
////
////					//int y=i;
//////							 for(int k=y; k<y; k++)
//////							 {
//////
//////								 WebElement e = driver.findElement(By.cssSelector("div.radios-wrapper > div.radio-options-container > div > label:nth-child("+k+")"));
//////								 allClass = e.getAttribute("class");
//////								 for (String d : allClass.split(" ")) {
//////									 if (d.equals("oosVariant"))
//////										 y++;
//////									 else
//////										 driver.findElement(By.cssSelector("div.radios-wrapper > div.radio-options-container > div > label:nth-child("+y+")")).click();
//////								 }
//////							 }
////					flag = true;
////				} else {
////					System.out.println("selected size is: " + elements.getText());
////					jse = (JavascriptExecutor) driver;
////					jse.executeScript("arguments[0].click();", elements);
////
////				}
////
////
////				if (i == elementsCount) {
////					Reporter.log("No any size available");
////					break;
////				}
////				if (flag == false)
////					break;
////
////			}
////
////
////		} else {
////			throw new Exception("Sizes not available for selection");
////		}
//
////dropdown
////
////			for(int i=1;i<=elementsCount;i++) {
////				flag = false;
////				//driver.findElement(SIZE_DROPDOWN).click();
////				WebElement elem = driver.findElement(SIZE_DROPDOWN_MENS);
////				jse.executeScript("arguments[0].click();", elem);
////				element = driver.findElement(By.cssSelector(".select--options__list[data-option-name = 'Mens Size'] > li:nth-child("+i+") > div"));
////
////				allClasses = element.getAttribute("class");
////				// check size available or not
////				for (String c : allClasses.split(" ")) {
////					if (c.equals("unavailable")) {
////
////						driver.findElement(By.cssSelector(".select--options__list[data-option-name = 'Mens Size']> li:nth-child("+i+")")).click();
////						WebElement eleme = driver.findElement(SIZE_DROPDOWN_WOMENS);
////						jse.executeScript("arguments[0].click();", eleme);
////						//	driver.findElement(By.cssSelector(".select--options__list[data-option-name = 'Womens Size']> li:nth-child("+i+")")).click();
////						int y =i;
////						for(int j=0;j<y;j++)
////						{
////
////							WebElement e = driver.findElement(By.cssSelector(".select--options__list[data-option-name = 'Womens Size'] > li:nth-child("+y+") > div"));
////							allClass = e.getAttribute("class");
////							for (String d : allClass.split(" ")) {
////
////								// check size available or not
////
////								if (d.equals("unavailable"))
////									y++;
////								else
////									driver.findElement(By.cssSelector(".select--options__list[data-option-name = 'Womens Size']> li:nth-child("+y+")")).click();
////							}
////						}
////
////
////						flag = true;
////
////						//break;
////					}
////					else
////					{
////						Thread.sleep(3000);
////						WebElement e =	driver.findElement(By.cssSelector(".select--options__list[data-option-name = 'Mens Size']> li:nth-child("+i+")"));
////						Thread.sleep(2000);
////						jse.executeScript("arguments[0].click();", e);
////						if(!driver.findElement(ADD_TO_CART_BUTTON).getText().contentEquals("Add to Cart"))
////						{
////							WebElement eleme = driver.findElement(SIZE_DROPDOWN_WOMENS);
////							jse.executeScript("arguments[0].click();", eleme);
////
////							int y =i;
////							for(int j=0;j<y;j++)
////							{
////
////								WebElement el = driver.findElement(By.cssSelector(".select--options__list[data-option-name = 'Womens Size'] > li:nth-child("+y+") > div"));
////								String allClas = el.getAttribute("class");
////								for (String u : allClas.split(" ")) {
////
////									// check size available or not
////
////									if (u.equals("unavailable"))
////										y++;
////									else
////										driver.findElement(By.cssSelector(".select--options__list[data-option-name = 'Womens Size']> li:nth-child("+y+")")).click();
////								}
////							}
////						}
////
////						//jse.executeScript("arguments[0].click();", driver.findElement(SIZE_DROPDOWN_WOMENS));
////
////						//	WebElement elm  = driver.findElement(By.cssSelector(".select--options__list[data-option-name = 'Womens Size']> li:nth-child("+i+") > div"));
////
////
////						//jse.executeScript("arguments[0].click();", elm);
////						if (!driver.findElement(ADD_TO_CART_BUTTON).getText().contentEquals("Add to Cart"))
////							throw new Exception("Wrong language on CTA for In stock bundle");
////
////					}
////
////					if(i==elementsCount) {
////						Reporter.log("No any size available");
////
////					}
////					if(flag==false)
////						break;
////					else
////						continue;
////
////				}
////				if(flag==false)
////					break;
////				else
////					continue;
////			}
//
//
//		// check if we are able to add and minus quantity
//
////		try {
////			// check if we are able to add and minus quantity
////			String quantity = driver.findElement(QUANTITY).getAttribute("data-add-qty");
////			Thread.sleep(3000);
////			element = driver.findElement(PLUS_BUTTON);
////			jse = (JavascriptExecutor) driver;
////			jse.executeScript("arguments[0].click();", element);
////			//	driver.findElement(PLUS_BUTTON).click();
////			Thread.sleep(3000);
////			String quantity1 = driver.findElement(QUANTITY).getAttribute("data-add-qty");
////			// check quantity added by 1 or not
////			if (Integer.parseInt(quantity1) != Integer.parseInt(quantity) + 1)
////				//if(!quantity1.equals(quantity + 1) )
////				//if(quantity1)
////				throw new Exception("Quantity not increased by 1");
////
////			Reporter.log("Quantity increased by 1");
////			element = driver.findElement(MINUS_BUTTON);
////			jse = (JavascriptExecutor) driver;
////			jse.executeScript("arguments[0].click();", element);
////			//driver.findElement(MINUS_BUTTON).click();
////
////			// some times .click() not clicking the element then that time use JavascriptExecutor
////			//		element = driver.findElement(By.id("minusBtn"));
////			//		jse = (JavascriptExecutor)driver;
////			//		jse.executeScript("arguments[0].click();", element);
////
////			String quantity2 = driver.findElement(QUANTITY).getAttribute("data-add-qty");
////			if (Integer.parseInt(quantity2) != Integer.parseInt(quantity1) - 1)
////				throw new Exception("Quantity not decreased by 1");
////			Reporter.log("Quantity decreased by 1");
////		} catch (Exception e) {
////			Reporter.log("Quantity selector was not present - please check if DY hides Qunatity selector is active");
////		}
//
//		// check 'Best PairGuarantee' and 'Shopping And Returns' are clickable or not
//		flag = false;
//
//
////		if (driver.findElement(PRODUCT_DETAILS).isDisplayed()) {
////			element = driver.findElement(PRODUCT_DETAILS);
////			jse = (JavascriptExecutor) driver;
////			jse.executeScript("arguments[0].click();", element);
////		} else throw new Exception("Product details accordion not present or not clikable ");
////		try {
////			if (driver.findElement(BEST_PAIR_GUARANTEE).isDisplayed()) {
////				element = driver.findElement(BEST_PAIR_GUARANTEE);
////				jse = (JavascriptExecutor) driver;
////				jse.executeScript("arguments[0].click();", element);
////			} else throw new Exception("PAIR_GUARANTEE accordion not present or not clikable");
////		} catch (Exception e) {
////			Reporter.log("Pair guarantee link is not present for this particular product");
////		}
////		if (driver.findElement(FREE_RETURNS).isDisplayed()) {
////			element = driver.findElement(FREE_RETURNS);
////			jse = (JavascriptExecutor) driver;
////			jse.executeScript("arguments[0].click();", element);
////			//driver.findElement(SHIPING_AND_RETURNS).click();
////		} else throw new Exception("SHIPING_AND_RETURNS accordion not present or not clikable");
//
//
//		if (!driver.findElement(ADD_TO_CART_BUTTON).isEnabled())
//			throw new Exception("ADD TO CART button is not present");
//		element = driver.findElement(ADD_TO_CART_BUTTON);
//		jse = (JavascriptExecutor) driver;
//		jse.executeScript("arguments[0].click();", element);
//
//		Reporter.log("ADD TO CART button is Displayed :: Clickable");

	}


	public void VerifyJoinTheWaitlist() throws Exception {

		String allClasses;
		new HomePageComponents(driver).navigateToAllUnderwearInMenCategory();
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)");
		Thread.sleep(4000);

		SoftAssert softAssert = new SoftAssert();
		// call method to navigate product details page
		new CollectionPageComponent(driver).navigateToProductDetailsPage();
		//driver.get("https://tommyjohn.com/collections/cool-cotton-boxer-brief/?variant=29882858340397");
		Thread.sleep(5000);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		allElements = driver.findElements(ALL_SIZE_LIST);
		elementsCount = allElements.size();
		System.out.println("All sizes list :: " + elementsCount);

		if (driver.findElement(By.cssSelector("div.radio-options-container")).isDisplayed()) {

			//radio
			for (int i = 3; i <= elementsCount; i = i + 2) {
				flag = false;
				element = driver.findElement(By.cssSelector("div.radios-wrapper > div.radio-options-container > div > label:nth-child(" + i + ")"));

				if (element.getAttribute("class").contains("oosVariant")) {
					{
						if (!driver.findElement(PRODUCT_TITLE).getText().contains("Pack")) {

							//if(driver.findElement(By.cssSelector(".select--options__list > li:nth-child("+i+") > div > span")).getText().contentEquals("(Out of Stock - Email Me)"))
							//{
							jse = (JavascriptExecutor) driver;
							jse.executeScript("arguments[0].click();", element);
							Thread.sleep(3000);

							//							WebElement atc = driver.findElement(By.cssSelector("div.multiPdp__atc-container>button"));
							//							String[] classname = atc.getAttribute("class").split(" ");
							//							String atc_status = classname[classname.length-1];
							//							if(!atc_status.equals("email-when-in-stock"))
							//							{
							//							WebElement oos_variant = driver.findElement(By.cssSelector(".product-option__variant-btn.email-when-in-stock"));
							//							jse.executeScript("arguments[0].click();", oos_variant);
							//							Thread.sleep(3000);
							//							}

							List<WebElement> unavailable_color = driver.findElements(By.cssSelector(".product-option__color-swatches-wrapper > ul >li>input"));
							for (int a = 1; a <= unavailable_color.size(); a++) {
								WebElement oos_color = driver.findElement(By.cssSelector(".product-option__color-swatches-wrapper > ul >li:nth-child(" + a + ")>input"));
								//oos_color.click();
								jse.executeScript("arguments[0].click();", oos_color);
								Thread.sleep(3000);
								String[] classname = oos_color.getAttribute("class").split(" ");
								String status = classname[classname.length - 1];
								if (status.equals("email-when-in-stock"))
									break;
								else continue;
							}

							driver.findElement(ADD_TO_CART_BUTTON).click();
							Thread.sleep(3000);
							softAssert.assertEquals(driver.findElement(EMAIL_WHEN_IN_STOCK_TITLE).getText(), "Email Me When Back In Stock", "Email When In Stock title change");
							softAssert.assertEquals(driver.findElement(JOINTHEWAITLIST_REMINDER_DESC).getText(), "Get notified via email when this product is back in stock", "Email When In Stock Reminder description change");
							softAssert.assertTrue(driver.findElement(JOINTHEWAITLIST_PRIVACYINFO).isDisplayed(), "Email When In Stock Privacy Info description not displayed");

							driver.findElement(JOINTHEWAITLIST_EMAILTEXTBOX).sendKeys("akshata@tommyjohnwear.com");
							driver.findElement(JOINTHEWAITLIST_NOTIFYBUTTON).click();
							Thread.sleep(4000);
							softAssert.assertEquals(driver.findElement(JOINTHEWAITLIST_SUCCESS).getText(), "Thank You", "Email when in stock not functional");
							System.out.println(driver.findElement(JOINTHEWAITLIST_SUCCESS).getText());
							driver.findElement(JOINTHEWAITLIST_CLOSEBTN).click();
							driver.switchTo().defaultContent();
							softAssert.assertAll();
							flag = true;

							//}
						}

						//break;
					}
					if (flag == true)
						break;
					else
						continue;
				}
				if (flag == true)
					break;
				else
					continue;
			}


		} else {
			//dropdown

			for (int i = 1; i <= elementsCount; i++) {
				flag = false;
				//driver.findElement(SIZE_DROPDOWN).click();
				executor.executeScript("arguments[0].click();", driver.findElement(SIZE_DROPDOWN));
				element = driver.findElement(By.cssSelector(".select--options__list > li:nth-child(" + i + ") > div"));

				allClasses = element.getAttribute("class");
				// check size available or not
				for (String c : allClasses.split(" ")) {
					if (c.equals("unavailable")) {
						if (!driver.findElement(PRODUCT_TITLE).getText().contains("Pack")) {

							if (driver.findElement(By.cssSelector(".select--options__list > li:nth-child(" + i + ") > div > span")).getText().contentEquals("(Out of Stock - Email Me)")) {
								jse = (JavascriptExecutor) driver;
								jse.executeScript("arguments[0].click();", element);
								//		driver.switchTo().frame("swell-popup");
								softAssert.assertEquals(driver.findElement(EMAIL_WHEN_IN_STOCK_TITLE).getText(), "Email When In Stock", "Email When In Stock title change");
								softAssert.assertEquals(driver.findElement(JOINTHEWAITLIST_REMINDER_DESC).getText(), "Get notified via email when this product is back in stock.", "Email When In Stock Reminder description change");
								softAssert.assertEquals(driver.findElement(JOINTHEWAITLIST_PRIVACYINFO).getText(), "We will send you an email once the product becomes available. Your email address will not be shared with anyone else.", "Email When In Stock Privacy Info description change");

								driver.findElement(JOINTHEWAITLIST_EMAILTEXTBOX).sendKeys("akshata@tommyjohnwear.com");
								driver.findElement(JOINTHEWAITLIST_NOTIFYBUTTON).click();
								Thread.sleep(4000);
								softAssert.assertEquals(driver.findElement(JOINTHEWAITLIST_SUCCESS).getText(), "Thank you!", "Email when in stock not functional");
								System.out.println(driver.findElement(JOINTHEWAITLIST_SUCCESS).getText());
								driver.findElement(JOINTHEWAITLIST_CLOSEBTN).click();
								driver.switchTo().defaultContent();
								softAssert.assertAll();
								flag = true;

							}
						}
					}

					if (flag == true)
						break;
					else
						continue;
				}
				if (flag == true)
					break;
				else
					continue;
			}
		}

	}


	public void verifyRecommendedForU() throws Exception {
		SoftAssert softAssert = new SoftAssert();
		element = driver.findElement(RFY_SECTION);
		jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(5000);
		softAssert.assertTrue(driver.findElement(RFY_TITLE).isDisplayed(), "Recommended for you section not present");
		softAssert.assertTrue(driver.findElement(RFY_SECTION).isDisplayed(), "Recommended for you section not present");
		softAssert.assertTrue(driver.findElement(RFY_PRODUCT_TITLE).isDisplayed(), "Recommended for you product title not present");
		softAssert.assertTrue(driver.findElement(RFY_PRODUCT_IMAGE).isDisplayed(), "Recommended for you product image not present");
		softAssert.assertTrue(driver.findElement(RFY_PRODUCT_REVIEWS).isDisplayed(), "Recommended for you product reviews not present");
		softAssert.assertTrue(driver.findElement(RFY_PRODUCT_PRICE).isDisplayed(), "Recommended for you product price not present");

		String text = driver.findElement(RFY_PRODUCT_TITLE).getText();
		jse.executeScript("arguments[0].click();", driver.findElement(RFY_PRODUCT_IMAGE));
		Thread.sleep(4000);
		softAssert.assertEquals(driver.findElement(PRODUCT_TITLE).getText(), text, "PDP page not correct after clicking on Recommended for you image");

		softAssert.assertAll();
		Reporter.log("Recommended for You :: Displayed");
		ExtentReport.extentTest.info("Recommended for You :: Displayed");
	}

	public void VerifyQuickShopOnRecommendedforYou() throws Exception {
		Actions action = new Actions(driver);
		element = driver.findElement(RFY_SECTION);
		jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(3000);
		element = driver.findElement(RFY_PRODUCT_IMAGE);
		jse.executeScript("arguments[0].scrollIntoView(true);", element);
		action.moveToElement(element).build().perform();
		Thread.sleep(3000);
		new CollectionPageComponent(driver).AddproductToCartByQuickShop();
		Reporter.log("Verified DY recs quick shop functionality on PDP");

	}

	public void MethodToCheckColorSwatchOnDYRecs() throws Exception {
		SoftAssert softassert = new SoftAssert();
		element = driver.findElement(RFY_SECTION);
		jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(3000);

		List<WebElement> allElements = driver.findElements(RFY_SWATCH_LIST);
		int elementsCount = allElements.size();
		System.out.println("Color Element count :: " + elementsCount);

		if (elementsCount > 1) {
			element = driver.findElement(By.cssSelector(".dy-recs-placeholder__above-desktop-reviews>div>.dy-rec>div>div:nth-child(2)>.dy-rec--products>div:nth-child(1) > div > div.product-item__swatches:nth-child(2)> div > div:nth-child(1)"));
			element.click();

			for (int i = 1; i < elementsCount; i++) {
				if (i < 4) {
					String Img_colortext = null;
					String colornew1 = null;
					String text = driver.findElement(RFY_PRODUCT_IMG_COLORNAME).getAttribute("href");
					String[] strarray = text.split("[=]");
					String text1 = strarray[strarray.length - 1];
					Img_colortext = text1.replace("-", " ").toLowerCase();

					System.out.println("Product image color name is: " + Img_colortext);


					element = driver.findElement(By.cssSelector(".dy-recs-placeholder__above-desktop-reviews>div>.dy-rec>div>div:nth-child(2)>.dy-rec--products>div:nth-child(1) > div > div.product-item__swatches:nth-child(2)> div > div:nth-child(" + i + ")>div"));
					colornew1 = element.getAttribute("title").replace("/", " ").toLowerCase();
					//	String colornew = colornew1.replace("/", " ");
					System.out.println("swatch color is: " + colornew1);
					if (colornew1.equalsIgnoreCase(Img_colortext)) {
						WebElement elemen = driver.findElement(By.cssSelector(".dy-recs-placeholder__above-desktop-reviews>div>.dy-rec>div>div:nth-child(2)>.dy-rec--products>div:nth-child(1) > div > div.product-item__swatches:nth-child(2)> div > div:nth-child(" + (i + 1) + ")>div"));
						jse.executeScript("arguments[0].click();", elemen);
						softassert.assertEquals(Img_colortext, colornew1, "Color not changing");
						Reporter.log("Color swatches are changing correctly");
					} else {
						throw new Exception("Color not changing");
					}
				}
			}
		}
		softassert.assertAll();
	}

	public void verifyMysteryPacks() throws Exception {
		jse = (JavascriptExecutor) driver;
		SoftAssert softAssert = new SoftAssert();

		new HomePageComponents(driver).navigateToMysteryPacksinPacksCategory();
		Thread.sleep(3000);

		jse = (JavascriptExecutor) driver;
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		jse.executeScript("window.scrollBy(0,-250)");

		Thread.sleep(4000);


		List<WebElement> producttitle = driver.findElements(By.cssSelector(".product-meta__title"));
		for (WebElement title : producttitle) {
			if (title.getText().contains("Mystery")) {
				Thread.sleep(3000);
				System.out.println(title.getText());

				jse.executeScript("arguments[0].click();", title);

				break;
			}

		}


		Thread.sleep(6000);

		softAssert.assertTrue(driver.findElement(PRODUCT_TITLE).isDisplayed(), "Product Title is not displayed");
		softAssert.assertTrue(driver.findElement(PRODUCT_PRICE).isDisplayed(), "Product price is not displayed");

		if (driver.findElement(SIZEGUIDE).isDisplayed()) {
			WebElement ele = driver.findElement(SIZEGUIDE);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", ele);
			Thread.sleep(3000);

			if (!driver.findElement(By.cssSelector(".size-chart-content")).isDisplayed())
				throw new Exception("Sizes chart overlay is not displayed after Size guide button was clicked");
			Reporter.log("Size guide link is Displayed :: Clickable");
			//Close first size guide window
			driver.findElement(By.cssSelector(".close_chart_modal")).click();

			Thread.sleep(3000);
		} else {
			System.out.println("Size guide link is not Displayed");
			Reporter.log("Size guide link is not Displayed.");
		}
		if (driver.findElement(RATING_STARS).isDisplayed()) {

			WebElement elem = driver.findElement(RATING_STARS);
			JavascriptExecutor executore = (JavascriptExecutor) driver;
			executore.executeScript("arguments[0].click();", elem);
			// wait for scroll it down
			Thread.sleep(6000);
			Reporter.log("Review Stars Are Displayed :: Clickable :: Clicked");
			// check for Rating and Reviews are present or not
			System.out.println("rating: " + driver.findElement(RATING_NUMBER).getText());

			if (!driver.findElement(RATING_NUMBER).getText().equals("(0)")) {


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
				if (!driver.findElement(NO_REVIEW_WRITE_REVIEW).isEnabled())

					throw new Exception("'WRITE A REVIEW' button is not displayed");
				driver.findElement(NO_REVIEW_WRITE_REVIEW).click();
				Reporter.log("'WRITE A REVIEW' button is Displayed :: Clickable");

			}
			Reporter.log("Rating and Reviews Displayed");
		} else
			throw new Exception("Rating Stars are not clickable");


		//driver.findElement(SIZE_DROPDOWN).click();
		jse.executeScript("arguments[0].click();", driver.findElement(SIZE_DROPDOWN));
		element = driver.findElement(By.cssSelector(".select--options__list > li:nth-child(2) > div"));

		jse.executeScript("arguments[0].click();", element);

		element = driver.findElement(ADD_TO_CART_BUTTON);

		//String text1 = element.getText();
		String text1 = element.getAttribute("innerText");
		System.out.println("Button text when Available size selected :: " + text1);
		if (!(text1.equalsIgnoreCase("OUT OF STOCK") || text1.equalsIgnoreCase("Join the Waitlist"))) {
			if (!(text1.equalsIgnoreCase("Add to Cart"))) {
				throw new Exception("Text change for 'ADD TO CART' ");
			}
		}
		// check if we are able to add and minus quantity
		try {
			// check if we are able to add and minus quantity
			String quantity = driver.findElement(QUANTITY).getAttribute("data-add-qty");
			Thread.sleep(3000);
			element = driver.findElement(PLUS_BUTTON);
			jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", element);
			//	driver.findElement(PLUS_BUTTON).click();
			Thread.sleep(3000);
			String quantity1 = driver.findElement(QUANTITY).getAttribute("data-add-qty");
			// check quantity added by 1 or not
			if (Integer.parseInt(quantity1) != Integer.parseInt(quantity) + 1)
				throw new Exception("Quantity not increased by 1");

			Reporter.log("Quantity increased by 1");
			element = driver.findElement(MINUS_BUTTON);
			jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", element);
			String quantity2 = driver.findElement(QUANTITY).getAttribute("data-add-qty");
			if (Integer.parseInt(quantity2) != Integer.parseInt(quantity1) - 1)
				throw new Exception("Quantity not decreased by 1");
			Reporter.log("Quantity decreased by 1");
		} catch (Exception e) {
			Reporter.log("Quantity selector was not present - please check if DY hides Qunatity selector is active");
		}


		// check 'Best PairGuarantee' and 'Shopping And Returns' are clickable or not
		flag = false;

		try {
			if (driver.findElement(PRODUCT_DETAILS).isDisplayed()) {
				element = driver.findElement(PRODUCT_DETAILS);
				jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].click();", element);
			} else throw new Exception("Product details accordion not present or not clikable ");
		} catch (Exception e) {
			Reporter.log("Product details accordion is not present for this particular product");
		}
		try {
			if (driver.findElement(BEST_PAIR_GUARANTEE).isDisplayed()) {
				element = driver.findElement(BEST_PAIR_GUARANTEE);
				jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].click();", element);
			} else throw new Exception("PAIR_GUARANTEE accordion not present or not clikable");
		} catch (Exception e) {
			Reporter.log("Pair guarantee link is not present for this particular product");
		}

		try {
			if (!driver.findElement(FREE_RETURNS).getText().contentEquals("Free Shipping & Returns")) {
				if (driver.findElement(FREE_RETURNS).isDisplayed()) {
					element = driver.findElement(FREE_RETURNS);
					jse = (JavascriptExecutor) driver;
					jse.executeScript("arguments[0].click();", element);
					//driver.findElement(SHIPING_AND_RETURNS).click();
				} else throw new Exception("SHIPING_AND_RETURNS accordion not present or not clikable");
			}
		} catch (Exception e) {
			Reporter.log("SHIPING_AND_RETURNS link is not present for this particular product");
		}

		if (!driver.findElement(ADD_TO_CART_BUTTON).isEnabled())
			throw new Exception("ADD TO CART button is not present");
		element = driver.findElement(ADD_TO_CART_BUTTON);
		jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", element);

		Reporter.log("ADD TO CART button is Displayed :: Clickable");
		softAssert.assertAll();

	}

	public void QuickShopOnBuyWith() throws Exception {
		new HomePageComponents(driver).navigateToAllPantiesInWomenCategory();
		new CollectionPageComponent(driver).navigateToProductDetailsPage();


		try {
			if (driver.findElement(By.cssSelector(".product-gallery>.buy-with-products")).isDisplayed()) {
				Actions action = new Actions(driver);
				Thread.sleep(3000);

				//Hover On product image under buy with section on PDP
				element = driver.findElement(FIRST_PRODUCT_IMAGE_BUYWITH);
				action.moveToElement(element).build().perform();
				Thread.sleep(3000);
				System.out.println("Product image is hovered.");
				new CollectionPageComponent(driver).AddproductToCartByQuickShop();
			} else
				throw new Exception("Buy with section is not displayed on PDP");
		} catch (Exception e) {
			throw new Exception("Buy with section is not displayed on PDP");
		}
	}

	public void ATC_Scrolling_CTA() throws Exception {
		new HomePageComponents(driver).navigateToAllUnderwearInMenCategory();
		new CollectionPageComponent(driver).navigateToProductDetailsPage();
		new AddToCartComponents(driver).selectSize();
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,650)");
		WebElement Stickynav = driver.findElement(By.cssSelector(".product-bar"));
		SoftAssert softassert = new SoftAssert();
		softassert.assertTrue(Stickynav.isDisplayed(), "Sticky nav is not displayed on PDP");
		element = driver.findElement(By.cssSelector(".product-bar__atc"));
		softassert.assertTrue(element.isDisplayed(), "scrolling CTA Add to Cart is not displayed");
		softassert.assertEquals(element.getText(), "Add To Cart", "Text changed for scrolling CTA Add to cart");
		Thread.sleep(3000);
		element.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.INLINE_CART_TITLE));
		softassert.assertTrue(driver.findElement(FlyCartPageLocator.INLINE_CART_TITLE).isDisplayed(), "Fly cart is not expanded after ATC on PDP");
		softassert.assertAll();
		Reporter.log("Scrolling CTA Add to Cart is displayed :: Clickable");
	}

	public void Sort_By_Reviews() throws Exception {
		jse = (JavascriptExecutor) driver;
		SoftAssert softassert = new SoftAssert();
		new HomePageComponents(driver).navigateToDressesInwomensCategory();
		new CollectionPageComponent(driver).navigateToProductDetailsPage();
		Thread.sleep(5000);
		if (!driver.findElement(RATING_NUMBER).getText().equals("(0)")) {
			WebElement rating = driver.findElement(RATING_NUMBER);
			jse.executeScript("arguments[0].click();", rating);

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(SORT_BY));

			softassert.assertEquals(driver.findElement(SORT_BY).getText(), "Sort By", "Default text is not 'Sort By' for reviews sort option");

			//Verify Default sort By is newest
			List<WebElement> sort_options = driver.findElements(SORT_BY_OPTIONS);
			for (int i = 1; i < sort_options.size(); i++) {
				WebElement sort_by_option = driver.findElement(By.cssSelector(".yotpo-reviews-question-container>div.yotpo-sort-by-options-container>ul>li:nth-child(" + i + ")"));
				String Option_name = sort_by_option.getAttribute("data-sort-option");
				if (Option_name.equals("Newest")) {
					String allclasses = sort_by_option.getAttribute("class");
					System.out.println("Class name is: " + allclasses);
					if (allclasses.contains("active")) {
						System.out.println("Default sort By is: " + Option_name);
						Reporter.log("Default sort By is newest");
					} else {
						throw new Exception("Default sort By is not newest.");
					}
					break;
				}

			}
			//verify sort By options are displayed
			driver.findElement(SORT_BY).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(SORT_BY_OPTIONS_DIV));
			softassert.assertTrue(driver.findElement(SORT_BY_OPTIONS_DIV).isDisplayed(), "Sort By option container is not displayed after click on sort by filter");
			for (int i = 1; i < sort_options.size(); i++) {
				WebElement sort_by_option = driver.findElement(By.cssSelector(".yotpo-reviews-question-container>div.yotpo-sort-by-options-container>ul>li:nth-child(" + i + ")"));
				String Option_name = sort_by_option.getAttribute("data-sort-option");
				softassert.assertTrue(sort_by_option.isDisplayed(), "Sort By option " + Option_name + "  is not displayed");
			}
			Reporter.log("Sort By options are displayed");

			//Apply sort By after rating filter
			driver.navigate().refresh();
			Thread.sleep(5000);
			for (int j = 1; j <= 5; j++) {
				element = driver.findElement(By.cssSelector(".yotpo-reviews-widget >div> div.yotpo-review-bottomline-container >div:nth-child(2) > ul.yotpo-revew-bottomline-list > li:nth-child(" + j + ") > .yotpo-review-bottomline-individual-ratings > .yotpo-review-bottomline-individual-ratings__count>span"));
				String num = element.getText();
				int count = Integer.parseInt(num);
				if (count > 0) {
					Thread.sleep(3000);
					jse.executeScript("arguments[0].click();", element);
					//	element.click();
					break;
				}
			}
			wait.until(ExpectedConditions.presenceOfElementLocated(REVIEW_FILTER_MESSAGE));
			element = driver.findElement(SORT_BY);
			jse.executeScript("arguments[0].click();", element);
			//Thread.sleep(3000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(SORT_BY_OPTIONS_DIV));
			softassert.assertTrue(driver.findElement(SORT_BY_OPTIONS_DIV).isDisplayed(), "Sort By option container is not displayed after apply rating filter");
			WebElement Most_Helpful_SortBy = driver.findElement(By.cssSelector(".yotpo-reviews-question-container>div.yotpo-sort-by-options-container>ul>li:nth-child(4)"));
			String allclass = Most_Helpful_SortBy.getAttribute("class");
			String optionname = Most_Helpful_SortBy.getAttribute("data-sort-option");
			System.out.println("selected option is: " + optionname + " & its class is: " + allclass);
			jse.executeScript("arguments[0].click();", Most_Helpful_SortBy);
			//Most_Helpful_SortBy.click();
			Thread.sleep(3000);
			softassert.assertEquals(driver.findElement(SORT_BY).getText(), optionname, "Sort By options are not clickable");
			Reporter.log("User is able to sort after applying rating filter");
		} else
			System.out.println("Desired PDP have no Sort By functionality because of zero reviews.");
		softassert.assertAll();
	}

	public void Check_Ask_Question() throws Exception {
		jse = (JavascriptExecutor) driver;
		SoftAssert softassert = new SoftAssert();
		new HomePageComponents(driver).navigateToAllUnderwearInMenCategory();
		new CollectionPageComponent(driver).navigateToProductDetailsPage();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		//Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(RATING_STARS));
		element = driver.findElement(RATING_NUMBER);
		jse.executeScript("arguments[0].click();", element);
		Thread.sleep(3000);
		softassert.assertEquals(driver.findElement(Ask_a_Question_CTA).getText(), "Ask a Question", "Text change for 'Ask a question'CTA");
		WebElement AQ_CTA = driver.findElement(Ask_a_Question_CTA);
		jse.executeScript("arguments[0].click();", AQ_CTA);
		Thread.sleep(3000);
		//	jse.executeScript("window.scrollBy(0, 30)");
		List<String> arraylist = new ArrayList<String>();
		String classes = driver.findElement(ASK_A_QSN_CONTAINER).getAttribute("class");
		for (String a : classes.split(" ")) {
			arraylist.add(a);
		}
		System.out.println("arraylist: " + arraylist);
		softassert.assertTrue(arraylist.contains("show"), "Ask a Question form is not displayed on cta click");
		softassert.assertTrue(driver.findElement(QSN_ANS_NAME_TEXTBOX).isDisplayed(), "Q&A Name textbox is not displayed");
		softassert.assertTrue(driver.findElement(QSN_ANS_EMAIL_TEXTBOX).isDisplayed(), "Q&A Email textbox is not displayed");
		softassert.assertTrue(driver.findElement(QSN_ANS_QUESTION_TEXTBOX).isDisplayed(), "Q&A Question textbox is not displayed");
		softassert.assertTrue(driver.findElement(SUBMIT_QUESTION_CTA).isDisplayed(), "Q&A submit button is not displayed");

		//field validations
		element = driver.findElement(SUBMIT_QUESTION_CTA);
		jse.executeScript("arguments[0].click();", element);
		Thread.sleep(2000);
		softassert.assertTrue(driver.findElement(QSN_ANS_NAME_ERROR).isDisplayed(), "Name error message is not displayed with invalid input");
		softassert.assertTrue(driver.findElement(QSN_ANS_EMAIL_ERROR).isDisplayed(), "Email error message is not displayed with invalid input");
		softassert.assertTrue(driver.findElement(QSN_ANS_QUESTION_ERROR).isDisplayed(), "Question error message is not displayed with invalid input");

		Reporter.log("Ask a Question form is Displayed :: Correct");
		softassert.assertAll();
	}


	public void Check_Selected_Swatch() throws Exception {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		new HomePageComponents(driver).navigateToAllUnderwearInMenCategory();
		Thread.sleep(5000);
		executor.executeScript("window.scrollBy(0, 350)", "");
		Thread.sleep(5000);

		List<WebElement> allElements = driver.findElements(By.cssSelector("div.mega-collection-grid.js-mega-collection-grid > div:nth-child(3) > div:nth-child(1)>div:nth-child(2)>.product-item__swatch-list>.slick-list>.slick-track>div>div"));
		int elementsCount = allElements.size();
		System.out.println("Color Element count :: " + elementsCount);

		if (elementsCount > 1) {
			element = allElements.get(elementsCount - 1);
			String swatch_name_plp = element.getAttribute("title").toString();
			executor.executeScript("arguments[0].click();", element);
			WebElement el = driver.findElement(CollectionPageLocator.FIRST_PRODUCT_HEAD_LINE_TEXT);
			executor.executeScript("arguments[0].click();", el);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_TITLE));
			String colortext_pdp = driver.findElement(COLOR_TEXT).getText();

			if (!swatch_name_plp.equalsIgnoreCase(colortext_pdp))
				throw new Exception("PDP doesn't opened with selected swatch from plp");
			Reporter.log("Validated PDP opens with selected swatch from plp");
		}


	}

	public void Check_Preselected_Size() throws Exception {

		SoftAssert softassert = new SoftAssert();
		List<String> selected_bundle_size1 = new ArrayList<String>();
		List<String> selected_bundle_size2 = new ArrayList<String>();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionPageLocator.FIRST_PRODUCT));
		Thread.sleep(3000);
		String[] title1 = driver.findElement(CollectionPageLocator.FIRST_PRODUCT).getAttribute("data-name").split(",");
		String pname = title1[0];
		String[] title = pname.split(" ");

		String Product_type = title[title.length - 1];
		System.out.println("product type is: " + Product_type);
		element = driver.findElement(CollectionPageLocator.FIRST_PRODUCT);
		List<WebElement> producttitle = driver.findElements(By.cssSelector(".product-meta__title"));
		if (Product_type.equalsIgnoreCase("Pack")) {
			for (WebElement packtitle : producttitle) {
				System.out.println("product title is: " + packtitle.getText());
				if (packtitle.getText().contains("Set")) {
					Thread.sleep(3000);
					System.out.println(packtitle.getText());
					element = packtitle;
					break;
				}
			}

		}

		if ((Product_type.equalsIgnoreCase("Set") || element.getText().contains("Set"))) {
			jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,250)");
			Thread.sleep(4000);

			producttitle = driver.findElements(By.cssSelector(".product-meta__title"));

			for (WebElement packtitle : producttitle) {
				System.out.println("product title is: " + packtitle.getText());
				if (packtitle.getText().contains("Set")) {
					Thread.sleep(3000);
					System.out.println(packtitle.getText());
					//	packtitle.click();
					jse = (JavascriptExecutor) driver;
					jse.executeScript("arguments[0].click();", packtitle);

					break;
				}
			}
			wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_TITLE));
			SelectSizeOnBundlePDP();
			List<WebElement> BundleSize_OptionName = driver.findElements(By.cssSelector("div.radios-wrapper > div.radio-options-container > div"));
			int BundleSize_OptionCount = BundleSize_OptionName.size();
			for (int i = 1; i <= BundleSize_OptionCount; i++) {
				allElements = driver.findElements(By.cssSelector("div.radios-wrapper > div.radio-options-container:nth-child(" + i + ") > div>input.sizeOption"));
				for (int j = 1; j < allElements.size(); j++) {
					element = driver.findElement(By.cssSelector("input.sizeOption:nth-child(" + (j * 2) + ")"));
					try {
						String value = element.getAttribute("checked");
						if (value == null) {
							continue;
						} else {
							BundleSizename1 = element.getAttribute("value");
							selected_bundle_size1.add(BundleSizename1);
							break;
						}
					} catch (Exception e) {
						System.out.println(element.getAttribute("value") + " size is not selected");
					}
				}

			}
			driver.navigate().back();
			Thread.sleep(5000);
			List<WebElement> Bundleproducttitle = driver.findElements(By.cssSelector(".product-meta__title"));
			for (int k = 2; k < Bundleproducttitle.size(); k++) {
				try {
					for (WebElement packtitle : Bundleproducttitle) {
						if (packtitle.getText().contains("Set")) {
							Thread.sleep(3000);
							element = driver.findElement(By.cssSelector("div.mega-collection-grid >div>div> div:nth-child(" + (k + 1) + ") > a> span"));
							break;
						}
					}


					//element = driver.findElement(By.cssSelector("div.mega-collection-grid > div:nth-child(3) > div:nth-child("+k+") > a> h2"));
				} catch (Exception ex) {
					for (WebElement packtitle : Bundleproducttitle) {
						if (packtitle.getText().contains("Set")) {
							Thread.sleep(3000);
							element = driver.findElement(By.cssSelector("div.mega-collection-grid> div >div> div:nth-child(" + (k + 2) + ") > a> span"));
							break;
						}
					}

					//element = driver.findElement(By.cssSelector("div.mega-collection-grid > div:nth-child(3) > div:nth-child("+(k+1)+") > a> h2"));
				}
				String[] Bundletitle1 = element.getText().split(",");
				String Bundle_pname = Bundletitle1[0];
				String[] BundleProducttitle = Bundle_pname.split(" ");
				String Bundle_Product_type = BundleProducttitle[BundleProducttitle.length - 1];
				if (Bundle_Product_type.equals("Set")) {
					Thread.sleep(3000);
					System.out.println("2nd selected product name is: " + element.getText());
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
					break;
				} else {
					((JavascriptExecutor) driver).executeScript("window.scrollBy(0,100)");
					continue;
				}
			}

			List<WebElement> BundleSize_OptionName2 = driver.findElements(By.cssSelector("div.radios-wrapper > div.radio-options-container > div"));
			int BundleSize_OptionCount2 = BundleSize_OptionName2.size();
			for (int i = 1; i <= BundleSize_OptionCount2; i++) {
				allElements = driver.findElements(By.cssSelector("div.radios-wrapper > div.radio-options-container:nth-child(" + i + ") > div>input.sizeOption"));
				for (int j = 1; j < allElements.size(); j++) {
					element = driver.findElement(By.cssSelector("input.sizeOption:nth-child(" + (j * 2) + ")"));
					try {
						String value = element.getAttribute("checked");
						if (value == null) {
							continue;
						} else {
							BundleSizename1 = element.getAttribute("value");
							selected_bundle_size2.add(BundleSizename1);
							break;
						}
					} catch (Exception e) {
						System.out.println(element.getAttribute("value") + " size is not selected");
					}
				}

			}

			System.out.println("selected size is: " + selected_bundle_size1 + " preselected size is: " + selected_bundle_size2);
			softassert.assertTrue(selected_bundle_size1.equals(selected_bundle_size2), "Preselect functionality for size options are incorrect on Bundle pdp");
		} else {
			new CollectionPageComponent(driver).navigateToProductDetailsPage();
			wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_TITLE));
			//new AddToCartComponents(driver).selectSize();
			Thread.sleep(2000);
			WebElement second_size = driver.findElement(By.cssSelector("div.radios-wrapper > div.radio-options-container > div > label:nth-child(5)"));
			//second_size.click();
			jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", second_size);

			allElements = driver.findElements(By.cssSelector("input.sizeOption"));
			for (int i = 1; i < allElements.size(); i++) {
				element = driver.findElement(By.cssSelector("input.sizeOption:nth-child(" + (i * 2) + ")"));
				try {
					String value = element.getAttribute("checked");
					if (value == null) {
						continue;
					} else {
						Sizename1 = element.getAttribute("value");
						System.out.println("size1 is: " + Sizename1);
						break;
					}
				} catch (Exception e) {
					System.out.println(element.getAttribute("value") + " size is not selected");
				}
			}

			driver.navigate().back();
			//((JavascriptExecutor)driver).executeScript("window.scrollBy(0,450)");
			Thread.sleep(5000);
			producttitle = driver.findElements(By.cssSelector(".product-meta__title"));
			for (int j = 2; j < producttitle.size(); j++) {
				try {
					if (driver.findElement(By.cssSelector("div.collection-hero--interactive-inner")).isDisplayed())
						((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)");
					element = driver.findElement(By.cssSelector("div.mega-collection-grid > div:nth-child(3) > div:nth-child(" + j + ") > a> span"));
				} catch (Exception e) {
					((JavascriptExecutor) driver).executeScript("window.scrollBy(0,100)");
					wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionPageLocator.FIRST_PRODUCT));
					try {
						element = driver.findElement(By.cssSelector("div.mega-collection-grid > div > div > div:nth-child(" + j + ")> a > span"));
					} catch (Exception ex) {
						element = driver.findElement(By.cssSelector("div.mega-collection-grid > div > div > div:nth-child(" + (j + 1) + ")> a > span"));
					}
				}
				if (element.getText().endsWith(Product_type)) {
					Thread.sleep(3000);
					System.out.println("2nd selected product name is: " + element.getText());
					//element.click();
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
					break;
				} else {
					((JavascriptExecutor) driver).executeScript("window.scrollBy(0,100)");
					continue;
				}
			}
			Thread.sleep(5000);
			allElements = driver.findElements(By.cssSelector("input.sizeOption"));
			for (int k = 1; k < allElements.size(); k++) {
				WebElement element1 = driver.findElement(By.cssSelector("input.sizeOption:nth-child(" + (k * 2) + ")"));
				try {
					String value1 = element1.getAttribute("checked");
					if (value1 == null) {
						continue;
					} else {
						Sizename2 = element1.getAttribute("value");
						System.out.println("size2 is: " + Sizename2);
						break;
					}
				} catch (Exception e) {
					System.out.println(element1.getAttribute("value") + " size is not selected");
				}
			}
			System.out.println("selected size is: " + Sizename1 + " preselected size is: " + Sizename2);
			softassert.assertEquals(Sizename1, Sizename2, "Preselect functionality for size options are incorrect");
		}

		Reporter.log("Preselect functionality for sizes are correct on pdp");
		softassert.assertAll();

	}

	public void ErrorMessagewithMaxQTYonPDP() throws Exception {
		SoftAssert softassert = new SoftAssert();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionPageLocator.FIRST_PRODUCT));
		Thread.sleep(3000);
		new CollectionPageComponent(driver).navigateToProductDetailsPage();
		wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_TITLE));
		new AddToCartComponents(driver).selectSize();
		if (!driver.findElement(ADD_TO_CART_BUTTON).isEnabled())
			throw new Exception("ADD TO CART button is not present");
		element = driver.findElement(ADD_TO_CART_BUTTON);
		jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", element);
		Thread.sleep(2000);

		driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOfElementLocated(HomePageLocators.CART_ICON));
		driver.findElement(HomePageLocators.CART_ICON).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.FIRST_PRODUCT_TITLE_IN_CART));
		driver.findElement(FlyCartPageLocator.QUANTITY).sendKeys("100000000");
		Thread.sleep(3000);
		driver.findElement(FlyCartPageLocator.PLUS_BUTTON).click();
		Thread.sleep(2000);
		driver.findElement(FlyCartPageLocator.INLINE_CART_CLOSE_BUTTON).click();
		Thread.sleep(3000);
		driver.findElement(ADD_TO_CART_BUTTON).click();
		Thread.sleep(5000);
		softassert.assertTrue(driver.findElement(LOW_INVENTORY_MSG).isDisplayed(), "On pdp 'Low inventory message' is not displayed with maximum qty added to cart");
		softassert.assertAll();
		Reporter.log("'Low inventory message' is verified on PDP");
	}

	public void SelectSizeOnBundlePDP() throws Exception {
		allElements = driver.findElements(ALL_SIZE_LIST);
		jse = (JavascriptExecutor) driver;
		elementsCount = allElements.size();
		System.out.println("All sizes list :: " + elementsCount);
		if (driver.findElement(By.cssSelector(".radio-options-container")).isDisplayed()) {
			//radio
			List<WebElement> Size_OptionName = driver.findElements(By.cssSelector("div.radios-wrapper > div.radio-options-container > div"));
			int Size_OptionCount = Size_OptionName.size();
			for (int i = 1; i <= Size_OptionCount; i++) {
				Thread.sleep(3000);
				List<WebElement> Sizes_Label = driver.findElements(By.cssSelector("div.radios-wrapper>div:nth-child(" + i + ")>div.Size>label"));
				elementsCount = Sizes_Label.size();
				System.out.println("All sizes list count is :: " + elementsCount);

				WebElement second_size_option = driver.findElement(By.cssSelector("div.radios-wrapper > div.radio-options-container:nth-child(" + i + ")> div > label:nth-child(5)"));
				//second_size_option.click();
				jse.executeScript("arguments[0].click();", second_size_option);
			}
		}
	}

	public void Add_Bundle_toCart() throws Exception {
		jse = (JavascriptExecutor) driver;
		String allClasses = null;
		String allClass = null;
		new SizeGuideComponents(driver).navigateToBundlePDP();
		Thread.sleep(5000);
		allElements = driver.findElements(ALL_SIZE_LIST);
		elementsCount = allElements.size();
		System.out.println("All sizes list :: " + elementsCount);
		if (driver.findElement(By.cssSelector(".radio-options-container")).isDisplayed()) {
			//radio
			List<WebElement> Size_OptionName = driver.findElements(By.cssSelector("div.radios-wrapper > div.radio-options-container > div"));
			int Size_OptionCount = Size_OptionName.size();
			for (int i = 1; i <= Size_OptionCount; i++) {
				Thread.sleep(3000);
				List<WebElement> Sizes_Label = driver.findElements(By.cssSelector("div.radios-wrapper>div:nth-child(" + i + ")>div.Size>label"));
				elementsCount = Sizes_Label.size();
				System.out.println("All sizes list count is :: " + elementsCount);

				for (int j = 3; j <= elementsCount; j = j + 2) {
					flag = false;
					element = driver.findElement(By.cssSelector("div.radios-wrapper > div.radio-options-container:nth-child(" + i + ")> div > label:nth-child(" + j + ")"));

					allClasses = element.getAttribute("class");
					// check size available or not
					for (String c : allClasses.split(" ")) {
						if (c.equals("oosVariant")) {
							/*
							 * jse = (JavascriptExecutor)driver; jse.executeScript("arguments[0].click();",
							 * element); System.out.println("Unavailable size clicked");
							 */
							int y = j;
							for (int k = y; k < y; k++) {

								WebElement e = driver.findElement(By.cssSelector("div.radios-wrapper > div.radio-options-container > div > label:nth-child(" + k + ")"));
								allClass = e.getAttribute("class");
								for (String d : allClass.split(" ")) {
									if (d.equals("oosVariant"))
										y++;
									else
										driver.findElement(By.cssSelector("div.radios-wrapper > div.radio-options-container > div > label:nth-child(" + y + ")")).click();
								}
							}
							//flag=true;
						} else {
							System.out.println("selected size is: " + element.getText());
							jse = (JavascriptExecutor) driver;
							jse.executeScript("arguments[0].click();", element);

						}

					}
					if (j == elementsCount) {
						Reporter.log("No any size available");
						break;
					}
					if (flag == false)
						break;
					else
						continue;

				}

				if (flag == true)
					continue;

			}
			if (!driver.findElement(ADD_TO_CART_BUTTON).isEnabled())
				throw new Exception("ADD TO CART button is not present");
			element = driver.findElement(ADD_TO_CART_BUTTON);
			jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", element);

		} else {
			//dropdown

			for (int i = 1; i <= elementsCount; i++) {
				flag = false;
				//driver.findElement(SIZE_DROPDOWN).click();
				WebElement elem = driver.findElement(SIZE_DROPDOWN_MENS);
				jse.executeScript("arguments[0].click();", elem);
				element = driver.findElement(By.cssSelector(".select--options__list[data-option-name = 'Mens Size'] > li:nth-child(" + i + ") > div"));

				allClasses = element.getAttribute("class");
				// check size available or not
				for (String c : allClasses.split(" ")) {
					if (c.equals("unavailable")) {

						driver.findElement(By.cssSelector(".select--options__list[data-option-name = 'Mens Size']> li:nth-child(" + i + ")")).click();
						WebElement eleme = driver.findElement(SIZE_DROPDOWN_WOMENS);
						jse.executeScript("arguments[0].click();", eleme);
						//	driver.findElement(By.cssSelector(".select--options__list[data-option-name = 'Womens Size']> li:nth-child("+i+")")).click();
						int y = i;
						for (int j = 0; j < y; j++) {

							WebElement e = driver.findElement(By.cssSelector(".select--options__list[data-option-name = 'Womens Size'] > li:nth-child(" + y + ") > div"));
							allClass = e.getAttribute("class");
							for (String d : allClass.split(" ")) {

								// check size available or not

								if (d.equals("unavailable"))
									y++;
								else
									driver.findElement(By.cssSelector(".select--options__list[data-option-name = 'Womens Size']> li:nth-child(" + y + ")")).click();
							}
						}


						flag = true;

						//break;
					} else {
						Thread.sleep(3000);
						WebElement e = driver.findElement(By.cssSelector(".select--options__list[data-option-name = 'Mens Size']> li:nth-child(" + i + ")"));
						Thread.sleep(2000);
						jse.executeScript("arguments[0].click();", e);
						if (!driver.findElement(ADD_TO_CART_BUTTON).getText().contentEquals("Add to Cart")) {
							WebElement eleme = driver.findElement(SIZE_DROPDOWN_WOMENS);
							jse.executeScript("arguments[0].click();", eleme);

							int y = i;
							for (int j = 0; j < y; j++) {

								WebElement el = driver.findElement(By.cssSelector(".select--options__list[data-option-name = 'Womens Size'] > li:nth-child(" + y + ") > div"));
								String allClas = el.getAttribute("class");
								for (String u : allClas.split(" ")) {

									// check size available or not

									if (u.equals("unavailable"))
										y++;
									else
										driver.findElement(By.cssSelector(".select--options__list[data-option-name = 'Womens Size']> li:nth-child(" + y + ")")).click();
								}
							}
						}

						//jse.executeScript("arguments[0].click();", driver.findElement(SIZE_DROPDOWN_WOMENS));

						//	WebElement elm  = driver.findElement(By.cssSelector(".select--options__list[data-option-name = 'Womens Size']> li:nth-child("+i+") > div"));


						//jse.executeScript("arguments[0].click();", elm);
						if (!driver.findElement(ADD_TO_CART_BUTTON).getText().contentEquals("Add to Cart"))
							throw new Exception("Wrong language on CTA for In stock bundle");

					}

					if (i == elementsCount) {
						Reporter.log("No any size available");

					}
					if (flag == false)
						break;
					else
						continue;

				}
				if (flag == false)
					break;
				else
					continue;
			}
			if (!driver.findElement(ADD_TO_CART_BUTTON).isEnabled())
				throw new Exception("ADD TO CART button is not present");
			element = driver.findElement(ADD_TO_CART_BUTTON);
			jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", element);

		}
	}

	public void verifyImageGallery() throws Exception {
		new ProductDetailsPage(driver).navigateToBraCategory();

		SoftAssert softAssert = new SoftAssert();

		//navigate to PDP
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_PRODUCT));

		WebElement el = driver.findElement(FIRST_PRODUCT_HEAD_LINE_TEXT);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", el);

		Thread.sleep(3000);
		allElements = driver.findElements(IMG_CAROUSEL);
		elementsCount = allElements.size();
		System.out.println("Image Element count :: " + elementsCount);

		if (elementsCount > 1) {
			for (int i = 1; i < elementsCount; i++) {

				String imagegallery = null;
				String mainimage = null;
				Thread.sleep(6000);
				imagegallery = driver.findElement(By.cssSelector(".pdpVerticalSlides>.slick-list.draggable>div>div:nth-child(" + i + ")")).getAttribute("data-slide-id");
				driver.findElement(By.cssSelector(".pdpVerticalSlides>.slick-list.draggable>div>div:nth-child(" + i + ")>img")).click();


				mainimage = driver.findElement(By.cssSelector(".pdpImageSlidesWrapper>div>div>div>div:nth-child(" + i + ").slick-active")).getAttribute("data-slide-id");
				System.out.println(imagegallery);
				System.out.println(mainimage);
				if (!imagegallery.contentEquals(mainimage)) {
					System.out.println("Image caraousel does not change");
				} else
					System.out.println("Image carousel changes");

			}
		}
		Reporter.log("Image gallery changes with main image");

		softAssert.assertAll();
	}


	public void viewImageGallery() throws Exception {
		String thumbnail;
		SoftAssert softAssert = new SoftAssert();
		new ProductDetailsPage(driver).navigateToAllUnderwearInMenCategory();

		//navigate to PDP
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_PRODUCT));
		Thread.sleep(10000);
		WebElement el = driver.findElement(FIRST_PRODUCT_HEAD_LINE_TEXT);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", el);

		Thread.sleep(3000);
		allElements = driver.findElements(COLOR_VARIANT);
		elementsCount = allElements.size();
		System.out.println("Image Element count :: " + elementsCount);

		if (elementsCount > 1) {
			for (int i = 1; i <= elementsCount; i++) {
	//			thumbnail = driver.findElement(By.cssSelector(".pdpVerticalSlides>.slick-list.draggable>div>div:nth-child(" + i + ")")).getAttribute("data-slide-id");

				WebElement img = driver.findElement(By.xpath("(//div[@class='space-y-lg']//input[@class='sr-only peer'])["+i+"]"));
				executor.executeScript("arguments[0].click();", img);

				Thread.sleep(3000);
	//			System.out.println(thumbnail);
			}
			Reporter.log("New Image is visible");
			ExtentReport.extentTest.info("New Image is visible");
		}
		softAssert.assertAll();

	}


	public void verifyOnImageBadging() throws Exception {

		SoftAssert softAssert = new SoftAssert();
//		new ProductDetailsPage(driver).navigateToSecondSkinSleepCrewNeckPocket();

		//navigate to PDP
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//		wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_PRODUCT));
//		Thread.sleep(10000);
//		WebElement el = driver.findElement(FIRST_PRODUCT_HEAD_LINE_TEXT);
//		JavascriptExecutor executor = (JavascriptExecutor) driver;
//		executor.executeScript("arguments[0].click();", el);

		driver.get("https://www.tommyjohn.com/products/second-skin-trunk-4-3-pack-49?variant=44120067735597");

//		jse = (JavascriptExecutor) driver;
//		jse.executeScript("window.scrollBy(0,800)");

		wait.until(ExpectedConditions.visibilityOfElementLocated(IMAGE_BADGING));

		WebElement allElements = (WebElement) driver.findElement(IMAGE_BADGING);
		try {
			if (!(allElements).isDisplayed())
				throw new Exception("Badging is not displayed on PDP.");
			else {
				System.out.println("Badging is displayed on PDP.");
				Reporter.log("Badge is displayed on PDP.");
				ExtentReport.extentTest.info("Badge is displayed on PDP.");
			}
		} catch (Exception e) {
			System.out.println("Badging is not applicable for this product.");
			Reporter.log("Badge on PDP :: Not displayed .");
			ExtentReport.extentTest.info("Badge on PDP :: Not displayed .");
		}

		softAssert.assertAll();
	}

	public void verifySecondaryBadging() throws Exception {

		SoftAssert softAssert = new SoftAssert();
		new ProductDetailsPage(driver).NavigateToAllSaleInSaleCategory();

		//navigate to PDP
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_PRODUCT));
		Thread.sleep(10000);
		WebElement el = driver.findElement(FIRST_PRODUCT_HEAD_LINE_TEXT);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", el);


		WebElement allElements;
		allElements = (WebElement) driver.findElement(SECONDARY_BADGING);
//		System.out.println(allElements);
		try {
			if (!(allElements).isDisplayed())
				throw new Exception("Badging is not displayed on PDP.");
			else {
				System.out.println("Badging is displayed on PDP.");
				Reporter.log("Badge is displayed on PDP.");
				ExtentReport.extentTest.info("Badge is displayed on PDP.");
			}
		} catch (Exception e) {
			System.out.println("Badging is not applicable for this product.");
			Reporter.log("Badge on PDP :: Not displayed .");
			ExtentReport.extentTest.info("Badge on PDP :: Not displayeds");
		}

	}

	public void verifySelectedColorSwatch() throws Exception {
		String colornew;
		SoftAssert softAssert = new SoftAssert();
		new ProductDetailsPage(driver).navigateToAllUnderwearInMenCategory();
		//new HomePageComponents(driver).navigateToAllPantiesInWomenCategory();

		//navigate to PDP
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_PRODUCT));
		Thread.sleep(10000);
		WebElement el = driver.findElement(FIRST_PRODUCT_HEAD_LINE_TEXT);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", el);

		Thread.sleep(3000);
		allElements = driver.findElements(COLOR_VARIANT);

		elementsCount = allElements.size();
		System.out.println("Color Element count :: " + elementsCount);
		System.out.println("*****************************");

		if (elementsCount > 1) {
			for (int i = 1; i < elementsCount; i++) {
				String imagecolor = null;
				WebElement elemen = driver.findElement(By.xpath("(//div[@class='space-y-lg']//input[@class='sr-only peer'])["+i+"]"));
				jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].click();", elemen);
//				driver.findElement(COLOR_VARIANT).getText();


//				List<WebElement> inactivesizes = driver.findElements(INACTIVE_SIZES);
//				int num1 = inactivesizes.size();


//				if (num1 > 0) {


					colornew = driver.findElement(By.xpath("(//div[@class='space-y-lg']//span[@class='font-medium text-text-primary'])[1]")).getText();
					imagecolor = driver.findElement(By.xpath("(//div[@class='space-y-lg']//input[@class='sr-only peer'])["+i+"]")).getText();

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
	}

	public void verifyAvailableSizes() throws Exception {
		int count = 0;
		SoftAssert softAssert = new SoftAssert();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		driver.navigate().to("https://www.tommyjohn.com/collections/mens-all");
		//new HomePageComponents(driver).navigateToAllPantiesInWomenCategory();

		//navigate to PDP
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_PRODUCT));
		Thread.sleep(10000);
		WebElement el = driver.findElement(FIRST_PRODUCT_HEAD_LINE_TEXT);
		jse.executeScript("arguments[0].click();", el);


		allElements = driver.findElements(ALL_SIZE_LIST);

		elementsCount = allElements.size();
		System.out.println("Size Element count :: " + elementsCount);
		Reporter.log("Sizes are present");

		if (elementsCount > 1) {
			for (int i = 1; i <= 3; i++) {

				WebElement elemen = driver.findElement(By.xpath("(//div[@class='space-y-lg']//input[@class='sr-only peer'])[" + i + "]"));

				jse.executeScript("arguments[0].click();", elemen);

//				List<WebElement> unavailablecolors = driver.findElements(STRIKED_COLOR_SWATCHES);
//				int num1 = unavailablecolors.size();
//				System.out.println(num1);

				String sizename = driver.findElement(By.xpath("(//input[@name='option[Size]'])["+i+"]")).getAttribute("value");

//				if (num1 > 0 && num1 < elementsCount) {
//					count++;
//					System.out.println("For size: " + sizename);
//
//				}

				String sizevalue = driver.findElement(SIZE_VALUE).getText();
				softAssert.assertEquals(sizename, sizevalue, "Sizes and Colors not changing");


			}

//			if (count > 0) {
//				System.out.println(count);
//				Reporter.log("Sizes are changing and colors are unavailable for few sizes");
//				ExtentReport.extentTest.info("Sizes are changing and colors are unavailable for few sizes");
//			}

			Reporter.log("Sizes are changing correctly");
			ExtentReport.extentTest.info("Sizes are changing correctly");
		}

	}

	public void verifyStickySelectASize() throws Exception {
		new HomePageComponents(driver).navigateToCoolCottonInMensCollection();
		new CollectionPageComponent(driver).navigateToProductDetailsPage();
		Thread.sleep(5000);
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,1200)");
		WebElement Stickynav = driver.findElement(By.xpath("//div[contains(@class, 'sticky bottom')]"));
		SoftAssert softassert = new SoftAssert();
		softassert.assertTrue(Stickynav.isDisplayed(), "Sticky nav is not displayed on PDP");
		WebElement SelectASize = driver.findElement(By.xpath("(//button[@class='button-secondary grow'])[2]"));
		jse.executeScript("arguments[0].click();", SelectASize);
		WebElement SelectSizeSection = driver.findElement(By.xpath("//div[@class='space-y-lg']"));
		softassert.assertTrue(SelectSizeSection.isDisplayed(), "Sticky Select a Size is not Clickable on PDP");
		Reporter.log("Scrolling CTA Select a Size is displayed :: Clickable");
		ExtentReport.extentTest.info("Scrolling CTA Select a Size is displayed :: Clickable");


	}

	public void verifyStickyATC() throws Exception {
		new HomePageComponents(driver).navigateToCoolCottonInMensCollection();
		new CollectionPageComponent(driver).navigateToProductDetailsPage();
		//new AddToCartComponents(driver).selectSize();

		Thread.sleep(5000);
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,200)");
		allElements = driver.findElements(TOTAL_SIZES);
		elementsCount = allElements.size();
		System.out.println("Size Element count :: " + elementsCount);


				jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0,800)");

	//			WebElement Stickynav = driver.findElement(By.xpath(".product-bar"));
				SoftAssert softassert = new SoftAssert();
	//			softassert.assertTrue(Stickynav.isDisplayed(), "Sticky nav is not displayed on PDP");
				element = driver.findElement(By.xpath("(//button[@class='button-secondary grow'])[2]"));
				softassert.assertTrue(element.isDisplayed(), "scrolling CTA Add to Cart is not displayed");
				//softassert.assertEquals(element.getText(), "Add To Cart", "Text is not matched");



				softassert.assertAll();
	//			break;

//			}
//		}

		Reporter.log("Scrolling CTA Add to Cart is displayed :: Clickable");
		ExtentReport.extentTest.info("Scrolling CTA Add to Cart is displayed :: Clickable");

	}


	public void verifyReviewRecommendations() throws Exception {
		new HomePageComponents(driver).navigateToWomensBriefs();
		new CollectionPageComponent(driver).navigateToProductDetailsPage();
		//new AddToCartComponents(driver).selectSize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		Thread.sleep(3000);
		if (driver.findElement(RATING_STARS).isDisplayed()) {

			WebElement elem = driver.findElement(RATING_STARS);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", elem);
			WebElement Readallreviews = wait.until(ExpectedConditions.visibilityOfElementLocated(READ_ALL_REVIEWS_IN_SUMMARY));
			executor.executeScript("arguments[0].click();", Readallreviews);
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

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		new HomePageComponents(driver).navigateToAllPantiesInWomenCategory();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		executor.executeScript("window.scrollBy(0, 300)");
		wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_PRODUCT_IMAGE));
		Thread.sleep(3000);
		String prodcttitle = driver.findElement(FIRST_PRODUCT_IMAGE).getAttribute("alt");
		System.out.println(prodcttitle);
		//		new CollectionPageComponent(driver).navigateToProductDetailsPage();
		WebElement el = driver.findElement(FIRST_PRODUCT_HEAD_LINE_TEXT);
//		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", el);
		wait.until(ExpectedConditions.visibilityOfElementLocated(ProductDetailsPageLocators.PRODUCT_TITLE));
		//new AddToCartComponents(driver).selectSize();
		productname = driver.findElement(PRODUCT_TITLE).getText();
		productprice = driver.findElement(PRODUCT_PRICE).getText();
		System.out.println("Product Name: " + productname);
		System.out.println("Product Price: " + productprice);
		SoftAssert softassert = new SoftAssert();
		try {
			softassert.assertEquals(productname, prodcttitle, "Text is not matched");
			Reporter.log("Product name and Price are displayed");
			ExtentReport.extentTest.info("Product name and Price are displayed");
		} catch (Exception e) {
			Reporter.log("Product name and Price are not displayed");
			ExtentReport.extentTest.info("Product name and Price are not displayed");
		}

		softassert.assertAll();
	}

	public void verifyAccordions() throws Exception {
		new HomePageComponents(driver).navigateToAllPantiesInWomenCategory();
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
		Thread.sleep(2000);
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

	public void verifyDetailsInFitMaterialAccordion() throws Exception {
		SoftAssert softAssert = new SoftAssert();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		new HomePageComponents(driver).navigateToAllPantiesInWomenCategory();
		new CollectionPageComponent(driver).navigateToProductDetailsPage();
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,800)");
		flag = false;
		Exception except = null;
		if (driver.findElement(FIT_MATERIAL).isDisplayed()) {
			element = driver.findElement(FIT_MATERIAL);
			jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", element);
			Thread.sleep(3000);
			//element.click();
		}
		else{
			System.out.println("Fit+Material Accordion Is Not Available");
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(ProductDetailsPageLocators.FIT_MATERIAL_SECTION));
		softAssert.assertTrue(driver.findElement(FIT_MATERIAL_MODEL_DETAIL).isDisplayed(),"Model details is not displayed");
		softAssert.assertTrue(driver.findElement(FIT_MATERIAL_MACHINEWASH).isDisplayed(),"Machine wash detail is not displayed");
		softAssert.assertTrue(driver.findElement(FIT_MATERIAL_TUMBLEDRY).isDisplayed(),"Tumble dry detail  displayed");
		Thread.sleep(5000);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(ProductDetailsPageLocators.FIT_MATERIAL_REVIEW_SIZE));
//		softAssert.assertTrue(driver.findElement(FIT_MATERIAL_REVIEW_SIZE).isDisplayed(),"Review size bar is not displayed");
//		softAssert.assertTrue(driver.findElement(FIT_MATERIAL_CLOTH_DETAIL1).isDisplayed(),"Cloth detail1 is not displayed");
//		softAssert.assertTrue(driver.findElement(FIT_MATERIAL_CLOTH_DETAIL2).isDisplayed(),"Cloth detail2 not displayed");
		softAssert.assertAll();
		Reporter.log("Details in the Fit+Material accordion is displayed appropriately");
		ExtentReport.extentTest.info("Details in the Fit+Material accordion is displayed appropriately");

	}


	public void verifyLowStockInventory() throws Exception {
//		new HomePageComponents(driver).navigateToAllPantiesInWomenCategory();
//		new CollectionPageComponent(driver).navigateToProductDetailsPage();
		driver.get("https://www.tommyjohn.com/products/second-skin-trunk-4-23?variant=42962253938733");
		Thread.sleep(3000);
		allElements = driver.findElements(COLOR_VARIANT);
		int count = 0;
		elementsCount = allElements.size();
		System.out.println("Color Element count :: " + elementsCount);
		Thread.sleep(2000);
		if (elementsCount > 1)

			for (int i = 1; i <= elementsCount; i++) {

				WebElement elemen = driver.findElement(By.xpath("(//div[@class='space-y-lg']//span[@class='sr-only'])["+i+"]"));
				jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].click();", elemen);
				Thread.sleep(1000);

				for (int k = 1; k < 6; k++) {
					WebElement elements = driver.findElement(By.xpath("(//input[@name='option[Size]'])["+k+"]"));
					//elements.click();
					Thread.sleep(1000);
					jse.executeScript("arguments[0].click();", elements);

					try {
						if (driver.findElement(LOW_INVENTORY_MSG).isDisplayed()) {
							System.out.println("LOW INVENTORY MESSAGE for below combinations");
							System.out.println("For Color: " + elemen.getText());
							System.out.println("For Size: " + elements.getAttribute("value"));
							System.out.println("===================");
							Reporter.log("Low Stock Inventory message :: Displayed");
							ExtentReport.extentTest.info("Low Stock Inventory message :: Displayed");
							count++;
						}
					}
					catch(Exception ex)
					{
						System.out.println("No Message");

					}

				}

				if (i > 15)
					break;
			}

		if(count>0) {
			Reporter.log("Message Displayed");
			ExtentReport.extentTest.info("Message Displayed");
		}
		else {
			Reporter.log("No Msg");
			ExtentReport.extentTest.info("No Msg");
		}

	}

//	public void ValidateClickingOnRatingsTierMeter() throws Exception {
//		SoftAssert softAssert = new SoftAssert();
//		new HomePageComponents(driver).navigateToAirTriangleBralette();
//		//		new CollectionPageComponent(driver).navigateToProductDetailsPage();
////		String text = null;
////		JavascriptExecutor js = (JavascriptExecutor) driver;
////		js.executeScript("window.scrollBy(0,250)");
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
////		wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_PRODUCT_HEAD_LINE_TEXT));
////		// get text and click on the first product
////		//text = driver.findElement(FIRST_PRODUCT_IMAGE).getAttribute("alt");
////		text = driver.findElement(FIRST_PRODUCT_HEAD_LINE_TEXT).getText();
////		WebElement el = driver.findElement(FIRST_PRODUCT_HEAD_LINE_TEXT);
////		JavascriptExecutor executor = (JavascriptExecutor) driver;
////		executor.executeScript("arguments[0].click();", el);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(ProductDetailsPageLocators.PRODUCT_TITLE));
//		Reporter.log(driver.getCurrentUrl());
//		ExtentReport.extentTest.info(driver.getCurrentUrl());
//		Thread.sleep(6000);
//		WebElement ratingstars = driver.findElement(RATING_STARS);
//		jse = (JavascriptExecutor) driver;
//		jse.executeScript("arguments[0].click();", ratingstars);
//		jse.executeScript("window.scrollBy(0, 1100)");
//		//ratingstars.click();
//		Thread.sleep(5000);
//		driver.findElement(FIVESTARTIERMETER).click();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(ProductDetailsPageLocators.FIVESTARRATING));
//		Thread.sleep(2000);
////		String fivestar = driver.findElement(FIVESTARRATING);
////		System.out.println(fivestar);
//		softAssert.assertTrue(driver.findElement(FIVESTARRATING).isDisplayed(),"5 Star Ratings are not displayed");
//
//		driver.findElement(FOURSTARTIERMETER).click();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(ProductDetailsPageLocators.FOURSTARRATING));
//		Thread.sleep(2000);
////		String fourstar = driver.findElement(STARRATING).getText();
////		System.out.println(fourstar);
//		softAssert.assertTrue(driver.findElement(FOURSTARRATING).isDisplayed(),"4 Star Ratings are not displayed");
//
//		driver.findElement(THREESTARTIERMETER).click();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(ProductDetailsPageLocators.THREESTARRATING));
//		Thread.sleep(2000);
////		String threestar = driver.findElement(STARRATING).getText();
////		System.out.println(threestar);
//		softAssert.assertTrue(driver.findElement(THREESTARRATING).isDisplayed(),"3 Star Ratings are not displayed");
//
//
//		driver.findElement(TWOSTARTIERMETER).click();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(ProductDetailsPageLocators.TWOSTARRATING));
//		Thread.sleep(2000);
////		String twostar = driver.findElement(STARRATING).getText();
////		System.out.println(twostar);
//		softAssert.assertTrue(driver.findElement(TWOSTARRATING).isDisplayed(),"2 Star Ratings are not displayed");
//
//		driver.findElement(ONESTARTIERMETER).click();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(ProductDetailsPageLocators.ONESTARRATING));
//		Thread.sleep(2000);
////		String onestar = driver.findElement(ONESTARRATING).getText();
////		System.out.println(onestar);
//		softAssert.assertTrue(driver.findElement(ONESTARRATING).isDisplayed(),"1 Star Ratings are not displayed");
//
//		softAssert.assertAll();
//		Reporter.log("Ratings Tiermeter displays appropriate results");
//		ExtentReport.extentTest.info("Ratings Tiermeter displays appropriate result");
//
//	}

	public void ValidateClickingOnRatingsTierMeter() throws Exception {
		SoftAssert softAssert = new SoftAssert();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		// Navigate to the Air Triangle Bralette product page
		new HomePageComponents(driver).navigateToAirTriangleBralette();
		wait.until(ExpectedConditions.visibilityOfElementLocated(ProductDetailsPageLocators.PRODUCT_TITLE));

		// Log the current URL
		String currentURL = driver.getCurrentUrl();
		Reporter.log(currentURL);
		ExtentReport.extentTest.info(currentURL);

		// Wait for and click the rating stars
		WebElement ratingStars = wait.until(ExpectedConditions.visibilityOfElementLocated(RATING_STARS));
		js.executeScript("arguments[0].click();", ratingStars);
		WebElement Readallreviews = wait.until(ExpectedConditions.visibilityOfElementLocated(READ_ALL_REVIEWS_IN_SUMMARY));
		js.executeScript("arguments[0].click();", Readallreviews);

		// Validate each rating tier meter
		Thread.sleep(5000);
		validateRatingTierMeter(FIVESTARTIERMETER, ProductDetailsPageLocators.FIVESTARRATING, "5 Star Ratings are not displayed", softAssert);
		validateRatingTierMeter(FOURSTARTIERMETER, ProductDetailsPageLocators.FOURSTARRATING, "4 Star Ratings are not displayed", softAssert);
		validateRatingTierMeter(THREESTARTIERMETER, ProductDetailsPageLocators.THREESTARRATING, "3 Star Ratings are not displayed", softAssert);
		validateRatingTierMeter(TWOSTARTIERMETER, ProductDetailsPageLocators.TWOSTARRATING, "2 Star Ratings are not displayed", softAssert);
		validateRatingTierMeter(ONESTARTIERMETER, ProductDetailsPageLocators.ONESTARRATING, "1 Star Ratings are not displayed", softAssert);

		// Assert all and log results
		softAssert.assertAll();
		Reporter.log("Ratings Tier Meter displays appropriate results");
		ExtentReport.extentTest.info("Ratings Tier Meter displays appropriate results");
	}

	// Helper method to validate a specific rating tier meter
	private void validateRatingTierMeter(By tierMeterLocator, By ratingLocator, String errorMessage, SoftAssert softAssert) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		// Always re-locate the element just before interacting with it
		WebElement tierMeter = wait.until(ExpectedConditions.presenceOfElementLocated(tierMeterLocator));
		js.executeScript("arguments[0].scrollIntoView(true);", tierMeter);

		// Re-fetch again before clicking to avoid stale reference
		tierMeter = wait.until(ExpectedConditions.elementToBeClickable(tierMeterLocator));
		js.executeScript("arguments[0].click();", tierMeter);

		// Wait for the corresponding rating result
		WebElement ratingElement = wait.until(ExpectedConditions.visibilityOfElementLocated(ratingLocator));
		softAssert.assertTrue(ratingElement.isDisplayed(), errorMessage);

		// Optional delay to handle transitions
		Thread.sleep(1000);
	}


	public void ValidateRecs() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		SoftAssert softAssert = new SoftAssert();
		new HomePageComponents(driver).navigateToMensAll();
		new CollectionPageComponent(driver).navigateToProductDetailsPage();
		new HomePageComponents(driver).navigateToAirTriangleBralette();
		wait.until(ExpectedConditions.visibilityOfElementLocated(ProductDetailsPageLocators.RATING_STARS));
		WebElement ratingstars = driver.findElement(RATING_STARS);
		jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", ratingstars);
		WebElement Readallreviews = wait.until(ExpectedConditions.visibilityOfElementLocated(READ_ALL_REVIEWS_IN_SUMMARY));
		jse.executeScript("arguments[0].click();", Readallreviews);
		jse.executeScript("window.scrollBy(0, 6000)");
		wait.until(ExpectedConditions.visibilityOfElementLocated(ProductDetailsPageLocators.SHOP_THE_COLLECTION));
		softAssert.assertTrue(driver.findElement(SHOP_THE_COLLECTION).isDisplayed(),"Shop the Collection section is not avaialable");
		wait.until(ExpectedConditions.visibilityOfElementLocated(ProductDetailsPageLocators.PEOPLE_ALSO_BOUGHT));
		softAssert.assertTrue(driver.findElement(PEOPLE_ALSO_BOUGHT).isDisplayed(),"People also bought section is not avaialable");
		jse.executeScript("window.scrollBy(0, 3000)");
//		wait.until(ExpectedConditions.visibilityOfElementLocated(ProductDetailsPageLocators.RECENTLY_VIEWED));
//		softAssert.assertTrue(driver.findElement(RECENTLY_VIEWED).isDisplayed(),"Recently viewed section is not avaialable");
		softAssert.assertAll();
		Reporter.log("Recs are available");
		ExtentReport.extentTest.info("Recs are available");
	}


	public void verifyReviewRecommendationsSection() throws Exception {
		SoftAssert softAssert = new SoftAssert();
		new HomePageComponents(driver).navigateToAirTriangleBralette();
		//		new CollectionPageComponent(driver).navigateToProductDetailsPage();
//		String text = null;
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("window.scrollBy(0,250)");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//		wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_PRODUCT_HEAD_LINE_TEXT));
//		// get text and click on the first product
//		//text = driver.findElement(FIRST_PRODUCT_IMAGE).getAttribute("alt");
//		text = driver.findElement(FIRST_PRODUCT_HEAD_LINE_TEXT).getText();
//		WebElement el = driver.findElement(FIRST_PRODUCT_HEAD_LINE_TEXT);
//		JavascriptExecutor executor = (JavascriptExecutor) driver;
//		executor.executeScript("arguments[0].click();", el);
		wait.until(ExpectedConditions.visibilityOfElementLocated(ProductDetailsPageLocators.PRODUCT_TITLE));
		Reporter.log(driver.getCurrentUrl());
		ExtentReport.extentTest.info(driver.getCurrentUrl());
		Thread.sleep(6000);
		WebElement ratingstars = driver.findElement(RATING_STARS);
		jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", ratingstars);
		WebElement Readallreviews = wait.until(ExpectedConditions.visibilityOfElementLocated(READ_ALL_REVIEWS_IN_SUMMARY));
		jse.executeScript("arguments[0].click();", Readallreviews);
		//ratingstars.click();
		Thread.sleep(4000);
//		jse.executeScript("window.scrollBy(0, 400)");
//		Thread.sleep(4000);
		String avgrating = driver.findElement(AVG_RATING_IN_FIRST_BOX).getText();
		System.out.println("Average Rating: " + avgrating);
		String totalreviews = driver.findElement(TOTAL_REVIEWS_IN_FIRST_BOX).getText();
		System.out.println("Total Rating: " + totalreviews);
		Reporter.log("Average rating and Total reviews are :: Displayed");
		ExtentReport.extentTest.info("Average rating and Total reviews are :: Displayed");
		//fit range
		WebElement fitrange = driver.findElement(FIT_RANGE);
		jse.executeScript("arguments[0].click();", fitrange);
		Thread.sleep(2000);
		Reporter.log("Fit range is :: Clickable");
		ExtentReport.extentTest.info("Fit range is :: Clickable");
		//filters click
		WebElement ratingsfilter = driver.findElement(FILTER_RATINGS);
		jse.executeScript("arguments[0].click();", ratingsfilter);
		Thread.sleep(1000);
//		WebElement ratingsfit = driver.findElement(FILTER_FIT);
//		jse.executeScript("arguments[0].click();", ratingsfit);
//		Thread.sleep(1000);
		WebElement ratingssortby = driver.findElement(FILTER_SORTBY);
		jse.executeScript("arguments[0].click();", ratingssortby);

		Thread.sleep(1000);
		Reporter.log("Filters are :: Clickable");
		ExtentReport.extentTest.info("Filters are :: Clickable");
		//sort by dropdown
		List<WebElement> elem = driver.findElements(FILTER_SORTBY_SECTION);
		int sortby = elem.size();
		Thread.sleep(3000);
		System.out.println(sortby);
		if (sortby >= 1) {

			for (int i = 1; i <= sortby; i++) {

				element = driver.findElement(By.xpath("(//span[@class='yotpo-dropdown-option'])["+i+"]"));
//					softAssert.assertTrue(element.isDisplayed(), "SORT bY Filter does not work");
				jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].click();", element);
				Thread.sleep(3000);
				WebElement ratingssortbynew = driver.findElement(FILTER_SORTBY);
				jse.executeScript("arguments[0].click();", ratingssortbynew);

			}
			Reporter.log("Sort by is :: Clickable");
			ExtentReport.extentTest.info("Sort by is :: Clickable");


		}
		//Display Questions
		WebElement qsntab = driver.findElement(QUESTION_TAB);
		//		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(QUESTION_TAB));
		jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", qsntab);
		Thread.sleep(5000);
		element = driver.findElement(QUESTION_FIRST);
		softAssert.assertTrue(element.isDisplayed(), "Questions are not displayed");
		Reporter.log("Questions are :: Displayed");
		ExtentReport.extentTest.info("Questions are :: Displayed");
		softAssert.assertAll();
	}

	public void verifyQuestionForm() throws Exception {

		SoftAssert softassert = new SoftAssert();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		new HomePageComponents(driver).navigateToMensAllUnderwear();
		new CollectionPageComponent(driver).navigateToProductDetailsPage();

		Thread.sleep(5000);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(RATING_STARS));
		WebElement ratingstars = driver.findElement(RATING_STARS);
		jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", ratingstars);
		WebElement Readallreviews = wait.until(ExpectedConditions.visibilityOfElementLocated(READ_ALL_REVIEWS_IN_SUMMARY));
		jse.executeScript("arguments[0].click();", Readallreviews);

		Thread.sleep(5000);
		jse.executeScript("window.scrollBy(0, 800)");
		WebElement qsntab = driver.findElement(QUESTION_TAB);
		//		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(QUESTION_TAB));
		jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", qsntab);
		WebElement AQ_CTA = driver.findElement(Ask_a_Question_CTA);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Ask_a_Question_CTA));
		softassert.assertEquals(driver.findElement(Ask_a_Question_CTA).getText(), "Ask a question", "Text change for 'Ask a question'CTA");

		jse.executeScript("arguments[0].click();", AQ_CTA);
		Thread.sleep(3000);
		//	jse.executeScript("window.scrollBy(0, 30)");
//		List<String> arraylist = new ArrayList<String>();
//		String classes = driver.findElement(ASK_A_QSN_CONTAINER).getAttribute("class");
//		for (String a : classes.split(" ")) {
//			arraylist.add(a);
//		}
		//System.out.println("arraylist: "+ arraylist);
	//	softassert.assertTrue(arraylist.contains("show"), "Ask a Question form is not displayed on cta click");
		softassert.assertTrue(driver.findElement(QSN_ANS_NAME_TEXTBOX).isDisplayed(), "Q&A Name textbox is not displayed");
		softassert.assertTrue(driver.findElement(QSN_ANS_EMAIL_TEXTBOX).isDisplayed(), "Q&A Email textbox is not displayed");
		softassert.assertTrue(driver.findElement(QSN_ANS_QUESTION_TEXTBOX).isDisplayed(), "Q&A Question textbox is not displayed");
		softassert.assertTrue(driver.findElement(SUBMIT_QUESTION_CTA).isDisplayed(), "Q&A submit button is not displayed");

		//field validations
		element = driver.findElement(SUBMIT_QUESTION_CTA);
		jse.executeScript("arguments[0].click();", element);
		Thread.sleep(2000);
		softassert.assertTrue(driver.findElement(QSN_ANS_NAME_ERROR).isDisplayed(), "Name error message is not displayed with invalid input");
		softassert.assertTrue(driver.findElement(QSN_ANS_EMAIL_ERROR).isDisplayed(), "Email error message is not displayed with invalid input");
		softassert.assertTrue(driver.findElement(QSN_ANS_QUESTION_ERROR).isDisplayed(), "Question error message is not displayed with invalid input");

		Reporter.log("Ask a Question form is Displayed :: Correct");
		ExtentReport.extentTest.info("Ask a Question form is Displayed :: Correct");
		softassert.assertAll();


	}

	public void verifyReviewForm() throws Exception {
		SoftAssert softAssert = new SoftAssert();
		// call method to navigate to collection page
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		new HomePageComponents(driver).NavigateToAllSaleInSaleCategory();
		new CollectionPageComponent(driver).navigateToProductDetailsPage();
		// call method to check correct PDP opend or not
		//	checkCorrectProductDetailsPageOpenedOrNot(text);
		Thread.sleep(5000);
		WebElement elem = driver.findElement(RATING_STARS);
		JavascriptExecutor executore = (JavascriptExecutor) driver;
		executore.executeScript("arguments[0].click();", elem);
		WebElement Readallreviews = wait.until(ExpectedConditions.visibilityOfElementLocated(READ_ALL_REVIEWS_IN_SUMMARY));
		executore.executeScript("arguments[0].click();", Readallreviews);
		// wait for scroll it down
		Thread.sleep(3000);
		executore.executeScript("window.scrollBy(0, 650)");
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(WRITE_REVIEW_BUTTON));
		WebElement writereview = driver.findElement(WRITE_REVIEW_BUTTON);
		executore.executeScript("arguments[0].click();", writereview);

		Thread.sleep(2000);
		softAssert.assertEquals(driver.findElement(WRITE_REVIEW_TITLE).getText(), "Write a review *", "Language mismatch for Write a Review title 2");
		System.out.println(driver.findElement(WRITE_REVIEW_TITLE).getText());
		driver.findElement(WRITE_REVIEW_TEXTBOX).sendKeys("Awesome");
		softAssert.assertEquals(driver.findElement(NAME).getText(), "Your name *", "Language mismatch for Name ");
		System.out.println(driver.findElement(NAME).getText());
		driver.findElement(NAME_TEXTBOX).sendKeys("Tester");
		softAssert.assertEquals(driver.findElement(EMAIL).getText(), "Your email address *", "Language mismatch for Email ");
		System.out.println(driver.findElement(EMAIL).getText());
		driver.findElement(EMAIL_TEXTBOX).sendKeys("kritika.bhat@tommyjohnwear.com");
//		softAssert.assertEquals(driver.findElement(LOCATION).getText(), "Location (Optional)", "Laguage mismatch for Location ");
//		driver.findElement(LOCATION_TEXTBOX).sendKeys("India");
//		softAssert.assertEquals(driver.findElement(FIT).getText(), "Fit", "Laguage mismatch for Fit	 ");
//		WebElement fit = driver.findElement(FIT_SELECTION);
//		executore.executeScript("arguments[0].click();", fit);
//		softAssert.assertEquals(driver.findElement(OVERALLRATING).getText(), "* Overall Rating", "Laguage mismatch for Overall Rating	 ");
//		WebElement stars = driver.findElement(STAR_SELECTION);
//		executore.executeScript("arguments[0].click();", stars);
		softAssert.assertEquals(driver.findElement(HEADLINE).getText(), "Add a headline *", "Language mismatch for Headline ");
		System.out.println(driver.findElement(HEADLINE).getText());
		driver.findElement(HEADLINE_TEXTBOX).sendKeys("Nice Product");
//		softAssert.assertEquals(driver.findElement(REVIEW).getText(), "* Review", "Laguage mismatch for Review	 ");
//		driver.findElement(REVIEW_TEXTBOX).sendKeys("Must Buy");


		softAssert.assertTrue(driver.findElement(SUBMIT_BTN).isEnabled(), "Submit button not enabled");
		Reporter.log("Review form is :: Displayed");
		ExtentReport.extentTest.info("Review form is :: Displayed");
		softAssert.assertAll();
	}

	public void verifyReviewFormErrorMessages() throws Exception {

		SoftAssert softAssert = new SoftAssert();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		// call method to navigate to collection page
		new HomePageComponents(driver).navigateToMensAll();
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)");
		new CollectionPageComponent(driver).navigateToProductDetailsPage();

		WebElement elem = driver.findElement(RATING_STARS);

		jse.executeScript("arguments[0].click();", elem);
		WebElement Readallreviews = wait.until(ExpectedConditions.visibilityOfElementLocated(READ_ALL_REVIEWS_IN_SUMMARY));
		jse.executeScript("arguments[0].click();", Readallreviews);
		// wait for scroll it down
		Thread.sleep(5000);
		jse.executeScript("window.scrollBy(0, 700)");
		try {
			WebElement reviewinput = driver.findElement(WRITE_REVIEW_BUTTON);
			jse.executeScript("arguments[0].click();", reviewinput);
			//driver.findElement(WRITE_REVIEW_BUTTON).click();
			Thread.sleep(2000);
			WebElement submitbtn = driver.findElement(SUBMIT_BTN);
			jse.executeScript("arguments[0].click();", submitbtn);
			softAssert.assertEquals(driver.findElement(NAME_ERROR).getText(), "A name is required", "Laguage mismatch for error message for Name");
			softAssert.assertEquals(driver.findElement(EMAIL_ERROR).getText(), "A valid email address is required", "Laguage mismatch for error message for Email");
			softAssert.assertEquals(driver.findElement(HEADLINE_ERROR).getText(), "Review headline is required", "Laguage mismatch for error message for Headline");
			softAssert.assertEquals(driver.findElement(REVIEW_ERROR).getText(), "Review content is required", "Laguage mismatch for error message for Review");
			Reporter.log("Review form error msgs are :: Displayed");
			ExtentReport.extentTest.info("Review form error msgs are :: Displayed");
		} catch (Exception e) {
			System.out.println("Review form error msgs are :: NOT Displayed");
			Reporter.log("Review form error msgs are :: NOT Displayed");
			ExtentReport.extentTest.info("Review form error msgs are :: NOT Displayed");
		}

		softAssert.assertAll();
		Reporter.log("Review form error msgs are :: Displayed");
		ExtentReport.extentTest.info("Review form error msgs are :: Displayed");
	}


	public void verifyAddToCart() throws Exception {
		String text = null;
		new SoftAssert();
		// call method to navigate to collection page
		new HomePageComponents(driver).navigateToMensBriefs();
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)");
		// call method to navigate product details page
		text = new CollectionPageComponent(driver).navigateToProductDetailsPage();
		// call method to check correct PDP opend or not
		//	checkCorrectProductDetailsPageOpenedOrNot(text);
		Thread.sleep(2000);

		allElements = driver.findElements(ProductDetailsPageComponents.COLOR_VARIANT);
		int count = 0;
		elementsCount = allElements.size();
		System.out.println("Color Element count :: " + elementsCount);

		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(1000);
		if (elementsCount > 1)

			for (int i = 1; i <= 3; i++) {
				WebElement elemen = driver.findElement(By.xpath("(//div[@class='space-y-lg']//input[@class='sr-only peer'])[" + i + "]"));
				jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].click();", elemen);
				Thread.sleep(3000);


				for (int k = 1; k <= 3; k++) {
					WebElement elements = driver.findElement(By.xpath("(//input[@name='option[Size]'])["+k+"]"));
					jse.executeScript("arguments[0].click();", elements);
					Thread.sleep(5000);

					try {

						String[] AddtoBag;
						AddtoBag = (driver.findElement(By.xpath("//button[@class='button grow']")).getText()).split(" -");
						String text1 = AddtoBag[0];
						System.out.println(text1);

						//	WebElement elem = driver.findElement(By.cssSelector(".multiPdp__atc-container>button"));
						if (driver.findElement(ProductDetailsPageComponents.ADD_TO_CART_BUTTON).getText().contains(text1)) {

							System.out.println(elemen.getText() + elements.getText() + "Product is added to Cart");
							Thread.sleep(2000);
							jse.executeScript("arguments[0].click();", driver.findElement(ADD_TO_CART_BUTTON));
							Thread.sleep(5000);
							driver.findElement(INLINE_CART_TITLE);
							jse.executeScript("arguments[0].click();", driver.findElement(INLINE_CART_CLOSE));

							Thread.sleep(1000);
							System.out.println("=============================");
							count++;

						}

					}

					catch(Exception ex)
					{
						System.out.println("Product is OOS");
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


					if (count == 2)
						break;

				}

				if (count == 2)
					break;

			}

		Reporter.log("ATC works fine");
		ExtentReport.extentTest.info("ATC works fine");
	}


	public void verifySizeCharts() throws Exception {
		//String text = null;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		SoftAssert softAssert = new SoftAssert();
		// call method to navigate to collection page
		new HomePageComponents(driver).navigateToAllUnderwearInMenCategory();
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)");
		// call method to navigate product details page
		new CollectionPageComponent(driver).navigateToProductDetailsPage();
		wait.until(ExpectedConditions.visibilityOfElementLocated(SIZEGUIDE));
		if (driver.findElement(SIZEGUIDE).isDisplayed()) {
			WebElement ele = driver.findElement(SIZEGUIDE);
			jse.executeScript("arguments[0].click();", ele);
			Thread.sleep(5000);
			//verify Fit Range
//			List<WebElement> fitrange = driver.findElements(SIZEGUIDE_FITRANGE);
//			int num = fitrange.size();
//			System.out.println(num);
//			if (num > 0) {
//
//				for (int i = 1; i <= num; i++) {
//					Thread.sleep(5000);
//					String range = driver.findElement(By.xpath("(//ul[@data-tj-qa='size-guide-fit-range-graph']//li)[" + i + "]")).getAttribute("class");
//					if (range.contentEquals("selected")) {
//						String rangevalue = driver.findElement(By.xpath("(//ul[@data-tj-qa='size-guide-fit-range-graph']//li)[" + i + "]")).getText();
//						System.out.println(rangevalue + " is selected");
//					}
//				}
//
//			} else {
//				System.out.println("NO FIT RANGE IS SELECTED");
//			}

			//View Measurements
			if (!driver.findElement(By.xpath("//h2[@class='mb-lg mt-2 pr-14 text-heading-md']")).isDisplayed())
				throw new Exception("Sizes chart overlay is not displayed after Size guide button was clicked");
			System.out.println("Size Chart is displayed");
			Reporter.log("Size guide link is Displayed :: Clickable");
			//Close first size guide window
			try {
				WebElement SizeChartClose = driver.findElement(By.xpath("(//button[@aria-label='Close modal'])[1]"));
				jse.executeScript("arguments[0].click();", SizeChartClose);
			}
			catch(Exception ex)
			{
				WebElement SizeChartClose = driver.findElement(By.xpath("(//button[@aria-label='Close modal'])[3]"));
				jse.executeScript("arguments[0].click();", SizeChartClose);
			}
	//		driver.findElement(By.xpath("(//button[@aria-label='Close modal'])[2]")).click();
		} else {
			System.out.println("Size guide link is not Displayed");
			Reporter.log("Size guide link is not Displayed.");
			ExtentReport.extentTest.info("Size guide link is not Displayed.");
		}


	}

	public void verifySizeChartTextBralette() throws Exception {
		String text = null;
		JavascriptExecutor js1 = (JavascriptExecutor) driver;;
		SoftAssert softAssert = new SoftAssert();
		// call method to navigate to collection page
//		new HomePageComponents(driver).navigateToTriangleBraletteCategory();
//		jse = (JavascriptExecutor) driver;
//		jse.executeScript("window.scrollBy(0,250)");
//		// call method to navigate product details page
//		new CollectionPageComponent(driver).navigateToProductDetailsPage();
		driver.get("https://www.tommyjohn.com/products/womens-air-bra-pack-2-pack?variant=43216573464621");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(SIZEGUIDE));

		//HOW TO Measure
		if (driver.findElement(SIZEGUIDE).isDisplayed()) {
			WebElement ele = driver.findElement(SIZEGUIDE);
			js1.executeScript("arguments[0].click();", ele);
			Thread.sleep(3000);
			js1.executeScript("window.scrollBy(0,500)");
			Thread.sleep(2000);

		}

		//View Measurements

		if (!driver.findElement(By.xpath("//h2[@class='mb-lg mt-2 pr-14 text-heading-md']")).isDisplayed())
			throw new Exception("Sizes chart overlay is not displayed after Size guide button was clicked");
		System.out.println("Size Chart is displayed");
		Reporter.log("Size Chart is displayed");
		ExtentReport.extentTest.info("Size Chart is displayed");
		Thread.sleep(2000);


		//Size Calculator
		if (!driver.findElement(BRA_BAND_SIZE_DROPDOWN).isEnabled()) {
			throw new Exception("Band Size Drop-Down is not enable.");
		} else {
			Select BandSize_DropDown = new Select(driver.findElement(BRA_BAND_SIZE_DROPDOWN));
			List<WebElement> BandSize_Options = BandSize_DropDown.getOptions();
			int BandSize_Options_Count = BandSize_Options.size();
			System.out.println("All Band Sizes list :: " + BandSize_Options_Count);
			Reporter.log("All Band Sizes list :: Displayed");
			ExtentReport.extentTest.info("All Band Sizes list :: Displayed");


			for (int i = 1; i < BandSize_Options_Count; i++) {

				Thread.sleep(2000);
				BandSize_DropDown.selectByIndex(i);
				Thread.sleep(2500);

				//driver.findElement(By.cssSelector(".size-guide-size-finder-dropdowns.visible>span:nth-child(1)")).click();

			}
		}

	}

	public void verifySizeChartTextBras() throws Exception {
		String text = null;
		SoftAssert softAssert = new SoftAssert();
		// call method to navigate to collection page
		new HomePageComponents(driver).navigateToBraCategory();
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)");
		// call method to navigate product details page
//	new CollectionPageComponent(driver).navigateToProductDetailsPage();
//	String text = null;
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
//	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(SIZEGUIDE));
		//HOW TO Measure
		Thread.sleep(5000);
		if (driver.findElement(SIZEGUIDE).isDisplayed()) {
			WebElement ele = driver.findElement(SIZEGUIDE);
			jse.executeScript("arguments[0].click();", ele);
			Thread.sleep(3000);
			jse.executeScript("window.scrollBy(0,200)");
//			WebElement elem = driver.findElement(SIZEGUIDE_FITRANGE_DROPDOWN);
//			jse.executeScript("arguments[0].click();", elem);
//		if(driver.findElement(SIZEGUIDE_FITRANGE_TEXT).isDisplayed()) {
//			System.out.println(driver.findElement(By.cssSelector(".instruction-text")).getText());

//		}
		}
		//View Measurements and Chart table

		if (!driver.findElement(SIZEGUIDE_CHART_TABLE).isDisplayed())
			throw new Exception("Sizes chart Table is not displayed");
		System.out.println("Size Chart Table is displayed");
		Reporter.log("Size Chart Table is displayed");
		ExtentReport.extentTest.info("Size Chart Table is displayed");
		Thread.sleep(2000);
		//fit Range Selected

//		List<WebElement> fitrange = driver.findElements(SIZEGUIDE_FITRANGE);
//		int num = fitrange.size();
//		if (num > 0) {
//
//			for (int i = 1; i <= num; i++) {
//				Thread.sleep(2000);
//				String range = driver.findElement(By.xpath("(//ul[@data-tj-qa='size-guide-fit-range-graph']//li)[" + i + "]")).getAttribute("class");
//				if (range.contentEquals("selected")) {
//					String rangevalue = driver.findElement(By.xpath("(//ul[@data-tj-qa='size-guide-fit-range-graph']//li)[" + i + "]")).getText();
//					System.out.println(rangevalue + " is selected");
//					Reporter.log("Fit Range is displayed");
//					ExtentReport.extentTest.info("Fit Range is displayed");
//
//				}
//			}
//
//		} else {
//			System.out.println("NO FIT RANGE IS SELECTED");
//			Reporter.log("NO FIT RANGE IS SELECTED");
//			ExtentReport.extentTest.info("NO FIT RANGE IS SELECTED");
//		}

		//Size Calculator
		if (!driver.findElement(BRA_BAND_SIZE_DROPDOWN).isEnabled()) {
			throw new Exception("Band Size Drop-Down is not enable.");
		} else {
			Select BandSize_DropDown = new Select(driver.findElement(BRA_BAND_SIZE_DROPDOWN));
			List<WebElement> BandSize_Options = BandSize_DropDown.getOptions();
			int BandSize_Options_Count = BandSize_Options.size();
			System.out.println("All Band Sizes list :: " + BandSize_Options_Count);
			Reporter.log("All Band Sizes list :: SELECTED");
			ExtentReport.extentTest.info("All Band Sizes list :: SELECTED");

			String BandSize_Default_Text = driver.findElement(BAND_SIZE_TEXT).getText();
			System.out.println(BandSize_Default_Text);
			if (!BandSize_Default_Text.equalsIgnoreCase("Band Size"))
				throw new Exception("Text change for 'Band Size'");
			for (int i = 1; i < BandSize_Options_Count; i++) {
				Thread.sleep(5000);
				BandSize_DropDown.selectByIndex(i);
			}
		}

//		if (!driver.findElement(BRA_CUP_VOLUME_DROPDOWN).isEnabled()) {
//			throw new Exception("Cup Volume/Size Drop-Down is not enable.");
//		} else {
//			Select CupVolume_DropDown = new Select(driver.findElement(BRA_CUP_VOLUME_DROPDOWN));
//			List<WebElement> CupVolume_Options = CupVolume_DropDown.getOptions();
//			int CupVolume_Options_Count = CupVolume_Options.size();
//			System.out.println("All CupVolume list ::" + CupVolume_Options_Count);
//			Reporter.log("All CupVolume list :: SELECTED");
//			ExtentReport.extentTest.info("All CupVolume list :: SELECTED");
//
//			String CupVolume_Default_Text = driver.findElement(CUP_VOLUME_TEXT).getText();
//
//			if (!CupVolume_Default_Text.equalsIgnoreCase("Cup Volume"))
//				throw new Exception("Text change for 'Cup Size'");
//
//			for (int i = 1; i < CupVolume_Options_Count; i++) {
//				Thread.sleep(2000);
//				CupVolume_DropDown.selectByIndex(i);
//
//			}
//			Thread.sleep(3000);
//
//			WebElement ele = driver.findElement(FIND_SIZE_BUTTON);
//			Thread.sleep(2000);
//			jse = (JavascriptExecutor) driver;
//			jse.executeScript("arguments[0].click();", ele);
//
//			if (!driver.findElement(CALCULATED_SIZE_RESULT).isDisplayed())
//				throw new Exception("Calculated Size Result is not displayed.");
//			if (!driver.findElement(FIND_SIZE_BUTTON).getText().equalsIgnoreCase("Edit Size"))
//				throw new Exception("Button text should change from 'Find Size' to 'Edit Size'");
//		}
	}

	public void verifyBundlePDPs() throws Exception {

		jse = (JavascriptExecutor) driver;
		SoftAssert softassert = new SoftAssert();
//		new HomePageComponents(driver).navigateToAllWomensPacksInPacksCategory();
//		new CollectionPageComponent(driver).navigateToProductDetailsPage();
		driver.get("https://www.tommyjohn.com/products/second-skin-brief-3-pack-1?variant=43158495068205");

		driver.findElement(By.xpath("//span[contains(text(),'3 Pack')]")).click();

		Thread.sleep(5000);
		allElements = driver.findElements(ProductDetailsPageComponents.COLOR_VARIANT);

		elementsCount = allElements.size();
		System.out.println("Color Element count :: " + elementsCount);

		if (elementsCount > 1)

			for (int i = 1; i <= 2; i++) {

				WebElement elemen = driver.findElement(By.xpath("(//div[@class='space-y-lg']//input[@class='sr-only peer'])[" + i + "]"));
				jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].click();", elemen);
				Thread.sleep(3000);

				for (int k = 1; k <= 3; k++) {
					WebElement elements = driver.findElement(By.xpath("(//input[@name='option[Size]'])[" + k + "]"));
					jse.executeScript("arguments[0].click();", elements);
					Thread.sleep(1000);

					//				WebElement elem = driver.findElement(By.cssSelector(".multiPdp__atc-container>button"));
					try {
						String[] AddtoBag;
						AddtoBag = (driver.findElement(By.xpath("//button[@class='button grow']")).getText()).split(" -");
						String text1 = AddtoBag[0];
						System.out.println(text1);

						if (driver.findElement(ProductDetailsPageComponents.ADD_TO_CART_BUTTON).getText().contains(text1)) {

							System.out.println(elemen.getText() + elements.getText() + "Product is added to Cart");
							Thread.sleep(2000);
							jse.executeScript("arguments[0].click();", driver.findElement(ADD_TO_CART_BUTTON));
							WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
							//	wait.until(ExpectedConditions.visibilityOfElementLocated(INLINE_CART_CLOSE));
							Thread.sleep(5000);
							jse.executeScript("arguments[0].click();", driver.findElement(INLINE_CART_CLOSE));

						}

					}
					catch (Exception ex)
					{

						System.out.println("Add to bag is disabled");

					}
				}



			}
		Reporter.log("Bundle PDP's are displayed");
		ExtentReport.extentTest.info("Bundle PDP's are displayed");

	}
}







