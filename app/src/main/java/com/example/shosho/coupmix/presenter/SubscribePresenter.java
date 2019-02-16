package com.example.shosho.coupmix.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.shosho.coupmix.R;
import com.example.shosho.coupmix.api.Client;
import com.example.shosho.coupmix.api.Service;
import com.example.shosho.coupmix.model.OrderItemResponse;
import com.example.shosho.coupmix.model.SubscribeResponse;
import com.example.shosho.coupmix.model.User;
import com.example.shosho.coupmix.view.OrderItemView;
import com.example.shosho.coupmix.view.SubscribeView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubscribePresenter {
    Context context;
    SubscribeView subscribeView;

    public SubscribePresenter(Context context, SubscribeView subscribeView) {
        this.context = context;
        this.subscribeView = subscribeView;
    }

    public void  getSubscribeResult(User user)
    {
        Map<String,String> map=new HashMap<>(  );
        {
            map.put( "email",user.getEmail() );

            Service service=Client.getClient().create( Service.class );
            Call<SubscribeResponse> call =service.getSubscribeData(  map);
            call.enqueue( new Callback<SubscribeResponse>() {
                @Override
                public void onResponse(Call<SubscribeResponse> call, Response<SubscribeResponse> response) {
                    if (response.isSuccessful())
                    {
                        subscribeView.showSubscribeResult(response.body().getData());

                    }
                    else
                    {
                        subscribeView.showError("");
                    }
                }

                @Override
                public void onFailure(Call<SubscribeResponse> call, Throwable t) {
                    Toast.makeText( context, R.string.NoDataFound,
                            Toast.LENGTH_SHORT).show();
                }
            } );
        }
    }
}
