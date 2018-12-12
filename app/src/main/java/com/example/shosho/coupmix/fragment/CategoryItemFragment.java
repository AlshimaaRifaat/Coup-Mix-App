package com.example.shosho.coupmix.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shosho.coupmix.R;
import com.example.shosho.coupmix.adapter.CategoryItemAdapter;
import com.example.shosho.coupmix.model.SearchLocBrandData;
import com.example.shosho.coupmix.presenter.BrandPresenter;
import com.example.shosho.coupmix.presenter.LocationPresenter;
import com.example.shosho.coupmix.presenter.SearchLocBrandPresenter;
import com.example.shosho.coupmix.view.SearchLocBrandView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryItemFragment extends Fragment implements SearchLocBrandView {



RecyclerView recyclerView;
SearchLocBrandPresenter searchLocBrandPresenter;
CategoryItemAdapter categoryItemAdapter;
String Location,Brand;



    public CategoryItemFragment() {
        // Required empty public constructor
    }

View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate( R.layout.fragment_category_item, container, false );
        Recycle();
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            Location = bundle.getString( "location" );
            Brand = bundle.getString( "brand" );
            searchLocBrandPresenter = new SearchLocBrandPresenter( getContext(), this );
            searchLocBrandPresenter.getSearchLocBrandResult( "en", Location, Brand );
        }
        return view;
    }



    private void Recycle() {
        recyclerView=view.findViewById( R.id.category_item_recycler_view );
    }

    @Override
    public void showSearhLocBrandResult(List<SearchLocBrandData> locBrandDataList) {
        Toast.makeText( getContext(), locBrandDataList.get( 0 ).getTitle(), Toast.LENGTH_SHORT ).show();
     categoryItemAdapter=new CategoryItemAdapter( getContext(),locBrandDataList );
     LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
     recyclerView.setLayoutManager(linearLayoutManager);
     recyclerView.setAdapter( categoryItemAdapter );



    }

    @Override
    public void error() {

    }
}
