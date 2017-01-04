package com.hackslash.Wordslash.Fragments;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

/**
 * Created by snehil on 4/1/17.
 */

public class IdiomFavList extends IdiomList {

    @Override
    public Query getQuery(DatabaseReference databaseReference) {

        Query allWordsQuery = databaseReference.child("UserIdiomFav").child(getUid());


        return allWordsQuery;
    }
}
