package edu.cmu.mobileapp.sensorlocation;

import android.content.Context;
import android.location.Address;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;

/**
 * Created by srikrishnan_suresh on 22-06-2015.
 */
public class LocationChangeListener implements LocationListener {

    GoogleMap googleMap;
    Context context;

    public LocationChangeListener(GoogleMap googleMap, Context context) {
        this.googleMap = googleMap;
        this.context = context;
    }

    @Override
    public void onLocationChanged(Location location) {
        googleMap.setMyLocationEnabled(true);
        Log.i("Location", location.getLatitude() + " , " + location.getLongitude());
    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

}
