package com.example.shosho.coupmix.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.shosho.coupmix.R;

import java.util.Locale;

public class LanguageActivity extends AppCompatActivity implements View.OnClickListener {
    CheckBox arabicLanguage, englishlanguage;
    Button doneButton;
    String Language;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
       // loadLocale();
        setContentView( R.layout.activity_language );
        arabicLanguage=findViewById( R.id.lang_checkbox_arabic );
        arabicLanguage.setOnClickListener( this );

        englishlanguage=findViewById( R.id.lang_checkbox_english );
        englishlanguage.setOnClickListener( this );
        doneButton=findViewById( R.id.lang_done_btn );
        doneButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Language!=null) {
                    setLocale( Language );
                    startActivity( new Intent( LanguageActivity.this,MainActivity.class ) );


                }else {
                    Toast.makeText( LanguageActivity.this, R.string.choose_language, Toast.LENGTH_SHORT ).show();
                }

            }
        } );

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.lang_checkbox_arabic:
                if(arabicLanguage.isChecked())
                {
                    Language="ar";
                    //setLocale("ar");
                   // recreate();


                }
            case  R.id.lang_checkbox_english:
                if (englishlanguage.isChecked())
                {
                    //setLocale("en");
                    //recreate();
                    Language="en";

                }
        }

    }

    public void setLocale(String Lang) {
        Locale locale=new Locale( Lang );
        Locale.setDefault( locale );
        Configuration configuration=new Configuration( );
        configuration.locale=locale;
        getBaseContext().getResources().updateConfiguration( configuration
                ,getBaseContext().getResources().getDisplayMetrics() );

        SharedPreferences.Editor editor=getSharedPreferences( "settings",MODE_PRIVATE ).edit();
        editor.putString( "my_lang",Lang );
        editor.apply();
    }

}
