package com.example.foodapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.foodapp.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView rv;
    private RecyclerView.Adapter rva;
    private RecyclerView.LayoutManager rvlm;
    private ArrayList<PantryItem> pantry = IngredientList.getInstance().getList();
    private boolean pantryMode = true;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        rv = root.findViewById(R.id.recyclerView);
        rv.setHasFixedSize(true);
        rva = new PantryItemAdapter(pantry);
        rvlm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(rvlm);
        rv.setAdapter(rva);

        final Button addBtn = root.findViewById(R.id.pantryAddBtn);
        EditText e = (EditText) root.findViewById(R.id.pantryItemText);

        final ImageButton profBtn = root.findViewById(R.id.profileBtn);
//        final Button editBtn = root.findViewById(R.id.editProfileBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = e.getText().toString();
                ///only adds if there are characters in string
                if (!s.equals("")){
                    pantry.add(new PantryItem(s));
                    IngredientList.getInstance().setUpdatedStatusTrue();
                    e.setText("");
                    rva.notifyDataSetChanged();
                }
            }
        });

        profBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = root.findViewById(R.id.pantryTitle);
                if (pantryMode) {
                    tv.setText("Your Profile Page");
                    profBtn.setImageResource(R.drawable.ic_baseline_clear_24);
                    rv.setVisibility(view.GONE);
                    e.setVisibility(view.GONE);
                    addBtn.setVisibility(view.GONE);
//                    editBtn.setVisibility(view.VISIBLE);

                } else {
                    tv.setText("Your Pantry List");
                    profBtn.setImageResource(R.drawable.ic_baseline_switch_account_24);
                    rv.setVisibility(view.VISIBLE);
                    e.setVisibility(view.VISIBLE);
                    addBtn.setVisibility(view.VISIBLE);
//                    editBtn.setVisibility(view.GONE);
                }
                pantryMode = !pantryMode;
            }
        });

        return root;
    }
}