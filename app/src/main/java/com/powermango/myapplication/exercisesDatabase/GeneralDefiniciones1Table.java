package com.powermango.myapplication.exercisesDatabase;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "general_definiciones1")
public class GeneralDefiniciones1Table {
    @PrimaryKey(autoGenerate = true)
    private int ID;
    @NonNull
    private String concepto;
    @NonNull
    private String definicion;

    public GeneralDefiniciones1Table(String concepto, String definicion) {
        this.concepto = concepto;
        this.definicion = definicion;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @NonNull
    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(@NonNull String concepto) {
        this.concepto = concepto;
    }

    @NonNull
    public String getDefinicion() {
        return definicion;
    }

    public void setDefinicion(@NonNull String definicion) {
        this.definicion = definicion;
    }
}
