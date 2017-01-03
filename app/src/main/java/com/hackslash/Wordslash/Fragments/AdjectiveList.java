package com.hackslash.Wordslash.Fragments;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

/**
 * Created by snehil on 3/1/17.
 */

public class AdjectiveList extends RareAdjList {

    public AdjectiveList(){}

    @Override
    public Query getQuery(DatabaseReference databaseReference) {

        Query allWordsQuery = databaseReference.child("Adjectives")
                .limitToFirst(100);


        return allWordsQuery;
    }


}
