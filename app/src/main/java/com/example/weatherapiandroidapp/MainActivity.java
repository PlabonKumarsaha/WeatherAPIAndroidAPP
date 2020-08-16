package com.example.weatherapiandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button btn_getCityId,btn_getWeatherByCityId,btn_getWeatherByCityName;
    EditText ET_dataInput;
    ListView lv_weatherReports;
    WeatherDataService weatherDataService = new WeatherDataService(MainActivity.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv_weatherReports = findViewById(R.id.lv_weatherReports);
        btn_getCityId = findViewById(R.id.btn_getCityId);
        btn_getWeatherByCityId = findViewById(R.id.btn_getWeatherByCityId);
        btn_getWeatherByCityName = findViewById(R.id.btn_getWeatherByCityName);
        ET_dataInput = findViewById(R.id.ET_dataInput);

        btn_getCityId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


         //add the input text and volly listener
         weatherDataService.getCityID(ET_dataInput.getText().toString(), new WeatherDataService.VolleyResponseListener() {
             @Override
             public void onError(String message) {
                 Toast.makeText(getApplicationContext(),message.toString(),Toast.LENGTH_SHORT).show();

             }

             @Override
             public void onResponse(String cityId) {
                 Toast.makeText(getApplicationContext(),cityId.toString(),Toast.LENGTH_SHORT).show();

             }
         });
         //this does not return anything

            }
        });

        btn_getWeatherByCityId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btn_getWeatherByCityName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}