package com.example.mydataapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    TextView tvWelcome;
    EditText etNama,etNIM,etProdi,etAlamat,etKelas,etEmail;
    Button BtnTambah, BtnLogout;
    ListView ListView;
    ArrayList<String> dataMahasiswa = new ArrayList<>();
    ArrayAdapter<String> adapter;
    public static final String MyPREFERENCE = "mypreference";
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        BtnTambah = (Button) findViewById(R.id.BtnTambah);
        BtnLogout = (Button) findViewById(R.id.BtnLogout);
        tvWelcome     = findViewById(R.id.tvWelcome);
        etNIM      = findViewById(R.id.NIM);
        etNama        = findViewById(R.id.Nama);
        etProdi       = findViewById(R.id.Prodi);
        etKelas       = findViewById(R.id.Kelas);
        etAlamat      = findViewById(R.id.Alamat);
        etEmail       = findViewById(R.id.Email);
        BtnTambah = findViewById(R.id.BtnTambah);
        BtnLogout     = findViewById(R.id.BtnLogout);
        ListView = findViewById(R.id.ListView);

        sharedPreferences = getSharedPreferences(MyPREFERENCE, Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("loggedUsername", "Pengguna");
        tvWelcome.setText("Selamat Datang, " + username + "!");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataMahasiswa);
        ListView.setAdapter(adapter);

        BtnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nim    = etNIM.getText().toString().trim();
                String nama   = etNama.getText().toString().trim();
                String prodi  = etProdi.getText().toString().trim();
                String kelas  = etKelas.getText().toString().trim();
                String alamat = etAlamat.getText().toString().trim();
                String email  = etEmail.getText().toString().trim();

                // Validasi
                if (nim.isEmpty() || nama.isEmpty() || prodi.isEmpty() ||
                        kelas.isEmpty() || alamat.isEmpty() || email.isEmpty()) {
                    Toast.makeText(MainActivity2.this,
                            "Semua field harus diisi!", Toast.LENGTH_SHORT).show();
                    return;
                }

                String dataFormatted =
                        "NIM    : " + nim    + "\n" +
                        "Nama   : " + nama   + "\n" +
                        "Prodi  : " + prodi  + "\n" +
                        "Kelas  : " + kelas  + "\n" +
                        "Alamat : " + alamat + "\n" +
                        "Email  : " + email;

                dataMahasiswa.add(dataFormatted);
                adapter.notifyDataSetChanged();

                // Reset form
                etNIM.setText("");
                etNama.setText("");
                etProdi.setText("");
                etKelas.setText("");
                etAlamat.setText("");
                etEmail.setText("");

                Toast.makeText(MainActivity2.this,
                        "Data berhasil ditambahkan!", Toast.LENGTH_SHORT).show();
            }
        });
        BtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = getSharedPreferences(MyPREFERENCE, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isLoggedIn", false);
                editor.apply();
                Intent HalLogin = new Intent(MainActivity2.this,MainActivity.class);
                startActivity(HalLogin);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}