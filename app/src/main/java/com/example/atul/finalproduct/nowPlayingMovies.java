package com.example.atul.finalproduct;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import DataObjects.Movie;
import Utils.movieCustomAdapter;
import YouTubeAPI.KEY;
import tmdbAPI.API_KEY;
import tmdbAPI.URL;

public class nowPlayingMovies extends AppCompatActivity {

    private ArrayList<Movie> movieList;
    private ListView listView;
    private movieCustomAdapter customAdapter;
    private EditText search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing_movies);

        if( getIntent().getSerializableExtra( "all" ) != null )
            movieList = (ArrayList<Movie>) getIntent().getSerializableExtra( "all" );
        else if( getIntent().getSerializableExtra( "thriller" ) != null )
            movieList = (ArrayList<Movie>) getIntent().getSerializableExtra("thriller");
        else if( getIntent().getSerializableExtra( "comedy" ) != null )
            movieList = (ArrayList<Movie>) getIntent().getSerializableExtra("comedy");
        else if( getIntent().getSerializableExtra( "horror" ) != null )
            movieList = (ArrayList<Movie>) getIntent().getSerializableExtra("horror");
        else
            movieList = (ArrayList<Movie>) getIntent().getSerializableExtra("romance");


        listView = ( ListView ) findViewById( R.id.now_playing_listView );
        search = ( EditText ) findViewById( R.id.nowPlayingSearch );
        customAdapter = new movieCustomAdapter( nowPlayingMovies.this,
                R.layout.movie_list_item, movieList );

        listView.setAdapter( customAdapter );
        Toast.makeText(nowPlayingMovies.this, "Loading...", Toast.LENGTH_LONG).show();

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                customAdapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        search.setText( "" );
    }

}
