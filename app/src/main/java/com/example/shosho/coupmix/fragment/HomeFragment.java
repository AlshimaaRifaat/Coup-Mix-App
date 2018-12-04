package com.example.shosho.coupmix.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.shosho.coupmix.NetworkConnection;
import com.example.shosho.coupmix.R;
import com.example.shosho.coupmix.adapter.BannerAdapter;
import com.example.shosho.coupmix.adapter.HomeCategoryAdapter;
import com.example.shosho.coupmix.adapter.HomeFeatureProductAdapter;
import com.example.shosho.coupmix.model.BookData;
import com.example.shosho.coupmix.presenter.BookPresenter;
import com.example.shosho.coupmix.view.BookView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements BookView,SwipeRefreshLayout.OnRefreshListener {
//RecyclerView recyclerViewBanner;
BookPresenter bookPresenter;
//BannerAdapter bannerAdapter;

/*int position;
List<BookData> banner =new ArrayList();
boolean end;*/

NetworkConnection networkConnection;
private SwipeRefreshLayout swipeRefreshLayout;

RecyclerView recyclerViewCategory;
HomeCategoryAdapter homeCategoryAdapter;

//RecyclerView recyclerViewFeatureProduct;
//HomeFeatureProductAdapter homeFeatureProductAdapter;
    View view;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       view= inflater.inflate( R.layout.fragment_home, container, false );
       swipeRefreshLayout=view.findViewById( R.id.home_swip_refresh );
       networkConnection=new NetworkConnection( getContext() );
       recycle();
      // banner();
       category();
      // featureProduct();
       swipRefresh();

        return view;

    }

   /* private void featureProduct() {
        bookPresenter=new BookPresenter( getContext(),this );
        bookPresenter.getBookResult( "ar","translation" );
    }*/

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
                   // bookPresenter.getBookResult( "ar","slider" );
                    bookPresenter.getBookResult( );
                   // bookPresenter.getBookResult( "ar","translation" );
                }
            }
        } );
    }

    private void category() {
        bookPresenter=new BookPresenter( getContext(),this );
        bookPresenter.getBookResult(  );


    }

   /* private void banner() {
        bookPresenter=new BookPresenter( getContext(),this );
        bookPresenter.getBookResult( "ar","slider" );
    }*/

    private void recycle()
    {
       // recyclerViewBanner=view.findViewById( R.id.home_recycler_banner );
        recyclerViewCategory=view.findViewById( R.id.home_recycler_view_category );
       // recyclerViewFeatureProduct=view.findViewById( R.id.home_recycler_view_features_products );
    }

    @Override
    public void showData(List<BookData> booksData) {
       /* banner=booksData;
        bannerAdapter=new BannerAdapter( getContext(),booksData );
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager( getContext() );
        linearLayoutManager.setOrientation( LinearLayoutManager.HORIZONTAL );
        recyclerViewBanner.setLayoutManager( linearLayoutManager );
        recyclerViewBanner.setAdapter( bannerAdapter );

        if(booksData.size()>1) {
            Timer timer = new Timer();
            timer.scheduleAtFixedRate( new AutoScrollTask(), 3000, 5000 );
        }*/

        homeCategoryAdapter=new HomeCategoryAdapter( getContext(),booksData );
        recyclerViewCategory.setLayoutManager( new GridLayoutManager( getContext(),3) );
        recyclerViewCategory.setAdapter( homeCategoryAdapter );

       /* homeFeatureProductAdapter=new HomeFeatureProductAdapter( getContext(),booksData );
        recyclerViewFeatureProduct.setLayoutManager( new GridLayoutManager( getContext(),2 ) );
        recyclerViewFeatureProduct.setAdapter( homeFeatureProductAdapter );*/

        swipeRefreshLayout.setRefreshing( false );


    }

    @Override
    public void error() {
        swipeRefreshLayout.setRefreshing( false );

    }

    @Override
    public void onRefresh() {
        if(networkConnection.isNetworkAvailable( getContext() ))
        {
            swipeRefreshLayout.setRefreshing( true );
          //  bookPresenter.getBookResult( "ar","slider" );
            bookPresenter.getBookResult(  );
           // bookPresenter.getBookResult( "ar","translation" );
        }else
        {
            Toast.makeText( getContext(), R.string.NoNetworkAvailable, Toast.LENGTH_SHORT ).show();
        }

    }

   /* public class AutoScrollTask extends TimerTask
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
    }*/
}
