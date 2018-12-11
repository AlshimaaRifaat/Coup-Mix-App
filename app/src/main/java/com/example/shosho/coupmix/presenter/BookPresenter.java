package com.example.shosho.coupmix.presenter;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.example.shosho.coupmix.R;
import com.example.shosho.coupmix.api.Client;
import com.example.shosho.coupmix.api.Service;
import com.example.shosho.coupmix.fragment.HomeFragment;
import com.example.shosho.coupmix.model.BooksResponse;
import com.example.shosho.coupmix.view.BookView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookPresenter {
    Context context;
    BookView bookView;

    public BookPresenter(Context context, BookView bookView) {
        this.context = context;
        this.bookView = bookView;
    }

    public void getBookResult(String Lang) {
        Map<String,String> map=new HashMap<>(  );
        map.put( "lang","en" );
        Service service = Client.getClient().create( Service.class );
        Call<BooksResponse> call = service.getBooksData(  map);
        call.enqueue( new Callback<BooksResponse>() {
            @Override
            public void onResponse(Call<BooksResponse> call, Response<BooksResponse> response) {
                if (response.isSuccessful()) {

                    HomeFragment.scrollViewHome.setVisibility( View.VISIBLE );
                    bookView.showData( response.body().getData() );
                }
            }

            @Override
            public void onFailure(Call<BooksResponse> call, Throwable t) {
                bookView.error();
                Toast.makeText( context, R.string.NoNetworkAvailable,
                        Toast.LENGTH_SHORT).show();
            }
        } );

    }
}
