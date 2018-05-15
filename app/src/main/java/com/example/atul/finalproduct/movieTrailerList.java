package com.example.atul.finalproduct;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import DataObjects.tvTrailerListObject;
import Utils.HTTPStuff;
import Utils.tvTrailerListAdapter;
import tmdbAPI.API_KEY;
import tmdbAPI.URL;

public class movieTrailerList extends AppCompatActivity {

    private ListView listView;
    private tvTrailerListAdapter customAdapter;
    private ArrayList<tvTrailerListObject> playList;
    private URL urlObject;
    private API_KEY apiKey;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_trailer_list);

        listView = ( ListView ) findViewById( R.id.movie_trailer_list );
        playList = new ArrayList<>();
        urlObject = new URL();
        apiKey = new API_KEY();

        customAdapter = new tvTrailerListAdapter( movieTrailerList.this, R.layout.tv_trailer_list_row, playList );
        listView.setAdapter(customAdapter);

        String id = getIntent().getStringExtra( "id" );

        url = urlObject.getLefUrl() + id + "/videos"
                + urlObject.getRightUrl() + apiKey.getApiKey();
        Toast.makeText( getApplicationContext(), "Loading...", Toast.LENGTH_SHORT ).show();
        //Log.v("ID", url);
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray jsonArray = response.getJSONArray( "results" );
                    if( jsonArray.length() > 0 ) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            tvTrailerListObject movie = new tvTrailerListObject();
                            JSONObject object = jsonArray.getJSONObject(i);

                            movie.setTitle(object.getString("name"));
                            movie.setVideoId(object.getString("key"));
                            movie.setPosterURL(getIntent().getStringExtra("posterUrl"));

                            playList.add(movie);
                        }
                        customAdapter.notifyDataSetChanged();
                    }
                    if( jsonArray.length() == 0 )
                        Toast.makeText( getApplicationContext(), "No Trailers Available", Toast.LENGTH_SHORT ).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        AppController.getInstance().addToRequestQueue(request);

    }

}
