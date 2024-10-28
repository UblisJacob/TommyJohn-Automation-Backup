package com.tommyjohn.automation.PageLocators;

import org.openqa.selenium.By;

public class CollectionPageLocator {
	
	public static By STYLE_COROSALS = By.cssSelector("div.dyCollection-hero__interactive-inner");
	public static By TOTAL_NO_OF_COROSALS = By.xpath("//div[@class='interactive-hero-slide asset-type-icons']");
	public static By FILTER_PILL = By.xpath("//button[@title='Filters']");
//	public static By MENS_ALL_UNDERWEAR_FIRST_PRODUCT_IMAGE = By.cssSelector(".product-image-container > a");
//	public static By THIRD_PRODUCT_IMAGE = By.cssSelector("div.mega-collection-grid__row:nth-child(3) > div:nth-child(3) > div:nth-child(1) > a > img");
//	public static By THIRD_PRODUCT_HEAD_LINE_TEXT = By.cssSelector("div.mega-collection-grid__row:nth-child(3) > div:nth-child(3) > a > h2");
//	public static By THIRD_PRODUCT_REVIEW_STARS = By.cssSelector("div.mega-collection-grid__row:nth-child(3) > div:nth-child(3) > a > div");
//	public static By FIRST_PRODUCT_IMAGE = By.cssSelector("div.mega-collection-grid__row:nth-child(3) > div:nth-child(1) > div > a > img");
//	public static By FIRST_PRODUCT_IMAGE = By.cssSelector(".product-image");
//	public static By FIRST_PRODUCT_IMAGE = By.cssSelector(".product-image-container");
//	public static By FIRST_PRODUCT_HEAD_LINE_TEXT_LINK = By.cssSelector("div.mega-collection-grid__row:nth-child(3) > div:nth-child(1) > a > h2");
//	public static By FIRST_PRODUCT_REVIEW_STARS = By.cssSelector("div.mega-collection-grid__row:nth-child(3) > div:nth-child(1) > a > div");
	
	public static By FIRST_PRODUCT = By.xpath("(//div[@class='relative aspect-tile'])[1]");
	public static By COMPARE_PRODUCT = By.cssSelector(".product-image-container > div.has-compare");
	public static By FIRST_PRODUCT_IMAGE = By.xpath("(//img[@class='aspect-tile bg-background-image object-cover text-[0] w-full'])[1]");
	public static By HOVER_PRODUCT_IMAGE = By.xpath("(//img[@class='aspect-tile bg-background-image object-cover text-[0] w-full'])[1]");
	public static By QUICKSHOP_CLOSE = By.xpath("(//span[@class='icon size-5 icon-x'])[1]");
	//changed
	public static String PRODUCT_IMAGES = "//div[contains(@class,'product-image')]//a";
	public static By PRODUCT_COUNT_TEXT = By.xpath("//*[data-tj-qa='plp-total-items']>span | //div[contains(@class,'controls__count')]//span");
	public static By HOVERED_PRODUCT_IMAGE = By.cssSelector("[data-tj-qa='plp-product-img-hover'],.product-image--hover");
	public static By FIRST_PRODUCT_HEAD_TEXT = By.xpath("(//div[@class='relative aspect-tile'])[1]");
	public static By FIRST_PRODUCT_HEAD_LINE_TEXT_COPY = By.xpath("(//span[@class='product-meta__title'])[1]");
	public static By FIRST_PRODUCT_HEAD_LINE_TEXT = By.xpath("(//a[@data-cy='productTileLink'])[1]");
	public static By FIRST_PRODUCT_TEXT = By.xpath("(//span[@class='product-meta__title'])[1]");
	public static By FILTER = By.xpath("//span[@class='collection-meta__title']");

	public static By FIRST_PRODUCT_REVIEW_STARS = By.cssSelector("[data-tj-qa='product-reviews'],.yotpo-bottomline");
	public static By FIRST_PRODUCT_URL = By.cssSelector(".product-meta");
	
