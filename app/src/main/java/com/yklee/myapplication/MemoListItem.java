package com.yklee.myapplication;

public class MemoListItem {
    private String tagName;
    private String content;
    private String date;

    public MemoListItem(String tagName, String content, String date) {
        this.tagName = tagName;
        this.content = content;
        this.date = date;
    }

    /*-- getter --*/
    public String getTagName() {
        return tagName;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }
}
