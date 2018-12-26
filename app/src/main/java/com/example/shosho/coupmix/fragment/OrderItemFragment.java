package com.example.shosho.coupmix.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.shosho.coupmix.NetworkConnection;
import com.example.shosho.coupmix.R;
import com.example.shosho.coupmix.activity.NavigationActivity;
import com.example.shosho.coupmix.model.OrderItemData;
import com.example.shosho.coupmix.model.User;
import com.example.shosho.coupmix.presenter.OrderItemPresenter;
import com.example.shosho.coupmix.view.OrderItemView;
import com.fourhcode.forhutils.FUtilsValidation;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderItemFragment extends Fragment implements OrderItemView{
OrderItemPresenter orderItemPresenter;
EditText userName,userEmail,userAddress,userPhone,userNote;
Button orderItem;
ImageView imageBack;
    Toolbar toolbar;
    public OrderItemFragment() {
        // Required empty public constructor
    }

View view;
    String orderid;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate( R.layout.fragment_order_item, container, false );
        init();
      /*  imageBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace( R.id.content_navigation,
                        new DetailsCategoryItemFragment()).addToBackStack( null ).commit();
            }
        } );*/
        orderItemPresenter=new OrderItemPresenter( getContext(),(OrderItemView) this );
        orderItem.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderItem();


            }
        } );

        NavigationActivity.toggle = new ActionBarDrawerToggle(
                getActivity(), NavigationActivity.drawer, toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        NavigationActivity.drawer.addDrawerListener(NavigationActivity.toggle);
        NavigationActivity.toggle.syncState();

        NavigationActivity.toggle.setDrawerIndicatorEnabled(false);
        toolbar.setNavigationIcon(R.drawable. icon_menu);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (NavigationActivity.drawer.isDrawerOpen(GravityCompat.START)) {
                    NavigationActivity.drawer.closeDrawer(GravityCompat.START);
                } else {
                    NavigationActivity.drawer.openDrawer(GravityCompat.START);
                }
            }
        });
        return view;
    }



    private void init() {
        userName=view.findViewById( R.id.order_item_edit_text_name );
        userEmail=view.findViewById( R.id.order_item_edit_text_email );
        userAddress=view.findViewById( R.id.order_item_edit_text_address);
        userPhone=view.findViewById( R.id.order_item_edit_text_phone);
        userNote=view.findViewById( R.id.order_item_edit_text_notes );
        orderItem=view.findViewById( R.id.order_item_btn_order );
       // imageBack=view.findViewById( R.id.order_item_image_back );
        toolbar=view.findViewById( R.id.order_item_toolbar );
    }

    public static boolean isValidEmail(String Email)
    {
        return !TextUtils.isEmpty( Email )&&  android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches();
    }
    public void orderItem()
    {
        FUtilsValidation.isEmpty( userName,getResources().getString(R.string.Pleasewriteyourname) );
        FUtilsValidation.isEmpty( userEmail,getResources().getString( R.string.Pleasewriteyouremail ));
        FUtilsValidation.isEmpty( userPhone,getResources().getString( R.string.Pleasewriteyourphone ));
        FUtilsValidation.isEmpty( userAddress,getResources().getString( R.string.Pleasewriteyouraddress ));
        NetworkConnection networkConnection=new NetworkConnection( getContext() );
        if (networkConnection.isNetworkAvailable( getContext() ))
        {
            if(!userName.getText().toString().equals( "" )&&
                    !userEmail.getText().toString().equals( "" )&&
                    !userAddress.getText().toString().equals("")&&
                    !userPhone.getText().toString().equals("")&&
                    validateEmail())
            {
                User user=new User();
                user.setName( userName.getText().toString() );
                user.setEmail( userEmail.getText().toString() );
                user.setPhone( userPhone.getText().toString() );
                user.setAddress( userAddress.getText().toString() );
                user.setNote( userNote.getText().toString() );
                orderItemPresenter.getOrderResult( user );
            }
            else
            {
                Toast.makeText( getContext(), R.string.Pleasefullyourinformation, Toast.LENGTH_SHORT ).show();
            }

        }
        else
        {
            Toast.makeText( getContext(), R.string.Checkyourinternetconnection
                    , Toast.LENGTH_SHORT ).show();
        }
    }


    @Override
    public void openPage(List<OrderItemData> Id) {
        if(Id!=null){
            orderid= String.valueOf( Id.get( 0 ).getIdOrder() );
            OrderSuccessFragment orderSuccessFragment=new OrderSuccessFragment();
            Bundle bundle=new Bundle( );
            bundle.putString( "id",orderid);
            orderSuccessFragment.setArguments( bundle );
            getFragmentManager().beginTransaction().replace( R.id.content_navigation,
                    orderSuccessFragment )
                    .addToBackStack( null).commit();

        }
    }

    @Override
    public void showError(String Error) {
        Toast.makeText( getContext(), Error, Toast.LENGTH_SHORT ).show();
    }
    private Boolean validateEmail(){
        String EMAIL=userEmail.getText().toString().trim();
        if (EMAIL.isEmpty()||!isValidEmail(EMAIL)){
            userEmail.setError(getResources().getString(R.string.Invalidemail));

            return false;
        }else if(!userEmail.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
            userEmail.setError(getResources().getString(R.string.Invalidemail));
            return false;
        }
        return true;
    }


}
