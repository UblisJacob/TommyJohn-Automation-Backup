package com.tommyjohn.automation.Components;

import java.util.List;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.tommyjohn.automation.PageLocators.ContactUsPageLocators;
import com.tommyjohn.automation.utils.CustomUtilities;

public class ContactUsComponent extends ContactUsPageLocators
{    
	WebDriver driver;
	SoftAssert softassert=new SoftAssert();
	Properties properties = CustomUtilities.properties;
	WebElement element;
	List<WebElement> allElements;
	String text= null;
	
	public ContactUsComponent(WebDriver driver)
	{
		this.driver=driver;
	}
	public void ContactUsFlow() throws Exception
	{
		driver.findElement(NAME_TEXTBOX).sendKeys("test");
		Thread.sleep(3000);
		driver.findElement(EMAIL_ADDRESS_TEXTBOX).sendKeys("test@tommyjohn.com");
		Thread.sleep(3000);
		driver.findElement(SUBJECT_LIST).click();
		Thread.sleep(3000);
		driver.findElement(MESSAGE_TEXTAREA).sendKeys("Test Message, Please ignore this.");
		Thread.sleep(3000);
		driver.findElement(SEND_MESSAGE_BUTTON).click();
		Reporter.log("User is able to send message through contact us form.");
		
	}
	public void ContactUsFieldValidation() throws Exception
	{		
		softassert.assertTrue(driver.findElement(PageTitle).isDisplayed(), "Contact us page title is not displayed.");
		softassert.assertEquals(driver.findElement(PageTitle).getText(), properties.getProperty("PageTitle"),"Text changed for Contact-us page title.");
		Reporter.log("Contact us Page Title is displayed :: Correct.");

		softassert.assertTrue(driver.findElement(CONTACT_INFO_TITLE).isDisplayed(), "Contact Information title is not displayed.");
		softassert.assertEquals(driver.findElement(CONTACT_INFO_TITLE).getText(), properties.getProperty("ContactInformationTitle"),"Text changed for Contact information title.");
		Reporter.log("Contact information Title is displayed :: Correct.");

		softassert.assertTrue(driver.findElement(EMAIL_US_AT_TEXT).isDisplayed(), "'Email us at' label is not displayed.");
		System.out.println("Email us at text is: "+driver.findElement(EMAIL_US_AT_TEXT).getText());
		softassert.assertEquals(driver.findElement(EMAIL_US_AT_TEXT).getText(), properties.getProperty("EmailUsAt"),"Text changed for 'Email us at'.");
		Reporter.log("'Email us at' label is displayed :: Correct.");

		softassert.assertTrue(driver.findElement(MAIL_TO).isDisplayed(), "'Email id' link is not displayed.");
		softassert.assertEquals(driver.findElement(MAIL_TO).getText(), properties.getProperty("EmailUsLink"),"Email id changed for 'Email us at'.");
		Reporter.log("'Email id' link is displayed :: Correct.");

		softassert.assertTrue(driver.findElement(CALL_US_AT_TEXT).isDisplayed(), "'Call us at' label is not displayed.");
		System.out.println("Call us at text is: "+driver.findElement(CALL_US_AT_TEXT).getText());
		softassert.assertEquals(driver.findElement(CALL_US_AT_TEXT).getText(), properties.getProperty("callusat"),"Text changed for 'Call us at'.");
		Reporter.log("'Call us at' label is displayed :: Correct.");

		softassert.assertTrue(driver.findElement(CALL_US_NUMBER).isDisplayed(), "'Tel:no' link is not displayed for 'Call us at' label.");
		softassert.assertEquals(driver.findElement(CALL_US_NUMBER).getText(), properties.getProperty("callUsLink"),"Tel: number changed for 'Call us at'.");
		Reporter.log("'Tel: number' link  for call is displayed :: Correct.");

		softassert.assertTrue(driver.findElement(TEXT_US_AT).isDisplayed(), "'Text us at' label is not displayed.");
		softassert.assertEquals(driver.findElement(TEXT_US_AT).getText(), properties.getProperty("TextUsAt"),"Text changed for 'Text us at'.");
		Reporter.log("'Text us at' label is displayed :: Correct.");

		softassert.assertTrue(driver.findElement(LIVE_CHAT_WITH_US).isDisplayed(), "'Live Chat With Us' link is not displayed.");
		softassert.assertEquals(driver.findElement(LIVE_CHAT_WITH_US).getText(), properties.getProperty("LiveChatWithUs"),"Text changed for 'Live Chat With Us'.");
		Reporter.log("'Live Chat With Us' link is displayed :: Correct.");
		
		/*driver.findElement(LIVE_CHAT_WITH_US).click();
		Thread.sleep(3000);
		element = driver.findElement(CHAT_WITH_US_IFRAME);
		driver.switchTo().frame(element);
		element = driver.findElement(CHAT_WITH_US_POPUP);
		if(!element.isEnabled())
			throw new Exception("Chat With Us Popup is not opened");
		if(element.isEnabled()) {
			if(!driver.findElement(CHAT_WITH_US_POPUP_MINIMIZE).isDisplayed())
				throw new Exception("Chat With Us Popup Minimize button is not present");
			driver.findElement(CHAT_WITH_US_POPUP_MINIMIZE).click();
			}
		else
			throw new Exception("Chat With Us Popup is not opened");
		Reporter.log("Chat With Us Popup is Displayed :: Minimize Button working");*/

		softassert.assertTrue(driver.findElement(FOR_FURTHER_INFO_TEXT).isDisplayed(), "'For Further Info...' label is not displayed.");
		softassert.assertEquals(driver.findElement(FOR_FURTHER_INFO_TEXT).getText(), properties.getProperty("ForFurtherInfoText"),"Text changed for 'For Further Info...'.");
		Reporter.log("'For further info..' label is displayed :: Correct.");

		softassert.assertTrue(driver.findElement(HELP_LINK).isDisplayed(), "'Help' link is not displayed.");
		softassert.assertEquals(driver.findElement(HELP_LINK).getText(), properties.getProperty("Help"),"Text changed for 'Help' link.");
		Reporter.log("'Help' link is displayed :: Correct.");
		
		driver.findElement(HELP_LINK).click();
		Thread.sleep(3000);
		if(driver.getCurrentUrl().equalsIgnoreCase("https://help.tommyjohn.com/hc/en-us")) 
			driver.navigate().back();
		else
			throw new Exception("Wrong page opened after Help link clicked");
		Reporter.log("Help link is Displayed :: Clickable");

		softassert.assertTrue(driver.findElement(SHIPPING_RETURNS_POLICY).isDisplayed(), "'Shipping & Return Policy' link is not displayed.");
		softassert.assertEquals(driver.findElement(SHIPPING_RETURNS_POLICY).getText(), properties.getProperty("Shipping&ReturnPolicy"),"Text changed for 'Shipping & Return Policy' link.");
		Reporter.log("'Shipping & Return Policy' link is displayed :: Correct.");

		driver.findElement(SHIPPING_RETURNS_POLICY).click();
		Thread.sleep(3000);
		if(driver.getCurrentUrl().equalsIgnoreCase("https://help.tommyjohn.com/hc/en-us/articles/360000507746-Shipping-Returns")) 
			driver.navigate().back();
		else
			throw new Exception("Wrong page opened after Shipping & Return Policy link clicked");
		Reporter.log("Shipping & Return Policy link is Displayed :: Clickable");
		
		softassert.assertTrue(driver.findElement(WHOLESALE_INQUIRIES).isDisplayed(), "'Wholesale Inquiries' link is not displayed.");
		softassert.assertEquals(driver.findElement(WHOLESALE_INQUIRIES).getText(), properties.getProperty("WholesaleInquiries"),"Text changed for 'Wholesale Inquiries' link.");
		Reporter.log("'Wholesale Inquiries' link is displayed :: Correct.");

		driver.findElement(WHOLESALE_INQUIRIES).click();
		Thread.sleep(3000);
		if(driver.getCurrentUrl().equalsIgnoreCase("https://tommyjohn.com/pages/retailers")) 
			driver.navigate().back();
		else
			throw new Exception("Wrong page opened after Wholesale Inquiries link clicked");
		Reporter.log("Wholesale Inquiries link is Displayed :: Clickable");
		
		softassert.assertTrue(driver.findElement(NAME_TEXT).isDisplayed(), "'Name' label is not displayed.");
		System.out.println("Name text is: "+driver.findElement(NAME_TEXT).getText());
		softassert.assertEquals(driver.findElement(NAME_TEXT).getText(), properties.getProperty("Name"),"Text changed for 'Name' label.");
		Reporter.log("'Name' label is displayed :: Correct.");
		softassert.assertTrue(driver.findElement(NAME_TEXTBOX).isDisplayed(), "'Name Textbox' is not displayed.");
		driver.findElement(NAME_TEXTBOX).sendKeys("testname");
		Reporter.log("'Name Textbox' is displayed ::Clickable.");

		softassert.assertTrue(driver.findElement(EMAIL_ADDRESS_TEXT).isDisplayed(), "'Email Address' label is not displayed.");
		System.out.println("Email address label is: "+driver.findElement(EMAIL_ADDRESS_TEXT).getText());
		softassert.assertEquals(driver.findElement(EMAIL_ADDRESS_TEXT).getText(), properties.getProperty("EmailAddress"),"Text changed for 'Email Address' label.");
		Reporter.log("'Email Address' label is displayed :: Correct.");
		softassert.assertTrue(driver.findElement(EMAIL_ADDRESS_TEXTBOX).isDisplayed(), "'Email Address textbox' is not displayed.");
		driver.findElement(EMAIL_ADDRESS_TEXTBOX).sendKeys("test@tommyjohnwear.com");
		Reporter.log("'Email Address textbox' is displayed ::Clickable.");

		softassert.assertTrue(driver.findElement(SUBJECT).isDisplayed(), "'SUBJECT' label is not displayed.");
		System.out.println("Subject label is: "+driver.findElement(SUBJECT).getText());
		softassert.assertEquals(driver.findElement(SUBJECT).getText(), properties.getProperty("Subject"),"Text changed for 'SUBJECT' label.");
		Reporter.log("'SUBJECT' label is displayed :: Correct.");
		// softassert.assertTrue(driver.findElement(SUBJECT_LIST).isDisplayed(), "'Subject list' drop down is not displayed.");
		allElements = driver.findElements(SUBJECT_LIST);
		int elementsCount = allElements.size();
		System.out.println("All Subject Option list count is :: "+ elementsCount);
		for(int i=1;i<elementsCount;i++) {
			driver.findElement(SUBJECT_LIST).click();
			text=allElements.get(i).getText();
			allElements.get(i).click();
			System.out.println("Selected option is :: "+text);
			Thread.sleep(3000);
		}
		Reporter.log("'Subject list' is displayed ::Clickable.");

		softassert.assertTrue(driver.findElement(MESSAGE).isDisplayed(), "'Message' label is not displayed.");
		System.out.println("Message label is: "+driver.findElement(MESSAGE).getText());
		softassert.assertEquals(driver.findElement(MESSAGE).getText(), properties.getProperty("Message"),"Text changed for 'Message' label.");
		Reporter.log("'Message' label is displayed :: Correct.");
		softassert.assertTrue(driver.findElement(MESSAGE_TEXTAREA).isDisplayed(), "'Message textarea' is not displayed.");
		driver.findElement(MESSAGE_TEXTAREA).sendKeys("Test Message, Please ignore this.");
		Reporter.log("'MESSAGE TEXTAREA' is displayed ::Clickable.");

		softassert.assertTrue(driver.findElement(SEND_MESSAGE_BUTTON).isDisplayed(), "'Send Message' button is not displayed.");
		softassert.assertEquals(driver.findElement(SEND_MESSAGE_BUTTON).getText(), properties.getProperty("SendMessage"),"Text changed for 'Submit' button.");
		driver.findElement(SEND_MESSAGE_BUTTON).click();
		Thread.sleep(3000);
		Reporter.log("'Submit' button is displayed :: Clickable.");

		softassert.assertTrue(driver.findElement(SUCCESS_TITLE).isDisplayed(), "Success title is not displayed.");
		softassert.assertEquals(driver.findElement(SUCCESS_TITLE).getText(), properties.getProperty("SuccessTitle"),"Text changed for Success Title.");
		Reporter.log("Success title is displayed :: Correct.");
		
		softassert.assertTrue(driver.findElement(SUCCESS_MESSAGE).isDisplayed(), "Success Message is not displayed.");
		softassert.assertEquals(driver.findElement(SUCCESS_MESSAGE).getText(), properties.getProperty("SuccessMessage"),"Text changed for Success Message.");
		Reporter.log("Success Message is displayed :: Correct.");
		
		softassert.assertAll();
	}
	
}
