package com.servpal.android.model;


import org.parceler.Parcel;

/**
 * Seems to be a Service Provider that has address, business hours, and location Id's
 * <p>
 * Usage of boxed primitives (e.g. Integer, Double, Float) indicate that the value can be `null` from server
 */
@SuppressWarnings("WeakerAccess")
@Parcel
public class Professional {

    int id;
    int professionId;
    int isActive;
    String firstName;
    String lastName;
    String profession;
    String business;    // represents business title
    String avatar;
    String description;
    Integer locationID;
    Float distance;
    Integer reviewsCount; // currently server response is Nullable
    Integer rating;

    String phone;
    String mobile;
    String city;
    String state;
    String zip;

    Double latitude;
    Double longitude;

    String location;

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

    public String getPhone() {
        return phone;
    }

    public String getMobile() {
        return mobile;
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
