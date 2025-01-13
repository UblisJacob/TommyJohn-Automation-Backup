package com.tommyjohn.automation.PageLocators;

import org.openqa.selenium.By;

public class ProductDetailsPageLocators {


	//Multipacks Sections
	public static By PDP_TABS = By.xpath("//span[@class='text-sm text-text-tertiary']");
	public static By PDP_TAB1 = By.xpath("(//span[@class='text-sm text-text-tertiary'])[1]");
	public static By SWATCH_SECTION = By.xpath("(//fieldset[@class='grid max-md:ml-lg max-md:items-center'])[1]");
	public static By SIZE_SECTION = By.xpath("(//fieldset[@class='grid max-md:ml-lg max-md:items-center'])[2]");
	public static By QUANTITY_ATC_SECTION = By.xpath("//div[@class='mt-3 flex gap-2']");
	public static By PDP_TAB2 = By.xpath("(//span[@class='text-sm text-text-tertiary'])[2]");
	public static By PDP_TAB3 = By.xpath("(//span[@class='text-sm text-text-tertiary'])[3]");



	public static By FIRST_PRODUCT_REVIEW_STARS = By.cssSelector("div.mega-collection-grid__row:nth-child(3) > div:nth-child(3) > a > div");
	//changed
	public static By PRODUCT_TITLE = By.xpath("//h1[@class='mb-0']");
 	
	//changed
	public static By PRODUCT_TITLE_1 = By.xpath("//div[@class='multi-pdp-title-wrap desktop-view desktop']//h1");//changed
	//	public static By PRODUCT_PRICE = By.cssSelector("div.product-info__inner-top > span > span:nth-child(2)");
	public static By PRODUCT_PRICE = By.xpath("//span[@data-cy='priceFinal']");
	
	// image carousels
	public static By IMG_CAROUSEL = By.xpath("//div[@class='hidden md:block w-full']//div");

	public static By COLOR_VARIANT = By.xpath("//div[@class='space-y-lg']//span[@class='sr-only']");

	public static By SIZE_VARIANT = By.xpath("//div[@class='space-y-lg']//span[@class='sr-only']");
	
	//first product on PDP
//	public static By FIRST_PRODUCT = By.cssSelector(".product-item");
	public static By FIRST_PRODUCT = By.xpath("(//div[@class='relative aspect-tile'])[1]");
	public static By FIRST_PRODUCT_HEAD_LINE_TEXT = By.xpath("(//a[@data-cy='productTileLink'])[1]");

	public static By THIRD_PRODUCT_HEAD_LINE_TEXT = By.xpath("(//span[@class='product-meta__title'])[2]");
		
	//image badging
//	public static By IMAGE_BADGING = By.cssSelector(".productImage-item__badge");

	public static By IMAGE_BADGING = By.xpath("//span[contains(text(),'New Colors')]");
	
	//Secondary Badging
	public static By SECONDARY_BADGING = By.xpath("//p[@class='m-0 inline-block text-body-xs leading-none text-[color:var(--text-color)]']");
	
	//image color
	public static By IMAGE_COLOR = By.cssSelector(".product-gallery.pdp-client-side-container.product-gallery-container>div>div:nth-child(2)>div>div>div>div.slick-active");
	public static By STRIKED_COLOR_SWATCHES = By.cssSelector(".unavailable[data-tj-qa='color-swatch'],.product-option__color-swatches-wrapper>ul>li>.product-option__variant-btn.product-option__variant-btn--color.js-variant--color.unavailable.email-when-in-stock");
	public static By AFTERPAY_DIV = By.cssSelector(".multi-pdp-title-wrap.desktop-view>span>.afterpay-paragraph");
	public static By SECOND_VERTICAL_IMAGE = By.cssSelector(".slick-vertical > div > div > div:nth-child(2)>img");

	public static By COLOR_LISTS = By.cssSelector("[data-tj-qa='color-swatch'],.product-option__color-swatches-wrapper > ul >li");
	public static By FIRST_COLOR = By.cssSelector(".product-option__color-swatches-wrapper > ul > li:nth-child(1) > label");

