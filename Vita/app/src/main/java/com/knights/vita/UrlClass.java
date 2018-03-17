package com.knights.vita;

/**
 * Created by josephvarghese on 17/03/18.
 */

public class UrlClass {

    String url = "http://10.90.90.19:3000";
    String outUrl = "https://vision.googleapis.com/v1/images:annotate?key=AIzaSyCtFDOWbGdYmjJrdKs4oUlwhoNR5w79Kew";

    public UrlClass(String url) {
        this.url = url;
        this.outUrl = outUrl;
    }

    public UrlClass() {
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOutUrl() {
        return outUrl;
    }

    public void setOutUrl(String outUrl) {
        this.outUrl = outUrl;
    }
}
