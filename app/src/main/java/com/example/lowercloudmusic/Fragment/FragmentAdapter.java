package com.example.lowercloudmusic.Fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class FragmentAdapter extends FragmentPagerAdapter {

    public FragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 1){
            return new LibraryFragment();
        } else if (position == 2){
            return new ProfileFragment();
        }
        return new HomeFragment();
    }

    @Override
    public int getCount() {
        return 3;
    }
}
