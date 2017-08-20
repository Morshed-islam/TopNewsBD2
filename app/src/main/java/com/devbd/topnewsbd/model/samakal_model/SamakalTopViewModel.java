package com.devbd.topnewsbd.model.samakal_model;

/**
 * Created by morshed on 7/24/17.
 */

public class SamakalTopViewModel {

    private String newsHeading;
    private String newsDescription;
    private String imageLink;
    private String date;
    private String link;


    public SamakalTopViewModel(String newsHeading, String imageLink, String link) {
        this.newsHeading = newsHeading;
        this.imageLink = imageLink;
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
}


