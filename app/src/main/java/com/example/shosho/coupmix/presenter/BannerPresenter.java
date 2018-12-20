package com.example.shosho.coupmix.presenter;

import android.content.Context;

import com.example.shosho.coupmix.R;
import com.example.shosho.coupmix.api.Client;
import com.example.shosho.coupmix.api.Service;
import com.example.shosho.coupmix.model.BannerResponse;
import com.example.shosho.coupmix.view.BannerView;
import com.example.shosho.coupmix.fragment.HomeFragment;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BannerPresenter {
    Context context;
    BannerView bannerView;

    public BannerPresenter(Context context, BannerView bannerView) {
        this.context = context;
        this.bannerView = bannerView;
    }
    public void getBannerResult()
    {
        Service service=Client.getClient().create( Service.class );
        Call<BannerResponse> call=service.getBannerData();
        call.enqueue( new Callback<BannerResponse>() {
            @Override
            public void onResponse(Call<BannerResponse> call, Response<BannerResponse> response) {
                if(response.isSuccessful())
                {
                   // HomeFragment.scrollViewHome.setVisibility( View.VISIBLE );
                    bannerView.showBannerData( response.body().getData() );
                }
            }

            @Override
            public void onFailure(Call<BannerResponse> call, Throwable t) {

                bannerView.error();
                Toast.makeText( context, R.string.NoNetworkAvailable,
                        Toast.LENGTH_SHORT).show();
            }
        } );
    }
}
