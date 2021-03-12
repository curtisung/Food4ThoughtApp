package com.example.foodapp.ui.saved;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodapp.R;
import com.example.foodapp.ui.recipe.RecipeFragment;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SavedFragment extends Fragment {
    private RecyclerView RecipeRecyclerView;
    private SavedRecyclerAdapter RecipeAdapter;
    private RecyclerView.LayoutManager RecipeLayoutManager;
    private View root;

    private SavedViewModel savedViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        savedViewModel =
                new ViewModelProvider(this).get(SavedViewModel.class);
        root = inflater.inflate(R.layout.fragment_saved, container, false);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        ArrayList<savedRecipeItem> savedRecipes = SavedList.getInstance().getList();
//        for(int i=0; i<savedRecipes.size(); i++) {
//            savedRecipeItem recipe = savedRecipes.get(i);
//            int id = recipe.getId();
//            String image = recipe.getmImageResource();
//            String title = recipe.getRecipeName();
//            Drawable star = ContextCompat.getDrawable(getActivity(), R.drawable.white_star);
//
//        }
        RecipeRecyclerView = root.findViewById(R.id.recyclerView);
        RecipeRecyclerView.setHasFixedSize(true);
        RecipeLayoutManager = new LinearLayoutManager(getContext());
        RecipeAdapter = new SavedRecyclerAdapter(savedRecipes);
        RecipeRecyclerView.setLayoutManager(RecipeLayoutManager);
        RecipeRecyclerView.setAdapter(RecipeAdapter);
        RecipeAdapter.setOnItemClickListener(new SavedRecyclerAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(int position) {
                RecipeFragment fragment= new RecipeFragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.saved_fragment, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }

            @Override
            public void onFavClick(int position) {
                Drawable star = ContextCompat.getDrawable(getActivity(), R.drawable.fav_star);
                savedRecipeItem current = savedRecipes.get(position);
                Boolean isSaved = Boolean.FALSE;
                for (int i = 0; i < savedRecipes.size(); i++){
                    if (savedRecipes.get(i).getId() == current.getId()){
                        isSaved = Boolean.TRUE;
                        break;
                    }
                }
                if (isSaved == Boolean.FALSE){
                    current.setStarDrawable(star);
                    current.setFavorited();
                    savedRecipeItem saved = new savedRecipeItem(current.getmImageResource(),star,current.getId(), current.getRecipeName());
                    savedRecipes.add(saved);
                }
                else{
                    current.setStarDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.white_star));
                    current.setFavorited();
                    for (int i = 0; i < savedRecipes.size(); i++){
                        if (savedRecipes.get(i).getId() == current.getId()){
                            savedRecipes.remove(i);
                            break;
                        }
                    }

                }
                RecipeAdapter.notifyDataSetChanged();

            }
        });

    }
}