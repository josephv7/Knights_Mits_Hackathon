package com.knights.vita;

/**
 * Created by josephvarghese on 17/03/18.
 */

public class UrlClass {

    String url = "http://10.90.101.150:3000";

    public UrlClass(String url) {
        this.url = url;
    }

    public UrlClass() {
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
