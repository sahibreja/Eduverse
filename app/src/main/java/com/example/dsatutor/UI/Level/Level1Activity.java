package com.example.dsatutor.UI.Level;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Toast;

import com.example.dsatutor.Adapter.LevelAdapter;
import com.example.dsatutor.MainActivity;
import com.example.dsatutor.Model.Level;
import com.example.dsatutor.Model.Levels.LevelCreate;
import com.example.dsatutor.Model.PrefManager;
import com.example.dsatutor.Model.ScreenType;
import com.example.dsatutor.Model.TimerCountDown;
import com.example.dsatutor.R;
import com.example.dsatutor.databinding.ActivityLevel1Binding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Level1Activity extends AppCompatActivity {
    private int currentApiVersion;
    //int lives = 5;
    long waitStartTime;
    private ActivityLevel1Binding binding;
    private PrefManager prefManager;
    private int lives;
    private int currentLevel;
    CountDownTimer timer;
    private ArrayList<LevelCreate> levelCreates;
    private LevelAdapter levelAdapter;
    private RecyclerView recyclerView;
    private ArrayList<Level> levelArrayList=new ArrayList<>();
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth auth;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
        setScreenType();
        binding=ActivityLevel1Binding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);

        init();
        firebaseDatabase.getReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                currentLevel=Integer.parseInt(snapshot.child("Users").child(auth.getCurrentUser().getUid()).child("currentLevel").getValue().toString());
                lives= Integer.parseInt(snapshot.child("Users").child(auth.getCurrentUser().getUid()).child("remainingLive").getValue().toString());

                levelArrayList.clear();
                for(DataSnapshot dataSnapshot:snapshot.child("Levels").child(auth.getCurrentUser().getUid()).getChildren())
                {
                    Level levels=dataSnapshot.getValue(Level.class);
                    levelArrayList.add(levels);
                }
                callValue(lives,currentLevel,levelArrayList);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void callValue(int lives, int currentLevel, ArrayList<Level> levelArrayList) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(10);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recyclerView.setNestedScrollingEnabled(true);

        addLevelDetails();
        levelAdapter = new LevelAdapter(Level1Activity.this,this,levelCreates,lives,currentLevel,levelArrayList);

        recyclerView.setAdapter(levelAdapter);
        recyclerView.scrollToPosition(0);
        levelAdapter.notifyDataSetChanged();
    }

    private void init()
    {
        firebaseDatabase=FirebaseDatabase.getInstance();
        auth=FirebaseAuth.getInstance();
        recyclerView=findViewById(R.id.recycler_view);
//        prefManager= new PrefManager(Level1Activity.this,"Game");
//        lives =prefManager.getLives();
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    private void addLevelDetails()
    {

        levelCreates = new ArrayList<>();
        levelCreates.add(new LevelCreate(1,getDrawable(R.drawable.bg_dashboard2)));
        levelCreates.add(new LevelCreate(2,getDrawable(R.drawable.bg_dashboard)));
        levelCreates.add(new LevelCreate(3,getDrawable(R.drawable.bg_dashboard2)));
        levelCreates.add(new LevelCreate(4,getDrawable(R.drawable.bg_dashboard)));
        levelCreates.add(new LevelCreate(5,getDrawable(R.drawable.bg_dashboard2)));
    }
    private boolean setValue()
    {
        SharedPreferences prefs = getSharedPreferences("game_data", MODE_PRIVATE);
        if (lives > 0) {
            // player can play the game
            //lives--;
            return true;
        } else {

            // player has no lives remaining
            long currentTime = System.currentTimeMillis();
            waitStartTime = prefs.getLong("wait_start_time", 0);
            long timeDifference = currentTime - waitStartTime;
            if (timeDifference < 180000) {
                timer= new CountDownTimer(timeDifference,1000) {
                    @Override
                    public void onTick(long l) {
                       // binding.txt.setText(String.valueOf(l/1000));
                    }

                    @Override
                    public void onFinish() {

                    }
                };

                // 30 minutes have not yet passed
                //binding.txt.setText("Please wait " + (180000 - timeDifference) / 60000 + " minutes before playing again.");
                //Toast.makeText(this, "Please wait " + (1800000 - timeDifference) / 60000 + " minutes before playing again.", Toast.LENGTH_LONG).show();
            } else {
                // 30 minutes have passed
                // reset the number of lives
                lives = 5;
                prefs.edit().remove("wait_start_time").apply();
                return true;
            }
        }

        if (lives == 0) {
            waitStartTime = System.currentTimeMillis();
            prefs.edit().putLong("wait_start_time", waitStartTime).apply();
            // clear the game from memory
            //System.exit(0);
        }
      return false;
    }
    private void setScreenType()
    {
        currentApiVersion = android.os.Build.VERSION.SDK_INT;

        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        // This work only for android 4.4+
        if(currentApiVersion >= Build.VERSION_CODES.KITKAT)
        {

            getWindow().getDecorView().setSystemUiVisibility(flags);

            // Code below is to handle presses of Volume up or Volume down.
            // Without this, after pressing volume buttons, the navigation bar will
            // show up and won't hide
            final View decorView = getWindow().getDecorView();
            decorView
                    .setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener()
                    {

                        @Override
                        public void onSystemUiVisibilityChange(int visibility)
                        {
                            if((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0)
                            {
                                decorView.setSystemUiVisibility(flags);
                            }
                        }
                    });
        }

    }

    @SuppressLint("NewApi")
    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);
        if(currentApiVersion >= Build.VERSION_CODES.KITKAT && hasFocus)
        {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
}