package com.example.foodapp.ui.recipe;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.foodapp.R;
import com.example.foodapp.ui.home.PantryItem;
import com.example.foodapp.ui.home.IngredientList;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

//import org.json.simple.JSONArray;
//import org.json.JSONException;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//import Picasso.*;

import java.io.IOException;
import java.util.ArrayList;


public class RecipeFragment extends Fragment{
    private RecipeViewModel recipeViewModel;
    private View rootView;
    private String info_str="";
    private String instr_str="";
    private OkHttpClient client;
    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream in = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(in, "src name");
            return d;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void onResume() {
        super.onResume();



        // cheese pesto pizza
        String id = CurrentRecipeID.getInstance().getCurrentID().toString();

        // for getting recipe info (or more specifically, the price)
        String info_url =  String.format("https://api.spoonacular.com/recipes/%s/" +
                "information?" +
                "apiKey=455aa5b8b88b4e82838a9f3ff34dbbc6&" +
                "includeNutrition=false", id);
        // for getting recipe instructions
        String instr_url = String.format("https://api.spoonacular.com/recipes/%s/" +
                "analyzedInstructions?" +
                "apiKey=455aa5b8b88b4e82838a9f3ff34dbbc6&", id);

        // info and instruction requests
        Request info_get = new Request.Builder()
                .url(info_url)
                .build();

        Request instr_get = new Request.Builder()
                .url(instr_url)
                .build();

        // assign JSON strings to info_str and instr_str
        client.newCall(info_get).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                    Headers responseHeaders = response.headers();
//                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
//                        System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
//                    }

                    info_str = responseBody.string();
                    response.close();
                }
            }
        });

        client.newCall(instr_get).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

//                    Headers responseHeaders = response.headers();
//                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
//                        System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
//                    }

                    instr_str = responseBody.string();
                    response.close();
                }
            }
        });

        // wait for info_str response
        while (info_str.equals("")){
            System.out.println("Loading infostr");
        }
        // wait for instr_str response
        while (instr_str.equals("")){
            System.out.println("Loading instr_str");
        }

