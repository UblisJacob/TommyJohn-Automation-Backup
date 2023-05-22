package com.tommyjohn.automation.PageLocators;

import org.openqa.selenium.By;

public class CollectionPageLocator {
	
	public static By STYLE_COROSALS = By.cssSelector("div.hero-interactive__slider.slick-initialized");
	public static By TOTAL_NO_OF_COROSALS = By.cssSelector(".hero-interactive-slide.slick-slide");
//	public static By MENS_ALL_UNDERWEAR_FIRST_PRODUCT_IMAGE = By.cssSelector(".product-image-container > a");
//	public static By THIRD_PRODUCT_IMAGE = By.cssSelector("div.mega-collection-grid__row:nth-child(3) > div:nth-child(3) > div:nth-child(1) > a > img");
//	public static By THIRD_PRODUCT_HEAD_LINE_TEXT = By.cssSelector("div.mega-collection-grid__row:nth-child(3) > div:nth-child(3) > a > h2");
//	public static By THIRD_PRODUCT_REVIEW_STARS = By.cssSelector("div.mega-collection-grid__row:nth-child(3) > div:nth-child(3) > a > div");
//	public static By FIRST_PRODUCT_IMAGE = By.cssSelector("div.mega-collection-grid__row:nth-child(3) > div:nth-child(1) > div > a > img");
//	public static By FIRST_PRODUCT_IMAGE = By.cssSelector(".product-image");
//	public static By FIRST_PRODUCT_IMAGE = By.cssSelector(".product-image-container");
//	public static By FIRST_PRODUCT_HEAD_LINE_TEXT_LINK = By.cssSelector("div.mega-collection-grid__row:nth-child(3) > div:nth-child(1) > a > h2");
//	public static By FIRST_PRODUCT_REVIEW_STARS = By.cssSelector("div.mega-collection-grid__row:nth-child(3) > div:nth-child(1) > a > div");
	
	public static By FIRST_PRODUCT = By.cssSelector(".product-item");
	public static By COMPARE_PRODUCT = By.cssSelector(".product-image-container > div.has-compare");
	public static By FIRST_PRODUCT_IMAGE = By.cssSelector(".product-image");
	//changed
	public static String PRODUCT_IMAGES = "//div[contains(@class,'product-image')]//a";
	public static By PRODUCT_COUNT_TEXT = By.xpath("//div[contains(@class,'controls__count')]//span");
	public static By HOVERED_PRODUCT_IMAGE = By.cssSelector(".product-image--hover");
	public static By FIRST_PRODUCT_HEAD_LINE_TEXT = By.cssSelector(".product-meta__title");
	public static By FIRST_PRODUCT_REVIEW_STARS = By.cssSelector(".yotpo-bottomline");
	public static By FIRST_PRODUCT_URL = By.cssSelector(".product-meta");
	
	public static By ALL_COLOR_LIST = By.cssSelector("div.mega-collection-grid.js-mega-collection-grid > div:nth-child(3) > div:nth-child(1)>div:nth-child(2)>.product-item__swatch-list>.slick-list>.slick-track>div");
	public static By PRODUCT_IMAGE_COLOR_NAME= By.cssSelector("div.mega-collection-grid.js-mega-collection-grid > div:nth-child(3) > div:nth-child(1)>div:nth-child(1)>a");
	public static By COLOR_SWATCH_PREV_SLICK_ARROWS = By.cssSelector("div.mega-collection-grid.js-mega-collection-grid > div:nth-child(3) > div:nth-child(1)>div:nth-child(2)>.product-item__swatch-list>.slick-next");
	public static By COLOR_SWATCH_NEXT_SLICK_ARROWS = By.cssSelector("div.mega-collection-grid.js-mega-collection-grid > div:nth-child(3) > div:nth-child(1)>div:nth-child(2)>.product-item__swatch-list>.slick-prev");

	
	public static By MAIN_HEADING_OF_SELECTED_CATEGORY = By.cssSelector("div.collection-title-bar__content > h1");
	public static By ALL_FILTERS = By.cssSelector("div.mega-collection-filters > div > a");
	//changed
	public static By DISPLAYED_ALL_SELECTED_OPTIONS = By.xpath("(//div[contains(@class,'mega-collection-filter-tags mega-collection-filter-tags--desktop')]//button)");
	public static By CLEAR_FILTER_BUTTON = By.cssSelector("div.mega-collection-filters--controls > a");
	public static By FIRST_ITEM_IN_DISPLAYED_LIST = By.cssSelector("div.js-mega-collection-desktop-filter-tags > button:nth-child(1)");
	public static By TOTAL_COUNTS_OF_ITEMS = By.cssSelector("div.mega-collection-filters--controls__count > span");
	public static By LAST_FILTER = By.cssSelector(".mega-collection-filters > select");
	public static By SORTBYPRICE_HIGH_TO_LOW_FILTER_OPTION = By.cssSelector(".js-mega-collection-filters-sort > option:nth-child(2)");
	
