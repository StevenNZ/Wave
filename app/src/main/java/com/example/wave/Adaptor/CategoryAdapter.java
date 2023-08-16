package com.example.wave.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wave.Activities.Category;
import com.example.wave.Activities.CategoryRecyclerInterface;
import com.example.wave.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private final CategoryRecyclerInterface categoryRecyclerInterface;
    int mLayoutID;
    List<Category> mCategories;
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

        Category currentCategory = mCategories.get(position);

        String categoryName = currentCategory.getCategoryName();
        String categoryImageName = currentCategory.getCategoryImage();

        // Set the category name
        holder.categoryTextView.setText(categoryName);

        // Get the resource ID of the category image
        int imageResourceID = mContext.getResources().getIdentifier(
                categoryImageName, "drawable", mContext.getPackageName());

        // Set the category image
        holder.categoryImageView.setImageResource(imageResourceID);
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