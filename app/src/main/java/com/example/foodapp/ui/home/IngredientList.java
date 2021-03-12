package com.example.foodapp.ui.home;

import java.util.ArrayList;

public  class IngredientList extends ArrayList{

    private ArrayList<PantryItem> itemList;
    private Boolean updatedStatus;
    private static IngredientList instance;

    private IngredientList(){
        itemList = new ArrayList<>();
        updatedStatus = Boolean.TRUE;
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

    public Boolean getUpdatedStatus(){
        return updatedStatus;
    }
    public void setUpdatedStatusFalse(){
        updatedStatus = Boolean.FALSE;
    }
    public void setUpdatedStatusTrue(){
        updatedStatus = Boolean.TRUE;
    }

}