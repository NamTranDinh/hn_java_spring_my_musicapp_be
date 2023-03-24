package com.aptech.mymusic.presentation.service.storage;

import com.aptech.mymusic.config.ResourceConfig;
import com.aptech.mymusic.presentation.internalmodel.Resource;
import com.google.common.io.Files;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class LocalStorageService extends StorageService {

    LocalStorageService() {
    }

    @Override
    protected void init() {
    }

    @Override
    public boolean uploadFile(@NotNull MultipartFile file, @NotNull Resource.Path path, String name) {
        try {
            File to = fileOf(path, name);
            if (to.getParentFile().exists() || to.getParentFile().mkdirs()) {
                Files.write(file.getBytes(), fileOf(path, name));
                return true;
            }
        } catch (IOException ignored) {
        }
        return false;
    }

    @Override
    public boolean deleteFile(@NotNull Resource.Path path, String name) {
        File file = fileOf(path, name);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    @Nullable
    public static String getUrl(@NotNull Resource.Path path, String name) {
        if (Resource.getBaseUrl() == null) {
            return null;
        }
        return Resource.getBaseUrl() + ResourceConfig.getInstance().getLocalResourceSuffix() + path.getPath() + name;
    }

    @NotNull
    private static File fileOf(@NotNull Resource.Path path, String name) {
        return new File(ResourceConfig.getInstance().getLocalResourcePath(), path.getPath() + name);
    }

}
