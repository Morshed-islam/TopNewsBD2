package com.devbd.topnewsbd.model.kalerkantho_model;

/**
 * Created by morshed on 7/24/17.
 */

public class KalerKanthoLatestModel {
    private String newsHeading;
    private String newsDescription;
    private String imageLink;
    private String date;
    private String link;

    public KalerKanthoLatestModel(String newsHeading, String imageLink, String date, String link) {
        this.newsHeading = newsHeading;
        this.imageLink = imageLink;
        this.date = date;
        this.link = link;
    }

    public KalerKanthoLatestModel(String newsHeading, String imageLink) {
        this.newsHeading = newsHeading;
        this.imageLink = imageLink;
    }


    public KalerKanthoLatestModel(String newsHeading, String imageLink, String date) {
        this.newsHeading = newsHeading;
        this.imageLink = imageLink;
        this.date = date;
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

    public KalerKanthoLatestModel(String newsHeading) {
        this.newsHeading = newsHeading;
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
