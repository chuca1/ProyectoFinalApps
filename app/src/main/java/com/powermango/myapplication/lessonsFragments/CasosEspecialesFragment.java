package com.powermango.myapplication.lessonsFragments;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.powermango.myapplication.R;


public class CasosEspecialesFragment extends Fragment {
    TextView title;
    TextView desc;
    String TEXT_FONT = "fonts/Montserrat-SemiBold.ttf";
    String TITLE_FONT = "fonts/Montserrat-Medium.ttf";
    Context context;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }
    public CasosEspecialesFragment() {
        // Required empty public constructor
    }


    public static CasosEspecialesFragment newInstance(String param1, String param2) {
        CasosEspecialesFragment fragment = new CasosEspecialesFragment();
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        title = view.findViewById(R.id.tvLecDescTitle);
        desc = view.findViewById(R.id.txtDescrip);
        title.setTypeface(Typeface.createFromAsset(context.getAssets(),TITLE_FONT));
        desc.setTypeface(Typeface.createFromAsset(context.getAssets(),TEXT_FONT));


        desc.setText(getString(R.string.casosEspeciales_desc));
        title.setText(getString(R.string.casosEspeciales_titulo));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_casos_especiales, container, false);
    }
}