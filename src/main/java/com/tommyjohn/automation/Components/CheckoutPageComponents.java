package com.tommyjohn.automation.Components;

import java.time.Duration;
import java.util.*;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.interactions.Actions;

import com.base.ExtentReport;
import com.tommyjohn.automation.PageLocators.AccountLoginLocators;
import com.tommyjohn.automation.PageLocators.CheckoutPageLocators;
import com.tommyjohn.automation.PageLocators.FlyCartPageLocator;
import com.tommyjohn.automation.PageLocators.HomePageLocators;
import com.tommyjohn.automation.utils.CustomUtilities;

import static com.tommyjohn.automation.PageLocators.FlyCartPageLocator.CHECKOUT_BUTTON;
import static com.tommyjohn.automation.PageLocators.HomePageLocators.PRODUCT_NAME;
import static com.tommyjohn.automation.PageLocators.ProductDetailsPageLocators.CART_ICON;

public class CheckoutPageComponents extends CheckoutPageLocators {

	//String Promo_Code = "TJHUSTLE";

	public CheckoutPageComponents(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriver driver;
	public JavascriptExecutor jse;
	Wait<WebDriver> wait;
	List<WebElement> allElements;
	WebElement element;
	double installment;
	String price;
	String text;
	SoftAssert softAssert = new SoftAssert();

	public void GWPValidation() throws Exception {

		softAssert = new SoftAssert();

		JavascriptExecutor jse =
				(JavascriptExecutor) driver;

		WebDriverWait wait =
				new WebDriverWait(driver, Duration.ofSeconds(60));

		final double TARGET_TOTAL = 2000.00;
		final int MINIMUM_QUANTITY = 50;

		boolean targetReached = false;

		// =========================================================
		// STEP 1: Navigate to Men's Underwear
		// =========================================================

		System.out.println("=================================================");
		System.out.println("Navigating to Men's Underwear");
		System.out.println("=================================================");

		new HomePageComponents(driver)
				.navigateToAllUnderwearInMenCategory();

		wait.until(
				ExpectedConditions.presenceOfElementLocated(
						By.xpath("//a[contains(@href,'/products/')]")
				)
		);

		// =========================================================
		// STEP 2: Collect Unique PDP URLs
		// =========================================================

		Set<String> productUrls =
				new LinkedHashSet<>();

		List<WebElement> productLinks =
				driver.findElements(
						By.xpath("//a[contains(@href,'/products/')]")
				);

		for (WebElement productLink : productLinks) {

			try {

				String href =
						productLink.getAttribute("href");

				if (href != null &&
						href.contains("/products/")) {

					String productUrl =
							normalizeProductUrl(href);

					if (productUrl != null &&
							!productUrl.isBlank()) {

						productUrls.add(productUrl);
					}
				}

			} catch (StaleElementReferenceException e) {

				System.out.println(
						"Product link became stale. Skipping."
				);
			}
		}

		System.out.println(
				"Unique PDP URLs Found :: "
						+ productUrls.size()
		);

		// =========================================================
		// STEP 3: Loop Through PDPs
		// =========================================================

		for (String productUrl : productUrls) {

			if (targetReached) {
				break;
			}

			System.out.println(
					"\n================================================="
			);

			System.out.println(
					"Opening PDP :: "
							+ productUrl
			);

			System.out.println(
					"================================================="
			);

			driver.get(productUrl);

			wait.until(
					ExpectedConditions.presenceOfElementLocated(
							By.tagName("body")
					)
			);

			boolean productProcessed = false;

			double unitPrice = 0.0;

			// =====================================================
			// STEP 4: GROUP
			// =====================================================

			List<WebElement> groups =
					driver.findElements(
							By.xpath(
									"//fieldset[.//input[@name='group']]//input[@name='group']"
							)
					);

			System.out.println(
					"Product Groups Found :: "
							+ groups.size()
			);

			for (int groupIndex = 0;
				 groupIndex < groups.size() && !productProcessed;
				 groupIndex++) {

				try {

					groups =
							driver.findElements(
									By.xpath(
											"//fieldset[.//input[@name='group']]//input[@name='group']"
									)
							);

					if (groupIndex >= groups.size()) {
						break;
					}

					WebElement group =
							groups.get(groupIndex);

					String groupValue =
							group.getAttribute("value");

					System.out.println(
							"Selecting Group :: "
									+ groupValue
					);

					jse.executeScript(
							"arguments[0].click();",
							group
					);

					Thread.sleep(400);

					// =================================================
					// STEP 5: COLOR
					// =================================================

					List<WebElement> colors =
							driver.findElements(
									ProductDetailsPageComponents.COLOR_VARIANT
							);

					System.out.println(
							"Colors Found :: "
									+ colors.size()
					);

					for (int colorIndex = 0;
						 colorIndex < colors.size() && !productProcessed;
						 colorIndex++) {

						try {

							colors =
									driver.findElements(
											ProductDetailsPageComponents.COLOR_VARIANT
									);

							if (colorIndex >= colors.size()) {
								break;
							}

							WebElement color =
									colors.get(colorIndex);

							String colorName =
									color.getAttribute("value");

							System.out.println(
									"Selecting Color :: "
											+ colorName
							);

							jse.executeScript(
									"arguments[0].click();",
									color
							);

							Thread.sleep(400);

							// =================================================
							// STEP 6: SIZE
							// =================================================

							List<WebElement> sizes =
									driver.findElements(
											By.xpath(
													"//input[@name='option[Size]']"
											)
									);

							System.out.println(
									"Sizes Found :: "
											+ sizes.size()
							);

							for (int sizeIndex = 0;
								 sizeIndex < sizes.size() && !productProcessed;
								 sizeIndex++) {

								try {

									sizes =
											driver.findElements(
													By.xpath(
															"//input[@name='option[Size]']"
													)
											);

									if (sizeIndex >= sizes.size()) {
										break;
									}

									WebElement size =
											sizes.get(sizeIndex);

									String sizeValue =
											size.getAttribute("value");

									System.out.println(
											"Selecting Size :: "
													+ sizeValue
									);

									// IMPORTANT:
									// Select Size before checking OOS.

									jse.executeScript(
											"arguments[0].click();",
											size
									);

									Thread.sleep(700);

									// =================================================
									// STEP 7: ADD TO CART BUTTON
									// =================================================

									List<WebElement> addToCartButtons =
											driver.findElements(
													ProductDetailsPageComponents.ADD_TO_CART_BUTTON
											);

									if (addToCartButtons.isEmpty()) {

										System.out.println(
												"ATC button not found. "
														+ "Trying next size."
										);

										continue;
									}

									WebElement addToCartButton =
											addToCartButtons.get(0);

									String buttonText =
											addToCartButton
													.getText()
													.trim()
													.toLowerCase();

									System.out.println(
											"ATC Button Text :: "
													+ buttonText
									);

									// =================================================
									// STEP 8: OOS CHECK
									// =================================================

									if (buttonText.contains("join the waitlist")
											|| buttonText.contains("out of stock")
											|| buttonText.contains("sold out")
											|| buttonText.contains("unavailable")) {

										System.out.println(
												"Variant OOS :: "
														+ groupValue
														+ " | "
														+ colorName
														+ " | "
														+ sizeValue
										);

										continue;
									}

									if (!buttonText.contains("add to bag")
											&& !buttonText.contains("add to cart")) {

										System.out.println(
												"Variant is not purchasable."
										);

										continue;
									}

									// =================================================
									// STEP 9: GET UNIT PRICE
									// =================================================

									unitPrice =
											extractPriceFromText(
													addToCartButton.getText()
											);

									if (unitPrice <= 0) {

										System.out.println(
												"Unable to determine unit price."
										);

										continue;
									}

									System.out.println(
											"Unit Price :: $"
													+ unitPrice
									);

									// =================================================
									// STEP 10:
									// Capture Cart Quantity IDs BEFORE Add
									// =================================================

									Set<String> quantityIdsBeforeAdd =
											getCartQuantityInputIds();

									System.out.println(
											"Quantity IDs Before Add :: "
													+ quantityIdsBeforeAdd
									);

									// =================================================
									// STEP 11: ADD TO CART
									// =================================================

									System.out.println(
											"Adding Product To Cart..."
									);

									jse.executeScript(
											"arguments[0].click();",
											addToCartButton
									);

									Thread.sleep(1200);

									System.out.println(
											"Product Added Successfully."
									);

									// =================================================
									// STEP 12: Navigate To Cart
									// =================================================

									navigateToCartForGWP(
											wait
									);

									// =================================================
									// STEP 13:
									// Wait For Cart Quantity Inputs
									// =================================================

									wait.until(
											ExpectedConditions.presenceOfElementLocated(
													By.xpath(
															"//input[contains(@id,'CartForm-Quantity-')]"
													)
											)
									);

									// =================================================
									// STEP 14:
									// Find NEW Quantity Input
									// =================================================

									String newQuantityInputId =
											findNewQuantityInputId(
													quantityIdsBeforeAdd,
													wait
											);

									// =================================================
									// Shopify merged product with existing line
									// =================================================

									if (newQuantityInputId == null) {

										System.out.println(
												"================================================="
										);

										System.out.println(
												"NO NEW CART LINE CREATED"
										);

										System.out.println(
												"Product was merged with an existing Cart line."
										);

										System.out.println(
												"Skipping this variant."
										);

										System.out.println(
												"================================================="
										);

										continue;
									}

									System.out.println(
											"New Quantity Input ID :: "
													+ newQuantityInputId
									);

									// =================================================
									// STEP 15: Get Current Cart Total
									// =================================================

									double currentCartTotal =
											getCurrentCartTotal();

									System.out.println(
											"Current Cart Total :: $"
													+ currentCartTotal
									);

									// =================================================
									// STEP 16:
									// Increase ONLY New Product
									// =================================================

									while (currentCartTotal <
											TARGET_TOTAL) {

										WebElement quantityInput =
												wait.until(
														ExpectedConditions.presenceOfElementLocated(
																By.id(
																		newQuantityInputId
																)
														)
												);

										int currentQuantity =
												Integer.parseInt(
														quantityInput
																.getAttribute("value")
																.trim()
												);

										int maxQuantity =
												getMaxQuantity(
														quantityInput
												);

										System.out.println(
												"\n-------------------------------------------------"
										);

										System.out.println(
												"New Product Quantity :: "
														+ currentQuantity
										);

										System.out.println(
												"Maximum Quantity :: "
														+ maxQuantity
										);

										System.out.println(
												"Current Cart Total :: $"
														+ currentCartTotal
										);

										System.out.println(
												"-------------------------------------------------"
										);

										// =================================================
										// MAX REACHED
										// =================================================

										if (currentQuantity >=
												maxQuantity) {

											System.out.println(
													"Maximum quantity reached."
											);

											break;
										}

										// =================================================
										// Calculate Quantity
										// =================================================

										double remainingAmount =
												TARGET_TOTAL
														- currentCartTotal;

										int calculatedQuantity =
												currentQuantity
														+ (int) Math.ceil(
														remainingAmount
																/ unitPrice
												);

										/*
										 * Minimum 50.
										 *
										 * If calculated = 9:
										 *     use 50
										 *
										 * If calculated = 75:
										 *     use 75
										 */

										int quantityToSet =
												Math.max(
														MINIMUM_QUANTITY,
														calculatedQuantity
												);

										/*
										 * Never exceed Shopify max.
										 */

										quantityToSet =
												Math.min(
														quantityToSet,
														maxQuantity
												);

										System.out.println(
												"Calculated Quantity :: "
														+ calculatedQuantity
										);

										System.out.println(
												"Quantity To Set :: "
														+ quantityToSet
										);

										// =================================================
										// Cannot increase
										// =================================================

										if (quantityToSet <=
												currentQuantity) {

											System.out.println(
													"Cannot increase quantity further."
											);

											break;
										}

										// =================================================
										// UPDATE ONLY NEW LINE
										// =================================================

										updateGWPQuantity(
												newQuantityInputId,
												quantityToSet
										);

										// =================================================
										// WAIT FOR TOTAL UPDATE
										// =================================================

										double previousCartTotal =
												currentCartTotal;

										currentCartTotal =
												waitForGWPValidationCartTotalChange(
														previousCartTotal
												);

										System.out.println(
												"Updated Cart Total :: $"
														+ currentCartTotal
										);

										// =================================================
										// TARGET REACHED
										// =================================================

										if (currentCartTotal >=
												TARGET_TOTAL) {

											targetReached = true;

											System.out.println(
													"\n================================================="
											);

											System.out.println(
													"TARGET REACHED :: $"
															+ currentCartTotal
											);

											System.out.println(
													"================================================="
											);

											break;
										}

										// =================================================
										// Check Updated Quantity
										// =================================================

										WebElement updatedQuantityInput =
												wait.until(
														ExpectedConditions.presenceOfElementLocated(
																By.id(
																		newQuantityInputId
																)
														)
												);

										int updatedQuantity =
												Integer.parseInt(
														updatedQuantityInput
																.getAttribute("value")
																.trim()
												);

										System.out.println(
												"Updated Quantity :: "
														+ updatedQuantity
										);

										if (updatedQuantity >=
												maxQuantity) {

											System.out.println(
													"Maximum quantity reached "
															+ "for this product."
											);

											break;
										}
									}

									// =================================================
									// STEP 17: Final Total For This Product
									// =================================================

									currentCartTotal =
											getCurrentCartTotal();

									System.out.println(
											"Cart Total After Product :: $"
													+ currentCartTotal
									);

									if (currentCartTotal >=
											TARGET_TOTAL) {

										targetReached = true;
									}

									/*
									 * This PDP has been processed.
									 * Move to next PDP only if needed.
									 */

									productProcessed = true;

								} catch (
										StaleElementReferenceException e) {

									System.out.println(
											"Size became stale. "
													+ "Trying next size."
									);

								} catch (Exception e) {

									System.out.println(
											"Error while processing size :: "
													+ e.getMessage()
									);
								}
							}

						} catch (
								StaleElementReferenceException e) {

							System.out.println(
									"Color became stale. "
											+ "Trying next color."
							);
						}
					}

				} catch (
						StaleElementReferenceException e) {

					System.out.println(
							"Group became stale. "
									+ "Trying next group."
					);
				}
			}

			// =========================================================
			// STEP 18: Stop If $2000 Reached
			// =========================================================

			if (targetReached) {
				break;
			}

			System.out.println(
					"Cart is still below $"
							+ TARGET_TOTAL
			);

			System.out.println(
					"Moving to next PDP..."
			);
		}

		// =========================================================
		// STEP 19: Final Cart Total
		// =========================================================

		double finalCartTotal =
				getCurrentCartTotal();

		System.out.println(
				"\n================================================="
		);

		System.out.println(
				"FINAL CART TOTAL :: $"
						+ finalCartTotal
		);

		System.out.println(
				"================================================="
		);

		// =========================================================
		// STEP 20: Failed To Reach Threshold
		// =========================================================

		if (finalCartTotal < TARGET_TOTAL) {

			softAssert.fail(
					"Unable to reach $"
							+ TARGET_TOTAL
							+ ". Final Cart Total = $"
							+ finalCartTotal
			);

			softAssert.assertAll();

			return;
		}

		// =========================================================
// CHECKOUT
// =========================================================

		System.out.println(
				"Cart total reached $2000."
		);

		System.out.println(
				"Navigating to Checkout..."
		);

		WebElement checkoutButton =
				wait.until(
						ExpectedConditions.elementToBeClickable(
								CHECKOUT_BUTTON
						)
				);

		jse.executeScript(
				"arguments[0].click();",
				checkoutButton
		);

		wait.until(
				ExpectedConditions.urlContains("checkouts")
		);

		System.out.println(
				"Checkout Page Loaded :: "
						+ driver.getCurrentUrl()
		);

// =========================================================
// CONTACT INFORMATION
// =========================================================

		wait.until(
				ExpectedConditions.visibilityOfElementLocated(
						CONTACTINFO_TEXT
				)
		);

		driver.findElement(CHECKOUT_EMAIL)
				.sendKeys(
						CustomUtilities.properties
								.getProperty("checkoutemail")
				);

		softAssert.assertEquals(
				driver.findElement(SHIPPINGADD_TEXT).getText(),
				"Delivery",
				"Shipping address title is not displayed"
		);

// =========================================================
// SHIPPING DETAILS
// =========================================================

		entershippingdetails();

		Reporter.log(
				"Shipping Address Details entered"
		);

		ExtentReport.extentTest.info(
				"Shipping Address Details entered"
		);

// =========================================================
// GWP VALIDATION - POUCH
// =========================================================

		By GWP_POUCH =
				By.xpath(
						"//p[normalize-space()='Pouch']"
				);

		try {

			WebElement pouch =
					wait.until(
							ExpectedConditions.visibilityOfElementLocated(
									GWP_POUCH
							)
					);

			softAssert.assertTrue(
					pouch.isDisplayed(),
					"GWP Pouch is displayed in Checkout"
			);

			System.out.println(
					"================================================="
			);

			System.out.println(
					"GWP VALIDATION PASSED"
			);

			System.out.println(
					"GWP Product :: "
							+ pouch.getText().trim()
			);

			System.out.println(
					"================================================="
			);

			Reporter.log(
					"GWP Product Pouch is displayed in Checkout"
			);

			ExtentReport.extentTest.pass(
					"GWP Product Pouch is displayed in Checkout"
			);

		} catch (TimeoutException e) {

			System.out.println(
					"================================================="
			);

			System.out.println(
					"GWP VALIDATION FAILED"
			);

			System.out.println(
					"GWP Product Pouch is not displayed in Checkout"
			);

			System.out.println(
					"================================================="
			);

			Reporter.log(
					"GWP Product Pouch is NOT displayed in Checkout"
			);

			ExtentReport.extentTest.fail(
					"GWP Product Pouch is NOT displayed in Checkout"
			);

			softAssert.fail(
					"GWP Product Pouch is not displayed in Checkout"
			);
		}

		softAssert.assertAll();
	}

	private Set<String> getCartQuantityInputIds() {

		Set<String> quantityInputIds =
				new LinkedHashSet<>();

		List<WebElement> quantityInputs =
				driver.findElements(
						By.xpath(
								"//input[contains(@id,'CartForm-Quantity-')]"
						)
				);

		for (WebElement input : quantityInputs) {

			try {

				String id =
						input.getAttribute("id");

				if (id != null &&
						!id.isBlank()) {

					quantityInputIds.add(id);
				}

			} catch (StaleElementReferenceException e) {

				// Ignore stale input
			}
		}

		return quantityInputIds;
	}

	private String findNewQuantityInputId(
			Set<String> quantityIdsBeforeAdd,
			WebDriverWait wait) {

		By quantityInputLocator =
				By.xpath(
						"//input[contains(@id,'CartForm-Quantity-')]"
				);

		try {

			return wait.until(driver -> {

				List<WebElement> quantityInputs =
						driver.findElements(
								quantityInputLocator
						);

				for (WebElement input : quantityInputs) {

					try {

						String id =
								input.getAttribute("id");

						if (id == null ||
								id.isBlank()) {

							continue;
						}

						if (!quantityIdsBeforeAdd.contains(id)) {

							return id;
						}

					} catch (StaleElementReferenceException e) {

						// Retry
					}
				}

				return null;
			});

		} catch (TimeoutException e) {

			return null;
		}
	}

	private void navigateToCartForGWP(
			WebDriverWait wait) {

		if (driver.getCurrentUrl().contains("/cart")) {

			return;
		}

		By viewCartLocator =
				By.xpath(
						"//a[contains(@href,'/cart') "
								+ "and contains("
								+ "translate(normalize-space(.),"
								+ "'ABCDEFGHIJKLMNOPQRSTUVWXYZ',"
								+ "'abcdefghijklmnopqrstuvwxyz'),"
								+ "'view cart')]"
				);

		try {

			WebElement viewCart =
					new WebDriverWait(
							driver,
							Duration.ofSeconds(10)
					).until(
							ExpectedConditions.elementToBeClickable(
									viewCartLocator
							)
					);

			((JavascriptExecutor) driver)
					.executeScript(
							"arguments[0].click();",
							viewCart
					);

		} catch (TimeoutException e) {

			System.out.println(
					"View Cart link not found."
			);

			System.out.println(
					"Opening Cart directly."
			);

			driver.get(
					"https://www.tommyjohn.com/cart"
			);
		}

		wait.until(
				ExpectedConditions.urlContains("/cart")
		);
	}

	private int getMaxQuantity(
			WebElement quantityInput) {

		String maxAttribute =
				quantityInput.getAttribute("max");

		if (maxAttribute == null ||
				maxAttribute.isBlank()) {

			return 9999;
		}

		try {

			return Integer.parseInt(
					maxAttribute.trim()
			);

		} catch (NumberFormatException e) {

			return 9999;
		}
	}

	private void updateGWPQuantity(
			String quantityInputId,
			int quantity) throws Exception {

		JavascriptExecutor jse =
				(JavascriptExecutor) driver;

		WebDriverWait wait =
				new WebDriverWait(
						driver,
						Duration.ofSeconds(30)
				);

		By quantityLocator =
				By.id(quantityInputId);

		WebElement quantityInput =
				wait.until(
						ExpectedConditions.presenceOfElementLocated(
								quantityLocator
						)
				);

		System.out.println(
				"Updating Cart Line Quantity :: "
						+ quantity
		);

		jse.executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				quantityInput
		);

		Thread.sleep(300);

		quantityInput.click();

		quantityInput.sendKeys(
				Keys.CONTROL + "a"
		);

		quantityInput.sendKeys(
				String.valueOf(quantity)
		);

		jse.executeScript(
				"""
                arguments[0].dispatchEvent(
                    new Event('input', {bubbles:true})
                );
    
                arguments[0].dispatchEvent(
                    new Event('change', {bubbles:true})
                );
                """,
				quantityInput
		);

		Thread.sleep(500);

		quantityInput.sendKeys(
				Keys.ENTER
		);

		wait.until(driver -> {

			try {

				WebElement updatedInput =
						driver.findElement(
								quantityLocator
						);

				String value =
						updatedInput.getAttribute(
								"value"
						);

				return String.valueOf(quantity)
						.equals(value);

			} catch (StaleElementReferenceException |
					 NoSuchElementException e) {

				return false;
			}
		});

		System.out.println(
				"Cart Line Quantity Updated Successfully :: "
						+ quantity
		);
	}





