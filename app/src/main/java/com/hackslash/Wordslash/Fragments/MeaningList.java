package com.hackslash.Wordslash.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hackslash.Wordslash.R;
import com.hackslash.Wordslash.SynAnt;
import com.hackslash.Wordslash.ViewHolder.WordHolder;
import com.hackslash.Wordslash.models.Antonyms;
import com.hackslash.Wordslash.models.Meaning;

/**
 * Created by snehil on 17/12/16.
 */

public abstract class MeaningList extends Fragment {


    private static final String TAG = "MeaningListFragment";

    private DatabaseReference mDatabase;

    private FirebaseRecyclerAdapter<Meaning, WordHolder> mAdapter;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;

    public MeaningList() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.activity_all_mean, container, false);




        // [START create_database_reference]
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [END create_database_reference]
        mRecycler = (RecyclerView) rootView.findViewById(R.id.meanings_list);
        mRecycler.setHasFixedSize(true);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Set up Layout Manager, reverse layout
        mManager = new LinearLayoutManager(getActivity());
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecycler.setLayoutManager(mManager);

        // Set up FirebaseRecyclerAdapter with the Query
        Query wordsQuery = getQuery(mDatabase);

        mAdapter = new FirebaseRecyclerAdapter<Meaning, WordHolder>(Meaning.class, R.layout.item_word,
                WordHolder.class, wordsQuery) {

            @Override
            protected void populateViewHolder(final WordHolder viewHolder, final Meaning model, final int position) {
                final DatabaseReference wordRef = getRef(position);


                final String postKey = wordRef.getKey();
                // Set click listener for the whole post view



                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Toast.makeText(getActivity(),"wait for details",Toast.LENGTH_SHORT).show();







                        //Intent intent=new Intent(getActivity(), WordDetail.class);

                        //intent.putExtra(WordDetail.EXTRA_POST_KEY,postKey);
                        //startActivity(intent);


                        /*
                        // Launch PostDetailActivity
                        Intent intent = new Intent(getActivity(), PostDetailActivity.class);
                        intent.putExtra(PostDetailActivity.EXTRA_POST_KEY, postKey);
                        startActivity(intent);
                        */
                    }
                });

                /*
                // Determine if the current user has liked this post and set UI accordingly
                if (model.stars.containsKey(getUid())) {
                    viewHolder.starView.setImageResource(R.drawable.ic_toggle_star_24);
                } else {
                    viewHolder.starView.setImageResource(R.drawable.ic_toggle_star_outline_24);
                }
                */
                /*
                // Bind Post to ViewHolder, setting OnClickListener for the star button
              viewHolder.bindToPost(model, new View.OnClickListener() {
                    @Override
                    public void onClick(View starView) {
                        // Need to write to both places the post is stored
                        DatabaseReference globalPostRef = mDatabase.child("posts").child(postRef.getKey());
                        DatabaseReference userPostRef = mDatabase.child("user-posts").child(model.uid).child(postRef.getKey());

                        // Run two transactions
                        onStarClicked(globalPostRef);
                        onStarClicked(userPostRef);
                    }
                });*/


                viewHolder.bindToPost(model, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        mDatabase.child("Antonyms").child(viewHolder.getWord()).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Antonyms antonyms=dataSnapshot.getValue(Antonyms.class);
                                Toast.makeText(getActivity(),antonyms.a1+" & "+antonyms.a2,Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(getActivity(), SynAnt.class);
                                Bundle extras = new Bundle();
                                extras.putString("a1",antonyms.a1 );
                                extras.putString("a2",antonyms.a2);
                                intent.putExtras(extras);
                                startActivity(intent);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });


                    }
                });
            }
        };


        mRecycler.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mAdapter != null) {
            mAdapter.cleanup();
        }
    }

    public abstract Query getQuery(DatabaseReference databaseReference);


}
