package com.tommyjohn.automation.Components;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

import com.base.ExtentReport;
import com.tommyjohn.automation.PageLocators.HomePageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;


import com.tommyjohn.automation.PageLocators.AddToCartLocators;
import com.tommyjohn.automation.PageLocators.ProductDetailsPageLocators;
import com.tommyjohn.automation.utils.CustomUtilities;

public class AddToCartComponents extends AddToCartLocators{

	public AddToCartComponents(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriver driver;
	public WebElement element;
	public Actions action;
	public JavascriptExecutor jse;
	Properties properties = CustomUtilities.properties;
	List<WebElement> allElements;
	int elementsCount;
	String text = null;

	String allClasses = null;

	public void AddSingleProduct() throws Exception {
		new HomePageComponents(driver).navigateToSecondSkinInMenCategory();
		jse=(JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)");
		// call method to navigate product details page
		text = new CollectionPageComponent(driver).navigateToPDP();
		Thread.sleep(2000);
		WebElement COLOR = driver.findElement(By.xpath("//span[@class='option-swatch__label-text' and contains(text(),'S')]"));
		jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", COLOR);
		WebElement SIZESMALL = driver.findElement(By.xpath("//span[@class='option-swatch__label-text' and contains(text(),'S')]"));
		jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", SIZESMALL);
		if(driver.findElement(ProductDetailsPageComponents.ADD_TO_CART_BUTTON).getText().contains("Add to Cart")) {
			System.out.println(SIZESMALL.getText() + "Product is added to Cart");
			jse.executeScript("arguments[0].click();", driver.findElement(ProductDetailsPageComponents.ADD_TO_CART_BUTTON));

		}
		else {
			System.out.println("Add to Cart CTA is not enabled");
			Reporter.log("Add to Cart CTA is not enabled");
			ExtentReport.extentTest.info("Add to Cart CTA is not enabled");

		}

	}
	
	public String addToCart() throws Exception {
		
		
		// call method to navigate to collection page
		new HomePageComponents(driver).navigateToMensCoolCotton();
		jse=(JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)");
		// call method to navigate product details page
		text = new CollectionPageComponent(driver).navigateToProductDetailsPage();
		
		Thread.sleep(2000);
		
		allElements = driver.findElements(ProductDetailsPageComponents.COLOR_VARIANT);
		
		elementsCount = allElements.size();
		System.out.println("Color Element count :: "+elementsCount);
		
		if(elementsCount > 1)
		{
			for(int i=1;i<=2;i++)
			{
				
				WebElement elemen = driver.findElement(By.xpath("(//div[@class='space-y-lg']//span[@class='sr-only'])["+i+"]"));
				jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].click();", elemen);
				Thread.sleep(3000);
				
					for(int k=1;k<=3;k++)
					{	
						WebElement elements = driver.findElement(By.xpath("(//input[@name='option[Size]'])["+k+"]"));
						jse.executeScript("arguments[0].click();", elements);
						Thread.sleep(1000);

						String[] AddtoBag;
						AddtoBag = (driver.findElement(By.xpath("//button[@class='button grow']")).getText()).split(" -");
						String text1 = AddtoBag[0];
						System.out.println(text1);


						if (driver.findElement(ProductDetailsPageComponents.ADD_TO_CART_BUTTON).getText().contains(text1)) {

							System.out.println(elemen.getText() + elements.getText() + "Product is added to Cart");
							Thread.sleep(2000);
							jse.executeScript("arguments[0].click();", driver.findElement(ADD_TO_CART_BUTTON));
							Thread.sleep(3000);
							driver.findElement(INLINE_CART_TITLE);
							jse.executeScript("arguments[0].click();", driver.findElement(INLINE_CART_CLOSE));

							Thread.sleep(1000);
							System.out.println("=============================");

						}

//
//						WebElement elem = driver.findElement(By.cssSelector(".multiPdp__atc-container>button"));
//
//						if(driver.findElement(ProductDetailsPageComponents.ADD_TO_CART_BUTTON).getText().contains("Add to Cart")) {
//
//							System.out.println(elemen.getText() + elements.getText() + "Product is added to Cart");
//							jse.executeScript("arguments[0].click();", driver.findElement(ProductDetailsPageComponents.ADD_TO_CART_BUTTON));
//							Thread.sleep(2000);
//							try{
//								jse.executeScript("arguments[0].click();", driver.findElement(INLINE_CART_CLOSE));
//							}catch (Exception e) {
//								Thread.sleep(2000);
//								jse.executeScript("arguments[0].click();", driver.findElement(INLINE_CART_CLOSE));
//							}
//							break;
//						}
						
//						if(elem.getText().contentEquals("Join the Waitlist")) {
//							System.out.println(elemen.getText()+ elements.getText() +"Product is Out of Stock and Join The waitlist is  :: Clickable");
//							jse.executeScript("arguments[0].click();", driver.findElement(ADD_TO_CART_BUTTON));
//							Thread.sleep(2000);
//							jse.executeScript("arguments[0].click();", driver.findElement(EMAIL_WHEN_IN_STOCK_BUTTON_CLOSE));
//
//							}
							
							
						}
						
													
			}
							
					}
		return text;
						
			}
	

