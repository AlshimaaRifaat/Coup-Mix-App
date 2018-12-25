package com.example.shosho.coupmix.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.shosho.coupmix.NetworkConnection;
import com.example.shosho.coupmix.R;
import com.example.shosho.coupmix.activity.SplashActivity;
import com.example.shosho.coupmix.adapter.AllCategoryAdapter;
import com.example.shosho.coupmix.adapter.HomeCategoryAdapter;
import com.example.shosho.coupmix.model.BookData;
import com.example.shosho.coupmix.model.SearchLocBrandData;
import com.example.shosho.coupmix.presenter.BookPresenter;
import com.example.shosho.coupmix.view.BookView;
import com.example.shosho.coupmix.view.OnClickItemCategoryView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment implements
        BookView,SwipeRefreshLayout.OnRefreshListener,OnClickItemCategoryView {

    NetworkConnection networkConnection;
    private SwipeRefreshLayout swipeRefreshLayout;

    RecyclerView recyclerViewCategory;
    AllCategoryAdapter allCategoryAdapter;
    BookPresenter bookPresenter;
    public CategoryFragment() {
        // Required empty public constructor
    }

View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate( R.layout.fragment_category, container, false );
        swipeRefreshLayout=view.findViewById( R.id.category_swip_refresh);
        networkConnection=new NetworkConnection( getContext() );
        init();
        category();
        swipRefresh();
        return view;
    }

    private void swipRefresh() {
        swipeRefreshLayout.setColorSchemeResources( android.R.color.holo_green_dark );
        swipeRefreshLayout.setEnabled( true );
        swipeRefreshLayout.setOnRefreshListener( this );
        swipeRefreshLayout.post( new Runnable() {
            @Override
            public void run() {
                if(networkConnection.isNetworkAvailable( getContext() ))
                {
                    swipeRefreshLayout.setRefreshing( true );
                    bookPresenter.getBookResult( SplashActivity.Language);
                }
            }
        } );
    }

    private void category() {
        bookPresenter=new BookPresenter( getContext(),this );
        bookPresenter.getBookResult( SplashActivity.Language );
    }

    private void init() {
        recyclerViewCategory=view.findViewById( R.id.category_recycler_view );
    }

    @Override
    public void onRefresh() {
        if(networkConnection.isNetworkAvailable( getContext() ))
        {
            swipeRefreshLayout.setRefreshing( true );
            bookPresenter.getBookResult(SplashActivity.Language );


        }else
        {
            Toast.makeText( getContext(), R.string.NoNetworkAvailable, Toast.LENGTH_SHORT ).show();
        }
    }

    @Override
    public void showData(List<BookData> booksData) {
        allCategoryAdapter=new AllCategoryAdapter( getContext(),booksData );
        allCategoryAdapter.onClick( this );
        recyclerViewCategory.setLayoutManager( new GridLayoutManager( getContext(),2) );
        recyclerViewCategory.setAdapter( allCategoryAdapter );
        swipeRefreshLayout.setRefreshing( false );
    }

    @Override
    public void error() {
        swipeRefreshLayout.setRefreshing( false );
    }

    @Override
    public void showOnClickItemCategoryResult(SearchLocBrandData searchLocBrandData) {
        SearchLocBrandFragment searchLocBrandFragment=new SearchLocBrandFragment();
        Bundle bundle=new Bundle( );
        bundle.putString( "id" ,searchLocBrandData.getId());
        searchLocBrandFragment.setArguments( bundle );
        getFragmentManager().beginTransaction().replace( R.id.content_navigation
                ,searchLocBrandFragment )
                .addToBackStack( null ).commit();
    }
}
