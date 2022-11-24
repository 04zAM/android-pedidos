package com.example.utnpedidos001;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText user, password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = findViewById(R.id.txtUser);
        password = findViewById(R.id.txtPass);
        login = findViewById(R.id.btnLogin);
    }

    public void cmdLogin_onClick (View v){
        if(user.getText().toString().equals("utn") && password.getText().toString().equals("admin")){
            Intent intent = new Intent(this,PedidosActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Bienvenido!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }
}