package com.dreamdigitizers.datafetchingapis.controllers;

import com.dreamdigitizers.datafetchingapis.controllers.services.interfaces.IServiceZing;
import com.dreamdigitizers.datafetchingapis.models.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/zing")
public class ControllerZing {
    @Autowired
    private IServiceZing serviceZing;

    @RequestMapping(value = "/fetch", method = RequestMethod.GET)
    public Song fetch(@RequestParam(value="name") String name, @RequestParam(value="artist") String artist, @RequestParam(value="id") String id) throws IOException {
        Song song = serviceZing.fetch(name, artist, id);
        return song;
    }
}
