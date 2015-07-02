package edu.cmu.mobileapp.sensorlocation;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

/**
 * Created by srikrishnan_suresh on 25-06-2015.
 */
public class LocationUtils {

    /**
     * Places a marker on the google map and address callout, using the location manager and context
     * */
    public static void markCurrentLocation(GoogleMap googleMap, LocationManager manager, Context context) {
        Location currentLocation = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);;
        if(LocationUtils.isGPSOn(manager)) {
            if(currentLocation == null)
                Toast.makeText(context, R.string.loading_location, Toast.LENGTH_SHORT).show();
            else {
                Log.i("Location", currentLocation.getLatitude() + " , " + currentLocation.getLongitude());
                updateMarker(googleMap, context, currentLocation);
            }
        }
        else
            Toast.makeText(context, R.string.gps_off, Toast.LENGTH_LONG).show();
    }

    /**
     * Checks if GPS is on for the given manager
     * */
    public static boolean isGPSOn(LocationManager manager) {
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER))
            return false;
        return true;
    }

    /**
     * Places only the marker for a given google map, context and location
     * */
    private static void updateMarker(GoogleMap googleMap, Context context, Location location) {
        MarkerOptions markerOptions = new MarkerOptions();
        Marker marker = null;

        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        markerOptions.position(latLng);

        Address address = LocationUtils.getAddressFromLocation(context, location);
        if(address != null)
            markerOptions.title(address.getAddressLine(0) + ", " + address.getAddressLine(1)).snippet(address.getCountryName());
        else
            Toast.makeText(context, R.string.internet_off, Toast.LENGTH_SHORT).show();

        marker = googleMap.addMarker(markerOptions);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        googleMap.setMyLocationEnabled(true);
        marker.showInfoWindow();
    }

    /**
     * Fetches the address for a given location and context
     * */
    public static Address getAddressFromLocation(Context context, Location location) {
        Address address = null;
        Geocoder gCoder = new Geocoder(context);
        try {
            List<Address> addresses = gCoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            address = addresses.get(0);
        }
        catch (IOException exception) {

        }
        return address;
    }
}
