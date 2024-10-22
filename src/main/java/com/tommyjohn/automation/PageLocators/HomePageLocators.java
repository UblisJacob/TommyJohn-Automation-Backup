package com.tommyjohn.automation.PageLocators;

import org.openqa.selenium.By;


public class HomePageLocators{

	//HomePageSections
	public static By HERO_BANNER_IMAGE = By.xpath("//div[@class='relative size-full']");
	public static By HERO_BANNER_CTA = By.xpath("(//a[@class='button-inverse'])[3]");
	public static By CATEGORY_SELECTOR_SECTION = By.xpath("(//div[@class='grid grid-cols-1 md:grid-cols-2'])[1]");
	public static By CATEGORY_SELECTOR_MEN = By.xpath("(//div[@class='space-x-2 md:space-x-4']//button)[1]");
	public static By CATEGORY_SELECTOR_WOMEN = By.xpath("(//div[@class='space-x-2 md:space-x-4']//button)[2]");
	public static By MEN_CATEGORIES = By.xpath("(//ul[@class='mt-6 md:mt-8'])[1]//li");
	public static By WOMEN_CATEGORIES = By.xpath("(//ul[@class='mt-6 md:mt-8'])[2]//li");
	public static By IMAGE_BANNER_MENS_CTA = By.xpath("(//a[@class='button-inverse'])[1]");
	public static By IMAGE_BANNER_WOMENS_CTA = By.xpath("(//a[@class='button-inverse'])[2]");
	public static By COLLECTION_SELECTOR = By.xpath("//div[@class='size-full']");
	public static By COLLECTION_SELECTOR_SECOND_SKIN = By.xpath("(//div[@class='mt-3 md:mt-8 space-y-4']//button)[1]");
	public static By COLLECTION_SELECTOR_COOL_COTTON = By.xpath("(//div[@class='mt-3 md:mt-8 space-y-4']//button)[2]");
	public static By COLLECTION_SELECTOR_360_SPORT = By.xpath("(//div[@class='mt-3 md:mt-8 space-y-4']//button)[3]");
	public static By COLLECTION_SELECTOR_APOLLO = By.xpath("(//div[@class='mt-3 md:mt-8 space-y-4']//button)[4]");
	public static By COLLECTION_SELECTOR_AIR = By.xpath("(//div[@class='mt-3 md:mt-8 space-y-4']//button)[5]");
	public static By OUR_STORY_CTA = By.xpath("//a[@class='button-inverse' and contains(text(),'Our Story')]");
	public static By LEARNMORE_CTA = By.xpath("(//a[@class='button-secondary mt-lg'])[1]");
	public static By OURSHOPPINGPOLICIES_CTA = By.xpath("(//a[@class='button-secondary mt-lg'])[2]");
	public static By OURSTORY_CTA = By.xpath("(//a[@class='button-secondary mt-lg'])[3]");

	//InstagramWidget
	public static By INSTA_SHOP_ICON = By.xpath("(//div[@class='dyUgc-firstlevel__slidetile-shopIcon'])[2]");
	public static By INSTA_ATC = By.xpath("(//div[@data-tj-qa='product-image']//button)[1]");
	public static By INSTA_FIRST_SIZE = By.xpath("//li[@data-tj-qa='plp-quickshop-instock']//label[contains(text(),'S')]");
	public static By INSTA_FIRST_PRODUCT_NAME = By.xpath("(//span[@class='product-meta__title'])[1]");
	public static By INSTA_COLOR_SWATCH = By.xpath("(//div[@class='product-item__swatch product-item__swatch--circle js-inline-quickshop-color active'])[2]");

//-----------------------------------------------------------------------------------------------
	public static By HOMEPAGE_LOGO = By.xpath("//span[contains(@class,'icon-primary-wordmark-logo')]");
	public static By MAIN_HEADER = By.xpath(".header-main");
	public static By SEARCH_ICON_IMAGE= By.xpath("//span[@class='icon block icon-search']");
	//public static By NEWARRIVALS_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(1) > a");
	//public static By MEN_IN_NEWARRIVALS_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(1) > div > ul > li:nth-child(1) > div");
	public static By LEVEL_TWO_ALL_OPTIONS = By.cssSelector(".nav-item__list.level-zero-list>li:nth-child(1) ul[class^='nav-item__list level-one-list']>li[class^='level-one-container']:nth-child(2) li.level-two-container");
	public static By LEVEL_TWO_FIRST_OPTION = By.cssSelector(".nav-item__list.level-zero-list>li:nth-child(1) ul[class^='nav-item__list level-one-list']>li[class^='level-one-container']:nth-child(2) li.level-two-container:nth-child(3)");
	public static By MENS_UNDERSHIRT_CREW_NECK = By.cssSelector(".nav-item__list.level-zero-list>li:nth-child(1) ul[class^='nav-item__list level-one-list']>li[class^='level-one-container']:nth-child(4) li[class='level-two-container']:nth-child(4)");
	public static By LEVEL_ZERO_LINKS_LIST = By.xpath("//a[contains(@class,'relative text-body')]");
	public static By LEVEL_ZERO_SPECIFIC_OPTION = By.xpath("(//a[contains(@class,'relative text-body')]//following::li[contains(@class,'group/level2 group')])[1]");
	public static By LEVEL_ZERO_LINKS_TEXT = By.xpath("//a[contains(@class,'relative text-body')]//following::li[contains(@class,'group/level2 group')]");

	//public static By MEN_UNDERWEAR_LINK = By.cssSelector("body > header:nth-child(22) > div:nth-child(1) > nav:nth-child(1) > div:nth-child(1) > div:nth-child(3) > ul:nth-child(1) > li:nth-child(1) > div:nth-child(2) > ul:nth-child(1) > span:nth-child(1) > li:nth-child(1) > ul:nth-child(2) > li:nth-child(1) > a:nth-child(1)");
	
	public static By MEN_UNDERWEAR_QUIZ = By.cssSelector("a[href='/pages/quiz?quizID=MN-UW-QZ']");


	public static By GENERIC_SHOP_ALL_LINK = By.cssSelector(".nav-item_'_list.level-zero-list>li:nth-child(1) ul[class^='nav-item__list level-one-list']>li[class^='level-one-container']:nth-child(2) li[data-tj-qa^='nav-all']");

	public static By MENS_LEVEL_1_LIST = By.cssSelector(".nav-item__list.level-zero-list>li:nth-child(1) ul[class^='nav-item__list level-one-list']>li[class^='level-one-container']>div a");

