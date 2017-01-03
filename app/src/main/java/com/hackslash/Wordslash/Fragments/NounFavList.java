package com.hackslash.Wordslash.Fragments;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

/**
 * Created by snehil on 1/1/17.
 */

public class NounFavList extends RareList {



    @Override
    public Query getQuery(DatabaseReference databaseReference) {

        Query allWordsQuery = databaseReference.child("UserNounFav").child(getUid());


        return allWordsQuery;
    }
}
