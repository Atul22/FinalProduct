package JSONParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import DataObjects.tvTrailerListObject;

/**
 * Created by atul on 2/13/16.
 */
public class SetListData {

    public static void setTVShow( JSONArray results, ArrayList< tvTrailerListObject > playList )
    {
        for( int i = 0; i < results.length(); i++ ) {
            try {
                JSONObject object = results.getJSONObject( i );
                tvTrailerListObject show = new tvTrailerListObject();

                JSONObject snippet = object.getJSONObject( "snippet" );
                show.setTitle(snippet.getString("title"));
                //show.setPublishedAt(snippet.getString("publishedAt"));
                JSONObject thumbnails = snippet.getJSONObject( "thumbnails" );
                show.setPosterURL(thumbnails.getJSONObject( "default" ).getString( "url" ));
                show.setVideoId( snippet.getJSONObject( "resourceId" ).getString( "videoId") );
                playList.add( show );
            }catch( JSONException e ){
                e.printStackTrace();
            }
        }
    }
}
