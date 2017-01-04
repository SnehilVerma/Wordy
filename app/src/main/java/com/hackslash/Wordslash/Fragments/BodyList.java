package com.hackslash.Wordslash.Fragments;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

/**
 * Created by snehil on 4/1/17.
 */

public class BodyList extends IdiomList {

    public BodyList(){}


    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        Query allWordsQuery = databaseReference.child("Body Idioms")
                .limitToFirst(100);


        return allWordsQuery;
    }

}
