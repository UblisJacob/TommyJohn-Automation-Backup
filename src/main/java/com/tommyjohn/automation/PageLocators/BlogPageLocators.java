package com.tommyjohn.automation.PageLocators;

import org.openqa.selenium.By;


public class BlogPageLocators {

	public static By BLOG_LINK = By.cssSelector("a[data-tj-qa='footer-blog']");
	public static By BLOG_TITLE = By.xpath("//*[data-tj-qa='blog-heading'] | //p[@title='Tommy John Blog']");
	
	public static By ARTICLE_IMG= By.xpath("[data-tj-qa='blog-article-image'],.article-Img");
	public static By ARTICLE_HEADING= By.xpath("[data-tj-qa='blog-article-summary']>h1,//div[@class='article-summary']//h1[@class='article-heading']");
	
	public static By READ_MORE_ARTICLE= By.xpath("[data-tj-qa='blog-article-summary-more'],//div[@class='article-content']//a[@title='Read More']");
	
	public static By BLOG_CARD_IMG= By.xpath("[data-tj-qa='blog-related-articles']>div:nth-child(1),//div[@class='blog-card']//div[@class='blog-image']");
	public static By BLOG_CARD_TITLE= By.xpath("[data-tj-qa='blog-related-articles']>div:nth-child(2),//div[@class='blog-card']//div[@class='blog-info']");
	
	public static By READ_MORE_BLOG_CARD= By.xpath("//div[@class='blog-card']//a[@title='Read Article']");
	public static By BLOG_RETURN_LINK=By.xpath("[data-tj-qa='back-to-blog-page'],//a[@title='Tommy John Blog']");
	
	public static By BLOG_ARTICLE_DATE=By.xpath("//div[@class='articleContent']//div[@class='articleDate']");
	public static By BLOG_ARTICLE_TITLE=By.xpath("[data-tj-qa='blog-page-article-heading'],//div[@class='articleContent']//h1[@class='articleTitle']");
	public static By BLOG_ARTICLE_CONTENT=By.xpath("[data-tj-qa='blog-page-article-main-content'],//div[@class='articleContent']//div[@class='articleMainContent']");
	
	public static By BLOG_PREV_ARTICLE=By.xpath("//div[@class='articleLinks']//div[@class='prevArticle articleCard']");
	public static By BLOG_PREV_LINK=By.xpath("[data-tj-qa='blog-previous-article,],//div[@class='articleLinks']//div[@class='prevArticle articleCard']//a[@title='Previous']");
	public static By BLOG_NEXT_ARTICLE=By.xpath("//div[@class='articleLinks']//div[@class='nextArticle articleCard']");
	public static By BLOG_NEXT_LINK=By.xpath("[data-tj-qa='blog-next-article'],//div[@class='articleLinks']//div[@class='nextArticle articleCard']//a[@title='Next']");
	
	public static By LOAD_MORE= By.xpath("[data-tj-qa='blog-load-more'],//div[@class='loadMoreContainer']//a[@title='Load More']");
	
	
	
}