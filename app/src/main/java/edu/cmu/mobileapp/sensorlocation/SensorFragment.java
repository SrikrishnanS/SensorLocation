package edu.cmu.mobileapp.sensorlocation;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.List;

/**
 * Created by srikrishnan_suresh on 21-06-2015.
 */
public class SensorFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.sensor_layout, container, false);
        populateTime(rootView);
        populateSensors(rootView);
        return rootView;
    }

    /**
     * Sets the text value for the time
     * */
    private void populateTime(View view) {
        final TextView time = (TextView) view.findViewById(R.id.currentTime);
        String dateTime = getDateTimeString();
        time.setText(dateTime);
    }

    /**
     * Returns the string form of date
     * */
    public String getDateTimeString() {
        String dateTime = DateFormat.getDateTimeInstance().format(new java.util.Date());
        Log.i("Date Time", dateTime);
        return dateTime;
    }

    /**
     * Sets the text value for the sensors strings
     * */
    public void populateSensors(View view){
        final TextView sensors = (TextView) view.findViewById(R.id.availableSensors);
        sensors.setText(getSensorString());
    }

    /**
     * Retrieves a list of sensors as a string
     * */
    public String getSensorString(){
        List<Sensor> sensorList;
        sensorList = ((SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE)).getSensorList(Sensor.TYPE_ALL);
        String sensorString = "";
        for (Sensor sensor : sensorList)
            sensorString = sensorString + sensor.getName() + "\n";
        Log.i("Sensor", sensorString);
        return sensorString;
    }
}
