package com.example.nyt.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class News {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("num_results")
    @Expose
    private int numRes;

    @SerializedName("results")
    private List<Article> articles;

    public String getStatus() {
        return status;
    }

    public int getNumRes() {
        return numRes;
    }

    public List<Article> getArticles() {
        return articles;
    }

}
