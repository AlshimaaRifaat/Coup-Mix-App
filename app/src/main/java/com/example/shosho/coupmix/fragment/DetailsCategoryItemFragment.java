package com.example.shosho.coupmix.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shosho.coupmix.R;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class    DetailsCategoryItemFragment extends Fragment {
ImageView imageView;
TextView name;
TextView discount;
TextView couponDetails;
TextView featuresOffer;
TextView country;
TextView phone;


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
        name=view.findViewById( R.id.details_category_item_text_name );
        discount=view.findViewById( R.id.details_category_item_text_discount );
        couponDetails=view.findViewById( R.id.details_category_item_text_coupon_details );
        featuresOffer=view.findViewById( R.id.details_category_item_text_features_offer);
        country =view.findViewById( R.id.details_category_item_text_country);
        phone=view.findViewById( R.id.details_category_item_text_phone);
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
                    .load( Image ).into(imageView);
            name.setText(Name);
            discount.setText( Discount );
            couponDetails.setText(CouponDetails);
            featuresOffer.setText(FeaturesOffer);
            country.setText(Country);
            phone.setText(Phone);
        }
        return view;
    }

}
