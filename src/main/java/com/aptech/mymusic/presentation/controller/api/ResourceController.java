package com.aptech.mymusic.presentation.controller.api;

import com.aptech.mymusic.presentation.internalmodel.FirebasePath;
import com.aptech.mymusic.presentation.internalmodel.Response;
import com.aptech.mymusic.presentation.service.FirebaseStorageService;
import com.google.firebase.database.utilities.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nullable;
import java.util.UUID;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/resource")
public class ResourceController {
    private final FirebaseStorageService firebaseStorageService;

    public ResourceController(FirebaseStorageService firebaseStorageService) {
        this.firebaseStorageService = firebaseStorageService;
    }

    /**
     * Upload a file to firebase.
     *
     * @param file a file part
     * @param type type of Path {@link FirebasePath#ordinal() }
     * @param name name expect of the file
     * @return success => 200
     * @see FirebasePath#ADS
     * @see FirebasePath#ALBUMS
     * @see FirebasePath#CATEGORIES
     * @see FirebasePath#PLAYLISTS
     * @see FirebasePath#SONGS
     * @see FirebasePath#TOPIC
     * @see FirebasePath#AUDIO
     */
    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam(name = "data") MultipartFile file,
                                        @RequestParam(name = "type") int type,
                                        @RequestParam(name = "name", required = false) String name) {
        if (type < 0 || type >= FirebasePath.values().length) {
            Response response = Response.error(String.format("Type from 0 to %d", FirebasePath.values().length - 1));
            return ResponseEntity.badRequest().body(response.body());
        }
        try {
            FirebasePath path = FirebasePath.values()[type];
            Pair<Boolean, String> valid = path.validFile(file);
            if (valid.getFirst() == null || !valid.getFirst()) {
                return ResponseEntity.internalServerError().body(Response.error(valid.getSecond()).body());
            }
            if (!isValidUUID(name)) {
                name = UUID.randomUUID().toString();
            }
            return firebaseStorageService.uploadFile(file, path, name)
                    ? ResponseEntity.ok().body(Response.ok()
                    .put("path", path.getPath())
                    .put("name", name)
                    .body())
                    : ResponseEntity.internalServerError().body(Response.error("Fail to upload file.").body());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Response.error(e.getMessage()).body());
        }
    }

    /**
     * Delete a file from firebase.
     *
     * @param type type of Path {@link FirebasePath#ordinal() }
     * @param name name expect of the file
     * @return success => 200
     * @see FirebasePath#ADS
     * @see FirebasePath#ALBUMS
     * @see FirebasePath#CATEGORIES
     * @see FirebasePath#PLAYLISTS
     * @see FirebasePath#SONGS
     * @see FirebasePath#TOPIC
     * @see FirebasePath#AUDIO
     */
    @PostMapping("/delete")
    public ResponseEntity<?> deleteFile(@RequestParam(name = "type") int type,
                                        @RequestParam(name = "name") String name) {
        if (type < 0 || type >= FirebasePath.values().length) {
            Response response = Response.error(String.format("Type from 0 to %d", FirebasePath.values().length - 1));
            return ResponseEntity.badRequest().body(response.body());
        }
        try {
            FirebasePath path = FirebasePath.values()[type];
            return firebaseStorageService.deleteFile(path, name)
                    ? ResponseEntity.ok().body(Response.ok()
                    .put("path", path.getPath())
                    .put("name", name)
                    .body())
                    : ResponseEntity.internalServerError().body(Response.error("Fail to delete file.").body());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Response.error(e.getMessage()).body());
        }
    }

    private boolean isValidUUID(@Nullable String uuid) {
        if (uuid == null) {
            return false;
        }
        Pattern UUID_REGEX = Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");
        return UUID_REGEX.matcher(uuid).matches();
    }

}
