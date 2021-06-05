package com.powermango.myapplication.menuFragments;

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


public class creditandhelp extends Fragment {

    private TextView text;
    private TextView title;
    private  String TAG_BUND = "MAINIB";
    String TEXT_FONT = "fonts/Montserrat-SemiBold.ttf";
    String TITLE_FONT = "fonts/Montserrat-Medium.ttf";
    Context context;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }
    public creditandhelp() {
        // Required empty public constructor
    }


    public static creditandhelp newInstance(String param1, String param2) {
        creditandhelp fragment = new creditandhelp();
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
        title = view.findViewById(R.id.tvTitleIbutton);
        title.setTypeface(Typeface.createFromAsset(context.getAssets(),TITLE_FONT));
        if(getArguments() != null){


            switch(getArguments().getInt(TAG_BUND)){
                case 1:{
                    text = view.findViewById(R.id.tvTxTibutton);
                    text.setText(getString(R.string.creditos).toString());
                    text.setTypeface(Typeface.createFromAsset(context.getAssets(),TEXT_FONT));
                    break;
                }
                case 2:{
                    text = view.findViewById(R.id.tvTxTibutton);
                    text.setText(getString(R.string.help_main_menu).toString());
                    text.setTypeface(Typeface.createFromAsset(context.getAssets(),TEXT_FONT));
                    break;
                }
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_creditandhelp, container, false);
    }
}