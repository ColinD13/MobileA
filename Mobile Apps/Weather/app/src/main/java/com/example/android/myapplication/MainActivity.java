package com.example.android.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public String urlcontent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        geturl();
    }

    public void geturl(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.openweathermap.org/data/2.5/weather?q=New%20York&appid=5a3581d769f0299c5e732b23c333a6f2&units=imperial";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        urlcontent = response;
                        gettext();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                urlcontent = "fail";
            }
        });
        queue.add(stringRequest);
    }

    public void gettext(){
        TextView header = findViewById(R.id.header);
        TextView main = findViewById(R.id.main);
        TextView description = findViewById(R.id.description);
        TextView temp = findViewById(R.id.temp);

        try {
            JSONObject obj = new JSONObject(urlcontent);
            header.setText(obj.get("name").toString());
            main.setText((CharSequence) obj.getJSONArray("weather").getJSONObject(0).get("main"));
            description.setText((CharSequence) obj.getJSONArray("weather").getJSONObject(0).get("description"));
            temp.setText(obj.getJSONObject("main").get("temp").toString() + " degrees");

            ImageView iv = findViewById(R.id.icon);
            String icon = obj.getJSONArray("weather").getJSONObject(0).get("icon").toString();
            String img = "https://openweathermap.org/img/wn/"+icon+"@4x.png";
            Glide.with(this).load(img).into(iv);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}