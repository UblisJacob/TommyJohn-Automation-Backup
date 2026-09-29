package com.tommyjohn.automation.Components;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import com.base.ExtentReport;
import com.tommyjohn.automation.PageLocators.*;
import com.tommyjohn.automation.Pages.ShoppingCartPage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;


import com.tommyjohn.automation.utils.CustomUtilities;

import static com.tommyjohn.automation.ADACheck.ADACheckMethods.softAssert;
import static com.tommyjohn.automation.PageLocators.ProductDetailsPageLocators.CART_ICON;
import static com.tommyjohn.automation.PageLocators.ProductDetailsPageLocators.PDP_TABS;
import static com.tommyjohn.automation.PageLocators.ShoppingCartLocators.CART_TOTAL;
import static com.tommyjohn.automation.PageLocators.ShoppingCartLocators.QUANTITY_INPUT;

public class AddToCartComponents extends AddToCartLocators{

	public AddToCartComponents(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriver driver;
	public WebElement element;
	public Actions action;
	public JavascriptExecutor jse;
	List<WebElement> RemoveItems;
	Properties properties = CustomUtilities.properties;
	List<WebElement> allElements;
	int elementsCount;
	String text = null;
	List<WebElement> Tabs;

	String allClasses = null;

	public void addProductsForGWP() throws Exception {

		WebDriverWait wait =
				new WebDriverWait(driver, Duration.ofSeconds(60));

		JavascriptExecutor jse =
				(JavascriptExecutor) driver;

		double targetAmount = 2000.00;
		double cartTotal = 0.00;
		double productUnitPrice = 0.00;

		int maxProducts = 15;
		int productsAttempted = 0;

		// =============================================================
		// Store PDP URLs which were already attempted
		// =============================================================

		Set<String> attemptedProducts =
				new HashSet<>();

		System.out.println("==============================================");
		System.out.println("Starting GWP Product Addition");
		System.out.println("Target Cart Total :: $" + targetAmount);
		System.out.println("==============================================");

		while (cartTotal < targetAmount &&
				productsAttempted < maxProducts) {

			productUnitPrice = 0.00;

			// =========================================================
			// STEP 1: Navigate to Men's Underwear PLP
			// =========================================================

			System.out.println(
					"Navigating to Men's Underwear PLP..."
			);

			new HomePageComponents(driver)
					.navigateToAllUnderwearInMenCategory();

			Thread.sleep(3000);

			// =========================================================
			// STEP 2: Find Product Links on PLP
			// =========================================================

			List<WebElement> productLinks =
					driver.findElements(
							By.xpath(
									"//a[contains(@href,'/products/')]"
							)
					);

			System.out.println(
					"Product Links Found :: " +
							productLinks.size()
			);

			if (productLinks.isEmpty()) {

				throw new AssertionError(
						"No product links found on Men's Underwear PLP."
				);
			}

			boolean productSelected = false;

			// =========================================================
			// STEP 3: Select NEXT Unattempted Product
			// =========================================================

			for (WebElement productLink : productLinks) {

				try {

					String productUrl =
							productLink.getAttribute("href");

					if (productUrl == null ||
							productUrl.trim().isEmpty()) {

						continue;
					}

					// -------------------------------------------------
					// Remove query parameters
					// -------------------------------------------------

					productUrl =
							productUrl.split("\\?")[0];

					// -------------------------------------------------
					// Skip already attempted product
					// -------------------------------------------------

					if (attemptedProducts.contains(productUrl)) {

						continue;
					}

					System.out.println(
							"Selecting New Product :: " +
									productUrl
					);

					// -------------------------------------------------
					// Mark product as attempted
					// -------------------------------------------------

					attemptedProducts.add(
							productUrl
					);

					// -------------------------------------------------
					// Navigate directly to PDP
					// -------------------------------------------------

					driver.get(productUrl);

					Thread.sleep(3000);

					productSelected = true;

					break;

				} catch (Exception e) {

					System.out.println(
							"Unable to select product link."
					);
				}
			}

			// =========================================================
			// STEP 4: No New Product Available
			// =========================================================

			if (!productSelected) {

				System.out.println(
						"=============================================="
				);

				System.out.println(
						"No new products available to try."
				);

				System.out.println(
						"Products Attempted :: " +
								attemptedProducts.size()
				);

				System.out.println(
						"=============================================="
				);

				break;
			}

			productsAttempted++;

			System.out.println(
					"Products Attempted :: " +
							productsAttempted
			);

			// =========================================================
			// STEP 5: Find Color Variants
			// =========================================================

			List<WebElement> colorVariants =
					driver.findElements(
							ProductDetailsPageComponents.COLOR_VARIANT
					);

			System.out.println(
					"Color Element count :: " +
							colorVariants.size()
			);

			boolean productAdded = false;

			// =========================================================
			// STEP 6: Find Available Color + Size
			// =========================================================

			if (!colorVariants.isEmpty()) {

				for (int colorIndex = 1;
					 colorIndex <= colorVariants.size() &&
							 !productAdded;
					 colorIndex++) {

					try {

						WebElement colorElement =
								driver.findElement(
										By.xpath(
												"(//div[@class='space-y-lg']//input[@class='sr-only peer'])[" +
														colorIndex +
														"]"
										)
								);

						jse.executeScript(
								"arguments[0].click();",
								colorElement
						);

						Thread.sleep(1500);

						// -------------------------------------------------
						// Get Sizes
						// -------------------------------------------------

						List<WebElement> sizeElements =
								driver.findElements(
										By.xpath(
												"//input[@name='option[Size]']"
										)
								);

						System.out.println(
								"Size Element count :: " +
										sizeElements.size()
						);

						for (int sizeIndex = 1;
							 sizeIndex <= sizeElements.size();
							 sizeIndex++) {

							try {

								WebElement sizeElement =
										driver.findElement(
												By.xpath(
														"(//input[@name='option[Size]'])[" +
																sizeIndex +
																"]"
												)
										);

								jse.executeScript(
										"arguments[0].click();",
										sizeElement
								);

								Thread.sleep(1200);

								// -------------------------------------------------
								// Get ATC Button
								// -------------------------------------------------

								WebElement addToCartButton =
										wait.until(
												ExpectedConditions
														.visibilityOfElementLocated(
																ProductDetailsPageComponents.ADD_TO_CART_BUTTON
														)
										);

								String buttonText =
										addToCartButton
												.getText()
												.trim();

								System.out.println(
										"ATC Button Text :: " +
												buttonText
								);

								// =================================================
								// Extract Price From ATC Button
								// =================================================

								productUnitPrice = 0.00;

								if (buttonText != null &&
										buttonText.contains("$")) {

									try {

										String priceText =
												buttonText.substring(
														buttonText.lastIndexOf("$") + 1
												).trim();

										productUnitPrice =
												Double.parseDouble(
														priceText.replaceAll(
																"[^0-9.]",
																""
														)
												);

										System.out.println(
												"Product Unit Price From ATC :: $" +
														productUnitPrice
										);

									} catch (Exception e) {

										System.out.println(
												"Unable to extract product price."
										);
									}
								}

								// =================================================
								// Check Available Product
								// =================================================

								if (buttonText != null &&
										buttonText
												.toLowerCase()
												.contains("add to bag") &&
										!buttonText
												.toLowerCase()
												.contains("out of stock") &&
										productUnitPrice > 0) {

									System.out.println(
											"Available product variant found."
									);

									System.out.println(
											"Color Index :: " +
													colorIndex
									);

									System.out.println(
											"Size Index :: " +
													sizeIndex
									);

									// -------------------------------------------------
									// Add Product
									// -------------------------------------------------

									jse.executeScript(
											"arguments[0].click();",
											addToCartButton
									);

									Thread.sleep(3000);

									System.out.println(
											"Product successfully added to cart."
									);

									productAdded = true;

									break;
								}

							} catch (Exception e) {

								System.out.println(
										"Size " +
												sizeIndex +
												" unavailable."
								);
							}
						}

					} catch (Exception e) {

						System.out.println(
								"Color " +
										colorIndex +
										" unavailable."
						);
					}
				}
			}

			// =========================================================
			// STEP 7: Product Could Not Be Added
			// =========================================================

			if (!productAdded) {

				System.out.println(
						"No available variant found for this product."
				);

				System.out.println(
						"Trying next product..."
				);

				driver.get(
						"https://www.tommyjohn.com/"
				);

				Thread.sleep(3000);

				continue;
			}

			// =========================================================
			// STEP 8: Open Cart
			// =========================================================

			System.out.println(
					"Opening cart..."
			);

			driver.get(
					"https://www.tommyjohn.com/"
			);

			Thread.sleep(3000);

			WebElement cartIcon =
					wait.until(
							ExpectedConditions
									.elementToBeClickable(
											CART_ICON
									)
					);

			jse.executeScript(
					"arguments[0].click();",
					cartIcon
			);

			WebElement viewCartLink =
					wait.until(
							ExpectedConditions
									.elementToBeClickable(
											FlyCartPageLocator.VIEW_CART_LINK
									)
					);

			jse.executeScript(
					"arguments[0].click();",
					viewCartLink
			);

			Thread.sleep(4000);

			// =========================================================
			// STEP 9: Get Current Cart Total
			// =========================================================

			cartTotal =
					getCurrentCartTotal(wait);

			System.out.println(
					"Current Cart Total :: $" +
							cartTotal
			);

			// =========================================================
			// STEP 10: Check Threshold
			// =========================================================

			if (cartTotal >= targetAmount) {

				System.out.println(
						"=============================================="
				);

				System.out.println(
						"GWP THRESHOLD REACHED"
				);

				System.out.println(
						"Cart Total :: $" +
								cartTotal
				);

				System.out.println(
						"=============================================="
				);

				break;
			}

			// =========================================================
			// STEP 11: Get Quantity Input
			// =========================================================

			try {

				WebElement quantityInput =
						wait.until(
								ExpectedConditions
										.visibilityOfElementLocated(
												QUANTITY_INPUT
										)
						);

				// ---------------------------------------------------------
				// Current Quantity
				// ---------------------------------------------------------

				int currentQuantity =
						Integer.parseInt(
								jse.executeScript(
												"return arguments[0].value;",
												quantityInput
										)
										.toString()
										.trim()
						);

				System.out.println(
						"Current Quantity :: " +
								currentQuantity
				);

				// =========================================================
				// STEP 12: Read Maximum Quantity
				// =========================================================

				String maxAttribute =
						quantityInput.getAttribute("max");

				int maxQuantity = 1450;

				if (maxAttribute != null &&
						!maxAttribute.trim().isEmpty()) {

					try {

						maxQuantity =
								Integer.parseInt(
										maxAttribute.trim()
								);

					} catch (NumberFormatException ignored) {

						System.out.println(
								"Unable to parse max quantity."
						);
					}
				}

				System.out.println(
						"Maximum Quantity Allowed :: " +
								maxQuantity
				);

				// =========================================================
				// STEP 13: Calculate Remaining Amount
				// =========================================================

				double remainingAmount =
						targetAmount - cartTotal;

				System.out.println(
						"Remaining Amount Required :: $" +
								remainingAmount
				);

				// =========================================================
				// STEP 14: Calculate Additional Quantity
				// =========================================================

				int additionalQuantity =
						(int) Math.ceil(
								remainingAmount /
										productUnitPrice
						);

				System.out.println(
						"Additional Quantity Required :: " +
								additionalQuantity
				);

				// =========================================================
				// STEP 15: Calculate Final Quantity
				// =========================================================

				int requiredQuantity =
						currentQuantity +
								additionalQuantity;

				System.out.println(
						"Final Quantity Required :: " +
								requiredQuantity
				);

				// =========================================================
				// STEP 16: Check Maximum Quantity
				// =========================================================

				if (requiredQuantity > maxQuantity) {

					System.out.println(
							"Required quantity " +
									requiredQuantity +
									" exceeds maximum allowed quantity " +
									maxQuantity
					);

					System.out.println(
							"This product cannot reach the GWP threshold."
					);

					System.out.println(
							"Keeping this product in cart."
					);

					System.out.println(
							"Trying another product..."
					);

					// -----------------------------------------------------
					// DO NOT clear the cart.
					// Go to PLP and select a different product.
					// -----------------------------------------------------

					driver.get(
							"https://www.tommyjohn.com/"
					);

					Thread.sleep(3000);

					continue;
				}

				// =========================================================
				// STEP 17: Set Quantity
				// =========================================================

				System.out.println(
						"Setting Quantity To :: " +
								requiredQuantity
				);

				quantityInput.click();

				quantityInput.sendKeys(
						Keys.CONTROL,
						"a"
				);

				quantityInput.sendKeys(
						String.valueOf(requiredQuantity)
				);

				// =========================================================
				// STEP 18: Trigger Events
				// =========================================================

				jse.executeScript(
						"""
                        arguments[0].dispatchEvent(
                            new Event('input', {
                                bubbles: true
                            })
                        );
    
                        arguments[0].dispatchEvent(
                            new Event('change', {
                                bubbles: true
                            })
                        );
    
                        arguments[0].blur();
                        """,
						quantityInput
				);

				Thread.sleep(5000);

				// =========================================================
				// STEP 19: Verify Quantity
				// =========================================================

				WebElement updatedQuantityInput =
						wait.until(
								ExpectedConditions
										.visibilityOfElementLocated(
												QUANTITY_INPUT
										)
						);

				int updatedQuantity =
						Integer.parseInt(
								jse.executeScript(
												"return arguments[0].value;",
												updatedQuantityInput
										)
										.toString()
										.trim()
						);

				System.out.println(
						"Updated Quantity :: " +
								updatedQuantity
				);

				// =========================================================
				// STEP 20: Quantity Not Accepted
				// =========================================================

				if (updatedQuantity < requiredQuantity) {

					System.out.println(
							"Requested quantity " +
									requiredQuantity +
									" was not accepted."
					);

					System.out.println(
							"Actual Quantity :: " +
									updatedQuantity
					);

					System.out.println(
							"Trying another product..."
					);

					driver.get(
							"https://www.tommyjohn.com/"
					);

					Thread.sleep(3000);

					continue;
				}

				// =========================================================
				// STEP 21: Get Updated Cart Total
				// =========================================================

				Thread.sleep(3000);

				cartTotal =
						getCurrentCartTotal(wait);

				System.out.println(
						"Updated Cart Total :: $" +
								cartTotal
				);

			} catch (Exception e) {

				System.out.println(
						"Unable to update product quantity."
				);

				e.printStackTrace();

				System.out.println(
						"Trying another product..."
				);

				driver.get(
						"https://www.tommyjohn.com/"
				);

				Thread.sleep(3000);

				continue;
			}

			// =========================================================
			// STEP 22: Check Threshold
			// =========================================================

			if (cartTotal >= targetAmount) {

				System.out.println(
						"=============================================="
				);

				System.out.println(
						"GWP THRESHOLD REACHED"
				);

				System.out.println(
						"Final Cart Total :: $" +
								cartTotal
				);

				System.out.println(
						"=============================================="
				);

				break;
			}

			// =========================================================
			// STEP 23: Continue With Another Product
			// =========================================================

			System.out.println(
					"Cart total is still below $2000."
			);

			System.out.println(
					"Current Cart Total :: $" +
							cartTotal
			);

			System.out.println(
					"Navigating to PLP for another product..."
			);

			driver.get(
					"https://www.tommyjohn.com/"
			);

			Thread.sleep(3000);
		}

		// =============================================================
		// FINAL VALIDATION
		// =============================================================

		if (cartTotal < targetAmount) {

			throw new AssertionError(
					"Unable to reach GWP threshold of $2000. " +
							"Final Cart Total :: $" +
							cartTotal +
							". Products Attempted :: " +
							attemptedProducts.size()
			);
		}

		System.out.println(
				"=============================================="
		);

		System.out.println(
				"FINAL GWP CART TOTAL :: $" +
						cartTotal
		);

		System.out.println(
				"GWP Threshold $2000 Successfully Reached."
		);

		System.out.println(
				"Products Attempted :: " +
						attemptedProducts.size()
		);

		System.out.println(
				"=============================================="
		);
	}

	private double getCurrentCartTotal(WebDriverWait wait) {

		try {

			WebElement cartTotalElement =
					wait.until(
							ExpectedConditions
									.visibilityOfElementLocated(
											CART_TOTAL
									)
					);

			String totalText =
					cartTotalElement
							.getText()
							.trim();

			System.out.println(
					"Cart Total Text :: " +
							totalText
			);

			String numericTotal =
					totalText.replaceAll(
							"[^0-9.]",
							""
					);

			return Double.parseDouble(
					numericTotal
			);

		} catch (Exception e) {

			System.out.println(
					"Unable to read cart total."
			);

			e.printStackTrace();

			return 0.00;
		}
	}

	private double getCartLineItemUnitPrice() {

		try {

			By unitPriceLocator =
					By.xpath(
							"//span[normalize-space()='Regular price']/following-sibling::span[1]"
					);

			WebElement priceElement =
					new WebDriverWait(
							driver,
							Duration.ofSeconds(30)
					).until(
							ExpectedConditions
									.visibilityOfElementLocated(
											unitPriceLocator
									)
					);

			String priceText =
					priceElement.getText().trim();

			System.out.println(
					"Unit Price Text :: " +
							priceText
			);

			String numericPrice =
					priceText.replaceAll(
							"[^0-9.]",
							""
					);

			double unitPrice =
					Double.parseDouble(
							numericPrice
					);

			System.out.println(
					"Unit Price :: $" +
							unitPrice
			);

			return unitPrice;

		} catch (Exception e) {

			System.out.println(
					"Unable to get cart line-item unit price."
			);

			e.printStackTrace();

			return 0.00;
		}
	}

	// Checks if the specified offer messaging is visible on the full /cart page
	public boolean isCartPageOfferMessagingDisplayed(String offerText) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			By locator = ShoppingCartLocators.getCartPageOfferMessageLocator(offerText);
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void AddSingleProduct() throws Exception {
		new HomePageComponents(driver).navigateToAllPantiesInWomenCategory();
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

	public String NonFreeShippingAddToCart() throws Exception {

		int count = 0;

		// call method to navigate to collection page
		new HomePageComponents(driver).navigateToMensUnderwear();
		jse=(JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)");
		// call method to navigate product details page
		text = new CollectionPageComponent(driver).navigateToProductDetailsPage();
		Tabs = driver.findElements(PDP_TABS);
		System.out.println(Tabs.size());
		if(Tabs.size()>=3)
		{
			jse.executeScript("arguments[0].click();", driver.findElement(By.xpath("(//span[@class='text-sm text-text-tertiary'])[1]")));
		}

		Thread.sleep(2000);

		allElements = driver.findElements(ProductDetailsPageComponents.COLOR_VARIANT);

		elementsCount = allElements.size();
		System.out.println("Color Element count :: "+elementsCount);

		if(elementsCount > 1)
		{
			for(int i=1;i<=4;i++)
			{

				WebElement elemen = driver.findElement(By.xpath("(//div[@class='space-y-lg']//input[@class='sr-only peer'])[" + i + "]"));
				jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].click();", elemen);
				Thread.sleep(3000);

				for(int k=1;k<=4;k++)
				{
					WebElement elements = driver.findElement(By.xpath("(//input[@name='option[Size]'])["+k+"]"));
					jse.executeScript("arguments[0].click();", elements);
					Thread.sleep(1000);

					try {
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
							count++;

						}
					}
					catch(Exception ex)
					{
						System.out.println("Product is OOS");

					}

//
//						WebElement elem = driver.findElement(By.cssSelector(".multiPdp__atc-container>button"));
//i
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
					if(count==1)
					{
						break;
					}

				}


				if(count==1)
				{
					break;
				}

			}

		}
		return text;

	}

