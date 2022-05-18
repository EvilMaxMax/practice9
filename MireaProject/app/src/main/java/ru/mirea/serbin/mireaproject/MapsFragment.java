package ru.mirea.serbin.mireaproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MapsFragment extends Fragment implements GoogleMap.OnMapClickListener {
    private GoogleMap map;
    private ArrayList<MarkerOptions> mapMarker =  new ArrayList<>();
    private LatLng position; String campus, description;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            map = googleMap;
            map.setOnMapClickListener(MapsFragment.this::onMapClick);

            position = new LatLng(55.6695953,37.4798824);
            campus = "Главный кампус на Проспекте Вернадского";
            description = "Пр-т Вернадского, д.78; (55.6695953, 37.4798824)";
            googleMap.addMarker(new MarkerOptions()
                    .position(position)
                    .title(campus)
                    .snippet(description));

            position = new LatLng(55.6618971,37.4745255);
            campus = "Детский технопарк 'Альтаир' на Проспекте Вернадского";
            description = "Пр-т Вернадского, д.86 с.2; (55.660878, 37.476127)";
            googleMap.addMarker(new MarkerOptions()
                    .position(position)
                    .title(campus)
                    .snippet(description));

            position = new LatLng(55.7938058,37.7000664);
            campus = "Кампус на улице Стромынка";
            description = "ул. Стромынка, д.20; (55.7938058, 37.7000664)";
            googleMap.addMarker(new MarkerOptions()
                    .position(position)
                    .title(campus)
                    .snippet(description));

            position = new LatLng(55.7317977,37.5745506);
            campus = "Кампус на улице Малая Пироговская";
            description = "ул. Малая Пироговская, д.1c5; (55.7317977, 37.5745506)";
            googleMap.addMarker(new MarkerOptions()
                    .position(position)
                    .title(campus)
                    .snippet(description));

            position = new LatLng(55.7648399,37.7392163);
            campus = "Кампус ИЭП на улице Соколиная Гора";
            description = "5-я ул. Соколиной Горы, д.22; (55.7648399, 37.7392163)";
            googleMap.addMarker(new MarkerOptions()
                    .position(position)
                    .title(campus)
                    .snippet(description));

            position = new LatLng(55.7250254,37.6304868);
            campus = "Кампус колледжа РТУ МИРЭА";
            description = "1-й Щипковский пер., д.23; (55.7250254, 37.6304868)";
            googleMap.addMarker(new MarkerOptions()
                    .position(position)
                    .title(campus)
                    .snippet(description));

            position = new LatLng(55.728676,37.5708812);
            campus = "Кампус военного учебного центра";
            description = "ул. Усачева, д.7/1; (55.728676, 37.5708812)";
            googleMap.addMarker(new MarkerOptions()
                    .position(position)
                    .title(campus)
                    .snippet(description));
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng).zoom(12).build();
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        Date currentTime = Calendar.getInstance().getTime();

        addNewMarker(currentTime, latLng);
    }

    public void addNewMarker(Date date, LatLng latLng){
        MarkerOptions marker = new MarkerOptions().title(date.toString()).snippet("Помеченное местоположение").position(latLng);
        map.addMarker(marker);
        mapMarker.add(marker);
    }
}