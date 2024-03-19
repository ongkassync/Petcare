package com.example.blood;





import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class DrawerNavigation extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_navigation);

        //DrawerLayout Initialization
        drawerLayout = findViewById(R.id.drawer_layout);


        //Toolbar Initialization
        Toolbar toolbar = findViewById(R.id.layoutToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //initializing navigation side view
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        //ActionBar Toggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //Open the home fragment when the app opens
        repFragment(new HomeFragment());

        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);


        //Bottom Navigation Method
        bottomNavigationView.setOnItemSelectedListener(item -> {

            int itemId = item.getItemId();
            if (itemId == R.id.home) {
                repFragment(new HomeFragment());
            } else if (itemId == R.id.profile) {
                repFragment(new ProfileFragment());
            } else if (itemId == R.id.settings) {
                repFragment(new SettingsFragment());
            }

            return true;
        });

    }


    //method for interchanging menus in side navigation
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();

        if (itemId == R.id.nav_profile) {
            repFragment(new SideProfileFragment());
        } else if (itemId == R.id.nav_account) {
            repFragment(new SideAccountFragment());
        } else if (itemId == R.id.nav_notification) {
            repFragment(new SideNotificationFragment());
        } else if (itemId == R.id.nav_settings) {
            repFragment(new SideSettingsFragment());
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    //Method for replacing fragments in  bottom navigation
    private void repFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_out,fragment);
        fragmentTransaction.commit();
    }
    }