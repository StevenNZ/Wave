package com.example.initial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView usernameGreet = findViewById(R.id.profileUsername);

        String receivedString = getIntent().getStringExtra("USERNAME");
        if (receivedString != null) {
            String greet = "Hi " + receivedString + "!";
            usernameGreet.setText(greet);
        }
    }
}