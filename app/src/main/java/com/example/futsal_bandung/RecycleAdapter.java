package com.example.futsal_bandung;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {

    private Context mContext;
    private List<Post> mData;
    RequestOptions option;

    public RecycleAdapter(Context mContext, List<Post> mData) {
        this.mContext = mContext;
        this.mData = mData;

        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        inflater.inflate(R.layout.item_recycle, parent, false);

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.Nama.setText(mData.get(position).getNama_tempat());
        holder.Alamat.setText(mData.get(position).getAlamat());

        Glide.with(mContext).load(mData.get(position).getFoto_url()).apply(option).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView Nama;
        TextView Alamat;
        ImageView imageView;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            Nama = itemView.findViewById(R.id.nama_tempat);
            Alamat = itemView.findViewById(R.id.alamat_tempat);
            imageView = itemView.findViewById(R.id.imagefutsal);
        }
    }

}



