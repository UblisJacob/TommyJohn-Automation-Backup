package com.tommyjohn.automation.Pages;

import org.openqa.selenium.WebDriver;

import com.tommyjohn.automation.Components.ReOrderPageComponents;
import com.tommyjohn.automation.Components.ReviewsPageComponents;

public class ReviewsPage {
	WebDriver driver;

	public ReviewsPage(WebDriver driver) {
		
		this.driver = driver;
	}

	public void validateReviewsPage() throws Exception {
		new ReviewsPageComponents(driver).validateReviewsPage();
	
}
}