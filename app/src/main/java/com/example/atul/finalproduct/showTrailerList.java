package com.example.atul.finalproduct;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import DataObjects.tvTrailerListObject;
import JSONParser.SetListData;
import Utils.HTTPStuff;
import Utils.tvTrailerListAdapter;
import YouTubeAPI.API_URLS;
import YouTubeAPI.KEY;

public class showTrailerList extends AppCompatActivity {

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
        setContentView(R.layout.activity_show_trailer_list);

        listView = ( ListView ) findViewById( R.id.showTrailersList );
        httpStuff = new HTTPStuff();
        playList = new ArrayList<>();
        api_urls = new API_URLS();
        key = new KEY();
        playListURL = getIntent().getStringExtra( "playListURL" );
        //Log.v( "playListURL: ", playListURL );
        String URL = api_urls.getPLAYLIST_ITEM_URL_LEFT() +
                api_urls.getPLAYLIST_ITEM_URL_MIDDLE() + playListURL +
                api_urls.getPLAYLIST_ITEM_URL_RIGHT() + key.getKEY();
        trailer_list_adapter = new tvTrailerListAdapter( showTrailerList.this, R.layout.tv_trailer_list_row, playList );
        listView.setAdapter(trailer_list_adapter);

        Toast.makeText(getApplicationContext(), "Loading...", Toast.LENGTH_LONG).show();
        makeRequest( URL );
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
