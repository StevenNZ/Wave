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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.wave.Activities.CategoryRecyclerInterface;
import com.example.wave.Activities.Popular;
import com.example.wave.Activities.PopularRecylcerInterface;
import com.example.wave.R;

import java.util.List;

public class PopularAdaptor extends RecyclerView.Adapter<PopularAdaptor.ViewHolder> {

    private PopularRecylcerInterface popularRecylcerInterface;
    int mLayoutId;
    List<Popular> mPopular;
    Context mContext;

    public void setFilteredList(List<Popular> filteredList){
        Log.d("SearchDebug", "onQueryTextChange: filteredList = " + filteredList);
        mPopular = filteredList;
        Log.d("SearchDebug", "onQueryTextChange: mPopular = " + mPopular);
        notifyDataSetChanged();
    }

    public PopularAdaptor(Context context, int resource, @NonNull List objects, PopularRecylcerInterface popularRecylcerInterface){
        mLayoutId = resource;
        mPopular = objects;
        mContext  = context;
        this.popularRecylcerInterface = popularRecylcerInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mLayoutId, parent, false);
        return new ViewHolder(view, popularRecylcerInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Popular currentItem = mPopular.get(position);

        String name = currentItem.getAlbumName();
        String image = currentItem.getAlbumImage();
        String artist = currentItem.getAlbumArtist();

        // Set the category name
        holder.discogName.setText(name);

        holder.discogArtist.setText(artist);

        // Set the category image
        Glide.with(mContext).load(image).into(holder.discogImage);

    }

    @Override
    public int getItemCount() {
        return mPopular.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView discogImage;
        TextView discogName, discogArtist;

        public ViewHolder(View itemView, PopularRecylcerInterface popularRecylcerInterface) {
            super(itemView);
            discogImage = itemView.findViewById(R.id.popular_image);
            discogName = itemView.findViewById(R.id.popular_name);
            discogArtist = itemView.findViewById(R.id.popular_artist);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (popularRecylcerInterface != null){
                        int pos = getAbsoluteAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            popularRecylcerInterface.onItemClick(pos);

                        }
                    }
                }
            });
        }
    }

    //    public PopularAdaptor(@NonNull Context context, int resource, @NonNull List<Popular> objects) {
//        super(context, resource, objects);
//        mPopular = objects;
//        mContext = context;
//        mLayoutId = resource;
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        //Get a reference to the current ListView item
//        View currentListViewItem = convertView;
//
//        // Check if the existing view is being reused, otherwise inflate the view
//        if (currentListViewItem == null) {
//            currentListViewItem = LayoutInflater.from(getContext()).inflate(mLayoutId, parent, false);
//        }
//
//
//
//        Log.d("SearchDebug", "getView: mPopular = " + mPopular);
//        Log.d("SearchDebug", "getView: mPopular = " + mPopular.size());
//        //Get the Number object for the current position
//        Popular currentPopular = mPopular.get(position);
//        //Set the attributed of list_view_number_item views
//        ImageView iconImageView = (ImageView) currentListViewItem.findViewById(R.id.popular_image);
//        Glide.with(mContext).load(currentPopular.getAlbumImage()).into(iconImageView);
//
//
//        //setting the text
//        TextView popularTextView = (TextView) currentListViewItem.findViewById(R.id.popular_name);
//        popularTextView.setText(currentPopular.getAlbumName());
//
//        TextView popularArtistTextView = (TextView) currentListViewItem.findViewById(R.id.popular_artist);
//        popularArtistTextView.setText(currentPopular.getAlbumArtist());
//
//
//        return currentListViewItem;
//    }
//
//    @Override
//    public int getCount() {
//        return mPopular.size();
//    }


}
