package com.example.etoo.guardiannews;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;

    public ViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new NewsFragment();
        } else if (position == 1) {
            return new ArtsFragment();
        } else if (position == 2) {
            return new LifestyleFragment();
        } else {
            return new SportsFragment();
        }
    }

    @Override
    public int getCount() {
        int tabAmount = 5;
        return tabAmount;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getResources().getString(R.string.news);
        } else if (position == 1) {
            return mContext.getResources().getString(R.string.arts);
        } else if (position == 2) {
            return mContext.getResources().getString(R.string.lifestyle);
        } else {
            return mContext.getResources().getString(R.string.sports);
        }
    }

}

