package com.powermango.myapplication.exercisesDatabase;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "integrador")
public class IntegradorTable {
    @PrimaryKey(autoGenerate = true)
    private int ID;
    @NonNull
    private String palabra;
    @NonNull
    private int lleva_tilde;
    @NonNull
    private int posicion;

    public IntegradorTable(@NonNull String palabra, int lleva_tilde, int posicion) {
        this.palabra = palabra;
        this.lleva_tilde = lleva_tilde;
        this.posicion = posicion;
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

    public int getLleva_tilde() {
        return lleva_tilde;
    }

    public void setLleva_tilde(int lleva_tilde) {
        this.lleva_tilde = lleva_tilde;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
}
