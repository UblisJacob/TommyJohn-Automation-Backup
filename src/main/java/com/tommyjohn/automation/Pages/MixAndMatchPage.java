package com.tommyjohn.automation.Pages;

import org.openqa.selenium.WebDriver;
import com.tommyjohn.automation.Components.MixAndMatchComponents;

public class MixAndMatchPage 
{
	WebDriver driver;
	public MixAndMatchPage(WebDriver driver) 
	{
		this.driver=driver;

	}
	public void ValidateFieldsOfMixAndMatch() throws Exception
	{
		new MixAndMatchComponents(driver).Validate_MixAndMatch_Page();
	}
}
