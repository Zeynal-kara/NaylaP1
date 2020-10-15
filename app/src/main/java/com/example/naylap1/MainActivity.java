package com.example.naylap1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.naylap1.fragment.InboxFragment;
import com.example.naylap1.fragment.ProfileFragment;
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

        bottomNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {

                Fragment fragment = null;
                switch (i){
                    case R.id.nav_category:
                        break;
                    case R.id.nav_search:
                        break;
                    case R.id.nav_message:
                        fragment = new InboxFragment();
                        tv_page_view.setText("Inbox");
                        img_page_icon.setImageResource(R.drawable.feather_search);
                        break;
                    case R.id.nav_profile:
                        tv_page_view.setText("Profile");
                        img_page_icon.setImageResource(R.drawable.edit);
                        fragment = new ProfileFragment();
                        break;
                }

                if (fragment != null){
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();

                }

            }
        });

        bottomNav.showBadge(R.id.nav_message, 1);



    }
}
