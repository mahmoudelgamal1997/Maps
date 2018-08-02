package com.example2017.android.maps;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnStreetViewPanoramaReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
       StreetViewPanoramaFragment streetViewPanoramaFragment=(StreetViewPanoramaFragment)getFragmentManager()
                .findFragmentById(R.id.map);
        streetViewPanoramaFragment.getStreetViewPanoramaAsync(this);







    }

    @Override
    public void onStreetViewPanoramaReady(StreetViewPanorama streetViewPanorama) {



        streetViewPanorama.setPosition(new LatLng(-33.87365, 151.20689



        ));

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */





/*
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(30.69227,31.140383);
        mMap.addMarker(new MarkerOptions().position(sydney).title("elsaqa mosque").snippet("home").icon(BitmapDescriptorFactory.fromResource(R.drawable.)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,12));




        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                double latitude=latLng.latitude;
                double longtiude=latLng.longitude;

                Toast.makeText(MapsActivity.this, String.valueOf(latitude)+" , " +String.valueOf(longtiude), Toast.LENGTH_SHORT).show();
            }
        });


    }
*/
}
