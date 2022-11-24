package com.example.utnpedidos001.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.utnpedidos001.AdminDB;
import com.example.utnpedidos001.classes.Cliente;

public class Clientes {

    private AdminDB Admin;
    private SQLiteDatabase Db;

    public Clientes(Context context, String nombreBD, int version) {
        Admin = new AdminDB(context, nombreBD, null, version);
    }

    public Cliente[] selectAllClientes() {
        Db = Admin.getReadableDatabase();
        Cursor Cursor = Db.rawQuery("SELECT * FROM clientes ORDER BY nombres", null);
        if (Cursor.getCount() <= 0) {
            return null;
        }
        Cliente[] ps = new Cliente[Cursor.getCount()];
        Cliente p;
        int i = 0;
        while (Cursor.moveToNext()) {
            p = new Cliente();
            p.cli_id = Cursor.getInt(0);
            p.identificacion = Cursor.getString(1);
            p.nombres = Cursor.getString(2);
            p.correo = Cursor.getString(3);
            ps[i++] = p;
        }
        return ps;
    }

    public Cliente selectClienteById(int id) {
        Db = Admin.getReadableDatabase();
        Cursor Cursor = Db.rawQuery("SELECT * FROM clientes WHERE cli_id=" + id + " ORDER BY nombres", null);
        if (Cursor.getCount() <= 0) {
            return null;
        }
        Cliente p = new Cliente();
        Cursor.moveToFirst();
        p.cli_id = Cursor.getInt(0);
        p.identificacion = Cursor.getString(1);
        p.nombres = Cursor.getString(2);
        p.correo = Cursor.getString(3);
        return p;
    }

    public Cliente insertCliente(int id, String identificacion, String nombres, String correo) {
        Db = Admin.getWritableDatabase();
        ContentValues Registro = new ContentValues();
        Registro.put("cli_id", id);
        Registro.put("identificacion", identificacion);
        Registro.put("nombres", nombres);
        Registro.put("correo", correo);
        try {
            Db.insert("clientes", null, Registro);
        } catch (Exception e) {
            System.out.print(e);
        }
        Db.close();
        Cliente p = new Cliente();
        p.cli_id = id;
        p.identificacion = identificacion;
        p.nombres = nombres;
        p.correo = correo;
        return p;
    }

    public Cliente updateCliente(int id, String identificacion, String nombres, String correo) {
        Db = Admin.getWritableDatabase();
        ContentValues Registro = new ContentValues();
        Registro.put("identificacion", identificacion);
        Registro.put("nombres", nombres);
        Registro.put("correo",correo);
        try {
            Db.update("clientes", Registro, "cli_id=" + id, null);
        } catch (Exception e) {
            System.out.print(e);
        }
        Db.close();
        Cliente p = new Cliente();
        p.cli_id = id;
        p.identificacion = identificacion;
        p.nombres = nombres;
        p.correo = correo;
        return p;
    }

    public void deleteCliente(int id) {
        Db = Admin.getWritableDatabase();
        try {
            Db.delete("clientes", "cli_id=" + id, null);
        } catch (Exception e) {
            System.out.print(e);
        }
        Db.close();
    }
}
