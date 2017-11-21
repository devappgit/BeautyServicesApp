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
    private String business;    // represents business title
    private String avatar;
    private String description;
    private int locationID;
    private float distance;
    private transient int reviewsCount; // currently server response is Nullable
    private int rating;

    private String city;
    private String state;
    private String zip;

    private String latitude;
    private String longitude;

    private String location;

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

    public String getBusiness() {
        return business;
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

    public String getLocation() {
        return location;
    }
}
