package com.example.android.hajjtechandroid;

import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.JsonObject;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class testFireStore extends AppCompatActivity {

    Double THRESHOLD = 100.0;
    int COUNT_THRESHOLD = 10;
    Location loc1;
    FirebaseFirestore firestore;
    private Double currentUserLatitude = 21.616843, currentUserLongitude = 39.157127;

    List<Location> closeUsersLocations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_fire_store);

        loc1 = new Location("");
        loc1.setLongitude(currentUserLongitude);
        loc1.setLatitude(currentUserLatitude);




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
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                //Log.d("xxx", document.getId() + " => " + document.getData().get("loc"));
                                Map<String,Object> map = (HashMap) document.getData().get("loc");
                                Double latitude = (Double) map.get("_latitude");
                                Double longitude = (Double) map.get("_longitude");

                                Location loc2 = new Location("");
                                loc2.setLongitude(longitude);
                                loc2.setLatitude(latitude);

                                if(loc1.distanceTo(loc2) < THRESHOLD && closeUsersLocations.size()<COUNT_THRESHOLD){
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
