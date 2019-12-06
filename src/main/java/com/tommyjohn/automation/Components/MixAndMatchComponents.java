package com.tommyjohn.automation.Components;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.tommyjohn.automation.PageLocators.FlyCartPageLocator;
import com.tommyjohn.automation.PageLocators.MixAndMatchLocators;
import com.tommyjohn.automation.PageLocators.ProductDetailsPageLocators;
import com.tommyjohn.automation.utils.CustomUtilities;

public class MixAndMatchComponents extends MixAndMatchLocators
{
	WebDriver driver;
	SoftAssert softassert=new SoftAssert();
	public JavascriptExecutor executore = (JavascriptExecutor)driver;
	Properties properties = CustomUtilities.properties;
	WebElement element;
	List<WebElement> allElements;
	String text= null;
	int elementsCount;
	String Top_center_prodTitle;
	String Bottom_center_prodTitle;
	boolean flag=false;
	int total_slide_section;

	public MixAndMatchComponents(WebDriver driver)
	{
		this.driver=driver;
	}

	public void Validate_MixAndMatch_Page() throws Exception
	{
		new HomePageComponents(driver).navigateToMixAndMatchInBrasCategory();

		softassert.assertTrue(driver.findElement(MIX_MATCH_HEADER).isDisplayed(), "Mix & Match Heading is not displayed.");
		System.out.println("Mix & Match heading is: "+driver.findElement(MIX_MATCH_HEADER).getText());
		softassert.assertEquals(driver.findElement(MIX_MATCH_HEADER).getText(), properties.getProperty("Mix&MatchHeading"),"Text changed for Mix & Match Heading.");
		Reporter.log("Mix & Match Heading is Displayed :: Correct.");

		//validate ATC btn Without selecting any size(On page load)
		softassert.assertTrue(driver.findElement(ATC_CTA).isDisplayed(), "ATC button is not displayed.");
		System.out.println("ATC button text is: "+driver.findElement(SELECT_SIZES_BTN_TEXT).getText());
		softassert.assertEquals(driver.findElement(SELECT_SIZES_BTN_TEXT).getText(), properties.getProperty("SelectSizes"),"Text changed for disabled ATC button.");
		Reporter.log("Disabled ATC button is Displayed :: Correct.");

		//validate Left slide section
		validate_slidesection();
		//validate Right rail details
		validateRight_rail();
		//Select available size
		SelectSize();

		//validate ATC btn after selecting sizes
		String price1 = driver.findElement(RIGHT_RAIL_TOP_PRODUCT_PRICE).getText();
		String price2 = driver.findElement(RIGHT_RAIL_BOTTOM_PRODUCT_PRICE).getText();

		String[] P1 = price1.split("\\$");
		String top_price = P1[P1.length-1];
		double top_price1 =Float.parseFloat(top_price);

		String[] P2 = price2.split("\\$");
		String bottom_price = P2[P2.length-1];
		double bottom_price1 =Float.parseFloat(bottom_price);

		double final_price = top_price1+bottom_price1;
		String final_price1 = String.format("%.2f",final_price );
		System.out.println("total price is: "+final_price1);

		text = driver.findElement(GET_BOTH_CTA).getText();
		String Expected = properties.getProperty("GetBothText")+final_price1;
		softassert.assertEquals(text, Expected,"Wrong price text displayed on ATC CTA.");
		Reporter.log("Enabled ATC button is displayed :: Price is correct.");
		//Add to cart
		ATC();
               Thread.sleep(3000);
               new HomePageComponents(driver).navigateToMixAndMatchInBrasCategory();
		
		//Check product title link
		Check_productLink();
		softassert.assertAll();

	}

