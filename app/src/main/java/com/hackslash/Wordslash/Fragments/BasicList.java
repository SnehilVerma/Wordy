package com.hackslash.Wordslash.Fragments;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

/**
 * Created by snehil on 17/12/16.
 */

public class BasicList extends MeaningList {


    public BasicList(){}


    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        Query allWordsQuery = databaseReference.child("Basic")
                .limitToFirst(100);
        // [END recent_posts_query]

        return allWordsQuery;
    }
}
