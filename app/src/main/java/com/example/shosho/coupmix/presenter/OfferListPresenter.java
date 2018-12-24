package com.example.shosho.coupmix.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.shosho.coupmix.R;
import com.example.shosho.coupmix.activity.SplashActivity;
import com.example.shosho.coupmix.api.Client;
import com.example.shosho.coupmix.api.Service;
import com.example.shosho.coupmix.model.BooksResponse;
import com.example.shosho.coupmix.model.OfferListResponse;
import com.example.shosho.coupmix.model.SearchLocBrandResponse;
import com.example.shosho.coupmix.view.BookView;
import com.example.shosho.coupmix.view.OfferListView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OfferListPresenter {
    Context context;
    OfferListView offerListView;

    public OfferListPresenter(Context context, OfferListView offerListView) {
        this.context = context;
        this.offerListView = offerListView;
    }

    public void getOfferListResult(String Lang, String Id_brand) {
        Map<String,String> map=new HashMap<>(  );
        map.put( "lang",SplashActivity.Language );
        map.put( "id_brand",Id_brand );

        Service service = Client.getClient().create( Service.class );
        Call<SearchLocBrandResponse> call = service.getOfferListData(  map);
        call.enqueue( new Callback<SearchLocBrandResponse>() {
            @Override
            public void onResponse(Call<SearchLocBrandResponse> call, Response<SearchLocBrandResponse> response) {
                if (response.isSuccessful()) {
                    offerListView.showOfferListData( response.body().getData() );
                }
            }

            @Override
            public void onFailure(Call<SearchLocBrandResponse> call, Throwable t) {
                offerListView.error();
                Toast.makeText( context, R.string.NoDataFound,
                        Toast.LENGTH_SHORT).show();
            }
        } );

    }
}