	public void validate_slidesection() throws Exception
	{
		//Validate Top Slide
		softassert.assertTrue(driver.findElement(TOP_SLIDE_SLICK_NEXT).isDisplayed(), "Top slider slick next arrow is not displayed.");
		softassert.assertTrue(driver.findElement(TOP_SLIDE_SLICK_PREV).isDisplayed(), "Top slider slick prev arrow is not displayed.");
		text = driver.findElement(TOP_SLIDE_CENTER_PRODUCT_NAME).getText();
		driver.findElement(TOP_SLIDE_SLICK_NEXT).click();
		Thread.sleep(3000);
		String actual = driver.findElement(TOP_SLIDE_CENTER_PRODUCT_NAME).getText();
		if(actual.equalsIgnoreCase(text))
			throw new Exception("Slick next arrow is not working.");

		driver.findElement(TOP_SLIDE_SLICK_PREV).click();
		Thread.sleep(3000);
		actual = driver.findElement(TOP_SLIDE_CENTER_PRODUCT_NAME).getText();
		if(!actual.equalsIgnoreCase(text))
			throw new Exception("Slick prev arrow is not working.");

		Reporter.log("Top slider slick arrows are displayed :: Clickable");
		softassert.assertTrue(driver.findElement(TOP_SLIDE_CENTER_PRODUCT_IMG).isDisplayed(), "Top slider center product image is not displayed.");
		softassert.assertTrue(driver.findElement(TOP_SLIDE_CENTER_PRODUCT_NAME).isDisplayed(), "Top slider center product title is not displayed.");
		Reporter.log("Top slide center product image and title is displayed :: correct.");

		//Validate Bottom Slide
		softassert.assertTrue(driver.findElement(BOTTOM_SLIDE_SLICK_NEXT).isDisplayed(), "Bottom slider slick next arrow is not displayed.");
		softassert.assertTrue(driver.findElement(BOTTOM_SLIDE_SLICK_PREV).isDisplayed(), "Bottom slider slick prev arrow is not displayed.");
		text = driver.findElement(BOTTOM_SLIDE_CENTER_PRODUCT_NAME).getText();
		driver.findElement(BOTTOM_SLIDE_SLICK_NEXT).click();
		Thread.sleep(3000);
		actual = driver.findElement(BOTTOM_SLIDE_CENTER_PRODUCT_NAME).getText();
		if(actual.equalsIgnoreCase(text))
			throw new Exception("Bottom Slick next arrow is not working.");

		driver.findElement(BOTTOM_SLIDE_SLICK_PREV).click();
		Thread.sleep(3000);
		actual = driver.findElement(BOTTOM_SLIDE_CENTER_PRODUCT_NAME).getText();
		if(!actual.equalsIgnoreCase(text))
			throw new Exception("Bottom Slick prev arrow is not working.");

		Reporter.log("Bottom slider slick arrows are displayed :: Clickable");
		softassert.assertTrue(driver.findElement(BOTTOM_SLIDE_CENTER_PRODUCT_IMG).isDisplayed(), "Bottom slider center product image is not displayed.");
		softassert.assertTrue(driver.findElement(BOTTOM_SLIDE_CENTER_PRODUCT_NAME).isDisplayed(), "Bottom slider center product title is not displayed.");
		Reporter.log("Bottom slide center product image and title is displayed :: correct.");
		softassert.assertAll();
	}

	public void SelectSize() throws Exception
	{
		// Select first size between available sizes
		for(int i=2;i<=3;i++){
			WebElement SIZE_DROP_DOWN =driver.findElement(By.cssSelector(".mixmatch-container>div:nth-child("+i+") > div.mixmatch_selection > div > div > div.option-dropdown__section >.option-dropdown__options-wrapper:nth-child(2)>div:nth-child(1)>div>a"));
			List<WebElement>ALL_SIZE_LIST = driver.findElements(By.cssSelector(".mixmatch-container>div:nth-child("+i+") > div.mixmatch_selection > div > div > div.option-dropdown__section > div.option-dropdown__options-wrapper > div:nth-child(5) > div > ul>li"));

			int elementsCount = ALL_SIZE_LIST.size();
			System.out.println("Top all sizes list :: "+ elementsCount);

			for(int j=1;j<=elementsCount;j++) {
				flag = false;
				System.out.println("Click on size drop-down link.");
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				jse.executeScript("arguments[0].click();", SIZE_DROP_DOWN);
				//SIZE_DROP_DOWN.click();
				Thread.sleep(3000);
				element = driver.findElement(By.cssSelector(".mixmatch-container>div:nth-child("+i+") > div.mixmatch_selection > div > div > div.option-dropdown__section >.option-dropdown__options-wrapper:nth-child(2)>div:nth-child(1)>div>ul>li:nth-child("+j+")>div"));
				text = element.getAttribute("class");
				// check size available or not

				if (text.equals("unavailable")) {
					// click on the unavailable item and check the button text
					element.click();
					Thread.sleep(3000);
					System.out.println("Unavailable size clicked");
					Thread.sleep(5000);
					element = driver.findElement(ATC_CTA);
					text = element.getText();
					System.out.println("Text of button when Unavailable size selected :: "+text);
					flag = true;

				}
				else  {
					jse = (JavascriptExecutor)driver;
					jse.executeScript("arguments[0].click();", element);
					//element.click();
					System.out.println("available size is selected");
					break;
				} 
				if(i==elementsCount) {
					Reporter.log("No any size available");
					break;
				}
				if(flag == true)
					continue;

			}
			Reporter.log("Size drop down is Displayed :: Clickable.");
			softassert.assertAll();
		}
	}

