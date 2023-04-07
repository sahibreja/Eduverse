package com.example.dsatutor.Model;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.dsatutor.Database.GameDatabase;
import com.example.dsatutor.MainActivity;
import com.example.dsatutor.Model.DAO.UsersDao;
import com.example.dsatutor.databinding.TestLayoutBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Game {

    private int lives;
    private long lastLifeRefillTime;
    private Context context;
    private Activity activity;
    private TextView timer_text_view;
    private FirebaseDatabase database;
    private FirebaseAuth auth;
    private boolean isUpdate=false;

    //Initialize by constructor
    public Game(Context context,TextView timer_text_view,Activity activity) {
        this.context = context;
        this.timer_text_view = timer_text_view;
        this.activity=activity;
    }

    // when lives is equal zero then it will start timer and showing timer on screen and also completion of timer it will refill the live
    public void startTimerAndRefillByFirebase() {
        database=FirebaseDatabase.getInstance();
        auth=FirebaseAuth.getInstance();
        database.getReference().child("Users").child(auth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                lastLifeRefillTime=Long.parseLong(snapshot.child("remainingTime").getValue().toString());
                lives=Integer.parseInt(snapshot.child("remainingLive").getValue().toString());
                if(lives<=0)
                {
                    long currentTime = System.currentTimeMillis();
                    long timeDifference = currentTime - lastLifeRefillTime;
                    // TimerCountDown t = new TimerCountDown(timer_text_view,(180000 - timeDifference) / 60000);
                    if(timeDifference < 900000)
                    {
                        new CountDownTimer(900000 - timeDifference, 1000) {
                            @SuppressLint("DefaultLocale")
                            public void onTick(long millisUntilFinished) {
                                String fmt=String.format("%d:%d",(millisUntilFinished/1000)/60,(millisUntilFinished/1000)%60);
                                timer_text_view.setText(fmt);
                            }
                            public void onFinish() {

                                database.getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("remainingLive").setValue(5);
                                timer_text_view.setText(String.valueOf(5));
                            }
                        }.start();
                    }else
                    {
                        database.getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("remainingLive").setValue(5);
                        //timer_text_view.setText(String.valueOf(5));

                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}
