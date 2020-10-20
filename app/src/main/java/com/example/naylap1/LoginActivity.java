package com.example.naylap1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.naylap1.model.Course;
import com.example.naylap1.model.Message;
import com.example.naylap1.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnSignUp);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadUserData();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
        });

    }

    //Mock user data creating here
    private void loadUserData(){
        User user = User.getInstance();

        user.setName("Rachel");
        user.setSurname("Green");
        user.setMail("Rachel@gmail.com");
        user.setJoinDate(new Date());
        user.setProfileImage(ContextCompat.getDrawable(this, R.drawable.profile_pic));

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
