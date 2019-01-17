package com.example.shosho.coupmix.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.shosho.coupmix.R;
import com.example.shosho.coupmix.model.BookData;
import com.example.shosho.coupmix.model.SearchLocBrandData;
import com.example.shosho.coupmix.model.SponsorData;
import com.example.shosho.coupmix.view.OnClickItemCategoryView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SponsorAdapter extends RecyclerView.Adapter<SponsorAdapter.ViewHolder> {
    private Context context;
    private List<SponsorData> sponsorDataList;
   // OnClickItemCategoryView onClickItemCategoryView;

    Typeface customFontLight,customFontMedium;
    public SponsorAdapter(Context context, List<SponsorData> sponsorDataList) {
        this.context = context;
        this.sponsorDataList = sponsorDataList;
    }

    @NonNull
    @Override
    public SponsorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from( context ).inflate(R.layout.row_sponsor,parent,false);
        return new SponsorAdapter.ViewHolder(view);
    }
    /*public void onClick(OnClickItemCategoryView onClickItemCategoryView)
    {
        this.onClickItemCategoryView=onClickItemCategoryView;
    }*/
    @Override
    public void onBindViewHolder(@NonNull final SponsorAdapter.ViewHolder holder, final int position) {

        holder.progressBar.setVisibility( View.VISIBLE );
        Glide.with( context ).load( "http://coupomix.com"+
                sponsorDataList.get( position ).getImage() )
                .listener( new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {

                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        holder.progressBar.setVisibility( View.GONE );
                        return false;
                    }
                } )
                .into( holder.imageView );

        holder.title.setText(sponsorDataList.get( position ).getTitle());
         customFontMedium=Typeface.createFromAsset( context.getAssets()
                ,"Fonts/SST Arabic Medium.ttf" );
        holder.title.setTypeface( customFontMedium );

        holder.address.setText( sponsorDataList.get( position ).getAddress() );
        customFontLight=Typeface.createFromAsset( context.getAssets(),"Fonts/SST Arabic Light.ttf" );
        holder.address.setTypeface( customFontLight );

        holder.phone.setText( "Tel: "+sponsorDataList.get( position ).getPhone() );
        customFontLight=Typeface.createFromAsset( context.getAssets(),"Fonts/SST Arabic Light.ttf" );
        holder.phone.setTypeface( customFontLight );
        /*holder.itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                *//*SearchLocBrandData searchLocBrandData=new SearchLocBrandData();

                onClickItemCategoryView.showOnClickItemCategoryResult( searchLocBrandData );*//*
                SearchLocBrandData searchLocBrandData=new SearchLocBrandData();
                searchLocBrandData.setId( booksData.get( position ).getId().toString() );
                onClickItemCategoryView.showOnClickItemCategoryResult( searchLocBrandData );
            }
        } );*/
    }

    @Override
    public int getItemCount() {
        return sponsorDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView title;
        private TextView address;
        private TextView phone;
        ProgressBar progressBar;

        public ViewHolder(View itemView) {
            super( itemView );
            imageView=itemView.findViewById( R.id.row_sponsor_image );
            title=itemView.findViewById( R.id.row_sponsor_title );
            address=itemView.findViewById( R.id.row_sponsor_address );
            phone=itemView.findViewById( R.id.row_sponsor_phone );
            progressBar=itemView.findViewById( R.id.row_sponsor_progress_bar );



        }
    }
}
