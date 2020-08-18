package com.example.weatherapiandroidapp;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeatherDataService {

    public static final String weatherAPILink = "https://www.metaweather.com/api/location/search/?query=";
    public static final String QUERY_FOR_CITY_WEATHER_BY_ID = "https://www.metaweather.com/api/location/";

    Context context;
    String cityId;

    public WeatherDataService(Context context) {
        this.context = context;
    }

    //syncing the code
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

 //to sync the code
    public interface ForecastById{
        void onError(String message);
        void onResponse(List<WeatherReportModel>weatherReportModelList );
    }

    public void getCityForcastById(String cityId, final ForecastById forecastById){
        final List<WeatherReportModel>weatherReportModels = new ArrayList<>();
        String url = QUERY_FOR_CITY_WEATHER_BY_ID + cityId;

        //get JSON onject
       final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
           @Override
           public void onResponse(JSONObject response) {
                //toast was for testing
               //Toast.makeText(context,report.toString(),Toast.LENGTH_SHORT).show();
               try {

                   //crated a JSOnArray to gee the JSON object
                   JSONArray consoladated_weather_list =  response.getJSONArray("consolidated_weather");



                   for(int i = 0;i<consoladated_weather_list.length();i++) {

                       WeatherReportModel one_day_weather = new WeatherReportModel();

                       JSONObject first_day_fromAPI = (JSONObject) consoladated_weather_list.get(i);

                       one_day_weather.setId(first_day_fromAPI.getInt("id"));
                       one_day_weather.setWeatherStateName(first_day_fromAPI.getString("weather_state_name"));
                       one_day_weather.setWeatherStateAbbr(first_day_fromAPI.getString("weather_state_abbr"));
                       one_day_weather.setWindDirectionCompas(first_day_fromAPI.getString("wind_direction_compass"));
                       one_day_weather.setCreated(first_day_fromAPI.getString("created"));
                       one_day_weather.setApplicable_date(first_day_fromAPI.getString("applicable_date"));
                       one_day_weather.setMin_temp((float) first_day_fromAPI.getDouble("min_temp"));
                       one_day_weather.setMax_temp((float) first_day_fromAPI.getDouble("max_temp"));
                       one_day_weather.setThe_temp((float) first_day_fromAPI.getDouble("the_temp"));
                       one_day_weather.setWind_speed((float) first_day_fromAPI.getDouble("wind_speed"));
                       one_day_weather.setWind_direction((float) first_day_fromAPI.getDouble("wind_direction"));
                       one_day_weather.setAir_pressure(first_day_fromAPI.getInt("air_pressure"));
                       one_day_weather.setHumidity(first_day_fromAPI.getInt("humidity"));
                       one_day_weather.setVisbility((float) first_day_fromAPI.getDouble("visibility"));
                       one_day_weather.setPreidctibility(first_day_fromAPI.getInt("predictability"));
                       weatherReportModels.add(one_day_weather);
                   }


                       forecastById.onResponse(weatherReportModels);

               } catch (JSONException e) {
                   e.printStackTrace();
               }

           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {

           }
       });

        MySingleton.getInstance(context).addToRequestQueue(request);
        }

        //to sync the code
        public interface CityForcastByNameCallback{
            void onError(String message);
            void onResponse(List<WeatherReportModel>weatherReportModelList );
        }

    public void getCityForcastByName(String cityName,final CityForcastByNameCallback cityForcastByNameCallback){


        getCityID(cityName, new VolleyResponseListener() {
            @Override
            public void onError(String message) {

            }

            @Override
            public void onResponse(String cityId) {
                   //now we have tech city id
                getCityForcastById(cityId, new ForecastById() {
                    @Override
                    public void onError(String message) {

                    }

                    @Override
                    public void onResponse(List<WeatherReportModel> weatherReportModelList) {

                        cityForcastByNameCallback.onResponse(weatherReportModelList);
                    }
                });

            }
        });
        

    }




}
