package com.powermango.myapplication.exercisesDatabase;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {
        GeneralCategoriasTable.class,
        GeneralDefinicionesTable.class,
        GeneralDefiniciones1Table.class,
        DiptongoHiatoTable.class,
        EspecialesInterrogativosExclamativosTable.class,
        EspecialesMonosilabosTable.class,
        //IntegradorTable.class
    }, version = 1)
public abstract class ExercisesDatabase extends RoomDatabase {
    // Definir Dao
    public abstract GeneralCategoriasDao getGeneralCategoriasDao();
    public abstract GeneralDefinicionesDao getGeneralDefinicionesDao();
    public abstract GeneralDefiniciones1Dao getGeneralDefiniciones1Dao();
    public abstract DiptongoHiatoDao getDiptongoHiatoDao();
    public abstract EspecialesInterrogativosExclamativosDao getEspecialesInterrogativosExclamativosDao();
    public abstract EspecialesMonosilabosDao getEspecialesMonosilabosDao();
    //public abstract IntegradorDao getIntegradorDao();

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
