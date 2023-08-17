package com.example.wave.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wave.R;
import com.example.wave.ViewModel.LoginViewModel;

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

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(getBaseContext(), "Make sure fields are not empty", Toast.LENGTH_SHORT).show();
                } else {
                    model.signInUser(email, password);
                    }
                }
        });

        model.getAuthenticatedUser().observe(this, user -> {
            if (user != null) {
                Toast.makeText(getBaseContext(), "Login successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                intent.putExtra("USERNAME", user.getDisplayName());
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