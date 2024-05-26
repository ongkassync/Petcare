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


    }


    //method for interchanging menus in side navigation
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.Dashboard) {
            repFragment(new HomeFragment());
        } else if (itemId == R.id.class_sched) {
            repFragment(new ClassScheduleFragment());
        } else if (itemId == R.id.enroll_course) {
            repFragment(new EnrollCourseFragment());
        } else if (itemId == R.id.payments) {
            repFragment(new PaymentFragment());
        } else if (itemId == R.id.payments_history) {
            repFragment(new PaymentHistoryFragment());
        } else if (itemId == R.id.account) {
            repFragment(new AccountFragment());
        } else if (itemId == R.id.settings) {
            repFragment(new SettingsFragment());
        } else {
            return false;
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