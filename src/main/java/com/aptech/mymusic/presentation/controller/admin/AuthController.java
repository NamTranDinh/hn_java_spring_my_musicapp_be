package com.aptech.mymusic.presentation.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/login")
    public ModelAndView login() {
        return null;
    }

    @GetMapping("/logout")
    public ModelAndView logout() {
        return null;
    }

    @GetMapping("/redirect")
    public ModelAndView redirect() {
        return new ModelAndView("redirect:/admin/home");
    }

}
