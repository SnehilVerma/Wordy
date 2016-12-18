package com.hackslash.Wordslash.models;

/**
 * Created by snehil on 16/12/16.
 */

public class UserMeanings {


    public String u_id;
    public String word;
    public String meaning;
    public int cat_id;


    public UserMeanings(String u_id,String word,String meaning,int category) {

        this.u_id=u_id;
        this.word=word;
        this.meaning = meaning;
        this.cat_id = category;
        //this.dept=dept;
    }


}
