package com.example.shosho.coupmix.view;

import com.example.shosho.coupmix.model.BookData;
import com.example.shosho.coupmix.model.LocationData;

import java.util.List;

public interface LocationView {
    void showLocationData(List<LocationData> locationDatalist);
    void error();
}