	public static By TOTAL_SIZES = By.xpath("//input[@name='option[Size]']");
	public static By INACTIVE_SIZES = By.cssSelector(".unavailable[data-tj-qa='product-size'],.option-swatch__option>li>.option-swatch__input.unavailable.email-when-in-stock");
	public static By SIZE_VALUE = By.xpath("(//span[@class='font-medium text-text-primary'])[2]");


	//color swatches
	public static By COLOR_SWATCHES = By.cssSelector("[data-tj-qa='color-swatch'],.product-option__color-swatches-wrapper>ul>li");
	public static By SECOND_COLOR = By.cssSelector(".product-option__color-swatches-wrapper > ul > li:nth-child(2) > label");
	public static By THIRD_COLOR = By.cssSelector(".product-option__color-swatches-wrapper > ul > li:nth-child(3) > label");
	public static By COLOR_TEXT = By.xpath("(//span[@class='font-medium text-text-primary'])[1]");       //("//div[@class= 'multi-pdp-info__header'] /span]");
	// ATC
	
	
	
	
	//public static By SIZE_TEXT = By.cssSelector(".product-info__header > span:nth-child(2)");

	public static By SIZE_DROPDOWN = By.cssSelector(".option-dropdown__option >div>a");
	//public static By ALL_SIZE_LIST = By.cssSelector("[data-tj-qa='product-size'],.option-swatch__option >li");
	public static By ALL_SIZE_LIST = By.xpath("//input[@name='option[Size]']");
	public static By SIZE_DROPDOWN_MENS = By.cssSelector(".option-dropdown__option >div[data-option-name = 'Mens Size'] >a");
	public static By SIZE_DROPDOWN_WOMENS = By.cssSelector(".option-dropdown__option >div[data-option-name = 'Womens Size'] >a");
	public static By SIZE_TEXT = By.xpath("//input[@class='sizeOption' and @checked='checked']");
	public static By BRA_BAND_SIZE_DROPDOWN = By.xpath("//select[@id='column-select']");
	public static By BRA_BAND_SIZE_SELECT = By.cssSelector("[data-tj-qa='find-your-size-band-size'],.size-guide-size-finder-dropdowns>span:nth-child(1)");
	public static By BAND_SIZE_TEXT = By.xpath("//label[@for='row-select']");
	public static By CUP_VOLUME_TEXT = By.cssSelector(".size-guide-size-finder-y-axis-title");
	public static By BRA_CUP_VOLUME_DROPDOWN = By.cssSelector("[data-tj-qa='find-your-size-cup-size'],.size-guide-size-finder-dropdowns>span:nth-child(2)>select");
	public static By FIND_SIZE_BUTTON = By.cssSelector("[data-tj-qa='find-your-size-calculator']>button,.calculate-size-button>button.find-size-button");
	public static By CALCULATED_SIZE_RESULT = By.cssSelector("[data-tj-qa='size-result'],.calculate-size-results");
	//public static By ALL_SIZE_LIST = By.cssSelector(".product-option__variants.product-option__variants-size > ul > li");


	public static By ALL_COLOR_LIST = By.xpath("//div[@class='product-option__color-swatches-wrapper']//li");
	public static By PRODUCT_DETAILS = By.xpath("//button[@aria-controls='product-details']");
	public static By SIZEGUIDE = By.xpath("//button[@class='button-link text-body-sm text-text-tertiary mr-6 md:mr-0']");
	public static By SIZEGUIDE_FITRANGE = By.xpath("//ul[@data-tj-qa='size-guide-fit-range-graph']//li");
	public static By SIZEGUIDE_FITRANGE_DROPDOWN= By.cssSelector(".expand-instructions");
	public static By SIZEGUIDE_FITRANGE_TEXT= By.cssSelector(".instructions-text");
	public static By SIZEGUIDE_CHART_TABLE= By.xpath("//table[@class='not-prose w-full whitespace-nowrap text-center align-baseline text-body-sm leading-6']");
	
	public static By SIZEGUIDE_TITLE= By.cssSelector("[data-tj-qa='size-guide-title']");
	public static By SIZE_GUIDE_BUTTON = By.cssSelector("[data-tj-qa='size-guide-link'],button.product-info__header-btn.size-guide");
	public static By ADD_TO_CART_BUTTON = By.xpath("//button[@id='ProductForm-Add']");

