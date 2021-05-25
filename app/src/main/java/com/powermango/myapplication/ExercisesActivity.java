package com.powermango.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.powermango.myapplication.exercisesFragments.GeneralCategorias;
import com.powermango.myapplication.exercisesFragments.GeneralDefiniciones1;
import com.powermango.myapplication.exercisesFragments.GeneralDefiniciones2;

import java.util.LinkedList;

public class ExercisesActivity extends AppCompatActivity {
    ExercisesViewModel viewModel;
    FragmentManager fragmentManager;
    LinkedList<Fragment> exercises;
    Fragment currentExercise;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        viewModel = new ViewModelProvider(this).get(ExercisesViewModel.class);
        fragmentManager = getSupportFragmentManager();
        exercises = new LinkedList<Fragment>();

        // TEST: los fragments se agregan manualmente
        exercises.add(GeneralCategorias.newInstance("", ""));
        //exercises.add(GeneralDefiniciones1.newInstance("", ""));
        //exercises.add(GeneralDefiniciones2.newInstance("", ""));

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        GeneralCategorias fragment = (GeneralCategorias) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);

        if (fragment == null) {
            fragmentTransaction.add(R.id.fragmentContainer, exercises.getFirst()).commit();
        }

        viewModel.getCurrentFragment().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {

            }
        });
    }
}