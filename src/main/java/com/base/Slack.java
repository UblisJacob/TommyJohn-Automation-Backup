package com.base;

import com.google.gson.JsonParser;
import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.net.HttpURLConnection;

import java.net.URL;

import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.stream.Collectors;


public class Slack {

    public static String TJ_QA_WEBHOOK_URL="https://hooks.slack.com/services/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";
    public static String Test_WEBHOOK_URL="https://hooks.slack.com/services/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";
    public static String Test_Automation_WEBHOOK_URL="https://hooks.slack.com/services/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";
    public static String CurrentDate;


    public  static String setCurrentDatetDate()
    {
        ZoneId easternTimeZone = ZoneId.of("America/New_York");

        ZonedDateTime easternTime = ZonedDateTime.now(easternTimeZone);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");

        System.out.println("Current Eastern Time (US & Canada): " + easternTime.format(formatter));

//        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
//
//        dateFormat.setTimeZone(TimeZone.getTimeZone("EST"));
//
//        System.out.println(dateFormat);
//
//        Date date = new Date();

        CurrentDate= easternTime.format(formatter);

        System.out.println(CurrentDate);

        return CurrentDate;

    }

    public static void sendSlackNotificationReg(String passed, String failed, String skipped, String reportLink /*, String JSonLink*/) throws Exception {

//                String ReportLINK = "https://drive.google.com/uc?export=download&id=15udrnuKuBj3zKWTVVQKaiGss1Gkact77";
            // Create JSON payload
                String Date = setCurrentDatetDate();

                System.out.println(passed+"\n"+failed+"\n"+skipped+"\n"+reportLink+"\n"/*+JSonLink*/);

               String payload = "{\"text\":\"*Regression Results*\n*Time:* " + Date + " \n*Passed:* " + passed + "\n*Failed:* "+ failed + "\n*Skipped:* " + skipped + "\n*Report Link:* <" + reportLink + "|Click> \",\"type\": \"mrkdwn\"}";

//              String payload = "{\"text\":\"*Regression Results*\n*Time:* " + Date + " \n*Passed:* " + 169 + "\n*Failed:* "+ 7 + "\n*Skipped:* " + skipped + "\n*Report Link:* <" + ReportLINK + "|Click> \",\"type\": \"mrkdwn\"}";

            URL url = new URL(TJ_QA_WEBHOOK_URL);

            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();

            httpConn.setDoOutput(true);

            httpConn.setRequestMethod("POST");

            httpConn.setRequestProperty("Content-Type", "application/json");

            httpConn.setRequestProperty("Accept", "application/json");

            try (OutputStream os = httpConn.getOutputStream()) {

                byte[] file = payload.getBytes("utf-8");
   //             byte[] input = payload.getBytes("utf-8");

                os.write(file, 0, file.length);


            }
            int responseCode = httpConn.getResponseCode();

            System.out.println("Response Code: " + responseCode);

    }

    public static void sendSlackNotificationImage(String passed, String failed, String skipped, String reportLink /*, String JSonLink*/) throws Exception {

        //           String ReportLINK = "https://drive.google.com/uc?export=download&id=1JO4IUdmbmYmjBnCSZk_zYXPRYq-FwHB8";
        // Create JSON payload
        String Date = setCurrentDatetDate();

        System.out.println(passed+"\n"+failed+"\n"+skipped+"\n"+reportLink+"\n"/*+JSonLink*/);

        String payload = "{\"text\":\"*Images Missing Alt Text*\n*Time:* " + Date + " \n*Passed:* " + passed + "\n*Failed:* "+ failed + "\n*Skipped:* " + skipped + "\n*Report Link:* <" + reportLink + "|Click> \",\"type\": \"mrkdwn\"}";

        //             String payload = "{\"text\":\"*Regression Results*\n*Time:* " + Date + " \n*Passed:* " + 152 + "\n*Failed:* "+ 16 + "\n*Skipped:* " + skipped + "\n*Report Link:* <" + ReportLINK + "|Click> \",\"type\": \"mrkdwn\"}";

        URL url = new URL(Test_Automation_WEBHOOK_URL);

        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();

        httpConn.setDoOutput(true);

        httpConn.setRequestMethod("POST");

        httpConn.setRequestProperty("Content-Type", "application/json");

        httpConn.setRequestProperty("Accept", "application/json");

        try (OutputStream os = httpConn.getOutputStream()) {

            byte[] file = payload.getBytes("utf-8");
            //             byte[] input = payload.getBytes("utf-8");

            os.write(file, 0, file.length);


        }
        int responseCode = httpConn.getResponseCode();

        System.out.println("Response Code: " + responseCode);

    }

