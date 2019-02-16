package com.example.shosho.coupmix.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.shosho.coupmix.R;
import com.example.shosho.coupmix.activity.SplashActivity;
import com.example.shosho.coupmix.api.Client;
import com.example.shosho.coupmix.api.Service;
import com.example.shosho.coupmix.model.GalleryResponse;
import com.example.shosho.coupmix.view.GalleryView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalleryPresenter {
    Context context;
    GalleryView galleryView;

    public GalleryPresenter(Context context, GalleryView galleryView) {
        this.context = context;
        this.galleryView = galleryView;
    }
    public  void getGallryResult(String Lang)
    {
        Map<String,String> map=new HashMap<>(  );
        map.put( "lang","ar");
        Service service=Client.getClient().create( Service.class );
        Call<GalleryResponse> call=service.getGalleryData( map );
        call.enqueue( new Callback<GalleryResponse>() {
            @Override
            public void onResponse(Call<GalleryResponse> call, Response<GalleryResponse> response) {
                if (response.isSuccessful())
                {
                 galleryView.showGalleryData( response.body().getData() );
                }
            }

            @Override
            public void onFailure(Call<GalleryResponse> call, Throwable t) {
                 galleryView.showError(  );
                Toast.makeText( context, R.string.NoNetworkAvailable,
                        Toast.LENGTH_SHORT).show();
            }
        } );
    }
}
