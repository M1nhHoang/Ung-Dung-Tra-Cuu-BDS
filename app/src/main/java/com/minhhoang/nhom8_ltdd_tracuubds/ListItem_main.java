package com.minhhoang.nhom8_ltdd_tracuubds;

public class ListItem_main {
    private int iconId1;
    private String text;

    public ListItem_main(int iconId1, String text) {
        this.iconId1=iconId1;
        this.text=text;
    }
    public int getIconId1() {
        return iconId1;
    }

    public void setIconId1(int iconId1) {
        this.iconId1 = iconId1;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
