package com.example.shosho.coupmix.view;

import com.example.shosho.coupmix.model.BookData;
import com.example.shosho.coupmix.model.FeatureProductData;

import java.util.List;

public interface FeatureProductView {
    void showFeaturesProductsData(List<FeatureProductData> featuresProductsData);
    void error();
}
