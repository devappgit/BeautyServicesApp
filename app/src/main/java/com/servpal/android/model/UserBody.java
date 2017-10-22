package com.servpal.android.model;

public class UserBody {

    private User user;
    private Profile profile;

    //secondaryProfessions
    //business
    //availabilities
    //services
    //pictures
    //boolean account
    //reviews

    public User getUser() {
        return this.user;
    }

    public Profile getProfile() {
        return this.profile;
    }

    // UserBody is structured this way because JSON is in the format of
//    Body: {
//            "user": {
//
//            }
//        }
}
