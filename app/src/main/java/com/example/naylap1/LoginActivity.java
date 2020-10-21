package com.example.naylap1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.naylap1.model.Course;
import com.example.naylap1.model.Message;
import com.example.naylap1.model.User;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

                User user = User.getInstance();
                user.loadMockUserData();
                user.saveUser(getApplicationContext());
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
        });

        if (User.checkUserLogin(this)){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }



    }



}
