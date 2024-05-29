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
import java.util.List;

public class GDrivePOST {

    private static final String APPLICATION_NAME = "Tommy John";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "Tokens";

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE);
    private static final String CREDENTIALS_FILE_PATH = "src/main/java/com/base/client_secret_968650974052-jge0d9f8s1kqsncl0k1qhojpnf6c60sh.apps.googleusercontent.com.json";

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


    public static void moveFile(Drive service, String fileID, String folderID) throws IOException {

        String query = "mimeType = 'application/vnd.google-apps.folder' and sharedWithMe";
        FileList result = service.files().list()
                .setQ(query)
                .setSpaces("drive")
                .setFields("nextPageToken, files(id, name)")
                .execute();

//        for (File file : result.getFiles()) {
//            System.out.printf("Found shared folder: %s (%s)\n", file.getName(), file.getId());
//        }

        // Now, assuming you know the shared folder ID, list nested folders inside it
        String sharedFolderId = "14h5xRVL6L--iDvaucRBz1_W0xuI2d_2e"; // Replace with your shared folder ID
        query = String.format("'%s' in parents and mimeType = 'application/vnd.google-apps.folder'", sharedFolderId);
        FileList nestedResult = service.files().list()
                .setQ(query)
                .setSpaces("drive")
                .setFields("nextPageToken, files(id, name)")
                .execute();

//        for (File nestedFile : nestedResult.getFiles()) {
//            System.out.printf("Found nested folder: %s (%s)\n", nestedFile.getName(), nestedFile.getId());
//        }

        File file = service.files().get(fileID)
                .setFields("parents")
                .execute();
        StringBuilder previousParents = new StringBuilder();
        for (String parent : file.getParents()) {
            previousParents.append(parent);
            previousParents.append(',');
        }

        // Move the file to the nested shared folder

        file = service.files().update(fileID, null)
                .setAddParents(folderID)
                .setRemoveParents(previousParents.toString())
                .setFields("id, parents")
                .execute();

                // Retrieve the existing parents to remove
//        File file = service.files().get(fileID)
//                .setFields("parents")
//                .execute();
//        StringBuilder previousParents = new StringBuilder();
//        for (String parent : file.getParents()) {
//            previousParents.append(parent);
//            previousParents.append(',');
//        }
//
//        // Move the file to the new folder
//        file = service.files().update(fileID, null)
//                .setAddParents(folderID)
//                .setRemoveParents(previousParents.toString())
//                .setFields("id, parents")
//                .execute();
//
//        System.out.println("File moved to folder: " + folderID);

    }

    public static String UploadReport() throws IOException, GeneralSecurityException {
//        public static void main(String args[]) throws IOException, GeneralSecurityException {
        // Build a new authorized API client service.
        Drive service = new Drive.Builder(GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, getCredentials())
                .setApplicationName(APPLICATION_NAME)
                .build();


        // Upload file
        String filepath = "C:/Users/HP/IdeaProjects/TJ_Automation/test-output/extentreport.html";
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

        moveFile(service, file.getId(), "1AubNi7muTDJdsmkNa5ZA8qCgEcw_WOrh");

        System.out.println("File ID: " + file.getId());
        System.out.println("WebContent Link: " + file.getWebContentLink());

        return file.getWebContentLink();
    }

public static String UploadScreenShot() throws IOException, GeneralSecurityException {

    String rootDir = System.getProperty("user.dir");
    String screenShotsFolder = "C:\\Users\\UTIS LOAN 286\\IdeaProjects\\TJ_Automation\\test-output\\Screenshots";

    Drive service = new Drive.Builder(GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, getCredentials())
            .setApplicationName(APPLICATION_NAME)
            .build();


    // Upload file
    String filepath = screenShotsFolder;
    File fileMetadata = new File();
    fileMetadata.setName("Regression Report");

//        fileMetadata.setParents(Collections.singletonList(folderId));  // Set the parent folder

    java.io.File filePath = new java.io.File(filepath);
    FileContent mediaContent = new FileContent("Report/.jpg", filePath);


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

