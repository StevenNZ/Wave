package com.example.wave.Adaptor;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.wave.Activities.Popular;
import com.example.wave.Activities.PopularRecylcerInterface;
import com.example.wave.R;
import com.like.LikeButton;
import com.like.OnLikeListener;
import java.util.List;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.ViewHolder> {

    private List<Popular> wishlistItems;
    private Context context;
    private int mLayoutId;
    private PopularRecylcerInterface popularRecylcerInterface;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }


    public WishlistAdapter(Context context, int resource, @NonNull List objects, PopularRecylcerInterface popularRecylcerInterface) {
        this.context = context;
        mLayoutId = resource;
        wishlistItems = objects;
        this.popularRecylcerInterface = popularRecylcerInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_list_item, parent, false);
        return new ViewHolder(itemView, popularRecylcerInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Popular currentItem = wishlistItems.get(position);

        String name = currentItem.getAlbumName();
        String image = currentItem.getAlbumImage();
        String artist = currentItem.getAlbumArtist();

        // Set the category name
        holder.discogName.setText(name);

        holder.discogArtist.setText(artist);

        // Set the category image
        Glide.with(context).load(image).into(holder.discogImage);

        holder.heartButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                Log.d("Search DEBUG", "WE LIKED IT BROTHER");
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                Log.d("Search DEBUG", "WE UNLIKED IT BROTHER");

            }
        });





    }

    @Override
    public int getItemCount() {
        return wishlistItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView discogImage;
        TextView discogName, discogArtist;

        LikeButton heartButton;

        public ViewHolder(View itemView, PopularRecylcerInterface popularRecylcerInterface) {
            super(itemView);
            discogImage = itemView.findViewById(R.id.popular_image);
            discogName = itemView.findViewById(R.id.popular_name);
            discogArtist = itemView.findViewById(R.id.popular_artist);
            heartButton = itemView.findViewById(R.id.heartIcon);

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

}

