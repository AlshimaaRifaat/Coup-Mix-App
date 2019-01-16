package com.example.shosho.coupmix.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GalleryData {

    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("linkvideo")
    @Expose
    private String linkvideo;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLinkvideo() {
        return linkvideo;
    }

    public void setLinkvideo(String linkvideo) {
        this.linkvideo = linkvideo;
    }

}