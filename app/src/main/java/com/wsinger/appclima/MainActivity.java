package com.wsinger.appclima;

import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.squareup.picasso.Picasso;
import com.wsinger.appclima.controller.AppController;
import com.wsinger.appclima.data.ForecastAsyncListResponse;
import com.wsinger.appclima.data.ForecastBackgroundImage;
import com.wsinger.appclima.data.ForecastData;
import com.wsinger.appclima.data.ForecastFragmentAdapter;
import com.wsinger.appclima.model.Forecast;
import com.wsinger.appclima.utils.Preferences;
import com.wsinger.appclima.utils.TranslationsCode;
import com.wsinger.appclima.view.ForecastFragment;

import java.util.ArrayList;
import java.util.List;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ForecastFragmentAdapter adapterViewPager;
    ViewPager viewPager;

    TextView currentDateText,locationText,currentTempText,currentInformationTextv,currHumidity,sunsetText,sunriseText;
    EditText currentLocationText;
    String strLocation;

    ImageView imageCurrWeather;
    //ImageView imageViewBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //imageViewBackground = findViewById(R.id.image_view_background);
        imageCurrWeather = findViewById(R.id.current_image_id);
        currentDateText =findViewById(R.id.currentDateText);
        locationText = findViewById(R.id.locationTextView);
        currentTempText = findViewById(R.id.currentTempTextView);
        currentInformationTextv = findViewById(R.id.current_information_textv);
        currHumidity = findViewById(R.id.humidity_textview);
        sunsetText =findViewById(R.id.sunset_textview);
        sunriseText = findViewById(R.id.sunrise_textview);


        currentLocationText = findViewById(R.id.input_location_edit);//es el input

        final Preferences prefs = new Preferences(this);
        currentLocationText.setText(prefs.getLocation() );

        //--obtengo imagen de fondo de flckr
        ConstraintLayout layout = findViewById(R.id.background_app);

        //layout.setBackground()
        new ForecastBackgroundImage(layout,this).execute();


        getWeather(currentLocationText.getText().toString().trim());

        currentLocationText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if((keyCode == KeyEvent.KEYCODE_ENTER) && (keyEvent.getAction() == KeyEvent.ACTION_DOWN) ){

                    strLocation = currentLocationText.getText().toString().trim();
                    getWeather(strLocation);
                    prefs.setLocation(strLocation);

                    return true;
                }
                return false;
            }
        });

    }


    private void getWeather(String l){
        adapterViewPager = new ForecastFragmentAdapter(getSupportFragmentManager(), getFragmentList(l));
        viewPager = findViewById(R.id.viewPagerId);
        viewPager.setAdapter(adapterViewPager);

    }

    private List<Fragment> getFragmentList(String strLocation){
       final TranslationsCode translationsCode = AppController.getInstance().getTrasnlationManager();
       final List<Fragment> fragmentList = new ArrayList<>();
       fragmentList.clear();

        new ForecastData().getForecastFromApi(new ForecastAsyncListResponse() {
            @Override
            public void processFinished(ArrayList<Forecast> forecastArrayList) {

                currentDateText.setText(forecastArrayList.get(0).getCurrDate());
                locationText.setText(forecastArrayList.get(0).getCity()+", "+forecastArrayList.get(0).getRegion());
                currentTempText.setText(forecastArrayList.get(0).getCurrTemperature()+"ºC");
                currHumidity.setText( String.valueOf(forecastArrayList.get(0).getCurrHumidity()));
                sunsetText.setText(forecastArrayList.get(0).getSunset());
                sunriseText.setText(forecastArrayList.get(0).getSunrise());
                //currentInformationTextv.setText(forecastArrayList.get(0).getCurrWeatherDescription());

                currentInformationTextv.setText( translationsCode.getSpanish(forecastArrayList.get(0).getCurrCodeCondition()));

                //Load the image
                String urlImage = "http://l.yimg.com/a/i/us/we/52/"+forecastArrayList.get(0).getCurrCodeCondition()+".gif";
                Picasso.get().load(urlImage).into(imageCurrWeather);


                for (int i = 0;i< forecastArrayList.size();i++){
                    //Log.d("forecast", forecastArrayList.get(i).getForecastDay());
                    ForecastFragment forecastFragment = new ForecastFragment().newInstance(forecastArrayList.get(i));

                    fragmentList.add(forecastFragment);
                }

                adapterViewPager.notifyDataSetChanged();
            }
        },strLocation);

        return fragmentList;

    }
}
