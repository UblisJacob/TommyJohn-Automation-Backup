package TestScenario;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.tommyjohn.automation.Pages.CollectionPage;
import com.tommyjohn.automation.utils.CustomUtilities;

	public class ValidateCollectionPage {
//	@BeforeTest(alwaysRun = true)
//	void setup() {
//		try {
//			CustomUtilities.launchtj();
//
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//	}

	@Test
	public void TC_ValidateProductImageOnCollectionPage() throws Exception
	{
		new CollectionPage().validateProductImage();
	}

	@Test
	public void TC_ValidateProductHeadingTextOnCollectionPage() throws Exception
	{
		new CollectionPage().validateProductHeadingText();
	}

	@Test
	public void TC_ValidateProductReviewStarsOnCollectionPage() throws Exception
	{
		new CollectionPage().validateProductReviewStars();
	}

	@Test
	public void TC_ValidateProductImageOnSingleCollectionPage() throws Exception
	{
		new CollectionPage().validateProductImageOnSingleCollectionPage();
	}

	@Test
	public void TC_ValidateProductHeadingTextOnSingleCollectionPage() throws Exception
	{
		new CollectionPage().validateProductHeadingTextOnSingleCollectionPage();
	}
	@Test
	public void TC_ValidateProductReviewStarsOnSingleCollectionPage() throws Exception
	{
		new CollectionPage().validateProductReviewStarsOnSingleCollectionPage();
	}

	//--------------------------validate mega collections----------------------------

	@Test
	public void TC_ValidateMegaCollectionAllPantiesInWomenCategory() throws Exception {
		new CollectionPage().validateWomensAllPantiesOnCollectionPage();
	}

	@Test
	public void TC_ValidateMegaCollectionAllUnderwearInMenCategory() throws Exception {
		new CollectionPage().validateMensAllUnderwearOnCollectionPage();
	}

	@Test
	public void TC_ValidateMegaCollectionAllUnderShirtsInMenCategory() throws Exception {
		new CollectionPage().validateMensAllUnderShirtsOnCollectionPage();
	}

	@Test
	public void TC_ValidateMegaCollectionAllCollectionsInMenCategory() throws Exception {
		new CollectionPage().validateMensAllCollectionsOnCollectionPage();
	}

		
	//---------------------------validate single collections---------------
	@Test
	public void TC_ValidateSingleCollectionSecondSkinInMenCategory() throws Exception {
		new CollectionPage().validateSecondSkinOnSingleCollectionPage();
	}

	@Test
	public void TC_ValidateSingleCollectionCoolCottonInMenCategory() throws Exception {
		new CollectionPage().validateCoolCottonOnSingleCollectionPage();
	}
	
	@Test
	public void TC_ValidateSingleCollectionPacksInWomenCategory() throws Exception {
		new CollectionPage().validatePacksOnSingleCollectionPage();
	}

	@Test
	public void TC_ValidateSingleCottonBasicsInShopByCollectionCategory() throws Exception {
		new CollectionPage().validateCottonBasicsOnSingleCollectionPage();
	}


//	@AfterSuite(alwaysRun = true)
//	void tearDown() {
//		try {
//			CustomUtilities.teardown();
//
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//	}

}
