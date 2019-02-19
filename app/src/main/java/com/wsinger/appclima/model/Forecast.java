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
    private int currCodeCondition;
    private int currHumidity;
    private String sunrise;
    private String sunset;

    //under forecast object
    private String forecastDay;
    private String forecastDate;
    private String forecastLowTemperature;
    private String forecastHighTemperature;
    private String forecastWeatherDescription;
    private int forecastCodeWeather;

    public int getCurrHumidity() {
        return currHumidity;
    }

    public void setCurrHumidity(int currHumidity) {
        this.currHumidity = currHumidity;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public int getCurrCodeCondition() {
        return currCodeCondition;
    }

    public void setCurrCodeCondition(int currCodeCondition) {
        this.currCodeCondition = currCodeCondition;
    }

    public int getForecastCodeWeather() {
        return forecastCodeWeather;
    }

    public void setForecastCodeWeather(int forecastCodeWeather) {
        this.forecastCodeWeather = forecastCodeWeather;
    }

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
