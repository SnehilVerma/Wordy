package com.hackslash.Wordslash.Fragments;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

/**
 * Created by snehil on 18/12/16.
 */

public class IntermediateList extends MeaningList {

    public IntermediateList(){}


    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        Query allWordsQuery = databaseReference.child("Intermediate")
                .limitToFirst(100);
        // [END recent_posts_query]

        return allWordsQuery;
    }
}
