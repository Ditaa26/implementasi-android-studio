package com.example.tokodonat;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.content.Intent;
import java.util.List;


public class profile extends AppCompatActivity {
    private EditText userNameInput, emailInput, nameInput, phoneInput, addressInput;
    private Button saveButton;
    private ImageView backButton;
    private UserDAO userDAO;

    private void displayProfileData() {
        // Bersihkan semua EditText sebelum menampilkan data baru
        userNameInput.setText("");  // Bersihkan nama pengguna
        emailInput.setText("");     // Bersihkan email
        nameInput.setText("");      // Bersihkan nama
        phoneInput.setText("");     // Bersihkan nomor telepon
        addressInput.setText("");   // Bersihkan alamat

        // Ambil data yang sudah disimpan dari UserDAO
        List<User> users = userDAO.getAllUsers();
        if (users != null && !users.isEmpty()) {
            User user = users.get(0); // Ambil data pengguna pertama
            userNameInput.setText(user.getName() != null ? user.getName() : "");
            emailInput.setText(user.getEmail() != null ? user.getEmail() : "");
            nameInput.setText(user.getName() != null ? user.getName() : "");
            phoneInput.setText(user.getPhone() != null ? user.getPhone() : "");
            addressInput.setText(user.getAddress() != null ? user.getAddress() : "");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile2);

        // Inisialisasi elemen UI
        backButton = findViewById(R.id.backButton);
        userNameInput = findViewById(R.id.userName);
        emailInput = findViewById(R.id.emailInput);
        nameInput = findViewById(R.id.nameInput);
        phoneInput = findViewById(R.id.nomortelponInput);
        addressInput = findViewById(R.id.alamatInput);
        saveButton = findViewById(R.id.loginButton);

        // Inisialisasi UserDAO
        userDAO = new UserDAO(this);
        userDAO.open();  // Membuka koneksi ke database

        // Menyimpan data pengguna ketika tombol save diklik
        saveButton.setOnClickListener(v -> {
            String userName = userNameInput.getText().toString();
            String email = emailInput.getText().toString();
            String name = nameInput.getText().toString();
            String phone = phoneInput.getText().toString();
            String address = addressInput.getText().toString();

            // Validasi data sebelum disimpan
            if (userName.isEmpty() || email.isEmpty() || name.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                Toast.makeText(profile.this, "Semua kolom harus diisi", Toast.LENGTH_SHORT).show();
            } else {
                // Cek apakah ada data pengguna yang sudah ada
                List<User> users = userDAO.getAllUsers();

                if (users != null && !users.isEmpty()) {
                    // Jika ada pengguna yang sudah ada, update data pengguna pertama (misalnya)
                    User existingUser = users.get(0);  // Mengambil pengguna pertama
                    int userId = existingUser.getId();
                    userDAO.updateUser(userId, userName, email, phone, address);
                } else {
                    // Jika tidak ada pengguna yang ada, tambahkan pengguna baru
                    userDAO.insertUser(userName, email, phone, address);
                }

                // Memberi feedback ke pengguna
                Toast.makeText(profile.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();

                // Pindah ke halaman ProfileActivity untuk melihat data yang disimpan
                Intent intent = new Intent(profile.this, profile.class);
                startActivity(intent);
                finish();
            }
        });


        // Tampilkan data jika sudah ada
        displayProfileData();

        // Menambahkan aksi untuk tombol kembali
        backButton.setOnClickListener(v -> {
            // Intent untuk kembali ke MainActivity (Halaman Utama)
            Intent intent = new Intent(profile.this, homepage.class);
            startActivity(intent);
            finish();  // Menutup activity profile agar tidak ada di stack kembali
        });
    }
}
