package com.example.initial;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegisterActivity extends AppCompatActivity {

    private RegisterViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button registerBtn = findViewById(R.id.registerBtn);
        EditText emailField = findViewById(R.id.emailField);
        EditText usernameField = findViewById(R.id.usernameField);
        EditText passwordField = findViewById(R.id.passwordField);
        EditText passwordConfirmedField = findViewById(R.id.passwordConfirmField);

        // Initialize Firebase Auth

        model = new ViewModelProvider(this).get(RegisterViewModel.class);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailAddress = emailField.getText().toString();
                String username = usernameField.getText().toString();
                String password = passwordField.getText().toString();
                String passwordTwo = passwordConfirmedField.getText().toString();

                if (checkIfEmpty(emailAddress, username, password)) {
                    Toast.makeText(getBaseContext(), "Make sure fields are not empty", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 6) {
                    Toast.makeText(getBaseContext(), "Password too short, needs at least 6 characters", Toast.LENGTH_SHORT).show();
                } else if (!checkIfMatch(password, passwordTwo)) {
                    Toast.makeText(getBaseContext(), "Password does not match", Toast.LENGTH_SHORT).show();
                } else {
                    model.registerUser(username, emailAddress, password);
                }
            }
        });

        Button tempBtn = findViewById(R.id.temp);
        tempBtn.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        model.getAuthenticationError().observe(this, error -> {
            if (error != null) {
                Toast.makeText(getBaseContext(), error, Toast.LENGTH_SHORT).show();
            }
        });

        model.getAuthenticatedUser().observe(this, user -> {
            if (user != null) {
                model.signOutUser();
                Toast.makeText(getBaseContext(), "Registered Successfully! Please check your email for verification", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }



    private boolean checkIfEmpty(String emailAddress, String username, String password) {
        return TextUtils.isEmpty(emailAddress) || TextUtils.isEmpty(username) || TextUtils.isEmpty(password);
    }

    private boolean checkIfMatch(String password, String passwordTwo) {
        return password.equals(passwordTwo);
    }
}