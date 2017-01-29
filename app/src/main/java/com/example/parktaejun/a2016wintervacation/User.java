package com.example.parktaejun.a2016wintervacation;

/**
 * Created by parktaejun on 2017. 1. 29..
 */

public class User {

    String user_id;
    String user_password;
    String user_name;
    String user_age;

    public String getUserID() {
        return user_id;
    }

    public void setUserID(String user_id) {
        this.user_id = user_id;
    }

    public String getUserPassword() {
        return user_password;
    }

    public void setUserPassword(String userPassword) { this.user_password = userPassword; }

    public String getUserName() {
        return user_name;
    }

    public void setUserName(String user_name) {
        this.user_name = user_name;
    }

    public String getUserAge() {
        return user_age;
    }

    public void setUserAge(String user_age) {
        this.user_age = user_age;
    }

    public User(String user_id, String user_password, String user_name, String user_age) {
        this.user_id = user_id;
        this.user_password = user_password;
        this.user_name = user_name;
        this.user_age = user_age;
    }
}
