package com.tommyjohn.automation.PageLocators;

import org.openqa.selenium.By;

public class SanityModuleLocators {

	//public static By HERO_IMAGE_1 = By.cssSelector("body > main:nth-child(27) > div:nth-child(3) > div:nth-child(1) > div:nth-child(5) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1)");
	public static By HERO_IMAGE_1 = By.xpath("(//div[@class='shopify-section relative']//img)[1]");
	
	
	public static By HERO_IMAGE_TEXT = By.xpath("//div[@class='my-14 md:my-3xl']//h2");
	
	public static By FIXED_BANNER_IMG = By.xpath("(//div[@class='shopify-section relative']//img)[1]");
	
	public static By THREE_TILE_MODULE = By.xpath("//div[@class='[grid-area:doorways] flex flex-col md:flex-row gap-2xs my-2xs']");
	public static By THREE_TILE_IMG_1 = By.xpath("//a[@aria-label='Shop Luxurious softness & daily support.']");
	public static By THREE_TILE_IMG_2 = By.xpath("//a[@aria-label='Shop Stay cool, dry & sweat free.']");
	public static By THREE_TILE_IMG_3 = By.xpath("//a[@aria-label='Shop Innovative, ultra-light & breathable.']");
	
	public static By IMG_CAROUSEL = By.xpath("//div[@class='wrapper max-sm:pr-0']");
	public static By IMG_CAROUSEL_1 = By.xpath("//a[@aria-label='Shop Underwear']//img");
	public static By IMG_CAROUSEL_2 = By.xpath("//a[@aria-label='Shop Undershirts']//img");
	public static By IMG_CAROUSEL_3 = By.xpath("//a[@aria-label='Shop Intimates']//img");
	public static By IMG_CAROUSEL_4 = By.xpath("//a[@aria-label='Shop Sleep Sets']//img");
	public static By SLIDE_CTRL = By.xpath("//span[@class='icon icon-chevron-right']");
	
	public static By GPTW_FIXED_BANNER = By.xpath("(//div[@data-tj-qa='fixed-banner-module'])[2]");
	public static By GPTW_FIXED_BANNER_IMG = By.xpath("(//img[@data-tj-qa='fixed-banner-image'])[2]");
	public static By GPTW_FIXED_BANNER_LINK = By.cssSelector("a[data-tj-qa='fixed-banner-cta']");
	
	public static By SIDE_BY_SIDE = By.xpath("(//div[@class='shopify-section'])[5]");
	public static By SIDE_BY_SIDE_IMG1 = By.xpath("//div[@class='mb-lg max-md:-mx-lg max-md:px-2xs']//img");
	public static By SIDE_BY_SIDE_IMG2 = By.xpath("//a[@class='relative block size-full md:scrim-top-left md:scrim-dark']//img");
	public static By SIDE_BY_SIDE_CTA = By.xpath("//a[@class='max-md:button-secondary md:button w-max']");
	public static By SIDE_BY_SIDE_TEXT = By.xpath("//span[@class='md:absolute md:-translate-x-full md:l-0']");
	
	public static By HERO_CTA = By.xpath("//button[@id='CustomSwiperNext-template--16940781600813__hero_slider_3jfe7T']");
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}