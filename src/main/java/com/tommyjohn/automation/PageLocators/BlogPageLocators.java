package com.tommyjohn.automation.PageLocators;

import org.openqa.selenium.By;


public class BlogPageLocators {

	public static By BLOG_LINK = By.cssSelector("a[data-tj-qa='footer-blog']");
	public static By BLOG_TITLE = By.xpath("//p[@title='Tommy John Blog']");
	
	public static By ARTICLE_IMG= By.xpath("//div[@class='article-content']//div[@class='article-Img']");
	public static By ARTICLE_HEADING= By.xpath("//div[@class='article-summary']//h1[@class='article-heading']");
	
	public static By READ_MORE_ARTICLE= By.xpath("//div[@class='article-content']//a[@title='Read More']");
	
	public static By BLOG_CARD_IMG= By.xpath("//div[@class='blog-card']//div[@class='blog-image']");
	public static By BLOG_CARD_TITLE= By.xpath("//div[@class='blog-card']//div[@class='blog-info']");
	
	public static By READ_MORE_BLOG_CARD= By.xpath("//div[@class='blog-card']//a[@title='Read Article']");
	public static By BLOG_RETURN_LINK=By.xpath("//a[@title='Tommy John Blog']");
	
	public static By BLOG_ARTICLE_DATE=By.xpath("//div[@class='articleContent']//div[@class='articleDate']");
	public static By BLOG_ARTICLE_TITLE=By.xpath("//div[@class='articleContent']//h1[@class='articleTitle']");
	public static By BLOG_ARTICLE_CONTENT=By.xpath("//div[@class='articleContent']//div[@class='articleMainContent']");
	
	public static By BLOG_PREV_ARTICLE=By.xpath("//div[@class='articleLinks']//div[@class='prevArticle articleCard']");
	public static By BLOG_PREV_LINK=By.xpath("//div[@class='articleLinks']//div[@class='prevArticle articleCard']//a[@title='Previous']");
	public static By BLOG_NEXT_ARTICLE=By.xpath("//div[@class='articleLinks']//div[@class='nextArticle articleCard']");
	public static By BLOG_NEXT_LINK=By.xpath("//div[@class='articleLinks']//div[@class='nextArticle articleCard']//a[@title='Next']");
	
	public static By LOAD_MORE= By.xpath("//div[@class='loadMoreContainer']//a[@title='Load More']");
	
	
	
}