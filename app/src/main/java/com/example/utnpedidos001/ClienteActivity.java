package com.example.utnpedidos001;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ClienteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);
        this.setTitle("Cliente #");
    }
}