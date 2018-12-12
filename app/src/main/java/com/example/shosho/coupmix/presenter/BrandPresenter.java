package com.example.shosho.coupmix.presenter;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.example.shosho.coupmix.R;
import com.example.shosho.coupmix.api.Client;
import com.example.shosho.coupmix.api.Service;
import com.example.shosho.coupmix.fragment.HomeFragment;
import com.example.shosho.coupmix.model.BrandResponse;
import com.example.shosho.coupmix.model.LocationResponse;
import com.example.shosho.coupmix.view.BrandView;
import com.example.shosho.coupmix.view.LocationView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BrandPresenter {

    Context context;
    BrandView brandView;

    public BrandPresenter(Context context, BrandView brandView) {
        this.context = context;
        this.brandView = brandView;
    }

    public void getBrandResult(String Lang, String Country) {
        Map<String,String> map=new HashMap<>(  );
        map.put( "lang",Lang );
        map.put( "country",Country );
        Service service = Client.getClient().create( Service.class );
        Call<BrandResponse> call = service.getBrandData(  map);
        call.enqueue( new Callback<BrandResponse>() {
            @Override
            public void onResponse(Call<BrandResponse> call, Response<BrandResponse> response) {
                if (response.isSuccessful()) {


                    brandView.showBrandData( response.body().getData() );
                }
            }

            @Override
            public void onFailure(Call<BrandResponse> call, Throwable t) {
                   brandView.error();
                Toast.makeText( context, R.string.NoNetworkAvailable,
                        Toast.LENGTH_SHORT).show();
            }
        } );

    }
}
