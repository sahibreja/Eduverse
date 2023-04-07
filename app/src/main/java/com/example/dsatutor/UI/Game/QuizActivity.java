package com.example.dsatutor.UI.Game;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dsatutor.Database.GameDatabase;
import com.example.dsatutor.MainActivity;
import com.example.dsatutor.Model.DAO.QuizQuestionDao;
import com.example.dsatutor.Model.DAO.UsersDao;
import com.example.dsatutor.Model.ModelClass.Level;
import com.example.dsatutor.Model.PrefManager;
import com.example.dsatutor.Model.Questions.PreInsertedQuizQuestion;
import com.example.dsatutor.Model.ModelClass.QuizQuestion;
import com.example.dsatutor.Model.Sound;
import com.example.dsatutor.R;
import com.example.dsatutor.databinding.ActivityQuizBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import me.samlss.bloom.Bloom;
import me.samlss.bloom.effector.BloomEffector;

public class QuizActivity extends AppCompatActivity {
    private ActivityQuizBinding binding;
    private int currentApiVersion;
    private int lives;
    private FirebaseAuth auth;
    private GameDatabase gameDatabase;
    private FirebaseDatabase database;
    private UsersDao usersDao;
    private QuizQuestionDao quizQuestionDao;
    private List<QuizQuestion> quizQuestionArrayList;
    private QuizQuestion question;
    private int getLevel;
    private int currentLevel;
    private int index = 0;
    private int correctAnswers = 0;
    private boolean isClicked = false, isFinish = false;
    private CountDownTimer timer;
    private int countForPower=0;
    private int power=0;
    private int score=0;
    private Level level;
    private PrefManager prefManager;
    private boolean isPowerUsing=false;
    private int noOfPowerUsed=0;
    private Sound sound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuizBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
        setScreenType();
        setContentView(view);

