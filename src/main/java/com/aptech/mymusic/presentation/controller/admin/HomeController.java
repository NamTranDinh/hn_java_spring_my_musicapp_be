package com.aptech.mymusic.presentation.controller.admin;

import com.aptech.mymusic.presentation.controller.base.BaseController;
import com.aptech.mymusic.presentation.internalmodel.Fragment;
import com.aptech.mymusic.presentation.internalmodel.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class HomeController extends BaseController {

    @GetMapping
    public ModelAndView index() {
        return home();
    }

    @GetMapping("/home")
    public ModelAndView home() {
        return view(buildContext(Resource.Layout.MasterAdminBlank)
                .setTitle("Admin | Home")
                .setContent(Fragment.of("templates/admin/pages/home/home.html")));
    }

}
