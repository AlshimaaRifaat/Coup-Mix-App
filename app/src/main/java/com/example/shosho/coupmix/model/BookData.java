package com.example.shosho.coupmix.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookData {

    @SerializedName("catID")
    @Expose
    private String catID;
    @SerializedName("iconUrl")
    @Expose
    private String iconUrl;
    @SerializedName("catName")
    @Expose
    private String catName;
    @SerializedName("prodID")
    @Expose
    private String prodID;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("prodName")
    @Expose
    private String prodName;
    @SerializedName("shortDesc")
    @Expose
    private String shortDesc;
    @SerializedName("offerDetails")
    @Expose
    private String offerDetails;
    @SerializedName("imgUrl")
    @Expose
    private String imgUrl;
    @SerializedName("discount")
    @Expose
    private String discount;
    @SerializedName("realPrice")
    @Expose
    private String realPrice;
    @SerializedName("discountPrice")
    @Expose
    private String discountPrice;
    @SerializedName("featureProdName")
    @Expose
    private String featureProdName;
    @SerializedName("featureProdImg")
    @Expose
    private String featureProdImg;
    @SerializedName("brand")
    @Expose
    private String brand;
    @SerializedName("location")
    @Expose
    private String location;


    public String getCatID() {
        return catID;
    }

    public void setCatID(String catID) {
        this.catID = catID;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getProdID() {
        return prodID;
    }

    public void setProdID(String prodID) {
        this.prodID = prodID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getOfferDetails() {
        return offerDetails;
    }

    public void setOfferDetails(String offerDetails) {
        this.offerDetails = offerDetails;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(String realPrice) {
        this.realPrice = realPrice;
    }

    public String getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(String discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getFeatureProdName() {
        return featureProdName;
    }

    public void setFeatureProdName(String featureProdName) {
        this.featureProdName = featureProdName;
    }

    public String getFeatureProdImg() {
        return featureProdImg;
    }

    public void setFeatureProdImg(String featureProdImg) {
        this.featureProdImg = featureProdImg;
    }

    public String getBrand() {
        return brand;
    }


    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return location;
    }
}