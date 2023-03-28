package com.aptech.mymusic.presentation.security.jwt;

import com.aptech.mymusic.presentation.internalmodel.Response;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAccessDeniedHandler extends AccessDeniedHandlerImpl {

    @Override
    public void handle(HttpServletRequest request, @NotNull HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        String errorMessage = "Access denied. You don't have permission to access this resource.";
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().write(Response.error(HttpStatus.FORBIDDEN, errorMessage).json());
    }
}
