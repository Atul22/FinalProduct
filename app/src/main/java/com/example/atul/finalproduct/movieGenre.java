package com.example.atul.finalproduct;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Array;
import java.util.ArrayList;

import DataObjects.Movie;

public class movieGenre extends AppCompatActivity {

    public Button allB, thrillerB, comedyB, horrorB, romanceB;
    public ArrayList< Movie > all, thriller, comedy, horror, romance;
    public String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_genre);

        allB = ( Button ) findViewById( R.id.all );
        thrillerB = ( Button ) findViewById( R.id.thriller );
        comedyB = ( Button ) findViewById( R.id.comedy );
        horrorB = ( Button ) findViewById( R.id.horror );
        romanceB = ( Button ) findViewById( R.id.romantic );

        all = new ArrayList<>();
        thriller = new ArrayList<>();
        comedy = new ArrayList<>();
        horror = new ArrayList<>();
        romance = new ArrayList<>();

        url = getIntent().getStringExtra( "URL" );
        makeRequest( 1 );

        setButtons();
    }

    public void setButtons()
    {
        allB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( movieGenre.this, nowPlayingMovies.class );
                Bundle information = new Bundle();
                information.putSerializable("all", all );
                intent.putExtras(information);
                startActivity( intent );
            }
        });

        thrillerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( movieGenre.this, nowPlayingMovies.class );
                Bundle information = new Bundle();
                information.putSerializable( "thriller", thriller );
                intent.putExtras(information);
                startActivity( intent );
            }
        });

        comedyB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( movieGenre.this, nowPlayingMovies.class );
                Bundle information = new Bundle();
                information.putSerializable("comedy", comedy );
                intent.putExtras(information);
                startActivity( intent );
            }
        });

        horrorB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( movieGenre.this, nowPlayingMovies.class );
                Bundle information = new Bundle();
                information.putSerializable("horror", horror );
                intent.putExtras(information);
                startActivity( intent );
            }
        });

        romanceB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( movieGenre.this, nowPlayingMovies.class );
                Bundle information = new Bundle();
                information.putSerializable("romance", romance );
                intent.putExtras(information);
                startActivity( intent );
            }
        });

    }

    public void makeRequest(  int page )
    {
        String pageUrl = url + "&page=" + String.valueOf( page );
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                pageUrl, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    int page = Integer.parseInt( response.getString( "page" ) );
                    int total_pages = Integer.parseInt(response.getString("total_pages"));

                    if( page < total_pages ) {
                        JSONArray jsonArray = response.getJSONArray( "results" );
                        parseObject( jsonArray );
                        makeRequest( page + 1 );
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("VolleyError", error.getMessage().toString());
            }
        });
        AppController.getInstance().addToRequestQueue(request);
    }

    public void parseObject( JSONArray jsonArray )
    {
        try {

            for( int i = 0; i < jsonArray.length(); i++ )
            {
                Movie movie = new Movie();
                JSONObject jsonObject = jsonArray.getJSONObject( i );

                movie.setPoster_path(jsonObject.getString("poster_path"));
                movie.setOverview(jsonObject.getString("overview"));
                movie.setReleaseDate(jsonObject.getString("release_date"));
                movie.setId(jsonObject.getString("id"));
                movie.setTitle(jsonObject.getString("title"));
                movie.setBackdrop_path(jsonObject.getString("backdrop_path"));
                movie.setPopularity(jsonObject.getString("popularity"));

                JSONArray genre = jsonObject.getJSONArray( "genre_ids" );
                String title = movie.getTitle();
                for( int j = 0; j < genre.length(); j++ )
                {
                    int gid = genre.getInt( j );
                    if( gid == 35 ) {
                        if( !inList( comedy, title ))
                            comedy.add( movie );
                    }
                    if( gid == 53 ) {
                        if( !inList( thriller, title ))
                            thriller.add(movie);
                    }
                    if( gid == 27 ) {
                        if( !inList( horror, title ))
                            horror.add(movie);
                    }
                    if( gid == 10749 ) {
                        if( !inList( romance, title ))
                            romance.add(movie);
                    }
                }
                if( !inList( all, title ))
                    all.add( movie );
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean inList( ArrayList< Movie > list, String title)
    {
        for( int i = 0 ; i < list.size(); i++ )
        {
            if( list.get( i ).getTitle().equals( title ) )
                return true;
        }
        return false;
    }

}
