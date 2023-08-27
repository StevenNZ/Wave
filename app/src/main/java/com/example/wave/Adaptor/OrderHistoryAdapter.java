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
import com.example.wave.Activities.PopularRecylcerInterface;
import com.example.wave.Entities.CartOrder;
import com.example.wave.Entities.WishlistOrder;
import com.example.wave.R;
import com.example.wave.ViewModel.WishlistViewModel;
import com.like.LikeButton;

import java.util.List;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.ViewHolder> {

    private List<List<CartOrder>> cartOrders;
    private Context context;
    private int mLayoutId;
    private PopularRecylcerInterface popularRecylcerInterface;

    public OrderHistoryAdapter(Context context, int resource, @NonNull List<List<CartOrder>> objects, PopularRecylcerInterface popularRecylcerInterface) {
        this.context = context;
        mLayoutId = resource;
        cartOrders = objects;
        this.popularRecylcerInterface = popularRecylcerInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(mLayoutId, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        List<CartOrder> currentOrder = cartOrders.get(position);
        // Bind data to views in the ViewHolder here

        if (!currentOrder.isEmpty()) {
            CartOrder item = currentOrder.get(0); // Get the first item from the list

            String name = item.getReleaseName();
            String image = item.getReleaseImage();
            String artistName = item.getArtistName();
            String discographyId = item.getDiscographyID();

            // Set the category name
            holder.discogName.setText(name);
            holder.discogArtist.setText(artistName);

            // Set the category image
            Glide.with(context).load(image).into(holder.discogImage);
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView discogImage;
        TextView discogName, discogArtist;

        LikeButton heartButton;

        public ViewHolder(View itemView) {
            super(itemView);
            discogImage = itemView.findViewById(R.id.popular_image);
            discogName = itemView.findViewById(R.id.orderAlbumName);
            discogArtist = itemView.findViewById(R.id.orderArtistName);
            //heartButton = itemView.findViewById(R.id.heartIcon);

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