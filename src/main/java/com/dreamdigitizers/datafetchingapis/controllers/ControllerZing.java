package com.dreamdigitizers.datafetchingapis.controllers;

import com.dreamdigitizers.datafetchingapis.exceptions.ExceptionSongNotFound;
import com.dreamdigitizers.datafetchingapis.models.MusicZing;
import com.dreamdigitizers.datafetchingapis.repositories.IRepositoryMusicZing;
import com.dreamdigitizers.datafetchingapis.services.interfaces.IServiceZing;
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
import java.util.Date;

@RestController
@RequestMapping("/zing")
public class ControllerZing extends ControllerDownloadAndUploadBase {
    private Thread downloadThread;

    @Value("${fileName.downloadFileName}")
    private String downloadFileName;

    @Value("${resources.downloads.zing}")
    private String downloadsDirectory;

    @Value("${google.drive.folders.uploads.zing.128kbps}")
    private String uploadsDirectory128kbps;

    @Value("${google.drive.folders.uploads.zing.320kbps}")
    private String uploadsDirectory320kbps;

    @Value("${constants.refetchMilliseconds}")
    private long refetchMilliseconds;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private IServiceZing serviceZing;

    @Autowired
    private IRepositoryMusicZing repositoryMusicZing;

    @RequestMapping(value = "/fetch", method = RequestMethod.GET)
    public MusicZing fetch(@RequestParam(value="name") String name, @RequestParam(value="artist") String artist, @RequestParam(value="id") String id) throws ExceptionSongNotFound {
        boolean shouldFetch = false;
        boolean shouldDownload = false;
        String fileName = null;
        MusicZing musicZing = this.repositoryMusicZing.findOne(id);
        if (musicZing == null) {
            shouldFetch = true;
            shouldDownload = true;
        } else {
            fileName = musicZing.getFileName();
            Date now = new Date();
            long differentMilliseconds = now.getTime() - musicZing.getLastFetchDatetime().getTime();
            if (differentMilliseconds > this.refetchMilliseconds) {
                shouldFetch = true;
            }
        }
        if (shouldFetch) {
            try {
                musicZing = this.serviceZing.fetch(name, artist, id);
                if (!StringUtils.isEmpty(fileName)) {
                    musicZing.setFileName(fileName);
                }
            } catch (IOException e) {
                throw new ExceptionSongNotFound(this.messageSource, name, e);
            }
            if (musicZing == null) {
                throw new ExceptionSongNotFound(this.messageSource, name);
            }
            this.saveMusic(musicZing, shouldDownload);
        }
        return musicZing;
    }

    private void saveMusic(final MusicZing musicZing, final boolean shouldDownload) {
        this.downloadThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    boolean shouldSave = true;
                    if (shouldDownload) {
                        shouldSave = false;
                        String downloadDestinationDirectory = Paths.get(
                                this.getClass()
                                        .getClassLoader()
                                        .getResource(ControllerZing.this.downloadsDirectory)
                                        .toURI())
                                .toFile()
                                .getAbsolutePath();
                        String fileName = String.format(ControllerZing.this.downloadFileName, musicZing.getTitle(), musicZing.getId());
                        String savedFileName = ControllerZing.this.downloadAndUpload(
                                musicZing.getSource(),
                                fileName,
                                downloadDestinationDirectory,
                                ControllerZing.this.uploadsDirectory128kbps);
                        if (!StringUtils.isEmpty(savedFileName)) {
                            savedFileName = ControllerZing.this.downloadAndUpload(
                                    musicZing.getHq(),
                                    fileName,
                                    downloadDestinationDirectory,
                                    ControllerZing.this.uploadsDirectory320kbps);
                            if (!StringUtils.isEmpty(savedFileName)) {
                                musicZing.setFileName(savedFileName);
                                shouldSave = true;
                            }
                        }
                    }
                    if (shouldSave) {
                        ControllerZing.this.repositoryMusicZing.save(musicZing);
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
}
