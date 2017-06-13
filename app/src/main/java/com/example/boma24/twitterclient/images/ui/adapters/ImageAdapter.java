package com.example.boma24.twitterclient.images.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.boma24.twitterclient.R;
import com.example.boma24.twitterclient.entities.Image;
import com.example.boma24.twitterclient.lib.base.ImageLoader;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by boma24 on 6/12/17.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private List<Image> dataset;
    private ImageLoader imageLoader;
    private OnItemClickListener clickListener;

    public ImageAdapter(List<Image> dataset, ImageLoader imageLoader, OnItemClickListener clickListener) {
        this.dataset = dataset;
        this.imageLoader = imageLoader;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_images,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Image imageTweet =  dataset.get(position);
        holder.setOnclickListener(imageTweet, clickListener);
        holder.txtTweet.setText(imageTweet.getTweetText());
        imageLoader.load(holder.imageMedia, imageTweet.getImageUrl());
    }

    public void setItems(List<Image> newItems){
        dataset.addAll(newItems);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return this.dataset.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.txtTweet) TextView txtTweet;
        @Bind(R.id.imageMedia) ImageView imageMedia;
        private View view;

        public ViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.view = itemView;
        }

        public void setOnclickListener(final Image image, final OnItemClickListener listener){
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(image);
                }
            });

        }
    }
}
