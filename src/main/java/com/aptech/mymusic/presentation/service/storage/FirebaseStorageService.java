package com.aptech.mymusic.presentation.service.storage;

import com.aptech.mymusic.presentation.internalmodel.Resource;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.TestOnly;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class FirebaseStorageService extends StorageService {

    public static final String PROJECT_ID = "mymusic-21f69";
    public static final String BUCKET_NAME = "mymusic-21f69.appspot.com";
    public static final String CREDENTIALS_PATH = "./firebase/mymusic-21f69-firebase-adminsdk-lvv54-f1a35b2cb5.json";

    FirebaseStorageService() {
    }

    @Override
    protected void init() {
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(getCredentials())
                .setProjectId(PROJECT_ID)
                .setStorageBucket(BUCKET_NAME)
                .build();
        FirebaseApp.initializeApp(options);
    }

    @Override
    public boolean uploadFile(@NotNull MultipartFile file, @NotNull Resource.Path path, String name) {
        try {
            getStorage().create(BlobInfo.newBuilder(blobOf(path, name))
                    .setContentType(file.getContentType())
                    .build(), file.getBytes());
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public boolean deleteFile(@NotNull Resource.Path path, String name) {
        return getStorage().delete(blobOf(path, name));
    }

    @TestOnly
    public boolean uploadFile(String contentType, @NotNull File file, @NotNull Resource.Path path, String name) {
        try {
            getStorage().create(BlobInfo.newBuilder(blobOf(path, name))
                    .setContentType(contentType)
                    .build(), Files.readAllBytes(file.toPath()));
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Nullable
    public static String getUrl(@NotNull Resource.Path path, String name) {
        Blob blob;
        if ((blob = getStorage().get(blobOf(path, name))) == null) {
            return null;
        }
        return blob.signUrl(1, TimeUnit.HOURS).toString();
    }


    ///////////////////////////////////////////////////////////////////////////
    // Private area
    ///////////////////////////////////////////////////////////////////////////

    private static Storage storage;

    private static Storage getStorage() {
        if (storage == null) {
            storage = StorageOptions.newBuilder().setCredentials(getCredentials()).build().getService();
        }
        return storage;
    }

    private static GoogleCredentials getCredentials() {
        try {
            return GoogleCredentials.fromStream(Files.newInputStream(Paths.get(CREDENTIALS_PATH)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    private static BlobId blobOf(@NotNull Resource.Path path, String name) {
        return BlobId.of(BUCKET_NAME, path.getPath() + name);
    }
}
