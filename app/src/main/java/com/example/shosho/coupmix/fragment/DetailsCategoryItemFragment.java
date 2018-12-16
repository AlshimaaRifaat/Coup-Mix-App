package com.example.shosho.coupmix.fragment;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shosho.coupmix.R;
import com.squareup.picasso.Picasso;

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
            discount.setText( "( -"+Discount+"% )" );

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
                getFragmentManager().beginTransaction().replace(R.id.content_navigation,
                        new OrderItemFragment()).addToBackStack( null ).commit();
            }
        } );
        return view;
    }

}
