package com.aptech.mymusic.presentation.security.jwt;

import com.aptech.mymusic.presentation.security.SecurityConstant;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, @NotNull HttpServletResponse response,
                         @NotNull AuthenticationException authException) throws IOException {
        LOGGER.warn("Unauthorized error: {}", authException.getMessage());
        response.sendRedirect(SecurityConstant.LOGIN_PAGE);
    }
}
