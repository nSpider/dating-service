package com.dray.dating.models;

public class User {
    String userId;
    CoOrdinates coOrdinates;
    int age;
    Gender gender;

    public User() {
    }

    public User(String userId, CoOrdinates coOrdinates, int age, Gender gender) {
        this.userId = userId;
        this.coOrdinates = coOrdinates;
        this.age = age;
        this.gender = gender;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public CoOrdinates getCoOrdinates() {
        return coOrdinates;
    }

    public void setCoOrdinates(CoOrdinates coOrdinates) {
        this.coOrdinates = coOrdinates;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public User withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public User withCoOrdinates(CoOrdinates coOrdinates) {
        this.coOrdinates = coOrdinates;
        return this;
    }

    public User withAge(int age) {
        this.age = age;
        return this;
    }

    public Gender getGender() {
        return gender;
    }

    public User withGender(Gender gender) {
        this.gender = gender;
        return this;
    }
}
