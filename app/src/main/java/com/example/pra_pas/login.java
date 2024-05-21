package com.example.pra_pas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {

    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String USERNAME_KEY = "username";
    private static final String PASSWORD_KEY = "password";

    private EditText loginEditText;
    private EditText passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEditText = findViewById(R.id.login);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = loginEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (username.equals("Renren") && password.equals("arka")) {
                    SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString(USERNAME_KEY, username);
                    editor.putString(PASSWORD_KEY, password);
                    editor.apply();

                    Intent intent = new Intent(login.this, MainActivity.class);
                    startActivity(intent);
                    finish(); // Close the login activity
                } else {
                    Toast.makeText(login.this, "Invalid login credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
