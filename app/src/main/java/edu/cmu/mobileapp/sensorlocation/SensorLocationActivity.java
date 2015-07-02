package edu.cmu.mobileapp.sensorlocation;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class SensorLocationActivity extends ActionBarActivity {

    ActionBar.Tab sensorTab, locationTab;
    Fragment sensorFragment = new SensorFragment();
    Fragment locationFragment = new LocationFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sensor_location);

        //ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        sensorTab = actionBar.newTab();
        locationTab = actionBar.newTab();

        sensorTab.setText("Sensors");
        locationTab.setText("Locate Me");

        sensorTab.setTabListener(new TabClickListener(sensorFragment));
        locationTab.setTabListener(new TabClickListener(locationFragment));

        actionBar.addTab(sensorTab);
        actionBar.addTab(locationTab);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sensor_location, menu);
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

        return super.onOptionsItemSelected(item);
    }
}
