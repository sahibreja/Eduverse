package com.example.dsatutor.Model;

import com.example.dsatutor.Model.ModelClass.Users;

import java.util.Comparator;

public class Compare implements Comparator<Users> {

    //compare the list based on total score of user to get ascending order of list of user to show in leaderboard
    @Override
    public int compare(Users users, Users t1) {
        return Integer.compare(t1.getTotalScore(), users.getTotalScore());
    }
}
