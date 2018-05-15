package DataObjects;

import java.io.Serializable;

/**
 * Created by atul on 2/13/16.
 */
public class tvTrailerListObject implements Serializable {

    private static final long serialVersionUID = 10L;

    private String publishedAt;
    private String title;
    private String videoId;
    private String posterURL;

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getPosterURL() {
        return posterURL;
    }

    public void setPosterURL(String posterURL) {
        this.posterURL = posterURL;
    }
}
