package com.example.nyt.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//class for getting multimedia object from GET call result

public class Multimedia {
    @SerializedName("url")
    @Expose
    private String imgUrl;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("caption")
    @Expose
    private String caption;

    public String getImgUrl() {
        return imgUrl;
    }

    public String getType() {
        return type;
    }

    public String getCaption() {
        return caption;
    }

}
