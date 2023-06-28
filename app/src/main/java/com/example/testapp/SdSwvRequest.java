package com.example.testapp;

public class SdSwvRequest {

    private String user;
    private String token;
    private String cookie;
    private String SWVServerUrl;
    private String packageName;
    private boolean isUpdateOnly;

    public SdSwvRequest(String user, String token, String cookie, String SWVServerUrl, String packageName, boolean isUpdateOnly) {
        this.user = user;
        this.token = token;
        this.cookie = cookie;
        this.SWVServerUrl = SWVServerUrl;
        this.packageName = packageName;
        this.isUpdateOnly = isUpdateOnly;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getSWVServerUrl() {
        return SWVServerUrl;
    }

    public void setSWVServerUrl(String SWVServerUrl) {
        this.SWVServerUrl = SWVServerUrl;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public boolean isUpdateOnly() {
        return isUpdateOnly;
    }

    public void setUpdateOnly(boolean updateOnly) {
        isUpdateOnly = updateOnly;
    }
}
