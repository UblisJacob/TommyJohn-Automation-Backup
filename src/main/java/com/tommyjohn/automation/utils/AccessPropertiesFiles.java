package com.tommyjohn.automation.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class AccessPropertiesFiles {

	static String strPropertiesFile = "src\\main\\resources\\configuration.properties";
	
	public static String readTestData(String strAttribute) throws Exception
	{
		String strValue="";
		Properties propertiesObject = new Properties();
		propertiesObject.load(new FileInputStream(strPropertiesFile));
		strValue = propertiesObject.getProperty(strAttribute);
		return strValue;
		
	}
}
