package com.example.shosho.coupmix.fragment;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

//import com.example.shosho.coupmix.NetworkConnection;
import com.example.shosho.coupmix.R;
import com.example.shosho.coupmix.activity.NavigationActivity;
import com.squareup.picasso.Picasso;

import static java.security.AccessController.getContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsCategoryItemFragment extends Fragment {
ImageView imageView;
TextView title;
TextView discount;
TextView couponDetails;
TextView featuresOffer;
TextView country;
TextView phone;
Button orderBtn;
ImageView imageBack;
    Toolbar toolbar;

    public DetailsCategoryItemFragment() {
        // Required empty public constructor
    }

View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate( R.layout.fragment_details_category_item, container, false );
        imageView=view.findViewById( R.id.details_category_item_image );
        title=view.findViewById( R.id.details_category_item_text_title );
       discount=view.findViewById( R.id.details_category_item_text_discount );
       couponDetails=view.findViewById( R.id.details_category_item_text_coupon_details );
       featuresOffer=view.findViewById( R.id.details_category_item_text_features_offer);
       country =view.findViewById( R.id.details_category_item_text_country);
       phone=view.findViewById( R.id.details_category_item_text_phone);
       orderBtn=view.findViewById( R.id.details_category_item_btn_order );
       toolbar=view.findViewById( R.id.details_category_item_toolbar );
      // imageBack=view.findViewById( R.id.details_category_item_image_back );

       /* imageBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace( R.id.content_navigation,
                        new CategoryItemFragment()).addToBackStack( null ).commit();
            }
        } );*/
        Bundle bundle=this.getArguments();
        if(bundle!=null)
        {
            String Image=bundle.getString("image");
            String Name=bundle.getString("name");
            String Discount=bundle.getString("discount");
            String CouponDetails=bundle.getString("couponDetails");
            String FeaturesOffer=bundle.getString("featuresOffer");
            String Country=bundle.getString("country");
            String Phone=bundle.getString("phone");

            Picasso.with( getContext() )
                    .load( "http://coupomix.com/"+Image ).into(imageView);
            title.setText(Name);
            discount.setText( "( "+Discount+"% )" );

            Typeface customFontLight = Typeface.createFromAsset( getActivity().getAssets(), "Fonts/SST Arabic Light.ttf" );
            couponDetails.setTypeface( customFontLight );
            couponDetails.setText( CouponDetails );

             customFontLight = Typeface.createFromAsset( getActivity().getAssets(), "Fonts/SST Arabic Light.ttf" );
            featuresOffer.setTypeface( customFontLight );
            featuresOffer.setText(FeaturesOffer);

            customFontLight = Typeface.createFromAsset( getActivity().getAssets(), "Fonts/SST Arabic Light.ttf" );
            country.setTypeface( customFontLight );
            country.setText(Country);
            phone.setText(Phone);
        }

        orderBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().add(R.id.content_navigation,
                        new OrderItemFragment()).addToBackStack( null ).commit();
            }
        } );
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




}
