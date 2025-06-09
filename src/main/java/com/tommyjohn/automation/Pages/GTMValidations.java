package com.tommyjohn.automation.Pages;

import com.tommyjohn.automation.Components.CollectionPageComponent;
import com.tommyjohn.automation.Components.GTMComponents;
import com.tommyjohn.automation.commonMethods.ExcelUtils;
import com.tommyjohn.automation.commonMethods.GeneralMethods;
import org.openqa.selenium.WebDriver;

public class GTMValidations extends GTMComponents {

    GeneralMethods objGeneralMethods = new GeneralMethods();
    ExcelUtils objExcelUtils = new ExcelUtils();
    public GTMValidations(WebDriver driver) {
        super(driver);
        this.driver=driver;
    }

    public void validateGTMEventsForFredHopper() throws Exception
    {
        new GTMComponents(driver).ValidateGTMforFacetEvent();
        new GTMComponents(driver).ValidateGTMforViewCollectionEvent();
        new GTMComponents(driver).ValidateGTMForAddToCartEvent();
        new GTMComponents(driver).ValidateGTMForClickEvent();
        new GTMComponents(driver).ValidateGTMForViewProductEvent();
        new GTMComponents(driver).ValidateGTMForSearchEvent();
        new GTMComponents(driver).ValidateGTMForRemoveFromCartEvent();
    }

    public void validateSplunk() throws Exception
    {
        new GTMComponents(driver).ValidateSplunk();

    }

    public void validatePowerfront() throws Exception
    {
        new GTMComponents(driver).ValidatePowerfront();

    }

    public void validateRudderstack() throws Exception
    {
        new GTMComponents(driver).ValidateRudderstackForPLP();
        new GTMComponents(driver).ValidateRudderstackForPDP();

    }

    public void validateKlaviyo() throws Exception
    {
        new GTMComponents(driver).ValidateKlaviyo();


    }

    public void validateContentSquare() throws Exception
    {
        new GTMComponents(driver).ValidateContentSquare();


    }

    public void validateClaritas() throws Exception
    {
       new GTMComponents(driver).validateGTMClaritasForSiteWide();
       new GTMComponents(driver).validateGTMClaritasForAddToCart();
//       new GTMComponents(driver).validateGTMClaritasForCheckout();

    }

    public void validateArtsai() throws Exception
    {
        new GTMComponents(driver).validateGTMArtsaiForContent();

    }

    public void validateBing() throws Exception
    {
        new GTMComponents(driver).validateGTMBingEvent();

    }

}