	public static By JOIN_THE_WAITLIST = By.cssSelector("[data-tj-qa='add-to-cart-cta'],button.product-info__atc.button.button--red.button--full.js-loop-atc.email-when-in-stock");
	//public static By QUANTITY = By.cssSelector(".qty-selector.desktop>#updatedQty.pdp.prodQty");
	public static By QUANTITY = By.cssSelector("[data-tj-qa='add-to-cart-cta'],button.product-info__atc");
	public static By PLUS_BUTTON = By.cssSelector("[data-tj-qa='quantity-plus'],.pdp.plusBtn");
	public static By UPDATED_QUANTITY = By.cssSelector("[data-tj-qa='product-quantity'],.pdp.updatedQty");
	public static By MINUS_BUTTON = By.cssSelector("[data-tj-qa='quantity-minus'],.pdp.minusBtn");
	public static By BEST_PAIR_GUARANTEE = By.cssSelector("[data-tj-qa='mobile-footer-accordion-best-pair-guarantee'],.product-details-container>div> ul > li:nth-child(2) > div:nth-child(1) > a");
	public static By FREE_RETURNS = By.cssSelector("[data-tj-qa='mobile-footer-accordion-free-returns'],.product-details-container>div> ul > li:nth-child(3) > div:nth-child(1) > a");
	public static By FIT_MATERIAL = By.xpath("//button[@aria-controls='product-fit-materials']");
	public static By CHECKOUT_BUTTON = By.cssSelector("[data-tj-qa='inline-cart-checkout'],.inline-cart__action-btns > a");
	//public static By COLOR_AND_SIZE = By.cssSelector("div.product-info__variant-select > div:nth-child(1) > div.product-info__header.hide > span");
    public static By LOW_INVENTORY_MSG = By.xpath("//p[@class='my-2 px-lg md:px-0']");
    public static By PRODUCT_DETAILS_SECTION = By.xpath("(//dt[@class='font-medium'])[1]");
    public static By BEST_PAIR_GUARANTEE_SECTION = By.cssSelector("div.product-info_low-inventory-message");
    public static By FREE_RETURNS_SECTION = By.cssSelector("div.product-info_low-inventory-message");
	public static By FIT_MATERIAL_SECTION = By.xpath("//div[@id='product-fit-materials']");
	public static By CART_ICON= By.xpath("//button[@data-tj-qa='header-cart-icon']");
	public static By OVERLAY= By.xpath("//div[@class='overlay active']");

	
	//afterpay

	public static By AFTERPAY_PDP = By.cssSelector("#afterpay-messaging-widget-product-multi , #afterpay-messaging-widget-product");
	public static By AFTERPAY_PDP_AFTERMESSAGE_LOGO = By.cssSelector("afterpay-logo-link>button");
	public static By AFTERPAY_PDP_BEFOREMESSAGE_LOGO = By.cssSelector("div.desktop-view > .afterpay-pdp-container > p> img ");
	
	//atc
	
	public static By EMAIL_WHEN_IN_STOCK_BUTTON = By.cssSelector("[data-tj-qa='join-the-waitlist-title'],.jtwl_header>span");
	public static By PAIR_GUARANTEE_LINK = By.cssSelector(".product-info__inner-bottom > div:nth-child(1) > button");
	public static By SHIPING_AND_RETURNS_LINK = By.cssSelector(".product-info__inner-bottom > div:nth-child(2) > button");
	public static By JOIN_THE_WAITLIST_TEXT = By.cssSelector("[data-tj-qa='add-to-cart-cta'],.multiPdp__atc-container>button");
	public static By INLINE_CART_TITLE = By.xpath("//h2[@class='text-heading-lg font-medium leading-none sm:text-heading']");
	public static By EMAIL_WHEN_IN_STOCK_BUTTON_CLOSE = By.cssSelector(".close_jtwl");
	public static By INLINE_CART_CLOSE = By.xpath("//button[@aria-label='Close cart']");

