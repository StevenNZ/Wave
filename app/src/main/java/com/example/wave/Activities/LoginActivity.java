package com.example.wave.Activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.wave.R;
import com.example.wave.ViewModel.LoginViewModel;
import com.google.android.material.appbar.MaterialToolbar;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView registerLink = findViewById(R.id.registerLink);
        Button loginBtn = findViewById(R.id.loginButton);
        EditText emailField = findViewById(R.id.emailField);
        EditText passwordField = findViewById(R.id.passwordField);
        MaterialToolbar toolbar = findViewById(R.id.topAppBar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (model.getAuthenticatedUser() == null) {
                    onBackPressed();
                } else {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
            }
        });

        registerLink.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        model = new ViewModelProvider(this).get(LoginViewModel.class);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailField.getText().toString();
                String password = passwordField.getText().toString();

                // check for invalid entry
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(getBaseContext(), "Make sure fields are not empty", Toast.LENGTH_SHORT).show();
                } else {
                    model.signInUser(email, password);
                    }
                }
        });

        // observe for if user signs in successfully
        model.getAuthenticatedUser().observe(this, user -> {
            if (user != null) {
                Toast.makeText(getBaseContext(), "Login successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        // error is of type string
        model.getAuthenticationError().observe(this, error -> {
            if (error != null) {
                Toast.makeText(getBaseContext(), error, Toast.LENGTH_SHORT).show();
                model.resetAuthenticationError();
            }
        });
    }
}