	public static By MENS_UW_NAV_LINK_RELAXED_FIT = By.cssSelector("a[data-tj-qa^='nav-link-Relaxed Fit Boxer 6']");
	public static By MENS_UW_NAV_LINK_SQUARE_CUT = By.cssSelector("a[data-tj-qa^='nav-link-Square Cut 2']");
	public static By MENS_UW_NAV_LINK_BRIEF = By.cssSelector("a[data-tj-qa^='nav-link-Brief']");
	public static By MENS_UW_NAV_LINK_JOHNS = By.cssSelector("a[data-tj-qa^='nav-link-Long Johns']");
	public static By MENS_UW_NAV_LINK_360SPORT = By.cssSelector("a[data-tj-qa^='nav-link-360 Sport']");
	public static By MENS_UW_NAV_LINK_UWQUIZ = By.cssSelector("a[data-tj-qa^='nav-link-Underwear Quiz']");	
	public static By WOMENS_LEVEL_1_LIST = By.cssSelector(".nav-item__list.level-zero-list>li:nth-child(2) ul[class^='nav-item__list level-one-list']>li[class^='level-one-container']>div a");
	public static By WOMEN_UNDERWEAR_LINK = By.cssSelector(".nav-item__list.level-zero-list>li:nth-child(2) ul[class^='nav-item__list level-one-list']>li[class^='level-one-container']:nth-child(2)>div a");
	public static By WOMEN_UNDERWEAR_QUIZ = By.cssSelector("a[href='/pages/quiz?quizID=WN-UW-QZ']");

	public static By WOMEN_BRA_LINK = By.cssSelector(".nav-item__list.level-zero-list>li:nth-child(2) ul[class^='nav-item__list level-one-list']>li[class^='level-one-container']:nth-child(3)>div a");
	public static By WOMEN_BRA_QUIZ = By.cssSelector("a[href='/pages/quiz?quizID=BR-10005-QZ'] ");

	public static By ALL_LEVEL_1_LINKS_LIST = By.cssSelector(".nav-item__list.level-zero-list>li ul[class^='nav-item__list level-one-list']>li[class^='level-one-container']");
//	public static By LEVEL_0_SPECIFIC_LINKS_LIST = By.cssSelector(".nav-item__list.level-zero-list>li:nth-child(1) ul[class^='nav-item__list level-one-list']>li[class^='level-one-container']>div a");
	public static By SPECIFIC_LEVEL_0_LEVEL_1_LINKS = By.xpath("//div[contains(@class,'absolute left')]//a");
	public static By LEVEL_1_OPTIONS_LIST_FROM_LEVEL_0 = By.cssSelector(".nav-item__list.level-zero-list>li:nth-child(1) ul[class^='nav-item__list level-one-list']>li[class^='level-one-container']>div a");
	public static By MENS_UW = By.cssSelector(".nav-item__list.level-zero-list>li:nth-child(1) ul[class^='nav-item__list level-one-list']>li[class^='level-one-container']:nth-child(2)>div a");

	public static By ALL_MENS_LINKS_LIST = By.cssSelector(".nav-item__list.level-zero-list>li:nth-child(1) ul[class^='nav-item__list level-one-list']>li[class^='level-one-container']>div a");
	public static By ALL_MENS_LINKS_LIST_TEXT = By.cssSelector(".nav-item__list.level-zero-list>li:nth-child(1) ul[class^='nav-item__list level-one-list']>li[class^='level-one-container']>div a>span");


	public static By EXPLORE_LEVEL_ONE_LINKS = By.cssSelector("li[arial-label='open Explore menu'] ul[class='nav-item__list level-one-list alternate']>li[class='level-one-container alternate image-column']");

	public static By MEN_CATEGORY = By.xpath("(//a[contains(text(),'Men')])[2]");
	public static By MEN_CATEGORY_COPY = By.xpath("(//a[contains(text(),'Men')])[1]");
	public static By WOMEN_CATEGORY = By.xpath("(//a[contains(text(),'Women')])[2]");
	public static By WOMEN_CATEGORY_COPY = By.xpath("(//a[contains(text(),'Women')])[1]");
	public static By FINAL_SALE = By.cssSelector(".level-zero-list>.level-zero-container:nth-child(3)> .level-zero-item >a");

	public static By IM_NEW_HERE = By.cssSelector(".level-zero-list>.level-zero-container:nth-child(3)> .level-zero-item >a");

	public static By NEW_FEATURED = By.xpath("(//a[contains(text(),'New + Featured')])[1]");
	
	public static By HEADER_RIGHT_SIDE_ELEMENTS_LIST = By.xpath("//div[@class='flex gap-x-8']//span[@aria-hidden]");
	public static By HEADER_RIGHT_SIDE_SPECIFIC_ELEMENT = By.xpath("(//div[@class='flex gap-x-8']//span[@aria-hidden='true'])[1]");


	public static By FIRST_lEVEL1_CATEGORY = By.xpath("(//a[contains(text(),'Undershirts')])[1]");
	public static By FIRST_lEVEL1_CATEGORY_COPY = By.xpath("//a[contains(text(),'Base Layers')]");
	public static By WOMENS_FIRST_lEVEL1_CATEGORY = By.xpath("(//a[contains(text(),'Bras & Bralettes')])[1]");
	public static By WOMENS_FIRST_lEVEL1_CATEGORY_COPY = By.xpath("(//a[contains(text(),'Intimates')])[2]");
	public static By SECOND_lEVEL1_CATEGORY = By.xpath("(//a[contains(text(),'New Arrivals')])[1]");
	public static By THIRD_lEVEL1_CATEGORY = By.cssSelector("li.active> div:nth-child(2)> ul.level-one-list >li.level-one-container:nth-child(4) > div > .level-one-item >a");
	public static By FOURTH_lEVEL1_CATEGORY = By.cssSelector("li.active> div:nth-child(2)> ul.level-one-list >li.level-one-container:nth-child(5) > div > .level-one-item >a");
	public static By FIFTH_lEVEL1_CATEGORY = By.cssSelector("li.active> div:nth-child(2)> ul.level-one-list >li.level-one-container:nth-child(6) > div > .level-one-item >a");
	public static By SIXTH_lEVEL1_CATEGORY = By.cssSelector("li.active> div:nth-child(2)> ul.level-one-list >li.level-one-container:nth-child(7) > div > .level-one-item >a");
	public static By SEVENTH_lEVEL1_CATEGORY = By.cssSelector("li.active> div:nth-child(2)> ul.level-one-list >li.level-one-container:nth-child(8) > div > .level-one-item >a");

	public static By SHOPALL_LINK = By.cssSelector("li[data-tj-qa='nav-all-Underwear'],li.active> div:nth-child(2)> ul.level-one-list> li.active > ul.level-two-list >li.shop-all-item > a");
	
