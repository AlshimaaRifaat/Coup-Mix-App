package com.example.shosho.coupmix.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.shosho.coupmix.R;
import com.example.shosho.coupmix.activity.SplashActivity;
import com.example.shosho.coupmix.api.Client;
import com.example.shosho.coupmix.api.Service;
import com.example.shosho.coupmix.model.SponsorResponse;
import com.example.shosho.coupmix.view.SponsorView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SponsorPresenter {
    Context context;
    SponsorView sponsorView;

    public SponsorPresenter(Context context, SponsorView sponsorView) {
        this.context = context;
        this.sponsorView = sponsorView;
    }

    public void getSponsorResult(String Lang) {
        Map<String, String> map = new HashMap<>();
        map.put( "lang", SplashActivity.Language );
        Service service = Client.getClient().create( Service.class );
        Call<SponsorResponse> call = service.getSponsorData( map );
        call.enqueue( new Callback<SponsorResponse>() {
            @Override
            public void onResponse(Call<SponsorResponse> call, Response<SponsorResponse> response) {
               if (response.isSuccessful())
               {
                   sponsorView.showSponsorData( response.body().getData() );
               }
            }

            @Override
            public void onFailure(Call<SponsorResponse> call, Throwable t) {
                 sponsorView.error();
                Toast.makeText( context, R.string.NoNetworkAvailable,
                        Toast.LENGTH_SHORT).show();
            }
        } );
    }
}

