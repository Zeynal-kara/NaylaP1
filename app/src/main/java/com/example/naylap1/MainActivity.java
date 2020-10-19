package com.example.naylap1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.naylap1.fragment.CategoryFragment;
import com.example.naylap1.fragment.InboxFragment;
import com.example.naylap1.fragment.ProfileFragment;
import com.example.naylap1.fragment.SearchFragment;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity {

    ChipNavigationBar bottomNav;
    FragmentManager fragmentManager;
    TextView tvPageView;
    ImageView imgPageIcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNav = findViewById(R.id.botton_nav);
        tvPageView = findViewById(R.id.page_title);
        imgPageIcon = findViewById(R.id.img_page_icon);



        setFragment(new ProfileFragment(),
                getString(R.string.bottom_nav_profile_text), R.drawable.edit);

        bottomNav.setItemSelected(R.id.nav_profile, true);

        bottomNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onItemSelected(int i) {

                switch (i){
                    case R.id.nav_category:
                        setFragment(new CategoryFragment(),
                                getString(R.string.bottom_nav_category_text), R.drawable.feather_search);
                        break;
                    case R.id.nav_search:
                        setFragment(new SearchFragment(),
                                getString(R.string.bottom_nav_search_text), R.drawable.feather_search);
                        break;
                    case R.id.nav_message:
                        setFragment(new InboxFragment(),
                                getString(R.string.bottom_nav_inbox_text), R.drawable.feather_search);
                        bottomNav.dismissBadge(R.id.nav_message);
                        break;
                    case R.id.nav_profile:
                        setFragment(new ProfileFragment(),
                                getString(R.string.bottom_nav_profile_text), R.drawable.edit);
                        break;
                }



            }
        });

        bottomNav.showBadge(R.id.nav_message, 1);

    }

    private void setFragment(Fragment fragment, String title, int drawableResId){

        tvPageView.setText(title);
        imgPageIcon.setImageResource(drawableResId);

        if (fragment != null){
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();

        }
    }
}