	public static By FIRST_lEVEL2_CATEGORY = By.cssSelector("div[data-tj-qa='nav-Underwear Packs']>a,li.active> div:nth-child(2)> ul.level-one-list> li.active > ul.level-two-list >li:nth-child(3) > div >a");
	public static By SECOND_lEVEL2_CATEGORY = By.cssSelector("div[data-tj-qa='nav-Trunk 4']>a,li.active> div:nth-child(2)> ul.level-one-list> li.active > ul.level-two-list >li:nth-child(4) > div >a");
	public static By THIRD_lEVEL2_CATEGORY = By.cssSelector("div[data-tj-qa='nav-Mid-Length Boxer Brief 6']>a,li.active> div:nth-child(2)> ul.level-one-list> li.active > ul.level-two-list >li:nth-child(5) > div >a");
	public static By FOURTH_lEVEL2_CATEGORY = By.cssSelector("li.active> div:nth-child(2)> ul.level-one-list> li.active > ul.level-two-list >li:nth-child(6) > div >a");
	public static By FIFTH_lEVEL2_CATEGORY = By.cssSelector("li.active> div:nth-child(2)> ul.level-one-list> li.active > ul.level-two-list >li:nth-child(7) > div >a");
	public static By SIXTH_lEVEL2_CATEGORY = By.cssSelector("li.active> div:nth-child(2)> ul.level-one-list> li.active > ul.level-two-list >li:nth-child(8) > div >a");
	public static By SEVENTH_lEVEL2_CATEGORY = By.cssSelector("li.active> div:nth-child(2)> ul.level-one-list> li.active > ul.level-two-list >li:nth-child(9) > div >a");
	
	public static By FIRST_LEVEL2_LINK = By.cssSelector("li.active> div:nth-child(2)> ul.level-one-list> li.active > ul.level-two-list > li.links-only > div:nth-child(2) >a");
	public static By SECOND_LEVEL2_LINK = By.cssSelector("li.active> div:nth-child(2)> ul.level-one-list> li.active > ul.level-two-list > li.links-only > div:nth-child(3) >a");
	public static By THIRD_LEVEL2_LINK = By.cssSelector("li.active> div:nth-child(2)> ul.level-one-list> li.active > ul.level-two-list > li.links-only > div:nth-child(4) >a");
	public static By FOURTH_LEVEL2_LINK = By.cssSelector("li.active> div:nth-child(2)> ul.level-one-list> li.active > ul.level-two-list > li.links-only > div:nth-child(5) >a");
	public static By FIFTH_LEVEL2_LINK = By.cssSelector("li.active> div:nth-child(2)> ul.level-one-list> li.active > ul.level-two-list > li.links-only > div:nth-child(6) >a");
	public static By SIXTH_LEVEL2_LINK = By.cssSelector("li.active> div:nth-child(2)> ul.level-one-list> li.active > ul.level-two-list > li.links-only > div:nth-child(7) >a");

