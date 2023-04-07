package com.example.dsatutor.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.dsatutor.Model.DAO.QuizQuestionDao;
import com.example.dsatutor.Model.DAO.UsersDao;
import com.example.dsatutor.Model.ModelClass.Level;
import com.example.dsatutor.Model.ModelClass.QuizQuestion;
import com.example.dsatutor.Model.ModelClass.Users;


@Database(entities = {Users.class, Level.class, QuizQuestion.class}, version = 1)
public abstract class GameDatabase extends RoomDatabase {
    //user data access object
    public abstract UsersDao userDao();

    //Quiz question data access object
    public abstract QuizQuestionDao quizQuestionDao();

}
