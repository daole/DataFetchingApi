package com.dreamdigitizers.datafetchingapis.controllers;

import com.dreamdigitizers.datafetchingapis.services.interfaces.IServiceCloudUpload;
import com.dreamdigitizers.datafetchingapis.utilities.Downloader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public abstract class ControllerDownloadAndUploadBase {
    @Autowired
    protected IServiceCloudUpload serviceGoogleDrive;

    protected String downloadAndUpload(String source, String fileName, String downloadDestinationDirectory, String uploadDestinationDirectory) throws IOException {
        String savedFileName = Downloader.download(
                source,
                downloadDestinationDirectory,
                fileName,
                true);
        if (!StringUtils.isEmpty(savedFileName)) {
            String savedFilePath = downloadDestinationDirectory + "/" + savedFileName;
            List<String> uploadDestinationDirectories = Arrays.asList(uploadDestinationDirectory);
            boolean uploadResult = ControllerDownloadAndUploadBase.this.serviceGoogleDrive.upload(savedFilePath, uploadDestinationDirectories);
            if (!uploadResult) {
                savedFileName = "";
            }
            File savedFile = new File(savedFilePath);
            savedFile.delete();
        }
        return savedFileName;
    }
}