	public static By ALL_COLOR_LIST = By.cssSelector("ul>li>input[data-tj-qa='color-swatch'] , div[data-index='0']>div:nth-child(2)>div>div>div.product-item__swatch--circle");
//	public static By PRODUCT_IMAGE_COLOR_NAME= By.cssSelector("div[data-index='0']>div>a");
	public static By PRODUCT_IMAGE_COLOR_NAME= By.xpath("(//div[@class='product_image slick-slide slick-current slick-active'])[2]");
	public static By COLOR_SWATCH_PREV_SLICK_ARROWS = By.cssSelector("div.mega-collection-grid.js-mega-collection-grid > div:nth-child(3) > div:nth-child(1)>div:nth-child(2)>.product-item__swatch-list>.slick-next");
	public static By COLOR_SWATCH_NEXT_SLICK_ARROWS = By.cssSelector("div.mega-collection-grid.js-mega-collection-grid > div:nth-child(3) > div:nth-child(1)>div:nth-child(2)>.product-item__swatch-list>.slick-prev");

	
	public static By MAIN_HEADING_OF_SELECTED_CATEGORY = By.cssSelector("[data-tj-qa='plp-title'],div.collection-title-bar__content > h1");
	public static By ALL_FILTERS = By.xpath("//span[@class='icon icon-filters']");
	public static By ALL_FILTERS_LIST = By.xpath("//div[@class='font-sans']");
	//changed
	public static By DISPLAYED_ALL_SELECTED_OPTIONS = By.xpath("(//div[contains(@class,'mega-collection-filter-tags mega-collection-filter-tags--desktop')]//button)");
	public static By CLEAR_FILTER_BUTTON = By.cssSelector("[data-tj-qa='plp-clear-filters'],div.mega-collection-filters--controls > a");
	public static By FIRST_ITEM_IN_DISPLAYED_LIST = By.cssSelector("div.js-mega-collection-desktop-filter-tags > button:nth-child(1)");
	public static By TOTAL_COUNTS_OF_ITEMS = By.cssSelector("[data-tj-qa='plp-total-items']>span,div.mega-collection-filters--controls__count > span");
	public static By LAST_FILTER = By.cssSelector("[data-tj-qa='plp-filter-sort'],.mega-collection-filters > select");
	public static By SORTBYPRICE_HIGH_TO_LOW_FILTER_OPTION = By.cssSelector("[data-tj-qa='plp-sortby-relevance'],.js-mega-collection-filters-sort > option:nth-child(2)");
	
	//Quick shop
		public static By QUICK_SHOP_ATC = By.xpath("//span[@class='font-normal']");
		public static By ADD_TO_BAG_QUICKSHOP = By.xpath("//button[@class='button grow']");
		public static By QUICK_SHOP_SIZE_OVERLAY = By.xpath("//div[@class='space-y-lg']");
		public static By QUICK_SHOP_SIZE_OVERLAY_CLOSE = By.xpath("(//span[@class='icon size-5 icon-x'])[1]");
		public static By QUICK_SHOP_SIZE_OVERLAY_HEADING = By.xpath("(//span[@class='font-medium'])[2]");
		public static By QUICK_SHOP_SIZE_LIST_1 = By.xpath("//input[@name='option[Top Size]']");
		public static By QUICK_SHOP_SIZE_LIST_2 = By.xpath("//input[@name='option[Bottom Size]']");
		public static By QUICK_SHOP_SIZE_LABEL = By.xpath("(//span[@class='font-medium'])[2]");
		public static By QUICK_SHOP_SIZE_OVERLAY_ATC = By.cssSelector(".inline-quick-shop>.inline-quick-shop__button");
		public static By PRODUCT_TITLE_ON_COLLECTION = By.xpath("(//a[@data-cy='productTileLink'])");
		public static By PRODUCT_ITEM_SWATCH_CIRCLE = By.xpath("//img[@class='size-full rounded-full bg-background-image object-cover text-[0] ring-1 ring-stroke-primary-subtle']");
		public static By PRODUCT_AMOUNT_ON_Collection = By.xpath("//span[@data-cy='priceFinal']");
		public static By PRODUCT_TITLE_ON_FLYCART = By.cssSelector("[data-tj-qa='cart-line-item-title'],div.inline-cart__col1 > div:nth-child(6) > .line-item >.line-item__summary>a");
	    public static By PRODUCT_LINE_ITEM_OPTION = By.cssSelector("[data-tj-qa='cart-line-item-option'],div.line-item__summary > div.line-item__option");
	    public static By PRODUCT_AMOUNT_ON_FLYCART = By.cssSelector("[data-tj-qa='cart-line-item-sale-price'],.line-item__price>span");
	    
