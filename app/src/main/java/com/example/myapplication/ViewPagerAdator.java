package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class ViewPagerAdator extends FragmentPagerAdapter {
    List<Fragment> fragments ;
    private String []tabName;
    public ViewPagerAdator(@NonNull FragmentManager fm,List<Fragment> fragments,String []tabName) {
        super(fm);
        this.fragments = fragments;
        this.tabName = tabName;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }
    @Override
    public int getCount() {
        return fragments.size();
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabName[position];
    }
}
