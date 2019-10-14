package com.example.blitzerrechner;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Vergehen.class}, version =  1, exportSchema = false)
public abstract class HistoryRoomDatabase extends RoomDatabase {

    public abstract HistoryDao historyDao();

    private static HistoryRoomDatabase INSTANCE;

    static HistoryRoomDatabase getDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    HistoryRoomDatabase.class, "vergehen_database")
                    .build();
        }
        return INSTANCE;
    }
}