	public String addToCart() throws Exception {

		int count = 0;

		// call method to navigate to collection page
		new HomePageComponents(driver).navigateToMensAll();
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
			for(int i=1;i<=5;i++)
			{

				WebElement elemen = driver.findElement(By.xpath("(//div[@class='space-y-lg']//input[@class='sr-only peer'])["+i+"]"));
				jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].click();", elemen);
				Thread.sleep(3000);

					for(int k=1;k<=5;k++)
					{
						WebElement elements = driver.findElement(By.xpath("(//input[@name='option[Size]'])["+k+"]"));
						jse.executeScript("arguments[0].click();", elements);
						Thread.sleep(1000);

						try {
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
								count++;

							}
						}
						catch(Exception ex)
						{
							System.out.println("Product is OOS");

						}

						if(count==3)
						{
							break;
						}

					}


					if(count==3)
					{
						break;
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

	public String addmultipackbundleproduct() throws Exception {

		int count =0;
		// call method to navigate to collection page
		new HomePageComponents(driver).navigateToMultipackBundleProduct();
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)");
		// call method to navigate product details page
	//	text = new CollectionPageComponent(driver).navigateToPDP();

		Thread.sleep(2000);

//		jse.executeScript("arguments[0].click();", driver.findElement(By.xpath("(//span[@class='text-sm text-text-tertiary'])[1]")));

		allElements = driver.findElements(ProductDetailsPageComponents.COLOR_VARIANT);

		elementsCount = allElements.size();
		System.out.println("Color Element count :: " + elementsCount);

		if (elementsCount > 1) {
			for (int i = 4; i <= elementsCount; i++) {

                WebElement elemen = driver.findElement(By.xpath("(//div[@class='space-y-lg']//input[@class='sr-only peer'])[" + i + "]"));
                jse = (JavascriptExecutor) driver;
                jse.executeScript("arguments[0].click();", elemen);
                Thread.sleep(3000);

                for (int j = 1; j <= 5; j++) {

                    WebElement elements = driver.findElement(By.xpath("(//input[@name='option[Top Size]'])[" + j + "]"));
                    jse.executeScript("arguments[0].click();", elements);
                    Thread.sleep(1000);

                    for (int k = 1; k <= 5; k++) {
                        WebElement elements1 = driver.findElement(By.xpath("(//input[@name='option[Bottom Size]'])[" + k + "]"));
                        jse.executeScript("arguments[0].click();", elements1);
                        Thread.sleep(1000);

                        try {
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
                                count++;


                            }

                        } catch (Exception ex) {
                            System.out.println("Product is OOS");
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

                        if (count == 1) {
                            break;
                        }

                    }
                    if (count == 1) {
                        break;
                    }

                }

                if (count == 1) {
                    break;
                }

            }

			driver.get("https://www.tommyjohn.com/");

		}
		return text;
	}

