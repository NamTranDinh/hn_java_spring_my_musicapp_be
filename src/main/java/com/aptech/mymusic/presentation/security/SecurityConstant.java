package com.aptech.mymusic.presentation.security;

import java.util.concurrent.TimeUnit;

public class SecurityConstant {

    public static final String LOGIN_PAGE = "/admin/auth/login";
    public static final String LOGOUT_PAGE = "/admin/auth/logout";
    public static final String LOGIN_FAILURE_PAGE = "/admin/auth/login?error";
    public static final String LOGIN_SUCCESS_PAGE = "/admin/auth/redirect";

    public static final long LOGIN_SESSION_EXP = TimeUnit.DAYS.toMillis(1);  // 10 minute
    public static final long LOGIN_REMEMBER_ME_EXP = TimeUnit.DAYS.toMillis(14); // 2 week

    public static final String[] PUBLIC_URL = new String[]{
            "/admin/auth/**",
            "/api/**",
            "/public/**",
            "/images/**",
            "/raw/**",
    };
    public static final String[] PUBLIC_GET_URL = new String[]{
    };

}
