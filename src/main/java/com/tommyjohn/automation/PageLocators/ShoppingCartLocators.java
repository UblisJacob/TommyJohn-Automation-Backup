package com.tommyjohn.automation.PageLocators;

import org.openqa.selenium.By;

public class ShoppingCartLocators {
	
//	List of elements which are not mapped with data-tj-qa attributes
	/**
	 * GIFT_WRAP_LINK
	 * All AfterPay Related
	 * PROMO_SUCCESSFUL_MESSAGE
	 * SALE_PRICE
	 * DISCOUNT_ICON
	 * APPLIED_PROMO_CODE
	 * APPLIED_PROMOCODE_FLYCART
	 * DISCOUNTED_GIFTWRAP_PRICE
	 * PROMOTIONAL_MESSAGE_CARTPAGE
	 * TIERED_METER_TIER
	 * All Quick Payment CTA's
	 * CANCEL_PROMO_CODE
	 */

	public static By CART_TITLE = By.xpath("//h1[contains(text(),'Your Cart')]");
	public static By EMPTY_CART_MESSAGE = By.xpath("//p[contains(text(),'Your cart is currently empty.')]");
	public static By START_SHOPPING_BUTTON = By.xpath("//a[@class='button mt-6']");
	public static By LIST_OF_ITEMS_IN_SHOPPING_CART = By.cssSelector(".cart__items div[data-tj-qa='cart-line-item-image'], .cart__outer>div:nth-child(1)>div>.line-item");
	public static By REMOVE_PRODUCT_FROM_CART = By.xpath("//button[contains(text(),'Remove')]");
	
	public static By PRODUCT_IMAGE = By.cssSelector(".cart__items div[data-tj-qa='cart-line-item-image']>img, .main-content > .cart.container > div.cart__content.content > div.cart__outer > .cart__items > div > article.line-item > .line-item__image > img");
	public static By PRODUCT_TITLE = By.xpath("//a[@class='sm:text-heading-sm']");
	public static By PRODUCT_TITLE_TEXT = By.xpath("//div[@class='mt-2 text-body text-text-secondary sm:mt-4']");
	public static By PRODUCT_TITLE_PDP = By.xpath("//h1[@class='mb-0']");
	public static By PRODUCT_COLOR_SIZE = By.cssSelector(".cart__items div[data-tj-qa='cart-line-item-option'], .main-content > .cart.container > div.cart__content.content > div.cart__outer > .cart__items > div > article.line-item > .line-item__summary > div");
	public static By PRODUCT_PRICE = By.cssSelector(".cart__items [data-tj-qa='cart-line-item-sale-price'], .main-content > .cart.container > div.cart__content.content > div.cart__outer > .cart__items > div > article.line-item > .line-item__summary > div.line-item__price > span");
	public static By PRODUCT_SUBTOTAL = By.cssSelector(".cart__items div[data-tj-qa='inline-cart-line-item-subtotal']:nth-child(2)>span, .main-content > .cart.container > div.cart__content.content > div.cart__outer > .cart__items > div > article.line-item > .line-item__summary > div.line-item__total > div.line-item__subtotal >span");
	public static By LINE_ITEM_SUBTOTAL_AFTER_DISCOUNT = By.cssSelector(".cart__items span[data-tj-qa='cart-line-item-discounted-total-price']");
	public static By PRODUCT_QUANTITY = By.xpath(".cart__items input[data-tj-qa='cart-line-item-qty'], .main-content > .cart.container > div.cart__content.content > div.cart__outer > .cart__items > div > article.line-item > .line-item__summary > div.line-item__total > div > .prodQty");
//	public static By PRODUCT_QUANTITY = By.xpath("//div[@class='cart__content content']//input[@data-tj-qa='inline-cart-line-item-qty']");
	public static By LINE_ITEM_PRICE_BEFORE_DISCOUNT = By.cssSelector(".cart__items span[data-tj-qa='cart-line-item-orignal-price']");
	public static By CART_SUBTOTAL = By.cssSelector("span[data-tj-qa='cart-subtotal-price']");
	public static By SUBTOTAL_PRICE = By.cssSelector("span[data-tj-qa='cart-discounted-subtotal-price'], .cart__summary>.cart__discount-subtotal>div>span:nth-child(3)");
	
