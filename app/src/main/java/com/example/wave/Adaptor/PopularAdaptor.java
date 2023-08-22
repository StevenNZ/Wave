package com.example.wave.Adaptor;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.wave.Activities.Popular;
import com.example.wave.R;

import java.util.List;

public class PopularAdaptor extends ArrayAdapter {


    int mLayoutId;
    List<Popular> mPopular;
    Context mContext;


    public void setFilteredList(List<Popular> filteredList){
        Log.d("SearchDebug", "onQueryTextChange: filteredList = " + filteredList);
        mPopular = filteredList;
        Log.d("SearchDebug", "onQueryTextChange: mPopular = " + mPopular);
        notifyDataSetChanged();
    }


    public PopularAdaptor(@NonNull Context context, int resource, @NonNull List<Popular> objects) {
        super(context, resource, objects);
        mPopular = objects;
        mContext = context;
        mLayoutId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Get a reference to the current ListView item
        View currentListViewItem = convertView;

        // Check if the existing view is being reused, otherwise inflate the view
        if (currentListViewItem == null) {
            currentListViewItem = LayoutInflater.from(getContext()).inflate(mLayoutId, parent, false);
        }



        Log.d("SearchDebug", "getView: mPopular = " + mPopular);
        Log.d("SearchDebug", "getView: mPopular = " + mPopular.size());
        //Get the Number object for the current position
        Popular currentPopular = mPopular.get(position);
        //Set the attributed of list_view_number_item views
        ImageView iconImageView = (ImageView) currentListViewItem.findViewById(R.id.popular_image);
        Glide.with(mContext).load(currentPopular.getAlbumImage()).into(iconImageView);


        //setting the text
        TextView popularTextView = (TextView) currentListViewItem.findViewById(R.id.popular_name);
        popularTextView.setText(currentPopular.getAlbumName());

        TextView popularArtistTextView = (TextView) currentListViewItem.findViewById(R.id.popular_artist);
        popularArtistTextView.setText(currentPopular.getAlbumArtist());


        return currentListViewItem;
    }

    @Override
    public int getCount() {
        return mPopular.size();
    }
}
