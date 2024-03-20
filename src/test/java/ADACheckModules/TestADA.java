package ADACheckModules;

import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.results.Rule;
import com.deque.html.axecore.selenium.AxeBuilder;
import com.deque.html.axecore.selenium.AxeReporter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.tommyjohn.automation.utils.CustomUtilities;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

public class TestADA {

static WebDriver driver;


    public static void main(String args[]) throws Exception {

        driver = new CustomUtilities().launchtj("chrome");

     //   driver.get("https://www.tommyjohn.com/collections/mens-all?sort-by=relevance&sort-order=descending");

        List<String> tags = Arrays.asList("wcag2aa");

        AxeBuilder builder = new AxeBuilder();

        Results results = builder.analyze(driver);
        builder.withTags(tags);
        List<Rule> violations = results.getViolations();

        if (violations.size() == 0)
        {
            Assert.assertTrue(true, "No violations found");
        }
        else
        {
            JsonParser jsonParser = new JsonParser();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            AxeReporter.writeResultsToJsonFile("ADACheckResults/ADAOutput", results);
            JsonElement jsonElement = jsonParser.parse(new FileReader("ADACheckResults/ADAOutput.json"));
            String prettyJson = gson.toJson(jsonElement);
            AxeReporter.writeResultsToTextFile("ADACheckResults/ADAOutputText"+System.currentTimeMillis(), prettyJson);
            Assert.assertEquals(violations.size(), 0, violations.size() + " violations found");
        }
    }
}
