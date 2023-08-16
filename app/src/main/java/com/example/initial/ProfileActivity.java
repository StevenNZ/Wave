package com.example.initial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity {

    private ProfileViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView usernameGreet = findViewById(R.id.profileUsername);
        Button signoutBtn = findViewById(R.id.signoutBtn);

        String receivedString = getIntent().getStringExtra("USERNAME");
        if (receivedString != null) {
            String greet = "Hi " + receivedString + "!";
            usernameGreet.setText(greet);
        }

        model = new ViewModelProvider(this).get(ProfileViewModel.class);

        signoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.signOutUser();

                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}