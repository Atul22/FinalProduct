package com.example.atul.finalproduct;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import DataObjects.Movie;

public class movieDetails extends AppCompatActivity {

    private Button trailerButton;
    private TextView releaseDate, overview, title;
    private NetworkImageView poster;
    private Movie movie;
    private ImageLoader imageLoader;
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        trailerButton = ( Button ) findViewById( R.id.movie_trailer_button );
        releaseDate = ( TextView ) findViewById( R.id.release_date );
        overview = ( TextView ) findViewById( R.id.movie_description );
        title = ( TextView ) findViewById( R.id.movie_name );
        poster = ( NetworkImageView ) findViewById(R.id.movie_poster);

        movie = ( Movie ) getIntent().getSerializableExtra( "dataPassed" );
        title.setText( movie.getTitle() );
        overview.setText( movie.getOverview() );
        releaseDate.setText( movie.getReleaseDate() );

        imageLoader = AppController.getInstance().getImageLoader();
        final String posterUrl = "http://image.tmdb.org/t/p/w300" + movie.getBackdrop_path();
        poster.setImageUrl(posterUrl, imageLoader);

        id = movie.getId();

        trailerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( movieDetails.this, movieTrailerList.class);
                intent.putExtra("id", id );
                intent.putExtra( "posterUrl", posterUrl );
                startActivity(intent);
            }
        });

    }

}
