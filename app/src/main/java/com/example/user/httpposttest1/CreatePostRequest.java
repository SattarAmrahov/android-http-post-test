package com.example.user.httpposttest1;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.io.UnsupportedEncodingException;



public class CreatePostRequest {
    String url = "";
    String xml = "";
    int operationType;
    CreatePostRequestListener listener;
    Context ctx;



    public CreatePostRequest(Context context, int operationType, String xml) {
        this.xml = xml;
        this.operationType = operationType;
        url = "http://46.32.191.238:20700/?OperationType=25&WebKey=A28805A4-544C-406E-AD98-B01BE0B9ACBE";
        ctx = context;
        listener = (CreatePostRequestListener) context;
    }

    StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            listener.getResponse(response);
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            listener.getErrorResponse(error);
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







    public interface CreatePostRequestListener{
        void getResponse(String response);
        void getErrorResponse(VolleyError error);
    }

}
