package com.example.mydataapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText etUsername,etPassword;
    Button BtnLogin,BtnCancel;

    public static final String MyPREFERENCE = "mypreference";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        etUsername = (EditText) findViewById(R.id.Username);
        etPassword = (EditText) findViewById(R.id.Password);
        BtnLogin = (Button) findViewById(R.id.BtnLogin);
        BtnCancel = (Button) findViewById(R.id.BtnCancel);

        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u = etUsername.getText().toString();
                String p = etPassword.getText().toString();
                if (u.isEmpty() || p.isEmpty()) {
                    showErrorLogin();
                } else {
                    if (u.equals("admin") && p.equals("admin123")){
                        successLogin();
                    } else {
                        errorLogin();
                    }
                }
            }
        });

        BtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etUsername.setText("");
                etPassword.setText("");
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void showErrorLogin(){
        Toast.makeText(this,"User dan Password Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
    }

    public void successLogin(){
        sharedPreferences = getSharedPreferences(MyPREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String u = etUsername.getText().toString().trim();
        editor.putBoolean("isLoggedIn",true);
        editor.putString("loggedUsername", u);
        editor.apply();
        Intent HalDashboard = new Intent(MainActivity.this,MainActivity2.class);
        startActivity(HalDashboard);
        Toast.makeText(this,"Selamat Datang", Toast.LENGTH_SHORT).show();
    }

    public void errorLogin(){
        Toast.makeText(this,"Username dan Password Salah", Toast.LENGTH_SHORT).show();
    }
}