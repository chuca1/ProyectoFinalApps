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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.powermango.myapplication.ExercisesViewModel;
import com.powermango.myapplication.R;
import com.powermango.myapplication.exercisesDatabase.DiptongoHiatoTable;
import com.powermango.myapplication.exercisesDatabase.ExercisesDatabase;

import static com.powermango.myapplication.Constants.SCORE_DECREMENT;
import static com.powermango.myapplication.Constants.SCORE_INITIAL;
import static com.powermango.myapplication.Constants.TOAST_CORRECT_ANSWER;
import static com.powermango.myapplication.Constants.TOAST_WRONG_ANSWER;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EjercicioDiptongoHiatoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EjercicioDiptongoHiatoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ExercisesViewModel viewModel;
    ExercisesDatabase database;
    DiptongoHiatoTable entry;

    TextView textViewPalabra;
    TextView textViewScore;
    RadioGroup radioGroupDiptongoHiato;
    RadioButton previousRadioButton;
    Spinner spinnerFormadoPor, spinnerTildeEn;
    Button buttonSubmit;

    double score;

    public EjercicioDiptongoHiatoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EjercicioDiptongoHiatoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EjercicioDiptongoHiatoFragment newInstance(String param1, String param2) {
        EjercicioDiptongoHiatoFragment fragment = new EjercicioDiptongoHiatoFragment();
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
        return inflater.inflate(R.layout.fragment_ejercicio_diptongo_hiato, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(ExercisesViewModel.class);

        textViewPalabra = getView().findViewById(R.id.textViewPalabra);
        textViewScore = getView().findViewById(R.id.textViewPuntosHolder);
        radioGroupDiptongoHiato = getView().findViewById(R.id.radioGroupDiptongoHiato);
        previousRadioButton = null;
        buttonSubmit = getView().findViewById(R.id.buttonSubmit);

        score = SCORE_INITIAL;

        // Randomly selected entry
        int tempId = viewModel.generateRandomInt(database.getDiptongoHiatoDao().selectCountAll());
        entry = database.getDiptongoHiatoDao().selectEntryById(tempId);

        //entry = new DiptongoHiatoTable("Hola", "Diptongo", "FF", "O", "A", "No lleva tilde", "No lleva tilde");

        textViewPalabra.setText(entry.getPalabra());
        textViewScore.setText(Integer.toString((int) score));

        spinnerFormadoPor = getView().findViewById(R.id.spinnerFormadoPor);
        ArrayAdapter<CharSequence> adapterFormadoPor  = ArrayAdapter.createFromResource(getContext(), R.array.diptongo_hiato_formado_por_respuestas, android.R.layout.simple_spinner_item);
        adapterFormadoPor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFormadoPor.setAdapter(adapterFormadoPor);

        String arrayTildeEn[] = {entry.getTildeOpcion1(), entry.getTildeOpcion2(), entry.getTildeOpcion3()};

        spinnerTildeEn = getView().findViewById(R.id.spinnerTildeEn);
        ArrayAdapter<String> adapterTildeEn = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arrayTildeEn);
        adapterTildeEn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTildeEn.setAdapter(adapterTildeEn);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
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

        RadioButton buttonDiptongoHiato = (RadioButton) getView().findViewById(radioGroupDiptongoHiato.getCheckedRadioButtonId());

        if (buttonDiptongoHiato == null)
            return false;

        String diptongoHiato = buttonDiptongoHiato.getText().toString();
        String formadoPor = spinnerFormadoPor.getSelectedItem().toString();
        String tildeEn = spinnerTildeEn.getSelectedItem().toString();

        previousRadioButton = buttonDiptongoHiato;

        if (!diptongoHiato.equals(entry.getDiptongoHiato()))
        {
            buttonDiptongoHiato.setTextColor(ContextCompat.getColor(getContext(), R.color.red_danger));
            updateScore();
            return false;
        }

        buttonDiptongoHiato.setTextColor(ContextCompat.getColor(getContext(), R.color.green_success));

        switch (entry.getFormadoPor()) {
            case "FF":
                if (!formadoPor.equals("Dos vocales fuertes")) {
                    updateScore();
                    return false;
                }
                break;
            case "FD":
                if (!formadoPor.equals("Vocal fuerte y vocal débil")) {
                    updateScore();
                    return false;
                }
                break;
            case "DF":
                if (!formadoPor.equals("Vocal débil y vocal fuerte")) {
                    updateScore();
                    return false;
                }
                break;
            case "DD":
                if (!formadoPor.equals("Dos vocales débiles")) {
                    updateScore();
                    return false;
                }
                break;
        }

        if (!tildeEn.equals(entry.getTildeOpcionCorrecta())) {
            updateScore();
            return false;
        }

        buttonDiptongoHiato.setTextColor(ContextCompat.getColor(getContext(), R.color.green_success));
        Toast.makeText(getContext(), TOAST_CORRECT_ANSWER, Toast.LENGTH_SHORT).show();
        return true;
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