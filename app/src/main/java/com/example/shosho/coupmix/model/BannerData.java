package com.example.shosho.coupmix.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BannerData {

    @SerializedName("c_img")
    @Expose
    private String cImg;

    public String getCImg() {
        return cImg;
    }

    public void setCImg(String cImg) {
        this.cImg = cImg;
    }

}