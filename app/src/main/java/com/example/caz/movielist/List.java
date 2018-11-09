package com.example.caz.movielist;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class List extends AppCompatActivity {

    private Context context;
    private LinearLayout    listLayout;
    private MovieViewModel movieViewModel;

    private static final int REQUEST_CODE = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        context = this;

        listLayout = findViewById(R.id.list_layout);

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);

        final Observer<ArrayList<Movie>> observer = new Observer<ArrayList<Movie>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Movie> movies) {                              // movies get declared here (remove later)

                if(movies != null) {
                    refreshMovieListView(movies);
                }
            }
        };

        movieViewModel.getMovieList().observe(this, observer);

         findViewById(R.id.add_button).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 Intent intent = new Intent(context, Edit.class);

                 Movie newMovie = new Movie(Movie.NO_ID);

                 intent.putExtra(Edit.CHECK_KEY, newMovie);
                 startActivityForResult(intent, REQUEST_CODE);
             }
         });

    }



    private TextView getDefaultTextView(final Movie movie) {
        TextView textView = new TextView(context);
        textView.setText(movie.getTitle());
        textView.setTextSize(24);
        textView.setPadding(10, 10, 10, 10);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Edit.class);
                intent.putExtra(Edit.CHECK_KEY, movie);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        return textView;
    }

    private void refreshMovieListView(ArrayList<Movie> movies) {
        listLayout.removeAllViews();
        for(Movie movie : movies) {
            listLayout.addView(getDefaultTextView(movie));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {       //method called right before resume (right after a pause)
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK) {
            if(requestCode == REQUEST_CODE) {
                if(data != null) {
                    Movie returnedMovieData = (Movie)data.getSerializableExtra(Edit.CHECK_KEY);

                    movieViewModel.addMovie(returnedMovieData);
                }
            }
        }

    }


}
