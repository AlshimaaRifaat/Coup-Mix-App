package com.example.shosho.coupmix.view;

import com.example.shosho.coupmix.model.SponsorData;

import java.util.List;

public interface SponsorView {
    void showSponsorData(List<SponsorData> sponsorDataList);
    void error();
}