	public String addSingleSplitproduct() throws Exception {

		int count =0;
		// call method to navigate to collection page
		new HomePageComponents(driver).navigateToMultipackBundleProduct();
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)");
		// call method to navigate product details page
	//	text = new CollectionPageComponent(driver).navigateToPDP();

		Thread.sleep(2000);

//		jse.executeScript("arguments[0].click();", driver.findElement(By.xpath("(//span[@class='text-sm text-text-tertiary'])[1]")));

		allElements = driver.findElements(ProductDetailsPageComponents.COLOR_VARIANT);

		elementsCount = allElements.size();
		System.out.println("Color Element count :: " + elementsCount);

		if (elementsCount > 1) {
			for (int i = 6; i <= elementsCount; i++) {

				WebElement elemen = driver.findElement(By.xpath("(//div[@class='space-y-lg']//input[@class='sr-only peer'])[" + i + "]"));
				jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].click();", elemen);
				Thread.sleep(3000);

				for (int j = 1; j <= 5; j++) {

					WebElement elements = driver.findElement(By.xpath("(//input[@name='option[Size]'])[" + j + "]"));
					jse.executeScript("arguments[0].click();", elements);
					Thread.sleep(1000);

					try {
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
							count++;


						}

					}
					catch(Exception ex)
					{
						System.out.println("Product is OOS");
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

					if(count==2)
					{
						break;
					}

				}
				if(count==2)
				{
					break;
				}
			}

