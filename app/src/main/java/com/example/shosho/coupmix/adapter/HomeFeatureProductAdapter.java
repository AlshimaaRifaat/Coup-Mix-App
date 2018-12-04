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
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeFeatureProductAdapter
{

       /* extends RecyclerView.Adapter<HomeFeatureProductAdapter.ViewHolder> {
    private Context context;
    private List<BookData> booksData;

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

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.with( context ).load( "http://alhabib-abobakr.com/uploads/"+
        booksData.get( position ).getCImg()).into(holder.imageView);

        holder.name.setText(booksData.get( position ).getCName());
        holder.discount.setText(booksData.get( position ).getTitle()); //replaced title with date

    }

    @Override
    public int getItemCount() {
        return booksData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView name;
        private TextView discount;
        public ViewHolder(View itemView) {
            super( itemView );
            imageView=itemView.findViewById( R.id.row_home_feature_product_image );
            name=itemView.findViewById( R.id.row_home_feature_product_name );
            discount=itemView.findViewById( R.id.row_home_feature_product_discount );

        }
    }*/
}