	//RECS
	public static By SHOP_THE_COLLECTION = By.xpath("(//swiper-container[@class='-mx-lg flex px-lg [grid-area:slides]'])[1]");
	public static By PEOPLE_ALSO_BOUGHT = By.xpath("(//swiper-container[@class='-mx-lg flex px-lg [grid-area:slides]'])[2]");
	public static By RECENTLY_VIEWED = By.xpath("(//swiper-container[@class='-mx-lg flex px-lg [grid-area:slides]'])[3]");



	//Reviews section


	public static By RATING_STARS = By.xpath("(//span[@class='flex items-center font-normal text-text-secondary'])[1]");
	public static By RATING_NUMBER = By.xpath("//div[@class='yotpo-bottom-line-left-panel yotpo-bottom-line-score']");
	public static By RATING_AND_REVIEWS_BOX = By.xpath("//div[@class='yotpo-header-container']");
	public static By RATING_AND_REVIEWS_FILTER = By.cssSelector(".yotpo-reviews-widget >div> div.yotpo-review-bottomline-container >div:nth-child(2)>ul>li");
	public static By RATING_AND_REVIEWS_FILTER_RATING_COUNT = By.cssSelector(".yotpo-reviews-widget >div> div.yotpo-review-bottomline-container >div:nth-child(2)>ul>li>div>div:nth-child(3)");
	public static By NO_RATING_TEXT = By.cssSelector("[data-tj-qa='product-fit'],div.multi-pdp-title-wrap.desktop-view > div.product-info__price-wrap > div.yotpo-star-wrapper > div > div > span:nth-child(1)");
	public static By RATING_AND_REVIEWS_TOTAL = By.cssSelector(".review-searched-items-wrapper.mobile-hide>span:nth-child(2)");
	
	
	public static By FIRST_BOX_IN_RATING_AND_REVIEWS = By.xpath("//div[@class='yotpo-summary-section']");
	public static By STARS_IN_FIRST_BOX = By.cssSelector(".yotpo-reviews-widget >div> div.yotpo-review-bottomline-container >div > div.yotpo-review-bottomline-rating > .yotpo-stars");
	public static By AVG_RATING_IN_FIRST_BOX = By.xpath("//div[@class='yotpo-bottom-line-left-panel yotpo-bottom-line-score']");
	public static By TOTAL_REVIEWS_IN_FIRST_BOX = By.xpath("//div[@class='yotpo-bottom-line-basic-text']");

	public static By SECOND_BOX_IN_RATING_AND_REVIEWS = By.xpath("//div[@class='yotpo-star-distribution']");
	public static By RATING_LABEL_IN_SECOND_BOX = By.cssSelector(".yotpo-reviews-widget >div> div.yotpo-review-bottomline-container >div:nth-child(2) > ul.yotpo-revew-bottomline-list > li > .yotpo-review-bottomline-individual-ratings > .yotpo-review-bottomline-ratings-label");
	public static By RATING_METER_IN_SECOND_BOX = By.cssSelector(".yotpo-reviews-widget >div> div.yotpo-review-bottomline-container >div:nth-child(2) > ul.yotpo-revew-bottomline-list > li:nth-child(3) > .yotpo-review-bottomline-individual-ratings > .yotpo-review-bottomline-individual-ratings__meter");
	public static By RATING_COUNT_IN_SECOND_BOX = By.cssSelector(".yotpo-reviews-widget >div> div.yotpo-review-bottomline-container >div:nth-child(2) > ul.yotpo-revew-bottomline-list > li:nth-child(5) > .yotpo-review-bottomline-individual-ratings > .yotpo-review-bottomline-individual-ratings__count");
	
	public static By THIRD_BOX_IN_RATING_AND_REVIEWS = By.xpath("//div[@class='yotpo-new-review-btn-wrapper']");
	public static By FIT_RANGE = By.xpath("(//div[contains(text(),'fit')])[1]");
	public static By FIT_GRAPH_IN_THIRD_BOX = By.cssSelector(".yotpo-reviews-widget >div> div.yotpo-review-bottomline-container >div > div.yotpo-review-bottomline-customer-say-container > .size-guide-yatpo-fit-graph > ul > li.selected");
	public static By FIT_STATUS_IN_THIRD_BOX = By.cssSelector(".yotpo-reviews-widget >div> div.yotpo-review-bottomline-container >div > div.yotpo-review-bottomline-customer-say-container > .yotpo-review-bottomline-customer-say > .yotpo-review-bottomline-fit");
	public static By POSTED_REVIEWS_SECTION = By.cssSelector(".yotpo-review-box");
	public static By PAGINATION_LEFT_ARROW = By.cssSelector(".yotpo-paginator__arrow.arrow-left");
	public static By PAGINATION_RIGHT_ARROW = By.cssSelector(".yotpo-paginator__arrow.arrow-right");
	public static By PAGINATION_CURRENT_VALUE = By.cssSelector(".yotpo-paginator__current-page");
	
