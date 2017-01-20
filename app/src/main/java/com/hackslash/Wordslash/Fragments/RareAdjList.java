package com.hackslash.Wordslash.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Query;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;
import com.hackslash.Wordslash.R;
import com.hackslash.Wordslash.SynAnt2;
import com.hackslash.Wordslash.ViewHolder.AdjHolder;
import com.hackslash.Wordslash.models.Adjective;
import com.hackslash.Wordslash.models.Antonyms;
import com.hackslash.Wordslash.models.Synonyms;
import com.hackslash.Wordslash.models.User;
import com.hackslash.Wordslash.models.UserAdj;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by snehil on 3/1/17.
 */

public abstract class RareAdjList extends Fragment{

    private static final String TAG = "RareListFragment";

    private DatabaseReference mDatabase;
    private DatabaseReference starBase;


    //to load images



    private FirebaseAuth auth;
    private FirebaseRecyclerAdapter<Adjective, AdjHolder> mAdapter;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;

    public RareAdjList() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.activity_all_adj, container, false);

        // [START create_database_reference]
        mDatabase = FirebaseDatabase.getInstance().getReference();


        // [END create_database_reference]

        auth = FirebaseAuth.getInstance();
        auth = FirebaseAuth.getInstance();

        mRecycler = (RecyclerView) rootView.findViewById(R.id.adj_list);
        mRecycler.setHasFixedSize(true);
        return rootView;


    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Set up Layout Manager, reverse layout
        mManager = new LinearLayoutManager(getActivity());
        //mManager.setItemPrefetchEnabled();

        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecycler.setLayoutManager(mManager);

        // Set up FirebaseRecyclerAdapter with the Query
        Query adjQuery = getQuery(mDatabase);




        mAdapter = new FirebaseRecyclerAdapter<Adjective, AdjHolder>(Adjective.class, R.layout.item_adj,
                AdjHolder.class, adjQuery) {

            @Override
            protected void populateViewHolder(final AdjHolder viewHolder, final Adjective model, final int position) {
                final DatabaseReference wordRef = getRef(position);


                final String postKey = wordRef.getKey();
                // Set click listener for the whole post view



                if(model.star.containsKey(getUid())){
                    viewHolder.star.setImageResource(R.drawable.heartx);

                }else{
                    viewHolder.star.setImageResource(R.drawable.hearty);
                }



                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Toast.makeText(getActivity(),"wait for details",Toast.LENGTH_SHORT).show();


                        /*
                        mDatabase.child("Details").child(viewHolder.getAdj()).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                AdjDetail wordDetail = dataSnapshot.getValue(AdjDetail.class);
                                //Toast.makeText(getActivity(), wordDetail.u1 + " & " + wordDetail.u2, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getActivity(), AdjDetailActivity.class);
                                Bundle extras = new Bundle();
                                extras.putString("word",model.adj);
                                extras.putString("pro",wordDetail.pro);
                                extras.putString("u1", wordDetail.u1);
                                extras.putString("u2", wordDetail.u2);
                                intent.putExtras(extras);
                                startActivity(intent);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                        */









                    }
                });


                viewHolder.bindToPost(model, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                mDatabase.child("AdjAntonyms").child(viewHolder.getAdj()).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        Antonyms antonyms = dataSnapshot.getValue(Antonyms.class);
                                        Toast.makeText(getActivity(), antonyms.a1 + " & " + antonyms.a2, Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getActivity(), SynAnt2.class);
                                        Bundle extras = new Bundle();
                                        String stat="ant";
                                        extras.putString("stat",stat);
                                        extras.putString("a1", antonyms.a1);
                                        extras.putString("a2", antonyms.a2);
                                        intent.putExtras(extras);
                                        startActivity(intent);
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });


                            }
                        }, new View.OnClickListener() {

                            @Override
                            public void onClick(View view) {

                                mDatabase.child("AdjSynonyms").child(viewHolder.getAdj()).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        Synonyms synonyms = dataSnapshot.getValue(Synonyms.class);
                                        Toast.makeText(getActivity(), synonyms.s1 + " & " + synonyms.s2, Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getActivity(), SynAnt2.class);
                                        Bundle extras = new Bundle();
                                        String stat="syn";
                                        extras.putString("stat",stat);
                                        extras.putString("s1", synonyms.s1);
                                        extras.putString("s2", synonyms.s2);
                                        intent.putExtras(extras);
                                        startActivity(intent);
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });


                            }
                        }, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {






                                mDatabase.child("UserAdjFav").child(getUid()).child(model.adj).addListenerForSingleValueEvent(
                                        new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                //get user value
                                                UserAdj um=dataSnapshot.getValue(UserAdj.class);


                                                if(um!=null){

                                                    Toast.makeText(getActivity(),"Already added to favs",Toast.LENGTH_SHORT).show();

                                                }
                                                else{


                                                    viewHolder.star.setImageResource(R.drawable.heartx);




                                                    FirebaseUser user=auth.getCurrentUser();
                                                    final String word=viewHolder.getAdj();
                                                    final String meaning=viewHolder.getMeaning();


                                                    final String userId=user.getUid();

                                                    mDatabase.child("User").child(userId).addListenerForSingleValueEvent(
                                                            new ValueEventListener() {
                                                                @Override
                                                                public void onDataChange(DataSnapshot dataSnapshot) {
                                                                    //get user value
                                                                    User user=dataSnapshot.getValue(User.class);

                                                                    if(user==null){
                                                                        // User is null, error out
                                                                        Log.e(TAG, "User " + userId + " is unexpectedly null");
                                                                        Toast.makeText(getActivity(),
                                                                                "Error: could not fetch user.",
                                                                                Toast.LENGTH_SHORT).show();
                                                                    } else {
                                                                        // Write new post
                                                                        writeToFav(userId, user.username, word, meaning,model);
                                                                    }

                                                                }


                                                                @Override
                                                                public void onCancelled(DatabaseError databaseError) {

                                                                }
                                                            }
                                                    );


                                                }

                                            }


                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        }
                                );






                                //model.star.put(getUid(),true);

                                /*
                                if(model.star.containsKey(getUid())){
                                    viewHolder.star.setImageResource(R.drawable.heart);

                                }else{
                                    viewHolder.star.setImageResource(R.drawable.star);
                                }*/

                                /*

                                String pos=Integer.toString(position);

                                starBase.child(model.category).child(pos).child(model.word).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        dataSnapshot.getRef().child("star").child(getUid()).setValue(true);
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });

                                */










                            }
                        }
                );
            }
        };


        mRecycler.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }




    public void writeToFav(String userid, String username, String word, String meaning, Adjective model){

        //String key = mDatabase.child("UserFav").push().getKey();

        UserAdj userfav = new UserAdj(userid, username, word, meaning, true);
        Map<String, Object> userfavValues = userfav.toMap();

        Map<String, Object> childUpdates = new HashMap<>();

        //Gg Snehil Verma.
        childUpdates.put("/UserAdjFav/" + userid +"/" + word, userfavValues);

        Toast.makeText(getActivity().getApplicationContext(),"Added to Favorites",Toast.LENGTH_SHORT).show();



        //childUpdates.put("/UserFav/" + userid + "/" + key, userfavValues);

        FirebaseUser user=auth.getCurrentUser();

        //DatabaseReference UserFavRef = mDatabase.child("UserFav").child(user.getUid()).child(word);


        //Toast.makeText(getActivity(),UserFavRef.toString(),Toast.LENGTH_SHORT).show();


        //onStarClicked(UserFavRef);


        mDatabase.updateChildren(childUpdates);





    }




    private void onStarClicked(DatabaseReference reference){
        reference.runTransaction(new Transaction.Handler() {

            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                UserAdj u=mutableData.getValue(UserAdj.class);
                if(u==null){
                    return Transaction.success(mutableData);
                }
                if(u.star.containsKey(getUid())){

                    Toast.makeText(getActivity().getApplicationContext(),"Already in favorites",Toast.LENGTH_SHORT).show();
                }
                else {
                    //model.star.put(getUid(),true);
                    u.star.put(getUid(),true);
                    Toast.makeText(getActivity().getApplicationContext(),"Added to Favorites",Toast.LENGTH_SHORT).show();
                }

                mutableData.setValue(u);
                return Transaction.success(mutableData);

            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {

                Log.d(TAG, "postTransaction:onComplete:" + databaseError);

            }
        });


    }







    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mAdapter != null) {
            mAdapter.cleanup();
        }
    }









    public String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public abstract Query getQuery(DatabaseReference databaseReference);








}