	public void ATC() throws Exception
	{
		String title1 = driver.findElement(RIGHT_RAIL_TOP_PRODUCT_TITLE).getText();
		String title2 = driver.findElement(RIGHT_RAIL_BOTTOM_PRODUCT_TITLE).getText();
		//driver.findElement(ATC_CTA);
		if(!driver.findElement(ATC_CTA).isEnabled())
			throw new Exception("ADD TO CART button is not present");
		element = driver.findElement(ATC_CTA);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click();", element);
		Thread.sleep(3000);

		allElements = driver.findElements(FlyCartPageLocator.LIST_OF_ITEMS_IN_FLYCART);
		System.out.println("Total item in cart is: "+allElements.size());
		for(int i=1; i<=allElements.size();i++)
		{
			String title = driver.findElement(By.cssSelector("div.inline-cart__col1 > div:nth-child(7) > article:nth-child("+i+") > div.line-item__summary > a")).getText();
			System.out.println("product title in fly cart is: " +title);
			if(title.equalsIgnoreCase(title2)|| title.equalsIgnoreCase(title1))
			{
				System.out.println("Correct product added to the cart.");
			}
			else 
				throw new Exception("Wrong product added to the cart.");
		}
		driver.findElement(FlyCartPageLocator.INLINE_CART_CLOSE_BUTTON).click();
		Thread.sleep(3000);
		Reporter.log("ADD TO CART button is Displayed :: Clickable");
	}

	public void validateRight_rail() throws Exception
	{
		for(int i=2;i<=3;i++)
		{
			WebElement RIGHT_RAIL_PRODUCT_TITLE = driver.findElement(By.cssSelector(".mixmatch-container>div:nth-child("+i+") > div.mixmatch_selection > div > div > div.multi-pdp-title-wrap > div.product-info__title-wrap > a > h3"));
			WebElement RIGHT_RAIL_PRODUCT_PRICE = driver.findElement(By.cssSelector(".mixmatch-container>div:nth-child("+i+") > div.mixmatch_selection > div > div > div.multi-pdp-title-wrap > div.product-info__price-wrap"));
			List<WebElement> ALL_COLOR_LIST = driver.findElements(By.cssSelector(".mixmatch-container>div:nth-child("+i+")> div.mixmatch_selection > div > div > div.product-info__variant-select> div.product-option__color-swatches-wrapper.active > ul > li"));
			WebElement COLOR_TEXT = driver.findElement(By.cssSelector(".mixmatch-container>div:nth-child("+i+") > div.mixmatch_selection > div > div > div.product-info__variant-select> div.multi-pdp-info__header > span"));
			WebElement SIZE_GUIDE = driver.findElement(By.cssSelector(".mixmatch-container>div:nth-child("+i+") > div.mixmatch_selection > div > div > div.option-dropdown__section > div.product-info__header.product-info__header--desktop > button"));
			WebElement SLIDE_CENTER_PRODUCT_NAME =driver.findElement(By.cssSelector(".mixmatch-container>div:nth-child("+i+") > div.mixmatch_slidesection > div > div > div > div.slick-slide.slick-current.slick-active.slick-center > div > div.mm-mobile > a > h4"));

			//Top Right rail 
			softassert.assertTrue(RIGHT_RAIL_PRODUCT_TITLE.isDisplayed(), "Right rail top product title is not displayed.");
			text = RIGHT_RAIL_PRODUCT_TITLE.getText();
			Top_center_prodTitle = SLIDE_CENTER_PRODUCT_NAME.getText().replaceAll("\n", " ");
			System.out.println("Top slide centered prod title is: "+Top_center_prodTitle+ " Right rail title is: "+text);
			softassert.assertEquals(text, Top_center_prodTitle,"Right rail top product title is mismatched with top slide centerd product title.");
			Reporter.log("Right rail top product title is displayed :: Correct.");
			softassert.assertTrue(RIGHT_RAIL_PRODUCT_PRICE.isDisplayed(), "Right rail top product price is not displayed.");

			// Check we can select color or not
			elementsCount = ALL_COLOR_LIST.size();
			System.out.println("Right rail Color Element count :: "+elementsCount);
			if(elementsCount > 1)
			{
				for(int j=1;j<elementsCount;j++)
				{
					if(j<4)
					{
						text = COLOR_TEXT.getText();
						String colornew = driver.findElement(By.cssSelector(".mixmatch-container>div:nth-child("+i+") > div.mixmatch_selection > div > div > div.product-info__variant-select> div.product-option__color-swatches-wrapper.active > ul > li:nth-child("+j+")>input")).getAttribute("data-option-name");
						System.out.println("Color text is: "+text+" new color is: "+colornew);
						if(!colornew.contentEquals(text))	
						{
							WebElement elemen = driver.findElement(By.cssSelector(".mixmatch-container>div:nth-child("+i+")  > div.mixmatch_selection > div > div > div.product-info__variant-select> div.product-option__color-swatches-wrapper.active > ul > li:nth-child("+j+")>label"));
							elemen.click();
							Thread.sleep(3000);
							softassert.assertEquals(COLOR_TEXT.getText() , colornew , "Colors are not changing");
						}
					}
				}
			}
			Reporter.log("Colors are changing correctly.");

			//validate size guide
			if(!SIZE_GUIDE.isEnabled())
				throw new Exception("Size Guide link is not present");

			SIZE_GUIDE.click();
			Thread.sleep(3000);

			if (!driver.findElement(By.cssSelector(".size-chart-content")).isDisplayed())
				throw new Exception("Sizes chart overlay is not displayed after Size guide button was clicked");
			Reporter.log("Size guide link is Displayed :: Clickable");

			driver.findElement(By.cssSelector(".close_chart_modal")).click();
			Thread.sleep(3000);
			softassert.assertAll();
		}

	}

