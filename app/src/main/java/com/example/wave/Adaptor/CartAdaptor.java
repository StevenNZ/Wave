package com.example.wave.Adaptor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.wave.Activities.PopularRecylcerInterface;
import com.example.wave.Domains.AuthenticationUserUseCase;
import com.example.wave.Domains.GetArtistUseCase;
import com.example.wave.Domains.GetCartUseCase;
import com.example.wave.Domains.GetDiscographyUseCase;
import com.example.wave.Domains.GetWishlistUseCase;
import com.example.wave.Entities.Artist;
import com.example.wave.Entities.CartOrder;
import com.example.wave.Entities.Discography;
import com.example.wave.Entities.Order;
import com.example.wave.R;
import com.example.wave.ViewModel.WishlistViewModel;
import com.like.LikeButton;

import java.util.List;

public class CartAdaptor extends RecyclerView.Adapter<CartAdaptor.ViewHolder> {

    private List<CartOrder> cartOrdersList;
    private Context context;
    private int mLayoutId;
    private PopularRecylcerInterface popularRecylcerInterface;
    private GetWishlistUseCase getWishlistUseCase = new GetWishlistUseCase();

    public CartAdaptor(Context context, int resource, @NonNull List objects, PopularRecylcerInterface popularRecylcerInterface) {
        this.context = context;
        mLayoutId = resource;
        cartOrdersList = objects;
        this.popularRecylcerInterface = popularRecylcerInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_list_item, parent, false);
        return new ViewHolder(itemView, popularRecylcerInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        CartOrder currentCartOrder = cartOrdersList.get(position);
        AuthenticationUserUseCase authenticationUserUseCase = new AuthenticationUserUseCase();
        GetCartUseCase getCartUseCase = new GetCartUseCase();

        GetDiscographyUseCase getDiscographyUseCase = new GetDiscographyUseCase();
        String currentDiscographyID = currentCartOrder.getDiscographyID();

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
                holder.discogPrice.setText(String.valueOf(currentCartOrder.getPrice()));
                holder.discogQuantity.setText(String.valueOf(currentCartOrder.getQuantity()));

                holder.increaseQuantityButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String quantityString = currentCartOrder.getQuantity();
                        int quantity = Integer.parseInt(quantityString);

                        quantity++;
                        currentCartOrder.setQuantity(String.valueOf(quantity));
                        holder.discogQuantity.setText(String.valueOf(quantity));
                        if (authenticationUserUseCase.isLogin()) {
                            getCartUseCase.updateQuantityByOrderID(authenticationUserUseCase.getUserID(), currentCartOrder.getOrderID(), String.valueOf(quantity));
                        }

                    }
                });

                holder.decreaseQuantityButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String quantityString = currentCartOrder.getQuantity();
                        int quantity = Integer.parseInt(quantityString);

                        if(quantity > 1){
                            quantity--;
                            currentCartOrder.setQuantity(String.valueOf(quantity));
                            holder.discogQuantity.setText(String.valueOf(quantity));
                            if (authenticationUserUseCase.isLogin()) {
                                getCartUseCase.updateQuantityByOrderID(authenticationUserUseCase.getUserID(), currentCartOrder.getOrderID(), String.valueOf(quantity));
                            }
                        }
                        else if (quantity == 1){
                            if (authenticationUserUseCase.isLogin()) {
                                getCartUseCase.removeFromCartByOrderID(authenticationUserUseCase.getUserID(), currentCartOrder.getOrderID());
                            }
                            cartOrdersList.remove(position);
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position, cartOrdersList.size());
                        }


                    }
                });




            }
        });



    }

    @Override
    public int getItemCount() {
        return cartOrdersList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView discogImage;
        TextView discogName, discogArtist, discogPrice, discogQuantity;
        Button removeButton;
        ImageButton increaseQuantityButton, decreaseQuantityButton;

        public ViewHolder(View itemView, PopularRecylcerInterface popularRecylcerInterface) {
            super(itemView);
            discogImage = itemView.findViewById(R.id.popular_image);
            discogName = itemView.findViewById(R.id.popular_name);
            discogArtist = itemView.findViewById(R.id.popular_artist);
            discogPrice = itemView.findViewById(R.id.priceTextBox);
            discogQuantity = itemView.findViewById(R.id.quantityField);
            increaseQuantityButton = itemView.findViewById(R.id.incrementBtn);
            decreaseQuantityButton = itemView.findViewById(R.id.decrementBtn);

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
