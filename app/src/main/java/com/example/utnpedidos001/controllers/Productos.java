package com.example.utnpedidos001.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.utnpedidos001.AdminDB;
import com.example.utnpedidos001.classes.Producto;

public class Productos {

    private AdminDB Admin;
    private SQLiteDatabase Db;

    public Productos(Context context) {
        String nombreBD = "pedidosUTN";
        int version = 1;
        Admin = new AdminDB(context, nombreBD, null, version);
    }

    public Producto[] selectAllProductos() {
        Db = Admin.getReadableDatabase();
        Cursor Cursor = Db.rawQuery("SELECT * FROM productos ORDER BY descripcion", null);
        if (Cursor.getCount() <= 0) {
            return null;
        }
        Producto[] ps = new Producto[Cursor.getCount()];
        Producto p;
        int i = 0;
        while (Cursor.moveToNext()) {
            p = new Producto();
            p.prod_id = Cursor.getInt(0);
            p.codigo = Cursor.getString(1);
            p.descripcion = Cursor.getString(2);
            p.precio = Cursor.getFloat(3);
            ps[i++] = p;
        }
        return ps;
    }

    public Producto selectProductoById(String codigo) {
        Db = Admin.getReadableDatabase();
        Cursor Cursor = Db.rawQuery("SELECT * FROM productos WHERE codigo=" + codigo + " ORDER BY descripcion", null);
        if (Cursor.getCount() <= 0) {
            return null;
        }
        Producto p = new Producto();
        Cursor.moveToFirst();
        p.prod_id = Cursor.getInt(0);
        p.codigo = Cursor.getString(1);
        p.descripcion = Cursor.getString(2);
        p.precio = Cursor.getFloat(3);
        return p;
    }

    public Producto insertProducto(String codigo, String descripcion, float precio) {
        Db = Admin.getWritableDatabase();
        ContentValues Registro = new ContentValues();
        Registro.put("codigo", codigo);
        Registro.put("descripcion", descripcion);
        Registro.put("precio", precio);
        try {
            Db.insert("productos", null, Registro);
        } catch (Exception e) {
            System.out.print(e);
        }
        Db.close();
        Producto p = new Producto();
        p.codigo = codigo;
        p.descripcion = descripcion;
        p.precio = precio;
        return p;
    }

    public Producto updateProducto(int id, String codigo, String descripcion, float precio) {
        Db = Admin.getWritableDatabase();
        ContentValues Registro = new ContentValues();
        Registro.put("codigo", codigo);
        Registro.put("descripcion", descripcion);
        Registro.put("precio", precio);
        try {
            Db.update("productos", Registro, "prod_id=" + id, null);
        } catch (Exception e) {
            System.out.print(e);
        }
        Db.close();
        Producto p = new Producto();
        p.prod_id = id;
        p.codigo = codigo;
        p.descripcion = descripcion;
        p.precio = precio;
        return p;
    }

    public void deleteProducto(String codigo) {
        Db = Admin.getWritableDatabase();
        try {
            Db.delete("productos", "codigo=" + codigo, null);
        } catch (Exception e) {
            System.out.print(e);
        }
        Db.close();
    }
}
