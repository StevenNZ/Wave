package com.example.wave.Adaptor;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.wave.Activities.CategoryBreakdown;
import com.example.wave.Activities.CategoryRecyclerInterface;

import com.example.wave.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private final CategoryRecyclerInterface categoryRecyclerInterface;
    int mLayoutID;
    List<CategoryBreakdown> mCategories;
    Context mContext;

    public CategoryAdapter(Context context, int resource, @NonNull List objects, CategoryRecyclerInterface categoryRecyclerInterface){
        mLayoutID = resource;
        mCategories = objects;
        mContext  = context;
        this.categoryRecyclerInterface = categoryRecyclerInterface;

    }

    @NonNull
    @Override
    //inflates each individual layout
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list_item, parent, false);
        return new ViewHolder(view, categoryRecyclerInterface);
    }

    //binding of all the data to each widget
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the data for the current position

        CategoryBreakdown currentCategory = mCategories.get(position);

        String categoryName = currentCategory.getCategoryName();
        String categoryImageName = currentCategory.getCategoryImage();
        String categoryID = currentCategory.getCategoryID();

        // Set the category name
        holder.categoryTextView.setText(categoryName);

        // Set the category image
        Glide.with(mContext).load(categoryImageName).into(holder.categoryImageView);
    }


    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView categoryImageView;
        TextView categoryTextView;

        public ViewHolder(View itemView, CategoryRecyclerInterface categoryRecyclerInterface) {
            super(itemView);
            categoryImageView = itemView.findViewById(R.id.category_image);
            categoryTextView = itemView.findViewById(R.id.category_text);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (categoryRecyclerInterface != null){
                        int pos = getAbsoluteAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            categoryRecyclerInterface.onItemClick(pos);

                        }
                    }
                }
            });
        }
    }


}
