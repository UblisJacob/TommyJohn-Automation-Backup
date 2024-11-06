package com.tommyjohn.automation.PageLocators;

import org.openqa.selenium.By;

public class FlyCartPageLocator {
	public static By INLINE_CART_SECTION = By.cssSelector(".section.inline-cart");
	public static By INLINE_CART_ICON = By.xpath("//span[@class='icon block icon-cart']");
	public static By INLINE_CART_CLOSE_BUTTON = By.xpath("//span[@class='icon block size-5 icon-arrow-left']");
	public static By VIEW_CART_LINK = By.xpath("//a[@class='button-link text-body sm:text-body-sm']");
	public static By INLINE_CART_TITLE = By.xpath("//h2[contains(text(),'Your Cart')]");
	public static By SHIPING_MESSAGE_TEXT = By.xpath("//p[contains(text(),'Your cart is currently empty.')]");
	public static By SHIPING_MESSAGE_METER = By.cssSelector(".shipping-message__meter > span");
	public static By INLINE_CART_EMPTY_TEXT = By.xpath("//p[contains(text(),'Your cart is currently empty.')]");
	public static By RECOMMENDED_PRODUCT_HEADING = By.cssSelector(".recommended-products__heading");
	public static By RECOMMENDED_PRODUCTS = By.cssSelector(".recommended-products");
	public static By RECOMMENDED_PRODUCTS_LIST = By.cssSelector(".recommended-products > a");
	public static By LIST_OF_ITEMS_IN_FLYCART = By.xpath("//td[@class='ml-4 p-0 sm:ml-6 sm:flex sm:flex-col sm:items-start sm:justify-start']");
	public static By LINE_ITEM_IMAGE = By.cssSelector("div[data-tj-qa='cart-line-item-image']");
	public static By LINE_ITEM_OPTION = By.cssSelector(
			"div.inline-cart__col1 > div:nth-child(4) > article:nth-child(1) > div.line-item__summary > div.line-item__option");
	
	public static By QUANTITY_PLUS_MINUS_BUTTON = By.xpath(
			"article:nth-child(1) > div.line-item__summary > div.line-item__total > div.qty-selector.line-item__qty");
	public static By ADD_GIFT_WRAP_BUTTON = By.xpath("//button[@class='button-link text-body sm:text-body-md']");
	public static By SUBTOTAL_TEXT = By.cssSelector("div[data-tj-qa='inline-cart-subtotal-text'],.inline-cart__subtotal--text");
	public static By ESTIMATE_TOTAL = By.xpath("//span[contains(text(),'Subtotal')]");
	public static By ESTIMATE_TOTAL_SMALL = By.cssSelector("div[data-tj-qa='inline-cart-subtotal-text']>small,div.inline-cart__summary > div > div > small");
	public static By CHECKOUT_BUTTON = By.xpath("//button[@data-cy='cartFormCheckoutButton']");
	public static By FIRST_PRODUCT_TITLE_IN_CART = By.xpath("(//a[@class='font-medium text-text-primary mt-1 block text-body-lg leading-tight sm:text-body sm:leading-5'])[1]"); // ("div.inline-cart__col1 >
																						// div:nth-child(7) > article >
																						// div.line-item__summary > a");
	public static By PLUS_BUTTON = By.cssSelector("button[data-tj-qa='cart-line-item-plus'],.qty-selector.line-item__qty > button:nth-child(3)");
	public static By MINUS_BUTTON = By.cssSelector("button[data-tj-qa='cart-line-item-minus'],.qty-selector.line-item__qty > button:nth-child(1)");
	public static By QUANTITY = By.cssSelector("input[data-tj-qa='cart-line-item-qty'] , .qty-selector.line-item__qty > input");
	public static By CROSS_CLOSE_BUTTON = By.cssSelector("button[data-tj-qa='cart-line-item-remove'],article > div.line-item__summary > button");

	public static By COLOR_AND_SIZE_IN_CART = By.xpath("(//div[@class='mt-2 text-body leading-[18px] text-text-secondary'])[1]");
	public static By PRICE_IN_QUANTITY_LINE = By.xpath("//div[@class='mt-2 flex gap-2 text-body font-medium']//span//following-sibling::span");

	public static By GIFT_WRAP = By.cssSelector("div.inline-cart__gift-wrap > a");
	public static By GIFT_WRAP_POPUP = By.xpath("//div[@aria-labelledby='GiftWrap-ModalTitle']");
	public static By GIFT_BOX_$5_CHECKBOX = By.xpath("//input[@name='gift-box']");
	public static By GIFT_BOX_$5 = By.cssSelector("div[data-tj-qa='gift-wrap-checkbox'],div.gift-option__input-group.gift-option__gift-box > div");
	public static By GIFT_WRAP_APPLY = By.xpath("//button[@class='button button-md sm:button-lg w-full']");
	public static By GIFT_WRAP_PRICE = By.cssSelector("span[data-tj-qa='gift-box-price'],div.inline-cart__gift-wrap > span:nth-child(2)");
	public static By ESTIMATE_TOTAL_PRICE = By.cssSelector("span[data-tj-qa='inline-cart-subtotal-price'],div.inline-cart__summary > div > span");

	public static By COLOR_AND_SIZE_IN_CART_MULTI = By
			.xpath("//div[@data-tj-qa='cart-line-item-option'] | (//div[@class='line-item__summary'])[1]//div[@class='line-item__option']");

