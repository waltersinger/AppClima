package com.wsinger.appclima;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.wsinger.appclima.data.ForecastAsyncListResponse;
import com.wsinger.appclima.data.ForecastData;
import com.wsinger.appclima.data.ForecastFragmentAdapter;
import com.wsinger.appclima.model.Forecast;
import com.wsinger.appclima.view.ForecastFragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import android.support.v4.app.Fragment;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ForecastFragmentAdapter adapterViewPager;
    ViewPager viewPager;

    TextView currentDateText,locationText,currentTempText,currentInformationTextv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPagerId);
        adapterViewPager = new ForecastFragmentAdapter(getSupportFragmentManager(), getFragmentList());

        viewPager.setAdapter(adapterViewPager);

        currentDateText =findViewById(R.id.currentDateText);
        locationText = findViewById(R.id.locationTextView);
        currentTempText = findViewById(R.id.currentTempTextView);
        currentInformationTextv = findViewById(R.id.current_information_textv);

    }

    private List<Fragment> getFragmentList(){
       final List<Fragment> fragmentList = new ArrayList<>();

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
        });

        return fragmentList;

    }
}
