package TestScenario;

import org.testng.annotations.Test;

import com.tommyjohn.automation.Pages.AddToCartPage;

public class AddToCartTests {


	@Test
	public void AddToCartTestCase() throws Exception {
		new AddToCartPage().ATC();
	}
	
	
}