	public static By SEARCH_ICON = By.xpath("//button[@aria-label='Search']");
	public static By SEARCH_ICON_CLOSE = By.xpath("//span[@class='icon block size-5 icon-arrow-left']");
	public static By SEARCH_TEXTBOX = By.xpath("//input[@id='PredictiveSearch-Query']");
	public static By SEARCH_SUGGESTION_CONTAINER = By.xpath("//div[@class='grow overflow-y-auto']");
	
//	public static By ALL_NEW_ARRIVALS_LINK_IN_MEN_CATEGORY = By.cssSelector(".level-one-container:nth-child(2) > .level-two-list > li.shop-all-item >a");
//	public static By ALL_UNDERWEAR_LINK_IN_MEN_CATEGORY = By.cssSelector(".level-one-container:nth-child(3) > .level-two-list > li.shop-all-item >a");
//	public static By ALL_UNDERSHIRTS_LINK_IN_MEN_CATEGORY = By.cssSelector(".level-one-container:nth-child(4) > .level-two-list > li.shop-all-item >a");
//	public static By ALL_LOUNGENSLEEP_LINK_IN_MEN_CATEGORY = By.cssSelector(".level-one-container:nth-child(5) > .level-two-list > li.shop-all-item >a");
//	public static By ALL_CLOTHING_LINK_IN_MEN_CATEGORY = By.cssSelector(".level-one-container:nth-child(6) > .level-two-list > li.shop-all-item >a");
//	public static By ALL_SOCKS_LINK_IN_MEN_CATEGORY = By.cssSelector(".level-one-container:nth-child(7) > .level-two-list > li.shop-all-item >a");

//	public static By MENS_NEWARRIVALS_COLLECTION_PAGELINK = By.cssSelector(".level-one-container:nth-child(2) > .level-two-list > li.level-two-container:nth-child(3)> div> a");
//	public static By MENS_TJxPRIDE_COLLECTION_PAGELINK = By.cssSelector(".level-one-container:nth-child(2) > .level-two-list > li.level-two-container:nth-child(4)> div> a");
//	public static By MENS_UNDERWEAR_COLLECTION_PAGELINK = By.cssSelector(".level-one-container:nth-child(2) > .level-two-list > li.level-two-container:nth-child(5)> div> a");
//	public static By MENS_LOUNGENSLEEP_COLLECTION_PAGELINK = By.cssSelector(".level-one-container:nth-child(2) > .level-two-list > li.level-two-container:nth-child(6)> div> a");
//	public static By MENS_CLOTHING_COLLECTION_PAGELINK = By.cssSelector(".level-one-container:nth-child(2) > .level-two-list > li.links-only > div.level-two-item:nth-child(2) > a");
//	public static By MENS_NA_PACKS_COLLECTION_PAGELINK = By.cssSelector(".level-one-container:nth-child(2) > .level-two-list > li.links-only > div.level-two-item:nth-child(3) > a");
//	public static By MENS_BESTSELLERS_COLLECTION_PAGELINK = By.cssSelector(".level-one-container:nth-child(2) > .level-two-list > li.links-only > div.level-two-item:nth-child(4) > a");
//	public static By MENS_GIFTCARD_COLLECTION_PAGELINK = By.cssSelector(".level-one-container:nth-child(2) > .level-two-list > li.links-only > div.level-two-item:nth-child(5) > a");
//
//	
//	public static By MENS_BOXERBRIEF_COLLECTION_PAGELINK = By.cssSelector(".level-one-container:nth-child(3) > .level-two-list > li.level-two-container:nth-child(3)> div> a");
//	public static By MENS_TRUNK_COLLECTION_PAGELINK = By.cssSelector(".level-one-container:nth-child(3) > .level-two-list > li.level-two-container:nth-child(4)> div> a");
//	public static By MENS_RELAXEDFITBOXER_COLLECTION_PAGELINK = By.cssSelector(".level-one-container:nth-child(3) > .level-two-list > li.level-two-container:nth-child(5)> div> a");
//	public static By MENS_SQUARECUT_COLLECTION_PAGELINK = By.cssSelector(".level-one-container:nth-child(3) > .level-two-list > li.level-two-container:nth-child(6)> div> a");
//	public static By MENS_BRIEF_COLLECTION_PAGELINK = By.cssSelector(".level-one-container:nth-child(3) > .level-two-list > li.level-two-container:nth-child(7)> div> a");
//	public static By MENS_LEGGING_COLLECTION_PAGELINK = By.cssSelector(".level-one-container:nth-child(3) > .level-two-list > li.level-two-container:nth-child(8)> div> a");
//	public static By MENS_UW_PACKS_COLLECTION_PAGELINK = By.cssSelector(".level-one-container:nth-child(3) > .level-two-list > li.level-two-container:nth-child(9)> div> a");
//
//	public static By MENS_CREWNECK_COLLECTION_PAGELINK = By.cssSelector(".level-one-container:nth-child(4) > .level-two-list > li.level-two-container:nth-child(3)> div> a");
//	public static By MENS_VNECK_COLLECTION_PAGELINK = By.cssSelector(".level-one-container:nth-child(4) > .level-two-list > li.level-two-container:nth-child(4)> div> a");
//	public static By MENS_DEEPVNECK_COLLECTION_PAGELINK = By.cssSelector(".level-one-container:nth-child(4) > .level-two-list > li.level-two-container:nth-child(5)> div> a");
//	public static By MENS_TANKTOP_COLLECTION_PAGELINK = By.cssSelector(".level-one-container:nth-child(4) > .level-two-list > li.level-two-container:nth-child(6)> div> a");
//	public static By MENS_US_PACKS_COLLECTION_PAGELINK = By.cssSelector(".level-one-container:nth-child(4) > .level-two-list > li.level-two-container:nth-child(7)> div> a");
//		
//	
//	public static By MENS_TOPS_COLLECTION_PAGELINK = By.cssSelector(".level-one-container:nth-child(5) > .level-two-list > li.level-two-container:nth-child(3)> div> a");
//	public static By MENS_BOTTOMS_COLLECTION_PAGELINK = By.cssSelector(".level-one-container:nth-child(5) > .level-two-list > li.level-two-container:nth-child(4)> div> a");
//	
//	public static By MENS_TEESPOLOS_COLLECTION_PAGELINK = By.cssSelector(".level-one-container:nth-child(6) > .level-two-list > li.level-two-container:nth-child(3)> div> a");
//	public static By MENS_PANTSNSHORTS_COLLECTION_PAGELINK = By.cssSelector(".level-one-container:nth-child(6) > .level-two-list > li.level-two-container:nth-child(4)> div> a");
//	public static By MENS_SWEATERSWEATSHIRT_COLLECTION_PAGELINK = By.cssSelector(".level-one-container:nth-child(6) > .level-two-list > li.level-two-container:nth-child(5)> div> a");
//	public static By MENS_BUTTONDOWNS_COLLECTION_PAGELINK = By.cssSelector(".level-one-container:nth-child(6) > .level-two-list > li.level-two-container:nth-child(6)> div> a");
//	public static By MENS_JACKETOUTWEAR_COLLECTION_PAGELINK = By.cssSelector(".level-one-container:nth-child(6) > .level-two-list > li.level-two-container:nth-child(7)> div> a");
//	public static By MENS_SOCKS_COLLECTION_PAGELINK = By.cssSelector(".level-one-container:nth-child(6) > .level-two-list > li.level-two-container:nth-child(8)> div> a");
//	public static By MENS_CLOTHINGPACKS_COLLECTION_PAGELINK = By.cssSelector(".level-one-container:nth-child(6) > .level-two-list > li.level-two-container:nth-child(9)> div> a");
//
//	public static By MENS_OVERTHECALF_COLLECTION_PAGELINK = By.cssSelector(".level-one-container:nth-child(7) > .level-two-list > li.level-two-container:nth-child(3)> div> a");
//	public static By MENS_CALF_COLLECTION_PAGELINK = By.cssSelector(".level-one-container:nth-child(7) > .level-two-list > li.level-two-container:nth-child(4)> div> a");
//	public static By MENS_NOSHOW_COLLECTION_PAGELINK = By.cssSelector(".level-one-container:nth-child(7) > .level-two-list > li.level-two-container:nth-child(5)> div> a");
//	public static By MENS_YOUTH_COLLECTION_PAGELINK = By.cssSelector(".level-one-container:nth-child(7) > .level-two-list > li.level-two-container:nth-child(6)> div> a");
//
//	public static By MENS_SECONDSKIN_COLLECTION_PAGELINK = By.cssSelector(".level-one-container:nth-child(8) > .level-two-list > li.level-two-container:nth-child(3)> div> a");
//	public static By MENS_COOLCOTTON_COLLECTION_PAGELINK = By.cssSelector(".level-one-container:nth-child(8) > .level-two-list > li.level-two-container:nth-child(4)> div> a");
//	public static By MENS_360SPORT_COLLECTION_PAGELINK = By.cssSelector(".level-one-container:nth-child(8) > .level-two-list > li.level-two-container:nth-child(5)> div> a");
//	public static By MENS_AIR_COLLECTION_PAGELINK = By.cssSelector(".level-one-container:nth-child(8) > .level-two-list > li.level-two-container:nth-child(6)> div> a");
//	public static By MENS_GOANYWHERE_COLLECTION_PAGELINK = By.cssSelector(".level-one-container:nth-child(8) > .level-two-list > li.level-two-container:nth-child(7)> div> a");
//	public static By MENS_COTTONBASIC_COLLECTION_PAGELINK = By.cssSelector(".level-one-container:nth-child(8) > .level-two-list > li.links-only > div.level-two-item:nth-child(2) > a");
//	public static By MENS_SLEAKHEAT_COLLECTION_PAGELINK = By.cssSelector(".level-one-container:nth-child(8) > .level-two-list > li.links-only > div.level-two-item:nth-child(2) > a");
//	public static By MENS_KEVINHARTFAVS_COLLECTION_PAGELINK = By.cssSelector(".level-one-container:nth-child(8) > .level-two-list > li.links-only > div.level-two-item:nth-child(2) > a");
//	
//	
//	public static By NEW_ARRIVALS_IN_WOMEN_CATEGORY = By.cssSelector(".level-one-list >li.level-one-container:nth-child(2) > div > .level-one-item >a");
//	public static By BRAS_IN_WOMEN_CATEGORY = By.cssSelector(".level-one-list >li.level-one-container:nth-child(3) > div > .level-one-item >a");
//	public static By UNDERWEAR_IN_WOMEN_CATEGORY = By.cssSelector(".level-one-list >li.level-one-container:nth-child(4) > div > .level-one-item >a");
//	public static By LOUNGENSLEEP_IN_WOMEN_CATEGORY = By.cssSelector(".level-one-list >li.level-one-container:nth-child(5) > div > .level-one-item >a");
//	public static By CLOTHING_IN_WOMEN_CATEGORY = By.cssSelector(".level-one-list >li.level-one-container:nth-child(6) > div > .level-one-item >a");
//	public static By COLLECTIONS_IN_WOMEN_CATEGORY = By.cssSelector(".level-one-list >li.level-one-container:nth-child(7) > div > .level-one-item >a");
//	
//	
//
//	
//	
//	
//	public static By MENS_SALE = By.cssSelector("li.active> div:nth-child(2)> ul.level-one-list >li.level-one-container:nth-child(2) > div > .level-one-item >a");
//	
//	
//	
//	
//	
//	
//	
//	
//	public static By TANK_IN_MEN_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(2) > div > ul > li:nth-child(3) > ul > li:nth-child(5) > a");
//	public static By DRESS_SHIRTS_IN_MEN_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(2) > div > ul > li:nth-child(6) > ul > li:nth-child(5) > a");
//	public static By ALL_CLOTHING_IN_MEN_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(2) > div > ul > li:nth-child(6) > ul > li:nth-child(1) > a");	
//	public static By ALLSOCKS_IN_MEN_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(2) > div > ul > li:nth-child(5) > ul > li:nth-child(1) > a");
//	
//	public static By BUTTON_DOWN_SHIRT_IN_MEN_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(2) > div > ul > li:nth-child(6) > ul > li:nth-child(5) > a");
//	public static By PANTS_AND_SHORTS_IN_MEN_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(2) > div > ul > li:nth-child(6) > ul > li:nth-child(6) > a");
//	public static By JACKETS_AND_OUTWEARS_IN_MEN_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(2) > div > ul > li:nth-child(6) > ul > li:nth-child(4) > a");
//	public static By CAMISOLE_IN_WOMEN_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(3) > div > ul > li:nth-child(2) > ul > li:nth-child(8) > a");
//	public static By TOPS_AND_TEES_IN_WOMEN_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(3) > div > ul > li:nth-child(5) > ul > li:nth-child(2) > a");
//	public static By HOODIES_AND_JACKETS_IN_WOMEN_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(3) > div > ul > li:nth-child(5) > ul > li:nth-child(4) > a");
//	public static By PANTS_AND_SHORTS_IN_WOMEN_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(3) > div > ul > li:nth-child(5) > ul > li:nth-child(5) > a");
//
//	
//	
//	
////	public static By BRAS_IN_WOMEN_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(3) > div > ul > li:nth-child(3) > ul > li:nth-child(1) > a");
////	public static By NEW_ARRIVALS_IN_WOMEN_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(3) > div > ul > li:nth-child(1) > ul > li:nth-child(1) > a");
//	public static By ALL_BRAS_IN_WOMEN_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(3) > div > ul > li:nth-child(3) > ul > li:nth-child(1) > a");
//	public static By BOYSHORT_IN_WOMEN_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(3) > div > ul > li:nth-child(2) > ul > li:nth-child(2) > a");
////	public static By ALL_WOMENS_COLLECTIONS_IN_WOMEN_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(3) > div > ul > li:nth-child(4) > ul > li:nth-child(1) > a");
//	public static By ALL_CLOTHING_IN_WOMEN_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(3) > div > ul > li:nth-child(5) > ul > li:nth-child(1) > a");
//
//	public static By UNDERWEAR_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(4) > a");
//	public static By MEN_IN_UNDERWEAR_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(4) > div > ul > li:nth-child(1) > div");
//
//	//	public static By SHOPBYCOLLECTION = By.cssSelector(".header-desktop-left > ul > li:nth-child(5) > a");
//	//public static By MEN_IN_SHOPBYCOLLECTION = By.cssSelector(".header-desktop-left > ul > li:nth-child(5) > div > ul > li:nth-child(1) > div");
//	//	public static By COTTON_BASICS_SHOPBYCOLLECTION = By.cssSelector(".header-desktop-left > ul > li:nth-child(5) > div > ul > li:nth-child(1) > ul > li:nth-child(9) > a");
//	public static By BRAS = By.cssSelector(".header-desktop-left > ul > li:nth-child(5) > a");
//	public static By PACKS = By.cssSelector(".header-desktop-left > ul > li:nth-child(6) > a");
//	public static By MEN_IN_PACKS = By.cssSelector(".header-desktop-left > ul > li:nth-child(6) > div > ul > li:nth-child(1) > div");
//	public static By ALL_MENS_PACKS_IN_PACKS_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(6) > div > ul > li:nth-child(1) > ul > li:nth-child(1) > a");
//	public static By ALL_WOMENS_PACKS_IN_PACKS_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(6) > div > ul > li:nth-child(2) > ul > li:nth-child(1) > a");
//	//	public static By COOL_COTTON_IN_MENS_COLLECTIONS = By.cssSelector(".header-desktop-left > ul > li:nth-child(2) > div > ul > li:nth-child(5) > ul > li:nth-child(4) > a");
//	public static By MYSTERYPACK_IN_PACKS = By.cssSelector(".header-desktop-left > ul > li:nth-child(6) > div > ul > li:nth-child(2) > ul > li:nth-child(3) > a");
//	public static By TEMP_TAB = By.cssSelector(".header-desktop-left > ul > li:nth-child(7) > a");
//
	public static By SEARCH_ICON_INPUT = By.xpath("//input[@id='PredictiveSearch-Query']");
	public static By SEARCH_ICON_FORM_TAG = By.cssSelector(".header-search-form");
	public static By POPULARCATEGORIES = By.xpath("//div[@class='desktop-search-product-tile-title']");
	public static By CATEGORIESINSEARCHDROPDOWN = By.xpath("//div[@id='PredictiveSearch-Results']");
	public static By POPULARTERMS = By.xpath("//div[@id='PredictiveSearch-Results']");
	public static By CATEGORIESINPOPULARTERMS = By.xpath("//ul[@class='space-y-1']/li");