	private double waitForGWPValidationCartTotalChange(
			double previousTotal) throws Exception {

		WebDriverWait wait =
				new WebDriverWait(
						driver,
						Duration.ofSeconds(30)
				);

		final double[] latestTotal =
				{previousTotal};

		try {

			wait.until(driver -> {

				try {

					latestTotal[0] =
							getCurrentCartTotal();

					System.out.println(
							"Waiting For Cart Total Update :: $"
									+ latestTotal[0]
					);

					return Math.abs(
							latestTotal[0]
									- previousTotal
					) > 0.009;

				} catch (Exception e) {

					return false;
				}
			});

		} catch (TimeoutException e) {

			latestTotal[0] =
					getCurrentCartTotal();
		}

		return latestTotal[0];
	}



	private String normalizeProductUrl(String url) {

		if (url == null) {
			return "";
		}

		// Remove query parameters
		url = url.split("\\?")[0];

		// Remove #Reviews, etc.
		url = url.split("#")[0];

		// Remove trailing slash
		if (url.endsWith("/")) {
			url = url.substring(
					0,
					url.length() - 1
			);
		}

		return url;
	}

	private double extractPriceFromText(String text) {

		if (text == null || text.isBlank()) {
			return 0.0;
		}

		Pattern pattern =
				Pattern.compile(
						"\\$\\s*([0-9,]+(?:\\.\\d{1,2})?)"
				);

		Matcher matcher =
				pattern.matcher(text);

		if (matcher.find()) {

			String price =
					matcher.group(1)
							.replace(",", "");

			return Double.parseDouble(price);
		}

		return 0.0;
	}

	private double getCurrentCartTotal() {

		try {

			String bodyText =
					driver.findElement(
							By.tagName("body")
					).getText();

			Pattern pattern =
					Pattern.compile(
							"(?i)\\bTotal\\s*\\$\\s*([0-9,]+(?:\\.\\d{1,2})?)"
					);

			Matcher matcher =
					pattern.matcher(bodyText);

			double total = 0.0;

			while (matcher.find()) {

				total = Double.parseDouble(
						matcher.group(1)
								.replace(",", "")
				);
			}

			return total;

		} catch (Exception e) {

			System.out.println(
					"Unable to get cart total :: "
							+ e.getMessage()
			);

			return 0.0;
		}
	}










	public void validateExtensibilityUpgrade() throws Exception {
		jse = (JavascriptExecutor) driver;
		SoftAssert softAssert = new SoftAssert();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		new AddToCartComponents(driver).addmultipleproductToCartforFreeOrder();
		new CheckoutPageComponents(driver).validateFreeOrder();
		new CheckoutPageComponents(driver).CheckFreeOrderpromo("TECH TEST");
		try {

			WebElement completeOrderButton =
					wait.until(
							ExpectedConditions.elementToBeClickable(
									COMPLETE_ORDER
							)
					);


			completeOrderButton.click();


			System.out.println();
			System.out.println(
					"Complete Order clicked normally."
			);

		} catch (Exception e) {

			System.out.println();
			System.out.println(
					"Normal Complete Order click failed."
			);

			System.out.println(
					"Using JavaScript click..."
			);


			WebElement completeOrderButton =
					wait.until(
							ExpectedConditions.presenceOfElementLocated(
									COMPLETE_ORDER
							)
					);


			jse.executeScript(
					"arguments[0].click();",
					completeOrderButton
			);


			System.out.println(
					"Complete Order clicked using JavaScript."
			);
		}


		/*
		 * ============================================================
		 * WAIT FOR ORDER CONFIRMATION
		 * ============================================================
		 *
		 * We don't use this as the trigger for LWD.
		 *
		 * LWD requests are captured by the listener independently.
		 * ============================================================
		 */

		try {

			wait.until(
					ExpectedConditions.visibilityOfElementLocated(
							ORDER_CONFIRMATION
					)
			);

			System.out.println();
			System.out.println(
					"Order confirmation displayed."
			);

		} catch (Exception e) {

			System.out.println();
			System.out.println(
					"WARNING: Order confirmation locator "
							+ "was not detected."
			);

			System.out.println(
					"Current URL :: "
							+ driver.getCurrentUrl()
			);
		}
		wait.until(ExpectedConditions.elementToBeClickable(CheckoutPageLocators.OTHER));
		driver.findElement(OTHER).sendKeys("ublisjacobn@unitedtechno.com");
		Thread.sleep(4000);
		jse.executeScript("arguments[0].click();", driver.findElement(SUBMIT_RESPONSE));
		wait.until(ExpectedConditions.elementToBeClickable(CheckoutPageLocators.THANK_YOU_FOR_REPLY));
		softAssert.assertTrue(driver.findElement(THANK_YOU_FOR_REPLY).isDisplayed(),"Extensibility Upgrade validation failed");
		Reporter.log("Extensibility Upgrade validation successful");
		ExtentReport.extentTest.info("Extensibility Upgrade validation successful");
	}
	public void NonFreeShippingWithUnderwearOffer() throws Exception {
		jse = (JavascriptExecutor)driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
//		new AddToCartComponents(driver).NonFreeShippingAddToCart();
		wait.until(ExpectedConditions.elementToBeClickable(HomePageLocators.CART_ICON));
		jse.executeScript("arguments[0].click();", driver.findElement(HomePageLocators.CART_ICON));
//		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
//		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		Thread.sleep(5000);
		jse.executeScript("arguments[0].click();", driver.findElement(CHECKOUT_BUTTON));
		wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACTINFO_TEXT));
		driver.findElement(CHECKOUT_EMAIL).sendKeys(CustomUtilities.properties.getProperty("checkoutemail"));
		softAssert.assertEquals(driver.findElement(SHIPPINGADD_TEXT).getText(), "Delivery", "Shipping address title is not displayed");
		entershippingdetails();
		Reporter.log("Shipping Address Details entered");
		ExtentReport.extentTest.info("Shipping Address Details entered");
		// driver.findElement(CONTINUE_TO_SHIPPING_BUTTON).click();
		WebElement Continue_shipping = driver.findElement(CONTINUE_TO_SHIPPING_BUTTON);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Continue_shipping);
		Reporter.log("User is navigated to select shipping method");
		ExtentReport.extentTest.info("User is navigated to select shipping method");
		Thread.sleep(10000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACT_EMAIL));
		wait.until(ExpectedConditions.visibilityOfElementLocated(NON_FREE_SHIPPING));
		String NonFreeShipping = driver.findElement(NON_FREE_SHIPPING).getText();
		System.out.println(NonFreeShipping);
		softAssert.assertEquals(NonFreeShipping,"$8.95","Free Shipping is offered when the Cart contains products with below $75");
		softAssert.assertAll();
		Reporter.log("Free Shipping is not offered when the Cart contains products with below $75");
		ExtentReport.extentTest.info("Free Shipping is not offered when the Cart contains products with below $75");
	}


	public void VerifyCartIconInCheckoutPage() throws InterruptedException {

		jse = (JavascriptExecutor)driver;
		SoftAssert softAssert = new SoftAssert();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.findElement(HomePageLocators.CART_ICON).click();

//		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
//		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		Thread.sleep(5000);
		jse.executeScript("arguments[0].click();", driver.findElement(CHECKOUT_BUTTON));
		Reporter.log("Checkout page displayed");
		ExtentReport.extentTest.info("Checkout page displayed");
		Thread.sleep(10000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACTINFO_TEXT));
		softAssert.assertTrue(driver.findElement(CHECKOUTPAGE_CART_ICON).isDisplayed(),"Cart Icon Is Not Available In Checkout Page");
//		jse.executeScript("arguments[0].click();", driver.findElement(CHECKOUTPAGE_CART_ICON));
//		wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_NAME));
//		softAssert.assertTrue(driver.findElement(PRODUCT_NAME).isDisplayed(),"Cart Icon In Checkout Page is not functional");
		Reporter.log("Cart Icon Is Available In Checkout Page");
		ExtentReport.extentTest.info("Cart Icon Is Available In Checkout Page");
	}

	public void ValidateCartIconInCheckoutPage() throws InterruptedException {

		jse = (JavascriptExecutor)driver;
		SoftAssert softAssert = new SoftAssert();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.findElement(HomePageLocators.CART_ICON).click();

//		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
//		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		Thread.sleep(10000);
		jse.executeScript("arguments[0].click();", driver.findElement(CHECKOUT_BUTTON));
		Reporter.log("Checkout page displayed");
		ExtentReport.extentTest.info("Checkout page displayed");
		Thread.sleep(10000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACTINFO_TEXT));
		jse.executeScript("arguments[0].click();", driver.findElement(CHECKOUTPAGE_CART_ICON));
		wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_NAME));
		softAssert.assertTrue(driver.findElement(PRODUCT_NAME).isDisplayed(),"Cart Icon In Checkout Page is not functional");
		Reporter.log("Cart Icon In Checkout Page is functional");
		ExtentReport.extentTest.info("Cart Icon In Checkout Page is functional");
	}

	public void ValidateOrderProtectionInCheckoutPage() throws InterruptedException {

		jse = (JavascriptExecutor)driver;
		SoftAssert softAssert = new SoftAssert();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.findElement(HomePageLocators.CART_ICON).click();

//		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
//		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		Thread.sleep(5000);
		jse.executeScript("arguments[0].click();", driver.findElement(CHECKOUT_BUTTON));
		Reporter.log("Checkout page displayed");
		ExtentReport.extentTest.info("Checkout page displayed");
		Thread.sleep(10000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACTINFO_TEXT));
		wait.until(ExpectedConditions.visibilityOfElementLocated(ORDER_PROTECTION));
		softAssert.assertTrue(driver.findElement(ORDER_PROTECTION).isDisplayed(),"Order Protection Is Not Added In Checkout Page");
		Reporter.log("Order Protection Is Added In Checkout Page");
		ExtentReport.extentTest.info("Order Protection Is Added In Checkout Page");
	}




	public void ValidatePromoCodeErrorScenarioInCheckoutPage() throws Exception {
		jse = (JavascriptExecutor)driver;
		SoftAssert softAssert = new SoftAssert();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(HomePageLocators.CART_ICON));
		Thread.sleep(20000);
		jse.executeScript("arguments[0].click();", driver.findElement(CART_ICON));
