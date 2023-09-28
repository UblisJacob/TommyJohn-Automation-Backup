package com.tommyjohn.automation.PageLocators;

import org.openqa.selenium.By;

public class QuizPageLocators 
{

	//Mens Quiz page
	public static By GET_STARTED_CTA = By.cssSelector(".hero-cta-container>button.hero-get-started-cta");
	public static By INSTRUCTION_HEADING = By.cssSelector(".question-container>p.instruction");
	public static By QUESTION_HEADING = By.cssSelector("[data-tj-qa='quiz-question'], .question-container>p.heading");
	public static By QUESTION_BLOCK_OPTIONS = By.cssSelector("[data-tj-qa='quiz-answer-option'],.questions-tile-parent>.questions-tile>.question-block");
	public static By NEXT_CTA = By.cssSelector("[data-tj-qa='quiz-next'],div.go-next>button");
	public static By PREVIOUS_ARROW = By.cssSelector("[data-tj-qa='quiz-back'],.go-back>img");
	public static By EDUCATION_CARD_TITLE = By.cssSelector("[data-tj-qa='quiz-education-title'],.card-title-wrapper>p.education-card-title");
	public static By SUMMARY_CARD_HEADING = By.cssSelector("[data-tj-qa='quiz-summary-title'],.summary-card-block>p.summary-card-heading");
	public static By SUMMARY_CARD_SUBHEADING = By.cssSelector("[data-tj-qa='quiz-summary-subtitle'],.summary-card-sub-heading");
	public static By SUMMARY_ANSWER = By.cssSelector("[data-tj-qa='quiz-summary-answer'],.summary-answer");
	public static By SEE_RESULT_CTA = By.cssSelector("[data-tj-qa='quiz-next'],.go-next>button.go-next-button");
	
	public static By QUIZ_HERO_WELCOME_TITLE = By.cssSelector("p[class='hero-welcome-title']");
	public static By QUIZ_HERO_WELCOME_SUBTITLE = By.cssSelector("p[class='hero-welcome-subtitle']");
	public static By QUIZ_ANSWER_HERO_TITLE = By.cssSelector("[data-tj-qa='quiz-result-hero-title']>p,.quiz-answers-hero__title>p");
	public static By RETAKE_QUIZ_CTA = By.cssSelector("[data-tj-qa='quiz-retake-link'],.quiz-answers-hero__content>a>button.quiz-answers-hero__text-link");
	public static By QUIZ_RESULT_PRODUCT_IMAGE= By.cssSelector("[data-tj-qa='product-image']>a,.product-image-container>a");
	public static By PREFERENCES_SUMMARY_ANSWER = By.cssSelector("div.summary-card-block > p:nth-child(5)");
	public static By RESULT_PAGE_SELECTED_VALUES = By.cssSelector("p.inline-section-banner__quiz-you-selected-value>span:nth-child(2)");
	
	//Bra
	public static By CUSTOME_INPUT_FIELD = By.cssSelector(".questions-tile-parent>.questions-tile>.custom-input-wrapper>div>input");
	public static By BRA_RESULT_PAGE_SELECTEDVALUES = By.cssSelector("#bra_row_0 > div.inline-section-banner--container > div > div > p.inline-section-banner__quiz-you-selected-value");
    public static By RESULT_UNAVAILABLE_MESSAGE = By.cssSelector("div.results-unavailable-title");
    public static By NO_PREFERENCE_OPTION= By.cssSelector(".questions-tile-parent>.questions-tile>.no-preference");
}
