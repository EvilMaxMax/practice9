package ru.mirea.serbin.mireaproject.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class TVSeriesViewModel extends ViewModel {
    private final LiveData<List<TVSeries>> tvseries;
    private final AppDataBase appDatabase = App.instance.getDatabase();
    private final TVSeriesDao tvSeriesDao = appDatabase.tvSeriesDao();

    public TVSeriesViewModel(){
        tvseries = tvSeriesDao.getAllTVSeries();
    }

    public void AddSeries(TVSeries tvSeries){
        tvSeriesDao.insert(tvSeries);
    }

    public LiveData<List<TVSeries>> getTVSLiveData(){
        return tvseries;
    }
    public List<TVSeries> getTVSeries(){
        return tvseries.getValue();
    }
}
