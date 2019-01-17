package com.example.shosho.coupmix.fragment;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.shosho.coupmix.R;
import com.example.shosho.coupmix.activity.DetailsGalleryActivity;
import com.example.shosho.coupmix.activity.NavigationActivity;
import com.example.shosho.coupmix.activity.SplashActivity;
import com.example.shosho.coupmix.adapter.GalleryAdapter;
import com.example.shosho.coupmix.model.GalleryData;
import com.example.shosho.coupmix.presenter.GalleryPresenter;
import com.example.shosho.coupmix.view.GalleryView;
import com.example.shosho.coupmix.view.VideoLinkView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GalleryFragment extends Fragment implements GalleryView,VideoLinkView {

Toolbar toolbar;

GalleryAdapter galleryAdapter;
GalleryPresenter galleryPresenter;
RecyclerView recyclerViewGallery;

    public GalleryFragment() {
        // Required empty public constructor
    }

View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate( R.layout.fragment_gallery, container, false );
        init();
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

        galleryPresenter=new GalleryPresenter( getContext(),this );
        galleryPresenter.getGallryResult( "ar" );
        return view;
    }

    private void init() {
        toolbar=view.findViewById( R.id.gallery_toolbar );
        recyclerViewGallery=view.findViewById( R.id.gallery_recycler );

    }

    @Override
    public void showGalleryData(List<GalleryData> galleryDataList) {
        galleryAdapter=new GalleryAdapter( getContext(),galleryDataList );
        galleryAdapter.onClick( this );
        recyclerViewGallery.setLayoutManager( new GridLayoutManager( getContext(),3 ) );
        recyclerViewGallery.setAdapter( galleryAdapter );


    }

    @Override
    public void showError() {


    }


    @Override
    public void sendLink(String Link, String Image) {
        if(!Link.equals( "" )){
            DetailsGalleryFragment detailsGalleryFragment=new DetailsGalleryFragment();
            Bundle bundle=new Bundle(  );
            bundle.putString( "video_link",Link);
            detailsGalleryFragment.setArguments(bundle);
                getFragmentManager().beginTransaction().add( R.id.content_navigation,
                        detailsGalleryFragment )
                        .addToBackStack( null ).commit();

        }else {

            Intent intent = new Intent( getActivity().getApplication(),
                    DetailsGalleryActivity.class );
            intent.putExtra("image",Image  );
            getActivity().startActivity( intent );
        }
    }
}
