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

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;


public class GdriveUpload {

    private static final String APPLICATION_NAME = "Tommy John";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "Tokens";

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE_FILE);
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




    //    public static String UploadReport() throws IOException, GeneralSecurityException {
    public static void main(String args[]) throws IOException, GeneralSecurityException {
        // Build a new authorized API client service.
        Drive service = new Drive.Builder(GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, getCredentials())
                .setApplicationName(APPLICATION_NAME)
                .build();

//        String folderId = "1AubNi7muTDJdsmkNa5ZA8qCgEcw_WOrh";  // Replace with your folder ID

//        String query = "'" + folderId + "' in parents and mimeType = 'application/vnd.google-apps.folder' and trashed = false";
        String query = "mimeType = 'application/vnd.google-apps.folder'";

        FileList result = service.files().list()
                .setQ(query)
                .setSpaces("drive")
                .setFields("nextPageToken, files(id, name, owners, shared, sharedWithMeTime)")
                .setPageSize(100)
                .execute();

        List<File> files = result.getFiles();
        if (files == null || files.isEmpty()) {
            System.out.println("No folders found.");
            return;
        }

        // Display shared folders
        System.out.println("All Folders:");
        for (File file : files) {
            System.out.printf("ID: %s, Name: %s, Shared: %s, Owners: %s, Shared With Me Time: %s\n",
                    file.getId(), file.getName(), file.getShared(), file.getOwners(), file.getSharedWithMeTime());
        }

        // Check if the folder we want to upload to exists in the list
        String targetFolderName = "CMKTS";  // Replace this with your target folder name
        String sharedFolderId = null;
        for (File file : files) {
            if (targetFolderName.equals(file.getName())) {
                sharedFolderId = file.getId();
                break;
            }
        }

        if (sharedFolderId == null) {
            System.out.println("Target folder not found in the shared with me list.");
            return;
        }


        // Upload file
        String filepath = "C:/Users/HP/IdeaProjects/TJ_Automation/test-output/extentreport.html";
        File fileMetadata = new File();
        fileMetadata.setName("Regression Report");

        fileMetadata.setParents(Collections.singletonList(sharedFolderId));  // Set the parent folder

        java.io.File filePath = new java.io.File(filepath);
        FileContent mediaContent = new FileContent("text/html", filePath);



        File file = service.files().create(fileMetadata, mediaContent)
                .setFields("id, parents, webViewLink, webContentLink")
                .execute();


        System.out.println("File ID: " + file.getId());
        System.out.println("WebView Link: " + file.getWebViewLink());
        System.out.println("WebContent Link: " + file.getWebContentLink());

//        return file.getWebContentLink();
    }


}

