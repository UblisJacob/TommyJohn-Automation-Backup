package com.tommyjohn.automation.Pages;


import com.tommyjohn.automation.Components.RetailersPageComponents;
import org.openqa.selenium.WebDriver;

import com.tommyjohn.automation.Components.ContactUsComponent;
import com.tommyjohn.automation.Components.HomePageComponents;

public class RetailersPage  {

    WebDriver driver;
    public RetailersPage(WebDriver driver) {
        this.driver=driver;

    }
    public void VerifyContactUsFlow() throws Exception
    {
        new HomePageComponents(driver).navigateToContactUsPage();
        Thread.sleep(3000);
        new ContactUsComponent(driver).ContactUsFlow();
        Thread.sleep(3000);
    }

    public void ValidateRetailersPageTitle() throws Exception
    {
        new RetailersPageComponents(driver).ValidatePageTitleInRetailersPage();

    }
}
