package com.example.shosho.coupmix.presenter;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.example.shosho.coupmix.activity.SplashActivity;
import com.example.shosho.coupmix.api.Client;
import com.example.shosho.coupmix.api.Service;
import com.example.shosho.coupmix.fragment.HomeFragment;
import com.example.shosho.coupmix.model.BooksResponse;
import com.example.shosho.coupmix.model.FeatureProductResponse;
import com.example.shosho.coupmix.view.BookView;
import com.example.shosho.coupmix.view.FeatureProductView;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.shosho.coupmix.R;
public class FeatureProductPresenter {
    Context context;
    FeatureProductView featureProductView;

    public FeatureProductPresenter(Context context, FeatureProductView featureProductView) {
        this.context = context;
        this.featureProductView = featureProductView;
    }

    public void getFeatureProductResult(String Lang) {
        Map<String,String> map=new HashMap<>(  );
        map.put( "lang",SplashActivity.Language );
        Service service = Client.getClient().create( Service.class );
        Call<FeatureProductResponse> call = service.getFeaturesProductsData(  map);
        call.enqueue( new Callback<FeatureProductResponse>() {
            @Override
            public void onResponse(Call<FeatureProductResponse> call, Response<FeatureProductResponse> response) {
                if (response.isSuccessful()) {

                   // HomeFragment.scrollViewHome.setVisibility( View.VISIBLE );
                    featureProductView.showFeaturesProductsData( response.body().getData() );
                }
            }

            @Override
            public void onFailure(Call<FeatureProductResponse> call, Throwable t) {
                featureProductView.error();
                Toast.makeText( context, R.string.NoNetworkAvailable,
                        Toast.LENGTH_SHORT).show();
            }
        } );

    }
}
