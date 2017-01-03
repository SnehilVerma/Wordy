package com.hackslash.Wordslash.models;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by snehil on 1/1/17.
 */

public class RareNoun {


    public String noun;
    public String desc;
    public Map<String, Boolean> star = new HashMap<>();

    public RareNoun() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public RareNoun(String noun,String desc) {

        this.noun=noun;
        this.desc = desc;



    }


}
