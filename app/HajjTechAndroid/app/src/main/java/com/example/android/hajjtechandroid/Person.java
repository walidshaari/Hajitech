package com.example.android.hajjtechandroid;

public class Person {

    String fistName, hajjRecord,lastName, nationality, phoneNumer;


    public Person(){}

    public Person(String fistName, String hajjRecord, String lastName, String nationality, String phoneNumer) {
        this.fistName = fistName;
        this.hajjRecord = hajjRecord;
        this.lastName = lastName;
        this.nationality = nationality;
        this.phoneNumer = phoneNumer;
    }


    public String getFistName() {
        return fistName;
    }

    public void setFistName(String fistName) {
        this.fistName = fistName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPhoneNumer() {
        return phoneNumer;
    }

    public void setPhoneNumer(String phoneNumer) {
        this.phoneNumer = phoneNumer;
    }
}
