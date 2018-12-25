package com.example.shosho.coupmix.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.shosho.coupmix.R;
import com.example.shosho.coupmix.activity.SplashActivity;
import com.example.shosho.coupmix.api.Client;
import com.example.shosho.coupmix.api.Service;
import com.example.shosho.coupmix.model.SearchLocBrandResponse;
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
                    if (response.body().getData()!=null) {
                        offerListView.showOfferListData( response.body().getData() );
                    }
                    else
                    {
                        Toast.makeText( context, "No Result Found", Toast.LENGTH_SHORT ).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<SearchLocBrandResponse> call, Throwable t) {
                offerListView.error("No Result Found");
                Toast.makeText( context, R.string.NoDataFound,
                        Toast.LENGTH_SHORT).show();
            }
        } );

    }
}
