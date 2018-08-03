package com.example.android.hajjtechandroid;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestSending extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener{
    ImageView sendButton, cancelButton;
    private GoogleMap mMap;
    private ProgressDialog progressDialog;

    Double THRESHOLD = 100.0;
    int COUNT_THRESHOLD = 10;
    Location loc1;
    FirebaseFirestore firestore;
    MarkerOptions marker;
    private Double currentUserLatitude, currentUserLongitude;

    List<Location> closeUsersLocations = new ArrayList<>();

    LocationManager locationManager;
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    LocationListener locationListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_sending);

        progressDialog = new ProgressDialog(this);

        sendButton = findViewById(R.id.confirmSendButton_send_request);
        cancelButton =findViewById(R.id.cancel_reuest_sending_button);

        sendButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);




        loc1 = new Location("");

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_home_screen);
        mapFragment.getMapAsync(this);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]
                            {Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        }
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                currentUserLongitude=longitude;
                currentUserLatitude=latitude;
                loc1.setLongitude(currentUserLongitude);
                loc1.setLatitude(currentUserLatitude);
                //get the location name from latitude and longitude
                Geocoder geocoder = new Geocoder(getApplicationContext());
                try {
                    List<Address> addresses =
                            geocoder.getFromLocation(latitude, longitude, 1);
                    String result = addresses.get(0).getLocality()+":";
                    result += addresses.get(0).getCountryName();
                    LatLng latLng = new LatLng(latitude, longitude);
                    loadUsersLocation();
                    marker  = new MarkerOptions().position(latLng);
                    marker.draggable(true);
                    mMap.clear();
                    mMap.addMarker(marker);
                    mMap.setMaxZoomPreference(20);
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12.0f));

                    LatLngBounds.Builder builder = new LatLngBounds.Builder();


                    Log.i("xxx Size",""+closeUsersLocations.size());
                    for(int i = 0 ; i < closeUsersLocations.size() ; i++) {
                        builder.include(marker.getPosition());
                        LatLng latLng1 = new LatLng(closeUsersLocations.get(i)
                                .getLatitude(), closeUsersLocations.get(i).getLongitude());
                        mMap.addMarker(new MarkerOptions().position(latLng1).title("Cool"));
                    }

                    if (closeUsersLocations.size() > 0) {
                        LatLngBounds bounds = builder.build();

                        int padding = 0; // offset from edges of the map in pixels
                        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);

                        mMap.moveCamera(cu);

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);


    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;



    }


    @Override
    public void onClick(View v) {
        if ( v == sendButton){
            progressDialog.setMessage("Sending your request, please wait");
            progressDialog.show();
            finish();
            progressDialog.dismiss();
            startActivity(new Intent(getApplicationContext(),MoreDetailsActivity.class));
            //send details
        }

        if (v == cancelButton){
            Toast.makeText(this, "Request is cancelled", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(getApplicationContext(),HomeScreen.class));
            //cancel request and return to home screen.
        }
    }

    private void loadUsersLocation() {

        firestore = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        firestore.setFirestoreSettings(settings);


        firestore.collection("hajjdata")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        //closeUsersLocations.clear();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                Log.d("xxx", document.getId() + " => " + document.getData().get("loc"));
                                Map<String, Object> map = (HashMap) document.getData().get("loc");
                                Double latitude = (Double) map.get("_latitude");
                                Double longitude = (Double) map.get("_longitude");


                                Location loc2 = new Location("");
                                loc2.setLongitude(longitude);
                                loc2.setLatitude(latitude);


                                if (loc1.distanceTo(loc2) < THRESHOLD && closeUsersLocations.size() < COUNT_THRESHOLD) {
                                    closeUsersLocations.add(loc2);
                                }

                            }
                        } else {
                            Log.d("xxx", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }


    }
