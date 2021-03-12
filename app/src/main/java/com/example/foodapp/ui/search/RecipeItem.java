package com.example.foodapp.ui.search;



public class RecipeItem {
    private String mImageResource;
    private int id;
    private String recipeName;
    private String numIncluded;
    private String numExcluded;

    public RecipeItem(String mImageResource, int id, String recipeName, String numIncluded, String numExcluded){
        this.mImageResource = mImageResource;
        this.id = id;
        this.recipeName = recipeName;
        this.numIncluded = numIncluded;
        this.numExcluded = numExcluded;
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

}
