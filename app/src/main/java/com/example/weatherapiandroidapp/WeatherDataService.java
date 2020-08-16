package com.example.weatherapiandroidapp;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherDataService {

    public static final String weatherAPILink = "https://www.metaweather.com/api/location/search/?query=";
    Context context;
    String cityId;

    public WeatherDataService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener{
        void onError(String message);
        void onResponse(String cityId);
    }

    public void getCityID(String cityName, final VolleyResponseListener volleyResponseListener ){

        // Instantiate the RequestQueue. we are using queue bcz  it will keep the oncomming request in a queue
        // RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        RequestQueue requestQueue;
        String url =weatherAPILink+cityName;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try {
                    JSONObject cityInfo =response.getJSONObject(0);
                     cityId = cityInfo.getString("woeid");
                  //  Toast.makeText(context,"City Id "+cityId,Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(context,response.toString(),Toast.LENGTH_SHORT).show();
                //added the volly response here
                volleyResponseListener.onResponse(cityId);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,error.toString(),Toast.LENGTH_SHORT).show();
                //add volly error here
                volleyResponseListener.onError("Something is wrong!");

            }
        });
        // Add the request to the RequestQueue.
        // queue.add(request);
        // Add a request (in this example, called stringRequest) to your RequestQueue.
        MySingleton.getInstance(context).addToRequestQueue(request);
        //return  cityId;


    }

/*
    public List<WeatherReportModel>getCityForcastById(String cityId){

    }

    public List<WeatherReportModel>getCityForcastById(String cityName){

    }
*/



}
