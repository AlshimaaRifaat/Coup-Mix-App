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
import com.example.shosho.coupmix.model.AllBrandData;
import com.example.shosho.coupmix.model.OfferListData;
import com.example.shosho.coupmix.model.SearchBrandData;
import com.example.shosho.coupmix.model.SearchLocBrandData;
import com.example.shosho.coupmix.view.OnClickItemAllBrandView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AllBrandAdapter  extends RecyclerView.Adapter<AllBrandAdapter.ViewHolder> {
    private Context context;
    private List<AllBrandData> allBrandDataList;
OnClickItemAllBrandView onClickItemAllBrandView;

    public AllBrandAdapter(Context context, List<AllBrandData> allBrandDataList) {
        this.context = context;
        this.allBrandDataList = allBrandDataList;
    }

    @NonNull
    @Override
    public AllBrandAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from( context ).inflate(R.layout.row_search_brand,parent,false);
        return new AllBrandAdapter.ViewHolder(view);
    }
    public void onClick(OnClickItemAllBrandView onClickItemAllBrandView)
    {
        this.onClickItemAllBrandView=onClickItemAllBrandView;
    }

    @Override
    public void onBindViewHolder(@NonNull AllBrandAdapter.ViewHolder holder, final int position) {
        Picasso.with( context ).load("http://coupomix.com/"+
                allBrandDataList.get( position ).getImage() ).into(holder.imageView);

        holder.title.setText(allBrandDataList.get( position ).getTitle());
        /*String Description=searchBrandDataList.get(position).getDescription();

        if(Description.length()>=35)
            Description=Description.substring( 0,35 )+"...";
        holder.description.setText( Description );*/
        holder.address.setText( allBrandDataList.get(position).getAddress() );

        holder.phone.setText( allBrandDataList.get( position ).getPhone() );
        //String OfferPercentage=searchBrandDataList.get(position).getOfferPercentage();
        //OfferPercentage="( Discount "+OfferPercentage+"%)";




holder.itemView.setOnClickListener( new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        AllBrandData allBrandData=new AllBrandData();
        allBrandData=allBrandDataList.get(position);
        onClickItemAllBrandView.showOnClickItemAllBrandResult( allBrandData );
    }
} );
    }

    @Override
    public int getItemCount() {
        return allBrandDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView title;
        private TextView description;
        private TextView address;
        private TextView phone;

        private ImageView imageBack;



        public ViewHolder(View itemView) {
            super( itemView );
            imageView=itemView.findViewById( R.id.row_search_brand_image );
            title=itemView.findViewById( R.id.row_search_brand_title );
            // description=itemView.findViewById( R.id.row_search_brand_description );
            address=itemView.findViewById( R.id.row_search_brand_address );
            phone=itemView.findViewById( R.id.row_search_brand_phone );

            //   imageBack=itemView.findViewById( R.id.row_category_item_image_back );


        }
    }
}
