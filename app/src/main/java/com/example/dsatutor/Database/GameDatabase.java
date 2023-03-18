package com.example.dsatutor.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.dsatutor.Model.DAO.QuizQuestionDao;
import com.example.dsatutor.Model.DAO.UsersDao;
import com.example.dsatutor.Model.Level;
import com.example.dsatutor.Model.QuizQuestion;
import com.example.dsatutor.Model.Users;


@Database(entities = {Users.class, Level.class, QuizQuestion.class}, version = 1)
public abstract class GameDatabase extends RoomDatabase {
    public abstract UsersDao userDao();
    public abstract QuizQuestionDao quizQuestionDao();

}
