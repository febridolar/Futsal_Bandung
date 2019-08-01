package com.apps.futsal_bandung;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.appcompat.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.futsal_bandung.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class TempatFragment extends Fragment {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private DatabaseReference mDatabase,mDatabase1;
    private TextView nama_tempat;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.fragment_tempat, container, false);
      setHasOptionsMenu(true);

      Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar1);
      ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
      toolbar.setTitle("Cari Tempat Futsal");
      toolbar.setTitleTextColor(0xff00796B);



        mDatabase= FirebaseDatabase.getInstance().getReference().child("tempat");
        mDatabase1= FirebaseDatabase.getInstance().getReference("tempat");
        mDatabase.keepSynced(true);
        recyclerView= view.findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        return view;
    }


    private void searchQuery(String searchText){

        Query firebaseSearchQuery = mDatabase1.orderByChild("nama_tempat").startAt(searchText).endAt(searchText + "\uf8ff");

        FirebaseRecyclerAdapter<Post, PostViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Post, PostViewHolder>(
                        Post.class,
                        R.layout.item_recycle,
                        PostViewHolder.class,
                        firebaseSearchQuery
                ) {
                    @Override
                    protected void populateViewHolder(PostViewHolder postViewHolder,final Post model, final int i) {
                        postViewHolder.setNama(model.getNama_tempat());
                        postViewHolder.setAlamat(model.getAlamat());
                        postViewHolder.setImage(getActivity().getApplicationContext(), model.getFoto_url());

                        postViewHolder.card_view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                Toast.makeText(getContext(), model.getNama_tempat(), i).show();
                                Intent intent = new Intent(getContext(), DetailTempat.class);
                                intent.putExtra("nama_tempat",model.getNama_tempat());
                                startActivity(intent);

                            }
                        });
                    }
                };

        recyclerView.setAdapter(firebaseRecyclerAdapter);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        getActivity().getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
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


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);



    }

    @Override
    public void onStart() {
        super.onStart();
        final FirebaseRecyclerAdapter<Post, PostViewHolder>firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Post, PostViewHolder>
                (Post.class, R.layout.item_recycle, PostViewHolder.class, mDatabase) {
            @Override
            protected void populateViewHolder(final PostViewHolder postViewHolder, final Post model, final int i) {
                postViewHolder.setNama(model.getNama_tempat());
                postViewHolder.setAlamat(model.getAlamat());
                postViewHolder.setImage(getActivity().getApplicationContext(), model.getFoto_url());

                postViewHolder.card_view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Toast.makeText(getContext(), model.getNama_tempat(), i).show();
                        Intent intent = new Intent(getContext(), DetailTempat.class);
                        intent.putExtra("nama_tempat",model.getNama_tempat());
                        startActivity(intent);

                    }
                });

            }

        };

        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }


    public static class PostViewHolder extends RecyclerView.ViewHolder
    {
        View mview, card_view;
        public PostViewHolder(final View itemView){
            super(itemView);
            card_view = itemView.findViewById(R.id.parentLayout);
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
