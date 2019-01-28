package com.wsinger.appclima.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wsinger.appclima.R;
import com.wsinger.appclima.controller.AppController;
import com.wsinger.appclima.model.Forecast;
import com.wsinger.appclima.utils.TranslationsCode;


/**
 * A simple {@link Fragment} subclass.
 */
public class ForecastFragment extends Fragment {


    public ForecastFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View forecastViewF= inflater.inflate(R.layout.fragment_forecast,container,false);

        ImageView forecastIcon = forecastViewF.findViewById(R.id.forecastImageId);
        TextView forecasttvDate = forecastViewF.findViewById(R.id.forecastDateTextv);
        //TextView forecastTemperature = forecastViewF.findViewById(R.id.forecastTempTextv);
        TextView forecastHighTemp = forecastViewF.findViewById(R.id.forecastHighTextv);
        TextView forecastLowTemp = forecastViewF.findViewById(R.id.forecastLowTextv);
        TextView forecastDescription = forecastViewF.findViewById(R.id.forecastDescriptionTextv);

        Forecast forecastObj = (Forecast) getArguments().getSerializable("forecast"); //getParcelable("forecast"); //getSerializable

        //forecastTemperature.setText(forecastObj.getCurrTemperature());
        forecasttvDate.setText(forecastObj.getForecastDate());
        forecastHighTemp.setText(forecastObj.getForecastHighTemperature());
        forecastLowTemp.setText(forecastObj.getForecastLowTemperature() );

        TranslationsCode translationsCode = AppController.getInstance().getTrasnlationManager();
        //forecastDescription.setText(forecastObj.getForecastWeatherDescription());
        forecastDescription.setText(translationsCode.getSpanish(forecastObj.getForecastCodeWeather()));


        return forecastViewF;
    }

    //en lugar de estar creando con new a cada rato en cualquier lado, se llama a esta funcion estatica
    public static final ForecastFragment newInstance(Forecast forecast){
        ForecastFragment forecastFragment = new ForecastFragment();
        Bundle bundle =new Bundle();
        //Serializable y parcelable son interfaces para guardar objetos (persite objetos) en lugar de putString etc... se pone o putserializable o putparce.
        //supuestamente parcelable es mas eficiente y mas rapid de recuperar que serializable
        bundle.putSerializable("forecast",  forecast); //putParcelable("forecast",(Parcelable) forecast); //putSerializable("forecast", (Serializable) forecast);

        forecastFragment.setArguments(bundle);

        return forecastFragment;
    }


}
