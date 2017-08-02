package com.devbd.topnewsbd.model.kalerkantho_model;

/**
 * Created by morshed on 7/24/17.
 */

public class KalerKanthoTopViewModel {

    private String newsHeading;
    private String newsDescription;
    private String imageLink;
    private String date;

    public KalerKanthoTopViewModel(String newsHeading, String imageLink, String date) {
        this.newsHeading = newsHeading;
        this.imageLink = imageLink;
        this.date = date;
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
