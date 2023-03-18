package com.example.dsatutor.Model.DAO;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.dsatutor.Model.QuizQuestion;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface QuizQuestionDao {


    @Query("Select * from QuizQuestion where levelId=:level")
    List<QuizQuestion> getLevelQuestion(int level);

    @Insert
    void setQuestion(QuizQuestion question);

    @Query("Select Count(*) from QuizQuestion")
    int getCount();
}
