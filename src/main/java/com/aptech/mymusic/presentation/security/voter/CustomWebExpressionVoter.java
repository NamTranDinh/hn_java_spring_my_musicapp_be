package com.aptech.mymusic.presentation.security.voter;

import com.aptech.mymusic.presentation.service.UserDetailService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.WebExpressionVoter;

import java.util.Collection;

public class CustomWebExpressionVoter extends WebExpressionVoter {

    private static final boolean debug = true;
    private static final Log logger = LogFactory.getLog(CustomWebExpressionVoter.class);

    private final UserDetailService userDetailService;

    public CustomWebExpressionVoter(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @Override
    public int vote(@NotNull Authentication authentication, FilterInvocation filterInvocation, Collection<ConfigAttribute> attributes) {

        int result = super.vote(authentication, filterInvocation, attributes);
        if (result != ACCESS_DENIED) {
            return result;
        }

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        if (authentication.getPrincipal() instanceof UserDetails) {
            String username = ((UserDetails) authentication.getPrincipal()).getUsername();
            UserDetails userDetails = userDetailService.loadUserByUsername(username);
            authorities = userDetails.getAuthorities();
        }

        log("--- Voter url:  " + filterInvocation.getRequestUrl());
        log("--- Authorities " + authentication.getAuthorities());

        for (GrantedAuthority authority : authorities) {
            PermissionMatcher matcher = new PermissionMatcher(authority.getAuthority());
            if (matcher.matches(filterInvocation.getRequestUrl())) {
                log("--> Granted");
                return ACCESS_GRANTED;
            }
        }
        log("--> Denied");
        return result;
    }

    private static void log(Object object) {
        if (debug) logger.info(object);
    }

}
