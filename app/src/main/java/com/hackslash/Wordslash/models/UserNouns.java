package com.hackslash.Wordslash.models;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by snehil on 1/1/17.
 */

public class UserNouns {


    public String u_id;
    public String u_name;
    public String noun;
    public String desc;

    public Map<String, Boolean> star = new HashMap<>();


    public UserNouns(){}


    public UserNouns(String u_id,String u_name,String word,String meaning,boolean t_value) {

        this.u_id=u_id;
        this.u_name=u_name;
        this.noun=word;
        this.desc = meaning;
        this.star.put(u_id,t_value);

        //this.dept=dept;
    }


    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", u_id);
        result.put("uname", u_name);
        result.put("noun", noun);
        result.put("desc", desc);
        result.put("star",star);
        //       result.put("starCount", starCount);
        //      result.put("stars",stars);
        return result;
    }


}


