package com.example.dsatutor.Model.DAO;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.dsatutor.Model.ModelClass.QuizQuestion;

import java.util.List;

@Dao
public interface QuizQuestionDao {

    //Get list of question from database according to level
    @Query("Select * from QuizQuestion where levelId=:level")
    List<QuizQuestion> getLevelQuestion(int level);

    //Insert question into database
    @Insert
    void setQuestion(QuizQuestion question);

    //get count of question present in database
    @Query("Select Count(*) from QuizQuestion")
    int getCount();
}
