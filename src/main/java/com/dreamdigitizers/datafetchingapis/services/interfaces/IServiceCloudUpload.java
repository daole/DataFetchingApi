package com.dreamdigitizers.datafetchingapis.services.interfaces;

import java.util.List;

public interface IServiceCloudUpload {
    boolean upload(String filePath, List<String> destinationDirectories);
}
