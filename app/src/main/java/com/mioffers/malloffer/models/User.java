package com.mioffers.malloffer.models;

/**
 * Created by laxman.muttineni on 23/05/17.
 */
public class User {

    private String name;

    private String id;

    private String emailId;

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
