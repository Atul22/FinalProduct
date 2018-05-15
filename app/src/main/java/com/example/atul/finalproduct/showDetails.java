package com.example.atul.finalproduct;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import org.w3c.dom.Text;

import DataObjects.TVShow;

public class showDetails extends AppCompatActivity {

    private NetworkImageView poster;
    private TextView overview, showTitle;
    private ImageLoader imageLoader;
    private String playListURL;
    private Button trailerButton;
    private TVShow show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);
        poster = ( NetworkImageView) findViewById(R.id.poster);
        showTitle = (TextView ) findViewById( R.id.show_name );
        overview = ( TextView ) findViewById( R.id.description );

        show = ( TVShow ) getIntent().getSerializableExtra("dataPassed");
        showTitle.setText( show.getTitle() );
        overview.setText(show.getDescription());
        imageLoader = AppController.getInstance().getImageLoader();
        poster.setImageUrl( show.getHighPosterURL(), imageLoader );
        playListURL = show.getPlayListId();
        trailerButton = ( Button ) findViewById( R.id.trailerButton );
        trailerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( showDetails.this, showTrailerList.class);
                intent.putExtra("playListURL", playListURL);
                //Log.v( "playListURL: ", playListURL );
                startActivity(intent);
            }
        });
    }

}
