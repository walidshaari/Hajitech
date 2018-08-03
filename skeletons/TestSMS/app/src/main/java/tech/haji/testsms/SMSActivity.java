package tech.haji.testsms;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.mobily.api.sms.entity.BalanceResponse;
import com.mobily.api.sms.utility.Const;
import com.mobily.api.sms.utility.MobilyAPI;
import com.mobily.api.sms.utility.OnDataReceiveListner;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.DataOutputStream;
import android.util.Log;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import android.os.Looper;
import com.loopj.android.http.AsyncHttpResponseHandler;
import android.app.Activity;
public class SMSActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {

    /**
     * Keep track of the send SMS task to ensure we can cancel it if requested.
     */
    private SendSMSTask mSMSTask = null;

    // UI references.
    private EditText mPhone;
    private View mProgressView;
    private View mSMSFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mPhone = (EditText) findViewById(R.id.phone);

        Button mphoneNumberButton = (Button) findViewById(R.id.sendSMS_button);
        mphoneNumberButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSMS();
            }
        });

        mSMSFormView = findViewById(R.id.sendSMS_form);
        mProgressView = findViewById(R.id.sendSMS_progress);
    }


    private void sendSMS() {
        if (mSMSTask != null) {
            return;
        }

        // Reset errors.
        mPhone.setError(null);

        // Store values at the time of the login attempt.
        String phoneNumber = mPhone.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(phoneNumber)) {
            mPhone.setError(getString(R.string.error_field_required));
            focusView = mPhone;
            cancel = true;
        } else if (!isValidNumber(phoneNumber)) {
            mPhone.setError(getString(R.string.error_invalid_phone));
            focusView = mPhone;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mSMSTask = new SendSMSTask(phoneNumber);
            mSMSTask.execute((Void) null);
        }
    }
    private boolean isValidNumber(String phoneNumber) {
        String regexStr = "^[0-9]{10}$";

        return phoneNumber.matches(regexStr);
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mSMSFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mSMSFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mSMSFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mSMSFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    /*public class FetchTask extends AsyncTask<Void, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(Void... params) {
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://www.yoursite.com/script.php");


                // Execute HTTP Post Request
                HttpResponse response = httpclient.execute(httppost);

                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                sb.append(reader.readLine() + "\n");
                String line = "0";
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                reader.close();
                String result11 = sb.toString();

                // parsing data
                return new JSONArray(result11);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(JSONArray result) {
            if (result != null) {
                // do something
            } else {
                // error occured
            }
        }
    }*/
    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class SendSMSTask extends AsyncTask<Void, Void, Boolean> {

        private final String mPhoneNumber;

        SendSMSTask(String number) {
            mPhoneNumber = number;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service


            try {

                //auth data
                String userName = "";
                String password = "";

                //send reques
                org.json.JSONObject auth = new JSONObject();
                auth.put("mobile", userName);
                auth.put("password", password);
                auth.put("numbers", "966560900049,966503199009");
                auth.put("sender", "ALHARA.NET");
                auth.put("msg", "Dear Participant, \nCongratulations. Your team has been selected for the second round. Please come to the office near the main gate.");
                auth.put("lang", "3");
                auth.put("msgId", "1");
                auth.put("applicationType", "68");
                auth.put("timeSend", "00:00:00");
                auth.put("dateSend", "01/01/2018");
                auth.put("dateSend", "01/01/2018");

                URL url = new URL("https://mobily.ws/api/msgSend.php");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);

                Log.i("JSON", auth.toString());
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
                os.writeBytes(auth.toString());

                os.flush();
                os.close();

                Log.i("STATUS ", String.valueOf(conn.getResponseCode()));
                Log.i("MSG ", conn.getResponseMessage());
                Log.i("type ", conn.getContentType());
                Log.i("type ", conn.getContent().toString());
                BufferedReader streamReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                StringBuilder responseStrBuilder = new StringBuilder();

                String inputStr;
                while ((inputStr = streamReader.readLine()) != null)
                    responseStrBuilder.append(inputStr);
                JSONObject obj = new JSONObject(responseStrBuilder.toString());
                Log.i("LL", obj.toString());
               // String response = convertInputStreamToString(inputStream);


                conn.disconnect();
                Log.i("DONE ", conn.getContentType());
            } catch (Exception e) {
                e.printStackTrace();
            }



            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mSMSTask = null;
            showProgress(false);
            finish();
        }

        @Override
        protected void onCancelled() {
            mSMSTask = null;
            showProgress(false);
        }
    }
}

