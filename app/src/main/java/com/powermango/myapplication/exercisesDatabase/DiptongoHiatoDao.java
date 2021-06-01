package com.powermango.myapplication.exercisesDatabase;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DiptongoHiatoDao {
    @Query("select * from diptongo_hiato")
    List<DiptongoHiatoTable> selectAllEntries();

    @Query("select * from diptongo_hiato where ID = :ID")
    DiptongoHiatoTable selectEntryById(int ID);
}
