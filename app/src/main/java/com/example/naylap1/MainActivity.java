package com.example.naylap1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.naylap1.fragment.CategoriFragment;
import com.example.naylap1.fragment.InboxFragment;
import com.example.naylap1.fragment.ProfileFragment;
import com.example.naylap1.fragment.SearchFragment;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity {

    ChipNavigationBar bottomNav;
    FragmentManager fragmentManager;
    TextView tv_page_view;
    ImageView img_page_icon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNav = findViewById(R.id.botton_nav);
        tv_page_view = findViewById(R.id.page_title);
        img_page_icon = findViewById(R.id.img_page_icon);



        setFragment(new ProfileFragment(), "Profile", R.drawable.edit);

        bottomNav.setItemSelected(R.id.nav_profile, true);

        bottomNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {

                switch (i){
                    case R.id.nav_category:
                        setFragment(new CategoriFragment(), "Categori", R.drawable.feather_search);
                        break;
                    case R.id.nav_search:
                        setFragment(new SearchFragment(), "Search", R.drawable.feather_search);
                        break;
                    case R.id.nav_message:
                        setFragment(new InboxFragment(), "Inbox", R.drawable.feather_search);
                        bottomNav.dismissBadge(R.id.nav_message);
                        break;
                    case R.id.nav_profile:
                        setFragment(new ProfileFragment(), "Profile", R.drawable.edit);
                        break;
                }



            }
        });

        bottomNav.showBadge(R.id.nav_message, 1);

    }

    private void setFragment(Fragment fragment, String title, int drawableResId){

        tv_page_view.setText(title);
        img_page_icon.setImageResource(drawableResId);

        if (fragment != null){
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();

        }
    }
}
