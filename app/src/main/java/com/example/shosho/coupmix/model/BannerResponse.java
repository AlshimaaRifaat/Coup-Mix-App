package com.example.shosho.coupmix.model;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BannerResponse {

    @SerializedName("data")
    @Expose
    private List<BannerData> data = null;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("error")
    @Expose
    private String error;

    public List<BannerData> getData() {
        return data;
    }

    public void setData(List<BannerData> data) {
        this.data = data;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}

