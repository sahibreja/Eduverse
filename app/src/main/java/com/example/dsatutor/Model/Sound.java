package com.example.dsatutor.Model;

import android.content.Context;
import android.media.MediaPlayer;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dsatutor.R;

public class Sound{
    private Context context;
    private PrefManager prefManager;
    MediaPlayer clickPlayer,dashBoardPlayer,prerequisitePlayer,quiz_bg_music_player,level_click;

    public Sound(Context context) {
        this.context = context;
        this.prefManager = new PrefManager(context,"Game");
    }
    
    //play sound when user click on button
    public void playClickSound() {
        clickPlayer = MediaPlayer.create(context, R.raw.click_sound);
        if(prefManager.isSoundActive())
        {
            clickPlayer.seekTo(200);
            clickPlayer.start();
            releaseMediaPlayer(clickPlayer);
        }

    }

    //play sound when user click on heart icon
    public void playHeartSound() {
        MediaPlayer heartSoundPlayer = MediaPlayer.create(context, R.raw.heart_sound);
        if(prefManager.isSoundActive())
        {
            heartSoundPlayer.seekTo(150);
            heartSoundPlayer.start();
            releaseMediaPlayer(heartSoundPlayer);
        }

    }

    //play music on dashboard
    public void playDashBoardSound() {
        dashBoardPlayer = MediaPlayer.create(context, R.raw.main_dashboard_bg_music);
        if(prefManager.isMusicActive())
        {
            dashBoardPlayer.seekTo(30000);
            dashBoardPlayer.setLooping(true);
            dashBoardPlayer.setVolume(4,4);
            dashBoardPlayer.start();
        }

    }

    //play correct answer sound of pre requisite question
    public void playCorrectAnswerSound() {
        MediaPlayer mediaPlayer= MediaPlayer.create(context,R.raw.correct_ans);
        mediaPlayer.seekTo(500);
        mediaPlayer.start();
        releaseMediaPlayer(mediaPlayer);
    }

    //play wrong answer sound of pre requisite question
    public void playWrongAnswerSound() {
        MediaPlayer mediaPlayer= MediaPlayer.create(context,R.raw.wrong_answer);
        mediaPlayer.start();
        releaseMediaPlayer(mediaPlayer);
    }

    //play background music of pre requisite question
    public void playPrerequisiteQuizMusic() {
        prerequisitePlayer= MediaPlayer.create(context,R.raw.prerequisite_bg_music);
        prerequisitePlayer.start();
    }

    //play background music of Quiz Activity
    public void playQuizBackgroundMusic(){
        quiz_bg_music_player=MediaPlayer.create(context,R.raw.quiz_bg_music);
        if(prefManager.isMusicActive()){
            quiz_bg_music_player.start();
        }
    }

    //play sound of click button
    public void playClickOnButtonSound(){
        MediaPlayer clickSound = MediaPlayer.create(context,R.raw.mouse_click);
        if(prefManager.isSoundActive())
        {
            clickSound.seekTo(200);
            clickSound.start();
            releaseMediaPlayer(clickSound);
        }
    }

    //play sound when user passed in pre requisite
    public void playExcellentSound(){
        MediaPlayer sound = MediaPlayer.create(context,R.raw.good_result);
        if(prefManager.isSoundActive())
        {
            sound.seekTo(100);
            sound.start();
            releaseMediaPlayer(sound);
        }
    }

    //play sound when user failed in pre requiste
    public void playBadResultSound(){
        MediaPlayer sound = MediaPlayer.create(context,R.raw.bad_result);
        if(prefManager.isSoundActive())
        {
            sound.start();
            releaseMediaPlayer(sound);
        }
    }

    //play sound of popup
    public void playPopupSound(){
        MediaPlayer sound = MediaPlayer.create(context,R.raw.popup_sound);
        if(prefManager.isSoundActive())
        {
            sound.seekTo(100);
            sound.start();
            releaseMediaPlayer(sound);

        }
    }

    //play sound when user click on level
    public void playClickOnLevelSound(){
         level_click = MediaPlayer.create(context,R.raw.level_click);
        if(prefManager.isSoundActive())
        {
            level_click.start();
            releaseMediaPlayer(level_click);
        }
    }

    //play sound of level passed when user complete the level
    public void playLevelPassed(){
        MediaPlayer sound = MediaPlayer.create(context,R.raw.level_pass);
        if(prefManager.isSoundActive())
        {
            sound.seekTo(100);
            sound.start();
            releaseMediaPlayer(sound);

        }
    }

    //play sound of level failed when user failed to complete the level
    public void playLevelFailed(){
        MediaPlayer sound = MediaPlayer.create(context,R.raw.level_failed);
        if(prefManager.isSoundActive())
        {
            sound.seekTo(100);
            sound.start();
            releaseMediaPlayer(sound);

        }
    }

    //play sound of correct answer of game activity
    public void playCorrectQuizAnswerSound(){
        MediaPlayer sound = MediaPlayer.create(context,R.raw.correct_quiz);
        if(prefManager.isSoundActive())
        {
            sound.seekTo(100);
            sound.start();
            releaseMediaPlayer(sound);

        }
    }

    //play sound of wrong answer of game activity
    public void playWrongQuizAnswerSound(){
        MediaPlayer sound = MediaPlayer.create(context,R.raw.wrong_quiz);
        if(prefManager.isSoundActive())
        {
            sound.seekTo(100);
            sound.start();
            releaseMediaPlayer(sound);

        }
    }

    //play sound of power when user will get power
    public void playGetPowerSound(){
        MediaPlayer sound = MediaPlayer.create(context,R.raw.power_get);
        if(prefManager.isSoundActive())
        {
            sound.seekTo(300);
            sound.start();
            releaseMediaPlayer(sound);

        }
    }

    // Release the media player when sound will complete
    private void releaseMediaPlayer(MediaPlayer mediaPlayer) {
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.release();
            }
        });
    }

    //stop the background music of dashboard
    public void stopDashBoardSound() {
       if(dashBoardPlayer!=null)
       {
           if (dashBoardPlayer.isPlaying() )
           {
               dashBoardPlayer.pause();
               dashBoardPlayer=null;
           }
       }

   }

   public void quizOnResume(){
       if(quiz_bg_music_player!=null){
           if(quiz_bg_music_player.isPlaying()){
               quiz_bg_music_player.pause();
           }
       }
   }
   public void quizOnPause() {
       if(quiz_bg_music_player!=null){
           if(!quiz_bg_music_player.isPlaying()){
               quiz_bg_music_player.start();
           }
       }
   }
   public void quizOnDestroy(){
       if (quiz_bg_music_player != null) {
           quiz_bg_music_player.release();
           quiz_bg_music_player = null;
       }
   }
    public void onPause() {

        if(level_click!=null)
        {
            if (level_click.isPlaying()) {
                level_click.pause();
            }
        }
        if(dashBoardPlayer!=null)
        {
            if (dashBoardPlayer.isPlaying()) {
                dashBoardPlayer.pause();
            }
        }

    }

    public void onResume() {

        if(dashBoardPlayer!=null)
        {
            if (!dashBoardPlayer.isPlaying()) {
                dashBoardPlayer.start();
            }
        }

    }

    public void onDestroy() {

        if (dashBoardPlayer != null) {
            dashBoardPlayer.release();
            dashBoardPlayer = null;
        }
    }
}
