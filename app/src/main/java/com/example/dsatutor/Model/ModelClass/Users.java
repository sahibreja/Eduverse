package com.example.dsatutor.Model.ModelClass;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Users {
    @PrimaryKey(autoGenerate = true)
    private int uid;
    @ColumnInfo
    private String userId;
    @ColumnInfo
    private String userName;
    @ColumnInfo
    private String userEmail;
    @ColumnInfo
    private String userPassword;
    @ColumnInfo
    private int currentLevel;
    @ColumnInfo
    private int remainingLive;
    @ColumnInfo
    private long remainingTime;
    @ColumnInfo
    private int totalScore;
    @ColumnInfo
    private float progress;

    @ColumnInfo
    private int power;


    public Users(String userId, String userName, String userEmail, String userPassword, int currentLevel, int remainingLive, long remainingTime, int totalScore, float progress, int power) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.currentLevel = currentLevel;
        this.remainingLive = remainingLive;
        this.remainingTime = remainingTime;
        this.totalScore = totalScore;
        this.progress = progress;
        this.power = power;
    }

    @Ignore
    public Users() {
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public int getRemainingLive() {
        return remainingLive;
    }

    public void setRemainingLive(int remainingLive) {
        this.remainingLive = remainingLive;
    }

    public long getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(long remainingTime) {
        this.remainingTime = remainingTime;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }
}
