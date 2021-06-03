package com.powermango.myapplication;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.powermango.myapplication.exercisesDatabase.ExercisesDatabase;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ExercisesViewModel extends ViewModel {
    private ExercisesDatabase database;
    private MutableLiveData<Integer> currentFragment;
    private int score;

    public ExercisesViewModel() {
        currentFragment = new MutableLiveData<>();
        currentFragment.setValue(0);
        score = 0;
    }

    public LiveData<Integer> getCurrentFragment() {
        return currentFragment;
    }

    public void nextFragment() {
        currentFragment.setValue(currentFragment.getValue() + 1);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addScore(int points) {
        setScore(getScore() + points);
    }

    public void updateScoreBy1() {setScore(getScore() + 1);}

    public int generateRandomInt(int max) {
        int random = new Random().nextInt(max) + 1;
        return random;
        //return ThreadLocalRandom.current().nextInt(1, max + 1);
    }
}