	public static By POPULAR_SEARCHES = By.xpath("//h2[@id='PredictiveSearch-QUERIES']");
	public static By PRODUCT_MATCHES_TITLE = By.xpath("//h2[@id='PredictiveSearch-PRODUCTS']");
	public static By PRODUCT_SUGGESTION = By.xpath("(//a[@class='text-sm hover:underline [&_mark]:bg-transparent [&_mark]:font-bold'])[1]");
//	public static By SEARCHRESULT_KEYWORD = By.cssSelector(".nxt-ac-section-container > .nxt-ac-item");
//	public static By SEARCHRESULT_PRODUCT = By.cssSelector(".nxt-ac-products-container > .nxt-ac-item");
	
	public static By SEARCHPAGE_TITLE = By.xpath("(//h1)[1]");
	public static By NORESULTPAGETITLE = By.xpath("(//h1)[1]");
	public static By SEARCHPAGE_DESC= By.cssSelector("[data-tj-qa='result-tip'],.collection-search-title-bar__description");
	public static By SEARCHPAGE_ITEMCOUNT= By.xpath(".mega-collection-filters--controls__count");
	public static By SEARCHPAGE_FILTER= By.cssSelector(".collection-filter");
	
	public static By SEARCHPAGE_PRODUCTS = By.cssSelector(".product-item");
		public static By SEARCHPAGE_PRODUCT_IMAGE = By.xpath("(//div[@class='relative aspect-tile'])[1]");
	public static By SEARCHPAGE_PRODUCT_SWATCHES = By.xpath("(//div[@class='flex items-center'])[1]");
	public static By SEARCHPAGE_PRODUCT_NAME = By.xpath("(//h2[@class='my-2xs text-body-md']//a)[1]");
	public static By SEARCHPAGE_PRODUCT_REVIEWS = By.xpath("(//a[@aria-label='Read reviews'])[1]");
	public static By SEARCHPAGE_PRODUCT_PRICE = By.xpath("(//span[@data-cy='priceFinal'])[1]");
	public static By SEARCHPAGE_PRODUCT_BADGE = By.cssSelector(".product-item__badge");	
	public static By RECENT_SEARCHES_TITLE = By.xpath("//h2[@id='PredictiveSearch-QUERIES']");
	public static By CLEAR_RECENT_SEARCHES = By.xpath("//button[@id='PredictiveSearch-ClearRecent']");
	public static By RECENT_SEARCH_CONTAINER = By.cssSelector(".desktop-recent-search-container");
	public static By SEARCHPAGE_ALL_FILTERS = By.cssSelector("div.mega-collection-filters > div > a");
	public static By SEARCHPAGE_ALL_SELECTED_FILTER_OPTIONS = By.cssSelector("div.js-mega-collection-desktop-filter-tags >div> button");
	public static By SEARCHPAGE_CLEAR_FILTER = By.cssSelector("div.mega-collection-filters--controls > a");
	public static By SEARCHPAGE_LAST_FILTER = By.cssSelector(".mega-collection-filters > select");
	public static By SUGGESTED_LINKS = By.xpath("//ul[@class='space-y-1']");
	public static By SUGGESTED_PRODUCT_IMG = By.xpath("//a[@class='flex flex-col gap-sm text-sm hover:underline']");
	public static By AUTO_SUGGESTED_PRODUCT_TILE_TITLE = By.xpath("(//li[@id='PredictiveSearch-Descendant-0']//a)[1]");
	public static By AUTO_SUGGESTED_PRODUCT_IMG_TITLE = By.xpath("(//a[@class='flex flex-col gap-sm text-sm hover:underline']//span)[1]");
	
