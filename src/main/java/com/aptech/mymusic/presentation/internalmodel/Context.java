package com.aptech.mymusic.presentation.internalmodel;

import org.jetbrains.annotations.NotNull;

public class Context {
    private Resource.Layout layout;
    private Fragment headFragment;
    private Fragment contentFragment;
    private String title;
    private String icon;

    private Context() {
    }

    @NotNull
    public static Context builder() {
        return builder(Resource.Layout.CommonMaster);
    }

    @NotNull
    public static Context builder(Resource.Layout layout) {
        return new Context().setLayout(layout);
    }

    public Resource.Layout getLayout() {
        return layout;
    }

    public Context setLayout(Resource.Layout layout) {
        this.layout = layout;
        return this;
    }

    public Fragment getHeadFragment() {
        return headFragment;
    }

    public Context setHeadFragment(Fragment headFragment) {
        this.headFragment = headFragment;
        return this;
    }

    public Fragment getContentFragment() {
        return contentFragment;
    }

    public Context setContentFragment(Fragment contentFragment) {
        this.contentFragment = contentFragment;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Context setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getIcon() {
        return icon;
    }

    public Context setIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public Context setIcon(@NotNull Resource.Icon icon) {
        this.icon = icon.getName();
        return this;
    }

}
