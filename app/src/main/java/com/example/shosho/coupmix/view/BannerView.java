package com.example.shosho.coupmix.view;

import com.example.shosho.coupmix.model.BannerData;

import java.util.List;

public interface BannerView {

     void showBannerData(List<BannerData> bannersData);
     void error();
}
