package com.aptech.mymusic.presentation.controller.admin.privileges;

import com.aptech.mymusic.presentation.controller.BaseController;
import com.aptech.mymusic.presentation.internalmodel.Fragment;
import com.aptech.mymusic.presentation.internalmodel.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/privileges/users")
public class UsersController extends BaseController {

    @Override
    @RequestMapping
    public ModelAndView index() {
        return view(buildContext(Resource.Layout.MasterAdmin)
                .setTitle("Users")
                .setContent(Fragment.of("templates/admin/pages/privileges/users.html")));
    }

}
