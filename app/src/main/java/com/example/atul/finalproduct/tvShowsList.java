package com.example.atul.finalproduct;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import DataObjects.TVShow;
import JSONParser.SetData;
import Utils.HTTPStuff;
import Utils.tvCustomAdapter;
import YouTubeAPI.API_URLS;
import YouTubeAPI.KEY;
import YouTubeAPI.YouTubeChannels;

public class tvShowsList extends AppCompatActivity {

    private ListView listView;
    private static ArrayList<TVShow> playList;
    private tvCustomAdapter customAdapter;
    private API_URLS api_urls;
    private KEY key;
    private YouTubeChannels channels;
    private static String nextPageToken;
    HTTPStuff httpStuff;
    private EditText search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_shows_list);

        listView = (ListView) findViewById( R.id.tv_shows_List );
        search = ( EditText ) findViewById( R.id.searchField );
        playList = new ArrayList<>();
        api_urls = new API_URLS();
        key = new KEY();
        channels = new YouTubeChannels();
        nextPageToken = new String();
        httpStuff = new HTTPStuff();

        String URL = api_urls.getPLAYLIST_URL_LEFT() + nextPageToken +
                api_urls.getPLAYLIST_URL_MIDDLE() + channels.getChannelID() +
                api_urls.getPLAYLIST_URL_RIGHT() + key.getKEY1();


        customAdapter = new tvCustomAdapter( tvShowsList.this, R.layout.show_list_row, playList );
        listView.setAdapter(customAdapter);

        Toast.makeText(getApplicationContext(), "Loading...", Toast.LENGTH_LONG).show();
        makeRequest(URL);

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

    public void makeRequest(String URL) {
        httpStuff.getData(URL, new HTTPStuff.VolleyCallBack() {
            @Override
            public void onSuccess(String data) {
                try {
                    JSONObject object = new JSONObject( data );
                    //Log.v( "JSON: ", data.toString() );
                    nextPageToken = object.getString("nextPageToken");
                    JSONArray results = object.getJSONArray("items");
                    SetData.setTVShow(results, playList);
                    customAdapter.notifyDataSetChanged();
                    if (object.getString("nextPageToken") != null) {
                        String url = api_urls.getPLAYLIST_URL_LEFT() + nextPageToken +
                                api_urls.getPLAYLIST_URL_MIDDLE() + channels.getChannelID() +
                                api_urls.getPLAYLIST_URL_RIGHT() + key.getKEY();
                        makeRequest(url);
                    }
                } catch (JSONException e) {

                    e.printStackTrace();
                }
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        search.setText("");
    }

}
