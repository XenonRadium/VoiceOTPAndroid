package com.example.custom_make_a_call;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText calleeNumber, verifyOTP;
    private Button callButton, verifyButton;
    private TextView verifyConfirm;

    private String url = "https://custom-make-a-call-4269-dev.twil.io/make-call";
    private String defaultURL = "https://custom-make-a-call-4269-dev.twil.io/default";

    private String OTPNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calleeNumber = findViewById(R.id.calleeNumber);
        callButton = findViewById(R.id.callButton);

        verifyOTP = findViewById(R.id.verifyOTP);
        verifyButton = findViewById(R.id.verifyButton);
        verifyConfirm = findViewById(R.id.verifyConfirm);


        final StringRequest[] stringRequest = new StringRequest[1];
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNetworkRequest(stringRequest);
            }
        });

        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setVerified();
            }
        });

    }


    public void addNetworkRequest(StringRequest[] stringRequest){
        OTPNumber = String.valueOf((int)Math.floor((100000 + Math.random() * 900000)));
        Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
        if (calleeNumber.getText().toString().equals("")) {
            stringRequest[0] = new StringRequest(Request.Method.POST, defaultURL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                            error.printStackTrace();
                        }
                    });
            RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
            requestQueue.add(stringRequest[0]);
        } else {
            String number = calleeNumber.getText().toString();

            stringRequest[0] = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("To", number);
                    params.put("OTP", OTPNumber);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
            requestQueue.add(stringRequest[0]);
        }
    }

    public void setVerified(){
        String inputString = verifyOTP.getText().toString();
        if (inputString.equals(OTPNumber)){
            verifyConfirm.setText("Verified");
            verifyConfirm.setBackgroundResource(R.color.green);
        }else{
            verifyConfirm.setText("Invalid");
            verifyConfirm.setBackgroundResource(R.color.red);
        }
//        verifyConfirm.setText(OTPNumber);
    }
}
