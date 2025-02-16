package com.example.tokodonat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class homepage extends AppCompatActivity {
    private ImageView profile;
    private LinearLayout linearMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        findViewById(R.id.profile_image_view).setOnClickListener(v -> {
            Intent intent = new Intent(homepage.this, profile.class);
            startActivity(intent);

        });

        findViewById(R.id.linearMenu).setOnClickListener(view -> {
            Intent intent = new Intent(homepage.this, menu.class);
            startActivity(intent);
        });
    }
}