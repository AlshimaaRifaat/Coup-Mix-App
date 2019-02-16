package com.example.shosho.coupmix.view;

import com.example.shosho.coupmix.model.OrderItemData;

import java.util.List;

public interface OrderItemView {
    void openPage(List<OrderItemData> Id);
    void showError(String Error);
}
