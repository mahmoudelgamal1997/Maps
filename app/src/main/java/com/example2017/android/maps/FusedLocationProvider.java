package com.example2017.android.maps;

import android.*;
import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;

public class FusedLocationProvider extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,LocationListener {

    FusedLocationProviderClient client;
    FusedLocationProviderApi LocationProvider = LocationServices.FusedLocationApi;
    Button button;
    GoogleApiClient mgoogleclient;
    int i = 0;
    int x = 0;
    TextView txt, lenght;
    ArrayList<Double> lat = new ArrayList<>();
    ArrayList<Double> lon = new ArrayList<>();
    LocationRequest locationRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fused_location_provider);


        requestPermmission();


        mgoogleclient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mgoogleclient.connect();


        locationRequest = new LocationRequest();
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);


        //    client = LocationServices.getFusedLocationProviderClient(this);

        button = (Button) findViewById(R.id.button3);
        txt = (TextView) findViewById(R.id.textView2);
        lenght = (TextView) findViewById(R.id.textView3);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {






                /*
                        if (x==2) {
                            Location loc1 = new Location("");
                            loc1.setLatitude(lat.get(i));
                            loc1.setLongitude(lon.get(i));

                            Location loc2 = new Location("");
                            loc2.setLatitude(lat.get(i + 1));
                            loc2.setLongitude(lon.get(i + 1));

                            float distanceInMeters = loc1.distanceTo(loc2);
                            lenght.setText("" + distanceInMeters);

*/

            }
        });

    }

    public void requestPermmission() {

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

        requestLocation();
    }




    public void requestLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mgoogleclient, locationRequest, this);
    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onResume() {
        super.onResume();


        if (mgoogleclient.isConnected()){
            requestLocation();
        }

    }



    @Override
    public void onLocationChanged(Location location) {

        lat.add(location.getLatitude());
        lon.add(location.getLongitude());


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (x>=2)
                {

                        int index =(lat.size()-1);
                        calculate(index);

                }

            }
        });


        x++;
      //  Toast.makeText(getApplication(),"Location changed :"+location.getLatitude() +","+location.getLongitude(),Toast.LENGTH_SHORT).show();
    }




    public void calculate(int index)
    {

        Location loc1 = new Location("");
        loc1.setLatitude(lat.get(index));
        loc1.setLongitude(lon.get(index));

        Location loc2 = new Location("");
        loc2.setLatitude(lat.get(index - 1));
        loc2.setLongitude(lon.get(index - 1));

        float distanceInMeters = loc1.distanceTo(loc2);
        lenght.setText("" + distanceInMeters+"\n"+lat.get(index)+","+lon.get(index)+"\n"+lat.get(index-1)+","+lon.get(index-1)+"\n");


    }
}
