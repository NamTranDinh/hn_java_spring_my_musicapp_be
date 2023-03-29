package com.aptech.mymusic.presentation.controller.page;

import com.aptech.mymusic.presentation.controller.BaseController;
import com.aptech.mymusic.presentation.internalmodel.Fragment;
import com.aptech.mymusic.presentation.internalmodel.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/page")
public class HomeController extends BaseController {

    @Override
    @RequestMapping
    public ModelAndView index() {
        return home();
    }

    @GetMapping("/home")
    public ModelAndView home() {
        return view(buildContext(Resource.Layout.MasterCommon)
                .setTitle("Home")
                .setContent(Fragment.of(Resource.Layout.SharedCommon))
        );
    }

}
