package com.servpal.android.model;

public class UserBody {

    private User user;

    //profile
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

    // UserBody is structured this way because JSON is in the format of
//    Body: {
//            "user": {
//                "user": {}
//            }
//        }
}
