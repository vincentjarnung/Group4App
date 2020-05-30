package com.example.group4app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MemShipActivity extends AppCompatActivity {
    String memshipID = "";
    int price = 0;
    int length = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mem_ship);
        Button next = findViewById(R.id.nextBtn);
        Button back = findViewById(R.id.backBtn);
        final Spinner spinner = findViewById(R.id.spinner);

        String[] choices = {
                "Gymträning 3 månader 249 kr/månaden",
                "Gymträning 6 månader 229 kr/månaden",
                "Gymträning 12 månader 199 kr/månaden",
                "Gruppträning 3 månader 299 kr/månaden",
                "Gruppträning 6 månader 279 kr/månaden",
                "Gruppträning 12 månader 249 kr/månaden"

        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, choices);

        spinner.setAdapter(adapter);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Spinner", spinner.getSelectedItemPosition() + "");

                if (spinner.getSelectedItemPosition() == 0) {
                    memshipID = "gym3";
                    price = 249;
                    length = 3;
                }else if (spinner.getSelectedItemPosition() == 1){
                    memshipID = "gym6";
                    price = 229;
                    length = 6;
                }else if(spinner.getSelectedItemPosition() == 2){
                    memshipID ="gym12";
                    price = 199;
                    length = 12;
                }else if(spinner.getSelectedItemPosition() == 3){
                    memshipID = "group3";
                    price = 299;
                    length = 3;
                }else if(spinner.getSelectedItemPosition() == 4){
                    memshipID = "group6";
                    price = 279;
                    length = 6;
                }else if(spinner.getSelectedItemPosition() == 5){
                    memshipID = "group12";
                    price = 249;
                    length = 12;
                }
                Intent intent = new Intent(getApplicationContext(),MemberActivity.class);
                intent.putExtra("memshipID",memshipID);
                intent.putExtra("price",price);
                intent.putExtra("length",length);
                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
