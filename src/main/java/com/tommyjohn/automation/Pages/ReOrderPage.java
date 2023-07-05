package com.tommyjohn.automation.Pages;

import org.openqa.selenium.WebDriver;

import com.tommyjohn.automation.Components.ReOrderPageComponents;

public class ReOrderPage extends ReOrderPageComponents {

	WebDriver driver;

	public ReOrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void validateReOrderPage() throws Exception {
		new ReOrderPageComponents(driver).reorderPagevalidation();
	}

	public void reOrderItem() throws Exception {
		new ReOrderPageComponents(driver).reorderitem();
	}

	public void reOrderToPDP() throws Exception {
		new ReOrderPageComponents(driver).reorderpagetopdp();
	}

	public void orderDetails() throws Exception {
		new ReOrderPageComponents(driver).orderdetails();
	}

	public void reviewPage() throws Exception {
		new ReOrderPageComponents(driver).reviewsPage_fromReorder();
	}

}
