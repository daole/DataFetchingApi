package com.dreamdigitizers.datafetchingapis.services.classes;

import com.dreamdigitizers.datafetchingapis.services.interfaces.IServiceCloudUpload;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.DataStoreCredentialRefreshListener;
import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.DataStore;
import com.google.api.client.util.store.MemoryDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.List;

public class ServiceGoogleDrive implements IServiceCloudUpload {
    @Value("${google.applicationName}")
    private String applicationName;

    @Value("${google.drive.username}")
    private String googleDriveUsername;

    @Value("${google.drive.clientId}")
    private String googleDriveClientId;

    @Value("${google.drive.clientSecret}")
    private String googleDriveClientSecret;

    @Value("${google.drive.refreshToken}")
    private String googleDriveRefreshToken;

    @Value("${google.drive.credentialFile}")
    private String googleCredentialFile;

    private boolean isInitialized;
    private HttpTransport httpTransport;
    private JsonFactory jsonFactory;
    private DataStore<StoredCredential> dataStore;
    private Credential credential;

    @Override
    public boolean upload(String filePath, List<String> destinationDirectories) {
        boolean result = false;
        InputStream inputStream = null;
        try {
            if (!this.isInitialized) {
                this.initialize();
            }

            String fileName = new java.io.File(filePath).getName();
            String mimeType = Files.probeContentType(Paths.get(filePath));
            File file = new File();
            file.setName(fileName);
            file.setMimeType(mimeType);
            if (destinationDirectories != null && !destinationDirectories.isEmpty()) {
                file.setParents(destinationDirectories);
            }

            inputStream = new FileInputStream(filePath);
            InputStreamContent inputStreamContent = new InputStreamContent(mimeType, new BufferedInputStream(inputStream));

            Drive drive = this.buildDriveService();
            drive.files()
                    .create(file, inputStreamContent)
                    .execute();
            result = true;
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    private void initialize() throws URISyntaxException, IOException, GeneralSecurityException {
        this.httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        this.jsonFactory = JacksonFactory.getDefaultInstance();
        this.dataStore = MemoryDataStoreFactory.getDefaultInstance().getDataStore("credentialDataStore");
        this.credential = new GoogleCredential.Builder()
                .setTransport(this.httpTransport)
                .setJsonFactory(this.jsonFactory)
                .setClientSecrets(this.googleDriveClientId, this.googleDriveClientSecret)
                .addRefreshListener(new DataStoreCredentialRefreshListener(this.googleDriveUsername, this.dataStore))
                .build();

        this.isInitialized = true;
    }

    private Drive buildDriveService() throws IOException {
        if (this.dataStore.containsKey(this.googleDriveUsername)) {
            StoredCredential storedCredential = this.dataStore.get(this.googleDriveUsername);
            this.credential.setAccessToken(storedCredential.getAccessToken());
            this.credential.setRefreshToken(storedCredential.getRefreshToken());
        } else {
            this.credential.setRefreshToken(this.googleDriveRefreshToken);
            this.credential.refreshToken();
        }

        Drive drive = new Drive.Builder(this.httpTransport, this.jsonFactory, this.credential)
                .setApplicationName(this.applicationName)
                .build();

        return drive;
    }
}
