package com.example.atul.finalproduct;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import YouTubeAPI.KEY;

public class Player extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private String videoId;
    private YouTubePlayerView youTubeView;
    private KEY key;
    private static final int RECOVERY_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        key = new KEY();
        videoId = getIntent().getStringExtra( "videoId" );
        youTubeView = ( YouTubePlayerView ) findViewById( R.id.player );
        youTubeView.initialize( key.getKEY(), this );
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        if( !wasRestored )
            youTubePlayer.cueVideo( videoId );
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult error ) {
        if( error.isUserRecoverableError() )
            error.getErrorDialog( this, RECOVERY_REQUEST ).show();
        else
            Toast.makeText(Player.this, "Error initializing YouTube player", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_REQUEST) {
            // Retry initialization if user performed a recovery action
            youTubeView.initialize(key.getKEY(), this);
        }
    }
}