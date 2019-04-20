package com.mission.mystore;

public class Service {
    private String mName;
    private int mIcon;

    public Service(String name, int icon) {
        mName = name;
        mIcon = icon;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getIcon() {
        return mIcon;
    }

    public void setIcon(int icon) {
        mIcon = icon;
    }
}
