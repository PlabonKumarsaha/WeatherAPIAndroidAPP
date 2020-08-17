package com.example.weatherapiandroidapp;

public class WeatherReportModel {
     private  int id;
     private String weatherStateName;
     private String weatherStateAbbr;
     private String windDirectionCompas;
     private String created;
     private String applicable_date;
     private float min_temp;
     private float max_temp;
     private float the_temp;
     private float wind_speed;
     private float wind_direction;
     private int air_pressure;
     private int humidity;
     private float visbility;
     private int preidctibility;

    public WeatherReportModel(int id, String weatherStateName, String weatherStateAbbr, String windDirectionCompas, String created, String applicable_date, float min_temp, float max_temp, float the_temp, float wind_speed, float wind_direction, int air_pressure, int humidity, float visbility, int preidctibility) {
        this.id = id;
        this.weatherStateName = weatherStateName;
        this.weatherStateAbbr = weatherStateAbbr;
        this.windDirectionCompas = windDirectionCompas;
        this.created = created;
        this.applicable_date = applicable_date;
        this.min_temp = min_temp;
        this.max_temp = max_temp;
        this.the_temp = the_temp;
        this.wind_speed = wind_speed;
        this.wind_direction = wind_direction;
        this.air_pressure = air_pressure;
        this.humidity = humidity;
        this.visbility = visbility;
        this.preidctibility = preidctibility;
    }

    @Override
    public String toString() {
        return "WeatherReportModel{" +
                "id=" + id +
                ", weatherStateName='" + weatherStateName + '\'' +
                ", weatherStateAbbr='" + weatherStateAbbr + '\'' +
                ", windDirectionCompas='" + windDirectionCompas + '\'' +
                ", created='" + created + '\'' +
                ", applicable_date='" + applicable_date + '\'' +
                ", min_temp=" + min_temp +
                ", max_temp=" + max_temp +
                ", the_temp=" + the_temp +
                ", wind_speed=" + wind_speed +
                ", wind_direction=" + wind_direction +
                ", air_pressure=" + air_pressure +
                ", humidity=" + humidity +
                ", visbility=" + visbility +
                ", preidctibility=" + preidctibility +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWeatherStateName() {
        return weatherStateName;
    }

    public void setWeatherStateName(String weatherStateName) {
        this.weatherStateName = weatherStateName;
    }

    public String getWeatherStateAbbr() {
        return weatherStateAbbr;
    }

    public void setWeatherStateAbbr(String weatherStateAbbr) {
        this.weatherStateAbbr = weatherStateAbbr;
    }

    public String getWindDirectionCompas() {
        return windDirectionCompas;
    }

    public void setWindDirectionCompas(String windDirectionCompas) {
        this.windDirectionCompas = windDirectionCompas;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getApplicable_date() {
        return applicable_date;
    }

    public void setApplicable_date(String applicable_date) {
        this.applicable_date = applicable_date;
    }

    public float getMin_temp() {
        return min_temp;
    }

    public void setMin_temp(float min_temp) {
        this.min_temp = min_temp;
    }

    public float getMax_temp() {
        return max_temp;
    }

    public void setMax_temp(float max_temp) {
        this.max_temp = max_temp;
    }

    public float getThe_temp() {
        return the_temp;
    }

    public void setThe_temp(float the_temp) {
        this.the_temp = the_temp;
    }

    public float getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(float wind_speed) {
        this.wind_speed = wind_speed;
    }

    public float getWind_direction() {
        return wind_direction;
    }

    public void setWind_direction(float wind_direction) {
        this.wind_direction = wind_direction;
    }

    public int getAir_pressure() {
        return air_pressure;
    }

    public void setAir_pressure(int air_pressure) {
        this.air_pressure = air_pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public float getVisbility() {
        return visbility;
    }

    public void setVisbility(float visbility) {
        this.visbility = visbility;
    }

    public int getPreidctibility() {
        return preidctibility;
    }

    public void setPreidctibility(int preidctibility) {
        this.preidctibility = preidctibility;
    }
}


