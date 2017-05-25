package com.mioffers.malloffer.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mioffers.malloffer.R;
import com.mioffers.malloffer.core.connectivity.FireBaseHandler;
import com.mioffers.malloffer.core.connectivity.LocationHandler;
import com.mioffers.malloffer.core.connectivity.NetHandler;
import com.mioffers.malloffer.dagger.component.DaggerMallOfferComponent;
import com.mioffers.malloffer.dagger.component.MallOfferComponent;
import com.mioffers.malloffer.dagger.module.AppModule;
import com.mioffers.malloffer.dagger.module.MallOfferModule;
import com.mioffers.malloffer.exceptions.ExceptionHandler;
import com.mioffers.malloffer.navDrawer.DrawerHandler;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    LocationHandler locationHandler;

    @Inject
    NetHandler netHandler;

    @Inject
    FireBaseHandler fireBaseHandler;

    @Inject
    DrawerHandler drawerHandler;

    public static String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MallOfferComponent component = DaggerMallOfferComponent.builder()
                .appModule(new AppModule(this.getApplication(), this))
                .mallOfferModule(new MallOfferModule(""))
                .build();
        component.inject(this);

        userId = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);

        netHandler.checkNet();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*Snackbar.make(toolbar, drawerHandler.temp, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();*/


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            /*if (shouldLoadHomeFragOnBackPress) {
                // checking if user is on other navigation menu
                // rather than home
                if (navItemIndex != 0) {
                    navItemIndex = 0;
                    CURRENT_TAG = TAG_HOME;
                    loadHomeFragment();
                    return;
                }
            }*/
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
            Toast.makeText(getApplicationContext(), "Logout user!", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
