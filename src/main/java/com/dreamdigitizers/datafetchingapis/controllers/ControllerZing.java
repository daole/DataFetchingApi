package com.dreamdigitizers.datafetchingapis.controllers;

import com.dreamdigitizers.datafetchingapis.controllers.repositories.zing.IMusicZingRepository;
import com.dreamdigitizers.datafetchingapis.controllers.services.interfaces.IServiceZing;
import com.dreamdigitizers.datafetchingapis.models.Song;
import com.dreamdigitizers.datafetchingapis.utilities.Downloader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

@RestController
@RequestMapping("/zing")
public class ControllerZing {
    private Thread downloadThread;

    @Value("${fileName.downloadFileName}")
    private String downloadFileName;

    @Value("${resources.downloads}")
    private String downloadsDirectory;

    @Autowired
    private IServiceZing serviceZing;

    @Autowired
    private IMusicZingRepository musicZingRepository;

    @RequestMapping(value = "/fetch", method = RequestMethod.GET)
    public Song fetch(@RequestParam(value="name") String name, @RequestParam(value="artist") String artist, @RequestParam(value="id") String id) throws IOException {
        Song song = this.musicZingRepository.findOne(id);
        if (song == null) {
            song = serviceZing.fetch(name, artist, id);
            if (song != null) {
                this.downloadAndSaveSongInformation(song);
            }
        }
        return song;
    }

    private void downloadAndSaveSongInformation(final Song song) {
        this.downloadThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String destinationDirectory = Paths.get(
                            this.getClass()
                                    .getClassLoader()
                                    .getResource(ControllerZing.this.downloadsDirectory)
                                    .toURI()
                    ).toFile().getAbsolutePath();
                    String savedFileName = Downloader.download(song.getSource(), destinationDirectory, String.format(ControllerZing.this.downloadFileName, song.getTitle(), song.getId()), true);
                    if (!StringUtils.isEmpty(savedFileName)) {
                        song.setFileName(savedFileName);
                        ControllerZing.this.musicZingRepository.save(song);
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
