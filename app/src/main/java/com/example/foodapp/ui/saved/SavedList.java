package com.example.foodapp.ui.saved;

import com.example.foodapp.ui.home.ExampleItem;

import java.util.ArrayList;

public  class SavedList {

    private ArrayList<savedRecipeItem> savedRecipeList;
    private static SavedList instance;

    private SavedList(){
        savedRecipeList = new ArrayList<>();
    }

    public static SavedList getInstance(){
        if(instance == null){
            instance = new SavedList();
        }
        return instance;
    }

    public ArrayList<savedRecipeItem> getList(){
        return this.savedRecipeList;
    }

}