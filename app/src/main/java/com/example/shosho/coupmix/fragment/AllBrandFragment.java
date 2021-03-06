package com.example.shosho.coupmix.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import android.widget.Toast;

import com.example.shosho.coupmix.NetworkConnection;
import com.example.shosho.coupmix.R;
import com.example.shosho.coupmix.activity.NavigationActivity;
import com.example.shosho.coupmix.activity.SplashActivity;
import com.example.shosho.coupmix.adapter.AllBrandAdapter;
import com.example.shosho.coupmix.adapter.HomeCategoryAdapter;
import com.example.shosho.coupmix.adapter.SearchBrandAdapter;
import com.example.shosho.coupmix.model.AllBrandData;
import com.example.shosho.coupmix.model.SearchLocBrandData;
import com.example.shosho.coupmix.presenter.AllBrandPresenter;
import com.example.shosho.coupmix.presenter.BookPresenter;
import com.example.shosho.coupmix.presenter.SearchBrandPresenter;
import com.example.shosho.coupmix.view.AllBrandView;
import com.example.shosho.coupmix.view.BookView;
import com.example.shosho.coupmix.view.OnClickItemAllBrandView;
import com.example.shosho.coupmix.view.OnClickItemCategoryView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllBrandFragment extends Fragment implements
        AllBrandView,SwipeRefreshLayout.OnRefreshListener ,OnClickItemAllBrandView{

    NetworkConnection networkConnection;
    private SwipeRefreshLayout swipeRefreshLayout;

    RecyclerView recyclerViewAllBrand;
    AllBrandAdapter allBrandAdapter;
    AllBrandPresenter allBrandPresenter;

    Bundle bundle;
    Toolbar toolbar;
    public AllBrandFragment() {
        // Required empty public constructor
    }

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate( R.layout.fragment_all_brand, container, false );
        allBrandPresenter=new AllBrandPresenter( getContext(),this );

        Recycle();
        init();
        networkConnection=new NetworkConnection( getContext() );
        allBrand();
        bundle=new Bundle(  );

        swipeRefresh();
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

        return view;
    }

    private void allBrand() {
        allBrandPresenter=new AllBrandPresenter( getContext(),this );
        allBrandPresenter.getAllBrandResult( SplashActivity.Language );
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

                        allBrandPresenter.getAllBrandResult( SplashActivity.Language );

                }
            }
        } );

    }

    private void init()
    {
        swipeRefreshLayout=view.findViewById( R.id.all_brand_swip_refresh );
        toolbar=view.findViewById( R.id.all_brand_toolbar );
    }

    private void Recycle() {
        recyclerViewAllBrand=view.findViewById( R.id.all_brand_recycler_view);
    }


    @Override
    public void onRefresh() {
        if(networkConnection.isNetworkAvailable( getContext() ))
        {
            swipeRefreshLayout.setRefreshing( true );
            allBrandPresenter.getAllBrandResult(SplashActivity.Language );


        }else
        {
            Toast.makeText( getContext(), R.string.NoNetworkAvailable, Toast.LENGTH_SHORT ).show();
        }

    }

    @Override
    public void showAllBrandData(List<AllBrandData> allBrandDataList) {
        allBrandAdapter=new AllBrandAdapter( getContext(),allBrandDataList );
        allBrandAdapter.onClick( this );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewAllBrand.setLayoutManager(linearLayoutManager);
        recyclerViewAllBrand.setAdapter( allBrandAdapter );
        swipeRefreshLayout.setRefreshing( false );
    }

    @Override
    public void error() {
        swipeRefreshLayout.setRefreshing( false );

    }


    @Override
    public void showOnClickItemAllBrandResult(AllBrandData allBrandData) {
        CategoryItemFragment categoryItemFragment=new CategoryItemFragment();
        Bundle bundle=new Bundle(  );
        bundle.putInt( "id",allBrandData.getId() );
        categoryItemFragment.setArguments( bundle );
        getFragmentManager().beginTransaction().add( R.id.content_navigation,categoryItemFragment )
                .addToBackStack( null ).commit();
    }
}
