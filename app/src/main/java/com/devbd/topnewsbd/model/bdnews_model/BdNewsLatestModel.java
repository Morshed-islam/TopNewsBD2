package com.devbd.topnewsbd.model.bdnews_model;

/**
 * Created by morshed on 7/24/17.
 */

public class BdNewsLatestModel {
    private String newsHeading;
    private String newsDescription;
    private String imageLink;
    private String link;


    public BdNewsLatestModel(String newsHeading, String link) {
        this.newsHeading = newsHeading;
        this.link = link;
    }

    public BdNewsLatestModel(String newsHeading) {
        this.newsHeading = newsHeading;
    }

    public String getNewsHeading() {
        return newsHeading;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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

}
