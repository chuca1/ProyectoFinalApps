package com.powermango.myapplication.exercisesDatabase;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface GeneralCategoriasDao {
    @Query("select * from general_categorias")
    List<GeneralCategorias> selectAllWords();

    @Query("select * from general_categorias where idPalabra = :idPalabra")
    GeneralCategorias selectWordById(int idPalabra);
}
