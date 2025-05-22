package com.tommyjohn.automation.Pages;

import com.tommyjohn.automation.Components.ElevarComponents;
import com.tommyjohn.automation.Components.GTMComponents;
import com.tommyjohn.automation.commonMethods.ExcelUtils;
import com.tommyjohn.automation.commonMethods.GeneralMethods;
import org.openqa.selenium.WebDriver;

public class ElevarValidations extends ElevarComponents {

    GeneralMethods objGeneralMethods = new GeneralMethods();
    ExcelUtils objExcelUtils = new ExcelUtils();
    public ElevarValidations(WebDriver driver) {
        super(driver);
        this.driver=driver;
    }

    public void validateElevarFacebookCheckout() throws Exception
    {
        new ElevarComponents(driver).ValidateElevarFacebookForCheckout();

    }
}
