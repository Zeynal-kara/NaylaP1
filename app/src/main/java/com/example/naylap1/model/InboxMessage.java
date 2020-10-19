package com.example.naylap1.model;

import java.text.DateFormat;
import java.util.Date;

public class InboxMessage {
    String title, content;
    Date date;
    int unreadCount;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return DateFormat.getDateInstance().format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(int unreadCount) {
        this.unreadCount = unreadCount;
    }
}
