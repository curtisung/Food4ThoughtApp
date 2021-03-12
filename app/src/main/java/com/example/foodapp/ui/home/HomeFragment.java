package com.example.foodapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.foodapp.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private RecyclerView panView;
    private RecyclerView.Adapter panViewAdapter;
    private RecyclerView.LayoutManager panViewLayoutManager;
    private ArrayList<PantryItem> pantry = IngredientList.getInstance().getList();
    public static ArrayList<Allergen> allergies = new ArrayList<Allergen>();

    private boolean profileMode = true;

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

        EditText e = (EditText) root.findViewById(R.id.pantryItemText);

        panView = root.findViewById(R.id.pantryView);
        panView.setHasFixedSize(true);
        panViewAdapter = new PantryItemAdapter(pantry);
        panViewLayoutManager = new LinearLayoutManager(getContext());
        panView.setLayoutManager(panViewLayoutManager);
        panView.setAdapter(panViewAdapter);

        final Button addBtn = root.findViewById(R.id.pantryAddBtn);
        final Button profileBtn = root.findViewById(R.id.profBtn);
        final Button prefBtn = root.findViewById(R.id.prefBtn);
        final Button updateBtn = root.findViewById(R.id.updateBtn);
        final ImageButton profileSwitchBtn = root.findViewById(R.id.profileSwitchBtn);
        final ImageButton pantrySwitchBtn = root.findViewById(R.id.pantrySwitchBtn);

        CheckBox cb0 = (CheckBox) root.findViewById(R.id.allergy0);
        CheckBox cb1 = (CheckBox) root.findViewById(R.id.allergy1);
        CheckBox cb2 = (CheckBox) root.findViewById(R.id.allergy2);
        CheckBox cb3 = (CheckBox) root.findViewById(R.id.allergy3);
        CheckBox cb4 = (CheckBox) root.findViewById(R.id.allergy4);
        CheckBox cb5 = (CheckBox) root.findViewById(R.id.allergy5);
        CheckBox cb6 = (CheckBox) root.findViewById(R.id.allergy6);
        CheckBox cb7 = (CheckBox) root.findViewById(R.id.allergy7);

        allergies.add(new Allergen((String) cb0.getText(), false));
        allergies.add(new Allergen((String) cb1.getText(), false));
        allergies.add(new Allergen((String) cb2.getText(), false));
        allergies.add(new Allergen((String) cb3.getText(), false));
        allergies.add(new Allergen((String) cb4.getText(), false));
        allergies.add(new Allergen((String) cb5.getText(), false));
        allergies.add(new Allergen((String) cb6.getText(), false));
        allergies.add(new Allergen((String) cb7.getText(), false));

        cb0.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                switch (compoundButton.getId()) {
                    case R.id.allergy0:
                        if (compoundButton.isChecked()) {
                            allergies.get(0).setAllergy(true);
                        }
                        else {
                            allergies.get(0).setAllergy(false);
                        }
                        break;
                    case R.id.allergy1:
                        if (compoundButton.isChecked()) {
                            allergies.get(1).setAllergy(true);
                        }
                        else {
                            allergies.get(1).setAllergy(false);
                        }
                        break;
                    case R.id.allergy2:
                        if (compoundButton.isChecked()) {
                            allergies.get(2).setAllergy(true);
                        }
                        else {
                            allergies.get(2).setAllergy(false);
                        }
                        break;
                    case R.id.allergy3:
                        if (compoundButton.isChecked()) {
                            allergies.get(3).setAllergy(true);
                        }
                        else {
                            allergies.get(3).setAllergy(false);
                        }
                        break;
                    case R.id.allergy4:
                        if (compoundButton.isChecked()) {
                            allergies.get(4).setAllergy(true);
                        }
                        else {
                            allergies.get(4).setAllergy(false);
                        }
                        break;
                    case R.id.allergy5:
                        if (compoundButton.isChecked()) {
                            allergies.get(5).setAllergy(true);
                        }
                        else {
                            allergies.get(5).setAllergy(false);
                        }
                        break;
                    case R.id.allergy6:
                        if (compoundButton.isChecked()) {
                            allergies.get(6).setAllergy(true);
                        }
                        else {
                            allergies.get(6).setAllergy(false);
                        }
                        break;
                    case R.id.allergy7:
                        if (compoundButton.isChecked()) {
                            allergies.get(7).setAllergy(true);
                        }
                        else {
                            allergies.get(7).setAllergy(false);
                        }
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + compoundButton.getId());
                }
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               TextView  nameEdit = root.findViewById(R.id.userName);
               TextView  emailEdit = root.findViewById(R.id.userEmail);
               TextView  locationEdit= root.findViewById(R.id.userLocation);
               TextView  hgtEdit = root.findViewById(R.id.userHeight);
               TextView  wgtEdit = root.findViewById(R.id.userWeight);

               TextView  userName = root.findViewById(R.id.profileName);
               TextView  userEmail = root.findViewById(R.id.profileEmail);
               TextView  userLocation = root.findViewById(R.id.profileLocation);
               TextView  userHgt = root.findViewById(R.id.profileHeight);
               TextView  userWgt = root.findViewById(R.id.profileWeight);

               userName.setText(nameEdit.getText());
               userEmail.setText(emailEdit.getText());
               userLocation.setText(locationEdit.getText());
               userHgt.setText(hgtEdit.getText());
               userWgt.setText(wgtEdit.getText());

           }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = e.getText().toString();
                ///only adds if there are characters in string
                if (!s.equals("")){
                    pantry.add(new PantryItem(s));
                    e.setText("");
                    panViewAdapter.notifyDataSetChanged();
                }
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!profileMode) {
                    ConstraintLayout profile = root.findViewById(R.id.userProfile);
                    ConstraintLayout allergnsView = root.findViewById(R.id.allergyView);
                    profile.setVisibility(view.VISIBLE);
                    allergnsView.setVisibility(view.GONE);
                    profileMode = !profileMode;
                }
            }
        });

        prefBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (profileMode) {
                    ConstraintLayout profile = root.findViewById(R.id.userProfile);
                    ConstraintLayout allergnsView = root.findViewById(R.id.allergyView);
                    profile.setVisibility(view.GONE);
                    allergnsView.setVisibility(view.VISIBLE);
                    profileMode = !profileMode;
                }
            }
        });

        profileSwitchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecyclerView panView = root.findViewById(R.id.pantryView);
                ConstraintLayout panBanner = root.findViewById(R.id.pantryBanner);
                ConstraintLayout profBanner = root.findViewById(R.id.profileBanner);
                ConstraintLayout profView = root.findViewById(R.id.userProfile);
                ConstraintLayout editProfBtn = root.findViewById(R.id.profileBtnLayout);
                ConstraintLayout editDietBtn = root.findViewById(R.id.preferenceBtnLayout);
                ConstraintLayout allergnsView = root.findViewById(R.id.allergyView);

                panView.setVisibility(view.GONE);
                panBanner.setVisibility(view.GONE);
                profBanner.setVisibility(view.VISIBLE);
                editProfBtn.setVisibility(view.VISIBLE);
                editDietBtn.setVisibility(view.VISIBLE);
                profView.setVisibility(view.VISIBLE);
                allergnsView.setVisibility(view.GONE);
            }

        });

        pantrySwitchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecyclerView panView = root.findViewById(R.id.pantryView);
                ConstraintLayout panBanner = root.findViewById(R.id.pantryBanner);
                ConstraintLayout profBanner = root.findViewById(R.id.profileBanner);
                ConstraintLayout profView = root.findViewById(R.id.userProfile);
                ConstraintLayout editProfBtn = root.findViewById(R.id.profileBtnLayout);
                ConstraintLayout editDietBtn = root.findViewById(R.id.preferenceBtnLayout);
                ConstraintLayout allergnsView = root.findViewById(R.id.allergyView);

                panView.setVisibility(view.VISIBLE);
                panBanner.setVisibility(view.VISIBLE);
                profBanner.setVisibility(view.GONE);
                editProfBtn.setVisibility(view.GONE);
                editDietBtn.setVisibility(view.GONE);
                profView.setVisibility(view.GONE);
                allergnsView.setVisibility(view.GONE);
            }

        });

        return root;
    }
}