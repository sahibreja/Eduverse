package com.example.dsatutor.Model.DAO;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.dsatutor.Model.ModelClass.Users;

@Dao
public interface UsersDao {

    //get user in which level currently are
    @Query("Select currentLevel from Users where userId =:userId")
    int getCurrentLevel(String userId);

    //set current level of user into database
    @Query("Update Users set remainingLive=:currentLevel where userId=:userId")
    void setCurrentLevel(String userId,int currentLevel);

    // get remaining live from database
    @Query("Select remainingLive from Users where userId=:userId")
    int getLives(String userId);

    //set remaining live into database
    @Query("Update Users set remainingLive=:lives where userId=:userId")
    void setLives(String userId,int lives);


    //get last refill time of live from database
    @Query("Select remainingTime from Users where userId=:userId")
    long getLastRefillTime(String userId);

    //set last refill time of live into database
    @Query("Update Users set remainingTime=:lastRefillTime where userId=:userId")
    void setLastRefillTimes(String userId,long lastRefillTime);

    //get total score of user from database
    @Query("Select totalScore from Users where userId=:userId")
    int getTotalScore(String userId);

    //set total score of user into database
    @Query("Update Users set remainingTime=:totalScore where userId=:userId")
    void setTotalScore(String userId,long totalScore);

    //get progress of user from database
    @Query("Select progress from Users where userId=:userId")
    float getProgress(String userId);

    //set progress of user into database
    @Query("Update Users set remainingTime=:progress where userId=:userId")
    void setProgress(String userId,float progress);


    //insert all details of user into database
    @Insert
    void insertUserData(Users users);


}
