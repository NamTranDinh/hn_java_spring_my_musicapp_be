package com.aptech.mymusic.presentation.controller.admin.musics;

import com.aptech.mymusic.presentation.controller.BaseController;
import com.aptech.mymusic.presentation.internalmodel.Fragment;
import com.aptech.mymusic.presentation.internalmodel.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/musics/song")
public class SongController extends BaseController {

    @Override
    @RequestMapping
    public ModelAndView index() {
        return view(buildContext(Resource.Layout.MasterAdmin)
                .setTitle("Song")
                .setContent(Fragment.of("templates/admin/pages/musics/song.html")));
    }

}
