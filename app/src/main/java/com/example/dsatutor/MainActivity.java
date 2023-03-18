package com.example.dsatutor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.dsatutor.Adapter.LeaderBoardAdapter;
import com.example.dsatutor.Adapter.LevelAdapter;
import com.example.dsatutor.Database.GameDatabase;
import com.example.dsatutor.Model.Algorithm.Check;
import com.example.dsatutor.Model.Compare;
import com.example.dsatutor.Model.DAO.UsersDao;
import com.example.dsatutor.Model.Game;
import com.example.dsatutor.Model.Level;
import com.example.dsatutor.Model.Levels.LevelCreate;
import com.example.dsatutor.Model.PrefManager;
import com.example.dsatutor.Model.Users;
import com.example.dsatutor.UI.Dashboard.LearningActivity;
import com.example.dsatutor.UI.Game.QuizActivity;
import com.example.dsatutor.UI.Level.Level1Activity;
import com.example.dsatutor.UI.start.authentication.LoginActivity;
import com.example.dsatutor.UI.start.authentication.SignUpActivity;
import com.example.dsatutor.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private android.widget.Button musicBtn,soundBtn,aboutBtn,helpBtn,logOutBtn;
    private ScrollView scrollView;
    private RelativeLayout height,touchOutside1,touchOutSide2;
    private int currentApiVersion;
    private ImageView back_btn,i_btn;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference ref;
    private FirebaseAuth auth;
    private TextView userNameView;
    String userName="";
    VideoView view1 ;
    private Integer fbRes=0;
    private ActivityMainBinding binding;
    private PrefManager prefManager;
    private Game game;
    private MediaPlayer mp;
    private GameDatabase gameDatabase;
    private UsersDao usersDao;
    private int lives;
    private int currentLevel;
    private long lastRefillTime;
    private int totalScore;
    private float progress;

    private ArrayList<Level> levelArrayList=new ArrayList<>();
    private ArrayList<Users> usersArrayList=new ArrayList<>();

    private LeaderBoardAdapter leaderBoardAdapter;

    private ArrayList<LevelCreate> levelCreates;
    private LevelAdapter levelAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
        setScreenType();
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        //setContentView(R.layout.activity_main);

        //initialization
        init();

        //click on button
        ButtonClick();

        //Background task
        BackgroundLoading bg = new BackgroundLoading();
        bg.execute();


    }

    @SuppressLint({"UseCompatLoadingForDrawables", "NotifyDataSetChanged"})
    private void setValueToView() {


        //call recycler view
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(10);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recyclerView.setNestedScrollingEnabled(true);

        addLevelDetails();
        levelAdapter = new LevelAdapter(MainActivity.this,this,levelCreates,lives,currentLevel,levelArrayList);

        recyclerView.setAdapter(levelAdapter);
        recyclerView.scrollToPosition(0);
        levelAdapter.notifyDataSetChanged();
        binding.loadingLayout.setVisibility(View.GONE);
        //score value
        if(currentLevel>1){
            binding.totalScore.setText(String.valueOf(getTotalScore()));
        }

        //progress value
        if(currentLevel>1)
        {
            binding.progressBar1.setProgress((int) (getTotalProgress()%100));
            binding.progressBar1.setMax(100);
            int prG=(int) (getTotalProgress()/100)*25;
            if(prG%100==0 && prG!=0)
            {
                int l=lives+(prG/100);
                if(l<5)
                {
                    firebaseDatabase.getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("remainingLive").setValue(l+1);
                }
            }
            binding.progressBar.setProgress(prG%100);
            binding.progressBar.setMax(100);

        }
        if(totalScore<getTotalScore())
        {
            firebaseDatabase.getReference().child("Users").child(auth.getCurrentUser().getUid()).child("totalScore").setValue(getTotalScore());
        }


    }
    @SuppressLint("UseCompatLoadingForDrawables")
    private void addLevelDetails() {

        levelCreates = new ArrayList<>();
        levelCreates.add(new LevelCreate(1,getDrawable(R.drawable.bg_dashboard2)));
        levelCreates.add(new LevelCreate(2,getDrawable(R.drawable.bg_dashboard)));
        levelCreates.add(new LevelCreate(3,getDrawable(R.drawable.bg_dashboard3)));
        levelCreates.add(new LevelCreate(4,getDrawable(R.drawable.bg_dashboard4)));
        levelCreates.add(new LevelCreate(5,getDrawable(R.drawable.bg_dashboard3)));
        levelCreates.add(new LevelCreate(6,getDrawable(R.drawable.bg_dashboard4)));
        levelCreates.add(new LevelCreate(7,getDrawable(R.drawable.bg_dashboard3)));
    }
    private int getTotalScore() {
        int totalScores=0;
        for (int i=0;i<levelArrayList.size();i++)
        {
            totalScores+=levelArrayList.get(i).getLevelScore();
        }
        return totalScores;
    }
    private float getTotalProgress() {
        float totalScores=0;
        for (int i=0;i<levelArrayList.size();i++)
        {
            totalScores+=levelArrayList.get(i).getLevelProgress();
        }

        return totalScores;
    }

    private void LivesShow(int lives,long lastRefillTime) {
        if(lives>0)
        {
            binding.lives.setText(String.valueOf(lives));
        }else
        {
            game=new Game(MainActivity.this,binding.lives,MainActivity.this);
            game.startTimerAndRefillByFirebase();
        }
    }

    private void getValueFromFirebase() {

        firebaseDatabase.getReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                usersArrayList.clear();
                for(DataSnapshot dataSnapshot :snapshot.child("Users").getChildren())
                {
                    Users users=dataSnapshot.getValue(Users.class);
                    usersArrayList.add(users);
                }

                lives= Integer.parseInt(snapshot.child("Users").child(auth.getCurrentUser().getUid()).child("remainingLive").getValue().toString());
                totalScore= Integer.parseInt(snapshot.child("Users").child(auth.getCurrentUser().getUid()).child("totalScore").getValue().toString());
                lastRefillTime=Long.parseLong(snapshot.child("Users").child(auth.getCurrentUser().getUid()).child("remainingTime").getValue().toString());
                progress=Float.parseFloat(snapshot.child("Users").child(auth.getCurrentUser().getUid()).child("progress").getValue().toString());
                currentLevel=Integer.parseInt(snapshot.child("Users").child(auth.getCurrentUser().getUid()).child("currentLevel").getValue().toString());
                userName=snapshot.child("Users").child(auth.getCurrentUser().getUid()).child("userName").getValue().toString();
                String[] name=userName.split(" ");
                userNameView.setText(name[0]);


                levelArrayList.clear();
                for(DataSnapshot dataSnapshot:snapshot.child("Levels").child(auth.getCurrentUser().getUid()).getChildren())
                {
                    Level levels=dataSnapshot.getValue(Level.class);
                    levelArrayList.add(levels);
                }
                LivesShow(lives,lastRefillTime);
                setValueToView();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private class BackgroundLoading extends AsyncTask<Void,Void,Integer>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            binding.loadingLayout.setVisibility(View.VISIBLE);
        }
        @Override
        protected Integer doInBackground(Void... voids) {
            getValueFromFirebase();
            return 0;
        }


    }
    private void ButtonClick() {

        binding.heartIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heartTouchEffect(view);
            }
        });
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heartTouchEffect(view);
                AlertDialog alertbox = new AlertDialog.Builder(MainActivity.this)
                        .setMessage("Do you want to leave this universe?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            // do something when the button is clicked
                            public void onClick(DialogInterface arg0, int arg1) {

                                finishAffinity();
                                //close();


                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {

                            // do something when the button is clicked
                            public void onClick(DialogInterface arg0, int arg1) {

                            }
                        })
                        .show();
            }
        });

        i_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heartTouchEffect(view);
                startActivity(new Intent(MainActivity.this, LearningActivity.class));
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                //startActivity(new Intent(MainActivity.this,Level1Activity.class));
            }
        });

        binding.leaderBoardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heartTouchEffect(view);
                if(binding.leaderboardLayout.getVisibility()==View.GONE)
                {
                    binding.leaderboardLayout.setVisibility(View.VISIBLE);
                    Animation slideInRight= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
                    binding.leaderboardLayout.startAnimation(slideInRight);
                }else
                {

                    Animation slideOutLeft= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_left);
                    binding.leaderboardLayout.startAnimation(slideOutLeft);
                    binding.leaderboardLayout.setVisibility(View.GONE);
                }
            }
        });

        binding.settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heartTouchEffect(view);
                if(binding.settingLayout.getVisibility()==View.GONE)
                {
                   // YoYo.with(Techniques.SlideInDown).duration(900).playOn(binding.settingLayout);
                    binding.settingLayout.setVisibility(View.VISIBLE);

                    Animation slideInDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_down);
                    binding.settingLayout.startAnimation(slideInDown);
                }else
                {
                    Animation slideOutUp  = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_up);
                    binding.settingLayout.startAnimation(slideOutUp );
                    binding.settingLayout.setVisibility(View.GONE);
                }

                //Toast.makeText(MainActivity.this, "We are working on this", Toast.LENGTH_SHORT).show();
            }
        });

        settingLayoutButtonClick();
        leaderBoardFunction();
    }

    private void leaderBoardFunction() {


        View view=findViewById(R.id.leaderboard);
        RecyclerView recyclerView =view.findViewById(R.id.recycler);
        RelativeLayout touchLayout=view.findViewById(R.id.layout2);
        touchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.leaderboardLayout.getVisibility()==View.VISIBLE)
                {
                    Animation slideOutLeft= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_left);
                    binding.leaderboardLayout.startAnimation(slideOutLeft);
                    binding.leaderboardLayout.setVisibility(View.GONE);
                }
            }
        });


        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(10);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recyclerView.setNestedScrollingEnabled(true);

        leaderBoardAdapter = new LeaderBoardAdapter(this,usersArrayList,auth);

        recyclerView.setAdapter(leaderBoardAdapter);
        recyclerView.scrollToPosition(0);
        leaderBoardAdapter.notifyDataSetChanged();
    }

    private void settingLayoutButtonClick() {
        touchOutside1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.settingLayout.getVisibility()==View.VISIBLE)
                {
                    Animation slideOutUp  = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_up);
                    binding.settingLayout.startAnimation(slideOutUp );
                    binding.settingLayout.setVisibility(View.GONE);
                }
            }
        });
        touchOutSide2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.settingLayout.getVisibility()==View.VISIBLE)
                {
                    Animation slideOutUp  = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_up);
                    binding.settingLayout.startAnimation(slideOutUp );
                    binding.settingLayout.setVisibility(View.GONE);
                }
            }
        });

        musicBtn.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   heartTouchEffect(view);
                   if(prefManager.isMusicActive())
                   {
                      musicBtn.setBackground(getDrawable(R.drawable.music_no));
                      prefManager.setMusicActive(false);
                   }else
                   {
                       musicBtn.setBackground(getDrawable(R.drawable.music_yes));
                       prefManager.setMusicActive(true);
                   }
               }
           });

        soundBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View view) {
                heartTouchEffect(view);

                if(prefManager.isSoundActive())
                {
                    soundBtn.setBackground(getDrawable(R.drawable.sound_no));
                    prefManager.setSoundActive(false);
                }else
                {
                    soundBtn.setBackground(getDrawable(R.drawable.sound_yes));
                    prefManager.setSoundActive(true);
                }
            }
        });

        aboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heartTouchEffect(view);
            }
        });

        helpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heartTouchEffect(view);

            }
        });

        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heartTouchEffect(view);

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(false);
                builder.setMessage("Are you sure you want to logout?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //if user pressed "yes", then he is allowed to exit from application
                        auth.signOut();
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                        finishAffinity();
                    }
                });
                builder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //if user select "No", just cancel this dialog and continue with app
                        dialog.cancel();
                    }
                });
                AlertDialog alert=builder.create();
                alert.show();


            }
        });

    }

    private void heartTouchEffect(View view) {
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);
        view.startAnimation(anim);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void init() {
        recyclerView=findViewById(R.id.recycler);
        //scrollView=findViewById(R.id.scrollView);
        //height=findViewById(R.id.height_1);
        back_btn=findViewById(R.id.back_btn);
        i_btn=findViewById(R.id.i_btn);
        userNameView=findViewById(R.id.userName);
        firebaseDatabase=FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        prefManager= new PrefManager(MainActivity.this,"Game");
       //game=new Game(MainActivity.this,gameDatabase,binding.lives,auth.getCurrentUser().getUid());
        //game =new Game(MainActivity.this,prefManager,binding.lives);
        View view=findViewById(R.id.sett);
        musicBtn=view.findViewById(R.id.music_btn);
        soundBtn=view.findViewById(R.id.sound_btn);
        aboutBtn=view.findViewById(R.id.about_btn);
        helpBtn=view.findViewById(R.id.help_btn);
        logOutBtn=view.findViewById(R.id.logout_btn);
        touchOutside1=view.findViewById(R.id.touch_outside);
        touchOutSide2=view.findViewById(R.id.touch_outside1);
        if(prefManager.isMusicActive())
        {
            musicBtn.setBackground(getDrawable(R.drawable.music_yes));
        }else {
            musicBtn.setBackground(getDrawable(R.drawable.music_no));
        }

        if(prefManager.isSoundActive())
        {
            soundBtn.setBackground(getDrawable(R.drawable.sound_yes));
        }else {
            soundBtn.setBackground(getDrawable(R.drawable.sound_no));
        }

    }
    private void setScreenType() {
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
    public void onWindowFocusChanged(boolean hasFocus) {
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


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to leave this universe?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                finishAffinity();
            }
        });
        builder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert=builder.create();
        alert.show();
    }



}