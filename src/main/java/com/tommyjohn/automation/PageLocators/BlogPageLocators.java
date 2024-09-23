package com.tommyjohn.automation.PageLocators;

import org.openqa.selenium.By;


public class BlogPageLocators {

	public static By BLOG_LINK = By.cssSelector("a[data-tj-qa='footer-blog']");
	public static By BLOG_TITLE = By.xpath("//div[@class='wrapper prose']");
	
	public static By ARTICLE_IMG= By.xpath("//div[@class='lg:col-span-2']//img");
	public static By ARTICLE_HEADING= By.xpath("//h2[@class='text-heading-lg text-text-primary leading-tight mb-lg']");
	
	public static By READ_MORE_ARTICLE= By.xpath("//p[@class='button-secondary']");
	
	public static By BLOG_CARD_IMG= By.xpath("(//a[@class='sm:flex sm:flex-col']//img)[1]");
	public static By BLOG_CARD_TITLE= By.xpath("(//h2[@class='text-heading-sm'])[1]");
	
	public static By READ_MORE_BLOG_CARD= By.xpath("(//p[@class='button-link mt-6'])[1]");
	public static By BLOG_RETURN_LINK=By.xpath("//a[@class=' flex items-center button-link']");
	
	public static By BLOG_ARTICLE_DATE=By.xpath("//div[@class='wrapper max-w-screen-md text-text-secondary']//time");
	public static By BLOG_ARTICLE_TITLE=By.xpath("//h1[@class='text-heading-lg leading-tight font-normal']");
	public static By BLOG_ARTICLE_CONTENT=By.xpath("(//div[@class='wrapper max-w-screen-md text-body-lg text-text-secondary leading-loose']//p)[1]");
	
	public static By BLOG_PREV_ARTICLE=By.xpath("(//div[@class='wrapper max-w-screen-md mt-2xl flex gap-6 max-sm:grow max-sm:justify-between']//article)[1]");
	public static By BLOG_PREV_LINK=By.xpath("(//div[@class='wrapper max-w-screen-md mt-2xl flex gap-6 max-sm:grow max-sm:justify-between']//a)[1]");
	public static By BLOG_NEXT_ARTICLE=By.xpath("(//div[@class='wrapper max-w-screen-md mt-2xl flex gap-6 max-sm:grow max-sm:justify-between']//article)[2]");
	public static By BLOG_NEXT_LINK=By.xpath("(//div[@class='wrapper max-w-screen-md mt-2xl flex gap-6 max-sm:grow max-sm:justify-between']//a)[2]");
	
	public static By LOAD_MORE= By.xpath("");
	
	
	
}