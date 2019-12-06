package com.tommyjohn.automation.Components;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.tommyjohn.automation.PageLocators.CollectionPageLocator;
import com.tommyjohn.automation.PageLocators.CompareToolLocators;
import com.tommyjohn.automation.utils.CustomUtilities;

public class CompareToolComponent extends CompareToolLocators
{
	WebDriver driver;
	WebElement element;
	Properties properties = CustomUtilities.properties;
	SoftAssert softAssert = new SoftAssert();
	String allclasses ;
	String text;
	int ComparedItem;
	List<WebElement> allelements;
	int count;
	public JavascriptExecutor jse ;

	public CompareToolComponent(WebDriver driver)
	{
		this.driver=driver;
	}

	public void CompareToolOnMensUnderwearCollection() throws Exception
	{
		Actions action = new Actions(driver);
		Thread.sleep(3000);

		//Hover On product image on collection page
		element = driver.findElement(CollectionPageLocator.FIRST_PRODUCT_IMAGE);
		action.moveToElement(element).build().perform();
		Thread.sleep(3000);
		softAssert.assertTrue(driver.findElement(COMPARE_CTA).isDisplayed(), "Compare button is not displayed on hover");
		System.out.println("Compare CTA text is: "+driver.findElement(COMPARE_CTA).getText());
		softAssert.assertEquals(driver.findElement(COMPARE_CTA).getText(), "Compare","Text changed for Compare CTA button on collection page.");
		Reporter.log("Compare button is Displayed :: Correct.");

		ValidateCompareModal();

		ClearAll();
		softAssert.assertAll();
	}

	public void ClearAll() throws Exception
	{
		softAssert.assertEquals(driver.findElement(CLEAR_ALL).getText(), properties.getProperty("ClearAll"),"Text changed for clear all button.");
	    driver.findElement(CLEAR_ALL).click();
	    Thread.sleep(3000);
	  //Check comparison bar displayed or not
		element = driver.findElement(By.cssSelector("#shopify-section-comparison-tool > div"));
		allclasses = element.getAttribute("class");
		for(String a : allclasses.split(" "))
		{
			if(a.equals("active"))
				throw new Exception("Comparison bar is displayed after click on clear all button.");
			}
		
		//Check compare button
		element =driver.findElement(By.cssSelector("div.product-image-container.product-image-container--has-hover > div"));
		allclasses = element.getAttribute("class");
		for(String a : allclasses.split(" "))
		{
			if(a.equals("compare-active"))
				throw new Exception("Compare button is not de-selected after click on clear all button.");
		}
		Reporter.log("Clear All button is displayed :: Clickable.");
	}

