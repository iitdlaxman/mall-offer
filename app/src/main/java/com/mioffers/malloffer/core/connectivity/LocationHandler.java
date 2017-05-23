package com.mioffers.malloffer.core.connectivity;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;

import com.mioffers.malloffer.utils.Constants;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by laxman.muttineni on 17/05/17.
 */
public class LocationHandler extends Service implements LocationListener {

    private static final Logger LOGGER = Logger.getLogger(LocationHandler.class.getCanonicalName());

    private final Context context;
    boolean isGPSEnabled;
    boolean isNetworkEnabled;
    private boolean canGetLocation;
    Location location;
    double latitude;
    double langtitude;
    private LocationManager locationManager;

    public LocationHandler(Context context){
        this.context = context;
        updateLocation();
    }

    @Nullable
    private Location updateLocation() {
        try{
            locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if(!isGPSEnabled && ! isNetworkEnabled){
                showSettingsAlert();
            }
            else{
                canGetLocation = true;
                String provider = LocationManager.NETWORK_PROVIDER;
                if (isGPSEnabled) {
                    provider = LocationManager.GPS_PROVIDER;
                }
                locationManager.requestLocationUpdates(provider,
                        Constants.MIN_TIME_BTW_UPDATES , Constants.MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

                if (locationManager != null){
                    location = locationManager.getLastKnownLocation(provider);
                    if (location!=null){
                        langtitude = location.getLongitude();
                        latitude = location.getLatitude();
                    }
                }
            }


        }
        catch (SecurityException e){
            LOGGER.log(Level.SEVERE, e.toString());
        }
        return location;
    }

    public  void stopUsingGPS(){
        if(locationManager != null){
            try{
                locationManager.removeUpdates(LocationHandler.this);
            }
            catch(SecurityException e){
                e.printStackTrace();
            }
        }
    }

    public double getLatitude(){
        if(location != null ){
            latitude =  location.getLatitude();
        }
        return latitude;
    }

    public double getLongtitude(){
        if(location != null ){
            langtitude =  location.getLatitude();
        }
        return langtitude;
    }

    public boolean canGetLocation(){
        return canGetLocation;
    }

    private void showSettingsAlert(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(Constants.GPS_SETTINGS_TITLE);
        alertDialog.setMessage(Constants.GPS_SETTINGS_MSG);
        alertDialog.setPositiveButton(Constants.GPS_SETTINGS_BUTTON, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.startActivity(intent);
            }
        });
        alertDialog.setNegativeButton(Constants.GPS_CANCEL_BUTTON, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
