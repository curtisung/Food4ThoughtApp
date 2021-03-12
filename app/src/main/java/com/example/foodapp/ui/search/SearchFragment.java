package com.example.foodapp.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.ui.home.PantryItem;
import com.example.foodapp.ui.home.IngredientList;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import okhttp3.OkHttpClient;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;

public class SearchFragment extends Fragment {
    private RecyclerView RecipeRecyclerView;
    private RecyclerView.Adapter RecipeAdapter;
    private RecyclerView.LayoutManager RecipeLayoutManager;
    private View root;
    private SearchViewModel searchViewModel;
    private ArrayList<PantryItem> pantry = IngredientList.getInstance().getList();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        root = inflater.inflate(R.layout.fragment_search, container, false);
        setHasOptionsMenu(true);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        //test api
        OkHttpClient client = new OkHttpClient();
//        Request get = new Request.Builder()
//        .url(createURL())
//        .build();
//
//        client.newCall(get).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) {
//                try {
//                    ResponseBody responseBody = response.body();
//                    if (!response.isSuccessful()) {
//                        throw new IOException("Unexpected code " + response);
//                    }
//
//                    Log.i("data", responseBody.string());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });

        String obj = loadJSONFromAsset();

        try {
            JSONArray recipeList = new JSONArray(obj);
            ArrayList<RecipeItem> recipeFragList = new ArrayList<>();
            for(int i=0; i<recipeList.length(); i++) {
                JSONObject recipe = (JSONObject) recipeList.getJSONObject(i);
                String image = (String) recipe.get("image");
                Integer id = (Integer) recipe.get("id");
                String title = (String) recipe.get("title");
                String usedIngredientCount = recipe.get("usedIngredientCount").toString();
                String missedIngredientCount = recipe.get("missedIngredientCount").toString();
                recipeFragList.add(new RecipeItem(image, id, title, usedIngredientCount, missedIngredientCount));
            }
            RecipeRecyclerView = root.findViewById(R.id.recyclerView);
            RecipeRecyclerView.setHasFixedSize(true);
            RecipeLayoutManager = new LinearLayoutManager(getContext());
            RecipeAdapter = new RecyclerAdapter(recipeFragList);
            RecipeRecyclerView.setLayoutManager(RecipeLayoutManager);
            RecipeRecyclerView.setAdapter(RecipeAdapter);



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
}

