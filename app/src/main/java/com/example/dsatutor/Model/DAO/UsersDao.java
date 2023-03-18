package com.example.dsatutor.Model.DAO;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.dsatutor.Model.Users;

@Dao
public interface UsersDao {


    @Query("Select currentLevel from Users where userId =:userId")
    int getCurrentLevel(String userId);

    @Query("Update Users set remainingLive=:currentLevel where userId=:userId")
    void setCurrentLevel(String userId,int currentLevel);


    @Query("Select remainingLive from Users where userId=:userId")
    int getLives(String userId);

    @Query("Update Users set remainingLive=:lives where userId=:userId")
    void setLives(String userId,int lives);

    @Query("Select remainingTime from Users where userId=:userId")
    long getLastRefillTime(String userId);

    @Query("Update Users set remainingTime=:lastRefillTime where userId=:userId")
    void setLastRefillTimes(String userId,long lastRefillTime);

    @Query("Select totalScore from Users where userId=:userId")
    int getTotalScore(String userId);

    @Query("Update Users set remainingTime=:totalScore where userId=:userId")
    void setTotalScore(String userId,long totalScore);

    @Query("Select progress from Users where userId=:userId")
    float getProgress(String userId);

    @Query("Update Users set remainingTime=:progress where userId=:userId")
    void setProgress(String userId,float progress);



    @Insert
    void insertUserData(Users users);


}
