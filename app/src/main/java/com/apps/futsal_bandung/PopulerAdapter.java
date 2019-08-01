package com.apps.futsal_bandung;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.futsal_bandung.R;

import java.util.ArrayList;

public class PopulerAdapter extends RecyclerView.Adapter<PopulerAdapter.ViewHolder> {
    private ArrayList<ItemRecycle> mappsList;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imagefutsal);
            mTextView1 = itemView.findViewById(R.id.nama_tempat);
            mTextView2 = itemView.findViewById(R.id.alamat_tempat);

        }
    }

    public PopulerAdapter(ArrayList<ItemRecycle> appsList) {

        mappsList = appsList;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle, parent, false);
        ViewHolder evh = new ViewHolder(v);

        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemRecycle currenItem = mappsList.get(position);

        holder.mImageView.setImageResource(currenItem.getmImageResource());
        holder.mTextView1.setText(currenItem.getText1());
        holder.mTextView2.setText(currenItem.getText2());


    }

    @Override
    public int getItemCount() {
        return mappsList.size();
    }

}
