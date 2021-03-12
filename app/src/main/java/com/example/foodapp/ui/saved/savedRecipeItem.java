package com.example.foodapp.ui.saved;


import android.graphics.drawable.Drawable;

public class savedRecipeItem {
    private String mImageResource;
    private int id;
    private String recipeName;

    private Drawable starDrawable;
    private Boolean favorited;

    public savedRecipeItem(String mImageResource, Drawable star, int id, String recipeName){
        this.mImageResource = mImageResource;
        this.id = id;
        this.recipeName = recipeName;
        this.starDrawable = star;
        this.favorited = Boolean.TRUE;

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
    public Boolean getFavoritedStatus(){return this.favorited; }
    public Drawable getStarDrawable() {return this.starDrawable;}



}
