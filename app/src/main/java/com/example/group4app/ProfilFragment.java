package com.example.group4app;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfilFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profil_fragment,container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        final Button trainRecBtn = (Button) getView().findViewById(R.id.trainRecBtn);
        final Button chaValBtn = (Button) getView().findViewById(R.id.changeValBtn);
        final Button payRecBtn = (Button) getView().findViewById(R.id.payRecBtn);
        final Button quitMemBtn = (Button) getView().findViewById(R.id.quitmemshipBtn);

        trainRecBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trainRecord();
            }
        });
        chaValBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeValues();
            }
        });
        payRecBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paymentRecord();
            }
        });
        quitMemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quitMemship();
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }



    public void trainRecord(){

    }
    public void changeValues(){

    }
    public void paymentRecord(){

    }
    public void quitMemship(){
        final Dialog dialog = new Dialog(getActivity());
        dialog.setCancelable(true);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        View view  = getActivity().getLayoutInflater().inflate(R.layout.quit_memship_popup, null);
        dialog.setContentView(view);
        dialog.getWindow().setLayout((8*width)/9, (4*height)/7);

        TextView emailT = dialog.findViewById(R.id.emailTQ);
        TextView emailF = dialog.findViewById(R.id.emailFQ);
        TextView passT = dialog.findViewById(R.id.passwordTQ);
        TextView passF = dialog.findViewById(R.id.passwordFQ);
        TextView backBtn = dialog.findViewById(R.id.goBackbtnQ);
        TextView quitBtn = dialog.findViewById(R.id.quitBtn);


        quitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(),MainActivity.class);
                startActivity(i);
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(),LoggedInActivity.class);
                startActivity(i);
            }
        });

        dialog.show();
    }


}
