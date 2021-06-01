package com.powermango.myapplication.exercisesDatabase;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EspecialesMonosilabosDao {
    @Query("select * from especiales_monosilabos")
    List<EspecialesMonosilabosTable> selectAllEntries();

    @Query("select * from especiales_monosilabos where ID = :ID")
    EspecialesMonosilabosTable selectEntryById(int ID);

    @Query("select count(*) from especiales_monosilabos")
    int selectCountAll();
}
