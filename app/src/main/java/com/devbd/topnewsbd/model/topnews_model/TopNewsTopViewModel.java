package com.devbd.topnewsbd.model.topnews_model;

/**
 * Created by morshed on 7/24/17.
 */

public class TopNewsTopViewModel {
    private String newsHeading;
    private String newsDescription;
    private String imageLink;
    private String link;
    private String date;
    private String newsPaperName;


    public TopNewsTopViewModel(String newsHeading, String imageLink, String link, String date, String newsPaperName) {
        this.newsHeading = newsHeading;
        this.imageLink = imageLink;
        this.link = link;
        this.date = date;
        this.newsPaperName = newsPaperName;
    }

    public TopNewsTopViewModel(String newsHeading, String imageLink, String date, String newsPaperName) {
        this.newsHeading = newsHeading;
        this.imageLink = imageLink;
        this.date = date;
        this.newsPaperName = newsPaperName;
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

    public String getNewsPaperName() {
        return newsPaperName;
    }

    public void setNewsPaperName(String newsPaperName) {
        this.newsPaperName = newsPaperName;
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


