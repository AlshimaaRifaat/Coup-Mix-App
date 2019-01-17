package com.example.shosho.coupmix.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shosho.coupmix.R;
import com.example.shosho.coupmix.activity.NavigationActivity;
import com.example.shosho.coupmix.activity.SplashActivity;
import com.example.shosho.coupmix.adapter.AllCategoryAdapter;
import com.example.shosho.coupmix.adapter.SponsorAdapter;
import com.example.shosho.coupmix.model.SponsorData;
import com.example.shosho.coupmix.presenter.SponsorPresenter;
import com.example.shosho.coupmix.view.SponsorView;

import java.util.List;

import static com.example.shosho.coupmix.activity.NavigationActivity.toolbar;

/**
 * A simple {@link Fragment} subclass.
 */
public class SponsorFragment extends Fragment implements SponsorView {

RecyclerView recyclerViewSponsor;
SponsorAdapter sponsorAdapter;
SponsorPresenter sponsorPresenter;
    public SponsorFragment() {
        // Required empty public constructor
    }

View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate( R.layout.fragment_sponsor, container, false );
        init();
        sponsorPresenter=new SponsorPresenter( getContext(),this );
        sponsorPresenter.getSponsorResult( SplashActivity.Language );
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

    private void init() {
        toolbar=view.findViewById( R.id.sponsor_toolbar );
        recyclerViewSponsor=view.findViewById( R.id.sponsor_recycler );
    }

    @Override
    public void showSponsorData(List<SponsorData> sponsorDataList) {
        sponsorAdapter=new SponsorAdapter( getContext(),sponsorDataList );
        recyclerViewSponsor.setLayoutManager( new GridLayoutManager( getContext(),2) );
        recyclerViewSponsor.setAdapter( sponsorAdapter );
    }

    @Override
    public void error() {

    }
}
