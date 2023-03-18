package com.example.dsatutor.Model.Learning;

public class SubChapter {
    private int subChapterNo;
    private String levelName;
    private String subChapterName;
    private String videoId;

    public SubChapter() {
    }

    public SubChapter(int subChapterNo, String levelName, String subChapterName, String videoId) {
        this.subChapterNo = subChapterNo;
        this.levelName = levelName;
        this.subChapterName = subChapterName;
        this.videoId = videoId;
    }

    public int getSubChapterNo() {
        return subChapterNo;
    }

    public void setSubChapterNo(int subChapterNo) {
        this.subChapterNo = subChapterNo;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getSubChapterName() {
        return subChapterName;
    }

    public void setSubChapterName(String subChapterName) {
        this.subChapterName = subChapterName;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}
