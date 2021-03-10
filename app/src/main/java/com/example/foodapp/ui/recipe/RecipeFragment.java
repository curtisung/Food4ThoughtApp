package com.example.foodapp.ui.recipe;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.foodapp.R;
import com.example.foodapp.ui.home.ExampleItem;
import com.example.foodapp.ui.home.IngredientList;

import java.io.IOException;
import java.util.ArrayList;


public class RecipeFragment extends Fragment{

    private RecipeViewModel recipeViewModel;
    private ArrayList<ExampleItem> pantry = IngredientList.getInstance().getList();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        recipeViewModel =
                new ViewModelProvider(this).get(RecipeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_recipe, container, false);
//        final TextView textView = root.findViewById(R.id.text_recipe);
//        recipeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }


}
