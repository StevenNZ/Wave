package com.example.wave.Adaptor;

import android.content.Context;
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
import com.example.wave.R;
import com.like.LikeButton;

import java.util.List;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.ViewHolder> {

    private List<CartOrder> orderItems;

    public CartItemAdapter(List<CartOrder> orderItems) {
        this.orderItems = orderItems;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartOrder item = orderItems.get(position);
        holder.discogName.setText(item.getReleaseName());
        holder.discogArtist.setText(item.getArtistName());
        Glide.with(holder.itemView.getContext()).load(item.getReleaseImage()).into(holder.discogImage);
    }

    @Override
    public int getItemCount() {
        return orderItems.size();
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

        }
    }
}
