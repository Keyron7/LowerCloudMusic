package com.example.lowercloudmusic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import com.example.lowercloudmusic.Fragment.FragmentAdapter;

public class HomeActivity extends AppCompatActivity {
    private int[] unselectTabRes = new int[]{R.mipmap.flame,R.mipmap.library,R.mipmap.account};
    private int[] selectTabRes = new int[]{R.mipmap.flame1,R.mipmap.library1,R.mipmap.account1};
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//隐藏掉整个ActionBar
        setContentView(R.layout.activity_home);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                for (int i = 0;i < 3;i++){
                    if (tab == tabLayout.getTabAt(i)) {
                        tabLayout.getTabAt(i).setIcon(selectTabRes[i]);
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                for (int i = 0; i < 3; i++) {
                    if (tab == tabLayout.getTabAt(i)) {
                        tabLayout.getTabAt(i).setIcon(unselectTabRes[i]);
                        viewPager.setCurrentItem(i);
                    }
                }


            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initData() {
    }

    private void initView() {
        viewPager = findViewById(R.id.home_viewpager);
        tabLayout = findViewById(R.id.home_tablayout);
        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
            for (int i = 0; i < 3; i++) {
                if (i == 0) {
                    tabLayout.getTabAt(0).setIcon(selectTabRes[0]);
                } else {
                    tabLayout.getTabAt(i).setIcon(unselectTabRes[i]);
                }
            }
        }
    }
