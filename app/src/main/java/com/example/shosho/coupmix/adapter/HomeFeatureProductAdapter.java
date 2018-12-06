package com.example.shosho.coupmix.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shosho.coupmix.R;
import com.example.shosho.coupmix.model.BookData;
import com.example.shosho.coupmix.model.ProductDetails;
import com.example.shosho.coupmix.view.DetailsProductView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeFeatureProductAdapter extends RecyclerView.Adapter<HomeFeatureProductAdapter.ViewHolder> {
    private Context context;
    private List<BookData> booksData;
    DetailsProductView detailsProductView;
    public HomeFeatureProductAdapter(Context context, List<BookData> booksData) {
        this.context = context;
        this.booksData = booksData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from( context ).inflate(R.layout.row_home_features_products,
                parent,false);
        return new HomeFeatureProductAdapter.ViewHolder(view);
    }
    public void onClick(DetailsProductView detailsProductView)
    {
        this.detailsProductView=detailsProductView;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Picasso.with( context ).load(
        booksData.get( position ).getFeatureProdImg()).into(holder.imageView);
        holder.name.setText(booksData.get( position ).getFeatureProdName());
        holder.discount.setText(booksData.get( position ).getDiscount());
        holder.itemView.setOnClickListener( new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        ProductDetails productDetails=new ProductDetails();
        productDetails.setImage( booksData.get( position ).getImgUrl() );
        productDetails.setName( booksData.get( position ).getProdName() );
        productDetails.setDiscount( booksData.get( position ).getDiscount() );
        productDetails.setCouponDetails( booksData.get( position ).getShortDesc() );
        productDetails.setFeaturesOffer( booksData.get( position ).getOfferDetails() );
        productDetails.setCountry( booksData.get( position ).getCountry() );
        productDetails.setPhone( booksData.get( position ).getPhone() );
        detailsProductView.showProductDetails( productDetails );
    }
} );

    }

    @Override
    public int getItemCount() {
        return booksData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView name;
        private TextView discount;
        private TextView couponDetail;
        private TextView featuresOffer;
        private TextView country;
        private TextView phone;

        public ViewHolder(View itemView) {
            super( itemView );

            imageView=itemView.findViewById( R.id.row_home_feature_product_image );
            name=itemView.findViewById( R.id.row_home_feature_product_name );
            discount=itemView.findViewById( R.id.row_home_feature_product_discount );

            couponDetail=itemView.findViewById( R.id.row_home_feature_product_coupon_details );
            featuresOffer=itemView.findViewById( R.id.row_home_feature_product_features_offer );
            country=itemView.findViewById( R.id.row_home_feature_product_country );
            phone=itemView.findViewById( R.id.row_home_feature_product_phone );
        }
    }
}
