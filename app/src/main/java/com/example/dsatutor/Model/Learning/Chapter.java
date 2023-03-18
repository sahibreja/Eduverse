package com.example.dsatutor.Model.Learning;

import java.util.ArrayList;

public class Chapter {
    private int chapter_no;
    private String chapterName;
    private ArrayList<SubChapter> subChapterArrayList;

    public Chapter(){}

    public Chapter(int chapter_no, String chapterName, ArrayList<SubChapter> subChapterArrayList) {
        this.chapter_no = chapter_no;
        this.chapterName = chapterName;
        this.subChapterArrayList = subChapterArrayList;
    }

    public int getChapter_no() {
        return chapter_no;
    }

    public void setChapter_no(int chapter_no) {
        this.chapter_no = chapter_no;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public ArrayList<SubChapter> getSubChapterArrayList() {
        return subChapterArrayList;
    }

    public void setSubChapterArrayList(ArrayList<SubChapter> subChapterArrayList) {
        this.subChapterArrayList = subChapterArrayList;
    }
}
