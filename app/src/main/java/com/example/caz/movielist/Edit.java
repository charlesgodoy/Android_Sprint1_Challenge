package com.example.caz.movielist;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class Edit extends AppCompatActivity {

    public static final String CHECK_KEY = "check_key";

    EditText editTitle;
    Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        editTitle = findViewById(R.id.edit_title);

        movie = (Movie)getIntent().getSerializableExtra(CHECK_KEY);
        if(movie == null) {
            movie = new Movie(Movie.NO_ID);
        }

        editTitle.setText(movie.getTitle());

    }

    @Override
    public void onBackPressed() {
        prepResult();
        super.onBackPressed();
    }

    private void prepResult() {
        movie.setTitle(editTitle.getText().toString());

        Intent resultItent = new Intent();
        resultItent.putExtra(CHECK_KEY, movie);
        setResult(Activity.RESULT_OK, resultItent);
    }
}
