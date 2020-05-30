package com.example.group4app;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BookPassFragment extends Fragment {
    RecyclerView rv;
    String [] nameType = {"Styrka 30 | Styrka"};
    String[] date = {"2020-01-06"};
    String [] empTime = {"Måndag 12:30 - 13:00"};
    String [] gymRoom = {"Björkvägen | LilleSkutt"};
    String [] placeCap = {"13 lediga platser av 20"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.book_pass_fragment,container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        rv = getView().findViewById(R.id.trainRV);

        TrainAdapter ta = new TrainAdapter(getActivity(),nameType,date,empTime,gymRoom,placeCap,2);
        rv.setAdapter(ta);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));


    }


}

