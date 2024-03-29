package com.devbd.topnewsbd.model.banglanews_model;

/**
 * Created by morshed on 7/24/17.
 */

public class BanglaNewsLatestModel {
    private String newsHeading;
    private String newsDescription;
    private String imageLink;
    private String moreLink;

    public BanglaNewsLatestModel(String newsHeading, String newsDescription, String imageLink, String moreLink) {
        this.newsHeading = newsHeading;
        this.newsDescription = newsDescription;
        this.imageLink = imageLink;
        this.moreLink = moreLink;
    }

    public String getNewsHeading() {
        return newsHeading;
    }

    public void setNewsHeading(String newsHeading) {
        this.newsHeading = newsHeading;
    }

    public String getNewsDescription() {
        return newsDescription;
    }

    public void setNewsDescription(String newsDescription) {
        this.newsDescription = newsDescription;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getMoreLink() {
        return moreLink;
    }

    public void setMoreLink(String moreLink) {
        this.moreLink = moreLink;
    }
}
