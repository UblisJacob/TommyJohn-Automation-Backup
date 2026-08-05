package com.tommyjohn.automation.PageLocators;

import org.openqa.selenium.By;

public class ShopifyLocators {

    public static By EMAIL_INPUT = By.xpath("//input[@class='next-input email-typo-input']");
    public static By CONTINUE_EMAIL_CTA = By.xpath("//button[contains(.,'Continue with email')]");
    public static By GET_STARTED = By.xpath("//a[@class='ui-button ui-button--link arrow-link']");
    public static By LOGIN_USING_DIFFERENT_METHOD =
            By.xpath("//*[contains(text(),'Log in using a different method')]");


    public static By CONTINUE_WITH_PASSWORD =
            By.xpath("//a[contains(@id,'login_alternative_password_auth') or contains(text(),'password')]");


    public static By PASSWORD_INPUT =
            By.xpath("//input[@type='password']");


    public static By SHOPIFY_LOGIN_BUTTON =
            By.xpath("//button[@type='submit']");


}
