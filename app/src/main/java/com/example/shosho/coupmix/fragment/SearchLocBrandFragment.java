package com.example.shosho.coupmix.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.shosho.coupmix.NetworkConnection;
import com.example.shosho.coupmix.R;
import com.example.shosho.coupmix.activity.NavigationActivity;
import com.example.shosho.coupmix.activity.SplashActivity;
import com.example.shosho.coupmix.adapter.BrandSpinnerAdapter;
import com.example.shosho.coupmix.adapter.LocationSpinnerAdapter;
import com.example.shosho.coupmix.model.BookData;
import com.example.shosho.coupmix.model.BrandData;
import com.example.shosho.coupmix.model.LocationData;
import com.example.shosho.coupmix.model.SearchLocBrandData;
import com.example.shosho.coupmix.presenter.BookPresenter;
import com.example.shosho.coupmix.presenter.BrandPresenter;
import com.example.shosho.coupmix.presenter.LocationPresenter;
import com.example.shosho.coupmix.presenter.SearchLocBrandPresenter;
import com.example.shosho.coupmix.view.BookView;
import com.example.shosho.coupmix.view.BrandView;
import com.example.shosho.coupmix.view.LocationView;
import com.example.shosho.coupmix.view.SearchLocBrandView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchLocBrandFragment extends Fragment implements  AdapterView.OnItemSelectedListener
        ,LocationView,BrandView,SwipeRefreshLayout.OnRefreshListener
{
    //ArrayAdapter<BookData> booksAdapter;
    LocationPresenter locationPresenter;
    Spinner locationSpinner;
    Integer LocationModelID;
    String LocationModel;
    LocationSpinnerAdapter locationSpinnerAdapter;
    Button searchBtn;

    BrandPresenter brandPresenter;
    Spinner brandSpinner;
    Integer BrandModelId;
    public static String BrandModel;
    BrandSpinnerAdapter brandSpinnerAdapter;
    ImageView imageBack;
    SwipeRefreshLayout swipeRefreshLayout;
    NetworkConnection networkConnection;

    public SearchLocBrandFragment() {
        // Required empty public constructor
    }

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=  inflater.inflate( R.layout.fragment_search_loc_brand, container, false );
        // NavigationActivity.toolbar.setVisibility(View.GONE );
        init();


        networkConnection=new NetworkConnection( getContext() );
      /*imageBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager(); // or 'getSupportFragmentManager();'
                int count = fm.getBackStackEntryCount();
                if(count!=0) {
                    for (int i = 0; i < count; ++i) {
                        fm.popBackStack();
                    }
                }
                getFragmentManager().beginTransaction()
                        .replace( R.id.content_navigation,new HomeFragment() )
                        .commit();
            }
        } );*/
        searchBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CategoryItemFragment categoryItemFragment=new CategoryItemFragment();
                Bundle bundle = new Bundle();
                bundle.putString("location", LocationModel);
                bundle.putString("brand", BrandModel);
                categoryItemFragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace
                        ( R.id.content_navigation,categoryItemFragment )
                        .addToBackStack( null ).commit();
            }
        } );
        locationPresenter=new LocationPresenter( getContext(),this );


        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String Id = bundle.getString( "id" );
            locationPresenter.getLocationResult( SplashActivity.Language, Id );
        }

        brandPresenter=new BrandPresenter( getContext(),this );

        //searchLocBrandPresenter=new SearchLocBrandPresenter( getContext(),this );

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
                    brandPresenter.getBrandResult("en",LocationModel  );
                }
            }
        } );
    }

    private void init()
    {
        locationSpinner=view.findViewById( R.id.search_location_spinner );
        brandSpinner=view.findViewById( R.id.search_brand_spinner );
        searchBtn=view.findViewById( R.id.search_btn2 );
        //  imageBack=view.findViewById( R.id.search_image_back );
        swipeRefreshLayout=view.findViewById( R.id.search_swip_refresh );
    }

    @Override
    public void showLocationData(final List<LocationData> locationDatalist) {
        ArrayList<String> locations=new ArrayList<>(  );
        for(int i=0;i<locationDatalist.size();i++)
        {
            locations.add( locationDatalist.get( i ).getCountry() );
        }

        locationSpinnerAdapter =new LocationSpinnerAdapter( getContext(), android.R.layout.simple_list_item_1);
        locationSpinnerAdapter.addAll( locations );
        locationSpinnerAdapter.add( "Location");
        locationSpinner.setAdapter( locationSpinnerAdapter );
        locationSpinner.setSelection( locationSpinnerAdapter.getCount() );
        locationSpinner.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (locationSpinner.getSelectedItem()=="Location")
                {

                }
                else
                {
                    LocationModel=locationSpinner.getSelectedItem().toString();
                    /*for (i=0;i<locationDatalist.size();i++)
                    {
                        if(locationDatalist.get(i).getCountry().equals( LocationModel ))
                        {
                            LocationModelID=locationDatalist.get(i).getId();
                        }
                    }*/
                    brandPresenter.getBrandResult("en",LocationModel  );
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        } );



    }

    @Override
    public void showBrandData(final List<BrandData> brandDataList) {
        ArrayList<String> brands=new ArrayList<>(  );
        for(int i=0;i<brandDataList.size();i++)
        {
            brands.add( brandDataList.get( i ).getName() );
        }

        brandSpinnerAdapter =new BrandSpinnerAdapter( getContext(), android.R.layout.simple_list_item_1);
        brandSpinnerAdapter.addAll( brands );
        brandSpinnerAdapter.add( "Brand");
        brandSpinner.setAdapter( brandSpinnerAdapter );
        brandSpinner.setSelection( brandSpinnerAdapter.getCount() );
        brandSpinner.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (brandSpinner.getSelectedItem()=="Brand")
                {

                }
                else
                {
                    BrandModel=brandSpinner.getSelectedItem().toString();
                    for (i=0;i<brandDataList.size();i++)
                    {
                        if(brandDataList.get(i).getName().equals( BrandModel ))
                        {
                            BrandModelId=brandDataList.get(i).getId();
                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        } );

        swipeRefreshLayout.setRefreshing( false );

    }

    @Override
    public void error(String error) {
        Toast.makeText( getContext(), error, Toast.LENGTH_SHORT ).show();
        swipeRefreshLayout.setRefreshing( false );
    }

   /* @Override
    public void showSearhLocBrandResult(List<SearchLocBrandData> locBrandDataList) {
        *//* locationPresenter.getLocationResult("en", "about");
         brandPresenter.getBrandResult("en",LocationModel  );
         *//*


    }*/

    @Override
    public void error() {
        swipeRefreshLayout.setRefreshing( false );
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing( true );
        brandPresenter.getBrandResult("en",LocationModel  );
    }
}
