package com.example.shosho.coupmix.view;

import com.example.shosho.coupmix.model.BrandData;
import java.util.List;

public interface BrandView {
    void showBrandData(List<BrandData> brandDataList);
    void error(String error);
}
