package com.aptech.mymusic.presentation.controller.admin;

import com.aptech.mymusic.presentation.controller.base.BaseController;
import com.aptech.mymusic.presentation.internalmodel.Fragment;
import com.aptech.mymusic.presentation.internalmodel.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/auth")
public class AuthController extends BaseController {

    @GetMapping("/login")
    public ModelAndView login() {
        return view(buildContext(Resource.Layout.MasterAdminBlank)
                .setTitle("MyMusic | Auth-Login")
                .setContent(Fragment.of("templates/admin/pages/auth/login.html")));
    }

    @GetMapping("/register")
    public ModelAndView register() {
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
