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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import DataObjects.TVShow;
import DataObjects.tvTrailerListObject;
import JSONParser.SetData;
import JSONParser.SetListData;
import Utils.HTTPStuff;
import Utils.tvCustomAdapter;
import Utils.tvTrailerListAdapter;
import YouTubeAPI.API_URLS;
import YouTubeAPI.KEY;
import YouTubeAPI.YouTubeChannels;

public class newTrailers extends AppCompatActivity {

    private ListView listView;
    private HTTPStuff httpStuff;
    private ArrayList<tvTrailerListObject> playList;
    private API_URLS api_urls;
    private KEY key;
    private tvTrailerListAdapter trailer_list_adapter;
    private String playListURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_trailers);

        listView = ( ListView ) findViewById( R.id.newTrailersList );
        httpStuff = new HTTPStuff();
        playList = new ArrayList<>();
        api_urls = new API_URLS();
        key = new KEY();
        playListURL = "PLScC8g4bqD47c-qHlsfhGH3j6Bg7jzFy-";

        String URL = api_urls.getPLAYLIST_ITEM_URL_LEFT() +
                api_urls.getPLAYLIST_ITEM_URL_MIDDLE() + playListURL +
                api_urls.getPLAYLIST_ITEM_URL_RIGHT() + key.getKEY2();

        trailer_list_adapter = new tvTrailerListAdapter( newTrailers.this, R.layout.tv_trailer_list_row, playList );
        listView.setAdapter(trailer_list_adapter);

        Toast.makeText(getApplicationContext(), "Loading...", Toast.LENGTH_LONG ).show();
        makeRequest(URL);

    }

    public void makeRequest( String URL )
    {
        httpStuff.getData( URL, new HTTPStuff.VolleyCallBack() {
            @Override
            public void onSuccess(String data) {
                try {
                    JSONObject object = new JSONObject(data);
                    JSONArray results = object.getJSONArray( "items" );
                    SetListData.setTVShow(results, playList);
                    trailer_list_adapter.notifyDataSetChanged();
                    if( object.getString( "nextPageToken" ) != null ){
                        //Log.v( "nextPageToken: ", nextPageToken );
                        String url = api_urls.getPLAYLIST_ITEM_URL_LEFT() +object.getString( "nextPageToken" ) +
                                api_urls.getPLAYLIST_ITEM_URL_MIDDLE() + playListURL +
                                api_urls.getPLAYLIST_ITEM_URL_RIGHT() + key.getKEY();
                        makeRequest( url );
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