	//Quick shop
		public static By QUICK_SHOP_ATC = By.cssSelector(".product-grid-controls.has-quickshop>button");
		public static By QUICK_SHOP_SIZE_OVERLAY = By.cssSelector(".inline-quick-shop");
		public static By QUICK_SHOP_SIZE_OVERLAY_CLOSE = By.cssSelector(".inline-quick-shop>.icon-close");
		public static By QUICK_SHOP_SIZE_OVERLAY_HEADING = By.cssSelector(".inline-quick-shop>.inline-quick-shop__option-heading");
		public static By QUICK_SHOP_SIZE_LIST = By.cssSelector(".quickshop-open>.inline-quick-shop>ul>li");
		public static By QUICK_SHOP_SIZE_LABEL = By.cssSelector(".quickshop-open>.inline-quick-shop>ul>li>label");
		public static By QUICK_SHOP_SIZE_OVERLAY_ATC = By.cssSelector(".inline-quick-shop>.inline-quick-shop__button");
		public static By PRODUCT_TITLE_ON_COLLECTION = By.cssSelector(".product-meta__title");
		public static By PRODUCT_ITEM_SWATCH_CIRCLE = By.cssSelector(".product-item__swatch-wrapper>div");
		public static By PRODUCT_AMOUNT_ON_Collection = By.cssSelector(".product-meta__price");
		public static By PRODUCT_TITLE_ON_FLYCART = By.cssSelector("div.inline-cart__col1 > div:nth-child(6) > .line-item >.line-item__summary>a");
	    public static By PRODUCT_LINE_ITEM_OPTION = By.cssSelector(" div.line-item__summary > div.line-item__option");
	    public static By PRODUCT_AMOUNT_ON_FLYCART = By.cssSelector(".line-item__price>span");
	    
	    //changed
	    public static By SORTBYPRICE_DROPDOWN = By.xpath("//select[@title='Sort By']");
	    public static By PLP_PRICE = By.cssSelector("span.product-meta__price");
	    public static By PLP_PRICE_FILTER = By.xpath("//a[@title='Price']//span");
	    public static By PRICE_LESS_THAN_25= By.xpath("(//input[contains(@data-filter-btn,'Less than $25')])[1]");
	    public static By LESS_THAN_25_PILL= By.xpath("(//button[@title='Less than $25'])[1]");
	    public static By NEW_ARRIVALS_CTC= By.xpath("//a[contains(text(),'New Arrivals')]");
	    public static By NEW_ARRIVALS_TITLE= By.xpath("//h1[@class='collection-title-bar__title']");
	    public static By NEW_ARRIVALS_BREADCRUMB= By.xpath("//div[@itemprop='itemListElement']//a//span[contains(text(),'New Arrivals')]");
	    
	    
	    //new
	    public static String PLP_PRODUCTS= "(//span[@class='product-meta__title'])";
	    public static By PLP_BREADCRUMB= By.cssSelector("nav.breadcrumbs>div:nth-child(2)>a");
	    public static By PLP_TITLE= By.cssSelector("div.collection-title-bar__content>h1");
	    public static By PLP_INLINE_BANNER= By.xpath("//div[contains(@class,'dy-inline-banner')]");
	    public static String PLP_SECTION_HEADER = "(//h2[@class='mega-collection-grid__title'])";
	    
	    public static By PLP_SEO_TITLE = By.xpath("//h2[@class='collection-seo-title']");
	    public static By PLP_SEO_BODY = By.xpath("//div[@class='collection-seo-body']");
	    public static By PLP_SEO_LINK = By.xpath("//div[@class='collection-seo-body']//a");
	    
	    public static By PLP_PRODUCT_IMAGE_BADGE = By.xpath("//div[@class='productImage-item__badge']//span");
	    public static By PLP_PRODUCT_SECONDARY_BADGE = By.xpath("//div[contains(@class,'product-item__badge')]//span");
	    
	    
	    
	  
	    
	    
}
