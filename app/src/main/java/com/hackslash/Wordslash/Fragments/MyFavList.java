package com.hackslash.Wordslash.Fragments;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

/**
 * Created by snehil on 21/12/16.
 */

public class MyFavList extends MeaningList {

    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        Query myWordsQuery = databaseReference.child("UserFav")
                .child(getUid());

        return myWordsQuery;
    }
}
