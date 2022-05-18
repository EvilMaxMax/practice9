package ru.mirea.serbin.mireaproject.data;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.serbin.mireaproject.MainActivity;
import ru.mirea.serbin.mireaproject.R;

public class AddSeries extends AppCompatActivity {
    private EditText tvsName;
    private EditText tvsYear;
    private Button button;

    private AppDataBase database;
    private TVSeriesDao tvseriesDao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_series);

        database = App.getInstance().getDatabase();
        tvseriesDao = database.tvSeriesDao();

        tvsName = findViewById(R.id.editTVSName);
        tvsYear = findViewById(R.id.editTVSYear);

        button = findViewById(R.id.saveTVS);
        button.setOnClickListener(this::saveBtn);
    }

    public void saveBtn(View view) {

        TVSeries tvSeries = new TVSeries();
        tvSeries.name = tvsName.getText().toString();
        tvSeries.year = tvsYear.getText().toString();

        tvseriesDao.insert(tvSeries);
        finish();
    }
}