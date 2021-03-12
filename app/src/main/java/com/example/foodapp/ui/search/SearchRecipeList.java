package com.example.foodapp.ui.search;

import com.example.foodapp.ui.home.PantryItem;

import java.util.ArrayList;

public class SearchRecipeList{

    private ArrayList<RecipeItem> itemList;
    private static SearchRecipeList instance;

    private SearchRecipeList(){
        itemList = new ArrayList<>();

    }

    public static SearchRecipeList getInstance(){
        if(instance == null){
            instance = new SearchRecipeList();
        }
        return instance;
    }

    public ArrayList<RecipeItem> getList(){
        return this.itemList;
    }

    public void setItemList(ArrayList<RecipeItem> newList){
        itemList = newList;
    }

}