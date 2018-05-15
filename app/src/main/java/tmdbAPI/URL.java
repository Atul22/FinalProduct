package tmdbAPI;

/**
 * Created by atul on 2/14/16.
 */
public class URL {

    private String lefUrl = "http://api.themoviedb.org/3/movie/";
    private String rightUrl = "?api_key=";

    public String getLefUrl() {
        return lefUrl;
    }

    public void setLefUrl(String lefUrl) {
        this.lefUrl = lefUrl;
    }

    public String getRightUrl() {
        return rightUrl;
    }

    public void setRightUrl(String rightUrl) {
        this.rightUrl = rightUrl;
    }
}
