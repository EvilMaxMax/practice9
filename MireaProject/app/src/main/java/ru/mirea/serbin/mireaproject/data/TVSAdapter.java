package ru.mirea.serbin.mireaproject.data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.mirea.serbin.mireaproject.R;

public class TVSAdapter  extends RecyclerView.Adapter<TVSAdapter.TVSeriesViewHolder> {
    private List<TVSeries> series;

    public TVSAdapter(List<TVSeries> series) {
        this.series = series;
    }

    @NonNull
    @Override
    public TVSeriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tvseries_list, parent, false);

        return new TVSeriesViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull TVSeriesViewHolder holder, int position) {
        TVSeries tvSeries = series.get(position);
        holder.name.setText(tvSeries.name);
        holder.year.setText(tvSeries.year);
    }

    @Override
    public int getItemCount() {
        return series.size();
    }

    public static class TVSeriesViewHolder extends RecyclerView.ViewHolder {
        public final TextView name;
        public final TextView year;

        public TVSeriesViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.TVSName);
            year = (TextView) itemView.findViewById(R.id.TVSYear);
        }
    }
}
