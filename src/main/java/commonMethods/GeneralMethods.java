package commonMethods;

import java.io.FileReader;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class GeneralMethods {
	
	public static final String dataPropertiesFile = "src/main/resources/configuration.properties";
	/*public GeneralMethods(WebDriver driver) {
		this.driver = driver;
	}
	public WebDriver driver;*/
	
	// Method for page loading
	public void waitTillPageLoad(WebDriver objDriver)
	{
		(new WebDriverWait(objDriver, Duration.ofSeconds(10))).until(new ExpectedCondition<Boolean>()
		{
			public Boolean apply(WebDriver objDriver)
			{
				return (((JavascriptExecutor) objDriver).executeScript("return document.readyState").equals("complete")
						||((JavascriptExecutor) objDriver).executeScript("return document.readyState").equals("loaded"));
			}
		});
	}
	
	//Method to click button by JavascriptExecutor
	public void clickButtonByJS(WebDriver objDriver, By objectPath) throws Exception
	{
		if(verifyElementExists(objDriver, objectPath))
		{
			JavascriptExecutor executor = (JavascriptExecutor)objDriver;
			executor.executeScript("arguments[0].click();", objDriver.findElement(objectPath));
			waitTillPageLoad(objDriver);
		}
	}
	// Mouse Actions - left click
	public void clickButtonByAction(WebDriver objDriver, By objectPath) throws Exception
	{
		WebElement clickable = objDriver.findElement(objectPath);
	     new Actions(objDriver)
	             .click(clickable)
	             .perform();
	}
	 
	public void clickButton(WebDriver objDriver, By objectPath) throws Exception
	{
		if(verifyElementExists(objDriver, objectPath))
		{
			objDriver.findElement(objectPath).click();
		}
	}
	//To verify the element till exists
	public boolean verifyElementExists(WebDriver objDriver, By objectPath) throws Exception
	{
		boolean boolResultFlag = false;
		try
		{
			waitTillPageLoad(objDriver);
			objDriver.findElement(objectPath);
			boolResultFlag = true;
		}
		catch(Exception e)
		{
			boolResultFlag = false;	
		}
		return boolResultFlag;
	}
	
	//To verify Element is clickable
	public void assertElementClickable(WebDriver objDriver, By objectPath, String strTrueMsg) throws Exception
    {
    	Assert.assertTrue(objDriver.findElement(objectPath).isEnabled(),"Element Clickable");
    	Reporter.log(strTrueMsg);
    }
	
	//To verify element is displayed
    public void assertElementDisplayed(WebDriver objDriver, By objectPath, String strTrueMsg) throws Exception
    {
    	Assert.assertTrue(objDriver.findElement(objectPath).isDisplayed(),"Element Displayed");
    	Reporter.log(strTrueMsg);
    }
    
    //Implicit wait
    public void implicitWait(WebDriver objDriver) throws Exception
	{
    	//objDriver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	}
    
    //To verify Element is displayed
    public boolean isElementVisible(WebDriver objDriver, By objectPath)
	{
		boolean boolResultFlag = false;
		try
		{
			waitTillPageLoad(objDriver);
			(new WebDriverWait(objDriver, Duration.ofSeconds(10))).until(ExpectedConditions.visibilityOfElementLocated(objectPath));
			boolResultFlag = true;
		}
		catch(Exception exception)
		{
			boolResultFlag = false;
		}
		return boolResultFlag;
	}
    
    public void clickButtonByJS(WebDriver objDriver, WebElement element) throws Exception
	{
		
			JavascriptExecutor executor = (JavascriptExecutor)objDriver;
			executor.executeScript("arguments[0].click();", element);
			waitTillPageLoad(objDriver);
		
	}
    
    //To scroll into view od the element
    public void scrollElementIntoView(WebDriver objDriver,By objectPath) throws InterruptedException
	{
		((JavascriptExecutor) objDriver).executeScript("arguments[0].scrollIntoView(true);", objDriver.findElement(objectPath));
		Thread.sleep(2000);
	}
    
    // To read properties file
    public String readPropertiesFile(String data) throws Exception {
    	
		 FileReader reader=new FileReader(dataPropertiesFile);  
	      
		 Properties p=new Properties();  
		 p.load(reader);
		 
		 String url="";
		 
		 url=p.getProperty(data);  
		 System.out.println(p.getProperty(url));  
		 
		 reader.close();
		 
		 return url;
		 
	}
    
	/**
	 * Used to change index of css locators consisting nth-child
	 * @param elementToIterate
	 * @param index
	 * @param occurence of nth-child
	 */
	public WebElement autoIndex(WebDriver driver, By by, int index) {

		String s = by.toString();
		String locator = s.substring(s.indexOf(' ')+1);
		
		String firstPart = locator.substring(0, locator.indexOf('(')+1);
		String secondPart = locator.substring(locator.indexOf(')'));
		
		By finalBy = By.cssSelector(firstPart + index + secondPart);
		
		return driver.findElement(finalBy);
	
	}
    
    
	
}
