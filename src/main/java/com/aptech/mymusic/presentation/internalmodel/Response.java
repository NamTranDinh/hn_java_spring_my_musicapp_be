package com.aptech.mymusic.presentation.internalmodel;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class Response {

    public static final String KEY_STATUS = "status";
    public static final String KEY_ERROR = "error";
    public static final String KEY_DATA = "data";

    private final Map<String, Object> data = new HashMap<>();

    private Response() {
    }

    @NotNull
    public static Response ok() {
        Response response = new Response();
        response.data.put(KEY_STATUS, 200);
        return response;
    }

    @NotNull
    public static Response error(String error) {
        Response response = new Response();
        response.data.put(KEY_STATUS, 400);
        response.data.put(KEY_ERROR, error);
        return response;
    }


    @NotNull
    public static Response data(Object data) {
        Response response = new Response();
        response.data.put(KEY_STATUS, 200);
        response.data.put(KEY_DATA, data);
        return response;
    }

    public Response put(String key, Object val) {
        data.put(key, val);
        return this;
    }

    public Map<String, Object> body() {
        return data;
    }

}
