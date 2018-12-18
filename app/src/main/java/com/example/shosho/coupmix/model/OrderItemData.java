package com.example.shosho.coupmix.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderItemData {

    @SerializedName("id_order")
    @Expose
    private Integer idOrder;




    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

}