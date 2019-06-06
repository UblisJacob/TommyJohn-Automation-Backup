//package TestScenario;
//
//import org.openqa.selenium.WebDriver;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import com.tommyjohn.automation.Pages.CollectionPage;
//import com.tommyjohn.automation.utils.CustomUtilities;
//
//public class ValidateCollectionPage {
//
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
//	public void TC_ValidateProductImageOnCollectionPage() throws Exception
//	{
//		new CollectionPage(driver).validateProductImage();
//	}
//
//	@Test
//	public void TC_ValidateProductHeadingTextOnCollectionPage() throws Exception
//	{
//		new CollectionPage(driver).validateProductHeadingText();
//	}
//
//	@Test
//	public void TC_ValidateProductReviewStarsOnCollectionPage() throws Exception
//	{
//		new CollectionPage(driver).validateProductReviewStars();
//	}
//
//	@Test
//	public void TC_ValidateProductImageOnSingleCollectionPage() throws Exception
//	{
//		new CollectionPage(driver).validateProductImageOnSingleCollectionPage();
//	}
//
//	@Test
//	public void TC_ValidateProductHeadingTextOnSingleCollectionPage() throws Exception
//	{
//		new CollectionPage(driver).validateProductHeadingTextOnSingleCollectionPage();
//	}
//	@Test
//	public void TC_ValidateProductReviewStarsOnSingleCollectionPage() throws Exception
//	{
//		new CollectionPage(driver).validateProductReviewStarsOnSingleCollectionPage();
//	}
//
//	//--------------------------validate mega collections----------------------------
//
//	@Test
//	public void TC_ValidateMegaCollectionAllPantiesInWomenCategory() throws Exception {
//		new CollectionPage(driver).validateWomensAllPantiesOnCollectionPage();
//	}
//
//	@Test
//	public void TC_ValidateMegaCollectionAllUnderwearInMenCategory() throws Exception {
//		new CollectionPage(driver).validateMensAllUnderwearOnCollectionPage();
//	}
//
//	@Test
//	public void TC_ValidateMegaCollectionAllUnderShirtsInMenCategory() throws Exception {
//		new CollectionPage(driver).validateMensAllUnderShirtsOnCollectionPage();
//	}
//
//	@Test
//	public void TC_ValidateMegaCollectionAllCollectionsInMenCategory() throws Exception {
//		new CollectionPage(driver).validateMensAllCollectionsOnCollectionPage();
//	}
//
//
//	//---------------------------validate single collections---------------
//	@Test
//	public void TC_ValidateSingleCollectionSecondSkinInMenCategory() throws Exception {
//		new CollectionPage(driver).validateSecondSkinOnSingleCollectionPage();
//	}
//
//	@Test
//	public void TC_ValidateSingleCollectionCoolCottonInMenCategory() throws Exception {
//		new CollectionPage(driver).validateCoolCottonOnSingleCollectionPage();
//	}
//
//	@Test
//	public void TC_ValidateSingleCollectionPacksInWomenCategory() throws Exception {
//		new CollectionPage(driver).validatePacksOnSingleCollectionPage();
//	}
//
//	@Test
//	public void TC_ValidateSingleCottonBasicsInShopByCollectionCategory() throws Exception {
//		new CollectionPage(driver).validateCottonBasicsOnSingleCollectionPage();
//	}
//
//	@AfterMethod(alwaysRun = true)
//	void teardown () {
//		
//		driver.close();
//		driver.quit();
//	}
//}
