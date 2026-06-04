package com.base;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.Permission;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GDrivePOST {

    // ===================== CONFIG =====================
    private static final String APPLICATION_NAME = "Tommy John Automation";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    // 🔐 Service Account JSON (DO NOT COMMIT TO GIT)
    private static final String SERVICE_ACCOUNT_KEY =
            "src/main/resources/tommyjohntest-8a98228aefee.json";

    // 📁 Shared Drive Folder ID
    private static final String REPORT_FOLDER_ID =
            "0AIAF7vczpBChUk9PVA";

    // ===================== DRIVE SERVICE =====================
    private static Drive getDriveService()
            throws IOException, GeneralSecurityException {

        GoogleCredentials credentials = GoogleCredentials
                .fromStream(new FileInputStream(SERVICE_ACCOUNT_KEY))
                .createScoped(Collections.singleton(DriveScopes.DRIVE));

        return new Drive.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY,
                new HttpCredentialsAdapter(credentials))
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    // ===================== FILE UPLOAD =====================
    private static String uploadFile(
            String filePath,
            String fileName,
            String mimeType,
            String folderId)
            throws IOException, GeneralSecurityException {

        Drive service = getDriveService();

        File metadata = new File();
        metadata.setName(fileName);
        metadata.setParents(Collections.singletonList(folderId));

        FileContent content =
                new FileContent(mimeType, new java.io.File(filePath));

        // Upload to Shared Drive
        File uploadedFile = service.files()
                .create(metadata, content)
                .setSupportsAllDrives(true)       // ✅ REQUIRED FOR SHARED DRIVE
                .setFields("id")
                .execute();

        // Make file public
        makePublic(service, uploadedFile.getId());

        // 🔥 ONE-CLICK DOWNLOAD LINK
        return "https://drive.google.com/uc?export=download&id="
                + uploadedFile.getId();
    }

    // ===================== PERMISSIONS =====================
    private static void makePublic(Drive service, String fileId)
            throws IOException {

        Permission permission = new Permission()
                .setType("anyone")
                .setRole("reader");

        service.permissions()
                .create(fileId, permission)
                .setSupportsAllDrives(true)       // ✅ REQUIRED
                .setFields("id")
                .execute();
    }

    // ===================== REPORT UPLOADS =====================
    public static String UploadReport() throws Exception {
        return uploadFile(
                "Report/index.html",
                "Regression Report",
                "text/html",
                REPORT_FOLDER_ID
        );
    }

    public static String UploadSmokeReport() throws Exception {
        return uploadFile(
                "Report/index.html",
                "Smoke Test Report",
                "text/html",
                REPORT_FOLDER_ID
        );
    }

    public static String UploadADAReport() throws Exception {
        return uploadFile(
                "Report/index.html",
                "ADA Check Report",
                "text/html",
                REPORT_FOLDER_ID
        );
    }

    public static String UploadGTMReport() throws Exception {
        return uploadFile(
                "Report/index.html",
                "GTM Report",
                "text/html",
                REPORT_FOLDER_ID
        );
    }

    public static String UploadImageReport() throws Exception {
        return uploadFile(
                "Report/index.html",
                "Image Report",
                "text/html",
                REPORT_FOLDER_ID
        );
    }

    // ===================== IMAGE + CSV =====================
    public static Map<String, String> UploadImageReportAndCSV()
            throws Exception {

        Map<String, String> links = new HashMap<>();

        links.put("reportLink",
                uploadFile(
                        "Report/index.html",
                        "Image Report",
                        "text/html",
                        REPORT_FOLDER_ID));

        links.put("homepageCsvLink",
                uploadFile(
                        "CSV/homepage_report.csv",
                        "homepage_report.csv",
                        "text/csv",
                        REPORT_FOLDER_ID));

        links.put("plpCsvLink",
                uploadFile(
                        "CSV/PLP_report.csv",
                        "PLP_report.csv",
                        "text/csv",
                        REPORT_FOLDER_ID));

        links.put("pdpCsvLink",
                uploadFile(
                        "CSV/PDP_report.csv",
                        "PDP_report.csv",
                        "text/csv",
                        REPORT_FOLDER_ID));

        return links;
    }

    // ===================== SCREENSHOTS ZIP =====================
    public static String UploadScreenShot() throws Exception {
        return uploadFile(
                "test-output/Screenshots.zip",
                "Regression Screenshots",
                "application/zip",
                REPORT_FOLDER_ID
        );
    }
}