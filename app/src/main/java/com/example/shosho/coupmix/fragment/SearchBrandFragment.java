package com.example.shosho.coupmix.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shosho.coupmix.NetworkConnection;
import com.example.shosho.coupmix.R;
import com.example.shosho.coupmix.activity.SplashActivity;
import com.example.shosho.coupmix.adapter.SearchBrandAdapter;
import com.example.shosho.coupmix.model.SearchBrandData;
import com.example.shosho.coupmix.presenter.SearchBrandPresenter;
import com.example.shosho.coupmix.view.SearchBrandView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchBrandFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,SearchBrandView {
    SwipeRefreshLayout swipeRefreshLayout;
    NetworkConnection networkConnection;
SearchBrandPresenter searchBrandPresenter;
SearchBrandAdapter searchBrandAdapter;
RecyclerView recyclerView;


    public SearchBrandFragment() {
        // Required empty public constructor
    }
Bundle bundle;
    String Key;
View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       view=inflater.inflate( R.layout.fragment_search_brand, container, false );
        searchBrandPresenter=new SearchBrandPresenter( getContext(),this );
        Recycle();
        init();
        networkConnection=new NetworkConnection( getContext() );
        bundle = this.getArguments();
        if (bundle != null) {

            Key=bundle.getString( "key" );

        }
        swipeRefresh();
       return view;
    }

    private void swipeRefresh() {
        swipeRefreshLayout.setColorSchemeResources( android.R.color.holo_orange_dark  );
        swipeRefreshLayout.setEnabled( true );
        swipeRefreshLayout.setOnRefreshListener( this );
        swipeRefreshLayout.post( new Runnable() {
            @Override
            public void run() {
                if (networkConnection.isNetworkAvailable( getContext() ))
                {
                    swipeRefreshLayout.setRefreshing( true );
                   if (Key!=null)
                    {
                        searchBrandPresenter.getSearhBrandResult( SplashActivity.Language,Key );
                    }
                }
            }
        } );
    }

    private void init() {
        swipeRefreshLayout=view.findViewById( R.id.search_brand_swip_refresh );

    }

    private void Recycle() {
        recyclerView=view.findViewById( R.id.search_brand_recycler_view );
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing( true );
        searchBrandPresenter.getSearhBrandResult( SplashActivity.Language,Key );
    }

    @Override
    public void showSearchBrandData(List<SearchBrandData> searchBrandDataList) {

        searchBrandAdapter=new SearchBrandAdapter( getContext(),searchBrandDataList );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter( searchBrandAdapter );
        swipeRefreshLayout.setRefreshing( false );

    }

    @Override
    public void error() {
        swipeRefreshLayout.setRefreshing( false );
    }
}
