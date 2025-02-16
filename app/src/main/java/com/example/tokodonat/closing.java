package com.example.tokodonat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class closing extends AppCompatActivity {

    private ImageView homeImageView, profileImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closing);

        homeImageView = findViewById(R.id.home_image_view4);
        profileImageView = findViewById(R.id.profile_image_view4);

        homeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(closing.this, homepage.class);
                startActivity(homeIntent);
            }
        });

        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profileIntent = new Intent(closing.this, profile.class);
                startActivity(profileIntent);
            }
        });
    }
}
