package com.example.shosho.coupmix.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.shosho.coupmix.R;
import com.example.shosho.coupmix.api.Client;
import com.example.shosho.coupmix.api.Service;
import com.example.shosho.coupmix.model.OrderItemResponse;
import com.example.shosho.coupmix.model.User;
import com.example.shosho.coupmix.view.OrderItemView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderItemPresenter {
    Context context;
    OrderItemView orderItemView;

    public OrderItemPresenter(Context context, OrderItemView orderItemView) {
        this.context = context;
        this.orderItemView = orderItemView;
    }
public void   getOrderResult(User user)
{
    Map<String,String> map=new HashMap<>(  );
    {
        map.put( "c_name",user.getName() );
        map.put("c_email", user.getEmail());
        map.put( "c_address",user.getAddress() );
        map.put( "c_phone",user.getPhone() );
        map.put( "c_note",user.getNote() );
        Service service=Client.getClient().create( Service.class );
        Call<OrderItemResponse> call =service.getOrderItemData(  map);
        call.enqueue( new Callback<OrderItemResponse>() {
            @Override
            public void onResponse(Call<OrderItemResponse> call, Response<OrderItemResponse> response) {
                if (response.isSuccessful())
                {
                    orderItemView.openPage(response.body().getData());

                }
                else
                {
                    orderItemView.showError("");
                }
            }

            @Override
            public void onFailure(Call<OrderItemResponse> call, Throwable t) {
                Toast.makeText( context, R.string.NoNetworkAvailable,
                        Toast.LENGTH_SHORT).show();
            }
        } );
    }
}
}
