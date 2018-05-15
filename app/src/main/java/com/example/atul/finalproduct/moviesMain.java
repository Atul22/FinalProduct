package com.example.atul.finalproduct;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import tmdbAPI.API_KEY;
import tmdbAPI.URL;

public class moviesMain extends AppCompatActivity {

    private Button now_playing, upcoming, new_trailers;
    private URL urlObject;
    private API_KEY apiKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_main);

        now_playing = ( Button ) findViewById( R.id.now_playing );
        upcoming = ( Button ) findViewById( R.id.upcoming );
        new_trailers = ( Button ) findViewById( R.id.new_trailers );
        urlObject = new URL();
        apiKey = new API_KEY();
        setButtons();
    }

    public void setButtons()
    {
        now_playing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( moviesMain.this, movieGenre.class );
                intent.putExtra( "URL", urlObject.getLefUrl() + "now_playing"
                        + urlObject.getRightUrl() + apiKey.getApiKey() );
                startActivity(intent);
            }
        });

        upcoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( moviesMain.this, movieGenre.class );
                intent.putExtra( "URL", urlObject.getLefUrl() + "upcoming"
                        + urlObject.getRightUrl() + apiKey.getApiKey() );
                startActivity(intent);
            }
        });

        new_trailers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( moviesMain.this, newTrailers.class ));
            }
        });
    }

}
