package com.example.android.hajjtechandroid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MoreDetailsActivity extends AppCompatActivity implements View.OnClickListener{

    protected static boolean SENT_REQUEST = false;

    ImageView imageView, imageView1,imageView2,imageView3, imageView4,imageView5;
    ProgressDialog progressDialog;
    ImageView cancelButton, confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_details);

        progressDialog = new ProgressDialog(this);
        imageView = findViewById(R.id.button0);
        imageView1 = findViewById(R.id.button1);
        imageView2 = findViewById(R.id.button2);
        imageView3 = findViewById(R.id.button3);
        imageView4 = findViewById(R.id.button4);
        imageView5 = findViewById(R.id.button5);
        cancelButton = findViewById(R.id.cancel_request_button);
        confirmButton = findViewById(R.id.confirmSendButton);


        //to gray out an image
        imageView.setColorFilter(Color.argb(150,192,192,192));
        imageView1.setColorFilter(Color.argb(150,192,192,192));
        imageView2.setColorFilter(Color.argb(150,192,192,192));
        imageView3.setColorFilter(Color.argb(150,192,192,192));
        imageView4.setColorFilter(Color.argb(150,192,192,192));
        imageView5.setColorFilter(Color.argb(150,192,192,192));

        imageView.setOnClickListener(this);
        imageView1.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        imageView3.setOnClickListener(this);
        imageView4.setOnClickListener(this);
        imageView5.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
        confirmButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == imageView){
            imageView.clearColorFilter();
        }
        if(v == imageView1){
            imageView1.clearColorFilter();
        }
        if(v == imageView2){
            imageView2.clearColorFilter();
        }
        if(v == imageView3){
            imageView3.clearColorFilter();
        }
        if(v == imageView4){
            imageView4.clearColorFilter();
        }
        if(v == imageView5){
            imageView5.clearColorFilter();
        }
        if(v == cancelButton){
            progressDialog.setMessage("Cancelling request ...");
            progressDialog.show();
            startActivity(new Intent(getApplicationContext(),HomeScreen.class));
        }
        if(v == confirmButton){
            progressDialog.setMessage("Sending details ...");
            progressDialog.show();
            SENT_REQUEST = true;
            startActivity(new Intent(getApplicationContext(),HomeScreen.class));
        }
    }
}
