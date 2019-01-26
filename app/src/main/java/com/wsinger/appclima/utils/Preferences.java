package com.wsinger.appclima.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {
    SharedPreferences sharedPreferences;

    public Preferences(Activity cntx){
        sharedPreferences = cntx.getPreferences(cntx.MODE_PRIVATE);

    }

    public String getLocation(){
        return sharedPreferences.getString("location","Buenos Aires,AR");
    }

    public void setLocation(String location){
        sharedPreferences.edit().putString("location",location).apply();
    }
}
