package com.example.shosho.coupmix.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.shosho.coupmix.R;
import com.example.shosho.coupmix.activity.SplashActivity;
import com.example.shosho.coupmix.api.Client;
import com.example.shosho.coupmix.api.Service;
import com.example.shosho.coupmix.model.BrandResponse;
import com.example.shosho.coupmix.model.SearchBrandResponse;
import com.example.shosho.coupmix.view.SearchBrandView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchBrandPresenter {
    Context context;
    SearchBrandView searchBrandView;

    public SearchBrandPresenter(Context context, SearchBrandView searchBrandView) {
        this.context = context;
        this.searchBrandView = searchBrandView;
    }

    public void getSearhBrandResult(String Lang, String Key) {
        Map<String, String> map = new HashMap<>();
        map.put( "lang", SplashActivity.Language );
        map.put( "key", Key );
        Service service = Client.getClient().create( Service.class );
        Call<SearchBrandResponse> call = service.getSearchBrandData( map );
        call.enqueue( new Callback<SearchBrandResponse>() {
            @Override
            public void onResponse(Call<SearchBrandResponse> call, Response<SearchBrandResponse> response) {
                searchBrandView.showSearchBrandData( response.body().getData() );
            }

            @Override
            public void onFailure(Call<SearchBrandResponse> call, Throwable t) {
                searchBrandView.error();
                Toast.makeText( context, R.string.NoDataFound,
                        Toast.LENGTH_SHORT).show();

            }
        } );
    }
}