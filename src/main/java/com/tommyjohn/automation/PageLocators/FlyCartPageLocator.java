package com.tommyjohn.automation.PageLocators;

import org.openqa.selenium.By;

public class FlyCartPageLocator {
	public static By INLINE_CART_SECTION = By.cssSelector(".section.inline-cart");
	public static By INLINE_CART_ICON = By.xpath("//button[@class='header-cart-btn']");
	public static By INLINE_CART_CLOSE_BUTTON = By.cssSelector("button[data-tj-qa='inline-cart-close'],button.inline-cart__close-btn");
	public static By VIEW_CART_LINK = By.xpath("//a[@class='inline-cart__cart-link']");
	public static By INLINE_CART_TITLE = By.cssSelector("[data-tj-qa='inline-cart-title'],.inline-cart__title");
	public static By SHIPING_MESSAGE_TEXT = By.cssSelector(".shipping-message__text");
	public static By SHIPING_MESSAGE_METER = By.cssSelector(".shipping-message__meter > span");
	public static By INLINE_CART_EMPTY_TEXT = By.cssSelector(".inline-cart__empty");
	public static By RECOMMENDED_PRODUCT_HEADING = By.cssSelector(".recommended-products__heading");
	public static By RECOMMENDED_PRODUCTS = By.cssSelector(".recommended-products");
	public static By RECOMMENDED_PRODUCTS_LIST = By.cssSelector(".recommended-products > a");
	public static By LIST_OF_ITEMS_IN_FLYCART = By.cssSelector(".line-item");
	public static By LINE_ITEM_IMAGE = By.cssSelector("div[data-tj-qa='cart-line-item-image']");
	public static By LINE_ITEM_OPTION = By.cssSelector(
			"div.inline-cart__col1 > div:nth-child(4) > article:nth-child(1) > div.line-item__summary > div.line-item__option");
	
	public static By QUANTITY_PLUS_MINUS_BUTTON = By.cssSelector(
			"article:nth-child(1) > div.line-item__summary > div.line-item__total > div.qty-selector.line-item__qty");
	public static By ADD_GIFT_WRAP_BUTTON = By.cssSelector("span[data-tj-qa='add-gift-wrap-link'],div.inline-cart__gift-wrap > a > span:nth-child(2)");
	public static By SUBTOTAL_TEXT = By.cssSelector("div[data-tj-qa='inline-cart-subtotal-text'],.inline-cart__subtotal--text");
	public static By ESTIMATE_TOTAL = By.cssSelector("div[data-tj-qa='inline-cart-subtotal-text']>span,div.inline-cart__summary > div > div > span");
	public static By ESTIMATE_TOTAL_SMALL = By.cssSelector("div[data-tj-qa='inline-cart-subtotal-text']>small,div.inline-cart__summary > div > div > small");
	public static By CHECKOUT_BUTTON = By
			.cssSelector("a[data-tj-qa='inline-cart-checkout'] , .inline-cart__action-btns>a");
	public static By FIRST_PRODUCT_TITLE_IN_CART = By.cssSelector("a[data-tj-qa='cart-line-item-title'],div>.line-item__title"); // ("div.inline-cart__col1 >
																						// div:nth-child(7) > article >
																						// div.line-item__summary > a");
	public static By PLUS_BUTTON = By.cssSelector("button[data-tj-qa='cart-line-item-plus'],.qty-selector.line-item__qty > button:nth-child(3)");
	public static By MINUS_BUTTON = By.cssSelector("button[data-tj-qa='cart-line-item-minus'],.qty-selector.line-item__qty > button:nth-child(1)");
	public static By QUANTITY = By.cssSelector("input[data-tj-qa='cart-line-item-qty'] , .qty-selector.line-item__qty > input");
	public static By CROSS_CLOSE_BUTTON = By.cssSelector("button[data-tj-qa='cart-line-item-remove'],article > div.line-item__summary > button");

	public static By COLOR_AND_SIZE_IN_CART = By.cssSelector("div[data-tj-qa='cart-line-item-option']");
	public static By PRICE_IN_QUANTITY_LINE = By.cssSelector(
			"div[data-tj-qa='inline-cart-line-item-subtotal']>span");

	public static By GIFT_WRAP = By.cssSelector("div.inline-cart__gift-wrap > a");
	public static By GIFT_WRAP_POPUP = By.cssSelector(".modal-overlay.gift-option.active");
	public static By GIFT_BOX_$5 = By.cssSelector("div[data-tj-qa='gift-wrap-checkbox'],div.gift-option__input-group.gift-option__gift-box > div");
	public static By GIFT_WRAP_APPLY = By.cssSelector("button[data-tj-qa='gift-box-apply'],button.gift-option__btn.button");
	public static By GIFT_WRAP_PRICE = By.cssSelector("span[data-tj-qa='gift-box-price'],div.inline-cart__gift-wrap > span:nth-child(2)");
	public static By ESTIMATE_TOTAL_PRICE = By.cssSelector("span[data-tj-qa='inline-cart-subtotal-price'],div.inline-cart__summary > div > span");

