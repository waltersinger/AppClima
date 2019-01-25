package com.wsinger.appclima.data;

import com.wsinger.appclima.model.Forecast;

import java.util.ArrayList;

public interface ForecastAsyncListResponse {
    void processFinished(ArrayList<Forecast> forecastArrayList);
}
