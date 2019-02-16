package com.example.shosho.coupmix.model;

public class CategoryItemDetails {
    private String Image;
    private String Name;
    private String Discount;
    private String CouponDetails;
    private String FeaturesOffer;
    private String Country;
    private String phone;

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }

    public String getCouponDetails() {
        return CouponDetails;
    }

    public void setCouponDetails(String couponDetails) {
        CouponDetails = couponDetails;
    }

    public String getFeaturesOffer() {
        return FeaturesOffer;
    }

    public void setFeaturesOffer(String featuresOffer) {
        FeaturesOffer = featuresOffer;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
