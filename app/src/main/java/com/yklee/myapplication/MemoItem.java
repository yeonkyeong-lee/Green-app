package com.yklee.myapplication;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class MemoItem {
    private String tagName;
    private Date date;
    private String content;

    public MemoItem(String tagName, Date date, String content) {
        this.tagName = tagName;
        this.content = content;
        this.date = date;
    }

    /*-- getter --*/
    public String getTagName() {
        return tagName;
    }
    public String getContent() {return content;}
    public Date getDate() {return date;}
    public String getStringDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

}
