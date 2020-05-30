package com.example.group4app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TrainAdapter extends RecyclerView.Adapter<TrainAdapter.TrainViewHolder> {

    Context c;
    String nameType[], empTime[], gymRoom[],placeCap[],date[];
    int choicer;
    Button addBtn;
    Button rmvBtn;

    public TrainAdapter(Context c, String[] nameType, String[] date, String[] empTime, String[] gymRoom, String[] placeCap,int choicer){
            this.c =c;
            this.nameType = nameType;
            this.date = date;
            this.empTime = empTime;
            this.gymRoom = gymRoom;
            this.placeCap = placeCap;
            this.choicer = choicer;
    }

    @NonNull
    @Override
    public TrainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(c);
        View view = inflater.inflate(R.layout.train_row,parent,false);
        return new TrainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainViewHolder holder, int position) {
        holder.nameTypeT.setText(nameType[position]);
        holder.dateT.setText(date[position]);
        holder.empTimeT.setText(empTime[position]);
        holder.gymRoomT.setText(gymRoom[position]);
        holder.placeCapT.setText(placeCap[position]);
        if(choicer == 1){
            rmvBtn.setVisibility(View.GONE);
        }else if(choicer == 2){
            addBtn.setVisibility(View.GONE);

        }else if(choicer == 3){
            addBtn.setVisibility(View.GONE);
            rmvBtn.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return nameType.length;
    }

    public class TrainViewHolder extends RecyclerView.ViewHolder {

        TextView nameTypeT,empTimeT,gymRoomT,placeCapT,dateT;

        public TrainViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTypeT = itemView.findViewById(R.id.nameType);
            empTimeT = itemView.findViewById(R.id.empDate);
            gymRoomT = itemView.findViewById(R.id.gymRoom);
            placeCapT = itemView.findViewById(R.id.placeCap);
            dateT = itemView.findViewById(R.id.date);
            addBtn = itemView.findViewById(R.id.addBtn);
            rmvBtn = itemView.findViewById(R.id.rmvBtn);

        }
    }
}
