package com.tommyjohn.automation.PageLocators;

import org.openqa.selenium.By;

public class SanityModuleLocators {

	//public static By HERO_IMAGE_1 = By.cssSelector("body > main:nth-child(27) > div:nth-child(3) > div:nth-child(1) > div:nth-child(5) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1)");
	public static By HERO_IMAGE_1 = By.cssSelector(".hero-module_image-container img[alt='Tommy John Image']");
	
	
	public static By HERO_IMAGE_TEXT = By.xpath("//div[@class='vue-title_text'][1]");
	
	public static By FIXED_BANNER_IMG = By.cssSelector("[data-tj-qa='fixed-banner-image'],div[data-tj-page-section-position='3']>div img");
	
	public static By THREE_TILE_MODULE = By.cssSelector(".three-tile-module.vue-module-container");
	public static By THREE_TILE_IMG_1 = By.cssSelector(".three-tile-module.vue-module-container>div>div:nth-child(1)");
	public static By THREE_TILE_IMG_2 = By.cssSelector(".three-tile-module.vue-module-container>div>div:nth-child(2)");
	public static By THREE_TILE_IMG_3 = By.cssSelector(".three-tile-module.vue-module-container>div>div:nth-child(3)");
	
	public static By IMG_CAROUSEL = By.cssSelector("div[data-tj-page-section='quotes-carousel']");
	public static By IMG_CAROUSEL_1 = By.cssSelector("div[data-tj-qa='quotes-tile'][data-slick-index='0']");
	public static By IMG_CAROUSEL_2 = By.cssSelector("div[data-tj-qa='quotes-tile'][data-slick-index='1']");
	public static By IMG_CAROUSEL_3 = By.cssSelector("div[data-tj-qa='quotes-tile'][data-slick-index='2']");
	public static By IMG_CAROUSEL_4 = By.cssSelector("div[data-tj-qa='quotes-tile'][data-slick-index='3']");
	public static By SLIDE_CTRL = By.cssSelector("ul.slick-dots>li[role='presentation']>button");
	
	public static By GPTW_FIXED_BANNER = By.cssSelector("div[data-tj-qa='fixed-banner-module']");
	public static By GPTW_FIXED_BANNER_IMG = By.cssSelector("img[data-tj-qa='fixed-banner-image']");
	public static By GPTW_FIXED_BANNER_LINK = By.cssSelector("a[data-tj-qa='fixed-banner-cta']");
	
	public static By SIDE_BY_SIDE = By.cssSelector("div.side-by-side-module");
	public static By SIDE_BY_SIDE_IMG1 = By.cssSelector("div[data-tj-qa='side-module-1']");
	public static By SIDE_BY_SIDE_IMG2 = By.cssSelector("div[data-tj-qa='side-module-2']");
	public static By SIDE_BY_SIDE_CTA = By.cssSelector("div[data-tj-qa='side-module-1']>div.side-module_text-container>div.vue-cta-parent-container>div>a");
	public static By SIDE_BY_SIDE_TEXT = By.cssSelector("div[data-tj-qa='side-module-1']>div.side-module_text-container>div.vue-title-container>div");
	
	public static By HERO_CTA = By.cssSelector("#btn-hero-module");
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}