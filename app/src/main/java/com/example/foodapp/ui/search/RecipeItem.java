package com.example.foodapp.ui.search;


import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.core.content.res.ResourcesCompat;

import com.example.foodapp.R;

public class RecipeItem {
    private String mImageResource;
    private int id;
    private String recipeName;
    private String numIncluded;
    private String numExcluded;
    private Drawable starDrawable;
    private Boolean favorited;

    public RecipeItem(String mImageResource, Drawable star, int id, String recipeName, String numIncluded, String numExcluded){
        this.mImageResource = mImageResource;
        this.id = id;
        this.recipeName = recipeName;
        this.numIncluded = numIncluded;
        this.numExcluded = numExcluded;
        this.starDrawable = star;
        this.favorited = Boolean.FALSE;

    }
    public void setStarDrawable(Drawable newsStar)
    {
        this.starDrawable = newsStar;
    }
    public void setFavorited(){
        this.favorited = !favorited;
    }
    public String getmImageResource(){
        return this.mImageResource;
    }
    public int getId(){
        return this.id;
    }
    public String getRecipeName(){
        return this.recipeName;
    }
    public String getNumIncluded(){
        return this.numIncluded;
    }
    public String getNumExcluded(){
        return this.numExcluded;
    }
    public Boolean getFavoritedStatus(){return this.favorited; }
    public Drawable getStarDrawable() {return this.starDrawable;}



}
