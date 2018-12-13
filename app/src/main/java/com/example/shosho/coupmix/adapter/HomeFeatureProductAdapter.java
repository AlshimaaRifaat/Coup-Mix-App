package com.example.shosho.coupmix.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shosho.coupmix.model.FeatureProductData;
import com.example.shosho.coupmix.model.FeatureProductDetails;
import com.example.shosho.coupmix.view.OnClickItemFeatureProductView;
import com.squareup.picasso.Picasso;
import com.example.shosho.coupmix.R;
import java.util.List;

public class HomeFeatureProductAdapter extends RecyclerView.Adapter<HomeFeatureProductAdapter.ViewHolder> {
    private Context context;
    private List<FeatureProductData> featureProductDataList;
    OnClickItemFeatureProductView onClickItemFeatureProductView;

    public HomeFeatureProductAdapter(Context context, List<FeatureProductData> featuresProductData) {
        this.context = context;
        this.featureProductDataList = featuresProductData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from( context ).inflate(R.layout.row_home_features_products,
                parent,false);
        return new HomeFeatureProductAdapter.ViewHolder(view);
    }
    public void onClick(OnClickItemFeatureProductView onClickItemFeatureProductView)
    {
        this.onClickItemFeatureProductView=onClickItemFeatureProductView;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Picasso.with( context ).load("http://coupomix.com/"+
                featureProductDataList.get( position ).getImage()).into(holder.imageView);

        Typeface customFontMedium = Typeface.createFromAsset( context.getAssets(), "Fonts/SST Arabic Medium.ttf" );
        holder.title.setTypeface( customFontMedium );
        holder.title.setText(featureProductDataList.get( position ).getTitle());
        holder.discount.setText(featureProductDataList.get( position ).getOfferPercentage()+"% Discount");


       holder.itemView.setOnClickListener( new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        FeatureProductDetails featureProductDetails=new FeatureProductDetails();
        featureProductDetails.setImage( featureProductDataList.get( position ).getImage() );
        featureProductDetails.setName( featureProductDataList.get( position ).getTitle() );
        featureProductDetails.setDiscount( featureProductDataList.get( position ).getOfferPercentage()+" %" );
        featureProductDetails.setCouponDetails( featureProductDataList.get( position ).getCoponDetails() );
        featureProductDetails.setFeaturesOffer( featureProductDataList.get( position ).getFeatureOffer() );
        featureProductDetails.setCountry( featureProductDataList.get( position ).getLocation() );
        featureProductDetails.setPhone( featureProductDataList.get( position ).getPhone() );
        onClickItemFeatureProductView.showOnClickItemFeatureProductResult( featureProductDetails );
    }
} );

    }

    @Override
    public int getItemCount() {
        return featureProductDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView title;
        private TextView discount;
        private TextView couponDetail;
        private TextView featuresOffer;
        private TextView country;
        private TextView phone;

        public ViewHolder(View itemView) {
            super( itemView );

            imageView=itemView.findViewById( R.id.row_home_feature_product_image );
            title=itemView.findViewById( R.id.row_home_feature_product_title );
            discount=itemView.findViewById( R.id.row_home_feature_product_discount );

            couponDetail=itemView.findViewById( R.id.row_home_feature_product_coupon_details );
            featuresOffer=itemView.findViewById( R.id.row_home_feature_product_features_offer );
            country=itemView.findViewById( R.id.row_home_feature_product_country );
            phone=itemView.findViewById( R.id.row_home_feature_product_phone );
        }
    }
}
