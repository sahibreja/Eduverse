package com.example.dsatutor.Model;

import java.util.Comparator;

public class Compare implements Comparator<Users> {
    @Override
    public int compare(Users users, Users t1) {
        return Integer.compare(t1.getTotalScore(), users.getTotalScore());
    }
}
