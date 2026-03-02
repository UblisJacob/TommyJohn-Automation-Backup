package com.base;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.api.services.drive.model.Permission;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GDrivePOST {

    private static final String APPLICATION_NAME = "Tommy John";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH =
            System.getProperty("user.home") + "/.tj-drive-tokens";

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE);
    private static final String CREDENTIALS_FILE_PATH = "src/main/java/com/base/client_secret_485693069449-nuh5mpr6lgqimvi8r1mcgmcngevqgj4v.apps.googleusercontent.com.json";

    /**
     * Creates an authorized Credential object.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private static Credential getCredentials() throws IOException, GeneralSecurityException {
        // Load client secrets.
        FileInputStream in = new FileInputStream(CREDENTIALS_FILE_PATH);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }


    public static void moveFile(Drive service, String fileId, String folderId) throws IOException {

        // Get current parents
        File file = service.files()
                .get(fileId)
                .setFields("parents")
                .setSupportsAllDrives(true)
                .execute();

        String previousParents = String.join(",", file.getParents());

        // Move file
        service.files()
                .update(fileId, null)
                .setAddParents(folderId)
                .setRemoveParents(previousParents)
                .setSupportsAllDrives(true)   // 🔥 CRITICAL FIX
                .setFields("id, parents")
                .execute();

        System.out.println("File moved to folder: " + folderId);
    }


//public static void main(String args[]) throws IOException, GeneralSecurityException{
   public static String UploadReport() throws IOException, GeneralSecurityException {
//        public static void main(String args[]) throws IOException, GeneralSecurityException {
        // Build a new authorized API client service.
        Drive service = new Drive.Builder(GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, getCredentials())
                .setApplicationName(APPLICATION_NAME)
                .build();


        // Upload file
        String filepath = "Report/index.html";
        File fileMetadata = new File();
        fileMetadata.setName("Regression Report");

//        fileMetadata.setParents(Collections.singletonList(folderId));  // Set the parent folder

        java.io.File filePath = new java.io.File(filepath);
        FileContent mediaContent = new FileContent("text/html", filePath);



        File file = service.files().create(fileMetadata, mediaContent)
                .setFields("id, webContentLink")
                .execute();

        Permission permission = new Permission();
        permission.setType("anyone");
        permission.setRole("reader");

        service.permissions().create(file.getId(), permission).execute();

        moveFile(service, file.getId(), "1qox4LPt_dQhZfE79Utf3n3VY43x6pwQ1");

        System.out.println("File ID: " + file.getId());
        System.out.println("WebContent Link: " + file.getWebContentLink());

        return file.getWebContentLink();
    }

    public static String UploadSmokeReport() throws IOException, GeneralSecurityException {
//        public static void main(String args[]) throws IOException, GeneralSecurityException {
        // Build a new authorized API client service.
        Drive service = new Drive.Builder(GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, getCredentials())
                .setApplicationName(APPLICATION_NAME)
                .build();


        // Upload file
        String filepath = "Report/index.html";
        File fileMetadata = new File();
        fileMetadata.setName("Smoke Test Report");

//        fileMetadata.setParents(Collections.singletonList(folderId));  // Set the parent folder

        java.io.File filePath = new java.io.File(filepath);
        FileContent mediaContent = new FileContent("text/html", filePath);



        File file = service.files().create(fileMetadata, mediaContent)
                .setFields("id, webContentLink")
                .execute();

        Permission permission = new Permission();
        permission.setType("anyone");
        permission.setRole("reader");

        service.permissions().create(file.getId(), permission).execute();

        moveFile(service, file.getId(), "1AubNi7muTDJdsmkNa5ZA8qCgEcw_WOrh");

        System.out.println("File ID: " + file.getId());
        System.out.println("WebContent Link: " + file.getWebContentLink());

        return file.getWebContentLink();
    }

    public static String UploadADAReport() throws IOException, GeneralSecurityException {
//        public static void main(String args[]) throws IOException, GeneralSecurityException {
        // Build a new authorized API client service.
        Drive service = new Drive.Builder(GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, getCredentials())
                .setApplicationName(APPLICATION_NAME)
                .build();


        // Upload file
        String filepath = "Report/index.html";
        File fileMetadata = new File();
        fileMetadata.setName("ADACheck Report");

//        fileMetadata.setParents(Collections.singletonList(folderId));  // Set the parent folder

        java.io.File filePath = new java.io.File(filepath);
        FileContent mediaContent = new FileContent("text/html", filePath);



        File file = service.files().create(fileMetadata, mediaContent)
                .setFields("id, webContentLink")
                .execute();

        Permission permission = new Permission();
        permission.setType("anyone");
        permission.setRole("reader");

        service.permissions().create(file.getId(), permission).execute();

        moveFile(service, file.getId(), "1AubNi7muTDJdsmkNa5ZA8qCgEcw_WOrh");

        System.out.println("File ID: " + file.getId());
        System.out.println("WebContent Link: " + file.getWebContentLink());

        return file.getWebContentLink();
    }

    public static String UploadGTMReport() throws IOException, GeneralSecurityException {
//        public static void main(String args[]) throws IOException, GeneralSecurityException {
        // Build a new authorized API client service.
        Drive service = new Drive.Builder(GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, getCredentials())
                .setApplicationName(APPLICATION_NAME)
                .build();


        // Upload file
        String filepath = "Report/index.html";
        File fileMetadata = new File();
        fileMetadata.setName("GTM Report");

//        fileMetadata.setParents(Collections.singletonList(folderId));  // Set the parent folder

        java.io.File filePath = new java.io.File(filepath);
        FileContent mediaContent = new FileContent("text/html", filePath);



        File file = service.files().create(fileMetadata, mediaContent)
                .setFields("id, webContentLink")
                .execute();

        Permission permission = new Permission();
        permission.setType("anyone");
        permission.setRole("reader");

        service.permissions().create(file.getId(), permission).execute();

        moveFile(service, file.getId(), "1AubNi7muTDJdsmkNa5ZA8qCgEcw_WOrh");

        System.out.println("File ID: " + file.getId());
        System.out.println("WebContent Link: " + file.getWebContentLink());

        return file.getWebContentLink();
    }


    public static String UploadImageReport() throws IOException, GeneralSecurityException {
//        public static void main(String args[]) throws IOException, GeneralSecurityException {
        // Build a new authorized API client service.
        Drive service = new Drive.Builder(GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, getCredentials())
                .setApplicationName(APPLICATION_NAME)
                .build();


        // Upload file
        String filepath = "Report/index.html";
        File fileMetadata = new File();
        fileMetadata.setName("Image Report");

//        fileMetadata.setParents(Collections.singletonList(folderId));  // Set the parent folder

        java.io.File filePath = new java.io.File(filepath);
        FileContent mediaContent = new FileContent("text/html", filePath);



        File file = service.files().create(fileMetadata, mediaContent)
                .setFields("id, webContentLink")
                .execute();

        Permission permission = new Permission();
        permission.setType("anyone");
        permission.setRole("reader");

        service.permissions().create(file.getId(), permission).execute();

        moveFile(service, file.getId(), "1AubNi7muTDJdsmkNa5ZA8qCgEcw_WOrh");

        System.out.println("File ID: " + file.getId());
        System.out.println("WebContent Link: " + file.getWebContentLink());

        return file.getWebContentLink();
    }


    public static Map<String, String> UploadImageReportAndCSV()
            throws IOException, GeneralSecurityException {

        Drive service = new Drive.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY,
                getCredentials()
        ).setApplicationName(APPLICATION_NAME).build();

        String folderId = "1AubNi7muTDJdsmkNa5ZA8qCgEcw_WOrh";
        Map<String, String> uploadedLinks = new HashMap<>();

        // ===================== Upload HTML Report =====================
        File reportMetadata = new File();
        reportMetadata.setName("Image Report");

        FileContent reportContent =
                new FileContent("text/html", new java.io.File("Report/index.html"));

        File uploadedReport = service.files()
                .create(reportMetadata, reportContent)
                .setFields("id, webContentLink")
                .execute();

        Permission permission = new Permission();
        permission.setType("anyone");
        permission.setRole("reader");
        service.permissions().create(uploadedReport.getId(), permission).execute();
        moveFile(service, uploadedReport.getId(), folderId);

        uploadedLinks.put("reportLink", uploadedReport.getWebContentLink());

        // ===================== Upload Homepage CSV =====================
        File homepageCsvMetadata = new File();
        homepageCsvMetadata.setName("homepage_report.csv");

        FileContent homepageCsvContent =
                new FileContent("text/csv", new java.io.File("CSV/homepage_report.csv"));

        File uploadedHomepageCSV = service.files()
                .create(homepageCsvMetadata, homepageCsvContent)
                .setFields("id, webContentLink")
                .execute();

        service.permissions().create(uploadedHomepageCSV.getId(), permission).execute();
        moveFile(service, uploadedHomepageCSV.getId(), folderId);

        uploadedLinks.put("homepageCsvLink", uploadedHomepageCSV.getWebContentLink());

        // ===================== Upload Alt Verification CSV =====================
        File altCsvMetadata = new File();
        altCsvMetadata.setName("PLP_report.csv");

        FileContent altCsvContent =
                new FileContent("text/csv", new java.io.File("CSV/PLP_report.csv"));

        File uploadedAltCSV = service.files()
                .create(altCsvMetadata, altCsvContent)
                .setFields("id, webContentLink")
                .execute();

        service.permissions().create(uploadedAltCSV.getId(), permission).execute();
        moveFile(service, uploadedAltCSV.getId(), folderId);

        uploadedLinks.put("plpCsvLink", uploadedAltCSV.getWebContentLink());

        // ===================== Upload PDP CSV =====================
        File pdpCsvMetadata = new File();
        pdpCsvMetadata.setName("PDP_report.csv");

        FileContent pdpCsvContent =
                new FileContent("text/csv", new java.io.File("CSV/PDP_report.csv"));

        File uploadedPdpCSV = service.files()
                .create(pdpCsvMetadata, pdpCsvContent)
                .setFields("id, webContentLink")
                .execute();

        service.permissions().create(uploadedPdpCSV.getId(), permission).execute();
        moveFile(service, uploadedPdpCSV.getId(), folderId);

        uploadedLinks.put("pdpCsvLink", uploadedPdpCSV.getWebContentLink());

        return uploadedLinks;
    }

    public static String UploadADACheckJsonFile() throws GeneralSecurityException, IOException {

        Drive service = new Drive.Builder(GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, getCredentials())
                .setApplicationName(APPLICATION_NAME)
                .build();


        // Upload file
        String filepath = "C:/Users/HP/IdeaProjects/TJ_Automation/ADACheckResults";
        File fileMetadata = new File();
        fileMetadata.setName("ADACheck Report");

//        fileMetadata.setParents(Collections.singletonList(folderId));  // Set the parent folder

        java.io.File filePath = new java.io.File(filepath);
        FileContent mediaContent = new FileContent("report/json", filePath);



        File file = service.files().create(fileMetadata, mediaContent)
                .setFields("id, webContentLink")
                .execute();

        Permission permission = new Permission();
        permission.setType("anyone");
        permission.setRole("reader");

        service.permissions().create(file.getId(), permission).execute();

        moveFile(service, file.getId(), "11l284hSg0VCzeEnOt9FtgKoNxZsn7Ap5B");

        System.out.println("File ID: " + file.getId());
        System.out.println("WebContent Link: " + file.getWebContentLink());

        return file.getWebContentLink();

    }

public static String UploadScreenShot() throws IOException, GeneralSecurityException {

//        String rootDir = System.getProperty("user.dir");
//		String screenShotsFolder = rootDir+"/test-output/Screenshots/";

//    String rootDir = System.getProperty("user.dir");
//    String screenShotsFolder = "C:\\Users\\UTIS LOAN 286\\IdeaProjects\\TJ_Automation\\test-output\\Screenshots";

    Drive service = new Drive.Builder(GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, getCredentials())
            .setApplicationName(APPLICATION_NAME)
            .build();


    // Upload file
    String filepath = "C:/Users/HP/IdeaProjects/TJ_Automation/test-output/Screenshots";
    File fileMetadata = new File();
    fileMetadata.setName("Regression Screenshot");

//        fileMetadata.setParents(Collections.singletonList(folderId));  // Set the parent folder

    java.io.File filePath = new java.io.File(filepath);
    FileContent mediaContent = new FileContent("Report/jpg", filePath);


    File file = service.files().create(fileMetadata, mediaContent)
            .setFields("id, webContentLink")
            .execute();

    Permission permission = new Permission();
    permission.setType("anyone");
    permission.setRole("reader");

    service.permissions().create(file.getId(), permission).execute();

    moveFile(service, file.getId(), "1I8Z1pYmLuOZ9DvA3N9Mvu-AHz05325z9");

    System.out.println("File ID: " + file.getId());
    System.out.println("WebContent Link: " + file.getWebContentLink());

    return file.getWebContentLink();

}


}

