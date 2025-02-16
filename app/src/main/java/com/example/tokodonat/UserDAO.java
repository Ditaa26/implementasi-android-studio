package com.example.tokodonat;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private SQLiteDatabase database;
    private DBHelper dbHelper;

    public UserDAO(Context context) {
        dbHelper = new DBHelper(context);  // Pastikan context diberikan di sini
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long insertUser(String name, String email, String phone, String address) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("email", email);
        values.put("phone", phone);
        values.put("address", address);

        // Menambahkan log untuk memeriksa data yang akan disimpan
        Log.d("UserDAO", "Inserting user: " + name + ", " + email);

        return database.insert("users", null, values);
    }

    public int updateUser(int id, String name, String email, String phone, String address) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("email", email);
        values.put("phone", phone);
        values.put("address", address);

        // Menambahkan log untuk memeriksa data yang akan diperbarui
        Log.d("UserDAO", "Updating user: " + name + ", " + email);

        // Update record dengan ID yang sesuai
        return database.update("users", values, "id = ?", new String[]{String.valueOf(id)});
    }


    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Cursor cursor = database.query("users", null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
                String phone = cursor.getString(cursor.getColumnIndexOrThrow("phone"));
                String address = cursor.getString(cursor.getColumnIndexOrThrow("address"));
                users.add(new User(id, name, email, phone, address));
            } while (cursor.moveToNext());
            cursor.close();
        }

        Log.d("UserDAO", "Fetched " + users.size() + " users");
        return users;
    }
}
