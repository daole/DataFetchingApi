package com.dreamdigitizers.datafetchingapis.services.interfaces;

import com.dreamdigitizers.datafetchingapis.models.Song;

import java.io.IOException;

public interface IServiceZing {
    Song fetch(String name, String artist, String id) throws IOException;
}
