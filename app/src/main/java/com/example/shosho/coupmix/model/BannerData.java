package com.example.shosho.coupmix.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BannerData {

    @SerializedName("c_id")
    @Expose
    private String cId;
    @SerializedName("c_img")
    @Expose
    private String cImg;

    public String getCId() {
        return cId;
    }

    public void setCId(String cId) {
        this.cId = cId;
    }

    public String getCImg() {
        return cImg;
    }

    public void setCImg(String cImg) {
        this.cImg = cImg;
    }

}
