package com.example.dsatutor.Model.Levels;

import android.graphics.drawable.Drawable;

public class LevelCreate {

    private int levelName;
    private Drawable background;

    public LevelCreate() {
    }

    public LevelCreate(int levelName, Drawable background) {
        this.levelName = levelName;
        this.background = background;
    }

    public int getLevelName() {
        return levelName;
    }

    public void setLevelName(int levelName) {
        this.levelName = levelName;
    }

    public Drawable getBackground() {
        return background;
    }

    public void setBackground(Drawable background) {
        this.background = background;
    }
}
