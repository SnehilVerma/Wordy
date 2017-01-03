package com.hackslash.Wordslash.models;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by snehil on 3/1/17.
 */

public class Adjective {


    public String adj;
    public String meaning;
    public Map<String, Boolean> star = new HashMap<>();

    public Adjective() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Adjective(String word,String meaning) {

        this.adj=word;
        this.meaning = meaning;




    }

}
