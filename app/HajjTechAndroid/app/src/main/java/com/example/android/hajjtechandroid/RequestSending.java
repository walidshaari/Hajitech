package com.example.android.hajjtechandroid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class RequestSending extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener{
    ImageView sendButton, cancelButton;
    private GoogleMap mMap;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_sending);

        progressDialog = new ProgressDialog(this);

        sendButton = findViewById(R.id.confirmSendButton_send_request);
        cancelButton =findViewById(R.id.cancel_reuest_sending_button);

        sendButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment map = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_home_screen);
        map.getMapAsync(this);
    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //to change map style into satellite
        //mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);

        mMap.addMarker(new MarkerOptions().position(sydney).title("I like aussie fries"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        //to zoom more
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,15));

        LatLng other1 = new LatLng(-34-5, 151+7);
        mMap.addMarker(new MarkerOptions().position(other1).title("Omar"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        //to zoom more
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(other1,15));
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

}
