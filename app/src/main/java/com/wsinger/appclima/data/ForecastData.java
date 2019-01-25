package com.wsinger.appclima.data;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.wsinger.appclima.controller.AppController;
import com.wsinger.appclima.model.Forecast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

public class ForecastData {
    private ArrayList<Forecast> forecastArrayList = new ArrayList<>();
    private String url ="https://weather-ydn-yql.media.yahoo.com/forecastrss?location=santiago del estero,ar&format=json&u=c";


    public void getForecastFromApi(final ForecastAsyncListResponse callback){
        /*
         * JsonObjectRequest takes in five paramaters
         * Request Type - This specifies the type of the request eg: GET,POST
         *
         * URL - This String param specifies the Request URL
         *
         * JSONObject - This parameter takes in the POST parameters."null" in
         * case of GET request.
         *
         * Listener -This parameter takes in a implementation of Response.Listener()
         * interface which is invoked if the request is successful
         *
         * Listener -This parameter takes in a implementation of Error.Listener()
         * interface which is invoked if any error is encountered while processing
         * the request
         */
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

//                    Forecast forecast = new Forecast();
                    //location
                    JSONObject location = response.getJSONObject("location");
//                    forecast.setCity(location.getString("city"));
//                    forecast.setRegion(location.getString("region"));
//                    forecast.setCountry(location.getString("country"));
//
                    //condition
                    JSONObject currentObservation = response.getJSONObject("current_observation");
                    JSONObject condition = currentObservation.getJSONObject("condition");
//                    forecast.setCurrWeatherDescription(condition.getString("text"));
//                    forecast.setCurrTemperature(condition.getString("temperature"));
//
//                    long udate = currentObservation.getLong("pubDate");
//                    Date currdate= new Date( udate * 1000);
//                    forecast.setCurrDate(currdate.toString());

                    //forecasts object
                    JSONArray forecastArray = response.getJSONArray("forecasts");
                    //Log.d("forecast", forecastArray.toString());
                    for (int i=0;i< forecastArray.length();i++){
                        JSONObject forecastObject = forecastArray.getJSONObject(i);
                        Forecast forecast = new Forecast();

                        forecast.setForecastDay(forecastObject.getString("day"));
                        Date fd = new Date(forecastObject.getLong("date")*1000);
                        forecast.setForecastDate(fd.toString());

                        forecast.setForecastLowTemperature(forecastObject.getString("low"));
                        forecast.setForecastHighTemperature(forecastObject.getString("high"));
                        forecast.setForecastWeatherDescription(forecastObject.getString("text"));
                        //location
                        forecast.setCity(location.getString("city"));
                        forecast.setRegion(location.getString("region"));
                        forecast.setCountry(location.getString("country"));
                        //condition
                        forecast.setCurrWeatherDescription(condition.getString("text"));
                        forecast.setCurrTemperature(condition.getString("temperature"));
                        long udate = currentObservation.getLong("pubDate");
                        Date currdate= new Date( udate * 1000);
                        forecast.setCurrDate(currdate.toString());

                        forecastArrayList.add(forecast);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(callback != null){
                    callback.processFinished(forecastArrayList);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })

                /** Passing some request headers, main  * */
        {
          @Override
          public Map getHeaders() throws AuthFailureError {
              HashMap headers = new HashMap();
              headers.put("Content-Type","application/json");
              headers.put("Authorization","OAuth realm=\"FtMPwV34\",oauth_consumer_key=\"dj0yJmk9UHpSdEFFYUlUVVpRJnM9Y29uc3VtZXJzZWNyZXQmc3Y9MCZ4PWFm\",oauth_signature_method=\"HMAC-SHA1\",oauth_timestamp=\"1548193112\",oauth_nonce=\"P3Xd22LpHK4\",oauth_version=\"1.0\",oauth_signature=\"HsfsmhXhFOx3uAwV1KX%2BGieF16k%3D\"");
              return headers;
          }
        };

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }
}
