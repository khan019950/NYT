package com.example.nyt.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

//Model class to map GET call result with

public class Article {

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("abstract")
    @Expose
    private String nAbstract;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("byline")
    @Expose
    private String byline;

    @SerializedName("published_date")
    @Expose
    private String published_date;

    @SerializedName("multimedia")
    @Expose
    private List<Multimedia> multimedia;

    public String getTitle() {
        return title;
    }

    public String getnAbstract() {
        return nAbstract;
    }

    public String getUrl() {
        return url;
    }

    public String getByline() {
        return byline;
    }

    public String getPublished_date() {
        return published_date;
    }

    public List<Multimedia> getMultimedia() {
        return multimedia;
    }

}
