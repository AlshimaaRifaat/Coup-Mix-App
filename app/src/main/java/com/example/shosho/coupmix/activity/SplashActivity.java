package com.example.shosho.coupmix.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.shosho.coupmix.R;

import java.util.Locale;

public class SplashActivity extends AppCompatActivity {
SharedPreferences sharedPreferences;
    String Language;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_splash );
       sharedPreferences = getSharedPreferences( "settings", Context.MODE_PRIVATE );
        Language = sharedPreferences.getString( "my_lang", "" );
        //Toast.makeText( this,Language , Toast.LENGTH_LONG).show();


        Thread timer=new Thread(  )
        {
            @Override
            public void run() {
                super.run();
                try {
                    sleep( 2000 );

                }catch (InterruptedException e)
                {
                    e.printStackTrace();
                }finally {
                    //lan==""
                   // lan!=""
                    if(Language.equals("")) {
                        Intent intent = new Intent( SplashActivity.this, LanguageActivity.class );
                        startActivity( intent );

                    }else
                    {
                        setLocale(  );
                        startActivity( new Intent( SplashActivity.this,NavigationActivity.class ) );
                    }   finish();
                }
            }
        };

        timer.start();

    }

    public void setLocale() {
        Locale locale=new Locale( Language );
        Locale.setDefault( locale );
        Configuration configuration=new Configuration( );
        configuration.locale=locale;
        getBaseContext().getResources().updateConfiguration( configuration
                ,getBaseContext().getResources().getDisplayMetrics() );


    }
}