	public void selectSize() throws Exception {
		
		Thread.sleep(9000);
		// Select first size between available sizes
		        String text1= null;
				allElements = driver.findElements(ALL_SIZE_LIST);
				elementsCount = allElements.size();
				System.out.println("All sizes list :: "+ elementsCount);
				JavascriptExecutor executor = (JavascriptExecutor)driver;
					    
			    try {
				if(driver.findElement(By.cssSelector("div.radio-options-container")).isDisplayed())
				{

					//radio			
					for(int i=3;i<=elementsCount;i=i+2) {
						boolean flag = false;
						element = driver.findElement(By.cssSelector(".radio-options-container > .Size > label:nth-child("+i+")"));
						if (element.getAttribute("class").contains("oosVariant")) {
							{
								if(!driver.findElement(ProductDetailsPageComponents.PRODUCT_TITLE).getText().contains("Pack"))
								{

									Thread.sleep(3000);
									jse = (JavascriptExecutor)driver;
									jse.executeScript("arguments[0].click();", element);
									//element.click();
									Thread.sleep(4000);

									System.out.println("Unavailable size clicked");
									element = driver.findElement(ProductDetailsPageComponents.JOIN_THE_WAITLIST);
									text1 = element.getText();
									System.out.println("Text of button when Unavailable size selected :: "+text1);
									if(!(text1.equalsIgnoreCase("JOIN THE WAITLIST")))
										throw new Exception("Text change for 'JOIN THE WAITLIST' ");

									flag = true;

								}
								else 
								{

									WebElement el = driver.findElement(By.cssSelector("div.radios-wrapper > div.radio-options-container > div > label:nth-child("+i+")"));
									jse = (JavascriptExecutor)driver;
									jse.executeScript("arguments[0].click();", el);

									System.out.println("Unavailable size clicked");
									System.out.println(driver.findElement(ADD_TO_CART_BUTTON).getText());
									if (!driver.findElement(ADD_TO_CART_BUTTON).getText().contentEquals("Out of Stock"))
									{
										element = driver.findElement(ProductDetailsPageComponents.JOIN_THE_WAITLIST);
										 text1 = element.getText();
										System.out.println("Text of button when Unavailable size selected :: "+text1);
										if(!(text1.equalsIgnoreCase("JOIN THE WAITLIST")))
											throw new Exception("Text change for 'JOIN THE WAITLIST' for Packs");
										text1 = element.getText();
									}
									flag = true;
								}
								//break;
							}
						}
						else 
						{
							jse = (JavascriptExecutor)driver;
							jse.executeScript("arguments[0].click();" , element);

						}


						if(i==elementsCount) {
							Reporter.log("No any size available");
							break;
						}	

						if(flag == true)
							continue;
						else {
							element = driver.findElement(ADD_TO_CART_BUTTON);
							text1 = element.getText();
							System.out.println("Button text when Available size selected :: "+text1);
							if(!text1.equalsIgnoreCase("OUT OF STOCK"))
							{
								if(!(text1.equalsIgnoreCase("ADD TO CART"))) {
									throw new Exception("Text change for 'ADD TO CART' ");
								}
							}
							break;}

					}

				}

			    }
			    catch(Exception e)
			    {
					//dropdown
							
					for(int i=1;i<=elementsCount;i++) {
						boolean flag = false;
						//driver.findElement(SIZE_DROPDOWN).click();
						executor.executeScript("arguments[0].click();", driver.findElement(ProductDetailsPageComponents.SIZE_DROPDOWN));
						element = driver.findElement(By.cssSelector(".select--options__list > li:nth-child("+i+") > div"));
						
						allClasses = element.getAttribute("class");
						
						// check size available or not
						for (String c : allClasses.split(" ")) {
							if (c.equals("unavailable")) {
								if(!driver.findElement(ProductDetailsPageComponents.PRODUCT_TITLE).getText().contains("Pack"))
								{
									Thread.sleep(3000);
									// click on the unavailable item and check the button text
								WebElement element1 = driver.findElement(By.cssSelector(".select--options__list > li:nth-child("+i+")"));
								jse = (JavascriptExecutor)driver;
								jse.executeScript("arguments[0].click();" , element1);
									//element.click();
									Thread.sleep(4000);
							
									driver.navigate().refresh();
//									driver.findElement(By.cssSelector(".swym-tab-modal-close")).click();
//									}		
//									catch (Exception e)
//									{
//										Reporter.log("Join the waitlist not functional at the moment");
//										
//									}
										
									System.out.println("Unavailable size clicked");
									
									List<WebElement> unavailable_color = driver.findElements(By.cssSelector(".product-option__color-swatches-wrapper > ul >li>input")); 
									for(int a=1; a<=unavailable_color.size();a++)
									{
										WebElement oos_color = driver.findElement(By.cssSelector(".product-option__color-swatches-wrapper > ul >li:nth-child("+a+")>input"));
										//oos_color.click();
										jse.executeScript("arguments[0].click();", oos_color);
										Thread.sleep(3000);
										String[] classname = oos_color.getAttribute("class").split(" ");
										String status = classname[classname.length-1];
										if(status.equals("email-when-in-stock"))
											break;
										else continue;
									}
									
									element = driver.findElement(ProductDetailsPageLocators.JOIN_THE_WAITLIST);
									text1 = element.getText();
									System.out.println("Text of button when Unavailable size selected :: "+text1);
									if(!(text1.equalsIgnoreCase("JOIN THE WAITLIST")))
										throw new Exception("Text change for 'JOIN THE WAITLIST' ");
									
									flag = true;
								}
								else 
								{
									driver.findElement(By.cssSelector(".select--options__list> li:nth-child("+i+")")).click();
									System.out.println("Unavailable size clicked");
								//	driver.findElement(ADD_TO_CART_BUTTON).click();
									System.out.println(driver.findElement(ADD_TO_CART_BUTTON).getText());
									if (!driver.findElement(ADD_TO_CART_BUTTON).getText().contentEquals("Out of Stock"))
									{
										element = driver.findElement(ProductDetailsPageComponents.JOIN_THE_WAITLIST);
										text1 = element.getText();
										System.out.println("Text of button when Unavailable size selected :: "+text1);
										if(!(text1.equalsIgnoreCase("JOIN THE WAITLIST")))
											throw new Exception("Text change for 'JOIN THE WAITLIST' for Packs");
										text1 = element.getText();
									}
									flag = true;
								}
								//break;
							}
					
							else 
							{
								jse = (JavascriptExecutor)driver;
								jse.executeScript("arguments[0].click();" , element);
								driver.navigate().refresh();
							//	element.click();
							}
						}
						if(i==elementsCount) {
							Reporter.log("No  size available");
							break;
						}			
						if(flag == true)
							continue;
						else {
						Thread.sleep(3000);
							element = driver.findElement(ADD_TO_CART_BUTTON);
							text1 = element.getText();
							System.out.println("Button text when Available size selected :: "+text1);
							if(!text1.equalsIgnoreCase("OUT OF STOCK"))
							{
								if(!(text1.equalsIgnoreCase("ADD TO CART"))) {
									throw new Exception("Text change for 'ADD TO CART' ");
								}
							}
			
			
					Thread.sleep(2000);
					break;
				}
			}
					
			    }
	}

