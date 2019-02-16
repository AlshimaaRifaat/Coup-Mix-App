package com.example.shosho.coupmix.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.shosho.coupmix.R;
import com.example.shosho.coupmix.model.BannerData;
import com.example.shosho.coupmix.model.BookData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.ViewHolder> {
    Context context;
    List<BannerData> bannersData;

    public BannerAdapter(Context context, List<BannerData> bannersData) {
        this.context = context;
        this.bannersData = bannersData;
    }

    @NonNull
    @Override
    public BannerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from( context ).inflate(R.layout.row_home_banner,parent,false);
        return new BannerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BannerAdapter.ViewHolder holder, int position) {
        Picasso.with( context ).load( "http://coupomix.com/uploads/"
                +bannersData.get( position ).getCImg() ).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return bannersData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        public ViewHolder(View itemView) {
            super( itemView );
            imageView=itemView.findViewById( R.id.row_home_banner_image);
        }
    }
}
