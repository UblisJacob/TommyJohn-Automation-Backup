package TestScenario;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tommyjohn.automation.Pages.NavigationFlowPage;
import com.tommyjohn.automation.utils.CustomUtilities;

//import atu.testrecorder.exceptions.ATUTestRecorderException;

public class ValidateNavigationFlow
{
	WebDriver driver ;

	@BeforeMethod(alwaysRun = true)
	public void setup() {
		try {
			driver = new CustomUtilities().launchtj();

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	@Test
	public void Validate_Mens_AllNewArrivals() throws Exception
	{
		new NavigationFlowPage(driver).NavigateToAllNewArrivalInMenCategory();
	
	}

	@Test
	public void Validate_Womens_AllNewArrivals() throws Exception
	{
		new NavigationFlowPage(driver).NavigateToAllNewArrivalInWomenCategory();
	
	}

	@Test
	public void Validate_FinalSale_AllMensSale() throws Exception
	{
		new NavigationFlowPage(driver).NavigateToAllMensSaleInFinalSaleCategory();
	
	}
	@Test
	public void Validate_Men_AllSocks() throws Exception
	{
		new NavigationFlowPage(driver).NavigateToAllSocksInMenCategory();
	
	}
	@Test
	public void Validate_Women_AllUnderwear() throws Exception
	{
		new NavigationFlowPage(driver).NavigateToAllUnderwearInWomenCategory();
	
	}
	@Test
	public void Validate_Men_Underwear_BoxerBrief() throws Exception
	{
		new NavigationFlowPage(driver).NavigateToBoxerBriefInMensUnderwearCategory();
	
	}
	@Test
	public void Validate_Men_Clothing_TeesAndPolos() throws Exception
	{
		new NavigationFlowPage(driver).NavigateToTeesAndPolosInMenCategory();
	
	}
	@Test
	public void Validate_Women_Bras_ScoopBralette() throws Exception
	{
		new NavigationFlowPage(driver).NavigateToScoopBraletteInWomensBrasCategory();
	
	}
//	@Test
//	public void Validate_Women_Collections_ComfortSmoothing() throws Exception
//	{
//		new NavigationFlowPage(driver).NavigatetoComfortSmoothingInWomenCollectionCategory();
//	
//	}
	@Test
	public void Validate_FinalSale_WomenSale_Underwear() throws Exception
	{
		new NavigationFlowPage(driver).NavigateToWomenSaleUnderwearInFinalSaleCategory();
	
	}
	
	@Test
	public void Validate_Men_Undershirts_VNeck() throws Exception
	{
		new NavigationFlowPage(driver).NavigatetoVNeckInMenUndershirtsCategory();
	
	}
	@Test
	public void Validate_Men_LoungeAndSleep_Tops() throws Exception
	{
		new NavigationFlowPage(driver).NavigateToTopsInMensLoungeAndSleepCategory();
	
	}
	@Test
	public void Validate_Mens_Socks_Calf() throws Exception
	{
		new NavigationFlowPage(driver).NavigateToCalfSocksInMenCategory();
	
	}
	@Test
	public void Validate_Women_Underwear_Cheeky() throws Exception
	{
		new NavigationFlowPage(driver).NavigateToCheeckyUnderwearInWomenCategory();
	
	}
	@Test
	public void Validate_FinalSale_MenSale_LoungeAndSleep() throws Exception
	{
		new NavigationFlowPage(driver).NavigateToMensLoungeAndSleepInFinalSaleCategory();
	
	}
	@Test
	public void Validate_Men_Clothing_ButtonDowns() throws Exception
	{
		new NavigationFlowPage(driver).NavigateToButtonDownsInMenCategory();
	
	}
	@Test
	public void Validate_Women_Clothing_Dresses() throws Exception
	{
		new NavigationFlowPage(driver).NavigateToDressesInWomenCategory();
	
	}
	@Test
	public void Validate_Women_LoungeAndSleep_Packs() throws Exception
	{
		new NavigationFlowPage(driver).NavigatetoPacks_LoungeAndSleep_InWomenCategory();
	
	}
	@AfterMethod(alwaysRun = true)
	void teardown () {
		
	
		
//		File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//		try {
//			FileUtils.copyFile(file, new File(CustomUtilities.properties.getProperty("executionvideospath")+"/Screenshot_"+ m.getName()+".png"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		driver.close();
		driver.quit();
	}

}
