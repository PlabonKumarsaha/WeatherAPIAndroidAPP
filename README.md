Rest APi - application program interface .Phones,PC, watches reply on the service on the internet.
- looks at specific URL.insted of html page ,it rsponds as data form.
- JSON(Java script object notation) data in REST API. 
- SOAP -> XML
-client initiates the request 

## 
- uses opensource api
- asynchornous code - call backs. It is a "non blocking" code. which means system keeps processing 
while the system fetches network data. So there will be no freez up and the app doesn't stop suddenly!

-Interface thread 
- Volly and Reftrofit 

## We did : 
-use voly library
-parse JSON data feed
- Asynchornous method with call back!

### Process :
detils : https://developer.android.com/training/volley <br>
1. Made three Button named btn_getCityId,btn_getWeatherByCityId,btn_getWeatherByCityName.
2. Volley is an HTTP library that makes networking for Android apps easier and most importantly, faster(then the other network operation)
3. Add 'implementation 'com.android.volley:volley:1.1.1'' to the dependenyc library and sync.
4. First try Send a simple request for volly :

    1.Add the INTERNET permission <br>
    2. copy the first chunk of code from "https://developer.android.com/training/volley/simple" <br>
    3. then change the context type in RequestQueue queue = Volley.newRequestQueue(MainActivity.this); <br>
    4.chnage the string URL to "https://www.metaweather.com/api/location/search/?query=london" <br>
    5.All the String requestes will be added in queue.
5. Now we will be changing the StringRequest to JSONArray Object format request.
ex : a JSON object  <br>
[{"title":"London", <br>
"location_type":"City", <br>
"woeid":44418, <br>
"latt_long":"51.506321, -0.12714"}]  ..here {}- json object and [] -JSON array <br>


 Advantage of this is that the array can be pieced. <br>
The last statement means that the obbject can be divided and a selective part of that object can also be retireved <br>
                            JSONObject cityInfo =response.getJSONObject(0); //takes the first object from the given URL <br>
                            String cityId = cityInfo.getString("woeid"); //only takes the id part from the whole afframetioned object <br>
                            Toast.makeText(getApplicationContext(),"City Id "+cityId,Toast.LENGTH_SHORT).show();


6.take this snnipet :  JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray> and implement the method.
see documentation (Transmit network data : Make a satndard Request): https://developer.android.com/training/volley/request

7.Next chnage the URL a bit like the following code sniipet : String url ="https://www.metaweather.com/api/location/search/?query="+ET_dataInput.getText().toString();.
Here in the Edit text you can enter your city name to get your city 'woeid' toasted!

8. We can modify the process using a singleton(a class that only have one instance). It will help to limit the request to go through one request queue.

ex : RequestQueue queue = Volley.newRequestQueue(MainActivity.this). This line is here and at the end we have this  queue.add(request); . We want to confind the request
only to one. So have to set up this request queue. <br>

           1.first make a 'MySingleton' class and copy the code ,MySingleton from 'https://developer.android.com/training/volley/requestqueue' and paste it.<br>
           2.comment out the exiting queue and make a nother queue.
           3. Add either of this two ways and to get the singleTon class insatnces
             // Get a RequestQueue
             RequestQueue queue = MySingleton.getInstance(this.getApplicationContext()).
             getRequestQueue();
             // ...
             // Add a request (in this example, called stringRequest) to your RequestQueue.
             MySingleton.getInstance(this).addToRequestQueue(stringRequest);
             
             
9. We can make this whole process easier by making a class name WeatherDataService. public String getCityID(String cityName )method will take the pervious codes that were used in btn click.
Now the btn_click will call the instance of that class and function will return the ID of the city.

10. this Toast.makeText(getApplicationContext(),cityId.toString(),Toast.LENGTH_SHORT).show();.Msg  returns cityId in the MainActivity class but retruns nothing in the CityID in WeatherDataService class.
The toast of WeatherDataService , class didn't work beacuse it is a backgorund process. Volly library keeps it in a queue in the background.Beacuse it needs couple of seconds to load the data.
We don't want to freez the UI in that meantime.So the process is in the background. So to solve this we willbe using 'CallBack'.(callback :A way to scedule a methodcall when another method finishes it's task)



11. We will now try to make the code async :
In the WeatherDataService class we take na interfce as such :
public interface volleyResponseListener{
        void onError(String message);
        void onResponse(String cityId); //kept similarity with the return type of gerCityID
    }

Next, take the interface object as parameter like this : public void  getCityID(String cityName,VolleyResponseListener volleyResponseListener).
Now in the Main Class  weatherDataService.getCityID(ET_dataInput.getText().toString(), new WeatherDataService.VolleyResponseListener()  add VolyResponse listener(which is a callback method) in the BtnClick listner.


12.Now let's work on getCityForcastById(String cityId) method. the following snnipet was donein this method .

 public void getCityForcastById(String cityId){
        final List<WeatherReportModel>report = new ArrayList<>();
        String url = QUERY_FOR_CITY_WEATHER_BY_ID + cityId;

        //get JSON onject
       final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
           @Override
           public void onResponse(JSONObject response) {

               Toast.makeText(context,report.toString(),Toast.LENGTH_SHORT).show();
           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {

           }
       });

        MySingleton.getInstance(context).addToRequestQueue(request);
        } //this toasts the "consolidated_weather" weather data as toasts


13.Now to get a first day report from the api(single array position from the array )  we have ti create a  JSONArray consoladated_weather_list =  response.getJSONArray("consolidated_weather"); and a WeatherRepostModel class 
instance(WeatherReportModel first_day ).

next do this to set all the values. ex :

  WeatherReportModel one_day_weather = new WeatherReportModel();
 JSONObject first_day_fromAPI = (JSONObject) consoladated_weather_list.get(0);

                   first_day.setId(first_day_fromAPI.getInt("id"));
                   first_day.setWeatherStateName(first_day_fromAPI.getString("weather_state_name"));
                   first_day.setWeatherStateAbbr(first_day_fromAPI.getString("weather_state_abbr"));
                   first_day.setWindDirectionCompas(first_day_fromAPI.getString("wind_direction_compass"));
                   first_day.setCreated(first_day_fromAPI.getString("created"));
                   first_day.setApplicable_date(first_day_fromAPI.getString("applicable_date"));
                   first_day.setMin_temp((float) first_day_fromAPI.getDouble("min_temp"));
                   first_day.setMax_temp((float) first_day_fromAPI.getDouble("max_temp"));
                   first_day.setThe_temp((float) first_day_fromAPI.getDouble("the_temp"));
                   first_day.setWind_speed((float) first_day_fromAPI.getDouble("wind_speed"));
                   first_day.setWind_direction((float) first_day_fromAPI.getDouble("wind_direction"));
                   first_day.setAir_pressure(first_day_fromAPI.getInt("air_pressure"));
                   first_day.setHumidity(first_day_fromAPI.getInt("humidity"));
                   first_day.setVisbility((float) first_day_fromAPI.getDouble("visibility"));
                   first_day.setPreidctibility( first_day_fromAPI.getInt("predictability"));

The above code will only work for one day. We can make an itteration to show the list of multiple days in a list.The list will be activated with the help of an simple adapter.

14.All the above terms we were getting results by city id .But now we will try to get the result by cityName.
To do that we must first fetch the city id by city name. and then with the city id will help us get city forcast.
getCityForcastById method will call the API  twice..firt it will get id by city name and then in the response. It will
get city forcast by that id name. This all will work on "weather by name" button.