    public static void sendSlackNotificationImageCSV(
            String passed,
            String failed,
            String skipped,
            String reportLink,
            String homepageCsvLink,
            String altCsvLink,
            Set<String> passedTests,
            Set<String> failedTests,
            Set<String> skippedTests
    ) throws Exception {

        String Date = setCurrentDatetDate();

        String passedTestNames = passedTests.isEmpty()
                ? "None"
                : passedTests.stream().collect(Collectors.joining(", "));

        String failedTestNames = failedTests.isEmpty()
                ? "None"
                : failedTests.stream().collect(Collectors.joining(", "));

        String skippedTestNames = skippedTests.isEmpty()
                ? "None"
                : skippedTests.stream().collect(Collectors.joining(", "));


        String payload =
                "{ \"text\": \"*Images missing \\\"Alt Text\\\"*"
                        + "\\n*Time:* " + Date
                        + "\\n\\n✅ *Passed Tests:* " + passedTestNames
                        + "\\n❌ *Failed Tests:* " + failedTestNames
                        + "\\n⏭ *Skipped Tests:* " + skippedTestNames
                        + "\\n\\n*Report Link:* <" + reportLink + "|View Report>"
                        + "\\n*Homepage CSV:* <" + homepageCsvLink + "|Download>"
                        + "\\n*PLP CSV:* <" + altCsvLink + "|Download>"
                        + "\" }";

        URL url = new URL(Test_Automation_WEBHOOK_URL);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();

        httpConn.setDoOutput(true);
        httpConn.setRequestMethod("POST");
        httpConn.setRequestProperty("Content-Type", "application/json");
        httpConn.setRequestProperty("Accept", "application/json");

        try (OutputStream os = httpConn.getOutputStream()) {
            os.write(payload.getBytes("utf-8"));
        }

        System.out.println("Slack Response Code: " + httpConn.getResponseCode());
    }

    public static void sendSlackNotificationADA(String passed, String failed, String skipped, String reportLink ) throws Exception {

        // Create JSON payload
        String Date = setCurrentDatetDate();

        System.out.println(passed+"\n"+failed+"\n"+skipped+"\n"+reportLink+"\n"/*+JSonLink*/);

        String payload = "{\"text\":\"*ADA Check result*\n*Time:* " + Date + " \n*Passed:* " + passed + "\n*Failed:* "+ failed + "\n*Skipped:* " + skipped + "\n*Report Link:* <" + reportLink + "|Click> \",\"type\": \"mrkdwn\"}";

        URL url = new URL(TJ_QA_WEBHOOK_URL);

        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();

        httpConn.setDoOutput(true);

        httpConn.setRequestMethod("POST");

        httpConn.setRequestProperty("Content-Type", "application/json");

        httpConn.setRequestProperty("Accept", "application/json");

        try (OutputStream os = httpConn.getOutputStream()) {

            byte[] file = payload.getBytes("utf-8");
            //             byte[] input = payload.getBytes("utf-8");

            os.write(file, 0, file.length);


        }
        int responseCode = httpConn.getResponseCode();

        System.out.println("Response Code: " + responseCode);

    }
      public static void sendSlackNotificationSmokeTest(String passed, String failed, String skipped, String reportLink ) throws Exception {

        // Create JSON payload
        String Date = setCurrentDatetDate();

        System.out.println(passed+"\n"+failed+"\n"+skipped+"\n"+reportLink+"\n"/*+JSonLink*/);

        String payload = "{\"text\":\"*Smoke Test results*\n*Time:* " + Date + " \n*Passed:* " + passed + "\n*Failed:* "+ failed + "\n*Skipped:* " + skipped + "\n*Report Link:* <" + reportLink + "|Click> \",\"type\": \"mrkdwn\"}";

        URL url = new URL(TJ_QA_WEBHOOK_URL);

        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();

        httpConn.setDoOutput(true);

        httpConn.setRequestMethod("POST");

        httpConn.setRequestProperty("Content-Type", "application/json");

        httpConn.setRequestProperty("Accept", "application/json");

        try (OutputStream os = httpConn.getOutputStream()) {

            byte[] file = payload.getBytes("utf-8");
            //             byte[] input = payload.getBytes("utf-8");

            os.write(file, 0, file.length);


        }
        int responseCode = httpConn.getResponseCode();

        System.out.println("Response Code: " + responseCode);

    }

    public static void sendSlackNotificationGTM(String passed, String failed, String skipped, String reportLink ) throws Exception {

   //     String ReportLINK = "https://drive.google.com/uc?export=download&id=1sgWgYyFUDnIh0M0TlCJ0OzrQm_VyJ2x8";
        // Create JSON payload
        String Date = setCurrentDatetDate();

        System.out.println(passed+"\n"+failed+"\n"+skipped+"\n"+reportLink+"\n"/*+JSonLink*/);

        String payload = "{\"text\":\"*GTM Result*\n*Time:* " + Date + " \n*Passed:* " + passed + "\n*Failed:* "+ failed + "\n*Skipped:* " + skipped + "\n*Report Link:* <" + reportLink + "|Click> \",\"type\": \"mrkdwn\"}";

         //     String payload = "{\"text\":\"*GTM Results*\n*Time:* " + Date + " \n*Passed:* " + 8 + "\n*Failed:* "+ 0 + "\n*Skipped:* " + skipped + "\n*Report Link:* <" + ReportLINK + "|Click> \",\"type\": \"mrkdwn\"}";


        URL url = new URL(TJ_QA_WEBHOOK_URL);

        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();

        httpConn.setDoOutput(true);

        httpConn.setRequestMethod("POST");

        httpConn.setRequestProperty("Content-Type", "application/json");

        httpConn.setRequestProperty("Accept", "application/json");

        try (OutputStream os = httpConn.getOutputStream()) {

            byte[] file = payload.getBytes("utf-8");
            //             byte[] input = payload.getBytes("utf-8");

            os.write(file, 0, file.length);


        }
        int responseCode = httpConn.getResponseCode();

        System.out.println("Response Code: " + responseCode);

    }
}




