package com.aptech.mymusic.presentation.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class HomeController {

    @GetMapping
    public ModelAndView index() {
        return home();
    }

    @GetMapping("/home")
    public ModelAndView home() {
        return new ModelAndView();
    }

}
