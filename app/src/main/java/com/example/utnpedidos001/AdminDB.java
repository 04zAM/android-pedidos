package com.example.utnpedidos001;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminDB extends SQLiteOpenHelper {

    public AdminDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS clientes(cli_id int primary key autoincrement, identificacion text(20), nombres text(200), correo text(100))"
        );
        sqLiteDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS productos(prod_id int primary key autoincrement, codigo text(20), descripcion text(20), precio real)"
        );
        sqLiteDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS pedidos(ped_id int primary key autoincrement, fecha_registro text(20), cli_id int)"
        );
        sqLiteDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS pedido_detalles(ped_det_id int primary key autoincrement, prod_id int, ped_id int, cantidad int, subtotal real)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