	public void Check_productLink() throws Exception
	{
		for(int i=2;i<=3;i++)
		{
			WebElement TOP_SLIDE_CENTER_PRODUCT_NAME =driver.findElement(By.cssSelector(".mixmatch-container>div:nth-child("+i+") > div.mixmatch_slidesection > div > div > div > div.slick-slide.slick-current.slick-active.slick-center > div > div.mm-mobile > a > h4"));
			text=TOP_SLIDE_CENTER_PRODUCT_NAME.getText().replaceAll("\n", " ");
			TOP_SLIDE_CENTER_PRODUCT_NAME.click();
			Thread.sleep(3000);
			ArrayList<String> tabs=new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			Thread.sleep(3000);
			String actual = driver.findElement(ProductDetailsPageLocators.PRODUCT_TITLE).getText();
			System.out.println("PDP title is: "+actual+" and slide product title is: "+text);
			if(!text.equalsIgnoreCase(actual))
				throw new Exception("Wrong page opened after click on 'slide center product title.'");
			driver.close();
			driver.switchTo().window(tabs.get(0));
			Thread.sleep(5000);
			
			WebElement RIGHT_RAIL_TOP_PRODUCT_TITLE = driver.findElement(By.cssSelector(".mixmatch-container>div:nth-child("+i+") > div.mixmatch_selection > div > div > div.multi-pdp-title-wrap > div.product-info__title-wrap > a > h3"));
			text=RIGHT_RAIL_TOP_PRODUCT_TITLE.getText();
			TOP_SLIDE_CENTER_PRODUCT_NAME.click();
			Thread.sleep(3000);
			tabs=new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			Thread.sleep(3000);
			actual = driver.findElement(ProductDetailsPageLocators.PRODUCT_TITLE).getText();
			System.out.println("PDP title is: "+actual+" and Right rail product title is: "+text);
			if(!text.equalsIgnoreCase(actual))
				throw new Exception("Wrong page opened after click on 'Right rail product title.'");
			driver.close();
			driver.switchTo().window(tabs.get(0));
			Thread.sleep(5000);
		}
		Reporter.log("Correct PDP opened in new tab after click on product title.");
	}

}

