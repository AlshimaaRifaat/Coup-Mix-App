package com.example.shosho.coupmix.api;
import com.example.shosho.coupmix.model.AllBrandResponse;
import com.example.shosho.coupmix.model.BannerResponse;
import com.example.shosho.coupmix.model.BooksResponse;
import com.example.shosho.coupmix.model.BrandResponse;
import com.example.shosho.coupmix.model.FeatureProductResponse;
import com.example.shosho.coupmix.model.LocationResponse;
import com.example.shosho.coupmix.model.OfferListResponse;
import com.example.shosho.coupmix.model.OrderItemResponse;
import com.example.shosho.coupmix.model.SearchBrandData;
import com.example.shosho.coupmix.model.SearchBrandResponse;
import com.example.shosho.coupmix.model.SearchLocBrandResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface Service {
    // /api_elshekh/Allpeotry/?api_token=100&lang=ar&section=book
    @GET("api/slider/")
    Call<BannerResponse>getBannerData();

    @POST("api/cate_list")
    Call<BooksResponse> getBooksData(@Body Map<String,String> map);

    @POST("api/featureOffers")
    Call<FeatureProductResponse> getFeaturesProductsData(@Body Map<String,String> map);

    @POST("api/location_list")
    Call<LocationResponse> getLocationData(@Body Map<String,String> map);

    @POST("api/brand_list")
    Call<BrandResponse> getBrandData(@Body Map<String,String> map);

    @POST("api/search_list")
    Call<SearchLocBrandResponse> getSearchLocBrandData(@Body Map<String,String> map);

    @POST("api/saveorder")
    Call<OrderItemResponse> getOrderItemData(@Body Map<String,String> map);

    @POST("api/brandsearch")
    Call<SearchBrandResponse> getSearchBrandData(@Body Map<String,String> map);

    @POST("api/brandsearch")
    Call<AllBrandResponse> getAllBrandData(@Body Map<String,String> map);

    @POST("api/offersearchlist")
    Call<SearchLocBrandResponse>getOfferListData(@Body Map<String,String> map);
}