	public static By COLOR_AND_SIZE_IN_CART_MULTI = By
			.xpath("//div[@data-tj-qa='cart-line-item-option'] | (//div[@class='line-item__summary'])[1]//div[@class='line-item__option']");

	// public static By COLOR_AND_SIZE_IN_CART_MULTI =
	// By.cssSelector("div.line-item__summary > div:nth-child(2)");

	// OOS
	public static By OOS_MESSAGE_ON_TOP = By
			.cssSelector("span[data-tj-qa='inline-cart-oos-message'],.inline-cart.active.has-recommendations>div:nth-child(2)>span");
	public static By OOS_LINE_ITEMS = By
			.cssSelector("span[data-tj-qa='inline-cart-line-item-oos-message']");

	// AfterPay

	public static By AFTERPAY_DIV = By.cssSelector(".afterpay-cart-container>.afterpay-paragraph");
	public static By AFTERPAYTEXT = By.cssSelector("#afterpay-messaging-widget");
	public static By AFTERPAYLOGO_ICON = By.cssSelector(".afterpay-link");
	public static By AFTERPAY_FLYCART = By.cssSelector(".afterpay-incart-container > p > span");

	public static By QUICK_SHOP_SIZE_LIST = By.cssSelector(".quickshop-open>.inline-quick-shop>ul>li");

	public static By TIERED_METER_TITLE = By.cssSelector("p[data-tj-qa='tier-message-hit'],p#tier-message-reached-cart");
	public static By TIERED_METER = By.cssSelector("div[data-tj-qa='tier-0']");	
	public static By PROMOTIONAL_MESSAGE = By.cssSelector("div[data-tj-qa='inline-cart-promo-message']");

	public static By PROMOTIONAL_PRICE = By.xpath("(((//div[@class='cart__content content']//div[@class='line-item__summary'])[1])//span[@class='sale'])[1]");

	//FlyCart Suggestions

	public static By SUGGESTION_FIRST_PRODUCT = By.xpath("(//img[@data-tj-qa='plp-product-img-main'])[1]");
	public static By SUGGESTION_SECOND_PRODUCT = By.xpath("(//img[@data-tj-qa='plp-product-img-main'])[2]");
    public static	By QUICK_SHOP_ATC = By.cssSelector("[data-tj-qa='plp-quickshop-cta'],.product-grid-controls.has-quickshop>button");
	public static By QUICK_SHOP_SIZE_OVERLAY = By.cssSelector(".inline-quick-shop");
	public static By SUGGESTION_FIRST_PRODUCT_SIZE = By.xpath("(//label[@class='inline-quick-shop__button'])[1]");
	public static By SUGGESTION_SECOND_PRODUCT_SIZE = By.xpath("(//label[@class='inline-quick-shop__button'])[1]");
	public static By SUGGESTION_FIRST_PRODUCT_NAME = By.xpath("(//span[@class='product-meta__title'])[1]");
	public static By SUGGESTION_SECOND_PRODUCT_NAME = By.xpath("(//span[@class='product-meta__title'])[2]");
	public static By INLINECART_PRODUCT_NAME = By.xpath("//a[@class='line-item__title']");

	public static By QUICK_SHOP_SIZE_OVERLAY_CLOSE = By.cssSelector("[data-tj-qa='plp-quickshop-close'],.inline-quick-shop>.icon-close");
	public static By QUICK_SHOP_SIZE_OVERLAY_HEADING = By.cssSelector(".inline-quick-shop>.inline-quick-shop__option-heading");
	public static By QUICK_SHOP_SIZE_LIST_1 = By.cssSelector("[data-tj-qa='plp-quickshop-instock'],.quickshop-open>.inline-quick-shop>ul:nth-child(4)>li");
	public static By QUICK_SHOP_SIZE_LIST_2 = By.cssSelector(".quickshop-open>.inline-quick-shop>ul:nth-child(6)>li");
	public static By QUICK_SHOP_SIZE_LABEL = By.cssSelector("[data-tj-qa='plp-quickshop-instock']>label,.quickshop-open>.inline-quick-shop>ul>li>label");
	public static By QUICK_SHOP_SIZE_OVERLAY_ATC = By.cssSelector(".inline-quick-shop>.inline-quick-shop__button");
	public static By PRODUCT_TITLE_ON_COLLECTION = By.cssSelector("[data-tj-qa='product-title'],.product-meta__title");
	public static By PRODUCT_ITEM_SWATCH_CIRCLE = By.cssSelector("[data-tj-qa='product-swatch'],.product-item__swatch-wrapper>div");
	public static By PRODUCT_AMOUNT_ON_Collection = By.cssSelector("[data-tj-qa='product-price'],.product-meta__price");
	public static By PRODUCT_TITLE_ON_FLYCART = By.cssSelector("[data-tj-qa='cart-line-item-title'],div.inline-cart__col1 > div:nth-child(6) > .line-item >.line-item__summary>a");
	public static By PRODUCT_LINE_ITEM_OPTION = By.cssSelector("[data-tj-qa='cart-line-item-option'],div.line-item__summary > div.line-item__option");
	public static By PRODUCT_AMOUNT_ON_FLYCART = By.cssSelector("[data-tj-qa='cart-line-item-sale-price'],.line-item__price>span");



}