package com.powermango.myapplication.menuFragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.powermango.myapplication.R;
import com.powermango.myapplication.exercisesDatabase.ExercisesDatabase;


public class MainMenu extends Fragment {
    private ExercisesDatabase database;
    private ImageButton credit;
    private ImageButton help;
    private  String TAG_BUND = "MAINIB";
    NavController navController = null;

    Button menuLec;
    Button menuEje;
    ViewPager2 viewPage;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // PRUEBA PARA VER SI BASE DE DATOS FUNCIONA
        // PRUEBA PARA VER SI BASE DE DATOS FUNCIONA
        navController = Navigation.findNavController(view);
        //menuLec = view.findViewById(R.id.btnLecciones);
        //menuEje = view.findViewById(R.id.btnPracticar);

        /*menuLec.setOnClickListener(viewl -> {
            navController.navigate(R.id.action_mainMenu_to_leccionesFrag);
        });

        menuEje.setOnClickListener(viewl -> {
            navController.navigate(R.id.action_mainMenu_to_menuEjercicios);
        });*/
        credit = view.findViewById(R.id.ib_credit);
        credit.setOnClickListener(view1 -> {
            //Pasar info usando bundles
            Bundle data = new Bundle();
            data.putInt(TAG_BUND,1);
            navController.navigate(R.id.action_mainMenu_to_creditAndHelp,data);
        });
        help = view.findViewById(R.id.ib_help);
        help.setOnClickListener(view1 -> {
            //Pasar info usando bundles
            Bundle data = new Bundle();
            data.putInt(TAG_BUND,2);
            navController.navigate(R.id.action_mainMenu_to_creditAndHelp,data);
        });
        viewPage = view.findViewById(R.id.pager);
        viewPage.setAdapter(new ViewPageAdapter(view.getContext(),viewPage,navController));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_menu, container, false);
    }
}