package com.example.dsatutor.Model;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static String PREF_NAME = "";
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    private static final String IS_SOUND_ACTIVE = "IsSoundActive";
    private static final String IS_MUSIC_ACTIVE = "IsMusicActive";

    public PrefManager(Context context,String PREF_NAME) {
        this._context = context;
        PrefManager.PREF_NAME =PREF_NAME;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
    public int getLives()
    {
        return pref.getInt("lives",5);
    }
    public void setLives(int lives) {
        editor.putInt("lives",lives);
        editor.commit();
    }
    public long getWaitTime()
    {
        return pref.getLong("wait_time",System.currentTimeMillis());
    }
    public void setWaitTime(long waitTime) {
        editor.putLong("wait_time",waitTime);
        editor.commit();
    }
    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    public boolean isSoundActive()
    {
        return pref.getBoolean(IS_SOUND_ACTIVE,true);
    }
    public void setSoundActive(boolean soundActive)
    {
        editor.putBoolean(IS_SOUND_ACTIVE,soundActive);
        editor.commit();
    }

    public boolean isMusicActive()
    {
        return pref.getBoolean(IS_MUSIC_ACTIVE,true);
    }
    public void setMusicActive(boolean musicActive)
    {
        editor.putBoolean(IS_MUSIC_ACTIVE,musicActive);
        editor.commit();
    }

}
