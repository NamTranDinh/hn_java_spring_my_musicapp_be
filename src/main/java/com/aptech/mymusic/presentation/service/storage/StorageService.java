package com.aptech.mymusic.presentation.service.storage;

import com.aptech.mymusic.presentation.internalmodel.Resource;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.multipart.MultipartFile;

public abstract class StorageService {

    StorageService() {
        init();
    }

    protected abstract void init();

    public abstract boolean uploadFile(@NotNull MultipartFile file, @NotNull Resource.Path path, String name);

    public abstract boolean deleteFile(@NotNull Resource.Path path, String name);

}
