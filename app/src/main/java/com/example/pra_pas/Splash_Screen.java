package com.example.pra_pas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class Splash_Screen extends AppCompatActivity {

    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String USERNAME_KEY = "username";
    private static final String PASSWORD_KEY = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                String username = settings.getString(USERNAME_KEY, "");
                String password = settings.getString(PASSWORD_KEY, "");

                if ("Renren".equals(username) && "arka".equals(password)) {
                    Intent intent = new Intent(Splash_Screen.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(Splash_Screen.this, login.class);
                    startActivity(intent);
                }
                finish(); // Finish the Splash_Screen activity
            }
        }, 500);
    }
}
