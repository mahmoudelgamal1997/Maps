package com.example2017.android.maps;

import android.*;
import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class FusedLocationProvider extends AppCompatActivity {

    FusedLocationProviderClient client;
    Button button;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fused_location_provider);


        requestLocation();


        client = LocationServices.getFusedLocationProviderClient(this);

        button = (Button) findViewById(R.id.button3);
        txt = (TextView) findViewById(R.id.textView2);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(FusedLocationProvider.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                client.getLastLocation().addOnSuccessListener(FusedLocationProvider.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {

                        double latitude=location.getLatitude();
                        double longtude=location.getLongitude();

                        txt.setText(""+latitude+","+longtude);
                    }
                });
            }
        });

    }

public void requestLocation(){

    ActivityCompat.requestPermissions(this,new String[]{ Manifest.permission.ACCESS_FINE_LOCATION},1);
}

}
