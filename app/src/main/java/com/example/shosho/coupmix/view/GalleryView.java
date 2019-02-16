package com.example.shosho.coupmix.view;

import com.example.shosho.coupmix.model.GalleryData;

import java.util.List;

public interface GalleryView {
    void showGalleryData(List<GalleryData> galleryDataList);
    void showError();
}