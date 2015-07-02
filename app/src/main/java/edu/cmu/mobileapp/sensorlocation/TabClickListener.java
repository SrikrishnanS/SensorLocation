package edu.cmu.mobileapp.sensorlocation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;

/**
 * Created by srikrishnan_suresh on 21-06-2015.
 */
public class TabClickListener implements ActionBar.TabListener{
    private android.support.v4.app.Fragment fragment;
    public TabClickListener(android.support.v4.app.Fragment fragment) {
        this.fragment = fragment;

    }
    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        ft.replace(R.id.activity_sensor_location, fragment);
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
        ft.remove(fragment);
    }
    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {

    }
}
