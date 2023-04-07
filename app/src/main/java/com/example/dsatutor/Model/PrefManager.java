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

    //initialize by constructor
    public PrefManager(Context context,String PREF_NAME) {
        this._context = context;
        PrefManager.PREF_NAME =PREF_NAME;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    //set first time launch as boolean like true or false into shared preferences
    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    //get first time launch as boolean like true or false from shared preferences
    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    //get sound is active or not as boolean like true or false from shared preferences
    public boolean isSoundActive()
    {
        return pref.getBoolean(IS_SOUND_ACTIVE,true);
    }

    //set sound is active or not as boolean like true or false into shared preferences
    public void setSoundActive(boolean soundActive) {
        editor.putBoolean(IS_SOUND_ACTIVE,soundActive);
        editor.commit();
    }

    //get music is active or not as boolean like true or false from shared preferences
    public boolean isMusicActive()
    {
        return pref.getBoolean(IS_MUSIC_ACTIVE,true);
    }

    //set music is active or not as boolean like true or false into shared preferences
    public void setMusicActive(boolean musicActive) {
        editor.putBoolean(IS_MUSIC_ACTIVE,musicActive);
        editor.commit();
    }

}
