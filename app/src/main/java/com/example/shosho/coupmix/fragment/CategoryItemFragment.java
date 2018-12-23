package com.example.shosho.coupmix.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shosho.coupmix.NetworkConnection;
import com.example.shosho.coupmix.R;
import com.example.shosho.coupmix.activity.SplashActivity;
import com.example.shosho.coupmix.adapter.CategoryItemAdapter;
import com.example.shosho.coupmix.adapter.SearchBrandAdapter;
import com.example.shosho.coupmix.model.CategoryItemDetails;
import com.example.shosho.coupmix.model.SearchBrandData;
import com.example.shosho.coupmix.model.SearchLocBrandData;
import com.example.shosho.coupmix.presenter.SearchBrandPresenter;
import com.example.shosho.coupmix.presenter.SearchLocBrandPresenter;
import com.example.shosho.coupmix.view.OnClickDetailsCategoryItemView;
import com.example.shosho.coupmix.view.SearchBrandView;
import com.example.shosho.coupmix.view.SearchLocBrandView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryItemFragment extends Fragment implements
        SearchLocBrandView,OnClickDetailsCategoryItemView,SwipeRefreshLayout.OnRefreshListener
{



    RecyclerView recyclerView;
    SearchLocBrandPresenter searchLocBrandPresenter;
    CategoryItemAdapter categoryItemAdapter;
    SearchBrandAdapter categoryItemBrandAdapter;
    String Location,Brand;


    Button showdetails;
    ImageView imageBack;
    SwipeRefreshLayout swipeRefreshLayout;
    NetworkConnection networkConnection;

    TextView textToolbar;

    Bundle bundle;
    SearchBrandPresenter searchBrandPresenter;
    //CategoryItemDetails categoryItemDetails;
    public CategoryItemFragment() {
        // Required empty public constructor
    }

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate( R.layout.fragment_category_item, container, false );
        searchLocBrandPresenter = new SearchLocBrandPresenter( getContext(), this );

        Recycle();
        init();
        networkConnection=new NetworkConnection( getContext() );

      /* imageBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .replace( R.id.content_navigation,new SearchLocBrandFragment() )
                        .addToBackStack(null  ).commit();
            }
        } );*/
        bundle = this.getArguments();
        if (bundle != null) {
            Location = bundle.getString( "location" );
            Brand = bundle.getString( "brand" );


        }
        swipRefresh();
        return view;
    }

    private void swipRefresh() {
        swipeRefreshLayout.setColorSchemeResources( android.R.color.holo_orange_dark  );
        swipeRefreshLayout.setEnabled( true );
        swipeRefreshLayout.setOnRefreshListener( this );
        swipeRefreshLayout.post( new Runnable() {
            @Override
            public void run() {
                if (networkConnection.isNetworkAvailable( getContext() ))
                {
                    swipeRefreshLayout.setRefreshing( true );
                    if(Location != null && Brand != null ) {
                        searchLocBrandPresenter.getSearchLocBrandResult( SplashActivity.Language, Location, Brand );
                    }

                }
            }
        } );
    }
    private void init()
    {
        showdetails=view.findViewById( R.id.row_category_item_btn );
        // imageBack=view.findViewById( R.id.category_item_image_back );
        swipeRefreshLayout=view.findViewById( R.id.category_item_swip_refresh );
        textToolbar=view.findViewById( R.id.category_item_text_toolbar );



    }


    private void Recycle() {
        recyclerView=view.findViewById( R.id.category_item_recycler_view );
    }

    @Override
    public void showSearhLocBrandResult(List<SearchLocBrandData> locBrandDataList) {
        textToolbar.setText( SearchLocBrandFragment.BrandModel );
        categoryItemAdapter=new CategoryItemAdapter( getContext(),locBrandDataList );
        categoryItemAdapter.onClick( this );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter( categoryItemAdapter );
        swipeRefreshLayout.setRefreshing( false );


    }



    @Override
    public void error() {
        swipeRefreshLayout.setRefreshing( false );
    }



    @Override
    public void showOnClickDetailsCategoryItemResult(CategoryItemDetails categoryItemDetails) {

        DetailsCategoryItemFragment detailsCategoryItemFragment=new DetailsCategoryItemFragment();
        Bundle bundle=new Bundle( );
        bundle.putString( "image" ,categoryItemDetails.getImage());
        bundle.putString( "name" ,categoryItemDetails.getName());
        bundle.putString( "discount" ,categoryItemDetails.getDiscount());
        bundle.putString( "couponDetails" ,categoryItemDetails.getCouponDetails() );
        bundle.putString( "featuresOffer" ,categoryItemDetails.getFeaturesOffer() );
        bundle.putString( "country" ,categoryItemDetails.getCountry() );
        bundle.putString( "phone" ,categoryItemDetails.getPhone() );
        detailsCategoryItemFragment.setArguments( bundle );
        getFragmentManager().beginTransaction().replace(R.id.content_navigation,detailsCategoryItemFragment)
                .addToBackStack( null ).commit();

        //    swipeRefreshLayout.setRefreshing( false );
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing( true );
        searchLocBrandPresenter.getSearchLocBrandResult( "en", Location, Brand );
    }
}
