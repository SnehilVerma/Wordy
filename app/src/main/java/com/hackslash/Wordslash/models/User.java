package com.hackslash.Wordslash.models;

/**
 * Created by snehil on 16/12/16.
 */


public class User{

    public String username;
    public String email;
    public String id;


    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username,String email,String profile) {

        this.username=username;
        this.email = email;
        this.id = profile;
        //this.dept=dept;
    }


    public String getId(){
        return id;
    }

    // public String getEmail(){return email;}
}
