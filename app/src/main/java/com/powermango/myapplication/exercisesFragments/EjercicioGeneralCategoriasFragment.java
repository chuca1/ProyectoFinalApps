package com.powermango.myapplication.exercisesFragments;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.powermango.myapplication.Constants;
import com.powermango.myapplication.ExercisesViewModel;
import com.powermango.myapplication.R;
import com.powermango.myapplication.exercisesDatabase.ExercisesDatabase;
import com.powermango.myapplication.exercisesDatabase.GeneralCategoriasTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.powermango.myapplication.Constants.*;
import com.powermango.myapplication.exercisesDatabase.GeneralDefinicionesTable;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EjercicioGeneralCategoriasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EjercicioGeneralCategoriasFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static final int NUMBER_OF_EXERCISES = 4;

    ExercisesViewModel viewModel;
    ExercisesDatabase database;
    TextView[] palabras;
    Spinner[] spinners;
    Spinner spinner1, spinner2, spinner3, spinner4;
    Button submitButton;

    String TITLE_FONT = "fonts/Montserrat-Medium.ttf";
    //.setTypeface(Typeface.createFromAsset(getContext().getAssets(),TITLE_FONT));

    GeneralCategoriasTable[] entries;
    String[] respuestas;

    public EjercicioGeneralCategoriasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EjercicioGeneralCategoriasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EjercicioGeneralCategoriasFragment newInstance(String param1, String param2) {
        EjercicioGeneralCategoriasFragment fragment = new EjercicioGeneralCategoriasFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        database = ExercisesDatabase.getInstance(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ejercicio_general_categorias, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Data structures initialization
        viewModel = new ViewModelProvider(getActivity()).get(ExercisesViewModel.class);
        entries = new GeneralCategoriasTable[NUMBER_OF_EXERCISES];
        palabras = new TextView[NUMBER_OF_EXERCISES];
        spinners = new Spinner[NUMBER_OF_EXERCISES];
        respuestas = new String[NUMBER_OF_EXERCISES];

        // TextView initialization
        palabras[0] = (TextView) getView().findViewById(R.id.palabra1);
        palabras[0].setTypeface(Typeface.createFromAsset(getContext().getAssets(),TITLE_FONT));
        palabras[1] = (TextView) getView().findViewById(R.id.palabra2);
        palabras[1].setTypeface(Typeface.createFromAsset(getContext().getAssets(),TITLE_FONT));
        palabras[2] = (TextView) getView().findViewById(R.id.palabra3);
        palabras[2].setTypeface(Typeface.createFromAsset(getContext().getAssets(),TITLE_FONT));
        palabras[3] = (TextView) getView().findViewById(R.id.palabra4);
        palabras[3].setTypeface(Typeface.createFromAsset(getContext().getAssets(),TITLE_FONT));

        // Spinner initialization
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.general_categorias_respuestas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinners[0] = (Spinner) getView().findViewById(R.id.spinner1);
        spinners[0].setAdapter(adapter);
        spinners[1] = (Spinner) getView().findViewById(R.id.spinner2);
        spinners[1].setAdapter(adapter);
        spinners[2] = (Spinner) getView().findViewById(R.id.spinner3);
        spinners[2].setAdapter(adapter);
        spinners[3] = (Spinner) getView().findViewById(R.id.spinner4);
        spinners[3].setAdapter(adapter);

        // Button initialization
        submitButton = getView().findViewById(R.id.buttonSubmit);

        submitButton.setTypeface(Typeface.createFromAsset(getContext().getAssets(),TITLE_FONT));

        // Get randomly selected entries from correspondent table
        for (int i = 0, size = database.getGeneralCategoriasDao().selectCountAll(); i < NUMBER_OF_EXERCISES; i++) {
            int tempId = viewModel.generateRandomInt(size);
            entries[i] = database.getGeneralCategoriasDao().selectEntryById(tempId);
            palabras[i].setText(entries[i].getPalabra());
        }

        // Debug: manual entries
        //entries[0] = new GeneralCategoriasTable("palabra1", "Aguda");
        //entries[1] = new GeneralCategoriasTable("palabra2", "Grave");
        //entries[2] = new GeneralCategoriasTable("palabra3", "Esdrújula");
        //entries[3] = new GeneralCategoriasTable("palabra4", "Sobreesdrújula");

        // Fill TextViews
//        for (int i = 0; i < NUMBER_OF_EXERCISES; i++) {
//            palabras[i].setText(entries[i].getPalabra());
//        }

        // Fill answers with default text value
        Arrays.fill(respuestas, Constants.DEFAULT_TEXT_VALUE);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (evaluarEjercicio())
                {
                    viewModel.updateScoreBy1();
                    viewModel.nextFragment();
                }

            }
        });
    }

    private boolean evaluarEjercicio() {
        int correctCount = 0;

        for (int i = 0; i < NUMBER_OF_EXERCISES; i++) {
            // Get selected answers
            respuestas[i] = spinners[i].getSelectedItem().toString();
            Log.i("info", "Valor entrada: " + entries[i].getCategoria());
            Log.i("info", "Valor respuesta: " + respuestas[i]);

            if (respuestas[i].equals(entries[i].getCategoria())) {
                palabras[i].setTextColor(ContextCompat.getColor(getContext(), R.color.green_success));
                correctCount++;
            }
            else {
                palabras[i].setTextColor(ContextCompat.getColor(getContext(), R.color.red_danger));
            }
        }

        Log.i("info", "//////////////////////////");
        Toast.makeText(getContext(), "Score: " + Integer.toString(correctCount), Toast.LENGTH_SHORT).show();
        return (correctCount == NUMBER_OF_EXERCISES);
    }
}