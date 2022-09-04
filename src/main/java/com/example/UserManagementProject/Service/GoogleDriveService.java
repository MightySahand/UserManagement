package com.example.UserManagementProject.Service;

import com.example.UserManagementProject.Annotation.LogNeeded;
import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleBrowserClientRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
/* class to demonstrate use of Drive files list API */
public class GoogleDriveService {

    public static final String CLIENT_ID = "1002117847851-qo0tpq1585q2gm24guqnsbed7c14tgi1.apps.googleusercontent.com";
    /**
     * Application name.
     */
    private static final String APPLICATION_NAME = "Google Drive API Java Quickstart";
    /**
     * Global instance of the JSON factory.
     */
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    /**
     * Directory to store authorization tokens for this application.
     */
    private static final String TOKENS_DIRECTORY_PATH = "G:\\new\\tokens";

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE_FILE);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";


    // Build a new authorized API client service.
    static NetHttpTransport HTTP_TRANSPORT;
    static Drive service;

    /**
     * Creates an authorized Credential object.
     *
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */

    public String getAuthorizationURL() {
        return new GoogleBrowserClientRequestUrl(CLIENT_ID, "http://localhost:8080/callback", SCOPES)
                .setState("/profile")
                .build();
    }

    @LogNeeded
    public Credential getCredentials(String accessToken) {
//        // Load client secrets.
//        InputStream in = GoogleDriveService.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
//        if (in == null) {
//            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
//        }
//        GoogleClientSecrets clientSecrets =
//                GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
//
//        // Build flow and trigger user authorization request.
//        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
//                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
//                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
//                .setAccessType("offline").setApprovalPrompt("force")
//                .build();
//        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
////        //returns an authorized Credential object.
//        new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
        Credential credential = new Credential(BearerToken.authorizationHeaderAccessMethod());
        credential.setAccessToken(accessToken);
        return credential;
    }

    public void getService(Credential credentials) throws GeneralSecurityException, IOException {
        HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, credentials)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    @LogNeeded
    public void uploadTextFile(String name, String path) {
        // Upload file Sahand.txt on drive.
        File fileMetadata = new File();
        fileMetadata.setName(name);
        // File's content.
        java.io.File filePath = new java.io.File(path + "\\" + name);
        // Specify media type and file-path for file.
        FileContent mediaContent = new FileContent("text/plain", filePath);
        try {
            File file = service.files().create(fileMetadata, mediaContent)
                    .setFields("id")
                    .execute();
            System.out.println("File ID: " + file.getId());
            System.err.println(file.getId());
        } catch (GoogleJsonResponseException e) {
            // TODO(developer) - handle error appropriately
            System.err.println("Unable to upload file: " + e.getDetails());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @LogNeeded
    public void printFilesInformation(int amount) {
        // Print the names and IDs for up to "amount" files.
        FileList result;
        try {
            result = service.files().list()
                    .setPageSize(amount)
                    .setFields("nextPageToken, files(id, name)")
                    .execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<File> files = result.getFiles();
        if (files == null || files.isEmpty()) {
            System.out.println("No files found.");
        } else {
            System.out.println("Files:");
            for (File file : files) {
                System.out.printf("%s (%s)\n", file.getName(), file.getId(), file.getId());
            }
        }
    }

    @LogNeeded
    public void makeFolder(String name) {
        // File's metadata.
        File fileMetadata = new File();
        fileMetadata.setName(name);
        fileMetadata.setMimeType("application/vnd.google-apps.folder");
        try {
            File file = service.files().create(fileMetadata)
                    .setFields("id")
                    .execute();
            System.out.println("Folder ID: " + file.getId());
            System.out.println(file.getId());
        } catch (GoogleJsonResponseException e) {
            // TODO(developer) - handle error appropriately
            System.err.println("Unable to create folder: " + e.getDetails());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @LogNeeded
    public void downloadFile(String realFileId) throws GoogleJsonResponseException {
        try {
            OutputStream outputStream = new ByteArrayOutputStream();

            service.files().get(realFileId)
                    .executeMediaAndDownloadTo(outputStream);

            System.out.println(outputStream);
        } catch (GoogleJsonResponseException e) {
            // TODO(developer) - handle error appropriately
            System.err.println("Unable to move file: " + e.getDetails());
            throw e;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @LogNeeded
    public List<File> searchFile() throws IOException {
        List<File> files = new ArrayList<>();
        String pageToken = null;
        do {
            // setQ -> is for setting a query for the search
            // In this example the method finds the jpeg files
            FileList result = service.files().list()
                    .setQ("name = 'Sina' and mimeType = 'text/plain'")
                    .setSpaces("drive")
                    .setFields("name")
                    .setPageToken(pageToken)
                    .execute();
            for (File file : result.getFiles()) {
                System.out.printf("Found file: %s (%s)\n",
                        file.getName(), file.getId());
            }
            files.addAll(result.getFiles());
            pageToken = result.getNextPageToken();
        } while (pageToken != null);

        return files;
    }

    @LogNeeded
    public void storeApplicationSpecificData() {
        try {
            // File's metadata.
            File fileMetadata = new File();
            fileMetadata.setName("config.json");
            fileMetadata.setParents(Collections.singletonList("appDataFolder"));
            java.io.File filePath = new java.io.File("files/config.json");
            FileContent mediaContent = new FileContent("application/json", filePath);
            File file = service.files().create(fileMetadata, mediaContent)
                    .setFields("id")
                    .execute();
            System.out.println("File ID: " + file.getId());
            System.err.println(file.getId());
        } catch (GoogleJsonResponseException e) {
            // TODO(developer) - handle error appropriately
            System.err.println("Unable to create file: " + e.getDetails());
            System.err.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}