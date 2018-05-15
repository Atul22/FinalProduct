package YouTubeAPI;

/**
 * Created by atul on 2/13/16.
 */
public class API_URLS {

    private String LIST_URL_LEFT = "https://www.googleapis.com/youtube/v3/searchpart=snippet&q=";
    private String LIST_URL_RIGHT = "&type=channel&key=";

    private String PLAYLIST_URL_LEFT = "https://www.googleapis.com/youtube/v3/playlists?pageToken=";
    private String PLAYLIST_URL_MIDDLE = "&part=snippet&channelId=";
    private String PLAYLIST_URL_RIGHT = "&key=";

    private String PLAYLIST_ITEM_URL_LEFT = "https://www.googleapis.com/youtube/v3/playlistItems?pageToken=";
    private String PLAYLIST_ITEM_URL_MIDDLE = "&part=snippet&playlistId=";
    private String PLAYLIST_ITEM_URL_RIGHT = "&key=";

    public String getPLAYLIST_ITEM_URL_MIDDLE() {
        return PLAYLIST_ITEM_URL_MIDDLE;
    }


    public String getPLAYLIST_ITEM_URL_LEFT() {
        return PLAYLIST_ITEM_URL_LEFT;
    }

    public String getPLAYLIST_ITEM_URL_RIGHT() {
        return PLAYLIST_ITEM_URL_RIGHT;
    }

    public String getPLAYLIST_URL_RIGHT() {
        return PLAYLIST_URL_RIGHT;
    }

    public String getPLAYLIST_URL_MIDDLE() {
        return PLAYLIST_URL_MIDDLE;
    }

    public String getPLAYLIST_URL_LEFT() {
        return PLAYLIST_URL_LEFT;
    }

    public String getLIST_URL_RIGHT() {
        return LIST_URL_RIGHT;
    }

    public String getLIST_URL_LEFT() {
        return LIST_URL_LEFT;
    }
}
