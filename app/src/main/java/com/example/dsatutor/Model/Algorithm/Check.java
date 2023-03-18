package com.example.dsatutor.Model.Algorithm;

import android.view.View;

import androidx.annotation.NonNull;

import com.example.dsatutor.Database.GameDatabase;
import com.example.dsatutor.Model.DAO.UsersDao;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Check {

    private FirebaseAuth auth;
    private FirebaseDatabase firebaseDatabase;
    private GameDatabase gameDatabase;
    private UsersDao usersDao;
    private String userId;
    private int fLives,oLives;
    private int fCurrentLevel,oCurrentLevel;
    private long fLastRefillTime,oLastRefillTime;
    private int fTotalScore,oTotalScore;
    private float fProgress,oProgress;

    public Check(FirebaseAuth auth, FirebaseDatabase firebaseDatabase, GameDatabase gameDatabase) {
        this.auth = auth;
        this.firebaseDatabase = firebaseDatabase;
        this.gameDatabase = gameDatabase;

    }

    public void getDataFromFirebase()
    {
        firebaseDatabase.getReference().child("Users").child(auth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                fLives= Integer.parseInt(snapshot.child("remainingLive").getValue().toString());
                fTotalScore= Integer.parseInt(snapshot.child("totalScore").getValue().toString());
                fLastRefillTime=Long.parseLong(snapshot.child("remainingTime").getValue().toString());
                fProgress=Float.parseFloat(snapshot.child("remainingTime").getValue().toString());
                fCurrentLevel=Integer.parseInt(snapshot.child("currentLevel").getValue().toString());
                getDataFromDatabase();
                CheckAndSetData();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void getDataFromDatabase()
    {
        userId= auth.getCurrentUser().getUid();
        usersDao= gameDatabase.userDao();
        oLives= usersDao.getLives(auth.getCurrentUser().getUid());
        oCurrentLevel= usersDao.getCurrentLevel(userId);
        oLastRefillTime= usersDao.getLastRefillTime(auth.getCurrentUser().getUid());
        oTotalScore= usersDao.getTotalScore(userId);
        oProgress= usersDao.getProgress(userId);
    }

    private void CheckAndSetData()
    {

        if(fLives<oLives)
        {
           firebaseDatabase.getReference().child("Users").child(userId).child("remainingLive").setValue(fLives);
        }
        else if(fLives>oLives)
        {
            usersDao.setLives(userId,oLives);
        }

        if(fCurrentLevel<oCurrentLevel)
        {
            firebaseDatabase.getReference().child("Users").child(userId).child("currentLevel").setValue(oCurrentLevel);
        }
        else if(fCurrentLevel>oCurrentLevel)
        {
           usersDao.setCurrentLevel(userId,fCurrentLevel);
        }

        if(fLastRefillTime<oLastRefillTime)
        {
            firebaseDatabase.getReference().child("Users").child(userId).child("remainingTime").setValue(oLastRefillTime);
        }
        else if(fLastRefillTime>oLastRefillTime)
        {
            usersDao.setLastRefillTimes(userId,fLastRefillTime);
        }

        if(fTotalScore<oTotalScore)
        {
            firebaseDatabase.getReference().child("Users").child(userId).child("totalScore").setValue(oTotalScore);
        }
        else if(fTotalScore>oTotalScore)
        {
            usersDao.setTotalScore(userId,fTotalScore);
        }

        if(fProgress<oProgress)
        {
            firebaseDatabase.getReference().child("Users").child(userId).child("progress").setValue(oProgress);
        }
        else if(fProgress>oProgress)
        {
           usersDao.setProgress(userId,fProgress);
        }
    }

}
