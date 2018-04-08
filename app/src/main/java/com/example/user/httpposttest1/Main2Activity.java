package com.example.user.httpposttest1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class Main2Activity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView = findViewById(R.id.textView2);

//        textView.setText(MainActivity.myJsonObj.toString());

        String jsonObject = "{\"employees\":[\n" +
                "    { \"firstName\":\"John\", \"lastName\":\"Doe\" },\n" +
                "    { \"firstName\":\"Anna\", \"lastName\":\"Smith\" },\n" +
                "    { \"firstName\":\"Peter\", \"lastName\":\"Jones\" }\n" +
                "]}";

        JSONObject newJsonObj = null;
        String xml = "";
        try {
            newJsonObj = new JSONObject(jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            xml = XML.toString(newJsonObj);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        textView.setText(xml);

    }
}
