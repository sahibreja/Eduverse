package com.example.dsatutor.UI.start.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dsatutor.Database.GameDatabase;
import com.example.dsatutor.MainActivity;
import com.example.dsatutor.Model.DAO.UsersDao;
import com.example.dsatutor.Model.PrefManager;
import com.example.dsatutor.Model.Sound;
import com.example.dsatutor.R;
import com.example.dsatutor.UI.start.Intro.IntroVideoActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    private EditText email;
    private TextInputEditText pass;
    private TextView newUser,forgotPass;
    private Button loginBtn;

    private FirebaseAuth auth;

    private FirebaseDatabase database;

    private ProgressDialog progressDialog,progressForgot;

    private GameDatabase gameDatabase;
    private UsersDao usersDao;
    private int fLives,fTotalScore,fCurrentLevel;
    private long fLastRefillTime;
    private float fProgress;
    private String userName;
    private String userId;
    private PrefManager prefManager;
    private Sound sound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
        setContentView(R.layout.activity_login);

        init();
        buttonClick();
    }

    private void buttonClick() {

        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heartTouchEffect(v);
                sound.playClickOnButtonSound();
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heartTouchEffect(v);
                sound.playClickOnButtonSound();
                String userEmail = email.getText().toString().trim();
                String userPassword = pass.getText().toString();
                if(userEmail.isEmpty())
                {
                    email.setError("Please Enter Your Email");
                }else if(userPassword.isEmpty())
                {
                    pass.setError("Please Enter Your Password");
                }else
                {
                    progressDialog.show();
                    auth.signInWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();
                            if(task.isSuccessful())
                            {
                                    prefManager.setFirstTimeLaunch(false);
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                                    finishAffinity();
                            }else
                            {
                                Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heartTouchEffect(v);
                sound.playClickOnButtonSound();
                String emailTxt=email.getText().toString().trim();
                if(emailTxt.isEmpty())
                {
                    Toast.makeText(LoginActivity.this, "Enter Your registered email address", Toast.LENGTH_SHORT).show();
                    return;
                }
                auth.sendPasswordResetEmail(emailTxt).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void init()
    {
        email =findViewById(R.id.email_txt);
        pass =findViewById(R.id.pass_txt);
        newUser =findViewById(R.id.new_user);
        loginBtn =findViewById(R.id.login_btn);
        forgotPass =findViewById(R.id.forgot_pass);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        prefManager= new PrefManager(LoginActivity.this,"Game");
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressForgot=new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Login");
        progressDialog.setMessage("Login to your account");
        progressForgot.setTitle("Forgot password");
        progressForgot.setMessage("Sending link to your email to reset your password");

        gameDatabase= Room.databaseBuilder(LoginActivity.this,
                GameDatabase.class, "game_database").allowMainThreadQueries().build();
        usersDao= gameDatabase.userDao();

        sound=new Sound(LoginActivity.this);
    }
    private void heartTouchEffect(View view)
    {
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);
        view.startAnimation(anim);
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser != null){
            //reload();
            startActivity(new Intent(LoginActivity.this, IntroVideoActivity.class));
            finish();
        }
    }
    private void getValue(String uid)
    {

    }

}