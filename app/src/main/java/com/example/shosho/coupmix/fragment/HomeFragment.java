package com.example.shosho.coupmix.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.shosho.coupmix.NetworkConnection;
import com.example.shosho.coupmix.R;
import com.example.shosho.coupmix.activity.NavigationActivity;
import com.example.shosho.coupmix.activity.SplashActivity;
import com.example.shosho.coupmix.adapter.BannerAdapter;
import com.example.shosho.coupmix.adapter.HomeCategoryAdapter;
import com.example.shosho.coupmix.adapter.HomeFeatureProductAdapter;
import com.example.shosho.coupmix.model.BannerData;
import com.example.shosho.coupmix.model.BookData;
import com.example.shosho.coupmix.model.CategorySendID;
import com.example.shosho.coupmix.model.FeatureProductData;
import com.example.shosho.coupmix.model.FeatureProductDetails;
import com.example.shosho.coupmix.model.SearchBrandData;
import com.example.shosho.coupmix.model.SearchLocBrandData;
import com.example.shosho.coupmix.presenter.BannerPresenter;
import com.example.shosho.coupmix.presenter.BookPresenter;
import com.example.shosho.coupmix.presenter.FeatureProductPresenter;
import com.example.shosho.coupmix.presenter.SearchBrandPresenter;
import com.example.shosho.coupmix.presenter.SearchLocBrandPresenter;
import com.example.shosho.coupmix.view.BannerView;
import com.example.shosho.coupmix.view.BookView;
import com.example.shosho.coupmix.view.OnClickItemFeatureProductView;
import com.example.shosho.coupmix.view.FeatureProductView;
import com.example.shosho.coupmix.view.OnClickItemCategoryView;
import com.example.shosho.coupmix.view.SearchBrandView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements
        BookView,SwipeRefreshLayout.OnRefreshListener,
        BannerView,OnClickItemCategoryView,OnClickItemFeatureProductView
        ,FeatureProductView {
    RecyclerView recyclerViewBanner;
    BookPresenter bookPresenter;
    BannerAdapter bannerAdapter;

    public static ScrollView scrollViewHome;
    int position;
    List<BannerData> banners =new ArrayList();
    boolean end;
    BannerPresenter bannerPresenter;


    NetworkConnection networkConnection;
    private SwipeRefreshLayout swipeRefreshLayout;

    RecyclerView recyclerViewCategory;
    HomeCategoryAdapter homeCategoryAdapter;

    RecyclerView recyclerViewFeatureProduct;
    HomeFeatureProductAdapter homeFeatureProductAdapter;

    FeatureProductPresenter featureProductPresenter;

    SharedPreferences sharedPreferences;
    Toolbar toolbar;

    EditText editTextSearch;
    ImageView imageViewSearch;
    SearchBrandPresenter searchBrandPresenter;
    String Image;
    String Title ;
    String Description;
    String Address;
    //String Lang;
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

        init();
        banner();
        category();
        featureProduct();
        swipRefresh();
        NavigationActivity.toggle = new ActionBarDrawerToggle(
                getActivity(), NavigationActivity.drawer, toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        NavigationActivity.drawer.addDrawerListener(NavigationActivity.toggle);
        NavigationActivity.toggle.syncState();

        NavigationActivity.toggle.setDrawerIndicatorEnabled(false);
        toolbar.setNavigationIcon(R.drawable. icon_menu);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (NavigationActivity.drawer.isDrawerOpen(GravityCompat.START)) {
                    NavigationActivity.drawer.closeDrawer(GravityCompat.START);
                } else {
                    NavigationActivity.drawer.openDrawer(GravityCompat.START);
                }
            }
        });

        imageViewSearch.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchByBrand();
            }
        } );
        return view;

    }

    private void searchByBrand() {
        if (networkConnection.isNetworkAvailable( getContext() ))
        {
            if(!editTextSearch.getText().toString().equals( "" ))
            {
                CategoryItemFragment categoryItemFragment = new CategoryItemFragment();
                Bundle bundle = new Bundle();
                bundle.putString( "key", editTextSearch.getText().toString() );
                categoryItemFragment.setArguments( bundle );
                getFragmentManager().beginTransaction().replace( R.id.content_navigation,categoryItemFragment )
                        .addToBackStack( null ).commit();
            }
        }
        else
        {
            Toast.makeText( getContext(), R.string.Checkyourinternetconnection
                    , Toast.LENGTH_SHORT ).show();
        }
    }



    private void featureProduct() {
        featureProductPresenter=new FeatureProductPresenter( getContext(),this );
        featureProductPresenter.getFeatureProductResult("en"  );
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
                    bannerPresenter.getBannerResult();
                    bookPresenter.getBookResult( "en");
                    featureProductPresenter.getFeatureProductResult( "en" );
                }
            }
        } );
    }

    private void category() {
        bookPresenter=new BookPresenter( getContext(),this );

        bookPresenter.getBookResult( "en" );



    }

    private void banner() {
        bannerPresenter=new BannerPresenter( getContext(),this );
        bannerPresenter.getBannerResult();
    }

    private void init()
    {
        scrollViewHome=view.findViewById( R.id.home_scroll );
        recyclerViewBanner=view.findViewById( R.id.home_recycler_banner );
        recyclerViewCategory=view.findViewById( R.id.home_recycler_view_category );
        recyclerViewFeatureProduct=view.findViewById( R.id.home_recycler_view_features_products );
        toolbar=view.findViewById( R.id.home_toolbar );
        editTextSearch=view.findViewById( R.id.home_edit_text_search );
        imageViewSearch=view.findViewById( R.id.home_image_search );
    }

    @Override
    public void showData(List<BookData> booksData) {


        homeCategoryAdapter=new HomeCategoryAdapter( getContext(),booksData );
        homeCategoryAdapter.onClick( this );
        recyclerViewCategory.setLayoutManager( new GridLayoutManager( getContext(),3) );
        recyclerViewCategory.setAdapter( homeCategoryAdapter );



    }

    @Override
    public void showBrandData(List<BookData> booksData) {

    }

    @Override
    public void showBannerData(List<BannerData> bannersData) {

        banners=bannersData;
        bannerAdapter=new BannerAdapter( getContext(),bannersData );
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager( getContext() );
        linearLayoutManager.setOrientation( LinearLayoutManager.HORIZONTAL );
        recyclerViewBanner.setLayoutManager( linearLayoutManager );
        recyclerViewBanner.setAdapter( bannerAdapter );

        if(banners.size()>1) {
            Timer timer = new Timer();
            timer.scheduleAtFixedRate( new AutoScrollTask(), 2000, 2000 );
        }
        swipeRefreshLayout.setRefreshing( false );
    }


    @Override
    public void showFeaturesProductsData(List<FeatureProductData>  featuresProductsData) {
        homeFeatureProductAdapter=new HomeFeatureProductAdapter( getContext(),featuresProductsData );
        homeFeatureProductAdapter.onClick( this );
        recyclerViewFeatureProduct.setLayoutManager( new GridLayoutManager( getContext(),2 ) );
        recyclerViewFeatureProduct.setAdapter( homeFeatureProductAdapter );

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
            bookPresenter.getBookResult("en"  );
            featureProductPresenter.getFeatureProductResult("en"  );

        }else
        {
            Toast.makeText( getContext(), R.string.NoNetworkAvailable, Toast.LENGTH_SHORT ).show();
        }
    }






    @Override
    public void showOnClickItemFeatureProductResult(FeatureProductDetails featureProductDetails) {
        DetailsCategoryItemFragment detailsCategoryItemFragment=new DetailsCategoryItemFragment();
        Bundle bundle=new Bundle( );
        bundle.putString( "image" ,featureProductDetails.getImage());
        bundle.putString( "name" ,featureProductDetails.getName());
        bundle.putString( "discount" ,featureProductDetails.getDiscount());
        bundle.putString( "couponDetails" ,featureProductDetails.getCouponDetails() );
        bundle.putString( "featuresOffer" ,featureProductDetails.getFeaturesOffer() );
        bundle.putString( "country" ,featureProductDetails.getCountry() );
        bundle.putString( "phone" ,featureProductDetails.getPhone() );
        detailsCategoryItemFragment.setArguments( bundle );

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_navigation, detailsCategoryItemFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


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


    public class AutoScrollTask extends TimerTask
    {

        @Override
        public void run() {
            if(position==banners.size()-1)
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