	// public static By COLOR_AND_SIZE_IN_CART_MULTI =
	// By.cssSelector("div.line-item__summary > div:nth-child(2)");

	//COUPONCODE
	public static By CART_COUPONFIELD = By.xpath("//input[@id='Discount-Code']");
	public static By APPLY_CTA = By.xpath("//button[@class='button button-lg px-8']");
	public static By APPLIED_COUPON = By.xpath("//span[@class='sr-only' and contains(text(),'Discount:')]");
	public static By ERROR_COUPON = By.xpath("//p[@class='mt-4 text-functional-error']");
	public static By INLINE_CART_APPLIED_COUPON = By.xpath("//div[@class='mt-2 text-body font-medium text-functional-sale']");

	//3for$48
	public static By DISCOUNT_3for$48 = By.xpath("//div[@class='mt-2 text-body font-medium text-functional-sale']");
	public static By TOTAL = By.xpath("(//span[contains(text(),'$48.00')])[2]");





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

	public static By QUICK_SHOP_SIZE_LIST = By.xpath("//input[@name='option[Size]']");

	public static By TIERED_METER_TITLE = By.cssSelector("p[data-tj-qa='tier-message-hit'],p#tier-message-reached-cart");
	public static By TIERED_METER = By.cssSelector("//div[@class='relative h-0.5 bg-surface-secondary']");
	public static By PROMOTIONAL_MESSAGE = By.xpath("//p[contains(text(),'Congratulations! You have earned free shipping!')]");

	public static By PROMOTIONAL_PRICE = By.xpath("(((//div[@class='cart__content content']//div[@class='line-item__summary'])[1])//span[@class='sale'])[1]");

	//FlyCart Suggestions

	public static By SUGGESTION_FIRST_PRODUCT = By.xpath("(//div[@Class='absolute right-0 top-0 h-full w-[201px] overflow-y-scroll max-sm:hidden']//div[@class='relative aspect-tile'])[1]");
	public static By SUGGESTION_SECOND_PRODUCT = By.xpath("(//img[@data-tj-qa='plp-product-img-main'])[2]");
    public static	By QUICK_SHOP_ATC = By.xpath("(//span[@class='icon icon-plus'])[1]");
	public static By QUICK_SHOP_SIZE_OVERLAY = By.xpath("(//form[@class='mt-0 sm:space-y-lg'])[1]");
	public static By SUGGESTION_FIRST_PRODUCT_SIZE = By.xpath("(//label[@class='inline-quick-shop__button'])[1]");
	public static By SUGGESTION_SECOND_PRODUCT_SIZE = By.xpath("(//label[@class='inline-quick-shop__button'])[1]");
	public static By SUGGESTION_FIRST_PRODUCT_NAME = By.xpath("//div[@class='mb-xs block text-body-md text-text-secondary']");
	public static By SUGGESTION_PRODUCTS = By.xpath("//div[@class='product-image-container product-image-container--has-quickshop']");
	public static By SUGGESTION_SECOND_PRODUCT_NAME = By.xpath("(//span[@class='product-meta__title'])[2]");
	public static By INLINECART_PRODUCT_NAME = By.xpath("//a[@class='font-medium text-text-primary mt-1 block text-body-lg leading-tight sm:text-body sm:leading-5']");
	public static By COLOR_SWATCH = By.xpath("(//div[@class='product-item__swatch product-item__swatch--circle js-inline-quickshop-color active'])[1]");
	public static By INLINE_COLOR = By.xpath("//div[@class='line-item__option']");



	public static By QUICK_SHOP_SIZE_OVERLAY_CLOSE = By.xpath("//span[@class='icon size-5 icon-x']");
	public static By QUICK_SHOP_SIZE_OVERLAY_HEADING = By.xpath("//div[@class='mb-xs block text-body-md text-text-secondary']");
	public static By QUICK_SHOP_SIZE_LIST_1 = By.cssSelector("[data-tj-qa='plp-quickshop-instock'],.quickshop-open>.inline-quick-shop>ul:nth-child(4)>li");
	public static By QUICK_SHOP_SIZE_LIST_2 = By.cssSelector(".quickshop-open>.inline-quick-shop>ul:nth-child(6)>li");
	public static By QUICK_SHOP_SIZE_LABEL = By.xpath("(//span[@class='pr-2xs text-text-tertiary'])[2]");
	public static By QUICK_SHOP_SIZE_OVERLAY_ATC = By.cssSelector(".inline-quick-shop>.inline-quick-shop__button");
	public static By PRODUCT_TITLE_ON_COLLECTION = By.xpath("(//a[@data-cy='productTileLink'])");
	public static By PRODUCT_ITEM_SWATCH_CIRCLE = By.cssSelector("[data-tj-qa='product-swatch'],.product-item__swatch-wrapper>div");
	public static By PRODUCT_AMOUNT_ON_Collection = By.cssSelector("[data-tj-qa='product-price'],.product-meta__price");
	public static By PRODUCT_TITLE_ON_FLYCART = By.cssSelector("[data-tj-qa='cart-line-item-title'],div.inline-cart__col1 > div:nth-child(6) > .line-item >.line-item__summary>a");
	public static By PRODUCT_LINE_ITEM_OPTION = By.cssSelector("[data-tj-qa='cart-line-item-option'],div.line-item__summary > div.line-item__option");
	public static By PRODUCT_AMOUNT_ON_FLYCART = By.cssSelector("[data-tj-qa='cart-line-item-sale-price'],.line-item__price>span");



}