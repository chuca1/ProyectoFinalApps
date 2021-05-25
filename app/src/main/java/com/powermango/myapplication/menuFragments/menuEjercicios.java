package com.powermango.myapplication.menuFragments;

import android.content.Intent;
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

import com.powermango.myapplication.ExercisesActivity;
import com.powermango.myapplication.MainActivity;
import com.powermango.myapplication.Principa;
import com.powermango.myapplication.R;


public class menuEjercicios extends Fragment {

    NavController navController = null;

    Button modoClas;
    Button modoContraT;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        modoClas = view.findViewById(R.id.bEjerciciosNormal);
        modoContraT = view.findViewById(R.id.bContraTiempo);

        modoClas.setOnClickListener(viewl -> {
            startExercises();
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu_ejercicios_frag, container, false);
    }

    private void startExercises() {
        Intent intent = new Intent(getContext(), ExercisesActivity.class);
        startActivity(intent);
    }
}