package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private RequestQueue mRequestQueue;
    TextView txtview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtview=findViewById(R.id.txtview);
        translateText("Volley is not suitable for large download or streaming operations.","hi");
    }

    public void translateText(String input,String op_lang) {

        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://translate.googleapis.com/translate_a/single?client=gtx&sl=en&tl="+op_lang+"&dt=t&q="+input,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        txtview.setText(response);
                        Log.e("xxxxxxxxxxxxxxxxxx",""+response);
                    }


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
              //  params.put("User-Agent","application/x-www-form-urlencoded");
                params.put("User-Agent","PostmanRuntime/7.13.0");
                params.put("Accept","*/*");
                params.put("Cache-Control","no-cache");
                params.put("Postman-Token","de48b522-a33b-4698-8de9-f21f6a7d7a00");
                params.put("Host","translate.googleapis.com");
                params.put("accept-encoding","gzip, deflate");

                return params;
            }



        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}