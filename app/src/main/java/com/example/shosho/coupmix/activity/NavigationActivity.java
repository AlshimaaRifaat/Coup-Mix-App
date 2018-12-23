package com.example.shosho.coupmix.activity;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.shosho.coupmix.R;
import com.example.shosho.coupmix.fragment.AboutCoupMixFragment;
import com.example.shosho.coupmix.fragment.CategoryFragment;
import com.example.shosho.coupmix.fragment.CategoryItemFragment;
import com.example.shosho.coupmix.fragment.HomeFragment;
import com.example.shosho.coupmix.fragment.OrderItemFragment;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
private int currentSelectedPosition=0;
Fragment fragment;
NavigationView navigationView;
    public static ActionBarDrawerToggle toggle;
    public static DrawerLayout drawer;

    public static Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_navigation );
       // toolbar = (Toolbar) findViewById( R.id.home_toolbar );
     // setSupportActionBar( toolbar );



       /* FloatingActionButton fab = (FloatingActionButton) findViewById( R.id.fab );
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make( view, "Replace with your own action", Snackbar.LENGTH_LONG )
                        .setAction( "Action", null ).show();
            }
        } );*/
       navigationView=findViewById( R.id.nav_view );
       onNavigationItemSelected( navigationView.getMenu().getItem( 0 ) );
         drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
         toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawer.addDrawerListener( toggle );
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById( R.id.nav_view );
        navigationView.setNavigationItemSelectedListener( this );

      /*toolbar.setNavigationOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
               if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
               } else {
                drawer.openDrawer(GravityCompat.START);
               }
           }
      });*/
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        if (drawer.isDrawerOpen( GravityCompat.START )) {
            drawer.closeDrawer( GravityCompat.START );
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.navigation, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected( item );
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
       switch (item.getItemId())
       {
           case R.id.nav_home:
               currentSelectedPosition=0;
               fragment=new HomeFragment();
               break;

           case R.id.nav_request_order:
               currentSelectedPosition=1;
               fragment=new OrderItemFragment();
               break;
           case R.id.nav_category:
               currentSelectedPosition=2;
               fragment=new CategoryFragment();
               break;
           case R.id.nav_about_coup_mix:
               currentSelectedPosition=3;
               fragment=new AboutCoupMixFragment();
               break;

               default:
                   currentSelectedPosition=0;
       }
if(item.isChecked())
{
    item.setCheckable( false );
}else
{
    item.setChecked( true );
}

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace( R.id.content_navigation,fragment ).commit();


        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        drawer.closeDrawer( GravityCompat.START );
        return true;
    }
}
