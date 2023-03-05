package com.aptech.mymusic.utils;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class UrlUtils {
    public static @NotNull String getBaseUrl() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            String scheme = request.getScheme();
            String host = request.getServerName();
            int port = request.getServerPort();
            return scheme + "://" + host + ":" + port;
        }
        return "";
    }
}
