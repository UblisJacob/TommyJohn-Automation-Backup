package com.tommyjohn.automation.Components;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;



import com.tommyjohn.automation.PageLocators.CollectionPageLocator;

public class CollectionPageComponent extends CollectionPageLocator {

	public CollectionPageComponent(WebDriver driver) {
		this.driver = driver;
	}
	public WebDriver driver;
	WebElement element;

	public String navigateToProductDetailsPage() throws Exception {
		String text = null;
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(THIRD_PRODUCT_HEAD_LINE_TEXT));
		// get text and click on the first product
		if(!driver.findElement(THIRD_PRODUCT_HEAD_LINE_TEXT).isDisplayed())
			throw new Exception("Product head line text is not displayed");
		text = driver.findElement(THIRD_PRODUCT_HEAD_LINE_TEXT).getText();
		driver.findElement(THIRD_PRODUCT_HEAD_LINE_TEXT).click();
		Thread.sleep(3000);
		Reporter.log(driver.getCurrentUrl());
		return text;
	}

	@SuppressWarnings("static-access")
	public void validateProductImageOnCollectionPage() throws Exception {
		String text = null;

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(THIRD_PRODUCT_HEAD_LINE_TEXT));
		// get product title and click on image if present
		text = driver.findElement(THIRD_PRODUCT_HEAD_LINE_TEXT).getText();
		Thread.sleep(5000);
		WebElement Prod_Image = driver.findElement(THIRD_PRODUCT_IMAGE);
		if(!Prod_Image.isDisplayed())
			//#		if(!driver.findElement(THIRD_PRODUCT_IMAGE).isDisplayed())
			throw new Exception("Product image is not present");
		WebElement ele = driver.findElement(THIRD_PRODUCT_IMAGE);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", ele);
		//	driver.findElement(THIRD_PRODUCT_IMAGE).click();
		Thread.sleep(3000);
		Reporter.log(driver.getCurrentUrl());

		// check correct Product details page opened or not
		new ProductDetailsPageComponents(driver).checkCorrectProductDetailsPageOpenedOrNot(text);	

		// validate product details page by calling the method
		new ProductDetailsPageComponents(driver).validatePage();

		Reporter.log("Product image on collection page is Displayed :: Clickable and validated successfully");
	}

	@SuppressWarnings("static-access")
	public void validateProductHeadingTextOnCollectionPage() throws Exception {
		String text = null;
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(THIRD_PRODUCT_HEAD_LINE_TEXT));
		// get product title and click on product heading text if present
		if(!driver.findElement(THIRD_PRODUCT_HEAD_LINE_TEXT).isDisplayed())
			throw new Exception("Product heading text is not present");
		text = driver.findElement(THIRD_PRODUCT_HEAD_LINE_TEXT).getText();
		driver.findElement(THIRD_PRODUCT_HEAD_LINE_TEXT).click();
		Thread.sleep(3000);
		Reporter.log(driver.getCurrentUrl());

		// check correct Product details page opened or not
		new ProductDetailsPageComponents(driver).checkCorrectProductDetailsPageOpenedOrNot(text);	

		// validate product details page by calling the method
		new ProductDetailsPageComponents(driver).validatePage();

		Reporter.log("Product head line text on collection page is Displayed :: Clickable and validated successfully");

	}

	@SuppressWarnings("static-access")
	public void validateProductReviewStarsOnCollectionPage() throws Exception {
		String text = null;


		// get product title and click on product heading text if present
		try {
			if(!driver.findElement(THIRD_PRODUCT_REVIEW_STARS).isDisplayed())
				throw new Exception("Product review stars are not present");
			text = driver.findElement(THIRD_PRODUCT_HEAD_LINE_TEXT).getText();
			driver.findElement(THIRD_PRODUCT_REVIEW_STARS).click();
			Thread.sleep(3000);
			Reporter.log(driver.getCurrentUrl());
			// check correct Product details page opened or not
			new ProductDetailsPageComponents(driver).checkCorrectProductDetailsPageOpenedOrNot(text);	

			// validate product details page by calling the method
			new ProductDetailsPageComponents(driver).validatePage();

			Reporter.log("Product review stars on collection page is Displayed :: Clickable and validated successfully");

		}catch(Exception e) {
			Reporter.log("Review Stars were not present for the selected product");
		}
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
		System.out.println("All filters list :: "+ filterList);

		// loop for total not of filters
		for(int i = 2; i <= filterList+1; i++) {
			element=driver.findElement(By.cssSelector("div.mega-collection-filters > div:nth-child("+i+") > a"));
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("arguments[0].click();", element);
			// get all the options list available in ith filter
			allElements = driver.findElements(By.cssSelector("div.mega-collection-filters > div:nth-child("+i+") > ul > li"));
			optionsList = allElements.size();
			System.out.println("All options list :: "+ optionsList);
			int ct = 0;
			//loop for clicking all the options in ith filter
			for(int j = 1; j <= optionsList; j++) {

				// check if element is not displayed but present then continue at this step
				System.out.println("i = "+i+" j = "+j);

				// check if not any option present
				if(optionsList == 0)
					break;

				if(!driver.findElement(By.cssSelector("div.mega-collection-filters > div:nth-child("+i+") > ul > li:nth-child("+j+") > label > input")).isDisplayed())
					continue;
				count++;
				driver.findElement(By.cssSelector("div.mega-collection-filters > div:nth-child("+i+") > ul > li:nth-child("+j+") > label > input")).click();
				Thread.sleep(1000);

				// get the name of selected option
				selectedOption = driver.findElement(By.cssSelector("div.mega-collection-filters > div:nth-child("+i+") > ul > li:nth-child("+j+") > label > input")).getAttribute("data-filter-btn");
				System.out.println("selected option :: "+selectedOption);


				// get all selected displayed optioins
				allElements = driver.findElements(DISPLAYED_ALL_SELECTED_OPTIONS);
				optionsList1 = allElements.size();

				System.out.println("count :: "+count);
				System.out.println("optionlist1 :: "+optionsList1);
				// check selected options count and displayed selected list count is same or not
				if(count != optionsList1)
					throw new Exception("Selected options and selected displayed options are not same");

				System.out.println("ct :: "+ct);
				System.out.println("ct th ele :: "+allElements.get(ct).getText());
				if(!(allElements.get(ct).getText()).equals(selectedOption))
					throw new Exception("Selected item is not present in displayed item list");

				// check total no of count is displayed or not
				if(!driver.findElement(TOTAL_COUNTS_OF_ITEMS).isDisplayed())
					throw new Exception("Total counts of items is not displayed for filter :: "+i);

				// check total not of count and products are correct
				totalDisplayedCount = driver.findElement(TOTAL_COUNTS_OF_ITEMS).getText();
				allProductsOnPage = driver.findElements(FIRST_PRODUCT_IMAGE);
				int allProductsOnPage1 = allProductsOnPage.size();
				System.out.println("Out ddd :: "+allProductsOnPage1);
				// check for hoverable class (if image is hoverable then it count two images for one image)
				element = driver.findElement(FIRST_PRODUCT_IMAGE);
				//   			String classes = element.getAttribute("class");
				//    			System.out.println("Classes :: "+classes);
				//    			for (String c : classes.split(" ")) {
				//    				System.out.println("C ::::: "+c);
				//    				if (c.equals("product-image-container--has-hover")) {
				//    					allProductsOnPage1 = (allProductsOnPage1)/2;
				//    					System.out.println("In ddd :: "+allProductsOnPage1);
				//    				}
				//    			}
				//                
				System.out.println("total displayed product on page :: "+allProductsOnPage1);
				System.out.println("total displayed count of product :: "+totalDisplayedCount);
				if(allProductsOnPage1 != (Integer.parseInt(totalDisplayedCount)))
					throw new Exception("Total displayed count is not same as all products on page");

				ct++;

			}

			// check again any options are present or not
			if(optionsList == 0)
				continue;

			// click on last item to remove it from list
			Thread.sleep(5000);
			element=driver.findElement(By.cssSelector("div.mega-collection-filters > div:nth-child("+i+") > a"));
			jse = (JavascriptExecutor)driver;
			jse.executeScript("arguments[0].click();", element);
			Thread.sleep(1000);
			System.out.println("last item :: "+driver.findElement(By.cssSelector("div.js-mega-collection-desktop-filter-tags > button:nth-child("+ct+")")).getText());
			element=driver.findElement(By.cssSelector("div.js-mega-collection-desktop-filter-tags > button:nth-child("+ct+")"));
			jse = (JavascriptExecutor)driver;
			jse.executeScript("arguments[0].click();", element);
			//driver.findElement(PLUS_BUTTON).click();
			Thread.sleep(5000);

			Thread.sleep(1000);
			// after last element removed decrease ct by 1
			System.out.println("Before ct::"+ct);
			// decrease ct by 1 bcz when one element is removed from list
			ct--;
			ct--; // for taking last element from list
			System.out.println("After ct--::"+ct);
			// check when we click on item then it remove from list or not
			System.out.println("allelements size:: "+allElements.size());


			if(ct!=0 && ct!=-1) {
				System.out.println("ct :: "+allElements.get(ct).getText());
				System.out.println("ct :: "+driver.findElement(By.cssSelector("div.js-mega-collection-desktop-filter-tags > button:nth-child("+ct+")")).getText());
				if((allElements.get(ct).getText()).equals(driver.findElement(By.cssSelector("div.js-mega-collection-desktop-filter-tags > button:nth-child("+ct+")")).getText()))
					throw new Exception("Item is not removed from displayed list when click on it");
			}
			ct = 0;

			// scroll it top again
			//            JavascriptExecutor js = (JavascriptExecutor) driver;
			//            js.executeScript("arguments[0].scrollIntoView();", driver.findElement(CLEAR_FILTER_BUTTON));
			//	((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -document.body.scrollHeight)");
			Thread.sleep(2000);

			// clear the filters
			WebElement ele = driver.findElement(CLEAR_FILTER_BUTTON);
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", ele);
			//	driver.findElement(CLEAR_FILTER_BUTTON).click();
			Thread.sleep(3000);
			allElements = null;
			count = 0;
		}
		Reporter.log("All filters are working correctly");
		Reporter.log("All selected items are displayed in list and removing from list");
		Reporter.log("Clear filters button works correctly");

		// scroll to last filter view
		//		        element = driver.findElement(LAST_FILTER);
		//		        JavascriptExecutor js = (JavascriptExecutor) driver;
		//		        js.executeScript("arguments[0].scrollIntoView();", element);
		//		        
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(LAST_FILTER));
		if(driver.findElement(LAST_FILTER).isDisplayed()) 
		{
			//driver.findElement(LAST_FILTER).click();
			Select selectElement = new Select(driver.findElement(LAST_FILTER));
			selectElement.selectByValue("price:asc");

			// click on second option

			//wait.until(ExpectedConditions.visibilityOfElementLocated(SORTBYPRICE_HIGH_TO_LOW_FILTER_OPTION));
			//driver.findElement(SORTBYPRICE_HIGH_TO_LOW_FILTER_OPTION).click();
			Thread.sleep(3000);
			allProductsOnPage = driver.findElements(FIRST_PRODUCT_IMAGE);
			System.out.println("All product on page :: "+allProductsOnPage.size());
			for(int i=1; i < allProductsOnPage.size(); i++) {
				String text = null;
				String text1 = null;
				String text2 = null;

				text = driver.findElement(By.cssSelector(".mega-collection-grid__row > div.product-item:nth-child("+i+") > div.product-meta__prices")).getText();
				System.out.println("Text :: "+text);
				for (String a : text.split("\\$")) 
					text1 = a;
				System.out.println("text 1 :: "+text1);
				// loop to check previous and next price
				int j = i+1;
				System.out.println("Text :: "+driver.findElement(By.cssSelector(".mega-collection-grid__row > div.product-item:nth-child("+j+") > div.product-meta__prices")).getText());
				for (String a : driver.findElement(By.cssSelector(".mega-collection-grid__row > div.product-item:nth-child("+j+") > div.product-meta__prices")).getText().split("\\$")) 
					text2 = a;
				System.out.println("text2 :: "+text2);
				if(Float.parseFloat(text1) > Float.parseFloat(text2))
					throw new Exception("Products are not sorted by price correctly");

			}
			Reporter.log("All Products are sorted as selected option");

		}


	}
	public void  methodToCheckCorosalsOnTopOfPageIsActiveWhenRelatedStyleIsChecked() throws Exception {
		boolean flag1 = false;
		int i = 2;
		List<WebElement> allElements = null;
		List<WebElement> allCorosals = null;

		Thread.sleep(20000);

		// check carosals are displayed on top of page or not
		if(!driver.findElement(STYLE_COROSALS).isDisplayed())
			throw new Exception("Corosals are not displayed on top of page");

		allCorosals = driver.findElements(TOTAL_NO_OF_COROSALS);
		System.out.println("Total no of corosals :: "+allCorosals.size());

		// first check all corosals are not active by default
		for(int k = 1;k <= allCorosals.size();k++) {
			element = driver.findElement(By.cssSelector(".hero-interactive-slide.slick-slide:nth-child("+k+")"));
			String classes = element.getAttribute("class");
			for (String c : classes.split(" ")) {
				if (c.equals("active")) {
					flag1 = true;
				}
			}
			if(flag1 == true)
				throw new Exception("All corosals are not deactive by default");
			flag1= false;
		}
		Reporter.log("All corosals are Displayed :: And by default not active");
		// check selected option in style and respected corosal is active or not
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.mega-collection-filters > div:nth-child("+i+") > a")));
		driver.findElement(By.cssSelector("div.mega-collection-filters > div:nth-child("+i+") > a")).click();

		// get all the options list available in ith filter
		allElements = driver.findElements(By.cssSelector("div.mega-collection-filters > div:nth-child("+i+") > ul > li"));
		//loop for clicking all the options in ith filter
		for(int j = 1; j <= allElements.size(); j++) {
			// check if element is not displayed but present then continue at this step
			System.out.println("i = "+i+" j = "+j);
			if(!driver.findElement(By.cssSelector("div.mega-collection-filters > div:nth-child("+i+") > ul > li:nth-child("+j+") > label > input")).isDisplayed())
				continue;

			driver.findElement(By.cssSelector("div.mega-collection-filters > div:nth-child("+i+") > ul > li:nth-child("+j+") > label > input")).click();
			Thread.sleep(1000);

			String selectedOption = driver.findElement(By.cssSelector("div.mega-collection-filters > div:nth-child("+i+") > ul > li:nth-child("+j+") > label > input")).getAttribute("data-filter-btn");  
			//	    System.out.println("Selected Option :: "+selectedOption);

			// check selected option related carosal is active or not
			for(int k = 1;k <= allCorosals.size();k++) {
				String corosalName = driver.findElement(By.cssSelector(".hero-interactive-slide.slick-slide:nth-child("+k+") > button")).getAttribute("data-hero-filter-name");
				if(selectedOption == corosalName) {
					element = driver.findElement(By.cssSelector(".hero-interactive-slide.slick-slide:nth-child("+k+")"));
					String classes = element.getAttribute("class");
					for (String c : classes.split(" ")) {
						if (c.equals("active")) {
							flag1 = true;
						}
					}
					if(!flag1 == true)
						throw new Exception("Corosal related to "+selectedOption+" is not gets active");
					flag1= false;
				}
			}
		}	    
		// clear all filters

		wait.until(ExpectedConditions.visibilityOfElementLocated(CLEAR_FILTER_BUTTON));
		WebElement ele = driver.findElement(CLEAR_FILTER_BUTTON);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", ele);

		//driver.findElement(CLEAR_FILTER_BUTTON).click();
		Reporter.log("Corosals gets active when respective option selected");
	}

	public void validateallurls()

	{
		try
		{
			File src=new File("src/main/resources/AllCollectionPages.xlsx");

			// load file
			FileInputStream fis=new FileInputStream(src);

			// Load workbook
			XSSFWorkbook wb=new XSSFWorkbook(fis);

			// Load sheet- Here we are loading first sheet only
			XSSFSheet sh1= wb.getSheetAt(0);
			System.out.println(sh1.getRow(0).getCell(0).getStringCellValue());


			int rowCount = sh1.getLastRowNum()-sh1.getFirstRowNum();

			for (int i = 1; i < rowCount+1; i++) {
				String url= sh1.getRow(i).getCell(0).getStringCellValue();
				checkurl(url);

			}


		}
		catch (Exception e)
		{
			Reporter.log("File not found");
		}
	}

	private void checkurl(String url) throws InterruptedException {

		driver.get(url);
		Thread.sleep(10000);
		List<WebElement> items =driver.findElements(By.cssSelector(".product-item"));
		Reporter.log("No. of items present in " + url + " is " + items.size());
		if(items.size()==0)
		{
			sendmail(url);
		}
	}

	private void sendmail(String url) {

		

		// Recipient's email ID needs to be mentioned.
		String to1 = "vijeta@tommyjohnwear.com";
		String to2 = "saurabh.agarwal@tommyjohnwear.com";
		String to3 = "manoj.konale@tommyjohnwear.com";
		String to4 = "vipul@tommyjohnwear.com";
		String to5 = "anjali.pathak@tommyjohnwear.com";
		String to6 = "jubin@tommyjohnwear.com";
		String to7 = "akshata@tommyjohnwear.com";
		String to8 = "anil@tommyjohnwear.com";
		
		// Sender's email ID needs to be mentioned
		String from = "noreplymw@tommyjohnwear.com";
		// Assuming you are sending email from localhost
		String host = "secure.emailsrvr.com";
		// Get system properties
		Properties properties = System.getProperties();
		// Setup mail server
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.smtp.port", "587");
		properties.setProperty("mail.smtp.auth", "true");
		
		Authenticator auth = new Authenticator()
				{
				protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication("edw_job_alerts@aretove.com", "RedBook2018");
				}
				};
		
		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties, auth);
		System.out.println("Session Created");

		try{
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);
			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));
			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(to1));
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(to2));
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(to3));
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(to4));
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(to5));
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(to6));
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(to7));
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(to8));
			
			// Set Subject: header field
			message.setSubject("URGENT! Collection Page is down");

			// Create the message part 
			BodyPart messageBodyPart = new MimeBodyPart();

			// Fill the message
			messageBodyPart.setText("Please check for collection as it may be down or taking more than 10 seconds to load : " + url);
			
			// Create a multipar message
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Part two is attachment
			//		         messageBodyPart = new MimeBodyPart();
			//		         String filename = "<Enter File Path of Emailable Report>";
			//		         DataSource source = new FileDataSource(filename);
			//		         messageBodyPart.setDataHandler(new DataHandler(source));
			//		         messageBodyPart.setFileName(filename);
			//		         multipart.addBodyPart(messageBodyPart);

			// Send the complete message parts
			message.setContent(multipart );
			System.out.println(message.getSubject());
			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		}catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
}

