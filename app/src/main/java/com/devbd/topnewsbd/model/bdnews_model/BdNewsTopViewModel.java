package com.devbd.topnewsbd.model.bdnews_model;

/**
 * Created by morshed on 7/24/17.
 */

public class BdNewsTopViewModel {
    private String newsHeading;
    private String newsDescription;
    private String imageLink;
    private String link;


    public BdNewsTopViewModel(String newsHeading, String link) {
        this.newsHeading = newsHeading;
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public BdNewsTopViewModel(String newsHeading) {
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