	public static By STAR_RATINGS = By.cssSelector(".yotpo-stars-and-sum-reviews > span > span.sr-only");
	//filter ratings
	public static By FILTER_RATINGS = By.xpath("//input[@class='yotpo-filter-select']");
	public static By FILTER_FIT = By.cssSelector("[data-tj-qa='pdp-fit-dropdown'],.yotpo-reviews-qa-header.filters>div:nth-child(3)");
	public static By FILTER_SORTBY = By.xpath("(//div[@class='yotpo-selected-value'])[2]");
	public static By FILTER_SORTBY_SECTION = By.xpath("//span[@class='yotpo-dropdown-option']");
	
	public static By QUESTION_TAB = By.xpath("//div[@class='ugc-storefront-widgets-tabs-container__tab']");
	public static By QUESTION_FIRST = By.xpath("(//span[@class='yotpo-shopper-name'])[1]");
	public static By ASK_QUESTION = By.cssSelector(".yotpo-ctas>div:nth-child(1)");
	public static By RESPONSE_FROM_TOMMYJOHN_LIST = By.cssSelector("[data-tj-qa='pdp-customer-review'],.yotpo.yotpo-reviews-container.yotpo-reviews-widget>ul>li");
	public static By RESPONSE_FROM_TOMMYJOHN = By.cssSelector("p.yotpo-review-response__header");
	

	
	public static By WRITE_REVIEW_BUTTON = By.xpath("//button[@class='yotpo-new-review-btn yotpo-btn-s']");
	public static By WRITE_REVIEW_HEADING = By.cssSelector("#pdpReviews > div > div > form > div");
	public static By WRITE_REVIEW_HEADING1 = By.cssSelector(".y-label.yotpo-header-title");
	public static By REVIEW_STARS_IN_RATING_AND_REVIEWS = By.cssSelector(".yotpo-bottomline-box-1 > span:nth-child(1)");
	public static By REVIEWS_TITLE = By.cssSelector("[data-tj-qa='pdp-reviews-title'],.review-title");
	
	public static By REVIEWER_NAME = By.cssSelector("[data-tj-qa='pdp-buyer-info-username'],.yotpo-reviews-widget > ul.review-list > li.review-item> .yotpo-review-box > div.yotpo-review-buyer-info > .reviewer-name-type > .reviewer-user-name");
	public static By REVIEWER_TYPE = By.cssSelector("[data-tj-qa='pdp-buyer-info-user-type'],.yotpo-reviews-widget > ul.review-list > li.review-item> .yotpo-review-box > div.yotpo-review-buyer-info > .reviewer-name-type > .reviewer-user-type");
	public static By USER_REVIEW_FIT = By.cssSelector("[data-tj-qa='pdp-buyer-info-fit'],.yotpo-reviews-widget > ul.review-list > li.review-item> .yotpo-review-box > div.yotpo-review-buyer-info > p:nth-child(3)");
	public static By USER_REVIEW_DATE = By.cssSelector("[data-tj-qa='pdp-buyer-info-date'],.yotpo-reviews-widget > ul.review-list > li.review-item> .yotpo-review-box > div.yotpo-review-buyer-info > p:nth-child(2)");
	public static By USER_REVIEW_LOCATION = By.cssSelector(".yotpo-reviews-widget > ul.review-list > li.review-item> .yotpo-review-box > div.yotpo-review-buyer-info > p:nth-child(4)");
	
