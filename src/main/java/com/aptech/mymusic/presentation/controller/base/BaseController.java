package com.aptech.mymusic.presentation.controller.base;

import com.aptech.mymusic.presentation.internalmodel.Context;
import com.aptech.mymusic.presentation.internalmodel.Resource;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

public abstract class BaseController {

    private static final String ATTR_CONTEXT = "context";

   public abstract ModelAndView index();

    @NotNull
    @SafeVarargs
    protected final ModelAndView view(Context context, Pair<String, Object>... data) {
        return view(new ModelAndView(), context, data);
    }

    @NotNull
    @SafeVarargs
    protected final ModelAndView view(@NotNull ModelAndView source, Context context, Pair<String, Object>... data) {
        if (source.getViewName() == null && context != null) {
            source.setViewName(context.getLayout());
        }
        if (data != null) for (Pair<String, Object> pair : data) {
            source.addObject(pair.getFirst(), pair.getSecond());
        }
        source.addObject(ATTR_CONTEXT, context);
        return source;
    }

    protected Context buildContext(Resource.Layout layout) {
        return Context.builder(layout).setIcon(Resource.Icon.AppLogo);
    }

}
