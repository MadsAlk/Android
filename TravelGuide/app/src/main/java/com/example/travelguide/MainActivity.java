package com.example.travelguide;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.example.travelguide.all.AllFragment;
import com.example.travelguide.ui.home.HomeFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.travelguide.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity{

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_all, R.id.nav_fav, R.id.nav_sort, R.id.nav_profile, R.id.nav_logout)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        ConnectionAsyncTask connectionAsyncTask = new ConnectionAsyncTask(this); //Maybe WRONG ***
        connectionAsyncTask.execute("https://run.mocky.io/v3/d1a9c002-6e88-4d1e-9f39-930615876bca");

        //create fragment tag
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        HomeFragment homeFragment = new HomeFragment();
//        ft.add(homeFragment, "homeFragment");
//        ft.commit();
//        //All
//        FragmentTransaction allf = getSupportFragmentManager().beginTransaction();
//        AllFragment allFragment = new AllFragment();
//        allf.add(allFragment, "allFragment");
//        allf.commit();

//        AllFragment fragment = new AllFragment();
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


//    @Override public void respond(List<Destination> destinations) {
//        System.out.println("HERE in respond _________________" + destinations.get(2));
//        HomeFragment homeFragment = (HomeFragment)getSupportFragmentManager().findFragmentByTag("homeFragment");
//        homeFragment.fillDestinations(destinations);
//    }
}