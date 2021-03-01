package com.example.foodapp.ui.home;

import java.util.ArrayList;
import java.util.List;

public  class IngredientList {

    private ArrayList<ExampleItem> itemList;
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

    public ArrayList<ExampleItem> getList(){
        return this.itemList;
    }

}