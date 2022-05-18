package ru.mirea.serbin.mireaproject;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class StoryActivity extends AppCompatActivity {
    private EditText name, surname, language;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        name = findViewById(R.id.storyName);
        surname = findViewById(R.id.storySurname);
        language = findViewById(R.id.storyLanguage);
    }

    public void newStory(View view){
        Intent intent = new Intent(this, StoriesFragment.class);
        intent.putExtra("name", name.getText().toString());
        intent.putExtra("surname", surname.getText().toString());
        intent.putExtra("language", language.getText().toString());
        final String TAG = "intent";
        String msg = name.getText().toString() + surname.getText().toString() +  language.getText().toString();
        Log.d(TAG,intent.toString());
        setResult(StoriesFragment.RESULT_OK, intent);
        finish();
    }
}