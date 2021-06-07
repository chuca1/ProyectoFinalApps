package com.powermango.myapplication.exercisesDatabase;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface GeneralDefiniciones1Dao {
    @Query("select * from general_definiciones1")
    List<GeneralDefinicionesTable> selectAllEntries();

    @Query("select * from general_definiciones1 where ID = :ID")
    GeneralDefinicionesTable selectEntryById(int ID);

    @Query("select count(*) from general_definiciones1")
    int selectCountAll();
}