	public void atc() throws Exception
	{
		// click on Add To Cart Button
		if(!driver.findElement(ADD_TO_CART_BUTTON).isEnabled())
			throw new Exception("ADD TO CART button is not present");
		element = driver.findElement(ADD_TO_CART_BUTTON);
		jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click();", element);

		Thread.sleep(5000);
	driver.get(CustomUtilities.baseUrl);
		//	driver.findElement(ADD_TO_CART_BUTTON).click();
		Thread.sleep(3000);
		Reporter.log("ADD TO CART button is Displayed :: Clickable");

	}

	public String addsingleproductToCartforFirst20Coupon() throws Exception {


		// call method to navigate to collection page
		new HomePageComponents(driver).navigateToAllPantiesnInWomenCategory();
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)");
		// call method to navigate product details page
		text = new CollectionPageComponent(driver).navigateToPDP();

		Thread.sleep(2000);

		allElements = driver.findElements(ProductDetailsPageComponents.COLOR_VARIANT);

		elementsCount = allElements.size();
		System.out.println("Color Element count :: " + elementsCount);

		if (elementsCount > 1) {
			for (int i = 1; i <= 3; i++) {

				WebElement elemen = driver.findElement(By.xpath("(//div[@class='space-y-lg']//span[@class='sr-only'])[" + i + "]"));
				jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].click();", elemen);
				Thread.sleep(3000);


				WebElement elements = driver.findElement(By.xpath("(//input[@name='option[Size]'])[" + i + "]"));
				jse.executeScript("arguments[0].click();", elements);
				Thread.sleep(1000);

				String[] AddtoBag;
				AddtoBag = (driver.findElement(By.xpath("//button[@class='button grow']")).getText()).split(" -");
				String text1 = AddtoBag[0];
				System.out.println(text1);

				//	WebElement elem = driver.findElement(By.cssSelector(".multiPdp__atc-container>button"));


				if (driver.findElement(ProductDetailsPageComponents.ADD_TO_CART_BUTTON).getText().contains(text1)) {

					System.out.println(elemen.getText() + elements.getText() + "Product is added to Cart");
					Thread.sleep(5000);
					jse.executeScript("arguments[0].click();", driver.findElement(ADD_TO_CART_BUTTON));
					Thread.sleep(3000);
					driver.findElement(INLINE_CART_TITLE);
					jse.executeScript("arguments[0].click();", driver.findElement(INLINE_CART_CLOSE));

					Thread.sleep(1000);
					System.out.println("=============================");
					break;

				}


//						if(driver.findElement(ProductDetailsPageComponents.ADD_TO_CART_BUTTON).getText().contains("Add to Cart")) {
//
//							System.out.println(elemen.getText() + elements.getText() + "Product is added to Cart");
//							jse.executeScript("arguments[0].click();", driver.findElement(ADD_TO_CART_BUTTON));
//							Thread.sleep(2000);
//							jse.executeScript("arguments[0].click();", driver.findElement(INLINE_CART_CLOSE));
//							break;
//
//						}


			}

