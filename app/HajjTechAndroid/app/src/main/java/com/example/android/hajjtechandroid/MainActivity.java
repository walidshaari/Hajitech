package com.example.android.hajjtechandroid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.*;
/*
import com.mukesh.countrypicker.Country;
import com.mukesh.countrypicker.CountryPicker;
import com.mukesh.countrypicker.OnCountryPickerListener;
*/

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "PhoneAuth";

    private EditText phoneText;
    private Button sendButton, resendButton, verifyButton;
    private EditText verificationCode;
    private ProgressDialog progressDialog;


    //private CountryPicker countryPicker;
    //private  String dialCode = "+966";
    //private int flag = com.mukesh.countrypicker.R.drawable.flag_sa;


    private String phoneVerificationId;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            verificationCallbacks;
    private PhoneAuthProvider.ForceResendingToken resendToken;

    private FirebaseAuth firebaseAuth;

    private LinearLayout verificationLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog = new ProgressDialog(this);
        verificationLayout = findViewById(R.id.verifycode_layout);


        phoneText = (EditText) findViewById(R.id.phoneNumber_editText);
        verificationCode = (EditText) findViewById(R.id.verifyCode_editText);
        //resendButton = (Button) findViewById(R.id.resendCode_button);
        sendButton = (Button) findViewById(R.id.sendCode_button);
        verifyButton = (Button) findViewById(R.id.verifyCode_button);

        verificationLayout.setVisibility(View.INVISIBLE);
        firebaseAuth = FirebaseAuth.getInstance();


    }

    public void sendCode(View view) {

        String phoneNumber = phoneText.getText().toString().trim();

        /*if(TextUtils.isEmpty(phoneNumber)){
            //email is empty
            Toast.makeText(this, "Please enter your phone number", Toast.LENGTH_SHORT).show();
            //to stop function execution put return
            return;
        }*/



        //Toast.makeText(this, "Phone number"+phoneNumber, Toast.LENGTH_SHORT).show();

        progressDialog.setMessage("Wait please ...");
        progressDialog.show();
        setUpVerificationCallbacks();


        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                verificationCallbacks);
    }


    private void setUpVerificationCallbacks() {

        verificationCallbacks =
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                    @Override
                    public void onVerificationCompleted(
                            PhoneAuthCredential credential) {
                        progressDialog.dismiss();
                        verificationLayout.setVisibility(View.VISIBLE);
                        signInWithPhoneAuthCredential(credential);
                    }

                    @Override
                    public void onVerificationFailed(FirebaseException e) {

                        if (e instanceof FirebaseAuthInvalidCredentialsException) {
                            // Invalid request
                            Log.d(TAG, "Invalid credential: "
                                    + e.getLocalizedMessage());
                        } else if (e instanceof FirebaseTooManyRequestsException) {
                            // SMS quota exceeded
                            Log.d(TAG, "SMS Quota exceeded.");
                        }
                    }

                    @Override
                    public void onCodeSent(String verificationId,
                                           PhoneAuthProvider.ForceResendingToken token) {
                        progressDialog.dismiss();
                        verificationLayout.setVisibility(View.VISIBLE);
                        Toast.makeText(MainActivity.this, "A code is sent", Toast.LENGTH_SHORT).show();

                        phoneVerificationId = verificationId;
                        resendToken = token;
                        verificationCode.setVisibility(View.VISIBLE);
                    }
                };
    }


    public void verifyCode(View view) {

        String code = verificationCode.getText().toString();

        PhoneAuthCredential credential =
                PhoneAuthProvider.getCredential(phoneVerificationId, code);
        progressDialog.setMessage("Signing in... please wait");
        progressDialog.show();
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            //progressDialog.dismiss();
                            finish();
                            startActivity(new Intent(getApplicationContext(), HomeScreen.class));
                            //FirebaseUser user = task.getResult().getUser();

                        } else {
                            if (task.getException() instanceof
                                    FirebaseAuthInvalidCredentialsException) {
                                //progressDialog.dismiss();
                                Toast.makeText(MainActivity.this,
                                        "You have entered an invalid code", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    public void resendCode(View view) {

        String phoneNumber = phoneText.getText().toString();

        setUpVerificationCallbacks();

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,
                60,
                TimeUnit.SECONDS,
                this,
                verificationCallbacks,
                resendToken);
    }
}
