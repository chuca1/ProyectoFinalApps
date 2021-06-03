package com.powermango.myapplication.exercisesDatabase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "general_categorias")
public class GeneralCategoriasTable {
    @PrimaryKey(autoGenerate = true)
    private int ID;
    @NonNull
    private String palabra;
    @NonNull
    private String categoria;

    public GeneralCategoriasTable(@NonNull String palabra, @NonNull String categoria) {
        this.palabra = palabra;
        this.categoria = categoria;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @NonNull
    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(@NonNull String palabra) {
        this.palabra = palabra;
    }

    @NonNull
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(@NonNull String categoria) {
        this.categoria = categoria;
    }
}
