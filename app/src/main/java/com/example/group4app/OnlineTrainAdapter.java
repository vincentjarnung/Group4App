package com.example.group4app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OnlineTrainAdapter extends RecyclerView.Adapter<OnlineTrainAdapter.OnlineTrainViewHolder> {

    Context c;
    String onlTrainName[];

    public OnlineTrainAdapter(Context c, String[] onlTrainName){
        this.c =c;
        this.onlTrainName = onlTrainName;
    }

    @NonNull
    @Override
    public OnlineTrainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(c);
        View view = inflater.inflate(R.layout.onl_train_row,parent,false);
        return new OnlineTrainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OnlineTrainViewHolder holder, int position) {
        holder.onlTrainBtn.setText(onlTrainName[position]);
    }

    @Override
    public int getItemCount() {
        return onlTrainName.length;
    }

    public class OnlineTrainViewHolder extends RecyclerView.ViewHolder {

        Button onlTrainBtn;

        public OnlineTrainViewHolder(@NonNull View itemView) {
            super(itemView);
            onlTrainBtn = itemView.findViewById(R.id.onlTrainBtn);
        }
    }
}