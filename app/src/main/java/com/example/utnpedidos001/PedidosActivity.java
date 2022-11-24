package com.example.utnpedidos001;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PedidosActivity extends AppCompatActivity {

    FloatingActionButton nuevoPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);
        this.setTitle("Pedidos");

        nuevoPedido = findViewById(R.id.btnNuevoPedido);
    }

    public void cmdNuevoPedido_onClick (View v){
        Intent intent = new Intent(this,PedidoActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Nuevo Pedido", Toast.LENGTH_SHORT).show();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuPedidos:
                toPedidos();
                return true;
            case R.id.menuClientes:
                toClientes();
                return true;
            case R.id.menuProductos:
                toProductos();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void toPedidos(){
        Intent intent = new Intent(this, PedidosActivity.class);
        startActivity(intent);
    }
    private void toClientes(){
        Intent intent = new Intent(this, ClientesActivity.class);
        startActivity(intent);
    }
    private void toProductos(){
        Intent intent = new Intent(this, ProductosActivity.class);
        startActivity(intent);
    }
}