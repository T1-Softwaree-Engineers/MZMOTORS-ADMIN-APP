package com.example.mzmotors_admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeAdmin extends AppCompatActivity implements View.OnClickListener {

    Intent i;
    Button post, pending, users, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        logout = (Button) findViewById(R.id.btnlogout);
        post = (Button) findViewById(R.id.btnpost);
        pending = (Button) findViewById(R.id.btnpending);
        users = (Button) findViewById(R.id.btnusers);

        logout.setOnClickListener(this);
        post.setOnClickListener(this);
        pending.setOnClickListener(this);
        users.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnlogout:
                i = new Intent(HomeAdmin.this, MainActivity.class);
                startActivity(i);
                break;
            case R.id.btnpost:
                i = new Intent(HomeAdmin.this, AllPost.class);
                startActivity(i);
                break;
            case R.id.btnpending:
                i = new Intent(HomeAdmin.this, PendingPost.class);
                startActivity(i);
                break;
            case R.id.btnusers:
                i = new Intent(HomeAdmin.this, AllUsers.class);
                startActivity(i);
                break;
        }
    }
}