			driver.get("https://www.tommyjohn.com/");

		}
		return text;

	}
	
	public String addsingleproductToCart() throws Exception {


		// call method to navigate to collection page
		new HomePageComponents(driver).navigateToMensAll();
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)");
		// call method to navigate product details page
		text = new CollectionPageComponent(driver).navigateToPDP();

		Thread.sleep(2000);

		allElements = driver.findElements(ProductDetailsPageComponents.COLOR_VARIANT);

		elementsCount = allElements.size();
		System.out.println("Color Element count :: " + elementsCount);

		if (elementsCount > 1) {
			for (int i = 1; i <= 3; i++) {

				WebElement elemen = driver.findElement(By.xpath("(//div[@class='space-y-lg']//span[@class='sr-only'])[" + i + "]"));
				jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].click();", elemen);
				Thread.sleep(3000);


				WebElement elements = driver.findElement(By.xpath("(//input[@name='option[Size]'])[" + i + "]"));
				jse.executeScript("arguments[0].click();", elements);
				Thread.sleep(1000);

				String[] AddtoBag;
				AddtoBag = (driver.findElement(By.xpath("//button[@class='button grow']")).getText()).split(" -");
				String text1 = AddtoBag[0];
				System.out.println(text1);

				//	WebElement elem = driver.findElement(By.cssSelector(".multiPdp__atc-container>button"));


				if (driver.findElement(ProductDetailsPageComponents.ADD_TO_CART_BUTTON).getText().contains(text1)) {

					System.out.println(elemen.getText() + elements.getText() + "Product is added to Cart");
					Thread.sleep(5000);
					jse.executeScript("arguments[0].click();", driver.findElement(ADD_TO_CART_BUTTON));
					Thread.sleep(3000);
					driver.findElement(INLINE_CART_TITLE);
					jse.executeScript("arguments[0].click();", driver.findElement(INLINE_CART_CLOSE));

					Thread.sleep(1000);
					System.out.println("=============================");
					break;

				}


//						if(driver.findElement(ProductDetailsPageComponents.ADD_TO_CART_BUTTON).getText().contains("Add to Cart")) {
//
//							System.out.println(elemen.getText() + elements.getText() + "Product is added to Cart");
//							jse.executeScript("arguments[0].click();", driver.findElement(ADD_TO_CART_BUTTON));
//							Thread.sleep(2000);
//							jse.executeScript("arguments[0].click();", driver.findElement(INLINE_CART_CLOSE));
//							break;
//
//						}


			}

			driver.get("https://www.tommyjohn.com/");

		}
		return text;

    }

	public String add3for$48product() throws Exception {


		// call method to navigate to collection page
		new HomePageComponents(driver).navigateToAllPantiesnInWomenCategory();
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)");
		// call method to navigate product details page
		text = new CollectionPageComponent(driver).navigateToPDP();

		Thread.sleep(2000);

		allElements = driver.findElements(ProductDetailsPageComponents.COLOR_VARIANT);

		elementsCount = allElements.size();
		System.out.println("Color Element count :: " + elementsCount);

		if (elementsCount > 1) {
			for (int i = 1; i <= 3; i++) {

				WebElement elemen = driver.findElement(By.xpath("(//div[@class='space-y-lg']//span[@class='sr-only'])[" + i + "]"));
				jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].click();", elemen);
				Thread.sleep(3000);


				WebElement elements = driver.findElement(By.xpath("(//input[@name='option[Size]'])[" + i + "]"));
				jse.executeScript("arguments[0].click();", elements);
				Thread.sleep(1000);

				String[] AddtoBag;
				AddtoBag = (driver.findElement(By.xpath("//button[@class='button grow']")).getText()).split(" -");
				String text1 = AddtoBag[0];
				System.out.println(text1);

				//	WebElement elem = driver.findElement(By.cssSelector(".multiPdp__atc-container>button"));


				if (driver.findElement(ProductDetailsPageComponents.ADD_TO_CART_BUTTON).getText().contains(text1)) {

					System.out.println(elemen.getText() + elements.getText() + "Product is added to Cart");
					Thread.sleep(5000);
					jse.executeScript("arguments[0].click();", driver.findElement(ADD_TO_CART_BUTTON));
					Thread.sleep(3000);
					driver.findElement(INLINE_CART_TITLE);
					jse.executeScript("arguments[0].click();", driver.findElement(INLINE_CART_CLOSE));

					Thread.sleep(1000);
					System.out.println("=============================");
//					break;

				}


//						if(driver.findElement(ProductDetailsPageComponents.ADD_TO_CART_BUTTON).getText().contains("Add to Cart")) {
//
//							System.out.println(elemen.getText() + elements.getText() + "Product is added to Cart");
//							jse.executeScript("arguments[0].click();", driver.findElement(ADD_TO_CART_BUTTON));
//							Thread.sleep(2000);
//							jse.executeScript("arguments[0].click();", driver.findElement(INLINE_CART_CLOSE));
//							break;
//
//						}


			}

			driver.get("https://www.tommyjohn.com/");

		}
		return text;

	}

















}
