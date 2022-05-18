package ru.mirea.serbin.mireaproject.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TVSeriesDao {
    @Query("SELECT * FROM tvseries")
    LiveData<List<TVSeries>> getAllTVSeries();
    @Insert
    void insert(TVSeries series);
    @Update
    void update(TVSeries series);
    @Delete
    void delete(TVSeries series);
}