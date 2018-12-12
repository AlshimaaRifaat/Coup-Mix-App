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
import com.example.shosho.coupmix.model.SearchLocBrandData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryItemAdapter extends RecyclerView.Adapter<CategoryItemAdapter.ViewHolder> {
    private Context context;
    private List<SearchLocBrandData> searchLocBrandDataList;

    public CategoryItemAdapter(Context context, List<SearchLocBrandData> searchLocBrandDataList) {
        this.context = context;
        this.searchLocBrandDataList = searchLocBrandDataList;
    }

    @NonNull
    @Override
    public CategoryItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from( context ).inflate(R.layout.row_category_item,parent,false);
        return new CategoryItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryItemAdapter.ViewHolder holder, int position) {
        Picasso.with( context ).load("http://coupomix.com/"+
                searchLocBrandDataList.get( position ).getImage() ).into(holder.imageView);

        holder.title.setText(searchLocBrandDataList.get( position ).getTitle());
        String Description=searchLocBrandDataList.get(position).getDescription();

        if(Description.length()>=35)
            Description=Description.substring( 0,35 )+"...";
        holder.description.setText( Description );
        holder.location.setText( searchLocBrandDataList.get(position).getLocation() );
        String OfferPercentage=searchLocBrandDataList.get(position).getOfferPercentage();
        OfferPercentage="(-"+OfferPercentage+"%)";
        holder.discount.setText(OfferPercentage);
    }

    @Override
    public int getItemCount() {
        return searchLocBrandDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView title;
        private TextView description;
        private TextView location;
        private TextView discount;
        public ViewHolder(View itemView) {
            super( itemView );
            imageView=itemView.findViewById( R.id.row_category_item_image );
            title=itemView.findViewById( R.id.row_category_item_title );
            description=itemView.findViewById( R.id.row_category_item_description );
            location=itemView.findViewById( R.id.row_category_item_country );
            discount=itemView.findViewById( R.id.row_category_item_discount_ratio );

        }
    }
}
