package com.example.initial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class PopularAdaptor extends ArrayAdapter {


    int mLayoutId;
    List<Popular> mPopular;
    Context mContext;

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
        //Get the Number object for the current position
        Popular currentPopular = mPopular.get(position);

        //Set the attributed of list_view_number_item views
        ImageView iconImageView = (ImageView) currentListViewItem.findViewById(R.id.popular_image);
        int i = mContext.getResources().getIdentifier(
                currentPopular.getAlbumImage(), "drawable",
                mContext.getPackageName());

        //Setting the icon
        iconImageView.setImageResource(i);

        //setting the text
        TextView popularTextView = (TextView) currentListViewItem.findViewById(R.id.popular_name);
        popularTextView.setText(currentPopular.getAlbumName());

        TextView popularArtistTextView = (TextView) currentListViewItem.findViewById(R.id.popular_artist);
        popularArtistTextView.setText(currentPopular.getAlbumArtist());


        return currentListViewItem;
    }
}