	public static By PRODUCT_QUANT_MINUS = By.cssSelector(".cart__items button[data-tj-qa='cart-line-item-minus'], .main-content > .cart.container > div.cart__content.content > div.cart__outer > .cart__items > div > article.line-item > .line-item__summary > div.line-item__total > div > button:nth-child(1)");
//	public static By PRODUCT_QUANT_MINUS = By.xpath("((//div[@class='cart__content content']//div[@class='qty-selector line-item__qty'])[1])//button[@data-tj-qa='inline-cart-line-item-minus']");
	public static By PRODUCT_QUANT_ADD = By.xpath("//span[@class='icon size-5 icon-plus']");
//	public static By PRODUCT_QUANT_ADD = By.xpath("((//div[@class='cart__content content']//div[@class='qty-selector line-item__qty'])[1])//button[@data-tj-qa='inline-cart-line-item-plus']");
	
	
	public static By CART_TOTAL_TEXT = By.cssSelector("span[data-tj-qa='cart-estimated-total-title'], section > div.cart__content.content > div.cart__outer > div.cart__summary > div.cart__summary-inner-div > div:nth-child(2) > div > span:nth-child(2)");
	public static By CART_TOTAL_TEXT_DISCOUNTED = By.cssSelector("div[data-discounted-subtotal-div] span[title='Estimated Total']");
	public static By ESTIMATED_TOTAL_SUBTITLE_ORIGINAL = By.cssSelector("div[data-original-subtotal-div]>small[data-tj-qa='cart-estimated-total-subtitle']");
	public static By ESTIMATED_TOTAL_SUBTITLE_DISCOUNTED = By.cssSelector("div[data-discounted-subtotal-div]>small[data-tj-qa='cart-estimated-total-subtitle']");
	
	public static By CART_TOTAL = By.cssSelector("div[data-original-subtotal-div] span[data-tj-qa='cart-estimated-total-price'], .main-content > .cart.container > div.cart__content.content > div.cart__outer > .cart__summary >.cart__summary-inner-div> .inline-cart__summary:nth-child(2) > div > span:nth-child(2)");
	public static By ESTIMATE_TOTAL_PRICE = By.cssSelector("div[data-original-subtotal-div] span[data-tj-qa='cart-estimated-total-price'], .inline-cart__subtotal>#total_price_money");
	public static By ESTIMATE_TOTAL_PRICE_ORIGINAL = By.cssSelector("div[data-original-subtotal-div] span[data-tj-qa='cart-estimated-total-price'], .inline-cart__subtotal>#total_price_money");
	public static By ESTIMATE_TOTAL_PRICE_DISCOUTED = By.cssSelector("div[data-discounted-subtotal-div] span[data-tj-qa='cart-estimated-total-price']");
	//public static By CHECKOUT_BUTTON = By.cssSelector(".main-content > .cart.container > div.cart__outer > .cart__summary > a");
	public static By CONTINUE_SHOPPING = By.cssSelector("a[data-tj-qa='cart-continue-shopping'], section > div.cart__content.content > div.cart__outer > div.cart__summary > div.cart__summary-inner-div > a.cart__continue.text-link");
	public static By GIFT_WRAP_LINK = By.xpath("//button[@class='button-link text-body']");
	//public static By CHECKOUT_BUTTON = By.cssSelector(".main-content > .cart.container > div.cart__outer > .cart__summary > div.cart__summary-inner-div>a.cart__checkout-btn");
	public static By CHECKOUT_BUTTON = By.cssSelector("a[data-tj-qa='cart-checkout-cta'], a.cart__checkout-btn");
	
	//AfterPay
	    public static By AFTERPAY_CART = By.cssSelector(".afterpay-cart-container >p> span");
    public static By AFTERPAY_DIV = By.cssSelector("#afterpay-messaging-widget-cart-original");
	public static By AFTERPAYTEXT_WITH_AVAILABLE = By.cssSelector(".inline-cart__summary>.afterpay-cart-container>.afterpay-paragraph>span");
	public static By AFTERPAYTEXT_WITH_INSTALLMENT = By.cssSelector(".inline-cart__summary>.afterpay-cart-container>p");
	public static By AFTERPAYLOGO_WITH_AVAILABLE_MSG = By.cssSelector(".inline-cart__summary>span>p:nth-child(2)>.afterpay-link");
	public static By AFTERPAYLOGO_WITH_INSTALLMENT_MSG = By.cssSelector(".afterpay-cart-container>p>p>a.afterpay-link");
	
