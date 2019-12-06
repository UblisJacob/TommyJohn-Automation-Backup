package com.tommyjohn.automation.PageLocators;

import org.openqa.selenium.By;

public class MixAndMatchLocators 
{	
	public static By MIX_MATCH_HEADER = By.cssSelector(".mm_header_content>h1");
	public static By ATC_CTA =By.cssSelector(".add-to-cart-floating-div>a");
	public static By SELECT_SIZES_BTN_TEXT = By.cssSelector(".add-to-cart-floating-div>a>span.desktop-view");
	public static By TOTAL_SLIDE_SECTIONS=By.cssSelector(".mixmatch_slidesection");
	public static By GET_BOTH_CTA = By.cssSelector(".add-to-cart>span");
	
	public static By TOTAL_IMG_IN_TOP_SLIDE = By.cssSelector(".mixmatch-container>div:nth-child(2) > div.mixmatch_slidesection > div > div > div > div > div > img");
	public static By TOP_SLIDE_SLICK_NEXT = By.cssSelector(".mixmatch_slidesection>.mixmatch_slider_top>button.slick-prev");
	public static By TOP_SLIDE_SLICK_PREV = By.cssSelector(".mixmatch_slidesection>.mixmatch_slider_top>button.slick-next");
	public static By TOP_SLIDE_CENTER_PRODUCT_IMG = By.cssSelector("div.mixmatch_images.desktop-top > div.mixmatch_slidesection > div > div > div > div.slick-slide.slick-current.slick-active.slick-center > div > img");
	public static By TOP_SLIDE_CENTER_PRODUCT_NAME =By.cssSelector("div.mixmatch_images.desktop-top > div.mixmatch_slidesection > div > div > div > div.slick-slide.slick-current.slick-active.slick-center > div > div.mm-mobile > a > h4");
	
	public static By TOTAL_IMG_IN_BOTTOM_SLIDE = By.cssSelector(".mixmatch-container>div:nth-child(3) > div.mixmatch_slidesection > div > div > div > div > div > img");
	public static By BOTTOM_SLIDE_SLICK_NEXT = By.cssSelector(".mixmatch_slidesection>.mixmatch_slider_bottom>button.slick-prev");
	public static By BOTTOM_SLIDE_SLICK_PREV = By.cssSelector(".mixmatch_slidesection>.mixmatch_slider_bottom>button.slick-next");
	public static By BOTTOM_SLIDE_CENTER_PRODUCT_IMG = By.cssSelector("div.mixmatch_images.desktop-bottom > div.mixmatch_slidesection > div > div > div > div.slick-slide.slick-current.slick-active.slick-center > div > img");
	public static By BOTTOM_SLIDE_CENTER_PRODUCT_NAME = By.cssSelector("div.mixmatch_images.desktop-bottom > div.mixmatch_slidesection > div > div > div > div.slick-slide.slick-current.slick-active.slick-center > div > div.mm-mobile > a > h4");
   
    public static By RIGHT_RAIL_TOP_PRODUCT_TITLE = By.cssSelector("div.mixmatch_images.desktop-top > div.mixmatch_selection > div > div > div.multi-pdp-title-wrap > div.product-info__title-wrap > a > h3");
    public static By RIGHT_RAIL_TOP_PRODUCT_PRICE = By.cssSelector("div.mixmatch_images.desktop-top > div.mixmatch_selection > div > div > div.multi-pdp-title-wrap > div.product-info__price-wrap");
   
    public static By RIGHT_RAIL_BOTTOM_PRODUCT_TITLE = By.cssSelector("div.mixmatch_images.desktop-bottom > div.mixmatch_selection > div > div > div.multi-pdp-title-wrap > div.product-info__title-wrap > a > h3");
    public static By RIGHT_RAIL_BOTTOM_PRODUCT_PRICE = By.cssSelector("div.mixmatch_images.desktop-bottom > div.mixmatch_selection > div > div > div.multi-pdp-title-wrap > div.product-info__price-wrap");
  
    
}
