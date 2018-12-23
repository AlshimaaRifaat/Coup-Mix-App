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
import com.example.shosho.coupmix.view.OnClickDetailsCategoryItemView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchBrandAdapter extends RecyclerView.Adapter<SearchBrandAdapter.ViewHolder> {
    private Context context;
    private List<SearchBrandData> searchBrandDataList;


    public SearchBrandAdapter(Context context, List<SearchBrandData> searchBrandDataList) {
        this.context = context;
        this.searchBrandDataList = searchBrandDataList;
    }

    @NonNull
    @Override
    public SearchBrandAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from( context ).inflate(R.layout.row_search_brand,parent,false);
        return new SearchBrandAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchBrandAdapter.ViewHolder holder, final int position) {
        Picasso.with( context ).load("http://coupomix.com/"+
                searchBrandDataList.get( position ).getImage() ).into(holder.imageView);

        holder.title.setText(searchBrandDataList.get( position ).getTitle());
        String Description=searchBrandDataList.get(position).getDescription();

        if(Description.length()>=35)
            Description=Description.substring( 0,35 )+"...";
        holder.description.setText( Description );
        holder.address.setText( searchBrandDataList.get(position).getAddress() );

        holder.phone.setText( searchBrandDataList.get( position ).getPhone() );
        //String OfferPercentage=searchBrandDataList.get(position).getOfferPercentage();
        //OfferPercentage="( Discount "+OfferPercentage+"%)";



    }

    @Override
    public int getItemCount() {
        return searchBrandDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView title;
        private TextView description;
        private TextView address;
        private TextView phone;

        //  private ImageView imageBack;



        public ViewHolder(View itemView) {
            super( itemView );
            imageView=itemView.findViewById( R.id.row_search_brand_image );
            title=itemView.findViewById( R.id.row_search_brand_title );
            description=itemView.findViewById( R.id.row_search_brand_description );
            address=itemView.findViewById( R.id.row_search_brand_address );
            phone=itemView.findViewById( R.id.row_search_brand_phone );

            //   imageBack=itemView.findViewById( R.id.row_category_item_image_back );


        }
    }
}
