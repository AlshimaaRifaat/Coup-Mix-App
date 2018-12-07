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
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.shosho.coupmix.R;
import com.example.shosho.coupmix.adapter.MySpinnerAdapter;
import com.example.shosho.coupmix.model.BookData;
import com.example.shosho.coupmix.presenter.BookPresenter;
import com.example.shosho.coupmix.view.BookView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchLocBrandFragment extends Fragment implements  AdapterView.OnItemSelectedListener,BookView{
ArrayAdapter<BookData> booksAdapter;
BookPresenter bookPresenter;
Spinner locationSpinner;
String Model,ModelID;
MySpinnerAdapter mySpinnerAdapter;
Button searchBtn;
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
        searchBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace( R.id.content_navigation,new CategoryItemFragment() )
                        .addToBackStack( null ).commit();

            }
        } );
       return view;
    }

    private void init()
    {
        locationSpinner=view.findViewById( R.id.search_location_spinner );
        searchBtn=view.findViewById( R.id.search_btn );
    }


    @Override
    public void showData(final List<BookData> booksData) {
        ArrayList<String> locations=new ArrayList<>(  );
        for(int i=0;i<booksData.size();i++)
        {
            locations.add( booksData.get( i ).getLocation() );
        }

        mySpinnerAdapter =new MySpinnerAdapter( getContext(), android.R.layout.simple_list_item_1);
        mySpinnerAdapter.addAll( locations );
        mySpinnerAdapter.add( "Location");
        locationSpinner.setAdapter( mySpinnerAdapter );
        locationSpinner.setSelection( mySpinnerAdapter.getCount() );
        locationSpinner.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (locationSpinner.getSelectedItem()=="Location")
                {

                }
                else
                {
                    Model=locationSpinner.getSelectedItem().toString();
                    for (i=0;i<booksData.size();i++)
                    {
                        if(booksData.get(i).getLocation().equals( Model ))
                        {
                            ModelID=booksData.get(i).getCatID();
                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        } );



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
