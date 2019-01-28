package com.wsinger.appclima.data;

import android.text.format.DateFormat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.wsinger.appclima.controller.AppController;
import com.wsinger.appclima.model.Forecast;

import net.oauth.OAuth;
import net.oauth.OAuthAccessor;
import net.oauth.OAuthConsumer;
import net.oauth.OAuthException;
import net.oauth.OAuthMessage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

public class ForecastData {
    private ArrayList<Forecast> forecastArrayList = new ArrayList<>();
    private String urlLeft = "https://weather-ydn-yql.media.yahoo.com/forecastrss?location=";
    private String urlRight="&format=json&u=c";


    //private String url ="https://weather-ydn-yql.media.yahoo.com/forecastrss?location=santiago del estero,ar&format=json&u=c";


    public void getForecastFromApi(final ForecastAsyncListResponse callback,String strLocation){
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
        final String url = urlLeft+strLocation+urlRight;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

//                    Forecast forecast = new Forecast();
                    //location
                    JSONObject location = response.getJSONObject("location");

                    //condition
                    JSONObject currentObservation = response.getJSONObject("current_observation");
                    JSONObject condition = currentObservation.getJSONObject("condition");

                    //forecasts object
                    JSONArray forecastArray = response.getJSONArray("forecasts");
                    //Log.d("forecast", forecastArray.toString());
                    for (int i=0;i< forecastArray.length();i++){
                        JSONObject forecastObject = forecastArray.getJSONObject(i);
                        Forecast forecast = new Forecast();

                        forecast.setForecastDay(forecastObject.getString("day"));
                        Date fd = new Date(forecastObject.getLong("date")*1000);
                        forecast.setForecastDate(capitalize( (String) DateFormat.format("EEEE dd 'de' MMMM",fd)));

                        forecast.setForecastLowTemperature(forecastObject.getString("low"));
                        forecast.setForecastHighTemperature(forecastObject.getString("high"));
                        forecast.setForecastWeatherDescription(capitalize(forecastObject.getString("text")));
                        forecast.setForecastCodeWeather(forecastObject.getInt("code"));
                        //location
                        forecast.setCity(capitalize(location.getString("city")));
                        forecast.setRegion(capitalize(location.getString("region")));
                        forecast.setCountry(capitalize(location.getString("country")));
                        //condition
                        forecast.setCurrWeatherDescription(capitalize(condition.getString("text")));
                        forecast.setCurrTemperature(condition.getString("temperature"));
                        forecast.setCurrCodeCondition(condition.getInt("code"));

                        long udate = currentObservation.getLong("pubDate");
                        Date currdate= new Date( udate * 1000);
                        String today = (String) DateFormat.format("EEEE dd 'de' MMMM 'de' yyyy ",currdate);
                        forecast.setCurrDate(capitalize(today));

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
              final String consumerKey = "dj0yJmk9UHpSdEFFYUlUVVpRJnM9Y29uc3VtZXJzZWNyZXQmc3Y9MCZ4PWFm";
              final String consumerSecret="203060a4914c0a7f4cfe4a1807b4747f3d2c45f9";

              //Configuro los objetos para traer el token de autorizacion con mi apikey y mi consumerkey
              OAuthConsumer consumer = new OAuthConsumer(null,consumerKey,consumerSecret,null);
              consumer.setProperty(OAuth.OAUTH_SIGNATURE_METHOD,OAuth.HMAC_SHA1);
              OAuthAccessor accessor = new OAuthAccessor(consumer);

              try {
                  //hago peticion para que me devuelva el token de autorizacion
                  OAuthMessage mrequest = accessor.newRequestMessage(OAuthMessage.GET,url,null);
                  String strAuthorization = mrequest.getAuthorizationHeader(null);
                  headers.put("Authorization",strAuthorization);
              } catch (OAuthException e) {
                  e.printStackTrace();
              } catch (IOException e) {
                  e.printStackTrace();
              } catch (URISyntaxException e) {
                  e.printStackTrace();
              }

              headers.put("Yahoo-App-Id","FtMPwV34");
              headers.put("Content-Type","application/json");
              headers.put("cache-control", "no-cache");
              //headers.put("Authorization", "OAuth realm=\"FtMPwV34\",oauth_consumer_key=\"dj0yJmk9UHpSdEFFYUlUVVpRJnM9Y29uc3VtZXJzZWNyZXQmc3Y9MCZ4PWFm\",oauth_signature_method=\"HMAC-SHA1\",oauth_timestamp=\"1548459856\",oauth_nonce=\"madO6pJmZoP\",oauth_version=\"1.0\",oauth_signature=\"COG%2FCsY8gvawrqCj8BYg8L0Gy3o%3D\"");
              //headers.put("Postman-Token", "c29879dd-b5c0-4a1d-b093-d3b54d10d10e");
              return headers;
          }
        };

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    private String capitalize(String str){

        String strCapitalized = str.substring(0,1).toUpperCase() + str.substring(1);

        return strCapitalized;
    }
}
