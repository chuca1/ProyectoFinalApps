package com.powermango.myapplication.exercisesDatabase;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "general_categorias")
public class GeneralCategorias {
    @PrimaryKey(autoGenerate = true)
    private int idPalabra;
    @NonNull
    private String palabra;
    @NonNull
    private String valor;

    public GeneralCategorias(String palabra, String valor) {
        this.palabra = palabra;
        this.valor = valor;
    }

    public int getIdPalabra() {
        return idPalabra;
    }

    public void setIdPalabra(int idPalabra) {
        this.idPalabra = idPalabra;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
