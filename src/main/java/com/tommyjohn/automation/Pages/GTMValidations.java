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

}
