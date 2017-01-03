package com.hackslash.Wordslash.Fragments;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

/**
 * Created by snehil on 1/1/17.
 */

public class Rare extends RareList {

    public Rare(){}

    @Override
    public Query getQuery(DatabaseReference databaseReference) {

        Query allWordsQuery = databaseReference.child("Nouns")
                .limitToFirst(100);


        return allWordsQuery;
    }
}
