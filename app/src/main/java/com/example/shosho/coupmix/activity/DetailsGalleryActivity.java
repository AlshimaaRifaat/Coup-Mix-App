package com.example.shosho.coupmix.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.shosho.coupmix.R;
import com.squareup.picasso.Picasso;

public class DetailsGalleryActivity extends AppCompatActivity {
ImageView imageView;
String Link;
String Image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_details_gallery );
        imageView=findViewById( R.id.details_gallery_Image );



                Image = getIntent().getExtras().getString("image");
                Glide.with( this )
                        .load( "http://coupomix.com" + Image ).into( imageView );



    }
}
