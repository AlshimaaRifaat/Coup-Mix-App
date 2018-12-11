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

import com.example.shosho.coupmix.model.BookData;
import com.example.shosho.coupmix.model.SearchLocBrandData;
import com.example.shosho.coupmix.view.OnClickItemCategoryView;
import com.squareup.picasso.Picasso;

import java.util.List;
import com.example.shosho.coupmix.R;
public class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.ViewHolder> {
    private Context context;
    private List<BookData> booksData;
    OnClickItemCategoryView onClickItemCategoryView;

    public HomeCategoryAdapter(Context context, List<BookData> booksData) {
        this.context = context;
        this.booksData = booksData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from( context ).inflate(R.layout.row_home_category,parent,false);
        return new HomeCategoryAdapter.ViewHolder(view);
    }
    public void onClick(OnClickItemCategoryView onClickItemCategoryView)
    {
        this.onClickItemCategoryView=onClickItemCategoryView;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Picasso.with( context ).load("http://coupomix.com/"+
                booksData.get( position ).getImage() ).into(holder.imageView);

        holder.name.setText(booksData.get( position ).getTitle());
        Typeface customFontLight=Typeface.createFromAsset( context.getAssets()
                ,"Fonts/SST Arabic Light.ttf" );
        holder.name.setTypeface( customFontLight );

        holder.itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchLocBrandData searchLocBrandData=new SearchLocBrandData();
             //   searchLocBrand.setID( booksData.get( position ).getId() );
                onClickItemCategoryView.showOnClickItemCategoryResult( searchLocBrandData );
            }
        } );
    }

    @Override
    public int getItemCount() {
        return booksData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView name;
        public ViewHolder(View itemView) {
            super( itemView );
            imageView=itemView.findViewById( R.id.row_home_category_image );
            name=itemView.findViewById( R.id.row_home_category_name );
        }
    }
}
