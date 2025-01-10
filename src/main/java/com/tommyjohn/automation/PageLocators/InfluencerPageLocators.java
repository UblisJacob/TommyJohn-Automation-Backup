package com.tommyjohn.automation.PageLocators;

import org.openqa.selenium.By;

public class InfluencerPageLocators {

    public static By FAVORITES_SECTION = By.xpath("(//swiper-container[@class='-mx-lg flex px-lg [grid-area:slides]'])[1]");
    public static By TRENDINGTHISWEEK = By.xpath("(//swiper-container[@class='-mx-lg flex px-lg [grid-area:slides]'])[2]");
    public static By SHOPBYCATEGORY = By.xpath("//div[@class='wrapper max-sm:pr-0']");

}
