package com.example.foodapp.ui.search;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecycleViewHolder> {
    private ArrayList<RecipeItem> recipeItemArrayList;

    public RecyclerAdapter(ArrayList<RecipeItem> recipeFragList) {
        recipeItemArrayList = recipeFragList;
    }

    public static class RecycleViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
//        public TextView id;
        public TextView recipeName;
        private TextView numIncluded;
        public TextView numExcluded;

        public RecycleViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            recipeName = itemView.findViewById(R.id.textView1);
            numIncluded = itemView.findViewById(R.id.textView2);
            numExcluded = itemView.findViewById(R.id.textView3);


        }
    }

    @NonNull
    @Override
    public RecyclerAdapter.RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_jtem, parent, false);
        RecyclerAdapter.RecycleViewHolder evh = new RecyclerAdapter.RecycleViewHolder(v);
        return evh;
    }


    public void onBindViewHolder(RecycleViewHolder holder, int position) {
        RecipeItem currentItem = recipeItemArrayList.get(position);
        Picasso.get().load(currentItem.getmImageResource()).centerCrop().fit().into(holder.mImageView);
        holder.recipeName.setText(currentItem.getRecipeName());
        String numExcluded = "Excluded Ingredients: " + currentItem.getNumExcluded();
        holder.numExcluded.setText(numExcluded);
        String numIncluded = "Included Ingredients: " + currentItem.getNumIncluded();
        holder.numIncluded.setText(numIncluded);
    }


    @Override
    public int getItemCount() {
        return recipeItemArrayList.size();
    }



}
