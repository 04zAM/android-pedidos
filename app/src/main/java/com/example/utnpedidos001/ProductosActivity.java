package com.example.utnpedidos001;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.utnpedidos001.classes.Producto;
import com.example.utnpedidos001.controllers.Productos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductosActivity extends AppCompatActivity {

    FloatingActionButton nuevoProducto;
    RecyclerView recyclerView;
    List<Producto> listaProductos;
    AdaptadorProductos adaptadorProductos;
    Productos controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        this.setTitle("Productos");

        nuevoProducto = findViewById(R.id.btnNuevoProducto);
        recyclerView = findViewById(R.id.listaProductos);

        listaProductos = new ArrayList<>();
        adaptadorProductos = new AdaptadorProductos(listaProductos);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adaptadorProductos);
        refrescarLista();
    }

    public void refrescarLista() {
        if (adaptadorProductos == null) return;
        listaProductos = Arrays.asList(controller.selectAllProductos());
        adaptadorProductos.setListaProductos(listaProductos);
        adaptadorProductos.notifyDataSetChanged();
    }

    public void cmdNuevoProducto_onClick (View v){
        Intent intent = new Intent(this,ProductoActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Nuevo Producto", Toast.LENGTH_SHORT).show();
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