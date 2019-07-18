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


public class RecFragment extends Fragment {

    private RecyclerView recyclerView;
    private DatabaseReference mDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_rec, container, false);

        mDatabase= FirebaseDatabase.getInstance().getReference().child("populer");
        mDatabase.keepSynced(true);

        recyclerView= view.findViewById(R.id.recycle1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<PostPopuler, PopulerViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<PostPopuler, PopulerViewHolder>
                (PostPopuler.class, R.layout.populer_recycle, RecFragment.PopulerViewHolder.class, mDatabase) {
            @Override
            protected void populateViewHolder(PopulerViewHolder populerViewHolder, PostPopuler postPopuler, int i) {
                populerViewHolder.setNama(postPopuler.getNama());
                populerViewHolder.setRating(postPopuler.getRating());
                populerViewHolder.setAlamat(postPopuler.getAlamat());
                populerViewHolder.setImage(getActivity().getApplicationContext(), postPopuler.getImg());
            }
        };

        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class PopulerViewHolder extends RecyclerView.ViewHolder
    {
        View mview;

        public PopulerViewHolder(View itemView){
            super(itemView);
            mview = itemView;
        }

        public void setNama(String nama) {
            TextView nama_tempat =(TextView) mview.findViewById(R.id.nama_tempat1);
            nama_tempat.setText(nama);
        }

        public void setAlamat(String alamat) {
            TextView alamat_tempat =(TextView) mview.findViewById(R.id.alamat_tempat1);
            alamat_tempat.setText(alamat);
        }

        public void setRating(Double rating) {
            TextView rating_tempat =(TextView) mview.findViewById(R.id.rating);
            String string = Double.toString(rating);
            rating_tempat.setText(string);
        }
        public void setImage(Context ctx, String img) {
            ImageView imageView =(ImageView) mview.findViewById(R.id.imagefutsal1);
            Picasso.with(ctx).load(img).into(imageView);
        }
    }
}
