package com.example.group4app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

public class LoggedInActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView nv = findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        nv.getMenu().getItem(0).setChecked(true);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfilFragment()).commit();
        onNavigationItemSelected(nv.getMenu().getItem(0));

    }

    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()){
            case (R.id.profil):
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfilFragment()).commit();
                drawer.closeDrawer(GravityCompat.START);
                break;
            case (R.id.SeGym):
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ValjGymFragment()).commit();
                drawer.closeDrawer(GravityCompat.START);

                break;
            case (R.id.bookPass):
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new BookPassFragment()).commit();
                drawer.closeDrawer(GravityCompat.START);

                break;
            case (R.id.findPass):
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FindPassFragment()).commit();
                drawer.closeDrawer(GravityCompat.START);

                break;
            case (R.id.onlineTraing):
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new OnlineTrainingFragment()).commit();
                drawer.closeDrawer(GravityCompat.START);

                break;
        }
        return true;
    }


    public void logOut(View v){
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }
}
