package com.powermango.myapplication.exercisesDatabase;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface GeneralDefinicionesDao {
    @Query("select * from general_definiciones")
    List<GeneralDefinicionesTable> selectAllEntries();

    @Query("select * from general_definiciones where ID = :ID")
    GeneralDefinicionesTable selectEntryById(int ID);

    @Query("select count(*) from general_definiciones")
    int selectCountAll();
}
