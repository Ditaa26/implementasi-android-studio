package com.example.tokodonat;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class menu extends AppCompatActivity {
    private LinearLayout item1, item2;
    private TextView textJumlah1, textJumlah2, textJumlahBayar, textHargaBayar;
    private ImageView plusButton1, minusButton1, plusButton2, minusButton2, backButton;
    private int hargaPerDonat1 = 7000; // Harga Donat Matcha
    private int hargaPerDonat2 = 10000; // Harga Donat Strawberry
    private int jumlahItem1 = 0;
    private int jumlahItem2 = 0;
    private ImageView homeImageView, profileImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        item1 = findViewById(R.id.item1);
        item2 = findViewById(R.id.item2);
        textJumlah1 = findViewById(R.id.jumlah1);
        textJumlah2 = findViewById(R.id.jumlah2);
        textJumlahBayar = findViewById(R.id.jumlahBayar);
        textHargaBayar = findViewById(R.id.hargaBayar);
        plusButton1 = findViewById(R.id.plus1);
        minusButton1 = findViewById(R.id.min1);
        plusButton2 = findViewById(R.id.plus2);
        minusButton2 = findViewById(R.id.min2);
        backButton = findViewById(R.id.back_button3);
        homeImageView = findViewById(R.id.home_image_view2);
        profileImageView = findViewById(R.id.profile_image_view2);

        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(menu.this, homepage.class);
            startActivity(intent);
        });

        item1.setOnClickListener(view -> {
            Intent intent = new Intent(menu.this, detail_produk.class);
            startActivity(intent);
        });

        item2.setOnClickListener(view -> {
            Intent intent = new Intent(menu.this, detail_produk2.class);
            startActivity(intent);
        });

        plusButton1.setOnClickListener(v -> {
            jumlahItem1++;
            textJumlah1.setText(String.valueOf(jumlahItem1));
            updateJumlahTotal();
            updateHargaTotal();
        });

        minusButton1.setOnClickListener(v -> {
            if (jumlahItem1 > 0) {
                jumlahItem1--;
                textJumlah1.setText(String.valueOf(jumlahItem1));
                updateJumlahTotal();
                updateHargaTotal();
            }
        });

        plusButton2.setOnClickListener(v -> {
            jumlahItem2++;
            textJumlah2.setText(String.valueOf(jumlahItem2));
            updateJumlahTotal();
            updateHargaTotal();
        });

        minusButton2.setOnClickListener(v -> {
            if (jumlahItem2 > 0) {
                jumlahItem2--;
                textJumlah2.setText(String.valueOf(jumlahItem2));
                updateJumlahTotal();
                updateHargaTotal();
            }
        });

        Button beliButton = findViewById(R.id.Beli);
        beliButton.setOnClickListener(v -> {
            Intent intent = new Intent(menu.this, purchase.class);

            // Kirim data donat Matcha
            intent.putExtra("hargaDonat1", hargaPerDonat1);
            intent.putExtra("jumlahDonat1", jumlahItem1);
            intent.putExtra("gambarDonat1", R.drawable.macha); // ID gambar dari drawable

            // Kirim data donat Strawberry
            intent.putExtra("hargaDonat2", hargaPerDonat2);
            intent.putExtra("jumlahDonat2", jumlahItem2);
            intent.putExtra("gambarDonat2", R.drawable.strawberry); // ID gambar dari drawable

            // Kirim total jumlah dan harga
            intent.putExtra("totalJumlah", jumlahItem1 + jumlahItem2);
            intent.putExtra("totalHarga", (jumlahItem1 * hargaPerDonat1) + (jumlahItem2 * hargaPerDonat2));

            startActivity(intent);
        });

        homeImageView.setOnClickListener(view -> {
            Intent intent = new Intent(menu.this, homepage.class);
            startActivity(intent);
        });

        profileImageView.setOnClickListener(view -> {
            Intent intent = new Intent(menu.this, profile.class);
            startActivity(intent);
        });
    }

    private void updateJumlahTotal() {
        int totalJumlah = jumlahItem1 + jumlahItem2;
        textJumlahBayar.setText(String.valueOf(totalJumlah));
    }

    private void updateHargaTotal() {
        int totalHarga = (jumlahItem1 * hargaPerDonat1) + (jumlahItem2 * hargaPerDonat2);
        textHargaBayar.setText("Rp " + totalHarga);
    }
}
