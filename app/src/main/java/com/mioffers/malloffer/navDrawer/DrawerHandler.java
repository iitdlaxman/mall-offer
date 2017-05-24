package com.mioffers.malloffer.navDrawer;

import android.app.Activity;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mioffers.malloffer.R;

/**
 * Created by laxman.muttineni on 23/05/17.
 */
public class DrawerHandler {

    private Activity activity;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Toolbar toolbar;

    private View navHeader;
    private TextView headerTitle, headerDescription;
    private ImageView profileImage;
    public String temp = "i am also injected";

    public DrawerHandler(Activity activity,
                         DrawerLayout drawer,
                         Toolbar toolbar,
                         NavigationView navigationView) {
        this.activity = activity;
        this.drawer = drawer;
        this.navigationView = navigationView;
        this.toolbar = toolbar;

        navHeader = navigationView.getHeaderView(0);
        headerTitle = (TextView) navHeader.findViewById(R.id.headerTitle);
        headerDescription = (TextView) navHeader.findViewById(R.id.headerDescription);
        profileImage = (ImageView) navHeader.findViewById(R.id.profileImage);
    }

    private void loadNavHeader() {
        headerTitle.setText("Ravi Tamada");
        headerDescription.setText("www.androidhive.info");
    }

    private void setUpNavigationView() {

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.offers :
                        break;

                    /*case R.id.:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_HOME;
                        break;
                    case R.id.nav_photos:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_PHOTOS;
                        break;
                    case R.id.nav_movies:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_MOVIES;
                        break;
                    case R.id.nav_notifications:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_NOTIFICATIONS;
                        break;
                    case R.id.nav_settings:
                        navItemIndex = 4;
                        CURRENT_TAG = TAG_SETTINGS;
                        break;
                    case R.id.nav_about_us:
                        // launch new intent instead of loading fragment
                        startActivity(new Intent(MainActivity.this, AboutUsActivity.class));
                        drawer.closeDrawers();
                        return true;
                    case R.id.nav_privacy_policy:
                        // launch new intent instead of loading fragment
                        startActivity(new Intent(MainActivity.this, PrivacyPolicyActivity.class));
                        drawer.closeDrawers();
                        return true;
                    default:
                        navItemIndex = 0;*/
                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);

                //loadHomeFragment();

                return true;
            }
        });


        ActionBarDrawerToggle actionBarDrawerToggle =
                new ActionBarDrawerToggle(activity, drawer, toolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawer.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }





//    private void loadHomeFragment() {
//        selectNavMenu();
//        setToolbarTitle();
//
//        // if user select the current navigation menu again, don't do anything
//        // just close the navigation drawer
//        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
//            drawer.closeDrawers();
//
//            // show or hide the fab button
//            toggleFab();
//            return;
//        }
//
//        // Sometimes, when fragment has huge data, screen seems hanging
//        // when switching between navigation menus
//        // So using runnable, the fragment is loaded with cross fade effect
//        // This effect can be seen in GMail app
//        Runnable mPendingRunnable = new Runnable() {
//            @Override
//            public void run() {
//                // update the main content by replacing fragments
//                Fragment fragment = getHomeFragment();
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
//                        android.R.anim.fade_out);
//                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
//                fragmentTransaction.commitAllowingStateLoss();
//            }
//        };
//
//        // If mPendingRunnable is not null, then add to the message queue
//        if (mPendingRunnable != null) {
//            mHandler.post(mPendingRunnable);
//        }
//
//        // show or hide the fab button
//        toggleFab();
//
//        //Closing drawer on item click
//        drawer.closeDrawers();
//
//        // refresh toolbar menu
//        invalidateOptionsMenu();
//    }
//
//    private void selectNavMenu() {
//        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
//    }
//
//    private void setToolbarTitle() {
//        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
//    }

}
