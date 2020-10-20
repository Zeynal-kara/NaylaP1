package com.example.naylap1.model;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import java.util.Date;

public class User {
    String name, surname, mail;
    Drawable profileImage;
    Date joinDate;
    MessageManager messageManager;
    CourseManager courseManager;

    static User user;

    public User(){
        messageManager = new MessageManager();
        courseManager = new CourseManager();
    }

    public static User getInstance() {
        if (user == null)
            user = new User();
        return user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Drawable getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Drawable profileImage) {
        this.profileImage = profileImage;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public MessageManager getMessageManager() {
        return messageManager;
    }

    public CourseManager getCourseManager() {
        return courseManager;
    }

}
