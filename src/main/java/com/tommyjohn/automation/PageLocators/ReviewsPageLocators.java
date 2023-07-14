package com.tommyjohn.automation.PageLocators;

import org.openqa.selenium.By;

public class ReviewsPageLocators {
	public static By PAGE_TITLE = By.cssSelector(".page-header>h1");
	
	public static By PAGE_TITLE_SUBTEXT = By.cssSelector(".rte.page-content>p");
	public static By TOTAL_STAR_RATINGS = By.cssSelector(".yotpo-bottomline.pull-left>span");
	public static By TOTAL_REVIEWS = By.cssSelector(".yotpo-bottomline.pull-left>a");
	public static By CX_REVIEWS = By.cssSelector(".yotpo-review.yotpo-regular-box.yotpo-regular-box-filters-padding");
	public static By CX_REVIEWS_TITLE = By.cssSelector(".yotpo-review.yotpo-regular-box.yotpo-regular-box-filters-padding>div:nth-child(2)>div");
	public static By CX_REVIEWS_SUBTITLE = By.cssSelector(".yotpo-review.yotpo-regular-box.yotpo-regular-box-filters-padding>div:nth-child(2)>div:nth-child(3)");
	public static By SECTION_TITLE = By.cssSelector(".rte.page-content>h2:nth-child(4)");
	public static By SECTION_LINK1 = By.cssSelector(".rte.page-content>p:nth-child(5)>a");
	public static By SECTION_LINK2 = By.cssSelector(".rte.page-content>p:nth-child(6)>a");
	public static By SECTION_LINK3 = By.cssSelector(".rte.page-content>p:nth-child(7)>a");
	public static By PAGES = By.cssSelector(".yotpo-pager>a");
	
	
	
	
	
	
	
}
