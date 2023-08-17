package com.tommyjohn.automation.Components;

import java.time.Duration;
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
import org.testng.asserts.SoftAssert;

import com.base.ExtentReport;
import com.tommyjohn.automation.PageLocators.SanityModuleLocators;

import com.tommyjohn.automation.utils.CustomUtilities;

public class SanityModuleComponents extends SanityModuleLocators{

	
	public WebDriver driver;
	public Actions action;
	WebElement element;
	WebElement element1;
	Properties properties = CustomUtilities.properties;
	WebDriverWait wait;
	int elementsCount;
	public JavascriptExecutor jse;
	SoftAssert softassert = new SoftAssert();
	WebElement Rec_mail;
	String GC_Price_PDP;
	String Expected_text;
	String Delivery_date;
	String newDelivery_Date;
	String invalidemail = "test";
		
	String text;
	List<WebElement> allElements;

	
	public SanityModuleComponents(WebDriver driver) {
		this.driver = driver;
	}
	
	public void verifyHeroModule() throws Exception {
		
		// call method to navigate to collection page
		new HomePageComponents(driver).navigateToCareersPage();
		Thread.sleep(8000);
		//JavascriptExecutor jse = (JavascriptExecutor)driver;
		//jse.executeScript("window.scrollBy(0,250)");
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(HERO_IMAGE_1, 0));
		if(driver.findElement(HERO_IMAGE_1).isDisplayed()) 
			{
				Thread.sleep(3000);
				WebElement heroimg = driver.findElement(HERO_IMAGE_1); 
				org.openqa.selenium.Dimension dim = heroimg.getSize();
				
				System.out.println(dim);
				int width=	dim.width;
				
				System.out.println(width);
				
				//margin validations
				if(width>=1250)  {
					Reporter.log("Hero Module annotations are correct");
					ExtentReport.extentTest.info("Hero Module annotations are correct");
					System.out.println("Hero Module Tile annotations are correct");
				}
				else {
					System.out.println("Hero Module annotations are not correct");
					ExtentReport.extentTest.info("Hero Module annotations are not correct");
					throw new Exception("Hero Module annotations are not correct");
				}
			}
				
		//Text Validations
		if(driver.findElement(HERO_IMAGE_TEXT).isDisplayed())
			{
				Thread.sleep(3000);
				WebElement herotext = driver.findElement(HERO_IMAGE_TEXT); 
				String fontsize = herotext.getCssValue("font-size");
				System.out.println(fontsize);
				
				if (!fontsize.contentEquals("40px")) {
					System.out.println("Hero Module Text annotations are correct");
				}
				
			}
		new HomePageComponents(driver).navigateToAboutUsPage();
		 
		WebElement herocta = driver.findElement(HERO_CTA);
		 