	    //changed
	   public static By SORTBYPRICE_DROPDOWN = By.xpath("//*[data-tj-qa='plp-filter-sort'] | //select[@title='Sort By']");
	    public static By PRICE_DROPDOWN = By.xpath("//span[@class='all-filter-pill-span']");
	    public static By PRICEASC_DROPDOWN = By.xpath("//div[@class='collection-filter__select']//li[@data-tj-qa='plp-sortby-price-low-to-high']");



	public static By PLP_PRICE = By.xpath("//span[@data-cy='priceFinal']");
	    public static By PLP_PRICE_FILTER = By.xpath("//a[@title='Price']//span");
	    
	    public static By PRICE_LESS_THAN_25= By.xpath("(//input[contains(@data-filter-btn,'Less than $25')])[1]");
	    public static By LESS_THAN_25_PILL= By.xpath("(//button[@title='Less than $25'])[1]");
	    public static By NEW_ARRIVALS_CTC= By.xpath("//a[contains(text(),'New Arrivals')]");
	    public static By NEW_ARRIVALS_TITLE= By.xpath("//*[data-tj-qa='plp-title'] | //h1[@class='collection-title-bar__title']");
	    public static By NEW_ARRIVALS_BREADCRUMB= By.xpath("//div[@itemprop='itemListElement']//a//span[contains(text(),'New Arrivals')]");
	    
	    
	    //new
	    public static String PLP_PRODUCTS= "//div[@class='relative aspect-tile']";
	    public static By PLP_BREADCRUMBGENDER= By.xpath("//span[contains(text(),'Womens')]");
	    public static By PLP_BREADCRUMBHOME= By.xpath("//span[contains(text(),'Home')]");
	    public static By PLP_TITLE= By.xpath("(//div[@class='shopify-section']//h1//span)[1]");
	    public static By PLP_TITLE_COUNT= By.xpath("//vue-app[@data-vue='CollectionCount']//span");
	    public static By PLP_INLINE_BANNER= By.xpath("(//div[@class='button-inverse mt-1'])[1]");
	    public static String PLP_SECTION_HEADER = "//p[@class='mb-md font-serif text-body-lg text-text-accent']";
		public static By INLINEBANNERTEXT = By.xpath("//div[@class='plpIlb_maintext']");
		public static By PAGEHEADER = By.xpath("//h2");
		public static By SHOP_PAGEHEADER = By.xpath("//h1");
		public static By PRODUCT_IMAGE = By.xpath("//img[@class='aspect-tile bg-background-image object-cover text-[0] w-full']");
	    public static By PLP_FILTER_SINGLE_PROODUCT = By.cssSelector("div.mega-collection-filters>div:nth-child(7)>ul>li>label>span[data-result-count='1']");
		public static By MULTIPACKS_CHECKBOX = By.xpath("(//input[@name='filter.pack_size'])[1]");
		public static By PACKS = By.xpath("//a[@data-cy='productTileLink']");
	    
	    public static By PLP_SEO_TITLE = By.xpath("//*[data-tj-qa='plp-seo-title'] | //h2[@class='collection-seo-title']");
	    public static By PLP_SEO_BODY = By.xpath("//*[data-tj-qa='plp-seo-body'] | //div[@class='collection-seo-body']");
	    public static By PLP_SEO_LINK = By.xpath("//div[@class='collection-seo-body']//a");
	    
	    public static By PLP_PRODUCT_IMAGE_BADGE = By.xpath("//p[contains(@class,'inline-block')]");
	    public static By PLP_PRODUCT_SECONDARY_BADGE = By.xpath("//p[contains(@class,'inline-block')]");
	    
	     public static By SEARCH_PRICE_FILTER = By.xpath("//a[@data-filter-group='facet_price_range']//span[contains(text(),'Price')]");
	    public static By SEARCH_PRICE_FILTER_1=By.xpath("//button[contains(@for,'25to50')]");
	    public static By SEARCH_PRICE_FILTER_PILL_1=By.xpath("(//button[contains(text(),'$25 to $50')])[1]");
	  
	    
	    
}

