package com.beidou.bd601.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.beidou.bd601.activity.MainActivity;


/**
 * Created by wangkuan on 2016/9/28 .
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private final int PAGER_COUNT = 4;
    private HomeFragment homeFragment = null;
    private LocationFragment locationFragment = null;
    private CommFragment commFragment = null;
    private UploadLocFragment uploadLocFragment = null;


    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        homeFragment = new HomeFragment();
        locationFragment = new LocationFragment();
        commFragment = new CommFragment();
        uploadLocFragment = new UploadLocFragment();
    }


    @Override
    public int getCount() {
        return PAGER_COUNT;
    }

    @Override
    public Object instantiateItem(ViewGroup vg, int position) {
        return super.instantiateItem(vg, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case MainActivity.PAGE_HOME:
                fragment = homeFragment;
                break;
            case MainActivity.PAGE_LOCATION:
                fragment = locationFragment;
                break;
            case MainActivity.PAGE_COMM:
                fragment = commFragment;
                break;
            case MainActivity.PAGE_UPLOADLOC:
                fragment = uploadLocFragment;
                break;
        }
        return fragment;
    }


}

