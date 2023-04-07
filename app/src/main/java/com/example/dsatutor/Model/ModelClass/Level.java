package com.example.dsatutor.Model.ModelClass;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Level {
    @PrimaryKey(autoGenerate = true)
    private int levelId;

    @ColumnInfo
    private String levelName;
    @ColumnInfo
    private String levelDescription;
    @ColumnInfo
    private float levelProgress;
    @ColumnInfo
    private int levelStars;
    @ColumnInfo
    private int levelScore;

    @Ignore
    public Level(int levelId, String levelName, String levelDescription, float levelProgress, int levelStars, int levelScore) {
        this.levelId = levelId;
        this.levelName = levelName;
        this.levelDescription = levelDescription;
        this.levelProgress = levelProgress;
        this.levelStars = levelStars;
        this.levelScore = levelScore;
    }

    @Ignore
    public Level()
    {

    }
    public Level(String levelName, String levelDescription, float levelProgress, int levelStars, int levelScore) {
        this.levelName = levelName;
        this.levelDescription = levelDescription;
        this.levelProgress = levelProgress;
        this.levelStars = levelStars;
        this.levelScore = levelScore;
    }


    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getLevelDescription() {
        return levelDescription;
    }

    public void setLevelDescription(String levelDescription) {
        this.levelDescription = levelDescription;
    }

    public float getLevelProgress() {
        return levelProgress;
    }

    public void setLevelProgress(float levelProgress) {
        this.levelProgress = levelProgress;
    }

    public int getLevelStars() {
        return levelStars;
    }

    public void setLevelStars(int levelStars) {
        this.levelStars = levelStars;
    }

    public int getLevelScore() {
        return levelScore;
    }

    public void setLevelScore(int levelScore) {
        this.levelScore = levelScore;
    }
}
