package com.aptech.mymusic.presentation.controller.admin.auth;

import com.aptech.mymusic.presentation.controller.base.BaseController;
import com.aptech.mymusic.presentation.internalmodel.Fragment;
import com.aptech.mymusic.presentation.internalmodel.LoginError;
import com.aptech.mymusic.presentation.internalmodel.Resource;
import com.aptech.mymusic.presentation.security.jwt.JwtCookiesManager;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin/auth")
public class AuthController extends BaseController {

    private final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    private final JwtCookiesManager jwtCookiesManager;

    public AuthController(JwtCookiesManager jwtCookiesManager) {
        this.jwtCookiesManager = jwtCookiesManager;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        LOGGER.info(encoder.encode("123456"));
    }

    @Override
    @RequestMapping
    public ModelAndView index() {
        return new ModelAndView("redirect:/admin/auth/login");
    }

    @GetMapping(value = "/login")
    public ModelAndView login(@NotNull HttpServletRequest request, @RequestParam(value = "error", required = false) String error) {
        if (jwtCookiesManager.getLoginSessionToken(request) != null) {
            return redirect();
        }
        return view(buildContext(Resource.Layout.MasterAdminBlank)
                .setTitle("Login")
                .setContent(Fragment.of("templates/admin/pages/auth/login.html")))
                .addObject("error", LoginError.decode(error));
    }

    @GetMapping("/register")
    public ModelAndView register() {
        return null;
    }

    @GetMapping("/logout")
    public ModelAndView logout() {
        // not handle
        return null;
    }

    @GetMapping("/redirect")
    public ModelAndView redirect() {
        LOGGER.warn("Redirect {}", SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        return new ModelAndView("redirect:/admin");
    }

}
