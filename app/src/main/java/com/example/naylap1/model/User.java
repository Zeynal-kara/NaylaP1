package com.example.naylap1.model;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import com.example.naylap1.LoginActivity;
import com.example.naylap1.MainActivity;
import com.example.naylap1.R;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User implements Serializable {
    String name, surname, mail;
    int imgResID;
    Date joinDate;
    MessageManager messageManager;
    CourseManager courseManager;

    static User user;

    public User(){
        messageManager = new MessageManager();
        courseManager = new CourseManager();
    }

    public static void setUser(User user) {
        User.user = user;
    }

    public static User getInstance() {
        if (user == null)
            user = new User();
        return user;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return name + " " + surname;
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

    public int getImgResID() {
        return imgResID;
    }

    public void setImgResID(int imgResID) {
        this.imgResID = imgResID;
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

    public void saveUser(Context context){
        FileOutputStream fos = null;
        try {

            fos = context.openFileOutput("user_data", Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(User.getInstance());
            os.close();
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkUserLogin(Context context){

        boolean isLogin = false;
        FileInputStream fis = null;
        try {
            fis = context.openFileInput("user_data");
            ObjectInputStream is = new ObjectInputStream(fis);
            User user = (User) is.readObject();

            if (user != null){
                isLogin = true;
                User.setUser(user);
            }


            is.close();
            fis.close();

        }catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return isLogin;

    }

    //Mock user data creating here
    public void loadMockUserData(){

        user.setName("Rachel");
        user.setSurname("Green");
        user.setMail("Rachel@gmail.com");
        user.setJoinDate(new Date());
        user.setImgResID(R.drawable.profile_pic);

        user.getMessageManager().setMessageList(loadMockMessages());
        user.getCourseManager().setCourseList(loadMockCourses());

    }

    //Load mock item for testing ...
    private List<Message> loadMockMessages(){

        List<Message>  messageList = new ArrayList<>();
        Message message = new Message();
        message.setTitle("From: Figma course");
        message.setContent("Congratulations! You passed the test and your certificate is ready.");
        message.setUnreadCount(1);
        message.setDate(new Date());

        messageList.add(message);


        for (int i=0; i<7; i++){

            Message message1 = new Message();
            message1.setTitle("From: Figma course"+i);
            message1.setContent("Congratulations! You passed the test and your certificate is ready.");
            message1.setUnreadCount(0);
            message1.setDate(new Date());
            messageList.add(message1);

        }

        return messageList;
    }

    //Load mock item for testing ...
    private List<Course> loadMockCourses(){

        List<Course> coursesList = new ArrayList<>();

        Course course = new Course(Course.ProcessCode.COMPLETED);
        course.setTitle("Figma for beginners");
        course.setDuration("Duration: 24hr 30 min");
        course.setImgIconResId(R.drawable.recent_cours_icon);

        coursesList.add(course);

        for (int i=0; i<7; i++){

            Course course1 = new Course(Course.ProcessCode.IN_PROCESS);
            course1.setTitle("Figma for beginners"+i);
            course1.setDuration("Duration: 24hr 30 min");
            course1.setImgIconResId(R.drawable.recent_cours_icon);

            coursesList.add(course1);

        }

        Course course2 = new Course(Course.ProcessCode.NOT_STARTED);
        course2.setTitle("Figma for beginners");
        course2.setDuration("Duration: 24hr 30 min");
        course2.setImgIconResId(R.drawable.recent_cours_icon);
        coursesList.add(course2);

        return coursesList;

    }
}
