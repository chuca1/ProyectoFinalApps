package com.powermango.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.powermango.myapplication.exercisesFragments.GeneralCategorias;
import com.powermango.myapplication.exercisesFragments.GeneralDefiniciones1;
import com.powermango.myapplication.exercisesFragments.GeneralDefiniciones2;
import static com.powermango.myapplication.Constants.*;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class ExercisesActivity extends AppCompatActivity {
    ExercisesViewModel viewModel;
    FragmentManager fragmentManager;
    ArrayList<Fragment> exercises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        viewModel = new ViewModelProvider(this).get(ExercisesViewModel.class);
        fragmentManager = getSupportFragmentManager();
        exercises = new ArrayList<>();

        // Agrega número determinado de ejercicios al arreglo
        for (int i = 0; i < EXERCISES_ARRAY_SIZE; i++) {
            int randomInt = ThreadLocalRandom.current().nextInt(0, EXERCISES_AVAILABLE + 1);
            //Log.i("info", "Número obtenido: " + Integer.toString(randomInt));
            ExerciseType exerciseType = ExerciseType.valueOf(randomInt);

            switch (exerciseType) {
                case GENERAL_CATEGORIAS:
                    exercises.add(GeneralCategorias.newInstance("", ""));
                    break;
                case GENERAL_DEFINICIONES_1:
                    exercises.add(GeneralDefiniciones1.newInstance("", ""));
                    break;
                case GENERAL_DEFINICIONES_2:
                    exercises.add(GeneralDefiniciones2.newInstance("", ""));
                    break;
            }
        }

        viewModel.getCurrentFragment().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Integer currentFragment = viewModel.getCurrentFragment().getValue();
                Toast.makeText(getApplicationContext(), "Se ha cambiado de fragment " + Integer.toString(currentFragment), Toast.LENGTH_SHORT).show();

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);

                if (fragment == null) {
                    fragmentTransaction.add(R.id.fragmentContainer, exercises.get(currentFragment)).commit();
                }
                else {
                    fragmentTransaction.replace(R.id.fragmentContainer, exercises.get(currentFragment)).commit();
                }
            }
        });
    }
}