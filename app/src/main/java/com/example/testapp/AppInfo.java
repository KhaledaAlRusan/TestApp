package com.example.testapp;

import androidx.annotation.NonNull;

public class AppInfo {
    private String displayName;
    private String packageName;

    public AppInfo(String displayName, String packageName) {
        this.displayName = displayName;
        this.packageName = packageName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getPackageName() {
        return packageName;
    }

    @NonNull
    @Override
    public String toString() {
        return displayName;
    }
}

