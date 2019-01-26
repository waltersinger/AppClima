package com.wsinger.appclima;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wsinger.appclima.data.ForecastAsyncListResponse;
import com.wsinger.appclima.data.ForecastData;
import com.wsinger.appclima.data.ForecastFragmentAdapter;
import com.wsinger.appclima.model.Forecast;
import com.wsinger.appclima.utils.Preferences;
import com.wsinger.appclima.view.ForecastFragment;

import java.util.ArrayList;
import java.util.List;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ForecastFragmentAdapter adapterViewPager;
    ViewPager viewPager;

    TextView currentDateText,locationText,currentTempText,currentInformationTextv;
    EditText currentLocationText;
    String strLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        currentDateText =findViewById(R.id.currentDateText);
        locationText = findViewById(R.id.locationTextView);
        currentTempText = findViewById(R.id.currentTempTextView);
        currentInformationTextv = findViewById(R.id.current_information_textv);

        currentLocationText = findViewById(R.id.input_location_edit);//es el input

        final Preferences prefs = new Preferences(this);
        currentLocationText.setText(prefs.getLocation() );

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
       final List<Fragment> fragmentList = new ArrayList<>();
       fragmentList.clear();

        new ForecastData().getForecastFromApi(new ForecastAsyncListResponse() {
            @Override
            public void processFinished(ArrayList<Forecast> forecastArrayList) {

                currentDateText.setText(forecastArrayList.get(0).getCurrDate());
                locationText.setText(forecastArrayList.get(0).getCity()+", "+forecastArrayList.get(0).getRegion());
                currentTempText.setText(forecastArrayList.get(0).getCurrTemperature()+"º C");
                currentInformationTextv.setText(forecastArrayList.get(0).getCurrWeatherDescription());


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
