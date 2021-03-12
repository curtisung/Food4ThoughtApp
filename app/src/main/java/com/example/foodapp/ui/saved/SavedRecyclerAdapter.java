package com.example.foodapp.ui.saved;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.ui.search.RecipeItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SavedRecyclerAdapter extends RecyclerView.Adapter<SavedRecyclerAdapter.RecycleViewHolder> {
    private ArrayList<savedRecipeItem> recipeItemArrayList;
    private OnItemClickListener mListener;
    public interface OnItemClickListener {
        void onItemClick(int position);
        void onFavClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }
    public SavedRecyclerAdapter(ArrayList<savedRecipeItem> recipeFragList) {
        recipeItemArrayList = recipeFragList;
    }

    public static class RecycleViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
//        public int rid;
        public TextView recipeName;
        public ImageView starImage;

        public RecycleViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            recipeName = itemView.findViewById(R.id.textView1);
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
    public SavedRecyclerAdapter.RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_jtem, parent, false);
        SavedRecyclerAdapter.RecycleViewHolder evh = new SavedRecyclerAdapter.RecycleViewHolder(v, mListener);
        return evh;
    }


    public void onBindViewHolder(RecycleViewHolder holder, int position) {
        savedRecipeItem currentItem = recipeItemArrayList.get(position);
        Picasso.get().load(currentItem.getmImageResource()).centerCrop().fit().into(holder.mImageView);
        holder.starImage.setImageDrawable(currentItem.getStarDrawable());
        holder.recipeName.setText(currentItem.getRecipeName());
    }


    @Override
    public int getItemCount() {
        return recipeItemArrayList.size();
    }



}
