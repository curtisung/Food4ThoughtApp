package com.example.foodapp.ui.recipe;


public  class CurrentRecipeID {

    private Integer currentID;
    private static CurrentRecipeID instance;

    private CurrentRecipeID(){
        currentID = 544091;
    }

    public static CurrentRecipeID getInstance(){
        if(instance == null){
            instance = new CurrentRecipeID();
        }
        return instance;
    }

    public Integer getCurrentID(){
        return this.currentID;
    }
    public void setCurrentID(int newID){
        this.currentID = newID;
    }


}