package com.powermango.myapplication;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ExercisesViewModel extends ViewModel {
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
}