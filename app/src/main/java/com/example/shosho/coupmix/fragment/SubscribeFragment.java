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
import android.widget.Toast;

import com.example.shosho.coupmix.NetworkConnection;
import com.example.shosho.coupmix.R;
import com.example.shosho.coupmix.activity.NavigationActivity;
import com.example.shosho.coupmix.model.User;
import com.example.shosho.coupmix.presenter.SubscribePresenter;
import com.example.shosho.coupmix.view.SubscribeView;
import com.fourhcode.forhutils.FUtilsValidation;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubscribeFragment extends Fragment implements SubscribeView {

    SubscribePresenter subscribePresenter;
    EditText userEmail;
    Button subscribeBtn;
    Toolbar toolbar;
    public SubscribeFragment() {
        // Required empty public constructor
    }

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate( R.layout.fragment_subscribe, container, false );
        init();
        subscribePresenter = new SubscribePresenter( getContext(), this );
        subscribeBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subscribHere();
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

    public static boolean isValidEmail(String Email) {
        return !TextUtils.isEmpty( Email ) && android.util.Patterns.EMAIL_ADDRESS.matcher( Email ).matches();
    }

    private void subscribHere() {


        FUtilsValidation.isEmpty( userEmail, getResources().getString( R.string.Pleasewriteyouremail ) );

        NetworkConnection networkConnection = new NetworkConnection( getContext() );
        if (networkConnection.isNetworkAvailable( getContext() )) {
            if (
                    !userEmail.getText().toString().equals( "" )
                            && validateEmail()) {
                User user = new User();

                user.setEmail( userEmail.getText().toString() );

                subscribePresenter.getSubscribeResult( user );
            } else {
                Toast.makeText( getContext(), R.string.Pleasewriteyouremail, Toast.LENGTH_SHORT ).show();
            }

        } else {
            Toast.makeText( getContext(), R.string.Checkyourinternetconnection
                    , Toast.LENGTH_SHORT ).show();
        }
    }




    private void init() {
        userEmail=view.findViewById( R.id.subscribe_edit_text_email );
        subscribeBtn=view.findViewById( R.id.subscribe_btn_subscribe );
        toolbar=view.findViewById( R.id.subscribe_toolbar );
    }

    @Override
    public void showSubscribeResult(String Data) {
        Toast.makeText( getContext(),R.string.ThanksForyoursubscribe, Toast.LENGTH_LONG ).show();
        getFragmentManager().beginTransaction().add( R.id.content_navigation,new HomeFragment() )
                .addToBackStack( null ).commit();
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
