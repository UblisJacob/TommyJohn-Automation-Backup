//package TestScenario;
//
//import org.openqa.selenium.WebDriver;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//import com.tommyjohn.automation.Pages.GiftCard;
//import com.tommyjohn.automation.utils.CustomUtilities;
//
//public class ValidateGiftCardFunctionality {
//	WebDriver driver;
//
//
//	@BeforeMethod(alwaysRun = true)
//	public void setup() {
//		try {
//			driver = new CustomUtilities().launchtj();
//
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//	}
//
//
//	@Test
//	public void ValidatePDPofGiftCard() throws Exception {
//
//		new GiftCard(driver).validateGCPDP();
//	}
//
//}
