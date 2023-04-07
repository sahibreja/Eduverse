package com.example.dsatutor.Model.ModelClass;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

//@Entity(foreignKeys = {@ForeignKey(entity = Level.class,
//        parentColumns = "levelId",
//        childColumns = "levelId",
//        onDelete = ForeignKey.CASCADE)
//})
@Entity
public class QuizQuestion {
    @PrimaryKey(autoGenerate = true)
    private int questionId;

    private int levelId;

    @ColumnInfo
    private String question_level;
    @ColumnInfo
    private String question;
    @ColumnInfo
    private String option1;
    @ColumnInfo
    private String option2;
    @ColumnInfo
    private String option3;
    @ColumnInfo
    private String option4;
    @ColumnInfo
    private String answer;
    @ColumnInfo
    private String questionHint;

    public QuizQuestion(int levelId, String question_level, String question, String option1, String option2, String option3, String option4, String answer, String questionHint) {
        this.levelId = levelId;
        this.question_level = question_level;
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
        this.questionHint = questionHint;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    public String getQuestion_level() {
        return question_level;
    }

    public void setQuestion_level(String question_level) {
        this.question_level = question_level;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestionHint() {
        return questionHint;
    }

    public void setQuestionHint(String questionHint) {
        this.questionHint = questionHint;
    }
}
