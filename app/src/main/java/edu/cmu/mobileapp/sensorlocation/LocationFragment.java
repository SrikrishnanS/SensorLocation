package edu.cmu.mobileapp.sensorlocation;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

/**
 * Created by srikrishnan_suresh on 21-06-2015.
 */

public class LocationFragment extends Fragment {
    MapView mapView;
    private GoogleMap googleMap;
    Context context;
    LocationListener listener;
    LocationManager manager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.location_layout, container, false);
        mapView = (MapView) rootView.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        mapView.onResume();

        try {
            context = getActivity().getApplicationContext();
            MapsInitializer.initialize(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(context, R.string.loading_map, Toast.LENGTH_SHORT).show();
        googleMap = mapView.getMap();

        //add the listeners for location changes
        manager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        listener = new LocationChangeListener(googleMap, context);

        //poll frequence is 1sec, distance change is 0.
        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, listener);
        LocationUtils.markCurrentLocation(googleMap, manager, context);
        return rootView;
    }

    /*Make sure your GPS sensor manager listener/ Delegate
    * should be unregistered when user switches use cases
    * on the tab button.
    * */
    @Override
    public void onDestroy() {
        manager.removeUpdates(listener);
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

}
