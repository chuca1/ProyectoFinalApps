package com.powermango.myapplication.exercisesDatabase;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "diptongo_hiato")
public class DiptongoHiatoTable {
    @PrimaryKey(autoGenerate = true)
    private int ID;
    @NonNull
    private String palabra;
    @NonNull
    private String diptongo_hiato;
    @NonNull
    private String formado_por;
    @NonNull
    private String tilde_en;

    public DiptongoHiatoTable(@NonNull String palabra, @NonNull String diptongo_hiato, @NonNull String formado_por, @NonNull String tilde_en) {
        this.palabra = palabra;
        this.diptongo_hiato = diptongo_hiato;
        this.formado_por = formado_por;
        this.tilde_en = tilde_en;
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
    public String getDiptongo_hiato() {
        return diptongo_hiato;
    }

    public void setDiptongo_hiato(@NonNull String diptongo_hiato) {
        this.diptongo_hiato = diptongo_hiato;
    }

    @NonNull
    public String getFormado_por() {
        return formado_por;
    }

    public void setFormado_por(@NonNull String formado_por) {
        this.formado_por = formado_por;
    }

    @NonNull
    public String getTilde_en() {
        return tilde_en;
    }

    public void setTilde_en(@NonNull String tilde_en) {
        this.tilde_en = tilde_en;
    }
}
