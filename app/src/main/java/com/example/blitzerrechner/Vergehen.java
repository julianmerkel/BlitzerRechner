package com.example.blitzerrechner;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Vergehen{

    @PrimaryKey(autoGenerate = true)
    private int id;
    private final int vergehen;
    private int bußgeld;
    private int punkte;
    private int fahrverbot;

    public Vergehen(int vergehen, int bußgeld, int punkte, int fahrverbot) {
        this.vergehen = vergehen;
        this.bußgeld = bußgeld;
        this.punkte = punkte;
        this.fahrverbot=fahrverbot;

    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getVergehen() {
        return vergehen;
    }

    public int getBußgeld(){ return bußgeld; }

    public int getPunkte(){
        return punkte;
    }

    public int getFahrverbot(){
        return fahrverbot;
    }
}
