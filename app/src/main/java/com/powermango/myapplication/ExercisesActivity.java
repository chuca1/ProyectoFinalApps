package com.powermango.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.powermango.myapplication.exercisesDatabase.ExercisesDatabase;
import com.powermango.myapplication.exercisesFragments.EjercicioGeneralCategoriasFragment;
import com.powermango.myapplication.exercisesFragments.EjercicioGeneralDefiniciones1Fragment;
import com.powermango.myapplication.exercisesFragments.EjercicioGeneralDefiniciones2Fragment;
import static com.powermango.myapplication.Constants.*;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class ExercisesActivity extends AppCompatActivity {
    ExercisesViewModel viewModel;
    FragmentManager fragmentManager;
    ArrayList<Fragment> exercises;
    ExercisesDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        viewModel = new ViewModelProvider(this).get(ExercisesViewModel.class);
        fragmentManager = getSupportFragmentManager();
        exercises = new ArrayList<>();
        //database = ExercisesDatabase.getInstance(this);

        // Agrega número determinado de ejercicios al arreglo
        for (int i = 0; i < EXERCISES_ARRAY_SIZE; i++) {
            int randomInt = ThreadLocalRandom.current().nextInt(0, EXERCISES_AVAILABLE + 1);
            //Log.i("info", "Número obtenido: " + Integer.toString(randomInt));
            ExerciseType exerciseType = ExerciseType.valueOf(randomInt);

            switch (exerciseType) {
                case GENERAL_CATEGORIAS:
                    exercises.add(EjercicioGeneralCategoriasFragment.newInstance("", ""));
                    break;
                case GENERAL_DEFINICIONES_1:
                    exercises.add(EjercicioGeneralDefiniciones1Fragment.newInstance("", ""));
                    break;
                case GENERAL_DEFINICIONES_2:
                    exercises.add(EjercicioGeneralDefiniciones2Fragment.newInstance("", ""));
                    break;
            }
        }

        viewModel.getCurrentFragment().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Integer currentFragment = viewModel.getCurrentFragment().getValue();
                //Toast.makeText(getApplicationContext(), "Se ha cambiado de fragment " + Integer.toString(currentFragment), Toast.LENGTH_SHORT).show();

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