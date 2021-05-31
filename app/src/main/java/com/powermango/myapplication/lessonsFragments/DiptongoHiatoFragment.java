package com.powermango.myapplication.lessonsFragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.powermango.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DiptongoHiatoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiptongoHiatoFragment extends Fragment {

    TextView title;
    TextView desc;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DiptongoHiatoFragment() {
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
    public static DiptongoHiatoFragment newInstance(String param1, String param2) {
        DiptongoHiatoFragment fragment = new DiptongoHiatoFragment();
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        title = view.findViewById(R.id.tvLecDescTitle);
        desc = view.findViewById(R.id.txtDescrip);
        desc.setText(getString(R.string.hiatoDesc));
        title.setText(getString(R.string.hiatoTitulo));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_diptongo_hiato, container, false);
    }
}