package Utils;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.atul.finalproduct.AppController;

/**
 * Created by atul on 2/13/16.
 */
public class HTTPStuff {

    public void getData( String URL, final VolleyCallBack callBack )
    {
        StringRequest request = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callBack.onSuccess( response );
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        AppController.getInstance().addToRequestQueue( request );
    }
    public interface VolleyCallBack
    {
        void onSuccess( String data );
    }
}
