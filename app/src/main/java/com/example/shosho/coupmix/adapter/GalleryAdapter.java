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
import com.example.shosho.coupmix.fragment.DetailsGalleryFragment;
import com.example.shosho.coupmix.fragment.GalleryFragment;
import com.example.shosho.coupmix.model.FeatureProductData;
import com.example.shosho.coupmix.model.FeatureProductDetails;
import com.example.shosho.coupmix.model.GalleryData;
import com.example.shosho.coupmix.view.OnClickItemFeatureProductView;
import com.example.shosho.coupmix.view.VideoLinkView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.security.PublicKey;
import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {
    private Context context;
    private List<GalleryData> galleryDataList;
    VideoLinkView videoLinkView;


    public GalleryAdapter(Context context, List<GalleryData> galleryDataList) {
        this.context = context;
        this.galleryDataList = galleryDataList;
    }

    @NonNull
    @Override
    public GalleryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from( context ).inflate(R.layout.row_gallery,
                parent,false);
        return new GalleryAdapter.ViewHolder(view);
    }
    public void onClick(VideoLinkView videoLinkView)
    {
        this.videoLinkView=videoLinkView;
    }
    @Override
    public void onBindViewHolder(@NonNull final GalleryAdapter.ViewHolder holder, final int position) {
        holder.progressBar.setVisibility( View.VISIBLE );

        Glide.with( context ).load( "http://coupomix.com"+
                galleryDataList.get( position ).getImage() )
                .listener( new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {

                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        holder.progressBar.setVisibility( View.GONE );
                        if (!galleryDataList.get( position ).getLinkvideo().equals( "" ))
                        {
                            holder.iconYoutube.setVisibility( View.VISIBLE );
                        }else
                        {
                            holder.iconYoutube.setVisibility( View.GONE );
                        }
                        return false;
                    }
                } )
                .into( holder.imageView );

        holder.itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                videoLinkView.sendLink( galleryDataList.get( position ).getLinkvideo()
                        ,galleryDataList.get( position ).getImage() );


            }
        } );


    }

    @Override
    public int getItemCount() {
        return galleryDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private ProgressBar progressBar;
     public  ImageView iconYoutube;
        public ViewHolder(View itemView) {
            super( itemView );
            imageView=itemView.findViewById( R.id.row_gallery_image );
            progressBar=itemView.findViewById( R.id.row_gallery_progress );
            iconYoutube=itemView.findViewById( R.id.row_gallery_icon_youtube);
        }
    }
}
