package com.tommyjohn.automation.PageLocators;

import org.openqa.selenium.By;

public class SanityModuleLocators {

	//public static By HERO_IMAGE_1 = By.cssSelector("body > main:nth-child(27) > div:nth-child(3) > div:nth-child(1) > div:nth-child(5) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1)");
	public static By HERO_IMAGE_1 = By.cssSelector(".hero-module_image-container img[alt='Tommy John Image']");
	
	
	public static By HERO_IMAGE_TEXT = By.cssSelector("div[class='vue-title_text']>div>p:nth-child(1)");
	
	public static By FIXED_BANNER_IMG = By.cssSelector("[data-tj-qa='fixed-banner-image'],div[data-tj-page-section-position='3']>div img");
	
	public static By THREE_TILE_MODULE = By.cssSelector(".three-tile-module.vue-module-container");
	public static By THREE_TILE_IMG_1 = By.cssSelector(".three-tile-module.vue-module-container>div>div:nth-child(1)");
	public static By THREE_TILE_IMG_2 = By.cssSelector(".three-tile-module.vue-module-container>div>div:nth-child(2)");
	public static By THREE_TILE_IMG_3 = By.cssSelector(".three-tile-module.vue-module-container>div>div:nth-child(3)");
	
	public static By IMG_CAROUSEL = By.cssSelector("[data-tj-qa='side-module-1'],div[data-tj-page-section-position='5']>div");
	public static By IMG_CAROUSEL_1 = By.cssSelector("div[data-slick-index='0']");
	public static By IMG_CAROUSEL_2 = By.cssSelector("div[data-slick-index='1']");
	public static By IMG_CAROUSEL_3 = By.cssSelector("div[data-slick-index='2']");
	public static By IMG_CAROUSEL_4 = By.cssSelector("div[data-slick-index='3']");
	public static By SLIDE_CTRL = By.cssSelector("button#slick-slide-control11");
	
	public static By GPTW_FIXED_BANNER = By.cssSelector("div[data-position='p5']");
	public static By GPTW_FIXED_BANNER_IMG = By.cssSelector("div[data-position='p5']>div>picture");
	public static By GPTW_FIXED_BANNER_LINK = By.cssSelector("div[data-position='p5']>div:nth-child(2)>div:nth-child(2)>div>a");
	
	public static By SIDE_BY_SIDE = By.cssSelector("div[data-position='p4']");
	public static By SIDE_BY_SIDE_IMG1 = By.cssSelector("div[data-position='p4']>.side-container.side-one-container.sub-position-1");
	public static By SIDE_BY_SIDE_IMG2 = By.cssSelector("div[data-position='p4']>.side-container.side-two-container.sub-position-2");
	public static By SIDE_BY_SIDE_CTA = By.cssSelector("div[data-position='p4']>.side-container.side-one-container.sub-position-1>div:nth-child(2)>div:nth-child(3)>div>a");
	public static By SIDE_BY_SIDE_TEXT = By.cssSelector("div[data-position='p4']>.side-container.side-one-container.sub-position-1>div:nth-child(2)>div>div");
	
	public static By HERO_CTA = By.cssSelector("#btn-hero-module");
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}