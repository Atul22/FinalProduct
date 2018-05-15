package DataObjects;

import java.io.Serializable;

/**
 * Created by atul on 2/13/16.
 */
public class TVShow implements Serializable{

    private static final long serialVersionUID = 10L;

    private String playListId;
    private String title;
    private String description;
    private String posterURL;
    private String highPosterURL;
    private String nextPageToken;

    public String getHighPosterURL() {
        return highPosterURL;
    }

    public void setHighPosterURL(String highPosterURL) {
        this.highPosterURL = highPosterURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlayListId() {
        return playListId;
    }

    public void setPlayListId(String playListId) {
        this.playListId = playListId;
    }

    public String getPosterURL() {
        return posterURL;
    }

    public void setPosterURL(String posterURL) {
        this.posterURL = posterURL;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

}
