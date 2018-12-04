package com.example.shosho.coupmix.view;

import com.example.shosho.coupmix.model.BookData;

import java.util.List;

public interface BookView {
    void showData(List<BookData> booksData);
    void error();
}
