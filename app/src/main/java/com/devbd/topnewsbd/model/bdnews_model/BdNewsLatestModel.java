package com.devbd.topnewsbd.model.bdnews_model;

/**
 * Created by morshed on 7/24/17.
 */

public class BdNewsLatestModel {
    private String newsHeading;
    private String newsDescription;
    private String imageLink;
    private String moreLink;

    public BdNewsLatestModel(String newsHeading, String newsDescription, String imageLink, String moreLink) {
        this.newsHeading = newsHeading;
        this.newsDescription = newsDescription;
        this.imageLink = imageLink;
        this.moreLink = moreLink;
    }

    public BdNewsLatestModel(String newsHeading) {
        this.newsHeading = newsHeading;
    }

    public String getNewsHeading() {
        return newsHeading;
    }

    public BdNewsLatestModel(String newsHeading, String imageLink) {
        this.newsHeading = newsHeading;
        this.imageLink = imageLink;
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
