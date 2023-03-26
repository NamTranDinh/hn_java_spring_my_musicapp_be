package com.aptech.mymusic.presentation.controller;

import com.aptech.mymusic.presentation.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexController extends BaseController {

    @Override
    @RequestMapping
    public ModelAndView index() {
        return new ModelAndView("redirect:/public");
    }

}
