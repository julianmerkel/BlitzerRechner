package com.example.blitzerrechner;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Vergehen vergehen);

    @Query("SELECT * from Vergehen ORDER BY id DESC")
    public List<Vergehen> getAll();

    @Delete
    public void delete(Vergehen vergehen);
}

