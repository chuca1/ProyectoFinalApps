package com.powermango.myapplication.menuFragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
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
import android.widget.TextView;

import com.powermango.myapplication.ExercisesActivity;
import com.powermango.myapplication.MainActivity;
import com.powermango.myapplication.Principa;
import com.powermango.myapplication.R;


public class menuEjercicios extends Fragment {

    NavController navController = null;

    Button modoClas;
    TextView explicacion;
    //Button modoContraT;
    String BUTTON_FONT = "fonts/Montserrat-Regular.ttf";
    String TITLE_FONT = "fonts/Montserrat-Light.ttf";
    Context context;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        modoClas = view.findViewById(R.id.bEjerciciosNormal);
        //modoContraT = view.findViewById(R.id.bContraTiempo);
        TextView titulo = view.findViewById(R.id.tvTituloMenuEjercios);
        titulo.setTypeface(Typeface.createFromAsset(context.getAssets(),TITLE_FONT));
        modoClas.setTypeface(Typeface.createFromAsset(context.getAssets(), BUTTON_FONT));
        explicacion = view.findViewById(R.id.tvExplicaciónEjercios);
        explicacion.setTypeface(Typeface.createFromAsset(context.getAssets(), BUTTON_FONT));
        explicacion.setText(getString(R.string.explicacionEjercios));
        //modoContraT.setTypeface(Typeface.createFromAsset(context.getAssets(), BUTTON_FONT));
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