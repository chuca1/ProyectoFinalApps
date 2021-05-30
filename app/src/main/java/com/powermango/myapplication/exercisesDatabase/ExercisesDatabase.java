package com.powermango.myapplication.exercisesDatabase;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {GeneralCategorias.class}, version = 1)
public abstract class ExercisesDatabase extends RoomDatabase {
    // Definir Dao
    public abstract  GeneralCategoriasDao getGeneralCategoriasDao();

    private static ExercisesDatabase INSTANCE;

    public static synchronized ExercisesDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = buildDatabaseInstance(context);
        }

        return INSTANCE;
    }

    private static ExercisesDatabase buildDatabaseInstance(Context context) {
        Log.i("info", "Construyendo la base de datos");
        //return Room.databaseBuilder(context, ExercisesDatabase.class, "exercises.db").allowMainThreadQueries().build();
        return Room.databaseBuilder(context, ExercisesDatabase.class, "exercises.db").allowMainThreadQueries().createFromAsset("database/exercises.db").build();
    }
}
