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

public class CategoryAdapter extends ArrayAdapter {

    int mLayoutID;
    List<Category> mCategories;
    Context mContext;


    public CategoryAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        mLayoutID = resource;
        mCategories = objects;
        mContext  = context;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Get a reference to the current ListView item
        View currentListViewItem = convertView;

        // Check if the existing view is being reused, otherwise inflate the view
        if (currentListViewItem == null) {
            currentListViewItem = LayoutInflater.from(getContext()).inflate(mLayoutID, parent, false);
        }
        //Get the category object for the current position
        Category currentCategory = mCategories.get(position);

        //Set the attributed of list_view_number_item views
        ImageView categoryImageView = (ImageView) currentListViewItem.findViewById(R.id.category_image);
        int i = mContext.getResources().getIdentifier(
                currentCategory.getCategoryImage(), "drawable",
                mContext.getPackageName());

        //Setting the category Image
        categoryImageView.setImageResource(i);

        TextView categoryTextView = (TextView) currentListViewItem.findViewById(R.id.category_text);
        categoryTextView.setText(currentCategory.getCategoryName());


        return currentListViewItem;
    }
}
