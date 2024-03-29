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
    private OnItemClickListener mListener;
    public interface OnItemClickListener {
        void onItemClick(int position);
        void onFavClick(int position);
    }
    public void setOnItemClickListener(RecyclerAdapter.OnItemClickListener listener){
        mListener = listener;
    }
    public RecyclerAdapter(ArrayList<RecipeItem> recipeFragList) {
        recipeItemArrayList = recipeFragList;
    }

    public static class RecycleViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
//        public int rid;
        public TextView recipeName;
        private TextView numIncluded;
        public TextView numExcluded;
        public ImageView starImage;

        public RecycleViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            recipeName = itemView.findViewById(R.id.textView1);
            numIncluded = itemView.findViewById(R.id.textView2);
            numExcluded = itemView.findViewById(R.id.textView3);
            starImage = itemView.findViewById(R.id.starView);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });

            starImage.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onFavClick(position);
                        }
                    }
                }
            });

        }
    }

    @NonNull
    @Override
    public RecyclerAdapter.RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_jtem, parent, false);
        RecyclerAdapter.RecycleViewHolder evh = new RecyclerAdapter.RecycleViewHolder(v, mListener);
        return evh;
    }


    public void onBindViewHolder(RecycleViewHolder holder, int position) {
        RecipeItem currentItem = recipeItemArrayList.get(position);
        Picasso.get().load(currentItem.getmImageResource()).centerCrop().fit().into(holder.mImageView);
        holder.starImage.setImageDrawable(currentItem.getStarDrawable());
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
