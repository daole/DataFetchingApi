package com.dreamdigitizers.datafetchingapis.services.interfaces;

import com.dreamdigitizers.datafetchingapis.models.MusicNct;

import java.io.IOException;

public interface IServiceNct {
    MusicNct fetch(String url) throws IOException;
}
