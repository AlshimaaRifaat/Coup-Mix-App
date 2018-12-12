package com.example.shosho.coupmix.presenter;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.example.shosho.coupmix.R;
import com.example.shosho.coupmix.api.Client;
import com.example.shosho.coupmix.api.Service;
import com.example.shosho.coupmix.fragment.HomeFragment;
import com.example.shosho.coupmix.model.BooksResponse;
import com.example.shosho.coupmix.model.LocationResponse;
import com.example.shosho.coupmix.view.BookView;
import com.example.shosho.coupmix.view.LocationView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationPresenter {
    Context context;
    LocationView locationView;

    public LocationPresenter(Context context, LocationView locationView) {
        this.context = context;
        this.locationView = locationView;
    }

    public void getLocationResult(String Lang,String Id) {
        Map<String,String> map=new HashMap<>(  );
        map.put( "lang",Lang );
        map.put( "id",Id );
        Service service = Client.getClient().create( Service.class );
        Call<LocationResponse> call = service.getLocationData(  map);
        call.enqueue( new Callback<LocationResponse>() {
            @Override
            public void onResponse(Call<LocationResponse> call, Response<LocationResponse> response) {
                if (response.isSuccessful()) {
                   locationView.showLocationData(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<LocationResponse> call, Throwable t) {
                locationView.error();

                Toast.makeText( context, R.string.NoNetworkAvailable,
                        Toast.LENGTH_SHORT).show();
            }
        } );

    }
}