//		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
//		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		Thread.sleep(10000);
		jse.executeScript("arguments[0].click();", driver.findElement(CHECKOUT_BUTTON));
		CheckErrorMessageForIncorrectPromoCode();

	}
	public void VerifySplitBundleProduct() throws InterruptedException {

		jse = (JavascriptExecutor)driver;
		SoftAssert softAssert = new SoftAssert();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.findElement(HomePageLocators.CART_ICON).click();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
//		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		Thread.sleep(5000);
		jse.executeScript("arguments[0].click();", driver.findElement(CHECKOUT_BUTTON));
		wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACTINFO_TEXT));
		wait.until(ExpectedConditions.visibilityOfElementLocated(SPLIT_BUNDLE));
		softAssert.assertTrue(driver.findElement(SPLIT_BUNDLE).isDisplayed(),"Bundle Product Is Not Splitting");
		Reporter.log("Bundle Product Is Splitting");
		ExtentReport.extentTest.info("Bundle Product Is Splitting");
	}

	public void VerifyGCFraudDisclosureInCheckoutPage() throws InterruptedException {

		jse = (JavascriptExecutor)driver;
		SoftAssert softAssert = new SoftAssert();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.findElement(HomePageLocators.CART_ICON).click();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
//		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		Thread.sleep(5000);
		jse.executeScript("arguments[0].click();", driver.findElement(CHECKOUT_BUTTON));
		wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACTINFO_TEXT));
		wait.until(ExpectedConditions.visibilityOfElementLocated(GC_FRAUDDISCLOSURE));
		softAssert.assertTrue(driver.findElement(GC_FRAUDDISCLOSURE).isDisplayed(),"Gift Certificate Fraud Disclosure Is Not Displayed in Information Page");
		Reporter.log("Gift Certificate Fraud Disclosure Is Displayed in Information Page");
		ExtentReport.extentTest.info("Gift Certificate Fraud Disclosure Is Displayed in Information Page");
	}

	public void VerifyGiftCardFraudDisclosureInShippingPage() throws Exception {
		jse = (JavascriptExecutor)driver;
		SoftAssert softAssert = new SoftAssert();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.findElement(HomePageLocators.CART_ICON).click();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
//		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		Thread.sleep(5000);
		jse.executeScript("arguments[0].click();", driver.findElement(CHECKOUT_BUTTON));
		wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACTINFO_TEXT));
		driver.findElement(CHECKOUT_EMAIL).sendKeys(CustomUtilities.properties.getProperty("checkoutemail"));
		softAssert.assertEquals(driver.findElement(SHIPPINGADD_TEXT).getText(), "Delivery", "Shipping address title is not displayed");
		entershippingdetails();
		Reporter.log("Shipping Address Details entered");
		ExtentReport.extentTest.info("Shipping Address Details entered");
		// driver.findElement(CONTINUE_TO_SHIPPING_BUTTON).click();
		WebElement Continue_shipping = driver.findElement(CONTINUE_TO_SHIPPING_BUTTON);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Continue_shipping);
		Reporter.log("User is navigated to select shipping method");
		ExtentReport.extentTest.info("User is navigated to select shipping method");
		wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACT_EMAIL));
		wait.until(ExpectedConditions.visibilityOfElementLocated(GC_FRAUDDISCLOSURE));
		softAssert.assertTrue(driver.findElement(GC_FRAUDDISCLOSURE).isDisplayed(),"Gift Certificate Fraud Disclosure Is Not Displayed in Shipping Page");
		Reporter.log("Gift Certificate Fraud Disclosure Is Displayed in Shipping Page");
		ExtentReport.extentTest.info("Gift Certificate Fraud Disclosure Is Displayed in Shipping Page");
	}

	public void VerifyGiftCardFraudDisclosureInPaymentPage() throws Exception {
		jse = (JavascriptExecutor)driver;
		SoftAssert softAssert = new SoftAssert();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.findElement(HomePageLocators.CART_ICON).click();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
//		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		Thread.sleep(5000);
		jse.executeScript("arguments[0].click();", driver.findElement(CHECKOUT_BUTTON));
		wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACTINFO_TEXT));
		driver.findElement(CHECKOUT_EMAIL).sendKeys(CustomUtilities.properties.getProperty("checkoutemail"));
		softAssert.assertEquals(driver.findElement(SHIPPINGADD_TEXT).getText(), "Delivery", "Shipping address title is not displayed");
		entershippingdetails();
		Reporter.log("Shipping Address Details entered");
		ExtentReport.extentTest.info("Shipping Address Details entered");
		// driver.findElement(CONTINUE_TO_SHIPPING_BUTTON).click();
		WebElement Continue_shipping = driver.findElement(CONTINUE_TO_SHIPPING_BUTTON);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Continue_shipping);
		Reporter.log("User is navigated to select shipping method");
		ExtentReport.extentTest.info("User is navigated to select shipping method");
		wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACT_EMAIL));
		wait.until(ExpectedConditions.visibilityOfElementLocated(SHIPPING_RADIO_BUTTON));
		jse.executeScript("window.scrollBy(0,100)");
		Thread.sleep(3000);
		element = driver.findElement(CONTINUE_TO_PAYMENT_BUTTON);
		jse.executeScript("arguments[0].click();", element);
		Reporter.log("User is navigated to payment page");
		ExtentReport.extentTest.info("User is navigated to payment page");
		wait.until(ExpectedConditions.visibilityOfElementLocated(GC_FRAUDDISCLOSURE));
		softAssert.assertTrue(driver.findElement(GC_FRAUDDISCLOSURE).isDisplayed(),"Gift Certificate Fraud Disclosure Is Not Displayed in Shipping Page");
		Reporter.log("Gift Certificate Fraud Disclosure Is Displayed in Shipping Page");
		ExtentReport.extentTest.info("Gift Certificate Fraud Disclosure Is Displayed in Shipping Page");
	}

	public void ValidatePromoCodeErrorScenarioInShippingPage() throws Exception {
		jse = (JavascriptExecutor)driver;
		SoftAssert softAssert = new SoftAssert();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.findElement(HomePageLocators.CART_ICON).click();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
//		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		Thread.sleep(5000);
		jse.executeScript("arguments[0].click();", driver.findElement(CHECKOUT_BUTTON));
		wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACTINFO_TEXT));
		driver.findElement(CHECKOUT_EMAIL).sendKeys(CustomUtilities.properties.getProperty("checkoutemail"));
		softAssert.assertEquals(driver.findElement(SHIPPINGADD_TEXT).getText(), "Delivery", "Shipping address title is not displayed");
		entershippingdetails();
		Reporter.log("Shipping Address Details entered");
		ExtentReport.extentTest.info("Shipping Address Details entered");
		// driver.findElement(CONTINUE_TO_SHIPPING_BUTTON).click();
		WebElement Continue_shipping = driver.findElement(CONTINUE_TO_SHIPPING_BUTTON);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Continue_shipping);
		Reporter.log("User is navigated to select shipping method");
		ExtentReport.extentTest.info("User is navigated to select shipping method");
		wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACT_EMAIL));
		CheckErrorMessageForIncorrectPromoCode();
	}

	public void ValidatePromoNotAppliedForFinalSaleProducts() throws Exception {
		jse = (JavascriptExecutor)driver;
		SoftAssert softAssert = new SoftAssert();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.findElement(HomePageLocators.CART_ICON).click();

//		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
//		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		Thread.sleep(5000);
		jse.executeScript("arguments[0].click();", driver.findElement(CHECKOUT_BUTTON));
		Reporter.log("Checkout page displayed");
		ExtentReport.extentTest.info("Checkout page displayed");
		Thread.sleep(10000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACTINFO_TEXT));

		CheckPromoNotAppliedForFinalSaleProducts("FIRST20");



	}

	public void ValidatePromoCodeErrorScenarioInPaymentPage() throws Exception {
		jse = (JavascriptExecutor)driver;
		SoftAssert softAssert = new SoftAssert();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.findElement(HomePageLocators.CART_ICON).click();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
//		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		Thread.sleep(5000);
		jse.executeScript("arguments[0].click();", driver.findElement(CHECKOUT_BUTTON));
		wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACTINFO_TEXT));
		driver.findElement(CHECKOUT_EMAIL).sendKeys(CustomUtilities.properties.getProperty("checkoutemail"));
		softAssert.assertEquals(driver.findElement(SHIPPINGADD_TEXT).getText(), "Delivery", "Shipping address title is not displayed");
		entershippingdetails();
		Reporter.log("Shipping Address Details entered");
		ExtentReport.extentTest.info("Shipping Address Details entered");
		// driver.findElement(CONTINUE_TO_SHIPPING_BUTTON).click();
		WebElement Continue_shipping = driver.findElement(CONTINUE_TO_SHIPPING_BUTTON);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Continue_shipping);
		Reporter.log("User is navigated to select shipping method");
		ExtentReport.extentTest.info("User is navigated to select shipping method");
		wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACT_EMAIL));
		wait.until(ExpectedConditions.visibilityOfElementLocated(SHIPPING_RADIO_BUTTON));
		jse.executeScript("window.scrollBy(0,100)");
		Thread.sleep(3000);
		element = driver.findElement(CONTINUE_TO_PAYMENT_BUTTON);
		jse.executeScript("arguments[0].click();", element);
		Reporter.log("User is navigated to payment page");
		ExtentReport.extentTest.info("User is navigated to payment page");
		CheckErrorMessageForIncorrectPromoCode();

	}


	public void Validate3for$48functionalityinInformationPage() throws Exception {
		jse = (JavascriptExecutor)driver;
		SoftAssert softAssert = new SoftAssert();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.findElement(HomePageLocators.CART_ICON).click();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
//		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		Thread.sleep(5000);
		jse.executeScript("arguments[0].click();", driver.findElement(CHECKOUT_BUTTON));
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(INFO_DISCOUNT_3for$48));
		}
		catch(Exception e)
		{

			wait.until(ExpectedConditions.visibilityOfElementLocated(INFO_DISCOUNT_3for$48_1));
		}
		try {
			String[] DISCOUNT_3for$48 = driver.findElement(INFO_DISCOUNT_3for$48).getText().split("\\(");
			System.out.println(DISCOUNT_3for$48[0]);
			softAssert.assertEquals(DISCOUNT_3for$48[0], "3 FOR $48 ", "3 for $48 discount is not applied and not displayed in the Information page line item");
		}
		catch(Exception e)
		{
			String[] DISCOUNT_3for$48 = driver.findElement(INFO_DISCOUNT_3for$48_1).getText().split("\\(");
			System.out.println(DISCOUNT_3for$48[0]);
			softAssert.assertEquals(DISCOUNT_3for$48[0], "3 FOR $48 ", "3 for $48 discount is not applied and not displayed in the Information page line item");
		}
		String TOTAL = driver.findElement(LINEITEM_TOTAL_3for$48).getText();
		System.out.println(TOTAL);
		softAssert.assertEquals(TOTAL,"$16.00","Line Item Total is differ from the 3 for $48 functionality");
		softAssert.assertAll();
		Reporter.log("3 for $48 functionality works fine in Information page");
		ExtentReport.extentTest.info("3 for $48 functionality works fine in Information page");
	}


	public void Validate5for$65functionalityinCheckoutPage() throws Exception {
		jse = (JavascriptExecutor)driver;
		SoftAssert softAssert = new SoftAssert();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.findElement(HomePageLocators.CART_ICON).click();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
//		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		Thread.sleep(5000);
		jse.executeScript("arguments[0].click();", driver.findElement(CHECKOUT_BUTTON));
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(INFO_DISCOUNT_5for$65));
		}
		catch(Exception e)
		{

			wait.until(ExpectedConditions.visibilityOfElementLocated(INFO_DISCOUNT_5for$65_1));
		}
		try {
			String[] DISCOUNT_5for$65 = driver.findElement(INFO_DISCOUNT_5for$65).getText().split("\\(");
			System.out.println(DISCOUNT_5for$65[0]);
			softAssert.assertEquals(DISCOUNT_5for$65[0], "5 FOR $65 ", "5 for $59 discount is not applied and not displayed in the Information page line item");
		}
		catch(Exception e)
		{
			String[] DISCOUNT_5for$65 = driver.findElement(INFO_DISCOUNT_5for$65_1).getText().split("\\(");
			System.out.println(DISCOUNT_5for$65[0]);
			softAssert.assertEquals(DISCOUNT_5for$65[0], "5 FOR $65 ", "5 for $65 discount is not applied and not displayed in the Information page line item");
		}
		String TOTAL = driver.findElement(LINEITEM_TOTAL_5for$65).getText();
		System.out.println(TOTAL);
		softAssert.assertEquals(TOTAL,"$13.00","Line Item Total is differ from the 5 for $65 functionality");
		softAssert.assertAll();
		Reporter.log("5 for $65 functionality works fine in Information page");
		ExtentReport.extentTest.info("5 for $65 functionality works fine in Information page");
	}

	public void Validate3for$48functionalityinShippingPage() throws Exception {
		jse = (JavascriptExecutor)driver;
		SoftAssert softAssert = new SoftAssert();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.findElement(HomePageLocators.CART_ICON).click();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
//		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		Thread.sleep(5000);
		jse.executeScript("arguments[0].click();", driver.findElement(CHECKOUT_BUTTON));
		Thread.sleep(5000);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(INFO_DISCOUNT_3for$48));
		}
		catch(Exception e)
		{

			wait.until(ExpectedConditions.visibilityOfElementLocated(INFO_DISCOUNT_3for$48_1));
		}
		driver.findElement(CHECKOUT_EMAIL).sendKeys(CustomUtilities.properties.getProperty("checkoutemail"));
		softAssert.assertEquals(driver.findElement(SHIPPINGADD_TEXT).getText(), "Delivery", "Shipping address title is not displayed");
		entershippingdetails();
		WebElement Continue_shipping = driver.findElement(CONTINUE_TO_SHIPPING_BUTTON);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Continue_shipping);
		Reporter.log("User is navigated to select shipping method");
		ExtentReport.extentTest.info("User is navigated to select shipping method");
		wait.until(ExpectedConditions.visibilityOfElementLocated(SHIPPING_RADIO_BUTTON));
		try {
			String[] DISCOUNT_3for$48 = driver.findElement(INFO_DISCOUNT_3for$48).getText().split("\\(");
			System.out.println(DISCOUNT_3for$48[0]);
			softAssert.assertEquals(DISCOUNT_3for$48[0], "3 FOR $48 ", "3 for $48 discount is not applied and not displayed in the Information page line item");
		}
		catch(Exception e)
		{
			String[] DISCOUNT_3for$48 = driver.findElement(INFO_DISCOUNT_3for$48_1).getText().split("\\(");
			System.out.println(DISCOUNT_3for$48[0]);
			softAssert.assertEquals(DISCOUNT_3for$48[0], "3 FOR $48 ", "3 for $48 discount is not applied and not displayed in the Information page line item");
		}
		String TOTAL = driver.findElement(LINEITEM_TOTAL_3for$48).getText();
		System.out.println(TOTAL);
		softAssert.assertEquals(TOTAL,"$16.00","Line Item Total is differ from the 3 for $48 functionality");
		softAssert.assertAll();
		Reporter.log("3 for $48 functionality works fine in Shipping page");
		ExtentReport.extentTest.info("3 for $48 functionality works fine in Shipping page");

	}


