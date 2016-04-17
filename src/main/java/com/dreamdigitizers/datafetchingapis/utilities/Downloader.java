package com.dreamdigitizers.datafetchingapis.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Downloader {
    public static void download(String resourceUrl, String destinationDirectory, String fileName) throws IOException {
        InputStream inputStream = null;
        try {
            URL url = new URL(resourceUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setInstanceFollowRedirects(true);
            inputStream = httpURLConnection.getInputStream();
            String extension = Downloader.getFileExtension(httpURLConnection.getURL().toString());
            if (extension != null && !extension.isEmpty()) {
                fileName += "." + extension;
            }
            Path destinationPath = FileSystems.getDefault().getPath(destinationDirectory, fileName);
            Files.copy(inputStream, destinationPath, StandardCopyOption.REPLACE_EXISTING);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    private static String getFileExtension(String url) {
        String extension = null;
        if (url != null && !url.isEmpty()) {
            int lastDotIndex = url.lastIndexOf(".");
            if (lastDotIndex >= 0 && lastDotIndex < url.length() - 1) {
                extension = url.substring(url.lastIndexOf(".") + 1, url.length());
            }
        }
        return extension;
    }
}