	public static By HELP_CENTER = By.cssSelector(".header-nav-dropdown > ul >li:nth-child(3) > a");
	public static By CUSTOMER_SERVICE = By.cssSelector(".header-nav-dropdown > ul >li:nth-child(3) > a");
	public static By ACCOUNT_NAV_DROPDOWN_OPTIONS = By.cssSelector(".header-nav-dropdown li:nth-child(1)>a");
	public static By REORDER = By.cssSelector(".header-nav-dropdown > ul >li:nth-child(2) > a");
	public static By HELP_ICON = By.cssSelector("i.icon-help");
	public static By HELP_ICON_HELP_OPTION = By.cssSelector(".header-nav-dropdown > ul >li:nth-child(2) > a");
	public static By MYACCOUNT_OPTION = By.cssSelector(".header-nav-dropdown > ul >li:nth-child(1) > a");
	public static By SIGNIN_OPTION = By.cssSelector(".header-nav-dropdown > ul >li:nth-child(1) > a");
	//public static By CHAT_WITH_US_POPUP = By.cssSelector(".desktop.sf_large.scroll_top.scroll_bottom > div.scrollable_inner.jx_ui_Widget > div:nth-child(2) > a");
	public static By CHAT_WITH_US_POPUP = By.cssSelector(".jx_ui_Widget");
	public static By CHAT_WITH_US_POPUP_MINIMIZE = By.cssSelector(".meshim_widget_widgets_titleBar_MinimizeButton > div.overlay.jx_ui_Widget");

	//public static By CHAT_WITH_US_POPUP_MINIMIZE = By.cssSelector(".button_container.last_child.jx_ui_Widget > div > div");
	public static By CALL_US_OPTION = By.cssSelector(".header-nav-dropdown > ul > li:nth-child(3) > a");
	public static By TEXT_US_OPTION = By.cssSelector(".header-nav-dropdown > ul > li:nth-child(4) > a");
	public static By EMAIL_US = By.cssSelector(".header-nav-dropdown > ul > li:nth-child(5) > a");
	
	public static By ACCOUNT_NAV_DROPDOWN = By.xpath("//span[@class='icon block icon-account']");
	public static By HEADER_LOGIN_TITLE = By.cssSelector("span[data-tj-qa='header-login']");
	//public static By REORDER = By.cssSelector("header-desktop-right > li:nth-child(3) >div > ul > li:nth-child(2) > a ");
	//	public static By CART_ICON = By.cssSelector("i.icon-shopping-cart");

	public static By CART_ICON = By.xpath("//span[@class='icon block icon-cart']");
	public static By REMOVE_ICON = By.xpath("//button[@class='button-link mt-4 text-body-xs text-text-tertiary' and contains(text(),'Remove')]");
	public static By OVERLAY = By.xpath("//div[@id='swell-overlay']");

	public static By CART_HEADING_TEXT = By.cssSelector(".inline-cart__col1 > h3");
	public static By MINICART_HEADING = By.xpath("//h2[contains(text(),'Your Cart')]");

	public static By ABOUT_LINK = By.cssSelector("a[data-tj-qa='footer-about']");
	public static By CAREERS_LINK = By.cssSelector("a[data-tj-qa='footer-careers']");
	public static By PRESS_LINK = By.cssSelector("a[data-tj-qa='footer-press']");
	public static By BLOG_LINK = By.cssSelector("a[data-tj-qa='footer-blog']");
	public static By REVIEWS_LINK = By.cssSelector("a[data-tj-qa='footer-reviews']");
	public static By SITEWIDE_PROMOTION_LINK = By.cssSelector("a[data-tj-qa='footer-sitewide-promotion']");
	public static By MILITARY_DISCOUNT_LINK = By.cssSelector("a[data-tj-qa='footer-military-discount']");
	public static By FIRST_RESPONDER = By.cssSelector("a[data-tj-qa='footer-first-responder']");
	public static By GOVERNMENT_EMPLOYEE_DISCOUNT = By.cssSelector("a[data-tj-qa='footer-government-employee-discount']");
	public static By TEACHER_DISCOUNT_LINK = By.cssSelector("a[data-tj-qa='footer-teacher-discount']");
	public static By REFERRAL_LINK = By.cssSelector("a[data-tj-qa='footer-give-$25,-get-$25']");
	public static By HEALTHCARE_DISC_LINK = By.linkText("Healthcare Hero Discount");
	public static By JOBS_LINK = By.linkText("Jobs");
//	public static By PRIVACY_POLICY_LINK = By.linkText("Privacy Policy");
	public static By PRIVACY_POLICY_LINK = By.cssSelector("a[data-tj-qa='footer-privacy-policy']");
//	public static By TERMS_AND_CONDITION_LINK = By.linkText("Terms & Conditions");
	public static By TERMS_AND_CONDITION_LINK = By.cssSelector("a[data-tj-qa='footer-terms-&-conditions']");
	public static By LEAVE_SITE_POPUP = By.cssSelector(".LeaveSiteModalContainer.active .LeaveSiteModalContent");
	public static By LEAVE_SITE_POPUP_OK_BTN = By.cssSelector(".LeaveSiteModalContainer.active button[data-leave-site='ok']");
	public static By LEAVE_SITE_POPUP_CANCEL_BTN = By.cssSelector(".LeaveSiteModalContainer.active button[data-leave-site='cancel']");

