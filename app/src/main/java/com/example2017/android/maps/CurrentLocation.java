package com.example2017.android.maps;

import android.*;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;

public class CurrentLocation extends AppCompatActivity {

    Button but_CurrentLocation;
    TextView txt_viewLocation;
    String mLocation = "";
    LocationManager locationManager;
    android.location.LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_location);




        ActivityCompat.requestPermissions(CurrentLocation.this,
                new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},
                1);


        but_CurrentLocation = (Button) findViewById(R.id.button);
        txt_viewLocation = (TextView) findViewById(R.id.textView);



        but_CurrentLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_viewLocation.setText(mLocation);
            }
        });

        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        locationListener = new android.location.LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                try {
                    double latitude = location.getLatitude();
                    double longtude = location.getLongitude();

                    mLocation = "" + latitude + "," + longtude;
                    Toast.makeText(CurrentLocation.this, "" + latitude + "," + longtude, Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(CurrentLocation.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };


        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;

        }else {
            Toast.makeText(CurrentLocation.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);

        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);

    }



    public void fused(View v){
        Intent intent =new Intent(CurrentLocation.this,FusedLocationProvider.class);
        startActivity(intent);
    }

    public void offline(View v){
        Intent intent =new Intent(CurrentLocation.this,WorkOffline.class);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                  if (ContextCompat.checkSelfPermission(this , Manifest.permission.ACCESS_FINE_LOCATION)
                          ==PackageManager.PERMISSION_GRANTED);
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);

            }

            // other 'case' lines to check for other
            // permissions this app might request

    }


}
