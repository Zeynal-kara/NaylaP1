package com.example.naylap1.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.naylap1.fragment.CategoryFragment;
import com.example.naylap1.fragment.InboxFragment;
import com.example.naylap1.fragment.ProfileFragment;
import com.example.naylap1.fragment.SearchFragment;

public class HomePagerAdapter extends FragmentStateAdapter {

    public enum PAGE{
        CATEGORY,
        SEARCH,
        INBOX,
        PROFILE
    }

    public HomePagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        PAGE page = PAGE.values()[position];
        switch (page) {
            case CATEGORY: // Fragment # 0 - This will show FirstFragment
                return CategoryFragment.newInstance();
            case SEARCH:
                return SearchFragment.newInstance();
            case INBOX:
                return InboxFragment.newInstance();
            case PROFILE:
                return ProfileFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return PAGE.values().length;
    }



}

