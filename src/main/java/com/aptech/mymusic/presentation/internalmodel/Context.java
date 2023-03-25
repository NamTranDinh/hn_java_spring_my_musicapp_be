package com.aptech.mymusic.presentation.internalmodel;

import org.jetbrains.annotations.NotNull;

public class Context {
    private String layout;
    private Fragment content;
    private String title;
    private String icon;

    private Context() {
    }

    @NotNull
    public static Context builder(Resource.Layout layout) {
        return new Context().setLayout(layout);
    }

    public String getLayout() {
        return layout;
    }

    public Context setLayout(String layout) {
        this.layout = layout;
        return this;
    }

    public Context setLayout(@NotNull Resource.Layout layout) {
        this.layout = layout.getName();
        return this;
    }

    public Fragment getContent() {
        return content;
    }

    public Context setContent(Fragment content) {
        this.content = content;
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
