package com.example.blitzerrechner;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Vergehen{

    @PrimaryKey(autoGenerate = true)
    private int id;
    private final int vergehen;
    private int bußgeld;

    public Vergehen(int vergehen, int bußgeld) {
        this.vergehen = vergehen;
        this.bußgeld = bußgeld;
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
}
