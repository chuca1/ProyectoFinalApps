package com.powermango.myapplication.exercisesDatabase;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface GeneralCategoriasDao {
    @Query("select * from general_categorias")
    List<GeneralCategoriasTable> selectAllEntries();

    @Query("select * from general_categorias where ID = :ID")
    GeneralCategoriasTable selectEntryById(int ID);

    @Query("select count(*) from general_categorias")
    int selectCountAll();
}
