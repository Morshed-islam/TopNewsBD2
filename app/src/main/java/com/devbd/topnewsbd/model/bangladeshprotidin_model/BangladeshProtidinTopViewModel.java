package com.devbd.topnewsbd.model.bangladeshprotidin_model;

/**
 * Created by morshed on 7/24/17.
 */

public class BangladeshProtidinTopViewModel {

    private String newsHeading;
    private String newsDescription;
    private String imageLink;
    private String moreLink;

    public BangladeshProtidinTopViewModel(String newsHeading) {
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

    public String getMoreLink() {
        return moreLink;
    }

    public void setMoreLink(String moreLink) {
        this.moreLink = moreLink;
    }
}
