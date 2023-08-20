package com.example.wave.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wave.R;
import com.example.wave.ViewModel.RegisterViewModel;
import com.google.android.material.appbar.MaterialToolbar;

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
        MaterialToolbar toolbar = findViewById(R.id.topAppBar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

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

        model.getAuthenticationError().observe(this, error -> {
            if (error != null) {
                Toast.makeText(getBaseContext(), error, Toast.LENGTH_SHORT).show();
                model.resetAuthenticationError();
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