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
import org.testng.SkipException;
import org.testng.asserts.SoftAssert;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class ADACheckMethods {

    public static String CurrentDate;
    public static SoftAssert softAssert;
    public static int Fail = 0;

    public  static String setCurrentDatetDate()
    {

        ZoneId easternTimeZone = ZoneId.of("America/New_York");

        ZonedDateTime easternTime = ZonedDateTime.now(easternTimeZone);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyyHHmmss");

        System.out.println("Current Eastern Time (US & Canada): " + easternTime.format(formatter));

  //      DateFormat dateFormat = new SimpleDateFormat("MMddyyyyHHmmss");

  //      formatter.setTimeZone(TimeZone.getTimeZone("EST"));

//        System.out.println(formatter);
//
//        Date date = new Date();

        CurrentDate= easternTime.format(formatter);

        System.out.println(CurrentDate);

        return CurrentDate;

    }
    public static void ADACheck(WebDriver driver, String Filename) throws FileNotFoundException {

        softAssert = new SoftAssert();

        String Date = setCurrentDatetDate();

        List<String> tags = Arrays.asList("wcag2aa");

        AxeBuilder builder = new AxeBuilder();

        Results results = builder.analyze(driver);
        builder.withTags(tags);
        List<Rule> violations = results.getViolations();

        if (violations.size() == 0)
        {
            Assert.assertTrue(true, "No violations found");
            System.out.println("No Violations");
        }
        else
        {
            JsonParser jsonParser = new JsonParser();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Filename = Filename+Date;
            AxeReporter.writeResultsToJsonFile("ADACheckResults/"+Filename, results);
            JsonElement jsonElement = jsonParser.parse(new FileReader("ADACheckResults/"+Filename+".json"));
            String prettyJson = gson.toJson(jsonElement);
//            System.out.println(Filename);
            AxeReporter.writeResultsToTextFile("ADACheckResults/"+Filename,prettyJson);
            softAssert.assertEquals(violations.size(), 0, violations.size() + " violations found");
            System.out.println("Violations Found");

        }

    }
}
