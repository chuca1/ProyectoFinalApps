package com.powermango.myapplication.exercisesDatabase;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "especiales_interrogativos_exclamativos")
public class EspecialesInterrogativosExclamativosTable {
    @PrimaryKey(autoGenerate = true)
    private int ID;
    @NonNull
    private String oracion;
    @NonNull
    private String opcion1;
    @NonNull
    private String opcion2;
    @NonNull
    private String opcion_correcta;

    public EspecialesInterrogativosExclamativosTable(@NonNull String oracion, @NonNull String opcion1, @NonNull String opcion2, @NonNull String opcion_correcta) {
        this.oracion = oracion;
        this.opcion1 = opcion1;
        this.opcion2 = opcion2;
        this.opcion_correcta = opcion_correcta;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @NonNull
    public String getOracion() {
        return oracion;
    }

    public void setOracion(@NonNull String oracion) {
        this.oracion = oracion;
    }

    @NonNull
    public String getOpcion1() {
        return opcion1;
    }

    public void setOpcion1(@NonNull String opcion1) {
        this.opcion1 = opcion1;
    }

    @NonNull
    public String getOpcion2() {
        return opcion2;
    }

    public void setOpcion2(@NonNull String opcion2) {
        this.opcion2 = opcion2;
    }

    @NonNull
    public String getOpcion_correcta() {
        return opcion_correcta;
    }

    public void setOpcion_correcta(@NonNull String opcion_correcta) {
        this.opcion_correcta = opcion_correcta;
    }
}
