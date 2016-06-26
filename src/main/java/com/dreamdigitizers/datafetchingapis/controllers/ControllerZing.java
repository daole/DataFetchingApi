package com.dreamdigitizers.datafetchingapis.controllers;

import com.dreamdigitizers.datafetchingapis.exceptions.ExceptionSongNotFound;
import com.dreamdigitizers.datafetchingapis.repositories.IRepositoryMusicZing;
import com.dreamdigitizers.datafetchingapis.services.interfaces.IServiceZing;
import com.dreamdigitizers.datafetchingapis.models.MusicZing;
import com.dreamdigitizers.datafetchingapis.utilities.Downloader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Date;

@RestController
@RequestMapping("/zing")
public class ControllerZing {
    private Thread downloadThread;

    @Value("${fileName.downloadFileName}")
    private String downloadFileName;

    @Value("${resources.downloads.zing}")
    private String downloadsDirectory;

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
            this.downloadAndSaveSongInformation(musicZing, shouldDownload);
        }
        return musicZing;
    }

    private void downloadAndSaveSongInformation(final MusicZing musicZing, final boolean shouldDownload) {
        this.downloadThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (shouldDownload) {
                        String destinationDirectory = Paths.get(
                                this.getClass().getClassLoader().getResource(ControllerZing.this.downloadsDirectory).toURI()
                        ).toFile().getAbsolutePath();
                        String savedFileName = Downloader.download(musicZing.getSource(), destinationDirectory, String.format(ControllerZing.this.downloadFileName, musicZing.getTitle(), musicZing.getId()), true);
                        musicZing.setFileName(savedFileName);
                    }
                    if (!StringUtils.isEmpty(musicZing.getFileName())) {
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
