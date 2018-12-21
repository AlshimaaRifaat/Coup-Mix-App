package com.example.shosho.coupmix.view;

import com.example.shosho.coupmix.model.SearchBrandData;

import java.util.List;

public interface SearchBrandView {
    void showSearchBrandData(List<SearchBrandData> searchBrandDataList);
    void error();
}