	public void ValidateCompareModal() throws Exception
	{	
		Actions action = new Actions(driver);
		for(int i=1;i<=4;i++)
		{
			//Hover on Product image
			WebElement COMPARE_CTA = driver.findElement(By.cssSelector("div.mega-collection-grid.js-mega-collection-grid > div:nth-child(3) > div.product-item:nth-child("+i+")> div.product-image-container > div>button.product-grid-controls__button--compare"));
			WebElement PRODUCT_IMAGE = driver.findElement(By.cssSelector(".mega-collection-grid>.mega-collection-grid__row:nth-child(3)>div:nth-child("+i+")>div:nth-child(1)>a>img:nth-child(2)"));
			action.moveToElement(PRODUCT_IMAGE).build().perform();
			Thread.sleep(3000);
            System.out.println("Product image hovered.");
            
          
			//Click on compare btn
            jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].click();", COMPARE_CTA);
			System.out.println("Compare CTA Clicked.");
			Thread.sleep(3000);
			//Check no. of clicked Compare CTA
			allelements = driver.findElements(By.cssSelector(".product-image-container>div.compare-active"));
			count = allelements.size();
			System.out.println("Total selected compare button is: "+count);

			//Check comparison bar displayed or not
			element = driver.findElement(By.cssSelector("#shopify-section-comparison-tool > div"));
			allclasses = element.getAttribute("class");
			for(String a : allclasses.split(" "))
			{
				if(a.equals("active"))
					System.out.println("Comparison bar is displayed.");
			}


			softAssert.assertTrue(driver.findElement(CLEAR_ALL).isEnabled(), "Clear all button is not enabled.");
			
			//check comparison bar status(no. of Compare Items)
			softAssert.assertTrue(driver.findElement(COMPARE_BAR_STATUS).isDisplayed(), "Comparison bar status is not displayed.");
			System.out.println("compare bar status text is: "+driver.findElement(COMPARE_BAR_STATUS).getText());
			text = driver.findElement(COMPARED_ITEM_NUMBER).getText();
			String[] textsplit = text.split(" ");
			String text1 = textsplit[0];
			ComparedItem = Integer.parseInt(text1);
			softAssert.assertEquals(ComparedItem,count,"Number of compared item is not equal to selected compare CTA.");


			if(ComparedItem==1)
			{
				softAssert.assertTrue(!driver.findElement(COMPARE_NOW).isEnabled(), "Compare Now button is enabled for one item.");
			    System.out.println("Compare Now button is disabled for one item.");
			}
			System.out.println("Clear all button text is: "+driver.findElement(CLEAR_ALL).getText());
			softAssert.assertEquals(driver.findElement(CLEAR_ALL).getText(), properties.getProperty("ClearAll"),"Text changed for clear all button.");
			System.out.println("Compare button text is: "+driver.findElement(COMPARE_NOW).getText());
			softAssert.assertEquals(driver.findElement(COMPARE_NOW).getText(), properties.getProperty("CompareNow"),"Text changed for Compare Now button.");

			if (ComparedItem>1) {
				softAssert.assertTrue(driver.findElement(COMPARE_NOW).isEnabled(), "Compare Now button is not enabled for more than one item.");
			    System.out.println("Compare Now button is enabled for "+i+" items.");
			    
			    //Click on CompareNow button
			    driver.findElement(COMPARE_NOW).click();
			    Thread.sleep(3000);
			    element=driver.findElement(COMPARE_MODAL);
			    allclasses=element.getAttribute("class");
			    System.out.println("allclasses are: "+allclasses);
			    for(String a:allclasses.split(" "))
			    {
			    	if(a.equals("active"))
			    	{
			    		System.out.println("Modal overlay is displayed.");
			    		softAssert.assertEquals(driver.findElement(COMPARE_ITEMS_TITLE).getText(), properties.getProperty("CompareItems"),"Text changed for 'Compare Items' title in modal-overlay.");
			    		//Close button
			    		softAssert.assertTrue(driver.findElement(MODAL_CLOSE).isDisplayed(), "Modal close button is not displayed.");
			    		
			    		for(int j=1;j<=ComparedItem;j++)
			    		{
			    		WebElement PRODUCT_IMG_IN_OVERLAY = driver.findElement(By.cssSelector("#shopify-section-comparison-tool > section > div > div > div:nth-child(1) > div:nth-child("+j+") > div > div > a > img"));	
			    		WebElement COMPARED_PRODUCT_TITLE = driver.findElement(By.cssSelector("#shopify-section-comparison-tool > section > div > div > div:nth-child(2) > div:nth-child("+j+") >p>a"));
			    		WebElement REVIEWS_TITLE_IN_OVERLAY = driver.findElement(By.cssSelector("#shopify-section-comparison-tool > section > div > div > div:nth-child(2) > div:nth-child("+j+") >p:nth-child(3)"));
			    		WebElement COLLECTION_TITLE_IN_OVERLAY = driver.findElement(By.cssSelector("#shopify-section-comparison-tool > section > div > div > div:nth-child(3) > div:nth-child("+j+")>h4"));
			    		WebElement COLLECTION_SUBTITLE_IN_OVERLAY = driver.findElement(By.cssSelector("#shopify-section-comparison-tool > section > div > div > div:nth-child(3) > div:nth-child("+j+")>p.comparison-grid__item__subtitle"));
			    		WebElement COLLECTION_DETAILS_IN_OVERLAY = driver.findElement(By.cssSelector("#shopify-section-comparison-tool > section > div > div > div:nth-child(3) > div:nth-child("+j+")>p.comparison-grid__item__copy"));		
			    		WebElement FEBRICATION_TITLE =driver.findElement(By.cssSelector("#shopify-section-comparison-tool > section > div > div > div:nth-child(4) > div:nth-child("+j+")>h4"));
			    		WebElement FEBRICATION_DETAILS=driver.findElement(By.cssSelector("#shopify-section-comparison-tool > section > div > div > div:nth-child(4) > div:nth-child("+j+")>ul"));
			    		WebElement INSEAM_LENGTH_TITLE =driver.findElement(By.cssSelector("#shopify-section-comparison-tool > section > div > div > div:nth-child(5) > div:nth-child("+j+") > h4"));
			    		WebElement INSEAM_LENGTH_DETAILS =driver.findElement(By.cssSelector("#shopify-section-comparison-tool > section > div > div > div:nth-child(5) > div:nth-child("+j+") > p"));
			    		WebElement FOUR_WAY_STRETCH_TITLE =driver.findElement(By.cssSelector("#shopify-section-comparison-tool > section > div > div > div:nth-child(6) > div:nth-child("+j+") > h4"));
			    		WebElement FOUR_WAY_STRETCH_DETAIL =driver.findElement(By.cssSelector("#shopify-section-comparison-tool > section > div > div > div:nth-child(6) > div:nth-child("+j+") > p"));
			    		WebElement PURPOSE_TITLE =driver.findElement(By.cssSelector("#shopify-section-comparison-tool > section > div > div > div:nth-child(7) > div:nth-child("+j+") > h4"));
			    		WebElement PURPOSE_DETAIL =driver.findElement(By.cssSelector("#shopify-section-comparison-tool > section > div > div > div:nth-child(7) > div:nth-child("+j+") > p"));
			    		WebElement STAY_PUT_WAISTBAND_TITLE =driver.findElement(By.cssSelector("#shopify-section-comparison-tool > section > div > div > div:nth-child(8) > div:nth-child("+j+")>h4"));
			    		WebElement STAY_PUT_WAISTBAND_DETAIL =driver.findElement(By.cssSelector("#shopify-section-comparison-tool > section > div > div > div:nth-child(8) > div:nth-child("+j+")>p"));
			    		WebElement QUICK_DRAW_FLY_TITLE =driver.findElement(By.cssSelector("#shopify-section-comparison-tool > section > div > div > div:nth-child(9) > div:nth-child("+j+")>h4"));
			    		WebElement QUICK_DRAW_FLY_DETAIL =driver.findElement(By.cssSelector("#shopify-section-comparison-tool > section > div > div > div:nth-child(9) > div:nth-child("+j+")>p"));
			    		WebElement CONTOUR_POUCH_TITLE =driver.findElement(By.cssSelector("#shopify-section-comparison-tool > section > div > div > div:nth-child(10) > div:nth-child("+j+")>h4"));
			    		WebElement CONTOUR_POUCH_DETAIL =driver.findElement(By.cssSelector("#shopify-section-comparison-tool > section > div > div > div:nth-child(10) > div:nth-child("+j+")>p"));

			    		
			    		//Product Image
			    		softAssert.assertTrue(PRODUCT_IMG_IN_OVERLAY.isDisplayed(), "Product image "+j+"  is not displayed in overlay for "+ComparedItem+" items.");
			    		allelements = driver.findElements(TOTAL_IMG_IN_OVERLAY);
			    		count = allelements.size();
			    		softAssert.assertEquals(count,ComparedItem,"product images count is not equal to selected compared items." );
			    		
			    		//Product title
			    		/*System.out.println("Product title is: "+COMPARED_PRODUCT_TITLE.getText());
			    		softAssert.assertTrue(COMPARED_PRODUCT_TITLE.isDisplayed(), "Product title "+j+" is not displayed in overlay for "+ComparedItem+" items.");
			    		*/
			    		//Reviews title
			    		softAssert.assertEquals(REVIEWS_TITLE_IN_OVERLAY.getText(), properties.getProperty("Reviews"),"Text changed for Reviews title "+j+"  in overlay for "+ComparedItem+" items.");
			    		
			    		//Check Collection details
			    		softAssert.assertEquals(COLLECTION_TITLE_IN_OVERLAY.getText(), properties.getProperty("Collection"),"Text changed for Collection title "+j+" in overlay for "+ComparedItem+" items.");
			    		softAssert.assertTrue(COLLECTION_SUBTITLE_IN_OVERLAY.isDisplayed(), "Collection Sub-title "+j+" is not displayed in overlay for "+ComparedItem+" items.");
			    		softAssert.assertTrue(COLLECTION_DETAILS_IN_OVERLAY.isDisplayed(), "Collection description "+j+" is not displayed in overlay for "+ComparedItem+" items.");
			    		
			    		//Check Fabrication details
			    		softAssert.assertEquals(FEBRICATION_TITLE.getText(), properties.getProperty("Fabrication"),"Text changed for Febrication title "+j+" in overlay for "+ComparedItem+" items.");
			    		softAssert.assertTrue(FEBRICATION_DETAILS.isDisplayed(), "Fabrication description "+j+" is not displayed in overlay for "+ComparedItem+" items.");
			    		
			    		//Check Inseam Length details
			    		System.out.println("Inseam title is: "+INSEAM_LENGTH_TITLE.getText());
			    		softAssert.assertEquals(INSEAM_LENGTH_TITLE.getText(), properties.getProperty("InseamLengthTitle"),"Text changed for Inseam Length title "+j+" in overlay for "+ComparedItem+" items.");
			    		softAssert.assertTrue(INSEAM_LENGTH_DETAILS.isDisplayed(), "Inseam Length description "+j+" is not displayed in overlay for "+ComparedItem+" items.");
			    
			    		//Check 4-way Stretch details
			    		System.out.println("4-way title is: "+FOUR_WAY_STRETCH_TITLE.getText());
			    		softAssert.assertEquals(FOUR_WAY_STRETCH_TITLE.getText(), properties.getProperty("4-Way_Stretch"),"Text changed for 4-way Stretch title "+j+" in overlay for "+ComparedItem+" items.");
			    		softAssert.assertTrue(FOUR_WAY_STRETCH_DETAIL.isDisplayed(), "4-way Stretch description "+j+" is not displayed in overlay for "+ComparedItem+" items.");
			    
			    		//Check Purpose details
			    		System.out.println("purpose title is: "+PURPOSE_TITLE.getText());
			    		softAssert.assertEquals(PURPOSE_TITLE.getText(), properties.getProperty("Purpose"),"Text changed for Purpose title "+j+" in overlay for "+ComparedItem+" items.");
			    		softAssert.assertTrue(PURPOSE_DETAIL.isDisplayed(), "Purpose description "+j+" is not displayed in overlay for "+ComparedItem+" items.");

			    		//Check Stay-put Waistband details
			    		System.out.println("Stay put waistband title is: "+STAY_PUT_WAISTBAND_TITLE.getText());
			    		softAssert.assertEquals(STAY_PUT_WAISTBAND_TITLE.getText(), properties.getProperty("Stay-Put_Waistband"),"Text changed for Stay-Put_Waistband title "+j+" in overlay for "+ComparedItem+" items.");
			    		softAssert.assertTrue(STAY_PUT_WAISTBAND_DETAIL.isDisplayed(), "Stay-Put_Waistband description "+j+" is not displayed in overlay for "+ComparedItem+" items.");
			    		
			    		//Check Quick Draw Fly details
			    		System.out.println("Quick draw fly title is: "+QUICK_DRAW_FLY_TITLE.getText());
			    		softAssert.assertEquals(QUICK_DRAW_FLY_TITLE.getText(), properties.getProperty("QuickDrawFly"),"Text changed for Quick Draw Fly title "+j+" in overlay for "+ComparedItem+" items.");
			    		softAssert.assertTrue(QUICK_DRAW_FLY_DETAIL.isDisplayed(), "Quick Draw Fly description "+j+" is not displayed in overlay for "+ComparedItem+" items.");
			    		
			    		//Check Contour Pouch details
			    		System.out.println("Contour pouch title is: "+CONTOUR_POUCH_TITLE.getText());
			    		softAssert.assertEquals(CONTOUR_POUCH_TITLE.getText(), properties.getProperty("ContourPouch"),"Text changed for Contour Pouch title "+j+" in overlay for "+ComparedItem+" items.");
			    		softAssert.assertTrue(CONTOUR_POUCH_DETAIL.isDisplayed(), "Contour Pouch description "+j+" is not displayed in overlay for "+ComparedItem+" items.");

			    		}
			    		
			    		driver.findElement(MODAL_CLOSE).click();
			    		Thread.sleep(3000);
			    		
			    	}
			    	
			    }
			}

		}
		Reporter.log("Compare button is Displayed :: Clickable.");
		Reporter.log("Comparison bar is displayed.");
		Reporter.log("Comparison bar status is displayed :: Correct");
		Reporter.log("Compare Now button is Displayed :: clickable");
		Reporter.log("Compare Modal overlay is displayed :: Correct");
		Reporter.log("'Compare Items' title in modal-overlay is displayed :: Correct.");
		Reporter.log("Modal Close button is displayed :: Clickable.");
		Reporter.log("Product images are displayed in modal overlay.");
		softAssert.assertAll();
	}
}
