package com.example.shosho.coupmix.view;

import com.example.shosho.coupmix.model.SearchLocBrandData;

import java.util.List;

public interface SearchLocBrandView {
   // void showSearhLocBrandPage(SearchLocBrand searchLocBrand);
   void showSearhLocBrandResult(List<SearchLocBrandData> locBrandDataList);
   void error(String error);
}
