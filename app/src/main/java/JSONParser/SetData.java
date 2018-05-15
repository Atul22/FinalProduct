package JSONParser;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import DataObjects.TVShow;

/**
 * Created by atul on 2/13/16.
 */
public class SetData {
    public static void setTVShow( JSONArray results, ArrayList< TVShow > playList )
    {
        for( int i = 0; i < results.length(); i++ ) {
            try {
                JSONObject object = results.getJSONObject( i );
                TVShow show = new TVShow();
                show.setPlayListId(object.getString("id"));

                JSONObject snippet = object.getJSONObject( "snippet" );
                show.setTitle( snippet.getString( "title" ));
                show.setDescription(snippet.getString( "description" ));

                JSONObject thumbnails = snippet.getJSONObject( "thumbnails" );
                show.setPosterURL( thumbnails.getJSONObject( "default" ).getString( "url" ));
                show.setHighPosterURL( thumbnails.getJSONObject( "high" ).getString( "url" ) );
                playList.add( show );
            }catch( JSONException e ){
                Log.v("Error: ", "Error");
                e.printStackTrace();
            }
        }
    }
}
