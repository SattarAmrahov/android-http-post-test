package com.example.user.httpposttest1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity implements CreatePostRequest.CreatePostRequestListener {
    private static final String TAG = "MainActivityTag";

    TextView textView;
    Button button, button2;


    public static JSONObject myJsonObj = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView = findViewById(R.id.textview);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://46.32.191.238:20700/?OperationType=25&WebKey=A28805A4-544C-406E-AD98-B01BE0B9ACBE";
                final String xml = "<StreetInfo><StreetID>85210</StreetID></StreetInfo>";

                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            myJsonObj = XML.toJSONObject(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                            textView.setText(myJsonObj.toString());


//                        JSONObject homenums = null;
//                        try {
//                            homenums = myJsonObj.getJSONObject("HomeNums");
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                        JSONObject homenum = null;
//                        try {
//                            homenum = homenums.getJSONObject("HomeNum");
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                        try {
//                            textView.setText(homenum.getString("ID"));
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView.setText("Error");
                    }
                }){
//                    @Override
//                    public String getBodyContentType() {
//                        return "application/xml;";
//                    }

                    @Override
                    public byte[] getBody() throws AuthFailureError {
                        try {
                            return xml == null? null : xml.getBytes("UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                            return null;
                        }
                    }
                };

            MySingleton.getMySingleton(getApplicationContext()).addToRequestQueue(request);


            }
        });

    }


    @Override
    public void getResponse(String response) {
        textView.setText(response);
    }

    @Override
    public void getErrorResponse(VolleyError error) {
        textView.setText("ERROR");
        Log.d(TAG, "getErrorResponse: " + error);
    }
}
