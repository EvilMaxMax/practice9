package ru.mirea.serbin.mireaproject;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    final String FIRST_PARAMETER = "Name";
    final String SECOND_PARAMETER = "Surname";
    final String THIRD_PARAMETER = "Language";

    private EditText editTextName;
    private EditText editTextSurname;
    private EditText editTextLang;

    private SharedPreferences preferences;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        editTextName = view.findViewById(R.id.editTextName);
        editTextSurname = view.findViewById(R.id.editTextSurname);
        editTextLang = view.findViewById(R.id.editTextLang);

        view.findViewById(R.id.saveParButton).setOnClickListener(this::onClickSave);

        preferences = getActivity().getPreferences(MODE_PRIVATE);

        String name = preferences.getString(FIRST_PARAMETER, "Name");
        editTextName.setText(name);

        String surname = preferences.getString(SECOND_PARAMETER, "Surname");
        editTextSurname.setText(surname);

        String age = preferences.getString(THIRD_PARAMETER, "Language");
        editTextLang.setText(age);

        return view;
    }

    public void onClickSave(View view) {
            String param1 = editTextName.getText().toString();
            String param2 = editTextSurname.getText().toString();
            String param3 = editTextLang.getText().toString();

            SharedPreferences.Editor editor = preferences.edit();

            editor.putString(FIRST_PARAMETER, param1);
            editor.putString(SECOND_PARAMETER, param2);
            editor.putString(THIRD_PARAMETER, param3);

            editor.apply();

            Toast.makeText(getActivity(), "Настройки сохранены", Toast.LENGTH_SHORT).show();
    }
}