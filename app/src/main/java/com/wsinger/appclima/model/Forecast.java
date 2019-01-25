package com.wsinger.appclima.model;

import java.io.Serializable;

public class Forecast implements Serializable {
    //Location
    private String city;
    private String region;
    private String country;

    //condition json object
    private String currWeatherDescription;
    private String currTemperature;
    private String currDate;

    //under forecast object
    private String forecastDay;
    private String forecastDate;
    private String forecastLowTemperature;
    private String forecastHighTemperature;
    private String forecastWeatherDescription;



    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrWeatherDescription() {
        return currWeatherDescription;
    }

    public void setCurrWeatherDescription(String currWeatherDescription) {
        this.currWeatherDescription = currWeatherDescription;
    }

    public String getCurrTemperature() {
        return currTemperature;
    }

    public void setCurrTemperature(String currTemperature) {
        this.currTemperature = currTemperature;
    }

    public String getCurrDate() {
        return currDate;
    }

    public void setCurrDate(String currDate) {
        this.currDate = currDate;
    }

    public String getForecastDay() {
        return forecastDay;
    }

    public void setForecastDay(String forecastDay) {
        this.forecastDay = forecastDay;
    }

    public String getForecastDate() {
        return forecastDate;
    }

    public void setForecastDate(String forecastDate) {
        this.forecastDate = forecastDate;
    }

    public String getForecastLowTemperature() {
        return forecastLowTemperature;
    }

    public void setForecastLowTemperature(String forecastLowTemperature) {
        this.forecastLowTemperature = forecastLowTemperature;
    }

    public String getForecastHighTemperature() {
        return forecastHighTemperature;
    }

    public void setForecastHighTemperature(String forecastHighTemperature) {
        this.forecastHighTemperature = forecastHighTemperature;
    }

    public String getForecastWeatherDescription() {
        return forecastWeatherDescription;
    }

    public void setForecastWeatherDescription(String forecastWeatherDescription) {
        this.forecastWeatherDescription = forecastWeatherDescription;
    }
}
