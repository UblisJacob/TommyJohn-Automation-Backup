package com.tommyjohn.automation.ADACheck;

import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.results.Rule;
import com.deque.html.axecore.selenium.AxeBuilder;
import com.deque.html.axecore.selenium.AxeReporter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ADACheckMethods {

    public static String CurrentDate;

    public  static String setCurrentDatetDate()
    {
        DateFormat dateFormat = new SimpleDateFormat("MMddyyyyHHmmss");

        Date date = new Date();

        CurrentDate= dateFormat.format(date);

        System.out.println(CurrentDate);

        return CurrentDate;

    }
    public static void ADACheck(WebDriver driver, String Filename) throws FileNotFoundException {

        String Date = setCurrentDatetDate();

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
            Filename = Filename+Date;
            AxeReporter.writeResultsToJsonFile("ADACheckResults/"+Filename, results);
            JsonElement jsonElement = jsonParser.parse(new FileReader("ADACheckResults/"+Filename+".json"));
            String prettyJson = gson.toJson(jsonElement);
            System.out.println(Filename);
            AxeReporter.writeResultsToTextFile("ADACheckResults/"+Filename,prettyJson);
            Assert.assertEquals(violations.size(), 0, violations.size() + " violations found");
        }
    }
}
