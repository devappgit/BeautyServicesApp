package com.servpal.android.model;


/**
 * Seems to be a Service Provider that has address, business hours, and location Id's
 */
public class Professional {

    private int id;
    private int professionId;
    private int isActive;
    private String firstName;
    private String lastName;
    private String profession;
    private String business;    // what's the difference between this and profession?
    private String avatar;
    private String description;
    //website;
    //facebook
    //twitter
    private int locationID;
    private float distance;
    private int reviewsCount;
    private int rating;

    // Address
    private String city;
    private String state;
    private String zip;

    private String latitude;    // could be floats?
    private String longitude;

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getProfession() {
        return profession;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getDescription() {
        return description;
    }

    public float getDistance() {
        return distance;
    }

    public int getReviewsCount() {
        return reviewsCount;
    }

    public int getRating() {
        return rating;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }
}
