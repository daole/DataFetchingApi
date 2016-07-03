package com.dreamdigitizers.datafetchingapis.controllers;

import com.dreamdigitizers.datafetchingapis.exceptions.ExceptionSongNotFound;
import com.dreamdigitizers.datafetchingapis.models.MusicNct;
import com.dreamdigitizers.datafetchingapis.repositories.IRepositoryMusicNct;
import com.dreamdigitizers.datafetchingapis.services.interfaces.IServiceNct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

@RestController
@RequestMapping("/nct")
public class ControllerNct extends ControllerDownloadAndUploadBase {
    private Thread downloadThread;

    @Value("${fileName.downloadFileName}")
    private String downloadFileName;

    @Value("${resources.downloads.nct}")
    private String downloadsDirectory;

    @Value("${google.drive.folders.uploads.nct.128kbps}")
    private String uploadsDirectory128kbps;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private IServiceNct serviceNct;

    @Autowired
    private IRepositoryMusicNct repositoryMusicNct;

    @RequestMapping(value = "/fetch", method = RequestMethod.GET)
    public MusicNct fetch(@RequestParam(value="url") String url, @RequestParam(value="keyword") String keyword) throws ExceptionSongNotFound {
        String id = this.extractMusicIdFromUrl(url);
        if (StringUtils.isEmpty(id)) {
            throw new ExceptionSongNotFound(this.messageSource, keyword);
        }
        MusicNct musicNct = this.repositoryMusicNct.findOne(id);
        if (musicNct == null) {
            try {
                musicNct = this.serviceNct.fetch(url);
            } catch (IOException e) {
                throw new ExceptionSongNotFound(this.messageSource, keyword, e);
            }
            if (musicNct == null) {
                throw new ExceptionSongNotFound(this.messageSource, keyword);
            }
            this.saveMusic(musicNct);
        }
        return musicNct;
    }

    private void saveMusic(final MusicNct musicNct) {
        this.downloadThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String downloadDestinationDirectory = Paths.get(
                            this.getClass()
                                    .getClassLoader()
                                    .getResource(ControllerNct.this.downloadsDirectory)
                                    .toURI())
                            .toFile()
                            .getAbsolutePath();
                    String fileName = String.format(ControllerNct.this.downloadFileName, musicNct.getTitle(), musicNct.getId());
                    String savedFileName = ControllerNct.this.downloadAndUpload(
                            musicNct.getLocation(),
                            fileName,
                            downloadDestinationDirectory,
                            ControllerNct.this.uploadsDirectory128kbps);
                    if (!StringUtils.isEmpty(savedFileName)) {
                        musicNct.setFileName(savedFileName);
                        ControllerNct.this.repositoryMusicNct.save(musicNct);
                    }
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        this.downloadThread.start();
    }

    private String extractMusicIdFromUrl(String url) {
        int lastDotIndex = url.lastIndexOf(".");
        if (lastDotIndex > 0) {
            int secondLastDotIndex = url.lastIndexOf(".", lastDotIndex - 1);
            if (secondLastDotIndex >= 0 && secondLastDotIndex + 1 < lastDotIndex) {
                return url.substring(secondLastDotIndex + 1, lastDotIndex);
            }
        }
        return null;
    }
}
