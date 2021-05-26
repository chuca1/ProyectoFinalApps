package com.powermango.myapplication.exercisesFragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.powermango.myapplication.ExercisesViewModel;
import com.powermango.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GeneralCategorias#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GeneralCategorias extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ExercisesViewModel viewModel;
    Spinner spinner1, spinner2, spinner3, spinner4;

    Button submitButton;

    public GeneralCategorias() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GeneralCategorias.
     */
    // TODO: Rename and change types and number of parameters
    public static GeneralCategorias newInstance(String param1, String param2) {
        GeneralCategorias fragment = new GeneralCategorias();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_general_categorias, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Toast.makeText(getContext(), "El fragmento se ha creado", Toast.LENGTH_SHORT).show();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.general_categorias_respuestas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1 = getView().findViewById(R.id.spinner1);
        spinner1.setAdapter(adapter);
        spinner2 = getView().findViewById(R.id.spinner3);
        spinner2.setAdapter(adapter);
        spinner3 = getView().findViewById(R.id.spinner2);
        spinner3.setAdapter(adapter);
        spinner4 = getView().findViewById(R.id.spinner4);
        spinner4.setAdapter(adapter);
        submitButton = getView().findViewById(R.id.buttonSubmit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(), "El botón responde", Toast.LENGTH_SHORT).show();
                viewModel = new ViewModelProvider(getActivity()).get(ExercisesViewModel.class);
                viewModel.nextFragment();
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }
}