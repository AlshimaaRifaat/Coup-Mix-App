package com.example.shosho.coupmix.view;

import com.example.shosho.coupmix.model.SearchLocBrandData;

import java.util.List;

public interface OfferListView {
    void showOfferListData(List<SearchLocBrandData> searchLocBrandData);
    void error();
}
