package com.aptech.mymusic.presentation.security.jwt;

import com.aptech.mymusic.presentation.internalmodel.Response;
import com.aptech.mymusic.presentation.security.SecurityConstant;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

    private final JwtCookiesManager jwtCookiesManager;

    public JwtAuthenticationEntryPoint(JwtCookiesManager jwtCookiesManager) {
        this.jwtCookiesManager = jwtCookiesManager;
    }

    @Override
    public void commence(HttpServletRequest request, @NotNull HttpServletResponse response,
                         @NotNull AuthenticationException authException) throws IOException {
        if (jwtCookiesManager.getLoginSessionToken(request) != null) {
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write(Response.error(HttpStatus.FORBIDDEN, authException.getMessage()).json());
            return;
        }
        LOGGER.warn("Unauthorized error: {}", authException.getMessage());
        response.sendRedirect(SecurityConstant.LOGIN_PAGE);
    }
}
