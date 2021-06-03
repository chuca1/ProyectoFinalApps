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

import com.powermango.myapplication.LessonsActivity;
import com.powermango.myapplication.R;

import static com.powermango.myapplication.Constants.*;


public class leccionesFrag extends Fragment {
    Button buttonReglaGeneral;
    Button buttonDiptongoHiato;
    Button buttonCasosEspeciales;
    NavController navController = null;
    String BUTTON_FONT = "fonts/Montserrat-Regular.ttf";
    String TITLE_FONT = "fonts/Montserrat-Light.ttf";
    Context context;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public leccionesFrag() {
        // Required empty public constructor
    }


    public static leccionesFrag newInstance() {
        leccionesFrag fragment = new leccionesFrag();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lecciones, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buttonReglaGeneral = view.findViewById(R.id.buttonReglaGeneral);
        buttonDiptongoHiato = view.findViewById(R.id.buttonDiptongoHiato);
        buttonCasosEspeciales = view.findViewById(R.id.buttonCasosEspeciales);
        TextView titulo = view.findViewById(R.id.tvTitulo_lecciones);
        titulo.setTypeface(Typeface.createFromAsset(context.getAssets(),TITLE_FONT));
        buttonCasosEspeciales.setTypeface(Typeface.createFromAsset(context.getAssets(), BUTTON_FONT));
        buttonDiptongoHiato.setTypeface(Typeface.createFromAsset(context.getAssets(), BUTTON_FONT));
        buttonReglaGeneral.setTypeface(Typeface.createFromAsset(context.getAssets(), BUTTON_FONT));

        navController = Navigation.findNavController(view);
        buttonReglaGeneral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_leccionesFrag_to_reglaGeneralFragment);
                //startLessonFragment(LessonType.REGLA_GENERAL);

            }
        });

        buttonDiptongoHiato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_leccionesFrag_to_diptongoHiatoFragment);
                //startLessonFragment(LessonType.DIPTONGO_HIATO);
            }
        });

        buttonCasosEspeciales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_leccionesFrag_to_casosEspecialesFragment);
                //startLessonFragment(LessonType.CASOS_ESPECIALES);
            }
        });
    }

    private void startLessonFragment(LessonType lessonType) {
        Intent intent = new Intent(getActivity(), LessonsActivity.class);
        intent.putExtra("lessonType", lessonType.getValue());
        startActivity(intent);
    }
}