	public static By HELP_LINK = By.linkText("Help");
//	public static By CONTACT_US_LINK = By.linkText("Contact & FAQ");
	public static By CONTACT_US_LINK = By.xpath("//a[contains(text(),'Contact & FAQ')]");
//	public static By RETURNS_AND_EXCHANGES = By.linkText("Returns & Exchanges");
	public static By RETURNS_AND_EXCHANGES = By.cssSelector("a[data-tj-qa='footer-returns-&-exchanges']");
//	public static By RETAILERS_LINK = By.linkText("Retailers");
	public static By RETAILERS_LINK = By.cssSelector("a[data-tj-qa='footer-retailers']");
	public static By AFFILIATES_LINK = By.cssSelector("a[data-tj-qa='footer-affiliates']");
	public static By KING_OF_PURSSIA_STORE_LINK = By.linkText("King of Prussia Store");
	public static By CHARLOTTE_STORE_LINK = By.linkText("Charlotte Store");
//	public static By TOMMY_JOHN_STORES = By.linkText("Tommy John Stores");
	public static By TOMMY_JOHN_STORES = By.cssSelector("a[data-tj-qa='footer-tommy-john-stores']");
//	public static By STORE_LOCATOR_LINK = By.linkText("Store Locator");
	public static By STORE_LOCATOR_LINK = By.xpath("//a[contains(text(),'Store Locator')]");
//	public static By SHIPPING_AND_RETURNS_LINK = By.linkText("Shipping & Return Policy");
	public static By SHIPPING_AND_RETURNS_LINK = By.cssSelector("a[data-tj-qa='footer-shipping-&-return-policy']");
	public static By GIFT_CARD_BALANCE_CHECK = By.cssSelector("a[data-tj-qa='footer-check-gift-card-balance']");
	public static By NUMBER_LINK = By.linkText("1-800-708-3490");
	public static By LOYALTY_LINK = By.linkText("Loyalty");

	public static By TOMMYJOHN_REWARDS_LINK = By.linkText("Tommy John Rewards");
//	public static By SHOP_WOMEN = By.linkText("Shop Women");
	public static By SHOP_WOMEN = By.cssSelector("a[data-tj-qa='footer-shop-women']");
//	public static By SHOP_MEN = By.linkText("Shop Men");
	public static By SHOP_MEN = By.cssSelector("a[data-tj-qa='footer-shop-men']");
//	public static By GIFT_CARD_LINK = By.linkText("Gift Cards");
	public static By GIFT_CARD_LINK = By.cssSelector("a[data-tj-qa='footer-gift-cards']");
//	public static By FABRIC_GUIDE_LINK = By.linkText("Fabric Guide");
	public static By FABRIC_GUIDE_LINK = By.cssSelector("a[data-tj-qa='footer-fabric-guide']");
	
	public static By SIGN_IN_FOOTER_LINK= By.cssSelector("a[data-tj-qa='footer-sign-in']");
	public static By ORDER_HISTORY = By.cssSelector("a[data-tj-qa='footer-order-history']");

//	public static By FACEBOOK_LINK = By.linkText("Facebook");
	public static By FACEBOOK_LINK = By.cssSelector("a[data-tj-qa='footer-facebook']");
//	public static By TWITTER_LINK = By.linkText("Twitter");
	public static By TWITTER_LINK = By.cssSelector("a[data-tj-qa='footer-twitter']");
//	public static By PINTEREST_LINK = By.linkText("Pinterest");
	public static By PINTEREST_LINK = By.cssSelector("a[data-tj-qa='footer-pinterest']");
//	public static By INSTAGRAM_LINK = By.linkText("Instagram");
	public static By INSTAGRAM_LINK = By.cssSelector("a[data-tj-qa='footer-instagram']");
//	public static By YOUTUBE_LINK = By.linkText("YouTube");
	public static By YOUTUBE_LINK = By.cssSelector("a[data-tj-qa='footer-youtube']");
	public static By GOOGLE_PLUS_LINK = By.linkText("Google+");
	
//	public static By FACEBOOK_ICON = By.cssSelector(".footer-social_icons>div.footer-icons>a:nth-child(1)");
	public static By FACEBOOK_ICON = By.cssSelector("a[data-tj-qa='footer-facebook']>svg");
//	public static By TWITTER_ICON = By.cssSelector(".footer-social_icons>div.footer-icons>a:nth-child(2)");
	public static By TWITTER_ICON = By.cssSelector("a[data-tj-qa='footer-twitter']>svg");
//	public static By PINTEREST_ICON = By.cssSelector(".footer-social_icons>div.footer-icons>a:nth-child(3)");
	public static By PINTEREST_ICON = By.cssSelector("a[data-tj-qa='footer-pinterest']>svg");
//	public static By INSTAGRAM_ICON = By.cssSelector(".footer-social_icons>div.footer-icons>a:nth-child(4)");
	public static By INSTAGRAM_ICON = By.cssSelector("a[data-tj-qa='footer-instagram']>svg");
//	public static By YOUTUBE_ICON = By.cssSelector(".footer-social_icons>div.footer-icons>a:nth-child(5)");
	public static By YOUTUBE_ICON = By.cssSelector("a[data-tj-qa='footer-youtube']>svg");
	
//	public static By NEWSLETTER_TITLE= By.cssSelector(".footer-section_one>div>.shopify-section>.footer-newsletter>.newsletter_title");
	public static By NEWSLETTER_TITLE= By.cssSelector(".desktop-footer-newsletter div[data-tj-qa='footer-newsletter-title']");
//	public static By NEWSLETTER_RADIO_BUTTONS = By.cssSelector(".footer-section_one>div>.shopify-section>section>form>.gender-container>span");
	public static By NEWSLETTER_MEN_RADIO_BUTTON = By.cssSelector(".desktop-footer-newsletter input[data-tj-qa='footer-newsletter-mens']");
	public static By NEWSLETTER_WOMEN_RADIO_BUTTON = By.cssSelector(".desktop-footer-newsletter input[data-tj-qa='footer-newsletter-women']");
	public static By NEWSLETTER_BOTH_RADIO_BUTTON = By.cssSelector(".desktop-footer-newsletter input[data-tj-qa='footer-newsletter-both']");
//	public static By NEWSLETTER_INPUT_TEXTBOX = By.cssSelector(".footer-section_one>div>.shopify-section>section>form>.footer-newsletter__submit-form>input");
	public static By NEWSLETTER_INPUT_TEXTBOX = By.cssSelector(".desktop-footer-newsletter input[data-tj-qa='footer-newsletter-email']");
//	public static By NEWSLETTER_SIGN_UP_BUTTON = By.cssSelector(".footer-section_one>div>.shopify-section>section>form>.footer-newsletter__submit-form>button");
	public static By NEWSLETTER_SIGN_UP_BUTTON = By.cssSelector(".desktop-footer-newsletter button[data-tj-qa='footer-newsletter-sign-up']");
//	public static By NEWSLETTER_FORM_SUCCESS_MESSAGE = By.cssSelector(".footer-section_one>div>.shopify-section>section>form>div.footer-newsletter__form-success");
	public static By NEWSLETTER_FORM_SUCCESS_MESSAGE = By.cssSelector(".desktop-footer-newsletter span[data-tj-qa='footer-newsletter-success']");
	public static By NEWSLETTER_FORM_SUCCESS_MESSAGE_VISIBLE = By.cssSelector(".desktop-footer-newsletter div[style='display: inline-flex;']");
	public static By NEWSLETTER_FORM_FAIL_MESSAGE = By.cssSelector(".desktop-footer-newsletter span[data-tj-qa='footer-newsletter-error']");
	public static By NEWSLETTER_FORM_FAIL_MESSAGE_VISIBLE = By.cssSelector(".desktop-footer-newsletter div[style='display: block;']");
//	public static By DO_NOT_SELL_PERSONAL_INFO_LINK= By.linkText("Do Not Sell My Information");
	public static By GIVE_FEEDBACK_TITLE = By.cssSelector("div[data-tj-qa='footer-feedback-title']");
	public static By GIVE_FEEDBACK_CTA = By.cssSelector(".footer-feedback_btn");
	public static By DO_NOT_SELL_PERSONAL_INFO_LINK= By.cssSelector("a[data-tj-qa='footer-ccpa']");
	public static By CA_SUPPLY_CHAINS_ACT_LINK= By.cssSelector("a[data-tj-qa='footer-transparency-sca']");
	