	public static By USER_REVIEW_STARS = By.cssSelector("[data-tj-qa='pdp-review-content-stars'],.yotpo-reviews-widget > ul.review-list > li.review-item> .yotpo-review-box > .yotpo-review-main-content >.yotpo-review-header> p>.yotpo-review-stars");
	public static By USER_REVIEW_TITLE = By.cssSelector("[data-tj-qa='pdp-review-content-title'],.yotpo-reviews-widget > ul.review-list > li.review-item> .yotpo-review-box > .yotpo-review-main-content >.yotpo-review-header >.yotpo-review-title");
	public static By USER_REVIEW_MAIN_CONTENT = By.cssSelector("[data-tj-qa='pdp-review-content-body'],.yotpo-reviews-widget > ul.review-list > li.review-item> .yotpo-review-box > .yotpo-review-main-content >.yotpo-review-content");
	public static By USER_REVIEW_WAS_THIS_HELPFUL_TEXT = By.cssSelector("[data-tj-qa='pdp-review-content-feedback-question'],.yotpo-reviews-widget > ul.review-list > li.review-item> .yotpo-review-box > .yotpo-review-main-content >.yotpo-review__feedback-wrapper > div > .yotpo-review-feedback-text");
	public static By USER_REVIEW_THUMBS_UP = By.cssSelector("[data-tj-qa='pdp-review-content-feedback-thumbs-up-icon'],.yotpo-reviews-widget > ul.review-list > li.review-item> .yotpo-review-box > .yotpo-review-main-content >.yotpo-review__feedback-wrapper > div > .yotpo-icon-vote-btn > .yotpo-icon-thumbs-up");
	public static By USER_REVIEW_THUMBS_DOWN = By.cssSelector("[data-tj-qa='pdp-review-content-feedback-thumbs-down-icon'],.yotpo-reviews-widget > ul.review-list > li.review-item> .yotpo-review-box > .yotpo-review-main-content >.yotpo-review__feedback-wrapper > div > .yotpo-icon-vote-btn > .yotpo-icon-thumbs-down");
	
	public static By NO_REVIEWS_TEXT = By.cssSelector(".yotpo-no-reviews-container > .yotpo-header");
	public static By NO_REVIEWS_SUBTEXT = By.cssSelector(".yotpo-no-reviews-container > .yotpo-subtext");
	public static By NO_REVIEW_WRITE_REVIEW = By.xpath("//button[@class='yotpo-new-review-btn yotpo-btn-s']");
	
	public static By WRITE_REVIEW_TITLE = By.xpath("//h4[@class='yotpo-form-label' and contains(text(),'Write a review')]");
	public static By WRITE_REVIEW_TEXTBOX = By.xpath("//textarea[@aria-label='Write a review']");
	public static By NAME_TEXTBOX = By.xpath("//input[@aria-label='Your name']");
	public static By EMAIL_TEXTBOX = By.xpath("//input[@aria-label='Your email address']");
	public static By LOCATION_TEXTBOX = By.cssSelector("[data-tj-qa='reviews-write-review-location-input'],#yotpo_input_review_location");
	public static By FIT_SELECTION = By.cssSelector("[data-tj-qa='reviews-fit-too-large-option'],label[for='yotpo_input__field_option_3']");
	public static By STAR_SELECTION = By.cssSelector("[data-tj-qa='reviews-overall-fitting-stars'],label[for='yotpo-form-input__review_score__4']");
	public static By HEADLINE_TEXTBOX = By.xpath("//input[@aria-label='Add a headline']");
	public static By REVIEW_TEXTBOX = By.cssSelector("[data-tj-qa='reviews-review-input'],#yotpo_input_review_content");
	public static By SUBMIT_BTN = By.xpath("//button[@class='yotpo-new-review-submit']");
	
	public static By NAME = By.xpath("//h4[@class='yotpo-form-label' and contains(text(),'Your name')]");
	public static By EMAIL = By.xpath("//h4[@class='yotpo-form-label' and contains(text(),'Your email address')]");
	public static By LOCATION = By.xpath("//*[data-tj-qa='reviews-write-review-location-label'] | //label[@for = 'yotpo_input_review_location']");
	public static By FIT = By.cssSelector("[data-tj-qa='reviews-fit-header'] , .yotpo-custom-tag-field > span");
	public static By HEADLINE = By.xpath("//h4[@class='yotpo-form-label' and contains(text(),'Add a headline')]");
	public static By REVIEW = By.xpath("//*[data-tj-qa='reviews-review-label'] | //label[@for = 'yotpo_input_review_content']");
	public static By OVERALLRATING = By.cssSelector("[data-tj-qa='reviews-overall-fitting-label'],.overall-rating-text");
	