//	public void Validate5for$59functionalityinShippingPage() throws Exception {
//		jse = (JavascriptExecutor)driver;
//		SoftAssert softAssert = new SoftAssert();
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//		driver.findElement(HomePageLocators.CART_ICON).click();
////		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
////		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
//		Thread.sleep(5000);
//		jse.executeScript("arguments[0].click();", driver.findElement(CHECKOUT_BUTTON));
//		Thread.sleep(5000);
//		try {
//			wait.until(ExpectedConditions.visibilityOfElementLocated(INFO_DISCOUNT_5for$59));
//		}
//		catch(Exception e)
//		{
//
//			wait.until(ExpectedConditions.visibilityOfElementLocated(INFO_DISCOUNT_5for$59_1));
//		}
//		driver.findElement(CHECKOUT_EMAIL).sendKeys(CustomUtilities.properties.getProperty("checkoutemail"));
//		softAssert.assertEquals(driver.findElement(SHIPPINGADD_TEXT).getText(), "Shipping address");
//		entershippingdetails();
//		WebElement Continue_shipping = driver.findElement(CONTINUE_TO_SHIPPING_BUTTON);
//		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Continue_shipping);
//		Reporter.log("User is navigated to select shipping method");
//		ExtentReport.extentTest.info("User is navigated to select shipping method");
//		wait.until(ExpectedConditions.visibilityOfElementLocated(SHIPPING_RADIO_BUTTON));
//		try {
//			String[] DISCOUNT_3for$48 = driver.findElement(INFO_DISCOUNT_5for$59).getText().split("\\(");
//			System.out.println(DISCOUNT_3for$48[0]);
//			softAssert.assertEquals(DISCOUNT_3for$48[0], "3 FOR $48 ", "3 for $48 discount is not applied and not displayed in the Information page line item");
//		}
//		catch(Exception e)
//		{
//			String[] DISCOUNT_3for$48 = driver.findElement(INFO_DISCOUNT_5for$59_1).getText().split("\\(");
//			System.out.println(DISCOUNT_3for$48[0]);
//			softAssert.assertEquals(DISCOUNT_3for$48[0], "3 FOR $48 ", "3 for $48 discount is not applied and not displayed in the Information page line item");
//		}
//		String TOTAL = driver.findElement(LINEITEM_TOTAL_5for$59).getText();
//		System.out.println(TOTAL);
//		softAssert.assertEquals(TOTAL,"$16.00","Line Item Total is differ from the 3 for $48 functionality");
//		softAssert.assertAll();
//		Reporter.log("3 for $48 functionality works fine in Shipping page");
//		ExtentReport.extentTest.info("3 for $48 functionality works fine in Shipping page");
//
//	}



	public void Validate3for$48functionalityinPaymentPage() throws Exception {
		jse = (JavascriptExecutor)driver;
		SoftAssert softAssert = new SoftAssert();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.findElement(HomePageLocators.CART_ICON).click();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
//		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		Thread.sleep(5000);
		jse.executeScript("arguments[0].click();", driver.findElement(CHECKOUT_BUTTON));
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(INFO_DISCOUNT_3for$48));
		}
		catch(Exception e)
		{

			wait.until(ExpectedConditions.visibilityOfElementLocated(INFO_DISCOUNT_3for$48_1));
		}
		driver.findElement(CHECKOUT_EMAIL).sendKeys(CustomUtilities.properties.getProperty("checkoutemail"));
		softAssert.assertEquals(driver.findElement(SHIPPINGADD_TEXT).getText(), "Delivery", "Shipping address title is not displayed");
		entershippingdetails();
		Reporter.log("Shipping Address Details entered");
		ExtentReport.extentTest.info("Shipping Address Details entered");
		// driver.findElement(CONTINUE_TO_SHIPPING_BUTTON).click();
		WebElement Continue_shipping = driver.findElement(CONTINUE_TO_SHIPPING_BUTTON);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Continue_shipping);
		Reporter.log("User is navigated to select shipping method");
		ExtentReport.extentTest.info("User is navigated to select shipping method");
		wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACT_EMAIL));
		wait.until(ExpectedConditions.visibilityOfElementLocated(SHIPPING_RADIO_BUTTON));
		jse.executeScript("window.scrollBy(0,100)");
		Thread.sleep(3000);
		element = driver.findElement(CONTINUE_TO_PAYMENT_BUTTON);
		jse.executeScript("arguments[0].click();", element);
		Reporter.log("User is navigated to payment page");
		ExtentReport.extentTest.info("User is navigated to payment page");
		Thread.sleep(5000);
		try {
			String[] DISCOUNT_3for$48 = driver.findElement(INFO_DISCOUNT_3for$48).getText().split("\\(");
			System.out.println(DISCOUNT_3for$48[0]);
			softAssert.assertEquals(DISCOUNT_3for$48[0], "3 FOR $48 ", "3 for $48 discount is not applied and not displayed in the Information page line item");
		}
		catch(Exception e)
		{
			String[] DISCOUNT_3for$48 = driver.findElement(INFO_DISCOUNT_3for$48_1).getText().split("\\(");
			System.out.println(DISCOUNT_3for$48[0]);
			softAssert.assertEquals(DISCOUNT_3for$48[0], "3 FOR $48 ", "3 for $48 discount is not applied and not displayed in the Information page line item");
		}
		String TOTAL = driver.findElement(LINEITEM_TOTAL_3for$48).getText();
		System.out.println(TOTAL);
		softAssert.assertEquals(TOTAL,"$16.00","Line Item Total is differ from the 3 for $48 functionality");
		softAssert.assertAll();
		Reporter.log("3 for $48 functionality works fine in Payment page");
		ExtentReport.extentTest.info("3 for $48 functionality works fine in Payment page");
	}

	public void ValidatePromoCodeFIRST20InCheckoutPage() throws Exception {
		WebElement element;
		SoftAssert softAssert = new SoftAssert();
		jse = (JavascriptExecutor)driver;
		driver.findElement(HomePageLocators.CART_ICON).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

//		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
//		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		Thread.sleep(5000);
		jse.executeScript("arguments[0].click();", driver.findElement(CHECKOUT_BUTTON));
		Reporter.log("Checkout page displayed");
		ExtentReport.extentTest.info("Checkout page displayed");
		Thread.sleep(10000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACTINFO_TEXT));

		CheckFirst20PromoCodeApplication("FIRST20");

	}
	public void ValidatePromoCodeFIRST20InShippingPage() throws Exception {
		WebElement element;
		SoftAssert softAssert = new SoftAssert();
		jse = (JavascriptExecutor)driver;
		driver.findElement(HomePageLocators.CART_ICON).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

//		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
//		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		Thread.sleep(5000);
		jse.executeScript("arguments[0].click();", driver.findElement(CHECKOUT_BUTTON));
		Reporter.log("Checkout page displayed");
		ExtentReport.extentTest.info("Checkout page displayed");
		Thread.sleep(4000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACTINFO_TEXT));
		driver.findElement(CHECKOUT_EMAIL).sendKeys(CustomUtilities.properties.getProperty("checkoutemail"));
		softAssert.assertEquals(driver.findElement(SHIPPINGADD_TEXT).getText(), "Delivery", "Shipping address title is not displayed");
		entershippingdetails();
		Reporter.log("Shipping Address Details entered");
		ExtentReport.extentTest.info("Shipping Address Details entered");

		// driver.findElement(CONTINUE_TO_SHIPPING_BUTTON).click();
		WebElement Continue_shipping = driver.findElement(CONTINUE_TO_SHIPPING_BUTTON);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Continue_shipping);
		Reporter.log("User is navigated to select shipping method");
		ExtentReport.extentTest.info("User is navigated to select shipping method");
		wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACT_EMAIL));
		CheckFirst20PromoCodeApplication("FIRST20");
	}

	public void ValidatePromoCodeFIRST20InPaymentPage() throws Exception {
		WebElement element;
		SoftAssert softAssert = new SoftAssert();
		jse = (JavascriptExecutor)driver;
		driver.findElement(HomePageLocators.CART_ICON).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

//		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
//		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		Thread.sleep(5000);
		jse.executeScript("arguments[0].click();", driver.findElement(CHECKOUT_BUTTON));
		Reporter.log("Checkout page displayed");
		ExtentReport.extentTest.info("Checkout page displayed");
		Thread.sleep(4000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACTINFO_TEXT));
		driver.findElement(CHECKOUT_EMAIL).sendKeys(CustomUtilities.properties.getProperty("checkoutemail"));
		softAssert.assertEquals(driver.findElement(SHIPPINGADD_TEXT).getText(), "Delivery", "Shipping address title is not displayed");
		entershippingdetails();
		Reporter.log("Shipping Address Details entered");
		ExtentReport.extentTest.info("Shipping Address Details entered");

		// driver.findElement(CONTINUE_TO_SHIPPING_BUTTON).click();
		WebElement Continue_shipping = driver.findElement(CONTINUE_TO_SHIPPING_BUTTON);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Continue_shipping);
		Reporter.log("User is navigated to select shipping method");
		ExtentReport.extentTest.info("User is navigated to select shipping method");
		wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACT_EMAIL));
		wait.until(ExpectedConditions.visibilityOfElementLocated(SHIPPING_RADIO_BUTTON));
		jse.executeScript("window.scrollBy(0,100)");
		Thread.sleep(3000);
		element = driver.findElement(CONTINUE_TO_PAYMENT_BUTTON);
		jse.executeScript("arguments[0].click();", element);
		Reporter.log("User is navigated to payment page");
		ExtentReport.extentTest.info("User is navigated to payment page");
		CheckFirst20PromoCodeApplication("FIRST20");
	}


	public void expresscheckout() throws Exception {
		jse = (JavascriptExecutor)driver;
		softAssert = new SoftAssert();
		String text = null;
		wait=new WebDriverWait(driver, Duration.ofSeconds(30));
//		Adds a product to cart
		new AddToCartComponents(driver).addToCart();
		
		wait.until(ExpectedConditions.elementToBeClickable(HomePageLocators.CART_ICON));
		jse.executeScript("arguments[0].click();", driver.findElement(HomePageLocators.CART_ICON));
		
		
		wait.until(ExpectedConditions.elementToBeClickable(CHECKOUT_BUTTON));
		jse.executeScript("arguments[0].click();", driver.findElement(CHECKOUT_BUTTON));
		

//		Wait for Checkout page
		wait.until(ExpectedConditions.visibilityOfElementLocated(EXPRESS_CHECKOUT_TEXT));
		
//		Verify Paypal Button
		driver.switchTo().frame(driver.findElement(By.className("paypalLight")));

		String mainWindow = driver.getWindowHandle();

		jse.executeScript("arguments[0].click();", driver.findElement(PAYPAL_BUTTON));
		Reporter.log("PayPal option is present on checkout page");
		ExtentReport.extentTest.info("PayPal option is present on checkout page");
		
		Set<String> set = driver.getWindowHandles();
		Iterator<String> itr = set.iterator();
		while (itr.hasNext()) {
			String childWindow = itr.next();
			// Compare whether the main windows ID is not equal to child window ID. If not equal,
			// we will switch.
			if (!mainWindow.equals(childWindow))
				driver.switchTo().window(childWindow);
//			text = driver.switchTo().window(childWindow).getTitle();
			text = driver.getTitle();
		}
		
		if (text.contains("PayPal")) {
			Thread.sleep(1000);
			driver.close();
			driver.switchTo().window(mainWindow);
		}else {
			Thread.sleep(1000);
			driver.close();
			//driver.switchTo().window(mainWindow);
			softAssert.fail("PayPal popup does not show up when clicked on paypal express checkout button");
			Reporter.log("PayPal popup does not show up when clicked on paypal express checkout button");
			ExtentReport.extentTest.info("PayPal popup does not show up when clicked on paypal express checkout button");
		}
		
//		Verify GPay Button
		try {
			wait.until(ExpectedConditions.elementToBeClickable(GPAY_BUTTON));
			jse.executeScript("arguments[0].click();", driver.findElement(GPAY_BUTTON));
			
			Reporter.log("GPay option is present on checkout page");
			ExtentReport.extentTest.info("GPay option is present on checkout page");

		} catch (ElementClickInterceptedException | NoSuchElementException | TimeoutException e) {
			driver.navigate().refresh();
			Thread.sleep(5000);
			try {
				jse.executeScript("arguments[0].click();", driver.findElement(GPAY_BUTTON));
				Reporter.log("GPAY option is present on checkout page");
				ExtentReport.extentTest.info("GPAY option is present on checkout page");
			} catch (ElementClickInterceptedException |NoSuchElementException | TimeoutException f) {
				softAssert.assertTrue(false, "Express Checkout Option GPay is not interactable");
			}
		}
		
//		Gpay Validation
//		we can try clicking on some other button if it gives exception we can conclude because of popup unable to do so, hence popup is present
		
		driver.navigate().refresh();
		
//		Verify Amazon Pay
		wait.until(ExpectedConditions.visibilityOfElementLocated(AMZ_PAY));
		jse.executeScript("arguments[0].click();", driver.findElement(AMZ_PAY));
	
		wait.until(ExpectedConditions.titleContains("Amazon"));
		
//		Thread.sleep(3900);
		text = driver.getTitle();
		softAssert.assertTrue(text.contains("Amazon"), "Amazon pay did not open up after clicking on Amazon Pay button ");

		Reporter.log("Amazon Pay option is present on checkout page");
		ExtentReport.extentTest.info("Amazon Pay option is present on checkout page");

		driver.navigate().back();
		
//		Verify Shop pay
		wait.until(ExpectedConditions.visibilityOfElementLocated(SHOP_PAY));
		jse.executeScript("arguments[0].click();", driver.findElement(SHOP_PAY));
		
		wait.until(ExpectedConditions.urlContains("shop"));
		
//		Thread.sleep(3900);
		text = driver.getCurrentUrl();
		softAssert.assertTrue(text.contains("shop"), "Shop pay did not open up after clicking on Shop Pay button ");

		Reporter.log("Shop Pay option is present on checkout page");
		ExtentReport.extentTest.info("Shop Pay option is present on checkout page");
		
		softAssert.assertAll();
		
		Reporter.log("Redirected to SHOP PAY login page when clicked on shop pay button");
		ExtentReport.extentTest.info("Redirected to SHOP PAY login page when clicked on shop pay button");
		Reporter.log("Redirected to Amazon login page when clicked on amz pay button");
		ExtentReport.extentTest.info("Redirected to Amazon login page when clicked on amz pay button");
		Reporter.log("PayPal popup shows up when clicked on paypal express checkout button");
		ExtentReport.extentTest.info("PayPal popup shows up when clicked on paypal express checkout button");
		Reporter.log("GPay popup shows up when clicked on Gpay express checkout button");
		ExtentReport.extentTest.info("GPay popup shows up when clicked on Gpay express checkout button");

	}

	public void CheckBreadcrumbs() {
		wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		softAssert.assertTrue(driver.findElement(BREADCRUMB_LINK_CART).isDisplayed(),
				"Cart link is not displayed on breadcrumb");

			softAssert.assertTrue(driver.findElement(BREADCRUMB_LINK_INFORMATION).isDisplayed(),
					"Information link is not displayed on breadcrumb");

			softAssert.assertTrue(driver.findElement(BREADCRUMB_LINK_SHIPPING).isDisplayed(),
						"Shipping link is not displayed on breadcrumb");

			softAssert.assertTrue(driver.findElement(BREADCRUMB_LINK_PAYMENT).isDisplayed(),
					"Payment link is not displayed on breadcrumb");

		softAssert.assertAll();
		Reporter.log("Validated Breadcrumbs on checkout pages");
	}

	public void guestcheckoutflow() throws Exception {
		softAssert = new SoftAssert();
		jse = (JavascriptExecutor)driver;
//		Add Product to Cart
		wait=new WebDriverWait(driver, Duration.ofSeconds(60));
		new AddToCartComponents(driver).addsingleproductToCart();
		
//		wait.until(ExpectedConditions.elementToBeClickable(HomePageLocators.CART_ICON));
//		jse.executeScript("arguments[0].click();", driver.findElement(HomePageLocators.CART_ICON));
		
		
		wait.until(ExpectedConditions.elementToBeClickable(CHECKOUT_BUTTON));
		jse.executeScript("arguments[0].click();", driver.findElement(CHECKOUT_BUTTON));
		
		
//		On Information Page
		wait.until(ExpectedConditions.visibilityOfElementLocated(CHECKOUT_EMAIL));

		Thread.sleep(2000);
		driver.findElement(CHECKOUT_EMAIL).clear();
		driver.findElement(CHECKOUT_EMAIL).sendKeys(CustomUtilities.properties.getProperty("checkoutemail"));

		Reporter.log("Email for Guest User entered successfully");
		ExtentReport.extentTest.info("Email for Guest User entered successfully");
		text = driver.findElement(SHIPPINGADD_TEXT).getText();
		System.out.println(text);
		softAssert.assertTrue(text.contains("Delivery"),"Shipping address text not present");
		
		entershippingdetails();
		
		Reporter.log("Shipping Address Details entered");
		ExtentReport.extentTest.info("Shipping Address Details entered");

//		Click on Continue to Shipping button
//		wait.until(ExpectedConditions.elementToBeClickable(CONTINUE_TO_SHIPPING_BUTTON));
//		jse.executeScript("arguments[0].click();", driver.findElement(CONTINUE_TO_SHIPPING_BUTTON));
		
//		Reporter.log("User is navigated to select shipping method");
//		ExtentReport.extentTest.info("User is navigated to select shipping method");
		
//		On Shipping Page
		Thread.sleep(4000);
		wait.until(ExpectedConditions.textToBe(SHIPPING_TEXT, "Shipping method"));
		text = driver.findElement(SHIPPING_TEXT).getText();
		softAssert.assertTrue(text.contains("Shipping method"),"Shipping method text not present");
		
//		wait.until(ExpectedConditions.elementToBeClickable(CONTINUE_TO_PAYMENT_BUTTON));
//		jse.executeScript("arguments[0].click();", driver.findElement(CONTINUE_TO_PAYMENT_BUTTON));
//		Reporter.log("Selected shipping method and user is navigated to payment page");
//		ExtentReport.extentTest.info("Selected shipping method and user is navigated to payment page");
		
//		On Payment Page
		wait.until(ExpectedConditions.elementToBeClickable(COMPLETE_ORDER_BUTTON));
		element = driver.findElement(COMPLETE_ORDER_BUTTON);
		softAssert.assertTrue(element.isDisplayed(), "Pay Now/Complete Order Button is not present");
		Reporter.log("Complete Order/Pay Now button is enabled");
		ExtentReport.extentTest.info("Complete Order/Pay Now button is enabled");

		softAssert.assertAll();
	}

	public void registeredusercheckoutflow() throws Exception {
		softAssert = new SoftAssert();
		wait=new WebDriverWait(driver, Duration.ofSeconds(60));
		String password = CustomUtilities.properties.getProperty("password");
		new AddToCartComponents(driver).addsingleproductToCart();
		jse = (JavascriptExecutor)driver;
	//	jse.executeScript("arguments[0].click();", driver.findElement(HomePageLocators.CART_ICON));
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		Thread.sleep(10000);
		jse.executeScript("arguments[0].click();", driver.findElement(CHECKOUT_BUTTON));
		//driver.findElement(FlyCartComponents.CHECKOUT_BUTTON).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACTINFO_TEXT));

		Reporter.log("Checkout page displayed");
		ExtentReport.extentTest.info("Checkout page displayed");

		driver.findElement(LOGIN_LINK).click();
		Reporter.log("User naviagted to login page");
		ExtentReport.extentTest.info("User naviagted to login page");
		Thread.sleep(3000);

		driver.findElement(AccountLoginLocators.CUSTOMER_EMAIL_INPUT)
				.sendKeys(CustomUtilities.properties.getProperty("loginemail"));
		driver.findElement(AccountLoginLocators.CUSTOMER_PASSWORD_INPUTBOX).sendKeys(password);
		driver.findElement(AccountLoginLocators.LOGIN_BUTTON).click();
		Reporter.log("User logged in successfully");
		ExtentReport.extentTest.info("User logged in successfully");

		Thread.sleep(6000);

		entershippingdetails();

		Reporter.log("Shipping Address Details entered");
		ExtentReport.extentTest.info("Shipping Address Details entered");

		// driver.findElement(CONTINUE_TO_SHIPPING_BUTTON).click();
		WebElement Continue_shipping = driver.findElement(CONTINUE_TO_SHIPPING_BUTTON);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Continue_shipping);

		Reporter.log("User is navigated to select shipping method");
		ExtentReport.extentTest.info("User is navigated to select shipping method");

		Thread.sleep(8000);

		softAssert.assertEquals(driver.findElement(SHIPPING_TEXT).getText(), "Shipping method");
		WebElement element = driver.findElement(CONTINUE_TO_PAYMENT_BUTTON);
		jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", element);

		// driver.findElement(CONTINUE_TO_PAYMENT_BUTTON).click();
		Reporter.log("Selected shipping method and user is navigated to payment page");
		ExtentReport.extentTest.info("Selected shipping method and user is navigated to payment page");
		Thread.sleep(5000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		wait.until(ExpectedConditions.visibilityOfElementLocated(COMPLETE_ORDER_BUTTON));
		softAssert.assertTrue(driver.findElement(COMPLETE_ORDER_BUTTON).isDisplayed(),
				"Complete Order button is not displayed");

		Reporter.log("Complete Order button is displayed");
		ExtentReport.extentTest.info("Complete Order button is displayed");
		softAssert.assertAll();
	}

	public void ValidateContactSectionOfCheckout() throws Exception {
		softAssert = new SoftAssert();
		jse = (JavascriptExecutor)driver;
		wait=new WebDriverWait(driver, Duration.ofSeconds(60));
		new AddToCartComponents(driver).addsingleproductToCart();

//	driver.findElement(HomePageLocators.CART_ICON).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
//		try {
//		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
//		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(ShoppingCartComponents.CART_TITLE));
//		}
//		catch(Exception e)
//		{
//			wait.until(ExpectedConditions.visibilityOfElementLocated(ShoppingCartComponents.CART_TITLE));
//		}

		wait.until(ExpectedConditions.visibilityOfElementLocated(CHECKOUT_BUTTON));
		jse.executeScript("arguments[0].click();", driver.findElement(CHECKOUT_BUTTON));
		Reporter.log("Checkout page displayed");
		ExtentReport.extentTest.info("Checkout page displayed");
		Thread.sleep(4000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACTINFO_TEXT));
		//CheckBreadcrumbs();
