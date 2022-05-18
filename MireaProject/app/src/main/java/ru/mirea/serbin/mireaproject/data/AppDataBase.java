package ru.mirea.serbin.mireaproject.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {TVSeries.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase{
    public abstract TVSeriesDao tvSeriesDao();
}