	public static By CCPA_FORM_TITLE = By.cssSelector(".ccpa-form-first-step-container>.ccpa-form-title");
	public static By CCPA_STATE_OF_RESIDENCE_QSN = By.cssSelector(".ccpa-form-first-step-container>.ccpa-form>div>label");
	public static By STATE_DROPDOWN = By.cssSelector(".ccpa-form-first-step-container>.ccpa-form>div>select");
	public static By ERROR_MESSAGE = By.cssSelector(".ccpa-form--error");
	public static By NON_CALIFORNIA_USER_HINT_SECTION = By.cssSelector(".ccpa-form-noncalifornia-user");
	public static By BACK_TO_HOME_CTA = By.cssSelector(".ccpa-form-submit-contents__cta");
	public static By CALIFORNIA_USER_FORM_HEADING = By.cssSelector(".ccpa-form-california-user>div:nth-child(1)");
	public static By NAME_LABEL = By.cssSelector(".ccpa-form-label__name");
	public static By NAME_TEXTBOX = By.cssSelector(".ccpa-form--input__name");
	public static By EMAIL_LABEL = By.cssSelector(".ccpa-form-label__email");
	public static By EMAIL_TEXTBOX = By.cssSelector(".ccpa-form--input__email");
	public static By WOULD_LIKE_TO_HEADING = By.cssSelector(".ccpa-form-label__consent");
	public static By SUBMIT_REQUEST_CTA = By.cssSelector(".ccpa-form--submit");
	public static By SUCCESSFULLY_SUBMIT_MESSAGE = By.cssSelector(".ccpa-form-submit>.ccpa-form-description");
	public static By BACK_TO_HOME_CALIFORNIA = By.cssSelector("#shopify-section-section-ccpa-form > div > div > div.ccpa-form-submit-contents__cta"); 
	
    public static By RFY_SWATCH_COUNT = By.cssSelector(".dy-rec--products>div:nth-child(1) > div > div.product-item__swatches:nth-child(2)> div > div");
	public static By RFY_PRODUCT_IMG_COLORNAME = By.cssSelector(".dy-rec--products>div:nth-child(1) > div > div>a");
    
//	public static By SECOND_SKIN_IN_MEN_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(2) > div > ul > li:nth-child(7) > ul > li:nth-child(1) > a");
//	public static By COOL_COTTON_IN_MENS_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(2) > div > ul > li:nth-child(7) > ul > li:nth-child(2) > a");
//	public static By COTTON_BASICS_IN_MENS_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(2) > div > ul > li:nth-child(7) > ul > li:nth-child(7) > a");
//	public static By SHOPBYCOLLECTION = By.cssSelector(".header-desktop-left > ul > li:nth-child(2) > a");
//	
//	public static By MIXANDMATCH = By.cssSelector(".header-desktop-left > ul > li:nth-child(5) > div > ul > li:nth-child(1) > ul > li:nth-child(3) > a");
//	public static By GIFT_GUIDE = By.cssSelector(".header-desktop-left >ul >li:nth-child(7) >a");
//	public static By GIFTCARDS = By.cssSelector(".header-desktop-left >ul >li:nth-child(7) > div > ul > li:nth-child(3) > ul >li >a");
//	//public static By GIFT_CARD_IN_NEWARRIVALS_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(1) > div > ul > li:nth-child(5) > ul > li:nth-child(1) > a");
//	public static By GIFT_CARD_IN_NEWARRIVALS_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(1) > div > ul > li:nth-child(3) > ul > li > a");
//	public static By BRA_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(5) > a");
//	public static By SHOP_ALL_BRAS = By.cssSelector(".header-desktop-left > ul > li:nth-child(5) > div > ul > li:nth-child(1) > ul > li:nth-child(1) > a");
//	
//	public static By BRAS_TITLE = By.cssSelector(".header-desktop-left > ul > li:nth-child(5) > div > ul > li:nth-child(1) > div");
//	public static By ALL_PANTIES_IN_WOMEN_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(3) > div > ul > li:nth-child(2) > ul > li:nth-child(1) > a");
//	public static By AIR_IN_WOMEN_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(3) > div > ul > li:nth-child(6) > ul > li:nth-child(3) > a");
//
//	public static By TRIANGLE_BRALETTE = By.cssSelector(".header-desktop-left > ul > li:nth-child(3) > div > ul > li:nth-child(3) > ul > li:nth-child(6) > a");
//	public static By SCOOP_BRALETTE = By.cssSelector(".header-desktop-left > ul > li:nth-child(3) > div > ul > li:nth-child(3) > ul > li:nth-child(5) > a");
//
//	public static By HISANDHERPACKS = By.cssSelector(".header-desktop-left > ul > li:nth-child(6) > div > ul > li:nth-child(2) > ul > li:nth-child(6) > a");
	
	public static String MENS_LOUNGEWEAR_TITLE_1 = "Men's Loungewear";
	public static By MENS_LOUNGEWEAR_TITLE = By.xpath("//h1[@class='collection-title-bar__title']");
	
}