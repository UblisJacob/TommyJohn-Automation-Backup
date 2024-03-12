package ADAChecks;
import com.tommyjohn.automation.utils.CustomUtilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.deque.axe.AXE;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;

public class Homepage_ADACheck {

    static WebDriver driver;
    private static final URL scriptUrl = Homepage_ADACheck.class.getResource("/axe.min.js");
    public static void main(String[] args) throws Exception {

//        System.setProperty("webdriver.chrome.driver" ,"C:\\Users\\HP\\Downloads\\chromedriver-win32_Updated\\chromedriver-win32\\chromedriver.exe");
//        driver = new ChromeDriver();
//        driver.get("https://www.tommyjohn.com/");

        driver = new CustomUtilities().launchtj("chrome");

        JSONObject response = new AXE.Builder(driver, scriptUrl).analyze();

        JSONArray violations = response.getJSONArray("violations");

        if (violations.length() > 0) {
            System.out.println("Accessibility issues found:");
            for (int i = 0; i < violations.length(); i++) {
                JSONObject violation = violations.getJSONObject(i);
                System.out.println(violation.getString("help"));
            }
        } else {
            System.out.println("No accessibility issues found.");
        }
    }

}
