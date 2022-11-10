package com.ibrahimkiceci.mapapplication;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.snackbar.Snackbar;
import com.ibrahimkiceci.mapapplication.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener {

    // when users click longly on the map, i put the red mark on the clicked area. Thus, to use this features i ll use and implement OnMapLongClickListener Interface

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    ActivityResultLauncher<String> permissionLauncher; // it is for permission
    LocationManager locationManager;
    LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //calling registerPermissionLauncher

        registerPermissionLauncher();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapLongClickListener(this); // i need a click listener to click longly on the map.

        // to get a location or working for location we need two classes; one of them is LocationManager, the other one is Location Listener.
        //casting
        // Location Manager is to get the location period from the user, locationlistener is interface so to get location info when user has moved.
        locationManager =(LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                // It shows what will you do when location has been changed
                //LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());
                //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation,15));

                // It coulod be necessary to call at least one time here
                // You need to create algorithm here. For example, shared preferences
                // It looks like compulsory to create algorthm because when user close the phone, the last location is not coming its open again!





            }


        };

        //GETTING LOCATION PERMISSION FROM USER

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            // If permission is not granted so we need to request permission

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
                // This Rationale  is created by android. For instance, if user do not give the permission at first, we can show a snackbar message to open the app

                Snackbar.make(binding.getRoot(), "Permission is compulsory for using maps", Snackbar.LENGTH_INDEFINITE).setAction("Allow Permission", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {



                        // when permission is not allowed by users and then user decided to allow the permission
                        permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);



                    }
                }).show();

            }else {

                // When the first time app starts;

                permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);

            }

        }else{
            // if permission is given before so when app is closed after the permission and open again, here is executes;

            // Every milseconds we are getting th location info from user,


            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);

            // Getting last known location from user;

            Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (lastLocation != null){

                LatLng lastUserLocation = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastUserLocation, 15));

            }

            mMap.setMyLocationEnabled(true); // making visible the blue pointer

        }



        //Latitude, longitude

        // Using of latlng class when user open the app where it will show.

        //vancouver art gallery: 49.2765117,-123.1326748
        /*

        LatLng vancouver = new LatLng(49.2765117,-123.1326748);
        mMap.addMarker(new MarkerOptions().position(vancouver).title("Vancouver Art Gallery")); // adding marker.
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(vancouver, 15)); // When map is open, the location should be closer, the zoom range is 0 to 25

        */
    }

    private void registerPermissionLauncher(){

        permissionLauncher =  registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {

                // if request is given;

                if(result) {

                    if (ContextCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                        // when permission is given

                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0, locationListener);

                        Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if (lastLocation != null){

                            LatLng lastUserLocation = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastUserLocation, 15));

                        }
                    }


                }else {

                    // permission is denied

                    Toast.makeText(MapsActivity.this, "Permission is compulsory !", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }


    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {

        // When users click longly, this method will be called;

        // to clear old red marks;
        //mMap.clear();

        mMap.addMarker(new MarkerOptions().position(latLng)); // adding red mark when clicked longly;



    }
}