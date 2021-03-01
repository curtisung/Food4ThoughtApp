package com.example.foodapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView rv;
    private RecyclerView.Adapter rva;
    private RecyclerView.LayoutManager rvlm;
    private ArrayList<ExampleItem> pantry = IngredientList.getInstance().getList();

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
        rva = new ExampleAdapter(pantry);
        rvlm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(rvlm);
        rv.setAdapter(rva);

        final Button btn = root.findViewById(R.id.pantryAddBtn);
        EditText e = (EditText) root.findViewById(R.id.pantryItemText);
        e.setHint("Enter Ingredient Name");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = e.getText().toString();
                ///only adds if there are characters in string
                if (!s.equals("")){
                    pantry.add(new ExampleItem(s));
                    e.setText("");
                    rva.notifyDataSetChanged();
                }

            }
        });

        return root;
    }
}