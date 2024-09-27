package com.tommyjohn.automation.PageLocators;

import org.openqa.selenium.By;

public class ReviewsPageLocators {
	public static By PAGE_TITLE = By.xpath("//h1[contains(text(),'Tommy John Reviews')]");
	
	public static By PAGE_TITLE_SUBTEXT = By.xpath("(//div[@id='shopify-section-template--17096995569709__main']//p)[1]");
	public static By TOTAL_STAR_RATINGS = By.xpath("//span[@class='yotpo-stars']");
	public static By TOTAL_REVIEWS = By.xpath("//a[@class='text-m']");
	public static By CX_REVIEWS = By.xpath("//span[@class='y-label yotpo-user-name yotpo-font-bold pull-left']");
	public static By CX_REVIEWS_TITLE = By.xpath("//div[@class='content-title yotpo-font-bold']");
	public static By CX_REVIEWS_SUBTITLE = By.xpath("//span[@class='y-label yotpo-user-title yotpo-action-hover']");
	public static By SECTION_TITLE = By.xpath("//h2[contains(text(),'What Others Are Saying About Tommy John Underwear')]");
	public static By SECTION_LINK1 = By.xpath("//a[@title='Honest Tommy John Review']");
	public static By SECTION_LINK2 = By.xpath("//a[@title='Honest Brand Reviews']");
	public static By SECTION_LINK3 = By.xpath("//a[@title='Are Tommy John's Underwear Worth It?']");
	public static By PAGES = By.xpath("//a[@class='yotpo-page-element goTo ']");
	
	
	
	
	
	
	
}