	public static By NAME_ERROR = By.xpath("//input[@aria-label='Your name']//following-sibling::p");
	public static By EMAIL_ERROR = By.xpath("//input[@aria-label='Your email address']//following-sibling::p");
	public static By HEADLINE_ERROR = By.xpath("//input[@aria-label='Add a headline']//following-sibling::p");
	public static By REVIEW_ERROR = By.xpath("//textarea[@aria-label='Write a review']//following-sibling::p");
	
	public static By EMAIL_WHEN_IN_STOCK_TITLE = By.cssSelector("[data-tj-qa='join-the-waitlist-title'],.jtwl_header > span");
	public static By JOINTHEWAITLIST_REMINDER_DESC = By.cssSelector("[data-tj-qa='join-the-waitlist-subtitle'],.jtwl_subtitle_wrapper> span");
	public static By JOINTHEWAITLIST_EMAILTEXTBOX = By.cssSelector("[data-tj-qa='join-the-waitlist-email-input'],.jtwl_form__input.jtwl_form__userEmail");
	public static By JOINTHEWAITLIST_NOTIFYBUTTON = By.cssSelector("[data-tj-qa='jtwl-notify-me-cta'],.jtwl_form__submit");
	public static By JOINTHEWAITLIST_PRIVACYINFO = By.cssSelector("[data-tj-qa='jtwl-footer-section'],.jtwl_footer>span");
	public static By JOINTHEWAITLIST_CLOSEBTN = By.cssSelector(".close_jtwl");
	public static By JOINTHEWAITLIST_SUCCESS = By.cssSelector("[data-tj-qa='jtwl-success-message'],.jtwl_success>span");
	
   //Recommended for you (RFY)
	public static By RFY_TITLE = By.cssSelector(".dy-rec--title");
	public static By RFY_SECTION = By.cssSelector("div.dy-rec--products");
	public static By RFY_PRODUCT_IMAGE = By.cssSelector("[data-tj-qa='product-image'],.product-image");
	public static By RFY_HOVER_PRODUCT_IMG= By.cssSelector(".product-image--hover");
	public static By RFY_PRODUCT_TITLE = By.cssSelector("[data-tj-qa='product-title'],.product-meta__title");
	public static By RFY_PRODUCT_REVIEWS = By.xpath("//*[data-tj-qa='product-reviews'] | //a[@class='product-meta']/div/div");
	public static By RFY_PRODUCT_PRICE = By.cssSelector(".product-meta__prices");
	public static By RFY_SWATCH_LIST = By.cssSelector(".dy-recs-placeholder__above-desktop-reviews>div>.dy-rec>div>div:nth-child(2)>.dy-rec--products>div:nth-child(1) > div > div.product-item__swatches:nth-child(2)> div > div");
	public static By RFY_PRODUCT_IMG_COLORNAME = By.cssSelector(".dy-recs-placeholder__above-desktop-reviews>div>.dy-rec>div>div:nth-child(2)>.dy-rec--products>div:nth-child(1) > div > div>a");
	
	
	//Mystery packs
	public static By MYSTERPACK_PRMOMSG = By.cssSelector(".multi-pdp-title-wrap.desktop-view >.product-info_promo.show");
	
	//Quick Shop on Buy With
		public static By FIRST_PRODUCT_IMAGE_BUYWITH = By.cssSelector("[data-tj-qa='product-image'],.product-image-container");
		public static By PRODUCT_TITLE_ON_COLLECTION = By.cssSelector("[data-tj-qa='product-title'],.product-meta__title");
		public static By PRODUCT_ITEM_SWATCH_CIRCLE = By.cssSelector("[data-tj-qa='product-swatch'],.product-item__swatch-wrapper>div");
		public static By PRODUCT_AMOUNT_ON_Collection = By.cssSelector("[data-tj-qa='product-price'],.product-meta__price");
		public static By PRODUCT_TITLE_ON_FLYCART = By.cssSelector("div.inline-cart__col1 > div:nth-child(6) > .line-item >.line-item__summary>a");
	    public static By PRODUCT_LINE_ITEM_OPTION = By.cssSelector("div.line-item__summary > div.line-item__option");
	    public static By PRODUCT_AMOUNT_ON_FLYCART = By.cssSelector("[data-tj-qa='cart-line-item-sale-price'],.line-item__price>span");
	    
