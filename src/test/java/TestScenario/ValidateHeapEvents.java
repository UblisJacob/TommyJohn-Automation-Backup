package TestScenario;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.tommyjohn.automation.Components.CollectionPageComponent;
import com.tommyjohn.automation.Components.HomePageComponents;
import com.tommyjohn.automation.utils.ChromeExtensionUtility;

public class ValidateHeapEvents {

	WebDriver driver;

	@Test
	public void TC_ValidateDYHomePageEvents() throws Exception
	{
		driver = ChromeExtensionUtility.launchchromewithextension();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("javascript:window.scrollBy(0,1300)");

		Thread.sleep(3000);
		String logEntries = driver.manage().logs().getAvailableLogTypes().toString();
		System.out.println(logEntries);



		if(driver.findElement(By.cssSelector(".dy-rec--products")).isDisplayed())
		{

			LogEntries logclient = driver.manage().logs().get(LogType.BROWSER);
			//	List<String> log = new ArrayList<>();
			for (LogEntry entry : logclient) {
				System.out.println(entry.getMessage().replace("chrome-extension://bihllkinhojjiacepgoipnppiiigbekj/src/events.js 50:24", "properties").replace("chrome-extension://bihllkinhojjiacepgoipnppiiigbekj/src/events.js 45:34", "Event"));

				if(entry.getMessage().contains("chrome-extension"))
				{
					if(entry.getMessage().contains("\"name:\" \"view_plp\""))
					{
						System.out.println(entry.getMessage());
						Reporter.log("view_plp event fired on homepage when product listing was present.");
						break;

					}


				}


			}

		}

		else {
			Reporter.log("No product listings on homepage, hence view_plp event cannot be verified on homepage.");
			System.out.println("No product listings on homepage, hence view_plp event cannot be verified on homepage.");

		}

	}



	@Test
	public void TC_ValidateCollectionPageEvents() throws Exception
	{
		driver = ChromeExtensionUtility.launchchromewithextension();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement element;
		Actions action = new Actions(driver);

		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String script = "console.clear();";
		js.executeScript(script);

		new HomePageComponents(driver).navigateToAllUnderwearInMenCategory();
		
	Thread.sleep(10000);
	driver.findElement(By.cssSelector("div.mega-collection-filters > div:nth-child(2) > a")).click();
	driver.findElement(By.cssSelector("div.mega-collection-filters > div:nth-child(2) > ul > li:nth-child(1) > label > input")).click();
	driver.findElement(By.cssSelector("div.mega-collection-filters > div:nth-child(2) > a")).click();

	Thread.sleep(3000);
		Select selectElement = new Select(driver.findElement(CollectionPageComponent.LAST_FILTER));
		selectElement.selectByValue("price:asc");
		
		Thread.sleep(5000);
		
		js.executeScript("window.scrollBy(0,250)", "");
		//Hover On product image on collection page
		
		wait.until(ExpectedConditions.presenceOfElementLocated(CollectionPageComponent.FIRST_PRODUCT_IMAGE));

		element = driver.findElement(CollectionPageComponent.FIRST_PRODUCT_IMAGE);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(3000);
		action.moveToElement(element).build().perform();
		
		element = driver.findElement(CollectionPageComponent.QUICK_SHOP_ATC);
		List<WebElement> allElements;
		String classtext;
		allElements = driver.findElements(CollectionPageComponent.QUICK_SHOP_SIZE_LIST);
		System.out.println("All sizes list :: "+ allElements.size());
		for(int i=0;i<allElements.size();i++) 
		{
			classtext=allElements.get(i).getAttribute("class");
			if (!classtext.contains("not-available")) 
			{
				element= driver.findElement(By.cssSelector("div.inline-quick-shop > ul > li:nth-child("+(i+1)+") > label"));
				js.executeScript("arguments[0].click();", element);
				String Selected_size = allElements.get(i).getAttribute("value");
				System.out.println("Selected size is: "+Selected_size);
				Thread.sleep(3000);	
				break;

			}	
			else
			{
				System.out.println("Selected size is OOS");
			}
		}
		
		

		Thread.sleep(10000);
		String logEntries = driver.manage().logs().getAvailableLogTypes().toString();
		System.out.println(logEntries);


		LogEntries logclient = driver.manage().logs().get(LogType.BROWSER);
		for (LogEntry entry : logclient) {
			{

				if(entry.getMessage().contains("chrome-extension"))
				{
					if(entry.getMessage().contains("\"name:\" \"view_plp\""))
					{
						System.out.println(entry.getMessage());
						Reporter.log("view_plp event fired on collection page.");
						break;

					}
					
					if(entry.getMessage().contains("\"name:\" \"apply_sort\""))
					{
						System.out.println(entry.getMessage());
						Reporter.log("view_plp event fired on collection page.");
						break;

					}
					
					if(entry.getMessage().contains("\"name:\" \"apply_filter\""))
					{
						System.out.println(entry.getMessage());
						Reporter.log("view_plp event fired on collection page.");
						break;

					}

					if(entry.getMessage().contains("\"name:\" \"add_to_cart_all\""))
					{
						System.out.println(entry.getMessage());
						Reporter.log("view_plp event fired on collection page.");
						break;

					}
				}


			}

		}
	}
}


