package com.powermango.myapplication.exercisesDatabase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "especiales_monosilabos")
public class EspecialesMonosilabosTable {
    @PrimaryKey(autoGenerate = true)
    private int ID;
    @NonNull
    private String oracion;
    @NonNull
    private String opcion1;
    @NonNull
    private String opcion2;
    @NonNull
    @ColumnInfo(name = "opcion_correcta")
    private String opcionCorrecta;

    public EspecialesMonosilabosTable(@NonNull String oracion, @NonNull String opcion1, @NonNull String opcion2, @NonNull String opcionCorrecta) {
        this.oracion = oracion;
        this.opcion1 = opcion1;
        this.opcion2 = opcion2;
        this.opcionCorrecta = opcionCorrecta;
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
    public String getOpcionCorrecta() {
        return opcionCorrecta;
    }

    public void setOpcionCorrecta(@NonNull String opcionCorrecta) {
        this.opcionCorrecta = opcionCorrecta;
    }
}
