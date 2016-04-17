package com.dreamdigitizers.datafetchingapis.utilities;

import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Downloader {
    public static String download(String resourceUrl, String destinationDirectory, String fileName, boolean isTryingToGetFileExtension) throws IOException {
        InputStream inputStream = null;
        String savedFileName = null;
        try {
            URL url = new URL(resourceUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setInstanceFollowRedirects(true);
            inputStream = httpURLConnection.getInputStream();
            if (isTryingToGetFileExtension) {
                String extension = Downloader.getFileExtension(httpURLConnection.getURL().toString());
                if (!StringUtils.isEmpty(extension)) {
                    if (fileName.charAt(fileName.length() - 1) != '.') {
                        fileName += ".";
                    }
                    fileName += extension;
                }
            }
            Path destinationPath = FileSystems.getDefault().getPath(destinationDirectory, fileName);
            Files.copy(inputStream, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            savedFileName = fileName;
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            return savedFileName;
        }
    }

    private static String getFileExtension(String url) {
        String extension = null;
        if (!StringUtils.isEmpty(url)) {
            int lastDotIndex = url.lastIndexOf(".");
            if (lastDotIndex >= 0 && lastDotIndex < url.length() - 1) {
                extension = url.substring(url.lastIndexOf(".") + 1, url.length());
            }
        }
        return extension;
    }
}
