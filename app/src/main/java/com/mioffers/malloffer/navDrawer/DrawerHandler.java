package com.mioffers.malloffer.navDrawer;

import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mioffers.malloffer.R;
import com.mioffers.malloffer.activities.MainActivity;
import com.mioffers.malloffer.fragments.HomeFragment;

/**
 * Created by laxman.muttineni on 23/05/17.
 */
public class DrawerHandler implements NavigationView.OnNavigationItemSelectedListener  {

    private MainActivity activity;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Toolbar toolbar;

    private View navHeader;
    private TextView headerTitle, headerDescription;
    private ImageView profileImage;


    private int navItemIndex = 0;

    private final String TAG_HOME = "home";
    private final String TAG_PHOTOS = "photos";
    private final String TAG_MOVIES = "movies";
    private final String TAG_NOTIFICATIONS = "notifications";
    private final String TAG_SETTINGS = "settings";
    private String CURRENT_TAG = TAG_HOME;

    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;

    public DrawerHandler(MainActivity activity,
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

        mHandler = new Handler();

        loadNavHeader();
        setUpNavigationView();
    }

    private void loadNavHeader() {
        headerTitle.setText(R.string.guest_user);
        headerDescription.setText(R.string.view_profile);
    }

    private void setUpNavigationView() {

        navigationView.setNavigationItemSelectedListener(this);
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

    private void loadHomeFragment() {
        selectNavMenu();
        setToolbarTitle();
        if (activity.getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();
            //toggleFab();
            return;
        }

        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

        // show or hide the fab button
        //toggleFab();

        //Closing drawer on item click
        drawer.closeDrawers();

        // refresh toolbar menu
        activity.invalidateOptionsMenu();
    }


    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setToolbarTitle() {
       // activity.getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            /*case 0:
                // home
                HomeFragment homeFragment = new HomeFragment();
                return homeFragment;
            case 1:
                // photos
                PhotosFragment photosFragment = new PhotosFragment();
                return photosFragment;
            case 2:
                // movies fragment
                MoviesFragment moviesFragment = new MoviesFragment();
                return moviesFragment;
            case 3:
                // notifications fragment
                NotificationsFragment notificationsFragment = new NotificationsFragment();
                return notificationsFragment;

            case 4:
                // settings fragment
                SettingsFragment settingsFragment = new SettingsFragment();
                return settingsFragment;*/
            default:
                return new HomeFragment();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        Snackbar.make(toolbar, "clicked", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

        switch (menuItem.getItemId()) {
            case R.id.offers :
                navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
                break;

            case R.id.reminders:
                navItemIndex = 1;
                CURRENT_TAG = TAG_HOME;
                break;
            case R.id.invite:
                navItemIndex = 2;
                CURRENT_TAG = TAG_PHOTOS;
                break;
            case R.id.shared:
                navItemIndex = 3;
                CURRENT_TAG = TAG_MOVIES;
                break;
            case R.id.promotions:
                navItemIndex = 4;
                CURRENT_TAG = TAG_NOTIFICATIONS;
                break;
            case R.id.settings:
                navItemIndex = 5;
                CURRENT_TAG = TAG_SETTINGS;
                break;
            case R.id.about:
                // launch new intent instead of loading fragment
                //startActivity(new Intent(activity, AboutUsActivity.class));
                drawer.closeDrawers();
                return true;
            case R.id.post:
                // launch new intent instead of loading fragment
                //startActivity(new Intent(MainActivity.this, PrivacyPolicyActivity.class));
                drawer.closeDrawers();
                return true;
            default:
                navItemIndex = 0;
        }

        //Checking if the item is in checked state or not, if not make it in checked state
        /*if (menuItem.isChecked()) {
            menuItem.setChecked(false);
        } else {
            menuItem.setChecked(true);
        }
        menuItem.setChecked(true);*/

        loadHomeFragment();

        return true;
    }
}