		if(herocta.isEnabled())
		{
			softassert.assertEquals(herocta.getCssValue("font-size"),"16px" , "Font-size is not correct");
			softassert.assertEquals(herocta.getCssValue("height"),"50px" , "Font-size is not correct");
			softassert.assertEquals(herocta.getCssValue("justify-content"),"center" , "Font-size is not correct");
			softassert.assertEquals(herocta.getCssValue("line-height"),"22.4px" , "Font-size is not correct");
		}
		softassert.assertAll();
	}
	
	public void verifyFixedBannerModule() throws Exception {
		// call method to navigate to collection page
		new HomePageComponents(driver).navigateToCareersPage();
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		Thread.sleep(3000);
		jse.executeScript("window.scrollBy(0,600)");
//		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
//		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(FIXED_BANNER_IMG, 0));
		if(driver.findElement(FIXED_BANNER_IMG).isDisplayed()) 
			{
				Thread.sleep(3000);
				WebElement fxdimg = driver.findElement(FIXED_BANNER_IMG); 
				org.openqa.selenium.Dimension dim = fxdimg.getSize();
				
				System.out.println(dim);
				int width=	dim.width;
				
				System.out.println(width);
				
				//margin validations
				if(width>=1184)  {
					Reporter.log("Fixed Banner Module annotations are correct");
					ExtentReport.extentTest.info("Fixed Banner Module annotations are correct");
					System.out.println("Fixed Banner Module annotations are correct");
				}
				else {
					System.out.println("Fixed Banner Module annotations are not correct");
					ExtentReport.extentTest.info("Fixed Banner Module annotations are not correct");
					throw new Exception("Fixed Banner Module annotations are not correct");
				}
			}
		softassert.assertAll();
	}
	
	
	
	public void verifyThreeTileModule() throws Exception {
		
		// call method to navigate to collection page
				new HomePageComponents(driver).navigateToCareersPage();
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				jse.executeScript("window.scrollBy(0,1000)");
				if(driver.findElement(THREE_TILE_MODULE).isDisplayed()) 
					{
						Thread.sleep(3000);
						//calculating total marging of module
						WebElement tilesimg = driver.findElement(THREE_TILE_MODULE); 
						org.openqa.selenium.Dimension dim = tilesimg.getSize();
						System.out.println(dim);
						int width=	dim.width;
						System.out.println(width);
						
						Thread.sleep(3000);
						WebElement module = driver.findElement(THREE_TILE_MODULE); 
						WebElement tileimg1 = driver.findElement(THREE_TILE_IMG_1); 
						WebElement tileimg2 = driver.findElement(THREE_TILE_IMG_2); 
						//softassert.assertTrue(module.getCssValue("margin-right"), "Overall Right margin is not correct");
						//softassert.assertEquals(module.getCssValue("margin-left"), "82.5px" , "Overall Left margin is not correct");
						
						//checking individual tiles are present
						softassert.assertTrue(driver.findElement(THREE_TILE_IMG_1).isDisplayed(), "Tile 1 is displayed");
						softassert.assertTrue(driver.findElement(THREE_TILE_IMG_2).isDisplayed(), "Tile 2 is displayed");
						softassert.assertTrue(driver.findElement(THREE_TILE_IMG_3).isDisplayed(), "Tile 3 is displayed");
						Thread.sleep(3000);
						
						//checking individual margins of tiles
						softassert.assertEquals(tileimg1.getCssValue("margin-right"), "24px" , "Right margin for Img 1 is not correct");
						softassert.assertEquals(tileimg2.getCssValue("margin-right"), "24px" , "Right margin For Img 2 is not correct");
					}
				softassert.assertAll();
	}	
	 public void verifyImagecarousel() throws Exception {
			Actions action = new Actions(driver);
			// call method to navigate to collection page
			new HomePageComponents(driver).navigateToCareersPage();
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("window.scrollBy(0,1800)");
			Thread.sleep(3000);
			WebElement img1 = driver.findElement(IMG_CAROUSEL_1);
			WebElement img2 = driver.findElement(IMG_CAROUSEL_2);
			Thread.sleep(3000);
			WebElement slide = driver.findElement(SLIDE_CTRL);
			if(driver.findElement(IMG_CAROUSEL).isDisplayed()) 
			{
				
				softassert.assertEquals(img1.getCssValue("margin-right"), "10px" , "Right margin for Img1 is not correct");
				softassert.assertEquals(img2.getCssValue("margin-left"), "10px" , "Left margin for Img2 is not correct");
			}
						
			Thread.sleep(3000);
			if(slide.isDisplayed()) 	
			{	
				action.moveToElement(slide).perform();
				action.click().perform();
				jse.executeScript("arguments[0].click();", slide);
				Thread.sleep(3000);
				WebElement img3 = driver.findElement(IMG_CAROUSEL_3);
				WebElement img4 = driver.findElement(IMG_CAROUSEL_4);
				System.out.println(img3.getCssValue("margin-right"));
				System.out.println(img4.getCssValue("margin-left"));
				softassert.assertEquals(img3.getCssValue("margin-right"), "10px" , "Right margin for Img3 is not correct");
				softassert.assertEquals(img4.getCssValue("margin-left"), "10px" , "Left margin for Img4 is not correct");
			}
			softassert.assertAll();
		 }
	 
	 public void verifyFixedBannerModuleLinks() throws Exception {
			// call method to navigate to collection page
			new HomePageComponents(driver).navigateToCareersPage();
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			Thread.sleep(3000);
			jse.executeScript("window.scrollBy(0,2100)");
//			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
//			wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(FIXED_BANNER_IMG, 0));
			if(driver.findElement(GPTW_FIXED_BANNER).isDisplayed()) 
				{
					Thread.sleep(3000);
					WebElement fxdimg = driver.findElement(GPTW_FIXED_BANNER_IMG); 
					org.openqa.selenium.Dimension dim = fxdimg.getSize();
					
					System.out.println(dim);
					int width=	dim.width;
					
					System.out.println(width);
					
					//margin validations
					if(width>=1184)  {
						Reporter.log("Fixed Banner Module annotations are correct");
						ExtentReport.extentTest.info("Fixed Banner Module annotations are correct");
						System.out.println("Fixed Banner Module annotations are correct");
					}
					else {
						System.out.println("Fixed Banner Module annotations are not correct");
						ExtentReport.extentTest.info("Fixed Banner Module annotations are not correct");
						throw new Exception("Fixed Banner Module annotations are not correct");
					}
				}
			if(driver.findElement(GPTW_FIXED_BANNER_LINK).isDisplayed()) 
			{
				softassert.assertTrue(driver.findElement(GPTW_FIXED_BANNER_LINK).isEnabled(), "FIXER BANNER LINK :NOT CLICKABLE:");
							
				Thread.sleep(3000);
				WebElement linktext = driver.findElement(GPTW_FIXED_BANNER_LINK); 
				softassert.assertEquals(linktext.getCssValue("font-size"), "16px" , "Font size is not correct");
				softassert.assertEquals(linktext.getCssValue("justify-content"), "center" , "Alignment is not correct");
				WebElement textlink = driver.findElement(GPTW_FIXED_BANNER_LINK);
				Thread.sleep(3000);
				
				if(textlink.isEnabled()) 
				{
					System.out.println("CTA is clickable");
					
				}
			
			driver.navigate().back();
			}
			softassert.assertAll();
		}
	 
	 public void verifySideBySideModule() throws Exception {
		 
				 new HomePageComponents(driver).navigateToAboutUsPage();
				 JavascriptExecutor jse = (JavascriptExecutor)driver;
				 Thread.sleep(3000);
				 jse.executeScript("window.scrollBy(0,1000)");
				 
				 WebElement img1 = driver.findElement(SIDE_BY_SIDE_IMG1);
				 WebElement img2 = driver.findElement(SIDE_BY_SIDE_IMG2);
				 WebElement cta = driver.findElement(SIDE_BY_SIDE_CTA);
				// WebElement text = driver.findElement(SIDE_BY_SIDE_TEXT);
				 Thread.sleep(3000);
				 if(driver.findElement(SIDE_BY_SIDE_IMG1).isDisplayed()) 
					{
						Thread.sleep(3000);
						 
						org.openqa.selenium.Dimension dim1 = img1.getSize();
						org.openqa.selenium.Dimension dim2 = img2.getSize();
						
						System.out.println(dim1);
						int width1=	dim1.width;
						System.out.println("Width of Image 1: " +width1);
						
						System.out.println(dim2);
						int width2=	dim2.width;
						System.out.println("Width of Image 1: " +width2);
						
						//margin validations
						if(width1==580)  {
							Reporter.log("SideBySide annotations for Img 1 are correct");
							ExtentReport.extentTest.info("SideBySide annotations for Img 1 are correct");
							System.out.println("SideBySide annotations for Img 1 are correct");
						}
						if(width2==580)  {
							Reporter.log("SideBySide annotations for Img 2 are correct");
							ExtentReport.extentTest.info("SideBySide annotations for Img 2 are correct");
							System.out.println("SideBySide annotations for Img 2 are correct");
						}
					}
				if(driver.findElement(SIDE_BY_SIDE).isDisplayed()) 
				{
					
					softassert.assertEquals(img1.getCssValue("margin-right"), "12px" , "Right margin is not correct");
					softassert.assertEquals(img2.getCssValue("margin-left"), "12px" , "Left margin is not correct");
						
				}
				
				if(driver.findElement(SIDE_BY_SIDE_CTA).isDisplayed()) 
				{
					softassert.assertEquals(cta.getCssValue("font-size"), "16px" , "Font Size is not correct");
					softassert.assertEquals(cta.getCssValue("height"), "50px" , "Line Height is not correct");
					String textlink = driver.findElement(SIDE_BY_SIDE_CTA).getAttribute("href");
					jse.executeScript("arguments[0].click();", cta);
					String url = driver.getCurrentUrl();
					if(url.contains(textlink)) 
						{
							System.out.println("CTA is clickable");
							
						}
					
					driver.navigate().back();
				}
				Thread.sleep(3000);
				if(driver.findElement(SIDE_BY_SIDE_TEXT).isDisplayed()) 
				{
					WebElement text = driver.findElement(SIDE_BY_SIDE_TEXT);
					System.out.println(text.getCssValue("line-height"));
					softassert.assertEquals(text.getCssValue("line-height"), "24px", "Line height is not correct");
					softassert.assertEquals(text.getCssValue("font-size"), "24px" , "Font Size is not correct");
					softassert.assertEquals(text.getCssValue("font-family"), "Basetica-regular" , "Font-family is not correct");	
					
				}
		 
		 softassert.assertAll();
	 }
}
