package com.powermango.myapplication.menuFragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.powermango.myapplication.R;


public class MainMenu extends Fragment {
    NavController navController = null;

    Button menuLec;
    Button menuEje;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        menuLec = view.findViewById(R.id.btnLecciones);
        menuEje = view.findViewById(R.id.btnPracticar);

        menuLec.setOnClickListener(viewl -> {
            navController.navigate(R.id.action_mainMenu_to_leccionesFrag);
        });

        menuEje.setOnClickListener(viewl -> {
            navController.navigate(R.id.action_mainMenu_to_menuEjercicios);
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_menu, container, false);
    }
}