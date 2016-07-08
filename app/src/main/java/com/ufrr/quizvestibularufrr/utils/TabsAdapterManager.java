package com.ufrr.quizvestibularufrr.utils;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ufrr.quizvestibularufrr.FragmentTab1;
import com.ufrr.quizvestibularufrr.FragmentTab2;
import com.ufrr.quizvestibularufrr.R;

public class TabsAdapterManager extends FragmentStatePagerAdapter {

    private Context mContext;
    private String Tab1, Tab2;
    private int heightIcon;
    private String[] titles = {"INÍCIO", "RANK"};

    public TabsAdapterManager(Context context, FragmentManager fm) {
        super(fm);
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return FragmentTab1.newInstance();
            default:
                return FragmentTab2.newInstance();
        }
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        Tab1 = mContext.getResources().getString(R.string.Tab1);
        Tab2 = mContext.getResources().getString(R.string.Tab2);
        return ( titles[position] );

    }
}
