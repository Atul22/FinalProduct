package Utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.atul.finalproduct.AppController;
import com.example.atul.finalproduct.R;
import com.example.atul.finalproduct.showDetails;

import java.util.ArrayList;

import DataObjects.TVShow;

/**
 * Created by atul on 2/13/16.
 */
public class tvCustomAdapter extends ArrayAdapter {

    private ArrayList<TVShow> showList;               // Original Values
    private ArrayList< TVShow > displayedList;          // Values to be displayed
    private Activity activity;
    int layoutResources;
    public tvCustomAdapter(Activity act, int resource, ArrayList< TVShow > data) {
        super( act, resource, data );
        activity = act;
        layoutResources = resource;
        showList = data;
        displayedList = data;
    }

    @Override
    public int getCount() {
        return displayedList.size();
    }

    @Override
    public TVShow getItem(int position) {
        return displayedList.get(position);
    }

    @Override
    public int getPosition(Object item) {
        return super.getPosition(item);
    }

    @Override
    public long getItemId(int position) {
        return position;
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

            holder.title = (TextView) row.findViewById( R.id.show_title );
            holder.imageView = (NetworkImageView) row.findViewById( R.id.show_poster );
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

                TVShow show = finalHolder.show;
                Bundle extras = new Bundle();
                extras.putSerializable("dataPassed", show);
                Intent intent = new Intent( activity, showDetails.class);

                intent.putExtras(extras);
                activity.startActivity(intent);
            }
        });

        return row;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                ArrayList< TVShow > FilteredArrList = new ArrayList<>();
                if( showList == null )
                    showList = new ArrayList< TVShow >( displayedList );// saves the original data in showList

                /********
                 *
                 *  If constraint(CharSequence that is received) is null returns the mOriginalValues(Original) values
                 *  else does the Filtering and returns FilteredArrList(Filtered)
                 *
                 ********/
                if (constraint == null || constraint.length() == 0) {

                    // set the Original result to return
                    results.count = showList.size();
                    results.values = showList;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < showList.size(); i++) {
                        String data = showList.get(i).getTitle();
                        if (data.toLowerCase().contains(constraint.toString())) {
                            FilteredArrList.add( showList.get(i) );
                        }
                    }
                    // set the Filtered result to return
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                displayedList = ( ArrayList< TVShow > ) results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }

    public class ViewHolder
    {
        TVShow show;
        NetworkImageView imageView;
        TextView title;
    }

}
