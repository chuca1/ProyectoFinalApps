package com.powermango.myapplication.exercisesDatabase;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface IntegradorDao {
    @Query("select * from integrador")
    List<IntegradorTable> selectAllEntries();

    @Query("select * from integrador where ID = :ID")
    IntegradorTable selectEntryById(int ID);
}