			driver.get("https://www.tommyjohn.com/");
			Thread.sleep(20000);
			jse.executeScript("arguments[0].click();", driver.findElement(CART_ICON));
			driver.get("https://www.tommyjohn.com/");

		}
		return text;
	}

	public String addmultipleproductToCartforFreeOrder() throws Exception {

		int count =0;
		// call method to navigate to collection page
		new HomePageComponents(driver).navigateToMensAllUnderwear();
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)");
		// call method to navigate product details page
		text = new CollectionPageComponent(driver).navigateToPDP();

		Thread.sleep(5000);

		jse.executeScript("arguments[0].click();", driver.findElement(By.xpath("(//span[@class='text-sm text-text-tertiary'])[1]")));

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(ProductDetailsPageComponents.COLOR_VARIANT));

		allElements = driver.findElements(ProductDetailsPageComponents.COLOR_VARIANT);

		elementsCount = allElements.size();
		System.out.println("Color Element count :: " + elementsCount);

		if (elementsCount > 1) {
			for (int i = 1; i <= elementsCount; i++) {

				WebElement elemen = driver.findElement(By.xpath("(//div[@class='space-y-lg']//input[@class='sr-only peer'])[" + i + "]"));
				jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].click();", elemen);
				Thread.sleep(3000);

				for (int j = 1; j <= 5; j++) {

					WebElement elements = driver.findElement(By.xpath("(//input[@name='option[Size]'])[" + j + "]"));
					jse.executeScript("arguments[0].click();", elements);
					Thread.sleep(1000);

					try {
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
							count++;


						}

					}
					catch(Exception ex)
					{
						System.out.println("Product is OOS");
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

					if(count==4)
					{
						break;
					}

				}
				if(count==4)
				{
					break;
				}
			}

			driver.get("https://www.tommyjohn.com/");
			Thread.sleep(20000);
			jse.executeScript("arguments[0].click();", driver.findElement(CART_ICON));
			driver.get("https://www.tommyjohn.com/");

		}
		return text;
	}

	public String addsingleproductToCartforFirst20Coupon() throws Exception {

		int count =0;
		// call method to navigate to collection page
		new HomePageComponents(driver).navigateToMensAllUnderwear();
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)");
		// call method to navigate product details page
		text = new CollectionPageComponent(driver).navigateToPDP();

		Thread.sleep(5000);

		jse.executeScript("arguments[0].click();", driver.findElement(By.xpath("(//span[@class='text-sm text-text-tertiary'])[1]")));

		allElements = driver.findElements(ProductDetailsPageComponents.COLOR_VARIANT);

		elementsCount = allElements.size();
		System.out.println("Color Element count :: " + elementsCount);

		if (elementsCount > 1) {
			for (int i = 1; i <= elementsCount; i++) {

				WebElement elemen = driver.findElement(By.xpath("(//div[@class='space-y-lg']//input[@class='sr-only peer'])[" + i + "]"));
				jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].click();", elemen);
				Thread.sleep(3000);

				for (int j = 1; j <= 5; j++) {

					WebElement elements = driver.findElement(By.xpath("(//input[@name='option[Size]'])[" + j + "]"));
					jse.executeScript("arguments[0].click();", elements);
					Thread.sleep(1000);

					try {
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
							count++;


						}

					}
					catch(Exception ex)
					{
						System.out.println("Product is OOS");
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

					if(count==2)
					{
						break;
					}

				}
				if(count==2)
				{
					break;
				}
			}

			driver.get("https://www.tommyjohn.com/");
			Thread.sleep(20000);
			jse.executeScript("arguments[0].click();", driver.findElement(CART_ICON));
			driver.get("https://www.tommyjohn.com/");

		}
		return text;
	}

	public String addFinalSaleProducts() throws Exception {

		int count =0;
		// call method to navigate to collection page
		new HomePageComponents(driver).navigateToAllSales();
		jse = (JavascriptExecutor) driver;
//		jse.executeScript("window.scrollBy(0,250)");
		// call method to navigate product details page
		text = new CollectionPageComponent(driver).navigateToFinalSalePDP();

		Thread.sleep(2000);

		try {
			jse.executeScript("arguments[0].click();", driver.findElement(By.xpath("(//span[@class='text-sm text-text-tertiary'])[1]")));
		}
		catch(Exception ex)
		{
			System.out.println("No Tabs Found");
		}

		allElements = driver.findElements(ProductDetailsPageComponents.COLOR_VARIANT);

		elementsCount = allElements.size();
		System.out.println("Color Element count :: " + elementsCount);

		if (elementsCount > 1) {
			for (int i = 1; i <= elementsCount; i++) {

				WebElement elemen = driver.findElement(By.xpath("(//div[@class='space-y-lg']//input[@class='sr-only peer'])[" + i + "]"));
				jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].click();", elemen);
				Thread.sleep(3000);

				for (int j = 1; j <= 5; j++) {

					WebElement elements = driver.findElement(By.xpath("(//input[@name='option[Size]'])[" + j + "]"));
					jse.executeScript("arguments[0].click();", elements);
					Thread.sleep(1000);

					try {
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
							count++;


						}

					}
					catch(Exception ex)
					{
						System.out.println("Product is OOS");
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

					if(count==2)
					{
						break;
					}

				}
				if(count==2)
				{
					break;
				}
			}

			driver.get("https://www.tommyjohn.com/");

		}
		return text;

	}

	public String addGCToCart() throws Exception {
		int count=0;
		// call method to navigate to collection page
		new HomePageComponents(driver).navigateToGiftCard();
		jse = (JavascriptExecutor) driver;
//		jse.executeScript("window.scrollBy(0,250)");
//		// call method to navigate product details page
//		text = new CollectionPageComponent(driver).navigateToPDP();
		Thread.sleep(2000);

		allElements = driver.findElements(ProductDetailsPageComponents.GC_VARIANT);

		elementsCount = allElements.size();
		System.out.println("Color Element count :: " + elementsCount);

		if (elementsCount >= 1) {
			for (int i = 1; i <= elementsCount; i++) {

				WebElement elemen = driver.findElement(By.xpath("(//input[@name='option[Amount]'])[" + i + "]"));
				String Amount = driver.findElement(By.xpath("(//input[@name='option[Amount]'])[" + i + "]")).getAttribute("value");
				jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].click();", elemen);
				Thread.sleep(3000);

					try {
						String[] AddtoBag;
						AddtoBag = (driver.findElement(By.xpath("//button[@class='button grow']")).getText()).split(" -");
						String text1 = AddtoBag[0];
						System.out.println(text1);

						//	WebElement elem = driver.findElement(By.cssSelector(".multiPdp__atc-container>button"));


						if (driver.findElement(ProductDetailsPageComponents.ADD_TO_CART_BUTTON).getText().contains(text1)) {

							System.out.println(Amount +  "is added to Cart");
							Thread.sleep(5000);
							jse.executeScript("arguments[0].click();", driver.findElement(ADD_TO_CART_BUTTON));
							Thread.sleep(3000);
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
//						if(driver.findElement(ProductDetailsPageComponents.ADD_TO_CART_BUTTON).getText().contains("Add to Cart")) {
//
//							System.out.println(elemen.getText() + elements.getText() + "Product is added to Cart");
//							jse.executeScript("arguments[0].click();", driver.findElement(ADD_TO_CART_BUTTON));
//							Thread.sleep(2000);
//							jse.executeScript("arguments[0].click();", driver.findElement(INLINE_CART_CLOSE));
//							break;
//
//						}


	//			}
				if(count==1)
				{
					break;
				}
			}

			driver.get("https://www.tommyjohn.com/");

		}
		return text;
	}

	public String addsingleproductToCart() throws Exception {

		int count=0;
		// call method to navigate to collection page
		new HomePageComponents(driver).navigateToAllUnderwearInMenCategory();
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)");
		// call method to navigate product details page
		text = new CollectionPageComponent(driver).navigateToPDP();

		Thread.sleep(2000);

		allElements = driver.findElements(ProductDetailsPageComponents.COLOR_VARIANT);

		elementsCount = allElements.size();
		System.out.println("Color Element count :: " + elementsCount);

		if (elementsCount >= 1) {
			for (int i = 1; i <= elementsCount; i++) {

				WebElement elemen = driver.findElement(By.xpath("(//div[@class='space-y-lg']//input[@class='sr-only peer'])[" + i + "]"));
				jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].click();", elemen);
				Thread.sleep(3000);

				for (int j = 1; j <= 5; j++) {

					WebElement elements = driver.findElement(By.xpath("(//input[@name='option[Size]'])[" + j + "]"));
					jse.executeScript("arguments[0].click();", elements);
					Thread.sleep(1000);

					try {
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
							count++;

						}

					}
					catch(Exception ex)
					{
						System.out.println("Product is OOS");
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
					if(count==1)
					{
						break;
					}

				}
				if(count==1)
				{
					break;
				}
			}

			driver.get("https://www.tommyjohn.com/");
			Thread.sleep(20000);
			jse.executeScript("arguments[0].click();", driver.findElement(CART_ICON));
			driver.get("https://www.tommyjohn.com/");
			Thread.sleep(20000);
			jse.executeScript("arguments[0].click();", driver.findElement(CART_ICON));

		}
		return text;
    }

	public String addproductToCartforIncreasingQuantity() throws Exception {

		int count=0;
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

		if (elementsCount >= 1) {
			for (int i = 1; i <= elementsCount; i++) {

				WebElement elemen = driver.findElement(By.xpath("(//div[@class='space-y-lg']//input[@class='sr-only peer'])[" + i + "]"));
				jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].click();", elemen);
				Thread.sleep(3000);

				for (int j = 1; j <= 5; j++) {

					WebElement elements = driver.findElement(By.xpath("(//input[@name='option[Size]'])[" + j + "]"));
					jse.executeScript("arguments[0].click();", elements);
					Thread.sleep(1000);

					try {
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
							count++;

						}

					}
					catch(Exception ex)
					{
						System.out.println("Product is OOS");
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
					if(count==1)
					{
						break;
					}

				}
				if(count==1)
				{
					break;
				}
			}

			driver.get("https://www.tommyjohn.com/");
			Thread.sleep(20000);
			jse.executeScript("arguments[0].click();", driver.findElement(CART_ICON));

		}
		return text;
	}

	public String addsingleproductToCartKlaviyo() throws Exception {

		int count=0;
		// call method to navigate to collection page
		new HomePageComponents(driver).navigateToMensBriefs();
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)");
		// call method to navigate product details page
		text = new CollectionPageComponent(driver).navigateToPDP();

		Thread.sleep(2000);

		allElements = driver.findElements(ProductDetailsPageComponents.COLOR_VARIANT);

		elementsCount = allElements.size();
		System.out.println("Color Element count :: " + elementsCount);

		if (elementsCount > 1) {
			for (int i = 1; i <= elementsCount; i++) {

				WebElement elemen = driver.findElement(By.xpath("(//div[@class='space-y-lg']//input[@class='sr-only peer'])[" + i + "]"));
				jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].click();", elemen);
				Thread.sleep(3000);

				for (int j = 1; j <= 5; j++) {

					WebElement elements = driver.findElement(By.xpath("(//input[@name='option[Size]'])[" + j + "]"));
					jse.executeScript("arguments[0].click();", elements);
					Thread.sleep(1000);

					try {
						WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='button grow']")));
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

							Thread.sleep(10000);
							System.out.println("=============================");
							count++;

						}

					}
					catch(Exception ex)
					{
						System.out.println("Product is OOS");
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
					if(count==1)
					{
						break;
					}

				}
				if(count==1)
				{
					break;
				}
			}

		//	driver.get("https://www.tommyjohn.com/");

		}
		return text;
	}

	public String removesingleproductfromCart() throws Exception {

		int count=0;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
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
			for (int i = 1; i <= elementsCount; i++) {

				WebElement elemen = driver.findElement(By.xpath("(//div[@class='space-y-lg']//input[@class='sr-only peer'])[" + i + "]"));
				jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].click();", elemen);
				Thread.sleep(3000);

				for (int j = 1; j <= 5; j++) {

					WebElement elements = driver.findElement(By.xpath("(//input[@name='option[Size]'])[" + j + "]"));
					jse.executeScript("arguments[0].click();", elements);
					Thread.sleep(1000);

					try {
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
							count++;
						}

					}
					catch(Exception ex)
					{
						System.out.println("Product is OOS");
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
					if(count==3)
					{
						break;
					}

				}
				if(count==3)
				{
					break;
				}
			}

			driver.get("https://www.tommyjohn.com/");
			Thread.sleep(10000);
			jse.executeScript("arguments[0].click();", driver.findElement(HomePageLocators.CART_ICON));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='button-link text-body-xs text-text-tertiary' and contains(text(),'Remove')]")));
			RemoveItems = driver.findElements(By.xpath("//button[@class='button-link text-body-xs text-text-tertiary' and contains(text(),'Remove')]"));
			System.out.println(RemoveItems.size());
			for(int i=1; i<=RemoveItems.size(); i++) {
				Thread.sleep(10000);
				jse.executeScript("arguments[0].click();",driver.findElement(By.xpath("(//button[@class='button-link text-body-xs text-text-tertiary' and contains(text(),'Remove')])[1]")));
				System.out.println(i);
			}
			Thread.sleep(10000);
		}
		return text;
	}

	public String add7for$35products() throws Exception {

		int count = 0;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		JavascriptExecutor jse = (JavascriptExecutor) driver;

		driver.get("https://www.tommyjohn.com/products/womens-second-skin-brief-13?variant=44729634914349");
		wait.until(ExpectedConditions.visibilityOfElementLocated(ProductDetailsPageLocators.PRODUCT_TITLE));

		Reporter.log(driver.getCurrentUrl());
		ExtentReport.extentTest.info(driver.getCurrentUrl());

		Thread.sleep(2000);

		// Get all color variants
		List<WebElement> colorVariants = driver.findElements(ProductDetailsPageComponents.COLOR_VARIANT);
		System.out.println("Color Element count :: " + colorVariants.size());

		if (colorVariants.size() > 0) {

			for (int i = 0; i < colorVariants.size(); i++) {

				// Re-fetch color elements to avoid stale element
				colorVariants = driver.findElements(ProductDetailsPageComponents.COLOR_VARIANT);
				WebElement color = colorVariants.get(i);

				jse.executeScript("arguments[0].click();", color);
				Thread.sleep(2000);

				// Fetch all size options for selected color
				List<WebElement> sizeOptions =
						driver.findElements(By.xpath("//input[@name='option[Size]']"));

				System.out.println("Size count :: " + sizeOptions.size());

				for (int j = 0; j < sizeOptions.size(); j++) {

					if (count == 7) {
						break;
					}

					try {
						// Re-fetch sizes to avoid stale
						sizeOptions =
								driver.findElements(By.xpath("//input[@name='option[Size]']"));
						WebElement size = sizeOptions.get(j);

						wait.until(ExpectedConditions.elementToBeClickable(size));
						jse.executeScript("arguments[0].click();", size);
						Thread.sleep(1000);

						String buttonText =
								driver.findElement(ProductDetailsPageComponents.ADD_TO_CART_BUTTON).getText();

						if (buttonText.contains("Add")) {

							System.out.println(
									"Added product → Color index: " + i + ", Size index: " + j);

							jse.executeScript(
									"arguments[0].click();",
									driver.findElement(ProductDetailsPageComponents.ADD_TO_CART_BUTTON)
							);

							Thread.sleep(3000);

							wait.until(ExpectedConditions.visibilityOfElementLocated(INLINE_CART_TITLE));
							jse.executeScript("arguments[0].click();",
									driver.findElement(INLINE_CART_CLOSE));

							count++;
							System.out.println("Total added so far: " + count);
							Thread.sleep(1000);
						}

					} catch (Exception ex) {
						System.out.println("Product is OOS for Color index: " + i + ", Size index: " + j);
					}
				}

				if (count == 7) {
					break;
				}
			}
		}

		driver.get("https://www.tommyjohn.com/");
		return "Total products added: " + count;
	}

	public String add7for$35product() throws Exception {

		int count = 0;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.get("https://www.tommyjohn.com/products/womens-second-skin-brief-13?variant=44729634914349");
		wait.until(ExpectedConditions.visibilityOfElementLocated(ProductDetailsPageLocators.PRODUCT_TITLE));
		Reporter.log(driver.getCurrentUrl());
		ExtentReport.extentTest.info(driver.getCurrentUrl());
		Thread.sleep(2000);

		allElements = driver.findElements(ProductDetailsPageComponents.COLOR_VARIANT);

		elementsCount = allElements.size();
		System.out.println("Color Element count :: " + elementsCount);

		if (elementsCount > 1) {
			for (int i = 1; i <= 8; i++) {

				WebElement elemen = driver.findElement(By.xpath("(//div[@class='space-y-lg']//input[@class='sr-only peer'])[" + i + "]"));
				jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].click();", elemen);
				Thread.sleep(3000);

				for (int j = 1; j <= 8; j++) {

					WebElement elements = driver.findElement(By.xpath("(//input[@name='option[Size]'])[" + j + "]"));
					jse.executeScript("arguments[0].click();", elements);
					Thread.sleep(1000);

					try{
						String[] AddtoBag;
						AddtoBag = (driver.findElement(By.xpath("//button[@class='button grow']")).getText()).split(" -");
						String text1 = AddtoBag[0];
						System.out.println(text1);

						// WebElement elem = driver.findElement(By.cssSelector(".multiPdp__atc-container>button"));


						if (driver.findElement(ProductDetailsPageComponents.ADD_TO_CART_BUTTON).getText().contains(text1)) {

							System.out.println(elemen.getText() + elements.getText() + "Product is added to Cart");
							Thread.sleep(5000);
							jse.executeScript("arguments[0].click();", driver.findElement(ADD_TO_CART_BUTTON));
							Thread.sleep(3000);
							driver.findElement(INLINE_CART_TITLE);
							jse.executeScript("arguments[0].click();", driver.findElement(INLINE_CART_CLOSE));

							Thread.sleep(1000);
							System.out.println("=============================");
							count++;

//              break;
						}

					}
					catch (Exception ex)
					{

						System.out.println("Product Is OOS");
					}


//                 if(driver.findElement(ProductDetailsPageComponents.ADD_TO_CART_BUTTON).getText().contains("Add to Cart")) {
//
//                    System.out.println(elemen.getText() + elements.getText() + "Product is added to Cart");
//                    jse.executeScript("arguments[0].click();", driver.findElement(ADD_TO_CART_BUTTON));
//                    Thread.sleep(2000);
//                    jse.executeScript("arguments[0].click();", driver.findElement(INLINE_CART_CLOSE));
//                    break;
//
					if (count == 8){
						break; }
				}

				if(count == 8){
					break;
				}

			}

			driver.get("https://www.tommyjohn.com/");

		}
		return text;

	}

	public String add3for$48productforbundletiercheck() throws Exception {

		int count = 0;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		// call method to navigate to collection page
//		new HomePageComponents(driver).navigateToAllPantiesnInWomenCategory();
//		jse = (JavascriptExecutor) driver;
//		jse.executeScript("window.scrollBy(0,250)");
//		// call method to navigate product details page
//		text = new CollectionPageComponent(driver).navigateTo3FOR$48PDP();
		driver.get("https://www.tommyjohn.com/products/womens-air-brief-1?variant=44929145798701");
		wait.until(ExpectedConditions.visibilityOfElementLocated(ProductDetailsPageLocators.PRODUCT_TITLE));
		Reporter.log(driver.getCurrentUrl());
		ExtentReport.extentTest.info(driver.getCurrentUrl());
		Thread.sleep(2000);

		allElements = driver.findElements(ProductDetailsPageComponents.COLOR_VARIANT);

		elementsCount = allElements.size();
		System.out.println("Color Element count :: " + elementsCount);

		if (elementsCount > 1) {
			for (int i = 1; i <= elementsCount; i++) {

				WebElement elemen = driver.findElement(By.xpath("(//div[@class='space-y-lg']//input[@class='sr-only peer'])[" + i + "]"));
				jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].click();", elemen);
				Thread.sleep(3000);

				for (int j = 1; j <= 5; j++) {

					WebElement elements = driver.findElement(By.xpath("(//input[@name='option[Size]'])[" + j + "]"));
					jse.executeScript("arguments[0].click();", elements);
					Thread.sleep(1000);

					try{
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
//							driver.findElement(INLINE_CART_TITLE);
//							jse.executeScript("arguments[0].click();", driver.findElement(INLINE_CART_CLOSE));

							Thread.sleep(1000);
							System.out.println("=============================");
							count++;

//					break;
						}

					}
					catch (Exception ex)
					{

						System.out.println("Product Is OOS");
					}


//						if(driver.findElement(ProductDetailsPageComponents.ADD_TO_CART_BUTTON).getText().contains("Add to Cart")) {
//
//							System.out.println(elemen.getText() + elements.getText() + "Product is added to Cart");
//							jse.executeScript("arguments[0].click();", driver.findElement(ADD_TO_CART_BUTTON));
//							Thread.sleep(2000);
//							jse.executeScript("arguments[0].click();", driver.findElement(INLINE_CART_CLOSE));
//							break;
//
					if (count == 1){
						break;	}
				}

				if(count==1){
					break;
				}

			}

