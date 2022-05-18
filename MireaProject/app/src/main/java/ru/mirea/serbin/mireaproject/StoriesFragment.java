package ru.mirea.serbin.mireaproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StoriesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StoriesFragment extends Fragment {

    public static final int RESULT_OK = -1;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    List<StoryPerson> persons = new ArrayList<>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StoriesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StoriesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StoriesFragment newInstance(String param1, String param2) {
        StoriesFragment fragment = new StoriesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stories, container, false);

        recyclerView = view.findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true);
        recyclerView.setLayoutManager(linearLayoutManager);
        RVAdapter adapter = new RVAdapter(persons);
        recyclerView.setAdapter(adapter);

        FloatingActionButton btn = view.findViewById(R.id.floatingActionButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), StoryActivity.class);
                final String TAG = "intent";
                Log.d(TAG, StoryActivity.class.toString());
                startActivityForResult(intent, 0);
          }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode ==  StoriesFragment.RESULT_OK ) {
             persons.add(new StoryPerson(data.getStringExtra("name"), data.getStringExtra("surname"), data.getStringExtra("language")));
             RVAdapter adapter = new RVAdapter(persons);
             recyclerView.setAdapter(adapter);
        }
    }
}
