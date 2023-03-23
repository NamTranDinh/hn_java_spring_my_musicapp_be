package com.aptech.mymusic.presentation.service.storage;

import com.aptech.mymusic.presentation.internalmodel.Resource;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.web.multipart.MultipartFile;

public class AmazonStorageService extends StorageService {

    AmazonStorageService() {
    }

    @Override
    protected void init() {

    }

    @Override
    public boolean uploadFile(@NotNull MultipartFile file, Resource.@NotNull Path path, String name) {
        return false;
    }

    @Override
    public boolean deleteFile(Resource.@NotNull Path path, String name) {
        return false;
    }

    @Nullable
    public static String getUrl(@NotNull Resource.Path path, String name) {
        return null;
    }

}
