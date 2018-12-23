package com.example.shosho.coupmix.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.shosho.coupmix.R;
import com.example.shosho.coupmix.activity.SplashActivity;
import com.example.shosho.coupmix.api.Client;
import com.example.shosho.coupmix.api.Service;
import com.example.shosho.coupmix.model.AllBrandResponse;
import com.example.shosho.coupmix.model.SearchBrandResponse;
import com.example.shosho.coupmix.view.AllBrandView;
import com.example.shosho.coupmix.view.SearchBrandView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllBrandPresenter {
    Context context;
    AllBrandView allBrandView;

    public AllBrandPresenter(Context context, AllBrandView allBrandView) {
        this.context = context;
        this.allBrandView = allBrandView;
    }

    public void getAllBrandResult(String Lang) {
        Map<String, String> map = new HashMap<>();
        map.put( "lang", SplashActivity.Language );
        Service service = Client.getClient().create( Service.class );
        Call<AllBrandResponse> call = service.getAllBrandData( map );
        call.enqueue( new Callback<AllBrandResponse>() {
            @Override
            public void onResponse(Call<AllBrandResponse> call, Response<AllBrandResponse> response) {
                if(response.isSuccessful()) {
                    allBrandView.showAllBrandData( response.body().getData() );
                }
            }

            @Override
            public void onFailure(Call<AllBrandResponse> call, Throwable t) {
                allBrandView.error();
                Toast.makeText( context, R.string.NoDataFound,
                        Toast.LENGTH_LONG).show();

            }
        } );
    }
}