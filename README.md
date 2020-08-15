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
