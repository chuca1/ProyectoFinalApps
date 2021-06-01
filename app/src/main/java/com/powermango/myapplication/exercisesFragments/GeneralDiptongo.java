package com.powermango.myapplication.exercisesFragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.powermango.myapplication.ExercisesViewModel;
import com.powermango.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GeneralDiptongo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GeneralDiptongo extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public int counter = 60;
    public int palabraNumero = 0;

    public String[] palabras = {
            "rocío",
            "pasión",
            "propia",
            "ideas",
            "autor",
            "teórico",
            "incluir",
            "poemas",
            "caído",
            "actúa",
            "actuar",
            "valían",
            "reía",
            "ruido"
    };

    public Boolean[] resultados = {
            true,
            false,
            false,
            true,
            false,
            true,
            false,
            true,
            true,
            true,
            false,
            true,
            true,
            true
    };
    ExercisesViewModel viewModel;
    public GeneralDiptongo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GeneralDiptongo.
     */
    // TODO: Rename and change types and number of parameters
    public static GeneralDiptongo newInstance(String param1, String param2) {
        GeneralDiptongo fragment = new GeneralDiptongo();
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
        TextView timer = (TextView) getView().findViewById(R.id.timer);
        TextView palabra = (TextView) getView().findViewById(R.id.palabra);
        Button empezar = (Button) getView().findViewById(R.id.empezar);
        Button diptongo = (Button) getView().findViewById(R.id.diptongo);
        Button hiato = (Button) getView().findViewById(R.id.hiato);
        Button submitButton = (Button) getView().findViewById(R.id.submit);
        empezar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                diptongo.setVisibility(View.VISIBLE);
                hiato.setVisibility(View.VISIBLE);
                empezar.setVisibility(View.GONE);
                palabra.setText(palabras[palabraNumero]);
                new CountDownTimer(60000, 1000){
                    public void onTick(long millisUntilFinished){
                        timer.setText(String.valueOf(counter));
                        counter--;
                    }
                    public  void onFinish(){
                        diptongo.setVisibility(View.GONE);
                        hiato.setVisibility(View.GONE);
                        palabra.setText("Se acabo");
                        submitButton.setVisibility(View.VISIBLE);
                    }
                }.start();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_general_diptongo, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Toast.makeText(getContext(), "El fragmento se ha creado", Toast.LENGTH_SHORT).show();
        Button diptongo = (Button) getView().findViewById(R.id.diptongo);
        Button hiato = (Button) getView().findViewById(R.id.hiato);
        Button submitButton = (Button) getView().findViewById(R.id.submit);
        final TextView[] puntuacionText = {(TextView) getView().findViewById(R.id.puntos)};
        TextView palabra = (TextView) getView().findViewById(R.id.palabra) ;
        diptongo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                palabraNumero++;
                palabra.setText(palabras[palabraNumero]);
                if (!resultados[palabraNumero]) {
                    int puntos = Integer.parseInt(puntuacionText[0].getText().toString()) + 1;
                    puntuacionText[0].setText(String.valueOf(puntos));
                }

            }
        });

        hiato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                palabraNumero++;
                palabra.setText(palabras[palabraNumero]);
                if (resultados[palabraNumero]) {
                    int puntos = Integer.parseInt(puntuacionText[0].getText().toString()) + 1;
                    puntuacionText[0].setText(String.valueOf(puntos));
                }

            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    viewModel = new ViewModelProvider(getActivity()).get(ExercisesViewModel.class);
                    viewModel.nextFragment();
            }
        });
    }
}