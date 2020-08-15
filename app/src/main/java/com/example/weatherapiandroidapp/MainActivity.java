package com.example.weatherapiandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    Button btn_getCityId,btn_getWeatherByCityId,btn_getWeatherByCityName;
    EditText ET_dataInput;
    ListView lv_weatherReports;

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