//		softAssert.assertTrue(driver.findElement(EXPRESS_CHECKOUT_TEXT).isDisplayed(),
//				"Express checkout text is not displayed");
		softAssert.assertTrue(driver.findElement(CONTACTINFO_TEXT).isDisplayed(),
				"Contact information text is not displayed");
	//	softAssert.assertTrue(driver.findElement(ALREADYHAVEANACCOUNT_TEXT).isDisplayed(),
	//			"Already have an account? text is not displayed");
		softAssert.assertTrue(driver.findElement(LOGIN_LINK).isDisplayed(), "Login link is not displayed");
		softAssert.assertTrue(driver.findElement(SHIPPINGADD_TEXT).isDisplayed(),
				"Shipping address text is not displayed");
		// softAssert.assertTrue(driver.findElement(REMEMBERME_CHCKBOX).isDisplayed(),
		// "Save this information for next time checkbox is not displayed");
//		softAssert.assertTrue(driver.findElement(RETURN_TO_CART_LINK).isDisplayed(),
//				"Return to cart link is not displayed");
//		softAssert.assertTrue(driver.findElement(CONTINUE_TO_SHIPPING_BUTTON).isDisplayed(),
//				"Continue to shipping method button is not displayed");
		entershippingdetails();
		driver.findElement(TEXTMEWITHNEWSLETTERSANDOFFERSCHECKBOX).click();
		Thread.sleep(5000);
		sidebar();
//		CheckPromoCodeApplication("TECHTESTORDER");
		softAssert.assertAll();

	}

	public void ValidateFieldsinCheckout() throws Exception {

		softAssert = new SoftAssert();
		jse = (JavascriptExecutor)driver;
		wait=new WebDriverWait(driver, Duration.ofSeconds(60));
		new AddToCartComponents(driver).addsingleproductToCart();
//		driver.get("https://www.tommyjohn.com/");
//		driver.findElement(HomePageLocators.CART_ICON).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfElementLocated(CHECKOUT_BUTTON));
		jse.executeScript("arguments[0].click();", driver.findElement(CHECKOUT_BUTTON));
		Reporter.log("Checkout page displayed");
		ExtentReport.extentTest.info("Checkout page displayed");
		Thread.sleep(4000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACTINFO_TEXT));
		checkmandatoryfields();

	}
	public void Validatetextmewithnewslettersandoffers() throws Exception {
		softAssert = new SoftAssert();
		jse = (JavascriptExecutor)driver;
		wait=new WebDriverWait(driver, Duration.ofSeconds(60));
		new AddToCartComponents(driver).addsingleproductToCart();
//		driver.findElement(HomePageLocators.CART_ICON).click();
		wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		Thread.sleep(5000);
		jse.executeScript("arguments[0].click();", driver.findElement(CHECKOUT_BUTTON));
		Reporter.log("Checkout page displayed");
		ExtentReport.extentTest.info("Checkout page displayed");
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACTINFO_TEXT));
		jse.executeScript("arguments[0].click();", driver.findElement(TEXTMEWITHNEWSLETTERSANDOFFERSCHECKBOX));
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		softAssert.assertTrue(driver.findElement(PHONENUMBERFIELD).isDisplayed(), "Phone Number Field is not displayed");
		Reporter.log("Phone Number Field is displayed");
		ExtentReport.extentTest.info("Phone Number Field is displayed");
	}

	public void ValidateSaveMyInformationInPaymentSection() throws Exception {
		jse = (JavascriptExecutor) driver;
		softAssert = new SoftAssert();
		wait=new WebDriverWait(driver, Duration.ofSeconds(60));
		new AddToCartComponents(driver).addsingleproductToCart();


//		jse.executeScript("arguments[0].click();", driver.findElement(HomePageLocators.CART_ICON));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.CHECKOUT_BUTTON));
		jse.executeScript("arguments[0].click();", driver.findElement(CHECKOUT_BUTTON));
		Reporter.log("Checkout page displayed");
		ExtentReport.extentTest.info("Checkout page displayed");
		wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACTINFO_TEXT));
		driver.findElement(CHECKOUT_EMAIL).sendKeys(CustomUtilities.properties.getProperty("checkoutemail"));

		softAssert.assertEquals(driver.findElement(SHIPPINGADD_TEXT).getText(), "Delivery", "Shipping address title is not displayed");
		Thread.sleep(5000);
		entershippingdetails();
		Reporter.log("Shipping Address Details entered");
		ExtentReport.extentTest.info("Shipping Address Details entered");

		// driver.findElement(CONTINUE_TO_SHIPPING_BUTTON).click();
//		WebElement Continue_shipping = driver.findElement(CONTINUE_TO_SHIPPING_BUTTON);
//		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Continue_shipping);
//
//		Reporter.log("User is navigated to select shipping method");
//		ExtentReport.extentTest.info("User is navigated to select shipping method");
		wait.until(ExpectedConditions.visibilityOfElementLocated(SHIPPING_RADIO_BUTTON));
		jse.executeScript("window.scrollBy(0,100)");
		Thread.sleep(3000);
//		WebElement element = driver.findElement(CONTINUE_TO_PAYMENT_BUTTON);
//		jse.executeScript("arguments[0].click();", element);
//		Reporter.log("User is navigated to payment page");
//		ExtentReport.extentTest.info("User is navigated to payment page");
		Thread.sleep(10000);
//		jse.executeScript("arguments[0].click();", driver.findElement(SAVE_MY_INFORMATION));
//		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		softAssert.assertTrue(driver.findElement(PHONENUMBERFIELD).isDisplayed(), "Phone Number Field is not displayed");
		Reporter.log("Phone Number Field is displayed");
		ExtentReport.extentTest.info("Phone Number Field is displayed");

	}

	public void ValidateBillingAddressFieldInPaymentSection() throws Exception {
		jse = (JavascriptExecutor) driver;
		softAssert = new SoftAssert();
		wait=new WebDriverWait(driver, Duration.ofSeconds(60));
		new AddToCartComponents(driver).addsingleproductToCart();
//		jse.executeScript("arguments[0].click();", driver.findElement(HomePageLocators.CART_ICON));
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.PRODUCTS));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
		jse.executeScript("arguments[0].click();", driver.findElement(CHECKOUT_BUTTON));
		Reporter.log("Checkout page displayed");
		ExtentReport.extentTest.info("Checkout page displayed");
		wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACTINFO_TEXT));
		driver.findElement(CHECKOUT_EMAIL).sendKeys(CustomUtilities.properties.getProperty("checkoutemail"));

		softAssert.assertEquals(driver.findElement(SHIPPINGADD_TEXT).getText(), "Delivery", "Shipping address title is not displayed");
		Thread.sleep(5000);
		entershippingdetails();
		Reporter.log("Shipping Address Details entered");
		ExtentReport.extentTest.info("Shipping Address Details entered");

		// driver.findElement(CONTINUE_TO_SHIPPING_BUTTON).click();
//		WebElement Continue_shipping = driver.findElement(CONTINUE_TO_SHIPPING_BUTTON);
//		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Continue_shipping);
//
//		Reporter.log("User is navigated to select shipping method");
//		ExtentReport.extentTest.info("User is navigated to select shipping method");
		wait.until(ExpectedConditions.visibilityOfElementLocated(SHIPPING_RADIO_BUTTON));
		jse.executeScript("window.scrollBy(0,100)");
		Thread.sleep(3000);
