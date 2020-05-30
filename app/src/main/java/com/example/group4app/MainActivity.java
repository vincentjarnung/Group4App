package com.example.group4app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActionBarContainer;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawer;
    DatabaseHelper db;
    Dialog loginPopup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);

        Cursor rs = db.getAllFromTable("Membership");
        Log.d("DB DATA NUM", ""+rs.getCount());

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView nv = findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        nv.getMenu().getItem(0).setChecked(true);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

        loginPopup = new Dialog(this);

    }


    @Override
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
            case (R.id.Hem):
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                drawer.closeDrawer(GravityCompat.START);
            break;
            case (R.id.SeGym):
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ValjGymFragment()).commit();
                drawer.closeDrawer(GravityCompat.START);

            break;
        }
        return true;
    }
    public void showLoginPopup(View v){
        loginPopup.setContentView(R.layout.login_popup);
        TextView emailT;
        TextView passwordT;
        EditText emailF;
        EditText passwordF;
        Button memBtn;
        Button logInBtn;
        Button backBtn;

        emailT = loginPopup.findViewById(R.id.emailT);
        emailF = loginPopup.findViewById(R.id.emailF);
        passwordT = loginPopup.findViewById(R.id.passwordT);
        passwordF = loginPopup.findViewById(R.id.passwordF);
        memBtn = loginPopup.findViewById(R.id.memBtn);
        logInBtn = loginPopup.findViewById(R.id.logInBtn);
        backBtn = loginPopup.findViewById(R.id.goBackbtn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPopup.dismiss();
            }
        });

        memBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MemShipActivity.class);
                startActivity(i);
            }
        });
        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),LoggedInActivity.class);
                startActivity(i);
            }
        });
        loginPopup.show();
    }


}


