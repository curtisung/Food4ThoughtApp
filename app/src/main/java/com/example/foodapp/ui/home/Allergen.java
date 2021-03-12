package com.example.foodapp.ui.home;

public class Allergen {
    private String food;
    private boolean allergic;

    public Allergen(String f, boolean b) {food = f; allergic = b;}
    public String allergenName() {return food;}
    public void setAllergy(boolean b) {allergic = b;}
    public boolean isAllergic() {return allergic;}
}