//			driver.get("https://www.tommyjohn.com/");

		}
		return text;

	}

	public String add3for$48product() throws Exception {

		int count = 0;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		// call method to navigate to collection page
//		new HomePageComponents(driver).navigateToAllPantiesnInWomenCategory();
//		jse = (JavascriptExecutor) driver;
//		jse.executeScript("window.scrollBy(0,250)");
//		// call method to navigate product details page
//		text = new CollectionPageComponent(driver).navigateTo3FOR$48PDP();
		driver.get("https://www.tommyjohn.com/products/womens-air-brief-1?variant=44929145798701");
		wait.until(ExpectedConditions.visibilityOfElementLocated(ProductDetailsPageLocators.PRODUCT_TITLE));
		Reporter.log(driver.getCurrentUrl());
		ExtentReport.extentTest.info(driver.getCurrentUrl());
		Thread.sleep(2000);

		allElements = driver.findElements(ProductDetailsPageComponents.COLOR_VARIANT);

		elementsCount = allElements.size();
		System.out.println("Color Element count :: " + elementsCount);

		if (elementsCount > 1) {
			for (int i = 1; i <= elementsCount; i++) {

				WebElement elemen = driver.findElement(By.xpath("(//div[@class='space-y-lg']//input[@class='sr-only peer'])[" + i + "]"));
				jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].click();", elemen);
				Thread.sleep(3000);

				for (int j = 1; j <= 5; j++) {

					WebElement elements = driver.findElement(By.xpath("(//input[@name='option[Size]'])[" + j + "]"));
					jse.executeScript("arguments[0].click();", elements);
					Thread.sleep(1000);

					try{
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
						count++;

//					break;
					}

					}
					catch (Exception ex)
					{

						System.out.println("Product Is OOS");
					}


//						if(driver.findElement(ProductDetailsPageComponents.ADD_TO_CART_BUTTON).getText().contains("Add to Cart")) {
//
//							System.out.println(elemen.getText() + elements.getText() + "Product is added to Cart");
//							jse.executeScript("arguments[0].click();", driver.findElement(ADD_TO_CART_BUTTON));
//							Thread.sleep(2000);
//							jse.executeScript("arguments[0].click();", driver.findElement(INLINE_CART_CLOSE));
//							break;
//
					if (count == 5){
					break;	}
				}

				if(count==5){
					break;
				}

			}

			driver.get("https://www.tommyjohn.com/");

		}
		return text;

	}

	public String addbundletierproduct() throws Exception {

		int count = 0;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		// call method to navigate to collection page
//		new HomePageComponents(driver).navigateToAllPantiesnInWomenCategory();
//		jse = (JavascriptExecutor) driver;
//		jse.executeScript("window.scrollBy(0,250)");
//		// call method to navigate product details page
//		text = new CollectionPageComponent(driver).navigateTo3FOR$48PDP();
		driver.get("https://www.tommyjohn.com/products/womens-air-brief-3?variant=66913155743789");
		wait.until(ExpectedConditions.visibilityOfElementLocated(ProductDetailsPageLocators.PRODUCT_TITLE));
		Reporter.log(driver.getCurrentUrl());
		ExtentReport.extentTest.info(driver.getCurrentUrl());
		Thread.sleep(2000);

		allElements = driver.findElements(ProductDetailsPageComponents.COLOR_VARIANT);

		elementsCount = allElements.size();
		System.out.println("Color Element count :: " + elementsCount);

		if (elementsCount > 1) {
			for (int i = 1; i <= elementsCount; i++) {

				WebElement elemen = driver.findElement(By.xpath("(//div[@class='space-y-lg']//input[@class='sr-only peer'])[" + i + "]"));
				jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].click();", elemen);
				Thread.sleep(3000);

				for (int j = 1; j <= 5; j++) {

					WebElement elements = driver.findElement(By.xpath("(//input[@name='option[Size]'])[" + j + "]"));
					jse.executeScript("arguments[0].click();", elements);
					Thread.sleep(1000);

					try{
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
							count++;

//					break;
						}

					}
					catch (Exception ex)
					{

						System.out.println("Product Is OOS");
					}


//						if(driver.findElement(ProductDetailsPageComponents.ADD_TO_CART_BUTTON).getText().contains("Add to Cart")) {
//
//							System.out.println(elemen.getText() + elements.getText() + "Product is added to Cart");
//							jse.executeScript("arguments[0].click();", driver.findElement(ADD_TO_CART_BUTTON));
//							Thread.sleep(2000);
//							jse.executeScript("arguments[0].click();", driver.findElement(INLINE_CART_CLOSE));
//							break;
//
					if (count == 7){
						break;	}
				}

				if(count==7){
					break;
				}

			}

			driver.get("https://www.tommyjohn.com/");

		}
		return text;

	}





















}
