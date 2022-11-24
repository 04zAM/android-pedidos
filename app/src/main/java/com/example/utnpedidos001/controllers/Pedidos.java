package com.example.utnpedidos001.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.utnpedidos001.AdminDB;
import com.example.utnpedidos001.classes.Pedido;
import com.example.utnpedidos001.classes.Pedido_Detalle;

public class Pedidos {

    private AdminDB Admin;
    private SQLiteDatabase Db;

    public Pedidos(Context context, String nombreBD, int version) {
        Admin = new AdminDB(context, nombreBD, null, version);
    }

    Productos Ps;

    public Pedido[] selectAllPedidos() {
        Db = Admin.getReadableDatabase();
        Cursor Cursor = Db.rawQuery("SELECT * FROM pedidos ORDER BY fecha_registro DESC", null);
        if (Cursor.getCount() <= 0) {
            return null;
        }
        Pedido[] ps = new Pedido[Cursor.getCount()];
        Pedido p;
        int i = 0;
        while (Cursor.moveToNext()) {
            p = new Pedido();
            p.ped_id = Cursor.getInt(0);
            p.fecha_registro = Cursor.getString(1);
            p.cli_id = Cursor.getInt(2);
            ps[i++] = p;
        }
        return ps;
    }

    public Pedido selectPedidoeById(int id) {
        Db = Admin.getReadableDatabase();
        Cursor Cursor = Db.rawQuery("SELECT * FROM pedidos WHERE ped_id=" + id + " ORDER BY fecha_registro DESC", null);
        if (Cursor.getCount() <= 0) {
            return null;
        }
        Pedido p = new Pedido();
        Cursor.moveToFirst();
        p.ped_id = Cursor.getInt(0);
        p.fecha_registro = Cursor.getString(1);
        p.cli_id = Cursor.getInt(2);
        return p;
    }

    public Pedido insertPedido(int id, String fecha_registro, int cli_id, Pedido_Detalle[] detalles) {
        Db = Admin.getWritableDatabase();
        ContentValues Registro = new ContentValues();
        Registro.put("ped_id", id);
        Registro.put("fecha_registro", fecha_registro);
        Registro.put("cli_id", cli_id);
        ContentValues RegistroDetalles = new ContentValues();
        for (Pedido_Detalle detalle: detalles) {
            RegistroDetalles.put("ped_det_id", detalle.ped_det_id);
            RegistroDetalles.put("ped_id", id);
            RegistroDetalles.put("prod_id", detalle.prod_id);
            RegistroDetalles.put("cantidad", detalle.cantidad);
            //Producto p = Ps.selectProductoById(detalle.prod_id);
            //RegistroDetalles.put("subtotal", detalle.cantidad * p.precio);
        }
        try {
            Db.insert("pedidos", null, Registro);
            Db.insert("pedido_detalles", null, RegistroDetalles);
        } catch (Exception e) {
            System.out.print(e);
        }
        Db.close();
        Pedido p = new Pedido();
        p.ped_id = id;
        p.fecha_registro = fecha_registro;
        p.cli_id = cli_id;
        return p;
    }
}
