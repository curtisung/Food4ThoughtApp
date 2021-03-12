package com.example.foodapp.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.foodapp.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class PantryItemAdapter extends RecyclerView.Adapter<PantryItemAdapter.ExampleViewHolder> {
    static ArrayList<PantryItem> itemList;

    public class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView tv;
        public CardView mCardView;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.item1);
            mCardView = (CardView) itemView.findViewById(R.id.pantryCard);

            final ImageButton btnDel = itemView.findViewById(R.id.pantryDelete);

            btnDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View view) {
                    CardView cv = mCardView;
                    if (getItemCount() != 0) {
                        int pos = (int) cv.getTag();
                        itemList.remove(pos);
                        notifyDataSetChanged();
                    }
                }
            } );
            }
    }

    public PantryItemAdapter(ArrayList<PantryItem> list) {
        itemList = list;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pantry_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);

        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        PantryItem currItem = itemList.get(position);
        holder.tv.setText(currItem.getText1());
        holder.mCardView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
