package com.example.foodapp.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.foodapp.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    private ArrayList<ExampleItem> itemList;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView tv;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.item1);
        }
    }

    public ExampleAdapter(ArrayList<ExampleItem> list) {
        itemList = list;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);

        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        ExampleItem currItem = itemList.get(position);
        holder.tv.setText(currItem.getText1());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
