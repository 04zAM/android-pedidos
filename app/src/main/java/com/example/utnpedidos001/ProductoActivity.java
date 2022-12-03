package com.example.utnpedidos001;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.utnpedidos001.controllers.Productos;

public class ProductoActivity extends AppCompatActivity {

    EditText txtCodigo, txtDescripcion, txtPrecio;
    String codigo, descripcion;
    Float precio;
    Productos productosController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);
        this.setTitle("Producto");
        productosController = new Productos(this);

        txtCodigo = findViewById(R.id.txtCodigoProducto);
        txtDescripcion = findViewById(R.id.txtDescProdcuto);
        txtPrecio = findViewById(R.id.txtPrecioProducto);
    }

    public void cmdInsertPro_OnClick(View view) {
        codigo = txtCodigo.getText().toString();
        descripcion = txtDescripcion.getText().toString();
        precio = Float.parseFloat(txtPrecio.getText().toString());
        try{
            productosController.insertProducto(codigo, descripcion, precio);
            Toast.makeText(this, "Producto Ingresado", Toast.LENGTH_LONG).show();
            finish();
        }catch (Exception ex){
            Log.e("Error", ex.getMessage());
            Toast.makeText(this, "Producto No ingresado", Toast.LENGTH_LONG).show();
        }
        txtCodigo.setText("");
        txtDescripcion.setText("");
        txtPrecio.setText("");
    }
}