	//Promo code section
	public static By PROMO_HEADER = By.cssSelector("span[data-tj-qa='cart-discount-code-header'], .inline-cart__promo-header");
	public static By DISCOUNTCODE_TEXTBOX = By.cssSelector("input[data-tj-qa='cart-promo-input'], cart-coupon");
	public static By PROMOCODE_APPLYCTA = By.cssSelector("button[data-tj-qa='cart-discount-apply-cta'], .inline-cart__promo-input-container>button");
	public static By PROMO_ERROR_MESSAGE = By.cssSelector("span[data-tj-qa='cart-promo-error-message'], .inline-cart__promo-error-message");
	public static By PROMO_SUCCESSFUL_MESSAGE = By.cssSelector(".inline-cart__promo-successful-message");
	public static By SALE_PRICE = By.cssSelector("div.cart__items > div > article:not(.line-item--hidden) > div.line-item__summary > div:nth-child(4) > span.sale");
	public static By DISCOUNT_ICON = By.cssSelector("div.cart__items > div > article > div.line-item__summary > div:nth-child(4) > .icon-svg");
	public static By APPLIED_PROMO_CODE = By.cssSelector("div.cart__items > div > article:not(.line-item--hidden)> div.line-item__summary > div:nth-child(4) > .coupon-code");
	public static By APPLIED_PROMOCODE_FLYCART = By.cssSelector("span.coupon-code"); 
	public static By DISCOUNTED_GIFTWRAP_PRICE = By.cssSelector("div.cart__outer > div.cart__summary > div.cart__summary-inner-div > div.cart__gift-wrap > span:nth-child(3)");
	public static By PROMOTIONAL_MESSAGE = By.xpath("//div[@class='cart__content content']//div[contains(@class,'line-item__message')]");
	public static By PROMOTIONAL_MESSAGE_CARTPAGE = By.xpath("");
	public static By PROMOTIONAL_MESSAGE_FLYCART = By.xpath("div[data-tj-qa='inline-cart-promo-message']");
//	public static By PROMOTIONAL_PRICE = By.xpath("(((//div[@class='cart__content content']//div[@class='line-item__summary'])[1])//span[@class='sale'])[1]");
	public static By PROMOTIONAL_PRICE = By.xpath(".cart__items span[data-tj-qa='cart-line-item-sale-price']");
	
	//Tiered-meter
	public static By TIERED_METER_TITLE = By.xpath("//p[@class='text-body']");
	public static By TIERED_METER = By.xpath("//div[@class='relative h-0.5 bg-surface-secondary']");
	public static By TIERED_METER_TIER= By.xpath("//div[@class='h-full bg-surface-hover']");
	//OOS
	public static By OOS_TAG = By.xpath("//div[@class='cart__content content']//span[@data-tj-qa='inline-cart-line-item-oos-message']");
	public static By OOS_TAG_FLYCART= By.xpath(".inline-cart.active span[data-tj-qa='inline-cart-line-item-oos-message'], .inline-cart.active span[data-tj-qa='inline-cart-line-item-oos-message']");
	public static By OOS_TAG_CARTPAGE = By.xpath("span[data-tj-qa='inline-cart-line-item-oos-message'], .cart__items .line-item__oos-msg");
//	public static By OOS_MESSAGE = By.xpath("//div[@class='cart__content content']//span[@data-tj-qa='inline-cart-oos-message']");
	public static By OOS_MESSAGE = By.cssSelector(".cart__items>span[data-tj-qa='inline-cart-oos-message'], .cart__items>.line-item__oos-main-msg");
	
    // PAYMENT
	public static By PAYPAL_BUTTON = By.cssSelector("div[aria-label='PayPal']");
	public static By GPAY_BUTTON = By.xpath("//div[@data-testid = 'GooglePay-button']");
	public static By AMZ_PAY = By.xpath("//form[@action = 'https://payments.amazon.com/checkout/signin'] /button" );
	public static By SHOP_PAY = By.xpath("//div[@data-testid='ShopifyPay-button']" );
	
	
	public static By CANCEL_PROMO_CODE = By.cssSelector(".tag__wrapper>button");
}
