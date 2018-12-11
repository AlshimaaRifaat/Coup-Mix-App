package com.example.shosho.coupmix.view;

import com.example.shosho.coupmix.model.BookData;

import java.util.List;

public interface BookView {
    void showData(List<BookData> booksData);//show location data in spinner
    void showBrandData(List<BookData> booksData);
    void error();
}
