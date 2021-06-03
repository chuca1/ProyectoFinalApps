package com.powermango.myapplication.exercisesFragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.powermango.myapplication.ExercisesViewModel;
import com.powermango.myapplication.R;
import com.powermango.myapplication.exercisesDatabase.ExercisesDatabase;
import com.powermango.myapplication.exercisesDatabase.GeneralDefinicionesTable;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EjercicioGeneralDefiniciones1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EjercicioGeneralDefiniciones1Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ExercisesViewModel viewModel;
    ExercisesDatabase database;
    GeneralDefinicionesTable entry;

    TextView textViewPrompt;
    RadioGroup radioGroupRespuestas;
    Button submitButton;

    RadioButton previousRadioButton;

    public EjercicioGeneralDefiniciones1Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EjercicioGeneralDefiniciones1Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EjercicioGeneralDefiniciones1Fragment newInstance(String param1, String param2) {
        EjercicioGeneralDefiniciones1Fragment fragment = new EjercicioGeneralDefiniciones1Fragment();
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
        return inflater.inflate(R.layout.fragment_ejercicio_general_definiciones1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(ExercisesViewModel.class);

        textViewPrompt = getView().findViewById(R.id.textViewPrompt);
        radioGroupRespuestas = getView().findViewById(R.id.radioGroupRespuestas);
        previousRadioButton = null;
        submitButton = getView().findViewById(R.id.buttonSubmit);

        int tempId = viewModel.generateRandomInt(database.getGeneralDefinicionesDao().selectCountAll());
        entry = database.getGeneralDefinicionesDao().selectEntryById(tempId);

        // Debug
        //entry = new GeneralDefinicionesTable("Agudas", "Cuando terminan en N, S o vocal");

        // Set prompt text
        String definicion = entry.getDefinicion().substring(0, 1).toLowerCase() + entry.getDefinicion().substring(1);
        String promptText = getString(R.string.general_definiciones_1_prompt) + " " + definicion + ":";
        textViewPrompt.setText(promptText);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (evaluarEjercicio())
                    viewModel.nextFragment();
            }
        });
    }

    private boolean evaluarEjercicio() {
        if (previousRadioButton != null)
            previousRadioButton.setTextColor(ContextCompat.getColor(getContext(), R.color.black));

        RadioButton currentRadioButton = getView().findViewById(radioGroupRespuestas.getCheckedRadioButtonId());

        if (!entry.getConcepto().equals(currentRadioButton.getText().toString())) {
            currentRadioButton.setTextColor(ContextCompat.getColor(getContext(), R.color.red_danger));
            previousRadioButton = currentRadioButton;
            return false;
        }

        currentRadioButton.setTextColor(ContextCompat.getColor(getContext(), R.color.green_success));
        return true;
    }
}