package com.powermango.myapplication.exercisesDatabase;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EspecialesInterrogativosExclamativosDao {
    @Query("select * from especiales_interrogativos_exclamativos")
    List<EspecialesInterrogativosExclamativosTable> selectAllEntries();

    @Query("select * from especiales_interrogativos_exclamativos where ID = :ID")
    EspecialesInterrogativosExclamativosTable selectEntryById(int ID);
}
