package com.example.futsal_bandung;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
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

    private void searchQuery(String searchText){
        Query firebaseSearchQuery = mDatabase.orderByChild("nama_tempat").startAt(searchText).endAt(searchText + "\uf8ff");

        FirebaseRecyclerAdapter<Post, PostViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Post, PostViewHolder>(
                        Post.class,
                        R.layout.item_recycle,
                        PostViewHolder.class,
                        firebaseSearchQuery
                ) {
                    @Override
                    protected void populateViewHolder(PostViewHolder postViewHolder, Post model, int i) {
                        postViewHolder.setNama(model.getNama_tempat());
                        postViewHolder.setAlamat(model.getAlamat());
                        postViewHolder.setImage(getActivity().getApplicationContext(), model.getFoto_url());
                    }
                };

        recyclerView.setAdapter(firebaseRecyclerAdapter);

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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchQuery(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                searchQuery(newText);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id ==  R.id.action_setting) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
