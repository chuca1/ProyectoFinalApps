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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.powermango.myapplication.ExercisesViewModel;
import com.powermango.myapplication.R;
import com.powermango.myapplication.exercisesDatabase.EspecialesInterrogativosExclamativosTable;
import com.powermango.myapplication.exercisesDatabase.EspecialesMonosilabosTable;
import com.powermango.myapplication.exercisesDatabase.ExercisesDatabase;

import static com.powermango.myapplication.Constants.SCORE_DECREMENT;
import static com.powermango.myapplication.Constants.SCORE_INITIAL;
import static com.powermango.myapplication.Constants.TOAST_CORRECT_ANSWER;
import static com.powermango.myapplication.Constants.TOAST_WRONG_ANSWER;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EjercicioEspecialesMonosilabosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EjercicioEspecialesMonosilabosFragment extends Fragment {
    private static final int NUMBER_OF_EXERCISES = 2;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ExercisesViewModel viewModel;
    ExercisesDatabase database;
    EspecialesMonosilabosTable[] entries;

    TextView textViewOracion1, textViewOracion2;
    TextView textViewScore;
    Spinner spinner1, spinner2;
    Button buttonSubmit;

    double score;

    public EjercicioEspecialesMonosilabosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EjercicioEspecialesMonosilabosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EjercicioEspecialesMonosilabosFragment newInstance(String param1, String param2) {
        EjercicioEspecialesMonosilabosFragment fragment = new EjercicioEspecialesMonosilabosFragment();
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
        return inflater.inflate(R.layout.fragment_ejercicio_especiales_monosilabos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(getActivity()).get(ExercisesViewModel.class);

        textViewOracion1 = getView().findViewById(R.id.textViewOracion1);
        textViewOracion2 = getView().findViewById(R.id.textViewOracion2);
        textViewScore = getView().findViewById(R.id.textViewPuntosHolder);
        buttonSubmit = getView().findViewById(R.id.buttonSubmit);

        entries = new EspecialesMonosilabosTable[NUMBER_OF_EXERCISES];

        int tempId = viewModel.generateRandomInt(database.getEspecialesMonosilabosDao().selectCountAll());
        entries[0] = database.getEspecialesMonosilabosDao().selectEntryById(tempId);
        tempId = viewModel.generateRandomInt(database.getEspecialesMonosilabosDao().selectCountAll());
        entries[1] = database.getEspecialesMonosilabosDao().selectEntryById(tempId);

        //entries[0] = new EspecialesMonosilabosTable("Hola mundo", "Que", "Qué", "Que");
        //entries[1] = new EspecialesMonosilabosTable("Hello world", "Cómo", "Como", "Cómo");

        score = SCORE_INITIAL;

        textViewOracion1.setText(entries[0].getOracion());
        textViewOracion2.setText(entries[1].getOracion());
        textViewScore.setText(Integer.toString((int) score));

        String[] array1 = {entries[0].getOpcion1(), entries[0].getOpcion2()};
        String[] array2 = {entries[1].getOpcion1(), entries[1].getOpcion2()};

        spinner1 = getView().findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, array1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        spinner2 = getView().findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, array2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);


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
        int correctCount = 0;
        String respuesta1 = spinner1.getSelectedItem().toString();
        String respuesta2 = spinner2.getSelectedItem().toString();

        if (respuesta1.equals(entries[0].getOpcionCorrecta())) {
            correctCount++;
        }

        if (respuesta2.equals(entries[1].getOpcionCorrecta())) {
            correctCount++;
        }

        if (correctCount == NUMBER_OF_EXERCISES) {
            Toast.makeText(getContext(), TOAST_CORRECT_ANSWER, Toast.LENGTH_SHORT).show();
            return true;
        }
        else {
            updateScore();
            return false;
        }
    }

    private void updateScore() {
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