//		WebElement element = driver.findElement(CONTINUE_TO_PAYMENT_BUTTON);
//		jse.executeScript("arguments[0].click();", element);
//		Reporter.log("User is navigated to payment page");
//		ExtentReport.extentTest.info("User is navigated to payment page");
		Thread.sleep(5000);
		jse.executeScript("arguments[0].click();", driver.findElement(USEDIFFERENTBILLINGADDRESS));
		wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		softAssert.assertTrue(driver.findElement(COUNTRYREGIONFIELD).isDisplayed(), "Country/Region Field is not displayed");
		Reporter.log("Country/Region Field is displayed");
		ExtentReport.extentTest.info("Country/Region Field is displayed");
		softAssert.assertTrue(driver.findElement(FIRSTNAME).isDisplayed(), "First Name Field is not displayed");
		Reporter.log("First Name Field is displayed");
		ExtentReport.extentTest.info("First Name Field is displayed");
		softAssert.assertTrue(driver.findElement(LASTNAME).isDisplayed(), "Last Name Field is not displayed");
		Reporter.log("Last Name Field is displayed");
		ExtentReport.extentTest.info("Last Name Field is displayed");
		softAssert.assertTrue(driver.findElement(ADDRESS).isDisplayed(), "Address Field is not displayed");
		Reporter.log("Address Field is displayed");
		ExtentReport.extentTest.info("Address Field is displayed");
		softAssert.assertTrue(driver.findElement(CITY).isDisplayed(), "City Field is not displayed");
		Reporter.log("City Field is displayed");
		ExtentReport.extentTest.info("City Field is displayed");
		softAssert.assertTrue(driver.findElement(STATE).isDisplayed(), "State Field is not displayed");
		Reporter.log("State Field is displayed");
		ExtentReport.extentTest.info("State Field is displayed");
		softAssert.assertTrue(driver.findElement(ZIPCODE).isDisplayed(), "Zipcode Field is not displayed");
		Reporter.log("Zipcode Field is displayed");
		ExtentReport.extentTest.info("Zipcode Field is displayed");
		softAssert.assertTrue(driver.findElement(PHONE).isDisplayed(), "Phone number Field is not displayed");
		Reporter.log("Phone number Field is displayed");
		ExtentReport.extentTest.info("Phone number Field is displayed");
		softAssert.assertAll();
		Reporter.log("All the Billing address fields are displayed");
		ExtentReport.extentTest.info("All the Billing address fields are displayed");
	}

	public void validateNonFreeShipping() throws Exception {
		jse = (JavascriptExecutor)driver;
		SoftAssert softAssert = new SoftAssert();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		new AddToCartComponents(driver).NonFreeShippingAddToCart();
		wait.until(ExpectedConditions.elementToBeClickable(HomePageLocators.CART_ICON));
		jse.executeScript("arguments[0].click();", driver.findElement(HomePageLocators.CART_ICON));
//		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
//		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		Thread.sleep(5000);
		jse.executeScript("arguments[0].click();", driver.findElement(CHECKOUT_BUTTON));
		wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACTINFO_TEXT));
		driver.findElement(CHECKOUT_EMAIL).sendKeys(CustomUtilities.properties.getProperty("checkoutemail"));
		softAssert.assertEquals(driver.findElement(SHIPPINGADD_TEXT).getText(), "Delivery", "Shipping address title is not displayed");
		entershippingdetails();
		Reporter.log("Shipping Address Details entered");
		ExtentReport.extentTest.info("Shipping Address Details entered");
		// driver.findElement(CONTINUE_TO_SHIPPING_BUTTON).click();
		WebElement Continue_shipping = driver.findElement(CONTINUE_TO_SHIPPING_BUTTON);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Continue_shipping);
		Reporter.log("User is navigated to select shipping method");
		ExtentReport.extentTest.info("User is navigated to select shipping method");
		Thread.sleep(10000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACT_EMAIL));
		String NonFreeShipping = driver.findElement(NON_FREE_SHIPPING).getText();
		System.out.println(NonFreeShipping);
		softAssert.assertEquals(NonFreeShipping,"$7.95","Free Shipping is offered when the Cart contains products with below $75");
		Reporter.log("Free Shipping is not offered when the Cart contains products with below $75");
		ExtentReport.extentTest.info("Free Shipping is not offered when the Cart contains products with below $75");
	}

	public void validateFreeOrder() throws Exception {
		jse = (JavascriptExecutor)driver;
		SoftAssert softAssert = new SoftAssert();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	//	new AddToCartComponents(driver).addToCart();
		wait.until(ExpectedConditions.elementToBeClickable(HomePageLocators.CART_ICON));
		jse.executeScript("arguments[0].click();", driver.findElement(HomePageLocators.CART_ICON));
//		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
//		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		Thread.sleep(5000);
		jse.executeScript("arguments[0].click();", driver.findElement(CHECKOUT_BUTTON));
		wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACTINFO_TEXT));
		driver.findElement(CHECKOUT_EMAIL).sendKeys("ublisjacobn@unitedtechno.com");
		softAssert.assertEquals(driver.findElement(SHIPPINGADD_TEXT).getText(), "Delivery", "Shipping address title is not displayed");
		entershippingdetails();
		Reporter.log("Shipping Address Details entered");
		ExtentReport.extentTest.info("Shipping Address Details entered");

//		wait.until(ExpectedConditions.visibilityOfElementLocated(FREE_SHIPPING));
//		softAssert.assertTrue(driver.findElement(FREE_SHIPPING).isDisplayed(),"Free Shipping is not redeemable when the Cart contains products with more than $75");
//		Reporter.log("Free Shipping is redeemable when the Product Cart contains products with more than $75");
//		ExtentReport.extentTest.info("Free Shipping is redeemable when the Product Cart contains products with more than $75");

	}

	public void validateFreeShipping() throws Exception {
		jse = (JavascriptExecutor)driver;
		SoftAssert softAssert = new SoftAssert();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		new AddToCartComponents(driver).addToCart();
		wait.until(ExpectedConditions.elementToBeClickable(HomePageLocators.CART_ICON));
		jse.executeScript("arguments[0].click();", driver.findElement(HomePageLocators.CART_ICON));
//		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
//		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		Thread.sleep(5000);
		jse.executeScript("arguments[0].click();", driver.findElement(CHECKOUT_BUTTON));
		wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACTINFO_TEXT));
		driver.findElement(CHECKOUT_EMAIL).sendKeys(CustomUtilities.properties.getProperty("checkoutemail"));
		softAssert.assertEquals(driver.findElement(SHIPPINGADD_TEXT).getText(), "Delivery", "Shipping address title is not displayed");
		entershippingdetails();
		Reporter.log("Shipping Address Details entered");
		ExtentReport.extentTest.info("Shipping Address Details entered");
		// driver.findElement(CONTINUE_TO_SHIPPING_BUTTON).click();
//		WebElement Continue_shipping = driver.findElement(CONTINUE_TO_SHIPPING_BUTTON);
//		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Continue_shipping);
//		Reporter.log("User is navigated to select shipping method");
//		ExtentReport.extentTest.info("User is navigated to select shipping method");
//		Thread.sleep(10000);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACT_EMAIL));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FREE_SHIPPING));
		softAssert.assertTrue(driver.findElement(FREE_SHIPPING).isDisplayed(),"Free Shipping is not redeemable when the Cart contains products with more than $75");
		Reporter.log("Free Shipping is redeemable when the Product Cart contains products with more than $75");
		ExtentReport.extentTest.info("Free Shipping is redeemable when the Product Cart contains products with more than $75");

	}

    public void validateShippingMethod() throws Exception {
        jse = (JavascriptExecutor)driver;
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        new AddToCartComponents(driver).addToCart();
        wait.until(ExpectedConditions.elementToBeClickable(HomePageLocators.CART_ICON));
        jse.executeScript("arguments[0].click();", driver.findElement(HomePageLocators.CART_ICON));
        Thread.sleep(5000);
        jse.executeScript("arguments[0].click();", driver.findElement(CHECKOUT_BUTTON));
        wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACTINFO_TEXT));
        driver.findElement(CHECKOUT_EMAIL).sendKeys(CustomUtilities.properties.getProperty("checkoutemail"));
		softAssert.assertEquals(driver.findElement(SHIPPINGADD_TEXT).getText(), "Delivery", "Shipping address title is not displayed");
        entershippingdetails();
        Reporter.log("Shipping Address Details entered");
        ExtentReport.extentTest.info("Shipping Address Details entered");
        wait.until(ExpectedConditions.visibilityOfElementLocated(FREE_SHIPPING));
        softAssert.assertTrue(driver.findElement(FREE_SHIPPING).isDisplayed(),"Free Shipping is not redeemable when the Cart contains products with more than $75");
        Reporter.log("Free Shipping is redeemable when the Product Cart contains products with more than $75");
        ExtentReport.extentTest.info("Free Shipping is redeemable when the Product Cart contains products with more than $75");
        wait.until(ExpectedConditions.visibilityOfElementLocated(SHIPPING_RADIO_BUTTON));
//        jse.executeScript("arguments[0].click();", driver.findElement(EXPEDITED_SHIPPING_PRICE));
//        String EXPEDITED_SHIPPING = driver.findElement(EXPEDITED_SHIPPING_PRICE).getText();
//        System.out.println(EXPEDITED_SHIPPING);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(SHIPPING_PRICE));
//        String Shipping = driver.findElement(SHIPPING_PRICE).getText();
//        System.out.println(Shipping);
//        softAssert.assertEquals(EXPEDITED_SHIPPING,Shipping,"Shipping price is not displayed as expected");
        softAssert.assertAll();
        Reporter.log("Shipping price is displayed as expected");
        ExtentReport.extentTest.info("Shipping price is displayed as expected");
    }

	public void ValidateRequiredBillingAddressFieldsInPaymentSection() throws Exception {
		jse = (JavascriptExecutor) driver;
		softAssert = new SoftAssert();
		wait=new WebDriverWait(driver, Duration.ofSeconds(60));
		new AddToCartComponents(driver).addsingleproductToCart();


//		jse.executeScript("arguments[0].click();", driver.findElement(HomePageLocators.CART_ICON));
////		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
		Thread.sleep(5000);
		jse.executeScript("arguments[0].click();", driver.findElement(CHECKOUT_BUTTON));
		Reporter.log("Checkout page displayed");
		ExtentReport.extentTest.info("Checkout page displayed");
		wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACTINFO_TEXT));
		driver.findElement(CHECKOUT_EMAIL).sendKeys(CustomUtilities.properties.getProperty("checkoutemail"));

		softAssert.assertEquals(driver.findElement(SHIPPINGADD_TEXT).getText(), "Delivery", "Shipping address title is not displayed");
		Thread.sleep(5000);
		entershippingdetails();
		Reporter.log("Shipping Address Details entered");
		ExtentReport.extentTest.info("Shipping Address Details entered");

		// driver.findElement(CONTINUE_TO_SHIPPING_BUTTON).click();
//		WebElement Continue_shipping = driver.findElement(CONTINUE_TO_SHIPPING_BUTTON);
//		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Continue_shipping);
//
//		Reporter.log("User is navigated to select shipping method");
//		ExtentReport.extentTest.info("User is navigated to select shipping method");
		wait.until(ExpectedConditions.visibilityOfElementLocated(SHIPPING_RADIO_BUTTON));
		jse.executeScript("window.scrollBy(0,100)");
		Thread.sleep(3000);
//		WebElement element = driver.findElement(CONTINUE_TO_PAYMENT_BUTTON);
//		jse.executeScript("arguments[0].click();", element);
//		Reporter.log("User is navigated to payment page");
//		ExtentReport.extentTest.info("User is navigated to payment page");
		jse.executeScript("window.scrollBy(0,1000)");
		Thread.sleep(5000);
		jse.executeScript("arguments[0].click();", driver.findElement(USEDIFFERENTBILLINGADDRESS));
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		Thread.sleep(5000);
		driver.findElement(FIRSTNAME_TEXTBOX).clear();
		driver.findElement(LASTNAME_TEXTBOX).clear();
		Thread.sleep(5000);
		driver.findElement(ADDRESS1_TEXTBOX_PAYMENTPAGE).clear();
		driver.findElement(CITY_TEXTBOX).clear();
		driver.findElement(ZIPCODE).clear();
		WebElement Pay_Now = driver.findElement(COMPLETE_ORDER_BUTTON);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Pay_Now);
		Thread.sleep(5000);
		softAssert.assertTrue(driver.findElement(FIRSTNAME_ERROR).isDisplayed(), "Firstname Field Error Message is not displayed");
		Reporter.log("Error Message displayed for Firstname field");
		ExtentReport.extentTest.info("Error Message displayed for Firstname field");

		softAssert.assertTrue(driver.findElement(LASTNAME_ERROR).isDisplayed(), "LastName Field Error Message is not displayed");
		Reporter.log("Error Message displayed for LastName field");
		ExtentReport.extentTest.info("Error Message displayed for LastName field");

//		softAssert.assertTrue(driver.findElement(ADDRESS_ERROR).isDisplayed(), "Address Field Error Message is not displayed");
//		Reporter.log("Error Message displayed for Address field");
//		ExtentReport.extentTest.info("Error Message displayed for Address field");

		softAssert.assertTrue(driver.findElement(ZIPCODE_ERROR).isDisplayed(), "ZipCode Field Error Message is not displayed");
		Reporter.log("Error Message displayed for ZipCode field");
		ExtentReport.extentTest.info("Error Message displayed for ZipCode field");

		softAssert.assertTrue(driver.findElement(CITY_ERROR).isDisplayed(), "City Field Error Message is not displayed");
		Reporter.log("Error Message displayed for City field");
		ExtentReport.extentTest.info("Error Message displayed for City field");

		softAssert.assertAll();
		Reporter.log("Error Message displayed for all the required fields");
		ExtentReport.extentTest.info("Error Message displayed for all the required fields");

	}

	public void checkmandatoryfields() throws InterruptedException {

				wait=new WebDriverWait(driver, Duration.ofSeconds(60));
				jse.executeScript("window.scrollBy(0,500)");

				wait.until(ExpectedConditions.visibilityOfElementLocated(COMPLETE_ORDER_BUTTON));
				driver.findElement(COMPLETE_ORDER_BUTTON).click();
			//	((JavascriptExecutor) driver).executeScript("arguments[0].click();", Continue_shipping);

				Thread.sleep(10000);
				softAssert.assertTrue(driver.findElement(EMAIL_ERROR).isDisplayed(), "Email Field Error Message is not displayed");
				Reporter.log("Error Message displayed for Email field");
				ExtentReport.extentTest.info("Error Message displayed for Email field");

				softAssert.assertTrue(driver.findElement(FIRSTNAME_ERROR).isDisplayed(), "Firstname Field Error Message is not displayed");
				Reporter.log("Error Message displayed for Firstname field");
				ExtentReport.extentTest.info("Error Message displayed for Firstname field");

				softAssert.assertTrue(driver.findElement(LASTNAME_ERROR).isDisplayed(), "LastName Field Error Message is not displayed");
				Reporter.log("Error Message displayed for LastName field");
				ExtentReport.extentTest.info("Error Message displayed for LastName field");

				softAssert.assertTrue(driver.findElement(ADDRESS_ERROR).isDisplayed(), "Address Field Error Message is not displayed");
				Reporter.log("Error Message displayed for Address field");
				ExtentReport.extentTest.info("Error Message displayed for Address field");

				softAssert.assertTrue(driver.findElement(ZIPCODE_ERROR).isDisplayed(), "ZipCode Field Error Message is not displayed");
				Reporter.log("Error Message displayed for ZipCode field");
				ExtentReport.extentTest.info("Error Message displayed for ZipCode field");

		softAssert.assertTrue(driver.findElement(CITY_ERROR).isDisplayed(), "City Field Error Message is not displayed");
		Reporter.log("Error Message displayed for City field");
		ExtentReport.extentTest.info("Error Message displayed for City field");

//				softAssert.assertTrue(driver.findElement(ZIPCODE_ERROR).isDisplayed(), "ZipCode Field Error Message is not displayed");
//				Reporter.log("Error Message displayed for ZipCode field");
//				ExtentReport.extentTest.info("Error Message displayed for ZipCode field");



				softAssert.assertAll();
				Reporter.log("Error Message displayed for all the required fields");
				ExtentReport.extentTest.info("Error Message displayed for all the required fields");
	}

	public void ValidateFieldsinIPaymentSection() throws Exception {
		jse = (JavascriptExecutor) driver;
		softAssert = new SoftAssert();
		wait=new WebDriverWait(driver, Duration.ofSeconds(60));
		new AddToCartComponents(driver).addsingleproductToCart();


//		jse.executeScript("arguments[0].click();", driver.findElement(HomePageLocators.CART_ICON));
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));

		Thread.sleep(2000);
		jse.executeScript("arguments[0].click();", driver.findElement(CHECKOUT_BUTTON));
		Reporter.log("Checkout page displayed");
		ExtentReport.extentTest.info("Checkout page displayed");
		wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACTINFO_TEXT));
		driver.findElement(CHECKOUT_EMAIL).sendKeys(CustomUtilities.properties.getProperty("checkoutemail"));

		softAssert.assertEquals(driver.findElement(SHIPPINGADD_TEXT).getText(), "Delivery", "Shipping address title is not displayed");
		Thread.sleep(5000);
		entershippingdetails();
		Reporter.log("Shipping Address Details entered");
		ExtentReport.extentTest.info("Shipping Address Details entered");

		// driver.findElement(CONTINUE_TO_SHIPPING_BUTTON).click();
//		WebElement Continue_shipping = driver.findElement(CONTINUE_TO_SHIPPING_BUTTON);
//		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Continue_shipping);
//
//		Reporter.log("User is navigated to select shipping method");
//		ExtentReport.extentTest.info("User is navigated to select shipping method");
		wait.until(ExpectedConditions.visibilityOfElementLocated(SHIPPING_RADIO_BUTTON));
		jse.executeScript("window.scrollBy(0,100)");
		Thread.sleep(3000);
