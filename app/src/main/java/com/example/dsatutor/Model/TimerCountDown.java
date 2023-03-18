package com.example.dsatutor.Model;

import android.os.CountDownTimer;
import android.widget.TextView;

public class TimerCountDown {
    private TextView timerTextView;
    private CountDownTimer countDownTimer;
    private long timeLeftInMilliseconds; // 10 minutes = 600000 milliseconds
    private boolean timerRunning;

    public TimerCountDown(TextView timerTextView,long timeLeftInMilliseconds) {
        this.timerTextView = timerTextView;
        this.timeLeftInMilliseconds=timeLeftInMilliseconds;
    }

    public void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMilliseconds, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMilliseconds = l;
                updateTimer();
            }

            @Override
            public void onFinish() {
                timerRunning = false;
            }
        }.start();

        timerRunning = true;
    }

    public void stopTimer() {
        countDownTimer.cancel();
        timerRunning = false;
    }

    public void updateTimer() {
        int minutes = (int) timeLeftInMilliseconds / 60000;
        int seconds = (int) timeLeftInMilliseconds % 60000 / 1000;

        String timeLeftText;
        timeLeftText = "" + minutes;
        timeLeftText += ":";
        if (seconds < 10) timeLeftText += "0";
        timeLeftText += seconds;

        timerTextView.setText(timeLeftText);
    }

    public boolean isTimerRunning() {
        return timerRunning;
    }

    public void setTimeLeftInMilliseconds(long timeLeftInMilliseconds) {
        this.timeLeftInMilliseconds = timeLeftInMilliseconds;
    }
}

