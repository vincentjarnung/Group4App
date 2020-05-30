package com.example.group4app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ChooseOnlineTrainingActivity extends AppCompatActivity {
    RecyclerView rv;
    OnlineTrainAdapter pta;
    String[] test = {"Styrka 30","Kondition 45"};
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.online_training_chooce_activity);
        rv = findViewById(R.id.onlineTraingRV);
        pta = new OnlineTrainAdapter(this,test);
        rv.setAdapter(pta);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }


}
