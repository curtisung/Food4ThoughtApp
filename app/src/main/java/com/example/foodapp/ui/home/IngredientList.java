package com.example.foodapp.ui.home;

import java.util.ArrayList;

public  class IngredientList {

    private ArrayList<PantryItem> itemList;
    private static IngredientList instance;

    private IngredientList(){
        itemList = new ArrayList<>();
    }

    public static IngredientList getInstance(){
        if(instance == null){
            instance = new IngredientList();
        }
        return instance;
    }

    public ArrayList<PantryItem> getList(){
        return this.itemList;
    }

}