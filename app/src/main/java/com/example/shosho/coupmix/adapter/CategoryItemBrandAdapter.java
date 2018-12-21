package com.example.shosho.coupmix.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shosho.coupmix.R;
import com.example.shosho.coupmix.model.CategoryItemDetails;
import com.example.shosho.coupmix.model.SearchBrandData;
import com.example.shosho.coupmix.model.SearchLocBrandData;
import com.example.shosho.coupmix.view.OnClickDetailsCategoryItemView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryItemBrandAdapter extends RecyclerView.Adapter<CategoryItemBrandAdapter.ViewHolder> {
    private Context context;
    private List<SearchBrandData> searchBrandDataList;
    OnClickDetailsCategoryItemView onClickDetailsCategoryItemView;

    public CategoryItemBrandAdapter(Context context, List<SearchBrandData> searchBrandDataList) {
        this.context = context;
        this.searchBrandDataList = searchBrandDataList;
    }

    @NonNull
    @Override
    public CategoryItemBrandAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from( context ).inflate(R.layout.row_category_item,parent,false);
        return new CategoryItemBrandAdapter.ViewHolder(view);
    }
    public void onClick(OnClickDetailsCategoryItemView onClickDetailsCategoryItemView)
    {
        this.onClickDetailsCategoryItemView=onClickDetailsCategoryItemView;
    }
    @Override
    public void onBindViewHolder(@NonNull CategoryItemBrandAdapter.ViewHolder holder, final int position) {
        Picasso.with( context ).load("http://coupomix.com/"+
                searchBrandDataList.get( position ).getImage() ).into(holder.imageView);

        holder.title.setText(searchBrandDataList.get( position ).getTitle());
        String Description=searchBrandDataList.get(position).getDescription();

        if(Description.length()>=35)
            Description=Description.substring( 0,35 )+"...";
        holder.description.setText( Description );
        holder.location.setText( searchBrandDataList.get(position).getAddress() );
        //String OfferPercentage=searchBrandDataList.get(position).getOfferPercentage();
        //OfferPercentage="( Discount "+OfferPercentage+"%)";

        //Remove This After update API
        String OfferPercentage="(50%)";
        holder.discount.setText(OfferPercentage);

        holder.showDetailsBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CategoryItemDetails categoryItemDetails=new CategoryItemDetails();
                categoryItemDetails.setImage( searchBrandDataList.get( position ).getImage() );
                categoryItemDetails.setName( searchBrandDataList.get( position ).getTitle() );
                /*categoryItemDetails.setDiscount( searchBrandDataList.get( position ).getOfferPercentage() );
                categoryItemDetails.setCouponDetails( searchBrandDataList.get( position ).getCoponDetails() );
                categoryItemDetails.setFeaturesOffer( searchBrandDataList.get( position ).getFeatureOffer() );
                */
                categoryItemDetails.setCountry( searchBrandDataList.get( position ).getAddress() );
                categoryItemDetails.setPhone( searchBrandDataList.get( position ).getPhone() );
                onClickDetailsCategoryItemView.showOnClickDetailsCategoryItemResult( categoryItemDetails );

            }
        } );
    }

    @Override
    public int getItemCount() {
        return searchBrandDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView title;
        private TextView description;
        private TextView location;
        private TextView discount;
        private Button showDetailsBtn;
        //  private ImageView imageBack;
        private TextView couponDetail;
        private TextView featuresOffer;
        private TextView phone;


        public ViewHolder(View itemView) {
            super( itemView );
            imageView=itemView.findViewById( R.id.row_category_item_image );
            title=itemView.findViewById( R.id.row_category_item_title );
            description=itemView.findViewById( R.id.row_category_item_description );
            location=itemView.findViewById( R.id.row_category_item_country );
            discount=itemView.findViewById( R.id.row_category_item_discount_ratio );
            showDetailsBtn=itemView.findViewById( R.id.row_category_item_btn );

            couponDetail=itemView.findViewById( R.id.row_category_item_text_coupon_detail );
            featuresOffer=itemView.findViewById( R.id.row_category_item_text_feature_offer );
            phone=itemView.findViewById( R.id.row_category_item_text_phone );
            //   imageBack=itemView.findViewById( R.id.row_category_item_image_back );


        }
    }
}
