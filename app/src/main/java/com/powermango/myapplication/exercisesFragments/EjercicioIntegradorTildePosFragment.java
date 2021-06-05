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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.powermango.myapplication.ExercisesViewModel;
import com.powermango.myapplication.R;
import com.powermango.myapplication.exercisesDatabase.ExercisesDatabase;
import com.powermango.myapplication.exercisesDatabase.IntegradorTable;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EjercicioIntegradorTildePosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EjercicioIntegradorTildePosFragment extends Fragment {
    private static final int SI = 1;
    private static final int NO = 0;
    private static final int ULTIMA = 1;
    private static final int PENULTIMA = 2;
    private static final int ANTEPENULTIMA = 3;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ExercisesViewModel viewModel;
    ExercisesDatabase database;
    IntegradorTable entry;

    TextView textViewPalabra;
    TextView textViewLlevaTilde;
    TextView textViewSilabaQueLoLleva;
    RadioGroup radioGroupTilde;
    RadioButton previousRadioButton;
    Spinner spinnerSilaba;
    Button buttonSubmit;

    String TITLE_FONT = "fonts/Montserrat-Medium.ttf";
    //.setTypeface(Typeface.createFromAsset(getContext().getAssets(),TITLE_FONT));

    public EjercicioIntegradorTildePosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EjercicioIntegradorTildePosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EjercicioIntegradorTildePosFragment newInstance(String param1, String param2) {
        EjercicioIntegradorTildePosFragment fragment = new EjercicioIntegradorTildePosFragment();
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
        return inflater.inflate(R.layout.fragment_ejercicio_integrador_tilde_pos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(getActivity()).get(ExercisesViewModel.class);;

        textViewPalabra = getView().findViewById(R.id.textViewPalabra);
        textViewLlevaTilde = getView().findViewById(R.id.textViewLlevaTilde);
        textViewSilabaQueLoLleva = getView().findViewById(R.id.textViewSilabaQueLoLleva);
        radioGroupTilde = getView().findViewById(R.id.radioGroupTilde);
        previousRadioButton = null;
        buttonSubmit = getView().findViewById(R.id.buttonSubmit);

        //int tempId = viewModel.generateRandomInt(database.getIntegradorDao().selectCountAll());
        //entry = database.getIntegradorDao().selectEntryById(tempId);
        entry = new IntegradorTable("Hola", 0, 0);

        textViewLlevaTilde.setTypeface(Typeface.createFromAsset(getContext().getAssets(),TITLE_FONT));
        textViewPalabra.setTypeface(Typeface.createFromAsset(getContext().getAssets(),TITLE_FONT));
        textViewSilabaQueLoLleva.setTypeface(Typeface.createFromAsset(getContext().getAssets(),TITLE_FONT));
        buttonSubmit.setTypeface(Typeface.createFromAsset(getContext().getAssets(),TITLE_FONT));

        textViewPalabra.setText(entry.getPalabra());

        spinnerSilaba = getView().findViewById(R.id.spinnerSilaba);
        ArrayAdapter<CharSequence> adapterSilaba  = ArrayAdapter.createFromResource(getContext(), R.array.integrador_silaba_respuestas, android.R.layout.simple_spinner_item);
        adapterSilaba.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSilaba.setAdapter(adapterSilaba);

        radioGroupTilde.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButtonSi) {
                    textViewSilabaQueLoLleva.setVisibility(View.VISIBLE);
                    spinnerSilaba.setVisibility(View.VISIBLE);
                }
                else if (checkedId == R.id.radioButtonNo) {
                    textViewSilabaQueLoLleva.setVisibility(View.INVISIBLE);
                    spinnerSilaba.setVisibility(View.INVISIBLE);
                }
            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (evaluarEjercicio()) {
                    viewModel.updateScoreBy1();
                    viewModel.nextFragment();
                }
            }
        });
    }

    private boolean evaluarEjercicio() {
        if (previousRadioButton != null)
            previousRadioButton.setTextColor(ContextCompat.getColor(getContext(), R.color.black));

        RadioButton currentRadioButton = getView().findViewById(radioGroupTilde.getCheckedRadioButtonId());
        String llevaTilde = currentRadioButton.getText().toString();
        String silaba = spinnerSilaba.getSelectedItem().toString();

        if (entry.getLlevaTilde() == SI) {
            if (!llevaTilde.equals("Sí")) {
                currentRadioButton.setTextColor(ContextCompat.getColor(getContext(), R.color.red_danger));
                return false;
            }

            switch (entry.getPosicion()) {
                case NO:
                    return false;
                case ULTIMA:
                    if (!silaba.equals("Última")) {
                        return false;
                    }
                    break;
                case PENULTIMA:
                    if (!silaba.equals("Penúltima")) {
                        return false;
                    }
                    break;
                case ANTEPENULTIMA:
                    if (!silaba.equals("Antepenúltima")) {
                        return false;
                    }
                        break;
            }
        }
        else if (entry.getLlevaTilde() == NO) {
            if (!llevaTilde.equals("No")) {
                Log.i("info", "RadioButton: " + llevaTilde);
                currentRadioButton.setTextColor(ContextCompat.getColor(getContext(), R.color.red_danger));
                return false;
            }
        }

        return true;
    }
}