package com.aptech.mymusic.presentation.internalmodel;

import org.jetbrains.annotations.NotNull;

public class Fragment {
    private String layout;
    private String name;

    @NotNull
    public static Fragment of(String layout, String name) {
        return new Fragment().setLayout(layout).setName(name);
    }

    @NotNull
    public static Fragment of(@NotNull Resource.Layout layout, String name) {
        return new Fragment().setLayout(layout).setName(name);
    }

    private Fragment() {
    }

    public String getLayout() {
        return layout;
    }

    public Fragment setLayout(String layout) {
        this.layout = layout;
        return this;
    }

    public Fragment setLayout(@NotNull Resource.Layout layout) {
        this.layout = layout.getName();
        return this;
    }

    public String getName() {
        return name;
    }

    public Fragment setName(String name) {
        this.name = name;
        return this;
    }

}
