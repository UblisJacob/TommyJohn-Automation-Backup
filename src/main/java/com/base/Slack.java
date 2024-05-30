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
import java.util.TimeZone;


public class Slack {

    public static String WEBHOOK_URL="https://hooks.slack.com/services/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";

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

            public static void sendSlackNotification(String passed, String failed, String skipped, String reportLink) throws Exception {

            // Create JSON payload
                String Date = setCurrentDatetDate();

                System.out.println(passed+"\n"+failed+"\n"+skipped+"\n"+reportLink);

                String payload = "{\"text\":\"*Regression result*\n*Time:* " + Date + " \n*Passed:* " + passed + "\n*Failed:* "+ failed + "\n*Skipped:* " + skipped + "\n*Report Link:* <" + reportLink + "|Click>\",\"type\": \"mrkdwn\"}";

            URL url = new URL(WEBHOOK_URL);

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
