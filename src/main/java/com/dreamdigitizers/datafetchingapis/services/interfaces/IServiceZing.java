package com.dreamdigitizers.datafetchingapis.services.interfaces;

import com.dreamdigitizers.datafetchingapis.models.MusicZing;

import java.io.IOException;

public interface IServiceZing {
    MusicZing fetch(String name, String artist, String id) throws IOException;
}
