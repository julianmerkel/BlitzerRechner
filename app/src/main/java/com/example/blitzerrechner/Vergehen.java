package com.example.blitzerrechner;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Vergehen{

    @PrimaryKey(autoGenerate = true)
    private int id;
    private final String vergehen;

    public Vergehen(String vergehen) {
        this.vergehen = vergehen;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getVergehen() {
        return vergehen;
    }
}
