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

public class FindPassFragment extends Fragment {
    DatePickerDialog.OnDateSetListener dateSetListener;
    SimpleDateFormat sdf;
    Calendar calendar;
    RecyclerView rv;
    Button dateBtn;
    TextView dateT;
    String [] nameType = {"Styrka 30 | Styrka","Springa 45 | Kondtition"};
    String[] date = {"2020-01-06","2020-01-06"};
    String [] empTime = {"Måndag 12:30 - 13:00 | Lisa Vanderpump","Tisdag 12:30 - 13:15 | Lisa Vanderpump"};
    String [] gymRoom = {"Björkvägen | LilleSkutt","Björkvägen | Skalman"};
    String [] placeCap = {"13 lediga platser av 20","12 lediga platser av 25"};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.find_pass_fragment,container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dateBtn = getView().findViewById(R.id.dateBtn);
        dateT = getView().findViewById(R.id.dateT);
        Date d = new Date();
        calendar = Calendar.getInstance();
        calendar.setTime(d);
        Date today = calendar.getTime();
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        dateT.setText(sdf.format(calendar.getTime()));

        rv = getView().findViewById(R.id.trainRV);

        TrainAdapter ta = new TrainAdapter(getActivity(),nameType,date,empTime,gymRoom,placeCap,1);
        rv.setAdapter(ta);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker();
            }
        });
        dateT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker();
            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                int rmonth = month +1;
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                dateT.setText(sdf.format(calendar.getTime()));
            }
        };

    }

    public void datePicker(){
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpdia = new DatePickerDialog(getActivity(),android.R.style.Theme_Holo_Dialog,dateSetListener,year,month,day);
        dpdia.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dpdia.show();
    }
}
