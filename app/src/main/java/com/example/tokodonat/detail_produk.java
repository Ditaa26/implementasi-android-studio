package com.example.tokodonat;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class detail_produk extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_produk);
    }

    public void goTomenu(View view) {
        Intent intent = new Intent(detail_produk.this, menu.class);
        startActivity(intent);
        finish();
    }
}
