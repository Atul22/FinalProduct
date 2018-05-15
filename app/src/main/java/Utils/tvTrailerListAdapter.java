package Utils;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.atul.finalproduct.AppController;
import com.example.atul.finalproduct.Player;
import com.example.atul.finalproduct.R;

import java.util.ArrayList;

import DataObjects.tvTrailerListObject;

/**
 * Created by atul on 2/13/16.
 */
public class tvTrailerListAdapter extends ArrayAdapter {

    private ArrayList<tvTrailerListObject> showList;
    private Activity activity;
    int layoutResources;
    public tvTrailerListAdapter(Activity act, int resource, ArrayList< tvTrailerListObject > data) {
        super( act, resource, data );
        activity = act;
        layoutResources = resource;
        showList = data;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public tvTrailerListObject getItem(int position) {
        return showList.get( position );
    }

    @Override
    public int getPosition(Object item) {
        return super.getPosition(item);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;
        if( row == null )
        {
            holder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from( activity );

            row = inflater.inflate( layoutResources, null );

            holder.title = (TextView) row.findViewById( R.id.title );
            holder.imageView = (NetworkImageView) row.findViewById( R.id.poster );
            row.setTag( holder );
        }else {
            holder = ( ViewHolder ) row.getTag();
        }

        holder.show = getItem( position );
        holder.title.setText(holder.show.getTitle());

        ImageLoader imageLoader = AppController.getInstance().getImageLoader();
        holder.imageView.setImageUrl(  holder.show.getPosterURL(), imageLoader);
        final ViewHolder finalHolder = holder;
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tvTrailerListObject show = finalHolder.show;
                Intent intent = new Intent( activity, Player.class);

                intent.putExtra( "videoId", show.getVideoId() );
                activity.startActivity(intent);
            }
        });


        return row;
    }
    public class ViewHolder
    {
        tvTrailerListObject show;
        NetworkImageView imageView;
        TextView title;
    }
}
