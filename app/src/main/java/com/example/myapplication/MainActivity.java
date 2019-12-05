package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabLayout tabLayout = findViewById(R.id.xix);
        fragments.add(new fragment_1());
        fragments.add(new fragment_2());
        fragments.add(new fragment_3());
        String[] a = {"呼入电话", "呼出电话", "未接来电"};
        ViewPager viewPager = findViewById(R.id.viewpager);
        ViewPagerAdator viewPagerAdator = new ViewPagerAdator(getSupportFragmentManager(), fragments, a);
        viewPager.setAdapter(viewPagerAdator);
        tabLayout.setupWithViewPager(viewPager);

    }
}

