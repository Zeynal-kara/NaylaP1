package com.example.naylap1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.naylap1.adapter.HomePagerAdapter;
import com.example.naylap1.adapter.HomePagerAdapter.PAGE;
import com.example.naylap1.model.User;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity
        implements ChipNavigationBar.OnItemSelectedListener{

    ChipNavigationBar bottomNav;
    FragmentManager fragmentManager;
    TextView tvPageView;
    ImageView imgPageIcon;

    FragmentStateAdapter adapterViewPager;
    ViewPager2 vpPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNav = findViewById(R.id.botton_nav);
        tvPageView = findViewById(R.id.page_title);
        imgPageIcon = findViewById(R.id.img_page_icon);

        vpPager = (ViewPager2) findViewById(R.id.vp_pager);
        adapterViewPager = new HomePagerAdapter(this);
        vpPager.setAdapter(adapterViewPager);
        vpPager.setOffscreenPageLimit(2);
        vpPager.setUserInputEnabled(false);



        bottomNav.setOnItemSelectedListener(this);

        bottomNav.setItemSelected(R.id.nav_category, true);

        int unReadMessageCount = User.getInstance().getMessageManager().getUnreadCount();
        if (unReadMessageCount > 0){
            bottomNav.showBadge(R.id.nav_inbox, unReadMessageCount);
        }

    }

    private void showNavPage(PAGE page, String title, int drawableResId){

        tvPageView.setText(title);
        imgPageIcon.setImageResource(drawableResId);
        vpPager.setCurrentItem(page.ordinal(), false);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onItemSelected(int i) {

        switch (i){
            case R.id.nav_category:
                showNavPage(PAGE.CATEGORY,
                        getString(R.string.bottom_nav_category_text), R.drawable.feather_search);
                break;
            case R.id.nav_search:
                showNavPage(PAGE.SEARCH,
                        getString(R.string.bottom_nav_search_text), R.drawable.feather_search);
                break;
            case R.id.nav_inbox:
                showNavPage(PAGE.INBOX,
                        getString(R.string.bottom_nav_inbox_text), R.drawable.feather_search);
                bottomNav.dismissBadge(R.id.nav_inbox);
                break;
            case R.id.nav_profile:
                showNavPage(PAGE.PROFILE,
                        getString(R.string.bottom_nav_profile_text), R.drawable.edit);
                break;
        }



    }
}
