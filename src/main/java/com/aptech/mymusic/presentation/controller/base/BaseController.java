package com.aptech.mymusic.presentation.controller.base;

import com.aptech.mymusic.presentation.internalmodel.Context;
import com.aptech.mymusic.presentation.internalmodel.Fragment;
import com.aptech.mymusic.presentation.internalmodel.Resource;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.ModelAndView;

public abstract class BaseController {

    private static final String ATTR_CONTEXT = "context";
    private static final String DEFAULT_HEADER_FRAGMENT_NAME = "head";

    protected ModelAndView view(Context context) {
        return view(new ModelAndView(), context);
    }

    protected ModelAndView view(@NotNull ModelAndView source, Context context) {
        if (source.getViewName() == null) {
            source.setViewName(context.getLayout().getName());
        }
        source.addObject(ATTR_CONTEXT, context);
        return source;
    }

    protected Context defaultContext() {
        return Context.builder()
                .setIcon(Resource.Icon.MusicIcon)
                .setHeadFragment(Fragment.of(Resource.Layout.CommonFragment, DEFAULT_HEADER_FRAGMENT_NAME));
    }

    protected Context defaultAdminContext() {
        return Context.builder(Resource.Layout.AdminMaster)
                .setIcon(Resource.Icon.MusicIcon)
                .setHeadFragment(Fragment.of(Resource.Layout.AdminFragment, DEFAULT_HEADER_FRAGMENT_NAME));
    }

    protected Context defaultClientContext() {
        return Context.builder(Resource.Layout.ClientMaster)
                .setIcon(Resource.Icon.MusicIcon)
                .setHeadFragment(Fragment.of(Resource.Layout.ClientFragment, DEFAULT_HEADER_FRAGMENT_NAME));
    }

}
