package com.example.shosho.coupmix.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.shosho.coupmix.R;
import com.example.shosho.coupmix.activity.SplashActivity;
import com.example.shosho.coupmix.api.Client;
import com.example.shosho.coupmix.api.Service;
import com.example.shosho.coupmix.model.SearchLocBrandResponse;
import com.example.shosho.coupmix.view.SearchLocBrandView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchLocBrandPresenter {
    Context context;
    SearchLocBrandView searchLocBrandView;

    public SearchLocBrandPresenter(Context context, SearchLocBrandView searchLocBrandView) {
        this.context = context;
        this.searchLocBrandView = searchLocBrandView;
    }
    public void getSearchLocBrandResult(String Lang,String Country,String Brand)
    {
        Map<String,String> map=new HashMap<>( );
        map.put( "lang", SplashActivity.Language );
        map.put( "country",Country );
        map.put( "brand",Brand );
        Service service=Client.getClient().create( Service.class );
        Call<SearchLocBrandResponse> call=service.getSearchLocBrandData( map );
        call.enqueue( new Callback<SearchLocBrandResponse>() {
            @Override
            public void onResponse(Call<SearchLocBrandResponse> call, Response<SearchLocBrandResponse> response) {
                if (response.isSuccessful())
                {
                    searchLocBrandView.showSearhLocBrandResult( response.body().getData(  ));
                }
            }

            @Override
            public void onFailure(Call<SearchLocBrandResponse> call, Throwable t) {
                searchLocBrandView.error();
                Toast.makeText( context, R.string.NoNetworkAvailable,
                        Toast.LENGTH_SHORT).show();
            }
        } );
    }
}
