package com.powermango.myapplication.exercisesDatabase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "diptongo_hiato")
public class DiptongoHiatoTable {
    @PrimaryKey(autoGenerate = true)
    private int ID;
    @NonNull
    private String palabra;
    @NonNull
    @ColumnInfo(name = "diptongo_hiato")
    private String diptongoHiato;
    @NonNull
    @ColumnInfo(name = "formado_por")
    private String formadoPor;
    @NonNull
    @ColumnInfo(name = "tilde_opcion1")
    private String tildeOpcion1;
    @NonNull
    @ColumnInfo(name = "tilde_opcion2")
    private String tildeOpcion2;
    @NonNull
    @ColumnInfo(name = "tilde_opcion3")
    private String tildeOpcion3;
    @NonNull
    @ColumnInfo(name = "tilde_opcion_correcta")
    private String tildeOpcionCorrecta;

    public DiptongoHiatoTable(@NonNull String palabra, @NonNull String diptongoHiato, @NonNull String formadoPor, @NonNull String tildeOpcion1, @NonNull String tildeOpcion2, @NonNull String tildeOpcion3, @NonNull String tildeOpcionCorrecta) {
        this.palabra = palabra;
        this.diptongoHiato = diptongoHiato;
        this.formadoPor = formadoPor;
        this.tildeOpcion1 = tildeOpcion1;
        this.tildeOpcion2 = tildeOpcion2;
        this.tildeOpcion3 = tildeOpcion3;
        this.tildeOpcionCorrecta = tildeOpcionCorrecta;
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
    public String getDiptongoHiato() {
        return diptongoHiato;
    }

    public void setDiptongoHiato(@NonNull String diptongoHiato) {
        this.diptongoHiato = diptongoHiato;
    }

    @NonNull
    public String getFormadoPor() {
        return formadoPor;
    }

    public void setFormadoPor(@NonNull String formadoPor) {
        this.formadoPor = formadoPor;
    }

    @NonNull
    public String getTildeOpcion1() {
        return tildeOpcion1;
    }

    public void setTildeOpcion1(@NonNull String tildeOpcion1) {
        this.tildeOpcion1 = tildeOpcion1;
    }

    @NonNull
    public String getTildeOpcion2() {
        return tildeOpcion2;
    }

    public void setTildeOpcion2(@NonNull String tildeOpcion2) {
        this.tildeOpcion2 = tildeOpcion2;
    }

    @NonNull
    public String getTildeOpcion3() {
        return tildeOpcion3;
    }

    public void setTildeOpcion3(@NonNull String tildeOpcion3) {
        this.tildeOpcion3 = tildeOpcion3;
    }

    @NonNull
    public String getTildeOpcionCorrecta() {
        return tildeOpcionCorrecta;
    }

    public void setTildeOpcionCorrecta(@NonNull String tildeOpcionCorrecta) {
        this.tildeOpcionCorrecta = tildeOpcionCorrecta;
    }
}
