package com.example.wongnaiinternassignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wongnaiinternassignment.Models.Coin;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    LayoutInflater mInflater;
    List<Coin> mCoin;

    public Adapter(Context ctx, List<Coin> coin){
        this.mInflater = LayoutInflater.from(ctx);
        this.mCoin = coin;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_list_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.mTextViewName.setText(mCoin.get(position).getmName());
        holder.mTextViewDescription.setText(mCoin.get(position).getmDescription());
        Picasso.get().load(mCoin.get(position).getmImageURL()).into(holder.mImageView);


    }

    @Override
    public int getItemCount() {
        return mCoin.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView mTextViewName, mTextViewDescription;
        ImageView mImageView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mTextViewName = itemView.findViewById(R.id.coinName);
            mTextViewDescription = itemView.findViewById(R.id.coinDescription);
            mImageView = itemView.findViewById(R.id.coinImage);


        }
    }
}
