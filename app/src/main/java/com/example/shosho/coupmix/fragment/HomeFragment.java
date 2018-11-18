package com.example.shosho.coupmix.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shosho.coupmix.activity.LanguageActivity;
import com.example.shosho.coupmix.R;
import com.example.shosho.coupmix.adapter.BannerAdapter;
import com.example.shosho.coupmix.model.BookData;
import com.example.shosho.coupmix.presenter.BookPresenter;
import com.example.shosho.coupmix.view.PictureView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements PictureView {
RecyclerView recyclerViewBanner;
BookPresenter bookPresenter;
BannerAdapter bannerAdapter;

int position;
List<BookData> banner =new ArrayList();
boolean end;
    View view;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       view= inflater.inflate( R.layout.fragment_home, container, false );
       recycle();
       banner();
        return view;

    }

    private void banner() {
        bookPresenter=new BookPresenter( getContext(),this );
        bookPresenter.getBookResult( "ar","slider" );
    }

    private void recycle() {
        recyclerViewBanner=view.findViewById( R.id.home_recycler_banner );
    }

    @Override
    public void showPictureData(List<BookData> booksData) {
        banner=booksData;
        bannerAdapter=new BannerAdapter( getContext(),booksData );
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager( getContext() );
        linearLayoutManager.setOrientation( LinearLayoutManager.HORIZONTAL );
        recyclerViewBanner.setLayoutManager( linearLayoutManager );
        recyclerViewBanner.setAdapter( bannerAdapter );

        if(booksData.size()>1) {
            Timer timer = new Timer();
            timer.scheduleAtFixedRate( new AutoScrollTask(), 2000, 4000 );
        }

    }

    @Override
    public void error() {

    }
    public class AutoScrollTask extends TimerTask
    {

        @Override
        public void run() {
            if(position==banner.size()-1)
            {
                end=true;
            }else if(position==0)
            {
                end=false;
            }

            if(!end)
            {
                position++;
            }else
            {
                position--;
            }
            recyclerViewBanner.smoothScrollToPosition( position );
        }
    }
}
