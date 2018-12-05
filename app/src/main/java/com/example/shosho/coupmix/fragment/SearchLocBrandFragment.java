package com.example.shosho.coupmix.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.shosho.coupmix.R;
import com.example.shosho.coupmix.model.BookData;
import com.example.shosho.coupmix.presenter.BookPresenter;
import com.example.shosho.coupmix.view.BookView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchLocBrandFragment extends Fragment implements  AdapterView.OnItemSelectedListener,BookView{
ArrayAdapter<BookData> booksAdapter;
BookPresenter bookPresenter;
Spinner locationSpinner;
String Model,ModelID;
    public SearchLocBrandFragment() {
        // Required empty public constructor
    }

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       view=  inflater.inflate( R.layout.fragment_search_loc_brand, container, false );
     init();
     bookPresenter=new BookPresenter( getContext(),this );
        bookPresenter.getBookResult();
       return view;
    }

    private void init() {
        locationSpinner=view.findViewById( R.id.search_location_spinner );
    }





    @Override
    public void showData(final List<BookData> booksData) {
        booksAdapter=new ArrayAdapter<BookData>( getContext(),R.layout.spinner_text_item,booksData )
        {
            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                TextView textView=(TextView)super.getDropDownView( position,convertView,parent );
                textView.setTextColor( Color.BLACK );
                return textView;
            }
        };
        booksAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item );
        locationSpinner.setOnItemSelectedListener(this  );
        locationSpinner.setAdapter( booksAdapter );
        locationSpinner.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Model=locationSpinner.getSelectedItem().toString();
                for (i=0;i<booksData.size();i++)
                {
                    if(booksData.get(i).getLocation().equals( Model ))
                    {
                        ModelID=booksData.get(i).getCatID();
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void error() {

    }
}
