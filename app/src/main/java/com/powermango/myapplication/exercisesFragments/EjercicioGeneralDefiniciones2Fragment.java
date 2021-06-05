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
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.powermango.myapplication.ExercisesViewModel;
import com.powermango.myapplication.R;
import com.powermango.myapplication.exercisesDatabase.ExercisesDatabase;
import com.powermango.myapplication.exercisesDatabase.GeneralDefinicionesTable;
import static com.powermango.myapplication.Constants.SCORE_DECREMENT;
import static com.powermango.myapplication.Constants.SCORE_INITIAL;
import static com.powermango.myapplication.Constants.TOAST_CORRECT_ANSWER;
import static com.powermango.myapplication.Constants.TOAST_WRONG_ANSWER;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EjercicioGeneralDefiniciones2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EjercicioGeneralDefiniciones2Fragment extends Fragment {

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
    TextView textViewScore;
    RadioGroup radioGroupRespuestas;
    Button submitButton;

    double score = SCORE_INITIAL;

    RadioButton previousRadioButton;

    String TITLE_FONT = "fonts/Montserrat-Medium.ttf";
    //.setTypeface(Typeface.createFromAsset(getContext().getAssets(),TITLE_FONT));

    public EjercicioGeneralDefiniciones2Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EjercicioGeneralDefiniciones2Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EjercicioGeneralDefiniciones2Fragment newInstance(String param1, String param2) {
        EjercicioGeneralDefiniciones2Fragment fragment = new EjercicioGeneralDefiniciones2Fragment();
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
        return inflater.inflate(R.layout.fragment_ejercicio_general_definiciones2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(ExercisesViewModel.class);

        textViewPrompt = getView().findViewById(R.id.textViewPrompt);
        textViewScore = getView().findViewById(R.id.textViewPuntosHolder);
        radioGroupRespuestas = getView().findViewById(R.id.radioGroupRespuestas);
        previousRadioButton = null;
        submitButton = getView().findViewById(R.id.buttonSubmit);

        textViewPrompt.setTypeface(Typeface.createFromAsset(getContext().getAssets(),TITLE_FONT));
        submitButton.setTypeface(Typeface.createFromAsset(getContext().getAssets(),TITLE_FONT));

        int tempId = viewModel.generateRandomInt(database.getGeneralDefinicionesDao().selectCountAll());
        entry = database.getGeneralDefinicionesDao().selectEntryById(tempId);

        // Debug
        //entry = new GeneralDefinicionesTable("Agudas", "Cuando terminan en N, S o vocal");

        // Set prompt text
        String concepto = entry.getConcepto().toLowerCase();
        String promptText = getString(R.string.general_definiciones_2_prompt_1) + " " + concepto + " " + getString(R.string.general_definiciones_2_prompt_2);
        textViewPrompt.setText(promptText);

        textViewScore.setText(Integer.toString((int) score));

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (evaluarEjercicio()) {
                    viewModel.addScore(score);
                    viewModel.nextFragment();
                }
            }
        });
    }

    private boolean evaluarEjercicio() {
        if (previousRadioButton != null)
            previousRadioButton.setTextColor(ContextCompat.getColor(getContext(), R.color.black));

        RadioButton currentRadioButton = getView().findViewById(radioGroupRespuestas.getCheckedRadioButtonId());

        if (currentRadioButton == null)
            return false;

        if (!entry.getDefinicion().equals(currentRadioButton.getText().toString())) {
            Log.i("info", "Definición: " + entry.getDefinicion());
            Log.i("info", "Radio: " + currentRadioButton.getText().toString());
            currentRadioButton.setTextColor(ContextCompat.getColor(getContext(), R.color.red_danger));
            previousRadioButton = currentRadioButton;
            updateScore();
            return false;
        }

        Toast.makeText(getContext(), TOAST_CORRECT_ANSWER, Toast.LENGTH_SHORT).show();
        currentRadioButton.setTextColor(ContextCompat.getColor(getContext(), R.color.green_success));
        return true;
    }

    private void updateScore() {
        Log.i("info", "Actualiza score");
        Toast.makeText(getContext(), TOAST_WRONG_ANSWER, Toast.LENGTH_SHORT).show();

        if (score > 0) {
            score -= SCORE_DECREMENT;

            if (score == 0) {
                textViewScore.setTextColor(ContextCompat.getColor(getContext(), R.color.red_danger));
                textViewScore.setText(Integer.toString((int) score));
            }
            else
                textViewScore.setText(Double.toString(score));
        }
    }
}