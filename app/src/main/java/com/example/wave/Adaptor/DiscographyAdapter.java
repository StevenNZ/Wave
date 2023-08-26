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
import com.example.wave.Entities.Discography;
import com.example.wave.Entities.HipHopDiscography;
import com.example.wave.Entities.KPopDiscography;
import com.example.wave.R;

import java.util.List;

public class DiscographyAdapter extends RecyclerView.Adapter<DiscographyAdapter.ViewHolder> {

    private List<Discography> discographyList;
    private PopularRecylcerInterface discographyRecyclerInterface;
    private Context mContext;

    private int mLayoutId;

    private boolean isSearchResults = true;

    private static final int VIEW_TYPE_KPOP = 0;
    private static final int VIEW_TYPE_POP = 1;
    private static final int VIEW_TYPE_HIP_HOP = 2;


    /**
     * setter to to see if got as a search result
     * @param isSearchResults
     */
    public void setIsearchResults(boolean isSearchResults){
        this.isSearchResults = isSearchResults;
    }

    /**
     * Setter to set filtered list when searching
     * @param filteredList
     */
    public void setFilteredList(List<Discography> filteredList){
        Log.d("SearchDebug", "onQueryTextChange: filteredList = " + filteredList);
        discographyList = filteredList;
        Log.d("SearchDebug", "onQueryTextChange: mPopular = " + discographyList);
        notifyDataSetChanged();
    }

    /**
     * Create discography adapater
     * @param context
     * @param resource
     * @param objects
     * @param popularRecylcerInterface
     */
    public DiscographyAdapter(Context context, int resource, @NonNull List objects, PopularRecylcerInterface popularRecylcerInterface){
        mLayoutId = resource;
        discographyList = objects;
        mContext  = context;
        discographyRecyclerInterface = popularRecylcerInterface;
    }

    /**
     * Create view holder, based on what type of category selected
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mLayoutId, parent, false);

        switch (viewType){
            case VIEW_TYPE_KPOP:
                return new KPopViewHolder(view, discographyRecyclerInterface);
            case VIEW_TYPE_POP:
                return new PopViewHolder(view, discographyRecyclerInterface);
            default:
                //search will be this one as well
                return new HipHopViewHolder(view, discographyRecyclerInterface);
        }

    }

    /**
     * Binding the view holder to whatever fields we want
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Discography currentItem = discographyList.get(position);


        if(isSearchResults){
            holder.bindCommonData(currentItem);
        }else{
            if (holder instanceof KPopViewHolder) {
                ((KPopViewHolder) holder).bindData(currentItem);
            } else if (holder instanceof PopViewHolder) {
                ((PopViewHolder) holder).bindData(currentItem);
            } else if (holder instanceof HipHopViewHolder) {
                ((HipHopViewHolder) holder).bindData(currentItem);
            }
        }


    }

    @Override
    public int getItemCount() {
        return discographyList.size();
    }

    @Override
    public int getItemViewType(int position) {
        Discography discography = discographyList.get(position);
        String categoryId = discography.getCategoryID();

        switch (categoryId) {
            case "kpop":
                return VIEW_TYPE_KPOP;
            case "pop":
                return VIEW_TYPE_POP;
            default:
                return VIEW_TYPE_HIP_HOP;
        }
    }

    /**
     * Creates Viewholders with their specific fields we want
     */
    public abstract class ViewHolder extends RecyclerView.ViewHolder {
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

        /**
         * Common data binding
         * @param discography
         */
        public void bindCommonData(Discography discography) {
            discogName.setText(discography.getReleaseName());
            discogArtist.setText(discography.getArtistID());
            Glide.with(itemView.getContext()).load(discography.getImageURL()).into(discogImage);

        }

        protected abstract void bindData(Discography discography);
    }

    /**
     * Kpop binding data; specific for KPOP only
     */
    public class KPopViewHolder extends ViewHolder {

        TextView fandomName;

        public KPopViewHolder(@NonNull View itemView, PopularRecylcerInterface discographyRecyclerInterface) {
            super(itemView, discographyRecyclerInterface);
            fandomName = itemView.findViewById(R.id.kpop_fandom_name);
        }

        @Override
        protected void bindData(Discography discography) {
            super.bindCommonData(discography);
            // Handle KPop category-specific data binding
            // For example, fandon name etc
            KPopDiscography kPopDiscography = (KPopDiscography) discography;
            fandomName.setText(kPopDiscography.getFandomName());
            Log.d("SEARCH DEBUG", "IS THIS KPOP?? " + discography.getClass());

        }

    }

    /**
     * Pop item specific binding
     */
    public class PopViewHolder extends ViewHolder {

        public PopViewHolder(@NonNull View itemView, PopularRecylcerInterface discographyRecyclerInterface) {
            super(itemView, discographyRecyclerInterface);
        }

        @Override
        protected void bindData(Discography discography) {
            super.bindCommonData(discography);
            // Handle KPop category-specific data binding
            // For example, setting text and images specific to KPop category
        }

    }

    /**
     * HipHop item specific binding
     */
    public class HipHopViewHolder extends ViewHolder {

        public HipHopViewHolder(@NonNull View itemView, PopularRecylcerInterface discographyRecyclerInterface) {
            super(itemView, discographyRecyclerInterface);
        }

        @Override
        protected void bindData(Discography discography) {
            super.bindCommonData(discography);


            // Handle KPop category-specific data binding
            // For example, setting text and images specific to KPop category
        }

    }




}

