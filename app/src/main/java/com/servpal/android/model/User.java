package com.servpal.android.model;


public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String email;

    private int roleID;
    private String role;

    private String accountKey;
    private String customerKey;

    public int getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public int getRoleID() {
        return roleID;
    }

    public String getRole() {
        return role;
    }

    public String getAccountKey() {
        return accountKey;
    }

    public String getCustomerKey() {
        return customerKey;
    }

    public String getFirstName() {
        return this.firstName;
    }
}
