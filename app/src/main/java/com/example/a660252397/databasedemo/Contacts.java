package com.example.a660252397.databasedemo;

/**
 * Created by 660252397 on 7/12/2017.
 */

public class Contacts {

    //define variables for the columns
    private int _id;
    private String firstname;
    private String lastname;

    //default constructor
    public Contacts(){}

    public Contacts(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    /***********************
        Accessor Methods
     *********************/
    public int get_id(){
        return _id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    /***********************
        Mutator Methods
     *********************/
    public void set_id(int _id) {
        this._id = _id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