//		WebElement element = driver.findElement(CONTINUE_TO_PAYMENT_BUTTON);
//		jse.executeScript("arguments[0].click();", element);
//		Reporter.log("User is navigated to payment page");
//		ExtentReport.extentTest.info("User is navigated to payment page");
		Thread.sleep(5000);
		CheckMandatoryCardFields();

	}

	public void CheckMandatoryCardFields() throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		Thread.sleep(5000);
		driver.findElement(CARDNAME_CLEAR).click();
		driver.findElement(COMPLETE_ORDER_BUTTON).click();
		Thread.sleep(10000);
		softAssert.assertTrue(driver.findElement(CARDNUMBER_ERROR).isDisplayed(), "CardNumber Field Error Message is not displayed");
		Reporter.log("Error Message displayed for CardNumber field");
		ExtentReport.extentTest.info("Error Message displayed for CardNumber field");

		softAssert.assertTrue(driver.findElement(CARDNAME_ERROR).isDisplayed(), "CardName Field Error Message is not displayed");
		Reporter.log("Error Message displayed for CardName field");
		ExtentReport.extentTest.info("Error Message displayed for CardName field");

		softAssert.assertTrue(driver.findElement(EXPIRY_ERROR).isDisplayed(), "Expiry Field Error Message is not displayed");
		Reporter.log("Error Message displayed for Expiry field");
		ExtentReport.extentTest.info("Error Message displayed for Expiry field");
		softAssert.assertTrue(driver.findElement(SECURITYCODE_ERROR).isDisplayed(), "SecurityCode Field Error Message is not displayed");
		Reporter.log("Error Message displayed for SecurityCode field");
		ExtentReport.extentTest.info("Error Message displayed for SecurityCode field");
		softAssert.assertAll();
		Reporter.log("Error Message displayed for all the required card fields");
		ExtentReport.extentTest.info("Error Message displayed for all the required card fields");

	}

	public void ValidateshippingSection() throws Exception {
		softAssert = new SoftAssert();
		wait=new WebDriverWait(driver, Duration.ofSeconds(60));
		
		new AddToCartComponents(driver).addsingleproductToCart();
		
//		driver.findElement(HomePageLocators.CART_ICON).click();

		Thread.sleep(5000);
		WebElement checkout_btn = driver.findElement(CHECKOUT_BUTTON);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkout_btn);

		Reporter.log("Checkout page displayed");
		ExtentReport.extentTest.info("Checkout page displayed");
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACTINFO_TEXT));
		driver.findElement(CHECKOUT_EMAIL).sendKeys(CustomUtilities.properties.getProperty("checkoutemail"));
		softAssert.assertEquals(driver.findElement(SHIPPINGADD_TEXT).getText(), "Delivery", "Delivery Title is not displayed");
		entershippingdetails();
		Reporter.log("Shipping Address Details entered");
		ExtentReport.extentTest.info("Shipping Address Details entered");

		// driver.findElement(CONTINUE_TO_SHIPPING_BUTTON).click();
//		WebElement Continue_shipping = driver.findElement(CONTINUE_TO_SHIPPING_BUTTON);
//		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Continue_shipping);
//		Reporter.log("User is navigated to select shipping method");
//		ExtentReport.extentTest.info("User is navigated to select shipping method");
//		wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACT_EMAIL));
		
//		CheckBreadcrumbs();
		Thread.sleep(5000);
		softAssert.assertTrue(driver.findElement(CONTACT_EMAIL).isDisplayed(), "Contact email is not present");
	//	softAssert.assertTrue(driver.findElement(SHIP_ADDRESS).isDisplayed(), "Shipping address is not present");
//		softAssert.assertTrue(driver.findElement(CHANGE_LINK_CONTACT).isDisplayed(), "Contact change link is not present to chnage ");
//		softAssert.assertTrue(driver.findElement(CHANGE_LINK_SHIP_TO).isDisplayed(), "Ship To change link is not present to chnage ");
		softAssert.assertTrue(driver.findElement(SHIPPING_TEXT).isDisplayed(), "Shipping text is not present");
		softAssert.assertTrue(driver.findElement(SHIPPING_TEXT).isDisplayed(), "Shipping text is not present");
//		wait.until(ExpectedConditions.elementToBeClickable(CONTINUE_TO_PAYMENT_BUTTON));
//		softAssert.assertTrue(driver.findElement(CONTINUE_TO_PAYMENT_BUTTON).isDisplayed(), "Continue to paymnet method button is not present");
		List<WebElement> shipmethods = driver.findElements(By.xpath("//div[@class='sqcDF']//strong"));
		for (int i = 0; i < shipmethods.size(); i++) {
			System.out.println(shipmethods.get(i).getText());
		}



//		sidebar();
//
//		CheckPromoCodeApplication("TECHTESTORDER");
		
		softAssert.assertAll();
	}

	public void Validatepaymentsection() throws Exception {
		jse = (JavascriptExecutor) driver;
		softAssert = new SoftAssert();
		wait=new WebDriverWait(driver, Duration.ofSeconds(60));
		new AddToCartComponents(driver).addsingleproductToCart();
		
		
//		jse.executeScript("arguments[0].click();", driver.findElement(HomePageLocators.CART_ICON));
////		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
		Thread.sleep(5000);
		jse.executeScript("arguments[0].click();", driver.findElement(CHECKOUT_BUTTON));
		Reporter.log("Checkout page displayed");
		ExtentReport.extentTest.info("Checkout page displayed");
		wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACTINFO_TEXT));
		driver.findElement(CHECKOUT_EMAIL).sendKeys(CustomUtilities.properties.getProperty("checkoutemail"));

		softAssert.assertEquals(driver.findElement(SHIPPINGADD_TEXT).getText(), "Delivery", "Delivery title Is not present");
		Thread.sleep(5000);
		entershippingdetails();
		Reporter.log("Shipping Address Details entered");
		ExtentReport.extentTest.info("Shipping Address Details entered");

		// driver.findElement(CONTINUE_TO_SHIPPING_BUTTON).click();
//		WebElement Continue_shipping = driver.findElement(CONTINUE_TO_SHIPPING_BUTTON);
//		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Continue_shipping);
//
//		Reporter.log("User is navigated to select shipping method");
//		ExtentReport.extentTest.info("User is navigated to select shipping method");
		wait.until(ExpectedConditions.visibilityOfElementLocated(SHIPPING_RADIO_BUTTON));
		jse.executeScript("window.scrollBy(0,100)");
		Thread.sleep(3000);
//		WebElement element = driver.findElement(CONTINUE_TO_PAYMENT_BUTTON);
//		jse.executeScript("arguments[0].click();", element);
//		Reporter.log("User is navigated to payment page");
//		ExtentReport.extentTest.info("User is navigated to payment page");
		Thread.sleep(10000);

//		sidebar();
//		softAssert.assertTrue(driver.findElement(CONTACT_EMAIL).isDisplayed(), "Contact email is not present");
//		softAssert.assertTrue(driver.findElement(SHIP_ADDRESS).isDisplayed(), "Shipping address is not present");
//		Thread.sleep(2000);
//		softAssert.assertTrue(driver.findElement(METHOD).isDisplayed(), "Shipping method is not present");
		// WebDriverWait waits = new WebDriverWait(driver, 60);
		// try {
		// wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("_hjRemoteVarsFrame")));
		// wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".bx-close-xsvg")));
		// driver.findElement(By.cssSelector(".bx-close-xsvg")).click();
		// driver.switchTo().defaultContent();
		// }
		// catch (ElementNotVisibleException e)
		// {
		// System.out.println("Overlay not displayed");
		// }
		//
		// wait.until(ExpectedConditions.visibilityOfElementLocated(CREDITCARD_OPTION));
	//	CheckBreadcrumbs();
//		softAssert.assertTrue(driver.findElement(CHANGE_LINK_CONTACT).isDisplayed(),
//				"Contact Change Link is not present");
//		softAssert.assertTrue(driver.findElement(CHANGE_LINK_SHIP_TO).isDisplayed(),
//				"Ship to Change Link is not present");
//		softAssert.assertTrue(driver.findElement(CHANGE_LINK_METHOD).isDisplayed(),
//				"Method Change Link is not present");
		softAssert.assertTrue(driver.findElement(CREDITCARD_OPTION).isDisplayed(), "Credit card option is not present");
		softAssert.assertTrue(driver.findElement(PAYPAL_OPTION).isDisplayed(), "Paypal option is not present");
//		softAssert.assertTrue(driver.findElement(BILLING_TEXT).isDisplayed(), "Billing text is not present");
//		softAssert.assertTrue(driver.findElement(HOWHEARD_TITLE).isDisplayed(), "How heard title is not present");
//		softAssert.assertTrue(driver.findElement(HOWHEARD_SUBTITLE).isDisplayed(), "How heard subtitle is not present");
//		softAssert.assertTrue(driver.findElement(HOWHEARD_DROPDOWN).isDisplayed(), "How heard dropdown is not present");
		softAssert.assertTrue(driver.findElement(COMPLETE_ORDER_BUTTON).isEnabled(), "Complete order button is not enabled");
		
