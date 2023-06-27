package com.tommyjohn.automation.Pages;

import org.openqa.selenium.WebDriver;

import com.tommyjohn.automation.Components.BlogPageComponents;

public class BlogPage extends BlogPageComponents{
	
	
		public BlogPage(WebDriver driver) {
			super(driver);
			// TODO Auto-generated constructor stub
		}
		public void validateBlogPage() throws Exception
		{
			new BlogPageComponents(driver).validateBlog();
		}
}
