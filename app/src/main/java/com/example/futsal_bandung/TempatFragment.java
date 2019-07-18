package com.example.futsal_bandung;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class TempatFragment extends Fragment {

    private RecyclerView recyclerView;
    private DatabaseReference mDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.fragment_tempat, container, false);

        mDatabase= FirebaseDatabase.getInstance().getReference().child("tempat");
        mDatabase.keepSynced(true);

        recyclerView= view.findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        final FirebaseRecyclerAdapter<Post, PostViewHolder>firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Post, PostViewHolder>
                (Post.class, R.layout.item_recycle, PostViewHolder.class, mDatabase) {
            @Override
            protected void populateViewHolder(PostViewHolder postViewHolder, Post model, int i) {
                postViewHolder.setNama(model.getNama_tempat());
                postViewHolder.setAlamat(model.getAlamat());
                postViewHolder.setImage(getActivity().getApplicationContext(), model.getFoto_url());

            }

        };

        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder
    {
        View mview;



        public PostViewHolder(View itemView){
            super(itemView);
            mview = itemView;
        }

        public void setNama(String nama_tempat) {
            TextView nama =(TextView) mview.findViewById(R.id.nama_tempat);
            nama.setText(nama_tempat);
        }

        public void setAlamat(String alamat) {
            TextView alamat_tempat =(TextView) mview.findViewById(R.id.alamat_tempat);
            alamat_tempat.setText(alamat);
        }
        public void setImage(Context ctx, String foto_url) {
            ImageView imageView =(ImageView) mview.findViewById(R.id.imagefutsal);
            Picasso.with(ctx).load(foto_url).into(imageView);
        }
    }

}