        init();
        ButtonClick();


    }

    //Click On Button Method
    private void ButtonClick() {

        //click on first option
        binding.option1Txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isClicked && !isFinish) {
                    if(timer!=null)
                        timer.cancel();
                   boolean ck= checkAnswer(binding.option1Txt);
                    if(ck)
                    {
                      anim(binding.option2Txt);binding.option2Txt.setVisibility(View.INVISIBLE);
                      anim(binding.option3Txt);  binding.option3Txt.setVisibility(View.INVISIBLE);
                      anim(binding.option4Txt);  binding.option4Txt.setVisibility(View.INVISIBLE);
                    }
                    isClicked = true;
                    binding.nextBtn.setVisibility(View.VISIBLE);
                }
            }
        });

        //click on second option
        binding.option2Txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isClicked && !isFinish) {
                    if(timer!=null)
                        timer.cancel();
                    boolean ck= checkAnswer(binding.option2Txt);
                    if(ck)
                    {
                        anim(binding.option3Txt); binding.option3Txt.setVisibility(View.INVISIBLE);
                        anim(binding.option4Txt); binding.option4Txt.setVisibility(View.INVISIBLE);
                        anim(binding.option1Txt); binding.option1Txt.setVisibility(View.INVISIBLE);
                    }
                    isClicked = true;
                    binding.nextBtn.setVisibility(View.VISIBLE);
                }
            }
        });

        //click on third option
        binding.option3Txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isClicked && !isFinish) {
                    if(timer!=null)
                        timer.cancel();
                    boolean ck= checkAnswer(binding.option3Txt);
                    if(ck)
                    {
                        anim(binding.option4Txt); binding.option4Txt.setVisibility(View.INVISIBLE);
                        anim(binding.option1Txt); binding.option1Txt.setVisibility(View.INVISIBLE);
                        anim(binding.option2Txt); binding.option2Txt.setVisibility(View.INVISIBLE);
                    }
                    isClicked = true;
                    binding.nextBtn.setVisibility(View.VISIBLE);
                }
            }
        });

        //click on fourth option
        binding.option4Txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!isClicked && !isFinish) {
                    if(timer!=null)
                        timer.cancel();
                    boolean ck= checkAnswer(binding.option4Txt);
                    if(ck)
                    {
                        anim(binding.option1Txt); binding.option1Txt.setVisibility(View.INVISIBLE);
                        anim(binding.option2Txt); binding.option2Txt.setVisibility(View.INVISIBLE);
                        anim(binding.option3Txt); binding.option3Txt.setVisibility(View.INVISIBLE);
                    }
                    isClicked = true;
                    binding.nextBtn.setVisibility(View.VISIBLE);
                }
            }
        });

        //click on next button
        binding.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heartTouchEffect(view);
                reset();
                if (index < quizQuestionArrayList.size()) {
                    binding.stepProgressBar.setProgress(index + 1);
                    binding.stepProgressBar.setMax(quizQuestionArrayList.size());
                    index++;
                    setNextQuestion();
                    setPower();
                    if (index == quizQuestionArrayList.size() - 1) {
                        index++;
                    }

                } else {
                    //frameLayout.setVisibility(View.GONE);
                    isFinish = true;
                    binding.stepProgressBar.setProgress(index);
                    float per = (correctAnswers / (float) quizQuestionArrayList.size()) * 100;
                    resultLayout(correctAnswers);
                }
            }
        });

        //click on home button
        binding.homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heartTouchEffect(view);
                sound.playClickSound();
                AlertDialog.Builder builder = new AlertDialog.Builder(QuizActivity.this);
                builder.setCancelable(false);
                builder.setMessage("Are you sure to leave?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //if user pressed "yes", then he is allowed to exit from application

                        lives--;
                        database.getReference().child("Users").child(auth.getCurrentUser().getUid()).child("remainingLive").setValue(lives).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                {

                                }
                            }
                        });
                        if (lives <= 0) {
                            //prefManager.setWaitTime(System.currentTimeMillis());
                            database.getReference().child("Users").child(auth.getCurrentUser().getUid()).child("remainingTime").setValue(System.currentTimeMillis()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(QuizActivity.this, "You have not live", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                        startActivity(new Intent(QuizActivity.this,MainActivity.class));
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

    //Result Layout
    @SuppressLint("UseCompatLoadingForDrawables")
    private void resultLayout(int marks) {
        int stars=0;
        float per = (marks / (float) quizQuestionArrayList.size()) * 100;
        binding.scoreTxt.setText(String.valueOf(score));
        if(per>=50) {
            sound.playLevelPassed();
            binding.resultLayout.setVisibility(View.VISIBLE);
            if(per<70)
            {
                stars=1;
                binding.stars1.setImageDrawable(getResources().getDrawable(R.drawable.star1_fill));
            }else if(per<100)
            {
                stars=2;
                binding.stars1.setImageDrawable(getResources().getDrawable(R.drawable.star1_fill));
                binding.stars2.setImageDrawable(getResources().getDrawable(R.drawable.star2_fill));
            }else if(per==100)
            {
                stars=3;
                binding.stars1.setImageDrawable(getResources().getDrawable(R.drawable.star1_fill));
                binding.stars2.setImageDrawable(getResources().getDrawable(R.drawable.star2_fill));
                binding.stars3.setImageDrawable(getResources().getDrawable(R.drawable.star1_fill));
            }

            setValueOfLevelInFireBase(stars,score);

        }else
        {
            sound.playLevelFailed();
            binding.completedView.setBackground(getResources().getDrawable(R.drawable.red));
            binding.completedViewTxt.setImageDrawable(getResources().getDrawable(R.drawable.once_more_txt));
            binding.replayBtn.setVisibility(View.GONE);
            binding.resultLayout.setVisibility(View.VISIBLE);
            binding.continueBtn.setText("Play Again");
            lives--;
            database.getReference().child("Users").child(auth.getCurrentUser().getUid()).child("remainingLive").setValue(lives).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                    {

                    }
                }
            });
            if (lives <= 0) {
                //prefManager.setWaitTime(System.currentTimeMillis());
                database.getReference().child("Users").child(auth.getCurrentUser().getUid()).child("remainingTime").setValue(System.currentTimeMillis()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(QuizActivity.this, "You have not live", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }


        binding.replayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heartTouchEffect(view);
                sound.playClickSound();
                if(lives>0)
                {
                    Intent intent = new Intent(QuizActivity.this,QuizActivity.class);
                    intent.putExtra("level",getLevel);
                    intent.putExtra("isFirstTime",false);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                    finishAffinity();
                }else
                {
                    Toast.makeText(QuizActivity.this, "You have not live", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(QuizActivity.this,MainActivity.class));
                            overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                            finishAffinity();

                        }
                    },2000);
                }

            }
        });
        binding.continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heartTouchEffect(view);
                sound.playClickSound();
                if(per>=50)
                {
                    Intent intent = new Intent(QuizActivity.this,QuizActivity.class);
                    intent.putExtra("isFirstTime",false);
                    intent.putExtra("level",getLevel+1);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                    finishAffinity();
                }else
                {
                    if(lives>0)
                    {
                        Intent intent = new Intent(QuizActivity.this,QuizActivity.class);
                        intent.putExtra("level",getLevel);
                        intent.putExtra("isFirstTime",false);
                        startActivity(intent);
                        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                        finishAffinity();
                    }else
                    {
                        Toast.makeText(QuizActivity.this, "You have not live", Toast.LENGTH_SHORT).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(new Intent(QuizActivity.this,MainActivity.class));
                                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                                finishAffinity();

                            }
                        },4000);
                    }
                }

            }
        });
        binding.replayHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heartTouchEffect(view);
                sound.playClickSound();
                startActivity(new Intent(QuizActivity.this,MainActivity.class));
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                finishAffinity();
            }
        });


    }

    //Update or set value to Firebase database
    private void setValueOfLevelInFireBase(int stars,int score) {

        float progress=(correctAnswers / (float) quizQuestionArrayList.size()) * 100;

        if(currentLevel>1 && currentLevel!=getLevel)
        {
            try {
                if(stars>level.getLevelStars())
                {
                    database.getReference().child("Levels").child(auth.getCurrentUser().getUid()).child(String.valueOf(getLevel)).child("levelStars").setValue(stars);
                }
                if(score > level.getLevelScore())
                {
                    database.getReference().child("Levels").child(auth.getCurrentUser().getUid()).child(String.valueOf(getLevel)).child("levelScore").setValue(score);
                }
                if(progress>level.getLevelProgress())
                {
                    database.getReference().child("Levels").child(auth.getCurrentUser().getUid()).child(String.valueOf(getLevel)).child("levelProgress").setValue(progress);
                }
            }catch (Exception e)
            {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            //Toast.makeText(this, "Good untill", Toast.LENGTH_SHORT).show();
        }else {
            if(currentLevel==getLevel)
            {
                Level level1= new Level(getLevel,"Level "+getLevel,"Level desc",progress,stars,score);
                database.getReference().child("Levels").child(auth.getCurrentUser().getUid()).child(String.valueOf(getLevel)).setValue(level1);
                currentLevel++;
                if (currentLevel<=12)
                {
                    database.getReference().child("Users").child(auth.getCurrentUser().getUid()).child("currentLevel").setValue(currentLevel);
                }

            }else
            {
                Toast.makeText(this, "Repeat", Toast.LENGTH_SHORT).show();
            }

        }
    }

    //Set Next Question to View
    private void setNextQuestion() {
        if(timer != null)
            timer.cancel();

        timer.start();
        if (index < quizQuestionArrayList.size()) {

            question = quizQuestionArrayList.get(index);
            binding.questionTxt.setText(question.getQuestion());
            ArrayList<String> option=new ArrayList<>();
            option.add(new String(question.getOption1()));
            option.add(new String(question.getOption2()));
            option.add(new String(question.getOption3()));
            option.add(new String(question.getOption4()));
            Collections.shuffle(option);
            binding.option1Txt.setText(option.get(0));
            binding.option2Txt.setText(option.get(1));
            binding.option3Txt.setText(option.get(2));
            binding.option4Txt.setText(option.get(3));

        } else {

        }
    }

    //Check Answer is Correct or Not
    private boolean checkAnswer(TextView selected) {
        String selectedAnswer = selected.getText().toString();
        if (selectedAnswer.equals(question.getAnswer())) {
            sound.playCorrectQuizAnswerSound();
            countForPower++;
            correctAnswers++;
            int i= Integer.parseInt(question.getQuestion_level());
            score=score+(i*10);
            selected.setVisibility(View.VISIBLE);
            return true;
        } else {
            sound.playWrongQuizAnswerSound();
            countForPower=0;
            anim(selected);
           selected.setVisibility(View.INVISIBLE);
           return false;
        }
    }

    //Explosion Animation to view
    private void anim(TextView selected) {
        Bloom.with(QuizActivity.this)
                .setParticleRadius(5)
                .setEffector(new BloomEffector.Builder()
                        .setDuration(1000)
                        .setAnchor(selected.getWidth() / 2, selected.getHeight() / 2)
                        .build())
                .boom(selected);
    }

    // Eliminate two option
    private void setTwoOption() {
        TextView[] randomTextView=new TextView[3];
        Random random= new Random();
        if(question.getAnswer().equals(binding.option1Txt.getText().toString()))
        {
            randomTextView[0]=binding.option2Txt; binding.option2Txt.setVisibility(View.INVISIBLE);
            randomTextView[1]=binding.option3Txt; binding.option3Txt.setVisibility(View.INVISIBLE);
            randomTextView[2]=binding.option4Txt; binding.option4Txt.setVisibility(View.INVISIBLE);
            int rm=random.nextInt(randomTextView.length);
            randomTextView[rm].setVisibility(View.VISIBLE);
        }else if(question.getAnswer().equals(binding.option2Txt.getText().toString()))
        {
            randomTextView[0]=binding.option1Txt; binding.option1Txt.setVisibility(View.INVISIBLE);
            randomTextView[1]=binding.option3Txt; binding.option3Txt.setVisibility(View.INVISIBLE);
            randomTextView[2]=binding.option4Txt; binding.option4Txt.setVisibility(View.INVISIBLE);
            int rm=random.nextInt(randomTextView.length);
            randomTextView[rm].setVisibility(View.VISIBLE);
        } else if(question.getAnswer().equals(binding.option3Txt.getText().toString()))
        {
            randomTextView[0]=binding.option1Txt; binding.option1Txt.setVisibility(View.INVISIBLE);
            randomTextView[1]=binding.option2Txt; binding.option2Txt.setVisibility(View.INVISIBLE);
            randomTextView[2]=binding.option4Txt; binding.option4Txt.setVisibility(View.INVISIBLE);
            int rm=random.nextInt(randomTextView.length);
            randomTextView[rm].setVisibility(View.VISIBLE);
        } else if(question.getAnswer().equals(binding.option4Txt.getText().toString()))
        {
            randomTextView[0]=binding.option1Txt; binding.option1Txt.setVisibility(View.INVISIBLE);
            randomTextView[1]=binding.option2Txt; binding.option2Txt.setVisibility(View.INVISIBLE);
            randomTextView[2]=binding.option3Txt; binding.option3Txt.setVisibility(View.INVISIBLE);
            int rm=random.nextInt(randomTextView.length);
            randomTextView[rm].setVisibility(View.VISIBLE);
        }

    }


    //Reset the Question To view
    void reset() {
        isClicked = false;
        isPowerUsing=false;
        binding.nextBtn.setVisibility(View.INVISIBLE);
        binding.option1Txt.setVisibility(View.VISIBLE);
        binding.option2Txt.setVisibility(View.VISIBLE);
        binding.option3Txt.setVisibility(View.VISIBLE);
        binding.option4Txt.setVisibility(View.VISIBLE);
        if(power>=1)
        {
            binding.power.setImageDrawable(getDrawable(R.drawable.lightbulb_of));
        }else
        {
            binding.power.setImageDrawable(getDrawable(R.drawable.lightbulb_of));
        }

    }


    //Reset the timer
    void resetTimer() {
        timer = new CountDownTimer(15000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                @SuppressLint("DefaultLocale")
                String fmt=String.format("%d : %d",(millisUntilFinished/1000)/60,(millisUntilFinished/1000)%60);
                binding.timer.setText(fmt);
            }

            @Override
            public void onFinish() {
                reset();
                if (index < quizQuestionArrayList.size()) {
                    correctAnswers--;
                    binding.stepProgressBar.setProgress(index + 1);
                    binding.stepProgressBar.setMax(quizQuestionArrayList.size());
                    index++;
                    setNextQuestion();
                    setPower();
                    if (index == quizQuestionArrayList.size() - 1) {
                        index++;
                    }

                }else
                {
                    binding.stepProgressBar.setProgress(index);
                    isFinish=true;
                    resultLayout(correctAnswers);
                    Toast.makeText(QuizActivity.this, "Time Up", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    //initialisation Of variable and object
    private void init() {
        Intent intent = getIntent();
        getLevel = intent.getExtras().getInt("level");
        binding.levelName.setText("Level "+getLevel);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        prefManager= new PrefManager(this,"Game");
        sound= new Sound(QuizActivity.this);
        sound.playQuizBackgroundMusic();
        database.getReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                int lives = Integer.parseInt(snapshot.child("Users").child(auth.getCurrentUser().getUid()).child("remainingLive").getValue().toString());
                int currentLevel=Integer.parseInt(snapshot.child("Users").child(auth.getCurrentUser().getUid()).child("currentLevel").getValue().toString());
                int power=Integer.parseInt(snapshot.child("Users").child(auth.getCurrentUser().getUid()).child("power").getValue().toString());

                Level level1=snapshot.child("Levels").child(auth.getCurrentUser().getUid()).child(String.valueOf(getLevel)).getValue(Level.class);
                binding.liveValue.setText(String.valueOf(lives));
                getData(lives,currentLevel,level1,power);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        gameDatabase = Room.databaseBuilder(QuizActivity.this,
                GameDatabase.class, "game_database").allowMainThreadQueries().build();
        quizQuestionDao = gameDatabase.quizQuestionDao();
        insertValue(quizQuestionDao);
        quizQuestionArrayList = quizQuestionDao.getLevelQuestion(getLevel);
        Collections.shuffle(quizQuestionArrayList);
        resetTimer();
        setNextQuestion();

    }

    //SetPower
    private void setPower() {
        if(countForPower==5)
        {
            power++;
            sound.playGetPowerSound();
            database.getReference().child("Users").child(auth.getCurrentUser().getUid()).child("power").setValue(power);
            countForPower=0;
        }
        if(power>=1)
        {
            binding.power.setImageDrawable(getDrawable(R.drawable.lightbulb_on));

        }else {
            binding.power.setImageDrawable(getDrawable(R.drawable.lightbulb_of));
        }
        binding.powerTxt.setText(String.valueOf(power));
        binding.power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (power>=1 && !isFinish && !isClicked && !isPowerUsing && noOfPowerUsed<2)
                {
                    noOfPowerUsed++;
                    isPowerUsing=true;
                    power--;
                    database.getReference().child("Users").child(auth.getCurrentUser().getUid()).child("power").setValue(power);
                    if(power<1)
                    {
                        binding.power.setImageDrawable(getDrawable(R.drawable.lightbulb_of));
                    }
                    setTwoOption();
                }
            }
        });
    }
    //set Click Effect
    private void heartTouchEffect(View view) {
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);
        view.startAnimation(anim);
    }

    //Insert Question to Local Database
    public void insertValue(QuizQuestionDao quizQuestionDao) {
        PreInsertedQuizQuestion preInsertedQuizQuestion = new PreInsertedQuizQuestion(quizQuestionDao);
        preInsertedQuizQuestion.insertInDatabase();
    }


    //Get Data from firebase database
    private void getData(int live,int currentLevel,Level level,int power) {
        this.lives = live;
        this.currentLevel=currentLevel;
        this.level=level;
        this.power=power;

        if(power>=1)
        {
            binding.power.setImageDrawable(getDrawable(R.drawable.lightbulb_on));

        }else {
            binding.power.setImageDrawable(getDrawable(R.drawable.lightbulb_of));
        }
        setPower();
    }

    //Set Screen Type
    private void setScreenType() {
        currentApiVersion = android.os.Build.VERSION.SDK_INT;

        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        // This work only for android 4.4+
        if (currentApiVersion >= Build.VERSION_CODES.KITKAT) {

            getWindow().getDecorView().setSystemUiVisibility(flags);

            // Code below is to handle presses of Volume up or Volume down.
            // Without this, after pressing volume buttons, the navigation bar will
            // show up and won't hide
            final View decorView = getWindow().getDecorView();
            decorView
                    .setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {

                        @Override
                        public void onSystemUiVisibilityChange(int visibility) {
                            if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
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
        if (currentApiVersion >= Build.VERSION_CODES.KITKAT && hasFocus) {
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
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(QuizActivity.this);
        builder.setCancelable(false);
        builder.setMessage("Are you sure to leave?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                lives--;
                database.getReference().child("Users").child(auth.getCurrentUser().getUid()).child("remainingLive").setValue(lives).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {

                        }
                    }
                });
                if (lives <= 0) {
                    //prefManager.setWaitTime(System.currentTimeMillis());
                    database.getReference().child("Users").child(auth.getCurrentUser().getUid()).child("remainingTime").setValue(System.currentTimeMillis()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(QuizActivity.this, "You have not live", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                startActivity(new Intent(QuizActivity.this,MainActivity.class));
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

    @Override
    protected void onDestroy() {
        timer.cancel();
        super.onDestroy();
        sound.quizOnDestroy();
    }
}