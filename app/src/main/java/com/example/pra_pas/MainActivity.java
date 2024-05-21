package com.example.pra_pas;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnItemSelectedListener(navListener);

        // Load the default fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new Fragment3())
                .commit();

        setSelectedMenuItem(R.id.menu_item3);
    }


    public void setSelectedMenuItem(int menuItemId) {
        bottomNavigationView.setSelectedItemId(menuItemId);
    }


    private BottomNavigationView.OnItemSelectedListener navListener =
            new BottomNavigationView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = new Fragment3();

                    if (item.getItemId() == R.id.menu_item1) {
                        selectedFragment = new Fragment1();
                    } else if (item.getItemId() == R.id.menu_item2) {
                        selectedFragment = new Fragment2();
                    } else if (item.getItemId() == R.id.menu_item3) {
                        selectedFragment = new Fragment3();
                    }

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, selectedFragment)
                            .commit();

                    return true;
                }
            };

}
