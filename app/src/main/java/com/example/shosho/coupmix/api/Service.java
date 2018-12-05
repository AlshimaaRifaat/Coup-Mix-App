package com.example.shosho.coupmix.api;
import com.example.shosho.coupmix.model.BannerResponse;
import com.example.shosho.coupmix.model.BooksResponse;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface Service {
    // /api_elshekh/Allpeotry/?api_token=100&lang=ar&section=book
    @GET("umdwu")
    Call<BooksResponse> getBooksData();

    @GET("p0oc6")
    Call<BannerResponse>getBannerData();


}
