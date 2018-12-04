package com.example.shosho.coupmix.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BooksResponse {


    @SerializedName("Cat")
    @Expose
    private List<BookData> cat = null;

    public List<BookData> getCat() {
        return cat;
    }

    public void setCat(List<BookData> cat) {
        this.cat = cat;
    }
}
