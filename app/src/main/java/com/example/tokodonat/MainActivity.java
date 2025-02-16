package com.example.tokodonat;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.content.Intent;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ImageView profile;
    private UserDAO userDAO; // Tambahkan UserDAO untuk database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi UserDAO dan buka koneksi ke database
        userDAO = new UserDAO(this);
        userDAO.open();

        // Mendapatkan semua pengguna dari database
        List<User> users = userDAO.getAllUsers();
        for (User user : users) {
            Log.d("User", "Name: " + user.getName() + ", Email: " + user.getEmail());
        }

        // Menutup koneksi database setelah selesai
        userDAO.close();

        // Mengatur tombol login untuk pindah ke homepage
        findViewById(R.id.loginButton).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, homepage.class);
            startActivity(intent);
        });
    }
}