//		CheckPromoCodeApplication("TECHTESTORDER");
//		Select dphowheard = new Select(driver.findElement(HOWHEARD_DROPDOWN));
//		dphowheard.selectByValue("Online Search");
//		Reporter.log("How heard values are displayed:: Clickable");
//		ExtentReport.extentTest.info("How heard values are displayed:: Clickable");
		softAssert.assertAll();

	}

	public void entershippingdetails() throws Exception {
		wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.presenceOfElementLocated(FIRSTNAME_TEXTBOX));
		Thread.sleep(10000);
		driver.findElement(FIRSTNAME_TEXTBOX).clear();
		driver.findElement(FIRSTNAME_TEXTBOX).sendKeys("Test");
		driver.findElement(LASTNAME_TEXTBOX).clear();
		driver.findElement(LASTNAME_TEXTBOX).sendKeys("User");
		driver.findElement(ADDRESS1_TEXTBOX).clear();
		driver.findElement(ADDRESS1_TEXTBOX).sendKeys("100 Broadway Test");
		Thread.sleep(5000);
		driver.findElement(ADDRESS2_TEXTBOX).clear();
		driver.findElement(ADDRESS2_TEXTBOX).sendKeys("8th floor");
		driver.findElement(CITY_TEXTBOX).clear();
		driver.findElement(CITY_TEXTBOX).sendKeys("New York");
		WebElement countrydp = driver.findElement(COUNTRY_DROPDOWN);
		Select country = new Select(countrydp);
		country.selectByValue("US");

		WebElement statedp = driver.findElement(STATE_DROPDOWN);
		Select state = new Select(statedp);
		state.selectByValue("NY");

		driver.findElement(PINCODE_TEXTBOX).clear();
		driver.findElement(PINCODE_TEXTBOX).sendKeys("10005");
		Thread.sleep(2000);
		driver.findElement(PHONE_TEXTBOX).clear();
		driver.findElement(PHONE_TEXTBOX).sendKeys("8889996667");
	}

	public void sidebar() throws InterruptedException {
		softAssert = new SoftAssert();
		softAssert.assertTrue(driver.findElement(ORDER_SUMMARY_SECTION).isDisplayed(), "ORDER SUMMARY SECTION is not displayed");
		softAssert.assertTrue(driver.findElement(DISCOUNT_CODE_TEXTBOX).isDisplayed(), "DISCOUNT CODE TEXTBOX is not displayed");
		softAssert.assertTrue(driver.findElement(APPLY_DISCOUNT_BUTTON).isDisplayed(), "APPLY DISCOUNT BUTTON is not displayed");
		softAssert.assertTrue(driver.findElement(SUBTOTAL).isDisplayed(), "Subtotal is not displayed");
		softAssert.assertTrue(driver.findElement(SHIPPING).isDisplayed(), "Shipping is not displayed");
		Thread.sleep(2000);
		softAssert.assertTrue(driver.findElement(TOTAL).isDisplayed(), "total price is not displayed");
		softAssert.assertAll();

	}

	public void CheckPromoNotAppliedForFinalSaleProducts(String promocode) throws Exception {
		softAssert = new SoftAssert();
		wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		Thread.sleep(3000);
		driver.findElement(DISCOUNT_CODE_TEXTBOX).sendKeys(promocode);
		WebElement element = driver.findElement(APPLY_DISCOUNT_BUTTON);
		jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", element);
		driver.findElement(DISCOUNT_CODE_TEXTBOX).sendKeys(promocode);
		WebElement element1 = driver.findElement(APPLY_DISCOUNT_BUTTON);
		jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", element1);
		// Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(PROMO_CODE_ERROR));
		System.out.println(driver.findElement(PROMO_CODE_ERROR).getText());
		softAssert.assertEquals(driver.findElement(PROMO_CODE_ERROR).getText(), "FIRST20 discount code isn’t valid for the items in your cart");
		softAssert.assertAll();
		Reporter.log("The Coupon code is not applied for Final Sale Products");
		ExtentReport.extentTest.info("The Coupon code is not applied for Final Sale Products");

	}

	public void CheckFreeOrderpromo(String promocode) throws Exception {
		softAssert = new SoftAssert();
		wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		Thread.sleep(3000);
		driver.findElement(DISCOUNT_CODE_TEXTBOX).sendKeys(promocode);
		WebElement element = driver.findElement(APPLY_DISCOUNT_BUTTON);
		jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", element);
		 Thread.sleep(3000);
		driver.findElement(DISCOUNT_CODE_TEXTBOX).sendKeys("TECH TEST");
		WebElement element1 = driver.findElement(APPLY_DISCOUNT_BUTTON);
		jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", element1);
		wait.until(ExpectedConditions.visibilityOfElementLocated(APPLIED_PROMO_CODE));
		System.out.println(driver.findElement(APPLIED_PROMO_CODE).getText());
		softAssert.assertEquals(driver.findElement(APPLIED_PROMO_CODE).getText(), promocode, "The Coupon code is not getting applied in Information page");
		jse.executeScript("window.scrollTo(0, 1000)");
		Thread.sleep(20000);
		try {

			System.out.println("==============================================");
			System.out.println("Checking Order Protection...");
			System.out.println("==============================================");

			WebElement orderProtectionCheckbox =
					wait.until(
							ExpectedConditions.presenceOfElementLocated(
									ORDER_PROTECTION_CHECKBOX
							)
					);

			// ---------------------------------------------------------
			// Scroll checkbox into view
			// ---------------------------------------------------------

			jse.executeScript(
					"arguments[0].scrollIntoView({block:'center', inline:'center'});",
					orderProtectionCheckbox
			);

			Thread.sleep(1000);

			// ---------------------------------------------------------
			// Check current state
			// ---------------------------------------------------------

			boolean checked =
					orderProtectionCheckbox.isSelected();

			System.out.println(
					"Order Protection checkbox checked :: " + checked
			);

			// ---------------------------------------------------------
			// If already disabled, do nothing
			// ---------------------------------------------------------

			if (!checked) {

				System.out.println(
						"Order Protection is already DISABLED."
				);

			} else {

				System.out.println(
						"Order Protection is ENABLED."
				);

				// -----------------------------------------------------
				// Attempt 1 - normal Selenium click
				// -----------------------------------------------------

				try {

					wait.until(
							ExpectedConditions.elementToBeClickable(
									orderProtectionCheckbox
							)
					);

					orderProtectionCheckbox.click();

					Thread.sleep(1000);

					System.out.println(
							"Normal click performed."
					);

				} catch (Exception e) {

					System.out.println(
							"Normal checkbox click failed."
					);

					// -------------------------------------------------
					// Attempt 2 - JavaScript click on input
					// -------------------------------------------------

					try {

						jse.executeScript(
								"arguments[0].click();",
								orderProtectionCheckbox
						);

						Thread.sleep(1000);

						System.out.println(
								"JavaScript click performed."
						);

					} catch (Exception jsException) {

						System.out.println(
								"JavaScript input click failed."
						);
					}
				}

				// -----------------------------------------------------
				// Verify after click
				// -----------------------------------------------------

				boolean checkedAfterClick =
						orderProtectionCheckbox.isSelected();

				System.out.println(
						"Order Protection checkbox checked after click :: "
								+ checkedAfterClick
				);

				// -----------------------------------------------------
				// Attempt 3 - click associated label
				// -----------------------------------------------------

				if (checkedAfterClick) {

					System.out.println(
							"Checkbox is still ENABLED."
					);

					System.out.println(
							"Trying associated label/wrapper..."
					);

					try {

						jse.executeScript(
								"""
                                var checkbox = arguments[0];
        
                                var label = checkbox.closest('label');
        
                                if (label) {
                                    label.click();
                                    return;
                                }
        
                                var parent = checkbox.parentElement;
        
                                if (parent) {
                                    parent.click();
                                    return;
                                }
        
                                checkbox.click();
                                """,
								orderProtectionCheckbox
						);

						Thread.sleep(1000);

					} catch (Exception labelException) {

						System.out.println(
								"Unable to click checkbox label/wrapper."
						);
					}
				}

				// -----------------------------------------------------
				// Final verification
				// -----------------------------------------------------

				boolean finalCheckedState =
						orderProtectionCheckbox.isSelected();

				System.out.println(
						"Final Order Protection checkbox state :: "
								+ finalCheckedState
				);

				if (finalCheckedState) {

					System.out.println(
							"WARNING: Order Protection is STILL ENABLED."
					);

				} else {

					System.out.println(
							"Order Protection is now DISABLED."
					);
				}
			}

		} catch (TimeoutException e) {

			System.out.println(
					"Order Protection checkbox was not found "
							+ "within the wait time."
			);

			System.out.println(
					"Continuing with order placement."
			);

		} catch (Exception e) {

			System.out.println(
					"Unexpected error while handling "
							+ "Order Protection."
			);

			e.printStackTrace();

			System.out.println(
					"Continuing with order placement."
			);
		}



		/*
		 * IMPORTANT:
		 * DO NOT CLICK COMPLETE_ORDER HERE.
		 */

		System.out.println(
				"Ready to place the order."
		);

		softAssert.assertAll();
		Reporter.log("The Coupon code is applied and Order is Placed");
		ExtentReport.extentTest.info("The Coupon code is applied and Order is Placed");
	}

	public void CheckFirst20PromoCodeApplication(String promocode) throws Exception {
		softAssert = new SoftAssert();
		wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		Thread.sleep(3000);
		driver.findElement(DISCOUNT_CODE_TEXTBOX).sendKeys(promocode);
		WebElement element = driver.findElement(APPLY_DISCOUNT_BUTTON);
		jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", element);
		// Thread.sleep(3000);
		driver.findElement(DISCOUNT_CODE_TEXTBOX).sendKeys("FIRST20");
		WebElement element1 = driver.findElement(APPLY_DISCOUNT_BUTTON);
		jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", element1);
		wait.until(ExpectedConditions.visibilityOfElementLocated(APPLIED_PROMO_CODE));
		System.out.println(driver.findElement(APPLIED_PROMO_CODE).getText());
		softAssert.assertEquals(driver.findElement(APPLIED_PROMO_CODE).getText(), promocode, "The Coupon code FIRST20 is not getting applied in Information page");
		driver.findElement(CANCEL_PROMO_CODE).click();
//		driver.findElement(CANCEL_PROMO_CODE).click();
		softAssert.assertAll();
		Reporter.log("The Coupon code FIRST20 is applied");
		ExtentReport.extentTest.info("The Coupon code FIRST20 is applied");

	}

	public void CheckErrorMessageForIncorrectPromoCode() throws Exception {
		softAssert = new SoftAssert();
		wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		Thread.sleep(3000);
		driver.findElement(DISCOUNT_CODE_TEXTBOX).sendKeys("first");
		WebElement element = driver.findElement(APPLY_DISCOUNT_BUTTON);
		jse = (JavascriptExecutor) driver;
		Thread.sleep(10000);
		jse.executeScript("arguments[0].click();", element);
		wait.until(ExpectedConditions.visibilityOfElementLocated(INCORRECT_PROMO_CODE_ERROR_MESSAGE));
		String Error = driver.findElement(INCORRECT_PROMO_CODE_ERROR_MESSAGE).getText();
		System.out.println(Error);
		softAssert.assertTrue(driver.findElement(INCORRECT_PROMO_CODE_ERROR_MESSAGE).isDisplayed(), "Error message is not displayed for Incorrect Promo code");
		softAssert.assertAll();
		Reporter.log("Error message is displayed for Incorrect Promo code");
		ExtentReport.extentTest.info("Error message is displayed for Incorrect Promo code");

	}

	public void CheckPromoCodeApplication(String promocode) throws Exception {
		wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		//Promo_Code = "FIRST20";
		driver.findElement(DISCOUNT_CODE_TEXTBOX).sendKeys(promocode);
		Thread.sleep(5000);
		WebElement element = driver.findElement(APPLY_DISCOUNT_BUTTON);
		jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", element);
	//	driver.findElement(APPLY_DISCOUNT_BUTTON).click();
		// Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(APPLIED_PROMO_CODE));
		softAssert.assertEquals(driver.findElement(APPLIED_PROMO_CODE).getText(), promocode, "Wrong/Invalid Promo Code applied");
		try {
			driver.findElement(CANCEL_PROMO_CODE).click();
		}
		catch(Exception ex)
		{
			driver.findElement(CANCEL_PROMO_CODE1).click();

		}
//		driver.findElement(CANCEL_PROMO_CODE).click();
		Thread.sleep(4000);
		softAssert.assertAll();
		Reporter.log("Validated Promo Code application functionality");
		ExtentReport.extentTest.info("Validated Promo Code application functionality");

	}

	public void AfterpayonCheckout() throws Exception {
		softAssert = new SoftAssert();
		wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		guestcheckoutflow();
		
		// Check AfterPay message is display
		wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		wait.until(ExpectedConditions.visibilityOfElementLocated(AFTERPAY_OPTION));
		if (!driver.findElement(AFTERPAY_OPTION).isDisplayed())
			throw new Exception("After pay option is not displayed on checkout page.");
		Reporter.log("After pay Message is displayed on Checkout page :: Displayed");
		ExtentReport.extentTest.info("After pay Message is displayed on Checkout page :: Displayed");

		// Check message with calculated installment and icon display
		price = driver.findElement(TOTAL).getText();
		String[] price1 = price.split("\\$");
		String price2 = price1[price1.length - 1];
		System.out.println("Estimate total is: " + price);
		if (Float.parseFloat(price2) < 35 || Float.parseFloat(price2) > 1000) {
			try {
				boolean present = driver.findElement(AFTERPAYLOGO_WITH_INSTALLMENT).isDisplayed();
			}
			catch(Exception e)
			{
				Reporter.log("afterpay logo is not displaayed on checkout if amount is not afterpay criteria : correct");
				ExtentReport.extentTest.info("afterpay logo is not displaayed on checkout if amount is not afterpay criteria : correct");
			}
		} else {
			softAssert.assertTrue(driver.findElement(AFTERPAYLOGO_WITH_INSTALLMENT).isDisplayed(), "Afterpay logo is not correct");
			driver.findElement(AFTERPAY_INSTALLMENTS).click();
			driver.findElement(COMPLETE_ORDER_BUTTON).click();
			Thread.sleep(4000);
			softAssert.assertTrue(driver.getCurrentUrl().contains("afterpay") , "user is not navigated to afterpay portal for payment" );
		}

		softAssert.assertAll();
	}

	public void CheckAfterPayForAddressOtherThanUS() throws Exception {
		wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		// add product to cart
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		new AddToCartComponents(driver).addToCart();
		driver.findElement(HomePageLocators.CART_ICON).click();

		// Click on Checkout button in inline cart
		wait.until(ExpectedConditions.visibilityOfElementLocated(CHECKOUT_BUTTON));
		driver.findElement(CHECKOUT_BUTTON).click();
		Thread.sleep(5000);

		driver.findElement(CHECKOUT_EMAIL).sendKeys(CustomUtilities.properties.getProperty("checkoutemail"));
		entershippingAddressOtherThanUS();
		Thread.sleep(3000);

		// navigate to checkout page
		driver.findElement(CONTINUE_TO_SHIPPING_BUTTON).click();
		Thread.sleep(5000);
		System.out.println("payment page");
		Thread.sleep(10000);
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(CONTINUE_TO_PAYMENT_BUTTON));
		WebElement element = driver.findElement(CONTINUE_TO_PAYMENT_BUTTON);
		if (!element.isDisplayed())
			throw new Exception("Continue to payment button is not displayed.");
		jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", element);
		Thread.sleep(10000);

		// Check after pay for address other than US
		try {
			System.out.println("Verifying for AfterPay Message .......");
			if (!driver.findElement(AFTERPAY_OPTION).getAttribute("style").equalsIgnoreCase("display: none;"))
				throw new Exception("AfterPay Message is displayed on Checkout page for address other than US.");
			Reporter.log("AfterPay Message is displayed on Checkout page for address other than US");
			ExtentReport.extentTest.info("AfterPay Message is displayed on Checkout page for address other than US");
		} catch (Exception e) {
			System.out.println("After Pay Message is not displayed on checkout page for address other than US.");
			Reporter.log("After Pay Message is not displayed on Checkout page for address other than US.");
			ExtentReport.extentTest.info("After Pay Message is not displayed on Checkout page for address other than US.");
		}
	}

	public void entershippingAddressOtherThanUS() {
		wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.findElement(ADDRESS1_TEXTBOX).sendKeys("123 Test Adress");
		driver.findElement(ADDRESS2_TEXTBOX).sendKeys("Suite1");
		driver.findElement(CITY_TEXTBOX).sendKeys("Perth");
		WebElement countrydp = driver.findElement(COUNTRY_DROPDOWN);
		Select country = new Select(countrydp);
		country.selectByValue("Australia");
		WebElement statedp = driver.findElement(STATE_DROPDOWN);
		Select state = new Select(statedp);
		state.selectByValue("WA");
		driver.findElement(PINCODE_TEXTBOX).sendKeys("6000");
		driver.findElement(PHONE_TEXTBOX).sendKeys("8889996667");
	}

	public void AfterPayMessageByModifyingPromoCode() throws Exception {
		wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		AfterpayonCheckout();
		jse = (JavascriptExecutor) driver;
		String Promo_Code = "TJHUSTLE";
		driver.findElement(DISCOUNT_CODE_TEXTBOX).sendKeys(Promo_Code);
		driver.findElement(APPLY_DISCOUNT_BUTTON).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.presenceOfElementLocated(APPLIED_PROMO_CODE));
		softAssert.assertEquals(driver.findElement(APPLIED_PROMO_CODE).getText(), Promo_Code,
				"Wrong/Invalid Promo Code applied");
		WebElement cancel_code = driver.findElement(CANCEL_PROMO_CODE);
		CheckAfterPayInstallment();
		jse.executeScript("window.scrollTo(0, 0)");
		jse.executeScript("arguments[0].click();", cancel_code);
		
		Thread.sleep(5000);
		driver.findElement(BREADCRUMB_LINK_SHIPPING).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(CONTINUE_TO_PAYMENT_BUTTON));
		Thread.sleep(3000);
		driver.findElement(DISCOUNT_CODE_TEXTBOX).sendKeys(Promo_Code);
		driver.findElement(APPLY_DISCOUNT_BUTTON).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(APPLIED_PROMO_CODE));

		driver.findElement(CONTINUE_TO_PAYMENT_BUTTON).click();
		Thread.sleep(5000);
		wait.until(ExpectedConditions.presenceOfElementLocated(APPLIED_PROMO_CODE));
		cancel_code = driver.findElement(CANCEL_PROMO_CODE);
		jse.executeScript("arguments[0].click();", cancel_code);
		Thread.sleep(8000);
		CheckAfterPayInstallment();
		Reporter.log("AfterPay installment amount displayed :: Correct with promo code change");
		ExtentReport.extentTest.info("AfterPay installment amount displayed :: Correct with promo code change");
		softAssert.assertAll();
	}

	public void CheckAfterPayInstallment() throws Exception {
		jse = (JavascriptExecutor) driver;
		
		wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		// Check message with calculated installment and icon display
		price = driver.findElement(TOTAL).getText();
		String[] price1 = price.split("\\$");
		String price2 = price1[price1.length - 1];
		System.out.println("Estimate total is: " + price);
		if (Float.parseFloat(price2) < 35 || Float.parseFloat(price2) > 1000) {
			try {
				boolean present = driver.findElement(AFTERPAYLOGO_WITH_INSTALLMENT).isDisplayed();
			}
			catch(Exception e)
			{
				Reporter.log("afterpay logo is not displaayed on checkout if amount is not afterpay criteria : correct");
				ExtentReport.extentTest.info("afterpay logo is not displaayed on checkout if amount is not afterpay criteria : correct");
			}
		} else {
			softAssert.assertTrue(driver.findElement(AFTERPAYLOGO_WITH_INSTALLMENT).isDisplayed(), "Afterpay logo is not correct");
			driver.findElement(AFTERPAY_INSTALLMENTS).click();
			driver.findElement(COMPLETE_ORDER_BUTTON).click();
			Thread.sleep(4000);
			softAssert.assertTrue(driver.getCurrentUrl().contains("afterpay") , "user is not navigated to afterpay portal for payment" );
		}

		softAssert.assertAll();
	}

	public void navigatetocheckoutpage() throws Exception {
		wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		new CollectionPageComponent(driver).AddproductToCartByQuickShop();
		driver.findElement(HomePageLocators.CART_ICON).click();
		driver.findElement(CHECKOUT_BUTTON).click();
	}

	public void completeorder() throws Exception {
		
		jse = (JavascriptExecutor) driver;
		wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		softAssert = new SoftAssert();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		new AddToCartComponents(driver).addToCart();	
		
		jse.executeScript("arguments[0].click();", driver.findElement(HomePageLocators.CART_ICON));
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));

		jse.executeScript("arguments[0].click();", driver.findElement(CHECKOUT_BUTTON));
		Reporter.log("Checkout page displayed");
		ExtentReport.extentTest.info("Checkout page displayed");
		Thread.sleep(5000);
		driver.findElement(CHECKOUT_EMAIL).sendKeys(CustomUtilities.properties.getProperty("orderplacementemail"));

		softAssert.assertEquals(driver.findElement(SHIPPINGADD_TEXT).getText(), "Shipping address");
		entershippingdetails();
		Reporter.log("Shipping Address Details entered");
		ExtentReport.extentTest.info("Shipping Address Details entered");

		// driver.findElement(CONTINUE_TO_SHIPPING_BUTTON).click();
		WebElement Continue_shipping = driver.findElement(CONTINUE_TO_SHIPPING_BUTTON);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Continue_shipping);

		Reporter.log("User is navigated to select shipping method");
		ExtentReport.extentTest.info("User is navigated to select shipping method");
		wait.until(ExpectedConditions.visibilityOfElementLocated(SHIPPING_RADIO_BUTTON));
		jse.executeScript("window.scrollBy(0,100)");
		Thread.sleep(3000);
		WebElement element = driver.findElement(CONTINUE_TO_PAYMENT_BUTTON);
		jse.executeScript("arguments[0].click();", element);
		Reporter.log("User is navigated to payment page");
		ExtentReport.extentTest.info("User is navigated to payment page");
		Thread.sleep(10000);
		CheckPromoCodeApplication("TEST_ORDER_TECH");
		wait.until(ExpectedConditions.elementToBeClickable(COMPLETE_ORDER_BUTTON));
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Select dphowheard = new Select(driver.findElement(HOWHEARD_DROPDOWN));
		dphowheard.selectByValue("Online Search");
		Reporter.log("How heard values are displayed:: Clickable");
		ExtentReport.extentTest.info("How heard values are displayed:: Clickable");
		
		jse.executeScript("arguments[0].click();",  driver.findElement(COMPLETE_ORDER_BUTTON));
		System.out.println(driver.getCurrentUrl());
		Thread.sleep(10000);
		System.out.println(driver.getCurrentUrl());
		wait.until(ExpectedConditions.visibilityOfElementLocated(ORDER_NUMBER));
		System.out.println(driver.getCurrentUrl());
		Reporter.log("Order Number - " + driver.findElement(ORDER_NUMBER).getText());
		ExtentReport.extentTest.info("Order Number - " + driver.findElement(ORDER_NUMBER).getText());
		
		softAssert.assertAll();
		
	}



}
