package com.example.tokodonat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class purchase extends AppCompatActivity {

    private ImageView tombolKembali, donutImageMacha, donutImageStrawberry;
    private TextView teksDonatMacha, jumlahBeliMacha, teksDonatStrawberry, jumlahBeliStrawberry,
            teksTotalPesanan, totalPesananSemua, teksLanjut;
    private LinearLayout layoutDonatMacha, layoutDonatStrawberry;
    private CheckBox cbCOD;
    private ImageView homeImageView, profileImageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        // Inisialisasi UI
        initUI();

        // Ambil data dari Intent
        Intent intent = getIntent();

        // Data Donat Matcha
        int hargaDonat1 = intent.getIntExtra("hargaDonat1", 0);
        int jumlahDonat1 = intent.getIntExtra("jumlahDonat1", 0);
        int gambarDonat1 = intent.getIntExtra("gambarDonat1", 0);

        // Data Donat Strawberry
        int hargaDonat2 = intent.getIntExtra("hargaDonat2", 0);
        int jumlahDonat2 = intent.getIntExtra("jumlahDonat2", 0);
        int gambarDonat2 = intent.getIntExtra("gambarDonat2", 0);

        // Total Data
        int totalJumlah = intent.getIntExtra("totalJumlah", jumlahDonat1 + jumlahDonat2);
        int totalHarga = intent.getIntExtra("totalHarga", (hargaDonat1 * jumlahDonat1) + (hargaDonat2 * jumlahDonat2));

        // Tampilkan data Donat Matcha jika ada
        if (jumlahDonat1 > 0) {
            donutImageMacha.setImageResource(gambarDonat1);
            teksDonatMacha.setText("Rp" + hargaDonat1);
            jumlahBeliMacha.setText(String.valueOf(jumlahDonat1));
        } else {
            layoutDonatMacha.setVisibility(View.GONE); // Sembunyikan layout jika tidak dipilih
        }

        // Tampilkan data Donat Strawberry jika ada
        if (jumlahDonat2 > 0) {
            donutImageStrawberry.setImageResource(gambarDonat2);
            teksDonatStrawberry.setText("Rp" + hargaDonat2);
            jumlahBeliStrawberry.setText(String.valueOf(jumlahDonat2));
        } else {
            layoutDonatStrawberry.setVisibility(View.GONE); // Sembunyikan layout jika tidak dipilih
        }

        // Set total pesanan
        teksTotalPesanan.setText(getString(R.string.total_pesanan, totalJumlah));
        totalPesananSemua.setText(getString(R.string.total_harga, totalHarga));

        // Tombol kembali
        tombolKembali.setOnClickListener(view -> finish());

        // Tombol lanjut
        teksLanjut.setOnClickListener(view -> {
            if (cbCOD.isChecked()) {
                // Jika CheckBox sudah dicentang, lanjutkan ke halaman berikutnya
                Intent closingIntent = new Intent(purchase.this, closing.class);
                closingIntent.putExtra("totalJumlah", totalJumlah);
                closingIntent.putExtra("totalHarga", totalHarga);
                startActivity(closingIntent);
            }
        });
        findViewById(R.id.home_image_view3).setOnClickListener(view -> {
            Intent homeIntent = new Intent(purchase.this, homepage.class); // Ganti HomeActivity dengan aktivitas yang sesuai
            startActivity(homeIntent);
        });

        // Tombol Profile
        findViewById(R.id.profile_image_view3).setOnClickListener(view -> {
            Intent profileIntent = new Intent(purchase.this, profile.class); // Ganti ProfileActivity dengan aktivitas yang sesuai
            startActivity(profileIntent);
        });
    }

    private void initUI() {
        tombolKembali = findViewById(R.id.back_button4);
        donutImageMacha = findViewById(R.id.donutImageView6);
        donutImageStrawberry = findViewById(R.id.donutImageViewStrawberry);
        teksDonatMacha = findViewById(R.id.jumlahHargaBayarMacha);
        jumlahBeliMacha = findViewById(R.id.jumlahBeliMacha);
        teksDonatStrawberry = findViewById(R.id.jumlahHargaBayarStrawberry);
        jumlahBeliStrawberry = findViewById(R.id.jumlahBeliStrawberry);
        teksTotalPesanan = findViewById(R.id.totalPesanan);
        totalPesananSemua = findViewById(R.id.totalPesananSemua);
        teksLanjut = findViewById(R.id.nexttextview);
        layoutDonatMacha = findViewById(R.id.LinearPembayaran);
        layoutDonatStrawberry = findViewById(R.id.LinearPembayaran2);
        cbCOD = findViewById(R.id.cb_cashOnDelivery);
        homeImageView = findViewById(R.id.home_image_view2);
        profileImageView = findViewById(R.id.profile_image_view2);

    }
}
