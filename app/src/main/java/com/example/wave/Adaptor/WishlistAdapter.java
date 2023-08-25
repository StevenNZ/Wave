package com.example.wave.Adaptor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.wave.Activities.Popular;
import com.example.wave.Activities.PopularRecylcerInterface;
import com.example.wave.Activities.WishlistActivity;
import com.example.wave.Domains.AuthenticationUserUseCase;
import com.example.wave.Domains.GetArtistUseCase;
import com.example.wave.Domains.GetDiscographyUseCase;
import com.example.wave.Domains.GetWishlistUseCase;
import com.example.wave.Entities.Artist;
import com.example.wave.Entities.Discography;
import com.example.wave.Entities.Order;
import com.example.wave.R;
import com.example.wave.ViewModel.WishlistViewModel;
import com.google.android.gms.tasks.Task;
import com.like.LikeButton;
import com.like.OnLikeListener;
import java.util.List;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.ViewHolder> {

    private List<Order> wishlistItems;
    private Context context;
    private int mLayoutId;
    private PopularRecylcerInterface popularRecylcerInterface;
    private GetWishlistUseCase getWishlistUseCase = new GetWishlistUseCase();
    private WishlistViewModel model;


    public interface OnItemClickListener {
        void onItemClick(int position);
    }


    public WishlistAdapter(Context context, int resource, @NonNull List objects, PopularRecylcerInterface popularRecylcerInterface, ViewModel model) {
        this.context = context;
        mLayoutId = resource;
        wishlistItems = objects;
        this.popularRecylcerInterface = popularRecylcerInterface;

        this.model = (WishlistViewModel) model;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_list_item, parent, false);
        return new ViewHolder(itemView, popularRecylcerInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Order currentItem = wishlistItems.get(position);

        GetDiscographyUseCase getDiscographyUseCase = new GetDiscographyUseCase();

        String currentDiscographyID = currentItem.getDiscographyID();


        getDiscographyUseCase.getDiscographyByDiscographyID(currentDiscographyID).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {

                Discography discography = task.getResult();
                String name = discography.getReleaseName();
                String image = discography.getImageURL();
                String discographyId = discography.getDiscographyID();

                GetArtistUseCase getArtistUseCase = new GetArtistUseCase();
                getArtistUseCase.getArtistByID(discography.getArtistID()).addOnCompleteListener(task1 -> {
                    if(task1.isSuccessful()) {
                        Artist artist = task1.getResult();
                        String artistName = artist.getArtistName();
                        holder.discogArtist.setText(artistName);
                    }
                });


                // Set the category name
                holder.discogName.setText(name);


                // Set the category image
                Glide.with(context).load(image).into(holder.discogImage);

                holder.heartButton.setOnLikeListener(new OnLikeListener() {
                    @Override
                    public void liked(LikeButton likeButton) {
                        Log.d("WISHLIST LIKE DEBUG", "THIS SHOULD NOT BE POSSIBLE");
                    }

                    @Override
                    public void unLiked(LikeButton likeButton) {
                        AuthenticationUserUseCase authenticationUserUseCase = new AuthenticationUserUseCase();
                        if (authenticationUserUseCase.isLogin()) {
                            String userID = authenticationUserUseCase.getUserID();
                            getWishlistUseCase.removeFromWishlistByOrderID(userID, discographyId);
                            wishlistItems.remove(position);
                            model.updateWishlist(wishlistItems);

                        }

                    }
                });
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

