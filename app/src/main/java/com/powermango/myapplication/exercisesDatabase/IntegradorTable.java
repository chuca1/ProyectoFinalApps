package com.powermango.myapplication.exercisesDatabase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "integrador")
public class IntegradorTable {
    @PrimaryKey(autoGenerate = true)
    private int ID;
    @NonNull
    private String palabra;
    @NonNull
    @ColumnInfo(name = "lleva_tilde")
    private int llevaTilde;
    @NonNull
    private int posicion;

    public IntegradorTable(@NonNull String palabra, int llevaTilde, int posicion) {
        this.palabra = palabra;
        this.llevaTilde = llevaTilde;
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

    public int getLlevaTilde() {
        return llevaTilde;
    }

    public void setLlevaTilde(int llevaTilde) {
        this.llevaTilde = llevaTilde;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
}