//        String info_str = "{\"vegetarian\":false,\"vegan\":false,\"glutenFree\":false,\"dairyFree\":false,\"veryHealthy\":false,\"cheap\":false,\"veryPopular\":false,\"sustainable\":false,\"weightWatcherSmartPoints\":14,\"gaps\":\"no\",\"lowFodmap\":false,\"preparationMinutes\":2,\"cookingMinutes\":15,\"aggregateLikes\":0,\"spoonacularScore\":28.0,\"healthScore\":3.0,\"creditsText\":\"Weelicious\",\"sourceName\":\"Weelicious\",\"pricePerServing\":243.61,\"extendedIngredients\":[{\"id\":93723,\"aisle\":\"Bakery/Bread;Ethnic Foods\",\"image\":\"naan.png\",\"consistency\":\"solid\",\"name\":\"naan bread\",\"nameClean\":\"naan\",\"original\":\"4 whole wheat naan bread\",\"originalString\":\"4 whole wheat naan bread\",\"originalName\":\"whole wheat naan bread\",\"amount\":4.0,\"unit\":\"\",\"meta\":[\"whole wheat\"],\"metaInformation\":[\"whole wheat\"],\"measures\":{\"us\":{\"amount\":4.0,\"unitShort\":\"\",\"unitLong\":\"\"},\"metric\":{\"amount\":4.0,\"unitShort\":\"\",\"unitLong\":\"\"}}},{\"id\":1033,\"aisle\":\"Cheese\",\"image\":\"parmesan.jpg\",\"consistency\":\"solid\",\"name\":\"parmesan cheese\",\"nameClean\":\"parmesan\",\"original\":\"2 tablespoons parmesan cheese, grated\",\"originalString\":\"2 tablespoons parmesan cheese, grated\",\"originalName\":\"parmesan cheese, grated\",\"amount\":2.0,\"unit\":\"tablespoons\",\"meta\":[\"grated\"],\"metaInformation\":[\"grated\"],\"measures\":{\"us\":{\"amount\":2.0,\"unitShort\":\"Tbsps\",\"unitLong\":\"Tbsps\"},\"metric\":{\"amount\":2.0,\"unitShort\":\"Tbsps\",\"unitLong\":\"Tbsps\"}}},{\"id\":1001026,\"aisle\":\"Cheese\",\"image\":\"shredded-cheese-white.jpg\",\"consistency\":\"solid\",\"name\":\"shredded mozzarella cheese\",\"nameClean\":\"shredded mozzarella\",\"original\":\"1 cup mozzarella cheese, shredded\",\"originalString\":\"1 cup mozzarella cheese, shredded\",\"originalName\":\"mozzarella cheese, shredded\",\"amount\":1.0,\"unit\":\"cup\",\"meta\":[\"shredded\"],\"metaInformation\":[\"shredded\"],\"measures\":{\"us\":{\"amount\":1.0,\"unitShort\":\"cup\",\"unitLong\":\"cup\"},\"metric\":{\"amount\":236.588,\"unitShort\":\"ml\",\"unitLong\":\"milliliters\"}}},{\"id\":11955,\"aisle\":\"Canned and Jarred;Produce\",\"image\":\"sundried-tomatoes.jpg\",\"consistency\":\"solid\",\"name\":\"sun dried tomato\",\"nameClean\":\"sun dried tomatoes\",\"original\":\"1/4 cup pesto, basil or sun dried tomato\",\"originalString\":\"1/4 cup pesto, basil or sun dried tomato\",\"originalName\":\"pesto, basil or sun dried tomato\",\"amount\":0.25,\"unit\":\"cup\",\"meta\":[\"dried\"],\"metaInformation\":[\"dried\"],\"measures\":{\"us\":{\"amount\":0.25,\"unitShort\":\"cups\",\"unitLong\":\"cups\"},\"metric\":{\"amount\":59.147,\"unitShort\":\"ml\",\"unitLong\":\"milliliters\"}}}],\"id\":544091,\"title\":\"Two Cheese Pesto Pizza\",\"readyInMinutes\":17,\"servings\":4,\"sourceUrl\":\"http://weelicious.com/2012/02/23/two-cheese-pesto-pizza/\",\"image\":\"https://spoonacular.com/recipeImages/544091-556x370.jpg\",\"imageType\":\"jpg\",\"summary\":\"The recipe Two Cheese Pesto Pizza could satisfy your Mediterranean craving in around <b>17 minutes</b>. For <b>$2.44 per serving</b>, this recipe <b>covers 7%</b> of your daily requirements of vitamins and minerals. One serving contains <b>486 calories</b>, <b>18g of protein</b>, and <b>17g of fat</b>. Only a few people really liked this main course. This recipe from Weelicious has 1 fans. If you have naan bread, parmesan cheese, mozzarella cheese, and a few other ingredients on hand, you can make it. All things considered, we decided this recipe <b>deserves a spoonacular score of 23%</b>. This score is rather bad. Try <a href=\\\"https://spoonacular.com/recipes/pesto-and-cheese-pizza-159650\\\">Pesto and Cheese Pizza</a>, <a href=\\\"https://spoonacular.com/recipes/three-cheese-pesto-pizza-396059\\\">Three-Cheese Pesto Pizza</a>, and <a href=\\\"https://spoonacular.com/recipes/easy-three-cheese-pesto-pizza-413839\\\">Easy Three-Cheese Pesto Pizza</a> for similar recipes.\",\"cuisines\":[\"Mediterranean\",\"Italian\",\"European\"],\"dishTypes\":[\"lunch\",\"main course\",\"main dish\",\"dinner\"],\"diets\":[],\"occasions\":[],\"winePairing\":{},\"instructions\":\"1. Preheat oven to 450 F. 2. Place the first 2 ingredients in a bowl and combine.3. Spread 1 tbsp of pesto on each piece of naan bread and top with  cup of the cheese blend.4. Place pizzas on a baking sheet and bake for 10-12 minutes.5. Serve.\",\"analyzedInstructions\":[{\"name\":\"\",\"steps\":[{\"number\":1,\"step\":\"Preheat oven to 450 F.\",\"ingredients\":[],\"equipment\":[{\"id\":404784,\"name\":\"oven\",\"localizedName\":\"oven\",\"image\":\"oven.jpg\",\"temperature\":{\"number\":450.0,\"unit\":\"Fahrenheit\"}}]},{\"number\":2,\"step\":\"Place the first 2 ingredients in a bowl and combine.\",\"ingredients\":[],\"equipment\":[{\"id\":404783,\"name\":\"bowl\",\"localizedName\":\"bowl\",\"image\":\"bowl.jpg\"}]},{\"number\":3,\"step\":\"Spread 1 tbsp of pesto on each piece of naan bread and top with  cup of the cheese blend.\",\"ingredients\":[{\"id\":93723,\"name\":\"naan\",\"localizedName\":\"naan\",\"image\":\"naan.png\"},{\"id\":1041009,\"name\":\"cheese\",\"localizedName\":\"cheese\",\"image\":\"cheddar-cheese.png\"},{\"id\":0,\"name\":\"spread\",\"localizedName\":\"spread\",\"image\":\"\"},{\"id\":93698,\"name\":\"pesto\",\"localizedName\":\"pesto\",\"image\":\"basil-pesto.png\"}],\"equipment\":[]},{\"number\":4,\"step\":\"Place pizzas on a baking sheet and bake for 10-12 minutes.\",\"ingredients\":[],\"equipment\":[{\"id\":404727,\"name\":\"baking sheet\",\"localizedName\":\"baking sheet\",\"image\":\"baking-sheet.jpg\"},{\"id\":404784,\"name\":\"oven\",\"localizedName\":\"oven\",\"image\":\"oven.jpg\"}],\"length\":{\"number\":12,\"unit\":\"minutes\"}},{\"number\":5,\"step\":\"Serve.\",\"ingredients\":[],\"equipment\":[]}]}],\"originalId\":" +
//                "null}";
//        String instr_str = "{\"name\":\"\",\"steps\":[{\"number\":1,\"step\":\"Preheat oven to 450 F.\",\"ingredients\":[],\"equipment\":[{\"id\":404784,\"name\":\"oven\",\"localizedName\":\"oven\",\"image\":\"oven.jpg\",\"temperature\":{\"number\":450.0,\"unit\":\"Fahrenheit\"}}]},{\"number\":2,\"step\":\"Place the first 2 ingredients in a bowl and combine.\",\"ingredients\":[],\"equipment\":[{\"id\":404783,\"name\":\"bowl\",\"localizedName\":\"bowl\",\"image\":\"bowl.jpg\"}]},{\"number\":3,\"step\":\"Spread 1 tbsp of pesto on each piece of naan bread and top with  cup of the cheese blend.\",\"ingredients\":[{\"id\":93723,\"name\":\"naan\",\"localizedName\":\"naan\",\"image\":\"naan.png\"},{\"id\":1041009,\"name\":\"cheese\",\"localizedName\":\"cheese\",\"image\":\"cheddar-cheese.png\"},{\"id\":0,\"name\":\"spread\",\"localizedName\":\"spread\",\"image\":\"\"},{\"id\":93698,\"name\":\"pesto\",\"localizedName\":\"pesto\",\"image\":\"basil-pesto.png\"}],\"equipment\":[]},{\"number\":4,\"step\":\"Place pizzas on a baking sheet and bake for 10-12 minutes.\",\"ingredients\":[],\"equipment\":[{\"id\":404727,\"name\":\"baking sheet\",\"localizedName\":\"baking sheet\",\"image\":\"baking-sheet.jpg\"},{\"id\":404784,\"name\":\"oven\",\"localizedName\":\"oven\",\"image\":\"oven.jpg\"}],\"length\":{\"number\":12,\"unit\":\"minutes\"}},{\"number\":5,\"step\":\"Serve.\",\"ingredients\":[],\"equipment\":[]}]}";

        try {
            JSONObject info_j = new JSONObject(info_str);
            JSONArray temp = new JSONArray(instr_str);
            JSONObject instr_j = temp.getJSONObject(0);

            String r_title = info_j.getString("title");
            String r_desc = info_j.getString("summary");
            r_desc = r_desc.replaceAll("<.*?>", "");
            String r_price = "$" + info_j.getString("pricePerServing");
            String r_pic = info_j.getString("image");
//            Log.i("TITLE",r_title);
//            Log.i("DESCRIPTION",r_desc);
//            Log.i("PRICE",r_price);
//            Log.i("PIC URL",r_pic);

            // build ingredients string
            String r_ingr = "";
            JSONArray ing_list = (JSONArray) info_j.get("extendedIngredients");
            for (int i=0; i<ing_list.length(); ++i){
                r_ingr += ( (JSONObject)ing_list.get(i) ).getString("original") + "\n";
            }
//            Log.i("INGREDIENTS", r_ingr);

            // build instructions string
            String r_instr = "";
            JSONArray steps = (JSONArray) instr_j.get("steps");
            for (int i=0; i<steps.length(); ++i){
                r_instr += "Step "+(i+1)+" - ";
                r_instr += ( (JSONObject)steps.get(i) ).getString("step") + "\n";
            }
//            Log.i("INSTRUCTIONS", r_instr);

            // set values into XML resources
            TextView tv_title = rootView.findViewById(R.id.recipeTitle);
            tv_title.setText(r_title);

            TextView tv_desc = rootView.findViewById(R.id.recipeDescriptionText);
            tv_desc.setText(r_desc);

            TextView tv_ingr = rootView.findViewById(R.id.recipeIngredientsText);
            tv_ingr.setText(r_ingr);

            TextView tv_instr = rootView.findViewById(R.id.recipePreparationText);
            tv_instr.setText(r_instr);

            ImageView iv_pic = rootView.findViewById(R.id.recipeImage);

            Picasso.get().load(r_pic).centerCrop().fit().into(iv_pic);

            // set picture

        } catch (JSONException e) {
            e.printStackTrace();
        }



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

    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        recipeViewModel =
                new ViewModelProvider(this).get(RecipeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_recipe, container, false);
        this.rootView = root;

        Button btn = (Button) root.findViewById(R.id.mapsButton);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetUrlFromIntent(v);
            }
        });
        client = new OkHttpClient();
        return root;
    }


    public void GetUrlFromIntent(View view) {
        String url = "https://www.google.com/maps/search/grocery+store/";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }




}