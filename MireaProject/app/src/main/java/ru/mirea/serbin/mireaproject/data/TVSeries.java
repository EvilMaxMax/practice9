package ru.mirea.serbin.mireaproject.data;

import androidx.room.PrimaryKey;
import androidx.room.Entity;

@Entity
public class TVSeries {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String name, year;
}