	    //Sort By
	    public static By SORT_BY = By.cssSelector("[data-tj-qa='pdp-sort-by-dropdown'],.yotpo-reviews-qa-header>div.yotpo-header-tab__sort-by");
	    public static By SORT_BY_OPTIONS_DIV = By.cssSelector(".yotpo-reviews-question-container>div.yotpo-sort-by-options-container.active");
	    public static By SORT_BY_OPTIONS = By.cssSelector("[data-tj-qa='pdp-sort-by-dropdown-option'],.yotpo-reviews-question-container>div.yotpo-sort-by-options-container>ul>li");
	    public static By POSTED_REVIEW_DATE= By.cssSelector("[data-tj-qa='pdp-buyer-info-date'],.yotpo-reviews-container>.review-list>li:nth-child(2)>div>div.yotpo-review-buyer-info>p:nth-child(2)");
	    public static By POSTED_REVIEW_DATE_NEXT= By.cssSelector("[data-tj-qa='pdp-buyer-info-date'],.yotpo-reviews-container>.review-list>li:nth-child(3)>div>div.yotpo-review-buyer-info>p:nth-child(2)");
        public static By REVIEW_FILTER_MESSAGE = By.cssSelector(".yotpo-bottomline__filter-message");
	    
        //Ask Questions
        public static By Ask_a_Question_CTA = By.xpath("//a[@id='yotpo-widget-btn']//span");
        public static By ASK_A_QSN_CONTAINER = By.cssSelector(".yotpo-reviews-widget>section>form");
        public static By ASK_A_QUESTION_TITLE = By.cssSelector("[data-tj-qa='ask-a-question-title'],.qna-form-title");
        public static By QSN_ANS_NAME_TEXTBOX = By.xpath("//input[@aria-label='Enter your name']");
        public static By QSN_ANS_EMAIL_TEXTBOX = By.xpath("//input[@aria-label='Enter your email address']");
        public static By QSN_ANS_QUESTION_TEXTBOX = By.xpath("//textarea[@aria-label='Type your question']");
        public static By SUBMIT_QUESTION_CTA = By.xpath("//button[@class='yotpo-question-submit']");
        public static By QSN_ANS_NAME_ERROR = By.xpath("//input[@aria-label='Enter your name']//following-sibling::h6");
        public static By QSN_ANS_EMAIL_ERROR = By.xpath("//input[@aria-label='Enter your email address']//following-sibling::h6");
        public static By QSN_ANS_QUESTION_ERROR = By.xpath("//textarea[@aria-label='Type your question']//following-sibling::h6");

        
        //Ratings
		public static By FIVESTARTIERMETER = By.xpath("(//div[@class='yotpo-progress-bar'])[1]");
		public static By FIVESTARRATING = By.xpath("(//div[@aria-label='5 star rating out of 5 stars'])[1]");
	    public static By FOURSTARTIERMETER = By.xpath("(//div[@class='yotpo-progress-bar'])[2]");
	    public static By FOURSTARRATING = By.xpath("(//div[@aria-label='4 star rating out of 5 stars'])[1]");
		public static By THREESTARTIERMETER = By.xpath("(//div[@class='yotpo-progress-bar'])[3]");
	    public static By THREESTARRATING = By.xpath("(//div[@aria-label='3 star rating out of 5 stars'])[1]");
		public static By TWOSTARTIERMETER = By.xpath("(//div[@class='yotpo-progress-bar'])[4]");
	    public static By TWOSTARRATING = By.xpath("(//div[@aria-label='2 star rating out of 5 stars'])[1]");
		public static By ONESTARTIERMETER = By.xpath("(//div[@class='yotpo-progress-bar'])[5]");
		public static By ONESTARRATING = By.xpath("(//div[@aria-label='1 star rating out of 5 stars'])[1]");
}