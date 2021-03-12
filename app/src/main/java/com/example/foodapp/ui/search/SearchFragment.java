package com.example.foodapp.ui.search;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.MainActivity;

import com.example.foodapp.R;
import com.example.foodapp.ui.home.IngredientList;

import com.example.foodapp.ui.recipe.CurrentRecipeID;

import com.example.foodapp.ui.home.PantryItem;

import com.example.foodapp.ui.recipe.RecipeFragment;
import com.example.foodapp.ui.saved.SavedList;
import com.example.foodapp.ui.saved.savedRecipeItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;

public class SearchFragment extends Fragment {
    private RecyclerView RecipeRecyclerView;
    private RecyclerAdapter RecipeAdapter;
    private RecyclerView.LayoutManager RecipeLayoutManager;
    private View root;
    private SearchViewModel searchViewModel;
    private String jsonStr = "";
    private OkHttpClient client;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        root = inflater.inflate(R.layout.fragment_search, container, false);
        setHasOptionsMenu(true);
        client = new OkHttpClient();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("SearchFragResume " + IngredientList.getInstance().getUpdatedStatus());
        if (IngredientList.getInstance().getUpdatedStatus()){
            APIThread p = new APIThread();
            p.start();


//        String obj = loadJSONFromAsset();

            while (jsonStr.equals("")){
                System.out.println("loading jsonStr");
            }
        }

        try {
            ArrayList<savedRecipeItem> savedRecipes = SavedList.getInstance().getList();
            if (IngredientList.getInstance().getUpdatedStatus()) {
                JSONArray recipeList = new JSONArray(jsonStr);
                IngredientList.getInstance().setUpdatedStatusFalse();

                ArrayList<RecipeItem> recipeFragList = new ArrayList<RecipeItem>();
                for(int i=0; i<recipeList.length(); i++) {
                    JSONObject recipe = (JSONObject) recipeList.getJSONObject(i);
                    String image = (String) recipe.get("image");
                    Integer id = (Integer) recipe.get("id");
                    String title = (String) recipe.get("title");
                    String usedIngredientCount = recipe.get("usedIngredientCount").toString();
                    String missedIngredientCount = recipe.get("missedIngredientCount").toString();
                    Drawable star = ContextCompat.getDrawable(getActivity(), R.drawable.white_star);
                    for (int x = 0; x < savedRecipes.size(); x++){
                        if (savedRecipes.get(x).getId() == id){
                            star = ContextCompat.getDrawable(getActivity(), R.drawable.fav_star);
                            break;
                        }
                    }
                    recipeFragList.add(new RecipeItem(image,star, id, title, usedIngredientCount, missedIngredientCount));
                }
                SearchRecipeList.getInstance().setItemList(recipeFragList);

            }



            ArrayList<RecipeItem> recipeFragList = SearchRecipeList.getInstance().getList();

            RecipeRecyclerView = root.findViewById(R.id.recyclerView);
            RecipeRecyclerView.setHasFixedSize(true);
            RecipeLayoutManager = new LinearLayoutManager(getContext());
            RecipeAdapter = new RecyclerAdapter(recipeFragList);
            RecipeRecyclerView.setLayoutManager(RecipeLayoutManager);
            RecipeRecyclerView.setAdapter(RecipeAdapter);

            RecipeAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener(){
                @Override
                public void onItemClick(int position) {
                    CurrentRecipeID.getInstance().setCurrentID(recipeFragList.get(position).getId());
                    Navigation.findNavController(getView()).navigate(R.id.navigation_recipe);
                }

                @Override
                public void onFavClick(int position) {
                    Drawable star = ContextCompat.getDrawable(getActivity(), R.drawable.fav_star);
                    RecipeItem current = recipeFragList.get(position);
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



        } catch (JSONException e) {
            e.printStackTrace();
        }
        // loop array


    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("trial.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public String createURL(){
        ArrayList<PantryItem> pantry = IngredientList.getInstance().getList();
        String url = "https://api.spoonacular.com/recipes/findByIngredients?apiKey=a4f99db33e0c409c9acfde9739fca4eb&ingredients=";
        for (int i = 0; i < pantry.size(); i++) {
            if (i == 0){
                url += pantry.get(i).getText1().trim();
            }
            else{
                url += "+" + pantry.get(i).getText1().trim();
            }
            if (i != pantry.size() - 1){
                url += ",";
            }
        }
        url += "&number=10";
        return url;
    }
    class APIThread extends Thread {

        APIThread() {
            System.out.println("Thread Start");
        }

        public void run() {
            Request get = new Request.Builder()
                    .url(createURL())
                    .build();

            client.newCall(get).enqueue(new Callback() {
                @Override public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override public void onResponse(Call call, Response response) throws IOException {
                    try (ResponseBody responseBody = response.body()) {
                        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

//                        Headers responseHeaders = response.headers();
//                        for (int i = 0, size = responseHeaders.size(); i < size; i++) {
//                            System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
//                        }

                        jsonStr = responseBody.string();
                        response.close();

                    }

                }
            });


        }
    }

}

