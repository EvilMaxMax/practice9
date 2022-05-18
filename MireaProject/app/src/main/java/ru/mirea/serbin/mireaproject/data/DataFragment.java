package ru.mirea.serbin.mireaproject.data;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.mirea.serbin.mireaproject.R;

public class DataFragment extends Fragment {
    private List<TVSeries> series = new ArrayList<>();
    private TVSeriesViewModel tvSeriesViewModel;
    private TVSAdapter tvsAdapter = new TVSAdapter(series);
    private RecyclerView recyclerView;
    private ActivityResultLauncher<Intent> launcher;
    public static final int ADD_CAT_RESULT_CODE=1;
    public static final String NAME_LABEL="name";
    public static final String YEAR_LABEL="year";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_data, container, false);

        if (getActivity() != null){
            tvSeriesViewModel = new ViewModelProvider(getActivity()).get(TVSeriesViewModel.class);
            tvSeriesViewModel.getTVSLiveData().observe(getActivity(), this::onChanged);
        }
        view.findViewById(R.id.btnAddDiscip).setOnClickListener(this::onNewClicked);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView = (RecyclerView) view.findViewById(R.id.TVSRecyclerView);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(tvsAdapter);

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                (result) -> {
                    if (result.getResultCode() == ADD_CAT_RESULT_CODE
                            && result.getData() != null){
                        TVSeries series = new TVSeries();
                        series.name = result.getData().getStringExtra(NAME_LABEL);
                        series.year = result.getData().getStringExtra(YEAR_LABEL);
                        tvSeriesViewModel.AddSeries(series);
                        tvsAdapter.notifyDataSetChanged();
                    }
                });
        return view;
    }

    public void onChanged(List<TVSeries> tvSeries) {
        if (!series.isEmpty()){
            series.clear();
        }
        series.addAll(tvSeries);
        tvsAdapter.notifyDataSetChanged();
    }

    private void onNewClicked(View view){
        Intent intent = new Intent(getActivity(), AddSeries.class);
        launcher.launch(intent);
    }
}
