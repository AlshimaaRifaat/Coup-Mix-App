package com.example.shosho.coupmix.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.shosho.coupmix.api.Client;
import com.example.shosho.coupmix.api.Service;
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

    public BookPresenter(Context context, BookView pictureView) {
        this.context = context;
        this.bookView = pictureView;
    }

    public void getBookResult() {

        Service service = Client.getClient().create( Service.class );
        Call<BooksResponse> call = service.getBooksData(  );
        call.enqueue( new Callback<BooksResponse>() {
            @Override
            public void onResponse(Call<BooksResponse> call, Response<BooksResponse> response) {
                if (response.isSuccessful()) {
                    bookView.showData( response.body().getCat() );
                }
            }

            @Override
            public void onFailure(Call<BooksResponse> call, Throwable t) {
                bookView.error();
                Toast.makeText( context, "غير متصل بالانترنت ,من فضلك تاكد من اتصالك بالانترنت", Toast.LENGTH_SHORT ).show();
            }
        } );

    }
}
