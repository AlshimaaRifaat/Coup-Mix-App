package com.example.shosho.coupmix.view;

import com.example.shosho.coupmix.model.AllBrandData;
import com.example.shosho.coupmix.model.SearchBrandData;

import java.util.List;

public interface AllBrandView {
    void showAllBrandData(List<AllBrandData> allBrandDataList);
    void error();
}
