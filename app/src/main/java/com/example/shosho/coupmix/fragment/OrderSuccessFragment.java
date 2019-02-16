package com.example.shosho.coupmix.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shosho.coupmix.R;
import com.example.shosho.coupmix.view.OrderItemView;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderSuccessFragment extends Fragment  {
TextView idText;
String Id;
    public OrderSuccessFragment() {
        // Required empty public constructor
    }

View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate( R.layout.fragment_order_success, container, false );
        idText=view.findViewById( R.id.order_succes_text_id );
        Bundle bundle=this.getArguments();
        if (bundle!=null)
        {
            Id=bundle.getString( "id" );
            idText.setText( Id );

        }

        return view;
    }



}
