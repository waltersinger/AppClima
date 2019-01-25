package com.wsinger.appclima.data;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;




import java.util.List;

public class ForecastFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> forecastArrayFragment;


    public ForecastFragmentAdapter(FragmentManager fm, List<Fragment> forecastFragments) {
        super(fm);
        this.forecastArrayFragment = forecastFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return this.forecastArrayFragment.get(position);
    }

    @Override
    public int getCount() {
        return this.forecastArrayFragment.size();
    }
}
