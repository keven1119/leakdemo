package com.keven.leak.leakdemo;

import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.TimeUnit;

public class LeakActivity extends AppCompatActivity{

    private LocationManager locationManager;
    private LocationListener locationListener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {
            Log.d("keven1119","onLocationChanged");
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.d("keven1119","onStatusChanged");
        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.d("keven1119","onProviderEnabled");
        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.d("keven1119","onProviderDisabled");
        }
    };

    GpsStatus.Listener listener = new GpsStatus.Listener() {
        @Override
        public void onGpsStatusChanged(int event) {
            Log.d("keven1119","onGpsStatusChanged");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                TimeUnit.MILLISECONDS.toMillis(5), 1, locationListener);

        locationManager.addGpsStatusListener(listener);
    }

    @Override
    protected void onDestroy() {
        locationManager.removeUpdates(locationListener);
//        locationListener = null;
        locationManager.removeGpsStatusListener(listener);
        locationManager = null;
//        listener = null;
        super.onDestroy();
    }
}
