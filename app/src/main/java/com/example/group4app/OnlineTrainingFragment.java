package com.example.group4app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class OnlineTrainingFragment extends Fragment {
    OnlineTrainAdapter pta;
    Button strengthBtn,kondBtn,yogaBtn,stretchBtn,fitOn15Btn;
    String[] test = {"Styrka 30","Kondition 45"};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.online_training_fragment,container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        strengthBtn = getView().findViewById(R.id.strengBtn);
        kondBtn = getView().findViewById(R.id.konBtn);
        yogaBtn = getView().findViewById(R.id.yogBtn);
        stretchBtn = getView().findViewById(R.id.streBtn);
        fitOn15Btn = getView().findViewById(R.id.FP15Btn);

        strengthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent i = new Intent(v.getContext(),ChooseOnlineTrainingActivity.class);
              startActivity(i);
            }
        });
        kondBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        yogaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        stretchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        fitOn15Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }



}
